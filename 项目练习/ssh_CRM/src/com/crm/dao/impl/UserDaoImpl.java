package com.crm.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.crm.dao.UserDao;
/*
 * UserDao ʵ����
 */
import com.crm.domain.User;
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	//�����û�
	@Override
	public void save(User user) {
		this.getHibernateTemplate().save(user);		
	}

	//�����û���&�����ѯ
	@Override
	public User login(User user) {
		String hql="from User where user_code=? and user_password=?";
		List<User> users=(List<User>) this.getHibernateTemplate().find(hql, user.getUser_code(),user.getUser_password());
		return users.size()>0?users.get(0):null;
	}

	
}
