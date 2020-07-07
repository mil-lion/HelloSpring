/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.context.lab02_2;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.lionsoft.hello.spring.context.entity.MusicItem;
import static ru.lionsoft.hello.spring.util.PathUtil.path;

public class JavaTunes {

    private static final String APPLICATION_CONTEXT = path(JavaTunes.class, "applicationContext.xml");

    public static void main(String[] args) {
        System.out.println("We're ready to learn Spring");
        // Create the application context
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT);

        // Look up the javaTunesCatalog bean 
        Catalog catalog = (Catalog) ctx.getBean("javaTunesCatalog");

        // Search by id - returns a single item
        System.out.println("result: " + catalog.findById(2L));

        // Look up one of the individual items that are configured
        MusicItem mi = (MusicItem) ctx.getBean("mi1");
        System.out.println("Looked up bean by id = mi1: " + mi);

        ctx.close();  // Close the context
        System.out.println("\nApplicationContext closed");
    }
}
