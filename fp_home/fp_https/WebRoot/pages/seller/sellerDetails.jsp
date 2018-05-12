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
	<link type="text/css" rel="stylesheet" href="${applicationScope.sysConfigs['sysPath']}/style/style-common/css/supplier-details.css">
	<script type="text/javascript" src="${applicationScope.sysConfigs['sysPath']}/style/style-module/seller/sellerDetails.js"></script>

  </head>
  
  <body>
    	<!-- 头部 S -->
		<%@ include file="/base/include/top.jsp"%>
		<!-- 头部 E -->
		<!-- 主体 S -->
		<div class="breadcrumb">
		    <div class="wrapper">
		      <strong><a href="${applicationScope.sysConfigs['sysPath']}/pages/index/indexEn.jsp">Home</a></strong> &gt; 
		      <a href="sellerList.jsp">Sellers</a> &gt; 
		      <span class="active">Contacts</span>
		    </div>    
		</div>
		<div class="supplier-details">
		    <div class="wrapper">
		        <div class="supd-tit"><a href="#"><img id="logo" /></a><a id="company" ></a></div>
		        <table>
		        	<tr>
		            	<th>Address</th>
		            	<td id="address"></td>
		            	<td rowspan="5" class="banner">
		                        <ul id="bigImg">
		                            
		                        </ul>
		                        <div class="btn" id="btnSmall">
		                            
		                        </div>
		                </td>
		            </tr>
		            <tr>
		            	<th>Website</th>
		            	<td id="website"></td>
		            </tr>
		            <tr>
		            	<th>Phone</th>
		            	<td id="phone"></td>
		            </tr>
		            <tr>
		            	<th>Fax</th>
		            	<td id="fax"></td>
		            </tr>
		            <tr>
		            	<th>E-mail</th>
		            	<td id="email"></td>
		            </tr>
		        </table>
		        <table>
		        	<tr>
		            	<th>Company profile</th>
		                <td id="content">
		                	
		                </td>
		            </tr>
		        </table>
		        <table>
		        	<tr>
		            	<th>Products</th>
		                <td class="pro-list">
		                	<ul id="supplierImgList">
		                    	
		                    </ul>
		                </td>
		            </tr>
		        </table>
		    </div>
		</div>
		<!-- 主体 E -->
		<!-- 尾部 S -->
		<%@ include file="/base/include/bottom.jsp"%>
	    <!-- 尾部 E -->
  </body>
</html>
