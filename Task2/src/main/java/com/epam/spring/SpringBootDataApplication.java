package com.epam.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootDataApplication {

    private static final Logger LOG = LoggerFactory.getLogger(SpringBootDataApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDataApplication.class, args);
    }

    @Bean
    public String greeting() {
        return "Hello World!!!";
    }

    @Bean
    public CommandLineRunner myMethod(String greeting) {
        return args -> LOG.info(greeting);
    }
}
