package com.txview.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.txview")
@EntityScan(basePackages = "com.txview")
@EnableJpaRepositories(basePackages = "com.txview")
public class DomainApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(DomainApiApplication.class, args);
    }
}
