package com.lhChen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@MapperScan("com.lhChen.mapper")
@SpringBootApplication
public class SkyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkyApplication.class,args);
    }
}
