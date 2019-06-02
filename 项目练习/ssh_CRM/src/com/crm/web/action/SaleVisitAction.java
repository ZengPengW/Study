package com.crm.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;

import com.crm.domain.PageBean;
import com.crm.domain.SaleVisit;
import com.crm.service.SaleVisitService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {

	private SaleVisit saleVisit=new SaleVisit();
	@Override
	public SaleVisit getModel() {
		return saleVisit;
	}
	@Resource(name="SaleVisitService")
	private SaleVisitService saleVisitService;

	
	//分页
	private Integer currPage=1;
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
		if(this.currPage==null)this.currPage=1;
	}
	
	private Integer pageSize=3;
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		if(this.pageSize==null)this.pageSize=3;
	}
	public String findAll() {
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(SaleVisit.class);
		//--------条件---------------------------------
		
		
		//------------------------------------------
		PageBean<SaleVisit> pageBean=saleVisitService.findByPage(detachedCriteria,currPage,pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAllSuccess";
	}
	
	//保存客户拜访记录
	public String saveUI() {	
		return "saveUI";
	}
	public String save() {	
		
		return "saveSuccess";
	}
}












