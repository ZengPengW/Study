<%@page import="com.zpp.utils.URLcodeUtils"%>
<%@page import="com.zpp.utils.Base64Utils"%>
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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<meta charset="utf-8">
<!--声明文档兼容模式，表示使用IE浏览器的最新模式-->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>商品仓库</title>
<link href="${pageContext.request.contextPath }/css/bootstrap.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.validate.min.js"></script>
<!--  <script type="text/javascript" src="/Zpp/js/sweet-alert.min.js"></script>
<link rel="stylesheet" type="text/css" href="/Zpp/css/sweet-alert.css">-->
<script type="text/javascript" src="${pageContext.request.contextPath }/js/SearchProduct.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/CRUD.js"></script>
<script src="${pageContext.request.contextPath }/js/sweetalert2.all.min.js"></script>
<link href="${pageContext.request.contextPath }/css/checkDetails.css" rel="stylesheet" type="text/css" >
<script src="${pageContext.request.contextPath }/js/es6-promise.auto.js" type="text/javascript" ></script>
</head>
<%
	request.setAttribute("currpage", "mer");
	List<Object> onsalePid = null;
	List<Object> productClass = null;
	try {
		String sid = CookiesUtils.getCookie(request.getCookies(), "sid");
		Jedis jedis = JedisPoolUtils.getJedis();
		String jsonstr = jedis.hget("users", sid);
		User user = JsonUtils.getUser(jsonstr);
		int uid = user.getId();
		SellerService service = new SellerServiceImpl();
		productClass = service.FindProductClass(uid);
		onsalePid = service.GetOnSalePid(uid);
		request.setAttribute("user", user);
		request.setAttribute("productClassList", productClass);
		request.setAttribute("onsalePid", onsalePid);
		jedis.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
%>
<body>
	<jsp:include page="/page/head.jsp" flush="true"></jsp:include>

	<script type="text/javascript">
		var list = new Array();
	</script>
	<c:forEach items="${onsalePid}" var="x">
		<script type="text/javascript">
			list.push("${x}");
		</script>
	</c:forEach>

	<div class="container">
		<div class="row">
			<div class="col-md-1 ">
				<div class="btn-group">
					<button type="button" class="btn btn-primary dropdown-toggle"
						data-toggle="dropdown">
						<span id="classname">选择商品类别</span> <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">

						<li><a
							href="${pageContext.request.contextPath }/FindProductAll?currentPage=1&productClass=5YWo6YOo">全部</a></li>
						<li role="presentation" class="divider"></li>

						<%
							for (int i = 0; i < productClass.size(); i++) {
								out.write("<li>");
								out.write("<a href='"+request.getContextPath()+"/FindProductAll?currentPage=1&productClass="
										+ URLcodeUtils.encoder(Base64Utils.encoder(String.valueOf(productClass.get(i)))) + "'>");
								out.write(productClass.get(i) + "</a></li>");
								out.write("<li role='presentation' class='divider'></li>");
							}
						%>



					</ul>
				</div>

			</div>



			<div class="col-md-4 col-md-offset-7">
				<div class="input-group">
					<input type="text" class="form-control"
						placeholder="请输入你要搜索的商品名称关键字" id="likeName" name="likeName">
					<span class="input-group-btn">
						<button class="btn btn-warning" type="button" id="search">搜索商品</button>
					</span>
				</div>

			</div>
			<!-- /.col-lg-6 -->
		</div>
		<div class="row">

			<div class="col-md-12 ">
				<table class="table table-striped table-responsive table-hover"
					id="ProductTable">
					<tr class="text-center warning">
						<th class="text-center">
							<h4>
								<strong>商品展示</strong>
							</h4>
						</th>
						<th class="text-center">
							<h4>
								<strong>商品分类</strong>
							</h4>
						</th>
						<th class="text-center">
							<h4>
								<strong>商品名称</strong>
							</h4>
						</th>
						<th class="text-center">
							<h4>
								<strong>商品数量</strong>
							</h4>
						</th>
						<th class="text-center">
							<h4>
								<strong>商品价格</strong>
							</h4>
						</th>
						<th class="text-center">
							<h4>
								<strong>商品描述</strong>
							</h4>
						</th>
						<th class="text-center">
							<h4>
								<strong>修改</strong>
							</h4>
						</th>
					</tr>
					<c:forEach items="${pageBean.list}" var="item">
						<tr class="text-center" name="productlist">

							<td><img width="180px" height="180px" class="img-rounded"
								src=${item.productImg } /></td>
							<td>
								<h3>
									<strong>${item.productClass}</strong>
								</h3>
							</td>
							<td>
								<h3>
									<strong>${item.productName}</strong>
								</h3>
							</td>
							<td>
								<h3>
									<strong><fmt:formatNumber value="${item.productCount}"
											type="number" pattern="0.00" maxFractionDigits="2" /> </strong>
								</h3>
							</td>
							<td>
								<h3>
									<strong><fmt:formatNumber value="${item.price}"
											type="number" pattern="0.00" maxFractionDigits="2" /></strong>
								</h3>
							</td>
							<td><textarea cols="20" rows="5" readonly
									class="form-control">${item.productMessage}</textarea></td>




							<td class="text-center">
								<div class="btn-group " style="margin: 10% auto auto auto;">
									<button type="button" class="btn btn-info" value="${item.pid}"
										onclick="alterProdcut(this)">修改</button>

									<button type="button" class="btn btn-danger"
										value="${item.pid}" onclick="deleteProdcut(this)">删除</button>


									<c:set var="flag" value="${fn:length(onsalePid)}"></c:set>

									<c:forEach items="${onsalePid}" var="i" begin="0" end="${flag}">

										<c:if test="${i==item.pid}">
											<c:set var="flag" value="-1"></c:set>
											<button type="button" class="btn btn-warning "
												disabled="disabled" value="${item.pid}"
												onclick="publish(this)">已发布</button>
										</c:if>
									</c:forEach>

									<c:if test="${flag!=-1}">
										<button type="button" class="btn btn-success "
											value="${item.pid}" onclick="publish(this)">发布</button>
									</c:if>



								</div>
							</td>
						</tr>

					</c:forEach>



				</table>
			</div>
		</div>
		<div class="row" id="limt">
			<nav style="text-align: center">
			<ul class="pagination">
				<c:if test="${pageBean.currentPage==1}">
					<li class="disabled"><a href="#">&laquo;</a></li>
				</c:if>
				<c:if test="${pageBean.currentPage!=1}">
					<li><a
						href="${pageContext.request.contextPath }/FindProductAll?currentPage=${(pageBean.currentPage-1)<=0?1:(pageBean.currentPage-1)}&productClass=${pageBean.productClass}">&laquo;</a></li>
				</c:if>

				<c:forEach begin="1" end="${pageBean.totalPage }" var="i">
					<c:if test="${i!=pageBean.currentPage}">
						<li><a
							href="${pageContext.request.contextPath }/FindProductAll?currentPage=${i}&productClass=${pageBean.productClass}">${i}</a></li>
					</c:if>
					<c:if test="${i==pageBean.currentPage}">
						<li class=" active "><a href="#">${i}</a></li>
					</c:if>
				</c:forEach>
				<c:if test="${pageBean.currentPage==pageBean.totalPage}">
					<li class="disabled"><a href="#">&raquo;</a></li>
				</c:if>
				<c:if test="${pageBean.currentPage!=pageBean.totalPage}">
					<li><a
						href="${pageContext.request.contextPath }/FindProductAll?currentPage=${(pageBean.currentPage+1)>pageBean.totalPage?pageBean.totalPage:(pageBean.currentPage+1)}&productClass=${pageBean.productClass}">&raquo;</a></li>
				</c:if>

			</ul>
			</nav>
		</div>
	</div>
<!-- websocket -->
<script src="${pageContext.request.contextPath }/js/tone.js" type="text/javascript" ></script>
<script>var uid="${user.id}a6"+new Date().getTime();</script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/MyWEBsocket.js" ></script>
<script >
//连接成功建立的回调方法
websocket.onopen = function () {
   // setMessageInnerHTML("WebSocket连接成功");
  //  send("sid:${cookie.sid.value}");
   
    
}
</script>
<!-- message -->
<link href="${pageContext.request.contextPath }/css/message.css" rel="stylesheet" type="text/css"/>



<div id="winpop">
 <div class="title">短消息！<span class="close" onclick="myclose()">×</span></div>
    <div class="con">有新的订单啦</div>
</div>

</html>