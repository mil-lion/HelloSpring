/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.context.lab02_4;

import java.util.Collection;

//bean
public class JavaInstructor implements Teacher {
    
    // *********** Bean Properties **************
    
    private Collection<InfoSource> infoSources;
    
    private Collection<String> favoriteTopics;
    
    private Double age;
    
    private String subject;

    public void setInfoSources(Collection<InfoSource> infoSources) {
        this.infoSources = infoSources;
    }

    public void setFavoriteTopics(Collection<String> favoriteTopics) {
        this.favoriteTopics = favoriteTopics;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    // ******************* Static Method ********************
    
    public static Double randomAge() {
        return 20 + Math.random() * 80;
    }
    
    // ****************** Business Methods *******************
    
    @Override
    public void teach() {
        System.out.println("\n@@@@ JavaInstructor.teach()");
        System.out.println("infoSources:");
        infoSources.forEach(s -> System.out.println("- " + s.getData()));
        System.out.println("favoriteTopics: " + favoriteTopics);
        System.out.println("age: " + age);
        System.out.println("subject: " + subject);
    }
}
