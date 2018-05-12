	//椤甸潰婊氬姩浜嬩欢
	var scrollTop;
	window.onscroll=isToTop;
	function isToTop(){
		if((document.body.scrollTop+document.documentElement.scrollTop)==0){
			$("#ad").fadeOut("normal",function(){
				
			});
		}else{
			$("#ad").fadeIn("normal",function(){
				
			});
		};
	}
	//鍒囨崲缃《鍥剧墖榧犳爣绉昏繎
	function imgOver(){
		$("#returnImage").attr("src","././library/img/returnTop2.png");
	}
	//鍒囨崲缃《鍥剧墖榧犳爣绉昏蛋
	function imgOut(){
		$("#returnImage").attr("src","././library/img/returnTop1.png");
	}
	//椤甸潰缃《婊氬姩鏁堟灉
	var scrollDelay;
	function pageScroll(){
		//document.documentElement.scrollTop鍜宒ocument.body.scrollTop鏈変竴涓殑鍊兼亽涓�锛屽洜涓轰竴涓湁鏁堟椂鍙﹀涓�釜灏变负0銆�
		
		var sTop=document.documentElement.scrollTop+document.body.scrollTop;
		if(sTop<1001){
			window.scrollBy(0,-15);
		}else if(sTop>1000&&sTop<2001){
			window.scrollBy(0,-25);
		}else if(sTop>2000&&sTop<3001){
			window.scrollBy(0,-35);
		}else if(sTop>3000&&sTop<4001){
			window.scrollBy(0,-45);
		}else if(sTop>4000&&sTop<5001){
			window.scrollBy(0,-55);
		}else{
			window.scrollBy(0,-100);
		}
		
		//涓�绉掑悗閲嶅鎵ц姝ゅ嚱鏁帮紝鐩村埌婊氬姩鏉″埌椤躲�
		scrollDelay = setTimeout('pageScroll()',1);
		if(sTop==0){
			clearTimeout(scrollDelay);
			window.onscroll=isToTop;
		}
	}
	
	//缁檚crollTop鍥剧墖缁戝畾鍗曞嚮浜嬩欢澶勭悊鍑芥暟
	$(document).ready(function(){
		$("#returnImage").click(function(){
			$("#ad").fadeOut("normal");
			window.onscroll=null;
			pageScroll();
			//$('#returnImage').attr('src','returnTop1.png');
		});
	});
	
