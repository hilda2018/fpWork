/***********************加载用户****************************/
$(function(){
	if (window.location.href.indexOf("?")!=-1){
		var id=window.location.href.split("=")[1];
		var url=baseUrl +"/ShopBase!getUserShowByID.dhtml?userID=" +id;
		$.get(url, function(data){
			var first=true;
			$.each(eval(data),function(i,n){
				var UserShowJson=eval(n);
				if (first==true){
					var logo = UserShowJson.logo;
					if (logo==""){
						logo = UploadMiddleUrl + "140.jpg";
					}else{
						logo = UploadMiddleUrl + logo;
					}
					$("#logo").attr("src",logo);
					$("#company").append(UserShowJson.companyenname);
					$("#address").append(UserShowJson.addressen);
					$("#website").append(UserShowJson.website);
					$("#phone").append(UserShowJson.telcountry + " " +UserShowJson.telarea + " " + UserShowJson.telnum);
					$("#fax").append(UserShowJson.faxcountry + " " +UserShowJson.faxarea + " " + UserShowJson.faxnum);
					$("#email").append(UserShowJson.email);
					$("#content").append(UserShowJson.content);
					first=false;
				}
				
				var companyImg = UserShowJson.picvideopath;
				if (companyImg!=""){
					companyImg = UploadCustomUrl + companyImg;
					
					var UserShowLi=document.createElement("li");
					$UserShowLi=$(UserShowLi);
					
					var UserSHowA=document.createElement("a");
					$UserSHowA=$(UserSHowA);
					$UserSHowA.attr("href","#");
					
					var UserShowImg = document.createElement("img");
					$UserShowImg=$(UserShowImg);
					$UserShowImg.attr("src",companyImg);
					
					$UserSHowA.append($UserShowImg);
					$UserShowLi.append($UserSHowA);
					$("#bigImg").append($UserShowLi);
					
					var UserShowSpan = document.createElement("span");
					$UserShowSpan=$(UserShowSpan);
					$UserShowSpan.addClass("");
					$UserShowSpan.append("&nbsp;");
					$("#btnSmall").append($UserShowSpan);
				}
			});
		});
		
		var supplierImgUrl=baseUrl +"/Seller!getSupplierPrdImg.dhtml?userID=" +id;
		$.get(supplierImgUrl, function(data){
			$.each(eval(data),function(i,n){
				var spImgJson=eval(n);
				var pImg=spImgJson.supplierproductimg;
				if (pImg==""){
					pImg = UploadMiddleUrl + "140.jpg";
				}else{
					pImg = UploadMiddleUrl + pImg;
				}
				
				var pImgLi=document.createElement("li");
				$pImgLi=$(pImgLi);
				
				var pImgA=document.createElement("a");
				$pImgA=$(pImgA);
				$pImgA.attr("href","sellerProduct.jsp?spid=" + spImgJson.supplierproductid);
				
				
				var pImgI=document.createElement("img");
				$pImgI=$(pImgI);
				$pImgI.attr("src",pImg);
				$pImgA.append($pImgI);
				
				var pImgS=document.createElement("span");
				$pImgS=$(pImgS);
				$pImgS.append(spImgJson.productid);
				$pImgA.append($pImgS);
				
				$pImgLi.append($pImgA);
				
				$("#supplierImgList").append($pImgLi);
				
			});
		});
	}
});