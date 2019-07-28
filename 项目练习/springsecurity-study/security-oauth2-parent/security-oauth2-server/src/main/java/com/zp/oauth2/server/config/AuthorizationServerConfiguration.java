package com.zp.oauth2.server.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@EnableAuthorizationServer // 开启认证服务
@Configuration
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	// @Bean
	// @Primary //主配置 覆盖默认配置
	// @ConfigurationProperties(prefix="spring.datasource") //配的是谁
	// public DataSource dataSource(){
	// return DataSourceBuilder.create().build();
	// }

	@Autowired
	private DataSource dataSource;

	@Bean
	public TokenStore tokenStore() {
		return new JdbcTokenStore(dataSource);
	}

	@Bean
	public ClientDetailsService jdbcClientDeatails() {
		return new JdbcClientDetailsService(dataSource);
	}

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	// 配置客户端及其授权方式
	// 访问 http://ip:prot/oauth/authorize?client_id=client&response_type=code
	// 获得授权码code
	// http://localhost:8080/oauth/authorize?client_id=client&response_type=code
	// http://client:secret@localhost:8080/oauth/token 通过code 获得令牌
	// post请求code=2vKd1h&grant_type=authorization_code
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		/* *************内存模式************** */
		/*
		 * clients.inMemory()//把客户端信息放在内存中 .withClient("client") //客户端id
		 * .secret(passwordEncoder.encode("secret")) //客户端安全码
		 * .authorizedGrantTypes("authorization_code")//授权码模式 .scopes("app")
		 * //授权范围 .redirectUris("http://localhost:8080/index");
		 */ // 回调地址 授权成功后的重定向地址

		/* ***********jdbc模式*************** */
		System.out.println("jdbc模式");
		clients.withClientDetails(jdbcClientDeatails());// 客户端走jdbc

	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore());
		
		//设置不过期
//		DefaultTokenServices defaultTokenServices = (DefaultTokenServices) endpoints.getTokenServices();
//		defaultTokenServices.setAccessTokenValiditySeconds(-1);
//		defaultTokenServices.setRefreshTokenValiditySeconds(-1);
//		defaultTokenServices.setSupportRefreshToken(true);
//		defaultTokenServices.setReuseRefreshToken(false);
//		defaultTokenServices.setTokenStore(tokenStore());
//		 endpoints.tokenServices(defaultTokenServices);
	}
}
