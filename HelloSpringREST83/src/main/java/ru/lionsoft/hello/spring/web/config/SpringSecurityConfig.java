/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2020 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/*
    <!-- Spring Security Configuration -->
    <security:http auto-config="true" use-expressions="true">
        <!--<security:http-basic />-->
        <!-- 
            Spring Security 5+ use expressions: 
            hasRole, hasAnyRole, hasAuthority, hasAnyAuthority, permitAll, denyAll, 
            isAnonymous, isRememberMe, isAuthenticated, isFullyAuthenticated
        -->
        <security:intercept-url pattern="/**" access="hasAnyRole('ROLE_USER','ROLE_ADMIN')" />
    </security:http>
    
    <security:authentication-manager>
        <security:authentication-provider>
            <security:user-service>
                <security:user name="admin" password="{noop}admin@password" authorities="ROLE_ADMIN" />
                <security:user name="user" password="{noop}user@password" authorities="ROLE_USER" />
            </security:user-service>
        </security:authentication-provider>
    </security:authentication-manager>   
      
    <security:global-method-security secured-annotations="enabled" jsr250-annotations="enabled"/> 

*/

/**
 * Конфигурирование Spring Security
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/**")
                .authenticated()
            .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}admin@password")
                .roles("ADMIN")
            .and()
                .withUser("user")
                .password("{noop}user@password")
                .roles("USER")
        ;
    }
        
}
