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
	//文件上传属性 upload 是表单名字
	private String uploadFileName;//文件名称
	private File upload;//上传文件
	private String uploadContentType;//文件类型
	
	
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
	
	//保存客户
	public String save() throws IOException {
		//文件上传
		if(upload!=null) {
			//设置文件上传路劲
			String path="C:/upload";
			//随机文件名字
			String uuidFileName=UploadUtils.getUuidFileName(uploadFileName);
			//分离后目录
			String realPath=UploadUtils.getPath(uuidFileName);
			//创建目录
			String url=path+realPath;
			File file=new File(url);
			if(!file.exists())file.mkdirs();
			
			//文件上传:
			File dictFile=new File(url+"/"+uuidFileName);
			FileUtils.copyFile(upload, dictFile);
			
		}
		customerService.save(customer);
		return "findAllAction";
	}
	
	//查询客户
	public String findAll() {
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Customer.class);
		//业务层查询
	 	PageBean<Customer> pageBeans= customerService.findByPage(detachedCriteria,currPage,pageSize);
	 	ActionContext.getContext().getValueStack().set("pageBeans",pageBeans);
		return "findAll";
	}
	
}
