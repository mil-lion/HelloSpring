/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.lionsoft.hello.spring.persist.lab04_1;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
@Configuration
@ComponentScan(basePackages = "ru.lionsoft.hello.spring.persist.lab04_1")
//@ImportResource("classpath:ru/**/lab04_1/*.xml")
@PropertySource("classpath:ru/lionsoft/hello/spring/persist/lab04_1/app.properties")
public class ApplicationConfig {
    
    @Autowired
    private Environment env; // from app.properties
    
    @Autowired
    private ItemDAO dao;
    
    @Bean("catalog")
    public Catalog createCatalog() {
        JavaTunesCatalog catalog = new JavaTunesCatalog(dao);
        catalog.setMaxSearchResults(env.getProperty("catalog.maxSearchResults", Integer.class));
        return catalog;
    }
    
    @Bean("sampleDataSource")
    public DataSource createDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("db.driverClass"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }
}
