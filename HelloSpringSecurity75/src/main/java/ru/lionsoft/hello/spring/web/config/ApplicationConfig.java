/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-12 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.web.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Spring application configuration
 * @author Igor Morenko <morenko at lionsoft.ru>
 */
@Configuration
@PropertySource("classpath:app.properties")
//@EnableTransactionManagement
public class ApplicationConfig {
    
    @Autowired
    private Environment env;
    
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("db.driver"));
        dataSource.setUrl(env.getProperty("db.jdbcUrl"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }

    @Bean
    public JpaVendorAdapter hibernateJpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(true);
//        jpaVendorAdapter.setGenerateDdl(true);
        return jpaVendorAdapter;
    }
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean emfBean = new LocalContainerEntityManagerFactoryBean();
        
        emfBean.setDataSource(dataSource);
        emfBean.setJpaVendorAdapter(jpaVendorAdapter);
        emfBean.setPersistenceUnitName("SamplePU");
        emfBean.setPackagesToScan("ru.lionsoft.hello.spring.web.entity");
               
        Properties jpaProperties = new Properties();
        jpaProperties.put("javax.persistence.schema-generation.database.action", "create");
        emfBean.setJpaProperties(jpaProperties);
        
        return emfBean;
    }
    
    // ******************** Properties ****************
    
    public int getMaxSearchResults() {
        return env.getProperty("catalog.maxSearchResults", Integer.class);
    }
    
}
