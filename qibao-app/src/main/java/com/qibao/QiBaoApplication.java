package com.qibao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动类
 *
 * @author qibao
 * @version v0.1 2021/1/6
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.qibao"})
public class QiBaoApplication {
    public static void main(String[] args) {
        SpringApplication.run(QiBaoApplication.class);
    }
}
