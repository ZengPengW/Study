package com.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.crm.dao.SaleVisitDao;
import com.crm.domain.PageBean;
import com.crm.domain.SaleVisit;
import com.crm.service.SaleVisitService;
@Transactional
public class SaleVisitServiceImpl implements SaleVisitService {
	@Resource(name="SaleVisitDao")
	private SaleVisitDao saleVisitDao;

	@Override
	public PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<SaleVisit> pageBean =new PageBean<SaleVisit>();
		pageBean.setCurrPage(currPage);
		pageBean.setPageSize(pageSize);
		
		Integer totalCount=saleVisitDao.findCount(detachedCriteria);
		Integer totalPage=totalCount%pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1;
		List<SaleVisit> list=saleVisitDao.findByPage(detachedCriteria, (currPage-1)*pageSize, pageSize);
		
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		
		
		return pageBean;
	}
	
	
}
