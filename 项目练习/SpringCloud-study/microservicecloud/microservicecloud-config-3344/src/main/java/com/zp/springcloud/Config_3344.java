package com.zp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer //配置配置微服务
public class Config_3344 {

	public static void main(String[] args) {
		SpringApplication.run(Config_3344.class, args);
	}
}
