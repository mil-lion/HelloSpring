/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-12 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.web.entity;

import java.util.List;
import javax.json.bind.annotation.JsonbProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "myList")
public class ObjectWithList {

    private List<MusicItem> list;

    public ObjectWithList(List<MusicItem> listIn) {
        list = listIn;
    }

    public ObjectWithList() {
    }

//    @XmlElementWrapper(name = "myList")
    @XmlElement(name = "musicItem")
    @JsonbProperty("myList")
    public List<MusicItem> getList() {
        return list;
    }

    public void setList(List<MusicItem> listIn) {
        list = listIn;
    }

}
