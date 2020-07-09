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

import java.util.Collection;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.lionsoft.hello.spring.persist.entity.Customer;
import ru.lionsoft.hello.spring.persist.entity.DiscountCode;
import ru.lionsoft.hello.spring.persist.entity.MicroMarket;

public class TestHibernate {

    public static void main(String[] args) {

        SessionFactory sf;
        Session s;

        try {
            sf = new Configuration().configure().buildSessionFactory();
            s = sf.openSession();
            s.beginTransaction();
            System.out.println("Session connect status: " + s.isConnected());

            DiscountCode discountCode = s.find(DiscountCode.class, "H");
            System.out.println("discountCode = " + discountCode);

            MicroMarket microMarket = s.find(MicroMarket.class, "95051");
            System.out.println("micromarket = " + microMarket);
            
            Customer customer = s.find(Customer.class, 1);
            System.out.println("customer = " + customer);
            
            Collection<Customer> customers = 
                    s.createNamedQuery("Customer.findAll", Customer.class)
                            .getResultList();
            System.out.println("Customers:");
            customers.forEach(System.out::println);
            
            s.getTransaction().commit();
            s.close();
            sf.close();
        } catch (HibernateException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
         }
    }

}
