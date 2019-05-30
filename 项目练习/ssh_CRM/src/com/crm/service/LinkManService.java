package com.crm.service;

import org.hibernate.criterion.DetachedCriteria;

import com.crm.domain.LinkMan;
import com.crm.domain.PageBean;

public interface LinkManService {

	public PageBean<LinkMan> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

}
