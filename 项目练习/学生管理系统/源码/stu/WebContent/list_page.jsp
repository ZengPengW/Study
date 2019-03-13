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
	<c:forEach items="${requestScope.pagebean.list}" var="stu">
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
	<tr>
	<td colspan="8">
	第${pagebean.currentPage}/${pagebean.totalPage}页&nbsp;&nbsp;
	每页显示${pagebean.pageSize}条&nbsp;&nbsp;&nbsp;&nbsp;
	总记录数${pagebean.totalSize}条&nbsp;&nbsp;
	
	<c:if test="${pagebean.currentPage!=1}">
	<a href="StudentListPageServlet?currentPage=1">首页</a>&nbsp;|&nbsp;<a href="StudentListPageServlet?currentPage=${pagebean.currentPage-1}"> 上一页</a>
	</c:if>
	
	<c:forEach begin="1" end="${pagebean.totalPage}" var="i">
		<c:if test="${pagebean.currentPage==i}">
			${i}
		</c:if>
		<c:if test="${pagebean.currentPage!=i}">
			<a href="StudentListPageServlet?currentPage=${i}">${i}</a>
		</c:if>
		
	</c:forEach>
	
	<c:if test="${pagebean.currentPage!=pagebean.totalPage}">
	<a href="StudentListPageServlet?currentPage=${pagebean.currentPage+1}">下一页</a> &nbsp; |&nbsp;<a href="StudentListPageServlet?currentPage=${pagebean.totalPage}"> 尾页</a>
	</c:if>
	</td>
	
	</tr>
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