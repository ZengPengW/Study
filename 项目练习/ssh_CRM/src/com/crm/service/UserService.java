package com.crm.service;

import com.crm.domain.User;

/*
 * �û�����ҵ���ӿ�
 */
public interface UserService {

	public void regist(User user);

	public User login(User user);

}
