

var global = {};
global.seriesDefaultOptions = {
	lang: {	rangeSelectorZoom: ''},
	title: null,
	chart: {
		animation: false,
		spacingBottom: 30,
		zoomType: 'xy',
		ignoreHiddenSeries: true
		
	},
	tooltip: {
		split: false,
		shared: true,
		shape: 'square',
		valueDecimals: 2,
		backgroundColor: 'white',
		shadow: false,
		split: true
	},
	credits: {
		enabled: false
	},
	legend: {
		borderColor: '#ffffff',
		align: 'center',
		floating: true,
		layout: 'horizontal',
		verticalAlign: ' bottom',
		itemMarginBottom: 0,
		itemWidth: 260,
		itemStyle: {
			fontWeight: 'normal'
		}
	},
	navigator: {
		adaptToUpdatedData: false,
		outlineWidth: 0,
		enabled: true,
		lineWidth: 0,
		tickWidth: 0,
		height: 28,
		gridLineWidth: 0,
		id: "navigator",
		xAxis: {
			verticalAlign: ' top',
			tickWidth: 0,
			lineWidth: 0,
			gridLineWidth: 0,
			dateTimeLabelFormats: {
				day: '%d/%m'
			},
			labels: {
				align: 'left',
				style: {
					color: '#999',
					fontSize: '11px'
				},
				x: -14,
				y: 20
			}
		}
	},
	scrollbar: {
		enabled: false
	},
	xAxis: {
		type: 'datetime',
		fontSize: 8,   gridLineDashStyle: 'longdash',
		tickInterval: 24 * 3600 * 1000,
		minRange: 3600 * 1000 * 24,
		tickWidth: 1,
		startOnTick: true,
		endOnTick: true,
		showLastLabel: true,
		showFirstLabel: true,
		dateTimeLabelFormats: {
			day: '%d/%m'
		}
	},
	rangeSelector: {
		buttonTheme: {
			display: 'none'
		},
		selected: 1,
		inputEnabled: false
	},
	yAxis: [{
				lineWidth: 0,
				align: 'left',
				color: '#7CB5EC',
				type: 'linear',   gridLineDashStyle: 'longdash',
				tickmarkPlacement: "on",
				minorGridLineColor: '#fcfcfc',
				maxZoom: 0.1,
				opposite: false,
				id: "first",
				title: {
					textAlign: 'right',
					align: 'high',
					offset: 10,
					y: 10,
					fontSize: 8,
					text: 'RMB/KG',
					rotation: 0
				},
				labels: {
					align: 'right',
					x: -10,
					showLastLabel: true,
					showFirstLabel: true
				},
				startOnTick: true,
				endOnTick: true
				}, {
					align: 'left',
					lineWidth: 0,
					color: '#7CB5EC',
					type: 'linear',
					minorGridLineColor: '#fcfcfc',
					linkedTo: 0,
					floor: 0,
					tickmarkPlacement: "on",
					opposite: true,
					id: "firstRight",
					labels: {
						align: 'left',
						x: 8,
						showLastLabel: true,
						showFirstLabel: true,
					},
					title: {
						textAlign: 'left',
						align: 'high',
						offset: 8,
						y: 10,
						fontSize: 8,
						text: 'RMB/KG',
						rotation: 0
					},
					startOnTick: true,
					endOnTick: true,

					maxZoom: 0.1,
					floor: 0
				}],
	gridLineDashStyle: 'longdash',
	animation: false,
	series: []
	};




Highcharts.setOptions(	global.seriesDefaultOptions  );




function makeChart(id, data ){

		var lineArr = [];

		for( var i = 0; i < data.length ; i++){

			var temp  = {
					name : 'line'+(i+1),
					data : data[i].data,
					marker : {
						enabled : true,
						radius : 3
					},
					shadow : true,
					tooltip : {
						valueDecimals : 2
					}
				};
			lineArr.push( temp ) ;

		}

		global.seriesDefaultOptions.series = lineArr;

		global.chartOne  = Highcharts.stockChart( id,  global.seriesDefaultOptions);
	
	

		generateElement( id );  // 生成图例，和 表格项

}

/** 更新图表 线  */
function updateLine(index,data){

	global.chartOne.series[index].update({
		series:setZones(data)
	});
		
}

/** 复制一份 图例 1*/
function generateElement( id ) {
	global.chartOne.legend.update({
		enabled: true
	});

	var allsvg = [];
	var all = global.chartOne.legend.getAllItems();
	var colors = [];


	for (var i = 0; i < all.length; i++) {
		allsvg.push( all[i].legendGroup.element) ;
		colors.push( all[i].color );
	}

	
	var str2 = $('<tbody>');
	var str = $('<div>');
	var num = 0;

	for (var i = 0; i < allsvg.length; i++) {

		allsvg[i] = $(allsvg[i]).attr("fill",colors[i]);
		var svg = getSVG(allsvg[i] , colors[i] ).clone();

		console.log(svg);
		num++;
		var $btnDiv = $('<div class="labelItem"></div>');
		$btnDiv.appendTo(str); // 第一次
 		svg.appendTo($btnDiv); // 第一次



		var svg2 = svg.clone();
		var trHtml = '<tr class="odd"  lineId = ' + all[i].index + '><td class="selectOption"> <a class="select"><svg class="icon" style="width: 16; height:16;vertical-align: middle;fill: #8bc34a;overflow: hidden;" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1660"><path d="M196.923077 0h630.153846a196.923077 196.923077 0 0 1 196.923077 196.923077v630.153846a196.923077 196.923077 0 0 1-196.923077 196.923077H196.923077a196.923077 196.923077 0 0 1-196.923077-196.923077V196.923077a196.923077 196.923077 0 0 1 196.923077-196.923077z m0 78.769231a118.153846 118.153846 0 0 0-118.153846 118.153846v630.153846a118.153846 118.153846 0 0 0 118.153846 118.153846h630.153846a118.153846 118.153846 0 0 0 118.153846-118.153846V196.923077a118.153846 118.153846 0 0 0-118.153846-118.153846H196.923077z m584.900923 258.205538a36.509538 36.509538 0 0 1 1.260308 51.633231l-299.480616 313.107692c-0.118154 0.157538-0.393846 0.236308-0.630154 0.472616l-0.393846 0.551384c-2.166154 2.126769-4.726154 3.229538-7.207384 4.726154-1.575385 0.866462-2.796308 2.166154-4.411077 2.835692a35.800615 35.800615 0 0 1-27.490462 0.07877c-1.260308-0.512-2.284308-1.614769-3.544615-2.284308-2.756923-1.457231-5.592615-2.835692-8.034462-5.12-0.196923-0.157538-0.275692-0.433231-0.512-0.669538-0.196923-0.118154-0.393846-0.196923-0.551384-0.354462l-150.843077-156.593231a36.430769 36.430769 0 0 1 0.945231-51.633231 36.391385 36.391385 0 0 1 51.63323 0.945231l124.455385 129.102769 273.092923-285.61723a36.548923 36.548923 0 0 1 51.712-1.181539z" p-id="1661"></path></svg></a> <a class="unselect"><svg class="icon" style="width: 16; height: 16;vertical-align: middle;fill: #8bc34a;overflow: hidden;" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1610"><path d="M196.923077 78.769231a118.153846 118.153846 0 0 0-118.153846 118.153846v630.153846a118.153846 118.153846 0 0 0 118.153846 118.153846h630.153846a118.153846 118.153846 0 0 0 118.153846-118.153846V196.923077a118.153846 118.153846 0 0 0-118.153846-118.153846H196.923077z m0-78.769231h630.153846a196.923077 196.923077 0 0 1 196.923077 196.923077v630.153846a196.923077 196.923077 0 0 1-196.923077 196.923077H196.923077a196.923077 196.923077 0 0 1-196.923077-196.923077V196.923077a196.923077 196.923077 0 0 1 196.923077-196.923077z" p-id="1611"></path></svg></a>  </td><td class="name"><span>' + num + '</span></td><td class="legend" ></td><td class="detail">USA,14mm+,sea</td><td class="marky">市场</td></tr>' ;
		var $tr = $(trHtml);

		svg2.appendTo($tr.find('.legend'));
		$tr.appendTo(str2); // 第2次

	}


	if( id == "container"){
		$('#tbody').empty().append(str2.html());
		$('#chartLabel').eq(0).empty().append(str.html());
	}else{
		$('#tbody2').empty().append(str2.html());
		$('#chartLabel2').eq(0).empty().append(str.html());
	}







}
/** 复制一份 图例 2*/
var getSVG = function(h,color) {

	var $str = $('<svg class="my"   xmlns="http:///www.w3.org/2000/svg"  version="1.1" fill='+color+' style="font-family:sans-serif;font-size:12px;height:18;vertical-align:middle;width:56;" ><rect  rx="0" ry="0" ry="0" visibility="visible"></rect></svg>');
	$(h).appendTo($str );

	$str.find('g').attr("transform", '');
	return $str

};

/* 增加线 */
function addLineSeries(oneData) {
    var $addLine = $('#addLine');
    var oneSeries = setZones(oneData);
    global.chartOne.addSeries(oneSeries);
    $addLine.removeClass('activeBtn');
    generateElement();

}


function setZones(data) {

	var standerId = data.standerId;
	
	var zonesArray = [];
	var newData = [];
	var data = data.data;

	for(var i = 0; i < data.length; i++) {

		var flag = data[i][1] && i != 0 && data[i - 1][1];
	
		newData[i] = {
			x: data[i][0],
			y: data[i][1],
			marker: {
				enabled: true,
				fillColor: chartInfo.color1[lineIndex],
				color: chartInfo.color1[lineIndex],
				lineColor: chartInfo.color1[lineIndex],
				stroke: chartInfo.color1[lineIndex],
				lineWidth: 4
			}
		};

		if(flag) {
			zonesArray[i] = {
				value: data[i][0] + 1,
				dashStyle: "solid"
			}
		} else {
			zonesArray[i] = {
				value: data[i][0] + 1,
				dashStyle: "dot"
			}
		}
		
		

	}
	
	
	var oneSeriesOptions = {
		name: 'line' + ( lineIndex + 1),
		customtitle:"33",
		type: "spline",
		data: newData,
		id: standerId,
		legend: {
			borderColor: '#ffffff',
			align: 'center',
			floating: true,
			layout: 'horizontal',
			verticalAlign: ' bottom',
			itemMarginBottom: 0,
			itemWidth: 260,
			itemStyle: {
				fontWeight: 'normal'
			}
		},
		yAixs: 1,
		color: chartInfo.color1[lineIndex],
		connectNulls: true,
		shadow: false,
		dataGrouping: {
			smoothed: true
		},
		pointInterval: 24 * 3600 * 1000,
		zoneAxis: "x",
		zones: zonesArray
	};
	
		
	lineIndex ++ ;
		
		
	
	return oneSeriesOptions;
}




/* 对 每条线的数据 进行处理*/

function setZones(data) {
	var data = data.data;
		//var name = data2.name;
		// var remarkLable = data2.remarkLable;
		// var standardId = data2.sId;
		var zonesArray = [];
		var newData = [];

		for (var i = 0; i < data.length; i++) {

			newData[i] = {
						name:data[i][0],
						x : data[i][0],
						y : data[i][1],
						marker : {
							enabled : true,
							lineWidth : 4
						}
					};


						zonesArray[i] = {
					value : data[i][0] + 1,
					dashStyle : "solid"
				}


		
		}
		var result = {};
		result.data = newData;
		result.zones = zonesArray;
		return result;
}


var createChart = function(id, data) {
var seriesCounter = 0;
var obj = setZones(data);
	
// 图表 线 默认的参数配置
global.seriesDefaultOptions ={	type : "spline",
				data:obj.data,
				legend : {
					borderColor : '#ffffff',
					align : 'center',
					floating : true,
					layout : 'horizontal',
					verticalAlign : ' bottom',
					itemMarginBottom : 0,
					itemWidth : 260,
					itemStyle : {
						fontWeight : 'normal'
					}
				},
				yAixs : 1,
				connectNulls : true,
				shadow : false,
				dataGrouping : {
					smoothed : true
				},
				pointInterval : 24 * 3600 * 1000,
				zoneAxis : 'x',
				zones:obj.zones	};


var series = jQuery.extend(true, {}, global.seriesDefaultOptions ); 


	

  
}






;(function($, window, document, undefined) {
	$.fn.commonSearch = function(options, callback) {
		$.fn.commonSearch.callback = callback;
		$.fn.commonSearch.RequestPramData = [];
		$.fn.commonSearch.RequestUrl = null;
		$.fn.commonSearch.param = null;
		var defaults = {
			url: "",
			initParam:null,
			dataList: [{
				id: "demo1",
				title: "title1",
				selectorList: [{
					id: "selector1",
					name: "选项一"
				}],
				multipleSelect: false,
				more: false
			}],
			style:{
			},
		};
		var settings = $.extend({}, defaults, options);
		$.fn.commonSearch.RequestUrl = settings.url;
		var $this = this;
		$this.css(defaults.style);
		$this.append('<div class="yixuan"><table><tr class="new"><td width="80" align="left"><strong> CHOOSED :</strong></td><td><div class="yixuanNavList"></div></td></tr></table></div>');



		//多选项
		var $selDiv = $("<div class='sel' ></div>");
		$this.append($selDiv);


		$.each(settings.dataList, function(index, dataList ) {

			var $row = $('<div id="' + dataList.id + '" class="rowFp" ><strong>' + dataList.title + '：</strong> </div>');


			$selDiv.append($row);

		
				
			//显示 option 
			var $jzqDiv = $("<div class='jzq '></div>");
			$row.append($jzqDiv);
			$.each(dataList.selectorList, function(idx, val) {
				var $a = $('<a data-parent="' + dataList.id + '" data-id="' + val.id + '"></a>');
				var $span = $('<span id="' + val.id + '_span" data-parent_id="' + dataList.id + '" data-parent_title="' + dataList.title + '" data-id="' + val.id + '" data-name="' + val.name + '">' + val.name + '</span>');
				var $i = $('<i class="ch" id="' + val.id + 'i" data-parent_id="' + dataList.id + '" data-parent_title="' + dataList.title + '" data-id="' + val.id + '" data-name="' + val.name + '"></i>');
				$a.append($i);
				$a.append($span);
			
				$jzqDiv.append($a);
				$a.append($span);
			
		
				$a.bind("click", function() {
					onclickFor($(this).find('span'));
					$(this).find('i').toggleClass("chbackground");
				});

	
		
			});


			
		});


	
		if(settings.initParam != null){
			onclickForBuildDIV(settings.initParam);
			$.fn.commonSearch.RequestPramData.push(settings.initParam);
		}




		return this;
	};
	





	function onclickForBuildDIV(requestData) {
		var parentId = requestData.parentId;
		var parentName = requestData.parentName;
		var nameShow = "";
		for(var i = 0; i < requestData.requestData.length; i++) {
			var id = requestData.requestData[i].requestParamId;
			var name = requestData.requestData[i].requestParamName;
			nameShow = nameShow + name + "、";

		}
		var $aa = $('<a data-parent_id="' + parentId + '">' + parentName + '：' + nameShow.substr(0, nameShow.length - 1) + '<span class="tit-close">×</span></a>');
		$(".yixuanNavList").append($aa);
		$aa.bind("click", function() {
			$(this).remove();
			resetData(parentId);
			var delParentId = $(this).attr("data-parent_id");
			$("#" + parentId).show();
			$("#" + parentId).find("span").unbind("click");
	
		});

	}

	function onclickFor(_this) {


		var $selectDiv = $(_this).parent(".jzq");

		$selectDiv.find("i").toggleClass("ch").removeClass("chbackground");
		$(this).siblings("i").toggleClass("chbackground");


		Get_AJAX();

	}

	function resetData(parentId) {
		$.each($.fn.commonSearch.RequestPramData, function(index, value) {
			if(parentId == dataList.parentId) {
				$.fn.commonSearch.RequestPramData.remove(index);
			}
		});
		Get_AJAX();
	}

	function Get_AJAX() {
		

	}
	Array.prototype.remove = function(dx) {
		if(isNaN(dx) || dx > this.length) {
			return false;
		}
		for(var i = 0, n = 0; i < this.length; i++) {
			if(this[i] != this[dx]) {
				this[n++] = this[i]
			}
		}
		this.length -= 1
	}
})(jQuery, window, document);