/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2024 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.web.model.entity;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.json.bind.annotation.JsonbPropertyOrder;
import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.json.bind.annotation.JsonbTypeAdapter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Сущность музыкальный элемент
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
@Entity
@Table(name = "items")
@XmlRootElement
@XmlType(propOrder = {"num", "title", "artist", "releaseDate", "listPrice", "price"})
@JsonbPropertyOrder({"id", "num", "title", "artist", "releaseDate", "listPrice", "price"})
public class MusicItem implements Serializable {
    private static final long serialVersionUID = 1L;

    // ***************** Properties *********************

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;
    
    @Column
    private String num;
    
    @Column
    private String title;
    
    @Column
    private String artist;
    
    @Column(name = "release_date")
    private Date releaseDate;
    
    @Column(name = "list_price")
    private BigDecimal listPrice;
    
    @Column
    private BigDecimal price;

    @XmlAttribute
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

    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(JaxbDateAdapter.class)
//    @JsonbDateFormat("yyyy-MM-dd")
    @JsonbTypeAdapter(JsonbDateAdapter.class)
    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Transient
    private final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @XmlTransient
    @JsonbTransient
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

    // ******************* Constructors **********************
    
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
        try {
            this.releaseDate = df.parse(releaseDate);
        } catch (ParseException ex) {
            Logger.getLogger(MusicItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.listPrice = new BigDecimal(listPrice);
        this.price = new BigDecimal(price);
    }

    // *********************** Equals & HashCode ***********************
    
    // override Object.equals
    @Override
    public boolean equals(Object compare) {
        if (compare instanceof MusicItem) {
            // cast to MusicItem
            MusicItem other = (MusicItem) compare;

            // if all the ids are equal, the objects are equal
            return other.getId().equals(this.getId());
        }
        return false;
    }

    // override Object.hashCode
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    // ********************** Cast to String ***************************
    
    // override Object.toString
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{"
                + "id=" + id
                + ", num='" + num
                + "', title='" + title
                + "', artist='" + artist
                + "', releaseDate='" + getReleaseDateString()
                + "', listPrice=" + listPrice
                + ", price=" + price
                + "}";
    }
    
}
