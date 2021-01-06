package com.qibao.webutils;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.qibao.webutils"})
public class WebutilsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebutilsApplication.class, args);
    }

}
