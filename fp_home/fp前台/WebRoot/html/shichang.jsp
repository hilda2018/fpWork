<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/base/include/basePage.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">

	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
		<title>批发市场信息管理</title>
		<link rel="shortcut icon" href="${applicationScope.sysConfigs['sysPath']}/style/style-common/images/favicon.ico">
		<link type="text/css" rel="stylesheet" href="${applicationScope.sysConfigs['sysPath']}/style/style-common/css/base.css">

		<style>
			#container .pic {
				margin: 10px;
				background: #000000;
				height: 240px;
				background: url("${applicationScope.sysConfigs['sysPath']}/style/style-common/images/shichang/banner.jpg") center no-repeat;
				background-size: cover;
			}
			
			.first {
				margin: 10px auto;
				font-size: 18px;
				line-height: 1.5em;
				text-align: center;
				color: #666;
			}
			
			.second {
				margin: 10px 140px;
				height: 460px;
				background: url("${applicationScope.sysConfigs['sysPath']}/style/style-common/images/shichang/1.jpg") center no-repeat;
			}
			
			.shichang_item {
				width: 50%;
				height: 250px;
				float: left;
				position: relative;
			}
			
			.shichang_item0,
			.shichang_item1,
			.shichang_item2,
			.shichang_item3 {
				width: 240px;
				height: 240px;
			}
			
			.shichang_item0 h3,
			.shichang_item1 h3,
			.shichang_item2 h3,
			.shichang_item3 h3 {}
			
			.shichang_item0 p,
			.shichang_item1 p,
			.shichang_item2 p,
			.shichang_item3 p {
				font-size: 18px;
				line-height: 1.5em;
				padding: 10px;
				color: #fff;
				border-radius: 10px;
				height: 105px;
			}
			
			.shichang_item0,
			.shichang_item2 {
				position: absolute;
				left: 46px;
			}
			
			.shichang_item1,
			.shichang_item3 {
				position: absolute;
				right: 46px;
			}
			
			.shichang_item0 h3 {
				color: #43b087;
				text-align: right;
			}
			
			.shichang_item1 h3 {
				color: #f66e14;
			}
			
			.shichang_item2 h3 {
				color: #eb3158;
				text-align: right;
			}
			
			.shichang_item3 h3 {
				color: #9d56c7;
			}
			
			.shichang_item0 p {
				border: 2px solid #43b087;
				background: #55c89d;
			}
			
			.shichang_item1 p {
				border: 2px solid #f66e14;
				background: #e4854d;
			}
			
			.shichang_item2 p {
				border: 2px solid #eb3158;
				background: #de5c78;
			}
			
			.shichang_item3 p {
				border: 2px solid #9d56c7;
				background: #b177d6;
			}
			
			@media all and (max-width: 1366px) {
				.second {
					margin: 10px 10px;
				}
			}
			
			@media all and (max-width: 1026px) {
				.shichang_item0,
				.shichang_item1,
				.shichang_item2,
				.shichang_item3 {
					width: 200px;
					height: 200px;
				}
				.shichang_item0,
				.shichang_item2 {
					left: 16px;
				}
				.shichang_item1,
				.shichang_item3 {
					right: 16px;
				}
			}
			
			@media all and (max-width: 920px) {
				.shichang_item0,
				.shichang_item1,
				.shichang_item2,
				.shichang_item3 {
					width: 160px;
					height: 200px;
				}
				.shichang_item0 p,
				.shichang_item1 p,
				.shichang_item2 p,
				.shichang_item3 p {
					font-size: 16px;
				}
				.second h3 {
					font-size: 16px;
				}
			}
			
			@media all and (max-width: 820px) {
				.second {
					background-size: 300px;
				}
			}
			
			@media all and (max-width: 820px) {
				.second {
					background-size: 230px;
				}
				.shichang_item0,
				.shichang_item2 {
					left: 6px;
				}
				.shichang_item1,
				.shichang_item3 {
					right: 6px;
				}
			}
			
			@media all and (max-width: 618px) {
				.second {
					background: url(${applicationScope.sysConfigs['sysPath']}/style/style-common/images/shichang/1.jpg) center 47% no-repeat;
					background-size: 230px;
				}
				.second {
					margin: 10px 0px;
				}
				.shichang_item0,
				.shichang_item2 {
					left: 0px;
				}
				.shichang_item1,
				.shichang_item3 {
					right: 0px;
				}
				.shichang_item0 p,
				.shichang_item1 p,
				.shichang_item2 p,
				.shichang_item3 p {
					font-size: 14px;
					height: 85px;
				}
				.second h3 {
					font-size: 14px;
				}
				.outline .title_bg1,
				.outline .title_bg2 {
					width: 0px;
				}
			}
			
			@media all and (max-width: 528px) {
				.second {
					background: url(${applicationScope.sysConfigs['sysPath']}/style/style-common/images/shichang/1.jpg) center 47% no-repeat;
					background-size: 230px;
				}
				.second {
					margin: 10px 0px;
				}
				.shichang_item0,
				.shichang_item2 {
					left: 0px;
				}
				.shichang_item1,
				.shichang_item3 {
					right: 0px;
				}
				.shichang_item0 p,
				.shichang_item1 p,
				.shichang_item2 p,
				.shichang_item3 p {
					font-size: 14px;
					height: 85px;
				}
				.second h3 {
					font-size: 14px;
				}
				.shichang_item {
					height: 280px;
				}
				.navbar {
					width: 246px;
					padding-top: 10px;
				}
				#footer {
					font-size: 12px;
					line-height: 22px;
				}
			}
			
			@media all and (max-width: 432px) {
				.second {
					background: url('');
				}
				.shichang_item0,
				.shichang_item1,
				.shichang_item2,
				.shichang_item3 {
					width: 100%;
					height: 200px;
				}
				.shichang_item {
					width: 100%;
				}
				.shichang_item {
					height: 120px;
				}
				.second h3 {
					text-align: left;
				}
				.shichang_item0 p,
				.shichang_item1 p,
				.shichang_item2 p,
				.shichang_item3 p {
					font-size: 14px;
					height: 45px;
				}
				#nav {
					background-size: 80px;
				}
				.navbar {
					width: 200px;
					padding-top: 0px;
				}
			}
			
			@media all and (max-width:326px) {
				.shichang_item0,
				.shichang_item1,
				.shichang_item2,
				.shichang_item3 {
					width: 100%;
					height: 220px;
				}
				.shichang_item {
					height: 130px;
				}
				.shichang_item0 p,
				.shichang_item1 p,
				.shichang_item2 p,
				.shichang_item3 p {
					font-size: 14px;
					height: 60px;
				}
				.second {
					height: 530px;
				}
				.first {
					font-size: 16px;
				}
				.navbar {
					width: 100%;
				}
				#nav {
					background-size: 0px;
				}
			}
			/* 效果 */
			
			.shichang_item0:hover p {
				background: #fff;
				color: #55c89d;
			}
			
			.shichang_item1:hover p {
				background: #fff;
				color: #e4854d;
			}
			
			.shichang_item2:hover p {
				background: #fff;
				color: #de5c78;
			}
			
			.shichang_item3:hover p {
				background: #fff;
				color: #b177d6;
			}
		</style>

	</head>

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
						<a href="about.jsp" target="_blank"> 关于我们 </a>
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
				<div class="pic"></div>
				<!-- 第一部分 -->
				<div class="outline">
					<h2 class="title">
				<span class="title_bg1">&nbsp;</span>
				批发市场信息管理系统介绍 
				<span class="title_bg2">&nbsp;</span>
				
			</h2>
					<span class="en">Introduction to wholesale market information management system</span>
				</div>
				<div class="first">
					提供货物进出市场、插拔电、吊柜等操作信息；<br/> 提供实时全面的账单查询功能；
					<br/><br/>
				</div>

				<!-- 第一部分 -->
				<div class="outline">
					<h2 class="title">
				<span class="title_bg1">&nbsp;</span>
				批发市场信息管理模块介绍
				<span class="title_bg2">&nbsp;</span>
				
			</h2>
					<span class="en">Module introduction to wholesale market information management system</span>
				</div>
				<div class="second">
					<div class="shichang1">
						<div class="shichang_item">
							<div class="shichang_item0">
								<h3>市场操作跟踪</h3>
								<p>实时查询:停留天数、货物进入或离开市场情况、插拔电灯操作</p>
							</div>
						</div>
						<div class="shichang_item">
							<div class="shichang_item1">
								<h3>预报管理功能</h3>
								<p>即时的给市场预报您未进场的货物，市场可以提前安排，提高效率！</p>
							</div>
						</div>
						<div style="clear:both"></div>
					</div>

					<div class="shichang1">
						<div class="shichang_item">
							<div class="shichang_item2">
								<h3>货物相关费用</h3>
								<p>账单查询：实时了解货物进入市场后产生的每一笔费用。</p>
							</div>
						</div>
						<div class="shichang_item">
							<div class="shichang_item3">
								<h3>租金水电账单</h3>
								<p>水电费查询：查询水电费明细。</p>
							</div>
						</div>
						<div style="clear:both"></div>
					</div>

				</div>
				<br />
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