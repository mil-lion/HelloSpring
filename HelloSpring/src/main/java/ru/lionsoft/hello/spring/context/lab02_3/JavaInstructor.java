/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.context.lab02_3;

//bean
public class JavaInstructor implements Teacher {
    
    // *************** Fabric Method ****************
    
    public static JavaInstructor getInstance() {
        System.out.println("\n@@@@ JavaInstructor.getInstance() - !!!Invoke Fabric Method!!!");
        return new JavaInstructor();
    }
    
    // *************** Constructors ******************
    
    public JavaInstructor() {
        System.out.println("\n@@@@ Constructor JavaInstructor()");
    }
    
    // *************** Bean Properties ***************
    private InfoSource info;
    
    public void setInfo(InfoSource info) {
        this.info = info;
    }
    
    // **************** Business Methods ************************
    
    @Override
    public void teach() {
        System.out.println("\n@@@@ JavaInstructor.teach() - " + info.getData());
    }
}
