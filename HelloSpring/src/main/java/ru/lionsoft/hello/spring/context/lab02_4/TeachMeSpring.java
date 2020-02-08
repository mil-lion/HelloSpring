/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.context.lab02_4;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import static ru.lionsoft.hello.spring.util.PathUtil.path;

/**
 *
 * @author Администратор
 */
public class TeachMeSpring {
    
    private static final String APPLICATION_CONTEXT = path(TeachMeSpring.class, "applicationContext.xml");

    public static void main(String[] args) {
        System.out.println("We're ready to learn Spring");
        // initialize context
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT);

        for (String beanName : new String[] {"springGuru", "seniorJavaInstructor"}) {
            System.out.println("\n#### " + beanName + ":");
            // find bean
            Teacher springTeacher = ctx.getBean(beanName, Teacher.class);
            // invoke business method
            springTeacher.teach();
        }
        
        // close spring context
        ctx.close();
        System.out.println("\nApplicationContext closed");
    }
}
