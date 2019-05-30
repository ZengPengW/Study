package com.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.crm.dao.LinkManDao;
import com.crm.domain.LinkMan;
import com.crm.domain.PageBean;
import com.crm.service.LinkManService;
@Transactional
public class LinkManServiceImpl implements LinkManService {
	private LinkManDao linkManDao;
	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}
	@Override
	public PageBean<LinkMan> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<LinkMan> pageBean=new PageBean<LinkMan>();
		
		pageBean.setCurrPage(currPage);
		pageBean.setPageSize(pageSize);
		
		Integer totalCount=linkManDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		
		pageBean.setTotalPage(totalCount%pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1);
		
		//每页显示的数据集合
		Integer begin =(currPage-1)*pageSize;
		List<LinkMan>list=linkManDao.findByPage(detachedCriteria,begin,pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}
	
	
	
}
