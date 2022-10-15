/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitablity for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-12 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.persist.lab04_3;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import ru.lionsoft.hello.spring.persist.entity.Customer;
import ru.lionsoft.hello.spring.persist.entity.DiscountCode;
import ru.lionsoft.hello.spring.persist.entity.MicroMarket;

/**
 *
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
@Configuration
@ComponentScan(basePackages = "ru.lionsoft.hello.spring.persist.lab04_3")
@PropertySource("classpath:ru/lionsoft/hello/spring/persist/lab04_3/app.properties")
class ApplicationConfig {
    
    @Autowired
    private Environment env; // from app.properties
        
    @Bean("sampleDataSource")
    public DataSource createDataSource() {
        DriverManagerDataSource  dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("db.driverClass"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }
    
    @Bean("localSessionFactory")
    public LocalSessionFactoryBean createLocalSessionFactoryBean() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(createDataSource());
        
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.DerbyDialect");
        hibernateProperties.put("hibernate.show_sql", true);
        hibernateProperties.put("hibernate.current_session_context_class", "thread");
        sessionFactory.setHibernateProperties(hibernateProperties);
        
        sessionFactory.setMappingResources("ru/lionsoft/hello/spring/persist/entity/MusicItem.hbm.xml");
        
        sessionFactory.setAnnotatedClasses(Customer.class, DiscountCode.class, MicroMarket.class);
//        sessionFactory.setPackagesToScan("ru.lionsoft.hello.spring.persist.entity");

        return sessionFactory;
    }
    
    @Bean JpaVendorAdapter createHibernateVendorAdapter() {
        HibernateJpaVendorAdapter bean = new HibernateJpaVendorAdapter();
        
        bean.setDatabasePlatform("org.hibernate.dialect.DerbyDialect");
        bean.setShowSql(true);
        bean.setGenerateDdl(false);
        
        return bean;
    }
    
    @Bean("localEMFactory")
    public LocalContainerEntityManagerFactoryBean createEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        
        bean.setDataSource(createDataSource());
        bean.setPersistenceUnitName("SamplePU");
        bean.setJpaVendorAdapter(createHibernateVendorAdapter());
        
        return bean;
    }
    
    // *************** Properties **************
    
    public int getMaxSearchResults() {
        return env.getProperty("catalog.maxSearchResults", Integer.class);
    }
}
