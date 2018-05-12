/** *********************加载产品*************************** */
$(function() {
	var productUrl = baseUrl + "/ShopBase!getProductIndex.dhtml";
	$.get(productUrl, function(data) {
		$.each(eval(data), function(i, n) {
			var productJson = eval(n);
			var productLi = document.createElement("li");
			$productLi = $(productLi);
			var productA = document.createElement("a");
			$productA = $(productA);
			$productA.attr("href", "../seller/sellerList.jsp?product="
					+ productJson.productenname);
			$productA.append(productJson.productenname);
			$productLi.append($productA);
			$("#product").append($productLi);
		});
	});
});

/** *********************加载地区*************************** */
$(function() {
	var countryUrl = baseUrl + "/ShopBase!getCountryIndex.dhtml";
	$.get(countryUrl, function(data) {
		$.each(eval(data), function(i, n) {
			var countryJson = eval(n);
			var areaLi = document.createElement("li");
			$areaLi = $(areaLi);
			var areaA = document.createElement("a");
			$areaA = $(areaA);
			$areaA.attr("href", "../seller/sellerList.jsp?country="
					+ countryJson.countryenname);
			$areaA.append(countryJson.countryenname);
			$areaLi.append($areaA);
			$("#Area").append($areaLi);
		});
	});
});
/** *********************加载海关新闻详情************************* */
$(function() {
	var customsNewsUrl = baseUrl + "/ShopBase!getCustomsNews.dhtml";
	$.get(customsNewsUrl, function(data) {
		$.each(eval(data), function(i, n) {
			var customsNewsJson = eval(n);
			var prdDetailsLi = document.createElement("li");
			$prdDetailsLi = $(prdDetailsLi);
			if (i == 0) {
				$prdDetailsLi.addClass("clearfix first");
			} else {
				$prdDetailsLi.addClass("clearfix last");
			}
			
			var customsnewsenname = customsNewsJson.customsnewsenname;
			var customsnewsennameShow;
			var customsnewscontent = customsNewsJson.customsnewscontent;
			var customsnewscontentShow;
			if (customsnewsenname.length > 15) {
				customsnewsennameShow = customsnewsenname.substr(0, 15) + "......";
			} else {
				customsnewsennameShow = customsnewsenname;
			}
			if (customsnewscontent.length > 30) {
				customsnewscontentShow = customsnewscontent.substr(0, 30) + "......";
			} else {
				customsnewscontentShow = customsnewscontent;
			}
			
			var prdDetailsh3 = document.createElement("h3");
			$prdDetailsh3 = $(prdDetailsh3);
			$prdDetailsh3.append(customsnewsennameShow);

			var prdDetailsspan = document.createElement("span");
			$prdDetailsspan = $(prdDetailsspan);
			$prdDetailsspan.append(customsnewscontentShow);

			var prdDetailsdiv = document.createElement("div");
			$prdDetailsdiv = $(prdDetailsdiv);
			$prdDetailsdiv.addClass("detail");

			var prdDetailsdetA = document.createElement("a");
			$prdDetailsdetA = $(prdDetailsdetA);
			$prdDetailsdetA.attr("href", customsNewsJson.customsnewsaddress);
			$prdDetailsdetA.attr("target", "_blank");
			$prdDetailsdetA.addClass("visit");
			$prdDetailsdetA.attr("id", customsNewsJson.customsnewsid);
			$prdDetailsdetA.html("Visit Now >");
			$prdDetailsdiv.append($prdDetailsdetA);
			var prdDetailsdetImg = document.createElement("img");
			$prdDetailsdetImg = $(prdDetailsdetImg);
			$prdDetailsdetImg.attr("src", UploadSmallUrl + customsNewsJson.customsnewsimgurl);
			$prdDetailsdiv.append($prdDetailsdetImg);

			$prdDetailsLi.append($prdDetailsh3);
			$prdDetailsLi.append($prdDetailsspan);
			$prdDetailsLi.append($prdDetailsdiv);
			$("#ProductDetails").append($prdDetailsLi);
		});
	});
});
/** *********************加载供应商*************************** */
$(function() {
	var sellersUrl = baseUrl + "/ShopBase!getSellerIndex.dhtml";
	$.get(sellersUrl, function(data) {
		$.each(eval(data), function(i, n) {
			var sellerJson = eval(n);
			var sellerLi = document.createElement("li");
			$sellerLi = $(sellerLi);

			var sellerA = document.createElement("a");
			$sellerA = $(sellerA);
			$sellerA.attr("href", "../seller/sellerDetails.jsp?id="
					+ sellerJson.userid);
			$sellerA.attr("id", sellerJson.userid);

			var sellerImg = document.createElement("img");
			$sellerImg = $(sellerImg);
			var logo = sellerJson.logo;
			if (logo==""){
				logo="140.jpg";
			}
			$sellerImg.attr("src", UploadMiddleUrl + logo);
			$sellerA.append($sellerImg);

			var sellerP = document.createElement("p");
			$sellerP = $(sellerP);
			$sellerP.addClass("name");
			$sellerP.append(sellerJson.companyenname);
			$sellerA.append($sellerP);

			var sellerSpan = document.createElement("span");
			$sellerSpan = $(sellerSpan);
			$sellerSpan.addClass("time");
			$sellerSpan.append(sellerJson.salesvoiume);
			$sellerA.append($sellerSpan);

			$sellerLi.append($sellerA);
			$("#sellerList").append($sellerLi);
		});
	});
});

/** *********************加载采购商*************************** */
$(function() {
	var buyerUrl = baseUrl + "/ShopBase!getBuyerIndex.dhtml";
	$.get(buyerUrl, function(data) {
		$.each(eval(data), function(i, n) {
			var buyerJson = eval(n);
			var buyerLi = document.createElement("li");
			$buyerLi = $(buyerLi);

			var buyerA = document.createElement("a");
			$buyerA = $(buyerA);
			$buyerA.attr("href", "../buyer/buyerDetails.jsp?id=" + buyerJson.userid);
			$buyerA.attr("id", buyerJson.userid);

			var buyerImg = document.createElement("img");
			$buyerImg = $(buyerImg);
			var logo = buyerJson.logo;
			if (logo==""){
				logo="140.jpg";
			}
			$buyerImg.attr("src", UploadMiddleUrl + logo);
			$buyerA.append($buyerImg);

			var buyerP = document.createElement("p");
			$buyerP = $(buyerP);
			$buyerP.addClass("name");
			$buyerP.append(buyerJson.companyenname);
			$buyerA.append($buyerP);

			var buyerSpan = document.createElement("span");
			$buyerSpan = $(buyerSpan);
			$buyerSpan.addClass("time");
			$buyerSpan.append(buyerJson.salesvoiume);
			$buyerA.append($buyerSpan);

			$buyerLi.append($buyerA);
			$("#buyerList").append($buyerLi);
		});
	});
	
});
/** *********************加载服务商*************************** */
$(function() {
	var serviceUrl = baseUrl + "/ShopBase!getServiceIndex.dhtml";
	$.get(serviceUrl, function(data) {
		$.each(eval(data), function(i, n) {
			var serviceJson = eval(n);
			var serviceLi = document.createElement("li");
			$serviceLi = $(serviceLi);

			var serviceA = document.createElement("a");
			$serviceA = $(serviceA);
			$serviceA.attr("href", "../service/serviceDetails.jsp?id="
					+ serviceJson.userid);
			$serviceA.attr("id", serviceJson.userid);

			var serviceImg = document.createElement("img");
			$serviceImg = $(serviceImg);
			var logo = serviceJson.logo;
			if (logo==""){
				logo="140.jpg";
			}
			$serviceImg.attr("src", UploadMiddleUrl + logo);
			$serviceA.append($serviceImg);

			var serviceP = document.createElement("p");
			$serviceP = $(serviceP);
			$serviceP.addClass("name");
			$serviceP.append(serviceJson.companyenname);
			$serviceA.append($serviceP);

			var serviceAMore = document.createElement("a");
			$serviceAMore = $(serviceAMore);
			$serviceAMore.attr("href", "#");
			$serviceAMore.addClass("view");
			$serviceAMore.append("More > ");
			$serviceA.append($serviceAMore);

			$serviceLi.append($serviceA);
			$("#serviceList").append($serviceLi);
		});
	});
	
});

/** *********************加载新闻*************************** */
$(function() {
	var url = baseUrl
			+ "/information!queryIndexNews.dhtml?pageindex=0&pagesize=3";
	$.get(url, function(data) {
		$.each(eval(data), function(i, n) {
			var informationJson = eval(n);
			var informationLi = document.createElement("li");
			$informationLi = $(informationLi);
			var informationA = document.createElement("a");
			$informationA = $(informationA);
			
			var islink = informationJson.islink;
			if (islink=="Y"){
				$informationA.attr("href", informationJson.informationsource);
				$informationA.attr("target", "_blank");
			}else{
				$informationA.attr("href","../information/informationDetails.jsp?id="
				  + informationJson.informationid);
				
				$informationA.attr("id",informationJson.informationid);
			}
			var infoName = informationJson.informationname;
			var infoNameShow;
			if (infoName.length > 53) {
				infoNameShow = infoName.substr(0, 51) + "......";
			} else {
				infoNameShow = infoName;
			}
			$informationA.append(infoNameShow);
			$informationLi.append(informationA);
			$("#newsList").append($informationLi);
		});
	});
});

/** *********************按日期查询供应商*************************** */
function turnSeller() {
	window.location.href = "../seller/sellerList.jsp?start="
			+ $("#startTime").val() + "&end=" + $("#endTime").val();
}

/** *********************进入后台界面*************************** */
function turnFreshPort(TypeInfo) {
	var url = baseUrl + "/account!getRole.dhtml";
	$.get(url, function(data) {
		if (data == "-1") {
			alert("Please log in!");
			return;
		} else if (data == "0") {
			switch (TypeInfo) {
			case "0":
				window.location.href = InvUrl;
				break;
			case "1":
				window.location.href = LogisticUrl;
				break;
			case "2":
				window.location.href = SettlementUrl;
				break;
			case "3":
				window.location.href = PaymentUrl;
				break;
			}
		} else {
			alert("you have no right to take this operation at present!");
			return;
		}
	});
}
