<%@page import="com.zpp.utils.CookiesUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>扫码取货</title>
<!--声明文档兼容模式，表示使用IE浏览器的最新模式-->
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
		<meta name="viewport" content="width=device-width, initial-scale=1, maxinum-scale=1,user-scalable=no">
		<link href="${pageContext.request.contextPath }/css/bootstrap.css" rel="stylesheet" type="text/css">
		<script src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
		<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
</head>
<style>
		.bg {
       background:url(图片地址) no-repeat center;
       background-size:contain;
}
	</style>
	<body>
		
		<div class="container">
			<div class="row col-md-6 col-lg-offset-3 col-md-offset-3 " >
				
				<div class="row bg" style=" padding:62% 30% 30% 30%;background-image: url(${pageContext.request.contextPath }/imgs/icon/qrbg.svg); background-size: cover; background-repeat: no-repeat;">	
					<img src="" class="img-thumbnail" id="myqr"/>	
				</div>
			</div>
		
		</div>
		<h4 align="center"><font color="red">凭此二维码到前台扫码取货</font></h4>
	</body>
<%
int oid=Integer.parseInt(request.getParameter("oid"));
String equipment=CookiesUtils.getCookie(request.getCookies(), "equipment");
int uid=Integer.parseInt(CookiesUtils.getCookie(request.getCookies(), "uid"));
String par="&uid"+uid+"&oid="+oid+"&equipment="+equipment;
request.setAttribute("par", par);
%>	
 <script>
$(function(){
var domain=window.location.host;

$("#myqr").attr("src","${pageContext.request.contextPath }/TekeServlet?addr="+domain+"${par}");
	
});
</script>
</html>