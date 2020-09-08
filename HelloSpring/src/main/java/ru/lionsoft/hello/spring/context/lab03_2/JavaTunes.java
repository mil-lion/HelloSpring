/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.context.lab03_2;

import java.util.stream.Stream;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.lionsoft.hello.spring.context.entity.MusicItem;
import static ru.lionsoft.hello.spring.util.PathUtil.path;

/**
 * 
 * @author Igor Morenko
 */
public class JavaTunes {

    private static final String APPLICATION_CONTEXT = path(JavaTunes.class, "applicationContext.xml");
    private static final String IN_MEMORY_ITEM_DAO = path(JavaTunes.class, "inMemoryItemDAO.xml");
    
    public static void main(String[] args) {
        System.out.println("We're ready to learn Spring");
        
        // List the two files directly
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
                APPLICATION_CONTEXT, IN_MEMORY_ITEM_DAO);

        // Look up the javaTunesCatalog bean 
        Catalog cat = (Catalog) ctx.getBean("javaTunesCatalog");

        // Search by id - returns a single item
        System.out.println(cat.findById(2L));
/*		
        // The below will now throw an exception if uncommented, since mi1 is an inner bean
        MusicItem mi = (MusicItem)ctx.getBean("mi1");
        System.out.println(mi);
*/

        // Optional - getting names of all defined beans.
        System.out.println("\n*** Listing of all bean names:");
        String[] names = ctx.getBeanDefinitionNames();
        for (String beanName : names) {
            System.out.println(beanName);
        }

        System.out.println("\n*** Listing of all bean names for type Catalog:");
        Stream.of(ctx.getBeanNamesForType(Catalog.class)).forEach(System.out::println);
        
        // Publish event to ApplicationContext
        System.out.println("\n*** Publish Event to ApplicationContext");
        ctx.publishEvent("Hello from Application Context!");
        
        ctx.close();
        System.out.println("\nApplicationContext closed");
    }
}
