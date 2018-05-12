<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.Map,java.util.HashMap,com.novaone.common.NovaSession" %>
<%
    String path = request.getContextPath();
	Map<String,String> sysConfigs = (Map<String, String>) application.getAttribute("sysConfigs");
	//生鲜港项目地址
	String basePath = sysConfigs.get("sysPath");
	//英文
	String enPath = sysConfigs.get("enPath");
	//单点登录地址
	String loginPath = sysConfigs.get("loginPath");
	//库存地址
	String inventoryPath = sysConfigs.get("inventoryPath");
	//销售地址
	String salesPath = sysConfigs.get("salesPath");
	//支付地址
	String payPath = sysConfigs.get("payPath");
	//前台地址
	String foreignprocurementPath = sysConfigs.get("foreignprocurementPath");
	//appmanager地址
	String appmanagerPath = sysConfigs.get("appmanagerPath");
	//purchase地址
	String purchasePath = sysConfigs.get("purchasePath");
	//logistic地址
	String logisticPath = sysConfigs.get("logisticPath");
	//info冠易项目地址
	String infoPath = sysConfigs.get("infoPath");
	//frontPath 前台
	String frontPath = sysConfigs.get("frontPath");
    
    NovaSession novaSession = new NovaSession(request.getSession(), true);
%>
<%--项目名称 --%>
<c:set var="sysName" scope="application" value="${applicationScope.sysConfigs['sysName']}" />

<%-- 项目地址 --%>
<c:set var="sysPath" scope="application" value="<%=basePath %>" />

<%-- 英文 --%>
<c:set var="enPath" scope="application" value="<%=enPath %>" />

<%-- 单点登录地址 --%>
<c:set var="loginPath" scope="application" value="<%=loginPath %>" />

<%-- 项目后台地址 --%>
<c:set var="foreignprocurementPath" scope="application" value="<%=foreignprocurementPath %>" />

<c:set var="base" value="<%=basePath %>" />
<c:set var="login" value="<%=loginPath %>" />
<c:set var="inventory" value="<%=inventoryPath %>" />
<c:set var="sales" value="<%=salesPath %>" />
<c:set var="pay" value="<%=payPath %>" />
<c:set var="front" value="<%=foreignprocurementPath %>" />
<c:set var="appmanager" value="<%=appmanagerPath %>" />
<c:set var="purchase" value="<%=purchasePath %>" />
<c:set var="logistic" value="<%=logisticPath %>" />
<c:set var="infoPath" value="<%=infoPath %>" />
<c:set var="frontPath" value="<%=frontPath %>" />

<%--图片服务器路径 --%>
<c:set var="imgServer" scope="application" value="${applicationScope.sysConfigs['imageServer']}"/>
<c:set var="imgSmall" scope="application" value="${applicationScope.sysConfigs['UploadSmallUrl']}"/>
<c:set var="imgMiddle" scope="application" value="${applicationScope.sysConfigs['UploadMiddleUrl']}"/>
<c:set var="imgBig" scope="application" value="${applicationScope.sysConfigs['UploadBigUrl']}"/>

<%--样式路径地址--%>
<c:set var="style" value="${sysPath}/style" />
<%--公共样式路径地址--%>
<c:set var="styleCommon" value="${style}/style-common" />

<%-- 所有js组件存放路径 --%>
<c:set var="library" value="${styleCommon}/library" />

<%-- jquery组件路径 --%>
<c:set var="libJquery" value="${library}/jquery" />
<%-- bootstrap组件路径 --%>
<c:set var="libBootstrap" value="${library}/bootstrap" />
<%-- kindeditor组件路径 --%>
<c:set var="libKindeditor" value="${library}/kindeditor" />
<%-- pw组件路径 --%>
<c:set var="libPw" value="${library}/pw" />
<%-- uploadify组件路径 --%>
<c:set var="libUploadify" value="${library}/uploadify" />


<%---------------------------------------------%>
<%---------- 系统需要使用的公共部分 -------------%>
<%---------------------------------------------%>
<c:set var="css" value="${styleCommon}/css" />
<c:set var="images" value="${styleCommon}/images" />
<c:set var="js" value="${styleCommon}/js" />
<%--平台自身模块样式路径地址--%>
<c:set var="styleModule" value="${style}/style-module" />

<script type="text/javascript">
//当前工程web路径
var basePath = "${sysPath}";
var baseUrl = "${sysPath}";
//英文
var enPath = "${enPath}";
//单点登录地址
var loginPath = "${loginPath}";
//项目后台地址
var foreignprocurementPath= "${foreignprocurementPath}";
//库存地址
var inventoryPath = "${inventory}";
//销售地址
var salesPath = "${sales}";
//支付地址
var payPath = "${pay}";
//appmanager地址
var appmanagerPath = "${appmanager}";
//purchase地址
var purchasePath = "${purchase}";
//purchase地址
var logisticPath = "${logistic}";
//前台地址
var frontPath = "${frontPath}";
//info冠易地址
var infoPath = "${infoPath}";

//js组件路径
var pluginpath = "${library}";
//项目名称
var baseTitle = "${sysName}";
//项目图片目录
var baseImages = "${images}";
//项目样式目录
var baseCss = "${css}";
//上传组件路径
var uploadify = "${libUploadify}";
//图片服务器
var imgServer = "${imgServer}";
var UploadSmallUrl = "${imgSmall}";
var UploadMiddleUrl = "${imgMiddle}";
var UploadBigUrl = "${imgBig}";
var styleCommon = "${styleCommon}";
</script>











