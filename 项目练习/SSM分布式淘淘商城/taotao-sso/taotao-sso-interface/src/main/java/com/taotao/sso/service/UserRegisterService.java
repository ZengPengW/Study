package com.taotao.sso.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

/**
 * 用户注册的接口
 * @author zp
 *
 */
public interface UserRegisterService {
	/**
	 * 根据参数和类型来校验数据
	 * @param param 参数 
	 * @param type  类型 1 username ,2 phone ,3 email
	 * @return
	 */
	public TaotaoResult checkData(String param,Integer type);
	
	/**
	 * 注册用户
	 * @param user
	 * @return
	 */
	public TaotaoResult register(TbUser user);

	
}
