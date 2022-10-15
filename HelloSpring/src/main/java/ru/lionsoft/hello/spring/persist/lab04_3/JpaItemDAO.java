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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import ru.lionsoft.hello.spring.persist.entity.MusicItem;

/**
 *
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
@Component
public class JpaItemDAO implements ItemDAO {

    @PersistenceContext(unitName = "SamplePU")
    private EntityManager em;
    
    @Override
    public MusicItem get(Long id) {
        return em.find(MusicItem.class, id);
    }

    @Override
    public Collection<MusicItem> searchByArtistTitle(String keyword) {
        String wildcarded = "%" + keyword.toLowerCase() + "%";
        return em.createQuery("SELECT mi FROM MusicItem mi WHERE lower(mi.title) LIKE :keyword OR lower(mi.artist) LIKE :keyword", MusicItem.class)
                .setParameter("keyword", wildcarded)
                .getResultList();
    }
    
}
