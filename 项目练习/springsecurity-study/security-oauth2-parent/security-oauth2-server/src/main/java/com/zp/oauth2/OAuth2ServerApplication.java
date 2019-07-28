package com.zp.oauth2;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages="com.zp.oauth2.server.mapper")
public class OAuth2ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OAuth2ServerApplication.class, args);
	}
}
