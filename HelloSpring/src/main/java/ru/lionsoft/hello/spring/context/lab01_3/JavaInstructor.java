/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitablity for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.context.lab01_3;

public class JavaInstructor implements Teacher {

    // ************* Bean Properties *******************
    
    private InfoSource info;

    public void setInfo(InfoSource infoIn) {
        info = infoIn;
    }

    // ************* Business Methods ******************
    
    @Override
    public void teach() {
        System.out.println("\n@@@@ JavaInstructor.teach() - " + info.getData());
    }
}
