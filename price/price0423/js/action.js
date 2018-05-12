$(function() {

	// 二次 复选
	$('.jzq a').bind("click", function() {
		//切换是否勾选
		var $i = $(this).find('i');
		$i.toggleClass("chbackground");

		var $itemDiv = $(this).closest('.rowFp');
		var nameShow = '';
		var parentName = $itemDiv.find('strong').text();
		nameShow = "<em>" + parentName + "</em>" + nameShow;

		$itemDiv.find('.chbackground').each(function(index, elem) {
			var name = $(elem).parent().text();
			nameShow = nameShow + $.trim(name) + "、";
		});
		var len = $itemDiv.find('.chbackground').length;
		if (len > 0) {
			$itemDiv.attr('data-custom', nameShow.substr(0, nameShow.length - 1));
		} else {
			$itemDiv.attr('data-custom', '');
		}
	});

	// 确认按钮
	$('#confirmOptions').click(function() {
		var html = '';
		$('#search').find('.rowFp').each(function(index, elem) {
			var item = $(this).attr('data-custom');
			if (item == '') {
				return;
			}
			html = html + '<a>' + item + '<span class="tit-close">×</span></a>';
			$(".yixuanNavList").html(html);

		});


	});


	// 二次筛选 重置按钮
	$('#resetOptions').click(function() {

		var html = '';
		var $rowFp = $('#search').find('.rowFp');

		$rowFp.each(function(index, elem) {
			var nameShow = '';
			var parentName = $(this).find('strong').text();
			nameShow = "<em>" + parentName + "</em>" + nameShow;
			var $spanParent = $(this);


			$(this).find('span').each(function(index, elem) {
				var name = $(elem).text();
				nameShow = nameShow + $.trim(name) + "、";
				$spanParent.attr('data-custom', nameShow.substr(0, nameShow.length - 1));

			});

			html = '<a>' + $spanParent.attr('data-custom') + '<span class="tit-close">×</span></a>' + html;
			$(this).find('i').addClass("chbackground");
		});

		$(".yixuanNavList").html(html);
	});



	// 左侧的 setting
	$('#wrapPrice').delegate('.selectOption', 'click', function() {

		//	$(this).attr('firstClick',true);//标志是否是第一次点击

		//第一次点击，请求数据,在ajax 成功回调函数中进行绘制 线；

		//$.ajax('',function(){




		var $closest = $(this).closest('tr');
		var justify = $closest.attr('clicked');
		var $table = $closest.closest('table');
		var index = $closest.index();

		//切换状态
		var doing = $('#wrapPrice').attr( "doing");

		if( !justify  && !doing){ //第一次更新数据
			$closest.attr("clicked",true );
			$(this).find('a').toggle();
			$closest.toggleClass('activeTr');


			updateLine(index, result ,function(){
				global.chartOne.yAxis[0].update({
					title:{text:"RMB/KG"}
				});
				global.chartOne.yAxis[1].update({
					title:{text:"RMB/KG"}
				});


				$('.mask').hide();
				$('#wrapPrice').attr( "doing",!doing);
			});


			return false;
		}





		if ($closest.hasClass('activeTr')) { // 隐藏
				if ($('.activeTr').length <= 1) {
					//layer.alert('At least 1 at the same time', {
					//	title : 'Info',
					//	btn : [ 'Confirm' ]
					//});
					alert(1);
				}
				else {
					$closest.toggleClass('activeTr');
					global.chartOne.series[index].hide();
					$(this).find('a').toggle();

				}

		} else {


			if(  $table.find('.activeTr').length > 4){//防止重复点击

				alert(5);
				$('#wrapPrice').attr( "doing",!doing);
				return false;
			}


			$(this).find('a').toggle();
				global.chartOne.series[index].show();
				$closest.toggleClass('activeTr');

		}

		$('#wrapPrice').attr( "doing",!doing);

	});




	makeChart('container', data);


	var shift = false;


	$('#tabAnother a').click(function() {


		$('#container').toggle(); //图表1 隐藏
		$('#container2').toggle(); //图表2 显示
		$('#tbody').toggle(); // 表1
		$('#tbody2').toggle(); //表2
		$('#chartLabel').toggle(); // 图例1
		$('#chartLabel2').toggle(); //图例2
		$(this).siblings().toggleClass('active');
		$(this).toggleClass('active');
		if (!shift) {

			shift = true;
			makeChart('container2', data);

			//图表2 加载图表数据，图表生成，生成 左侧的 侧边栏
		}
	});


	$('#showSearch').click(function() {

		$(this).find('em').toggle();
		if ($('.hide_search').css("display") == "none") {
			$('#search').parent().removeClass('lowHeight');

		} else {
			$('#search').parent().addClass('lowHeight');
		}

	});


});











var data = [{
	"data": []
}, {
	"data": []
}, {
	"data": []
}, {
	"data": []
}, {
	"data": []
}, {
	"data": []
}, {
	"data": []
}, {
	"data": []
}, {
	"data": []
}, {
	"data": []
}, {
	"data": []
}, {
	"data": []
}, {
	"data": []
}, {
	"data": []
}, {
	"data": []
}, {
	"data": []
}, {
	"data": []
}, {
	"data": []
}, {
	"data": []
}, {
	"data": []
}, {
	"data": []
}, {
	"data": []
}, {
	"data": []
}];


var result = {
	"retCode": 0,
	"erviceMsgCode": "INFO0000",
	"retMsg": "Success",
	"data": [{
		"avgPrice": -1,
		"commentNum": 0,
		"sDate": "2017-11-14 00:00:00",
		"statisticsDate": 1510588800000
	}, {
		"avgPrice": -1,
		"commentNum": 0,
		"sDate": "2017-11-15 00:00:00",
		"statisticsDate": 1510675200000
	}, {
		"avgPrice": -1,
		"commentNum": 0,
		"sDate": "2017-11-16 00:00:00",
		"statisticsDate": 1510761600000
	}, {
		"avgPrice": -1,
		"commentNum": 0,
		"sDate": "2017-11-17 00:00:00",
		"statisticsDate": 1510848000000
	}, {
		"avgPrice": -1,
		"commentNum": 0,
		"sDate": "2017-11-18 00:00:00",
		"statisticsDate": 1510934400000
	}, {
		"avgPrice": -1,
		"commentNum": 0,
		"sDate": "2017-11-19 00:00:00",
		"statisticsDate": 1511020800000
	}, {
		"avgPrice": -1,
		"commentNum": 0,
		"sDate": "2017-11-20 00:00:00",
		"statisticsDate": 1511107200000
	}, {
		"avgPrice": -1,
		"commentNum": 0,
		"sDate": "2017-11-21 00:00:00",
		"statisticsDate": 1511193600000
	}, {
		"avgPrice": 105.0000,
		"commentNum": 0,
		"sDate": "2017-11-22 00:00:00",
		"statisticsDate": 1511280000000
	}, {
		"avgPrice": 116.0000,
		"commentNum": 0,
		"sDate": "2017-11-23 00:00:00",
		"statisticsDate": 1511366400000
	}, {
		"avgPrice": -1,
		"commentNum": 0,
		"sDate": "2017-11-24 00:00:00",
		"statisticsDate": 1511452800000
	}, {
		"avgPrice": 110.0000,
		"commentNum": 0,
		"sDate": "2017-11-25 00:00:00",
		"statisticsDate": 1511539200000
	}, {
		"avgPrice": -1,
		"commentNum": 0,
		"sDate": "2017-11-26 00:00:00",
		"statisticsDate": 1511625600000
	}, {
		"avgPrice": 102.0000,
		"commentNum": 0,
		"sDate": "2017-11-27 00:00:00",
		"statisticsDate": 1511712000000
	}, {
		"avgPrice": -1,
		"commentNum": 0,
		"sDate": "2017-11-28 00:00:00",
		"statisticsDate": 1511798400000
	}, {
		"avgPrice": -1,
		"commentNum": 0,
		"sDate": "2017-11-29 00:00:00",
		"statisticsDate": 1511884800000
	}, {
		"avgPrice": -1,
		"commentNum": 0,
		"sDate": "2017-11-30 00:00:00",
		"statisticsDate": 1511971200000
	}, {
		"avgPrice": -1,
		"commentNum": 0,
		"sDate": "2017-12-01 00:00:00",
		"statisticsDate": 1512057600000
	}, {
		"avgPrice": -1,
		"commentNum": 0,
		"sDate": "2017-12-02 00:00:00",
		"statisticsDate": 1512144000000
	}, {
		"avgPrice": -1,
		"commentNum": 0,
		"sDate": "2017-12-03 00:00:00",
		"statisticsDate": 1512230400000
	}, {
		"avgPrice": 102.0000,
		"commentNum": 0,
		"sDate": "2017-12-04 00:00:00",
		"statisticsDate": 1512316800000
	}, {
		"avgPrice": 92.0000,
		"commentNum": 0,
		"sDate": "2017-12-05 00:00:00",
		"statisticsDate": 1512403200000
	}, {
		"avgPrice": 92.0000,
		"commentNum": 0,
		"sDate": "2017-12-06 00:00:00",
		"statisticsDate": 1512489600000
	}, {
		"avgPrice": -1,
		"commentNum": 0,
		"sDate": "2017-12-07 00:00:00",
		"statisticsDate": 1512576000000
	}, {
		"avgPrice": -1,
		"commentNum": 0,
		"sDate": "2017-12-08 00:00:00",
		"statisticsDate": 1512662400000
	}, {
		"avgPrice": -1,
		"commentNum": 0,
		"sDate": "2017-12-09 00:00:00",
		"statisticsDate": 1512748800000
	}, {
		"avgPrice": -1,
		"commentNum": 0,
		"sDate": "2017-12-10 00:00:00",
		"statisticsDate": 1512835200000
	}, {
		"avgPrice": -1,
		"commentNum": 0,
		"sDate": "2017-12-11 00:00:00",
		"statisticsDate": 1512921600000
	}, {
		"avgPrice": -1,
		"commentNum": 0,
		"sDate": "2017-12-12 00:00:00",
		"statisticsDate": 1513008000000
	}, {
		"avgPrice": -1,
		"commentNum": 0,
		"sDate": "2017-12-13 00:00:00",
		"statisticsDate": 1513094400000
	}, {
		"avgPrice": -1,
		"commentNum": 0,
		"sDate": "2017-12-14 00:00:00",
		"statisticsDate": 1513180800000
	}, {
		"avgPrice": -1,
		"commentNum": 0,
		"sDate": "2017-12-15 00:00:00",
		"statisticsDate": 1513267200000
	}, {
		"avgPrice": -1,
		"commentNum": 0,
		"sDate": "2017-12-16 00:00:00",
		"statisticsDate": 1513353600000
	}, {
		"avgPrice": -1,
		"commentNum": 0,
		"sDate": "2017-12-17 00:00:00",
		"statisticsDate": 1513440000000
	}, {
		"avgPrice": -1,
		"commentNum": 0,
		"sDate": "2017-12-18 00:00:00",
		"statisticsDate": 1513526400000
	}, {
		"avgPrice": -1,
		"commentNum": 0,
		"sDate": "2017-12-19 00:00:00",
		"statisticsDate": 1513612800000
	}, {
		"avgPrice": -1,
		"commentNum": 1,
		"sDate": "2017-12-20 00:00:00",
		"statisticsDate": 1513699200000
	}, {
		"avgPrice": -1,
		"commentNum": 0,
		"sDate": "2017-12-21 00:00:00",
		"statisticsDate": 1513785600000
	}, {
		"avgPrice": -1,
		"commentNum": 0,
		"sDate": "2017-12-22 00:00:00",
		"statisticsDate": 1513872000000
	}, {
		"avgPrice": -1,
		"commentNum": 0,
		"sDate": "2017-12-23 00:00:00",
		"statisticsDate": 1513958400000
	}, {
		"avgPrice": -1,
		"commentNum": 0,
		"sDate": "2017-12-24 00:00:00",
		"statisticsDate": 1514044800000
	}, {
		"avgPrice": -1,
		"commentNum": 0,
		"sDate": "2017-12-25 00:00:00",
		"statisticsDate": 1514131200000
	}, {
		"avgPrice": -1,
		"commentNum": 0,
		"sDate": "2017-12-26 00:00:00",
		"statisticsDate": 1514217600000
	}, {
		"avgPrice": -1,
		"commentNum": 0,
		"sDate": "2017-12-27 00:00:00",
		"statisticsDate": 1514304000000
	}]
}