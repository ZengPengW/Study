<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>提示</title>
	</head>
	<style>
		.di{
			
			 height: 100px; 
			 width:200px;
			 margin-left: 40%;
		}
		
	</style>
	<script type="text/javascript">
		function countDown(){
		setTimeout("location.href='/Zpp/page/login.jsp'",1000);
             
	}
	countDown();

	</script>
	<body >
	<div class="di">
	<c:if test="${isSuccess}">
	<img src="/Zpp/imgs/icon/success.svg" width="100px" height="100px" />
	<h2 style="margin-left: -15%;">恭喜，操作成功！</h2>
	<h4 style="margin-left: -15%;">正在跳转界面....</h4>
	</c:if>
	<c:if test="${!isSuccess}">
	<img src="/Zpp/imgs/icon/fail.svg" width="100px" height="100px" />
	<h2 style="margin-left: -15%;">抱歉，操作失敗！</h2>
	<h4 style="margin-left: -15%;">正在跳转界面....</h4>
	</c:if>
	</div>
	

	</body>
</html>
