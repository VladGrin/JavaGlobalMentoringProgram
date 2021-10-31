package com.apress.spring.task1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootGreetingApplication {

    private static final Logger LOG = LoggerFactory.getLogger(SpringBootGreetingApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootGreetingApplication.class, args);
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
