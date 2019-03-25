$(function(){
	$("#wjmm").mouseleave(function(){
		$(this).css("color","#333333")
	});
	$("#wjmm").mouseover(function(){
		$(this).css("color","red")
	});
	
	$("#registForm").validate({
			  
			  rules:{
			
				 password:{
					 required:true,
					 minlength:6
					 },
				 repassword:{
					required:true,
					equalTo:$("#password") 
						 },
				 email:{
					  required:true,
					  email:email
					 
					 },	
				 username:{
					 required:true,
					 rangelength:[3,20],
					  minlength:3
					 },	
	 	
			
				yanzhengma:{
					required:true,
					 rangelength:[5,5]
					},
					
				 shopname:{
					 required:true,
				}
			  },
				
				messages:{
				 username:{
					required: "用户名不能为空",
					 minlength:"用户名不能少于3位且不能大于20位"
					 } ,
				 password:{
					 required:"密码不能为空",
					
					 minlength:"密码不能少于6位"
					 },
				 repassword:{
					required:"确认密码不能为空",
					equalTo:"两次密码不一致" 
						 },
				 email:{
					  required:"邮箱不能为空",
					  email:"请输入正确的邮箱"
					 
					 },	
				
				yanzhengma:{
					required:"请输入验证码",
					 rangelength:"验证码错误"
					},
				
				shopname:{required:"请输入商品名称"}
					
				}
				  
			//	focusInvalid:true,
			//当未通过验证的元素获得焦点时，移除错误提示
		//	focusCleanup:true,
			  
			  
			  });
			//获取验证码
     $("#getcheck").click(function  () {
 				var validCode=true;
 				//	alert("的");
                 var time=45;
                  if (time==0)validCode=false;
                  if (validCode) {
                      validCode=false;
                    
					$(this).prop("disabled",true);
                     var t=setInterval(function  () {
                         time--;
                         $("#getcheck").val(time+"秒");
                         if (time<=0) {
                         	  validCode=false;
                         	 $("#getcheck").val("重新获取");
               				 $("#getcheck").prop("disabled",false);
                             clearInterval(t);
                        
                         }
                     },1000)
                 }
                  
              
             });
        
});

function getyzm (biaoqian) {
 				var validCode=true;
 				//	alert("的");
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