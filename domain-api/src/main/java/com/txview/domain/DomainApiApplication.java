package com.txview.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.txview")
public class DomainApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(DomainApiApplication.class, args);
    }
}
