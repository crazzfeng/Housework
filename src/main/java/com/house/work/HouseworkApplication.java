package com.house.work;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component//加上注释@Component，可以直接在其他地方使用@Autowired来创建HouseworkApplication实列对象
@SpringBootApplication
@MapperScan(basePackages = "com.**.dao")
@ComponentScan(basePackages = {"com.house.work.**.dao","com.house.work.**.service","com.house.work.**.controller"})
@ComponentScan(basePackages = {"com.config.redis"})
@ServletComponentScan(basePackages = {"com.house.work.filter"})
public class HouseworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(HouseworkApplication.class, args);
	}

}
