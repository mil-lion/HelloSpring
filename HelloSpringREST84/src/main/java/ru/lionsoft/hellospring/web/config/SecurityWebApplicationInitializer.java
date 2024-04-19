/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2024 LionSoft LLC.
 */
package ru.lionsoft.hellospring.web.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/*
web.xml:

    <!-- Filter for Spring Security -->
    <filter>
        <filter-name>springSecurityFilterChain</filter-name>
        <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>springSecurityFilterChain</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping> 	

*/

/**
 * Конфигурирование Filter for Spring Security
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
    // Nothing to be added here
}
