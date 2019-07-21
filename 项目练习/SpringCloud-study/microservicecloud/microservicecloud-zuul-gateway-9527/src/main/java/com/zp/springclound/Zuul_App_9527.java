package com.zp.springclound;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Zuul
 * 
 * @author zp
 *
 */
@SpringBootApplication
@EnableZuulProxy //zuul注解 
public class Zuul_App_9527 {
	public static void main(String[] args) {
		SpringApplication.run(Zuul_App_9527.class, args);
	}
}
