package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.excel")
public class IsSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(IsSpringBootApplication.class);
    }
}
