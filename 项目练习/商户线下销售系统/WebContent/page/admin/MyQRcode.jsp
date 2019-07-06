<%@page import="com.zpp.utils.CookiesUtils"%>
<%@page import="com.zpp.domain.User"%>
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
		<link href="${pageContext.request.contextPath }/css/bootstrap.css" rel="stylesheet" type="text/css">
		<script src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
		<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
	</head>
<% request.setAttribute("currpage", "myqr"); %>
<%
	User user=CookiesUtils.getUser(CookiesUtils.getCookie(request.getCookies(), "sid"));
	request.setAttribute("user", user);
%>
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
				
				<div class="row bg" style=" padding:62% 30% 30% 30%;background-image: url(${pageContext.request.contextPath }/imgs/icon/qrbg.svg); background-size: cover; background-repeat: no-repeat;">	
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

<!-- websocket -->
<script src="${pageContext.request.contextPath }/js/tone.js" type="text/javascript" ></script>
<script>var uid="${user.id}a7"+new Date().getTime();</script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/MyWEBsocket.js" ></script>

<!-- message -->
<link href="${pageContext.request.contextPath }/css/message.css" rel="stylesheet" type="text/css"/>



<div id="winpop">
 <div class="title">短消息！<span class="close" onclick="myclose()">×</span></div>
    <div class="con">有新的订单啦</div>
</div>

</html>