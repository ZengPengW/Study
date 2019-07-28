package com.zp.oauth2.resource.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.zp.oauth2.resource.mapper.TbContentCategoryMapper;
import com.zp.oauth2.resource.service.TbContentCategoryService;

public class TbContentCategoryServiceImpl implements TbContentCategoryService {
	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;

}
