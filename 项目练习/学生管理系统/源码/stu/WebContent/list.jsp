<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>学生列表</title>
</head>

<body>
<form action="SearchStudentServlet" method="post">
<table border="1" width="800">
	
	<tr>
		<td colspan="8">
		按姓名查询：<input type="text" name="sname">&nbsp;&nbsp;&nbsp;&nbsp;
		按性别查询：
		<select name="sgender">
			<option value="">--请选择--</option>
			<option value="男">男</option>
			<option value="女">女</option>
		</select>&nbsp;&nbsp;&nbsp;
		<input type="submit" value="查询">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="add.jsp">添加</a>
		</td>
	</tr>
	<tr align="center">
		<td>编号</td>
		<td>姓名</td>
		<td>性别</td>
		<td>电话</td>
		<td>生日</td>
		<td>爱好</td>
		<td>简介</td>
		<td>操作</td>
	</tr>
	<c:forEach items="${requestScope.students}" var="stu">
	<tr align="center">
		<td>${stu.sid}</td>
		<td>${stu.sname}</td>
		<td>${stu.gender}</td>
		<td>${stu.phone}</td>
		<td>${stu.birthday}</td>
		<td>${stu.hobby}</td>
		<td>${stu.info}</td>
		<td><a href="EditServlet?sid=${stu.sid}">更新</a>     <a href="#" onclick="doDelete(${stu.sid})" >删除</a></td>
	</tr>
	</c:forEach>
	
</table>
</form>
<script>
	function doDelete(sid){
		var flag=confirm("是否确定删除？");
		if(flag){
			window.location.href="DeleteServlet?sid="+sid;
		}
	}
</script>
</body>

</html>