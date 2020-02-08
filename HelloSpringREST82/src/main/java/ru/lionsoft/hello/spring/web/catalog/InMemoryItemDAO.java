/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-12 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.web.catalog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import ru.lionsoft.hello.spring.web.entity.MusicItem;

/**
 * This class is a simple bean that emulates the search of some catalog. We do
 * this so we don't need a database at this early stage of the course. This will
 * be replaced with a persistent entity that goes to a database in a later lab.
 */
public class InMemoryItemDAO implements ItemDAO {

    // catalog of MusicItem objects
    private final Collection<MusicItem> catalog;

    // Constructor - JavaTunesSearch must be created with a catalog passed in
    public InMemoryItemDAO(Collection<MusicItem> catalogIn) {
        catalog = catalogIn;
    }

    // searches catalog by ID
    @Override
    public MusicItem get(Long id) {
        // declare return value
        MusicItem result = null;

        // iterate through the catalog, looking for an ID match
        for (MusicItem item : catalog) {
            if (item.getId().equals(id)) {
                result = item;    // assign to return value
                break;            // found it - stop looping
            }
        }
        return result;
    }

    // searches catalog by keyword
    @Override
    public Collection<MusicItem> searchByArtistTitle(String keyword) {
        // declare return value
        Collection<MusicItem> result = new ArrayList<>();

        // remove case sensitivity
        keyword = keyword.toLowerCase();

        // iterate through the catalog, looking for a keyword match
        for (MusicItem item : catalog) {
            if (item.getTitle().toLowerCase().contains(keyword)
             || item.getArtist().toLowerCase().contains(keyword)) {
                result.add(item);
            }
        }
        return result;
    }

    @Override
    public Collection<MusicItem> getAll() {
        return catalog;
    }

}
