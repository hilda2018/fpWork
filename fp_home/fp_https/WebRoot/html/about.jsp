<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/base/include/basePage.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">

	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
		<title>生鲜港--关于我们</title>
		<link rel="shortcut icon" href="${applicationScope.sysConfigs['sysPath']}/style/style-common/images/favicon.ico">
		<script type="text/javascript" src="../../style/style-module/index/config.js"></script>

	</head>
	<style>
		html,
		body {
			width: 100%;
			background: url(${applicationScope.sysConfigs['sysPath']}/style/style-common/images/bg.png);
			font-family: "微软雅黑";
		}
		
		body {
			padding: 0;
			margin: 0;
		}
		
		ul,
		li {
			list-style: none;
			margin: 0;
			padding: 0;
		}
		
		p {
			padding: 0;
			margin: 0;
		}
		
		a {
			text-decoration: none;
			color: #333;
		}
		/* 布局 css   width 1024 需要做适配     */
		
		#main {
			padding-top: 74px;
			padding-bottom: 54px;
			background: transparent;
			width: 1280px;
			height: 100%;
			box-sizing: border-box;
			margin: 0 auto;
			position: relative;
		}
		
		#nav {
			width: 100%;
			height: 64px;
			background: #fff;
			position: absolute;
			top: 0;
			left: 0;
			margin-bottom: 20px;
			border-radius: 5px;
		}
		
		#footer {
			width: 100%;
			height: 24px;
			background: #fff;
			position: absolute;
			bottom: 0;
			left: 0;
			border-radius: 5px;
		}
		
		#container {
			width: 100%;
			height: 100%;
			box-sizing: border-box;
			border-radius: 5px;
			padding: 10px;
			background: url(${applicationScope.sysConfigs['sysPath']}/style/style-common/images/contact_us.png) no-repeat 20px 38px #fff;
		}
		/* nav css */
		
		#nav {
			background: url(${applicationScope.sysConfigs['sysPath']}/style/style-common/images/logo.png) #fff no-repeat 6px center;
		}
		
		.nav_img {
			width: 50%;
			text-decoration: none;
			float: left;
		}
		
		.nav_img span {
			display: inline-block;
			margin-left: 136px;
			line-height: 64px;
			color: #fa9d2a;
			font-size: 16px;
			float: left;
			font-weight: 560;
		}
		
		.navbar {
			width: 378px;
			float: right;
			height: 100%;
		}
		
		.navbar li {
			list-style: none;
			float: left;
			padding: 10px;
			box-sizing: border-box;
			height: 100%;
			line-height: 44px;
		}
		
		.navbar a {
			text-decoration: none;
			color: #333;
			height: 100%;
			line-height: 100%;
		}
		/*footer css*/
		
		#footer {
			width: 100%;
			text-align: center;
			line-height: 44px;
			height: 44px;
			padding: 0;
			margin: 0;
			color: #666;
			font-size: 14px;
		}
		/* contact_us  **/
		
		#container {
			width: 100%;
			height: 100%;
			box-sizing: border-box;
			border-radius: 5px;
			padding: 10px;
			background: url(${applicationScope.sysConfigs['sysPath']}/style/style-common/images/about_us.png) no-repeat 20px 38px #fff;
		}
		
		#container {
			padding-left: 112px;
			padding-top: 20px;
		}
		
		#content {
			width: 100%;
			height: 100%;
			box-sizing: border-box;
		}
		
		.title {
			border-bottom: 1px solid #666;
			height: 50px;
		}
		
		.title .circle {
			height: 8px;
			width: 8px;
			border-radius: 50%;
			background: #000;
			float: left;
			position: relative;
			left: -16px;
			top: 20px;
		}
		
		.title .en {
			font-size: 12px;
			margin-left: 4px;
			font-weight: normal;
			color: #888;
		}
		
		.title,
		.title span {
			line-height: 42px;
			height: 42px;
		}
		
		.title h2 {
			float: left;
			font-size: 22px;
			line-height: 42px;
			height: 42px;
			padding: 0;
			margin: 0;
			font-weight: normal;
		}
		
		.title .title_right {
			float: right;
			font-size: 14px;
			margin-top: 4px;
		}
		
		.intro {
			width: 100%;
			box-sizing: border-box;
			margin-top: 20px;
		}
		
		.intro_left {
			height: 320px;
			width: 30%;
			box-sizing: border-box;
			float: left;
			background: url("${applicationScope.sysConfigs['sysPath']}/style/style-common/images/about.png") no-repeat center;
			margin-right: 2%;
			border: 1px solid #e9e9e9;
			background-size: cover;
		}
		
		.intro_right {
			height: 320px;
			width: 68%;
			overflow: hidden;
			box-sizing: border-box;
			padding-left: 20px;
			float: left;
			padding-top: 10px;
			padding-bottom: 10px;
		}
		
		.intro_right .text {
			font-size: 16px;
			line-height: 2em;
			height: 284px;
			white-spacing: 4px;
			text-align: justify;
			letter-spacing: 1.2px;
		}
		
		.index-container {
			width: 100%;
			min-height: 220px;
			height: auto;
		}
		
		.service_item {
			width: 30%;
			float: left;
			box-sizing: border-box;
			padding: 10px;
			margin-right: 5%;
			text-align: center;
			border: 1px solid #e9e9e9;
			height: 100%;
		}
		
		.last {
			margin-right: 0;
		}
		
		.service_text {
			font-size: 16px;
			line-height: 36px;
			color: #969696;
			min-height: 72px;
			overflow: hidden;
		}
		
		.service_icon {
			display: inline-block;
			width: 60px;
			height: 60px;
			background: #f00;
			margin: 0 auto;
		}
		
		.icon1 {
			background: url(${applicationScope.sysConfigs['sysPath']}/style/style-common/images/entrance.png) no-repeat;
		}
		
		.icon2 {
			background: url(${applicationScope.sysConfigs['sysPath']}/style/style-common/images/service.png) no-repeat;
		}
		
		.icon3 {
			background: url(${applicationScope.sysConfigs['sysPath']}/style/style-common/images/financial.png) no-repeat;
		}
		
		.icon1,
		.icon2,
		.icon3 {
			background-size: cover;
			margin-top: 6px;
		}
		
		h3 {
			margin: 12px;
		}
		
		h2 {
			margin: 0;
			margin-bottom: 12px;
			font-size: 20px;
			font-weight: normal;
		}
		
		.section_title {
			max-width: 383px;
			height: 46px;
			margin: 0px auto;
			border: 1px solid #bbb;
			position: relative;
			margin-bottom: 50px;
		}
		
		.section_title .theme {
			max-width: 273px;
			height: 42px;
			background: #FFF;
			position: absolute;
			left: 88px;
			bottom: -10px;
			z-index: 9;
			font-size: 24px;
			color: #333333;
			text-align: center;
		}
		
		.service {
			margin-top: 40px;
		}
		
		.section_title span {
			display: inline-block;
			width: 100%;
			height: 21px;
			line-height: 21px;
			text-align: center;
			font-size: 14px;
			font-weight: normal;
		}
		
		.navbar .active {
			color: #f97f00;
		}
		/* 媒询 */
		
		@media all and (max-width: 1366px) {
			#main {
				width: 1024px;
			}
			.intro_left {
				height: 388px;
			}
			.intro_right {
				height: 388px;
			}
			.intro_right .text {
				height: 286px;
			}
		}
		
		@media all and (max-width: 1048px) {
			#main {
				width: 900px;
			}
			.index-container {
				height: 250px;
			}
			.intro_right .text {
				font-size: 15px;
			}
			h2 {
				font-size: 18px;
				margin-bottom: 8px;
			}
			.intro_left {
				height: 444px;
			}
			.intro_right {
				height: 444px;
			}
			.intro_right .text {
				height: 356px;
			}
		}
		
		@media all and (max-width: 920px) {
			#main {
				width: 800px;
			}
		}
		
		@media all and (max-width: 816px) {
			#main {
				width: 700px;
			}
			.index-container {
				height: 270px;
			}
			.section_title .theme {
				left: 90px;
			}
			.intro_right .text {
				font-size: 14px;
			}
			.intro_right {
				padding-left: 10px;
			}
			.intro_left {
				height: 484px;
			}
			.intro_right {
				height: 484px;
			}
		}
		
		@media all and (max-width: 706px) {
			#main {
				width: 600px;
			}
			.list_lh li p span {
				display: none;
			}
			input[name="email"] {
				width: 40%!important;
			}
			.nav_img span {
				display: none;
			}
			#footer {
				font-size: 12px;
				line-height: 22px;
			}
			#container {
				background: url('') #fff;
			}
			#container {
				padding-left: 22px;
			}
			.intro_right {
				padding-left: 10px;
			}
			.intro_right .text {
				font-size: 14px;
				line-height: 28px;
			}
			.section_title .theme {
				left: 80px;
			}
			.intro_left {
				height: 486px;
			}
			.intro_right {
				height: 486px;
			}
		}
		
		@media all and (max-width: 602px) {
			#main {
				width: 500px;
			}
			.index-container {
				height: 150px;
			}
			.service_item {
				width: 100%;
			}
			.intro_right h2 {
				font-size: 18px;
			}
			.intro_right .text {
				font-size: 14px;
				line-height: 23px;
				height: 156px;
			}
			.section_title .theme {
				left: 100px;
			}
		}
		
		@media all and (max-width: 520px) {
			#main {
				width: 400px;
			}
			.title .title_right {
				float: left;
			}
			.title,
			.title span {
				height: 82px;
			}
			#nav {
				background: url('') #fff no-repeat 6px center;
			}
			.intro_right h2 {
				font-size: 18px;
			}
			.intro_right .text {
				font-size: 14px;
				line-height: 29px;
				height: 156px;
			}
			.section_title .theme {
				left: 80px;
			}
			.intro_left {
				height: 692px;
			}
			.intro_right {
				height: 692px;
			}
			.intro_right .text {
				height: 580px;
			}
		}
		
		@media all and (max-width: 420px) {
			#main {
				width: 300px;
			}
			.section_title .theme {
				left: 30px;
			}
			.intro_left {
				height: 952px;
			}
			.intro_right {
				height: 952px;
			}
		}
	</style>

	<body>

		<div id="main">

			<!--导航-->
			<div id="nav">
				<a class="nav_img" href="${applicationScope.sysConfigs['sysPath']}" target="_blank">
					<span>信&nbsp;息&nbsp;服&nbsp;务&nbsp;平&nbsp;台</span>
				</a>
				<ul class="navbar">
					<li>
						<a href="${applicationScope.sysConfigs['sysPath']}"> 首页 </a>
					</li>
					<li class="hr">
						<a href="#"> | </a>
					</li>
					<li>
						<a href="${infoPath}/fp-online-infoshow/home/index" target="_self"> English </a>
					</li>
					<li class="hr">
						<a href="#"> | </a>
					</li>
					<li>
						<a href="#" class="active"> 关于我们 </a>
					</li>
					<li class="hr">
						<a href="#"> | </a>
					</li>
					<li>
						<a href="contact.jsp" target="_blank"> 联系我们 </a>
					</li>

				</ul>
			</div>

			<!--导航-->

			<!-- 主体 -->
			<div id="container">
				<div id="content">
					<div class="title">
						<h2><span class="circle"></span>关于我们<span class="en">ABOUT&nbsp;US</span></h2>
						<div class="title_right">您当前的位置：
							<a href="${applicationScope.sysConfigs['sysPath']}" target="_blank">首页</a> &nbsp;>&nbsp; 关于我们</div>
						<div style="clear: both;"></div>
					</div>
					<div class="intro">
						<h2 class="section_title"><div class="theme"><span>Company Profile</span>公司简介</div></h2>
						<div class="intro_left"></div>
						<div class="intro_right">
							<h2>生鲜港是专业进口水果服务商</h2>
							<p class="text">
								生鲜港电子商务有限公司立足于上海辉展市场（一级进口水果批发市场），由上海欧恒进出口有限公司于2015年7月发起成立，运用互联网技术，对传统进口水果行业管理的升级换代，提高各个角色之间的信息互通和服务更加便捷安全，在平台信息化数据化的基础上帮助各参与方提高管理效率；<br/>
								<span style="display: block;height:10px;width:100%;"></span> 生鲜港拥有资深管理团队，作为上海水果行业最专业的服务商，在货运、进出口报关报检、外贸服务、批发市场管理等方面服务超过15年，积累了丰富的经验和资源。生鲜港充分了解贸易管理中产生的问题，结合实际需求与用户共同设计开发系统服务，服务内容包括:业务管理系统，寄售二次征税，金融服务，批发市场信息管理，外汇支付管理，货代物流管理。
								<br/>
								<span style="display: block;height:10px;width:100%;"></span> 如果您希望进一步了解生鲜港，欢迎和我们交流，期待与您合作。
							</p>
						</div>
						<div class="" style="clear:both;"></div>
					</div>

					<div>
						<div class="service">
							<h2 class="section_title"><div class="theme"><span>Our Service</span>我们的服务</div></h2>

							<!--index-container start-->
							<div class="index-container">

								<div class="service_item">
									<i class="service_icon icon1"></i>
									<h3 class="service_title">信息入口</h3>
									<div class="service_text">
										<p>可实时管理进口清关，物流，市场，支付，寄售二次征税等信息</p>
									</div>
								</div>

								<div class="service_item">
									<i class="service_icon icon2"></i>
									<h3 class="service_title">贸易精细化管理服务</h3>
									<div class="service_text">
										<p>以软件为核心<br/>提高效率，控制成本</p>
									</div>
								</div>

								<div class="service_item last">
									<i class="service_icon icon3"></i>
									<h3 class="service_title">专业金融服务</h3>
									<div class="service_text">
										<p>帮助企业快速发展</p>
									</div>
								</div>
								<div class="" style="clear:both;"></div>

							</div>
							<!--index-container end-->

							<div class="" style="clear:both;"></div>
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