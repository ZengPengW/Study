package com.crm.dao;

import com.crm.domain.User;

/*
 * 用户管理接口
 */
public interface UserDao extends BaseDao<User> {

	

	public User login(User user);

}
