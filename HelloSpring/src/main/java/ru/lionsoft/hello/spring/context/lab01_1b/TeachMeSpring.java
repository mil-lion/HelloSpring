/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.context.lab01_1b;

public class TeachMeSpring {

    public static void main(String[] args) {
        
        // Create componenet
        JavaInstructor javaInstructor = new JavaInstructor();
        javaInstructor.setInfo(new SpringCourseBook());
        
        // Invoke business method
        Teacher teacher = javaInstructor;
        teacher.teach();
    }
}
