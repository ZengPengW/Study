package com.crm.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.crm.domain.Customer;
import com.crm.domain.PageBean;
import com.crm.service.CustomerService;
import com.crm.utils.UploadUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

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
	
	private Integer pageSize=3;
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		if(this.pageSize==null)this.pageSize=3;
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
			String path="D:/upload";
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
			customer.setCust_image(url+"/"+uuidFileName);
		}
		customerService.save(customer);
		return "findAllAction";
	}
	
	//查询客户
	public String findAll() {
		
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Customer.class);
		//添加条件
		if(customer.getCust_name()!=null&&!customer.getCust_name().trim().isEmpty()){
			detachedCriteria.add(Restrictions.like("cust_name", "%"+customer.getCust_name()+"%"));
		}
		if(customer.getBaseDictIndustry()!=null&&customer.getBaseDictIndustry().getDict_id()!=null&&!customer.getBaseDictIndustry().getDict_id().trim().isEmpty()){
			detachedCriteria.add(Restrictions.eq("baseDictIndustry.dict_id", customer.getBaseDictIndustry().getDict_id()));
		}
		if(customer.getBaseDictSource()!=null&&customer.getBaseDictSource().getDict_id()!=null&&!customer.getBaseDictSource().getDict_id().trim().isEmpty()){
			detachedCriteria.add(Restrictions.eq("baseDictSource.dict_id", customer.getBaseDictSource().getDict_id()));
		}
		
		if(customer.getBaseDictLevel()!=null&&customer.getBaseDictLevel().getDict_id()!=null&&!customer.getBaseDictLevel().getDict_id().isEmpty()){
			detachedCriteria.add(Restrictions.eq("baseDictLevel.dict_id", customer.getBaseDictLevel().getDict_id()));
		}
		//业务层查询
	 	PageBean<Customer> pageBeans= customerService.findByPage(detachedCriteria,currPage,pageSize);
	 	ActionContext.getContext().getValueStack().set("pageBeans",pageBeans);
		return "findAll";
	}
	
	//删除客户
	public String delete() {
		 customer=customerService.findById(customer.getCust_id());
		customerService.delete(customer);
		return "deleteSuccess";
		
	}
	
	//修改客户UI
	public String edit() {
		 customer=customerService.findById(customer.getCust_id());	
		return "editSuccess";
	}
	//修改客户
	public String update() throws IOException {
		if(upload!=null){
			//删除原有文件
			if(customer.getCust_image()!=null&&!customer.getCust_image().isEmpty()){
				File filedelete=new File(customer.getCust_image());
				if(filedelete.exists())filedelete.delete();
				
			}
			//设置文件上传路劲
			String path="D:/upload";
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
			customer.setCust_image(url+"/"+uuidFileName);
		}
		try {
			customerService.update(customer);
		} catch (Exception e) {
			return "updateSuccess";
		}
		return "updateSuccess";
	}
	
	//异步查询
	public String findAllCustomer() throws IOException {
		List<Customer>list=	customerService.findAll();
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[]{"linkMans","cust_phone","cust_mobile","cust_image","baseDictSource","baseDictIndustry","baseDictLevel"});
		
		JSONArray jsonArray=JSONArray.fromObject(list,jsonConfig);
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());;
		
		return NONE;
	}
}
