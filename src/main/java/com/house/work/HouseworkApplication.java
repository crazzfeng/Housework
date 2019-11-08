package com.house.work;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.**.dao")
public class HouseworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(HouseworkApplication.class, args);
	}

}
