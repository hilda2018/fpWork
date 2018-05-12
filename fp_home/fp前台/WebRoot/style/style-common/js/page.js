/* 
* 客户端分页类 
* @container 分页容器
* @pageSize  每页显示记录数 
* @rowCount  当前数据总数
* @Func      点击换页时触发的事件
*/
var pageInstance=void(0); 
/* 
* 构造
*/
function PagingClass(container,pageSize,rowCount,Func){
	this._container=container;        //容器ID
	this._pageSize=pageSize;          //页面显示条数
	this.pageIndex=1;                 //当前页
	this.pageindexShow=10;            //导航页显示个数
	this._rowCount=rowCount;          //当前数据总数
	this.pageCount=0;                 //当前页面总数
	this._func =Func;                 //绑定数据源事件
	this._initPaging();
};

/* 
* 初始化数据
*/
PagingClass.prototype._initPaging=function(){
	$container=$("#" + this._container);
	$container.empty();
	if (this._dataCount<=0){
		$("#" + container).innerHTML("暂无数据！");
	}else{
		this.pageCount=parseInt(this._rowCount%this._pageSize == 0 ? this._rowCount/this._pageSize : this._rowCount/this._pageSize+1);
		this._createInit();
	}
};

/* 
* 初始化界面
*/
PagingClass.prototype._createInit=function(){
	$container=$("#" + this._container);
	
	/********上一页********/
	var prevA=document.createElement("a");
	$prevA=$(prevA);
	$prevA.attr("href","#");
	$prevA.addClass("prev");
	$prevA.append("Pre");
	//$prevA.bind("click",{start:this.pageIndex,typeInfo:"Prev"},this._changePage);
	$container.append($prevA);
	
	/********导航页********/
	var middleSpan=document.createElement("span");
	$middleSpan=$(middleSpan);
	$middleSpan.attr("id","middleSpan");
	$container.append($middleSpan);
	this._updatePageShow(this.pageIndex);
	$(".pageShow").first().addClass("on");
	
	/********下一页********/
	var nextA=document.createElement("a");
	$nextA=$(nextA);
	$nextA.attr("href","#");
	$nextA.addClass("next");
	$nextA.append("Next");
	//$nextA.bind("click",{start:this.pageIndex,typeInfo:"Next"},this._changePage);
	$container.append($nextA);
	
	/*********总条数********/
	$container.append("&nbsp;&nbsp;Page&nbsp;&nbsp;" + this.pageCount+"&nbsp;&nbsp;&nbsp;&nbsp;to");
	
	/*********页面跳转********/
	var aimPageInput=document.createElement("input");
	$aimPageInput=$(aimPageInput);
	$aimPageInput.attr("type","text");
	$aimPageInput.attr("id","aimPageInput");
	$container.append($aimPageInput);
	
	$container.append("&nbsp;&nbsp;&nbsp;&nbsp;page&nbsp;&nbsp;&nbsp;&nbsp;");
	
	var confirmA=document.createElement("a");
	$confirmA=$(confirmA);
	$confirmA.attr("href","#");
	$confirmA.addClass("confirm");
	$confirmA.append("Confirm");
	//$confirmA.bind("click",this._aimPage);
	$container.append($confirmA);
};


/* 
* 更换导航页
* @pageIndexStart  导航页的起始页
*/
PagingClass.prototype._updatePageShow=function(pageIndexStart){
	$container=$("#" + this._container);
	$("#middleSpan").empty();
	
	if (pageIndexStart+this.pageindexShow-1<=this.pageCount){
		for(var i=pageIndexStart;i<pageIndexStart+this.pageindexShow;i++){
			this._createPageShow(i);
		}
	}else{
		for(var i=pageIndexStart;i<=this.pageCount;i++){
			this._createPageShow(i);
		}
	}
};

/* 
* 创建导航页元素
* @pageIndexStart  导航页的起始页
*/
PagingClass.prototype._createPageShow=function(pageIndexInfo){
	var pageShowA=document.createElement("a");
	$pageShowA=$(pageShowA);
	$pageShowA.attr("href","#");
	$pageShowA.attr("id","pageShow"+pageIndexInfo);
	$pageShowA.addClass("pageShow");
	$pageShowA.append(pageIndexInfo);
	$pageShowA.bind("click",{start:pageIndexInfo,typeInfo:"pageShow"},this._changePage);
	//$pageShowA.bind("click",this._changePage(pageIndexInfo,"pageShow"));
	$("#middleSpan").append($pageShowA);
};


/* 
* 换页
*/
PagingClass.prototype._changePage=function(event){
	var source=event.data.typeInfo;
	var pageindexInfo=event.data.start;
	if (source=="Prev"){
		if (pageInstance.pageIndex==1){
			return false;
		}else if (pageInstance.pageIndex%pageInstance.pageindexShow == 1){
			pageInstance._updatePageShow(pageInstance.pageIndex-pageInstance.pageindexShow);
			$(".pageShow").last().addClass("on");
			pageInstance.pageIndex-=1;
		}else{
			$(".pageShow").removeClass("on");
			$(".pageShow[id=pageShow" + (pageInstance.pageIndex-1) +"]").addClass("on");
			pageInstance.pageIndex-=1;
		}
		pageInstance._updateDataSource(pageInstance.pageIndex);
	}else if (source=="Next"){
		if (pageInstance.pageIndex==pageInstance.pageCount){
			return false;
		}else if (pageInstance.pageIndex%pageInstance.pageindexShow == 0){
			pageInstance._updatePageShow(pageInstance.pageIndex+1);
			$(".pageShow").first().addClass("on");
			pageInstance.pageIndex+=1;
		}else{
			$(".pageShow").removeClass("on");
			$(".pageShow[id=pageShow" + (pageInstance.pageIndex+1) +"]").addClass("on");
			pageInstance.pageIndex+=1;
		}
		pageInstance._updateDataSource(pageInstance.pageIndex);
	}else{
		$(".pageShow").removeClass("on");
		$(".pageShow[id=pageShow" + pageindexInfo +"]").addClass("on");
		pageInstance.pageIndex=pageindexInfo;
		pageInstance._updateDataSource(pageindexInfo);
		//this._updateDataSource(pageindexInfo-1);
	}
};

/* 
* 页定位方法
*/
PagingClass.prototype._aimPage=function(){
	var pattern=/^[0-9]*[1-9][0-9]*$/;
	var aimPageSize = $("#aimPageInput").val();
	if (!pattern.test(aimPageSize||aimPageSize>pageInstance.pageCount)){
		$("#aimPageInput").empty();
	}else{
		if (aimPageSize%pageInstance.pageindexShow == 0){
			pageInstance._updatePageShow(aimPageSize-pageInstance.pageindexShow+1);
			$(".pageShow").last().addClass("on");
			pageInstance.pageIndex=parseInt(aimPageSize);
		}else{
			var pageindexIn=aimPageSize-aimPageSize%pageInstance.pageindexShow+1;
			pageInstance._updatePageShow(pageindexIn);
			$(".pageShow").removeClass("on");
			$(".pageShow[id=pageShow" + aimPageSize +"]").addClass("on");
			pageInstance.pageIndex=parseInt(aimPageSize);
		}
	}
	pageInstance._updateDataSource(pageInstance.pageIndex);
	$("#aimPageInput").val("");
};

/* 
* 更换数据源
*/
PagingClass.prototype._updateDataSource=function(pageindexInfo){
	pageInstance._func(pageindexInfo,pageInstance._pageSize);
};