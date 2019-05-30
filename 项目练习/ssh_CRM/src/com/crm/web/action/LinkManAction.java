package com.crm.web.action;

import org.hibernate.criterion.DetachedCriteria;

import com.crm.domain.LinkMan;
import com.crm.domain.PageBean;
import com.crm.service.LinkManService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {

	private LinkMan linkMan=new LinkMan();
	@Override
	public LinkMan getModel() {		
		return linkMan;
	}
	
	private LinkManService linkManService;
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	
	//查询联系人
	/*
	 * 分页参数
	 */
	private Integer currPage=1;
	private Integer pageSize=3;
	
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
		if(this.currPage==null)this.currPage=1;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		if(this.pageSize==null)this.pageSize=1;
	}

	public String findAll() {
		//创建离线条件查询：
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(LinkMan.class);
		//设置条件
		
		//查询
		PageBean<LinkMan> pageBean=linkManService.findAll(detachedCriteria,currPage,pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}

}
