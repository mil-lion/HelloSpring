/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitablity for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-12 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.web.entity;

import java.io.Serializable;
import java.sql.Date;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ITEMS")
public class MusicItem implements Serializable {

    private static final long serialVersionUID = 1L;

    // properties
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID", nullable = false)
    private Long id;

    private String num;
    
    private String title;
    
    private String artist;
    
    @Column(name = "RELEASE_DATE")
    private Date releaseDate;

    @Column(name = "LIST_PRICE")
    private BigDecimal listPrice;
    
    private BigDecimal price;

    // Constructors
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
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof MusicItem)) {
            return false;
        } else {
            return Objects.equals(this.getNum(), ((MusicItem) other).getNum());
        }
    }

    // override Object.hashCode
    @Override
    public int hashCode() {
        return Objects.hashCode(num);
    }

    // override Object.toString
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{"
                + "id=" + id
                + ", num='" + num
                + "', title='" + title
                + "', artist='" + artist
                + "', releaseDate='" + releaseDate
                + "', listPrice=" + listPrice
                + ", price=" + price
                + "}";
    }
}
