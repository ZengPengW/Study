package com.taotao.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentService;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;

/**
 * 内容处理接口
 * 
 * @author zp
 *
 */
@Service
@Transactional
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public EasyUIDataGridResult<TbContent> getContentList(Long categoryId, Integer page, Integer rows) {
		if (page == null)
			page = 1;
		if (rows == null)
			rows = 30;
		PageHelper.startPage(page, rows);
		TbContentExample example = new TbContentExample();
		if (categoryId != null && categoryId != 0) {
			Criteria criteria = example.createCriteria();
			criteria.andCategoryIdEqualTo(categoryId);
		}
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);

		EasyUIDataGridResult<TbContent> dataGridResult = new EasyUIDataGridResult<TbContent>();
		PageInfo<TbContent> pageInfo = new PageInfo<TbContent>(list);
		dataGridResult.setTotal(pageInfo.getTotal());
		dataGridResult.setRows(list);

		return dataGridResult;
	}

	@Override
	public TaotaoResult saveContent(TbContent tContent) {
		// 补全属性
		tContent.setCreated(new Date());
		tContent.setUpdated(tContent.getCreated());
		// 插入
		contentMapper.insertSelective(tContent);
		return TaotaoResult.ok();

	}

	@Override
	public TaotaoResult updateContent(TbContent tContent) {
		tContent.setUpdated(new Date());
		contentMapper.updateByPrimaryKeyWithBLOBs(tContent);
		return TaotaoResult.ok(tContent);
	}

	@Override
	public TaotaoResult deleteContent(String ids) {
		String[] idss = ids.split(",");

		List<Long> id = new ArrayList<Long>(idss.length);
		for (int i = 0; i < idss.length; i++) {
			id.add(Long.valueOf(idss[i]));
		}
		TbContentExample example =new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(id);
		contentMapper.deleteByExample(example);
		return TaotaoResult.ok(null);
	}

}
