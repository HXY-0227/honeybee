package com.honeybee;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@MapperScan("com.honeybee.dao")
@ComponentScan(basePackages = {"com.honeybee"})
public class HoneybeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(HoneybeeApplication.class, args);
    }
}
