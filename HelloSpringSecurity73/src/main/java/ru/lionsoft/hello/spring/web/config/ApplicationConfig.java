/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-12 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.web.config;

import java.util.ArrayList;
import java.util.Collection;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.lionsoft.hello.spring.web.catalog.InMemoryItemDAO;
import ru.lionsoft.hello.spring.web.catalog.ItemDAO;
import ru.lionsoft.hello.spring.web.entity.MusicItem;

/**
 * Spring application configuration
 * @author Igor Morenko <morenko at lionsoft.ru>
 */
@Configuration
@PropertySource("classpath:db.properties")
public class ApplicationConfig {
    
    @Autowired
    private Environment env;
    
    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("db.driver"));
        dataSource.setUrl(env.getProperty("db.jdbcUrl"));
        dataSource.setUsername(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }
    
    @Bean
    public ItemDAO inMemoryItemDAO() {
        // generate collection MusicItem
        Collection<MusicItem> catalog = new ArrayList<>();
        catalog.add(new MusicItem(1L, "CD501", "Diva", "Annie Lennox", "1992-01-04", "17.97", "13.99"));
        catalog.add(new MusicItem(2L, "CD502", "Dream of the Blue Turtles", "Sting", "1985-02-05", "17.97", "14.99"));
        catalog.add(new MusicItem(3L, "CD503", "Trouble is...", "Kenny Wayne Shepherd Band", "1997-08-08", "17.97", "14.99"));
        catalog.add(new MusicItem(4L, "CD504", "Lie to Me", "Jonny Lang", "1997-08-26", "17.97", "14.99"));
        catalog.add(new MusicItem(5L, "CD505", "Little Earthquakes", "Tori Amos", "1992-01-18", "17.97", "14.99"));
        catalog.add(new MusicItem(6L, "CD506", "Seal", "Seal", "1991-08-18", "17.97", "14.99"));
        catalog.add(new MusicItem(7L, "CD507", "Ian Moore", "Ian Moore", "1993-12-05", "9.97", "9.97"));
        catalog.add(new MusicItem(8L, "CD508", "So Much for the Afterglow", "Everclear", "1997-01-19", "16.97", "13.99"));
        catalog.add(new MusicItem(9L, "CD509", "Surfacing", "Sarah McLachlan", "1997-12-04", "17.97", "13.99"));
        catalog.add(new MusicItem(10L, "CD510", "Hysteria", "Def Leppard", "1987-06-20", "17.97", "14.99"));
        catalog.add(new MusicItem(11L, "CD511", "A Life of Saturdays", "Dexter Freebish", "2000-12-06", "16.97", "12.99"));
        catalog.add(new MusicItem(12L, "CD512", "Human Clay", "Creed", "1999-10-21", "18.97", "13.28"));
        catalog.add(new MusicItem(13L, "CD513", "My, I'm Large", "Bobs", "1987-02-20", "11.97", "11.97"));
        catalog.add(new MusicItem(14L, "CD514", "So", "Peter Gabriel", "1986-10-03", "17.97", "13.99"));
        catalog.add(new MusicItem(15L, "CD515", "Big Ones", "Aerosmith", "1994-05-08", "18.97", "14.99"));
        catalog.add(new MusicItem(16L, "CD516", "90125", "Yes", "1983-10-16", "11.97", "11.97"));
        catalog.add(new MusicItem(17L, "CD517", "1984", "Van Halen", "1984-08-19", "11.97", "11.97"));
        catalog.add(new MusicItem(18L, "CD518", "Escape", "Journey", "1981-02-25", "11.97", "11.97"));

        // return bean
        return new InMemoryItemDAO(catalog);
    }
}
