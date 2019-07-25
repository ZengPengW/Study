package com.zp.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zp.springsecurity.utils.CheckImgServlet;

@SpringBootApplication
@Configuration
public class SpringSecurityApp {

	@Bean
	public CheckImgServlet getCheckImg(){
		CheckImgServlet checkImgServlet=new CheckImgServlet();
		return checkImgServlet;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApp.class, args);
	}
}
