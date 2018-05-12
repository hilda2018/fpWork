
var productName="All";    //产品名称
var AreaName="All";       //地区名称
var nameStr="";           //采购商名称

/***********************查询采购商列表***************************/
function GetBuyer(pageIndex,pageSize){
	$("#buyerList tr:gt(0)").remove();
	
	var pageIndexStart=(pageIndex-1)*pageSize;
	var productNameStr="";
	var AreaNameStr="";
	if (productName=="All"){
		productNameStr="";
	}else{
		productNameStr=productName;
	}
	if (AreaName=="All"){
		AreaNameStr="";
	}else{
		AreaNameStr=AreaName;
	}
	var url=baseUrl +"/Buyer!getBuyerList.dhtml?pageindex=" +pageIndexStart +"&pagesize="+pageSize
					+ "&product="+productNameStr +"&area="+AreaNameStr +"&companyName="+nameStr;
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
			var newRow = "<tr><td class=\"pl20\"><a href=\"buyerDetails.jsp?id=" +usersJson.userid +"\">"+
						"<img src=\""+logo +"\"></a>"+
						"<a href=\"buyerDetails.jsp?id="+usersJson.userid +"\"><span>" +companyennameShow +"</span></a></td>"+
						"<td class=\"tc\">"+ usersJson.buyerarea+"</td>"+
						"<td class=\"tc\">"+ usersJson.salesvoiume+"</td>"+
						"<td class=\"tc\">"+ usersJson.buyerproduct+"</td></tr>";
			$("#buyerList thead").after(newRow);
		});
	});
};

function ChangeBuyer(){
	$("#pagenation").empty();
	$("#buyerList tr:gt(0)").remove();
	var productNameStr="";
	var AreaNameStr="";
	if (productName=="All"){
		productNameStr="";
	}else{
		productNameStr=productName;
	}
	if (AreaName=="All"){
		AreaNameStr="";
	}else{
		AreaNameStr=AreaName;
	}
	var url=baseUrl +"/Buyer!getBuyerCount.dhtml?product="+productNameStr +"&area="+AreaNameStr +"&companyName="+nameStr;
	$.get(url, function(data){
		$.each(eval(data),function(i,n){
			var UsersJson=eval(n);
			if (UsersJson.countinfo>0){
				pageInstance = new PagingClass("pagenation", 8, UsersJson.countinfo,GetBuyer);  
				$(".prev").bind("click",{start:pageInstance.pageIndex,typeInfo:"Prev"},pageInstance._changePage);
				$(".next").bind("click",{start:pageInstance.pageIndex,typeInfo:"Next"},pageInstance._changePage);
				$(".confirm").bind("click",pageInstance._aimPage);
				GetBuyer(0,8);
			}
			
		});
	});
}

/***********************更改选择产品****************************/
function changeProduct(event){
	productName=$(this).text();
	$("#PName > a").removeClass("cc");
	$(this).addClass("cc");
	ChangeBuyer();
};
/***********************更改选择地区****************************/
function changeArea(event){
	AreaName=$(this).text();
	$("#AName > a").removeClass("cc");
	$(this).addClass("cc");
	ChangeBuyer();
};

/***********************界面初始化****************************/
$(function(){
	
	/*****************加载产品**********************/
	var ProductUrl=baseUrl +"/ShopBase!getProduct.dhtml";
	$.get(ProductUrl, function(data){
		$.each(eval(data),function(i,n){
			var productJson=eval(n);
			
			var productA=document.createElement("a");
			$productA=$(productA);
			$productA.append(productJson.productenname);
			$productA.attr("href","#");
			$("#PName").append($productA);
		});
		$("#PName > a").bind("click",changeProduct);
	});
	
	/*****************加载地区**********************/
	var AreaUrl=baseUrl +"/ShopBase!getArea.dhtml";
	$.get(AreaUrl, function(data){
		$.each(eval(data),function(i,n){
			var areaJson=eval(n);
			
			var AreaA=document.createElement("a");
			$AreaA=$(AreaA);
			$AreaA.append(areaJson.countryareaenname);
			$AreaA.attr("href","#");
			$("#AName").append($AreaA);
		});
		$("#AName > a").bind("click",changeArea);
	});
});

/***********************加载采购商****************************/
$(function(){
	if (window.location.href.indexOf("?")!=-1){
		var argStr=window.location.href.split("?")[1];
		var typeStr=argStr.split("=")[0];
		if (typeStr=="name"){
			nameStr=argStr.split("=")[1];
			$("#mySle").val("1");
			$("#ser").attr("value",nameStr);
		}
	}
	ChangeBuyer();
});