package com.crm.dao;

import com.crm.domain.User;

/*
 * �û�����ӿ�
 */
public interface UserDao {

	public void save(User user);

	public User login(User user);

}
