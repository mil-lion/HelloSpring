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

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.lionsoft.hello.spring.persist.entity.MusicItem;

public class HibernateMusicItemDAO implements MusicItemDAO {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        sessionFactory = sf;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public MusicItem searchById(Long id) {
        Session s = getSessionFactory().getCurrentSession();
        MusicItem ret = (MusicItem) s.get(MusicItem.class, id);
        return ret;
    }

    @Override
    public void create(MusicItem item) {
        // TODO
    }

    @Override
    public Collection<MusicItem> searchByKeyword(String keyword) {
        // create the %keyword% wildcard syntax used in SQL LIKE operator
        String wildcarded = "%" + keyword + "%";
        // TODO: 
        return null;
    }

    @Override
    public void delete(MusicItem item) {
        // TODO - Finish delete
    }
}
