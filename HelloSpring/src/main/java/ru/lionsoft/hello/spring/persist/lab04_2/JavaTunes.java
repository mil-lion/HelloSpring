/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitablity for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-12 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.persist.lab04_2;

import java.util.Collection;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
public class JavaTunes {
    
    public static void main(String[] args) {
        System.out.println("@@@@ main()");
        
        // Initialize Application Context
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        System.out.println("Application Context ready.");
        
        // Get(Search) Bean
        Catalog catalog = ctx.getBean("catalog", Catalog.class);
        System.out.println("Find Bean Catalog: class " + catalog.getClass().getName());

        // Invoke Business Method
        System.out.println("result: " + catalog.findById(1L));
        
        Collection<MusicItem> items = catalog.findByKeyword("a");
        System.out.println("Find:");
        items.forEach(System.out::println);        
    }
}
