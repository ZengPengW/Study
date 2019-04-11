jQuery.validator.addMethod("notblank", function(value, element) {
	       var pwdblank = /^\s*$/;
	       return this.optional(element) ||(value.indexOf(" ")==-1);
	   }, "不可包含空格");
$(function() {

	$("#base_info").validate({

		rules: {

			shopname: {
				required: true,
				notblank:true
			},
			username: {
				required: true,
				rangelength: [3, 20],
				notblank:true,
				remote: {
					type: "POST",
					url: "/Zpp/CheckAlterUserNameInfo", // 请求地址
					data: {
						username: function() {
							return $("#username").val();
						}
					}

				}

			},
			yanzhengma: {
				required: true,
				rangelength: [6, 6],
				remote: {
					type: "POST",
					url: "/Zpp/CheckMsg", // 请求地址
					data: {
						msg: function() {
							return $("#yanzhengma").val();
						}
					}

				}
			}
		},

		messages: {

			shopname: {
				required: "请输入店铺名称"
			},

			
			username: {
				required: "用户名不能为空",
				rangelength: "用户名不能少于3位且不能大于20位",
				remote: "用户名已存在"
			},
			yanzhengma: {
				required: "请输入验证码",
				rangelength: "验证码错误",
				remote: "验证码错误"
			}

		},
		onfocus: true,
		　　　
		onkeyup: false,
		　　　
		focusCleanup: true

	});
	
	
	$("#shopnamechange").click(function(){
		$("#shopname").removeProp("readonly");
	});
	
	$("#usernamechange").click(function(){
		$("#username").removeProp("readonly");
	});
	
	$("#paychange").click(function(){
		$("#payment").removeProp("readonly");
	});
	
	
});

function getyzm(biaoqian) {

	$.post("/Zpp/GetAlterInfoEmailMsg", function(data, status) {});
	var validCode = true;
			var time = 45;
			if(time == 0) validCode = false;
			if(validCode) {
				validCode = false;

				$(biaoqian).prop("disabled", true);
				var t = setInterval(function() {
					time--;
					$(biaoqian).val(time + "秒");
					if(time <= 0) {
						validCode = false;
						$(biaoqian).val("重新获取");
						$(biaoqian).prop("disabled", false);
						clearInterval(t);

					}
				}, 1000)
			}
		

}
