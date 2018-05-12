/***********************获取验证码****************************/
function getCode(){
	var username=$("#username").val();
	if (username==""){
		alert("username can not empty!");
		$("#username").val("");
		$("#username").focus();
		return;
	}
	$("#getCode").attr("disabled","false");
	var url=baseUrl +"/account!getCodeByUser.dhtml?username=" + username;
	$.get(url, function(data){
		if (data=="0"){
			alert("The mail sent successfully, please check!");
			$("#getCode").attr("disabled","false");
		}else if(data=="-1"){
			alert("The user does not have a mailbox!");
			$("#username").val("");
			$("#username").focus();
			$("#getCode").attr("disabled","true");
		}else{
			alert("The mail can not be delieved!");
		}
	});
}
/***********************重置****************************/
function Reset(){
	var username=$("#username").val();
	var newpwd=$("#newpwd").val();
	var conpwd=$("#conpwd").val();
	var securityCode=$("#securityCode").val();
	if (username==""){
		alert("username can not empty!");
		$("#username").val("");
		$("#username").focus();
		return false;
	}
	if (newpwd==""){
		alert("password can not empty!");
		$("#newpwd").val("");
		$("#newpwd").focus();
		return false;
	}
	if (newpwd!=conpwd){
		alert("The passwords you typed do not match. Type the same password in both text boxes!");
		$("#newpwd").val("");
		$("#conpwd").val("");
		$("#newpwd").focus();
		return false;
	}
	if (securityCode==""){
		alert("securityCode can not empty!");
		$("#securityCode").val("");
		$("#securityCode").focus();
		return false;
	}
	var url=baseUrl +"/account!updatePwd.dhtml?username=" + username+
	"&newPassword=" + newpwd+"&securityCode=" +securityCode;
	$.get(url, function(data){
		if (data=="-1"){
			alert("verification code is not correct!");
			$("#username").val("");
			$("#password").val("");
			$("#username").focus();
		}else if(data=="1"){
			alert("Reset failed,username or password is wrong! ");
			$("#securityCode").val("");
			$("#securityCode").focus();
		}else{
			alert("Reset Success!");
			window.location.href="login.jsp";
		}
	});
}

$(function(){
	document.onkeydown = function(e){ 
	    var ev = document.all ? window.event : e;
	    if(ev.keyCode==13) {

	           $("#Reset").click();//处理事件

	     }
	}
});  