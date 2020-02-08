/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.lionsoft.hello.spring.jpa.lab60;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Igor Morenko <morenko at lionsoft.ru>
 */
public class HibernateUtil {
    
    private static final SessionFactory SESSION_FACTORY;
    
    static {
        // Static initializer to create SessionFactory singleton
        try {
            SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
        } catch (Exception ex) {
            System.err.println("Initial SessionFactory failed. " + ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
}
