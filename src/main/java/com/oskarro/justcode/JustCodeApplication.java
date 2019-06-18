package com.oskarro.justcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class JustCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(JustCodeApplication.class, args);
    }

}
