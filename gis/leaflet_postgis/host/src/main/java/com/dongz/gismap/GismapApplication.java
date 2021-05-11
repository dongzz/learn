package com.dongz.gismap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.dongz.gismap.mapper")
public class GismapApplication {

    public static void main(String[] args) {
        SpringApplication.run(GismapApplication.class, args);
    }

}
