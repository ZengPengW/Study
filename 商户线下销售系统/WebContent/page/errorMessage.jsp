<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>提示</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
		<meta name="viewport" content="width=device-width, initial-scale=1, maxinum-scale=1,user-scalable=no">
		<link href="${pageContext.request.contextPath }/css/bootstrap.css" rel="stylesheet" type="text/css">
		<script src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
		<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
			<div class="row col-md-6 col-lg-offset-3 col-md-offset-3  "  align="center" style="font-weight: 300;">
			<c:if test="${status==0}">
						<img alt="错误" src="${pageContext.request.contextPath }/imgs/icon/fail.svg" class="img-thumbnail" >
			
			
			<h3 class="alert alert-danger" ><font color="red">${message}</font></h3>
			
		
			</c:if>
			<c:if test="${status==1}">
			<img alt="成功" src="${pageContext.request.contextPath }/imgs/icon/success.svg" class="img-thumbnail">
			<h3 class="alert alert-success"><font color="limegreen">${message}</font></h3>
			</c:if>	
			</div>
		
		</div>
		
</body>

</html>