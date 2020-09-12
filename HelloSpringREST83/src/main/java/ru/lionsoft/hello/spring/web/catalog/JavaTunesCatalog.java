/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.web.catalog;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.lionsoft.hello.spring.web.entity.MusicItem;

/**
 * Bean Catalog
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
@Component("catalog")
public class JavaTunesCatalog implements Catalog {
    
    // **************** Properties **************
    
    @Value("#{applicationConfig.catalogMaxSearchResults}")
    private int maxSearchResults = 0; // 0 - all elements

    /**
     * Get the value of maxSearchResults
     *
     * @return the value of maxSearchResults
     */
    public int getMaxSearchResults() {
        return maxSearchResults;
    }

    /**
     * Set the value of maxSearchResults
     *
     * @param maxSearchResults new value of maxSearchResults
     */
    // !!!Setter Injection!!!
    public void setMaxSearchResults(int maxSearchResults) {
        this.maxSearchResults = maxSearchResults;
    }

    // ************** Constructors *********************
    
    private final ItemDAO dao;

    // Constructor - We require a JavaTunesCatalog to be initialized
    // with an ItemDAO bean when it's created
    // !!!Constructor Injection!!!
    public JavaTunesCatalog(ItemDAO dao) {
        this.dao = dao;
    }
    
    // **************** Implements of interface Catalog *****************

    @Override
    public MusicItem findById(Long id) {
        System.out.println("@@@@ JavaTunesCatalog.findById()");
        return dao.get(id);
    }

    @Override
    public Collection<MusicItem> findByKeyword(String keyword) {
        System.out.println("@@@@ JavaTunesCatalog.findByKeyword()");
        System.out.println("- maxSearchResults = " + maxSearchResults);
        return trim(dao.searchByArtistTitle(keyword));
    }
    
    @Override
    public Collection<MusicItem> findAll() {
        System.out.println("@@@@ JavaTunesCatalog.findAll()");
        return dao.getAll();
    }
    
    // ****************** Private Methods ***********************

    private Collection<MusicItem> trim(Collection<MusicItem> results) {
        Collection<MusicItem> ret = results;
        if ((maxSearchResults > 0) && (ret.size() > maxSearchResults) && (results instanceof List)) {
            ret = ((List)results).subList(0, maxSearchResults);
        }
        return ret;
    }

}
