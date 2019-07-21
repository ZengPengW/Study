package com.zp.springcloud.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Config_ClientRest_3355 {

	@Value("${spring.application.name}")
	private String applicationName;
	
	@Value("${eureka.client.server-url.defaultZone}")
	private String eurekaServers;
	
	@Value("${server.port}")
	private String port;

	@RequestMapping("/config")
	public String getConfig() {

		System.out.println("applicationName:" + this.applicationName + "eurekaServers:" + this.eurekaServers + "port:"
				+ this.port);
		return "applicationName:" + this.applicationName + "eurekaServers:" + this.eurekaServers + "port:" + this.port;
	}

}
