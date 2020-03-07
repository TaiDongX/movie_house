package com.ws;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.ws.mapper")
public class MovieHouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieHouseApplication.class, args);
    }

}
