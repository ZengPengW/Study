package com.crm.web.interceptor;

import org.apache.struts2.ServletActionContext;

import com.crm.domain.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class PrivilegeInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		//�ж�session�Ƿ��е�½��Ϣ
		User user=(User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		
		if(user!=null) {
		    return invocation.invoke();
		}else {
			ActionSupport actionSupport=(ActionSupport) invocation.getAction();
			actionSupport.addActionError("�û���û��½,û�÷���Ȩ��!!!");
			return "login";
		}
		
		
	}

}
