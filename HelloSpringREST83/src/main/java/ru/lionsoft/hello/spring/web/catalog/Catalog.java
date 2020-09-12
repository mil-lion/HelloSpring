/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.web.catalog;

import java.util.Collection;
import ru.lionsoft.hello.spring.web.entity.MusicItem;

/**
 *
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
public interface Catalog {
    
    /**
     * Find All MusicItemS
     * @return all items of music catalog
     */
    Collection<MusicItem> findAll();
    
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
