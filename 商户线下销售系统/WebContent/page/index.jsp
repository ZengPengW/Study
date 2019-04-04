<%@page import="com.zpp.domain.Finance"%>
<%@page import="com.zpp.utils.JsonUtils"%>
<%@page import="com.zpp.service.SellerServiceImpl"%>
<%@page import="com.zpp.service.SellerService"%>
<%@page import="com.zpp.utils.CookiesUtils"%>
<%@page import="com.zpp.domain.User"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="redis.clients.util.JedisURIHelper"%>
<%@page import="Jedis.JedisPoolUtils"%>
<%@page import="redis.clients.jedis.Jedis"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">

	<head>
		<meta charset="utf-8">
		<!--声明文档兼容模式，表示使用IE浏览器的最新模式-->
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>首页</title>
		<link href="../css/bootstrap.css" rel="stylesheet" type="text/css">
		<script src="../js/jquery-1.11.3.min.js"></script>
		<script src="../js/bootstrap.min.js"></script>

	</head>

<%
Jedis jedis=JedisPoolUtils.getJedis();					
String sid=CookiesUtils.getCookie(request.getCookies(), "sid");
String userJson=jedis.hget("users",sid );
User user =JsonUtils.getUser(userJson);	
request.setAttribute("user", user);
String depotCount=jedis.hget("depot", String.valueOf(user.getId()));

SellerService service=new SellerServiceImpl();
if(depotCount==null){
	depotCount=String.valueOf(service.CheckProductCount(user.getId(),"全部"));
	jedis.hset("depot", String.valueOf(user.getId()), depotCount);
	depotCount=String.format("%.2f", Double.valueOf(depotCount));
}
else {
  depotCount=String.format("%.2f", Double.valueOf(depotCount))  ;
}

String onsale=jedis.hget("onsale", String.valueOf(user.getId()));
if(onsale==null){
	onsale=String.valueOf(service.CheckOnsaleProductCount(user.getId(), "全部"));
	jedis.hset("onsale", String.valueOf(user.getId()), onsale);
}
onsale=String.format("%.2f", Double.valueOf(onsale));

Finance finance=service.GetFinanceByUid(user.getId());
if(finance==null){
	finance=new Finance();
	finance.setBalance(0);
	finance.setSoldout(0);
	finance.setTotal(0);
}

jedis.close();
%>
	<body>

		<!--头部-->
		<jsp:include page="/page/head.jsp" flush="true"></jsp:include>

		<div class="container">
			<table class="table table-striped table-responsive">
				<tr>
				
					<th colspan="2" class="text-center">
						<h2><strong>商铺信息表</strong></h2></th>
					</tr>
				<tr class=" success">
					<td>
						<h3>余额/元</h3></td>
					<td>
						<h2><strong><%=String.format("%.2f", Double.valueOf(finance.getBalance())) %></strong></h2></td>
				</tr>
				<tr>
					<td>
						<h3>出售中/条</h3></td>
					<td>
						<h2><strong><%=onsale %></strong></h2></td>
				</tr>
				<tr class="warning">
					<td>
						<h3>仓库中的商品/条</h3></td>
					<td>
						<h2><strong><%=depotCount %></strong></h2></td>
				</tr>
				<tr>
					<td>
						<h3>已售出/件</h3></td>
					<td>
						<h2><strong><%=String.format("%.2f", Double.valueOf(finance.getSoldout())) %></strong></h2></td>
				</tr>

				<tr class="danger">
					<td>
						<h3>总盈利/元</h3></td>
					<td>
						<h2><strong><%=String.format("%.2f", Double.valueOf(finance.getTotal())) %></strong></h2></td>
				</tr>

			</table>

		</div>
	</body>

</html>