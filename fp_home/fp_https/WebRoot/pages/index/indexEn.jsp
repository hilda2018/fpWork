<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/base/include/basePage.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>Fresh Port Shop</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@ include file="/base/common/jQuery.jsp"%>
	<link type="text/css" rel="stylesheet" href="${applicationScope.sysConfigs['sysPath']}/style/style-common/css/baseen.css">
	<link type="text/css" rel="stylesheet" href="${applicationScope.sysConfigs['sysPath']}/style/style-common/css/common.css">
	<link type="text/css" rel="stylesheet" href="${applicationScope.sysConfigs['sysPath']}/style/style-common/css/index.css">
	<link type="text/css" rel="stylesheet" href="${applicationScope.sysConfigs['sysPath']}/style/style-common/css/banner.css">
	<script type="text/javascript" src="${applicationScope.sysConfigs['sysPath']}/style/style-module/index/indexEn.js"></script>
	<script type="text/javascript" src="${applicationScope.sysConfigs['sysPath']}/style/style-module/index/slide.js"></script>
	<script type="text/javascript" src="${applicationScope.sysConfigs['sysPath']}/style/style-module/index/banner.js"></script>
  </head>
  
  <body>
  	<!-- 头部 S -->
	<%@ include file="/base/include/top.jsp"%>
	<!-- 头部 E -->
	
	<!-- 主体 S -->
	<div class="wrapper">
		<!-- main S -->
		<div class="main clearfix">
			<div class="category">
				<h4 class="c1">COMMODITY<a href="${applicationScope.sysConfigs['sysPath']}/pages/seller/sellerList.jsp" class="more">More</a></h4>
				<ul class="clearfix" id="product">
				</ul>
				<h4 class="c2">COUNTRY<a href="${applicationScope.sysConfigs['sysPath']}/pages/seller/sellerList.jsp" class="more">More</a></h4>
				<ul class="clearfix" id="Area">
				</ul>
				<h4 class="c3">TIME<a href="${applicationScope.sysConfigs['sysPath']}/pages/seller/sellerList.jsp" class="more">More</a></h4>
				<ul class="clearfix">
					<li>
					<div class="time">
						<div class="fl">					
						<select id="startTime">
							<option value ="1">January </option>
							<option value ="2">February </option>
							<option value ="3">March </option>
							<option value ="4">April </option>
							<option value ="5">May </option>
							<option value ="6">June </option>
							<option value ="7">July </option>
							<option value ="8">Aguest </option>
							<option value ="9">September </option>
							<option value ="10">October </option>
							<option value ="11">November </option>
							<option value ="12">December </option>
						</select>
						—
						<select id="endTime">
							<option value ="1">January </option>
							<option value ="2">February </option>
							<option value ="3">March </option>
							<option value ="4">April </option>
							<option value ="5">May </option>
							<option value ="6">June </option>
							<option value ="7">July </option>
							<option value ="8">Aguest </option>
							<option value ="9">September </option>
							<option value ="10">October </option>
							<option value ="11">November </option>
							<option value ="12">December </option>
						</select>
						</div>
						<button class="ser" onclick="turnSeller();"></button>
					</div>
				</li>
				</ul>
			</div>
			<div class="banner">
				<div class="banner1">
					<a href="#"><img src="${applicationScope.sysConfigs['sysPath']}/style/style-common/images/banner1.jpg" /></a>
			        <a href="#"><img src="${applicationScope.sysConfigs['sysPath']}/style/style-common/images/banner2.jpg" /></a>
			        <a href="#"><img src="${applicationScope.sysConfigs['sysPath']}/style/style-common/images/banner3.jpg" /></a>
			        <div class="banner-n">
			            <div class="banner-n2">
			            	<a></a>
			            	<a></a>
			            	<a></a>
			            </div>
			        </div>
		        </div>
				<div class="visible-info">
					<ul  class="clearfix" id="ProductDetails">
						
					</ul>
				</div>
			</div>
			<div class="sales-info">
				<div class="import">
					<h1>Consignment Sale</h1>
					<div class="detail">
						<ul class="cleafix">
							<li class="cleafix">
								<a href="" onclick="turnFreshPort('0');">
									<img src="${applicationScope.sysConfigs['sysPath']}/style/style-common/images/icon_release.png">
									<div class="info">
										<h3>Inv & PL</h3>
										<span>You can input Inv &PL information in this page.</span>
									</div>
								</a>
							</li>
							<li class="cleafix">
								<a href="" onclick="turnFreshPort('1');">
									<img src="${applicationScope.sysConfigs['sysPath']}/style/style-common/images/icon_search.png">
									<div class="info">
										<h3>Logistic</h3>
										<span>You can search information in this page</span>
									</div>
								</a>
							</li>
							<li class="cleafix">
								<a href="" onclick="turnFreshPort('2');">
									<img src="${applicationScope.sysConfigs['sysPath']}/style/style-common/images/icon_settle.png">
									<div class="info">
										<h3>Settlement</h3>
										<span>Settlement confirm.</span>
									</div>
								</a>
							</li>
							<li class="cleafix">
								<a href="" onclick="turnFreshPort('3');">
									<img src="${applicationScope.sysConfigs['sysPath']}/style/style-common/images/panyment.png">
									<div class="info">
										<h3>Payment </h3>
										<span>Payment Confirm</span>
									</div>
								<a/>
							</li>
						</ul>
					</div>
				</div>
				<div class="news">
					<h1>NEWS<a href="${applicationScope.sysConfigs['sysPath']}/pages/information/informationList.jsp" class="more">more > </a></h1>
						<ul id="newsList">
							
						</ul>
				</div>
			</div>
		</div>
		<!-- main E -->
		
		<!-- center S -->
		<div class="center">
			<!-- seller S -->
			<div class="list-box">
				<div class="left">
					<h3>Seller</h3>
					<span>This is some of the information from the seller.</span>
					<a href="../seller/sellerList.jsp">View More >  </a>
				</div>
				<div class="right " id="seller">
					<a href="javascript:void(0)" onclick="prev(this)" class="prev"></a>
					<a href="javascript:void(0)" onclick="next(this)" class="next"></a>
					<div class="box">
						<ul class="piclist1" id="sellerList" >
							
						</ul>
					</div>
				</div>
			</div>
			<!-- seller E -->
			<!-- buyer S -->
	        <div class="list-box">
	            <div class="left">
	                <h3>Buyer</h3>
	                <span>This is some of the information from the buyer.</span>
	                <a href="../buyer/buyerList.jsp">View More >  </a>
	            </div>
	            <div class="right  buyer" id="buyer">
	                <a href="javascript:void(0)" onclick="prev(this)" class="prev"></a>
	                <a href="javascript:void(0)" onclick="next(this)" class="next"></a>
	                <div class="box">
	                    <ul class="piclist1" id="buyerList" >
	                        
	                    </ul>
	                </div>
	            </div>
	        </div>
	        <!-- buyer E -->
	        <!-- service S -->
	        <div class="list-box">
	            <div class="left">
	                <h3>Service</h3>
	                <span>This is some of the information from the service.</span>
	                <a href="../service/serviceList.jsp">View More >  </a>
	            </div>
	            <div class="right service" id="service">
	                <a href="javascript:void(0);" onclick="prev(this)" class="prev"></a>
	                <a href="javascript:void(0)" onclick="next(this)" class="next"></a>
	                <div class="box">
	                    <ul class="piclist1" id="serviceList" >
	                        
	                    </ul>
	                </div>
	            </div>
	        </div>
	        <!-- service E -->
	        <!-- link S -->
			<div class="link">
				<h1>OTHER LINKS</h1>
				<ul>
					<li>
						<h3><img src="${applicationScope.sysConfigs['sysPath']}/style/style-common/images/link1.png">CUSTOMS</h3>
						<p>to commodity trading portal -- barter network as the core and the foundation, </p>
					</li>
					<li>
						<h3><img src="${applicationScope.sysConfigs['sysPath']}/style/style-common/images/link2.png">ENMORE</h3>
						<p>to commodity trading portal -- barter network as the core and the foundation, its business, </p>
					</li>
					<li>
						<h3><img src="${applicationScope.sysConfigs['sysPath']}/style/style-common/images/link3.png">SIPG</h3>
						<p>to commodity trading portal --  as the core and the foundation, its business, </p>
					</li>
					<li>
						<h3><img src="${applicationScope.sysConfigs['sysPath']}/style/style-common/images/link4.png">SIPG</h3>
						<p>to commodity trading barter network as the core and the foundation, its business, </p>
					</li>
					<li class="last">
						<h3><img src="${applicationScope.sysConfigs['sysPath']}/style/style-common/images/link5.png">INSPECTION </h3>
						<p>to commodity trading portal -- barte core and the foundation, its business, </p>
					</li>
				</ul>
			</div>
			<!-- link E -->
		</div>
		<!-- center E -->
	</div>
	<!-- 主体 E -->
	
	<!-- 尾部 S -->
	<%@ include file="/base/include/bottom.jsp"%>
    <!-- 尾部 E -->
  </body>
</html>
