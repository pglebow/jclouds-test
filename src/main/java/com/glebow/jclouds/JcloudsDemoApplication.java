package com.glebow.jclouds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.glebow.jclouds")
public class JcloudsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JcloudsDemoApplication.class, args);
	}
}
