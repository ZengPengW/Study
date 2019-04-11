<%@page import="com.zpp.utils.CookiesUtils"%>
<%@page import="com.zpp.utils.Base64Utils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>我的订单</title>
		<!--声明文档兼容模式，表示使用IE浏览器的最新模式-->
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
		<meta name="viewport" content="width=device-width, initial-scale=1, maxinum-scale=1,user-scalable=no">
		<link href="${pageContext.request.contextPath }/css/bootstrap.css" rel="stylesheet" type="text/css">
		<script src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
		<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>

	</head>
	<style>
		.header {
			height: 46px;
			position: fixed;
			left: 0;
			top: 0;
			width: 100%;
			background: #2395ff;
			padding-left: 10px;
			z-index: 2;
			display: flex;
			justify-content: space-between;
			box-sizing: border-box;
			line-height: 46px;
		}
		
		.header .fon {
			font-size: 1.1em;
			color: white;
			position:absolute;
			left:40%;
			font-weight: 700;
		}
		
		.cart-group-2U2FI img {
			width: .96rem;
			width: 28.6vw;
			height: .96rem;
			height: 28.6vw;
			position:absolute;
			text-align: left;
		}
		
		body {
			margin-top: 22%;
		}
	</style>

	<body>
	<%
	String shopname= Base64Utils.decoder(CookiesUtils.getCookie(request.getCookies(), "shopname"));
	request.setAttribute("shopname", shopname);
	%>
		<header class="header " >
		<span ><a href="${pageContext.request.contextPath }/OnSaleProductListClient?currentPage=1&productClass=5YWo6YOo"><img src="${pageContext.request.contextPath }/imgs/icon/index.svg" width="50%" height="100%" alt="首页"></a></span>
			<h1 class="fon" >
				<strong>我的订单</strong>
			</h1>
		</header>

		<div class="container">
		<c:if test="${empty myOrder}">
			<h2 align="center"><span class="label label-default">暂无订单</span></h2>
		</c:if>
		<c:forEach items="${myOrder}" var="ls">
		
			<div class="panel panel-default">

				<div class="panel-heading ">
					<span>${shopname }</span>
					<span class="col-sm-offset-10" style="right: 10%;position: absolute;">实付款:<strong  ><font color="red"  >￥<fmt:formatNumber value="${ls.money}"
						type="number" pattern="0.00" maxFractionDigits="2" /></font></strong></span>

				</div>
				<div class="panel-body ">

					<div class="media">
						
						<div class="media-body text-left">
							<h5>用户名：${ls.username}</h5>
							<h5>手机:${fn:substring(ls.phone,0,3) }***${fn:substring(ls.phone,8,11) }</h5>
							<h5>取货编号：${ls.gid}</h5>
							<h5>创建时间：</h5>
							<h6><small>${ls.time}</small></h6>
						
							<h6>订单状态：
							<c:if test="${ls.isteke==0}">
							<font color="#00E067">
							<c:if test="${ls.statu==0}">
							待确认···
							</c:if>
							<c:if test="${ls.statu==1}">
							待备货
							</c:if>
							<c:if test="${ls.statu==2}">
							已备货
							</c:if>
							</font>
							</c:if>
							
							<c:if test="${ls.isteke==1}">
							<font color="#FF4A07">
							
							已取货
							
							</font>
							</c:if>
							</h6>

						</div>

					</div>
						<c:if test="${ls.isteke==0}">
						<form method="post" action="${pageContext.request.contextPath}/page/customer/teke.jsp" style="margin:0px;display:inline;">
						<input style="display: none" value="${ls.oid}"  name="oid"></input>
						<input type="submit" class="btn btn-success" value="取货"></input>
					</form>
						
						</c:if>
						<c:if test="${ls.isteke==1}">
						<button type="button" class="btn btn-warning disabled">已取货</button>
						</c:if>
						
					<form method="post" action="${pageContext.request.contextPath}/OrderDetails" style="margin:0px;display:inline;">
						<input style="display: none" value="${ls.oid}"  name="oid"></input>
						<input type="submit" class="btn btn-danger" value="详情"></input>
					</form>
						
					
				</div>

			</div>
		
		</c:forEach>
		

		</div>

		

	</body>

</html>