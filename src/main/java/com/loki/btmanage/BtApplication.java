package com.loki.btmanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.loki.btmanage.mapper")
@EnableScheduling
public class BtApplication {

    public static void main(String[] args) {
        SpringApplication.run(BtApplication.class, args);
    }

}
