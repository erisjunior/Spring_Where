package com.erisvan.where;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WhereApplication {

    @Bean
    public CommandLineRunner init() {
        return args -> {
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(WhereApplication.class, args);
    }

}
