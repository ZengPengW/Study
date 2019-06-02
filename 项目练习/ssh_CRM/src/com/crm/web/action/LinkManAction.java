package com.crm.web.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.crm.domain.Customer;
import com.crm.domain.LinkMan;
import com.crm.domain.PageBean;
import com.crm.service.CustomerService;
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
	
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	//��ѯ��ϵ��
	/*
	 * ��ҳ����
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
		//��������������ѯ��
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(LinkMan.class);
		//��������
		if(linkMan.getLkm_name()!=null&&!linkMan.getLkm_name().trim().isEmpty()){
			detachedCriteria.add(Restrictions.like("lkm_name", "%"+linkMan.getLkm_name()+"%"));
		}
		if(linkMan.getLkm_gender()!=null&&!linkMan.getLkm_gender().trim().isEmpty()){
			detachedCriteria.add(Restrictions.eq("lkm_gender", linkMan.getLkm_gender()));
		}
		//��ѯ
		PageBean<LinkMan> pageBean=linkManService.findAll(detachedCriteria,currPage,pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}

	
	//������ϵ��
	public String saveUI() {
		//��ѯ���пͻ�
		List<Customer> list=customerService.findAll();	
		ActionContext.getContext().getValueStack().set("list", list);
		return "saveUI";
	}
	public String save() {
		//����ҵ���
		try {
			linkManService.save(linkMan);
		} catch (Exception e) {
			System.out.println("zhuadaole");
		}
		
		return "saveSuccess";
		
	}
	
	//�޸�
	public String edit() {
		linkMan=linkManService.findById(linkMan.getLkm_id());
		List<Customer> list=customerService.findAll();
		ActionContext.getContext().getValueStack().push(linkMan);
		ActionContext.getContext().getValueStack().set("list", list);
		return "editSuccess";
	}
	public String update() {
		linkManService.update(linkMan);
		return "updateSuccess";
	}
	
	//�h��
	public String delete() {
		linkMan=linkManService.findById(linkMan.getLkm_id());
		linkManService.delete(linkMan);
		
		return "deleteSuccess";
	}
}















