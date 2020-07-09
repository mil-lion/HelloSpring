/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitablity for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.persist.lab07_1;

import java.util.Collection;
import ru.lionsoft.hello.spring.persist.entity.MusicItem;

public interface MusicItemDAO {
    // TODO uncomment these methods as you implment them in JdbcMusicItemDAO

    MusicItem searchById(Long id);

    Collection<MusicItem> searchByKeyword(String keyword);

    void create(MusicItem item);

    void update(MusicItem item);

    void delete(MusicItem item);
}
