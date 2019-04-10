<%@page import="java.util.HashMap"%>
<%@page import="com.zpp.service.SellerServiceImpl"%>
<%@page import="com.zpp.service.SellerService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.zpp.domain.Product"%>
<%@page import="net.sf.json.JsonConfig"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="com.zpp.domain.ShopCart"%>
<%@page import="com.zpp.utils.URLcodeUtils"%>
<%@page import="com.zpp.utils.CookiesUtils"%>
<%@page import="com.zpp.utils.Base64Utils"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>欢迎光临&nbsp;&nbsp; <%=Base64Utils.decoder(CookiesUtils.getCookie(request.getCookies(), "shopname"))%></title>

<!--声明文档兼容模式，表示使用IE浏览器的最新模式-->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
<meta name="viewport"
	content="width=device-width, initial-scale=1, maxinum-scale=1,user-scalable=no">
<link href="${pageContext.request.contextPath }/css/bootstrap.css"
	rel="stylesheet" type="text/css">
<script
	src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath }/js/costomerProductList.js"></script>
<link
	href="${pageContext.request.contextPath }/css/customerProductList.css"
	rel="stylesheet" type="text/css" />
</head>

<body>
	<%
		String cart = CookiesUtils.getCookie(request.getCookies(), "cart");
		List<ShopCart> shopCarts = null;
		if (cart != null) {
			JSONArray jsonArray = JSONArray.fromObject(cart);
			shopCarts = jsonArray.toList(jsonArray, ShopCart.class);
		}
		List<Product> cartList = new ArrayList<Product>();
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		if (shopCarts != null) {
			SellerService service = new SellerServiceImpl();
			int uid = Integer.parseInt(CookiesUtils.getCookie(request.getCookies(), "uid"));
			Product p = null;
			

			int shoppingCount = 0;
			double sumPrice = 0;
			for (ShopCart sp : shopCarts) {
				p = service.GetProductByPid(uid, sp.getPid());
				cartList.add(p);
				hm.put(sp.getPid(), sp.getCount());
				shoppingCount += sp.getCount();
				sumPrice += (p.getPrice() * sp.getCount());
			}
			
			request.setAttribute("cartlist", cartList);
			request.setAttribute("hm", hm);
			request.setAttribute("shoppingCount", shoppingCount);
			request.setAttribute("sumPrice", sumPrice);		
		}
	%>
	<div class="container">
		<div class="row">
			<div class="col-lg-4 col-md-4 col-sm-6"></div>
			<div class="col-lg-5 col-md-5 hidden-xs col-sm-6"></div>
			<div class="col-lg-3 col-md-3 col-sm-12" style="padding-top: 15px;">
				<h4>
					<small>Welcome to <%=Base64Utils.decoder(CookiesUtils.getCookie(request.getCookies(), "shopname"))%></small>
				</h4>
				<h4>
					<img src="/Zpp/imgs/icon/me.svg" witch="20px" height="20px"><%=Base64Utils.decoder(CookiesUtils.getCookie(request.getCookies(), "username"))%>
					<a class="label label-danger" href="${pageContext.request.contextPath }/MyOrderCheck">我的订单</a>
				</h4>
			</div>
		</div>
	</div>

	<!--导航栏-->
	<div class="container" style="margin-top: 10px;">
		<nav class="navbar navbar-inverse navbar-fixed-top ">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
						aria-expanded="false">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand active" href="#">
						<p class="tab-p">商品分类</p>
					</a>
				</div>

				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">

						<li class=""><a
							href="${pageContext.request.contextPath }/OnSaleProductListClient?currentPage=1&productClass=5YWo6YOo">全部
						</a></li>
						<li role='presentation' class='divider'></li>
						<%
							List<Object> productclass = (List<Object>) request.getAttribute("productclass");
							for (int i = 0; i < productclass.size(); i++) {
								out.write("<li>");
								out.write("<a href='" + request.getContextPath()
										+ "/OnSaleProductListClient?currentPage=1&productClass="
										+ URLcodeUtils.encoder(Base64Utils.encoder(String.valueOf(productclass.get(i)))) + "'>");
								out.write(productclass.get(i) + "</a></li>");
								out.write("<li role='presentation' class='divider'></li>");
							}
						%>


					</ul>

				</div>
			</div>
		</nav>
	</div>

	<div class="container">

		<div class="row">
			<table class="table  table-hover" id="tb" style="width: 97%;">

				<c:forEach items="${pageBean.list}" var="pro">
					<tr>
						<td>
							<div class="fooddetails-root_2HoY2">
								<span class="fooddetails-logo_2Q0S7"> <img
									src="${pro.productImg}" class="img-responsive" />
								</span>

							</div>

							<div
								class="row col-md-offset-1  col-sm-offset-1 col-xs-offset-1 ">
								<small>&nbsp;剩:<font color="green">
										${pro.productCount} </font>件
								</small>
							</div>
						</td>
						<td>
							<div class="row" style="margin-top: 5%;">
								<strong>&nbsp;&nbsp;${pro.productName}</strong>
							</div>
							<div class="row">

								<h5 class="fooddetails-desc_3tvBJ">
									<small class="tab-pane">&nbsp;${pro.productMessage}</small>
								</h5>
							</div>
							<div class="row " style="margin-top: 15%;">
								<div style="color: red;">
									￥
									<fmt:formatNumber value="${pro.price}" type="number"
										pattern="0.00" maxFractionDigits="2" />
								</div>

							</div>
							<p></p>
							<div class="row">
								<div>
									<a href="javascript:" onclick="subpro(this)" pid="${pro.pid}"
										sum="${pro.productCount}" shopname="${pro.productName}"
										shopprice="${pro.price}"><img
										src="${pageContext.request.contextPath }/imgs/icon/sub.svg"
										class="mysvg" /></a> <span id="${pro.pid}" name='selected'>${hm.get(pro.pid)!=null?hm.get(pro.pid):0}</span>
									<a href="javascript:" onclick="addpro(this)" pid="${pro.pid}"
										sum="${pro.productCount}" shopname="${pro.productName}"
										shopprice="${pro.price}"><img
										src="${pageContext.request.contextPath }/imgs/icon/add.svg"
										class="mysvg" /></a>

								</div>

							</div>
						</td>
					</tr>

				</c:forEach>


			</table>

			<c:if test="${pageBean.currentPage<pageBean.totalPage}">
				<div class="but1 text-center" style="margin-bottom: 20%;">
					<a href="javascript:" id="more">.......加载更多(${pageBean.currentPage }/
						${pageBean.totalPage }).......</a>
				</div>
			</c:if>
			<br />
		</div>

	</div>
	<script>
		var totalPage = ${pageBean.totalPage};
		var productClass = "${pageBean.productClass}";
		var currentPage = ${pageBean.currentPage};

		$(function() {
			$("#more")
					.click(
							function() {
								currentPage = currentPage + 1;
								$.post("${pageContext.request.contextPath}/OnSaleMore",
												{
													productClass : productClass,
													currentPage : currentPage
												},
												function(data) {
													$(data).each(function(index,c) {
														var str=Mycontains(jsmap,c.pid);
														
																$("#tb").append("<tr><td><div class='fooddetails-root_2HoY2'><span class='fooddetails-logo_2Q0S7'><img src='"+c.productImg+"' /></span></div><div class='row col-md-offset-1  col-sm-offset-1 col-xs-offset-1 '><small>&nbsp;剩:<font color='green'> "
																+ c.productCount
																+ "</font>件</small></div></td><td><div class='row' style='margin-top: 5%;'><strong>&nbsp;&nbsp;"
																+ c.productName
																+ "</strong></div><div class='row'><h5 class='fooddetails-desc_3tvBJ'><small class='tab-pane'>&nbsp;"
																+ c.productMessage
																+ "</small></h5></div><div class='row' style='margin-top: 15%;'><div style='color: red;'>￥"
																+ c.price
																+ "</div></div><p></p><div class='row'><div><a href='javascript:' onclick='subpro(this)' pid='"+c.pid+"' sum='"+c.productCount+"' shopname='"+c.productName+"' shopprice='"+c.price+"' ><img src='${pageContext.request.contextPath}/imgs/icon/sub.svg' class='mysvg' /></a>&nbsp;<span id='"+c.pid+"' name='selected' >"+str+"</span>&nbsp;<a href='javascript:' pid='"
																+ c.pid
																+ "' onclick='addpro(this)'  sum='"+c.productCount+"' shopname='"+c.productName+"' shopprice='"+c.price+"' ><img src='${pageContext.request.contextPath }/imgs/icon/add.svg' class='mysvg'/></a></div></div></td></tr>");
																	});

													if (totalPage > currentPage) {
														$("#more")
																.text(
																		".......加载更多("
																				+ currentPage
																				+ "/ "
																				+ totalPage
																				+ ").......");
													} else {
														$("#more").remove();
													}

												}, "json");
							});

		});
	</script>

	
	<div id="shoping"
		style="width: 99%; height: 43%; background-color: white; position: fixed; top: 100%; overflow: auto;">
		<table class="table" style="position: fixed; width: 98%;">
			<tr bgcolor="#eceff1">
				<th><font color="#666">已选商品</font></th>
				<th colspan="2" class="text-right" id="clean"><img
					src="${pageContext.request.contextPath }/imgs/icon/clean.svg"
					width="20vw" height="20vw" /> <font color="#666">清空</font></th>
			</tr>
		</table>

		<table class="table" style="margin-top: 12%; width: 99%;"
			id="myshoplist">
			<c:forEach items="${cartlist}" var="ls">

				<tr name="${ls.pid}">
					<td>${ls.productName}</td>
					<td><strong><font color='red'>￥<fmt:formatNumber
									value="${ls.price}" type="number" pattern="0.00"
									maxFractionDigits="2" /></font></strong></td>
					<td class='text-right'><a href='javascript:'
						onclick='subpro2(this)' pid='${ls.pid}' sum='${ls.productCount}'
						shopname='${ls.productName}'
						shopprice='<fmt:formatNumber value="${ls.price}" type="number" pattern="0.00" maxFractionDigits="2" />'><img
							src='${pageContext.request.contextPath}/imgs/icon/sub.svg'
							class='mysvg' /></a><span>${hm.get(ls.pid)}</span><a
						href='javascript:' onclick='addpro2(this)' pid='${ls.pid}'
						sum='${ls.productCount}' shopname='${ls.productName}'
						shopprice='<fmt:formatNumber value="${ls.price}" type="number" pattern="0.00" maxFractionDigits="2" />'><img
							src='${pageContext.request.contextPath}/imgs/icon/add.svg'
							class='mysvg' /></a></td>
				</tr>


			</c:forEach>


		</table>
	</div>

	<div class="footer">

		<div class="fa-shopping-cart" onclick="showshop()">
			<img alt="购物车"
				src="${pageContext.request.contextPath}/imgs/icon/ShoppingCart.svg"
				id="cart">
		</div>

		<div onclick="showshop()">
			<!--记录订单总数  -->
			<c:if test="${shoppingCount>0}">
				<div class="total" id="total">${shoppingCount}</div>
			</c:if>
			<c:if test="${empty shoppingCount ||shoppingCount<=0}">
				<div class="total" id="total" style="display: none;">0</div>
			</c:if>

		</div>

		<c:if test="${sumPrice>0}">
			<div id="paybox"
				style="position: absolute; left: 27%; font-size: 2rem; color: white; ">
				<span id="paymoney">￥<fmt:formatNumber value="${sumPrice}"
						type="number" pattern="0.00" maxFractionDigits="2" /></span>
			</div>
		</c:if>


		<c:if test="${empty sumPrice||sumPrice<=0}">
			<div id="paybox"
				style="position: absolute; left: 27%; font-size: 2rem; color: white; display: none;">
				<span id="paymoney">￥0</span>
			</div>
		</c:if>




		<div class="settlement" id="settlement">去结算</div>
	</div>

	<script>
	var jsmap=new Array();
		$("#settlement").click(
			function() {
				window.location.href = "${pageContext.request.contextPath}/page/customer/order.jsp";
			});
		
	</script>
	<c:forEach items="${hm}" var="item">
	<script type="text/javascript">
	jsmap.push("${item.key}:${item.value}");
	</script>
	</c:forEach>
	<script type="text/javascript">
	function Mycontains(arr,obj){
		var i = arr.length;
		var temp=null;
		
	    while (i--) {
	    	temp=parseInt(arr[i].indexOf(":"));
	    	
	        if (arr[i].substr(0,temp) == obj) {
	            return arr[i].substr(temp+1);
	        }
	    }
	    return 0;
	}
	
	
	</script>
</body>


</html>