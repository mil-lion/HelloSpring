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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

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
public class SpringSecurityConfig {
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
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//                .authorizeRequests(authorize -> authorize
//                                                .anyRequest().authenticated()
//		)
//                .formLogin();
                .authorizeRequests().anyRequest().hasAnyRole("ADMIN", "USER")
            .and()
                .formLogin(); //.httpBasic()
        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) {
//        UserDetails admin = User //.withDefaultPasswordEncoder()                
//                .withUsername("ladmin")
//                .password("{noop}admin@password") // {noop} makes sure that the password encoder doesn't do anything
//                .roles("ADMIN") // Role of the user
//                .build();
//        UserDetails user = User
//                .withUsername("luser")
//                .password(bCryptPasswordEncoder.encode("user@password"))
//                .roles("LOCAL", "USER")
//                .build();
//        return new InMemoryUserDetailsManager(admin, user);
//    }

    @Bean 
    public JdbcUserDetailsManager jdbcUserService(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        
        manager.setUsersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?");
        manager.setAuthoritiesByUsernameQuery("SELECT username, authority FROM authorities WHERE username = ?");
        
        return manager;
    }

//    @Bean
//    public AuthenticationManager authManager(HttpSecurity http, DataSource dataSource, BCryptPasswordEncoder bCryptPasswordEncoder)
//            throws Exception {
//        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
//        // First Provider
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?")
//                .authoritiesByUsernameQuery("SELECT username, authority FROM authorities WHERE username = ?")
//                .passwordEncoder(bCryptPasswordEncoder);
//        
//        // Second Provider
//        auth.inMemoryAuthentication()
//                .withUser("ladmin")
//                .password("{noop}admin@password") // {noop} makes sure that the password encoder doesn't do anything
//                .roles("ADMIN") // Role of the user
//            .and()
//                .withUser("luser")
//                .password(bCryptPasswordEncoder.encode("user@password"))
//                .roles("LOCAL", "USER");
//       
//        return auth.build();
//    }
    
}
