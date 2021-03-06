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

@Component  // For optional qualifier part
@Qualifier("Scala")
public class ScalaCourseBook implements InfoSource {

    @Override
    public String getData() {
        return "Scala - functional orientation programming";
    }
}
