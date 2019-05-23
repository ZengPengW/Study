package com.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.crm.dao.CustomerDao;
import com.crm.domain.Customer;
import com.crm.domain.PageBean;
import com.crm.service.CustomerService;
@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED)
public class CustomerServiceImpl implements CustomerService {
	private CustomerDao customerDao;
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
	//保存客户
	@Override
	public void save(Customer customer) {
		customerDao.save(customer);
	}

	//分页查询客户
	@Override
	public PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage,Integer pageSize) {
		PageBean<Customer> pageBean=new PageBean<Customer>();
		//封装当前页
		pageBean.setCurrPage(currPage);
		//每页显示记录数
		pageBean.setPageSize(pageSize);
		//封装总记录数
		Integer totalCount=customerDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		//总页数
	//	System.out.println(totalCount+" "+pageSize);
		pageBean.setTotalPage(totalCount%pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1);
		//每页显示数据集合
		Integer begin=(currPage-1)*pageSize;
		List<Customer> list=customerDao.findByPage(detachedCriteria,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}
	
	
}
