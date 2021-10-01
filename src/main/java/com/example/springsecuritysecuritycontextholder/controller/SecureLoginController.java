package com.example.springsecuritysecuritycontextholder.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureLoginController {

    @GetMapping("/secure/resource")
    public String hello(){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        return "Hi! " +authentication.getName();
    }

    @GetMapping("/secure/resource/thread")
    @Async
    public String helloThread(){
        SecurityContext securityContext = SecurityContextHolder.getContextHolderStrategy().getContext();
        Authentication authentication = securityContext.getAuthentication();
        System.out.println("Hi! " +authentication.getName());
        return "Hi! " + SecurityContextHolder.getContextHolderStrategy().toString() + " " +authentication.getName();
    }

}
