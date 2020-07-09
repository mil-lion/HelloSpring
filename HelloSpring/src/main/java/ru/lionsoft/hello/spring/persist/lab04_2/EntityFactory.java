/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.lionsoft.hello.spring.persist.lab04_2;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 *
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
public class EntityFactory {
    
    // ********************* Singletone *******************
    
    private EntityFactory() {
    }
    
    public static EntityFactory getInstance() {
        return EntityFactoryHolder.INSTANCE;
    }
    
    private static class EntityFactoryHolder {

        private static final EntityFactory INSTANCE = new EntityFactory();
    }
    
    // ******************** Public Methods **********************
    
    public <T> T createEntity(ResultSet rs, Class<T> entityClass) throws SQLException {
        T entity = null;
        try {
            entity = entityClass.getConstructor().newInstance();
            EntityDescription entityDescr = getEntityDescription(entityClass);
            for (Map.Entry<Field, String> entry : entityDescr.columnMap.entrySet()) {
                final Field field = entry.getKey();
                final String columnName = entry.getValue();
                field.setAccessible(true);
                field.set(entity, rs.getObject(columnName));
            }
        } catch (IllegalAccessException | IllegalArgumentException 
                | InstantiationException | InvocationTargetException 
                | NoSuchMethodException | SecurityException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        return entity;
    }

    public String getEntityTableName(Class entityClass) {
        return getEntityDescription(entityClass).tableName;
    }
    
    public String getIdColumnName(Class entityClass) {
        EntityDescription entityDescr = getEntityDescription(entityClass);
        return entityDescr.columnMap.get(entityDescr.idField);
    }
    
    public String getDeleteSqlText(Object entity) {
        EntityDescription entityDescr = getEntityDescription(entity.getClass());
        String sqlText = null;
        try {
            entityDescr.idField.setAccessible(true);
            sqlText = "DELETE FROM " + entityDescr.tableName 
                + " WHERE " + entityDescr.idColumnName
                + " = " + entityDescr.idField.get(entity); // TODO string ''
        } catch (IllegalAccessException | IllegalArgumentException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        return sqlText;
    }
    
    // ***************** Private Methods **********************
    
    private class EntityDescription {
        final Class entityClass;
        String tableName;
        Field idField;
        String idColumnName;
        final Map<Field, String> columnMap = new TreeMap<>((f1, f2) -> f1.getName().compareTo(f2.getName()));

        public EntityDescription(Class entityClass) {
            this.entityClass = entityClass;
            tableName = entityClass.getSimpleName().toUpperCase();
        }

        @Override
        public String toString() {
            return "EntityDescription{" 
                    + "entityClass=" + entityClass.getSimpleName()
                    + ", tableName=" + tableName 
                    + ", idField=" + (idField == null ? "null" : idField.getName())
                    + ", columnMap=" + columnMap.entrySet().stream()
                            .map((e) -> e.getKey().getName() + "=" + e.getValue())
                            .collect(Collectors.joining(", ", "{", "}"))
                    + '}';
        }
    }
    
    private final Map<Class, EntityDescription> entityCache = new HashMap<>();
    
    private EntityDescription getEntityDescription(Class entityClass) {
        EntityDescription entityDescr = entityCache.get(entityClass);
        if (entityDescr != null) return entityDescr;
        synchronized(this) {
            entityDescr = new EntityDescription(entityClass);
            // table name
            Table annTable = (Table) entityClass.getAnnotation(Table.class);
            if (annTable != null) {
                entityDescr.tableName = annTable.name();
            }
            // column map
            for (Field field : entityClass.getDeclaredFields()) { // TODO иерархия классов не работает
                Column annColumn = field.getAnnotation(Column.class);
                if (annColumn == null) continue; // skip filed
                String columnName = field.getName().toUpperCase();
                if (!annColumn.name().isEmpty()) {
                    columnName = annColumn.name();
                }
                entityDescr.columnMap.put(field, columnName);
                // id
                Id annId = field.getAnnotation(Id.class);
                if (annId != null) {
                    entityDescr.idField = field;
                    entityDescr.idColumnName = columnName;
                }
            }
            entityCache.put(entityClass, entityDescr);
            System.out.println("Add to cache: " + entityDescr);
        }
        return entityDescr;
    }
}
