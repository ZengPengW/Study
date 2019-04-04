$(function() {
	$("#welcome").validate({

		rules: {

			username: {
				required: true
			},
			phone: {
				required: true,
				isMobile: true
			}
		},

		messages: {

			username: {
				required: "名字不能为空 &",

			},
			phone: {
				required: "手机不能为空",
				isMobile: "手机号码错误"
			}

		},
		onfocus: true,
		　　　
		onkeyup: false,
		　　　
		focusCleanup: true,
		errorPlacement: function(error, element) {
			if(element.parent().hasClass("input-group")) {
				element.parent().after(error);
			} else
				element.after(error)
		},
		errorPlacement: function(error, element) {
			error.appendTo(element.parent().parent());
		},

	});

});
jQuery.validator.addMethod("isMobile", function(value, element) {
	var length = value.length;
	var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
	return this.optional(element) || (length == 11 && mobile.test(value));
}, "请正确填写您的手机号码");