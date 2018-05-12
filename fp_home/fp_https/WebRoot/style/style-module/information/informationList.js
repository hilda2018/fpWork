/**************请求并绑定资讯信息*****************/
function GetInformation(pageIndex,pageSize){
	$("#informationList").empty();
	var pageIndexStart=(pageIndex-1)*pageSize;
	var url=baseUrl +"/information!queryIndexNews.dhtml?pageindex=" +pageIndexStart +"&pagesize="+pageSize;
	$.get(url, function(data){
		$.each(eval(data),function(i,n){
			
			var informationJson=eval(n);
			
			var informationLi=document.createElement("li");
			$informationLi=$(informationLi);
			
			var informationDivD=document.createElement("div");
			$informationDivD=$(informationDivD);
			$informationDivD.addClass("item-date");
			//$informationDivD.append("111");
			$informationDivD.append(informationJson.informationdate);
			$informationLi.append($informationDivD);
			
			var informationDivN=document.createElement("div");
			$informationDivN=$(informationDivN);
			$informationDivN.addClass("item-name");
			var informationAN=document.createElement("a");
			$informationAN=$(informationAN);
			var islink = informationJson.islink;
			if (islink=="Y"){
				$informationAN.attr("href", informationJson.informationsource);
				$informationAN.attr("target", "_blank");
			}else{
				$informationAN.attr("href","informationDetails.jsp?id=" + informationJson.informationid);
			}
			
			$informationAN.append(informationJson.informationname);
			$informationDivN.append($informationAN);
			$informationLi.append($informationDivN);
			
			var informationDivI=document.createElement("div");
			$informationDivI=$(informationDivI);
			$informationDivI.addClass("item-intro");
			var infoIntro=informationJson.informationsource;
			var infoIntroShow;
			if (infoIntro.length>53){
				infoIntroShow=infoIntro.substr(0,400)+"......";
			}else{
				infoIntroShow=infoIntro;
			}
			$informationDivI.append(infoIntroShow);
			$informationLi.append($informationDivI);
			
			var informationDivC=document.createElement("div");
			$informationDivC=$(informationDivC);
			$informationDivC.addClass("item-cont");
			var informationAC=document.createElement("a");
			$informationAC=$(informationAC);
			if (islink=="Y"){
				$informationAC.attr("href", informationJson.informationsource);
				$informationAC.attr("target", "_blank");
			}else{
				$informationAC.attr("href","informationDetails.jsp?id=" + informationJson.informationid);
			}
			$informationAC.addClass("con-btn");
			$informationAC.append("Continue");
			$informationDivC.append($informationAC);
			$informationLi.append($informationDivC);
			
			$("#informationList").append($informationLi);
		});
	});
};
/***********************加载新闻****************************/
$(function(){
	var url=baseUrl +"/information!queryCount.dhtml";
	$.get(url, function(data){
		$.each(eval(data),function(i,n){
			var informationJson=eval(n);
			if (informationJson.countinfo>0){
				pageInstance = new PagingClass("pagenation", 3, informationJson.countinfo,GetInformation);  
				$(".prev").bind("click",{start:pageInstance.pageIndex,typeInfo:"Prev"},pageInstance._changePage);
				$(".next").bind("click",{start:pageInstance.pageIndex,typeInfo:"Next"},pageInstance._changePage);
				$(".confirm").bind("click",pageInstance._aimPage);
				GetInformation(1,3);
			}
			
		});
	});
});