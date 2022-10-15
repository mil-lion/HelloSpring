/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitablity for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-12 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.persist.lab04_3;

import java.util.Collection;
import ru.lionsoft.hello.spring.persist.entity.MusicItem;

/**
 *
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
public interface Catalog {

    /**
     * Find one MusicItem by id
     * @param id identificator of music item
     * @return music item
     */
    MusicItem findById(Long id); 
    
    /**
     * Search for MusicItems containing keyword in title or artist
     * @param keyword artist or title keyword
     * @return collection of music item
     */
    Collection<MusicItem> findByKeyword(String keyword);
    
}
