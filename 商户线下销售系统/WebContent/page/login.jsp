<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<!--设置视口的宽度(值为设备的理想宽度)，页面初始缩放值<理想宽度/可见宽度>-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>登陆</title>

<script src="../js/jquery-1.11.3.min.js"></script>
<link href="../css/bootstrap.css" rel="stylesheet"/>
<script src="../js/bootstrap.min.js"></script>
<!--  <script src="../js/stytle.js"></script>-->
<link href="../css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="../js/jquery.validate.min.js" ></script>
 <script type="text/javascript" src="../js/messages_zh.js" ></script>
<style>
	 label.error {
			background: url(../imgs/icon/unchecked.gif) no-repeat 10px 3px;
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
		$("#msgimg").prop("src","/Zpp/CheckImgServlet?"+new Date().getTime());
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
	          		url:"/Zpp/CheckLoginMsg", // 请求地址
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
			
			$.post("/Zpp/CheckUserIsExis",
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
</head>
<body style="height: 100%;">
	
<div id="bgcolor" >
	
<div class="container" >
	
<div class="row">
	<div class="col-md-8 hidden-xs hidden-sm">
		<br /><br /><br /><br />
		<img src="../imgs/icon/login.svg" width="500" height="330" alt="会员登录" title="会员登录">
	</div>
<div class="col-md-4" id="lgform">
	<br />
	<center><h3 style=" color: red; "><strong>账户登陆</strong></h3></center>
	
<form role="form" class="form-horizontal" method="post" action="/Zpp/LoginServlet" id="loginFrom"  >
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
									placeholder="请输入密码" style="border-radius: 15px;">
							</div>
						</div>
						<div class="form-group">
							<label for="yanzhengma" class="col-sm-3 control-label">验证码</label>
							<div class="col-sm-5">
								<input type="text" class="form-control" id="yanzhengma" name="yanzhengma"
									placeholder="请输入验证码" style="border-radius: 15px;">
							</div>
							
							<div class="col-sm-3 col-md-offset-3">
								<img id="msgimg" src="/Zpp/CheckImgServlet" />
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
						<a href="register.html"  style="color: red;" ><img src="../imgs/icon/zc.svg"  style="width: 20px; height: 20px;"/>立即注册</a>
</form>
		
	</div>
</div>
	
	
</div>
</div>


</body>
</html>