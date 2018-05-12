<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'top.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script type="text/javascript" src="${applicationScope.sysConfigs['sysPath']}/style/style-common/js/selectbox.js"></script>
	<script type="text/javascript" src="${applicationScope.sysConfigs['sysPath']}/style/style-common/js/top.js"></script> 	 
    
  </head>
  
  <body>
    <div class="header">
	<div class="wrapper clearfix">
		<div class="ls_list">
			<span class="fr">
	            <a href="${applicationScope.sysConfigs['sysPath']}/pages/buyer/buyerList.jsp">Buyers</a> | <a href="${applicationScope.sysConfigs['sysPath']}/pages/seller/sellerList.jsp">Sellers</a> | <a href="${applicationScope.sysConfigs['sysPath']}/pages/service/serviceList.jsp">Services</a> | <a href="${applicationScope.sysConfigs['sysPath']}/pages/information/informationList.jsp">News</a>| <a href="${applicationScope.sysConfigs['sysPath']}/pages/index/index.jsp">Chinese</a>
	        </span>
	       <!-- 登录后 -->
	        <span class="fr name" id="LoginA"  style="display:none;">
	        	<a href="" id="usernameInfo">test</a> | <a href="javascript:void(0);" onclick="clearUser();">Sign out<span style="color:#FF771C">退出</span></a> | <a id="enterFP" href="">My Freshport&nbsp;&nbsp;<span style="color:#FF771C">我的生鲜港</span></a> 
	        </span>
	        <!-- 未登录 -->
	       	<span class="fr name" id="LoginU">
	            <%-- <a href="${applicationScope.sysConfigs['sysPath']}/pages/account/login.jsp">Login</a> | <a href="${applicationScope.sysConfigs['sysPath']}/pages/account/register.jsp">Register</a> --%>
	            <a href="${applicationScope.sysConfigs['sysPath']}/pages/account/login.jsp">Login&nbsp;&nbsp;<span style="color:#FF771C">登陆</span></a> | <a href="${applicationScope.sysConfigs['sysPath']}/pages/account/apply.jsp">Apply&nbsp;&nbsp;<span style="color:#FF771C">注册</span></a>
	        </span>
		
	        
	    </div>
	</div>
	<div class="wrapper clearfix">
	    <div class="logo"><img src="${applicationScope.sysConfigs['sysPath']}/style/style-common/images/logo.jpg"></div>
	    <div class="search">
	    	<div class="role">
	    		<em></em>
				<select style="display: none;" name="mySle" id="mySle">
					<option selected="selected" value="1">Buyer</option>
					<option value="0">Seller</option>
					<option value="2">Service</option>
				</select>
	    	</div>
	    	
	    	<input type="text" value="" placeholder="what are you looking for..." id="ser" />
	    	<a href="#" class="ser-btn">Search</a>
	    </div>
	</div>
</div> 
  </body>
<script type="text/javascript"> $(document).ready(function() {$('#mySle').selectbox();});</script>
</html>
