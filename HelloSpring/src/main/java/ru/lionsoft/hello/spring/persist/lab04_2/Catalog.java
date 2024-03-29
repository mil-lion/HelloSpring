/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitablity for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-12 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.persist.lab04_2;

import java.util.Collection;

/**
 *
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
public interface Catalog {
    
    /**
     * Find one MusicItem by id
     * @param id identified of music item
     * @return item of music catalog
     */
    MusicItem findById(Long id);
    
    /**
     * Search for MusicItems containing keyword
     * @param keyword keyword of Artist or Title
     * @return items of music catalog
     */
    Collection<MusicItem> findByKeyword(String keyword);
}
