/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.context.lab02_4;

/**
 *
 * @author Администратор
 */
public class SpringCourseBook implements InfoSource {
    
    @Override
    public String getData() {
        return "Spring 5 in Action, 5th Edition";
    }
}
