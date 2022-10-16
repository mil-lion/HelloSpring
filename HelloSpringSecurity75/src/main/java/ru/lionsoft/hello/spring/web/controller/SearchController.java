/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-12 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.web.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.lionsoft.hello.spring.web.catalog.Catalog;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private Catalog catalog;

    // Optional - Method to add reference data into model
    @ModelAttribute("artists")
    public Collection<String> populateArtists() {
        ArrayList<String> artists = new ArrayList<>();
        artists.add("");
        artists.add("Annie Lennox");
        artists.add("Sting");
        artists.add("Seal");
        return artists;
    }

    // Handler nethod to forward to the search form
    @RequestMapping(method = RequestMethod.GET)
    public String get(@ModelAttribute("search") Search search) {
        search.setKeyword("Diva");
        return "searchForm";
    }

    // Handler method to process the search form submission
    // Uses optional searchArtist functionality
    @RequestMapping(method = RequestMethod.POST)
    public String processSearch(@ModelAttribute("search") Search search) {
        System.out.println("SearchController.processSearch()");
        String searchFor = search.getKeyword();
        if (search.getArtist() != null && search.getArtist().length() > 0) {
            searchFor = search.getArtist();
        }
        search.setResults(catalog.findByKeyword(searchFor));
        return "searchResults";
    }

}
