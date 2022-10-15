/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitablity for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-12 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.persist.lab04_1;

import java.util.Collection;
import ru.lionsoft.hello.spring.persist.entity.MusicItem;

/**
 * Item Data Access Objects
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
public interface ItemDAO {
    
    /**
     * Find one MusicItem by id 
     * @param id
     * @return 
     */
    MusicItem get(Long id);
    
    /**
     * Search for MusicItems containing keyword in title or artist
     * @param keyword
     * @return 
     */
    Collection<MusicItem> searchByArtistTitle(String keyword);
}
