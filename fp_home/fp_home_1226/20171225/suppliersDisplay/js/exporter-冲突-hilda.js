
var company = {
	"logoImageUrl": "./images/1.jpg",
	"companyName": "SOCIEDAD SAN FRANCISCO LO GARCES LTDA.22",
	"location": "I am a location I am a locationI am a location",
	"profile":[{"name":"Picture album download 1","link":"https://wenku.baidu.com/view/c15780c70b1c59eef9c7b41a.html?from=search"},
				{"name":"Picture album download 2","link":"https://wenku.baidu.com/view/c15780c70b1c59eef9c7b41a.html?from=search"},
				{"name":"Picture album download 3","link":"https://wenku.baidu.com/view/c15780c70b1c59eef9c7b41a.html?from=search"}],
	"introduct_item": [{
			"title": "Stone fruits Stone fruits Sne fruits22"
		}, 
		{
			"title": "Stone fruits Stone fruits Sne fruits"
		},
		{
			"title": "Stone fruits Stone fruits Sne fruits"
		},
		{
			"title": "Stone fruits Stone fruits Sne fruits"
		}
	],
	"info": "Abcd company is one of the leading fruit trading companAbcd company is oneeeeeee of the leading fruit trading companAbcd company is one of the leading fruit trading companAbcd company is one of the leading fruit trading companAbcd company is one of the leading fruit trading companAbcd company is one of the leading fruit trading companAbcd company is one of the leading fruit trading companAbcd company is one of the leading fruit trading companAbcd company is one of the leading fruit trading companAbcd company is one of the leading fruit trading companAbcd company is one of the leading fruit trading companAbcd company is one of the leading fruit trading compan"
}

var arrImg = ["images/gd1.jpg",
	"images/2.png",
	"images/1.jpg"
];

var teamData = [{
		"url": "images/1.jpg",
		"position": "Sales Manager Sales Manager Sales Manager Sales Manager",
		"name": "zhangjiajia",
		"email": "2232989977@qq.com",
		"tel": "13729038395",
		"language": "English"
	},
	{
		"url": "images/logo.png",
		"position": "Sales Manager",
		"name": "zhangjiajia",
		"email": "2232989977@qq.com",
		"tel": "13729038395",
		"language": "English"
	},
	{

		"url": "images/errorTeam.jpg",
		"position": "Sales Manager",
		"name": "zhangjiajia",
		"email": "2232989977@qq.com",
		"tel": "13729038395",
		"language": "English"
	}
];

var cropData = [
	{"kind":"Ap11111111111111111111111111111ple","origin":"America","content":"111111111111","imgAll":['images/check.png','images/big.png','images/big.png','images/big.png'],"season":[1,2,3,4,5,8,9]},
	{"kind":"BananaAp11111111111111111111111111111ple","origin":"USA","content":"2222222222222222222222","imgAll":['images/big.png','images/big.png','images/gd2.png','images/gd1.png','images/big.png','images/big.png','images/big.png','images/big.png','images/big.png','images/big.png'],"season":[11,12,3,4,5,6,7]},
	{"kind":"CherryAp11111111111111111111111111111ple","origin":"Englend","content":"333333333","imgAll":['images/big.png'],"season":[3,4,5,8,9,12]},
	{"kind":"BananaAp11111111111111111111111111111ple","origin":"USA","content":"2222222222222222222222","imgAll":['images/big.png','images/big.png','images/big.png'],"season":[5,6,7,8,9,10,11]},
	{"kind":"AppleAp11111111111111111111111111111ple","origin":"America","content":"111111111111","imgAll":['images/big.png','images/big.png','images/big.png'],"season":[1,2,3,4,5,8,9]},
	{"kind":"BananaAp11111111111111111111111111111ple","origin":"USA","content":"2222222222222222222222","imgAll":['images/big.png','images/big.png','images/big.png'],"season":[1,2,3,6,7,11,12]},
	{"kind":"CherryAp11111111111111111111111111111ple","origin":"Englend","content":"333333333","imgAll":['images/big.png'],"season":[3,4,5,8,9,12]},
	{"kind":"BananaAp11111111111111111111111111111ple","origin":"USA","content":"2222222222222222222222","imgAll":['images/big.png','images/big.png','images/big.png'],"season":[5,8,9,10,11]},
	{"kind":"AppleAp11111111111111111111111111111ple","origin":"America","content":"111111111111","imgAll":['images/big.png','images/big.png','images/big.png','images/big.png'],"season":[1,2,3,4,5,8,9]},
	{"kind":"BananaAp11111111111111111111111111111ple","origin":"USA","content":"2222222222222222222222","imgAll":['images/big.png','images/big.png','images/big.png'],"season":[6,7,11,12]},
	{"kind":"CherryAp11111111111111111111111111111ple","origin":"Englend","content":"333333333","imgAll":['images/big.png'],"season":[3,4,5,8,9,12]},
	{"kind":"BananaAp11111111111111111111111111111ple","origin":"USA","content":"2222222222222222222222","imgAll":['images/big.png','images/big.png','images/big.png'],"season":[5,8,9,10,11]}
];


$(function() {
	
	
	// 第一部分相关 公司介绍
	setion1(company, arrImg) 
	
	// 第二部分相关主要产品
	generateTab( cropData )
	var tabBox = new TabSwitch('tabbox',8000,cropData);//第一个参数 是 ID 值 ，第二个参数是 切换速度
	drawSeason(cropData);
	
	
	// 第四部分相关团队
	section7(teamData);

	nav();// 侧边栏
	picCompany()// 解决company 展示图片的问题

	toChange();
	$(window).scroll(function(){
		
		toChange();//团队图片延迟加载
	});

});







//图片延迟加载
function toChange(){
		
	$('#section7 img').each(function(i,elem){
		
		if( $(elem).offset().top < $(window).height() + $(window).scrollTop() ){
			
			$(elem).attr('src',$(elem).attr('_src'));
			
		}
		
	});
		
}
//第一部分

function setion1(company, arrImg) {

	$('#companyImg').attr({
		"src": company.logoImageUrl
	});
	
	$('#companyName').html(company.companyName+'<span id="location">'+company.location+'</span>');
	
	$.each($('#tableIntro .introduct_item'), function(i, elem) {

		$(elem).html(company.introduct_item[i].title);


	});

	$("#info").text(company.info);
		
	/****************/
	var companyprofileData =  company["profile"]

	
	if( companyprofileData.length ){
		var tem = $('<div></div>');
		for(var i = 0 ; i< companyprofileData.length ; i++){
			var a = $('<a href="'+companyprofileData[i]["link"]+'" target="_blank" class="profile" alt="Company Brochure">'+companyprofileData[i]["name"]+'</a>');
			 tem.append(a);
		}
		
		$('#profileWrap').html(tem.html());
	}
	
	
	/***************/
	var $setion1 = $('#section1');
	var $tableIntro = $('#tableIntro');
	var $viewMore = $("#viewMore");
	var $shrink = $("#shrink");
	var $infocol = $(".info-col");
	var h = $('#profileWrap').outerHeight();
	
	$setion1.height($tableIntro.outerHeight() + h +90);

	$viewMore.click(function() {

		$infocol.addClass("active");
		$viewMore.hide();
		$shrink.show();
		$setion1.height($tableIntro.outerHeight() + h +30);
		$('#all').height( $('#allSection').outerHeight());
	});

	$shrink.click(function() {

		$infocol.removeClass("active");
		$viewMore.show();
		$shrink.hide();
		$setion1.height($tableIntro.outerHeight()  + h +90);
		$('#all').height( $('#allSection').outerHeight());
	});

	var $temp = $('<div></div>');
	
	var showlen = arrImg.length > 4? 4 : arrImg.length ;
	
	for(var i = 0; i < arrImg.length ; i++) {

		var $img = $('<img src="' + arrImg[i] + '" width="300" height="300" />');
		$temp.append($img);
	}
	$('#showbox').html($temp.html());
	$temp = null;

	var showproduct = {
		"boxid": "showbox",
		"sumid": "showsum",
		"boxw": 300, //宽度,该版本中请把宽高填写成一样
		"boxh": 300, //高度,该版本中请把宽高填写成一样
		"sumw": 58, //列表每个宽度,该版本中请把宽高填写成一样
		"sumh": 58, //列表每个高度,该版本中请把宽高填写成一样
		"sumi": 7, //列表间隔
		"sums":showlen , //列表显示个数
		"sumsel": "sel",
		"sumborder": 1, //列表边框，没有边框填写0，边框在css中修改
		"lastid": "showlast",
		"nextid": "shownext"
	}; //参数定义	  
	$.ljsGlasses.pcGlasses(showproduct); //方法调用，务必在加载完后执行
	

	
}



function section7(teamData) {

	var $team = $('#team');

	var $ul = $('<ul></ul>');

	for(var i = 0; i < teamData.length; i++) {

		var $li = $('<li></li>');
		var $div = $('<div class="thumbnail" ></div>');

		var $img = $('<div class="wrapImg"><img _src="' + teamData[i].url + '" alt="team" ><span></span></div>');

		var $div2 = $('<div class="caption" ></div>');

		var $postion = $('<p class="team_member_position"  title="Position:' + teamData[i]["position"] + '">Position:<span class="position">' + teamData[i]["position"] + '</span></p>');

		var $name = $('<p class="team_member_name" title="Name:' + teamData[i]["name"] + '">Name:<span class="name">' + teamData[i]["name"] + '</span></p>');

		var $email = $('<p class="team_member_email"  title="Email:' + teamData[i]["email"] + '">Email:<span class="email">' + teamData[i]["email"] + '</span></p>');

		var $tel = $('<p class="team_member_tel" title="Tel:' + teamData[i]["tel"] + '">Tel:<span class="tel">' + teamData[i]["tel"] + '</span></p>');

		var $language = $('<p class="team_member_language" title="Language Speaking:' + teamData[i]["language"] + '">Language Speaking:<span class="language" onerror="this.src=' + "images/errorTeam.jpg" + '">' + teamData[i]["language"] + '</span></p>');

		$div2.append($postion, $name, $email, $tel, $language);
		$div.append($img, $div2);
		$li.append($div);
		$ul.append($li);

	}

	$team.html($ul.html());

	$ul = null;

}



//扩展 放大镜插件
jQuery.ljsGlasses = {
	pcGlasses : function(_obj) {
		var _box = $("#" + _obj.boxid);
		var _sum = $("#" + _obj.sumid);
		var _last, _next;
		var _imgarr = _box.find("img");
		var _length = _imgarr.length;
		var _index = 0;
		var _arr = new Array();
		_sum.append("<p style='position:absolute;left:0;top:0;'></p>");
		var _sumbox = _sum.find("p");

		for (var i = 0; i < _length; i++) {
			_arr[i] = new Array();
			_arr[i][0] = _imgarr.eq(i).attr("src");
			_arr[i][1] = _imgarr.eq(i).attr("width");
			_arr[i][2] = _imgarr.eq(i).attr("height");
			var _scale = _arr[i][1] / _arr[i][2];
			if (_scale == 1) {
				_arr[i][3] = _obj.boxw; // width
				_arr[i][4] = _obj.boxh; // height
				_arr[i][5] = 0; // top
				_arr[i][6] = 0; // left
				_arr[i][7] = _obj.boxw / 2;
				_arr[i][8] = _obj.boxw * 2; // width
				_arr[i][9] = _obj.boxh * 2; // height
				_sumbox.append("<span><img src='" + _imgarr.eq(i).attr("src") + "' width='" + _obj.sumw + "' height='" + _obj.sumh + "' /><i>&nbsp;</i></span>");
			}
			if (_scale > 1) {
				_arr[i][3] = _obj.boxw; // width
				_arr[i][4] = _obj.boxw / _scale;
				_arr[i][5] = (_obj.boxh - _arr[i][4]) / 2;
				_arr[i][6] = 0; // left
				_arr[i][7] = _arr[i][4] / 2;
				_arr[i][8] = _obj.boxh * 2 * _scale; // width
				_arr[i][9] = _obj.boxh * 2; // height
				var _place = _obj.sumh - (_obj.sumw / _scale);
				_place = _place / 2;
				_sumbox.append("<span><img src='" + _imgarr.eq(i).attr("src") + "' width='" + _obj.sumw + "' style='top:" + _place + "px;' /><i>&nbsp;</i></span>");
			}
			if (_scale < 1) {
				_arr[i][3] = _obj.boxh * _scale; // width
				_arr[i][4] = _obj.boxh; // height
				_arr[i][5] = 0; // top
				_arr[i][6] = (_obj.boxw - _arr[i][3]) / 2;
				_arr[i][7] = _arr[i][3] / 2;
				_arr[i][8] = _obj.boxw * 2; // width
				_arr[i][9] = _obj.boxw * 2 / _scale;
				var _place = _obj.sumw - (_obj.sumh * _scale);
				_place = _place / 2;
				_sumbox.append("<span><img src='" + _imgarr.eq(i).attr("src") + "' height='" + _obj.sumh + "' style='left:" + _place + "px;' /><i>&nbsp;</i></span>");
			}
		}
		_imgarr.remove();

		_sum.append("<div style='clear:both;width:100%;'></div>");
		var _sumarr = _sum.find("span");
		var _sumimg = _sum.find("img");
		_sumarr.eq(_index).addClass(_obj.sumsel);
		var _border = _obj.sumborder * 2 + _obj.sumh;
		var _sumwidth = (_border + _obj.sumi) * _obj.sums;
		var _sumboxwidth = (_border + _obj.sumi) * _length;
		_sum.css({
			"overflow" : "hidden",
			"height" : _border + "px",
			"width" : _sumwidth + "px",
			"position" : "relative"
		});
		_sumbox.css({
			"width" : _sumboxwidth + "px"
		});
		_sumarr.css({
			"float" : "left",
			"margin-left" : _obj.sumi + "px",
			"width" : _obj.sumw + "px",
			"height" : _obj.sumh + "px",
			"overflow" : "hidden",
			"position" : "relative"
		});
		_sumimg.css({
			"max-width" : "100%",
			"max-height" : "100%",
			"position" : "relative"
		});

		_box.append("<div style='position:relative;'><b style='display:block;'><img id='pic300' style='display:block;' src='' /></b><span style='position:absolute;left:0;top:0;display:none;z-index:5;'></span></div><p style='position:absolute;overflow:hidden;top:0;display:none;'><img style='max-width:none;max-height:none;position:relative;left:0;top:0;' src='' /></p>");
		var _glass = _box.find("span");
		var _boximg = _box.find("b img");
		var _imgout = _box.find("div");
		var _showbox = _box.find("p");
		var _showimg = _box.find("p img");



		_box.css({
			"width" : _obj.boxw + "px",
			"height" : _obj.boxh + "px",
			"position" : "relative"
		});
		var _showboxleft = _obj.boxw + 10;
		_showbox.css({
			"width" : _obj.boxw + "px",
			"height" : _obj.boxh + "px",
			"left" : _showboxleft + "px"
		});

		var imgPlaces = function() {
			_showimg.attr("src", _arr[_index][0]);
			_boximg.attr("src", _arr[_index][0]);
			_boximg.css({
				"width" : _arr[_index][3] + "px",
				"height" : _arr[_index][4] + "px"
			});
			_imgout.css({
				"width" : _arr[_index][3] + "px",
				"height" : _arr[_index][4] + "px",
				"top" : _arr[_index][5] + "px",
				"left" : _arr[_index][6] + "px",
				"position" : "relative"
			});
			_glass.css({
				"width" : _arr[_index][7] + "px",
				"height" : _arr[_index][7] + "px"
			});
			_showimg.css({
				"width" : _arr[_index][8] + "px",
				"height" : _arr[_index][9] + "px"
			});

		
	
			if(!document.addEventListener){
				var pic300Height = $('#pic300').height() ;
				var picTop =(300-pic300Height ) / 2 ;
				if( pic300Height < 300){
					$('#pic300').css({"margin-top":picTop });
				}else{
					picTop = 0;
					$('#pic300').css({"margin-top":picTop });
				}
			}else{
				$('#pic300').load(function(){
					var pic300Height = $('#pic300').height() ;
					var picTop =(300-pic300Height ) / 2 ;
					if( pic300Height < 300){
						$('#pic300').css({"margin-top":picTop });
					}else{
						picTop = 0;
						$('#pic300').css({"margin-top":picTop });
					}
					
				});
			}
		
	
			
		};
		imgPlaces();

	
	

		_imgout.mousemove(function(e){
			play(e);
		}); // mouse END


		function play(e){
			var _gl_w = _glass.width() / 2;
			var _maxX = _imgout.width() - _gl_w;
			var _maxY = _imgout.height() - _gl_w;
			var _moveX = 0, _moveY = 0;
			var _nowX = e.pageX - _imgout.offset().left;
			var _nowY = e.pageY - _imgout.offset().top;
			var _moveX = _nowX - _gl_w, _moveY = _nowY - _gl_w;

			if (_nowX <= _gl_w) {
				_moveX = 0;
			}
			if (_nowX >= _maxX) {
				_moveX = _maxX - _gl_w;
			}
			if (_nowY <= _gl_w) {
				_moveY = 0;
			}
			if (_nowY >= _maxY) {
				_moveY = _maxY - _gl_w;
			}
			_glass.css({
				"left" : _moveX + "px",
				"top" : _moveY + "px"
			});

			var _imgX = -_moveX * _showbox.width() / _glass.width();
			var _imgY = -_moveY * _showbox.width() / _glass.width();
			
			var pic300Width = $('#pic300').width() ;
			var pic300Height = $('#pic300').height() ;
			var picTop =(300-pic300Height ) / 2 ;
			
	
			if( pic300Height < 300){
				$('#pic300').css({"margin-top":picTop });
			}else{
				picTop = 0;
				$('#pic300').css({"margin-top":picTop });
			}
			
			_showimg.css({
				"left" : _imgX + "px",
				"top" : (_imgY + picTop  )+ "px",
				"height": pic300Height * 2,
				"width": pic300Width * 2,
				"max-width":"600px",
				"max-height":"600px"
			});

		}

		_imgout.mouseenter(function() {
			_glass.css("display", "block");
			_showbox.css("display", "block");
		});
		_imgout.mouseleave(function() {
			_glass.css("display", "none");
			_showbox.css("display", "none");
		});

		// åˆ—è¡¨éƒ¨åˆ†
		var _nextbtn = $("#" + _obj.nextid);
		var _lastbtn = $("#" + _obj.lastid);
		var _moveindex = 0; // ç´¢å¼•ç§»åŠ¨

		var _sumListMove = function() {
			var _leftmove = -_moveindex * (_border + _obj.sumi);
			if (_sumbox.is(":animated")) {
				_sumbox.stop(true, true);
			}
			_sumbox.animate({
				left : _leftmove + "px"
			}, 300);
			_sumarr.eq(_index).addClass(_obj.sumsel).siblings().removeClass(_obj.sumsel);
			imgPlaces();
		}; // fun END

		if (_length <= _obj.sums) {
			var _place = (_obj.sums - _length) * _border / 2;
			_sumbox.css("left", _place + "px");
			_nextbtn.click(function() {
				_index++;
			
				if (_index >= _length) {
					_index = _length - 1;
				}
				_sumarr.eq(_index).addClass(_obj.sumsel).siblings().removeClass(_obj.sumsel);
				imgPlaces();
			});
			_lastbtn.click(function() {
				_index--;
				if (_index <= 0) {
					_index = 0;
				}
				_sumarr.eq(_index).addClass(_obj.sumsel).siblings().removeClass(_obj.sumsel);
				imgPlaces();
			});
		} else {
			var _maxNum = _length - _obj.sums;
			_nextbtn.click(function() {
				_moveindex++;
				if (_moveindex >= _maxNum) {
					_moveindex = _maxNum;
				}
				if (_index <= _moveindex) {
					_index = _moveindex;
				}
				_sumListMove();
				move( _moveindex );
			});
			_lastbtn.click(function() {
				_moveindex--;
				if (_moveindex <= 0) {
					_moveindex = 0;
				}
				if (_index >= _moveindex + _obj.sums) {
					_index = _moveindex + _obj.sums - 1;
				}
				_sumListMove();
				move( _moveindex );
			
			});
		} // if END

		_sumarr.hover(function() {
			_index = $(this).index();
			move( _index );
		
		});
		
		
		function move(_index){
			_sumarr.eq(_index).addClass(_obj.sumsel).siblings().removeClass(_obj.sumsel);
			imgPlaces();
			var pic300Width = $('#pic300').width() ;
			var pic300Height = $('#pic300').height() ;
			var picTop =(300-pic300Height ) / 2 ;
			
	
			if( pic300Height < 300){
				$('#pic300').css({"margin-top":picTop });
			}else{
				picTop = 0;
				$('#pic300').css({"margin-top":picTop });
			}
		}
	
	} // pcGlasses END
}

function picCompany(){
	var index =  $('.sel').index();
	var l =  $('#showsum').find('span').length
	var allSpan = $('#showsum').find('span') ;
	var now =  $('.sel').index();
	var src = '';
	var $showlast = $('#showlast');
	var $shownext = $('#shownext');
	
	$showlast.click(function(){
	
		index =  $('.sel').index();
		
		if(index == 0 ){
			$(this).css({"background":"#eee","color":"#eee"});
		}else{
			index --;
			allSpan.eq(index).addClass('sel').siblings().remove('sel');
			src = $('.sel').find('img').attr("src");
			$(this).css({"background":"#fff","color":"#333"});
			$shownext.css({"background":"#fff","color":"#333"});
			$('#pic300').attr({"src":src});
		}
		
	
	
		
	})

	$shownext.click(function(){	
			
		index =  $('.sel').index();
		if(index >= l-4){
			$(this).css({"background":"#eee","color":"#eee"});	
		}else{
			index ++ ;
			allSpan.eq(index).addClass('sel').siblings().remove('sel');
			src = $('.sel').find('img').attr("src");
			$('#pic300').attr({"src":src});
			$(this).css({"background":"#fff","color":"#333"});
			$showlast.css({"background":"#fff","color":"#333"});
		}
	
	})

	if( l > 4){
		
		$.each(allSpan,function( i ,elem){
		
		$(elem).mouseover(function(){
			
				if(i==0 ){
					$showlast.css({"background":"#eee","color":"#eee"});
				}else if( i == l -1 ){
					$shownext.css({"background":"#eee","color":"#eee"});
				}else{
					$shownext.css({"background":"#fff","color":"#333"});
					$showlast.css({"background":"#fff","color":"#333"});
				}
			});
		});
	}
	else{
		allSpan.mouseover(function(){
			$shownext.css({"background":"#eee","color":"#eee"});
			$showlast.css({"background":"#eee","color":"#eee"});
		});
	}
	
	
	
}

function nav(){
	
	var $navFixed = $('#navFixed');
	var onOff = true;
	var $footeCompany = $('#footeCompany');

	$(window).scroll(function(){
			
		setTimeout(function(){
			var scrollTop =	 $('body').scrollTop();
		
			var min= 20;
		
			if(scrollTop > 200 ){
				
				if( scrollTop  ){
					
				}
				$navFixed.css({"top": scrollTop-200});
				
				
			}else{
				$navFixed.css({"top": 20});
			}
			
			if(onOff){
				$footeCompany.show();
				$('#all').outerHeight( $('#allSection').outerHeight());
				onOff = !onOff
			}
		},600);
		
		
	});
	
	var allA = $navFixed.find('a');
	$navFixed.delegate('a','click',function(){
		$navFixed.css("top",$navFixed.css("top"));
		allA.removeClass('active');
		$(this).addClass('active');
	} );
}

function generateTab( cropData ){
	var $tempUl = $('<ul></ul>');
	var $tempUl2 = $('<ul></ul>');
	var $tab = $('#tabbox .tab');		
	var $content = $('#tabbox .content ul');	
	
	var cropLength = cropData.length > 10 ? 10 : cropData.length ;


	for(var i = 0 ; i < cropLength ; i++ ){
		
		if( i == 0){
			var cropLi = $('<li><a href="javascript:;" class="on"><strong>'+cropData[i]["kind"]+'</strong><br/><span>'+cropData[i]["origin"]+'</span></a></li>');
	
		}else{
			var cropLi = $('<li><a href="javascript:;" ><strong>'+cropData[i]["kind"]+'</strong><br/><span>'+cropData[i]["origin"]+'</span></a></li>');	
		}
		
		var cropContentLi = $('<li><p>'+cropData[i]["content"]+'</p></li>');
		
		$tempUl.append( cropLi );
		$tempUl2.append( cropContentLi );
	}
			
	 $tab.html($tempUl.html());		
	 $content.html($tempUl2.html());	
			
	 $tempU = $tempUl2 = null ;
}
	
//tab 切换对象 构造函数
function TabSwitch(id,time,cropData){
	
	this.tabbox = $('#'+id);
	this.time = time;
	this.li = this.tabbox.find('.tab li');
	this.auto = 1;
	this.tabChange = null ;
	this.prev = -1;
	this.index = 0;
	this.number = 0;
	this.maxNumber = this.li.length;
	
	this.shopPicShow = $('#shopPicShow');
	this.cropData = cropData;
	this.onOff = true;
	var obj = this ;
	this.li.mouseover(function(){
		obj.tabs( $(this));
		obj.pics($(this).index());

	});
	
	if(this.auto ==1){
		var number = this.number;
		var maxNumber = this.maxNumber;
		var index = this.index;
		

		obj.autotab(number,maxNumber,index );

	
		var h = this.tabbox.offset().top ;
		var _this = this ;
		var onOff = true ;
		
		$(window).scroll(function(_this ){
			
			if(  h < $(window).height() + $(window).scrollTop() ){
			
				
				if(onOff){
					
			
					_this.tabChange = setInterval(function(){
			
						obj.autotab(number,maxNumber,index );
					},obj.time);
				
					console.log( _this.tabChange );
				}
				
				onOff = false;
			
			}
			
		});
		
		
		

		//鼠标悬停暂停切换
		this.tabbox.mouseover(function(){
			clearInterval(obj.tabChange);
		});
		this.tabbox.mouseout(function(){
			obj.tabChange = setInterval(function(){
				obj.autotab(number,maxNumber,index );
			},obj.time);
		});
	}
	
	
}


TabSwitch.prototype.pics= function(index){
	
	if(this.prev == index){	
		return false ;
	}

	var arrImg  = this.cropData[index]["imgAll"]	
	var $temp = $('<div></div>');

	
	var len = arrImg.length > 10 ? 10 :arrImg.length;
	 
	this.shopPicShow.html('');
	for(var i = 0; i <len; i++ ){
		
		var $div = $('<div class="wrapDiv" style="z-index:'+(len - i)+'" ><div class="small_pic"><span class="mark"></span><span class="float_layer"></span><img src="'+arrImg[i]+'" alt="" longdesc="https://www.freshport.com"  width="100px"/></div><div class="big_pic"><img _src="'+arrImg[i]+'" alt="" longdesc="https://www.freshport.com" /></div></div>');
		
		$temp.append( $div );
		
	}

	this.shopPicShow.html($temp.html());
	cropFruitPicShow();
	this.prev = index;
};


TabSwitch.prototype.tabs = function(target){
	this.tabbox.find('a').removeClass('on');
	target.find('a').addClass('on');
	this.index  = target.index();
	this.number = this.index ;
	this.tabbox.find('.content li').hide();
	this.tabbox.find('.content li:eq('+this.index+')').show();	
};

TabSwitch.prototype.autotab= function(number,maxNumber,index){
	

	this.number == this.maxNumber? this.number = 0 : this.number;
	this.index = this.number;

	this.tabbox.find('a').removeClass('on');
	this.tabbox.find('.tab li:eq('+this.number+') a').addClass('on');
	
	this.tabbox.find('.content li:eq('+this.index +')').show().siblings().hide();
	
 	this.pics(this.number);
	this.number++;
};

//*************产品图片放大
function show(target){
	var $oFloat = target.find('.float_layer');
	var $oBig = target.find('.big_pic');
	
	$oBig.find('img').attr('src',$oBig.find('img').attr('_src'));
	
	$oFloat.show();
	$oBig.show();
}

function hide(target){
	var $oFloat = target.find('.float_layer');
	var $oBig = target.find('.big_pic');
	$oFloat.hide();
	$oBig.hide();
}

function showMove(target,event){
	
		var oDiv = target.get(0);
		var oFloat = target.find('.float_layer').get(0);
		var oSmall =  target.find('.small_pic').get(0);
		var oMark =  target.find('.mark').get(0);
		var oImg = target.find('img').get(1);
		var oBig = target.find('.big_pic').get(0);
		var oEvent = event.originalEvent
		
		var scrollTop = $('body').scrollTop() || document.documentElement.scrollTop
		

		var tDis = topHeight -scrollTop;
	
		var l=oEvent.clientX-oDiv.offsetLeft-oSmall.offsetLeft-oFloat.offsetWidth/2-left+2;
		var t=oEvent.clientY-oDiv.offsetTop-oSmall.offsetTop-oFloat.offsetHeight/2-tDis + 2;

		if(l<0)
		{
			l=0;
		}
		else if(l>oMark.offsetWidth-oFloat.offsetWidth)
		{
			l=oMark.offsetWidth-oFloat.offsetWidth;
		}
		
		if(t<0)
		{
			t=0;
		}
		else if(t>oMark.offsetHeight-oFloat.offsetHeight)
		{
			t=oMark.offsetHeight-oFloat.offsetHeight;
		}
		

		
		oFloat.style.left=l+'px';
		oFloat.style.top=t+'px';
		
		var percentX=l/(oMark.offsetWidth-oFloat.offsetWidth);
		var percentY=t/(oMark.offsetHeight-oFloat.offsetHeight);
		
		oImg.style.left=-percentX*(oImg.offsetWidth-oBig.offsetWidth)+'px';
		oImg.style.top=-percentY*(oImg.offsetHeight-oBig.offsetHeight)+'px';

}

function cropFruitPicShow(){
	
	var $wrapDiv = $('.wrapDiv');
	left =$('#shopPicShow').offset().left;
	topHeight  =$('#shopPicShow').offset().top;

	$.each($wrapDiv, function(i,elem) {
		
		var oMark =  $(elem).find('.mark');
		var targetObj = $(elem);
		
	
		$(elem).mouseover(function(){
			$(this).addClass('active').siblings().removeClass('active');
			show($(this));
		
		});
 		
 		$(elem).mouseout(function(){
			$(this).removeClass('active');
			hide( $(this) );	
		});
	 
 		$(elem).mousemove(function(ev){
 		
			showMove( $(this),ev);
		
		});

		
	});

}



/***** 画柱状图  start **********/
// seasoneData  : 需要传人的数据
var drawSeason = function(seasoneData){
	
			var monthArr = ['Jan.','Feb.','Mar.','Apr.','May.','Jun.','Jul.','Aug.','Sept.','Oct.','Nov.','Dec.'];

			var count = seasoneData.length > 10 ? 10 : seasoneData.length ;// 传人过来的数据
			//动态生成 方格背景
			var $line = $('#line');
			var $temp = $('<div></div>');
			for(var i= 0 ; i < count ;i++){
				
				var $ulLine = $('<ul></ul>');
				for(var s= 0 ;s < 13 ;s++){
						
						var $liLine = $('<li><div></div><li>');
						$ulLine.append( $liLine );
				}
				$temp.append($ulLine);
			}
			$line.html($temp.html());
			
			// 动态生成 x 轴
			var $xNum = $('#xNum');
			var $ulX = $('<ul></ul>');
			for(var i = 0 ; i < 12 ; i++ ){
				var $xLi = $('<li>'+monthArr[i]+'</li>');
				$ulX.append($xLi);
			}
			$xNum.html($ulX.html());
			
			$ulX= null;
			
			
			// 动态生成 Y 轴
			$ulX = $('<ul></ul>');
			var $count = $('#count');
			for(var i = 0 ; i < count ; i++ ){
				var $yLi = $('<li>'+ seasoneData[i]["kind"] +'<br/><span>('+seasoneData[i]["origin"]+')</span>'+'</li>');
				$ulX.append($yLi);
			}
			
			$count.html($ulX.html());
			var colors = ['#B0706E', '#8C6367', '#CB7673', '#BB657E', '#754D5D', 
    '#AC5855', '#894B60', '#B9646C','#B0706E','#764848'] 
			
			for ( var i =0 ; i < count   ; i ++){
				
				var arrSeason = seasoneData[i].season 
				
				var $li = $('<li></li>');
				var $span = $('<span  class="histogram-box" ></span>');
						
					for(var j = 1; j < 13 ; j++){
			
					if( 	$.inArray(j,arrSeason) != -1  ){//存在  
						
						var $a = $('<a  title="20%"  style="height:20px;background:'+colors[i]+';position:absolute;top:10px;left:'+((j-1) * 60+1)+'px;" >&nbsp;</a>');
					}else{
						var $a = $('<a  title="20%"   style="height:20px; position:absolute;top:10px;left:'+((j-1) * 60+1)+'px;" >&nbsp;</a>');
					}
						
					
			
						$span.append($a);
					}
				
				$li.append($span);
				$('#bar').append($li);
			}
}
	
/***** 画柱状图  end **********/