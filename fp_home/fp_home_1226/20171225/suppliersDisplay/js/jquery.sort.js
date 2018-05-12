
/*定义三级分类数据*/
//一级分类
var province = ["美国1", "美国11", "美国111", "美国331", "美国441", "美国551", "美国661", "美国166"];
//二级分类
var city = [
	["辉展市场", "江南市场", "嘉兴市场"],
	["辉展市场", "江南市场", "嘉兴市场"],
	["辉展市场", "江南市场", "嘉兴市场"],
	["辉展市场", "江南市场", "嘉兴市场"],
	["辉展市场", "江南市场", "嘉兴市场"],
	["辉展市场", "江南市场", "嘉兴市场"],
	["辉展市场", "江南市场", "嘉兴市场"],
	["辉展市场", "江南市场", "嘉兴市场"]
];
//三级分类
var district = [

	[
		["金果1", "金果1", "金果1"],
		["金果1", "金果1", "金果1"],
		["金果1", "金果1", "金果1", "金果1", "金果1"]
	],

	[
		["金果1", "金果1", "金果1"],
		["金果1", "金果1", "金果1"],
		["金果1", "金果1", "金果1"],
		["金果1", "金果1", "金果1","金果1", "金果1"],
		["金果1", "金果1", "金果1","金果1", "金果1"]
	],

	[
		["金果1", "金果1", "金果1"],
		["金果1", "金果1", "金果1","金果1", "金果1"],
		["金果1", "金果1", "金果1","金果1", "金果1"]
	],

	[
		["金果1", "金果1", "金果1"],
		["金果1", "金果1", "金果1"],
		["金果1", "金果1", "金果1"],
		["金果1", "金果1", "金果1","金果1", "金果1"],
		["金果1", "金果1", "金果1","金果1", "金果1"],
		["金果1", "金果1", "金果1"],
		["金果1", "金果1", "金果1","金果1", "金果1"],
		["金果1", "金果1", "金果1","金果1", "金果1"]
	],

	[
		["金果1", "金果1", "金果1"],
		["金果1", "金果1", "金果1","金果1", "金果1"],
		["金果1", "金果1", "金果1","金果1", "金果1"],
		["金果1", "金果1", "金果1"],
		["金果1", "金果1", "金果1","金果1", "金果1"],
		["金果1", "金果1", "金果1","金果1", "金果1"]
	],

	[
		["金果1", "金果1", "金果1"],
		["金果1", "金果1", "金果1","金果1", "金果1"],
		["金果1", "金果1", "金果1","金果1", "金果1"],
		["金果1", "金果1", "金果1"],
		["金果1", "金果1", "金果1","金果1", "金果1"],
		["金果1", "金果1", "金果1","金果1", "金果1"]
	],

	[
		["金果1", "金果1", "金果1","金果1", "金果1"],
		["金果1", "金果1", "金果1","金果1", "金果1"],
		["金果1", "金果1", "金果1"],
		["金果1", "金果1", "金果1","金果1", "金果1"]
	],

	[
		["金果1", "金果1", "金果1"],
		["金果1", "金果1"],
		["金果1", "金果1", "金果1","金果1", "金果1"],
		["金果1", "金果1", "金果1","金果1", "金果1"],
		["金果1", "金果1", "金果1","金果1", "金果1"],
		["金果1", "金果1", "金果1"],
		["金果1", "金果1", "金果1","金果1", "金果1"],
		["金果1", "金果1", "金果1","金果1", "金果1"]
	]
];
var expressP, expressC, expressD, expressArea, areaCont;
var arrow = " <font>&gt;</font> ";

/*初始化一级目录*/
function intProvince() {
	areaCont = "";
	for (var i=0; i<province.length; i++) {
		areaCont += '<li onClick="selectP(' + i + ');"><a href="javascript:void(0)">' + province[i] + '</a></li>';
	}
	$("#sort1").html(areaCont);
}
intProvince();

/*选择一级目录*/
function selectP(p) {
	areaCont = "";
	for (var j=0; j<city[p].length; j++) {
		areaCont += '<li onClick="selectC(' + p + ',' + j + ');"><a href="javascript:void(0)">' + city[p][j] + '</a></li>';
	}
	$("#sort2").html(areaCont).show();
	$("#sort3").hide();
	$("#sort1 li").eq(p).addClass("active").siblings("li").removeClass("active");
	expressP = province[p];
	$("#selectedSort").html(expressP);
	$("#releaseBtn").removeAttr("disabled");
}

/*选择二级目录*/
function selectC(p,c) {
	areaCont = "";
	expressC = "";
	for (var k=0; k<district[p][c].length; k++) {
		areaCont += '<li onClick="selectD(' + p + ',' + c + ',' + k + ');"><a href="javascript:void(0)">' + district[p][c][k] + '</a></li>';
	}
	$("#sort3").html(areaCont).show();
	$("#sort2 li").eq(c).addClass("active").siblings("li").removeClass("active");
	expressC = expressP + arrow + city[p][c];
	$("#selectedSort").html(expressC);
}

/*选择三级目录*/
function selectD(p,c,d) {
	$("#sort3 li").eq(d).addClass("active").siblings("li").removeClass("active");
	expressD = expressC + arrow + district[p][c][d];
	$("#selectedSort").html(expressD);
}

/*点击下一步*/
$("#releaseBtn").click(function() {
	var releaseS = $(this).prop("disabled");
	if (releaseS == false) {//未被禁用
		//location.href = "商品发布-详细信息.html";//跳转到下一页
	}
});
