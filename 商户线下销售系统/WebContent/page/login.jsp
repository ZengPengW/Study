<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<meta name="viewport" content="width=device-width, initial-scale=1">
<title>登陆</title>

<script src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
<link href="${pageContext.request.contextPath }/css/bootstrap.css" rel="stylesheet"/>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>

<link href="${pageContext.request.contextPath }/css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.validate.min.js" ></script>
 <script type="text/javascript" src="${pageContext.request.contextPath }/js/messages_zh.js" ></script>
<style>
	 label.error {
			background: url(${pageContext.request.contextPath }/imgs/icon/unchecked.gif) no-repeat 10px 3px;
			padding-left: 30px;
			font-family: georgia;
			font-size: 15px;
			font-style: normal;
			color: red;
		}
</style>
<script type="text/javascript">
$(function(){
	$("#wjmm").mouseleave(function(){
		$(this).css("color","#333333")
	});
	$("#wjmm").mouseover(function(){
		$(this).css("color","red")
	});
	
	
	$("#msgimg").click(function(){
		$("#msgimg").prop("src","${pageContext.request.contextPath }/CheckImgServlet?"+new Date().getTime());
	});
	
	$("#loginFrom").validate({
		  
		  rules:{
			  
			  username:{
					required:true,
					 } ,
			  password:{
					 required:true
					 },  
			yanzhengma:{
				required:true,
				 remote:{
				 	type:"POST",
	          		url:"${pageContext.request.contextPath }/CheckLoginMsg", // 请求地址
	         	 	data:{
	              	msg:function(){ return $("#yanzhengma").val(); }
	         	 	}

				 }
				}
		  },
			
			messages:{
			
			yanzhengma:{
				required:"请输入验证码",
				 remote:"验证码错误"
				},
			username:{
				required:"用户名不能为空"
				 } ,
			password:{
				 required:"密码不能为空"
				 }
			
			},
		submitHandler:function(form) {
			var username=$("#username").val();
			var password=$("#password").val();
			
			$.post("${pageContext.request.contextPath }/CheckUserIsExis",
					{//参数
						name:username,
						password:password
					},
				function(data,status){
				if(data=="false"){
					$("#message").html("<strong class='col-sm-6' style='color: red'>用户名或密码错误</strong>");	
				}else{
					form.submit();
				}
			});
			
			},
			onclick:false,
			 onfocus:false,　　　
    		 onkeyup:false,　　　
    		 focusCleanup:true ,
    		 onsubmit:true
		  });
	

});



</script>
<style type="text/css">
body{
    background-image: url(${pageContext.request.contextPath }/imgs/icon/sign_bg.png);
}
</style>
</head>
<body style="height: 100%;">
	
<div id="bgcolor" >
	
<div class="container" >
	
<div class="row">
	<div class="col-md-8 hidden-xs hidden-sm">
		<br /><br /><br /><br />
	<!--<img src="${pageContext.request.contextPath }/imgs/icon/login.svg" width="500" height="330" alt="会员登录" title="会员登录">  -->	
	</div>
<div class="col-md-4" id="lgform">
	<br />
	<center>
	<a id="zhdl" href="javascript:void(0)" style="cursor:pointer; text-decoration: none;"><h3 style="display : inline;"><strong><span><font color="red" id="zh1">账户登陆</font></span></strong></h3></a>
	<img  src="${pageContext.request.contextPath }/imgs/icon/shu.svg" width="30" height="30" >
	<a id="smdl" href="javascript:void(0)" style="cursor:pointer; text-decoration: none;"><h3 style="display : inline "><strong><span><font color="black" id="sm1">扫码登陆</font></span></strong></h3></a>
	</center>
	<div class="form-horizontal" id="smlogin" style="display: none;">
		<center><samp><font color="red" style="display: none;" id="loginMess"></font></samp></center>
		<center><img id="sweepimg" src="${pageContext.request.contextPath }/imgs/icon/fail.svg" width="200" height="200" /></center>
		<center><samp><font color="red">请使用已登录账户的浏览器扫码登录</font></samp></center>
	
	</div>
<form role="form" class="form-horizontal" method="post" action="${pageContext.request.contextPath }/LoginServlet" id="loginFrom"  >
					<div class="form-group">
							
							<div class="col-sm-12 col-md-offset-3 col-sm-offset-3 ">
								<center><h5 style=" color: red; " id="message"></h5></center>
							</div>
						</div>

 						<div class="form-group">
							<label for="username" class="col-sm-3 control-label">用户名</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="username" name="username"
									placeholder="请输入用户名/邮箱" style="border-radius: 15px;">
							</div>
						</div>
						
						<div class="form-group">
							<label for="password" class="col-sm-3 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;码</label>
							<div class="col-sm-6">
								<input type="password" class="form-control" id="password" name="password"
									placeholder="请输入密码" autocomplete="off" style="border-radius: 15px;">
							</div>
						</div>
						<div class="form-group">
							<label for="yanzhengma" class="col-sm-3 control-label">验证码</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="yanzhengma" name="yanzhengma"
									placeholder="请输入验证码" style="border-radius: 15px;" autocomplete="off"/>
							</div>
							
							<div class="col-sm-3 col-md-offset-3">
								<img id="msgimg" src="${pageContext.request.contextPath }/CheckImgServlet" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-3 col-sm-10">
								<div class="checkbox">
									<label> <input type="checkbox" name="auto_login" id="auto_login" > 自动登录
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-offset-2 col-sm-10 ">
     							 <button type="submit"  style=" width: 80%;   height: 40px; background-color: red; font-size: 20px; color: white;"  class="btn btn-default">登 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;陆</button>
    						</div>
						</div>
				
						<a href="findPwd.html" id="wjmm" style="color: #666666;">忘记密码</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="register.html"  style="color: red;" ><img src="${pageContext.request.contextPath }/imgs/icon/zc.svg"  style="width: 20px; height: 20px;"/>立即注册</a>
</form>
		
	</div>
</div>
	
	
</div>
</div>
<script>  var loc='${pageContext.request.contextPath }';</script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/sweepLogin.js" ></script>


	


</body>
</html>