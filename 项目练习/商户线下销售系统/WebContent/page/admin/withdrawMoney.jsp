<%@page import="com.zpp.domain.User"%>
<%@page import="com.zpp.utils.CookiesUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>提现</title>
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
<script src="${pageContext.request.contextPath }/js/regutils.js"></script>
<script src="${pageContext.request.contextPath }/js/sweetalert2.all.min.js"></script>
<script src="${pageContext.request.contextPath }/js/es6-promise.auto.js" type="text/javascript" ></script>
 <style>
        th{
            text-align:center;
        }
        table{
            text-align:center;
        }
        .fixedhead{
            
            background: white;
        }
    </style>

</head>
<%
request.setAttribute("currpage", "tixian");
String sid=CookiesUtils.getCookie(request.getCookies(), "sid");
User user=CookiesUtils.getUser(sid);
request.setAttribute("user", user);
%>
<body>
<jsp:include page="/page/head.jsp" flush="true"></jsp:include>


<div class="container">
	<div class="row">
		
		<div class="panel panel-success">
  <div class="panel-heading">
   提现面板&nbsp;&nbsp;<small><font color="red"> 注：提现前必须先绑定支付宝，否则将提现失败.(提现只能提现整数金额)</font></small>
  </div>
  <div class="panel-body" >
  	<div class="row">
  		
  		<h3 style="margin-left: 2%">当前余额：<span style="color: red;" id="yver">￥${finance.balance}</span></h3>
  		<div class="col-lg-5 col-md-5 col-sm-5">
  		<div class="input-group" style="margin-top: 20px;">
  <span class="input-group-addon">姓名</span>
  <input type="text" class="form-control" placeholder="姓名需要与支付宝姓名一致否则提现失败" id="curname">
</div>
  		
  		</div>
  		
  	<div class="col-lg-5 col-md-5 col-sm-5">
    <div class="input-group" style="margin-top: 20px;" >
     <input type="number" class="form-control" placeholder="请输入整数的提现金额" id="money" >
      <span class="input-group-btn">
        <button class="btn btn-primary" type="button" onclick="tixian()" >提现</button>
      </span>
      
    </div><!-- /input-group -->
  		
  		
  	
    <span id="tishi" style="display: none; color: red;"></span>
  </div>
  		
  
  	</div>	
  	
  	<h2>提现记录：</h2>
  	

		<table  class="table " style="" >
		<thead>
		<tr>
  		<th>提现金额</th>	
  		<th>收款账号</th>
  		<th>收款人</th>
  		<th>申请时间</th>
  		<th>进度</th>
  		</tr>
		</thead>
	
  	<c:forEach items="${moneylist}" var="ml">
  	<tr>
  		<td>￥<font color="red">${ml.amount }</font></td>
  		<td>${ml.account }</td>
  		<td>${ml.payee }</td>
  		<td>${ml.time }</td>
  		<td>
  		<c:if test="${ml.status==0}"><font color=#1E90FF>提现中...</font></c:if>
  		<c:if test="${ml.status==1}"><font color="limegreen">提现完成</font></c:if>
  		</td>
  		
  	</tr>
  
  	
  	</c:forEach>	
  		
  	</table>
		
		<!-- pages -->
			<div class="row" style="text-align: center">
<ul class="pager" >
<c:if test="${currentPage-1>0}">
 <li><a href="${pageContext.request.contextPath }/WithMoneyWeb?currentPage=${currentPage-1}">上一页</a></li>
</c:if>
<c:if test="${currentPage-1<0}">
 <li class="disabled"><a href="javascript:">上一页</a></li>
</c:if> 
 
 <c:if test="${currentPage+1<=totalPage}">
 <li><a href="${pageContext.request.contextPath }/WithMoneyWeb?currentPage=${currentPage+1}">下一页</a></li>
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
  
  </div>
</div>
		
	</div>
<script>
function jump(){
var page=$("#page").val();
if(page>0&&page<=${totalPage})
	location.href="${pageContext.request.contextPath }/WithMoneyWeb?currentPage="+page+"";

}
</script>

<script>
	function tixian(){
	var money=$("#money").val().trim();
	var curname=$("#curname").val();
	if(isNull(money))	{
		$("#tishi").text("金额不能为空");
		$("#tishi").show();
		return;
	}
	if(isNull(curname)){
		$("#tishi").text("姓名不能为空");
		$("#tishi").show();
		return;
	}
	if(!isNumber(money)){
		$("#tishi").text("请输入正整数金额");
		$("#tishi").show();
		return;
	}
	if(parseInt(money)>parseFloat($("#yver").text().substring(1))&&parseFloat($("#yver").text().substring(1))!=0){
		$("#tishi").text("提现金额不能大于余额或为0");
		$("#tishi").show();
		return;
	}
	
	$.post("${pageContext.request.contextPath }/WithMoney",{money:money,name:curname},function(data){
		if(data=="true"){
			swal({text:"提现成功!等待审核！！！", type:'success'});
			setTimeout(function(){location.href="${pageContext.request.contextPath }/WithMoneyWeb?"+new Date().getTime();}, 2000);
			
		}else{
			swal({text:"未知错误！提现失败!", type:'error'});
		}
		
		
	});
	}
</script>
</body>
</html>