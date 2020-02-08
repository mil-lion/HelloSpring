/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-12 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.lionsoft.hello.spring.web.catalog.Catalog;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private Catalog cat;

    @RequestMapping(method = RequestMethod.GET)
    public String get() {
        System.out.println("@@@@ SearchController.get()");
        return "searchForm";
    }

    // Handler method to process the search form submission
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView processSearch(@RequestParam(value = "keyword", required = true) String keyword) {
        System.out.println("@@@@ SearchController.processSearch() - " + keyword);
        return new ModelAndView("searchResults", "results", cat.findByKeyword(keyword));
    }
}
