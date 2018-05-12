<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/base/include/basePage.jsp" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
  <head>
    
    <title>生鲜港</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@ include file="/base/common/jQuery.jsp"%>

	<link type="text/css" rel="stylesheet" href="${applicationScope.sysConfigs['sysPath']}/style/style-common/css/common.css">
	<link type="text/css" rel="stylesheet" href="${applicationScope.sysConfigs['sysPath']}/style/style-common/css/index.css">
	<link rel="shortcut icon" href="${applicationScope.sysConfigs['sysPath']}/style/style-common/images/favicon.ico">
	<link type="text/css" rel="stylesheet" href="${applicationScope.sysConfigs['sysPath']}/style/style-common/css/banner.css">
	<script type="text/javascript" src="${applicationScope.sysConfigs['sysPath']}/style/style-module/index/index.js"></script>
	<script type="text/javascript" src="${applicationScope.sysConfigs['sysPath']}/style/style-module/index/slide.js"></script>
	<script type="text/javascript" src="${applicationScope.sysConfigs['sysPath']}/style/style-module/index/banner.js"></script>
<style>
		/* 公共的样式 */
		html,body{width:100%;background:url(${applicationScope.sysConfigs['sysPath']}/style/style-common/images/bg.png);font-family:"微软雅黑";}
		body{padding:0;margin:0;}
		ul,li{list-style:none;margin:0;padding:0;}
		p{padding:0;margin:0;}
		a{text-decoration:none;color:#333;}
		
		/* 布局 css   width 1024 需要做适配     */
		#main{padding-top:74px;padding-bottom:54px;background:transparent;width:1280px;box-sizing:border-box;margin:0 auto;position:relative;}
		.navbar{width:300px;}
		#nav{width:100%;height:64px;background:#fff;position:absolute;top:0;left:0;margin-bottom:20px;border-radius:5px;}
		
		#footer{width:100%;height:24px;background:#fff;position:absolute;bottom:0;left:0;border-radius:5px;}
		
	
		
		
		/* nav css */
		#nav{background:url(${applicationScope.sysConfigs['sysPath']}/style/style-common/images/logo.png) #fff no-repeat 6px center;}
		.nav_img{width:50%;text-decoration:none;float:left;}
		.nav_img span{display:inline-block;margin-left:136px;line-height:64px;color:#fa9d2a;font-size:16px;float:left;font-weight:560;}
		.navbar{width:300px;float:right;height:100%;}
		.navbar li{list-style:none;float:left;padding:10px;box-sizing:border-box;height:64px;line-height:44px;}
		.navbar a{text-decoration:none;color:#333;height:64px;line-height:44px;}
		
		/*footer css*/
		#footer{width:100%;text-align:center;line-height:44px;height:44px;padding:0;margin:0;color:#666;font-size:14px;}
				
		/*  container css*/
		#container{width:100%;height:828px;background:#fff;border-radius:5px;}
		.container_session{height:784px;widht:98%;background:#fff;margin:1% auto;padding:0 1%;}
		.container_left{width:68%;border-right:1px solid #eee;float:left;}
		.container_right{width:27%;float:left;}
		
		.container_top{height:580px;width:100%;box-sizing:border-box;border:1px solid #eee;background:url("${applicationScope.sysConfigs['sysPath']}/style/style-common/images/banner_bg.jpg") no-repeat;background-size:100% 100%;background-position:left top;}
		
		.container_bottom{height:186px;width:100%;border-top:none;position:relative;overflow:hidden;padding-top:10px;}
		
		.container_right_bottom{height:480px;width:98%;}
		.container_right_top{height:310px;width:98%;}
		.container_center{height:10px;width:100%;}
		
		.container_right .register {padding: 0 1%;width:98%;box-sizing:border-box;border-radius: 6px;-moz-border-radius: 6px;-webkit-border-radius: 6px;border: 1px solid #eee;box-shadow: 0 1px 2px #ccc!important;background: #fff;height:98%;position:relative;overflow:hidden;
		}
		
		.register h1 {
			font-size:18px;font-weight: 500;padding: 6px; margin: 0; border-bottom: 1px solid #eee; text-align: center; padding-bottom: 10px;margin-bottom:10px;
			
		}
		
		.text {
			border: 1px solid rgba(0, 0, 0, .1);padding: 6px 12px;border-radius: 3px;-moz-border-radius: 3px;-webkit-border-radius: 3px;margin-bottom: 5px;font-size: 13px;
		}
		.text1 {
			border: 1px solid rgba(0, 0, 0, .1);padding: 6px 0px;border-radius: 3px;-moz-border-radius: 3px;-webkit-border-radius: 3px;margin-bottom: 5px;font-size: 13px;
		}
		.text img {
			margin-right: 6px;vertical-align: middle;position: relative;
		}
		
		.register input[type=text], .register input[type=password] {
			border: 0;font-size: 14px;font-weight: 300;height: 22px;position: absolute;margin-top: -2px;width: 80%;
		}
		/* 轮播图 的css  */
		.product{width:204px;float:left;height:184px;float:left;position:relative;}
	/* 	
		.product em{position:absolute;bottom:0;height:80px;text-align:center;width:100%;line-height:80px;padding:0;margin:0;color:#fff;font-weight:600;font-size:22px;}
		 */
		.container_bottom .mark_left,.mark_right{width:6%;height:100%;background:#000;position:absolute;top:0%;filter: alpha(opacity:0); opacity: 0; z-index:3000;  }
		
		.mark_left{left:0;float:left;}
		
		.mark_right{right:0;text-align:right;}
		
		.img-responsive{
		    display: block;
		    max-width: 100%;
		    height: auto;
		}
		
		#playimages{width:100%;height:100%;position:relative;}
		#playimages ul{margin:0;padding:0;list-style:none;position:absolute;left:0;top:0;width:3000px;height:100%;transition:1s easing;}
		
		#playimages ul li{box-sizing:border-box;}
		.arrow{display:none;}
		.prev,.next { width: 60px; height:60px;position: absolute;z-index: 3001; cursor: pointer;font-family:"宋体";font-size:36px;top:50%;margin-top:-30px;line-height:60px;color:#666;font-weight:bold;z-index:3000;text-decoration:none;}
		.prev{left:4px;}
		.next{right:4px;text-align:right;}
		
		
		//公告 css
		/* bcon */
		.bcon{width:100%;border:1px solid #eee;margin:20px auto;box-sizing:border-box;}
	
		.bcon a,.bcon span{font-size:12px;}
		.a_blue{font-size:14px;}
		.list_lh{height:100%;overflow:hidden;width:100%;}
		.bcon .more {
			width: 52px;
			height: 19px;
			position:absolute;
			background: url(${applicationScope.sysConfigs['sysPath']}/style/style-common/images/more.png);
			text-align: center;
			line-height: 19px;
			font-weight: 500;
			color: #8bbd3d;
			margin-top:5px;
			right:10px;
		}
		.list_lh li{padding:8px;width:100%;}
		.list_lh .lieven{background:#F8F8F8;}
		.list_lh .item {
			margin-left: 7px;
			padding-left: 2px;
			background: url(${applicationScope.sysConfigs['sysPath']}/style/style-common/images/dian.png) repeat-y left top;
			
		}
		
		.item .time {
			height: 22px;
			padding-left: 24px;
			margin-left: -14px;
			background: url(${applicationScope.sysConfigs['sysPath']}/style/style-common/images/time.png) no-repeat left 6px;
			margin-bottom: 12px;
		}
		
		.item .time span {
			display: inline-block;
			height: 100%;
			width: 120px;
			background: url(${applicationScope.sysConfigs['sysPath']}/style/style-common/images/timeBg.png) no-repeat left top;
			font-size: 12px;
			text-align: center;
			line-height: 17px;
			color: #222;
		}
		
		.item p {
			padding-left: 20px;
			font-size: 14px;
			line-height: 14px;
			color: #3a3a3a;
			line-height: 1.5em;
			padding-bottom: 5px;
		}
		
		.qin {
			display: inline-block;
			padding:0 12px;
		}
			
		.btn_lh:hover{color:#fff;text-decoration:none;}
		

		
		// 6大功能
		#playimages_ul{height:100%;}
		#playimages_ul li{border:1px solid #eee;border-left:none;border-right:1px solid #eee;}

		
		

		#LoginA {
		    width: 100%;
		    height: 90%;
		    border-top: 1px solid rgba(0, 0, 0, .05);
		    box-shadow: 0 1px 2px rgba(0, 0, 0, .2) !important;
		    border-radius: 5px;
			background:#fff!important;
		}
		
		a#usernameInfo {
		    display: block;
		    padding-top: 120px;
		    background:url("${applicationScope.sysConfigs['sysPath']}/style/style-common/images/menber.png") no-repeat center 20px;
		    text-align: center;
		    font-size: 18px;
		    line-height: 2em;
		    padding-bottom: 10px;
		    border-bottom: 1px solid #ddd;
		}
		
		#info {
		    text-align: center;
		    line-height: 3em;
		    font-size: 18px;
		}	
		
		a#usernameInfo::before {
	    content: "欢迎登录生鲜港";
	    display: block;
	    font-weight: bold;
		}
		
		
		.qqserver {
	    position: fixed;
	    top: 50%;
		float:right;
	    right: 2px;
	    height: 110px;
	    margin-top: -55px;
	    width:70px;
	    border-radius:4px;
	    background:url("${applicationScope.sysConfigs['sysPath']}/style/style-common/images/app.png") no-repeat left top;
	    
		}
		
		.qqserver a{
			
			display:block;
			width:70px;
			height: 110px;
		}
		
		/* 媒询 */
		@media all and (max-height: 1024px) {
		#container{height:800px;}
		.container_right_bottom{height:470px;}
		.container_right_top{height:300px;}
		.container_center{height:4px;}
		} 
		

		@media all and (max-width: 1366px) {
			#main{width:1024px;}
			.container_top{background:url("${applicationScope.sysConfigs['sysPath']}/style/style-common/images/banner_bg2.jpg") no-repeat;
			background-size:cover;
			}
				
		}
		
		@media all and (max-width: 1022px) {
			#main{width:900px;}
			.container_left{width:61.8%;}
			.container_right{width:33.2%;}
		}
		
		@media all and (max-width: 900px) {
			#main{width:800px;}
			.register input[type=text], .register input[type=password] {width: 70%;}
		}
		
		@media all and (max-width: 808px) {
			#main{width:700px;}
		}
		
		@media all and (max-width: 706px) {
			#main{width:620px;}
			.list_lh li p span{display:none;}
			input[name="email"]{width:40%!important;}
			.nav_img span {display:none;}
			#info{font-size:16px;}
			.container_left{width:50%;}
			.container_right{width:44%;}
		}
		
		@media all and (max-width: 602px) {
			#main{width:500px;}
			.register input[type=text], .register input[type=password] {width: 60%;}
			#code{width:40%;}
		
		
		}
		@media all and (max-width: 506px) {
			#main{width:400px;}
			.register input[type=text], .register input[type=password] {width: 50%;}
			#code{width:40%;}
			.container_left{width:10%;visibility:hidden;}
			.container_right{width:90%;margin-left:-5%;}
			
			.navbar {width: 100px;}
			.navbar li {height: 20px;line-height: 20px;font-size: 12px;}
			.hr{display:none;}
			.navbar { margin-top: -8px;}
			 #footer{font-size: 12px;line-height: 22px;}
		}
		@media all and (max-width:426px) {
			#main{width:300px;}
		
		}
		
		@media all and (max-width:326px) {
			#main{width:240px;}
		
		}
	.product a{width:100%;height:100%;display:block;}	
	.product{position:relative;}

	.list_title{height:24px;}
	.list_detail{height:auto;font-size:12px;}
	</style>
	<script type="text/javascript">
	console.log('${novaSession}');
	</script>
  </head>
  
  <body>
  	<div id="main">
	
		<!--导航-->
		<div id="nav">
			<a  class="nav_img" href="#">
			<span>信&nbsp;息&nbsp;服&nbsp;务&nbsp;平&nbsp;台</span>
			</a>
			<ul class="navbar">
				<li> <a href="${frontPath}" target="_self"> English </a></li>
				<li  class="hr" > <a href="#"> | </a></li>
				<li> <a href="${frontPath}/fp-online-infoshow/priceImport/show" target="_self"> 价格详情 </a></li>
				<li  class="hr" > <a href="#"> | </a></li>
				<li> <a href="${applicationScope.sysConfigs['sysPath']}/html/about.jsp" target="_blank" > 关于我们 </a></li>
				<li  class="hr" > <a href="#"> | </a></li>
				<li> <a  href="${applicationScope.sysConfigs['sysPath']}/html/contact.jsp" target="_blank"> 联系我们 </a></li>
				
			</ul>
		</div>

		<!--导航-->

		<!-- 主体 -->
		<div id="container">
		
			<div class="container_session container_left">
				<div class="container_top" ></div>
				<div class="container_bottom" id="pic" >
				
					<a class="mark_left"  href="javascript:;"></a>
					<a class="mark_right" href="javascript:;"></a>
					<div class="arrow"> <a class="prev" href="javascript:;">&lt;</a></div>
					<div class="arrow"><a class="next" href="javascript:;">&gt;</a></div>
				<div  id="playimages">
					<ul id="playimages_ul">
						<li class="product" style="border-left:1px solid #eee;background:url(${applicationScope.sysConfigs['sysPath']}/style/style-common/images/1.png) no-repeat;background-position:center;background-size:contain;">
							<a href="${applicationScope.sysConfigs['sysPath']}/html/jishou.jsp"  title="寄售二次征税">

							</a>
						</li>
						<li class="product" style="background:url(${applicationScope.sysConfigs['sysPath']}/style/style-common/images/2.png) no-repeat;background-position:center;background-size:contain;">
							<a href="${applicationScope.sysConfigs['sysPath']}/html/waihui.jsp"  title="外汇支付管理"></a>
						</li>
						<li class="product" style="background:url(${applicationScope.sysConfigs['sysPath']}/style/style-common/images/3.png) no-repeat;background-position:center;background-size:contain;" >
							<a href="${applicationScope.sysConfigs['sysPath']}/html/wuliu.jsp"  title="货代物流跟踪"></a>
						</li >
						<li class="product"  style="background:url(${applicationScope.sysConfigs['sysPath']}/style/style-common/images/4.png) no-repeat;background-position:center;background-size:contain;">
							<a href="${applicationScope.sysConfigs['sysPath']}/html/shichang.jsp"   title="批发市场信息管理"></a>
						</li>
						<li class="product" style="background:url(${applicationScope.sysConfigs['sysPath']}/style/style-common/images/5.png) no-repeat;background-position:center;background-size:contain;" >
							<a href="${applicationScope.sysConfigs['sysPath']}/html/jinrong.jsp"  title="金融服务"></a>
						</li>
	
						<li class="product" style="background:url(${applicationScope.sysConfigs['sysPath']}/style/style-common/images/6.png) no-repeat;background-position:center;background-size:contain;">
							<a href="${applicationScope.sysConfigs['sysPath']}/html/guanli.jsp"  title="业务管理系统" ></a>
						
						</li>
						<li class="product" style="background:url(${applicationScope.sysConfigs['sysPath']}/style/style-common/images/1.png) no-repeat;background-position:center;background-size:contain;">
							<a href="${applicationScope.sysConfigs['sysPath']}/html/jishou.jsp"  title="寄售二次征税">

							</a>
						</li>
						<li class="product" style="background:url(${applicationScope.sysConfigs['sysPath']}/style/style-common/images/2.png) no-repeat;background-position:center;background-size:contain;">
							<a href="${applicationScope.sysConfigs['sysPath']}/html/waihui.jsp"  title="外汇支付管理"></a>
						</li>
						<li class="product" style="background:url(${applicationScope.sysConfigs['sysPath']}/style/style-common/images/3.png) no-repeat;background-position:center;background-size:contain;" >
							<a href="${applicationScope.sysConfigs['sysPath']}/html/wuliu.jsp"  title="货代物流跟踪"></a>
						</li >
						<li class="product"  style="background:url(${applicationScope.sysConfigs['sysPath']}/style/style-common/images/4.png) no-repeat;background-position:center;background-size:contain;">
							<a href="${applicationScope.sysConfigs['sysPath']}/html/shichang.jsp"   title="批发市场信息管理"></a>
						</li>
						<li class="product" style="background:url(${applicationScope.sysConfigs['sysPath']}/style/style-common/images/5.png) no-repeat;background-position:center;background-size:contain;" >
							<a href="${applicationScope.sysConfigs['sysPath']}/html/jinrong.jsp"  title="金融服务"></a>
						</li>
	
						<li class="product" style="background:url(${applicationScope.sysConfigs['sysPath']}/style/style-common/images/6.png) no-repeat;background-position:center;background-size:contain;">
							<a href="${applicationScope.sysConfigs['sysPath']}/html/guanli.jsp"  title="业务管理系统" ></a>
						
						</li>
					</ul>
				</div>
			</div>
		</div>
			
			<!-- 登录 -->
			<div class="container_session container_right" style="overflow:hidden;">
			
				<div class="container_right_top" id="top">
			   
					 <c:choose>
			        	<c:when test="${empty sessionScope.userName }">
				        	<iframe scrolling="auto" id="framelogin" frameborder="0"  src="${loginPath }/login?service=${sysPath}/redirect.jsp&locale=zh_CN" style="width:100%;height:100%;overflow:hidden;"></iframe>
				        </c:when>
				        <c:otherwise>
				        <div class="fr name" id="LoginA" style="display: block;">
					        	<a href="" id="usernameInfo"></a>  
								 <p id="info">
								  <a href="javascript:void(0);" onclick="clearUser();" id="out">退出登录&nbsp;&nbsp;</a> | <a id="enterFP" href="${foreignprocurementPath }/jump!page.dhtml?p=login_page" >&nbsp;&nbsp;我的生鲜港</a> 
								</p>				        
						</div>
  
				        </c:otherwise>
			        </c:choose>
				
				</div>
				
				<div class="container_center"></div>
				
				<div class="container_right_bottom" id="tell">
					<div class="register bcon">
						<h1>最新公告<a class="more" href="${applicationScope.sysConfigs['sysPath']}/html/newList.jsp" target="_blank">更多</a></h1>
					
						<div class="list_lh" >
						<ul class="item" style="height:3000px;" id="getList">


						</ul>
						
					 </div>
					</div>
				
				</div>
			</div>
		</div>
		<!-- 主体 -->

		<!--页脚-->
		<div id="footer">
			备案/许可证号 沪ICP备15035308&nbsp;&nbsp;|&nbsp;&nbsp;2015 Freshport.com. All rights reserved
		
		</div>
		<!-- 页脚-->
		
		<!-- 侧边栏-->	
		<div class="qqserver">
			<a href="${applicationScope.sysConfigs['sysPath']}/html/app.jsp" ></a>
		</div>
	
	</div>

	<script type="text/javascript">


	
	$(document).ready(function(){
	
	
		var $getList = $('#getList');
        var $tempUl = $('<ul></ul>');
		var data =  getList() ;
		var len = data.length < 10 ? data.length : 10 ;
		
		for(var i = 0 ;i < len ;i++){
	
			var $li = $('<li></li>');
			var year = data[i]["time"].substring(0,4);
			var month = data[i]["time"].substring(4,6);
			var day = data[i]["time"].substring(6,8);
			
			var $div =$('<div class="time"><span>'+year +'年'+month+'月'+day+'日'+'</span></div>');
			var $p =$('<p><a  href="javascript:;" >'+data[i].text+'</a></p>');
			$li.append($div,$p);
			$tempUl.append($li);
		}
	
		$getList.html( $tempUl.html() );
		
		
		var widthContaner=$('.container_top').width();
		$('.product').css({"width":(widthContaner-2)/4});
		
		
		$.each($('.product'),function(index,elem){
			
			if($(elem).index()==5){
				
				$(elem).css({"border-right":"none"});
			
			}
			
		});
		
	
		
		$('.product').hover(function(){
			$(this).css({"border":"1px solid #f97f00","border-left":"none"});
			$(this).prev().css({"border-right":"1px solid #f97f00"});
			if($(this).index()==0){
				
				$(this).css({"border":"1px solid #f97f00"});
			}
			
		},function(){
			$(this).css({"border":"1px solid #eee","border-left":"none"});
			$(this).prev().css({"border-right":"1px solid #eee"});
		});
	
		$('.list_lh li:even').addClass('lieven');
		
		$("div.list_lh").myScroll({
			speed:60, //数值越大，速度越慢
			rowHeight:112//li的高度
		});

		
	})
	
	function getList(){
		
		var data0 = null;
		
		$.ajax({
			type:"get",
			url:'${applicationScope.sysConfigs['sysPath']}/html/data.txt',
			dataType:"json",
			async:false,
			success:function(data){
				data0 = data; 
			}
		});
		
		return data0;
	}
	</script>
	<!-- modernizr.js 文件 -->
	<script src="https://cdn.bootcss.com/modernizr/2.8.3/modernizr.min.js"></script>
  </body>
</html>
