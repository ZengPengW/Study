package com.crm.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.crm.dao.LinkManDao;
import com.crm.domain.LinkMan;

public class LinkManDaoImpl extends HibernateDaoSupport implements LinkManDao {

	@Override
	public Integer findCount(DetachedCriteria detachedCriteria) {
		detachedCriteria.setProjection(Projections.rowCount());
		List<Long> list=(List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		return list.size()>0?list.get(0).intValue():0;
	}

	@Override
	public List<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
		detachedCriteria.setProjection(null);
		
		List<LinkMan> linkMans=(List<LinkMan>) this.getHibernateTemplate().findByCriteria(detachedCriteria,begin,pageSize);
		return linkMans;
	}

}
