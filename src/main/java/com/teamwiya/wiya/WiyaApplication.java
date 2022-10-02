package com.teamwiya.wiya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
//@ComponentScan(basePackages = {"com.teamwiya.wiya.controller"})
@SpringBootApplication
public class WiyaApplication {
	public static void main(String[] args) {
		SpringApplication.run(WiyaApplication.class, args);
	}

}
