package com.zp.springsecurity.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zp.springsecurity.mapper.UserMapper;
import com.zp.springsecurity.pojo.Permission;
import com.zp.springsecurity.pojo.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-*.xml")
public class TestUserMapper {

	@Autowired
	private UserMapper mapper;
	
	@Test
	public void test(){
		User user = mapper.findByUserName("eric");
		System.out.println(user);
		List<Permission> permissions = mapper.findPermissionByUsername("eric");
		for (Permission permission : permissions) {
			System.out.println(permission);
		}
		
		
		
	}
	
	
	@Test
	public void test2(){
		User user = mapper.findByUserName("jack");
		
		PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
		//加密密码
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		mapper.updatePassword(user);
		
		
	}
}
