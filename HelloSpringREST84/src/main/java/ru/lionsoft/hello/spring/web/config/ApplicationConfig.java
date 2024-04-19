/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2024 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.web.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Настройки Spring приложения
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:app.properties")
public class ApplicationConfig {
    
    @Autowired
    private Environment env; // from app.properties

    // ************** Beans *******************
    
    @Bean("sampleDataSource")
    public DataSource sampleDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("db.driverClass"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        
        return dataSource;
    }
        
    @Bean
    public JpaVendorAdapter createHibernameVendorAdapter() {
        HibernateJpaVendorAdapter bean = new HibernateJpaVendorAdapter();
        
        bean.setDatabasePlatform("org.hibernate.dialect.DerbyDialect");
        bean.setShowSql(true);
        bean.setGenerateDdl(false);
        
        return bean;
    }
    
    @Bean("localEMFactory")
    public LocalContainerEntityManagerFactoryBean createEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        
        bean.setDataSource(sampleDataSource());
        bean.setPersistenceUnitName("SamplePU");
        bean.setJpaVendorAdapter(createHibernameVendorAdapter());
        
        return bean;
    }
    
    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager(createEntityManagerFactory().getObject());
        return transactionManager;
    }
    
    // ************** Properties **************
    
    public int getMaxSearchResults() {
        return env.getProperty("catalog.maxSearchResults", Integer.class);
    }
    
}
