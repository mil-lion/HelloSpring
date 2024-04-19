/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2024 LionSoft LLC.
 */
package ru.lionsoft.hellospring.web.model.catalog;

import java.util.Collection;
import ru.lionsoft.hellospring.web.model.entity.MusicItem;

/**
 *
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
public interface ItemDAO {
    
    /**
     * Find all MusicItems
     * @return all items of music catalog
     */
    Collection<MusicItem> getAll();
    
    /**
     * Find one MusicItem by id
     * @param id identified of music item
     * @return item of music catalog
     */
    MusicItem get(Long id);
    
    /**
     * Search for MusicItems containing keyword
     * @param keyword keyword of Artist or Title
     * @return items of music catalog
     */
    Collection<MusicItem> searchByArtistTitle(String keyword);

    /**
     * Create of music item into catalog
     * @param entity item of music catalog 
     */
    void create(MusicItem entity);
    
    /**
     * Update of music item into catalog
     * @param entity item of music catalog
     */
    void edit(MusicItem entity);
    
    /**
     * Remove of music item from catalog
     * @param entity item of music catalog
     */
    void remove(MusicItem entity);
}
