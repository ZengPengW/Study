package com.crm.dao;

import com.crm.domain.User;

/*
 * �û�����ӿ�
 */
public interface UserDao extends BaseDao<User> {

	

	public User login(User user);

}
