/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2024 LionSoft LLC.
 */
package ru.lionsoft.hellospring.web.model.entity;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

/**
 * Класс для агрегации музыкальных элементов в один XML/JSON элемент
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
@XmlRootElement(name = "myList")
public class ObjectWithList {
    
    // *********** Properties ************
    
    private Collection<MusicItem> list;

    @XmlElement(name = "item")
    @JsonbProperty("items")
    public Collection<MusicItem> getList() {
        return list;
    }

    public void setList(Collection<MusicItem> list) {
        this.list = list;
    }

    // ************** Constructors ***********

    public ObjectWithList() {
    }

    public ObjectWithList(Collection<MusicItem> list) {
        this.list = list;
    }
        
}
