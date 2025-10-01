package com.aithronix.aithronix_cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.aithronix.aithronix_cart.client")
public class AithronixCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(AithronixCartApplication.class, args);
	}

}
