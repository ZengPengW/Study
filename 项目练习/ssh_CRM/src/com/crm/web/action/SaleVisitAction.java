package com.crm.web.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

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

	
	//��ҳ
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
		//--------����---------------------------------
		if(visit_begin_time!=null) {
			detachedCriteria.add(Restrictions.ge("visit_time",visit_begin_time));	
		//	ActionContext.getContext().getValueStack().push(visit_begin_time);
		}
		if(visit_end_time!=null) {
			detachedCriteria.add(Restrictions.le("visit_time",visit_end_time));
		//	ActionContext.getContext().getValueStack().push(visit_end_time);

		}
		
		//------------------------------------------
		PageBean<SaleVisit> pageBean=saleVisitService.findByPage(detachedCriteria,currPage,pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		
		return "findAllSuccess";
	}
	
	//����ͻ��ݷü�¼
	public String saveUI() {	
		return "saveUI";
	}
	public String save() {	
		saleVisitService.save(saleVisit);
		return "saveSuccess";
	}
	

	//�޸�
		public String editUI() {
			saleVisit=saleVisitService.findById(saleVisit.getVisit_id());
			ActionContext.getContext().getValueStack().push(saleVisit);
			return "editUI";
		}
		public String edit() {
			saleVisitService.update(saleVisit);
			return "editSuccess";
		}
	//ɾ��
		public String delete() {
			saleVisit=saleVisitService.findById(saleVisit.getVisit_id());
			saleVisitService.delete(saleVisit);
			return "deleteSuccess";
		}
	//ɸѡ
		private Date visit_begin_time;
		private Date visit_end_time;
		public void setVisit_begin_time(Date visit_begin_time) {
			this.visit_begin_time = visit_begin_time;
		}
		public void setVisit_end_time(Date visit_end_time) {
			this.visit_end_time = visit_end_time;
		}
		public Date getVisit_begin_time() {
			return visit_begin_time;
		}
		public Date getVisit_end_time() {
			return visit_end_time;
		}
		
}












