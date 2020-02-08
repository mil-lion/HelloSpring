/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitablity for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.persist.lab06_1;

import org.hibernate.SessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.lionsoft.hello.spring.persist.entity.MusicItem;

class SpringHibernateTest {

    public static void main(String[] args) throws Exception {
        String[] configFiles = {"ru/lionsoft/hello/spring/persist/lab06_1/applicationContext.xml"};
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(configFiles);
        System.out.println("Created container");

        MusicItemDAO dao = (MusicItemDAO) ctx.getBean("musicItemDAO");
        SessionFactory sf = (SessionFactory) ctx.getBean("javatunesSessionFactory");
        try {
            // test searchById
            System.out.println("*** searchById = 2 ***");
            sf.getCurrentSession().beginTransaction();
            MusicItem item = dao.searchById(new Long(2));
            sf.getCurrentSession().getTransaction().commit();
            System.out.println(item);
            System.out.println("*** searchById = 2 ***\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
