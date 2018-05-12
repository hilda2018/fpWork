
$(function() {
    picShow(picInfo)
});
var picInfo = [{
    "img": staticRoot + "home/images/bg1.jpg" + staticVersion,
    "title": "",
    "url": rootPath + "/priceImport/show",
    "extend": "<img src='" + staticRoot + "home/images/price.png" + staticVersion + "'/>",
    "imgInfo": staticRoot + "home/images/bg1_img.png"+ staticVersion,
    "flag": 1
}, {
    "img": staticRoot + "home/images/bg2.jpg" + staticVersion,
    "title": "",
    "url": rootPath + "/arrival/show",
    "extend": "",
    "imgInfo": staticRoot + "home/images/bg2_img.png"+ staticVersion,
    "flag": 1
}, {
    "img": staticRoot + "home/images/bg3.jpg" + staticVersion,
    "title": "",
    "url": rootPath + "/demand/list",
    "extend": "",
    "imgInfo": staticRoot + "home/images/bg3_img.png"+ staticVersion,
    "flag": 1
}, {
    "img": staticRoot + "home/images/bg4.jpg" + staticVersion,
    "title": "",
    "url": "#indexNews",
    "extend": "",
    "imgInfo": staticRoot + "home/images/bg4_img.png"+ staticVersion,
    "flag": 1
}, {
    "img": staticRoot + "home/images/bg5.jpg" + staticVersion,
    "title": "",
    "url": staticPrefix + "/html/signUp/signUp.html",
    "extend": "",
    "imgInfo": staticRoot + "home/images/bg5_img.png"+ staticVersion,
    "flag": 1
}];
function picShow(picInfo) {
    if (!picInfo) {
        return false
    }
    genelatePic(picInfo)
}
var $picShow = $('#picShow');
var $smallNav = $('#smallNav');
var picData = $picShow.get(0);

function genelatePic(picInfo) {
    var str1 = '',
        str2 = '';
    $picShow.add($smallNav).html('');
    for (var i = 0; i < picInfo.length; i++) {
        str1 += '<li class="picLi" style="background-image:url(' + picInfo[i].img + ') ; background-size:cover;"  ><a class="wrap"  href="'+picInfo[i].url +'"><span class="bgText"> </span><span class="imgCustom"> <img src=' + picInfo[i].imgInfo + '/><i></i></span> </a></li>';

        if (i == 0) {
            str2 += '<li class="bg"></li>'
        } else {
            str2 += '<li></li>'
        }
    }
    $picShow.html(str1);
    $smallNav.html(str2);
    picData.complete = true;
    if (!picData.complete) {
        return false
    }
    picData.len = picInfo.length;
    picData.num = -1;
/*    autoPic();*/
    $('#boxPrev').click(function() {
        clearInterval(picData.time);
        picData.num = $("#smallNav .bg").index();
        if (picData.num == 0) {
            picData.num = picData.len - 1
        } else {
            picData.num = picData.num - 1
        }
        changePic(picData.num)
    });
    $('#boxNext').click(function() {
        clearInterval(picData.time);
        picData.num = $("#smallNav .bg").index();
        picData.num = (picData.num + 1) % picData.len;
        changePic(picData.num)
    });
    $("#smallNav li").each(function(i, elem) {
        $('#picShow li').eq(i).attr("id", "pic" + i);
        $(elem).click(function() {
            var index = $(this).index();
            changePic(index)
        })
    })
}
function scrollPic() {
    $("#smallNav li").hover(function() {
        clearInterval(picData.time)
    }, function() {
        autoPic()
    })
}
function autoPic() {
    picData.time = setInterval(function() {
        junmper()
    }, 6000)
}
function junmper() {
    picData.num++;
    if (picData.num > picData.len) {
        picData.num = 0
    }
    changePic(picData.num)
}
function changePic(index) {
    var $target = $(".nav ul li").eq(index);
    var $target2 = $(".pic ul li").eq(index);
    $target.addClass("bg").siblings().removeClass("bg");
    $target2.fadeIn().siblings().hide()
}