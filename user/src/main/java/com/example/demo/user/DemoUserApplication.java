package com.example.demo.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.demo.user.dao")
@SpringBootApplication
public class DemoUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoUserApplication.class, args);
	}

}
