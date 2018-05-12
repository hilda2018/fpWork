var companyInfo = (function() {
	var companyID;
	var company;
	var arrImg = [];
	var teamData = [];
	var Return = {
		Init : function() {
			getQueryParam();
			bindSection1();
			bindSection2();
			bindSection2_season();
			bindSection4();
			bindSection7();
			initLeftNavFixed();
			postponeTeamImgLoad();
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
			fpbase.Ajax.Read("", "export/company", "checkCompanyAvailability", {
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
		fpbase.Ajax.Read("", "export/company", "getCompanyProfileInfo", {
			"reqParams" : JSON.stringify(param)
		}, function(resultData) {
			if (resultData != null) {
				company = makeOneProfileInfo(resultData);
				setion1(company);
			}
		});
		fpbase.Ajax.Read("", "export/company", "getCompanyProfileImg", {
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
		var businessType = "";
		if (item.bussinessTypeList != undefined && item.bussinessTypeList.length > 0) {
			for (var i = 0; i < item.bussinessTypeList.length; i++) {
				if (item.bussinessTypeList[i].businessTypeId != -1) {
					businessType += item.bussinessTypeList[i].businessTypeNameEn + ",";
				}
			}
			for (var i = 0; i < item.bussinessTypeList.length; i++) {
				if (item.bussinessTypeList[i].businessTypeId == -1) {
					if (item.bussinessTypeList[i].businessTypeOthersRemarks != undefined && item.bussinessTypeList[i].businessTypeOthersRemarks != null && item.bussinessTypeList[i].businessTypeOthersRemarks != "") {
						businessType += item.bussinessTypeList[i].businessTypeOthersRemarks;
					}
				}
			}
			if (businessType != "") {
				var businessTypetArr = businessType.split("");
				if (businessTypetArr.length > 0) {
					var str = businessTypetArr[businessTypetArr.length - 1];
					if (str == ",") {
						businessType = businessType.substring(0, businessType.length - 1);
					}
				}
			}
		}
		profileInfo["companyName"] = item.firmName;
		profileInfo["firmAddressEn"] = item.firmAddress;
		profileInfo["country"] = item.firmCountryRegionName;
		profileInfo["businessType"] = businessType;
		profileInfo["createDate"] = item.createYear;
		profileInfo["firmUrl"] = item.firmWebsite != undefined ? item.firmWebsite : "";
		profileInfo["info"] = item.firmProfile;
		profileInfo["file"] = item.fileList;
		return profileInfo;
	}
	var bindSection2 = function() {
		var param = {};
		param["firmCode"] = companyID;
		param["async"] = false;
		fpbase.Ajax.Read("", "export/company", "getMainfruit", {
			"reqParams" : JSON.stringify(param)
		}, function(resultData) {
			if (resultData != null) {
				generateFruitTab(resultData);
			}
		});
		$(".ulFruitTab").on("click", ".liFruitTab", function() {
			var $li = $(this);
			$li.find("a").addClass("on");
			$li.siblings().find("a").removeClass("on");
			var fparam = {};
			fparam["mainFruitsId"] = $li.attr("data-id");
			fpbase.Ajax.Read("", "export/company", "getMainfruitImages", {
				"reqParams" : JSON.stringify(fparam)
			}, function(resultData) {
				if (resultData != null) {
					makeOneFruit(resultData, $li.attr("data-des"));
				}
			});
		});
	}
	var generateFruitTab = function(tabData) {
		var tabLiHtml = "";
		for (var i = 0; i < tabData.length; i++) {
			tabLiHtml += " <li class=liFruitTab data-id='" + tabData[i].mainFruitsId + "' data-des='" + tabData[i].description + "'><a href='javascript:;' class='' ><strong>" + tabData[i].catalogNameEn + "</strong><br><span>" + tabData[i].originNameEn + "</span></a></li>";
		}
		$(".ulFruitTab").append(tabLiHtml);
		$(".ulFruitTab").children().first().click();
		tabControl();

	}
	var tabControl = function() {

		var attrJson = {};

		attrJson.daoshudier = 0;
		attrJson.tab = $('#tabbox .tab');

		attrJson.len = attrJson.tab.find('li').length;

		attrJson.allLi = attrJson.tab.find('li');

		// var $allLi =
		attrJson.w = 0;
		// var w = 0;
		var starL = 0;

		attrJson.allW = [];

		$.each(attrJson.allLi, function(i, elem) {

			var temW = $(elem).outerWidth(true, true);
			attrJson.w += temW;

			attrJson.allW.push(temW);
			if (attrJson.w <= 890) {
				attrJson.daoshudier += temW;
			}
			if (attrJson.w > 890) {
				$(elem).addClass('hide');
			}

		});

		attrJson.tab.width(attrJson.w + 6);

		var firstHideIndex = attrJson.tab.find('.hide').eq(0).index();
		var orgin = firstHideIndex - 1;

		var next = prev = -1;

		var slideDistance = 0;
		var b = true;

		var lastCurrent = firstHideIndex - 1;

		if (attrJson.w > 890) {

			attrJson.tabPrev = $('#tabPrev');
			attrJson.tabNext = $('#tabNext');
			attrJson.tabPrev.show();
			attrJson.tabNext.show();

			attrJson.tabWrapUl = $('#tabbox .tabWrapUl');
			attrJson.tabWrapUl.css({
				"width" : attrJson.daoshudier - 2,
				"padding-left" : (852 - attrJson.daoshudier) / 2
			});

			attrJson.tabNext.click(function() {

				prev = -1;
				if (next != firstHideIndex) {

					next = firstHideIndex;
					if (firstHideIndex >= attrJson.len) {
						next = -1;
						return false;
					}
					slideDistance -= attrJson.allW[firstHideIndex];

					attrJson.tab.animate({
						"marginLeft" : slideDistance
					}, 800, function() {
						lastCurrent = firstHideIndex;
						++firstHideIndex;
					});
				}
			});

			attrJson.tabPrev.click(function() {

				next = -1;
				if (prev != lastCurrent) {

					prev = lastCurrent;
					if (lastCurrent <= orgin) {
						return false;
					}
					slideDistance += attrJson.allW[lastCurrent];
					attrJson.tab.animate({
						"marginLeft" : slideDistance
					}, 800, function() {
						firstHideIndex = lastCurrent;
						--lastCurrent;
					});

				}
			});
		}

	}
	var makeOneFruit = function(friutImgData, description) {
		$(".ulFruitContent").html("");
		$("#shopPicShow").html("");
		var contentLiHtml = "<li style='display: list-item;'><p>" + description + "</p></li>";
		$(".ulFruitContent").append(contentLiHtml);
		var imgHtml = "";
		for (var i = 0; i < friutImgData.length; i++) {
			var index = friutImgData.length;
			imgHtml += "<div class='wrapDiv' style='z-index:" + (index - i) + "'>";
			imgHtml += "  <div class='small_pic'>";
			imgHtml += "    <span class='mark'></span>";
			imgHtml += "    <span class='float_layer'></span>";
			imgHtml += "    <img src='" + fileServerEnDomain + friutImgData[i].imageUrlEn + "' alt='' longdesc='https://www.freshport.com' width='100px'>";
			imgHtml += "  </div>";
			imgHtml += "  <div class='big_pic'>";
			imgHtml += "   <img src='" + fileServerEnDomain + friutImgData[i].imageUrlEn + "' alt='' longdesc='https://www.freshport.com' width='300px'>";
			imgHtml += "  </div>";
			imgHtml += "</div>";
			index = index - 1;
		}
		$("#shopPicShow").append(imgHtml);
		cropFruitPicShow();

	}
	var bindSection2_season = function() {
		var param = {};
		param["firmCode"] = companyID;
		param["async"] = false;
		fpbase.Ajax.Read("", "export/company", "getMainfruitSeasonDetail", {
			"reqParams" : JSON.stringify(param)
		}, function(resultData) {
			if (resultData != null) {
				drawSeason(resultData);
			}
		});
	}
	var bindSection4 = function() {
		var param = {};
		param["firmCode"] = companyID;
		param["async"] = false;
		fpbase.Ajax.Read("", "export/company", "getVideoLink", {
			"reqParams" : JSON.stringify(param)
		}, function(resultData) {
			if (resultData != null && resultData.length > 0) {
				var arrLink = [];
				for (var i = 0; i < resultData.length; i++) {
					arrLink.push(resultData[i].fileUrl);
				}
				section4Video(arrLink);
				$(".divSection4").show();
			}
		});
	}
	var bindSection7 = function() {
		var param = {};
		param["firmCode"] = companyID;
		param["async"] = false;
		fpbase.Ajax.Read("", "export/company", "getOurTeam", {
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
					lan += ",";
				}
			}
		}
		for (var i = 0; i < item.languageList.length; i++) {
			if (item.languageList[i].languageId == -1 && item.languageList[i].othreLanguages != undefined && item.languageList[i].othreLanguages != null && item.languageList[i].othreLanguages != "") {
				lan += lan == "" ? "" : ",";
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
		$(".tdCountry").attr('title', company.country);
		$(".tdCountry").html(company.country);
		$(".tdBussinessType").attr('title', company.businessType);
		$(".tdBussinessType").html(company.businessType);
		$(".tdEstablished").attr('title', company.createDate);
		$(".tdEstablished").html(company.createDate);
		$(".tdWebsite").append(company.firmUrl);
		if (company.file.length) {
			var fileHtml = "<div>";
			for (var i = 0; i < company.file.length; i++) {
				fileHtml += '<a href="' + company.file[i]["fileUrl"] + '" target="_blank" class="profile" alt="Company Brochure">' + company.file[i]["fileName"] + '</a>';
			}
			fileHtml += "</div>";
			$('#profileWrap').append(fileHtml);
		}
		var $setion1 = $('#section1');
		var $tableIntro = $('#tableIntro');
		var $viewMore = $("#viewMore");
		var $shrink = $("#shrink");
		var $infocol = $(".info-col");
		var h = $('#profileWrap').outerHeight();

		$infocol.css({
			"height" : "auto"
		});
		var actualHeight = $infocol.height();

		if (actualHeight < 164) {
			$viewMore.hide();
			$shrink.hide();
		}

		$infocol.css({
			"height" : "164px"
		});
		$setion1.height($tableIntro.outerHeight() + h + 90);
		$viewMore.click(function() {

			$infocol.addClass("active");
			$viewMore.hide();
			$shrink.show();
			$infocol.css({
				"height" : "auto"
			});
			$setion1.height($tableIntro.outerHeight() + 30);
			$('#all').height($('#allSection').outerHeight());
		});

		$shrink.click(function() {

			$infocol.removeClass("active");
			$viewMore.show();
			$shrink.hide();
			$infocol.css({
				"height" : "164px"
			});
			$setion1.height($tableIntro.outerHeight() + h + 90);
			$('#all').height($('#allSection').outerHeight());
		});
	}
	var section1_Img = function(arrImg) {
		var $temp = $('<div></div>');
		var showlen = arrImg.length > 4 ? 4 : arrImg.length;
		for (var i = 0; i < arrImg.length; i++) {
			var $img = $('<img src="' + arrImg[i] + '&width=60" data-src="' + arrImg[i] + '"  width="300" height="300" />');
			$temp.append($img);
		}
		$('#showbox').html($temp.html());
		$temp = null;
		var showproduct = {
			"boxid" : "showbox",
			"sumid" : "showsum",
			"boxw" : 300, // 瀹藉害,璇ョ増鏈腑璇锋妸瀹介珮濉啓鎴愪竴鏍�
			"boxh" : 300, // 楂樺害,璇ョ増鏈腑璇锋妸瀹介珮濉啓鎴愪竴鏍�
			"sumw" : 58, // 鍒楄〃姣忎釜瀹藉害,璇ョ増鏈腑璇锋妸瀹介珮濉啓鎴愪竴鏍�
			"sumh" : 58, // 鍒楄〃姣忎釜楂樺害,璇ョ増鏈腑璇锋妸瀹介珮濉啓鎴愪竴鏍�
			"sumi" : 7, // 鍒楄〃闂撮殧
			"sums" : showlen, // 鍒楄〃鏄剧ず涓暟
			"sumsel" : "sel",
			"sumborder" : 1, // 鍒楄〃杈规锛屾病鏈夎竟妗嗗～鍐�0锛岃竟妗嗗湪css涓慨鏀�
			"lastid" : "showlast",
			"nextid" : "shownext"
		}; // 鍙傛暟瀹氫箟
		$.ljsGlasses.pcGlasses(showproduct); // 鏂规硶璋冪敤锛屽姟蹇呭湪鍔犺浇瀹屽悗鎵ц
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
	var drawSeason = function(seasoneData) {

		var monthArr = [ 'Jan.', 'Feb.', 'Mar.', 'Apr.', 'May.', 'Jun.', 'Jul.', 'Aug.', 'Sept.', 'Oct.', 'Nov.', 'Dec.' ];

		var count = seasoneData.length > 10 ? 10 : seasoneData.length;// 浼犱汉杩囨潵鐨勬暟鎹�
		// 鍔ㄦ€佺敓鎴� 鏂规牸鑳屾櫙
		var $line = $('#line');
		var $temp = $('<div></div>');
		for (var i = 0; i < count; i++) {

			var $ulLine = $('<ul></ul>');
			for (var s = 0; s < 13; s++) {

				var $liLine = $('<li><div></div><li>');
				$ulLine.append($liLine);
			}
			$temp.append($ulLine);
		}
		$line.html($temp.html());


		var $xNum = $('#xNum');
		var $ulX = $('<ul></ul>');
		for (var i = 0; i < 12; i++) {
			var $xLi = $('<li>' + monthArr[i] + '</li>');
			$ulX.append($xLi);
		}
		$xNum.html($ulX.html());

		$ulX = null;

		// 鍔ㄦ€佺敓鎴� Y 杞�
		$ulX = $('<ul></ul>');
		var $count = $('#count');
		for (var i = 0; i < count; i++) {
			var $yLi = $('<li>' + seasoneData[i]["catalogNameEn"] + '<br/><span>(' + seasoneData[i]["originNameEn"] + ')</span>' + '</li>');
			$ulX.append($yLi);
		}

		$count.html($ulX.html());
		var colors = [ '#B0706E', '#8C6367', '#CB7673', '#BB657E', '#754D5D', '#AC5855', '#894B60', '#B9646C', '#B0706E', '#764848' ]

		for (var i = 0; i < count; i++) {

			var arrSeason = seasoneData[i].seasonMonthList

			var $li = $('<li></li>');
			var $span = $('<span  class="histogram-box" ></span>');

			for (var j = 1; j < 13; j++) {

				if ($.inArray(j, arrSeason) != -1) {// 瀛樺湪

					var $a = $('<a  style="height:20px;background:' + colors[i] + ';position:absolute;top:10px;left:' + ((j - 1) * 60 + 1) + 'px;" >&nbsp;</a>');
				} else {
					var $a = $('<a  style="height:20px; position:absolute;top:10px;left:' + ((j - 1) * 60 + 1) + 'px;" >&nbsp;</a>');
				}

				$span.append($a);
			}

			$li.append($span);
			$('#bar').append($li);
		}
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
		var index = $('.sel').index();
		var l = $('#showsum').find('span').length
		var allSpan = $('#showsum').find('span');
		var now = $('.sel').index();
		var src = '';
		var $showlast = $('#showlast');
		var $shownext = $('#shownext');

		$showlast.click(function() {

			index = $('.sel').index();

			if (index == 0) {
				$(this).css({
					"background" : "#eee",
					"color" : "#eee"
				});
			} else {
				index--;
				allSpan.eq(index).addClass('sel').siblings().remove('sel');
				src = $('.sel').find('img').attr("src");
				$(this).css({
					"background" : "#fff",
					"color" : "#333"
				});
				$shownext.css({
					"background" : "#fff",
					"color" : "#333"
				});
				$('#pic300').attr({
					"src" : src
				});
			}

		})

		$shownext.click(function() {

			index = $('.sel').index();
			if (index >= l - 4) {
				$(this).css({
					"background" : "#eee",
					"color" : "#eee"
				});
			} else {
				index++;
				allSpan.eq(index).addClass('sel').siblings().remove('sel');
				src = $('.sel').find('img').attr("src");
				$('#pic300').attr({
					"src" : src
				});
				$(this).css({
					"background" : "#fff",
					"color" : "#333"
				});
				$showlast.css({
					"background" : "#fff",
					"color" : "#333"
				});
			}

		})

		if (l > 4) {

			$.each(allSpan, function(i, elem) {

				$(elem).mouseover(function() {

					if (i == 0) {
						$showlast.css({
							"background" : "#eee",
							"color" : "#eee"
						});
					} else if (i == l - 1) {
						$shownext.css({
							"background" : "#eee",
							"color" : "#eee"
						});
					} else {
						$shownext.css({
							"background" : "#fff",
							"color" : "#333"
						});
						$showlast.css({
							"background" : "#fff",
							"color" : "#333"
						});
					}
				});
			});
		} else {
			allSpan.mouseover(function() {
				$shownext.css({
					"background" : "#eee",
					"color" : "#eee"
				});
				$showlast.css({
					"background" : "#eee",
					"color" : "#eee"
				});
			});
		}

	}
	// *************鍥㈤槦鍥剧墖寤惰繜鍔犺浇
	var postponeTeamImgLoad = function() {
		toChange();
		$(window).scroll(function() {
			toChange();
		});
	}
	var toChange = function() {
		$('#section7 img').each(function(i, elem) {
			if ($(elem).offset().top < $(window).height() + $(window).scrollTop()) {
				$(elem).attr('src', $(elem).attr('_src'));
			}
		});
	}

	var show = function(target) {
		var $oFloat = target.find('.float_layer');
		var $oBig = target.find('.big_pic');

		$oBig.find('img').attr('src', $oBig.find('img').attr('_src'));

		$oFloat.show();
		$oBig.show();
	}
	var hide = function(target) {
		var $oFloat = target.find('.float_layer');
		var $oBig = target.find('.big_pic');
		$oFloat.hide();
		$oBig.hide();
	}
	var showMove = function(target, event) {
		var oDiv = target.get(0);
		var oFloat = target.find('.float_layer').get(0);
		var oSmall = target.find('.small_pic').get(0);
		var oMark = target.find('.mark').get(0);
		var oImg = target.find('img').get(1);
		var oBig = target.find('.big_pic').get(0);
		var oEvent = event.originalEvent

		var scrollTop = $('body').scrollTop() || document.documentElement.scrollTop

		var tDis = topHeight - scrollTop;

		var l = oEvent.clientX - oDiv.offsetLeft - oSmall.offsetLeft - oFloat.offsetWidth / 2 - left + 2;
		var t = oEvent.clientY - oDiv.offsetTop - oSmall.offsetTop - oFloat.offsetHeight / 2 - tDis + 2;

		if (l < 0) {
			l = 0;
		} else if (l > oMark.offsetWidth - oFloat.offsetWidth) {
			l = oMark.offsetWidth - oFloat.offsetWidth;
		}
		if (t < 0) {
			t = 0;
		} else if (t > oMark.offsetHeight - oFloat.offsetHeight) {
			t = oMark.offsetHeight - oFloat.offsetHeight;
		}
		oFloat.style.left = l + 'px';
		oFloat.style.top = t + 'px';
		var percentX = l / (oMark.offsetWidth - oFloat.offsetWidth);
		var percentY = t / (oMark.offsetHeight - oFloat.offsetHeight);
		oImg.style.left = -percentX * (oImg.offsetWidth - oBig.offsetWidth) + 'px';
		oImg.style.top = -percentY * (oImg.offsetHeight - oBig.offsetHeight) + 'px';
	}
	var cropFruitPicShow = function() {
		var $wrapDiv = $('.wrapDiv');
		left = $('#shopPicShow').offset().left;
		topHeight = $('#shopPicShow').offset().top;
		$.each($wrapDiv, function(i, elem) {
			var oMark = $(elem).find('.mark');
			var targetObj = $(elem);
			$(elem).mouseover(function() {
				$(this).addClass('active').siblings().removeClass('active');
				show($(this));
			});
			$(elem).mouseout(function() {
				$(this).removeClass('active');
				hide($(this));
			});
			$(elem).mousemove(function(ev) {
				showMove($(this), ev);
			});
		});
	}

	var section4Video = function(linkArr) {

		var len = linkArr.length;

		if (!len) {
			$('#video').hide();
			return false;
		}
		var str='';

	for( var i = 0;i < len ; i++){
		
		if( i == 0 ){
		var $li = '<li class="active"  index="'+i+'"  ><div><a href="javascript:void(0);"><span><svg class="nav__icon"><use xlink:href="#icon-pin"></use></svg></span></a><span class="point">'+(i+1)+'</span></div></li>';
		}else{
			var $li = '<li  index="'+i+'"  ><div><a href="javascript:void(0);"><span><svg class="nav__icon"><use xlink:href="#icon-pin"></use></svg></span></a><span class="point">'+(i+1)+'</span></div></li>'
			
		}
		str += $li;
	}
	
		var slideWidth = 402/len;
		var $videoSlideBtns = $('#videoSlideBtns');
		var $video_mainPanel   = $('.video_mainPanel') ;
		
		$videoSlideBtns.html( str );
		
		$video_mainPanel.css({"margin-left": - ( $video_mainPanel.width() / 2 ) }); 
		var current = 0 ;
		var $js_videoCon_1 =$('#js_videoCon_1');
		var $allLi =$videoSlideBtns.find('li');
		var $next = $('#videoNext');
		var $prev = $('#videoPrev');
		var $timeline = $('#timeline .inside');
		$allLi.css({"width":slideWidth}); 
		$timeline.animate({"width":slideWidth },1200);
	
		$js_videoCon_1.attr({"src":linkArr[0]});  
	
		$allLi.click(function(){
			
			$(this).addClass("active").siblings().removeClass("active");
	
			current = $(this).index();
			$js_videoCon_1.attr({"src":linkArr[current]});  
			
		});
	
		$allLi.click(function(){
	
			$(this).addClass("active").siblings().removeClass("active");
			current = $(this).index();
			$js_videoCon_1.attr({"src":linkArr[current]});
			$timeline.animate({"width":(parseInt(current)+1)*slideWidth },1600);
			$(this).prevAll().find('.point').addClass('green');
			$(this).prevAll().find('a span').addClass('greenBorder');
			$(this).nextAll().find('.point').removeClass('green');
			$(this).nextAll().find('a span').removeClass('greenBorder');
		});
		
		$('#prevVideo').click(function(){
			
			$prev.click();
		});
		
		$('#nextVideo').click(function(){
			
			$next.click();
		});
	
		$prev.click(function(){
			
			var index = $videoSlideBtns.find('.active').index();
			if( index == 0){
				$(this).addClass("active").attr("title","First");
				return false;
			}
			
			current = index - 1;
			
			$(this).add( $next ).removeClass("active").attr("title","video");
			var $active = $allLi .removeClass("active").eq(current).addClass("active");
			$(this).trigger('move',[$js_videoCon_1,linkArr[current],$active,current,slideWidth]);
		});
		
		$next.click(function(){
			
			var index = $videoSlideBtns.find('.active').index();
			if( index == len - 1 ){
				$(this).addClass("active").attr("title","Last");;
				return false;
			}
			current = index + 1;
			$(this).add( $prev ).removeClass("active").attr("title","video");
			var $active =$allLi.removeClass("active").eq(current).addClass("active");
			$(this).trigger('move',[$js_videoCon_1,linkArr[current],$active,current,slideWidth]);
		});
	
		$next.add( $prev).on('move',function(event,$js_videoCon_1,src,$active,current,slideWidth){
			$js_videoCon_1.attr({"src":src});
			$active.prevAll().find('.point').addClass('green');
			$active.prevAll().find('a span').addClass('greenBorder');
			$active.nextAll().find('.point').removeClass('green');
			$active.nextAll().find('a span').removeClass('greenBorder');
			$timeline.animate({"width":(current+1)*slideWidth },1600);
		});

	}

	return Return;
})();

$(function() {
	companyInfo.Init();
})


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
			_boximg.attr("src", _arr[_index][0] + "&width=300");
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

			if (!document.addEventListener) {
				var pic300Height = $('#pic300').height();
				var picTop = (300 - pic300Height) / 2;
				if (pic300Height < 300) {
					$('#pic300').css({
						"margin-top" : picTop
					});
				} else {
					picTop = 0;
					$('#pic300').css({
						"margin-top" : picTop
					});
				}
			} else {
				$('#pic300').load(function() {
					var pic300Height = $('#pic300').height();
					var picTop = (300 - pic300Height) / 2;
					if (pic300Height < 300) {
						$('#pic300').css({
							"margin-top" : picTop
						});
					} else {
						picTop = 0;
						$('#pic300').css({
							"margin-top" : picTop
						});
					}

				});
			}

		};
		imgPlaces();

		_imgout.mousemove(function(e) {
			play(e);
		}); // mouse END

		function play(e) {
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

			var pic300Width = $('#pic300').width();
			var pic300Height = $('#pic300').height();
			var picTop = (300 - pic300Height) / 2;

			if (pic300Height < 300) {
				$('#pic300').css({
					"margin-top" : picTop
				});
			} else {
				picTop = 0;
				$('#pic300').css({
					"margin-top" : picTop
				});
			}

			_showimg.css({
				"left" : _imgX + "px",
				"top" : (_imgY + picTop) + "px",
				"height" : pic300Height * 2,
				"width" : pic300Width * 2,
				"max-width" : "600px",
				"max-height" : "600px"
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

		// 
		var _nextbtn = $("#" + _obj.nextid);
		var _lastbtn = $("#" + _obj.lastid);
		var _moveindex = 0; //

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
				move(_moveindex);
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
				move(_moveindex);

			});
		} // if END

		_sumarr.hover(function() {
			_index = $(this).index();
			move(_index);

		});

		function move(_index) {
			_sumarr.eq(_index).addClass(_obj.sumsel).siblings().removeClass(_obj.sumsel);
			imgPlaces();
			var pic300Width = $('#pic300').width();
			var pic300Height = $('#pic300').height();
			var picTop = (300 - pic300Height) / 2;

			if (pic300Height < 300) {
				$('#pic300').css({
					"margin-top" : picTop
				});
			} else {
				picTop = 0;
				$('#pic300').css({
					"margin-top" : picTop
				});
			}
		}

	} // pcGlasses END
}