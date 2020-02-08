/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitablity for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.persist.lab06_1;

import java.math.BigDecimal;
import java.sql.Date;
import ru.lionsoft.hello.spring.persist.entity.MusicItem;

public class DownloadableMusicItem extends MusicItem {

    private static final long serialVersionUID = 1L;

    private String URL;
    private String fileType;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String url) {
        URL = url;
    }

    public DownloadableMusicItem() {
    }

    public DownloadableMusicItem(Long id, String num, String title,
            String artist, Date releaseDate, BigDecimal listPrice,
            BigDecimal price) {
        super(id, num, title, artist, releaseDate, listPrice, price);
    }

    @Override
    public String toString() {
        final String comma = ", ";
        StringBuilder sb = new StringBuilder();
        sb.append("DownloadableItem: ");
        sb.append(getURL());
        sb.append(comma);
        sb.append(getFileType());
        sb.append(comma);
        sb.append(super.toString());
        return sb.toString();
    }

}
