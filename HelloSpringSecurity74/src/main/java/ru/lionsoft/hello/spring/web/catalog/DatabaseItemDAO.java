/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-12 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.web.catalog;

import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import org.springframework.stereotype.Component;
import ru.lionsoft.hello.spring.web.entity.MusicItem;

/**
 * This class is a simple bean that emulates the search of some catalog. We do
 * this so we don't need a database at this early stage of the course. This will
 * be replaced with a persistent entity that goes to a database in a later lab.
 */
@Component
public class DatabaseItemDAO implements ItemDAO {

    @PersistenceContext
    private EntityManager em;
    
    // searches catalog by ID
    @Override
    public MusicItem get(Long id) {
        return em.find(MusicItem.class, id);
    }

    // searches catalog by keyword
    @Override
    public Collection<MusicItem> searchByArtistTitle(String keyword) {
        String sql = 
                "SELECT mi"
                + " FROM MusicItem mi"
                + " WHERE mi.title LIKE '%" + keyword + "%'"
                + " OR mi.artist LIKE '%" + keyword + "%'";
        return em.createQuery(sql).getResultList();
    }

    @Override
    public Collection<MusicItem> getAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(MusicItem.class));
        return em.createQuery(cq).getResultList();
    }

}
