<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<h1>欢迎: ${username }</h1>
<!-- 当前用户有什么权限才可以看到 -->
<sec:authorize access="hasAuthority('ROLE_ADD_PRODUCT')">
<a href="/product/add">增加</a>
</sec:authorize>

<sec:authorize  access="hasAuthority('ROLE_UPDATE_PRODUCT')">
<a href="/product/update">修改</a>
</sec:authorize>

<sec:authorize access="hasAuthority('ROLE_LIST_PRODUCT')">
<a href="/product/list">查询</a>
</sec:authorize>

<sec:authorize access="hasAuthority('ROLE_DELETE_PRODUCT')">
<a href="/product/delete">删除</a>
</sec:authorize>





</body>
</html>