jQuery.validator.addMethod("notblank", function(value, element) {
	       var pwdblank = /^\s*$/;
	       return this.optional(element) ||(value.indexOf(" ")==-1);
	   }, "不可包含空格");

$(function(){
	 $("#changepassword").validate({
			  
			  rules:{
			
				 password:{
					 required:true,
					 rangelength:[6,18],
					 notblank:true
					 },
				 repassword:{
					required:true,
					equalTo:$("#password") 
						 }
			  },
				
				messages:{
				
				 password:{
					 required:"密码不能为空",
					
					 rangelength:"密码不能少于6位且大于18位"
					 },
				 repassword:{
					required:"确认密码不能为空",
					equalTo:"两次密码不一致" 
						 }
					
				},
				 onfocus:true,　　　
           		 onkeyup:false,　　　
           		 focusCleanup:true
			  
			  });
	 
	
	
});