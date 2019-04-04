<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<div class="container">
			<div class="row">
				<div class="col-lg-4 col-md-4 col-sm-6">
					<h4><small>Welcome to Zpp</small></h4>
				</div>
				<div class="col-lg-5 col-md-5 hidden-xs col-sm-6">

				</div>
				<div class="col-lg-3 col-md-3 col-sm-12" style="padding-top: 15px;">
				<h4><img src="/Zpp/imgs/icon/me.svg" witch="20px" height="20px">${user.username} <a class="label label-danger" href="/Zpp/SignOut">退出</a>
				<a class="label label-danger" href="/Zpp/page/findPwd.html">修改密码</a></h4>
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
								<a href="/Zpp/page/admin/AddMerchandise.jsp">添加商品 <span class="sr-only">(current)</span></a>
							</li>
							<li>
								<a href="/Zpp/OnSaleProductList?currentPage=1&productClass=全部 " >在售商品 <span class="sr-only">(current)</span></a>
							</li>
							<li>
								<a href="/Zpp/FindProductAll?currentPage=1&productClass=全部">商品仓库<span class="sr-only">(current)</span></a>
							</li>
							<li>
								<a href="/Zpp/page/admin/base_info_set.jsp">商铺设置</a>
							</li>
							<li>
								<a href="/Zpp/MyQRCode">我的二维码</a>
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
