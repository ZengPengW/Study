$(function(){
	
	
});
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
		$("#myshoplist").append();
		
	}
}
function subpro(item){
	var count=parseInt($(item).next().text());
	var pid=$(item).attr("pid");
	if(count-1>=0){
		$(item).next().text(--count);
		var tol=parseInt($("#total").text())-1;
		$("#total").text(tol);
		if(tol<=0)$("#total").hide();
		setTimeout(function(){$("#cart").css({"margin-top":"-30%"});}, 0);
		 
		setTimeout(function(){$("#cart").css({"margin-top":"-40%"}); }, 100);
		
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
