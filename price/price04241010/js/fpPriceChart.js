var global = {};


global.seriesDefaultOptions = {
    lang: {
        rangeSelectorZoom: ''
    },
    title: null,
    chart: {
        animation: false,
        spacingBottom: 30,
        zoomType: 'xy',
        ignoreHiddenSeries: true
    },
    tooltip: {
        shared: true,
        shape: 'square',
        valueDecimals: 2,
        backgroundColor: 'white',
        shadow: false,
        split: false,
        borderWidth:1,             // 边框宽度
        xDateFormat :  '%Y-%m-%d',
        useHTML:true,
        headerFormat:null,
        pointFormat: '{series.name}',
        footerFormat: ''

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
    scrollbar: {
        enabled: false
    },
    xAxis: {
        type: 'datetime',
        fontSize: 8,
        gridLineDashStyle: 'longdash',
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
    navigator: {
        adaptToUpdatedData: false,
        outlineWidth: 0,
        enabled: true,
        lineWidth: 0,
        tickWidth: 0,
        height: 48,
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
    rangeSelector: {
        buttonTheme: {
            display: 'none'
        },
        selected:1/2,
        inputEnabled: false
    },
    yAxis: [{
        lineWidth: 0,
        align: 'left',
        color: '#7CB5EC',
        type: 'spline',
        gridLineDashStyle: 'longdash',
        tickmarkPlacement: "on",
        minorGridLineColor: '#fcfcfc',
        opposite: false,
        id: "first",
        title: {
            textAlign: 'right',
            align: 'high',
            offset: 10,
            y: 10,
            fontSize: 8,
            text: '',
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
        type: 'spline',
        minorGridLineColor: '#fcfcfc',
        linkedTo: 0,
        tickmarkPlacement: "on",
        opposite: true,
        id: "firstRight",
        labels: {
            align: 'left',
            x: 8,
            showLastLabel: true,
            showFirstLabel: true
        },
        title: {
            textAlign: 'left',
            align: 'high',
            offset: 8,
            y: 10,
            fontSize: 8,
            text: '',
            rotation: 0
        },
        startOnTick: true,
        endOnTick: true,
        floor: 0
    }],
    gridLineDashStyle: 'longdash',
    plotOptions: {
        series: {
            point: {
                events: {
                    click: function () {
                        var pointer = this;
                        var chart = this.series.chart;
                        var itemPriceId2 = chart.get(this.series.name);
                        var date = Highcharts.dateFormat('%Y/%m/%d', this.x);

                        var timer;
                        clearTimeout(timer);

                        if (this.zone.remark == false) {
                             $(".reporting").hide();
                            return false;
                        }

                        if (chart.renderTo.id == "container") {

                            var BooleanClick = $(pointer).attr("click");

                            if (BooleanClick) {
                                $(pointer).attr({
                                    "click" : false
                                });
                                $('#container').find('.reporting').remove();
                            } else {
                                $(pointer).attr({
                                    "click" : true
                                });
                                $('#container').find('.reporting').remove();
                                $('#container').append('<div class="reporting"><h2>' + date + '&nbsp;' + pointer.series.name + '</h2><div>' + data + '</div><span class="reportClose"> CLOSE</span></div>');
                                $('#container').find('.reporting').css({
                                    "top" : this.plotY - 40 + "px",
                                    "left" :this.plotX + 40 + "px"
                                }).show();
                            }
                        }

                        $('.reportClose').click(function() {
                            $(this).parent().hide();
                        });

                        timer = setTimeout(function() {
                            $(".reporting").hide();
                        }, 100000);

                        return false;
                    }
                }
            }
        }
    },
    animation: false,
    zoneAxis: 'x',
    series: []
};
Highcharts.setOptions(global.seriesDefaultOptions);

global.colors = ['#2f7ed8', '#0d233a', '#8bbc21', '#910000', '#1aadce', '#492970', '#f28f43', '#77a1e5', '#c42525', '#a6c96a', '#4572A7', '#AA4643', '#89A54E', '#80699B', '#3D96AE', '#DB843D', '#92A8CD', '#A47D7C', '#B5CA92', "#7cb5ec", "#434348", "#90ed7d", "#f7a35c", "#8085e9", "#f15c80", "#e4d354", "#2b908f", "#f45b5b", "#91e8e1"];


function makeChart(id, data) {
    var lineArr = [];
    for (var i = 0; i < data.length; i++) {
        var temp = {
            type:"spline",
            name: 'line' + (i + 1),
            data: data[i].data,
            color: global.colors[i],
            marker: {
                enabled: true,
                fillColor: global.colors[i],
                color: global.colors[i],
                lineColor: global.colors[i],
                stroke: global.colors[i],
                lineWidth:2
            },
            yAixs : 1,
            connectNulls : true,
            shadow : false,
            dataGrouping : {
                smoothed : true
            },
            pointInterval : 24 * 3600 * 1000,
            zoneAxis : 'x'

        };
        lineArr.push(temp);
    }
    global.seriesDefaultOptions.series = lineArr;
    if( id == "container"){
        global.chartOne = Highcharts.stockChart(id, global.seriesDefaultOptions);

    }else if(  id == "container2" ){
        global.chartTwo = Highcharts.stockChart(id, global.seriesDefaultOptions);
    }
    generateElement(id); // 生成图例，和 表格项
}
/** 更新图表 线  */
function updateLine(index, LineData, callback ,id) {


    var updata;
    var tempArr = [];
    var remarkLable = [];
    updata = LineData.data;

    for (var i = 0; i < updata.length; i++) {
        var dateStr = updata[i].sDate;
        var dateArr = dateStr.split(' ');
        var arr = new Array(2);
        var subDateArr = dateArr[0].split('-');
        arr[0] = Date.UTC(subDateArr[0], subDateArr[1] - 1, subDateArr[2], 0, 0, 0);
        if (updata[i].avgPrice == -1) {
            arr[1] = null;
        } else {
            arr[1] = updata[i].avgPrice;
        }
        if (updata[i].commentNum > 0) {
            remarkLable.push(true);
        }else{
            remarkLable.push(false);
        }
        tempArr.push(arr);
    }
    var data = tempArr;
    var newData = [];
    var zonesArray = [];

    for (var k = 0; k < data.length; k++) {


        var flag = ( data[k][1] && k != 0 && data[k - 1][1]) ? true:false;


// 有评论的，一定有数据；有数据，不一定有评论
if(data[k][1] && remarkLable[k] ){

        newData.push({
            x: data[k][0],
            y: data[k][1],
            color: 'red',
            marker: {
                enabled: true,
                fillColor: 'red',
                color: 'red',
                lineColor: 'red',
                lineWidth: 4
            }
                    });

            }else{
                    newData.push({
                        x: data[k][0],
                        y: data[k][1],
                        color: global.colors[index]
                    });
            }

        if (flag) {  // 有价格的连续

            zonesArray.push(
                {
                    value: data[k][0]+1,       dashStyle: "solid",
                    stroke: global.colors[index],
                    remark: remarkLable[k]
                }
            );

        } else {
            zonesArray.push(
                {
                    value: data[k][0]+1,
                    dashStyle: "dot",
                    remark: remarkLable[k]
                }
            );

        }

    }

    if( id == "container"){
        global.chartOne.series[index].update({
            data: newData,
            zones:  zonesArray
        });
        global.chartOne.yAxis[0].update({
            title:{text:"RMB"}
        });
        global.chartOne.yAxis[1].update({
            title:{text:"RMB"}
        });

    }else if( id == "container2"){
        global.chartTwo.series[index].update({
            data: newData,
            zones:  zonesArray
        });
        global.chartTwo.yAxis[0].update({
            title:{text:"RMB/KG"}
        });
        global.chartTwo.yAxis[1].update({
            title:{text:"RMB/KG"}
        });

    }


    if (callback)
        callback()
}

/** 复制一份 图例 1*/
function generateElement(id) {
    global.chartOne.legend.update({
        enabled: true
    });
    var allsvg = [];
    var all = global.chartOne.legend.getAllItems();
    var colors = [];
    for (var i = 0; i < all.length; i++) {
        allsvg.push(all[i].legendGroup.element);
        colors.push(all[i].color);
    }
    var str2 = $('<tbody>');
    var str = $('<div>');
    var num = 0;
    for (i = 0; i < allsvg.length; i++) {
        allsvg[i] = $(allsvg[i]).attr("fill", colors[i]);
        var svg = getSVG(allsvg[i], colors[i]).clone();
        num++;
        var $btnDiv = $('<div class="labelItem"></div>');
        $btnDiv.appendTo(str); // 第一次
        svg.appendTo($btnDiv); // 第一次
        var svg2 = svg.clone();
        var trHtml = '<tr class="odd"  lineId = ' + all[i].index + '><td class="selectOption"> <a class="select"><svg class="icon" style="width: 16; height:16;vertical-align: middle;fill: #8bc34a;overflow: hidden;" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1660"><path d="M196.923077 0h630.153846a196.923077 196.923077 0 0 1 196.923077 196.923077v630.153846a196.923077 196.923077 0 0 1-196.923077 196.923077H196.923077a196.923077 196.923077 0 0 1-196.923077-196.923077V196.923077a196.923077 196.923077 0 0 1 196.923077-196.923077z m0 78.769231a118.153846 118.153846 0 0 0-118.153846 118.153846v630.153846a118.153846 118.153846 0 0 0 118.153846 118.153846h630.153846a118.153846 118.153846 0 0 0 118.153846-118.153846V196.923077a118.153846 118.153846 0 0 0-118.153846-118.153846H196.923077z m584.900923 258.205538a36.509538 36.509538 0 0 1 1.260308 51.633231l-299.480616 313.107692c-0.118154 0.157538-0.393846 0.236308-0.630154 0.472616l-0.393846 0.551384c-2.166154 2.126769-4.726154 3.229538-7.207384 4.726154-1.575385 0.866462-2.796308 2.166154-4.411077 2.835692a35.800615 35.800615 0 0 1-27.490462 0.07877c-1.260308-0.512-2.284308-1.614769-3.544615-2.284308-2.756923-1.457231-5.592615-2.835692-8.034462-5.12-0.196923-0.157538-0.275692-0.433231-0.512-0.669538-0.196923-0.118154-0.393846-0.196923-0.551384-0.354462l-150.843077-156.593231a36.430769 36.430769 0 0 1 0.945231-51.633231 36.391385 36.391385 0 0 1 51.63323 0.945231l124.455385 129.102769 273.092923-285.61723a36.548923 36.548923 0 0 1 51.712-1.181539z" p-id="1661"></path></svg></a> <a class="unselect"><svg class="icon" style="width: 16; height: 16;vertical-align: middle;fill: #8bc34a;overflow: hidden;" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1610"><path d="M196.923077 78.769231a118.153846 118.153846 0 0 0-118.153846 118.153846v630.153846a118.153846 118.153846 0 0 0 118.153846 118.153846h630.153846a118.153846 118.153846 0 0 0 118.153846-118.153846V196.923077a118.153846 118.153846 0 0 0-118.153846-118.153846H196.923077z m0-78.769231h630.153846a196.923077 196.923077 0 0 1 196.923077 196.923077v630.153846a196.923077 196.923077 0 0 1-196.923077 196.923077H196.923077a196.923077 196.923077 0 0 1-196.923077-196.923077V196.923077a196.923077 196.923077 0 0 1 196.923077-196.923077z" p-id="1611"></path></svg></a>  </td><td class="name"><span>' + num + '</span></td><td class="legend" ></td><td class="detail">USA,14mm+,sea</td><td class="marky">市场</td></tr>';
        var $tr = $(trHtml);
        svg2.appendTo($tr.find('.legend'));
        $tr.appendTo(str2); // 第2次
    }
    if (id == "container") {
        $('#tbody').empty().append(str2.html());
        $('#chartLabel').eq(0).empty().append(str.html());
    } else {
        $('#tbody2').empty().append(str2.html());
        $('#chartLabel2').eq(0).empty().append(str.html());
    }
}
/** 复制一份 图例 2*/
var getSVG = function (h, color) {
    var $str = $('<svg class="my"   xmlns="http:///www.w3.org/2000/svg"  version="1.1" fill=' + color + ' style="font-family:sans-serif;font-size:12px;height:18;vertical-align:middle;width:80;" ><rect  rx="0" ry="0" ry="0" visibility="visible"></rect></svg>');
    $(h).appendTo($str);
    $str.find('g').attr("transform", '');
    return $str
}