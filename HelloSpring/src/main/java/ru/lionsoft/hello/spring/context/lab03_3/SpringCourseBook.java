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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
// TODO - import Component

// TODO - Add Component annotation
@Component
@Qualifier("Java") // Optional Qualifier part
@Scope("prototype")
public class SpringCourseBook implements InfoSource {

    @Override
    public String getData() {
        return "Spring - Dependencies are not so cool (" + Integer.toHexString(hashCode()) + ")";
    }
}
