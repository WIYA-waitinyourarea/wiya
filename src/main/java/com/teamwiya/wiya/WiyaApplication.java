package com.teamwiya.wiya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD
import org.springframework.context.annotation.ComponentScan;
=======
>>>>>>> chae4
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;


//@ComponentScan(basePackages = {"com.teamwiya.wiya.controller"})
@EnableScheduling
@EnableJpaAuditing
@SpringBootApplication
@EnableScheduling //시간변동
@EnableJpaAuditing //데이터로 적용해주는 것
public class WiyaApplication {
	public static void main(String[] args) {
		SpringApplication.run(WiyaApplication.class, args);
	}

}
