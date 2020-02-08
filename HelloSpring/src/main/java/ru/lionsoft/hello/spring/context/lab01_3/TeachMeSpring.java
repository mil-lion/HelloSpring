/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.context.lab01_3;

//TODO - Import the needed Spring classes
import org.springframework.context.support.ClassPathXmlApplicationContext;
import static ru.lionsoft.hello.spring.util.PathUtil.path;

public class TeachMeSpring {

    public static final String APPLICATION_CONTEXT = path(TeachMeSpring.class, "applicationContext.xml");

    public static void main(String[] args) {
        System.out.println("We're ready to learn Spring");
        // TODO: Create an ApplicationContext that reads the applicationContext.xml file on the classpath
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT);

        // TODO - Uncomment below and look up the springGuru bean to initialize the teacher variable
        Teacher teacher = ctx.getBean("springGuru", Teacher.class);
        // TODO - Have the teacher teach.
        teacher.teach();

        ctx.close();  // Close the context
        System.out.println("\nApplicationContext closed");
    }
}
