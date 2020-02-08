/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-12 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.web.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*
applicationContext.xml:

<?xml version='1.0' encoding='UTF-8' ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:security="http://www.springframework.org/schema/security"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/security http://www.springframework.org/schema/security/spring-security.xsd">

    <security:http auto-config="true" use-expressions="true">
        <!-- 
            Spring Security 5+ use expressions: 
            hasRole, hasAnyRole, hasAuthority, hasAnyAuthority, permitAll, denyAll, 
            isAnonymous, isRememberMe, sAuthenticated, isFullyAuthenticated
        -->
        <security:intercept-url pattern="/**" access="hasRole('ROLE_USER')" />
    </security:http>
        

    <security:authentication-manager>
        <security:authentication-provider>
            <security:user-service>
                <!-- Spring Security 5+ add to password {noop} for plain password -->
                <security:user name="admin" password="{noop}password" authorities="ROLE_ADMIN" />
                <security:user name="user" password="{noop}password" authorities="ROLE_USER" />
            </security:user-service>
        </security:authentication-provider>
    </security:authentication-manager>

    <security:global-method-security secured-annotations="enabled" jsr250-annotations="enabled"/>

</beans>

*/

/**
 * Spring Security configuration setup
 * @author Igor Morenko <morenko at lionsoft.ru>
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().hasAnyRole("ADMIN", "USER")
            .and()
                .formLogin();
//                .httpBasic(); // Use Basic authentication 
    }

    @Autowired
    private DataSource dataSource;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // First Provider
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled"
                        + " from users where username = ?")
                .authoritiesByUsernameQuery("select username, authority"
                        + " from authorities where username = ?")
                .passwordEncoder(new BCryptPasswordEncoder());
        
        // Second Provider
        auth.inMemoryAuthentication()
                .withUser("ladmin")
                .password("{noop}admin@password") // {noop} makes sure that the password encoder doesn't do anything
                .roles("ADMIN") // Role of the user
            .and()
                .withUser("luser")
                .password("{noop}user@password")
                .roles("USER");                

    }
    
}
