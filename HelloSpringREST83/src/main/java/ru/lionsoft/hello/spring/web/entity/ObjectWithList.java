/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.web.entity;

import java.util.Collection;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
@XmlRootElement(name = "myList")
public class ObjectWithList {
    
    private Collection<MusicItem> list;

    @XmlElement(name = "item")
    public Collection<MusicItem> getList() {
        return list;
    }

    public void setList(Collection<MusicItem> list) {
        this.list = list;
    }

    public ObjectWithList() {
    }

    public ObjectWithList(Collection<MusicItem> list) {
        this.list = list;
    }

    
}
