/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
