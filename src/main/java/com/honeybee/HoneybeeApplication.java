package com.honeybee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class HoneybeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(HoneybeeApplication.class, args);
    }
}


