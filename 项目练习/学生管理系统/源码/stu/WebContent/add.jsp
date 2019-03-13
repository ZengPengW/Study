<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>添加学生</title>
</head>
<body>
<form method="post" action="AddStudentServlect">
	<table border="1" width="600">

<tr>
<td>姓名</td>
<td><input type="text" name="sname"></td>
</tr>

<tr>
<td>性别</td>
<td>
男<input type="radio" name="gender" value="男">
女<input type="radio" name="gender" value="女">
</td>
</tr>

<tr>
<td>电话</td>
<td><input type="text" name="phone"></td>
</tr>

<tr>
<td>生日</td>
<td><input type="text" name="birthday"></td>
</tr>

<tr>
<td>爱好</td>
<td>
<input type="checkbox" value="游泳" name="hobby">游泳
<input type="checkbox" value="篮球" name="hobby">篮球
<input type="checkbox" value="足球" name="hobby">足球
<input type="checkbox" value="看书" name="hobby">看书
<input type="checkbox" value="写字" name="hobby">写字
</td>
</tr>

<tr>
<td>简介</td>
<td><textarea name="info" rows="3" cols="20"></textarea></td>
</tr>

<tr>
<td colspan="2"><input type="submit" value="添加"></td>
</tr>

</table>	
</form>
</body>
</html>