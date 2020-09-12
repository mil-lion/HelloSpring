/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.lionsoft.hello.spring.web.catalog.Catalog;
import ru.lionsoft.hello.spring.web.entity.MusicItem;
import ru.lionsoft.hello.spring.web.entity.ObjectWithList;

/**
 *
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
@Service
@RequestMapping("/catalog")
public class CatalogService {
    
    @Autowired
    private Catalog catalog;
    
    @GetMapping
//    @RequestMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Object getAllItems() {
        return new ObjectWithList(catalog.findAll());
    }
    
    @GetMapping("{id}")
//    @RequestMapping(path = "{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public MusicItem getById(@PathVariable("id") Long id) {
        return catalog.findById(id);
    }
}
