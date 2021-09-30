package com.example.springsecuritysecuritycontextholder.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class CustomWebSecurityConfigurerAdaptor extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeRequests().mvcMatchers("**/secure/**").authenticated().and().authorizeRequests()
                .mvcMatchers("**/public/**").permitAll();
    }

}
