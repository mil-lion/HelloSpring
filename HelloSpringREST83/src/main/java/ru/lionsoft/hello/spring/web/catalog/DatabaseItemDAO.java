/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.web.catalog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.stereotype.Component;
import ru.lionsoft.hello.spring.web.entity.MusicItem;

/**
 *
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
@Component("itemDAO")
public class DatabaseItemDAO implements ItemDAO {

    @Resource(name = "sampleDS")
    private DataSource dataSource;
    
    // ****************** Business Methods *******************
    
    @Override
    public MusicItem get(Long id) {
        MusicItem item = null;
        try (Connection connection = dataSource.getConnection();) {
            final String sqlText = "SELECT * FROM items WHERE item_id=?"; 
            try (PreparedStatement pstmt = connection.prepareStatement(sqlText);) {
                pstmt.setLong(1, id);
                try (ResultSet rs = pstmt.executeQuery();) {
                    if (rs.next()) {
                        item = createMusicItem(rs);
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        return item;
    }

    @Override
    public Collection<MusicItem> searchByArtistTitle(String keyword) {
        Collection<MusicItem> results = new ArrayList<>();
        final String sqlText = "SELECT * FROM items "
                + "WHERE artist LIKE '%" + keyword + "%' "
                + "OR title LIKE '%" + keyword + "%'";
        try (Connection connection = dataSource.getConnection();) {
            try (Statement stmt = connection.createStatement();) {
                try (ResultSet rs = stmt.executeQuery(sqlText);) {
                    while (rs.next()) {
                        results.add(createMusicItem(rs));
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        return results;
    }
    
    @Override
    public Collection<MusicItem> getAll() {
        Collection<MusicItem> results = new ArrayList<>();
        final String sqlText = "SELECT * FROM items";
        try (Connection connection = dataSource.getConnection();) {
            try (Statement stmt = connection.createStatement();) {
                try (ResultSet rs = stmt.executeQuery(sqlText);) {
                    while (rs.next()) {
                        results.add(createMusicItem(rs));
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        return results;
    }
    
    // ******************* Private Methods *******************
    
    private MusicItem createMusicItem(ResultSet rs) throws SQLException {
                                             // ORM - Object-Relation Mapping
        return new MusicItem(                // class MusicItem <=> table ITEMS
            rs.getLong("item_id"),           //              id <=> ITEM_ID
            rs.getString("num"),             //             num <=> NUM
            rs.getString("title"),           //           title <=> TITLE  
            rs.getString("artist"),          //          artist <=> ARTIST
            rs.getDate("release_date"),      //      releseDate <=> RELEASE_DATE
            rs.getBigDecimal("list_price"),  //       listPrice <=> LIST_PRICE
            rs.getBigDecimal("price")        //           price <=> PRICE
        );
    }
} 
