<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/base/include/basePage.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<html>

	<head>
		<meta charset="utf-8">
		<!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"><![endif]-->
		<title></title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

		<style>
			* {
				margin: 0px;
				padding: 0px;
			}
			
			html{height:100%}
body{height:100%;margin:0px;padding:0px}
#container{height:100%}
#weixin,#weixin img,#web{width:100%;height100%;overflow:hidden;}
#weixin,#web{display:none;}

h3{margin:20px auto;}

.img-cont ,.img-cont a{
	display:block;
	width: 300px;
	height: 300px;
	margin: auto;
	border:1px solid #ccc;
	text-align: center;
}
.img-cont img{
	max-width:100%;
	max-height:100%;
	display: inline;
	position: relative;
	top:50%;
	transform: translateY(-50%);
	-webkit-transform: translateY(-50%);
	-moz-transform: translateY(-50%);
}

.wz{ text-align:center}
		</style>
	</head>

	<body  id="body">
	<div id="weixin">
		<img id="apkImg" />
	</div>
		
		
	<div id="web">
	
	<h3 class="wz">点击下载安装</h3>
		<!--图片展示模块-->
		<div class="img-cont">
						<a href="https://github.com/freshport/freshport.github.io/releases/download/1.0/freshport.apk">
				<img id="webImg" / >
				
				</a>
		</div>
	</div>

<script type="text/javascript"> 

//查找屏幕高度
document.body.style.minHeight=document.documentElement.clientHeight+'px';


function isWeiXin(){ 
var ua = window.navigator.userAgent.toLowerCase(); 
if(ua.match(/MicroMessenger/i) == 'micromessenger'){ 
return true; 
}else{ 
return false; 
} 
} 


window.onload = function(){ 
	if(isWeiXin()){ 
		var div = document.getElementById('weixin'); 
		var img = document.getElementById('apkImg'); 
		div.style.display='block';
		img.src="${applicationScope.sysConfigs['sysPath']}/style/style-common/images/app/apkweixin.gif";

	}else{
				
		var div = document.getElementById('web'); 
		var img = document.getElementById('webImg'); 
		div.style.display='block';
		img.src="${applicationScope.sysConfigs['sysPath']}/style/style-common/images/app/apk.gif";
	}
} 

</script> 
	</body>

</html>