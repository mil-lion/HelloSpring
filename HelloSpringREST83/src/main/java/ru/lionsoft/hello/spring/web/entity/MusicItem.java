/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.web.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.annotation.JsonbTransient;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
@XmlRootElement
@XmlType(propOrder = {"num", "title", "artist", "releaseDate", "listPrice", "price"})
@JsonbPropertyOrder({"id", "num", "title", "artist", "releaseDate", "listPrice", "price"})
public class MusicItem implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    // ***************** Properties ****************
    
    private Long id;

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    @XmlAttribute
    public Long getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(Long id) {
        this.id = id;
    }

    private String num;

    /**
     * Get the value of num
     *
     * @return the value of num
     */
    public String getNum() {
        return num;
    }

    /**
     * Set the value of num
     *
     * @param num new value of num
     */
    public void setNum(String num) {
        this.num = num;
    }

    private String title;

    /**
     * Get the value of title
     *
     * @return the value of title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the value of title
     *
     * @param title new value of title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    private String artist;

    /**
     * Get the value of artist
     *
     * @return the value of artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Set the value of artist
     *
     * @param artist new value of artist
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    private Date releaseDate;

    /**
     * Get the value of releaseDate
     *
     * @return the value of releaseDate
     */
    @XmlSchemaType(name = "date")
    @JsonbDateFormat("yyyy-MM-dd")
    public Date getReleaseDate() {
        return releaseDate;
    }

    /**
     * Set the value of releaseDate
     *
     * @param releaseDate new value of releaseDate
     */
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    @XmlTransient
    @JsonbTransient
    public String getReleaseDateString() {
        return dateFormat.format(releaseDate);
    }
    
    public void setReleaseDateString(String releaseDate) {
        try {
            this.releaseDate = dateFormat.parse(releaseDate);
        } catch (ParseException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
    }
    
    private BigDecimal listPrice;

    /**
     * Get the value of listPrice
     *
     * @return the value of listPrice
     */
    public BigDecimal getListPrice() {
        return listPrice;
    }

    /**
     * Set the value of listPrice
     *
     * @param listPrice new value of listPrice
     */
    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    private BigDecimal price;

    /**
     * Get the value of price
     *
     * @return the value of price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Set the value of price
     *
     * @param price new value of price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    // **************** Constructors ***************

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
    
    public MusicItem(Long id, String num, String title, String artist, 
            String releaseDate, String listPrice, String price) {
        this.id = id;
        this.num = num;
        this.title = title;
        this.artist = artist;
        setReleaseDateString(releaseDate);
        this.listPrice = new BigDecimal(listPrice);
        this.price = new BigDecimal(price);
    }
    
    // *************** Equals & HashCode **************************

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.id);
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
    
    // ******************** Cast to String ************************

    @Override
    public String toString() {
        return "MusicItem{" 
                + "id=" + id 
                + ", num=" + num 
                + ", title=" + title 
                + ", artist=" + artist 
                + ", releaseDate=" + releaseDate 
                + ", listPrice=" + listPrice 
                + ", price=" + price 
                + '}';
    }
       
}
