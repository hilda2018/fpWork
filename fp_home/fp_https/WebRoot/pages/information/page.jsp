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
	<link type="text/css" rel="stylesheet" href="${applicationScope.sysConfigs['sysPath']}/style/style-common/css/style.css">
	<script type="text/javascript" src="${applicationScope.sysConfigs['sysPath']}/style/style-common/js/page.js"></script>
	<script type="text/javascript">
		
		function bbccc(pageIndex,pageSize){
			alert(pageIndex);
			alert(pageSize);
		}
		
		$(function(){
			pageInstance = new PagingClass("aaaaa", 5, 201,bbccc);  
			$(".prev").bind("click",{start:pageInstance.pageIndex,typeInfo:"Prev"},pageInstance._changePage);
			$(".next").bind("click",{start:pageInstance.pageIndex,typeInfo:"Next"},pageInstance._changePage);
			$(".confirm").bind("click",pageInstance._aimPage);
		});
	</script>
  </head>
  
  <body>
    <div id="aaaaa" class="pagenation"></div>
  </body>
</html>
