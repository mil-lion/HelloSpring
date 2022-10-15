/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitablity for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-12 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.persist.lab04_2;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 *
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
@Table(name = "ITEMS")
public class MusicItem implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    // **************** Properties ****************
    
    @Id
    @Column(name = "ITEM_ID")
    private Long id;
    @Column
    private String num;
    @Column
    private String title;
    @Column
    private String artist;
    @Column(name = "RELEASE_DATE")
    private Date releaseDate;
    @Column(name = "LIST_PRICE")
    private BigDecimal listPrice;
    @Column
    private BigDecimal price;

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
    
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    public final String getReleaseDateString() {
        return dateFormat.format(releaseDate);
    }

    public final void setReleaseDateString(String releaseDate) {
        this.releaseDate = Date.valueOf(releaseDate);
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
    
    // ***************** Constructors *******************

    public MusicItem() {
    }

    public MusicItem(Long id) {
        this.id = id;
    }

    // ***************** Equals & HashCode **********************

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MusicItem other = (MusicItem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    // ******************* Cast to String **********************

    @Override
    public String toString() {
        return "MusicItem{" 
                + "id=" + id 
                + ", num=" + num 
                + ", title=" + title 
                + ", artist=" + artist 
                + ", releaseDate=" + getReleaseDateString()
                + ", listPrice=" + listPrice 
                + ", price=" + price 
                + '}';
    }
        
}
