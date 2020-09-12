/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.web.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Настройки Spring приложения
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
@Configuration
@PropertySource("classpath:app.properties")
public class ApplicationConfig {
    
    @Autowired
    private Environment env; // from app.properties
    
    // ***************** Beans *********************
    
    @Bean
    public DataSource sampleDS() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("db.driverClass"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }
    
    // ************ Properties *****************
    
    public Integer getCatalogMaxSearchResults() {
        return env.getProperty("catalog.maxSearchResults", Integer.class);
    }
}
