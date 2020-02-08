/*
 *
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitablity for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-12 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.persist.lab05_3;

import ru.lionsoft.hello.spring.persist.entity.MusicItem;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestHibernate {

    public static void main(String[] args) {

        SessionFactory sf;
        Session s;

        try {
            sf = new Configuration().configure().buildSessionFactory();
            s = sf.openSession();
            s.beginTransaction();
            System.out.println("Session connect status: " + s.isConnected());

            Long id = new Long(1);
            MusicItem mItem = (MusicItem) s.get(MusicItem.class, id);
            System.out.println("ival = " + mItem);
            System.out.println("Retrieved MusicItem with Num: " + mItem.getNum());

            s.getTransaction().commit();
            s.close();
            sf.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
