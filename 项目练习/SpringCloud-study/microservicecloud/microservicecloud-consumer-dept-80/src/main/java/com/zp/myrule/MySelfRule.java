package com.zp.myrule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;

/**
 * 自定义Ribbon 负载均衡配置类
 * @author zp
 *
 */
@Configuration
public class MySelfRule {

	@Bean
	public IRule myRule(){
		return new RandomRule_ZY();
	}
	
}
