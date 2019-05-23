package com.crm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.crm.domain.Customer;

public interface CustomerDao {

	public void save(Customer customer);

	public Integer findCount(DetachedCriteria detachedCriteria);

	public List<Customer> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);

}
