
$(function(){

	 generateRequireData(requireData,'boxRequirement',requireCustomData);
})

var requireData=[
		{
			"imgSrc":"/images/profile1.jpg",
			"name":"Jane Doe @JaneDoe",
			"time":"",
			"detail":"Lorem Ipsum Dolor Sit Amet Avet Sis etam Integer pes Elipsum vivamus in amet vivamus in ame in amet..."
		},		{
			"imgSrc":"./images/profile1.jpg",
			"name":"Jane Doe @JaneDoe",
			"time":"",
			"detail":"Lorem Ipsum Dolor Sit Amet Avet Sis etam Integer pes Elipsum vivamus in amet vivamus in ame in amet..."
		},		{
			"imgSrc":"../images/profile1.jpg",
			"name":"Jane Doe @JaneDoe",
			"time":"",
			"detail":"Lorem Ipsum Dolor Sit Amet Avet Sis etam Integer pes Elipsum vivamus in amet vivamus in ame in amet..."
		},		{
			"imgSrc":"../images/profile1.jpg",
			"name":"Jane Doe @JaneDoe",
			"time":"",
			"detail":"Lorem Ipsum Dolor Sit Amet Avet Sis etam Integer pes Elipsum vivamus in amet vivamus in ame in amet..."
		},		{
			"imgSrc":"../images/profile1.jpg",
			"name":"Jane Doe @JaneDoe",
			"time":"",
			"detail":"Lorem Ipsum Dolor Sit Amet Avet Sis etam Integer pes Elipsum vivamus in amet vivamus in ame in amet..."
		},		{
			"imgSrc":"../images/profile1.jpg",
			"name":"Jane Doe @JaneDoe",
			"time":"",
			"detail":"Lorem Ipsum Dolor Sit Amet Avet Sis etam Integer pes Elipsum vivamus in amet vivamus in ame in amet..."
		},		{
			"imgSrc":"../images/profile1.jpg",
			"name":"Jane Doe @JaneDoe",
			"time":"",
			"detail":"Lorem Ipsum Dolor Sit Amet Avet Sis etam Integer pes Elipsum vivamus in amet vivamus in ame in amet..."
		}
	
	];
var requireCustomData = $('#boxRequirement').get(0);
function generateRequireData(data ,id,customData){
	
	var str='';
	var $box=$('#'+id);
	for(var i = 0;i<data.length;i++){
		str+='<li><div class="first"><i><img src='+data[i].imgSrc+'/></i><div class="bubble"><span class="bubbleTriangle"></span><p class="infoPeople"><em> '+data[i].name+'</em><strong>'+data[i].time+'</strong></p><p>'+data[i].detail+'</p></div></div></li>';
	}
	$box.html(str);
	$box.get(0).dataComplete=true;
	if($box.get(0).dataComplete){
	

		customData.memberArr = new Array();
		customData.flag = true;	
		customData.w = $box.find('li').outerWidth(true);
		customData.l = $box.find('li').length;	
		
		workShow(id,'requirementprev','requirementnext','Widget',customData);
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