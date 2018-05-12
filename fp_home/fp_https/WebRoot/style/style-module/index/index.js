var oMarkPrev=null;
var oMarkNext=null;

function getElementsByClass(oparent,tagName,className){
		var arr=[];
		var aEls=oparent.getElementsByTagName(tagName);
		
		for(var i=0;i<aEls.length;i++){
			var arrClassName=aEls[i].className.split(' ');
			for(var j=0;j<arrClassName.length;j++){
				if(arrClassName[j]==className){
					arr.push(aEls[i]);
					break;
				}
			}
		}
		
		return arr;
}

window.onload = function (){
	var oDiv = document.getElementById('playimages');
	var oParent = document.getElementById('pic');
	var oUl = oDiv.getElementsByTagName('ul')[0];
	var aLi = oUl.getElementsByTagName('li');
	var len=aLi.length;
	var oneWidth=aLi[0].offsetWidth;
	var oArrow=getElementsByClass(oParent,'div','arrow');

	oMarkPrev=oParent.getElementsByTagName('a')[0];	
	oMarkNext=oParent.getElementsByTagName('a')[1];
	var num = 0;

	var timer = null;

	
	function autoPlay(){
		clearTimeout( timer );
		timer = setInterval(function(){
			num++;
		
			num%=7;
			
			fnTab();
			
		}, 2200);
	}
	//autoPlay();
	
	setTimeout( autoPlay, 1000 );
	
	oParent.onmouseover = stop;
	oParent.onmouseout = function(){
		oArrow[0].style.display="none";
		oArrow[1].style.display="none";
		autoPlay();
	
	};

	////////////////////////////////////////////////////////////////////////


	
	oMarkPrev.onmouseover=stop;
	oMarkPrev.onclick=oArrow[0].onclick=prev;
	
	oMarkNext.onmouseover=stop;
	oMarkNext.onmouseout = autoPlay;
	oMarkNext.onclick=oArrow[1].onclick=next;
	
	
	
	
	function prev(){
		clearTimeout( timer );
		num--;
		if(num==-1){
			num=5;
		}
		fnTab();	
	}
	
	function next(){
		clearTimeout( timer );
		num++;
		if(num==7){
			num=0;
		}
		fnTab();		
	}
	
	function stop(){
		oArrow[0].style.display="block";
		oArrow[1].style.display="block";
		clearTimeout( timer );
	}
	
	
	// 初始化
	function fnTab(){
		//oDiv.style.left=-(num*oneWidth)+'px';
		//document.title=num;
		

			startMove(oDiv,{left:-(num*oneWidth-2)},function(){
				if(num==6){
					
					oDiv.style.left='0px';
				}
			});
		
	}
	fnTab();
	
/* 	for( var i=0; i<aLi.length; i++ ){
		aLi[i].index = i;			// 索引值
		aLi[i].onclick = function (){
			num = this.index;
			fnTab();
		};
	}
	 */

	


	
	
	//运动函数
	function startMove(obj, json, fnEnd)
	{
		if(obj.timer)
		{
			clearInterval(obj.timer);
		}
		obj.timer=setInterval(function (){
			doMove(obj, json, fnEnd);

		
			
		},30);
	}

	function getStyle(obj, attr)
	{
		if(obj.currentStyle)
		{
			return obj.currentStyle[attr];
		}
		else
		{
			return getComputedStyle(obj, false)[attr];
		}
	}
	
	function doMove(obj, json, fnEnd)
	{
		var iCur=0;
		var attr='';
		var bStop=true;	//假设运动已经该停止了
		
		for(attr in json)
		{
			if(attr=='opacity')
			{
				iCur=parseInt(100*parseFloat(getStyle(obj, 'opacity')));
			}
			else
			{
				iCur=parseInt(getStyle(obj, attr));
			}
			
			if(isNaN(iCur))
			{
				iCur=0;
			}
			
			var iSpeed=(json[attr]-iCur)/6;
			iSpeed=iSpeed>0?Math.ceil(iSpeed):Math.floor(iSpeed);
			
			if(json[attr]!=iCur)
			{
				bStop=false;
			}
			
			if(attr=='opacity')
			{
				obj.style.filter="alpha(opacity:"+(iCur+iSpeed)+")";
				obj.style.opacity=(iCur+iSpeed)/100;
			}
			else
			{
				obj.style[attr]=iCur+iSpeed+'px';
			}
		}
		
		if(bStop)
		{
			clearInterval(obj.timer);
			obj.timer=null;
			
			if(fnEnd)
			{
				fnEnd();
			}
		}
	}
	};
	
		
	//运动函数

	// JavaScript Document
(function($){
	$.fn.myScroll = function(options){
	//默认配置
	var defaults = {
		speed:40,  //滚动速度,值越大速度越慢
		rowHeight:24 //每行的高度
	};
	
	var opts = $.extend({}, defaults, options),intId = [];
	
	function marquee(obj, step){
	
		obj.find("ul").animate({
			marginTop: '-=1'
		},0,function(){
				var s = Math.abs(parseInt($(this).css("margin-top")));
				if(s >= step){
					$(this).find("li").slice(0, 1).appendTo($(this));
					$(this).css("margin-top", 0);
				}
			});
		}
		
		this.each(function(i){
			var sh = opts["rowHeight"],speed = opts["speed"],_this = $(this);
			intId[i] = setInterval(function(){
				if(_this.find("ul").height()<=_this.height()){
					clearInterval(intId[i]);
				}else{
					marquee(_this, sh);
				}
			}, speed);

			_this.hover(function(){
				clearInterval(intId[i]);
			},function(){
				intId[i] = setInterval(function(){
					if(_this.find("ul").height()<=_this.height()){
						clearInterval(intId[i]);
					}else{
						marquee(_this, sh);
					}
				}, speed);
			});
		
		});

	}

})(jQuery);



$(function() {
	var url = basePath + "/account!getUser.dhtml?num=" + Math.random();
	$.ajax({
		type : "GET",
		url : url,
		async : false,
		success : function(data) {
			if (data != "") {
				$("#LoginA").css("display", "block");
				$("#usernameInfo").empty();
				$("#usernameInfo").append(data);
				$("#framelogin").css("display", "none");
				//$("#enterFP").attr("href", FreshPortUrl);
			} else {
				$("#LoginA").css("display", "none");
				$("#framelogin").css("display", "block");
			}
		}
	});
});

function clearUser() {
	var url = basePath + "/account!clearUser.dhtml?num=" + Math.random();
    var payLogout = payPath + "/masterlogin!logout.dhtml?num=" + Math.random();
    var salesLogout = salesPath + "/masterlogin!logout.dhtml?num=" + Math.random();
    var appmanagerLogout = appmanagerPath + "/masterlogin!logout.dhtml?num=" + Math.random();
    var purchaseLogout = purchasePath + "/masterlogin!logout.dhtml?num=" + Math.random();
    var logisticLogout = logisticPath + "/masterlogin!logout.dhtml?num=" + Math.random();
    var foreignprocurementLogout = foreignprocurementPath + "/masterlogin!logout.dhtml?num=" + Math.random();
    var inventoryLogout = inventoryPath + "/masterlogin!logout.dhtml?num=" + Math.random();
    $.get(payLogout);
    $.get(salesLogout);
    $.get(appmanagerLogout);
    $.get(purchaseLogout);
    $.get(logisticLogout);
    $.get(foreignprocurementLogout);
    $.get(inventoryLogout);
	$.ajax({
		type : "GET",
		url : url,
		async : false,
		success : function(data) {
			//不使用单点登录
			//window.location.href = "../index/index.jsp";
			//如果使用单点登录
			window.parent.location.href = loginPath+"/logout?service="+basePath;
		}
	});
}