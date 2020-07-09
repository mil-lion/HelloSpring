/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitablity for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.persist.lab06_1;

import java.util.Collection;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.lionsoft.hello.spring.persist.entity.MusicItem;
import static ru.lionsoft.hello.spring.util.PathUtil.path;

class SpringHibernateTest {

    public static void main(String[] args) throws Exception {
        String[] configFiles = {path(SpringHibernateTest.class, "applicationContext.xml")};
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(configFiles);
        System.out.println("Application Context Created");

        MusicItemDAO dao = (MusicItemDAO) ctx.getBean("musicItemDAO");
        SessionFactory sf = (SessionFactory) ctx.getBean("javatunesSessionFactory");
        try {
            Session s = sf.getCurrentSession();
            s.beginTransaction();
            
            // test searchById
            System.out.println("\n*** searchById = 2 ***");
            MusicItem item = dao.searchById(2L);
            System.out.println(item);
            System.out.println("*** searchById = 2 ***\n");

            // test searchByKeyword
            System.out.println("\n*** searchByKeyword = nn ***");
            Collection<MusicItem> items = dao.searchByKeyword("nn");
            items.forEach(System.out::println);
            System.out.println("*** searchByKeyword = nn ***\n");
            
            s.getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println("Error: " + ex.getMessage());
        }
        
        ctx.close();
        System.out.println("Application Context Closed");
    }
}
