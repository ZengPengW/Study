package com.taotao.test.pagehelp;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;

public class TestPageHelp {

	@Test
	public void testhelper() {
		
		//初始化spring容器
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		//获取mapper的代理对象
		TbItemMapper tbItemMapper = applicationContext.getBean(TbItemMapper.class);
		//设置分页信息
		PageHelper.startPage(1, 3);
		//调用mapper的方法查询数据
		TbItemExample example=new TbItemExample();
		List<TbItem> selectByExample = tbItemMapper.selectByExample(example);
		//取分页信息
		PageInfo<TbItem> pageInfo=new PageInfo<>(selectByExample);
		//遍历打印结果集
		System.out.println("list.size="+selectByExample.size());
		System.out.println("查询总记录数:"+pageInfo.getTotal());
		for (TbItem tbItem : pageInfo.getList()) {
			System.out.println(tbItem.getTitle());
			
		}
	}
}
