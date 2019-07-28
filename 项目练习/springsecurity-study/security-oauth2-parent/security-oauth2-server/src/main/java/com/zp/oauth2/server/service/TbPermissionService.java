package com.zp.oauth2.server.service;

import java.util.List;

import com.zp.oauth2.server.pojo.TbPermission;

public interface TbPermissionService {
	
	List<TbPermission> selectByUserId(Long userId);
	
}
