package com.crm.web.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.hibernate.criterion.DetachedCriteria;

import com.crm.domain.Customer;
import com.crm.domain.PageBean;
import com.crm.service.CustomerService;
import com.crm.utils.UploadUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
	//�ļ��ϴ����� upload �Ǳ�����
	private String uploadFileName;//�ļ�����
	private File upload;//�ϴ��ļ�
	private String uploadContentType;//�ļ�����
	
	
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	private Integer currPage=1;
	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
		if(this.currPage==null)this.currPage=1;
	}
	
	private Integer pageSize=5;
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		if(this.pageSize==null)this.pageSize=5;
	}
	
	
	private Customer customer=new Customer();
	@Override
	public Customer getModel() {
		return customer;
	}
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	public String saveUI() {
		return "saveUI";
	}
	
	//����ͻ�
	public String save() throws IOException {
		//�ļ��ϴ�
		if(upload!=null) {
			//�����ļ��ϴ�·��
			String path="C:/upload";
			//����ļ�����
			String uuidFileName=UploadUtils.getUuidFileName(uploadFileName);
			//�����Ŀ¼
			String realPath=UploadUtils.getPath(uuidFileName);
			//����Ŀ¼
			String url=path+realPath;
			File file=new File(url);
			if(!file.exists())file.mkdirs();
			
			//�ļ��ϴ�:
			File dictFile=new File(url+"/"+uuidFileName);
			FileUtils.copyFile(upload, dictFile);
			
		}
		customerService.save(customer);
		return "findAllAction";
	}
	
	//��ѯ�ͻ�
	public String findAll() {
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Customer.class);
		//ҵ����ѯ
	 	PageBean<Customer> pageBeans= customerService.findByPage(detachedCriteria,currPage,pageSize);
	 	ActionContext.getContext().getValueStack().set("pageBeans",pageBeans);
		return "findAll";
	}
	
}
