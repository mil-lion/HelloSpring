/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.context.lab02_1;

import java.util.Collection;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.lionsoft.hello.spring.context.entity.MusicItem;
import static ru.lionsoft.hello.spring.util.PathUtil.path;

public class JavaTunes {

    public static final String APPLICATION_CONTEXT = path(JavaTunes.class, "applicationContext.xml");

    public static void main(String[] args) {
        System.out.println("We're ready to learn Spring");
        // TODO: Create an ApplicationContext that reads the applicationContext.xml file on the classpath
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT);
        // TODO - Look up the javaTunesCatalog bean 
        Catalog catalog = ctx.getBean("javaTunesCatalog", Catalog.class);

        // TODO - the following compiles, but will only run properly when you finish the wiring up in the Spring configuration
        // and the initialization above
        // Search by id - returns a single item
        System.out.println("result: " + catalog.findById(1L));
        
        // Search by keyword - returns up to maxSearchResults items for JavaTunesCatalog
        Collection<MusicItem> items = catalog.findByKeyword("a");
        System.out.println("result:");
        items.forEach(System.out::println);

        ctx.close();  // Close the context
        System.out.println("\nApplicationContext closed");
    }
}
