<%@ page language="java"  pageEncoding="utf-8" import="java.util.*"%>
<%@ include file="/base/include/basePage.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
    <title>客户端1</title>
    <%@ include file="/base/common/jQuery.jsp"%>
    <script type="text/javascript">
    var url= "${sysPath}/account!signleLogin.dhtml?num=" + Math.random();
    console.log(url);
	$.ajax({
		type : "GET",
		url : encodeURI(encodeURI(url)),
		async : false,
		success : function(data) {
			if (data=="-1"){
				alert("Login failed, username or password is wrong!");
				$("#username").val("");
				$("#password").val("");
				$("#username").focus();
			}else if(data=="-2"){
				alert("Your account has not been approved, please try again later!");
			}
			else if(data=="1"){
				alert("Verification code is not correct!");
				$("#securityCode").val("");
				$("#securityCode").focus();
			}else{
				window.parent.location.href="${sysPath}/pages/index/indexEn.jsp";
			}
		}
	});
    </script>
</head>
</html>