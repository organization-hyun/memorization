package com.hyunjuuun.memorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MemorizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemorizationApplication.class, args);
    }

}
