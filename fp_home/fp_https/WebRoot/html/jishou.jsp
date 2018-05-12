<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/base/include/basePage.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">

	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

		<meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
		<title>寄售贸易二次征税服务</title>
		<link rel="shortcut icon" href="${applicationScope.sysConfigs['sysPath']}/style/style-common/images/favicon.ico">
		<link type="text/css" rel="stylesheet" href="${applicationScope.sysConfigs['sysPath']}/style/style-common/css/base.css">

		<style>
			#container .pic {
				margin: 10px;
				background: #000000;
				height: 240px;
				background: url("${applicationScope.sysConfigs['sysPath']}/style/style-common/images/jishou/banner.jpg") center no-repeat;
				background-size: cover;
			}
			
			.first {
				margin: 10px 170px;
				background: url("${applicationScope.sysConfigs['sysPath']}/style/style-common/images/jishou/beijing.jpg") left top no-repeat;
				padding-left: 160px;
				font-size: 18px;
				line-height: 1.5em;
				text-align: left;
				color: #666;
			}
			
			.second {
				margin: 10px 170px;
				height: 400px;
				font-size: 18px;
				line-height: 1.5em;
				text-align: left;
			}
			
			.second_left {
				background: url("${applicationScope.sysConfigs['sysPath']}/style/style-common/images/jishou/core.jpg") right center no-repeat;
				width: 38.8%;
				height: 100%;
				float: left;
			}
			
			.second_right {
				width: 56.2%;
				;
				height: 100%;
				float: left;
				box-sizing: border-box;
				padding-left: 5%;
			}
			
			.second_item {
				height: 80px;
				line-height: 76px;
				background: url("${applicationScope.sysConfigs['sysPath']}/style/style-common/images/jishou/core1.jpg") left center no-repeat;
				padding-left: 80px;
			}
			
			.second_item:nth-of-type(2) {
				background: url("${applicationScope.sysConfigs['sysPath']}/style/style-common/images/jishou/core2.jpg") left center no-repeat;
			}
			
			.second_item:nth-of-type(3) {
				background: url("${applicationScope.sysConfigs['sysPath']}/style/style-common/images/jishou/core3.jpg") left center no-repeat;
			}
			
			.second_item:nth-of-type(4) {
				background: url("${applicationScope.sysConfigs['sysPath']}/style/style-common/images/jishou/core4.jpg") left center no-repeat;
			}
			
			.second_item:nth-of-type(5) {
				background: url("${applicationScope.sysConfigs['sysPath']}/style/style-common/images/jishou/core5.jpg") left center no-repeat;
			}
			/*******/
			
			.table {
				margin: 10px 140px;
			}
			
			.table_top {
				background: #4c8aa5;
				height: 42px;
				line-height: 42px;
				color: #fff;
			}
			
			.table_top a {
				text-align: center;
			}
			
			.table a {
				border-right: 1px solid #ddd;
				box-sizing: border-box;
			}
			
			.left {
				border-left: 1px solid #ddd;
			}
			
			.table_top .left,
			.table_top .right {
				width: 50%;
				height: 100%;
				float: left;
				color: #fff;
				font-size: 20px;
			}
			
			.table_top .left {
				border-right: none;
			}
			
			.table_top .right {
				width: 49%;
				border-right: none;
			}
			
			.table_tr {
				height: 150px;
				color: #666;
				border: 1px solid #ddd;
				border-top: none;
				overflow: hidden;
			}
			
			.table_body .left,
			.table_body .right {
				color: #666;
				font-size: 18px;
				line-height: 36px;
				float: left;
				width: 25%;
				padding-left: 24%;
				height: 90%;
				padding-top: 20px;
			}
			
			.table_body .right {
				border-left: 1px solid #ddd;
			}
			
			.table_body .right li,
			.table_body .left li {
				list-style: disc;
			}
			
			.table_body .left {
				border-left: 1px solid #ddd;
				background: url(${applicationScope.sysConfigs['sysPath']}/style/style-common/images/jishou/compare1.gif) 30px center no-repeat;
			}
			
			.table_body .right {
				border-left: 1px solid #ddd;
				background: url(${applicationScope.sysConfigs['sysPath']}/style/style-common/images/jishou/compare2.gif) 30px center no-repeat;
			}
			
			@media all and (max-width: 1366px) {
				.first,
				.second,
				.table {
					margin: 10px 60px;
				}
			}
			
			@media all and (max-width: 1068px) {
				#main {
					width: 900px;
				}
				.first,
				.second,
				.table {
					margin: 10px 30px;
				}
				.spec {
					line-height: 36px;
				}
			}
			
			@media all and (max-width: 930px) {
				#main {
					width: 800px;
				}
				.first,
				.second,
				.table {
					margin: 10px 30px;
				}
				.second_left {
					background-size: contain;
				}
				.second_right {
					padding-left: 20px;
				}
			}
			
			@media all and (max-width: 830px) {
				#main {
					width: 700px;
				}
				.first,
				.second,
				.table {
					margin: 10px 30px;
				}
				.second_left {
					background-size: contain;
				}
				.second_right {
					padding-left: 20px;
				}
				.spec1 {
					line-height: 36px;
				}
			}
			
			@media all and (max-width: 720px) {
				#main {
					width: 600px;
				}
				.first,
				.second,
				.table {
					margin: 10px 0px;
				}
				.outline .title_bg1,
				.outline .title_bg2 {
					width: 0px;
				}
				.spec2 {
					line-height: 36px;
				}
				.table_body .left {
					background: url(${applicationScope.sysConfigs['sysPath']}/style/style-common/images/jishou/compare1.gif) center top no-repeat;
				}
				.table_body .right {
					background: url(${applicationScope.sysConfigs['sysPath']}/style/style-common/images/jishou/compare2.gif) center top no-repeat;
				}
				.table_body .left,
				.table_body .right {
					width: 38%;
					padding-left: 10%;
					height: 100%;
					padding-top: 22%;
				}
				.table_body .right {
					width: 32%;
					padding-left: 16%;
				}
				.table_tr {
					height: 230px;
				}
				.table a {
					padding-left: 0;
				}
				.table_top .left,
				.table_top .right {
					font-size: 18px;
				}
				#footer {
					font-size: 12px;
					line-height: 22px;
				}
			}
			
			@media all and (max-width: 620px) {
				#main {
					width: 500px;
				}
				.spec3 {
					line-height: 36px;
				}
				.table_body .left,
				.table_body .right {
					padding-top: 23%;
				}
				.table_top .left,
				.table_top .right {
					font-size: 14px;
				}
			}
			
			@media all and (max-width: 530px) {
				#main {
					width: 400px;
				}
				.second_left {
					width: 100%;
					height: 38.80%;
					background: url("${applicationScope.sysConfigs['sysPath']}/style/style-common/images/jishou/core.jpg") center center no-repeat;
					background-size: contain;
				}
				.second_right {
					width: 100%;
					height: 61.2%;
				}
				.spec2 {
					line-height: 72px;
				}
				.second {
					height: 700px;
				}
				.table_body .left,
				.table_body .right {
					padding-top: 26%;
				}
			}
			
			@media all and (max-width: 420px) {
				#main {
					width: 300px;
				}
				.first {
					padding-left: 0px;
					background: url('');
					text-align: center;
				}
				.spec2 {
					line-height: 36px;
				}
				.table_body .left,
				.table_body .right {
					padding-top: 36%;
				}
				.table_top .left,
				.table_top .right,
				.table_body .left,
				.table_body .right {
					font-size: 12px;
				}
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
				寄售贸易二次征税背景 
				<span class="title_bg2">&nbsp;</span>
				
			</h2>
					<span class="en">Background for Consignment Twice Taxation</span>
				</div>
				<div class="first">
					进口水果进口时没有确定结算价格而是在销售完成后进行结算（结算金额=销售额-佣金-税金-费用），在进口时只能按海关估价征税，但是海关估价与实际结算价格并不相同，存在征税的法律风险。为满足相应客户的需求，保障行业长期健康发展，在上海海关的指导和监督下，生鲜港于2015年建立了一个综合开放创新性的第三方寄售信息管理平台，引导进口商依法按实纳税。
					<br/><br/>
				</div>

				<!-- 第二部分 -->
				<div class="outline">
					<h2 class="title">
				<span class="title_bg1">&nbsp;</span>
				寄售贸易二次征税核心功能
				<span class="title_bg2">&nbsp;</span>
				
			</h2>
					<span class="en">Core functions for Consignment Twice Taxation</span>
				</div>

				<div class="second">
					<div class="second_left"></div>
					<div class="second_right">
						<div class="second_item  spec2">由进口货代录入合同等基本信息；</div>
						<div class="second_item spec3">进口商提供在线结算报告；</div>
						<div class="second_item spec3">出口商在线确认结算报告；</div>
						<div class="second_item spec1">系统生成二次征税报告 ，海关审核；</div>
						<div class="second_item spec">生鲜港平台辅助海关对价格及费用做动态监管;</div>

					</div>
					<div style="clear:both;"></div>
				</div>

				<!-- 第三部分 -->
				<br />
				<div class="outline">
					<h2 class="title">
				<span class="title_bg1">&nbsp;</span>
				寄售贸易二次征税与传统估价征税的比较
				<span class="title_bg2">&nbsp;</span>
				
			</h2>
					<span class="en">Comparison between Consignment Twice Taxation and traditional Price Evaluation Taxation</span>
				</div>

				<div class="table">

					<div class="table_top">
						<a class="left">传统估计征税</a>
						<a class="right">生鲜港寄售贸易二次征税</a>
					</div>
					<div class="table_body">
						<div class="table_tr">
							<ul class="left">
								<li>存在法律风险</li>
								<li>人为导致成本不公平</li>
							</ul>
							<ul class="right">
								<li>符合法律规定</li>
								<li>合理如实征税</li>
								<li>提高通关效率</li>
							</ul>
						</div>

						<br/>
					</div>
					<div class="clear:both"></div>
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