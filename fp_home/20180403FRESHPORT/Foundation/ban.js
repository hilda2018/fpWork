

/**
 * Created by HP on 2018/4/27.
 */
$(function() {
    // 轮播图
    picShow(picInfo);

});
//var picInfo=[
//	{
//		"img":staticRoot+"home/images/bg6.jpg"+staticVersion,
//		"title":"",
//		"url":"#",
//		"detail":"",
//		"buttonInfo":"",
//		"flag":0
//},
////{
////	"img":staticRoot+"home/images/bg1.jpg"+staticVersion,
////	"title":"China Imported Fruits Market Price ",
////	"url":rootPath+"/price/show",
////	"detail":"Guidance before quoting ",
////	"buttonInfo":"View More",
////	"flag":1
////},
//{
//	"img":staticRoot+"home/images/bg2.jpg"+staticVersion,
//	"title":"Global Fruits Arrival Data  ",
//	"url":rootPath+"/arrival/show",
//	//"url":staticPrefix+"/html/peru/index.html",
//	"detail":"Guidance before delivery",
//	"buttonInfo":"View More",
//	"flag":1
//},{
//	"img":staticRoot+"home/images/bg3.jpg"+staticVersion,
//	"title":"Post selling and buying Information",
//	"url":rootPath+"/demand/list",
//	"detail":"Attract new business conversation",
//	"buttonInfo":"View More",
//	"flag":1
//},{
//	"img":staticRoot+"home/images/bg4.jpg"+staticVersion,
//	"title":"Industrial News and Announcements",
//	"url":"#indexNews",//资讯列表
//	"detail":"Industry news <br/>Business Promotion Campaign<br/>Intergrated Knowledge About Importing Fruits",
//	"buttonInfo":"View More",
//	"flag":1
//},{
//	"img":staticRoot+"home/images/bg5.jpg"+staticVersion,
//	"title":"Company Profile Display, Registration Free to create and edit ",
//	"url":staticPrefix+"/html/signUp/signUp.html",
//	"detail":"More business opportunities",
//	"buttonInfo":"View More",
//	"flag":1
//}];
var picInfo=[{
    "img":staticRoot+"home/images/bg1.jpg"+staticVersion,
    "title":"CHINA IMPORTED FRUITS MARKET PRICE",
    "url":rootPath+"/price/show",
    "detail":"Guidance Before Quoting",
    "buttonInfo":"View More",
    "flag":1
},{
    "img":staticRoot+"home/images/bg2.jpg"+staticVersion,
    "title":"Global Fruits Arrival Data  ",
    "url":rootPath+"/arrival/show",
    "detail":"Guidance before delivery",
    "buttonInfo":"View More",
    "flag":1
},{
    "img":staticRoot+"home/images/bg3.jpg"+staticVersion,
    "title":"Post selling<br/><span> & buying Information </span>",
    "url":rootPath+"/demand/list",
    "detail":"Attract new business conversation",
    "buttonInfo":"View More",
    "flag":1
},{
    "img":staticRoot+"home/images/bg4.jpg"+staticVersion,
    "title":"Industrial News  <br/> & Announcements",
    "url":"#indexNews",//资讯列表
    "detail":"Industry news Business Promotion Campaign<br/>Intergrated Knowledge About Importing Fruits",
    "buttonInfo":"View More",
    "flag":1
},{
    "img":staticRoot+"home/images/bg5.jpg"+staticVersion,
    "title":"Company Profile Display, Registration Free to create and edit ",
    "url":staticPrefix+"/html/signUp/signUp.html",
    "detail":"More Business Opportunites",
    "buttonInfo":"View More",
    "flag":1
}];

function picShow(picInfo) {
    if (!picInfo) {
        return false;
    }
    genelatePic(picInfo);
}

var $picShow = $('#picShow');
var $smallNav = $('#smallNav');
var picData = $picShow.get(0);

function genelatePic(picInfo) {
    var str1 = '', str2 = '';
    $picShow.add($smallNav).html('');
    for (var i = 0; i < picInfo.length; i++) {
        if(picInfo[i].flag == 0){

            str1 += '<li class="picLi" style="background:url('
                + picInfo[i].img
                + ') no-repeat center center;background-size:cover;"></li>';

        }else if (picInfo[i].flag == 1){
            str1 += '<li class="picLi" style="background:url('
                + picInfo[i].img
                + ') no-repeat center center;background-size:cover;"><div class="wrap"><div class="title"><h2>'
                + picInfo[i].title
                + '</h2><hr class="line"/><div class="moreDetail">';
            if(picInfo[i].url.indexOf("#") == -1){
                str1 += '<a class="smalltext" target="_blank" href='
                    + picInfo[i].url
                    + '><span class="pictext2">'
                    + picInfo[i].detail
                    + '</span><span class="picArrow"><em>'
                    + picInfo[i].buttonInfo
                    + '</em><svg><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#svg_arrow"></use></svg></span></a></div></div></div></li>';
            }else{// 首页锚点
                str1 += '<a class="smalltext" href='
                    + picInfo[i].url
                    + '><span class="pictext2">'
                    + picInfo[i].detail
                    + '</span><span class="picArrow"><em>'
                    + picInfo[i].buttonInfo
                    + '</em><svg><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#svg_arrow"></use></svg></span></a></div></div></div></li>';
            }
        }
        if (i == 0) {
            str2 += '<li class="bg"></li>';
        } else {
            str2 += '<li></li>';
        }
    }

    $picShow.html(str1);
    $smallNav.html(str2);
    picData.complete = true;
    if (!picData.complete) {
        return false;
    }

    picData.len = picInfo.length;
    picData.num = -1;

    scrollPic();

    $('#boxPrev').click(function() {
        clearInterval(picData.time);
        picData.num = $("#smallNav .bg").index();
        if (picData.num == 0) {
            picData.num = picData.len - 1;
        } else {
            picData.num = picData.num - 1;
        }
        changePic(picData.num);
    });
    $('#boxNext').click(function() {
        clearInterval(picData.time);
        picData.num = $("#smallNav .bg").index();
        picData.num = (picData.num + 1) % picData.len;
        changePic(picData.num);
    });
}

function scrollPic() {

    autoPic();

    $("#smallNav li").each(function(i, elem) {

        $('#picShow li').eq(i).attr("id", "pic" + i);
        $(elem).click(function() {
            var index = $(this).index();
            changePic(index);
        });
    });

    $("#smallNav li").hover(function() {
        clearInterval(picData.time);
    }, function() {
        autoPic();
    })
}

function autoPic() {
    picData.time = setInterval(function() {
        junmper()
    }, 6000);
}

function junmper() {
    picData.num++;
    if (picData.num > picData.len) {
        picData.num = 0;
    }

    changePic(picData.num);

}

function changePic(index) {

    var $target = $(".nav ul li").eq(index);
    var $target2 = $(".pic ul li").eq(index);
    $target.addClass("bg").siblings().removeClass("bg");
    //$target2.fadeIn().siblings().fadeOut();
    $target2.fadeIn().siblings().hide();

//	if ($target2.attr('custom')) {// 是否自定义
//		if ($target2.find(".title").length == 0) {
//			return false;
//		}
//
//		var customLeft = $target2.attr('customLeft')
//		$target2.find(".title").animate({
//			display : "none",
//			left : "-1980px"
//		});
//
//		if ($target2.find(".img2").length != 0) {
//			$target2.find(".img2").animate({
//				display : "none",
//				left : "-15px"
//			});
//		}
//
//		$target2.find(".title").animate({
//			left : customLeft1
//		}, 500, function() {
//
//			if ($target2.find(".img2").length == 0) {
//				return false;
//			}
//
//			var customLeft2 = $target2.attr('customLeft2');
//			var marginLeft = $target2.attr('marginLeft');
//			$target2.find(".img2").css({
//				display : "block"
//			}).animate({
//				left : customLeft1,
//				"marginLeft" : marginLeft
//			}, 500);
//
//		});
//
//	}

}