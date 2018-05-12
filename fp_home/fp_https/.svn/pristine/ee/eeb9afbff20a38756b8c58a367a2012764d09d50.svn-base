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
	<script type="text/javascript" src="${applicationScope.sysConfigs['sysPath']}/style/style-module/account/resetPwd.js"></script>

  </head>
  
  <body>
        <!-- 头部 S -->
		<%@ include file="/base/include/top.jsp"%>
		<!-- 头部 E -->

		<!-- 主体 S -->
		<div class="breadcrumb">
		    <div class="wrapper">
		      <strong><a href="${applicationScope.sysConfigs['sysPath']}/pages/index/index.jsp">Home</a></strong> &gt; 
		      <a href="">Reset</a> 
		    </div>    
		</div>
		<div class="wrapper">
		<div class="account-info">
				<h3>Reset password</h3>
				<div class="detail">
					<p><span>User name: </span><input id="username" type="text" value="" class="w320"></p>
					<p><span>New password: </span><input id="newpwd" type="password" value="" class="w320"></p>
					<p><span>Confirm password: </span><input id="conpwd" type="password" value="" class="w320"></p>
					<p><span>Security Code: </span><input id="securityCode" type="text" value="" class="w180">
					<input id="getCode" value="GetSecurityCode" type="button" class="btn1" onclick="getCode();">
						<a href=""><img style="visibility:hidden" src="${applicationScope.sysConfigs['sysPath']}/kaptcha.jpg" class="code"></a></p>
					<p><span></span>
					<input class="btn" id="Reset" value="Reset" type="button" onclick="Reset();">
					
				</div>
			</div>
		</div>
		
		
		<!-- 主体 E -->

		<!-- 尾部 S -->
		<%@ include file="/base/include/bottom.jsp"%>
	    <!-- 尾部 E -->
  </body>
</html>
