/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.web.config;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.JsonbHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
<?xml version="1.0" encoding="UTF-8"?>

<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd 
                           http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
                           http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!-- TODO - add in component scanning starting at package com.javatunes.web -->
    <context:component-scan base-package="ru.lionsoft.hello.spring"/>
	
    <!-- TODO - add mvc annotation support - which includes message converters -->
    <mvc:annotation-driven>
        <mvc:message-converters>
            <bean id="jsonConverter" class="org.springframework.http.converter.json.JsonbHttpMessageConverter">
                <property name="supportedMediaTypes" value="application/json"/>
            </bean>
        </mvc:message-converters>
    </mvc:annotation-driven>

</beans>
*/

/**
 * Конфигурирование Spring Web MVC
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
@Configurable
@EnableWebMvc
@ComponentScan("ru.lionsoft.hello.spring.web")
public class WebMvcApplicationConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        JsonbHttpMessageConverter jsonbMessageConverter = new JsonbHttpMessageConverter();
        jsonbMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
        converters.add(jsonbMessageConverter);
    }
        
}
