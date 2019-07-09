package com.taotao.sso.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * 登陆接口
 * @author zp
 *
 */
public interface UserLoginService {

	/**
	 * 根据用户名和密码登录
	 * @param username 
	 * @param password
	 * @return taotaoResult 
	 * 登陆成功：返回200 和 一个token
	 * 登录失败：返回400
	 */
	public TaotaoResult login(String username,String password);
	
	/**
	 * 根据token获取用户信息
	 * @param token
	 * @return TaotaoResult
	 */
	public TaotaoResult getUserByToken(String token);
	
	/**
	 * 根据token删除用户信息（登出）
	 * @param token
	 * @return
	 */
	public TaotaoResult deleteUserByToken(String token);
}
