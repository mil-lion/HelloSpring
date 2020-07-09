/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.persist.lab06_2;

import java.math.BigDecimal;
import java.sql.Date;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.lionsoft.hello.spring.persist.entity.MusicItem;
import static ru.lionsoft.hello.spring.util.PathUtil.path;

public class SpringHibernateTest {

    public static void main(String[] args) throws Exception {
        String[] configFiles = {path(SpringHibernateTest.class, "applicationContext.xml")};
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(configFiles);
        System.out.println("Created container");

        MusicItemDAO dao = (MusicItemDAO) ctx.getBean("musicItemDAO");
        SessionFactory sf = (SessionFactory) ctx.getBean("javatunesSessionFactory");
        try {
            String newItemNum = "CD525";
            if (args.length != 0) {
                newItemNum = args[0];
            }

            MusicItem mItem = new MusicItem();

            //* Commented out so we don't keep creating instances during testing
            // Create a new MusicItem
            mItem.setNum(newItemNum);
            mItem.setTitle("Master of Puppets");
            mItem.setArtist("Metallica");
            mItem.setReleaseDate(Date.valueOf("1986-03-03"));
            mItem.setListPrice(new BigDecimal("25.00"));
            mItem.setPrice(new BigDecimal("14.99"));
            System.out.println("Created MusicItem: " + mItem);

            // test create
            System.out.println("*** create ***");
            sf.getCurrentSession().beginTransaction();
            dao.create(mItem);
            sf.getCurrentSession().getTransaction().commit();
            System.out.println("Created item: " + mItem.getId());
            // */
            // Modifying an entity
            System.out.println("*** update ***");
            Long modId = 5L;
            sf.getCurrentSession().beginTransaction();
            mItem = dao.searchById(modId);
            System.out.println("Retrieved MusicItem with id, Title: " + modId + ", " + mItem.getTitle());
            mItem.setTitle("This is a new Title");
            sf.getCurrentSession().getTransaction().commit();

            // Deleting an entity
            Long delId = 12L;
            sf.getCurrentSession().beginTransaction();
            mItem = dao.searchById(delId);
            dao.delete(mItem);
            sf.getCurrentSession().getTransaction().commit();

        } catch (HibernateException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
