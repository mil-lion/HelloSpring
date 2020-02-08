/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.context.lab03_4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TeachConfig {

    @Bean
    public Teacher springGuru() {
        JavaInstructor ji = new JavaInstructor();
        ji.setInfo(springBook());
        return ji;
    }

    @Bean
    public InfoSource springBook() {
        return new SpringCourseBook();
    }

    @Bean
    public InfoSource rubyBook() {
        return new RubyCourseBook();
    }
}
