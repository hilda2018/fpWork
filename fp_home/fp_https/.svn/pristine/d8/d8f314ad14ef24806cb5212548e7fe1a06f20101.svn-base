
var serviceName="All";    //产品名称
var nameStr="";           //服务商名称

/***********************查询供应商列表***************************/
function GetService(pageIndex,pageSize){
	$("#serviceList tr:gt(0)").remove();
	
	var pageIndexStart=(pageIndex-1)*pageSize;
	var serviceNameStr="";
	if (serviceName=="All"){
		serviceNameStr="";
	}else{
		serviceNameStr=serviceName;
	}
	var url=baseUrl +"/Service!getServiceList.dhtml?pageindex=" +pageIndexStart +"&pagesize="+pageSize
					+ "&serviceType="+serviceNameStr +"&companyName="+nameStr;
	$.get(url, function(data){
		$.each(eval(data),function(i,n){
			var usersJson=eval(n);
			var logo = usersJson.logo;
			if (logo==""){
				logo = UploadSmallUrl + "70.jpg";
			}else{
				logo = UploadSmallUrl + logo;
			}
			var companyenname=usersJson.companyenname;
			var companyennameShow="";
			if (companyenname!=null){
				if (companyenname.length>33){
					companyennameShow=companyenname.substr(0,23)+"...";
				}else{
					companyennameShow=companyenname;
				}
			}
			var newRow = "<tr><td class=\"pl20\"><a href=\"serviceDetails.jsp?id=" +usersJson.userid +"\">"+
						"<img src=\""+logo +"\"></a>"+
						"<a href=\"serviceDetails.jsp?id="+usersJson.userid +"\"><span>" +companyennameShow +"</span></a></td>"+
						"<td class=\"tc\">"+ usersJson.countryenname+"</td>"+
						"<td class=\"tc\">"+ usersJson.salesvoiume+"</td>"+
						"<td class=\"tc\">"+ usersJson.buyerproduct+"</td></tr>";
			$("#serviceList thead").after(newRow);
		});
	});
};

function ChangeService(){
	$("#pagenation").empty();
	$("#serviceList tr:gt(0)").remove();
	var serviceNameStr="";
	if (serviceName=="All"){
		serviceNameStr="";
	}else{
		serviceNameStr=serviceName;
	}
	var url=baseUrl +"/Service!getServiceCount.dhtml?serviceType="+serviceNameStr +"&companyName="+nameStr;
	$.get(url, function(data){
		$.each(eval(data),function(i,n){
			var UsersJson=eval(n);
			if (UsersJson.countinfo>0){
				pageInstance = new PagingClass("pagenation", 8, UsersJson.countinfo,GetService);  
				$(".prev").bind("click",{start:pageInstance.pageIndex,typeInfo:"Prev"},pageInstance._changePage);
				$(".next").bind("click",{start:pageInstance.pageIndex,typeInfo:"Next"},pageInstance._changePage);
				$(".confirm").bind("click",pageInstance._aimPage);
				GetService(0,8);
			}
			
		});
	});
}

/***********************更改选择产品****************************/
function changeServiceType(event){
	serviceName=$(this).text();
	$("#SName > a").removeClass("cc");
	$(this).addClass("cc");
	ChangeService();
};

/***********************界面初始化****************************/
$(function(){
	
	/*****************加载产品**********************/
	var servicetUrl=baseUrl +"/ShopBase!getServiceType.dhtml";
	$.get(servicetUrl, function(data){
		$.each(eval(data),function(i,n){
			var serviceJson=eval(n);
			
			var serviceA=document.createElement("a");
			$serviceA=$(serviceA);
			$serviceA.append(serviceJson.servicetypeenname);
			$serviceA.attr("href","#");
			$("#SName").append($serviceA);
		});
		$("#SName > a").bind("click",changeServiceType);
	});
	
});

/***********************加载服务商****************************/
$(function(){
	if (window.location.href.indexOf("?")!=-1){
		var argStr=window.location.href.split("?")[1];
		var typeStr=argStr.split("=")[0];
		if (typeStr=="name"){
			nameStr=argStr.split("=")[1];
			$("#mySle").val("2");
			$("#ser").attr("value",nameStr);
		}
	}
	ChangeService();
});