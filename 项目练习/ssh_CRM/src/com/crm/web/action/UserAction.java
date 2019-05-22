package com.crm.web.action;

import org.apache.struts2.ServletActionContext;

import com.crm.domain.User;
import com.crm.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/*
 * �û�������
 */
public class UserAction extends ActionSupport implements ModelDriven<User> {

	private User user=new User();
	@Override
	public User getModel() {		
		return user;
	}
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public String regist(){
		userService.regist(user);	
		return LOGIN;
	}
	
	public String login() {
		User isExistUser=userService.login(user);
		if(isExistUser!=null) {
			//�ɹ�
			ActionContext.getContext().getSession().put("existUser", isExistUser);
			return SUCCESS;
		}else {
			//ʧ��
			this.addActionError("�û������������");
			return LOGIN;
		}
		
	}
}
