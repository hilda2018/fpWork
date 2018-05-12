var companyInfo = (function() {
	var currentYear;
	var companyID;
	var company;
	var arrImg = [];
	var fruits = [];
	var turnoverData = [];
	var suppliersData = [];
	var channelData = [ {
		"channel" : "Wholesale Channel",
		"badge" : "",
		"detail" : "--"
	}, {
		"channel" : "Commercial Supermarket",
		"badge" : "",
		"detail" : "--"
	}, {
		"channel" : "E-commerce",
		"badge" : "",
		"detail" : "--"
	}, {
		"channel" : "Others",
		"badge" : "",
		"detail" : "--"
	} ];
	var teamData = [];
	var chinaMapDoShow = [];
	var channelDict = {};
	var Return = {
		Init : function() {
			defineCurrentYear();
			defineChannelDict();
			getQueryParam();
			bindSection1();
			bindSection2();
			bindSection3();
			bindSection4();
			bindSection5();
			bindSection6();
			bindSection7();
			initLeftNavFixed();
		}
	}
	var defineCurrentYear = function() {
		var mydate = new Date();
		currentYear = mydate.Format("yyyy");
	}
	var defineChannelDict = function() {
		channelDict = {
			1 : "Wholesale Channel",
			2 : "Commercial Supermarket",
			3 : "E-commerce",
			4 : "Others"
		}
	}
	var getQueryParam = function() {
		companyID = fpbase.Url.queryString("ckey");
		if (companyID == undefined || fpbase.Validator.isNullOrEmpty(companyID)) {
			layer.alert("The company does not exist.", function(index) {
				layer.close(index);
				fpbase.Url.goToUrl(rootPath + "/home/index");
			});
		} else {
			var param = {};
			param["firmCode"] = companyID;
			param["async"] = false;
			fpbase.Ajax.Read("", "company", "checkCompanyAvailability", {
				"reqParams" : JSON.stringify(param)
			}, function(resultData) {
				if (resultData == null || resultData.showFlag == 1) {
					layer.alert("The company does not exist.", function(index) {
						layer.close(index);
						fpbase.Url.goToUrl(rootPath + "/home/index");
					});
				}
			});
		}
	}
	var bindSection1 = function() {
		var param = {};
		param["firmCode"] = companyID;
		param["async"] = false;
		fpbase.Ajax.Read("", "company", "getCompanyProfileInfo", {
			"reqParams" : JSON.stringify(param)
		}, function(resultData) {
			if (resultData != null) {
				company = makeOneProfileInfo(resultData);
				setion1(company);
			}
		});
		fpbase.Ajax.Read("", "company", "getCompanyProfileImg", {
			"reqParams" : JSON.stringify(param)
		}, function(resultData) {
			if (resultData != null && resultData.length > 0) {
				for (var i = 0; i < resultData.length; i++) {
					arrImg.push(fileServerEnDomain + resultData[i].imgUrlEn);
				}
				section1_Img(arrImg);
			}
		});
	}
	var makeOneProfileInfo = function(item) {
		var profileInfo = {};
		profileInfo["logoImageUrl"] = $("#hidDefaultLogoImg").val();
		if (item.firmLogoUrlEn != undefined && item.firmLogoUrlEn != null && item.firmLogoUrlEn != "") {
			profileInfo["logoImageUrl"] = fileServerEnDomain + item.firmLogoUrlEn + "&width=80";
		}
		profileInfo["companyName"] = item.firmFullNameEn;
		profileInfo["firmControllerEn"] = item.firmControllerEn;
		profileInfo["createDate"] = item.createDateYear;
		profileInfo["firmAddressEn"] = item.firmAddressEn;
		profileInfo["intoYear"] = item.intoYear;
		profileInfo["employeesNum"] = item.employeesNum;
		profileInfo["firmUrl"] = item.firmUrl;
		profileInfo["info"] = item.firmProfile;
		return profileInfo;
	}
	var bindSection2 = function() {
		var param = {};
		param["firmCode"] = companyID;
		param["async"] = false;
		fpbase.Ajax.Read("", "company", "getCompanyMainProducts", {
			"reqParams" : JSON.stringify(param)
		}, function(resultData) {
			if (resultData != null && resultData.length > 0) {
				for (var i = 0; i < resultData.length; i++) {
					fruits.push(makeOneFruits(resultData[i]));
				}
				setion2(fruits);
				$(".divSection2").show();
			}
		});
	}
	var makeOneFruits = function(item) {
		var fruit = {};
		fruit["title"] = item.catalogNameEn;
		fruit["percent"] = "";
		if (item.proportion != undefined && item.proportion != null && item.proportion != 0) {
			fruit["percent"] = item.proportion + "%";
		}
		var product = "";
		// for (var i = 0; i < item.childrenList.length; i++) {
		// if (item.childrenList[i].catalogNameEn != undefined &&
		// item.childrenList[i].catalogNameEn != null &&
		// item.childrenList[i].catalogNameEn != "") {
		// product += item.childrenList[i].catalogNameEn;
		// }
		// if (i != item.childrenList.length - 1) {
		// product += ";";
		// }
		// }
		// for (var i = 0; i < item.childrenList.length; i++) {
		// if (item.childrenList[i].catalogNameEn != undefined &&
		// item.childrenList[i].catalogNameEn != null &&
		// item.childrenList[i].catalogNameEn != "") {
		// } else {
		// if (item.childrenList[i].othersCatalogNames != undefined &&
		// item.childrenList[i].othersCatalogNames != null &&
		// item.childrenList[i].othersCatalogNames != "") {
		// product += product == "" ? product : ";";
		// product += item.childrenList[i].othersCatalogNames;
		// }
		// }
		// }

		for (var i = 0; i < item.childrenList.length; i++) {
			if (item.childrenList[i].catalogId != -1) {
				if (item.childrenList[i].catalogNameEn != undefined && item.childrenList[i].catalogNameEn != null && item.childrenList[i].catalogNameEn != "") {
					product += item.childrenList[i].catalogNameEn;
				}
				product += ";";
			}
		}
		for (var i = 0; i < item.childrenList.length; i++) {
			if (item.childrenList[i].catalogId == -1) {
				if (item.childrenList[i].othersCatalogNames != undefined && item.childrenList[i].othersCatalogNames != null && item.childrenList[i].othersCatalogNames != "") {
					product += item.childrenList[i].othersCatalogNames;
				}
			}
		}
		if (product != "") {
			var productArr = product.split("");
			if (productArr.length > 0) {
				var str = productArr[productArr.length - 1];
				if (str == ";") {
					product = product.substring(0, product.length - 1);
				}
			}
		}

		fruit["detail"] = product;
		return fruit;
	}
	var bindSection3 = function() {
		var param = {};
		param["firmCode"] = companyID;
		param["async"] = false;
		fpbase.Ajax.Read("", "company", "getCompanyTurnover", {
			"reqParams" : JSON.stringify(param)
		}, function(resultData) {
			if (resultData != null && resultData.length > 0) {
				for (var i = 0; i < resultData.length; i++) {
					if (resultData[i].year != currentYear) {
						turnoverData.push(makeOneTurnover(resultData[i]));
					}
				}
				if (turnoverData.length > 0) {
					setion3(turnoverData);
					$(".divSection3").show();
				}
			}
		});
	}
	var makeOneTurnover = function(item) {
		var turnover = {};
		turnover["years"] = item.year;
		turnover["salesTurnover"] = item.salesTotal;
		turnover["importVolumn"] = item.importedTotal;
		turnover["consignmentSales"] = item.agentSellTotal;
		return turnover;
	}
	var bindSection4 = function() {
		var param = {};
		param["firmCode"] = companyID;
		param["async"] = false;
		fpbase.Ajax.Read("", "company", "getCompanyMarketingRegion", {
			"reqParams" : JSON.stringify(param)
		}, function(resultData) {
			if (resultData != null && resultData.length > 0) {
				for (var i = 0; i < resultData.length; i++) {
					if (resultData[i].regionRemarkEn != "" && resultData[i].regionRemarkEn.indexOf(";") != -1) {
						var retionCitys = resultData[i].regionRemarkEn.split(";");
						for (var j = 0; j < retionCitys.length; j++) {
							chinaMapDoShow.push(retionCitys[j]);
						}
					}
				}
				ChianMap.MakeMap(chinaMapDoShow, 650, 500);
				$(".divSection4").show();
			}
		});
	}
	var bindSection5 = function() {
		var param = {};
		param["firmCode"] = companyID;
		param["async"] = false;
		fpbase.Ajax.Read("", "company", "getCompanySuppliers", {
			"reqParams" : JSON.stringify(param)
		}, function(resultData) {
			if (resultData != null && resultData.length > 0) {
				for (var i = 0; i < resultData.length; i++) {
					suppliersData.push(makeOneSupplier(resultData[i]));
				}
				section5(suppliersData);
				$(".divSection5").show();
			}
		});
	}
	var makeOneSupplier = function(item) {
		var supplier = {};
		supplier["suppliers"] = item.supplierName;
		return supplier;
	}
	var bindSection6 = function() {
		var param = {};
		param["firmCode"] = companyID;
		param["async"] = false;
		fpbase.Ajax.Read("", "company", "getBusinessChannel", {
			"reqParams" : JSON.stringify(param)
		}, function(resultData) {
			if (resultData != null && resultData.length > 0) {
				for (var i = 0; i < resultData.length; i++) {
					if (resultData[i].type != 1 || resultData[i].marketList.length > 0) {
						channelData[resultData[i].type - 1] = makeOneBusinessChannel(resultData[i]);
					}
				}
				if (channelData.length > 0) {
					section6(channelData);
					$(".divSection6").show();
				}
			}
		});
	}
	var makeOneBusinessChannel = function(item) {
		var businessChannel = {};
		businessChannel["channel"] = channelDict[item.type];
		businessChannel["badge"] = "";
		if (item.proportion != undefined && item.proportion != null && item.proportion != 0) {
			businessChannel["badge"] = item.proportion + "%";
		} else {
			businessChannel["badge"] = "0%";
		}
		if (item.type != 1) {
			businessChannel["detail"] = item.info;
		} else {
			var detail = "";
			if (item.marketList != undefined && item.marketList != null && item.marketList.length > 0) {
				for (var i = 0; i < item.marketList.length; i++) {
					detail += item.marketList[i].marketNameEn;
					if (i != item.marketList.length - 1) {
						detail += ";";
					}
				}
				businessChannel["detail"] = detail;
			}
		}
		return businessChannel;
	}
	var bindSection7 = function() {
		var param = {};
		param["firmCode"] = companyID;
		param["async"] = false;
		fpbase.Ajax.Read("", "company", "getOurTeam", {
			"reqParams" : JSON.stringify(param)
		}, function(resultData) {
			if (resultData != null && resultData.length > 0) {
				for (var i = 0; i < resultData.length; i++) {
					teamData.push(makeOneTeam(resultData[i]));
				}
				section7(teamData);
			}
		});
	}
	var makeOneTeam = function(item) {
		var team = {};
		team["url"] = $("#hidDefaultTeamImg").val();
		if (item.imgUrlEn != undefined && item.imgUrlEn != null && item.imgUrlEn != "") {
			team["url"] = fileServerEnDomain + item.imgUrlEn + "&width=250";
		}
		team["position"] = item.positionName;
		team["name"] = item.name;
		team["email"] = item.email;
		team["tel"] = item.telephone;
		var lan = "";
		for (var i = 0; i < item.languageList.length; i++) {
			if (item.languageList[i].languageId != -1) {
				lan += item.languageList[i].languageNameEn;
				if (i != item.languageList.length - 1) {
					lan += ";";
				}
			}
		}
		for (var i = 0; i < item.languageList.length; i++) {
			if (item.languageList[i].languageId == -1 && item.languageList[i].othreLanguages != undefined && item.languageList[i].othreLanguages != null && item.languageList[i].othreLanguages != "") {
				lan += lan == "" ? "" : ";";
				lan += item.languageList[i].othreLanguages;
			}
		}
		team["language"] = lan;
		return team;
	}
	var setion1 = function(company) {
		$('#companyImg').attr({
			"src" : company.logoImageUrl
		});
		$('#companyName').html("<span title='" + company.companyName + "'>" + company.companyName + "</span>" + "<span title='" + company.firmAddressEn + "' id='location'>" + company.firmAddressEn + "</span>");
		$("#info").text(company.info);
		$(".tdHoldingPeople").attr('title', company.firmControllerEn);
		$(".tdHoldingPeople").html(company.firmControllerEn);
		$(".tdEstablished").attr('title', company.createDate);
		$(".tdEstablished").html(company.createDate);
		$(".tdYearsWorking").attr('title', currentYear - company.intoYear);
		$(".tdYearsWorking").html(currentYear - company.intoYear);
		$(".tdTotalEmployee").attr('title', company.employeesNum);
		$(".tdTotalEmployee").html(company.employeesNum);
		$(".tdWebsite").append(company.firmUrl);
		var $viewMore = $("#viewMore");
		var $shrink = $("#shrink");
		var $setion1 = $('#section1');
		var $tableIntro = $('#tableIntro');
		var $infocol = $(".info-col");
		var h = $('#profileWrap').outerHeight();

		$infocol.css({"height":"auto"});
		var actualHeight = $infocol.height();
	
		if( actualHeight < 164 ){
			$viewMore.hide();
			$shrink.hide();
		}
		
		$infocol.css({"height":"164px"});
		$setion1.height($tableIntro.outerHeight() + h +90);
		$viewMore.click(function() {
	
			$infocol.addClass("active");
			$viewMore.hide();
			$shrink.show();
			$infocol.css({"height":"auto"});
			$setion1.height($tableIntro.outerHeight()+30);
			$('#all').height( $('#allSection').outerHeight());
		});
	
		$shrink.click(function() {
	
			$infocol.removeClass("active");
			$viewMore.show();
			$shrink.hide();
			$infocol.css({"height":"164px"});
			$setion1.height($tableIntro.outerHeight() + h+90);
			$('#all').height( $('#allSection').outerHeight());
		});
		
	
	}
	var section1_Img = function(arrImg) {
		var $temp = $('<div></div>');
		var showlen = arrImg.length > 4 ? 4 : arrImg.length;
		for (var i = 0; i < arrImg.length; i++) {
			var $img = $('<img src="' + arrImg[i] + '&width=60" data-src="'+arrImg[i]+'"  width="300" height="300" />');
			$temp.append($img);
		}
		$('#showbox').html($temp.html());
		$temp = null;
		var showproduct = {
			"boxid" : "showbox",
			"sumid" : "showsum",
			"boxw" : 300, // 宽度,该版本中请把宽高填写成一样
			"boxh" : 300, // 高度,该版本中请把宽高填写成一样
			"sumw" : 58, // 列表每个宽度,该版本中请把宽高填写成一样
			"sumh" : 58, // 列表每个高度,该版本中请把宽高填写成一样
			"sumi" : 7, // 列表间隔
			"sums" : showlen, // 列表显示个数
			"sumsel" : "sel",
			"sumborder" : 1, // 列表边框，没有边框填写0，边框在css中修改
			"lastid" : "showlast",
			"nextid" : "shownext"
		}; // 参数定义
		$.ljsGlasses.pcGlasses(showproduct); // 方法调用，务必在加载完后执行
	}
	var setion2 = function(fruits) {
		// 主要产品 水果
		var fruitsObj = $('#fruits');
		var $tbody = $('<tbody></tbody>');
		var len = fruits.length;
		var off = false;
		if (fruits[0]["percent"]) {
			off = true;
		}
		// 判断是否 有 百分比，要是第一个值就没有，下面就不产生百分比。而且饼图不出现）
		for (var i = 0; i < len; i++) {
			var $tr = $('<tr></tr>');
			var fruitsItem = $('<td class="fruitTrading" >' + fruits[i]["title"] + '</td>');
			var fruitsDetail = $('<td><div title="' + fruits[i]["detail"] + '">' + fruits[i]["detail"] + '</div></td>');
			if (off) {
				var fruitsPercent = $('<td class="percent"><span style="width:80px;" >' + fruits[i]["percent"] + '</span></td>');
			}

			if (i == len - 1) {
				var fruitsItem = $('<td class="last fruitTrading">' + fruits[i]["title"] + '</td>');
				var fruitsDetail = $('<td class="last" title="' + fruits[i]["detail"] + '">' + fruits[i]["detail"] + '</td>');
				if (off) {
					var fruitsPercent = $('<td class="percent last"><span style="width:80px;" >' + fruits[i]["percent"] + '</span></td>');
				}

			}

			if (off) {
				$tr.append(fruitsItem, fruitsDetail, fruitsPercent);
			} else {
				$tr.append(fruitsItem, fruitsDetail);
			}

			$tbody.append($tr);
		}
		fruitsObj.append($tbody);
		if (off) {
			var myData = new Array();
			for (var i = 0; i < fruits.length; i++) {
				var tempArr = [];
				var percent = parseFloat(fruits[i]["percent"]);
				if (percent && percent != "" && percent != 0.0) {
					tempArr.push(fruits[i]["title"], percent);

					myData.push(tempArr);
				}
			}
			Highcharts.setOptions({
				colors : [ '#FFA007', '#B2AD43', '#9B293A', '#F4C104', '#5F6C8E', '#3B5F91', '#BB98D7', '#9C0D05', '#CED307', '#F22E84', '#29CE6F', '#DBE8A2' ]
			});
			$('#container').highcharts({
				chart : {
					plotBackgroundColor : null,
					plotBorderWidth : null,
					plotShadow : false
				},
				title : {
					text : ''
				},
				tooltip : {
					headerFormat : '{series.name}<br>',
					pointFormat : '{point.name}: <b>{point.percentage:.1f}%</b>'
				},
				plotOptions : {
					pie : {
						allowPointSelect : true,
						cursor : 'pointer',
						dataLabels : {
							enabled : true,
							format : '{point.name}: {point.percentage:.1f} %',
							style : {
								color : (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black',
								fontSize : '14px',
								fontWeight : 'normal'
							}
						}
					}
				},
				series : [ {
					type : 'pie',
					name : 'Percentage of Main products',
					data : myData
				// style : {
				// fontSize : '12px',
				// fontWeight : 'normal'
				// }
				} ]
			});

		} else {
			$('#container').detach();
			// $('#section2').css("height", "400px");
		}
	}

	var setion3 = function(turnoverData) {
		var labelsX = [];
		var data1 = [];
		var data2 = [];
		var data3 = [];
		var len = turnoverData.length;
		for (var i = 0; i < turnoverData.length; i++) {
			labelsX.push(turnoverData[i]["years"]);
		}
		for (var i = 0; i < turnoverData.length && i < 4; i++) {
			data1.push(parseFloat(turnoverData[i]["salesTurnover"]));
			data2.push(parseFloat(turnoverData[i]["importVolumn"]));
			data3.push(parseFloat(turnoverData[i]["consignmentSales"]));
		}
		// 主要销售额
		var turnoverObj = $('#turnover');
		var $turnoverTbody = $('<tbody></tbody>');
		var $temp = $('<tbody></tbody>');
		for (var i = 0; i < turnoverData.length; i++) {
			if (i % 2 == 0) {
				var $tr = $('<tr class="gray"> </tr>');
			} else {
				var $tr = $('<tr> </tr>');
			}
			var years = $('<td>' + turnoverData[i]["years"] + '</td>');
			var salesTurnover = $('<td class="w270" >' + turnoverData[i]["salesTurnover"] + '&nbsp;' + '</td>');
			var importVolumn = $('<td class="w270">' + turnoverData[i]["importVolumn"] + '&nbsp;' + '</td>');
			var consignmentSales = $('<td class="w270">' + turnoverData[i]["consignmentSales"] + '&nbsp;' + '</td>');
			$tr.append(years, salesTurnover, importVolumn, consignmentSales);
			$temp.append($tr);
		}
		$turnoverTbody.html($temp.html());
		turnoverObj.append($turnoverTbody);
		// high chart
		Highcharts.setOptions({
			colors : [ '#4F81BD', '#C0504D', '#9BBB59' ]
		});
		$('#container2').highcharts({
			chart: {
				type: 'column'
			},
			title: {
				text: 'Sales Force Analysis ',
				style: {
					fontSize: '18px'
	
				}
			},
			xAxis: {
				categories: labelsX,
				crosshair: true
			},
			yAxis: {
				min: 0,
				title:{
					text: 'Million USD',
					align: 'high',
	                offset: 0,
	                rotation: 0,
	                y: -24
	
				}
			},
			tooltip: {
				headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
				pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
					'<td style="padding:0"><b>{point.y:.1f} Million</b></td></tr>',
				footerFormat: '</table>',
				shared: true,
				useHTML: true
			},
			plotOptions: {
				column: {
					pointPadding: 0.2,
					borderWidth: 0,
					pointWidth:20 //固定了每一个柱状图宽度
				}
	
			},
			legend: {
				align: 'center',
				verticalAlign: 'top',
				y:20,
				x:200,
				borderWidth: 0,
				width:800
			},
			series: [{
				name: 'Sales Turn over',
				data: data1
			}, {
				name: 'Import Volume',
				data: data2
			}, {
				name: 'Consignment Sales Turnover',
				data: data3
			}]
		});
	}

	var section5 = function(suppliersData) {
		var SuppliersObj = $('#Suppliers');
		for (var i = 0; i < suppliersData.length; i++) {
			var $div = $('<div class="col-6"></div>');
			var $a = $('<span title="' + suppliersData[i]["suppliers"] + '" class="thumbnail">' + suppliersData[i]["suppliers"] + '</span>');
			$div.append($a);
			SuppliersObj.append($div);
		}
	}

	var section6 = function(channelData) {
		var $channelGroud = $('#channelGroud');
		var $temp = $('<div></div>');
		var justify = false;
		// 判断是否有百分比传人过来
		if (channelData[0]["badge"] && channelData[0]["badge"] != "0%") {
			justify = true;
		}
		for (var i = 0; i < channelData.length; i++) {
			if (i % 2 == 1) {
				var $div = $('<div class="list-group two"></div>');
			} else {
				var $div = $('<div class="list-group"></div>');
			}
			var $subDiv = $('<div class="list-group-itemActive"><div>');
			var $h4 = $('<h4 class="list-group-item-heading"></h4>');
			var $span1 = $(' <span class="channel">' + channelData[i]["channel"] + '</span>');
			if (justify) {
				var $span2 = $('  <span class="badge" >' + channelData[i]["badge"] + '</span>');
				$h4.append($span1, $span2);
			} else {
				$h4.append($span1);
			}
			var $p = $('<p class="list-group-item"></p>');
			var $aa = $('<a title="' + channelData[i]["detail"] + '" class="list-group-item-text">' + channelData[i]["detail"] + '</a>');
			$subDiv.append($h4);
			$p.append($aa);
			$div.append($subDiv, $p);
			$temp.append($div);
		}
		$channelGroud.html($temp.html());
		$temp = null;
		$('#channelAll').height( $channelGroud.height() + 400 );
		

		if (justify) {
			var myData = new Array();
			for (var i = 0; i < channelData.length; i++) {
				var tempArr = [];
				var percent = parseFloat(channelData[i]["badge"]);
				if (percent && percent != "" && percent != 0.0) {
					tempArr.push(channelData[i]["channel"], percent);
					myData.push(tempArr);
				}
			}
			Highcharts.setOptions({
				colors : [ '#5F6C8E', '#3B5F91', '#9C0D05', '#CED307' ]
			});
			$('#container3').highcharts({
				chart : {
					plotBackgroundColor : null,
					plotBorderWidth : null,
					plotShadow : false
				},
				title: {
					text: ''
				},
				tooltip : {
					headerFormat : '{series.name}<br>',
					pointFormat : '{point.name}: <b>{point.percentage:.1f}%</b>'

				},
				plotOptions : {
					pie : {
						allowPointSelect : true,
						cursor : 'pointer',
						dataLabels : {
							enabled : true,
							format : '{point.name}:<br> {point.percentage:.1f} %',
							style : {
								color : (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black',
								fontSize : '14px',
								fontWeight : 'normal'

							}
						}
					}
				},
				series : [ {
					type : 'pie',
					name : 'Percentage of Business Channel',
					data : myData,
					style : {
						fontSize : '12px',
						fontWeight : 'normal'
					}
				} ]
			});
			$('#channelAll').height( $channelGroud.height() + 400 );
		} else {
			$('#container3').detach();
			$('#section6').css("height", "270px");
			$('#channelGroud').addClass('semi');
		}
	}

	var section7 = function(teamData) {

		var $team = $('#team');

		var $ul = $('<ul></ul>');

		for (var i = 0; i < teamData.length; i++) {

			var $li = $('<li></li>');

			var $div = $('<div class="thumbnail" ></div>');

			var $img = $('<div class="wrapImg"><img src="' + teamData[i].url + '" alt="team" ><span></span></div>');

			var $div2 = $('<div class="caption" ></div>');

			var $postion = $('<p class="team_member_position" >Position:<span class="position" title="' + teamData[i]["position"] + '">' + teamData[i]["position"] + '</span></p>');

			var $name = $('<p class="team_member_name" >Name:<span class="name" title="' + teamData[i]["name"] + '">' + teamData[i]["name"] + '</span></p>');

			var $email = $('<p class="team_member_email" >Email:<span class="email" title="' + teamData[i]["email"] + '">' + teamData[i]["email"] + '</span></p>');

			var $tel = $('<p class="team_member_tel" >Tel:<span class="tel" title="' + teamData[i]["tel"] + '">' + teamData[i]["tel"] + '</span></p>');

			var $language = $('<p class="team_member_language" >Language:<span class="language" title="' + teamData[i]["language"] + '" onerror="this.src=' + "images/errorTeam.jpg" + '">' + teamData[i]["language"] + '</span></p>');

			$div2.append($postion, $name, $email, $tel, $language);
			$div.append($img, $div2);
			$li.append($div);
			$ul.append($li);

		}

		$team.html($ul.html());

		$ul = null;

	}

	var initLeftNavFixed = function() {
		var onOff = true;
		var $footeCompany = $("#footeCompany");
		var $navFixed = $('#navFixed');

		$(window).scroll(function() {

			setTimeout(function() {
				var scrollTop = $('body').scrollTop();
				var min = 20;

				if (scrollTop > 200) {
					$navFixed.css({
						"top" : scrollTop - 200
					});
				} else {
					$navFixed.css({
						"top" : 20
					});
				}
				if (onOff) {
					$footeCompany.show();
					$('#all').outerHeight($('#allSection').outerHeight());
				}
			}, 600);

		});

		var allA = $navFixed.find('a');
		$navFixed.delegate('a', 'click', function() {
			$navFixed.css("top", $navFixed.css("top"));
			allA.removeClass('active');
			$(this).addClass('active');
		});
		
		var now = 0;
		var index =  $('.sel').index();
		var l =  $('#showsum').find('span').length
		var allSpan = $('#showsum').find('span') ;
		var now =  $('.sel').index();
		var src = '';
		var $showlast = $('#showlast');
		var $shownext = $('#shownext');
		
		$showlast.click(function(){
		
			index =  $('.sel').index();
			
			if(index == 0 ){
				$(this).css({"background":"#eee","color":"#eee"});
			}else{
				index --;
				allSpan.eq(index).addClass('sel').siblings().remove('sel');
				src = $('.sel').find('img').attr("src");
				$(this).css({"background":"#fff","color":"#333"});
				$shownext.css({"background":"#fff","color":"#333"});
				$('#pic300').attr({"src":src});
			}
			
		
		
			
		})
	
		$shownext.click(function(){	
				
			index =  $('.sel').index();
			if(index >= l-4){
				$(this).css({"background":"#eee","color":"#eee"});	
			}else{
				index ++ ;
				allSpan.eq(index).addClass('sel').siblings().remove('sel');
				src = $('.sel').find('img').attr("src");
				$('#pic300').attr({"src":src});
				$(this).css({"background":"#fff","color":"#333"});
				$showlast.css({"background":"#fff","color":"#333"});
			}
		
		})
		
		
		if( l > 4){
			
			$.each(allSpan,function( i ,elem){
			
			$(elem).mouseover(function(){
				
					if(i==0 ){
						$showlast.css({"background":"#eee","color":"#eee"});
					}else if( i == l -1 ){
						$shownext.css({"background":"#eee","color":"#eee"});
					}else{
						$shownext.css({"background":"#fff","color":"#333"});
						$showlast.css({"background":"#fff","color":"#333"});
					}
				});
			});
		}
		else{
			allSpan.mouseover(function(){
				$shownext.css({"background":"#eee","color":"#eee"});
				$showlast.css({"background":"#eee","color":"#eee"});
			});
		}
		
		
	}
	return Return;
})();

$(function() {
	companyInfo.Init();
})


//扩展 放大镜插件
jQuery.ljsGlasses = {
	pcGlasses : function(_obj) {
		var _box = $("#" + _obj.boxid);
		var _sum = $("#" + _obj.sumid);
		var _last, _next;
		var _imgarr = _box.find("img");
		var _length = _imgarr.length;
		var _index = 0;
		var _arr = new Array();
		_sum.append("<p style='position:absolute;left:0;top:0;'></p>");
		var _sumbox = _sum.find("p");

		for (var i = 0; i < _length; i++) {
			_arr[i] = new Array();
			_arr[i][0] = _imgarr.eq(i).attr("data-src");
			_arr[i][1] = _imgarr.eq(i).attr("width");
			_arr[i][2] = _imgarr.eq(i).attr("height");
			var _scale = _arr[i][1] / _arr[i][2];
			if (_scale == 1) {
				_arr[i][3] = _obj.boxw; // width
				_arr[i][4] = _obj.boxh; // height
				_arr[i][5] = 0; // top
				_arr[i][6] = 0; // left
				_arr[i][7] = _obj.boxw / 2;
				_arr[i][8] = _obj.boxw * 2; // width
				_arr[i][9] = _obj.boxh * 2; // height
				_sumbox.append("<span><img src='" + _imgarr.eq(i).attr("src") + "' width='" + _obj.sumw + "' height='" + _obj.sumh + "' /><i>&nbsp;</i></span>");
			}
			if (_scale > 1) {
				_arr[i][3] = _obj.boxw; // width
				_arr[i][4] = _obj.boxw / _scale;
				_arr[i][5] = (_obj.boxh - _arr[i][4]) / 2;
				_arr[i][6] = 0; // left
				_arr[i][7] = _arr[i][4] / 2;
				_arr[i][8] = _obj.boxh * 2 * _scale; // width
				_arr[i][9] = _obj.boxh * 2; // height
				var _place = _obj.sumh - (_obj.sumw / _scale);
				_place = _place / 2;
				_sumbox.append("<span><img src='" + _imgarr.eq(i).attr("src") + "' width='" + _obj.sumw + "' style='top:" + _place + "px;' /><i>&nbsp;</i></span>");
			}
			if (_scale < 1) {
				_arr[i][3] = _obj.boxh * _scale; // width
				_arr[i][4] = _obj.boxh; // height
				_arr[i][5] = 0; // top
				_arr[i][6] = (_obj.boxw - _arr[i][3]) / 2;
				_arr[i][7] = _arr[i][3] / 2;
				_arr[i][8] = _obj.boxw * 2; // width
				_arr[i][9] = _obj.boxw * 2 / _scale;
				var _place = _obj.sumw - (_obj.sumh * _scale);
				_place = _place / 2;
				_sumbox.append("<span><img src='" + _imgarr.eq(i).attr("src") + "' height='" + _obj.sumh + "' style='left:" + _place + "px;' /><i>&nbsp;</i></span>");
			}
		}
		_imgarr.remove();

		_sum.append("<div style='clear:both;width:100%;'></div>");
		var _sumarr = _sum.find("span");
		var _sumimg = _sum.find("img");
		_sumarr.eq(_index).addClass(_obj.sumsel);
		var _border = _obj.sumborder * 2 + _obj.sumh;
		var _sumwidth = (_border + _obj.sumi) * _obj.sums;
		var _sumboxwidth = (_border + _obj.sumi) * _length;
		_sum.css({
			"overflow" : "hidden",
			"height" : _border + "px",
			"width" : _sumwidth + "px",
			"position" : "relative"
		});
		_sumbox.css({
			"width" : _sumboxwidth + "px"
		});
		_sumarr.css({
			"float" : "left",
			"margin-left" : _obj.sumi + "px",
			"width" : _obj.sumw + "px",
			"height" : _obj.sumh + "px",
			"overflow" : "hidden",
			"position" : "relative"
		});
		_sumimg.css({
			"max-width" : "100%",
			"max-height" : "100%",
			"position" : "relative"
		});

		_box.append("<div style='position:relative;'><b style='display:block;'><img id='pic300' style='display:block;' src='' /></b><span style='position:absolute;left:0;top:0;display:none;z-index:5;'></span></div><p style='position:absolute;overflow:hidden;top:0;display:none;'><img style='max-width:none;max-height:none;position:relative;left:0;top:0;' src='' /></p>");
		var _glass = _box.find("span");
		var _boximg = _box.find("b img");
		var _imgout = _box.find("div");
		var _showbox = _box.find("p");
		var _showimg = _box.find("p img");



		_box.css({
			"width" : _obj.boxw + "px",
			"height" : _obj.boxh + "px",
			"position" : "relative"
		});
		var _showboxleft = _obj.boxw + 10;
		_showbox.css({
			"width" : _obj.boxw + "px",
			"height" : _obj.boxh + "px",
			"left" : _showboxleft + "px"
		});

		var imgPlaces = function() {
			_showimg.attr("src", _arr[_index][0]);
			_boximg.attr("src", _arr[_index][0]+"&width=300");
			_boximg.css({
				"width" : _arr[_index][3] + "px",
				"height" : _arr[_index][4] + "px"
			});
			_imgout.css({
				"width" : _arr[_index][3] + "px",
				"height" : _arr[_index][4] + "px",
				"top" : _arr[_index][5] + "px",
				"left" : _arr[_index][6] + "px",
				"position" : "relative"
			});
			_glass.css({
				"width" : _arr[_index][7] + "px",
				"height" : _arr[_index][7] + "px"
			});
			_showimg.css({
				"width" : _arr[_index][8] + "px",
				"height" : _arr[_index][9] + "px"
			});

		
	
			if(!document.addEventListener){
				var pic300Height = $('#pic300').height() ;
				var picTop =(300-pic300Height ) / 2 ;
				if( pic300Height < 300){
					$('#pic300').css({"margin-top":picTop });
				}else{
					picTop = 0;
					$('#pic300').css({"margin-top":picTop });
				}
			}else{
				$('#pic300').load(function(){
					var pic300Height = $('#pic300').height() ;
					var picTop =(300-pic300Height ) / 2 ;
					if( pic300Height < 300){
						$('#pic300').css({"margin-top":picTop });
					}else{
						picTop = 0;
						$('#pic300').css({"margin-top":picTop });
					}
					
				});
			}
		
	
			
		};
		imgPlaces();

	
	

		_imgout.mousemove(function(e){
			play(e);
		}); // mouse END


		function play(e){
			var _gl_w = _glass.width() / 2;
			var _maxX = _imgout.width() - _gl_w;
			var _maxY = _imgout.height() - _gl_w;
			var _moveX = 0, _moveY = 0;
			var _nowX = e.pageX - _imgout.offset().left;
			var _nowY = e.pageY - _imgout.offset().top;
			var _moveX = _nowX - _gl_w, _moveY = _nowY - _gl_w;

			if (_nowX <= _gl_w) {
				_moveX = 0;
			}
			if (_nowX >= _maxX) {
				_moveX = _maxX - _gl_w;
			}
			if (_nowY <= _gl_w) {
				_moveY = 0;
			}
			if (_nowY >= _maxY) {
				_moveY = _maxY - _gl_w;
			}
			_glass.css({
				"left" : _moveX + "px",
				"top" : _moveY + "px"
			});

			var _imgX = -_moveX * _showbox.width() / _glass.width();
			var _imgY = -_moveY * _showbox.width() / _glass.width();
			
			var pic300Width = $('#pic300').width() ;
			var pic300Height = $('#pic300').height() ;
			var picTop =(300-pic300Height ) / 2 ;
			
	
			if( pic300Height < 300){
				$('#pic300').css({"margin-top":picTop });
			}else{
				picTop = 0;
				$('#pic300').css({"margin-top":picTop });
			}
			
			_showimg.css({
				"left" : _imgX + "px",
				"top" : (_imgY + picTop  )+ "px",
				"height": pic300Height * 2,
				"width": pic300Width * 2,
				"max-width":"600px",
				"max-height":"600px"
			});

		}

		_imgout.mouseenter(function() {
			_glass.css("display", "block");
			_showbox.css("display", "block");
		});
		_imgout.mouseleave(function() {
			_glass.css("display", "none");
			_showbox.css("display", "none");
		});

		// åˆ—è¡¨éƒ¨åˆ†
		var _nextbtn = $("#" + _obj.nextid);
		var _lastbtn = $("#" + _obj.lastid);
		var _moveindex = 0; // ç´¢å¼•ç§»åŠ¨

		var _sumListMove = function() {
			var _leftmove = -_moveindex * (_border + _obj.sumi);
			if (_sumbox.is(":animated")) {
				_sumbox.stop(true, true);
			}
			_sumbox.animate({
				left : _leftmove + "px"
			}, 300);
			_sumarr.eq(_index).addClass(_obj.sumsel).siblings().removeClass(_obj.sumsel);
			imgPlaces();
		}; // fun END

		if (_length <= _obj.sums) {
			var _place = (_obj.sums - _length) * _border / 2;
			_sumbox.css("left", _place + "px");
			_nextbtn.click(function() {
				_index++;
			
				if (_index >= _length) {
					_index = _length - 1;
				}
				_sumarr.eq(_index).addClass(_obj.sumsel).siblings().removeClass(_obj.sumsel);
				imgPlaces();
			});
			_lastbtn.click(function() {
				_index--;
				if (_index <= 0) {
					_index = 0;
				}
				_sumarr.eq(_index).addClass(_obj.sumsel).siblings().removeClass(_obj.sumsel);
				imgPlaces();
			});
		} else {
			var _maxNum = _length - _obj.sums;
			_nextbtn.click(function() {
				_moveindex++;
				if (_moveindex >= _maxNum) {
					_moveindex = _maxNum;
				}
				if (_index <= _moveindex) {
					_index = _moveindex;
				}
				_sumListMove();
				move( _moveindex );
			});
			_lastbtn.click(function() {
				_moveindex--;
				if (_moveindex <= 0) {
					_moveindex = 0;
				}
				if (_index >= _moveindex + _obj.sums) {
					_index = _moveindex + _obj.sums - 1;
				}
				_sumListMove();
				move( _moveindex );
			
			});
		} // if END

		_sumarr.hover(function() {
			_index = $(this).index();
			move( _index );
		
		});
		
		
		function move(_index){
			_sumarr.eq(_index).addClass(_obj.sumsel).siblings().removeClass(_obj.sumsel);
			imgPlaces();
			var pic300Width = $('#pic300').width() ;
			var pic300Height = $('#pic300').height() ;
			var picTop =(300-pic300Height ) / 2 ;
			
	
			if( pic300Height < 300){
				$('#pic300').css({"margin-top":picTop });
			}else{
				picTop = 0;
				$('#pic300').css({"margin-top":picTop });
			}
		}
	
	} // pcGlasses END
}