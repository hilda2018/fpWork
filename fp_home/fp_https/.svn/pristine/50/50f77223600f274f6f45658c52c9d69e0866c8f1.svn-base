// JavaScript Document
$(function(){
	/*焦点图*/
	$(".banner1 img").hide();
	$(".banner1 img").eq(0).show();
	$(".banner-n a").eq(0).addClass("banner-arry");
	var n=0;
	var m=$(".banner-n a").length-1;
	function changeImg(){
		n=n+1;
			$(".banner1 img").hide();
			$(".banner1 img").eq(n).show();
			$(".banner-n a").removeClass("banner-arry");
			$(".banner-n a").eq(n).addClass("banner-arry");
			if(n>m)
			{ 
				n=0;
				$(".banner1 img").hide();
				$(".banner1 img").eq(0).show();
				$(".sroll-img li").hide();
				$(".sroll-img li").eq(0).show();
				$(".banner-n a").eq(0).addClass("banner-arry");	
			}
		}
		var time=setInterval(changeImg, 6000)
		$(".banner1").hover(function(){
			clearInterval(time);
			},function(){
				time=setInterval(changeImg,6000);});
				
		$(".banner-n a").click(function(){
			$(".banner-n a").removeClass("banner-arry");
			$(this).addClass("banner-arry");
			n=$(".banner-n a").index(this);
			$(".banner1 img").hide();
			$(".banner1 img").eq(n).show();
			$(".sroll-img li").hide();
			$(".sroll-img li").eq(n).show();
			})
			
	})