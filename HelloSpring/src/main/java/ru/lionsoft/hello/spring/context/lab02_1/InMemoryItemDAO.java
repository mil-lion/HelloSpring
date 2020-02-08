/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.context.lab02_1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.sql.Date;
import java.math.BigDecimal;
import ru.lionsoft.hello.spring.context.entity.MusicItem;

/**
 * This class is a simple bean that emulates the search of some catalog. We do
 * this so we don't need a database at this early stage of the course. This will
 * be replaced with a persistent entity that goes to a database in a later lab.
 */
// TODO - Make sure InMemoryItemDAO implements ItemDAO
public class InMemoryItemDAO implements ItemDAO {

    // catalog of MusicItem objects
    private static final Collection<MusicItem> catalog = new ArrayList<>();

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
        return Collections.unmodifiableCollection(catalog);
    }

    // loads catalog with data
    static {
        add(1, "CD501", "Diva", "Annie Lennox", "1992-01-04", "17.97", "13.99");
        add(2, "CD502", "Dream of the Blue Turtles", "Sting", "1985-02-05", "17.97", "14.99");
        add(3, "CD503", "Trouble is...", "Kenny Wayne Shepherd Band", "1997-08-08", "17.97", "14.99");
        add(4, "CD504", "Lie to Me", "Jonny Lang", "1997-08-26", "17.97", "14.99");
        add(5, "CD505", "Little Earthquakes", "Tori Amos", "1992-01-18", "17.97", "14.99");
        add(6, "CD506", "Seal", "Seal", "1991-08-18", "17.97", "14.99");
        add(7, "CD507", "Ian Moore", "Ian Moore", "1993-12-05", "9.97", "9.97");
        add(8, "CD508", "So Much for the Afterglow", "Everclear", "1997-01-19", "16.97", "13.99");
        add(9, "CD509", "Surfacing", "Sarah McLachlan", "1997-12-04", "17.97", "13.99");
        add(10, "CD510", "Hysteria", "Def Leppard", "1987-06-20", "17.97", "14.99");
        add(11, "CD511", "A Life of Saturdays", "Dexter Freebish", "2000-12-06", "16.97", "12.99");
        add(12, "CD512", "Human Clay", "Creed", "1999-10-21", "18.97", "13.28");
        add(13, "CD513", "My, I'm Large", "Bobs", "1987-02-20", "11.97", "11.97");
        add(14, "CD514", "So", "Peter Gabriel", "1986-10-03", "17.97", "13.99");
        add(15, "CD515", "Big Ones", "Aerosmith", "1994-05-08", "18.97", "14.99");
        add(16, "CD516", "90125", "Yes", "1983-10-16", "11.97", "11.97");
        add(17, "CD517", "1984", "Van Halen", "1984-08-19", "11.97", "11.97");
        add(18, "CD518", "Escape", "Journey", "1981-02-25", "11.97", "11.97");
    }

    private static void add(long id, String num, String title, String artist,
            String releaseDate, String listPrice, String price) {
        catalog.add(new MusicItem(id, num, title, artist,
                Date.valueOf(releaseDate), new BigDecimal(listPrice), new BigDecimal(price)));
    }

}
