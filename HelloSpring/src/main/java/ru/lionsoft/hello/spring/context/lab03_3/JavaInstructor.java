/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.context.lab03_3;

import java.util.Collection;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

// TODO - Add Component annotation
@Component("springGuru")
public class JavaInstructor implements Teacher {

    // TODO - Add Resource annotation
    @Resource(name="springBook")  // Commented out to do optional autowiring
    private InfoSource info;
    
    @Autowired  // Optional autowiring
    private Collection<InfoSource> infoSources;

    @Resource
    @Qualifier("Java") // Optional Qualifier part.
    private Collection<InfoSource> javaInfo;
    
//    @Autowired // Optional autowired by proberty name = bean name
    private InfoSource scalaBook;
    
    @Autowired // Optional autowired by proberty name = bean name
    public void setScalaBook(InfoSource scalaBook) {
        this.scalaBook = scalaBook;
    }
    
    @Autowired // Optional autowired by bean name
    @Qualifier("javaBook")
    private InfoSource javaBook;

    @Override
    public void teach() {
        System.out.println("\n@@@@ JavaInstructor.teach()");
        System.out.println("springInfo: " + info.getData());
        System.out.println("javaInfo: " + javaInfo);
        System.out.println("javaBook: " + javaBook.getData());
        System.out.println("scalaBook: " + scalaBook.getData());
        System.out.println("infoSources:");
        infoSources.forEach(e -> System.out.println("- " + e.getData()));
    }
}
