/*
/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.persist.lab04_1;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
@Configuration
//@ComponentScan(basePackages = "ru.lionsoft.hello.spring.persist.lab04_1")
@PropertySource("classpath:ru/lionsoft/hello/spring/persist/lab04_1/app.properties")
public class ApplicationConfig {
    
    @Autowired
    private Environment env; // from app.properties
 
    // ******************* Beans ************************
    
    @Bean("sampleDataSource")
    public DataSource createDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.apache.derby.jdbc.ClientDriver");
//        dataSource.setUrl("jdbc:derby://localhost:1527/sample");
//        dataSource.setUsername("app");
//        dataSource.setPassword("app");
        dataSource.setDriverClassName(env.getProperty("db.driverClass"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }
    
//    @Autowired
//    private ItemDAO dao;
//    
//    @Bean("catalog")
//    public Catalog createCatalog() {
//        JavaTunesCatalog catalog = new JavaTunesCatalog(dao);
//        catalog.setMaxSearchResults(getCatalogMaxSearchResults());
//        return catalog;
//    }
    
    // ******************* Properties ***********************
    
    public int getCatalogMaxSearchResults() {
        return env.getProperty("catalog.maxSearchResults", Integer.class);
    }
}
