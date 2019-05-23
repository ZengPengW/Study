package com.crm.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.crm.dao.CustomerDao;
import com.crm.domain.Customer;

public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {

	@Override
	public void save(Customer customer) {
		this.getHibernateTemplate().save(customer);
	}

	//条件统计个数
	@Override
	public Integer findCount(DetachedCriteria detachedCriteria) {
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> integers= (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		return integers.size()>0?integers.get(0).intValue():0;
	}

	//分页查询客户
	@Override
	public List<Customer> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
		detachedCriteria.setProjection(null);
		return 	(List<Customer>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, pageSize);
		
	}

}
