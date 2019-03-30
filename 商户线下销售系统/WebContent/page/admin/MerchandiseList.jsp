<%@page import="com.sun.net.httpserver.Authenticator.Success"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.util.List"%>
<%@page import="com.zpp.service.SellerServiceImpl"%>
<%@page import="com.zpp.service.SellerService"%>
<%@page import="com.zpp.utils.JsonUtils"%>
<%@page import="com.zpp.domain.User"%>
<%@page import="redis.clients.jedis.Jedis"%>
<%@page import="Jedis.JedisPoolUtils"%>
<%@page import="com.zpp.utils.CookiesUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

		<meta charset="utf-8">
		<!--声明文档兼容模式，表示使用IE浏览器的最新模式-->
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>商品仓库</title>
		<link href="../../css/bootstrap.css" rel="stylesheet" type="text/css">
		<script src="../../js/jquery-1.11.3.min.js"></script>
		<script src="../../js/bootstrap.min.js"></script>
		<script type="text/javascript" src="../../js/jquery.validate.min.js"></script>

	</head>

	<body>
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
						<a class="navbar-brand " href="/Zpp/page/index.jsp">
							首页
						</a>
					</div>

					<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">

							<li>
								<a href="/Zpp/page/admin/AddMerchandise.jsp">添加商品 <span class="sr-only">(current)</span></a>
							</li>
							<li>
								<a href="#">在售商品 <span class="sr-only">(current)</span></a>
							</li>
							<li class="active">
								<a href="#">商品仓库<span class="sr-only">(current)</span></a>
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
try {
	String sid=CookiesUtils.getCookie(request.getCookies(), "sid");
	Jedis jedis=JedisPoolUtils.getJedis();
	String jsonstr=jedis.hget("users", sid);
	User user=JsonUtils.getUser(jsonstr);
	int uid=user.getId();
	SellerService service =new SellerServiceImpl();
	List<Object> productClass=service.FindProductClass(uid);
	request.setAttribute("productClassList", productClass);
} catch (SQLException e) {
	request.setAttribute("isSuccess", false);
	request.getRequestDispatcher("/Zpp/page/Success.jsp").forward(request, response);
}	
%>
		<div class="container">
			<div class="row">
				<div class="col-md-1 ">
					<div class="btn-group">
						<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
      <span id="classname">选择商品类别</span>
      <span class="caret"></span>
    </button>
						<ul class="dropdown-menu">
							<c:forEach items="${productClassList}" var="ls">
								<li>
									<a href="#">${ls}</a>
								</li>
								<li role="presentation" class="divider"></li>
							</c:forEach>
							
							
						</ul>
					</div>

				</div>

			</div>
			<div class="row">

				<div class="col-md-12 ">
					<table class="table table-striped table-responsive">
						<tr class="text-center">
							<th class="text-center">
								<h4><strong>商品展示</strong></h4>
							</th>
							<th class="text-center">
								<h4><strong>商品分类</strong></h4>
							</th>
							<th class="text-center">
								<h4><strong>商品名称</strong></h4>
							</th>
							<th class="text-center">
								<h4><strong>商品数量</strong></h4>
							</th>
							<th class="text-center">
								<h4><strong>商品价格</strong></h4>
							</th>
							<th class="text-center">
								<h4><strong>商品描述</strong></h4>
							</th>
							<th class="text-center">
								<h4><strong>修改</strong></h4>
							</th>
						</tr>
						<tr class="text-center">
							<td>
								<img width="100px" height="100px" src="../../imgs/icon/douzi.svg" />
							</td>
							<td>
								<h3><strong>分类1</strong></h3>
							</td>
							<td>
								<h3><strong>随便</strong></h3>
							</td>
							<td>
								<h3><strong>2.00</strong></h3>
							</td>
							<td>
								<h3><strong>1231.00</strong></h3>
							</td>
							<td>
								<textarea cols="20" rows="5" readonly class="form-control">垃adas ad da打开就好客户贷款打卡机卡卡开发阿奎卡空间打卡机阿克家啊比较快科技</textarea>
							</td>
							<td class="text-center">
								<div class="btn-group " style="margin: 10% auto auto auto;">
									<button type="button" class="btn btn-default">修改</button>
									<button type="button" class="btn btn-default">删除</button>
									<button type="button" class="btn btn-default">发布</button>
								</div>
							</td>
						</tr>

					</table>
				</div>
			</div>
		</div>

</html>