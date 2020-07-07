/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.context.lab02_3;

/**
 *
 * @author Igor Morenko <imorenko at yandex.ru>
 */
public class MyFactory {
    
    public Teacher createJavaInstructor() {
        System.out.println("\n@@@@ MyFactory.createJavaInstructor()!!!");
        JavaInstructor instructor = new JavaInstructor();
        instructor.setInfo(new JavaCourseBook());
        return instructor;
    }
    
    public Teacher createSpringInstructor() {
        System.out.println("\n@@@@ MyFactory.createSpringInstructor()!!!");
        JavaInstructor instructor = new JavaInstructor();
        instructor.setInfo(new SpringCourseBook());
        return instructor;
    }
    
}
