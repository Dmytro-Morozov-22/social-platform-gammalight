package com.ua.vinn.GammaLight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@ComponentScan(basePackages="com.ua.vinn.GammaLight")
//@EnableAutoConfiguration
//@EnableJpaRepositories("com.ua.vinn.GammaLight.repository")
//@EntityScan( basePackages = {"com.ua.vinn.GammaLight.models"} ) // entities package name
public class GammaLightApplication {
	public static void main(String[] args) {

		SpringApplication.run(GammaLightApplication.class, args);






	}






}




