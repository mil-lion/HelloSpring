/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.context.lab02_3;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import static ru.lionsoft.hello.spring.util.PathUtil.path;

public class TeachMeSpring {
    
    private static final String APPLICATION_CONTEXT = path(TeachMeSpring.class, "applicationContext1.xml");

    public static void main(String[] args) {
        System.out.println("We're ready to learn Spring");
        // initialize context
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT);
        
        // find bean
        Teacher teacher = ctx.getBean("springGuru", Teacher.class);
        // invoke business method
        teacher.teach();
        
        // close spring context
        ctx.close();
        System.out.println("\nApplicationContext closed");
    }
}
