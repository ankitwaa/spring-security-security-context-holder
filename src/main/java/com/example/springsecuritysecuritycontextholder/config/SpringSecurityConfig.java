package com.example.springsecuritysecuritycontextholder.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SpringSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
        userDetailsService.createUser(User.withUsername("ankit").password("{noop}ankit").authorities("read").build());
        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * This type of Bean will be ready to service only after properties being set.
     * Here we are setting the SecurityContext Holder Strategy to - SecurityContextHolder.MODE_THREADLOCAL
     *
     * There are 3 types of Stratery that we can selection for SecurityContext Population.
     *
     * SecurityContextHolder.MODE_THREADLOCAL - Each Spring Managed Thread will have there own SecurityContext copied.
     * SecurityContextHolder.MODE_GLOBAL - Each Spring Manager Thread will have one SecurityContext Shared.
     * SecurityContextHolder.MODE_INHERITABLETHREADLOCAL - Each Thread and it's Child thread have there own SecurityContext copied.
     *
     * @return
     *
     */
    @Bean
    public InitializingBean securityContext() {
       // return () -> SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_THREADLOCAL);
       // return () -> SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
        return () -> SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }
}
