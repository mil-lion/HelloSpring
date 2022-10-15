/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitablity for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-12 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.persist.lab04_3;

import java.util.Collection;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.lionsoft.hello.spring.persist.entity.MusicItem;

/**
 *
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
public class JavaTunes {
    
    public static void main(String[] args) {
        
        try ( // Spring Context Initizlize
                AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class)) {
            // get Catalog Bean
            Catalog catalog = context.getBean("catalog", Catalog.class);
            // call Business Methods
            
            // Test 1
            System.out.println("result 1: " + catalog.findById(1L));
            // Test 2
            Collection<MusicItem> items = catalog.findByKeyword("nn");
            System.out.println("result 2:");
            items.forEach(System.out::println);
        } // AutoClose Spring Context
    }
}
