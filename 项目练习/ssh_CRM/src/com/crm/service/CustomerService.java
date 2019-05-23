package com.crm.service;

import org.hibernate.criterion.DetachedCriteria;

import com.crm.domain.Customer;
import com.crm.domain.PageBean;

public interface CustomerService {

	public void save(Customer customer);

	public PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage,Integer pageSize);

}
