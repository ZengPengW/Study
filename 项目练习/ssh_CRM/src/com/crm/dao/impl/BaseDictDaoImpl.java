package com.crm.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.crm.dao.BaseDictDao;
import com.crm.domain.BaseDict;

public class BaseDictDaoImpl extends BaseDaoImpl<BaseDict> implements BaseDictDao {



	@Override
	public List<BaseDict> findByTypeCode(String dict_type_code) {
		String hql="from BaseDict where dict_type_code=?";		
		return (List<BaseDict>) this.getHibernateTemplate().find(hql, dict_type_code);
	}

}
