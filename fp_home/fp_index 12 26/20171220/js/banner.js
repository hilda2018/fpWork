$(function() {
    picShow(picInfo)
});
var picInfo = [{
    "img": "images/bg1.jpg",
    "title": "China Imported Fruits Market Price ",
    "url": "",
    "detail": "Guidance before quoting ",
    "imgInfo": "images/bg1_img.png"
}, {
    "img": "images/bg2.jpg",
    "title": "Global Fruits Arrival Data  ",
    "url": "",
    "detail": "Guidance before delivery",
    "imgInfo": "images/bg2_img.png"
}, {
    "img": "images/bg3.jpg",
    "title": "Post selling and buying Information",
    "url": "",
    "detail": "Attract new business conversation",
    "imgInfo": "images/bg3_img.png"
}, {
    "img": "images/bg4.jpg",
    "title": "Industrial News and Announcements",
    "url": "",
    "detail": "Industry news <br/>Business Promotion Campaign<br/>Intergrated Knowledge About Importing Fruits",
    "imgInfo": "images/bg4_img.png"
}, {
    "img": "images/bg5.jpg",
    "title": "Company Profile Display, Registration Free to create and edit ",
    "url": "",
    "detail": "More Business Opportunites",
    "imgInfo": "images/bg5_img.png"
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
        str1 += '<li class="picLi" style="background-image:url(' + picInfo[i].img + ') ; background-size:cover;"><a class="wrap"  href=\'+picInfo[i].url +\'><span class="bgText"> </span><span class="imgCustom">';

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
    scrollPic();
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