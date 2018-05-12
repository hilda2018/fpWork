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
	<link type="text/css" rel="stylesheet" href="${applicationScope.sysConfigs['sysPath']}/style/style-common/css/buylist.css">
	<script type="text/javascript" src="${applicationScope.sysConfigs['sysPath']}/style/style-common/js/page.js"></script>
	<script type="text/javascript" src="${applicationScope.sysConfigs['sysPath']}/style/style-module/seller/sellerList.js"></script>

  </head>
  
  <body>
    	<!-- 头部 S -->
		<%@ include file="/base/include/top.jsp"%>
		<!-- 头部 E -->
		<!-- 主体 S -->
		<div class="wrapper">
			<div class="buyer-list">
				<div class="map">
					<a href="${applicationScope.sysConfigs['sysPath']}/pages/index/index.jsp">Home</a> > <a href="">Sellers</a>  
					<!-- <div class="ser"><input type="text" value=""><em></em></div>-->
					
				</div>
				<div class="ser-tip clearfix">
					<dl class="clearfix">
						<dt><a href="">Product: </a></dt>
						<dd id="PName">
							<a href="#" class="cc">All</a>
						</dd>
					</dl>
					<dl class="clearfix">
						<dt><a href="">Country:</a></dt>
						<dd id="CName">
							<a href="#" class="cc">All</a>
						</dd>
					</dl>
					<dl class="clearfix last">
						<dt><a href="">Date:</a></dt>
						<dd id="DInfo">
							<select id="startTen">
								<option value ="-1">please select</option>
								<option value ="0">Early</option>
								<option value ="1">Middle</option>
								<option value ="2">Late</option>
							</select>
							of
							<select id="startMonth">
								<option value ="0">please select</option>
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
							to
							<select id="endTen">
								<option value ="-1">please select</option>
								<option value ="0">Early</option>
								<option value ="1">Middle</option>
								<option value ="2">Late</option>
							</select>
							of
							<select id="endMonth">
								<option value ="0">please select</option>
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
							<button class="ser" id="SearchBtn" style="height: 28px; padding: 0 5px;  background: #f4f4f4;  border: 1px solid #ccc;
  border-radius: 4px;">Search</button>
						</dd>
					</dl>
				</div>
				<table id="sellerList">
					<thead>
						<td class="t1">Company</td>
						<td class="t2">Region</td>
						<td class="t3">Annual Supply</td>
						<td class="t4">Item</td>
					</thead>
					
				</table>
				<div class="pagenation" id="pagenation">
					
				</div>
			</div>
			
		</div>
		
		<!-- 主体 E -->
		<!-- 尾部 S -->
		<%@ include file="/base/include/bottom.jsp"%>
	    <!-- 尾部 E -->
  </body>
</html>
