package com.taotao.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.content.jedis.JedisClient;
import com.taotao.content.jedis.JedisClientCluster;
import com.taotao.content.service.ContentService;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.LimitPojo;
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

	@Autowired
	private JedisClient jedisClientCluster;
	
	@Value("${TBCONTENT_KEY}")
	private String CONTENT_KEY;
	
	
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public EasyUIDataGridResult<TbContent> getContentList(Long categoryId, Integer page, Integer rows) {
		if (page == null)
			page = 1;
		if (rows == null)
			rows = 30;
		// PageHelper.startPage(page, rows);
		// TbContentExample example = new TbContentExample();
		// if (categoryId != null && categoryId != 0) {
		// Criteria criteria = example.createCriteria();
		// criteria.andCategoryIdEqualTo(categoryId);
		// }
		page = (page - 1) * rows;
		// List<TbContent> list =
		// contentMapper.selectByExampleWithBLOBs(example);

		LimitPojo limitPojo = new LimitPojo();
		limitPojo.setBeginId(page);
		limitPojo.setRows(rows);
		if (categoryId != null && categoryId != 0)
			limitPojo.setCategoryId(categoryId);
		List<TbContent> list = contentMapper.selectByLimit(limitPojo);
		EasyUIDataGridResult<TbContent> dataGridResult = new EasyUIDataGridResult<TbContent>();
		// PageInfo<TbContent> pageInfo = new PageInfo<TbContent>(list);
		// dataGridResult.setTotal(pageInfo.getTotal());
		
		TbContentExample example = new TbContentExample();
		if (categoryId != null && categoryId != 0) {
			Criteria criteria = example.createCriteria();
			criteria.andCategoryIdEqualTo(categoryId);
		}
		Long count = (long) contentMapper.countByExample(example);
		dataGridResult.setRows(list);
		dataGridResult.setTotal(count);
		return dataGridResult;
	}

	@Override
	public TaotaoResult saveContent(TbContent tContent) {
		// 补全属性
		tContent.setCreated(new Date());
		tContent.setUpdated(tContent.getCreated());
		// 插入
		contentMapper.insertSelective(tContent);
		try {
			//同步缓存
			jedisClientCluster.hdel(CONTENT_KEY, tContent.getCategoryId().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TaotaoResult.ok();

	}

	@Override
	public TaotaoResult updateContent(TbContent tContent) {
		tContent.setUpdated(new Date());
		contentMapper.updateByPrimaryKeySelective(tContent);
		try {
			//同步缓存
			jedisClientCluster.hdel(CONTENT_KEY, tContent.getCategoryId().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TaotaoResult.ok(tContent);
	}

	@Override
	public TaotaoResult deleteContent(String ids) {
		String[] idss = ids.split(",");

		List<Long> id = new ArrayList<Long>(idss.length);
		for (int i = 0; i < idss.length; i++) {
			id.add(Long.valueOf(idss[i]));
		}
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(id);
		contentMapper.deleteByExample(example);
		
		try {
			//同步缓存
			TbContentExample example1 = new TbContentExample();
			Criteria criteria2 = example1.createCriteria();
			criteria2.andIdIn(id);
			List<TbContent> tbContents = contentMapper.selectByExample(example1);
			for (TbContent tbContent : tbContents) {
				jedisClientCluster.hdel(CONTENT_KEY, tbContent.getCategoryId().toString());
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return TaotaoResult.ok(null);
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override	
	public List<TbContent> getContentListByCatId(Long categoryId) {
		try {
			//判断redis中是否存在
			String jsonstr = jedisClientCluster.hget(CONTENT_KEY, categoryId.toString());
			if (StringUtils.isNoneBlank(jsonstr)) {
				return JsonUtils.jsonToList(jsonstr, TbContent.class);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//redis不存在将走数据库并且添加缓存
		TbContentExample example=new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> list=contentMapper.selectByExampleWithBLOBs(example);		
		try {
			//添加缓存到redis 中
			jedisClientCluster.hset(CONTENT_KEY, categoryId.toString(), JsonUtils.objectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
