package com.zp.springsecurity.mapper;

import java.util.List;


import com.zp.springsecurity.pojo.Permission;
import com.zp.springsecurity.pojo.User;

public interface UserMapper {

	/**
	 * 查询当前用户
	 * @return
	 */
	public User findByUserName(String username);
	
	/**
	 * 通过名字查询用户权限
	 * @param username
	 * @return
	 */
	public List<Permission> findPermissionByUsername(String username);

	/**
	 * 修改密码
	 * @param user
	 */
	public void updatePassword(User user) ;

}
