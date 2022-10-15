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
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.lionsoft.hello.spring.persist.entity.MusicItem;

/**
 *
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
@Component("catalog")
public class JavaTunesCatalog implements Catalog {

    // Field for dao
    private final ItemDAO dao; 

    // ******************* Constructors *******************
    
    // Constructor - We require a JavaTunesCatalog to be initialized
    // with an ItemDAO bean when it's created
    public JavaTunesCatalog(@Qualifier("jpaItemDAO") ItemDAO dao) {
        System.out.println("\n@@@@JavaTunesCatalog using dao: " + dao);
        this.dao = dao;
    }
    
    // ***************** Properties ********************
    // maxSearchResults property
    private int maxSearchResults = 0; 

    public int getMaxSearchResults() {
        return maxSearchResults;
    }

    @Value("#{applicationConfig.maxSearchResults}")
    public void setMaxSearchResults(int maxSearchResults) {
        this.maxSearchResults = maxSearchResults;
    }
            
    // ********************* Business Methods **************
    
    @Override
    public MusicItem findById(Long id) {
        System.out.println("\n@@@@JavaTunesCatalog.findById("+ id + ")");
        return dao.get(id);
    }

    @Override
    public Collection<MusicItem> findByKeyword(String keyword) {
        System.out.println("\n@@@@JavaTunesCatalog.findByKeyword(" + keyword + ")");
        System.out.println("- maxSearchResults = " + maxSearchResults);
        
        return trim(dao.searchByArtistTitle(keyword));
    }

    // Simple method to trim the results collection down to a size of maxSearchResults
    // We only bother to do it for Lists because their is an easy method to do so, and that's adequate to test the lab
    private Collection<MusicItem> trim(Collection<MusicItem> results) {
        Collection<MusicItem> ret = results;
        if ((maxSearchResults > 0)
                && (results.size() > maxSearchResults)
                && (results instanceof List)) {
            ret = ((List<MusicItem>) results).subList(0, maxSearchResults);
        }
        return ret;
    }
    
}
