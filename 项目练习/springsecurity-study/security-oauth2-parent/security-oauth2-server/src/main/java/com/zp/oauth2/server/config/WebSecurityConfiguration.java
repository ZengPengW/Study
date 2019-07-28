package com.zp.oauth2.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity //启动Security过滤器链
@EnableGlobalMethodSecurity(prePostEnabled=true,securedEnabled=true,jsr250Enabled=true) //开启全局方法拦截器
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl UserDetailsServiceImpl;

	//默认用BCrypt加密
	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoderFactories.createDelegatingPasswordEncoder();//创建密码加密 自动判断你的密码加密类型
		/* ***********走内存 ******************/
//		auth.inMemoryAuthentication()
//		.withUser("admin").password(passwordEncoder().encode("123456")).roles("admin")//用户1
//		.and().withUser("user").password(passwordEncoder().encode("123456")).roles("user");//用户1
	
		/* ********走数据库********* */
		
		auth.userDetailsService(UserDetailsServiceImpl);
		
		
		
	
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/oauth/check_token");//忽略地址
	}
}
