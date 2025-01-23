package com.robo.alice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class RoboAliceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoboAliceApplication.class, args);
	}

}
