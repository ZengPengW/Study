package com.zp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages={"com.zp.springcloud"})
@ComponentScan("com.zp.springcloud")
public class DeptConsumer_Fegin {

	public static void main(String[] args) {
		SpringApplication.run(DeptConsumer_Fegin.class, args);
	}
}
