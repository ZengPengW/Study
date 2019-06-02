package com.crm.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.dao.UserDao;
import com.crm.domain.User;
import com.crm.service.UserService;
import com.crm.utils.MD5Utils;
/*
 * 用户管理业务层接口实现类
 */
@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED)
public class UserServiceImpl implements UserService {
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	@Override
	public void regist(User user) {
		//加密密码
		String passWordMd5=MD5Utils.md5(user.getUser_password());
		user.setUser_password(passWordMd5);
		user.setUser_state('1');
		//调用dao
		userDao.save(user);
		
	}
	@Override
	public User login(User user) {
		String passWordMd5=MD5Utils.md5(user.getUser_password());
		user.setUser_password(passWordMd5);
		
		return userDao.login(user);
	}
	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}
}
