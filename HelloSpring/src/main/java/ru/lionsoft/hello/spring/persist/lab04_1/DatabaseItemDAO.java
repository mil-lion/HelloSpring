/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.lionsoft.hello.spring.persist.lab04_1;

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
import ru.lionsoft.hello.spring.persist.entity.MusicItem;

/**
 *
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
@Component
public class DatabaseItemDAO implements ItemDAO {
    
    @Resource(name = "sampleDataSource") // field injection
    private DataSource dataSource;

    // ************************* Business Methods ***********************
            
    @Override
    public MusicItem get(Long id) {
        MusicItem item = null;
        try (Connection connection = dataSource.getConnection();) {
            try (PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM items WHERE item_id=?");) {
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
        Collection<MusicItem> items = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();) {
            String sqlText = "SELECT * FROM items WHERE title LIKE '%" + keyword + "%' OR artist LIKE '%" + keyword + "%'";
            try (Statement stmt = connection.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(sqlText);) {
                    while (rs.next()) {
                        items.add(createMusicItem(rs));
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        return items;
    }
    
    // ********************** Methods ******************
    
    // mapping ResultSet => Object MusicItem
    private MusicItem createMusicItem(ResultSet rs) throws SQLException {
        return new MusicItem(
                rs.getLong("item_id"),
                rs.getString("num"),
                rs.getString("title"),
                rs.getString("artist"),
                rs.getDate("release_date"),
                rs.getBigDecimal("list_price"),
                rs.getBigDecimal("price")
        );
    }

}
