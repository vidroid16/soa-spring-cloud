package ru.shalya.soashopservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SoaShopServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoaShopServiceApplication.class, args);
    }

}
