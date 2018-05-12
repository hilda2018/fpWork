var chooseObj = {}; // 被选择的元素 
chooseObj[0] = {}; //多选 水果
chooseObj[1] = {}; //多选 Fruit Origins
 //单选 Years of Company Established  chooseObj[2] ;
chooseObj[3] = {}; //多选 Business Types
chooseObj[4] = {}; //多选 Harvet Season
filterArr = [];
// 筛选条件
var filterChoose = ["product", "Origins", "companyEstablished", "businessTypes","seasons"];
var filterObj = {}; //被过滤的元素
filterObj["product"] =[];
filterObj["Origins"] =[];
/*filterObj["countryCompany"] =[];*/
filterObj["companyEstablished"] ='';
filterObj["businessTypes"] =[];
filterObj["seasons"] =[];
var shopsListData = null ;
//水果分类数据
var dataFruits = [
	[],
	[],
	['Peaches', 'Plums', 'Apricots', 'Nectarines', 'Others '],
	['Blueberries', 'Strawberries', 'Raspberries', 'Others'],
	['Lemons', 'Grapefruits', 'Mandarins', 'Others'],
	[],
	[],
	['Bananas', 'Mangos', 'Dragon fruits', 'Coconuts', 'Durains', 'Pineapples', 'Lichees', 'mangosteens', 'Others'],
	[],
	['Pears', 'Apples', 'Others'],
	['Watermelon', 'Cantaloupe', 'Horned Melon', 'Crenshaw Melon', 'Honeydew', 'Bitter Melon', 'Canary Melon', 'Hami Melon', 'Others'],
	['Cherimoyas', 'Quinces', 'Pomegranates', 'Figs', 'Others'],
	[]
];


//世界 分类数据
var dataWord = [
	[],
	['U.S.A', 'Canada', 'Mexico'],
	['Panama'],
	['Chile', 'Ecuador', 'Peru', 'Brazil','South Africa', 'Columbia', 'Costa Rica', 'Uruguay', 'Argentina'],
	['South Africa','Egypt','Morocco'],
	['Netherlands','Belgium','France','Italy','Spain','Turkey','Cyprus','Greece','Poland'],
	['Israel', 'Sri Lanka', 'India','Philippine','Vietnam','Thailand','Burma','Laos','Nepal',
	'Malaysia','Indonesia','Pakistan','Tajikistan','Kyrgyzstan','South Korea','North Korea','Japan','Taiwan'],
	['Australia', 'New Zealand'],
	[]
];


//生成分类
var crumbData = [
	{
		"title": "Main Fruits Exporting:",
		"data": ["All", "Cherries", "Stone fruit", "Berries", "Subtropical Fruit", "Citrus",
			"Avocados ", "Tropical fruits", "Table grapes ", "Pome fruits", "Melons", "Fruit", "Kiwis"
		]
	},
	{
		"title": "Fruit Origins",
		"data": ["All", "North America", "Central America", "South America", "Africa", "Europe", " Asia", "Oceania","Others"]
	},
	{
		"title": "Years of Company Established:",
		"data": ["0-2years", "3-5years", "6-10years", "11-15years", "Over 15years"]
	},
	{
		"title": "Business Types:",
		"data": ["Grower", "Packer",'Trader',"Broker", "Others"]
	},
	{
		"title": "Harvet Season:",
		"data": []
	}
];


$(function() {

	var type = document.getElementById("type");
	var len = crumbData.length;

	//动态生成 主分类
	for(var i = 0; i < len; i++) {
		newLi = document.createElement("li");
		newSpan = document.createElement("span");

		newSpan.innerText = crumbData[i].title;
		newLi.appendChild(newSpan)

		// 拿到每个对象中的数组，循环这个数组，生成a标签
		
		if( i == len -1 ){
			
			$(newLi).append($('#startMonth'),$('#endMonth'));
			
		}
		else{
			for(var j = 0; j < crumbData[i].data.length; j++) {
				newA = document.createElement("a");
				newA.href = 'javascript:;';
				newA.innerText = crumbData[i].data[j];
				newLi.appendChild(newA)
			}
		}
		
		type.appendChild(newLi);

	}

	var lis = type.children; // 获取到所有的li
	var itemA = null;
	var num =0;

	for(var k = 0; k < lis.length; k++) {
		//得到每一个li中的所有的a标签
		itemA = lis[k].getElementsByTagName("a");

		lis[k].prevNode = null; // 记录点击的a标签

		lis[k].index = num;
		++num;// 记录每一个li的下标

		for(var m = 0; m < itemA.length; m++) {
			// 给每一个a标签绑定点击处理函数
			itemA[m].index = m;

			itemA[m].onclick = function() {
				
	
				var obj = this;
	
				if(this.parentNode.index == 0 ) { 
					
					multi(obj, dataFruits, this.index);
								
				}else if( this.parentNode.index == 1 ){
					
					multi(obj, dataWord , this.index);
				}else if(this.parentNode.index == 3 || this.parentNode.index == 4 )
				{ // 多选的情况 没子集
					multi2 (obj, this.index);
				}else if ( this.parentNode.index == 2  ){
					single(obj)
					
				}

			}

		}
	}


	
	// 清空所有
	$('#delete').click(function(){
		$('#chooseShow').empty();
		chooseObj = {};
		chooseObj[0] = {}; //多选 水果
		chooseObj[1] = {}; //多选 Fruit Origins
/*		chooseObj[2] = {}; //多选 Country of Company:*/
		chooseObj[2] = '';
		chooseObj[3] = {}; //多选 Business Types
		chooseObj[4] = {}; //多选 Harvet Season
		filterObj = {};
	});
	
	// 是否是缩略图显示
	var onOff=true;
	var $showSupplier=$('#showSupplier');
	
	$('#icons-list').click(function(){
		

		
		if(onOff){
			$(this).css({"background-position":"0px bottom"});
			$showSupplier.addClass('smallView');
		}else{
			$(this).css({"background-position":"0px top"});	
			$showSupplier.removeClass('smallView');
		}
	
		onOff=!onOff;
		
		
		$('#list').delegate('li','click',function(){
			
			window.open( 'newCompany.html' );
		});
		
	});
	

	
	
	$.ajax({
			type:"get",
			url:"./2.txt",
			dataType:"json",
			async:true,
			success:function(resultData){	
				//默认加载 传入过来的数据
				shopsListData = resultData.rows;
				
				console.log( shopsListData );
				createListElement(shopsListData);
				var $This = $('#wrapSearch');
				var $showSupplier=$('#showSupplier');
				
				$This.parent().height($This.outerHeight());
				$showSupplier.parent().height($showSupplier.outerHeight());
				$This.add($showSupplier).click(function() {
		
				$This.parent().height($This.outerHeight());
				$showSupplier.parent().height($showSupplier.outerHeight());
			});
			}
	});
	
	
	
	




});

// 单选情况
function single(obj) {
	$(obj).css({
		"color": "red"
	}).siblings().css({
		"color": "#000"
	});

	var parentLiIndex=obj.parentNode.index;
	
	chooseObj[parentLiIndex] = $(obj).html();
	
	var index = $(obj).index();

	
	createChooseHtml()

	
}

// 没有 子分类的  多选情况
function multi2(obj,aIndex) {
	$(obj).css({
		"color": "red"
	}).siblings().css({
		"color": "#000"
	});

	var parentLiIndex=obj.parentNode.index;
	
	chooseObj[parentLiIndex][aIndex]= $(obj).html();

	createChooseHtml()
	

	
}
// 有 子分类的  多选情况
function multi(obj, subData, aIndex) { //被点击的 a 标签 //subData 是指子分类数据

	var parentLiIndex = Number(obj.parentNode.index); // 点击的a标签的父级 li的索引
	var parent = obj.parentNode; // 点击的a标签的父级 li
	// 去掉之前点击的a标签的color
	if(parent.prevNode) {
		parent.prevNode.style.color = ''
	}
	obj.style.color = 'red';
	parent.prevNode = obj; // 点击之后记录点击的这个元素

	var subObj = document.getElementById("hide2");
	if(subObj) {
		$(subObj).remove();
	}
	newDiv = document.createElement("div");
	newDiv.className = "hide2";
	newDiv.id = "hide2";
	//newDiv.innerHTML = obj.index;

	//在Div 中动态添加子元素
	var data = subData[obj.index];

	if(data.length == 0) {
		//alert('我没孩子');
		//将该数据 归入到 被筛选的记录 
		//alert( obj.innerHTML);
		if(aIndex == 0 && parentLiIndex == 0) {
			chooseObj[parentLiIndex] = {};
			chooseObj[parentLiIndex][aIndex + '0'] = 'All Products';

		}else if(aIndex == 0 && parentLiIndex == 1){
			chooseObj[parentLiIndex] = {};
			chooseObj[parentLiIndex][aIndex + '0'] = 'All Origins';
			//console.log(chooseObj);
			
		}else {

			chooseObj[parentLiIndex][aIndex + '0'] = obj.innerHTML;

		}

		createChooseHtml();

	} else {


		if(parentLiIndex == 0) {
			if(chooseObj[0]['00']){delete chooseObj[0]['00'];}

		}
		createElement(data, newDiv, parentLiIndex, aIndex);
		obj.parentNode.appendChild(newDiv);

	}

}

// 创建 多选 li 的 孩子
function createElement(data, parent, parentLiIndex, aIndex) {

	for(var i = 0; i < data.length; i++) {

		var $newA = $('<a href="javascript:;" index="' + i + '" >' + data[i] + '</a>');

		$newA.click(function() {
			$(this).css({
				"color": "red"
			}).siblings().css({
				"color": "#000"
			});
			// 记得将数据 归入到 已筛选 的json 里面
			var cusIndex = $(this).index();
			chooseObj[parentLiIndex][aIndex + '' + cusIndex] = $(this).text();
			createChooseHtml();

		});
		$newA = $newA.get(0);
		newDiv.appendChild($newA);
	}

}

//动态创建选择的元素 以及 添加 点击删除该元素，删除结构的同时删除 数据
// 选择的容器
var chooseElement = document.getElementById("chooseShow")

function createChooseHtml() {

	// 对象是没有顺序
	//动态添加 被选择的数据
	var html = '';
	for(var i in chooseObj) {

		if(chooseObj[i] && ( i == 2) ) { //单选情况

			// 生成结构的时候在行间保存对象的key值
			html += '<strong ><em  class="text">' + chooseObj[i] + '</em ><span  href="javascript:;">x</span></strong>';
		}

		else if(chooseObj[i] && (i < 5)) { //多选情况

			if(chooseObj[i] == 'All Products') {

				html += '<strong  ><em class="text">' + chooseObj[i] + '</em ><span href="javascript:;">x</span></strong>';

			} else if(chooseObj[i] == 'All Areas') {
				html += '<strong  ><em  class="text">' + chooseObj[i] + '</em ><span href="javascript:;">x</span></strong>';

			} else {

				for(var s in chooseObj[i]) {

					if(chooseObj[i][s]) {

						html += '<strong ><em  class="text">' + chooseObj[i][s] + '</em ><span  href="javascript:;">x</span></strong>';
					}
				}
			}

		} 

	}

	chooseElement.innerHTML = html;

	// 给选择 的元素 添加 点击 事件
	$(chooseElement).delegate('strong', 'click', function() {

		
		var subObj = document.getElementById("hide2");
		if(subObj) {
			$(subObj).remove();
		}
		//删除数据 ，同时也删除 结构
		var text = $(this).find('em').text();

		$.each(chooseObj, function(i, elem) {

	
				for(var key in chooseObj[i]) {

					if(chooseObj[i][key] == text) {

						delete chooseObj[i][key];
						
		

					}

				}

			
});
	
		//console.log('chooseObj：'+chooseObj);	console.log('filterObj：'+filterObj);
		filterData();	
		$(this).remove();

	});

	filterData();

}

//生成 筛选 数据
function filterData() {

	filterObj = {};//被过滤的元素
	filterObj["product"] =[];
	filterObj["Origins"] =[];/*
	filterObj["countryCompany"] =[];*/
	filterObj["companyEstablished"] ='';
	filterObj["businessTypes"] =[];
	filterObj["seasons"] =[];
	
	//chooseObj
	

	for(var key in chooseObj) {

		if(key == '0') {

			for(var key0 in chooseObj['0']) {
			
				filterObj["product"].push( chooseObj['0'][key0] ); 
			}

		}else if(key == '1')
		{

			for(var key0 in chooseObj['1']) {
				
				filterObj["Origins"].push( chooseObj['1'][key0] ); 
			}
		
		}else if(key == '2')
		{

			for(var key0 in chooseObj['2']) {
				
			
				filterObj["companyEstablished"] = chooseObj['2']; 
			}
		
		}else if(key == '3')
		{

			for(var key0 in chooseObj['3']) {
			
				filterObj["businessTypes"].push( chooseObj['3'][key0] ); 
			}
		
		}else if(key == '4')
		{

			for(var key0 in chooseObj['4']) {
			
				filterObj["seasons"].push( chooseObj['4'][key0] ); 
			}
		
		}
		
		
		
		
		
		
	}console.log(filterObj);
	filterHandle();
}

//筛选数据
function filterHandle() {
	filterArr = []; // 等于原始数组
	

	for(var attr in filterObj) {

			
		for( var i = 0; i<filterObj[attr].length; i++ ){
				
			 if(i == 2){
			 	filterArr = filter(attr,  filterObj[attr] , shopsListData , filterArr);
			 	
	
			 }else{
			 	
			 	filterArr = filter(attr,  filterObj[attr][i] , shopsListData , filterArr)
			 }
				
		}
			
	
	}

	createListElement(filterArr);
}

function filter(attr, filterObjArr , shopsListData ,filterArr ){

	$.each(shopsListData,function(i,elem){
		

			if(shopsListData[i][attr] == filterObjArr){
					
			
				if( $.inArray( shopsListData[i] ,filterArr ) == -1){
					
						filterArr.push( shopsListData[i]  );	
				}
			}

	});

	return filterArr ;
	
}




function createListElement(filterArr){
	
	var $list=$('#list');
	$('#list').empty();
	
	for(var i=0;i<filterArr.length;i++){
		
		var $listImg=$('<div class="list_img"></div>');
		
	
		
		var $img=$('<img>').attr({"src":filterArr[i]["imgUrl"]});
		var $span=$('<span>&nbsp;</span>');
		
		 $listImg.append($img);
		 $listImg.append($span);
		 
		var $propTitle=$('<div class="prop-title" title="companyName">'+filterArr[i]["companyName"]+'</div>');
		

		var $span1=$('<span><em>Country:</em> <strong class="established">'+filterArr[i]["Origins"]+'</strong></span>');
		var $span2=$('<span><em>Years Established:</em> <strong class="Yearwork">'+filterArr[i]["companyEstablished"]+'</strong></span>');
		var $span3=$('<span><em>Business Type:</em> <strong class="Turnover">'+filterArr[i]["businessTypes"]+'</strong></span>');

		
		var $infoTable=$('<div class="info-table"></div>');
		
		$infoTable.append($span1,$span2,$span3);
		
		var $listInfo=$('<div class="info-info"></div>');

		$listInfo.append($propTitle,$infoTable);
		
		var $btn=$('<a class="btn btn-danger"   href="newCompany.html">&nbsp;View Details&nbsp;</a>');
		var $clearfix=$('<div class="clearfix"></div>');
		
		var $Li=$('<li class="thumbnail"></li>');
		
		$Li.append($listImg,$propTitle,$listInfo,$btn,$clearfix);

		$list.append($Li);
	}
	

	
}


/****************页码  以及 公司列表 的加载 ******/

$('#pageSize').delegate('span','click',function(){
	option.pageCount = parseInt( $(this).html() );
	initPagination(option);	
});

var option={
	"pageCount":40,//默认是40
	"type":"GET",//默认是get
	"currentPage":0,//默认选择为0 非必需
	"url":'2.txt',//必填
	"data":''
};
initPagination(option);	

function initPagination(option){
	
	var setting={
		"pageCount":40,//默认是40
		"type":"GET",//默认是get
		"currentPage":0,//默认选择为0 非必需
		"url":'',//必填
		"data":''//非必需，
	}

	$.extend(setting,option) ;
	var pageCount = setting.pageCount?setting.pageCount:40;
	var type = setting.type?setting.type:"GET";
	var currentPage = setting.currentPage?(parseInt(setting.currentPage)-1):0; 
	var url = setting.url;
	var data = setting.data ? '&'+ setting.data : '';

	$.ajax({
			type:type,
			url:url,
			data:"pageIndex=0&pageCount=" + pageCount+data,
			dataType:"json",
			async:true,
			success:function(resultData){
				var total = resultData.total;
				page(url,type,data, total,pageCount,currentPage,total,resultData);
					
			
			}
	});
	
	
}
	 

function page(url,type,data,total,pageCount,currentPage,total,resultData){

	$("#pagination").pagination(total, {
		num_edge_entries:2, //边缘页数
		num_display_entries: 2, //主体页数
		callback: pageselectCallback,
		items_per_page:pageCount, //每页显示1项
		current_page:currentPage,
		prev_text: "prev",
		next_text: "next"
	});
	
	
	function pageselectCallback(pageIndex, jq){//从0 开始
		
	    if(pageIndex == 0){
	    	
			showData(resultData,pageIndex, pageCount,jq)
	    	return false;
	   	}

		$.ajax({
			type:type,
			url:url,
			data: "pageIndex=" + (pageIndex) + "&pageCount=" + pageCount+data,   
			dataType:"json",
			async:true,
			success:function(resultData){
				
				showData(resultData,pageIndex, pageCount,jq);
				
			}
		});
		
		return false;
	}

}


function showData(resultData,pageIndex, pageCount,jq){
	
	var total = resultData.total;
	var pageStart = pageIndex * pageCount  + 1;
	var pageEnd = ( pageIndex + 1) * pageCount ;
	
	var pages  =Math.ceil( total /  pageCount  );

	
	if( pageEnd > total){ pageEnd = total;}
	//Displaying 1 to 10 of 114 items
/*	var $spanInfo = $('<span id="spanInfo" >Page &nbsp;'+(pageIndex+1)+'&nbsp;of &nbsp;'+pages+'&nbsp;,&nbsp;&nbsp;Displaying &nbsp;'+pageStart+'&nbsp;to&nbsp;'+pageEnd+',of&nbsp;'+ total +'&nbsp;items</span>');
*/
	if( pageIndex == 0 ){
	}else{
		createListElement(filterArr)
	}
	
	
/*	$(jq).append($spanInfo);*/
	
}

var $startMonth = $('#startMonth');
var $endMonth = $('#endMonth');
var $endMonthVal = $endMonth.val();

if( $endMonthVal == '0'){
	$endMonth.css({"color":"#999"});
}
if( $startMonth.val() == '0'){
	$startMonth.css({"color":"#999"});
}			

$startMonth.change(
	function(){
		
		//数字值  $(this).val();
		//具体值  $(this).find("option:selected").text();
		 if($(this).val() == $endMonth.val() && $(this).val() != '0'){
			
			layer.alert('请选择正确日期', {icon:5});
			$(this).val('0');
			$(this).css({"color":"#999"});delete chooseObj[4][0];createChooseHtml();
			
		}else if( $(this).val() != '0'){
			$(this).css({"color":"#333"});
			chooseObj[4][0]= $(this).find("option:selected").text();
			createChooseHtml();
		}
		else{
			$(this).css({"color":"#999"});
			delete chooseObj[4][0];createChooseHtml();
		}
			
	}
);


$endMonth.change(
	function(){
	
		//数字值  $(this).val();
		//具体值  $(this).find("option:selected").text();
		if($(this).val() == $startMonth.val() && $(this).val() != '0'){
			
			layer.alert('请选择正确日期', {icon:5});
			$(this).val('0');
			$(this).css({"color":"#999"});delete chooseObj[4][1];createChooseHtml();
			
		}
		else if( $(this).val() == '0'){
			$(this).val('0');
			$(this).css({"color":"#999"});
			delete chooseObj[4][1];createChooseHtml();
		}
		else{
			$(this).css({"color":"#333"});
			chooseObj[4][1]= $(this).find("option:selected").text();
			createChooseHtml();
		}
		
	
	
	}
);

