/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.lionsoft.hello.spring.context.lab03_3;

/**
 * Bean Instructor Data
 * @author Igor Morenko
 */
public class InstructorData {
    
    // ********************* Properties *******************
    
    private String name;    
    private Double age;
    
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    // ********************* Constructors *******************

    public InstructorData() {
    }

    public InstructorData(String name, Double age) {
        this.name = name;
        this.age = age;
    }

    // ********************* Cast to String *******************

    @Override
    public String toString() {
        return "InstructorData{" 
                + "name=" + name 
                + ", age=" + age 
                + '}';
    }

}
