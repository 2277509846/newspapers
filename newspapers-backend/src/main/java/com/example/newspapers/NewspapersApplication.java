package com.example.newspapers;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.newspapers.dao")
public class NewspapersApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewspapersApplication.class, args);
    }

}
