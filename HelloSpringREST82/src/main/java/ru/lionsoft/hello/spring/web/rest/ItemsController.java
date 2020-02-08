/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitablity for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-12 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.lionsoft.hello.spring.web.catalog.Catalog;
import ru.lionsoft.hello.spring.web.entity.MusicItem;
import ru.lionsoft.hello.spring.web.entity.ObjectWithList;

@Controller
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private Catalog cat;


    // Optional part - returns wrapped object that can be converted to XML    
//    @RequestMapping(headers = {"Accept=application/xml"})
    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Object getAllItems() {
        System.out.println("property: " + System.getProperty("spring.http.converters.preferred-json-mapper"));
        List<MusicItem> results = (List<MusicItem>) cat.getAll();
        return new ObjectWithList(results);  // return wrapped collection
//        return results; // return bare collection, will throw 406 exception (Optional part) with application/xml specified
    }

/*    
    // Original - returns JSON if JSON jars  available - otherwise throws 406 error
    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public Object getAllItems() {
        List<MusicItem> results = (List<MusicItem>)cat.getAll();
        return results; 
    }
*/
    
    @RequestMapping(value = "/{id}", 
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    public MusicItem findItem(@PathVariable("id") Long id) {
        return cat.findById(id);
    }

}
