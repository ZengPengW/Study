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
	
	//����ͻ�
	@Override
	public void save(Customer customer) {
		customerDao.save(customer);
	}

	//��ҳ��ѯ�ͻ�
	@Override
	public PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage,Integer pageSize) {
		PageBean<Customer> pageBean=new PageBean<Customer>();
		//��װ��ǰҳ
		pageBean.setCurrPage(currPage);
		//ÿҳ��ʾ��¼��
		pageBean.setPageSize(pageSize);
		//��װ�ܼ�¼��
		Integer totalCount=customerDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		//��ҳ��
	//	System.out.println(totalCount+" "+pageSize);
		pageBean.setTotalPage(totalCount%pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1);
		//ÿҳ��ʾ���ݼ���
		Integer begin=(currPage-1)*pageSize;
		List<Customer> list=customerDao.findByPage(detachedCriteria,begin,pageSize);
		pageBean.setList(list);
		return pageBean;
	}
	
	
}
