/***********************加载新闻****************************/
$(function(){
	if (window.location.href.indexOf("?")!=-1){
		var id=window.location.href.split("=")[1];
		var url=baseUrl +"/information!queryDetails.dhtml?id=" +id;
		$.get(url, function(data){
			$.each(eval(data),function(i,n){
				var informationJson=eval(n);
				$(".active").append(informationJson.informationname);
				
				var informationH3=document.createElement("h3");
				$informationH3=$(informationH3);
				$informationH3.append(informationJson.informationname);
				$("#informationList").append($informationH3);
				
				var informationP=document.createElement("p");
				$informationP=$(informationP);
				$informationP.addClass("datePublished");
				$informationP.append(informationJson.informationdate);
				$("#informationList").append($informationP);
				
				$("#informationList").append(informationJson.informationsource);
			});
		});
	}
});