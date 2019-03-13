<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>添加学生</title>
</head>
<body>
<form method="post" action="UpdateServlet?sid=${stu.sid}">
	<table border="1" width="600">

<tr>
<td>姓名</td>
<td><input type="text" name="sname" value="${stu.sname}"></td>
</tr>

<tr>
<td>性别</td>
<td>

男<input type="radio" name="gender" value="男" <c:if test="${stu.gender=='男'}">checked</c:if>>
女<input type="radio" name="gender" value="女" <c:if test="${stu.gender=='女'}">checked</c:if>>
</td>
</tr>

<tr>
<td>电话</td>
<td><input type="text" name="phone" value="${stu.phone}"></td>
</tr>

<tr>
<td>生日</td>
<td><input type="text" name="birthday" value="${stu.birthday}"></td>
</tr>

<tr>
<td>爱好</td>
<td>
<!-- ${fn:contains('原字符串','包含的字符串')} -->
<input type="checkbox" value="游泳" name="hobby" <c:if test="${fn:contains(stu.hobby,'游泳')}">checked</c:if>>游泳
<input type="checkbox" value="篮球" name="hobby" <c:if test="${fn:contains(stu.hobby,'篮球')}">checked</c:if>>篮球
<input type="checkbox" value="足球" name="hobby" <c:if test="${fn:contains(stu.hobby,'足球')}">checked</c:if>>足球
<input type="checkbox" value="看书" name="hobby" <c:if test="${fn:contains(stu.hobby,'看书')}">checked</c:if>>看书
<input type="checkbox" value="写字" name="hobby" <c:if test="${fn:contains(stu.hobby,'写字')}">checked</c:if>>写字
</td>
</tr>

<tr>
<td>简介</td>
<td><textarea name="info" rows="3" cols="20">${stu.info}</textarea></td>
</tr>

<tr>
<td colspan="2"><input type="submit" value="更新"></td>
</tr>

</table>	
</form>
</body>
</html>