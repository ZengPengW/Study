<%@page import="com.zpp.utils.JsonUtils"%>
<%@page import="com.zpp.domain.User"%>
<%@page import="com.zpp.utils.CookiesUtils"%>
<%@page import="Jedis.JedisPoolUtils"%>
<%@page import="redis.clients.jedis.Jedis"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
		<meta charset="utf-8">
		<!--声明文档兼容模式，表示使用IE浏览器的最新模式-->
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>首页</title>
		<link href="../../css/bootstrap.css" rel="stylesheet" type="text/css">
		<script src="../../js/jquery-1.11.3.min.js"></script>
		<script src="../../js/bootstrap.min.js"></script>

</head>
<%
String sid=CookiesUtils.getCookie(request.getCookies(), "sid");
User user=CookiesUtils.getUser(sid);
request.setAttribute("user", user);
%>
<body>
<!--头部-->
		<div class="container">
			<div class="row">
				<div class="col-lg-4 col-md-4 col-sm-6">
					<h4><small>Welcome to Zpp</small></h4>
				</div>
				<div class="col-lg-5 col-md-5 hidden-xs col-sm-6">

				</div>
				<div class="col-lg-3 col-md-3 col-sm-12" style="padding-top: 15px;">
				<h4><img src="/Zpp/imgs/icon/me.svg" witch="20px" height="20px">${user.username} <a class="label label-danger" href="/Zpp/SignOut">退出</a></h4>
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
								<a href="/Zpp/OnSaleProductList?currentPage=1&productClass=全部 " >在售商品 <span class="sr-only">(current)</span></a>
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
		
		<div class="container">
			<div class="row">
				<div class="col-md-2">
					<img src="../../imgs/icon/me.svg" width="100px" height="100px" />
				</div>
				<div class="col-md-3">
					
				</div>
				<div class="col-md-5">
				<h1><span class="label label-danger">商铺资料</span></h1>
				</div>
			</div>
			<div class="row">
				<div class="container">
			<form role="form" class="form-horizontal " action="/Zpp/AlterProductServlet" method="post" id="AlterMerchandise" enctype="multipart/form-data">
				<div class="form-group">
					<label for="productName" class="col-sm-3 control-label">商铺名称</label>
					<div class="col-sm-6">
						<input type="text" style="display: none" value="${product.pid}" id="pid" name="pid">
						<input type="text" class="form-control" id="productName" placeholder="请输入你要修改商铺名称" name="productName" value="sdad" disabled="disabled">
					</div>
				</div>
				<div class="form-group">
					<label for="username" class="col-sm-3 control-label">用户名</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="username"  placeholder="请输入你要修改的用户名" name="username" value="" disabled="disabled">
					</div>
				</div>
				<div class="form-group">
					<label for="email" class="col-sm-3 control-label">邮箱</label>
					<div class="col-sm-6">
					<!--
                    	<input type="text" class="form-control" id="price" name="email" value="" disabled="disabled">
                    -->	
					zp***********@qq.com
					</div>
				</div>
				<div class="form-group">
					<label for="payment" class="col-sm-3 control-label">支付宝</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="payment" placeholder="请输入你要绑定的支付宝账户" name="payment" value="" disabled="disabled">

					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-offset-2 col-sm-10 ">
						<button type="submit" style=" width: 80%;   height: 40px; background-color: red; font-size: 20px; color: white;" class="btn btn-default">保存</button>
					</div>
				</div>
				<br />

			</form>

		</div>
				
			</div>
			
		</div>
		
</body>
</html>