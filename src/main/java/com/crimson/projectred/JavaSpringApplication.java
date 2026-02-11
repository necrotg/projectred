package com.crimson.projectred;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
@ComponentScan(basePackages = {"com.crimson.projectred", "security"})
public class JavaSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavaSpringApplication.class, args);
    }
}
