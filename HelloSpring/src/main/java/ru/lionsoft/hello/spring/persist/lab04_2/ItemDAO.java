/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.lionsoft.hello.spring.persist.lab04_2;

import java.util.Collection;

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
