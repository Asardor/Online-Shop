package com.company.onlineshop.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @PostConstruct
    public void init() {
        System.out.println("This init method execute!");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("This destroy method execute!");
    }

}
