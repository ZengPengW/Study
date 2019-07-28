package com.zp.oauth2.resource.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zp.oauth2.resource.mapper.TbContentMapper;
import com.zp.oauth2.resource.pojo.TbContent;
import com.zp.oauth2.resource.service.TbContentService;

@Service
public class TbContentServiceImpl implements TbContentService {

	@Autowired
	private TbContentMapper tbContentMapper;

	public TbContent getById(Long id) {
		return tbContentMapper.selectByPrimaryKey(id);
	}

	public List<TbContent> selectAll() {
		return tbContentMapper.selectAll();
	}

	public int insert(TbContent tbContent) {
		return tbContentMapper.insert(tbContent);
	}

	
	public int update(TbContent tbContent) {
		return tbContentMapper.updateByPrimaryKey(tbContent);
	}

	
	public int delete(Long id) {
		return tbContentMapper.deleteByPrimaryKey(id);
	}

}
