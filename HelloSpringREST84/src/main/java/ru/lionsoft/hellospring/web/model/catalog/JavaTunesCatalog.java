/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2024 LionSoft LLC.
 */
package ru.lionsoft.hellospring.web.model.catalog;

import jakarta.annotation.security.RolesAllowed;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import ru.lionsoft.hellospring.web.model.entity.MusicItem;

/**
 *
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
@Component
@Secured("ROLE_ADMIN")
//@RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
public class JavaTunesCatalog implements Catalog {

    private final ItemDAO dao;

    // ***************** Constructor ***********
    
    @Autowired
    public JavaTunesCatalog(ItemDAO dao) {
        this.dao = dao;
    }

    // **************** Properties *************
    
    private int maxSearchResults = 0;

    public int getMaxSearchResults() {
        return maxSearchResults;
    }

    @Value("#{applicationConfig.maxSearchResults}")
    public void setMaxSearchResults(int maxSearchResults) {
        this.maxSearchResults = maxSearchResults;
    }

    // ***************** Business Methods (implements of interface Catalog) ***********
    
    @Override
    public Collection<MusicItem> findAll() {
        System.out.println("@@@@ ru.lionsoft.hellospring.web.model.catalog.JavaTunesCatalog.findAll()");
        return dao.getAll();
    }

    @Override
    public MusicItem findById(Long id) {
        System.out.println("@@@ ru.lionsoft.hellospring.web.catalog.JavaTunesCatalog.findById(" + id + ")");
        return dao.get(id);
    }

    @Override
//    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public Collection<MusicItem> findByKeyword(String keyword) {
        System.out.println("@@@ ru.lionsoft.hellospring.web.catalog.JavaTunesCatalog.findByKeyword(" + keyword + ")");
        System.out.println("- maxSearchResults = " + maxSearchResults);
        
        return trim(dao.searchByArtistTitle(keyword));
    }
    
    private Collection<MusicItem> trim(Collection<MusicItem> results) {
        Collection<MusicItem> ret = results;
        if ((maxSearchResults > 0) && (results.size() > maxSearchResults) && (results instanceof List)) {
            ret = ((List<MusicItem>)results).subList(0, maxSearchResults);
        }
        return ret;
    }

    @Override
    public MusicItem insert(MusicItem entity) {
        System.out.println("@@@@ ru.lionsoft.hellospring.web.model.catalog.JavaTunesCatalog.insert(" + entity + ")");
        dao.create(entity);        
        return entity;
    }

    @Override
    public MusicItem update(MusicItem entity) {
        System.out.println("@@@@ ru.lionsoft.hellospring.web.model.catalog.JavaTunesCatalog.update(" + entity + ")");
        dao.edit(entity);
        return entity;
    }

    @Override
    public MusicItem delete(Long id) {
        System.out.println("@@@@ ru.lionsoft.hellospring.web.model.catalog.JavaTunesCatalog.delete(" + id + ")");
        MusicItem entity = dao.get(id);
        dao.remove(entity);
        return entity;
    }
}
