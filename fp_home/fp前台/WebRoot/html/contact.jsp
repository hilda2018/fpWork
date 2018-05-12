<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/base/include/basePage.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
    <title>生鲜港--联系我们</title>
	<link rel="shortcut icon" href="${applicationScope.sysConfigs['sysPath']}/style/style-common/images/favicon.ico">
	<link type="text/css" rel="stylesheet" href="${applicationScope.sysConfigs['sysPath']}/style/style-common/css/base.css">
</head>
<style>


/* contact_us  **/
#container{padding-left:84px;padding-top:20px;background:url(${applicationScope.sysConfigs['sysPath']}/style/style-common/images/contact_us.png) no-repeat 20px 38px #fff;background-size:32px;}
#container h2{margin-top:0px;}

#content {width:100%;height:100%;box-sizing:border-box;}
.title{border-bottom:1px solid #666;}
.title .circle{height:8px;width:8px;border-radius:50%;background:#000;float:left;position:relative;left:-16px;top:20px;}
#content .title .en{font-size:12px;margin-left:4px;margin-top:8px;font-weight:normal;color:#888;display:inline-block;float:right;}
.title,.title span{line-height:42px;height:42px;}
#content .title h2{float:left;font-size:22px;line-height:44px;height:42px;padding:0;margin:0;font-weight:normal;}
.title .title_right{float:right;font-size:14px;padding-left: 10px;}
.title_detail{font-size:14px;float:left;width: 100%;padding-left: 10px;box-sizing: border-box;}

dd,dl{margin:0;padding:0;}
.navbar .active{color:#f97f00;}


#detail{margin-top:20px;margin-left:10px;}
#detail_left{width:50%;float:left;}
#detail_right{width:50%;float:left;}

#detail .detail_right li{font-size:18px;line-height:40px;padding-left:20px;}
#detail dd{height:124px;margin:0 auto;}
#detail dt{font-size:20px;line-height:50px;height:50px;padding-left:20px;font-weight:bold;}
.detail_item0{padding-bottom:20px;border:1px solid #ddd;height:182px;width:96%;
float:left;box-sizing:border-box;position: relative;top: 0;margin-right:3%; border-radius: 4px;background-color: #FFF;box-shadow: 0 2px 4px #eee;
cursor: pointer;transition: top .2s ease-in,boxShadow .2s;margin-bottom:30px;    box-shadow: 0 3px 15px rgba(0,0,0,.1);}
.detail_item1{height:392px;}





/* 媒询 */


@media all and (max-width: 1366px) {
	#main{width:1024px;}
	#detail .detail_right li{font-size:16px;}
}

@media all and (max-width: 1028px) {
	#main{width:900px;}
	.detail_item0 {width: 96%;}

}


@media all and (max-width: 900px) {
	#main{width:800px;}
	.detail_item0 {width: 96%;}
}

@media all and (max-width: 808px) {
	#main{width:700px;}
	.detail_item0 {width: 96%;}
	#detail .detail_right li{font-size:14px;}
}


@media all and (max-width: 718px) {
	#main{width:600px;}
	
	#detail_left,#detail_right{width: 100%;float: left;}
	#detail .detail_right li{font-size:16px;}
	#footer{font-size: 12px;line-height: 22px;}
}

@media all and (max-width: 618px) {
	#main{width:500px;}
	#detail .detail_right li{font-size:16px;}
	#container {padding-left: 20px;background:url('') #fff;}
	
	
}

@media all and (max-width: 518px) {
	#main{width:400px;}
	.title .title_right {font-size: 12px;}
	#detail .detail_right li{font-size:14px;}
}


@media all and (max-width: 418px) {
	#main{width:300px;}

} 
</style>

<body>

	<div id="main">
	
		<!--导航-->
		<div id="nav">
			<a  class="nav_img" href="${applicationScope.sysConfigs['sysPath']}"  target="_blank">
			<span>信&nbsp;息&nbsp;服&nbsp;务&nbsp;平&nbsp;台</span>
			</a>
			<ul class="navbar">
				<li> <a href="${infoPath}/fp-online-infoshow/home/index" target="_blank"> English </a></li>
				<li  class="hr" > <a href="#"> | </a></li>
				<li> <a href="about.jsp"  target="_blank"> 关于我们 </a></li>
				<li  class="hr" > <a href="#"> | </a></li>
				<li> <a href="#" class="active" > 联系我们 </a></li>
				
			</ul>
		</div>

		<!--导航-->

		
		<!-- 主体 -->
		<div id="container">
			<div id="content">
				<div class="title">
					<h2><span class="circle"></span>联系我们<span class="en">CONTACT&nbsp;US</span></h2>
					<div class="title_right">您当前的位置：<a href="${applicationScope.sysConfigs['sysPath']}" target="_blank">首页</a> &nbsp;>&nbsp; 联系我们</div>
					<div style="clear:both"></div>
				</div>
				<div class="title_detail">如果您对我们的服务有任何疑问，或是想要了解更多的服务信息，请随时联系我们相关工作人员，我们非常乐意为您服务</div>
				<div style="clear:both"></div>
				<div id="detail">
					<div id="detail_left">
						<div class="detail_item0">
							<dl>
								<dt>系统服务</dt>
								<dd>
							
									<div class="detail_right">
										<ul class="detail_item" class="img-responsive">
											<li>联系人：姚可蒙</li>
											<li>电话：13359102543</li>
											<li>邮箱：laura@freshport.com</li>
										</ul>
									</div>
								</dd>
	
							</dl>
						</div>
						
						<div class="detail_item0" >
						<dt>寄售二次征税</dt>
								<dd>
						
									<div class="detail_right">
										<ul class="detail_item">
											<li>联系人：周剑锋</li>
											<li>电话：18217400561</li>
											<li>邮箱：jerome@freshport.com</li>
										</ul>
									</div>
								</dd>
	
							</dl>												
						</div>
					</div>
					
					<div id="detail_right">
						<div class="detail_item0 detail_item1" style="margin-right:0;">
							<dl>
								<dt>金融方案</dt>
								<dd>
								
									<div class="detail_right">
										<ul class="detail_item">
											<li>联系人：张群</li>
											<li>电话：13301854275</li>
											<li>邮箱：zhangqun567@icloud.com</li>
										</ul>
									</div>
								</dd>
	
							</dl>
							<dl>
								<dt></dt>
								<dd>
								
									<div class="detail_right">
										<ul class="detail_item">
											<li>联系人：范洁</li>
											<li>电话：13681642094</li>
											<li>邮箱：sh-fan@foxmail.com</li>
										</ul>
									</div>
								</dd>
	
							</dl>
					</div>
					</div>
				</div>
			
				<div style="clear:both"></div>
			</div>
		</div>	
		<!-- 主体 -->

		<!--页脚-->
		<div id="footer">
			备案/许可证号 沪ICP备15035308&nbsp;&nbsp;|&nbsp;&nbsp;2015 Freshport.com. All rights reserved
		
		</div>
		<!-- 页脚-->

	
	</div>

</body>
	



</html>