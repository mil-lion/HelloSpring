/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.lionsoft.hello.spring.persist.lab04_2;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
public class JavaTunesCatalog implements Catalog {
    
    // *********************** Properties *****************
    
    // maxSearchResults property
    private int maxSearchResults = 0;

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
     * Setter Injection!!!
     *
     * @param maxSearchResults new value of maxSearchResults
     */
    public void setMaxSearchResults(int maxSearchResults) {
        this.maxSearchResults = maxSearchResults;
    }

    // ********************* Constructors ***********************

    // Field for dao
    private final ItemDAO dao;
        
    // Constructor - We require a JavaTunesCatalog to be initialized
    // with an ItemDAO bean when it's created
    // Constructor Injection!!!
    @Autowired
    public JavaTunesCatalog(ItemDAO dao) {
        this.dao = dao;
    }

    // ************* Implements of interface Catalog ***************
    
    @Override
    public MusicItem findById(Long id) {
        System.out.println("@@@@ JavaTunesCatalog.findById()");
        return dao.get(id);
    }

    @Override
    public Collection<MusicItem> findByKeyword(String keyword) {
        System.out.println("@@@@ JavaTunesCatalog.findByKeyword() - " + keyword);
        System.out.println("- maxSearchResults = " + maxSearchResults);
        return trim(dao.searchByArtistTitle(keyword));
    }

    // ****************** Private Methods *****************
    
    private Collection<MusicItem> trim(Collection<MusicItem> results) {
        Collection<MusicItem> ret = results;
        if ((maxSearchResults > 0) && (ret.size() > maxSearchResults) && (results instanceof List)) {
            ret = ((List)results).subList(0, maxSearchResults);
        }
        return ret;
    }
}
