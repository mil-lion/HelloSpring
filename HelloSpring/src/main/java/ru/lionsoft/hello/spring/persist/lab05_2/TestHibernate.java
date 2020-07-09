/*
 *
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitablity for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-12 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.persist.lab05_2;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.lionsoft.hello.spring.persist.entity.Event;
import ru.lionsoft.hello.spring.persist.entity.MusicItem;

public class TestHibernate {

    public static void main(String[] args) {

        SessionFactory sf;
        Session s;

        try {
            sf = new Configuration().configure().buildSessionFactory();
            s = sf.openSession();
            s.beginTransaction();

            System.out.println("Session connect status: " + s.isConnected());

            Long id = 1L;
            Event event = s.get(Event.class, id);
            System.out.println("Retreived event: " + event.getTitle());

            id = 2L;
            MusicItem mItem = (MusicItem) s.get(MusicItem.class, id);
            System.out.println("Retrieved MusicItem with Num: " + mItem.getNum());
            System.out.println("mItem = " + mItem);

            s.getTransaction().commit();
            s.close();
            sf.close();

        } catch (HibernateException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
