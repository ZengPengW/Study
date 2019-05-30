package com.crm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.crm.domain.LinkMan;

public interface LinkManDao {

 public	Integer findCount(DetachedCriteria detachedCriteria);

public List<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);

}
