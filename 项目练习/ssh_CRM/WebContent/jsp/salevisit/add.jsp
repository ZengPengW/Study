<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>添加客户拜访记录</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>


<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
<s:form id="form1" name="form1" namespace="/" theme="simple"
		action="saleVisit_save.action"
		method="post" >
	
		

		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
						border=0></TD>
					<TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg"
						 height=20></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15 background=${pageContext.request.contextPath }/images/new_022.jpg><IMG
						src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：客户拜访记录管理 &gt; 添加客户拜访记录</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE cellSpacing=0 cellPadding=5  border=0>
							<tr>
								<td>拜访客户：</td>
								<td >
								<select id="customer" name="customer.cust_id">
								<option value="">-请选择-</option>
								</select>
								</td>
								<td>业务员名称：</td>
								<td>
								<select id="user" name="user.user_id">
								<option value="">-请选择-</option>
								</select>								
								</td>
							</tr>
							<TR>
								
								<td>拜访时间：</td>
								<td>
								<s:textfield  name="visit_time" cssClass="textbox" id="visit_time"  cssStyle="WIDTH: 180px" maxLength="50"  />
								</td>
								<td>拜访地点 ：</td>
								<td>
								<s:textfield cssClass="textbox" id="sChannel2"  cssStyle="WIDTH: 180px" maxLength="50" name="visit_addr" />
								</td>
							</TR>
							<TR>
								
								<td>拜访详情 ：</td>
								<td>
								<s:textfield cssClass="textbox" id="sChannel2"  cssStyle="WIDTH: 180px" maxLength="50" name="visit_detail" />
								</td>
								<td>下次拜访时间 ：</td>
								<td>
								<s:textfield cssClass="textbox" id="visit_nexttime"  cssStyle="WIDTH: 180px" maxLength="50" name="visit_nexttime" />
								</td>
							</TR>
							
							
						
							<tr>
								<td rowspan=2>
								<INPUT class=button id=sButton2 type=submit
														value="保存 " name=sButton2>
								</td>
							</tr>
						</TABLE>
						
						
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg">
					<IMG src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg"
						border=0></TD>
					<TD align=middle width="100%"
						background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	</s:form>
</BODY>
<script type="text/javascript">
$(function(){
	//加载客户
	$.post("${pageContext.request.contextPath}/customer_findAllCustomer.action",function(data){
		$(data).each(function(index,c){
			$("#customer").append("<option value='"+c.cust_id+"' >"+c.cust_name+"</option>")
		});
		
	},"json");
	
	//加载用户
	$.post("${pageContext.request.contextPath}/user_findAllUser.action",function(data){
		$(data).each(function(index,c){
			$("#user").append("<option value='"+c.user_id+"' >"+c.user_name+"</option>")
		});
		
	},"json");
});

</script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/jquery/jquery.datepick.css" type="text/css">
<script type="text/javascript" src="jquery/jquery-1.4.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery.datepick.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery.datepick-zh-CN.js"></script>
<script type="text/javascript">
		$(document).ready(function(){
			//使用class属性处理  'yy-mm-dd' 设置格式"yyyy/mm/dd"
			$('#visit_time').datepick({dateFormat: 'yy-mm-dd'});
			$('#visit_nexttime').datepick({dateFormat: 'yy-mm-dd'}); 

		});
</script>
</HTML>
