/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitablity for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.context.lab01_2;

// TODO - Have JavaInstructor implement Teacher
public class ScalaInstructor implements Teacher {

    // ************* Business Methods ******************

    @Override
    public void teach() {
        System.out.println("Scala - functional orientation programming");
    }
}
