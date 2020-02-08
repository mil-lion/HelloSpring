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
import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.springframework.security.access.annotation.Secured;
import ru.lionsoft.hello.spring.web.entity.MusicItem;

// Implementation of Catalog interface using ItemDAO
@RolesAllowed("ROLE_ADMIN") // Would be commented out for optional parts
//@Secured("ROLE_ADMIN")
public class JavaTunesCatalog implements Catalog {

    // Properties
    private final ItemDAO dao;

    private int maxSearchResults = 0;

    public void setMaxSearchResults(int maxIn) {
        maxSearchResults = maxIn;
    }

    public int getMaxSearchResults() {
        return maxSearchResults;
    }

    // Constructor - We require a JavaTunesCatalog to be initialized with a SearchUtility bean when created
    public JavaTunesCatalog(ItemDAO daoIn) {
        dao = daoIn;
    }

    // Business methods
    @Override
    public MusicItem findById(Long id) {
        System.out.println("JavaTunesCatalog:findById - " + id);

        // delegate to the search bean
        return dao.get(id);
    }

    @Override
    public Collection<MusicItem> findByKeyword(String keyword) {
        System.out.println("JavaTunesCatalog:findByKeyword - " + keyword);
        System.out.println("maxSearchResults = " + maxSearchResults);

        // delegate to the search Bean, then trim the results
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
