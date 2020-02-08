/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.context.lab01_1a;


public class TeachMeSpring {

    public static void main(String[] args) {
        JavaInstructor teacher = new JavaInstructor();
        teacher.setInfo(new SpringCourseBook());
        teacher.teach();
    }
}
