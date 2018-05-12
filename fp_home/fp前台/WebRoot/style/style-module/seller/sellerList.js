
var productName="All";    //产品名称
var CountryName="All";       //地区名称
var nameStr="";           //供应商名称
var argType="";           //传递过来的类型
var argValue="";           //传递过来的值

/***********************查询采购商列表***************************/
function GetSeller(pageIndex,pageSize){
	$("#sellerList tr:gt(0)").remove();
	
	var pageIndexStart=(pageIndex-1)*pageSize;
	var productNameStr="";
	var CountryNameStr="";
	if (productName=="All"){
		productNameStr="";
	}else{
		productNameStr=productName;
	}
	if (CountryName=="All"){
		CountryNameStr="";
	}else{
		CountryNameStr=CountryName;
	}
	var url=baseUrl +"/Seller!getSellerList.dhtml?pageindex=" +pageIndexStart +"&pagesize="+pageSize
					+ "&product="+productNameStr +"&country="+CountryNameStr
					+"&startMonth=" +$("#startMonth").val()+"&startTen="+$("#startTen").val()+
					"&endMonth="+$("#endMonth").val()+"&endTen="+$("#endTen").val() +"&companyName="+nameStr;
	$.get(url, function(data){
		var uid="";
		$.each(eval(data),function(i,n){
			var usersJson=eval(n);
			if (uid!=usersJson.userid){
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
				var newRow = "<tr><td class=\"pl20\"><a href=\"sellerDetails.jsp?id=" +usersJson.userid +"\">"+
							"<img src=\""+logo +"\"></a>"+
							"<a href=\"sellerDetails.jsp?id="+usersJson.userid +"\"><span>" +companyennameShow +"</span></a></td>"+
							"<td class=\"tc\">"+ usersJson.countryenname+"</td>"+
							"<td class=\"tc\">"+ usersJson.salesvoiume+"</td>"+
							"<td class=\"tc\">"+ usersJson.buyerproduct+"</td></tr>";
				$("#sellerList thead").after(newRow);
				uid=usersJson.userid;
				
			}else{
				var pinfo=$("#sellerList tr:eq(1) td:eq(3)").text();
				$("#sellerList tr:eq(1) td:eq(3)").val(pinfo+"," +usersJson.buyerproduct);
			}
			
		});
	});
};

function ChangeSeller(){
	$("#pagenation").empty();
	$("#sellerList tr:gt(0)").remove();
	var CountryNameStr="";
	var productNameStr="";
	if (productName=="All"){
		productNameStr="";
	}else{
		productNameStr=productName;
	}
	if (CountryName=="All"){
		CountryNameStr="";
	}else{
		CountryNameStr=CountryName;
	}
	var url=baseUrl +"/Seller!getSellerCount.dhtml?product="+productNameStr +"&country="+CountryNameStr
					+"&startMonth=" +$("#startMonth").val()+"&startTen="+$("#startTen").val()+
					"&endMonth="+$("#endMonth").val()+"&endTen="+$("#endTen").val() +"&companyName="+nameStr;
	$.get(url, function(data){
		$.each(eval(data),function(i,n){
			var UsersJson=eval(n);
			if (UsersJson.countinfo>0){
				pageInstance = new PagingClass("pagenation", 8, UsersJson.countinfo,GetSeller);  
				$(".prev").bind("click",{start:pageInstance.pageIndex,typeInfo:"Prev"},pageInstance._changePage);
				$(".next").bind("click",{start:pageInstance.pageIndex,typeInfo:"Next"},pageInstance._changePage);
				$(".confirm").bind("click",pageInstance._aimPage);
				GetSeller(0,8);
			}
			
		});
	});
}

/***********************更改选择产品****************************/
function changeProduct(event){
	productName=$(this).text();
	$("#PName > a").removeClass("cc");
	$(this).addClass("cc");
	ChangeSeller();
};
/***********************更改选择地区****************************/
function changeCountry(event){
	CountryName=$(this).text();
	$("#CName > a").removeClass("cc");
	$(this).addClass("cc");
	ChangeSeller();
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
			if (argType=="product"){
				if (productJson.productenname==argValue){
					$("#PName > a").removeClass("cc");
					$productA.addClass("cc");
				}
			}
			$("#PName").append($productA);
		});
		$("#PName > a").bind("click",changeProduct);
	});
	
	/*****************加载国家**********************/
	var CountryUrl=baseUrl +"/ShopBase!getCountry.dhtml";
	$.get(CountryUrl, function(data){
		$.each(eval(data),function(i,n){
			var countryJson=eval(n);
			
			var CountryA=document.createElement("a");
			$CountryA=$(CountryA);
			$CountryA.append(countryJson.countryenname);
			$CountryA.attr("href","#");
			if (argType=="country"){
				if (countryJson.countryenname==argValue){
					$("#CName > a").removeClass("cc");
					$CountryA.addClass("cc");
				}
			}
			$("#CName").append($CountryA);
		});
		$("#CName > a").bind("click",changeCountry);
		
	});
});

/***********************加载供应商****************************/
$(function(){
	if (window.location.href.indexOf("?")!=-1){
		var argStr=window.location.href.split("?")[1];
		var typeStr=argStr.split("=")[0];
		argType=typeStr;
		if (typeStr=="product"){
			productName=argStr.split("=")[1];
			argValue=productName;
			
		}else if (typeStr=="country"){
			CountryName=argStr.split("=")[1];
			argValue=CountryName;
		}else if (typeStr=="name"){
			nameStr=argStr.split("=")[1];
			$("#mySle").val("0");
			$("#ser").attr("value",nameStr);
		}else if (typeStr=="start"){
			var start= argStr.split("&")[0].split("=")[1];
			var end= argStr.split("&")[1].split("=")[1];
			if (start!=""){
				$("#startMonth").attr("value",start);
			}
			if (end!=""){
				$("#endMonth").attr("value",end);
			}
		}
	}
	ChangeSeller();
});
/***********************月份过滤****************************/
$(function(){
	$("#SearchBtn").bind("click",ChangeSeller);
});