package com.zp.oauth2.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zp.oauth2.server.mapper.TbUserMapper;
import com.zp.oauth2.server.pojo.TbUser;
import com.zp.oauth2.server.service.TbUserService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class TbUserServiceImpl implements TbUserService{
	
	
	
	@Autowired
	private TbUserMapper tbUserMapper;
	
	
	public TbUser getByUserName(String username) {
		Example example=new Example(TbUser.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("username", username);
		
		
		return tbUserMapper.selectOneByExample(example);
	}
	
}
