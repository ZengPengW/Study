<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>提示</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
		<meta name="viewport" content="width=device-width, initial-scale=1, maxinum-scale=1,user-scalable=no">
		<link href="${pageContext.request.contextPath }/css/bootstrap.css" rel="stylesheet" type="text/css">
		<script src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
		<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
			<div class="row col-md-6 col-lg-offset-3 col-md-offset-3  "  align="center" style="font-weight: 300;">
			<c:if test="${status==0}">
						<img alt="错误" src="${pageContext.request.contextPath }/imgs/icon/fail.svg" class="img-thumbnail" >
			
			
			<h3 class="alert alert-danger" ><font color="red">${message}</font></h3>
			
		
			</c:if>
			<c:if test="${status==1}">
			<img alt="成功" src="${pageContext.request.contextPath }/imgs/icon/success.svg" class="img-thumbnail">
			<h3 class="alert alert-success"><font color="limegreen">${message}</font></h3>
			</c:if>	
			</div>
		
		</div>
		
</body>
<c:if test="${status==1}">

<script >


var ud='${uid}';
var oid='${oid}';
var websocket = null;
var domain2=window.location.host;

//判断当前浏览器是否支持WebSocket
if ('WebSocket' in window) {
	//alert("ws://"+domain2+loc+"/ShowTekeSocket");
    websocket = new WebSocket("ws://"+domain2+"${pageContext.request.contextPath }/ShowTekeSocket");
}
else {
    alert('当前浏览器 Not support websocket, 请手动刷新取货页面')
}

websocket.onerror = function () {
   
};


websocket.onclose = function () {
}


window.onbeforeunload = function () {
    closeWebSocket();
}
websocket.onopen = function () {
	   // setMessageInnerHTML("WebSocket连接成功");
	    send("sid:${cookie.sid.value}");
	   
	    send(oid+"已取货"+ud);
	}

//将消息显示在网页上
function setMessageInnerHTML(innerHTML) {
 
}


function closeWebSocket() {
    websocket.close();
}

function send(mess) {
  websocket.send(mess);
}



websocket.onmessage = function (event) {
 
}






</script>




</c:if>
</html>