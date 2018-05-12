/**
 * Created by HP on 2018/4/28.
 */
$(function() {
    // 轮播图
    picShow(picInfo);

});
var picInfo=[{
    "img":staticRoot+"home/images/bg1.jpg"+staticVersion,
    "title":"<svg  viewBox='0 0 100 50' ><use xmlns:xlink='http://www.w3.org/1999/xlink' xlink:href='#banner1Text'></use></svg>",
    "url":rootPath+"/priceImport/show",
    "extend":"<img src='"+staticRoot+"home/images/price.png"+staticVersion+"'/>",
    "flag":1
},{
    "img":staticRoot+"home/images/bg2.jpg"+staticVersion,
    "title":"<svg  viewBox='0 0 100 50' ><use xmlns:xlink='http://www.w3.org/1999/xlink' xlink:href='#banner4Text'></use></svg>",
    "url":rootPath+"/arrival/show",
    "extend":"",
    "flag":1
},{
    "img":staticRoot+"home/images/bg3.jpg"+staticVersion,
    "title":"<svg  viewBox='0 0 100 50' ><use xmlns:xlink='http://www.w3.org/1999/xlink' xlink:href='#banner3Text'></use></svg>",
    "url":rootPath+"/demand/list",
    "extend":"",
    "flag":1
},{
    "img":staticRoot+"home/images/bg4.jpg"+staticVersion,
    "title":"<svg  viewBox='0 0 100 50' ><use xmlns:xlink='http://www.w3.org/1999/xlink' xlink:href='#banner2Text'></use></svg>",
    "url":"#indexNews",//资讯列表
    "extend":"",
    "flag":1
},{
    "img":staticRoot+"home/images/bg5.jpg"+staticVersion,
    "title":"<svg  viewBox='0 0 100 50' ><use xmlns:xlink='http://www.w3.org/1999/xlink' xlink:href='#banner5Text'></use></svg>",
    "url":staticPrefix+"/html/signUp/signUp.html",
    "extend":"",
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
            str1 += '<li class="picLi" style="background:url('+ picInfo[i].img +') no-repeat center center;background-size:cover;"></li>';
        }else if (picInfo[i].flag == 1){
            str1 += '<li class="picLi" style="background:url('+ picInfo[i].img +') no-repeat center center;background-size:cover;">'
                +'<a class="wrap"  href='+ picInfo[i].url +' target="_blank" >'
                +'<div class="imgCustom">'
                + picInfo[i].extend
                +'</div>'
                +'<div class="title" >'
                + picInfo[i].title
                +'</div>'
                +'</a>'
                +'</li>';
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

function goToByUrl(url){
    if (url.indexOf("#") == -1){
        window.open(url);
    }else{
        window.location.href=url;
    }
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
    $target2.fadeIn().siblings().hide();


}