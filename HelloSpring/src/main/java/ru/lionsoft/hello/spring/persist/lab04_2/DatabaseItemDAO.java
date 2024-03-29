/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitablity for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-12 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.persist.lab04_2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.stereotype.Component;

/**
 *
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
@Component
public class DatabaseItemDAO implements ItemDAO {
    
    @Resource(name = "sampleDataSource") // field injection
    private DataSource dataSource;
    
    private final EntityFactory entityFactory = EntityFactory.getInstance();

    // ************************* Business Methods ***********************
            
    @Override
    public MusicItem get(Long id) {
        MusicItem item = null;
        try (Connection connection = dataSource.getConnection();) {
            try (PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM items WHERE item_id=?");) {
                pstmt.setLong(1, id);
                try (ResultSet rs = pstmt.executeQuery();) {
                    if (rs.next()) {
                        item = entityFactory.createEntity(rs, MusicItem.class);
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        return item;
    }

    @Override
    public Collection<MusicItem> searchByArtistTitle(String keyword) {
        Collection<MusicItem> items = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();) {
            String sqlText = "SELECT * FROM items WHERE title LIKE '%" + keyword + "%' OR artist LIKE '%" + keyword + "%'";
            try (Statement stmt = connection.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(sqlText);) {
                    while (rs.next()) {
                        items.add(entityFactory.createEntity(rs, MusicItem.class));
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        return items;
    }
}
