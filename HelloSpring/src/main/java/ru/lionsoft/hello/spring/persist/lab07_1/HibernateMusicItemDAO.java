/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitablity for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.persist.lab07_1;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.lionsoft.hello.spring.persist.entity.MusicItem;

public class HibernateMusicItemDAO implements MusicItemDAO {

    private final SessionFactory sessionFactory;

    public HibernateMusicItemDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // ******************** Business Methods **************************

    @Override
    public MusicItem searchById(Long id) {
        return sessionFactory.getCurrentSession().get(MusicItem.class, id);
    }

    @Override
    public Collection<MusicItem> searchByKeyword(String keyword) {
        // create the %keyword% wildcard syntax used in SQL LIKE operator
        String wildcarded = "%" + keyword + "%";
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT mi FROM MusicItem mi "
                        + "WHERE mi.title LIKE :keyword OR mi.artist LIKE :keyword")
                .setParameter("keyword", wildcarded)
                .getResultList();
    }

    @Override
    public void create(MusicItem item) {
        sessionFactory.getCurrentSession().save(item);
    }

    @Override
    public void update(MusicItem item) {
        sessionFactory.getCurrentSession().update(item);
    }

    @Override
    public void delete(MusicItem item) {
        sessionFactory.getCurrentSession().delete(item);
    }

}
