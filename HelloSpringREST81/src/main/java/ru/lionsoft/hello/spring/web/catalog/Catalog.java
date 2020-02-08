/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-12 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.web.catalog;

import java.util.Collection;
import ru.lionsoft.hello.spring.web.entity.MusicItem;

public interface Catalog {

    public MusicItem findById(Long id);

    public Collection<MusicItem> findByKeyword(String keyword);

   public Collection<MusicItem> getAll();
}

