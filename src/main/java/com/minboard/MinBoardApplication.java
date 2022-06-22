package com.minboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan(basePackages = {"com.handler"})
@SpringBootApplication
public class MinBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinBoardApplication.class, args);
    }

}
