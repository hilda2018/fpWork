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
	<link type="text/css" rel="stylesheet" href="${applicationScope.sysConfigs['sysPath']}/style/style-common/css/information.css">
	<script type="text/javascript" src="${applicationScope.sysConfigs['sysPath']}/style/style-module/index/config.js"></script>
	<script type="text/javascript" src="${applicationScope.sysConfigs['sysPath']}/style/style-common/js/page.js"></script>
	<script type="text/javascript" src="${applicationScope.sysConfigs['sysPath']}/style/style-module/information/informationList.js"></script>
	

  </head>
  
  <body>
	<!-- 头部 S -->
		<%@ include file="/base/include/top.jsp"%>
	<!-- 头部 E -->
	
    <!-- 主体 S -->
	<div class="breadcrumb">
	    <div class="wrapper">
	      <strong><a href="${applicationScope.sysConfigs['sysPath']}/pages/index/index.jsp">Home</a></strong> &gt; 
	      <span class="active">News</span>
	    </div>    
	</div>
	<div class="news-info">
	    <div class="wrapper">
	        <h3>News</h3>
	        <ul id="informationList">
	            
	        </ul>
	    </div>
	</div>
	<div id="pagenation" class="pagenation"></div>
	<!-- 尾部 S -->
	<%@ include file="/base/include/bottom.jsp"%>
    <!-- 尾部 E -->
  </body>
</html>
