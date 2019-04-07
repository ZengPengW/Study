<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>商铺二维码</title>
		<!--声明文档兼容模式，表示使用IE浏览器的最新模式-->
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
		<meta name="viewport" content="width=device-width, initial-scale=1, maxinum-scale=1,user-scalable=no">
		<link href="/Zpp/css/bootstrap.css" rel="stylesheet" type="text/css">
		<script src="/Zpp/js/jquery-1.11.3.min.js"></script>
		<script src="/Zpp/js/bootstrap.min.js"></script>
	</head>
<% request.setAttribute("currpage", "myqr"); %>
	<style>
		.bg {
       background:url(图片地址) no-repeat center;
       background-size:contain;
}
	</style>
	<body>
		<jsp:include page="/page/head.jsp" flush="true"></jsp:include>
		<div class="container">
			<div class="row col-md-6 col-lg-offset-3 col-md-offset-3 " >
				
				<div class="row bg" style=" padding:62% 30% 30% 30%;background-image: url(../../imgs/icon/qrbg.svg); background-size: cover; background-repeat: no-repeat;">	
					<img src="" class="img-thumbnail" id="myqr"/>	
				</div>
			</div>
		
		</div>
		
	</body>
 <script>
$(function(){
var domain=window.location.host;

$("#myqr").attr("src","/Zpp/MyQRCode?addr="+domain);
	
});
</script>
</html>