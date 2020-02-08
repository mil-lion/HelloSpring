/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitablity for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.context.lab01_3;

// TODO - Have SpringCourseBook implement InfoSource
public class SpringCourseBook implements InfoSource {

    // ************* Business Methods ******************
    
    @Override
    public String getData() {
        return "Dependencies are not so cool";
    }
}
