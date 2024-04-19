/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2024 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.web.model.catalog;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaQuery;
import java.util.Collection;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.lionsoft.hello.spring.web.model.entity.MusicItem;

/**
 *
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
@Component
@Transactional(propagation = Propagation.SUPPORTS)
public class JpaItemDAO implements ItemDAO {

    @PersistenceContext(name = "SamplePU")
    private EntityManager em;

    @Override
    public Collection<MusicItem> getAll() {
        CriteriaQuery<MusicItem> cq = em.getCriteriaBuilder().createQuery(MusicItem.class);
        cq.select(cq.from(MusicItem.class));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public MusicItem get(Long id) {
        return em.find(MusicItem.class, id);
    }

    @Override
    public Collection<MusicItem> searchByArtistTitle(String keyword) {
        String wildcard = "%" + keyword.toLowerCase() + "%";
        return em.createQuery("SELECT mi FROM MusicItem mi WHERE lower(mi.artist) LIKE :keyword OR lower(mi.title) LIKE :keyword", MusicItem.class)
                .setParameter("keyword", wildcard)
                .getResultList();
    }

    
    @Override @Transactional(propagation = Propagation.REQUIRED)
    public void create(MusicItem entity) {
        em.persist(entity);
    }

    @Override @Transactional(propagation = Propagation.REQUIRED)
    public void edit(MusicItem entity) {
        em.merge(entity);
    }

    @Override @Transactional(propagation = Propagation.REQUIRED)
    public void remove(MusicItem entity) {
        em.remove(em.merge(entity));
    }

}
