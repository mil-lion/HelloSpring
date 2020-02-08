/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.context.lab03_3;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import static ru.lionsoft.hello.spring.util.PathUtil.path;

public class TeachMeSpring {

    public static final String APPLICATION_CONTEXT = path(TeachMeSpring.class, "applicationContext.xml");
    
    public static void main(String[] args) {
        System.out.println("We're ready to learn Spring");
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT);
        
        Teacher teacher = ctx.getBean("springGuru", Teacher.class);
        teacher.teach();
        
        ctx.close();  // Close the context
        System.out.println("\nApplicationContext closed");
    }
}
