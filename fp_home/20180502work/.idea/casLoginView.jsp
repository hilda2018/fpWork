<!DOCTYPE html>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="zh-CN">

	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1 , user-scalable=no">
		<title>生鲜港登录界面</title>
		<link rel="shortcut icon" href="<c:url value=" /common/favicon.ico " />">
		<script>
			document.createElement("section");
			
			var arrLink = ["cn/registrationAction!registration.action","fp-online-infoshow/member/login","cn/registrationAction!registration.action"];
			
			 function getLink(a,num){
				
				if( window.location.hostname.indexOf('test')==-1 ){//非測試
					
					a.href = "https://www.freshport.com/" + arrLink[num] ;
				}else{
								
					a.href = "https://wwwtest.freshport.com/" + arrLink[num] ;

				}

				console.log( a.href );
			}


		</script>
		<%
	String loginat = request.getParameter("loginat");
	if (loginat != null && loginat.equals("info")) {
%>
		<style>
			*{padding:0;margin:0;}
#LoginU{width:auto;max-width:100%;height:60px;line-height:60px;white-space:nowrap;overflow:hidden;text-align:left;}
			

#LoginU{width:244px;height:60px;}
.nava,.nava2{color:#fff;text-decoration:none;font-size:9pt;
margin-top: 18px;width:auto;padding:0 1pc;height:26px;border:1px solid #8bc34a;line-height:24px;display: inline-block;margin-left: 12px;border-radius: 15px;}
.nava{background:#8bc34a;color:#fff;}
.nava2{background:#fff;color:#8bc34a;}

			</style>

		<div class="nav-user" id="LoginU" style="width:220px!important;">
	
			<a href="javavoid(0)" class="nava"  target="_top" onclick="getLink(this,0);" >Sign up</a>
			
			<a href="javavoid(0)" class="nava2"   target="_top"  onclick="getLink(this,1);"  >Login</a>
		
		</div>

		<%	
	} else{
 %>
		<style>
			section {
				background: #fff;
				display: block;
			}

			.register {
				padding: 10px;
				width: 100%;
				box-sizing: border-box;
				border-radius: 6px;
				-moz-border-radius: 6px;
				-webkit-border-radius: 6px;
				border: 1px solid #ddd;
				box-shadow: 0 1px 2px rgba(0, 0, 0, .2) !important;
				background: #fff;
				height: 100%;
				position: relative;
				overflow: hidden;
				margin: 0px auto;
				max-width: 400px;
			}

			.register h1 {
				font-size: 18px;
				font-weight: 500;
				padding: 6px;
				padding-top: 0px;
				margin: 0;
				border-bottom: 1px solid #eee;
				text-align: center;
				padding-bottom: 10px;
				margin-bottom: 10px;
			}
			
			.text {
				border: 1px solid #EEEEEE;
				padding: 6px 12px;
				border-radius: 3px;
				-moz-border-radius: 3px;
				-webkit-border-radius: 3px;
				margin-bottom: 5px;
				font-size: 13px;
				padding-right: 2px;
			}
			
			.text1 {
				border: 1px solid #ddd;
				padding: 6px 0px;
				border-radius: 3px;
				-moz-border-radius: 3px;
				-webkit-border-radius: 3px;
				margin-bottom: 5px;
				font-size: 13px;
			}
			
			.text img {
				margin-right: 6px;
				vertical-align: middle;
				position: relative;
			}
			
			.register input[type=text],
			.register input[type=password] {
				border: 0;
				font-size: 14px;
				font-weight: 300;
				height: 22px;
				position: absolute;
				margin-top: -2px;
				width: 80%;
				-webkit-box-shadow: 0 0 0px 20px white inset
			}
			
			.register input[type=text]:focus,
			.register input[type=password]:focus {
				outline: 0;
			}
			
			.register input[type=submit] {
				border: 0;
				padding: 8px;
				border-radius: 4px;
				-moz-border-radius: 4px;
				-webkit-border-radius: 4px;
				border: 1px solid rgba(0, 0, 0, .1);
				font-size: 16px;
				font-weight: 300;
				width: 100%;
				text-align: center;
				margin-top: 6px;
				background: #8bbd3d;
				/* Old browsers */
				background: -moz-linear-gradient(top, #b4e469 0%, #8bbd3d 100%);
				background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #b4e469), color-stop(100%, #8bbd3d));
				background: -webkit-linear-gradient(top, #b4e469 0%, #8bbd3d 100%);
				background: -o-linear-gradient(top, #b4e469 0%, #8bbd3d 100%);
				background: -ms-linear-gradient(top, #b4e469 0%, #8bbd3d 100%);
				background: linear-gradient(to bottom, #b4e469 0%, #8bbd3d 100%);
				border-right: 1px solid #8bbd3d;
				border-bottom: 1px solid #8bbd3d;
				border-left: 1px solid #8bbd3d;
				border-top: 1px solid #fff;
				font-weight: bold;
				color: #fff;
				text-shadow: 0 1px 1px rgba(0, 0, 0, .3);
				text-transform: uppercase;
				-webkit-box-shadow: inset 0 1px 0 0 #8bbd3d, 0 1px 2px 0 #8bbd3d;
				box-shadow: inset 0 1px 0 0 #8bbd3d, 0 1px 2px 0 #8bbd3d;
				letter-spacing: 4px;
			}
			
			.login a {
				text-decoration: none;
				font-weight: 500;
			}
			
			@media all and (max-width: 602px) {
				.register input[type=text],
				.register input[type=password] {
					width: 60%;
				}
				#code {
					width: 40%;
				}
			}
			
			@media all and (max-width: 506px) {
				.register input[type=text],
				.register input[type=password] {
					width: 50%;
				}
				#code {
					width: 40%;
				}
			}
			
			.codeimg {
				float: right;
				max-width: 30%;
				max-height: 22px;
				height: auto;
				vertical-align: middle;
			}
			
			.errors {
				border: 1px dotted #BB0000;
				color: #BB0000;
				background: url('') no-repeat 20px center;
				padding: 6px 12px;
				border-radius: 3px;
				-moz-border-radius: 3px;
				-webkit-border-radius: 3px;
				margin-bottom: 5px;
				font-size: 13px;
				padding-right: 2px;
				clear: both;
			}
			
			#register {
				color: #9ccd4f;
				font-size: 12px;
				height: 22px;
				line-height: 22px;color: #80b927;
    font-weight: bold;
				display: block;
				text-align: right;
				text-decoration: none;
				margin-top: 2px;
				position: relative;
				top: 4px;
				letter-spacing: 1px;
			}
		</style>
	</head>

	<body style="overflow:hidden;padding:0;margin:0;box-sizing: border-box;">
		<form:form method="post" class="register" id="fm1" commandName="${commandName}">
			<h1><spring:message code="screen.welcome.welcome"/></h1>

			<section class="text">
				<img src="<c:url value=" /common/images/username.png " />" alt="username" />
				<spring:message code="screen.welcome.label.netid.accesskey" var="userNameAccessKey" />
				<spring:message code="screen.welcome.label.netid" var="username" />
				<form:input cssErrorClass="error" id="username" name="username" size="25" accesskey="${userNameAccessKey}" path="username" placeholder="${username }" autocomplete="off" htmlEscape="true" />
				<br />
			</section>

			<spring:message code="screen.welcome.label.password" var="password" />
			<section class="text">
				<img src="<c:url value=" /common/images/password.png " />" alt="password" />
				<spring:message code="screen.welcome.label.password.accesskey" var="passwordAccessKey" />
				<form:password cssErrorClass="error" id="password" name="password" size="25" placeholder="${password }" path="password" accesskey="${passwordAccessKey}" htmlEscape="true" autocomplete="off" />
				<br />
			</section>

			<section class="text" style="float:left;width:100%;box-sizing:border-box;">
				<img src="<c:url value=" /common/images/email.png " />" alt="email" />
				<spring:message code="screen.register.label.code.accesskey" var="codeAccessKey" />
				<spring:message code="screen.register.label.code" var="securitycode" />
				<input type="text" placeholder="${securitycode }" style="width:40%;" id="securitycode" name="securitycode" autocomplete="off" accesskey="${codeAccessKey}" />

				<a href=""><img src="/kaptcha.jpg" class="codeimg"></a>
				<br />
			</section>

			<form:errors path="*" id="msg" cssClass="errors" element="div" htmlEscape="false" />
			<div id="msg" class="errors" style="display: none"></div>

			<section class="row btn-row">
				<label for="password"><span class="accesskey"></span></label>
				<input type="hidden" name="lt" value="${loginTicket}" />
				<input type="hidden" name="execution" value="${flowExecutionKey}" />
				<input type="hidden" name="_eventId" value="submit" />
				<!-- <input class="btn-submit" name="login" accesskey="l" type="submit" value="Login"> -->
				<%--<span class="login-btn"><spring:message code="screen.welcome.button.login"/></span>--%>
				<%--<input class="btn-reset" name="reset" accesskey="c"--%>
				<%--value="<spring:message code="screen.welcome.button.clear" />" ="reset"/>--%>
			</section>
			<spring:message code="screen.welcome.button.login" var="login" />
			<input name="login" accesskey="l" type="submit" value="${login }" id="denglu" /> <br />
			<section class="login">
				<a href="" id="register" target="_top"   onclick="getLink(this,2);" ><span style="display:none;"><spring:message code="screen.register.label.apply"/></span>No account? Sign up now</a>
			</section>
		</form:form>
		<%
	}
 %>
	</body>

</html>