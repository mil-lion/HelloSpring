/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.persist.lab04_1;

import java.util.Collection;
import java.util.stream.Stream;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.lionsoft.hello.spring.persist.entity.MusicItem;

/**
 *
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
public class JavaTunes {
    
    public static void main(String[] args) {
        System.out.println("@@@@ main()");
        
        // Initialize Application Context
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JavaTunes.class.getPackageName());
        System.out.println("Application Context ready.");
        
        // Get(Search) Bean
        Catalog catalog = ctx.getBean("catalog", Catalog.class);
        System.out.println("\nFind Bean Catalog: class " + catalog.getClass().getName());

        // Invoke Business Method
        System.out.println("result: " + catalog.findById(1L));
        
        Collection<MusicItem> items = catalog.findByKeyword("nn");
        System.out.println("\nFind:");
        items.forEach(System.out::println);

        System.out.println("\nList Bean Definition Names:");
        Stream.of(ctx.getBeanDefinitionNames()).forEach(System.out::println);
        
        ctx.close();  // Close the context
        System.out.println("\nApplicationContext closed");
    }
}
