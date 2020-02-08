/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitablity for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-12 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.web.rest;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.lionsoft.hello.spring.web.catalog.Catalog;
import ru.lionsoft.hello.spring.web.entity.MusicItem;

@Controller
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private Catalog cat;

    // Handler method to forward to the search form
    @RequestMapping
    @ResponseBody
    public String getAllItems() {
        Collection<MusicItem> results = (Collection<MusicItem>) cat.getAll();
        return results.toString();
    }

    @RequestMapping("/{id}")
    @ResponseBody
    public String findItem(@PathVariable("id") Long id) {
        return cat.findById(id).toString();
    }

}
