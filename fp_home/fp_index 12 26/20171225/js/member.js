
$(function(){
	 generateWorkData( memberdata,'button_items',memberCustomData);
})
var memberdata=[
		{
			"imgSrc":"images/association.png",
			"name":"3F,Building 1,No.1688 Yongnan RD,Shanghai,China"
		},
			{
			"imgSrc":"images/association.png",
			"name":"3F,Building 1,No.1688 Yongnan RD,Shanghai,China"
		},
			{
			"imgSrc":"images/association.png",
			"name":"3F,Building 1,No.1688 Yongnan RD,Shanghai,China"
		},
			{
			"imgSrc":"images/association.png",
			"name":"3F,Building 1,No.1688 Yongnan RD,Shanghai,China"
		},
			{
			"imgSrc":"images/association.png",
			"name":"3F,Building 1,No.1688 Yongnan RD,Shanghai,China"
		},
			{
			"imgSrc":"images/association.png",
			"name":"3F,Building 1,No.1688 Yongnan RD,Shanghai,China"
		},
			{
			"imgSrc":"images/association.png",
			"name":"3F,Building 1,No.1688 Yongnan RD,Shanghai,China"
		},
			{
			"imgSrc":"images/association.png",
			"name":"3F,Building 1,No.1688 Yongnan RD,Shanghai,China"
		},
			{
			"imgSrc":"images/association.png",
			"name":"3F,Building 1,No.1688 Yongnan RD,Shanghai,China"
		},
			{
			"imgSrc":"images/association.png",
			"name":"3F,Building 1,No.1688 Yongnan RD,Shanghai,China"
		},
			{
			"imgSrc":"images/association.png",
			"name":"3F,Building 1,No.1688 Yongnan RD,Shanghai,China"
		},
			{
			"imgSrc":"images/association.png",
			"name":"3F,Building 1,No.1688 Yongnan RD,Shanghai,China"
		},
			{
			"imgSrc":"images/association.png",
			"name":"3F,Building 1,No.1688 Yongnan RD,Shanghai,China"
		},
			{
			"imgSrc":"images/association.png",
			"name":"3F,Building 1,No.1688 Yongnan RD,Shanghai,China"
		}
	];
var memberCustomData = $('#button_items').get(0);


function generateWorkData(data ,id,customData){
	var str='';
	var $box=$('#'+id);
	for(var i = 0;i<data.length;i++){
		str+='<li class="button_item"><div class="shadow"><div class="button_itemsImg"><img src='+data[i].imgSrc+'/><span></span></div><p>'+data[i].name+'</p><a class="btnMember"><span>Read More </span><svg><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#svg_arrow"></use></svg></a></div></li>'
	}
	$box.html(str);
	$box.get(0).dataComplete=true;
	if($box.get(0).dataComplete){
		customData.memberArr = new Array();
		customData.flag = true;	
		customData.w = $box.find('li').outerWidth(true);
		customData.l = $box.find('li').length;	
		
		workShow(id,'memberBtnPrev','memberBtnNext','wrapMenberShow',customData);
	}
}


function workShow (id,prev,next,parent,customData) {

	var $box=$('#'+id);

	$box.html($box.html() + $box.html());
	$box.css({
		width: (2 * customData.l * customData.w) + 'px'
	});
	auto(customData);
	$('#'+prev).on('click', function() {	clearInterval(customData.autoRollTimer);	
		customData.flag ? prevEvent(customData) : "";
	});
	$('#'+next).on('click', function() {
			clearInterval(customData.autoRollTimer);	
		customData.flag ? nextEvent(customData) : "";
	});
	$('#'+parent).hover(function() {	
		clearInterval(customData.autoRollTimer);	
	},function() {
		auto(customData)
	});
	

}
function auto(customData) {
	var $box=$(customData);
	customData.autoRollTimer = setInterval(function() {
		$box.animate({
			marginLeft: - customData.w
		}, 1000, function() {
			$box.find('li').slice(0, 1).appendTo($box);
			$box.css({
				marginLeft: 0
			})
		})
	}, 5000)

}
function nextEvent(customData) {
	var $box=$(customData);
	customData.flag = false;
	$box.animate({
		marginLeft: -customData.w
	}, 1000, function() {
		$box.find('li').slice(0, 1).appendTo($box);
		$box.css({
			marginLeft: 0
		});
		customData.flag = true
	})
}
function prevEvent(customData) {
	var $box=$(customData);
	customData.flag = false;
	customData.memberArr = $box.find('li').slice(-1);
	for (var i = 0; i < 1; i++) {
		$(customData.memberArr[i]).css({
			marginLeft: -customData.w * (i + 1)
		}).prependTo($box)
	}
	$box.animate({
		marginLeft: customData.w
	}, 1000, function() {
		$box.find('li').removeAttr('style');
		$box.css({
			marginLeft: 0
		});
		customData.flag = true
	})
}