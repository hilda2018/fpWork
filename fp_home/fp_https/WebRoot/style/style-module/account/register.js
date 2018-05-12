/***********************获取验证码****************************/
function getCode(){
	var email=$("#email").val();
	var canSend =chkEmail();
	if (canSend==true){
		$("#getCode").attr("disabled","false");
		var url=baseUrl +"/account!getCode.dhtml?email=" + email;
		$.get(url, function(data){
			if (data=="0"){
				alert("The mail sent successfully, please check!");
				$("#getCode").attr("disabled","false");
			}else{
				alert("The mail can not be delieved!");
				$("#getCode").attr("disabled","true");
			}
		});
	}else{
		alert("email can not empty!");
	}
}
/***********************获取验证码****************************/
function register(){
	var checkReturn=chkInfo();
	if (checkReturn==true){
		var username=$("#username").val();
		var password=$("#password").val();
		var email=$("#email").val();
		var company=$("#company").val();
		var securityCode=$("#securityCode").val();
		
		var servicetype= $("#sType").val();
		var url=baseUrl +"/account!register.dhtml?username=" + username+
				"&password=" + password+"&email=" + email+"&company="+company+
				"&securityCode=" + securityCode + "&servicetype=" + servicetype + "&num=" + Math.random();
		
		$.ajax({
			type : "GET",
			url : encodeURI(encodeURI(url)),
			async : false,
			success : function(data) {
				if(data=="1"){
					alert("verification code is not correct!");
				}else if(data=="2"){
					alert("mailbox already exists!");
				}else if(data=="3"){
					alert("email can not empty!");
				}else if(data=="4"){
					alert("registration failure!");
					clearInfo();
				}else{
					alert("Registration success, please wait for the audit!");
					window.location.href="../index/index.jsp";
				}
			}
		});
		
		/*
		$.get(url, function(data){
			if(data=="1"){
				alert("verification code is not correct!");
			}else if(data=="2"){
				alert("mailbox already exists!");
			}else if(data=="3"){
				alert("email can not empty!");
			}else if(data=="4"){
				alert("registration failure!");
				clearInfo();
			}else{
				window.location.href="../index/index.jsp";
			}
		});
		*/
	}
}

/***********************验证登录信息****************************/
function chkInfo(){
	var returnStr=true;
	
	var username=$("#username").val();
	var password=$("#password").val();
	var conpassword=$("#conpassword").val();
	var email=$("#email").val();
//	var securityCode=$("#securityCode").val();
	if (username==""){
		alert("username can not empty!");
		$("#username").val("");
		$("#username").focus();
		return false;
	}
	if (password==""){
		alert("password can not empty!");
		$("#password").val("");
		$("#password").focus();
		return false;
	}
	if (password!=conpassword){
		alert("The passwords you typed do not match. Type the same password in both text boxes!");
		$("#password").val("");
		$("#conpassword").val("");
		$("#password").focus();
		return false;
	}
	/*if (email==""){
		alert("email can not empty!");
		$("#email").val("");
		$("#email").focus();
		return false;
	}else{
		var pattern = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;    
		var isEmail= pattern.test(email);
		if (isEmail==false){
			alert("The mailbox form is not correct, please fill in!");
			$("#email").val("");
			$("#email").focus();
			return false;
		}
	}
	if (securityCode==""){
		alert("securityCode can not empty!");
		$("#securityCode").val("");
		$("#securityCode").focus();
		return false;
	}*/
	return returnStr;
}
/***********************验证用户名****************************/
function UserBlur(){
	var username=$("#username").val();
	if (email==""){
		alert("username can not empty!");
		$("#username").val("");
		$("#username").focus();
		return false;
	}
	var url=baseUrl +"/account!chkUser.dhtml?username=" + username;
	$.get(url, function(data){
		if (data=="1"){
			alert("username already exists!");
			$("#username").val("");
			$("#username").focus();
			return false;
		}
	});
}
/***********************验证邮箱****************************/
function EmailBlur(){
	/*var email=$("#email").val();
	if (email==""){
		alert("email can not empty!");
		$("#email").val("");
		$("#email").focus();
		return false;
	}else{
		var pattern = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;    
		var isEmail= pattern.test(email);
		if (isEmail==false){
			alert("The mailbox form is not correct, please fill in!");
			$("#email").val("");
			$("#email").focus();
			return false;
		}
	}
	var url=baseUrl +"/account!ChkEmail.dhtml?email=" + email;
	$.get(url, function(data){
		if (data=="1"){
			alert("mailbox already exists!");
			$("#email").val("");
			$("#email").focus();
			return false;
		}
	});*/
}
/***********************验证邮件****************************/
function chkEmail(){
	var emailStr=$("#email").val();
	var pattern = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;    
	return pattern.test(emailStr);
}

/***********************清空数据****************************/
function clearInfo(){
	$("#username").val("");
	$("#password").val("");
	$("#conpassword").val("");
	$("#email").val("");
	$("#securityCode").val("");
}

$(function(){
	document.onkeydown = function(e){ 
	    var ev = document.all ? window.event : e;
	    if(ev.keyCode==13) {

	           $("#Register").click();//处理事件

	     }
	}
});  

/***********************验证申请信息****************************/
function apply(){
	var checkReturn=chkInfoForApply();
	if (checkReturn==true){
		var username=$("#username").val();
		var password=$("#password").val();
		var email=$("#email").val();
		var company=$("#company").val();
		var servicetype= $("#sType").val();
		var url=baseUrl +"/account!apply.dhtml?username=" + username+
				"&password=" + password+"&email=" + email+"&company="+company+
				"&servicetype=" + servicetype;
		$.ajax({
			type : "GET",
			url : encodeURI(encodeURI(url)),
			async : false,
			success : function(data) {
				if(data=="1"){
					alert("verification code is not correct!");
				}else if(data=="2"){
					alert("mailbox already exists!");
				}else if(data=="3"){
					alert("email can not empty!");
				}else if(data=="4"){
					alert("registration failure!");
					clearInfo();
				}else{
					alert("Registration success, please wait for the audit!");
					window.location.href="../index/index.jsp";
				}
			}
		});
		
		
	}
}

/***********************验证申请信息****************************/
function chkInfoForApply(){
	var returnStr=true;
	
	var username=$("#username").val();
	var password=$("#password").val();
	var email=$("#email").val();
	var company =$("#company").val();
	if (username==""){
		alert("username can not empty!");
		$("#username").val("");
		//$("#username").focus();
		return false;
	}
	if (password==""){
		alert("password can not empty!");
		$("#password").val("");
		//$("#password").focus();
		return false;
	}
	
	if (email==""){
		alert("email can not empty!");
		$("#email").val("");
		//$("#email").focus();
		return false;
	}else{
		var pattern = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;    
		var isEmail= pattern.test(email);
		if (isEmail==false){
			alert("The mailbox form is not correct, please fill in!");
			$("#email").val("");
			$("#email").focus();
			return false;
		}
	}
	
	if (company==""){
		alert("company can not empty!");
		$("#company").val("");
		//$("#company").focus();
		return false;
	}
	
	return returnStr;
}


/***********************验证用户名****************************/
function UserBlurForUserName(){
	var username=$("#username").val();
	if (username==""){
		alert("username can not empty!");
		$("#username").val("");
		//$("#username").focus();
		return true;
	}
	var url=baseUrl +"/account!chkUserNameForApply.dhtml?username=" + username;
	$.get(url, function(data){
		if (data=="1"){
			alert("username already exists!");
			$("#username").val("");
			$("#username").focus();
			return false;
		}
	});
}
/***********************验证邮箱****************************/
function EmailBlurForApply(){
	var email=$("#email").val();
	if (email==""){
		alert("email can not empty!");
		$("#email").val("");
		//$("#email").focus();
		return true;
	}else{
		var pattern = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;    
		var isEmail= pattern.test(email);
		if (isEmail==false){
			alert("The mailbox form is not correct, please fill in!");
			$("#email").val("");
			$("#email").focus();
			return false;
		}
	}
	var url=baseUrl +"/account!chkEmailForApply.dhtml?email=" + email;
	$.get(url, function(data){
		if (data=="1"){
			alert("mailbox already exists!");
			$("#email").val("");
			$("#email").focus();
			return false;
		}
	});
}