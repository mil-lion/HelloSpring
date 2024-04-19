/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2024 LionSoft LLC.
 */
package ru.lionsoft.hellospring.web.config;

import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.JsonbHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2CollectionHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
springmvc-servlet.xml:

    <context:component-scan base-package="ru.lionsoft.hellospring.web"/>

    <!-- TODO - add mvc annotation support - which includes message converters -->
    <mvc:annotation-driven>
        <mvc:message-converters>
            <bean id="jaxbConverter" class="org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter">
                <property name="supportedMediaTypes" value="application/xml"/>
            </bean>
            <bean id="jsonbConverter" class="org.springframework.http.converter.json.JsonbHttpMessageConverter">
                <property name="supportedMediaTypes" value="application/json"/>
            </bean>
        </mvc:message-converters>
    </mvc:annotation-driven>
*/

/**
 * Конфигурирование Spring Web MVC
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "ru.lionsoft.hellospring.web")
public class WebMvcApplicationConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jaxb2RootElementHttpMessageConverter jaxbConverter = new Jaxb2RootElementHttpMessageConverter();
        jaxbConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_XML));
        converters.add(jaxbConverter);

        JsonbHttpMessageConverter jsonbConverter = new JsonbHttpMessageConverter();
        jsonbConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
        converters.add(jsonbConverter);
        
//        Jaxb2CollectionHttpMessageConverter converter = new Jaxb2CollectionHttpMessageConverter();
//        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_XML));
//        converters.add(converter);                
    }

}
