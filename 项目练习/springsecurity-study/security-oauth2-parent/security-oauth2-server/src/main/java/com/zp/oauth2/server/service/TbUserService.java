package com.zp.oauth2.server.service;

import com.zp.oauth2.server.pojo.TbUser;

public interface TbUserService {
	
	TbUser getByUserName(String username);
}
