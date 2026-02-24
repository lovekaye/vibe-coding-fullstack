package com.example.vibeapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.example.vibeapp")
@RestController
@RequestMapping("/api")
public class VibeApp {
    public static void main(String[] args) {
        SpringApplication.run(VibeApp.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Vibe!";
    }
}
