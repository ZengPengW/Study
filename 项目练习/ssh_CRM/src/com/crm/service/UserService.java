package com.crm.service;

import java.util.List;

import com.crm.domain.User;

/*
 * 用户管理业务层接口
 */
public interface UserService {

	public void regist(User user);

	public User login(User user);

	public List<User> findAll();

}
