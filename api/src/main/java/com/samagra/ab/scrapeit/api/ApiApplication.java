package com.samagra.ab.scrapeit.api;

import com.samagra.ab.scrapeit.common.CommonLibrary;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @GetMapping("/")
    public String home() {
        return new CommonLibrary().getHelloWorld();
    }
}