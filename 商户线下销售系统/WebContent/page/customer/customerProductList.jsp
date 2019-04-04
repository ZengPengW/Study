<!--<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>-->
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>欢迎光临 ${shopname}</title>

		<!--声明文档兼容模式，表示使用IE浏览器的最新模式-->
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
		<meta name="viewport" content="width=device-width, initial-scale=1, maxinum-scale=1,user-scalable=no">
		<link href="../../css/bootstrap.css" rel="stylesheet" type="text/css">
		<script src="../../js/jquery-1.11.3.min.js"></script>
		<script src="../../js/bootstrap.min.js"></script>
	</head>

	<body>
		<div class="container">
			<div class="row">
				<div class="col-lg-4 col-md-4 col-sm-6">
					<h4><small>Welcome to ${shopname}</small></h4>
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
						<a class="navbar-brand active" href="#">
							商品分类
						</a>
					</div>

					<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">

							<li class="">
								<a href="/Zpp/page/admin/AddMerchandise.jsp">添加商品 <span class="sr-only">(current)</span></a>
							</li>
							<li>
								<a href="/Zpp/OnSaleProductList?currentPage=1&productClass=全部 ">在售商品 <span class="sr-only">(current)</span></a>
							</li>
							<li>
								<a href="/Zpp/FindProductAll?currentPage=1&productClass=全部">商品仓库<span class="sr-only">(current)</span></a>
							</li>
							<li>
								<a href="/Zpp/page/admin/base_info_set.jsp">商铺设置</a>
							</li>

						
						</ul>

					</div>
				</div>
			</nav>
		</div>

	</body>

</html>