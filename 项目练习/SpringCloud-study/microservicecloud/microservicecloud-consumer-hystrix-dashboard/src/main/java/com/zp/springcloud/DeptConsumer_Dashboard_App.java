package com.zp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * 开启图形化服务监控
 * @author zp
 * localhost:9001/hystrix
 */
@SpringBootApplication
@EnableHystrixDashboard //开启图形化监控注解
public class DeptConsumer_Dashboard_App {

	public static void main(String[] args) {
		SpringApplication.run(DeptConsumer_Dashboard_App.class, args);
	}
}
