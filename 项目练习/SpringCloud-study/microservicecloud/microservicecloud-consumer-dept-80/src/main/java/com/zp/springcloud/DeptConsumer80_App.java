package com.zp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

import com.zp.myrule.MySelfRule;

@SpringBootApplication
@EnableEurekaClient
//在启动该微服务时就可以加载我们自定义的Ribbon 配置类，从而使得配置生效
//参数一：针对哪一个微服务 ，参数二：指定自定义负载均衡配置类
@RibbonClient(name="MICROSERVICECLOUD-DEPT",configuration=MySelfRule.class)
public class DeptConsumer80_App {

	public static void main(String[] args) {
		SpringApplication.run(DeptConsumer80_App.class, args);
	}
}
