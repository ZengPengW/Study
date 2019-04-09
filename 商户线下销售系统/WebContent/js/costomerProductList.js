var srcsub="/Zpp/imgs/icon/sub.svg";
var srcadd="/Zpp/imgs/icon/add.svg";
var myloc="/Zpp";
function addpro(item){
	var count=parseInt($(item).prev().text());
	var sum=parseInt($(item).attr("sum"));
	var pid=parseInt($(item).attr("pid"));
	var temp=count+1
	
	if(temp<=sum){
		$(item).prev().text(temp);
		var tol=parseInt($("#total").text())+1;
		
		$("#total").text(tol);
		$("#total").show();
		
		setTimeout(function(){$("#cart").css({"margin-top":"-30%"});}, 0);
		 
		setTimeout(function(){$("#cart").css({"margin-top":"-40%"}); }, 100);
		var shopname=$(item).attr("shopname");
		var shopprice=parseFloat($(item).attr("shopprice"));
		
		if($("tr[name='"+pid+"']").length){
			$("tr[name='"+pid+"'] td span").text(temp);
		
		}
		else{
		$("#myshoplist").append("<tr name='"+pid+"'><td>"+shopname+"</td><td><strong><font color='red'>￥"+shopprice+"</font></strong></td><td class='text-right'><a href='javascript:' onclick='subpro2(this)' pid='"+pid+"' sum='"+sum+"' shopname='"+shopname+"' shopprice='"+shopprice+"'><img src='"+srcsub+"' class='mysvg'/></a><span >1</span><a href='javascript:' onclick='addpro2(this)' pid='"+pid+"' sum='"+sum+"' shopname='"+shopname+"' shopprice='"+shopprice+"' ><img src='"+srcadd+"' class='mysvg'/></a></td></tr>");
	
		}
		var no =parseFloat($("#paymoney").text().substr(1));
		no=no+shopprice;
		no=no.toFixed(1);
		if(no>0)$("#paybox").show();
		$("#paymoney").text("￥"+no);
		
		
		$.post(myloc+"/ShoppingCart",
		{
			pid:pid,
			count:temp
		},function(data){});
	
	}
}
function subpro(item){
	var count=parseInt($(item).next().text());
	var pid=$(item).attr("pid");
	if(count-1>=0){
		if(count-1==0){
			$("tr[name='"+pid+"']").remove();
		
		}
		$(item).next().text(--count);
		var tol=parseInt($("#total").text())-1;
		$("#total").text(tol);
		if(tol<=0)$("#total").hide();
		setTimeout(function(){$("#cart").css({"margin-top":"-30%"});}, 0);
		 
		setTimeout(function(){$("#cart").css({"margin-top":"-40%"}); }, 100);
		
		var shopname=$(item).attr("shopname");
		var shopprice=parseFloat($(item).attr("shopprice"));
		
		if($("tr[name='"+pid+"']").length){
			$("tr[name='"+pid+"'] td span").text(count);
		
		}
		var no =parseFloat($("#paymoney").text().substr(1));
		no=no-shopprice;
		no=no.toFixed(1);
		if(no<=0)$("#paybox").hide();
		$("#paymoney").text("￥"+no);
		
		
		$.post(myloc+"/ShoppingCart",
		{
			pid:pid,
			count:count
		},function(data){});
	}
}

function addpro2(item){
	var count=parseInt($(item).prev().text());
	var sum=parseInt($(item).attr("sum"));
	var pid=parseInt($(item).attr("pid"));
	var temp=count+1
	
	if(temp<=sum){
		$(item).prev().text(temp);
		var tol=parseInt($("#total").text())+1;
		$("#"+pid).text(temp);
		$("#total").text(tol);
		$("#total").show();
		
		setTimeout(function(){$("#cart").css({"margin-top":"-30%"});}, 0);
		 
		setTimeout(function(){$("#cart").css({"margin-top":"-40%"}); }, 100);
		var shopname=$(item).attr("shopname");
		var shopprice=parseFloat($(item).attr("shopprice"));
		
		var no =parseFloat($("#paymoney").text().substr(1));
		//alert(shopprice+" "+no);
		no=no+shopprice;
		no=no.toFixed(1);
		if(no>0)$("#paybox").show();
		$("#paymoney").text("￥"+no);
		
		$.post(myloc+"/ShoppingCart",
		{
			pid:pid,
			count:temp
		},function(data){});
	
	}
}
function subpro2(item){
	var count=parseInt($(item).next().text());
	var pid=$(item).attr("pid");
	if(count-1>=0){
		if(count-1==0){
			$("tr[name='"+pid+"']").remove();
		
		}
		$(item).next().text(--count);
		$("#"+pid).text(count);
		var tol=parseInt($("#total").text())-1;
		$("#total").text(tol);
		if(tol<=0)$("#total").hide();
		setTimeout(function(){$("#cart").css({"margin-top":"-30%"});}, 0);
		 
		setTimeout(function(){$("#cart").css({"margin-top":"-40%"}); }, 100);
		
		var shopname=$(item).attr("shopname");
		var shopprice=parseFloat($(item).attr("shopprice"));
		var no =parseFloat($("#paymoney").text().substr(1));
		no=no-shopprice;
		no=no.toFixed(1);
		if(no<=0)$("#paybox").hide();
		$("#paymoney").text("￥"+no);
		
		$.post(myloc+"/ShoppingCart",
		{
			pid:pid,
			count:count
		},function(data){});
	}
}


var mark=1;
var ds=null;
var top1=0;
var hg=0;
function showshop(){
	clearInterval(ds);
    hg=$(window).height() ;
	top1=$("#shoping").position().top;
	mark=1-mark;
	if(mark==0)hg=hg/2;
	
	ds = setInterval("myshowshop()", 0.5);
	
	
}
function myshowshop(){
	
	if(mark==0){
		if(top1<=hg){
			clearInterval(ds);
			return;
		}
		top1-=3;
		$("#shoping").css({"top":top1+"px"}); 
		if(top1<=hg){
			clearInterval(ds);
			return;
		}
	}
	else{
		if(top1>=hg){
			clearInterval(ds);
			return;
		}
		top1+=3;
		$("#shoping").css({"top":top1+"px"});
		if(top1>=hg){
			clearInterval(ds);
			return;
		}
	}
	
	
}


$(function(){
	$("#clean").click(function(){
		$("#myshoplist tr").remove();
		$("#paymoney").text("￥0");
		$("#paybox").hide();
		$("#tb span[name='selected']").text(0);
		$("#total").text(0);
		$("#total").hide();
		
		$.post(myloc+"/ShoppingCart",
		{
			pid:1,
			count:1,
			clean:"yes"
		},function(data){});
	
	})
	
});
