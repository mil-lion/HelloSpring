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
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

// TODO - Add Component annotation
@Component("springGuru")
public class JavaInstructor implements Teacher {

    // **************** Field Injection ********************
    
    @Resource
    private ApplicationContext ctx;
    
    // TODO - Add Resource annotation
    @Resource(name="springCourseBook")  // Commented out to do optional autowiring
    // @AuAutowired // byName or byType
    // @Autowired @Qualifier("springCourseBook") // by bean name
    private InfoSource info;
    
    
    //@Resource
    @Autowired  // Optional autowiring
    private Collection<InfoSource> allInfoSources;

    @Resource
    @Qualifier("Java") // Optional Qualifier part.
    private Collection<InfoSource> javaInfoSources;
    
    @Autowired // Optional autowired by property name = bean name
    private InfoSource javaBook;
    
    // **************** Setter Injection ********************

    private InfoSource scalaBook;
    
    @Autowired // Optional autowired by bean name
    @Qualifier("scalaCourseBook")
    public void setScalaBook(InfoSource scalaBook) {
        this.scalaBook = scalaBook;
    }

    // ******************* Constructor Injection **********************
    
    private final InstructorData data;

//    @Resource // error!!!
    @Autowired // byType
    public JavaInstructor(InstructorData data) {
        this.data = data;
    }
        
    // ******************* Business Method **********************
    
    @Override
    public void teach() {
        System.out.println("\n@@@@ JavaInstructor.teach()");
        System.out.println("info: " + info.getData());
        System.out.println("javaBook: " + javaBook.getData());
        System.out.println("scalaBook: " + scalaBook.getData());
        System.out.println("javaInfoSources: ");
        javaInfoSources.forEach(e -> System.out.println("* " + e.getData()));
        System.out.println("allInfoSources:");
        allInfoSources.forEach(e -> System.out.println("* " + e.getData()));
    }
    
    // ******************* Bean Lifecycle ************************
    
    @PostConstruct
    public void init() {
        System.out.println("\n@@@@ JavaInstructor.init()");
        System.out.println("ctx = " + ctx);
        System.out.println("info = " + info);
    }
    
    @PreDestroy
    public void cleanUp() {
        System.out.println("\n@@@@ JavaInstructor.cleanUp()");
    }
}
