/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright 2005-2024 LionSoft LLC.
 */
package ru.lionsoft.hello.spring.web.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/*
applicationContext.xml:

    <!-- Spring Security Configuration -->
    <security:http auto-config="true" use-expressions="true">
        <security:form-login/>
        <!-- 
            Spring Security 5+ use expressions: 
            hasRole, hasAnyRole, hasAuthority, hasAnyAuthority, permitAll, denyAll, 
            isAnonymous, isRememberMe, isAuthenticated, isFullyAuthenticated
        -->
        <security:intercept-url pattern="/**" access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')" />
    </security:http>

    <security:global-method-security secured-annotations="enabled" jsr250-annotations="enabled"/>

    <security:authentication-manager>
        <security:authentication-provider>
            <security:user-service>
                <!-- Spring Security 5+ add to password {noop} for plain password -->
                <security:user name="admin" password="{noop}admin@password" authorities="ROLE_ADMIN" />
                <security:user name="user" password="{noop}user@password" authorities="ROLE_USER" />
            </security:user-service>
        </security:authentication-provider>
    </security:authentication-manager>

    <bean id="webSecurityExpressionHandler" class="org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler" />  

*/

/**
 * Конфигурирование Spring Security
 * @author Igor Morenko (emailto:imorenko@yandex.ru)
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SpringSecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth.anyRequest().hasAnyRole("USER", "ADMIN"))
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
/*    
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails admin = User
                .withUsername("ladmin")
                .password("{noop}admin@password")
                .roles("ADMIN")
                .build();
        UserDetails user = User
                .withUsername("luser")
                .password("{noop}user@password")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }
*/    
    @Bean
    public JdbcUserDetailsManager jdbcUserDetailService(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        
        manager.setUsersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?");
        manager.setAuthoritiesByUsernameQuery("SELECT username, authority FROM authorities WHERE username = ?");
        
        return manager;
    }
   
    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
/*    
    @Bean 
    public AuthenticationManager authManager(HttpSecurity http, DataSource dataSource, BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {
        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        
        // First Provider
        auth.inMemoryAuthentication()
                    .withUser("ladmin")
                    .password("$2a$04$UgaEiQw.g9KRXeBc0.ZiZuc942SMad6uykxznChXURal4UFxHd92e")
                    .roles("ADMIN")
                .and()
                    .withUser("luser")
                    .password("$2a$04$zkJIUO2iwpFDEgAVFrJMyu/ZLJTq10kPM5wqu1LguckV6JBRaHqyC")
                    .roles("LOCAL", "USER")
                .and()
                    .passwordEncoder(bCryptPasswordEncoder)
                ;
        
        // Second Provider
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?")
                .authoritiesByUsernameQuery("SELECT username, authority FROM authorities WHERE username = ?")
                .passwordEncoder(bCryptPasswordEncoder);
        
        return auth.build();
    }
*/
}
