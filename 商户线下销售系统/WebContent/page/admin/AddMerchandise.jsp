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
		<title>添加商品</title>
		<link href="../../css/bootstrap.css" rel="stylesheet" type="text/css">
		<script src="../../js/jquery-1.11.3.min.js"></script>
		<script src="../../js/bootstrap.min.js"></script>
		<script type="text/javascript" src="../../js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="../../js/MyValidate.js"></script>

		<script type="text/javascript" src="../../js/AddMer.js"></script>
	</head>
	<style>
		a {
			text-decoration: none;
		}
		
		label.error {
			background: url(../../imgs/icon/unchecked.gif) no-repeat 10px 3px;
			padding-left: 30px;
			font-family: georgia;
			font-size: 15px;
			font-style: normal;
			color: red;
		}
	</style>

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
						<a class="navbar-brand " href="/Zpp/page/index.jsp">
							首页
						</a>
					</div>

					<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">

							<li class="active">
								<a href="#">添加商品 <span class="sr-only">(current)</span></a>
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
		<div class="container">
			<form role="form" class="form-horizontal " action="../../UploadProduct" method="post" id="AddMerchandise" enctype="multipart/form-data">
				<div class="form-group">
					<label for="productName" class="col-sm-3 control-label">商品名称</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="productName" placeholder="请输入商品名称" name="productName">
					</div>
				</div>
				<div class="form-group">
					<label for="productClass" class="col-sm-3 control-label">商品分类</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="productClass" placeholder="分类名字相同的商品会被分在一起" name="productClass">
					</div>
				</div>
				<div class="form-group">
					<label for="price" class="col-sm-3 control-label">价格/元</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="price" placeholder="请输入商品价格" name="price">

					</div>
				</div>
				<div class="form-group">
					<label for="productCount" class="col-sm-3 control-label">数量/件</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="productCount" placeholder="请输入商品数量" name="productCount">

					</div>
				</div>

				<div class="form-group">
					<label for="productImg" class="col-sm-3 control-label">商品图片</label>
					<div class="col-sm-6">
						<!--<input id="productImg" type="file" name="productImg" />-->
						<div class="input-group">
							<input id='location' class="form-control" onclick="$('#productImg').click();">
							<label class="input-group-btn">
               <input type="button" id="i-check" value="浏览文件" class="btn btn-primary" onclick="$('#productImg').click();">
           </label>
						</div>
						<input type="file" name="productImg" id='productImg'  onchange="$('#location').val($('#productImg').val());" style="display: none">
					</div>
				</div>

				<div class="form-group">
					<label class="col-sm-3 control-label"></label>
					<div class="col-sm-6">
						<img class="img-rounded img-thumbnail" width="140px" height="140px" src="..\..\imgs\icon\douzi.svg" id="spimg" />
					</div>
				</div>

				<div class="form-group">
					<label for="productMessage" class="col-sm-3 control-label">商品描述</label>
					<div class="col-sm-6">

						<textarea cols="20" rows="5" data-first="true" id="productMessage" name="productMessage"></textarea>

					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-offset-2 col-sm-10 ">
						<button type="submit" style=" width: 80%;   height: 40px; background-color: red; font-size: 20px; color: white;" class="btn btn-default">添加到仓库</button>
					</div>
				</div>
				<br />

			</form>

		</div>

	</body>

</html>