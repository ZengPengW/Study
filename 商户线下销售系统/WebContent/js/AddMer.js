$(function() {
	$("#productImg").change(function() {
		
		var filePath = $(this).val();
		var fileformat = filePath.substring(filePath.lastIndexOf(".")).toLowerCase();
		if(!fileformat.match(/.png|.jpg|jpeg|.gif/)) {
			alert('上传错误,文件格式必须为：png/jpg/jpeg/gif');
			return;
		}
		
		if(((this.files[0].size).toFixed(2))>=(1024*1024*3)){
   
	          alert("请上传小于3M的图片");
	          $("#productImg").val("");
	          $("#location").val("");
	          return;
	        }
		
		var src = window.URL.createObjectURL(this.files[0]);
		$("#spimg").prop("src", src);

	});

	$("#AddMerchandise").validate({

		rules: {

			productName: {
				required: true,
				remote: {
					type: "POST",
					url: "/Zpp/CheckProductName", // 请求地址
					data: {
						productName: function() {
							return $("#productName").val();
						}
					}

				}
			},
			productClass: {
				required: true
			},
			price: {
				required: true,
				number: true,
				min: 0.01,
				max: 2000000000
			},
			productCount: {
				required: true,
				digits: true,
				min: 0,
				max: 2000000000
			},
			productImg: {
				required: true
				
			},

			productMessage: {
				required: true,
			}
		},

		messages: {
			productName: {
				required: "请输入商品名称",
				remote: "该商品已存在"
			},
			productClass: {
				required: "请输入分类名称",
			},
			price: {
				required: "请输入商品价格",
				number: "请输入正确到价格",
				min: "价格不能小于0.01",
				max: "价格越界了，你个黑心商家"

			},
			productCount: {
				required: "请输入商品数量",
				digits: "请输入整数数量",
				min: "商品数量最小为0",
				max: "写那么多货干嘛你有吗？"
			},
			productImg: {
				required: "请选择商品图片",
				
			},

			productMessage: {
				required: "商品信息不能为空"
			}

		},
		submitHandler: function(form) {
			var filePath = $("#productImg").val();
			var fileformat = filePath.substring(filePath.lastIndexOf(".")).toLowerCase();
			
			
			if(!fileformat.match(/.png|.jpg|jpeg|.gif|.svg/)) {
				alert('上传错误,文件格式必须为：.png|.jpg|.jpeg|.gif|.svg');
			} else {
				form.submit();
			}
		},
		onfocus: true,
		　　　
		onkeyup: false,
		　　　
		focusCleanup: true

	});
});

