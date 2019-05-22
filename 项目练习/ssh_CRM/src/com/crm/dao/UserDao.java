package com.crm.dao;

import com.crm.domain.User;

/*
 * 用户管理接口
 */
public interface UserDao {

	public void save(User user);

	public User login(User user);

}
