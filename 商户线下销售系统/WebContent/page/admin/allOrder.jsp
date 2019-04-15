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
<!--  <script type="text/javascript" src="${pageContext.request.contextPath }/js/sweet-alert.min.js"></script>-->
<!--  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/sweet-alert.css">-->
<link href="${pageContext.request.contextPath }/css/checkDetails.css"  rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath }/js/sweetalert2.all.min.js"></script>
<link href="${pageContext.request.contextPath }/css/checkDetails.css" rel="stylesheet" type="text/css" >
<script src="${pageContext.request.contextPath }/js/es6-promise.auto.js" type="text/javascript" ></script>
<script src="${pageContext.request.contextPath }/js/tone.js" type="text/javascript" ></script>

</head>
<%

User user=CookiesUtils.getUser(CookiesUtils.getCookie(request.getCookies(), "sid"));
request.setAttribute("user", user);
if(request.getParameter("orderClass").equals("5YWo6YOo")){
	request.setAttribute("myclass", "5YWo6YOo6K6i5Y2V");
}


%>
<body>
<jsp:include page="/page/head.jsp" flush="true"></jsp:include>
<div class="container" >
<!-- 搜索 -->
<div class="col-md-4 col-md-offset-8">
				<div class="input-group">
					<input type="text" class="form-control"
						placeholder="手机号/编号/姓名/关键字" id="likeName" name="likeName">
					<span class="input-group-btn">
						<button class="btn btn-warning" type="button" id="search">搜索订单</button>
					</span>
				</div>

			</div>
			
	<div class="row" id="orderlist">
	<c:forEach items="${allOrd}" var="ls">
		<div class="col-lg-4 col-md-6 col-sm-6" name="ding">
			
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
		待取货
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
  	<button class="btn btn-info" oid='${ls.oid}' onclick="checkOrder(this)">查看订单</button>
  		
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
var ud='${user.id}';
function myoption(item){
	var oid=$(item).attr("oid");
	var isOption=$(item).attr("isOption");
	var mess;
	
	if(isOption==1){
		mess="是否确认订单"	;
	}else if(isOption==2){
		mess="是否备货完毕,完毕后可在未取货列表中查看";
	}else{
		mess="是否手动进行取货。（推荐使用扫码取货）";
	}
	
	
	sweetAlert({
		  title: "确认操作",
		  text: mess,
		  type: "warning",
		  showCancelButton: true,
		  confirmButtonColor: "#CD3333",
		  cancelButtonText:"再想想",
		  confirmButtonText: "确认!",
		  closeOnConfirm: true
		}).then(function(result) {
			  if (result.value) {
					$.post("${pageContext.request.contextPath }/OrderOption",{
						   oid:oid,
						   isOption:isOption
						},function(data){
							
						if(data=="true"){
						
						if(isOption==1){
							$(item).text("已确认");
							$(item).removeAttr("onclick");
							
						$(item).prev().children("font:eq(0)").text("待备货");
						}else if(isOption==2){
							 $(item).removeAttr("onclick");
							$(item).text("已备货");
							$(item).prev().text("已确认").addClass("disabled");
							$(item).prev().removeAttr("onclick");
							$(item).prev().prev().children("font:eq(0)").text("待取货");
							send(oid+"已备货"+ud);
						}else{
						$(item).text("已取货");
						$(item).removeAttr("onclick");
						$(item).prev().removeAttr("onclick");
						$(item).prev().prev().removeAttr("onclick");
							$(item).prev().prev().prev().children("font:eq(0)").text("已取货");
							$(item).prev().text("已备货").addClass("disabled");
							$(item).prev().prev().text("已确认").addClass("disabled");
							send(oid+"已取货"+ud);
						}
						
						$(item).addClass("disabled");
						}else if(isOption==3&&data=="false"){
							$(item).removeAttr("onclick");
							$(item).prev().removeAttr("onclick");
							$(item).prev().prev().removeAttr("onclick");
							$(item).text("已取货");
							$(item).prev().prev().prev().children("font:eq(0)").text("已取货");
							$(item).prev().text("已备货").addClass("disabled");
							$(item).prev().prev().text("已确认").addClass("disabled");
							swal("该订单已经取货，请勿重复取货");
							$(item).addClass("disabled");
						}
						
						});  
				  
			  }
			
		
		});

	
}

function checkOrder(item){
var oid=$(item).attr("oid");
var mylist="";
//UserOrderDetails
$.post("${pageContext.request.contextPath }/UserOrderDetails",{oid:oid},function(data){
	
$(data).each(function(index,ls){

mylist=mylist+"<p style='margin-left: -10%;' align='left'> <span style='margin-left: 10%;'> <img src='"+ls.product.productImg+"'/ width='50px' height='50px'></span> <span style='margin-left: 10%;'>"+ls.product.productName+"</span><span style='margin-left: 10%;'> x&nbsp;"+ls.count+"</span><span style='margin-left: 10%;'><font color='red'>￥"+ls.product.price+"</font></span></p>";



});

	 swal({
		  title: "订单详情",
		  
		  html:mylist,
		  
		  confirmButtonColor: "#CD3333",
		  
		  confirmButtonText: "确认!",
		  closeOnConfirm: true
		});


},"json");




}

$("#search").click(function(){
var searchinfo=$("#likeName").val();
$.post("${pageContext.request.contextPath }/OrderSearch",{searchinfo:searchinfo},function(data){

if(data!=false){
$("[name='ding']").remove();
var infomessage="";
$(data).each(function(index,c){
infomessage="<div class='col-lg-4 col-md-6 col-sm-6' name='ding'><div class='panel panel-default'><div class='panel-body'>用户："+c.username+"<span class='col-sm-offset-5' style='right: 10%;position: absolute;'>实付款:<strong  ><font color='red'  >￥"+c.money.toFixed(2)+"</font></strong></span></div><div class='panel-footer'><h5>用户名："+c.username+"</h5><h5>手机:"+c.phone+"</h5><h5>取货编号："+c.gid+"</h5><h5>创建时间：</h5><h6><small>"+c.time+"</small></h6><h6>订单状态:";





if(c.isteke=="0"){
infomessage=infomessage+"<font color='#00E067'>";

if(c.statu=="0")infomessage+="待确认···";
if(c.statu=="1")infomessage+="待备货···";
if(c.statu=="2")infomessage+="待取货···";
infomessage=infomessage+"</font></h6>";

}
if(c.isteke=="1"){
	infomessage=infomessage+"<font color='#FF4A07'>";
	infomessage+="已取货···";
	
	infomessage=infomessage+"</font></h6>";

	}
if(c.isteke=="1"){

infomessage=infomessage+"<button class='btn btn-primary disabled'>已确认</button><button class='btn btn-success disabled'>已备货</button><button class='btn btn-warning disabled'>已取货</button>";

}

if(c.isteke=="0"){
	
	if(c.statu=="0"){
	infomessage=infomessage+"<button class='btn btn-primary ' oid='"+c.oid+"' isOption='1' onclick='myoption(this)'>确认订单</button><button class='btn btn-success ' oid='"+c.oid+"' isOption='2' onclick='myoption(this)'>备货完毕</button>";

	}

	if(c.statu=="1"){
	infomessage=infomessage+"<button class='btn btn-primary disabled'>已确认</button><button class='btn btn-success ' oid='"+c.oid+"' isOption='2' onclick='myoption(this)'>备货完毕</button>";

	}	
	
	if(c.statu=="2"){
		
		infomessage=infomessage+"<button class='btn btn-primary disabled'>已确认</button><button class='btn btn-success disabled'>已备货</button>";
	}
	infomessage=infomessage+"<button class='btn btn-warning' oid='"+c.oid+"' isOption='3' onclick='myoption(this)'>取货</button>";

	
}

infomessage=infomessage+"<button class='btn btn-info' oid='"+c.oid+"' onclick='checkOrder(this)'>查看订单</button></div></div></div>";


$("#orderlist").append(infomessage);

});


}




},"json");




});


var uid="${user.id}a3"+new Date().getTime();

</script>


<!-- websocket -->

<script type="text/javascript" src="${pageContext.request.contextPath }/js/MyWEBsocket.js" ></script>
<script >
//连接成功建立的回调方法
websocket.onopen = function () {
   // alert("WebSocket连接成功");
   // send("sid:${cookie.sid.value}");
   
    
}
</script>
</body>
<!-- message -->
<link href="${pageContext.request.contextPath }/css/message.css" rel="stylesheet" type="text/css"/>



<div id="winpop">
 <div class="title">短消息！<span class="close" onclick="myclose()">×</span></div>
    <div class="con">有新的订单啦</div>
</div>
<script>
function refresh(){
	closeWebSocket();
	window.location.href=window.location.href; 
	
}
setTimeout("refresh()", 300000);
</script>
</html>