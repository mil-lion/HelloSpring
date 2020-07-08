/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.context.lab03_3;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

// TODO - import Component
// TODO - Add Component annotation
@Component("javaBook")
@Qualifier("Java") // Optional Qualifier part
public class JavaCourseBook implements InfoSource {

    @Override
    public String getData() {
        return "Java for Professional!";
    }
}
