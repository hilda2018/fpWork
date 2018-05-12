$(function() {
	$(".search .role")
			.hover(
					function() {
						$(this).find("li").css("display", "block");
						$(this)
								.find("li em")
								.css(
										"background",
										"url(${applicationScope.sysConfigs['sysPath']}/style/style-common/images/arrow_t.png) no-repeat");
					},
					function() {
						$(this).find("li:not(:first)").css("display", "none");
						$(this)
								.find("li em")
								.css(
										"background",
										"url(${applicationScope.sysConfigs['sysPath']}/style/style-common/images/arrow_d.png) no-repeat");
					});
});

/** ******************检查用户是否登录***************** */
$(function() {
	var url = baseUrl + "/account!getUser.dhtml?num=" + Math.random();
	$.ajax({
		type : "GET",
		url : url,
		async : false,
		success : function(data) {
			if (data != "") {
				$("#LoginA").css("display", "block");
				$("#usernameInfo").empty();
				$("#usernameInfo").append(data);
				$("#LoginU").css("display", "none");
				$("#enterFP").attr("href", foreignprocurementPath);
			} else {
				$("#LoginA").css("display", "none");
				$("#LoginU").css("display", "block");
			}
		}
	});
});

/** ******************清空用户***************** */
function clearUser() {
	var url = baseUrl + "/account!clearUser.dhtml?num=" + Math.random();
	$.ajax({
		type : "GET",
		url : url,
		async : false,
		success : function(data) {
			//不使用单点登录
			//window.location.href = "../index/index.jsp";
			//如果使用单点登录
			window.location.href = loginPath+"/logout?service="+basePath;
		}
	});
}

/*---------------------查询按钮事件-----------------*/
$(function() {
	$(".ser-btn").click(
			function() {
				var te = $("#ser").attr("value");
				var sel = $("#mySle :selected").text();
				if (sel == "Seller") {
					if (window.location.href.split("?")[0] != baseUrl
							+ "/pages/seller/sellerList.jsp") {
						window.location.href = baseUrl
								+ "/pages/seller/sellerList.jsp?name=" + te;
					} else {
						nameStr = te;
						ChangeSeller();
					}

				} else if (sel == "Buyer") {
					if (window.location.href.split("?")[0] != baseUrl
							+ "/pages/buyer/buyerList.jsp") {
						window.location.href = baseUrl
								+ "/pages/buyer/buyerList.jsp?name=" + te;
					} else {
						nameStr = te;
						ChangeBuyer();
					}
				} else {
					if (window.location.href.split("?")[0] != baseUrl
							+ "/pages/service/serviceList.jsp") {
						window.location.href = baseUrl
								+ "/pages/service/serviceList.jsp?name=" + te;
					} else {
						nameStr = te;
						ChangeService();
					}
				}
			});
});