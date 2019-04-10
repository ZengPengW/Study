<%@page import="com.zpp.utils.CookiesUtils"%>
<%@page import="com.zpp.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta charset="utf-8">
<!--声明文档兼容模式，表示使用IE浏览器的最新模式-->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>全部订单</title>
<link href="${pageContext.request.contextPath }/css/bootstrap.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>

</head>
<%
	User user=CookiesUtils.getUser(CookiesUtils.getCookie(request.getCookies(), "sid"));
	request.setAttribute("user", user);
%>
<%
request.setAttribute("currpage", "allOrder");
%>
<body>
<jsp:include page="/page/head.jsp" flush="true"></jsp:include>
<div class="container">
	<div class="row">
	<c:forEach items="${allOrd}" var="ls">
		<div class="col-lg-4 col-md-6 col-sm-6">
			
	<div class="panel panel-default">
  	<div class="panel-body">
   	 用户：${ls.username}
   	 	<span class="col-sm-offset-5" style="right: 10%;position: absolute;">实付款:<strong  ><font color="red"  >￥<fmt:formatNumber value="${ls.money}"
						type="number" pattern="0.00" maxFractionDigits="2" /></font></strong></span>
  	</div>
  	<div class="panel-footer">
  	<h5>用户名：${ls.username}</h5>
		<h5>手机:${ls.phone}</h5>
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
	
	<c:if test="${ls.isteke==1}">
	<button class="btn btn-primary disabled">已确认</button>
 	 <button class="btn btn-success disabled">已备货</button>
  <button class="btn btn-warning disabled">已取货</button>
	</c:if>	
 <c:if test="${ls.isteke==0}">
  <c:if test="${ls.statu==0}">
	<button class="btn btn-primary " oid='${ls.oid}' isOption="1" onclick="myoption(this)">确认订单</button>
	<button class="btn btn-success " oid='${ls.oid}' isOption="2" onclick="myoption(this)">备货完毕</button>
	</c:if>
 <c:if test="${ls.statu==1}">
	<button class="btn btn-primary disabled">已确认</button>
	<button class="btn btn-success " oid='${ls.oid}' isOption="2" onclick="myoption(this)">备货完毕</button>
	</c:if>
	<c:if test="${ls.statu==2}">
	<button class="btn btn-primary disabled">已确认</button>
 	 <button class="btn btn-success disabled">已备货</button>
	</c:if>
 	 
  	<button class="btn btn-warning" oid='${ls.oid}' isOption="3" onclick="myoption(this)">取货</button>
	</c:if>	
  	<button class="btn btn-info">查看订单</button>
  		
  	</div>
	</div>
	
	
	</div>
	
	</c:forEach>

		
		<!--  -->
		
	</div>
	<div class="row" style="text-align: center">
<ul class="pager" >
<c:if test="${currentPage-1>0}">
 <li><a href="${pageContext.request.contextPath }/AllOrderCheck?orderClass=${orderClass}&currentPage=${currentPage-1}">上一页</a></li>
</c:if>
<c:if test="${currentPage-1<0}">
 <li class="disabled"><a href="javascript:">上一页</a></li>
</c:if> 
 
 <c:if test="${currentPage+1<=totalPage}">
 <li><a href="${pageContext.request.contextPath }/AllOrderCheck?orderClass=${orderClass}&currentPage=${currentPage+1}">下一页</a></li>
</c:if>
<c:if test="${currentPage>totalPage}">
 <li class="disabled"><a href="javascript:">下一页</a></li>
</c:if> 
  
  <li> 
  <input type="number" id='page' placeholder="页码" style=" height:34px ; width: 40px;" value="">
  <button class="btn  btn-primary" onclick="jump()">跳转</button>
  共${currentPage}/${totalPage}页
  </li>
</ul>
	
	
	</div>

		
	
	
	
</div>
<script>
function jump(){
var page=$("#page").val();
if(page>0&&page<=${totalPage})
	location.href="${pageContext.request.contextPath }/AllOrderCheck?orderClass=${orderClass}&currentPage="+page+"";

}
function myoption(item){
	var oid=$(item).attr("oid");
	var isOption=$(item).attr("isOption");
	$.post("",{
	   oid:oid,
	   isOption:isOption
	},function(data){
	if(data=="true"){
	
	if(isOption==1){
		$(item).text("已确认");		
	}else if(isOption==2){
		$(item).text("已备货");
	}else{
	$(item).text("已取货");
	}
	
	$(item).addClass("disabled");
	}
		
	});
	
}
</script>
</body>
</html>