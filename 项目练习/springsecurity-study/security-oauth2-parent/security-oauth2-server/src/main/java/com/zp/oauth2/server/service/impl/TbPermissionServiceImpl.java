package com.zp.oauth2.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zp.oauth2.server.mapper.TbPermissionMapper;
import com.zp.oauth2.server.pojo.TbPermission;
import com.zp.oauth2.server.service.TbPermissionService;

@Service
public class TbPermissionServiceImpl implements TbPermissionService {

	@Autowired
	private TbPermissionMapper tbPermissionMapper;
	
	public List<TbPermission> selectByUserId(Long userId) {
		
		return tbPermissionMapper.selectByUserId(userId);
	}

}
