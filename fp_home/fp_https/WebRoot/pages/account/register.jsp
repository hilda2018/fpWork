<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

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
	<link type="text/css" rel="stylesheet" href="${applicationScope.sysConfigs['sysPath']}/style/style-common/css/base.css">
	<link type="text/css" rel="stylesheet" href="${applicationScope.sysConfigs['sysPath']}/style/style-common/css/common.css">
	<link type="text/css" rel="stylesheet" href="${applicationScope.sysConfigs['sysPath']}/style/style-common/css/login.css">
	<script type="text/javascript" src="${applicationScope.sysConfigs['sysPath']}/style/style-module/index/config.js"></script>
	<script type="text/javascript" src="${applicationScope.sysConfigs['sysPath']}/style/style-module/account/register.js"></script>

  </head>
  
  <body>
        <!-- 头部 S -->
		<%@ include file="/base/include/top.jsp"%>
		<!-- 头部 E -->

		<!-- 主体 S -->
		<div class="breadcrumb">
		    <div class="wrapper">
		      <strong><a href="${applicationScope.sysConfigs['sysPath']}/pages/index/index.jsp">Home</a></strong> &gt; 
		      <a href="">Register</a> 
		    </div>    
		</div>
		<div class="wrapper">
			<div class="account-info">
				<h3>Registered fresh port account</h3>
				<div class="detail">
					<p><span>User name: </span><input id="username" type="text" value="" class="w320" onblur="UserBlur();"></p>
					<p><span>Password: </span><input id="password" type="password" value="" class="w320"></p>
					<p><span>Password confirm: </span><input id="conpassword" type="password" value="" class="w320"></p>
					<p><span>Email: </span><input id="email" type="text" value="" class="w320" onblur="EmailBlur();"></p>
					<p><span>Company: </span><input id="company" type="text" value="" class="w320"></p>
					<p><span>ServiceType: </span>
						<select class="w320" id="sType">
							<option selected="selected" value="1">Buyer</option>
							<option value="0">Seller</option>
							<option value="2">Forwarder</option>
							<option value="3">Inspection</option>
							<option value="4">Market</option>
							<option value="5">Customs</option>
						</select>
					</p>
					<p><span>Security Code: </span><input id="securityCode" type="text" value="" class="w180">
						<input id="getCode" value="GetSecurityCode" type="button" class="btn1" onclick="getCode();">
						<a href=""><img style="visibility:hidden" src="${applicationScope.sysConfigs['sysPath']}/kaptcha.jpg" class="code"></a></p>
					<p style="text-align:center; font-size:16px; margin-top:60px;">Click the "register",that means you agree and are willing to comply with the <a href="" class="f_underline">fresh port user agreement</a></p>
					<p><span></span>
					<input class="btn" style="margin-top:0px;" id="Register" value="Register" type="button" onclick="register();">
					</p>
				</div>
			</div>
			
		</div>
		
		<!-- 主体 E -->

		<!-- 尾部 S -->
		<%@ include file="/base/include/bottom.jsp"%>
	    <!-- 尾部 E -->
  </body>
</html>
