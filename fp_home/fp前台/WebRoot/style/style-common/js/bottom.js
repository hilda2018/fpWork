/** ******************加载底部信息***************** */
/*
$(function() {
	var url = baseUrl + "/ShopBase!getBottomLinks.dhtml";
	$.ajax({
		type : "GET",
		url : url,
		async : false,
		success : function(data) {
			$.each(eval(data), function(i, n) {
				var bottomLinksJson = eval(n);
				var bltype = bottomLinksJson.bottomlinkstype;
				
				var blDD = document.createElement("dd");
				$blDD = $(blDD);
				var blA=document.createElement("a");
				$blA = $(blA);
				$blA.attr("href", bottomLinksJson.bottomlinksaddress);
				$blA.attr("target", "_blank");
				$blA.append(bottomLinksJson.bottomlinksname);
				$blDD.append($blA);
				
				switch (bltype) {
				case "0":
					$("#AboutUs").append($blDD);
					break;
				case "1":
					$("#HelpCenter").append($blDD);
					break;
				case "2":
					$("#WebsiteRules").append($blDD);
					break;
				case "3":
					$("#ContactUs").append($blDD);
					break;
				case "4":
					$("#Complaint").append($blDD);
					break;
				case "5":
					$("#LearningCenter").append($blDD);
					break;
				}
				
			});
		}
	});
});
*/