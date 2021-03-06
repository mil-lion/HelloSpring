/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitablity for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.persist.lab06_1;

import java.util.Collection;

import org.hibernate.SessionFactory;
import ru.lionsoft.hello.spring.persist.entity.MusicItem;

public class HibernateMusicItemDAO implements MusicItemDAO {

    private final SessionFactory sessionFactory;

    // Constructor Injection
    public HibernateMusicItemDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // ******************* Business Methods *****************
    
    @Override
    public MusicItem searchById(Long id) {
        return sessionFactory.getCurrentSession().get(MusicItem.class, id);
    }

    @Override
    public Collection<MusicItem> searchByKeyword(String keyword) {
        // create the %keyword% wildcard syntax used in SQL LIKE operator
        String wildcarded = "%" + keyword + "%";
        // TODO: create query 
        return sessionFactory.getCurrentSession()
                .createQuery("FROM MusicItem mi "
                        + "WHERE mi.title LIKE :keyword "
                        + "OR mi.artist LIKE :keyword"
                        , MusicItem.class)
                .setParameter("keyword", wildcarded)
                .getResultList();
    }

    @Override
    public void create(MusicItem item) {
        // TODO
    }

    @Override
    public void update(MusicItem item) {
        // TODO
    }

    @Override
    public void delete(MusicItem item) {
        // TODO - Finish delete
    }
}
