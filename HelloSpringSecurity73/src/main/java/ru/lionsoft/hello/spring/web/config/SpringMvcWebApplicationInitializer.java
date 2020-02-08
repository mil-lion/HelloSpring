/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-12 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.web.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/*
web.xml:

    <!-- config files for ContextLoaderListener -->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>
            /WEB-INF/applicationContext.xml
            /WEB-INF/inMemoryItemDAO.xml
        </param-value>
    </context-param>

    <!-- Load root application context at startup -->
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>

    <!-- Servlet element for Spring MVC Dispatcher Servlet -->
    <servlet>
        <servlet-name>springmvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>springmvc</servlet-name>
        <url-pattern>*.do</url-pattern>
    </servlet-mapping>

*/

/**
 * Spring MVC setup
 * @author Igor Morenko <morenko at lionsoft.ru>
 */
public class SpringMvcWebApplicationInitializer 
        extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ ApplicationConfig.class, SpringSecurityConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ WebApplicationConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
//        return new String[]{ "*.do" };
        return new String[]{ "/" };
    }
    
}
