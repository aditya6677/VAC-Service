package com.app.vac_center_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix
public class VacCenterServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VacCenterServiceApplication.class, args);
	}

}
