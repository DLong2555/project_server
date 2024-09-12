package com.finalProject.gym;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@ComponentScan(basePackages = { "com.finalProject.gym" })
@MapperScan(basePackages = { "com.finalProject.gym" })

@PropertySources({ 
	@PropertySource(value = { "file:c:/springWorkspace/configure.properties",
		"file:/usr/local/project/properties/configure.properties", }, ignoreResourceNotFound = true) })
public class SpringBootFinalProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFinalProjectApplication.class, args);
	}

}
