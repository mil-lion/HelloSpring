/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2024 LionSoft LLC.
 */
package ru.lionsoft.hellospring.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.lionsoft.hellospring.web.model.catalog.Catalog;
import ru.lionsoft.hellospring.web.model.entity.MusicItem;
import ru.lionsoft.hellospring.web.model.entity.ObjectWithList;

/**
 * Контролер REST сервиса
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
@Controller
@RequestMapping("/items")
public class ItemsController {
    
    @Autowired
    private Catalog catalog;
    
    @RequestMapping(method = RequestMethod.GET, 
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Object getAllItems() {
        System.out.println("@@@@ ru.lionsoft.hellospring.web.controller.ItemsController.getAllItems()");
        return new ObjectWithList(catalog.findAll());
    }
    
    @RequestMapping(value = "/{id}", 
            method = RequestMethod.GET, 
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public MusicItem findItem(@PathVariable("id") Long id) {
        System.out.println("@@@@ ru.lionsoft.hellospring.web.controller.ItemsController.findItem(" + id + ")");
        return catalog.findById(id);
    }

    @RequestMapping(value = "/keyword/{keyword}", 
            method = RequestMethod.GET, 
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Object findByKeyword(@PathVariable("keyword") String keyword) {
        System.out.println("@@@@ ru.lionsoft.hellospring.web.controller.ItemsController.findByKeyword(" + keyword + ")");
        return new ObjectWithList(catalog.findByKeyword(keyword));
    }
    
    @RequestMapping(method = RequestMethod.POST, 
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public MusicItem createItem(@RequestBody MusicItem item) {
        System.out.println("@@@@ ru.lionsoft.hellospring.web.controller.ItemsController.createItem(" + item + ")");
        return catalog.insert(item);
    }

    @RequestMapping(value = "/{id}", 
            method = RequestMethod.PUT, 
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public MusicItem updateItem(@PathVariable("id") Long id, @RequestBody MusicItem item) {
        System.out.println("@@@@ ru.lionsoft.hellospring.web.controller.ItemsController.updateItem(" + id + "," + item + ")");
        MusicItem oldItem = catalog.findById(id);
        // merge item
//        oldItem.setId(item.getId());
        oldItem.setNum(item.getNum());
        oldItem.setTitle(item.getTitle());
        oldItem.setArtist(item.getArtist());
        oldItem.setReleaseDate(item.getReleaseDate());
        oldItem.setListPrice(item.getListPrice());
        oldItem.setPrice(item.getPrice());
        // update
        return catalog.update(oldItem);
    }
    
    @RequestMapping(value = "/{id}", 
            method = RequestMethod.DELETE, 
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public MusicItem deleteItem(@PathVariable("id") Long id) {
        System.out.println("@@@@ ru.lionsoft.hellospring.web.controller.ItemsController.deleteItem(" + id + ")");
        return catalog.delete(id);
    }    
}
