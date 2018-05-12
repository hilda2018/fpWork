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
	<link type="text/css" rel="stylesheet" href="${applicationScope.sysConfigs['sysPath']}/style/style-common/css/product-details.css">
	<script type="text/javascript" src="${applicationScope.sysConfigs['sysPath']}/style/style-module/index/config.js"></script>
	<script type="text/javascript" src="${applicationScope.sysConfigs['sysPath']}/style/style-module/seller/base.js"></script>
	<script type="text/javascript" src="${applicationScope.sysConfigs['sysPath']}/style/style-module/seller/jdMarquee.js"></script>
	<script type="text/javascript" src="${applicationScope.sysConfigs['sysPath']}/style/style-module/seller/jqueryzoom.js"></script>
	<script type="text/javascript" src="${applicationScope.sysConfigs['sysPath']}/style/style-module/seller/sellerProduct.js"></script>
  </head>
  
  <body>
	   	<!-- 头部 S -->
		<%@ include file="/base/include/top.jsp"%>
		<!-- 头部 E -->
		<!-- 主体 S -->
		<div class="breadcrumb">
		    <div class="wrapper">
		      <strong><a href="${applicationScope.sysConfigs['sysPath']}/pages/index/index.jsp">Home</a></strong> &gt; 
		      <a href="sellerList.jsp">Sellers</a> &gt; 
		      <span class="active">Product details</span>
		    </div>    
		</div>
		<div class="product-details">
		    <div class="wrapper">
		        <div class="clearfix">
		        	<div class="fl" id="preview">
		                <div class="jqzoom" id="spec-n1"><img id="indexImg" height=400 src="images/visit2.jpg" jqimg="images/visit2.jpg" width=400>
		                </div>
		                <div id="spec-n5">
		                    <div id="spec-list">
		                        <ul class="list-h" id="imgShowList">
		                            
		                        </ul>
		                    </div>
		                </div>
		            </div>
		            <div class="fr">
		            	<h3 id="prdName"></h3>
		                	<div class="control-group">
		                		<label class="control-label">Variety:</label>
		                        <div class="controls" id="varietyName"></div>
		                    </div>
		                	<div class="control-group">
		                		<label class="control-label">Period:</label>
		                        <div class="controls" id="PeriodName" ></div>
		                    </div>
		                	<div class="control-group">
		                		<label class="control-label">MOQ:</label>
		                        <div class="controls" id="MOQName"></div>
		                    </div>
		                	<div class="control-group">
		                		<label class="control-label">Supply:</label>
		                        <div class="controls" id="SupplyName"></div>
		                    </div>
		                	<div class="control-group">
		                		<label class="control-label">Acreage:</label>
		                        <div class="controls" id="AcreageName"></div>
		                    </div>
		                	<div class="control-group">
		                		<label class="control-label">Transportation:</label>
		                        <div class="controls" id="transName"></div>
		                    </div>
		                	<div class="control-group">
		                		<label class="control-label">Country/Region:</label>
		                        <div class="controls" id="countryName"></div>
		                    </div>
		                	<div class="control-group">
		                		<label class="control-label">Orchard:</label>
		                        <div class="controls" id="OrchardName"></div>
		                    </div>
		                	<div class="control-group">
		                		<label class="control-label">Certificale:</label>
		                        <div class="controls" id="CertificaleName"></div>
		                    </div>
		            </div>
		        </div>
		        <div class="details" id="Specification">
		            <h3>Specification</h3>
		        </div>
		    </div>
		</div>
		
		<!-- 主体 E -->
		<!-- 尾部 S -->
		<%@ include file="/base/include/bottom.jsp"%>
	    <!-- 尾部 E -->
  </body>
</html>
