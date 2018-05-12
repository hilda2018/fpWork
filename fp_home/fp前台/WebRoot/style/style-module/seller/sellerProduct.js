/***********************更换月份*******************************/
function changeMonth(oldMonth){
	if (oldMonth!=""){
		switch (oldMonth){
		case "1":
			return "January";
			break;
		case "2":
			return "February";
			break;
		case "3":
			return "March";
			break;
		case "4":
			return "April";
			break;
		case "5":
			return "May";
			break;
		case "6":
			return "June";
			break;
		case "7":
			return "July";
			break;
		case "8":
			return "August";
			break;
		case "9":
			return "September";
			break;
		case "10":
			return "October";
			break;
		case "11":
			return "November";
			break;
		case "12":
			return "December";
			break;
		}
	};
}
/***********************更换旬********************************/
function changeTen(oldTen){
	if (oldTen!=""){
		switch (oldTen){
		case "0":
			return "Early";
			break;
		case "1":
			return "Middle";
			break;
		case "2":
			return "Late";
			break;
		}
	};
}
/***********************加载产品信息****************************/
$(function(){
	if (window.location.href.indexOf("?")!=-1){
		var spid=window.location.href.split("=")[1];
		var url=baseUrl +"/Seller!getSupplierPrdInfo.dhtml?supplierProductID=" +spid;
		$.get(url, function(data){
			var first=true;
			var second=true;
			$.each(eval(data),function(i,n){
				var spProductJson=eval(n);
				if (first==true){
					var supplierproductimg = spProductJson.supplierproductimg;
					if (supplierproductimg==""){
						supplierproductimg = UploadBigUrl + "400.jpg";
					}else{
						supplierproductimg = UploadBigUrl + supplierproductimg;
					}
					$("#indexImg").attr("src",supplierproductimg);
					$("#indexImg").attr("jqimg",supplierproductimg);
					
					$("#prdName").append(spProductJson.productid);
					$("#varietyName").append(spProductJson.varietiesid);
					
					if (spProductJson.startsupplymonth!="" || spProductJson.endsupplymonth!=""){
						var startMonth = changeMonth(spProductJson.startsupplymonth);
						var startTen = changeTen(spProductJson.startsupplyten);
						var endMonth = changeMonth(spProductJson.endsupplymonth);
						var endTen = changeTen(spProductJson.endsupplyten);
						$("#PeriodName").append(startTen + " " + startMonth + " to " + endTen + " " + endMonth);
					}
					
					$("#MOQName").append(spProductJson.moq + spProductJson.moqunit);
					$("#SupplyName").append(spProductJson.yearsupply + spProductJson.yearsupplyunit);
					$("#AcreageName").append(spProductJson.plantarea + spProductJson.plantareaunit);
					
					var trans = spProductJson.transport;
					if (trans=="0"){
						$("#transName").append("Sea");
					}else if (trans=="1"){
						$("#transName").append("Air");
					}
					
					$("#countryName").append(spProductJson.countryid +"," +spProductJson.countryareaid);
					$("#OrchardName").append(spProductJson.orchard);
					$("#Specification").append(spProductJson.introduction);
					first=false;
				}
				
				var spProductImg = spProductJson.picvideopath;
				if (spProductImg!=""){
					if (spProductJson.contenttype=="1"){     //图片展示
						spProductImg=UploadMiddleUrl + "140.jpg";
						var spImgLi=document.createElement("li");
						$spImgLi=$(spImgLi);
						
						var spImgi=document.createElement("img");
						$spImgi=$(spImgi);
						$spImgi.attr("src",spProductImg);
						$spImgLi.append($spImgi);
						$("#imgShowList").append($spImgLi);
					}else{     //证书展示
						spProductImg=UploadSmallUrl + "70.jpg";
						var spImgi=document.createElement("img");
						$spImgi=$(spImgi);
						$spImgi.attr("src",spProductImg);
						$spImgi.addClass("vm");
						
						$("#CertificaleName").append(spProductJson.picvideonotes);
						$("#CertificaleName").append($spImgi);
					}
				}
				$(".jqzoom").jqueryzoom({
					xzoom:400,
					yzoom:400,
					offset:10,
					position:"right",
					preload:1,
					lens:1
				});
				$("#spec-list").jdMarquee({
					deriction:"left",
					width:400,
					height:60,
					step:2,
					speed:4,
					delay:10,
					control:true,
					_front:"#spec-right",
					_back:"#spec-left"
				});
				$("#spec-list img").bind("mouseover",function(){
					var src=$(this).attr("src");
					$("#spec-n1 img").eq(0).attr({
						src:src.replace("\/n5\/","\/n1\/"),
						jqimg:src.replace("\/n5\/","\/n0\/")
					});
					$(this).css({
						"border":"1px solid #ff6600",
					});
				}).bind("mouseout",function(){
					$(this).css({
						"border":"1px solid #ccc",
					});
				});				
			});
		});
	}
});





$(function(){			
   
});