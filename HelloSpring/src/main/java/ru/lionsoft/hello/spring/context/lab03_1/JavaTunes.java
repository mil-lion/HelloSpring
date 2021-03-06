/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.context.lab03_1;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.lionsoft.hello.spring.context.entity.MusicItem;
import static ru.lionsoft.hello.spring.util.PathUtil.path;

public class JavaTunes {

    private static final String APPLICATION_CONTEXT = path(JavaTunes.class, "applicationContext.xml");
    private static final String IN_MEMORY_ITEM_DAO = path(JavaTunes.class, "inMemoryItemDAO.xml");

    public static void main(String[] args) {
        System.out.println("We're ready to learn Spring");

        // Create an ApplicationContext that reads the multiple files, using various capabilities
        // Use wildcards
        // ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:ru/lionsoft/hello/spring/context/lab03_1*.xml");
        
        // Use file: - note this will be relative to the root directory of the project, which is where it is run from
        // ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("file:target/classes/ru/lionsoft/hello/spring/context/lab03_1/*.xml");
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("file:target/classes/**/lab03_1/*.xml");

        // List the two files directly
        // ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT, IN_MEMORY_ITEM_DAO);
        
        // Look up the javaTunesCatalog bean 
        Catalog cat = (Catalog) ctx.getBean("javaTunesCatalog");

        // Search by id - returns a single item
        System.out.println(cat.findById(Long.valueOf(2)));

        // The below will now throw an exception, since mi1 is an inner bean
        MusicItem mi = (MusicItem) ctx.getBean("mi1");
        System.out.println("Looked up bean by id = mi1: ");
        System.out.println(mi);

        ctx.close();  // Close the context		
    }
}
