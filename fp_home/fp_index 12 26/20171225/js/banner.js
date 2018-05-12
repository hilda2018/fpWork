
	$(function(){
		//轮播图
		 picShow(picInfo);	
		
	});
	var picInfo=[{
		"img":"images/bg1.jpg",
		"title":"Post selling  and  buying Information",
		"url":"",
		"detail":"Attract new business conversation",
		"buttonInfo":"Read More"
	},{
		"img":"images/bg2.jpg",
		"title":"Post selling  and  buying Information",
		"url":"",
		"detail":"Attract new business conversation",
		"buttonInfo":"Read More"
	},{
		"img":"images/bg3.jpg",
		"title":"Post selling  and  buying Information",
		"url":"",
		"detail":"Attract new business conversation",
		"buttonInfo":"Read More"
	}];
				
			
	
	function picShow(picInfo) {
		if(!picInfo) {
			return false;
		}
		genelatePic(picInfo);
	}

	
	function genelatePic(picInfo){
		var str1='',str2='';
		var $picShow = $('#picShow');
		var $smallNav = $('#smallNav');
		var len=picInfo.length;
		var num = -1;
		var time = 0;
				
		$picShow.add($smallNav).html('');
		
		for(var i=0;i<len;i++){
			str1 += '<li class="picLi" style="background:url('+picInfo[i].img+') no-repeat center center;backgroud-size:cover;"><div class="wrap"><div class="title"><h2>'+picInfo[i].title +'</h2><hr class="line"/><div class="moreDetail">';
			str1 += '<a class="smalltext" href='+picInfo[i].url +'><span class="pictext2">'+picInfo[i].detail +'</span><span class="picArrow"><em>'+picInfo[i].buttonInfo +'</em><img src="images/arrow.png"/></span></a></div></div></div></li>';
			if(i==0){
				 str2 +='<li class="bg"></li>';
			}else{
				 str2 +='<li></li>';
			}
		}
		
		$picShow.html(str1);
		$smallNav.html(str2);
		scrollPic(num,len);
		
		$('#boxPrev').click(function(){
			clearInterval(time);
			var index = $("#smallNav .bg").index();
			if(index==0){
				index = len-1;
			}else{
				index =index-1;
			}
			changePic(index);
		});
		$('#boxNext').click(function(){
			clearInterval(time);
			var index = $("#smallNav .bg").index();
			index= (index+1)% len;
			changePic(index);	
		});
	}
			
			
	function scrollPic(num,len){
			
				junmper(num,len);
				time=setInterval("junmper()",3700);

				$("#smallNav li").each(function(i,elem){
					
					$('#picShow li').eq(i).attr("id","pic"+i);
					$(elem).click(function(){
						var index = $(this).index();
						changePic(index );
					});
				});

					
				$("#smallNav li").hover(function(){
					clearInterval(time);
				},function(){
					time=setInterval("junmper()",3700);
				})
	}
			
			
	function junmper(num,len) {
		num++;
		if(num > len) {
			num = 0;
		}

		changePic(num);

	}

	function changePic(index) {

				var $target = $(".nav ul li").eq(index);
				var $target2 = $(".pic ul li").eq(index);
				$target.addClass("bg").siblings().removeClass("bg");/*
				$target2.fadeIn().siblings().fadeOut();
		
				if( $target2.attr('custom')){//是否自定义
					if($target2.find(".title").length==0){
						return false;
					}
						
						
					var customLeft=$target2.attr('customLeft')
					$target2.find(".title").animate({
						display: "none",
						left: "-1980px"
					});
				
					
					if($target2.find(".img2").length!=0){
						$target2.find(".img2").animate({
							display: "none",
							left: "-15px"
						});
					}
						
					$target2.find(".title").animate({
						left: customLeft1
					}, 500, function() {
						
						if($target2.find(".img2").length==0){
							return false;
						}
						
					
						var customLeft2=$target2.attr('customLeft2');
						var marginLeft=$target2.attr('marginLeft');
						$target2.find(".img2").css({display:"block"}).animate({left: customLeft1,"marginLeft": marginLeft},500);
						
					});*/
					
				
				}
				
				


			}