package com.indestructible_backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.indestructible_backend.mapper")
public class IndestructibleBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(IndestructibleBackendApplication.class, args);
    }

}
