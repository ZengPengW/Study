package com.crm.service;

import org.hibernate.criterion.DetachedCriteria;

import com.crm.domain.LinkMan;
import com.crm.domain.PageBean;

public interface LinkManService {

	public PageBean<LinkMan> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	public void save(LinkMan linkMan);

	public LinkMan findById(Long lkm_id);

	public void update(LinkMan linkMan);

	public void delete(LinkMan linkMan);

}
