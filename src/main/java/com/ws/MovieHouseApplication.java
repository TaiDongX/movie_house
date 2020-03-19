package com.ws;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EntityScan("com.ws.bean")
@MapperScan("com.ws.mapper")
@ServletComponentScan(basePackages = "com.ws.*")
public class MovieHouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieHouseApplication.class, args);

        System.setProperty("tomcat.util.http.parser.HttpParser.requestTargetAllow","|{}");
    }

}
