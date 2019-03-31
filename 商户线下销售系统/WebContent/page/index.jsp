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

	<body>

		<!--头部-->
		<div class="container">
			<div class="row">
				<div class="col-lg-4 col-md-4 col-sm-6">
					<h4><small>Welcome to Zpp</small></h4>
				</div>
				<div class="col-lg-5 col-md-4 hidden-xs col-sm-6">

				</div>
				<div class="col-lg-3 col-md-4 col-sm-12" style="padding-top: 15px;">
					<a href="#">登录</a>
					<a href="#">注册</a>
					<a href="#">购物车</a>
				</div>
			</div>
		</div>

		<!--导航栏-->
		<div class="container" style="margin-top: 10px;">
			<nav class="navbar navbar-inverse">
				<div class="container-fluid">
					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					        <span class="sr-only">Toggle navigation</span>
					        <span class="icon-bar"></span>
					        <span class="icon-bar"></span>
					        <span class="icon-bar"></span>
					    </button>
						<a class="navbar-brand active" href="/Zpp/page/index.jsp">
							首页
						</a>
					</div>

					<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">

							<li class="">
								<a href="admin/AddMerchandise.jsp">添加商品 <span class="sr-only">(current)</span></a>
							</li>
							<li>
								<a href="#">在售商品 <span class="sr-only">(current)</span></a>
							</li>
							<li>
								<a href="/Zpp/FindProductAll?currentPage=1&productClass=全部">商品仓库<span class="sr-only">(current)</span></a>
							</li>
							<li>
								<a href="#">商铺设置</a>
							</li>

							<li class="dropdown">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">订单中心 <span class="caret"></span></a>
								<ul class="dropdown-menu ">
									<li>
										<a href="#">全部订单</a>
									</li>
									<li role="separator" class="divider"></li>
									<li>
										<a href="#">未取货订单</a>
									</li>
									<li>
										<a href="#">已取货订单</a>
									</li>
									<li role="separator" class="divider"></li>
									<li>
										<a href="#">展示未取货编号</a>
									</li>

								</ul>
							</li>
						</ul>

					</div>
				</div>
			</nav>
		</div>
<%
Jedis jedis=JedisPoolUtils.getJedis();					
String sid=CookiesUtils.getCookie(request.getCookies(), "sid");
String userJson=jedis.hget("users",sid );
jedis.close();
User user =JsonUtils.getUser(userJson);						
String depotCount=jedis.hget("depot", String.valueOf(user.getId()));	
if(depotCount==null){
	SellerService service=new SellerServiceImpl();
	depotCount=String.valueOf(service.CheckProductCount(user.getId(),"全部"));
	jedis.hset("depot", String.valueOf(user.getId()), depotCount);
	depotCount=String.format("%.2f", Double.valueOf(depotCount));
	
}
else {
  depotCount=String.format("%.2f", Double.valueOf(depotCount))  ;
}
%>
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
						<h2><strong>0.00</strong></h2></td>
				</tr>
				<tr>
					<td>
						<h3>在售商品/条</h3></td>
					<td>
						<h2><strong>0.00</strong></h2></td>
				</tr>
				<tr class="warning">
					<td>
						<h3>仓库中的商品/条</h3></td>
					<td>
						<h2><strong><%=depotCount %></strong></h2></td>
				</tr>
				<tr>
					<td>
						<h3>总售出/件</h3></td>
					<td>
						<h2><strong>1.00</strong></h2></td>
				</tr>

				<tr class="danger">
					<td>
						<h3>总盈利/元</h3></td>
					<td>
						<h2><strong>0.00</strong></h2></td>
				</tr>

			</table>

		</div>
	</body>

</html>