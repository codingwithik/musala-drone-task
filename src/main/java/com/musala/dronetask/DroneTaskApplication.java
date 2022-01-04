package com.musala.dronetask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DroneTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(DroneTaskApplication.class, args);
	}

}
