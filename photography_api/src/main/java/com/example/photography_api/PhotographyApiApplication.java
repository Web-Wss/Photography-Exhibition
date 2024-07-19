package com.example.photography_api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.photography_api.mapper")
public class PhotographyApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotographyApiApplication.class, args);
    }

}
