package com.zp.springsecurity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zp.springsecurity.mapper.UserMapper;
import com.zp.springsecurity.pojo.Permission;
import com.zp.springsecurity.pojo.User;
import com.zp.springsecurity.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	
	@Override
	public User findByUserName(String username) {
		return userMapper.findByUserName(username);
	}

	@Override
	public List<Permission> findPermissionByUsername(String username) {
		return userMapper.findPermissionByUsername(username);
	}

	@Override
	public void updatePassword(User user) {
		String password=user.getPassword();
		//哈希算法+加变量 加密密码
		PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(password));
		
		userMapper.updatePassword(user);
	}

}
