/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.context.lab03_4.optional;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TeachMeSpring {

    public static void main(String[] args) {
        System.out.println("We're ready to learn Spring");
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.scan("ru.lionsoft.hello.spring.context.lab03_4.optional");
        ctx.refresh();
        Teacher teacher = ctx.getBean("springGuru", Teacher.class);
        teacher.teach();
        ctx.close();  // Close the context
    }
}
