/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitablity for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-12 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.persist.lab04_3;

import java.util.Collection;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.lionsoft.hello.spring.persist.entity.MusicItem;

/**
 *
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
@Component
//@Transactional(readOnly = true, propagation=Propagation.NOT_SUPPORTED)
public class HibernateItemDAO implements ItemDAO {

    @Autowired
    @Qualifier("localSessionFactory")
    private SessionFactory sessionFactory;
        
    // searches catalog by ID
    @Override
    public MusicItem get(Long id) {
        return sessionFactory.openSession().get(MusicItem.class, id);//getCurrentSession()
    }

    // searches catalog by keyword
    @Override
    public Collection<MusicItem> searchByArtistTitle(String keyword) {
        String wildcarded = "%" + keyword.toLowerCase() + "%";
        return sessionFactory.openSession()//getCurrentSession()
                .createQuery("FROM MusicItem mi WHERE lower(mi.title) LIKE :keyword OR lower(mi.artist) LIKE :keyword", MusicItem.class)
                .setParameter("keyword", wildcarded)
                .getResultList();
    }

}
