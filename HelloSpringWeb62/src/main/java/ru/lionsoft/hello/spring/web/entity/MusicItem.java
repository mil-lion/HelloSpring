/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-12 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.web.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.math.BigDecimal;
import java.util.Objects;

public class MusicItem
        implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    // properties
    private Long id;
    private String num;
    private String title;
    private String artist;
    private Date releaseDate;
    private BigDecimal listPrice;
    private BigDecimal price;

    public MusicItem() {
    }

    public MusicItem(Long id) {
        this.id = id;
    }

    public MusicItem(Long id, String num, String title, String artist,
            Date releaseDate, BigDecimal listPrice, BigDecimal price) {
        this.id = id;
        this.num = num;
        this.title = title;
        this.artist = artist;
        this.releaseDate = releaseDate;
        this.listPrice = listPrice;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    private final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public String getReleaseDateString() {
        return releaseDate != null ? df.format(releaseDate) : "null";
    }
    
    public void setReleaseDateString(String releaseDateString) {
        try {
            releaseDate = df.parse(releaseDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    // override Object.equals
    @Override
    public boolean equals(Object compare) {
        boolean result = false;

        if (compare instanceof MusicItem) {
            // cast to MusicItem
            MusicItem other = (MusicItem) compare;

            // if all the ids are equal, the objects are equal
            result = other.getId().equals(this.getId());
        }
        return result;
    }

    // override Object.hashCode
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
        return hash;
    }

    // override Object.toString
    @Override
    public String toString() {
        return this.getClass().getName() + "{"
                + ":id=" + id
                + ", :num=" + num
                + ", title=" + title
                + ", artist=" + artist
                + ", releaseDate=" + getReleaseDateString()
                + ", listPrice=" + listPrice
                + ", price=" + price
                + "}";
    }
}
