/***********************获取验证码****************************/
function getCode(){
	var username=$("#username").val();
	if (username==""){
		alert("username can not empty!");
		$("#username").val("");
		$("#username").focus();
		return;
	}
	var url=baseUrl +"/account!getCodeByUser.dhtml?username=" + username;
	$.get(url, function(data){
		if (data=="0"){
			alert("The mail sent successfully, please check!");
			$("#getCode").attr("disabled","false");
		}else if(data=="-1"){
			alert("The user does not have a mailbox!");
			$("#username").val("");
			$("#username").focus();
		}else{
			alert("The mail can not be delieved!");
		}
	});
}
/***********************登录****************************/
function login(){
	var username=$("#username").val();
	var password=$("#password").val();
	var securityCode=$("#securityCode").val();
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
	if (securityCode==""){
		alert("securityCode can not empty!");
		$("#securityCode").val("");
		$("#securityCode").focus();
		return false;
	}
	var url=baseUrl +"/account!login.dhtml?username=" + username+
	"&password=" + password+"&securityCode=" + securityCode + "&num=" + Math.random();
	
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
				window.location.href="../index/indexEn.jsp";
			}
		}
	});
	
	/*
	$.get(url, function(data){
		if (data=="-1"){
			alert("Login failed, username or password is wrong!");
			$("#username").val("");
			$("#password").val("");
			$("#username").focus();
		}else if(data=="1"){
			alert("verification code is not correct!");
			$("#securityCode").val("");
			$("#securityCode").focus();
		}else{
			window.location.href="../index/index.jsp";
		}
	});
	*/
}
$(function(){
	document.onkeydown = function(e){ 
	    var ev = document.all ? window.event : e;
	    if(ev.keyCode==13) {

	           $("#Login").click();//处理事件

	     }
	}
});  