<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>
</head>
<body>
<h1>登录页面</h1>
<c:if test="${not empty param.error}">
<h3><font color="red">用户名或密码错误</font></h3>
</c:if>
<form action="${pageContext.request.contextPath}/securityLogin" method="post">
用户名:<input type="text" name="username"><br/>
密   码: <input type="password" name="password"><br/>
记住我:<input type="checkbox" name="remember-me" value="true" checked="checked" />
<input type="submit" value="登录">
</form>

</body>
</html>