<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
</head>
<body>
<h1>登录页面</h1>
<h3 ><font color="red" id="info" style="display: none" ></font></h3>
<form  id="loginFrom" method="post">
用户名:<input type="text" name="username"><br/>
密   码: <input type="password" name="password"><br/>
记住我:<input type="checkbox" name="remember-me" value="true" checked="checked" /><br/>
验证码:<input type="text" name="imgCode"> <img alt="验证码" src="${pageContext.request.contextPath}/imgCode" id="getimgcode">
<input type="button" value="登录" id="loginBut">
</form>

<!-- 异步登录 -->

<script>
$(function(){
	$("#loginBut").click(function(){
		$.post("${pageContext.request.contextPath}/securityLogin",
				$("#loginFrom").serialize(),
				function(data){
				if(data.status==1){
					window.location.href="${pageContext.request.contextPath}/index"
				}else if(data.status==2){
					$("#info").text("用户名或密码错误").show();
				}else{
					$("#info").text(data.errorMsg).show();
				}
			
		  },"json");
		
		
	});
	
	
	$("#getimgcode").click(function(){
		var url="${pageContext.request.contextPath}/imgCode";
		$("#getimgcode").attr("src",url+"?"+new Date().getTime());
		
	});
	
	
});


</script>



</body>
</html>