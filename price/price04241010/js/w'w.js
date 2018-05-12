


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

    for (var  = 0; i < allsvg.length; i++) {

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




/**
 * Created by HP on 2018/4/22.
 */
