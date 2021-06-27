package com.samagra.ab.scrapeit.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.samagra.ab.scrapeit")
public class AsyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsyncApplication.class, args);
	}

}
