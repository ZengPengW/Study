<%@page import="com.zpp.domain.User"%>
<%@page import="com.zpp.utils.CookiesUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta charset="utf-8">
<!--声明文档兼容模式，表示使用IE浏览器的最新模式-->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>取货编号</title>
<link href="${pageContext.request.contextPath }/css/bootstrap.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/showteke.css" type="text/css" />
</head>

<% request.setAttribute("currpage","showpage");
User user=CookiesUtils.getUser(CookiesUtils.getCookie(request.getCookies(), "sid"));
request.setAttribute("user", user);
%>



	<style>
	.bg {
     position:absolute;
      width: 76vw;
    background-size: cover;
     margin-top: -17%;
   height:70vw;
   z-index: -5;
}
h1{
display : inline;
margin-top: 20px;
margin-bottom: 10px;
margin-left: 10px;
float: left;
}
body{
line-height: 3.428571;
}
	</style>
	<body>
		<jsp:include page="/page/head.jsp" flush="true"></jsp:include>
		<div class="container">
			<div class="row">
				
		<div class="panel panel-warning">
  		<div class="panel-heading">
    <h3 class="panel-title">备货完毕请以下编号前来取货</h3>
  </div>
  <div class="panel-body" id="tekeid">
  
  <c:forEach items="${gidlist}" var="ls" >
  <c:set var="rand"><%= (int)(Math.random()*4+1) %></c:set>
   <c:if test="${rand==1}">
  <h1 id="${ls.gid}" ><span class="label label-success">${ls.gid}</span></h1>
  </c:if>
  
  <c:if test="${rand==2}">
  <h1 id="${ls.gid}"><span class="label label-warning">${ls.gid}</span></h1>
  </c:if>
  <c:if test="${rand==3}">
  <h1 id="${ls.gid}"><span class="label label-danger">${ls.gid}</span></h1>
  </c:if>
  <c:if test="${rand==4}">
  <h1 id="${ls.gid}"><span class="label label-info">${ls.gid}</span></h1>
  </c:if>
  <c:if test="${rand==5}">
  <h1 id="${ls.gid}"><span class="label label-primary">${ls.gid}</span></h1>
  </c:if>
  
  </c:forEach>

  
 
  
 
  
  </div>
				
				
				
			</div>
			
			
			
		</div>
		
	</div>	
		
		
		
		
		
		
		
		
		
	<!--  	<div  class="container" style="height: 1000px;margin-top: 1px;">
			<div class="row col-md-12  col-xs-offset-1 col-lg-pull-2      "  >
				
				<div class="row bg" style=" padding:62% 30% 30% 30%;background-image: url(${pageContext.request.contextPath }/imgs/icon/kuan.svg);  background-repeat: no-repeat;">	
					<div class="row">
					<div class="col-lg-4">
						<h1><span class="label label-success">Success</span></h1>
					
					</div>
					<div class="col-lg-4">
							<h1><span class="label label-success">Success</span></h1>
					
					</div>
					<div class="col-lg-4">
							<h1><span class="label label-success">Success</span></h1>
					
					</div>
					</div>
				</div>
			</div>
		
		</div>-->
	

</body>
<!-- websocket -->
<script>var uid="${user.id}a9"+new Date().getTime();</script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/MyWEBsocket.js" ></script>
<script >


function refresh(){
	closeWebSocket();
	window.location.href=window.location.href; 
	
}
setTimeout("refresh()", 300000);
</script>
</html>