$(document).ready(function(){
	initMenu({containerId:"menuContainerId"});
	initContent({containerId:"contentContainerId"});
	initHeader({containerId:"headerContainerId"});
	

	//选中		
	$('#contentContainerId .tabs').delegate('li','click',function(){
		var index =  $(this).index();
		var nodeId= $('#contentContainerId .tabs-panels').find('.panel').eq(index).find('.panel-body').attr("mycustom") ;
		$('.tree-node').removeClass('tree-node-selected');
		$('[node-id = '+ nodeId +']').addClass('tree-node-selected');
	
	});


});

//当前打开的标签数
var tabLen = 0;
var urlForMap='http://121.199.1.213:8088';
//var urlForMap='http://localhost:8088';
/*var urlMap = {'inventory':urlForMap+'/inventory/',
			'foreignprocurement':urlForMap+'/foreignprocurement/',
			'foreignprocurement_pay':urlForMap+'/foreignprocurement_pay/',
			'sales_management':urlForMap+'/sales_management/',
			'AppManager':urlForMap+'/AppManager/'};  */

var urlMap = {'inventory':inventoryPath,
		'foreignprocurement':basePath,
		'foreignprocurement_pay':payPath,
		'sales_management':salesPath,
		'AppManager':appmanagerPath,
		'purchase':purchasePath,
		'logistic':logisticPath,
		'info':infoPath};  
//最多打开的标签数
var maxTabNum = 8;
var prevNode = null
function initMenu(p){
	//设置样式
	
	var $menu=$(".panel-header .panel-icon");
	
	
	// panel-header 的设置   样式控制  菜单图标
	$(".layout-panel-west").css({"border-right-color":"#BFBFBF"});
	$(".panel-header").css({"background":"#e3e3e3","color":"#000","border-color":"#BFBFBF"});
	$(".panel-header").css({"font-size":"14px"});
	$(".panel-header .panel-title").css({"color":"#000"});
	$(".panel-header .panel-tool").css({"float":"right"});
	$menu.addClass('iconfont').html('&#xe6da;');
	$menu.css({
		"line-height":"28px",
		"height":"28px",
		"width":"22px",
		"display":"inline-block",
		"padding-left":"2px",
		"font-size":"10px!important"
	});

//        serverAccess.request({
//            serviceName:"user",
//            funcName:"getMenu",
//            args:{requestParam:null}
//        });

	try{
		/*var serviceName="";
		serverAccess.request({
			serviceName:"account!getServiceNameByUser",
			//funcName:"getMenu",
			args:{requestParam:cmnPcr.jsonToStr({})},
			successFunc:function(obj){
				//alert(obj.result.menuItems);
				initMenuItems(obj.result.menuItems);
			},
			failFunc:function(obj){
				msgBox.alert({title:"提示",info:obj.message});
			},
			waitingBarParentId:p.containerId
		});*/
		serverAccess.request({
			serviceName:"account!getMenu",
			//funcName:"getMenu",
			args:{requestParam:cmnPcr.jsonToStr({})},
			successFunc:function(obj){
				//alert(obj.result.menuItems);
				initMenuItems(obj.result.menuItems);
			},
			failFunc:function(obj){
				msgBox.alert({title:"提示",info:obj.message});
			},
			waitingBarParentId:p.containerId
		});
	}catch(e){
		alert('error:'+e.message);
	}
	


	var initMenuItems = function(menuItems){
		$("#" + p.containerId).accordion({
			animate:false,
			fit:true,
			border:false,
			onSelect:function(title,index){
				
				
				var menuItem = menuItems[index];
		        //alert(2);
				if(!menuItem.hasInit){
					var divId = p.containerId + "_" + index;
					setTimeout(function(){
						$("#" + divId).tree({
							data:menuItem.children,
							//单击打开菜单，放弃onDblClick
							onClick:function(node){  //console.log(node.target.style.background="red");
								prevNode =node.id;
								//执行菜单项表达式
								if(node.attributes != undefined && node.attributes.actionexp != undefined){
									iocClient.execExp(node.attributes.actionexp);
									console.log(node.attributes.actionexp);
								}
								
								
								if( $(node.target.firstElementChild).hasClass('tree-expanded')|| $(node.target.firstElementChild).hasClass('tree-collapsed') ){
									$(node.target.firstElementChild).trigger('click');
									
								}
								
							
							},
							onLoadSuccess:function(node, data){
								//设置每个菜单项的图标 hilda  二级菜单
								var setNodeIcon = function(nodeData){
									var nodeObj = $("#" + divId).find("div[node-id='"+nodeData.id+"']")[0];
									var icon = nodeData.attributes.icon;
									
									var $childMenu=$(nodeObj).children(".tree-icon");
									var $icon=$childMenu.append('<i class="iconfont">&#xe658;</i>');
									$icon.css({
							
										"line-height":"16px",
										"font-size":"10px"
										
									});
							
								
									if(icon != undefined){
										
										//需要使用，利用数据库
										//$(nodeObj).children(".tree-icon").addClass(icon).css({"line-height":"18px"});
										/*.css({backgroundImage:"url('"+baseImages+"/menu/"+icon+".png')",
										backgroundPositionX:"0px",
										backgroundPositionY:"0px"});*/
									}

									if(nodeData.children != null){
										for(var i=0;i<nodeData.children.length;i++){
											setNodeIcon(nodeData.children[i]);
										}
									}
								}
								if(data!=null){
									for(var i=0;i<data.length;i++){
										setNodeIcon(data[i]);
									}
								}
							}
						});
						//默认折叠全部菜单，可删除
						$("#" + divId).tree('collapseAll');
					},10);
					menuItem.hasInit = true;
				}
			}
		});
		
		var arrIcon=[];
		for(var i=0;i<menuItems.length;i++){
			var menuItem = menuItems[i];
			var divId = p.containerId + "_" + i;
			var treeContainerHtml = "<ul id=\""+divId+"\" style=\"width:100%;overflow:hidden;height:100%;\"></ul>";
			$("#" + p.containerId).accordion("add", {title:menuItem.text,
				content:treeContainerHtml,
				selected: (i == 0),
				iconCls:"icon-"+menuItem.attributes.icon,
				height:400
				
				});
			
	
		}



		$(".panel-body .accordion-body").css({"height":"auto","padding":"5px 0"});
		
		
	}
}

function initContent(p){
	//alert(p.containerId);
	$("#" + p.containerId).tabs({
		border:false,
		plain:true,
		fit:true,
		onSelect:function(title){
		
		},
		onClose:function(title,index){
			if(tabLen > 0){
	    	  tabLen --;
	      	}
		},
		tools:[{
			iconCls:'icon-reload',
			title:' reflesh ',
			handler:function(){
				
				var pp = $("#" + p.containerId).tabs('getSelected');   
				//console.dir(pp);
			
				var href=$(pp).find('iframe').attr('src');
				console.dir(href);
				$(pp).find('iframe').attr('src',href);
			
			}
		},{
			iconCls:'icon-search',
			title:' callapse ',
			handler:function(){
				
				callpse();
			
			}
		}]
		});
	
	iocClient.addEntity("mainPageTab", {
		//盘点tab是否存在
		exist : function(id){
			var allTabs = $("#" + p.containerId).tabs("tabs");
			for(var i=0;i<allTabs.length;i++){
				if($(allTabs[i]).attr("id") == id){
					return true;
				}
			}
			return false;
		},

		//选中此tab
		select:function(id){
			var allTabs = $("#" + p.containerId).tabs("tabs");
			for(var i=0;i<allTabs.length;i++){
				if($(allTabs[i]).attr("id") == id){
					var index = $("#" + p.containerId).tabs("getTabIndex", allTabs[i]);
					$("#" + p.containerId).tabs("select", index);
				}
			}

		},

		//显示tab
		showContent:function(id, name, content, closable){	
		
			if(this.exist(id)){
				this.select(id);
			}
			else{
				$("#" + p.containerId).tabs("add",{
					id:id,
					title:name,
					content:content,
					closable: closable == undefined ? true : closable
				});
				var tab = $("#" + p.containerId).tabs('getSelected');
				$(tab).attr("id", id);
				$(tab).attr("myCustom", prevNode);
		
			}
		},
		//获得弹出的tab中frameid
		getFrameId:function(id){
			var allTabs = $("#" + p.containerId).tabs("tabs");
			for(var i=0;i<allTabs.length;i++){
				if($(allTabs[i]).attr("id") == id){
					return $(allTabs[i]).attr("iframeId");
				}
			}
		},
		//在tab页面中弹出其它链接 
		addPage:function(id, name, url, closable){
			if(this.exist(id)){
				this.select(id);
			}
			else{
				var iframeId = cmnPcr.getRandomValue();
				var innerHtml = "<div style=\"width:100%;height:100%;overflow:hidden;\"><iframe id=\"" + iframeId + "\" style=\"width:100%;height:100%;border:0px;\" frameborder=\"0\" /></div>";
				$("#" + p.containerId).tabs("add",{
					id:id,
					title:name,
					content:innerHtml,
					closable: closable == undefined ? true : closable
				});
				var baseurl = url;
				if(baseurl.lastIndexOf('?')==baseurl.indexOf('?')){
					baseurl = baseurl + "?iframeId=" + iframeId;
				}else{
					baseurl = baseurl + "&iframeId=" + iframeId;
				}
				$("#" + iframeId).attr("src", baseurl);
				var tab = $("#" + p.containerId).tabs('getSelected');
				$(tab).attr("id", id);
				$(tab).attr("myCustom", prevNode);
			}
		},
		//在tab页面中弹出其它链接 
		addPageMain:function(id, name, url, urltype,closable){
			if(this.exist(id)){
				this.select(id);
			}
			else{
				var iframeId = cmnPcr.getRandomValue();
				var innerHtml = "<div style=\"width:100%;height:100%;overflow:hidden;\"><iframe id=\"" + iframeId + "\" style=\"width:100%;height:100%;border:0px;\" frameborder=\"0\" /></div>";
				$("#" + p.containerId).tabs("add",{
					id:id,
					title:name,
					content:innerHtml,
					closable: closable == undefined ? true : closable
				});
				debugger;
				var baseurl = urlMap[urltype] + url;
				if(baseurl.lastIndexOf('?')==-1){
					baseurl = baseurl + "?iframeId=" + iframeId;
				}else{
					baseurl = baseurl + "&iframeId=" + iframeId;
				}
				$("#" + iframeId).attr("src", baseurl);
				var tab = $("#" + p.containerId).tabs('getSelected');
				$(tab).attr("id", id);
				$(tab).attr("myCustom", prevNode);
			}
		},
		

		//显示嵌入页面的tab
		showPage:function(id, name, url, closable){
			
			
			console.log('我是id:'+id);
			if(this.exist(id)){
				this.select(id);
			}
			else{
				if(tabLen>maxTabNum){
					alert("请先尝试关闭一些页面，再打开新的页面！");
					return;
				}
				tabLen ++;
				var iframeId = cmnPcr.getRandomValue();
				var innerHtml = "<div style=\"width:100%;height:100%;overflow:hidden;\"><iframe id=\"" + iframeId + "\" style=\"width:100%;height:100%;border:0px;\" frameborder=\"0\" /></div>";
				$("#" + p.containerId).tabs("add",{
					id:id,
					title:name,
					content:innerHtml,
					closable: closable == undefined ? true : closable
				});
				//if(url.indexOf(".dhtml") != -1){
				//	url = basePath+"/"+url;
				//}else{
				//	url = basePath+"/page/"+url;
				//}
				//alert(url);
				var baseurl = basePath +  url;
				if(baseurl.indexOf('?')==-1){
					baseurl = baseurl + "?iframeId=" + iframeId;
				}else{
					baseurl = baseurl + "&iframeId=" + iframeId;
				}
				$("#" + iframeId).attr("src",baseurl);
				var tab = $("#" + p.containerId).tabs('getSelected');
				$(tab).attr("id", id);
				
				$(tab).attr("myCustom", prevNode);
				

				
				//$("#close").remove();
				/**
				$("#" + p.containerId).tabs("add",{
					id:'close',
					title:'关闭全部',
					closable: false
				});
				**/
				var allTabs = $("#" + p.containerId).tabs("tabs");
				if(allTabs.length>1){
					//获取最后一个tabs 在新加的选项卡后面添加"关闭全部"
			        var li = $(".tabs-wrap ul li:last-child");
			        $("#close").remove();
			        //li.after("<li id='close'><a class='tabs-inner' href='javascript:void()' onClick='javascript:iocClient.mainPageTab().closeAll()'>关闭全部</a></li>");
				}
			}
		},
		closeAll:function() {
			var allTabs = $("#" + p.containerId).tabs("tabs");
			$("#" + p.containerId +" .tabs li").each(function(index, obj) {
            	//获取所有可关闭的选项卡
	          	var tab = $(".tabs-closable", this).text();
	          	if(tab != undefined && tab != ""){
		          	$("#" + p.containerId).tabs('close', tab);
	          	}
	      	});
			tabLen = 1;
			//同时把此按钮关闭
	      	$("#close").remove();
	    }
	});
	iocClient.mainPageTab().showPage("mainPage", "home", "/jump!page.dhtml?p=defaultpage", false);
}

function initHeader(p){
	serverAccess.request({
		serviceName:"login!getSysParam",
		//funcName:"getSysParam",
		args:{requestParam:cmnPcr.jsonToStr({})},
		successFunc:function(obj){
				$("#"+p.containerId).html( obj.result.username);
		},
		failFunc:function(obj){
			msgBox.alert({title:"提示",info:obj.message});
		},
		waitingBarParentId:p.containerId
	});
}
function changePwd(){
	iocClient.mainPageTab().showPage('changePassword','Change Password','/account!changePwdEn.dhtml');
}
function clearUserMap() {
	
	var url = frontPath + "/account!clearUser.dhtml?num=" + Math.random();
    var payLogout = payPath + "/masterlogin!logout.dhtml?num=" + Math.random();
    var salesLogout = salesPath + "/masterlogin!logout.dhtml?num=" + Math.random();
    var appmanagerLogout = appmanagerPath + "/masterlogin!logout.dhtml?num=" + Math.random();
    var purchaseLogout = purchasePath + "/masterlogin!logout.dhtml?num=" + Math.random();
    var logisticLogout = logisticPath + "/masterlogin!logout.dhtml?num=" + Math.random();
    var inventoryLogout = inventoryPath + "/masterlogin!logout.dhtml?num=" + Math.random();
    $.get(payLogout);
    $.get(salesLogout);
    $.get(appmanagerLogout);
    $.get(purchaseLogout);
    $.get(logisticLogout);
    $.get(inventoryLogout);
	$.ajax({
		type : "GET",
		url : url,
		async : false,
		success : function(data) {
			G.Pro.logout('/masterlogin!logoutTo.dhtml');
		}
	});
}

var flag=true;

function callpse(){
	
	var $obj=$('#novaoneHeader');
	var $frame=$('#frame-all');
	var per=1-(48/$(window).height());
	
	per=per*100+'%';
	
	
	if( flag){
		
		$obj.hide();
		$obj.attr({"callapse":"callapse"});
		$frame.css({"height":"100%"});
		$frame.layout('resize');
		$('.icon-search').css({"background-position":"right center"});
		
		
		console.log($(window).height()+'frame'+$frame.height());
	
		if($('.callapseA')){//当向上收缩，左边是收缩的时候
			$('.callapseA').next().hide();
			$('.callapseA').show();
			$('.callapseA').trigger('click')
		
		}
	
		
	}else{
		$obj.show();
		$obj.attr({"callapse":"uncallapse"});
		$frame.css({"height":per});
		$frame.layout('resize');
		$('.icon-search').css({"background-position":"left center"});
		console.log($(window).height()+'frame'+$frame.height());
		
		if($('.callapseA')){//当向上收缩，左边是收缩的时候
			$('.callapseA').next().hide();
			$('.callapseA').show();
			$('.callapseA').trigger('click')
		
		}

		
		
	}
	flag=!flag;
	

	
	if($('.expandB')){
		$('.expandB').hide().prev().show();
		
	}
	//$('#frame-all').layout({height:$(window).height()});

}
