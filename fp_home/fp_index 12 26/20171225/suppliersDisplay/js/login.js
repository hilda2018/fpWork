
checkUser();
/** ******************检查用户是否登录***************** */
$('#LoginU').click(function(){
	$('#framelogin').show();
alert(	$('#framelogin').contents().find('#usernameInfo') );
		checkUser();
});

/***************************清空用户*************************/
	
$('#logout').click(function(){
		clearUser();
});
	

/** ******************清空用户***************** */
function clearUser() {
	var url = "clearname.txt?num=" + Math.random();
	$.ajax({
		type : "GET",
		url : url,
		async : false,
		success : function(data) {
			
			$("#LoginA").css("display", "none");
			$("#LoginU").css("display", "block");
			//不使用单点登录
			//window.location.href = "newIndex.html";
			//如果使用单点登录
			//window.location.href = "";
		}
	});
}

function checkUser(){
	var url = "username.txt?num=" + Math.random();
	$.ajax({
			type : "GET",
			url : url,
			async : false,
			success : function(data) {
				if (data != "") {//已经登录
					$("#LoginA").css("display", "block");
					$("#usernameInfo").empty();
					$("#usernameInfo").append(data);
					$("#LoginU").css("display", "none");
					$("#enterFP").attr("href", "");//后一个参数:生鲜港后台地址
				} else {
					$("#LoginA").css("display", "none");
					$("#LoginU").css("display", "block");
				}
			}
	});
	
}
