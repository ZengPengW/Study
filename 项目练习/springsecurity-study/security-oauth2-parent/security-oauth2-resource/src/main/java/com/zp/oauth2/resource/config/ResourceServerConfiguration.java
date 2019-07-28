package com.zp.oauth2.resource.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer //资源服务器
@EnableGlobalMethodSecurity(prePostEnabled=true,securedEnabled=true,jsr250Enabled=true) //全局方法拦截器
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/list").hasAnyAuthority("SystemContent")
		.antMatchers("/view/**").hasAnyAuthority("SystemContentView");
		
	}
	
}
