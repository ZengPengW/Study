package com.crm.service;

import java.util.List;

import com.crm.domain.User;

/*
 * �û�����ҵ���ӿ�
 */
public interface UserService {

	public void regist(User user);

	public User login(User user);

	public List<User> findAll();

}
