<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/base/include/basePage.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">

	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
		<title>生鲜港公告列表</title>
		<link rel="shortcut icon" href="${applicationScope.sysConfigs['sysPath']}/style/style-common/images/favicon.ico">
		<link type="text/css" rel="stylesheet" href="${applicationScope.sysConfigs['sysPath']}/style/style-common/css/base.css">
	</head>
	<style>
		/* new clearfix */
		
		.clearfix:after {
			visibility: hidden;
			display: block;
			font-size: 0;
			content: " ";
			clear: both;
			height: 0;
		}
		
		* html .clearfix {
			zoom: 1;
		}
		/* IE6 */
		
		*:first-child+html .clearfix {
			zoom: 1;
		}
		/* IE7 */
		
		.hidden {
			display: none;
		}
		
		.last {
			border-bottom: none !important;
		}
		/* page */
		
		.page {
			display: table;
			margin: 0 auto;
			background: #fff;
			-moz-box-shadow: 0 5px 20px #CCCCCC;
			-webkit-box-shadow: 0 5px 20px #CCCCCC;
			box-shadow: 0 5px 20px #CCCCCC;
		}
		
		.link {
			margin-top: 30px;
			float: right;
			text-align: right;
			_width: 718px;
		}
		
		.link li {
			float: left;
			display: inline;
			margin-left: 60px;
		}
		
		.link li a {
			color: #4F4E4E;
			font-size: 16px;
			font-weight: 500;
			padding-bottom: 6px;
			display: block;
		}
		
		.link li.active {
			border-bottom: 2px solid #9BCD57;
		}
		
		.link li.active a {
			color: #0066FF
		}
		
		.link li:hover {
			border-bottom: 2px solid #9BCD57;
			color: #0066FF
		}
		
		.link li a:hover {
			color: #0066FF
		}
		
		.box {
			width: 940px;
			margin: 18px auto 0 auto;
		}
		
		.event_year {
			width: 88px;
			border-bottom: 2px solid #DDD;
			text-align: center;
			float: left;
			margin-top: 10px;
		}
		
		.event_year li {
			height: 40px;
			line-height: 40px;
			background: #FFF;
			margin-bottom: 1px;
			font-size: 18px;
			color: #828282;
			cursor: pointer;
		}
		
		.event_year li.current {
			width: 88px;
			background: #9BCD57 url('${applicationScope.sysConfigs['sysPath']}/style/style-common/images/jian.png') 87px 0 no-repeat;
			color: #FFF;
			text-align: left;
			padding-left: 9px;
		}
		
		label {
			width: 100%;
			height: 100%;
			display: inline-block;
		}
		
		.event_list {
			width: 850px;
			float: right;
			background: url('${applicationScope.sysConfigs['sysPath']}/style/style-common/images/dian3.png') 139px 0 repeat-y;
			margin: 10px 0 20px 0;
		}
		
		.event_list h3 {
			margin: 0 0 10px 132px;
			font-size: 24px;
			font-family: Georgia;
			color: #9BCD57;
			padding-left: 25px;
			background: url('${applicationScope.sysConfigs['sysPath']}/style/style-common/images/jian.png') 0 -45px no-repeat;
			height: 38px;
			line-height: 30px;
			font-style: italic;
		}
		
		.event_list li {
			background: url('${applicationScope.sysConfigs['sysPath']}/style/style-common/images/jian.png') 136px -80px no-repeat;
		}
		
		.event_list li span {
			width: 127px;
			text-align: right;
			display: block;
			float: left;
			margin-top: 10px;
		}
		
		.event_list li p {
			width: 680px;
			margin-left: 24px;
			display: inline-block;
			padding-left: 10px;
			background: url('${applicationScope.sysConfigs['sysPath']}/style/style-common/images/jian.png') -21px 0 no-repeat;
			line-height: 25px;
			_float: left;
		}
		
		.event_list li p span {
			width: 650px;
			font-size: 12px;
			text-align: left;
			border-bottom: 2px solid #DDD;
			padding: 10px 15px;
			background: #FFF;
			margin: 0;
			display: block;
		}
		
		.event_list li p span em {
			font-style: normal;
			font-size: 16px;
			font-weight: 400;
			color: #000;
		}
		
		body,
		html {
			width: 100%;
			min-height: 100%;
			background: #F0F0F0 url('${applicationScope.sysConfigs['sysPath']}/style/style-common/images/dian2.png') repeat-x;
		}
		
		.page2,
		#container {
			width: 100%;
			background: #F0F0F0;
		}
		
		html {
			position: relative;
		}
		
		#footer {
			position: absolute;
			bottom: 0;
		}
		
		#main {
			paddint-bottom: 20px;
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
						<a href="${applicationScope.sysConfigs['sysPath']}" target="_blank"> 首页</a>
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
			<div id="container" class="page2">

				<div class="box">
					<ul class="event_year" id="event_year">

					</ul>

					<ul class="event_list" id="event_list">

					</ul>

					<div class="clearfix"></div>

				</div>

			</div>
			<!-- 主体 -->

		</div>

		<!--页脚-->
		<div id="footer">
			备案/许可证号 沪ICP备15035308&nbsp;&nbsp;|&nbsp;&nbsp;2015 Freshport.com. All rights reserved

		</div>
		<!-- 页脚-->
		<script type="text/javascript" src="${applicationScope.sysConfigs['sysPath']}/style/style-common/library/jquery/jquery.js"></script>
		<script type="text/javascript">
			$(function() {

				$.ajax({
					url: "data.txt",
					success: function(data) {

						var newsListData = $.parseJSON(data);

						var $event_year = $('#event_year');
						var $event_list = $('#event_list');

						var allYears = [];
						var allDataSort = [];
						var num = 0;

						$.each(newsListData, function(i, elem) {

							var years = newsListData[i].time.slice(0, 6);

							if($.inArray(years, allYears) == -1) {
								allYears.push(years);
								var newArray = [];
								allDataSort[num] = {};
								allDataSort[num][years] = [];
								num++;

							}

							var newjson = {};

							newjson['date'] = newsListData[i].time.slice(6);
							newjson['title'] = newsListData[i].title;
							newjson['text'] = newsListData[i]['text'];

							allDataSort[num - 1][years].push(newjson);

						});

						allYears = allYears.sort(sortNumber);

						function sortNumber(a, b) {
							a = parseInt(a);
							b = parseInt(b);
							return b - a;
						}

						allYears = allYears.sort(sortNumber);

						var $ul = $('<ul></ul>');
						$.each(allYears, function(i, elem) {

							var key1 = allYears[i];

							if(i == 0) {
								var $newLi = $('<li class="current" ><label   for="' + allYears[i] + '">' + allYears[i].slice(0, 4) + '/' + allYears[i].slice(4) + '</label></li>');

							} else {

								var $newLi = $('<li><label for="' + allYears[i] + '">' + allYears[i].slice(0, 4) + '/' + allYears[i].slice(4) + '</label></li>');
							}

							$.each(allDataSort, function(i, elem) {

								for(var key0 in allDataSort[i]) {

									if(key0 == key1) {

										createItem(key0, allDataSort[i][key0]);
									}
								}

							});

							$event_year.append($newLi);

						})

						function createItem(key0, data0) {
							console.log(data0);

							var $div = $('<div></div>');
							var $h3 = $('<h3 id="' + key0 + '">' + key0.slice(0, 4) + '/' + key0.slice(4) + '</h3>');
							$div.append($h3);

							$.each(data0, function(i, elem) {

								var $li = $('<li></li>');
								var $span = $('<span>' + data0[i]["date"] + '日' + '</span>');
								var $p = $('<p><span><em>' + data0[i]["title"] + '</em><br/>' + data0[i]["text"] + '</span></p>');
								$li.append($span, $p);
								$div.append($li);

							});

							$event_list.append($div);

						}

						$('.event_year').find('li').delegate('label', 'click', function() {
							$('.event_year>li').removeClass('current');
							$(this).parent('li').addClass('current');
							var year = $(this).attr('for');
							$('#' + year).parent().prevAll('div').slideUp(800);
							$('#' + year).parent().slideDown(800).nextAll('div').slideDown(800);
						});

					}
				});

			});
		</script>
	</body>

</html>