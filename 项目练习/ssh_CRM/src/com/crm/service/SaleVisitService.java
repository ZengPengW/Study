package com.crm.service;

import org.hibernate.criterion.DetachedCriteria;

import com.crm.domain.PageBean;
import com.crm.domain.SaleVisit;

public interface SaleVisitService {

 public	PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

public void save(SaleVisit saleVisit);

public SaleVisit findById(String visit_id);

public void update(SaleVisit saleVisit);

public void delete(SaleVisit saleVisit);

}
