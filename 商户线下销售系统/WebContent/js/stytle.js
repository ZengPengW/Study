$(function(){
	
	
	$("#registForm").validate({
			  
			  rules:{
			
				 password:{
					 required:true,
					 rangelength:[6,18],
					 },
				 repassword:{
					required:true,
					equalTo:$("#password") 
						 },
				 email:{
					  required:true,
					  email:email,
					  remote:{
						 	type:"POST",
	                    	url:"../CheckUserEmail", // 请求地址
	                   	 	data:{
	                   	 	email:function(){ return $("#email").val(); }
	                   	 	}

						 }
					 },	
				 username:{
					 required:true,
					 rangelength:[3,20],
					 remote:{
						 	type:"POST",
	                    	url:"../CheckUserName", // 请求地址
	                   	 	data:{
	                   	 		username:function(){ return $("#username").val(); }
	                   	 	}

						 }
					  
					 },	
	 	
			
				yanzhengma:{
					required:true,
					 rangelength:[6,6],
					 remote:{
					 	type:"POST",
                    	url:"../CheckMsg", // 请求地址
                   	 	data:{
                        	msg:function(){ return $("#yanzhengma").val(); }
                   	 	}

					 }
					},
					
				 shopname:{
					 required:true,
				}
			  },
				
				messages:{
				 username:{
					required: "用户名不能为空",
					rangelength:"用户名不能少于3位且不能大于20位",
					remote:"用户名已存在"
					 } ,
				 password:{
					 required:"密码不能为空",
					
					 rangelength:"密码不能少于6位且大于18位"
					 },
				 repassword:{
					required:"确认密码不能为空",
					equalTo:"两次密码不一致" 
						 },
				 email:{
					  required:"邮箱不能为空",
					  email:"请输入正确的邮箱",
					 remote:"该邮箱已经注册过了"
					 },	
				
				yanzhengma:{
					required:"请输入验证码",
					 rangelength:"验证码错误",
					 remote:"验证码错误"
					},
				
				shopname:{required:"请输入店铺名称"}
					
				},
				 onfocus:true,　　　
           		 onkeyup:false,　　　
           		 focusCleanup:true
					  
// focusInvalid:true,
// 当未通过验证的元素获得焦点时，移除错误提示
// focusCleanup:true,
			  
			  
			  });
	
	

 
        
});

function getyzm (biaoqian) {
	// alert(checkEmail($("#email").val()));
	if(checkEmail($("#email").val())){
		
		var emailid=$("#email").val();
		$.post("../CheckUserEmail",{email:emailid},function(data,status){
			//alert(data);
			if(data=="true"){
				$.post("../GetCode",{email:emailid},function(data,status){});
				var validCode=true;
		 				// alert("的");
		                 var time=45;
		                  if (time==0)validCode=false;
		                  if (validCode) {
		                      validCode=false;
		                    
							$(biaoqian).prop("disabled",true);
		                     var t=setInterval(function  () {
		                         time--;
		                         $(biaoqian).val(time+"秒");
		                         if (time<=0) {
		                         	  validCode=false;
		                         	 $(biaoqian).val("重新获取");
		               				 $(biaoqian).prop("disabled",false);
		                             clearInterval(t);
		                        
		                         }
		                     },1000)
		                 }
				
			}
		});
	
                  
	}else{
		alert("请输入正确的邮箱");
	}
 				
              
   }

function getyzmZHMM (biaoqian) {
	if(checkEmail($("#email").val())){
		
		var emailid=$("#email").val();
		
				$.post("../GetCode",{email:emailid},function(data,status){});
				var validCode=true;
		 				
		                 var time=45;
		                  if (time==0)validCode=false;
		                  if (validCode) {
		                      validCode=false;
		                    
							$(biaoqian).prop("disabled",true);
		                     var t=setInterval(function  () {
		                         time--;
		                         $(biaoqian).val(time+"秒");
		                         if (time<=0) {
		                         	  validCode=false;
		                         	 $(biaoqian).val("重新获取");
		               				 $(biaoqian).prop("disabled",false);
		                             clearInterval(t);
		                        
		                         }
		                     },1000)
		                 }
	
                  
	}else{
		alert("请输入正确的邮箱");
	}
 				
              
   }