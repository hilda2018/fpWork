/**
 * 多图片上传类
 * 
 * @param {Object} $
 * @memberOf {TypeName} 
 * @return {TypeName} 
 * @author 于采兴
 */
(function($) {
	//全局参数
	var iobj;
	//存放大图
	var srcImgFiles = new Array();
	//存放小图
	var smallImgFiles = new Array();
	//存放中图
	var middleImgFiles = new Array();
	var _style = "font-size:14px;color:#808080";
	var _info = '<div class="info"><font color="red">*</font> <span style="'+_style+'">主图</span></div>';
	
	/**
	 * 上传主函数入口
	 * p:参数定义
	 * custumFunction：上传成功后要在页面进行处理(如果需要则传入)
	 * 
	 * 上传成功后返回的json数据说明：
	 * data.status       上传状态(true:成功 false:失败)
	 * data.info         上传返回结果(上传成功.  上传失败.)
	 * data.srcImgUrl    源图地址(不包括应用地址)
	 * data.smallImgUrl  缩略图地址(不包括应用地址)
	 * data.fileName     原文件名(不包含路径的带后缀的文件名)
	 **/
	function init(uploadObj, p){
		//srcImgFiles = new Array();
		//smallImgFiles = new Array();
		
		var inputObj;
		var _li;
		
		var _tips = $('<p class="tip" style="'+_style+'">最多可上传'+p.queueSizeLimit+'个文件，每个文件最大'+p.fileSizeLimit+'。</p>');
		var _e = $('<div class="multimage-gallery"></div>');
		
		var _er = $('<div class="left"></div>');
		var _er_ui = $('<ul id="imageUl_'+p.fileObjName+'"></ul>');
		
		iobj = p;
		
		//alert(p.fileObjName);
		//构建上传按钮
		inputObj =  $('<span class="filespan"><input type="file" id="'+p.fileObjName
					+ '" name="'+p.fileObjName + '" class="input_ll" /></span>');
		$(inputObj).appendTo(_er);
		
		//将上传部分添加进div中
		_er.appendTo(_e);
		
		//构建上传后的图片预览
		for ( var i = 0; i < p.queueSizeLimit; i++) {
			_li = $('<li class="imageLi_'+p.fileObjName+'" id="imageLi_'+p.fileObjName+i+'">');
			if(i == 0){
				$(_info).appendTo(_li);
			}
			$(_li).appendTo(_er_ui);
		}
		//事件绑定
		bindAction(_e, p);
		
		//将图片预览部分添加进div中
		_er_ui.appendTo(_e);
		
		//将页面友情提示添加进div中
		_tips.appendTo(_e);
		
		//在页面生成整个上传功能页面
		_e.appendTo($(uploadObj));
	}
	
	//事件绑定
	function bindAction(obj, _setting){
//		obj.find("input.input_ll").bind({
//			click : function() {
//				clickAction(obj, _setting);
//			}
//		});
		
		$("#"+_setting.fileObjName).live("click",function() {
			clickAction(obj, _setting);
		});
		
		$("#"+_setting.fileObjName).live("change",function() {
			var filePath = $(this).val();
			
			if(filePath == ''){
				return;
			}
			if(!!_setting.fileTypeExts.length && !RegExp("\.(" + _setting.fileTypeExts.join("|") + ")$", "i").test(filePath)){
				alert("只允许上传"+_setting.fileTypeExts.join("|")+"格式的文件.");
				$(this).val('');
				//G.Msg.showWarning("只允许上传"+_setting.fileTypeExts.join("|")+"格式的文件.", function(){
				//	$(this).val('');
				//});
				return;
			}
			
			ajaxUpload(_setting);
		});
	}
	
	//绑定点击事件
	function clickAction(obj, _setting){
		var imgCount = 0;
		$("#imageUl_" + _setting.fileObjName+" li").each(function(){
			if($(this).find("img").length == 1){
				++imgCount;
			}
		});
		if (imgCount == _setting.queueSizeLimit) {
			alert("上传文件数已满，最多上传文件数：" + _setting.options.queueSizeLimit + "个.");
			//G.Msg.showWarning("上传文件数已满，最多上传文件数：" + _setting.options.queueSizeLimit + "个.");
			return false;
		}
		$(this).val("");
	}
	/**
	 * 返回中图的url
	 */
	getMiddleFiles = function(objId){
		resetData("middle", objId);
		return middleImgFiles.join();
	}
	/**
	 * 返回缩略图的url
	 */
	getSmaillFiles = function(objId){
		resetData("smaill", objId);
		return smallImgFiles.join();
	}
	/**
	 * 返回原图的URL
	 */
	getSrcFiles = function(objId){
		resetData("big", objId);
		return srcImgFiles.join();
	}
	
	//重新获取上传的大小图文件名
	resetData = function(src, fileObjName){
		var name = src == "big"?"srcImgUrl":"smaill"?"smallImgUrl":"middleImgUrl";//middle
		
		if(src == "big"){
			srcImgFiles = new Array();
		}else if(src == "smaill"){
			smallImgFiles = new Array();
		}else{
			middleImgFiles = new Array();
		}
		
		$("#imageUl_"+fileObjName+" li").each(function(){
			//如果已经上传则统计
			if($(this).find(".cobjs_"+fileObjName).length == 1){
				var imgUrl = $(this).find(".cobjs_"+fileObjName).attr(name);
				if(src == "big"){
					srcImgFiles.push(imgUrl);
				}else if(src == "smaill"){
					smallImgFiles.push(imgUrl);
				}else{
					middleImgFiles.push(imgUrl);
				}
			}
		});
	}
	

	//ajax上传方法
	ajaxUpload = function(obj, fun){
		$.ajaxFileUpload({
			url : basePath + obj.uploader, //用于文件上传的服务器端请求地址
			secureuri : false, //是否需要安全协议，一般设置为false
			fileElementId : obj.fileObjName, //文件选择框的id属性
			dataType : obj.dataType,//返回值类型 一般设置为json
			data : {
				'uploadFilePath':obj.uploadFilePathTag, 
				'fileSizeLimit':obj.fileSizeLimit,
				"fileObjName" : obj.fileObjName,
				"memberId" : obj.memberId
			},
			//服务器成功响应处理函数
			success : function (data, status){
				data = $.parseJSON(data);
				$("#"+obj.fileObjName).val('');
				if(data != ""){
					if(data.status == "false"){
						alert("上传的文件大小为" + data.filesize + "K，超过允许的大小"+obj.fileSizeLimit);
						//G.Msg.showWarning("上传的文件大小为" + data.filesize + "K，超过允许的大小"+obj.fileSizeLimit);
					}else{
						if(fun != null){
							fun(data);
						}else{
							//此处可自定义
							var imgOrder = 0;
							$(".imageLi_"+obj.fileObjName).each(function(){
								if($(this).find("img").length == 0){
									var img = '<div class="operate">'
											+ '<i class="toleft" onclick="upladMoveLeft(this,\'' + data.fileName +'\',\''+obj.fileObjName+'\')">左移</i>'
											+ '<i class="toright" onclick="upladMoveRight(this,\'' + data.fileName +'\',\''+obj.fileObjName+'\')">右移</i>'
											//+ '<i class="del" onclick="uploads.deleteImg(\''+data.fileName+'\',\'' + data.srcImgUrl + '\','
											//+ '\''+ data.smallImgUrl +'\',\'' + imgOrder + '\')">删除</i>'
											+ '<i class="del" onclick="upladDeleteImg(\'' + data.fileName +'\',\''+obj.fileObjName+'\',\'' + imgOrder + '\')">删除</i>'
											+ '<i class="cobjs_'+obj.fileObjName+'" style="display:none;" srcImgUrl="' + data.srcImgUrl +'" smallImgUrl="' + data.smallImgUrl + '" '
											+ 'middleImgUrl="' + data.middleImgUrl + '"  ></i>'
											+ '</div>'
											+ '<div class="preview">'
											+ '<img src="' + imgServer + data.middleImgUrl + '" width="'+obj.imgWidth+'" height="'+obj.imgHeight+'" />'
											+ '</div>';
									
									$(this).html(img);
									
									return false;
								}
								imgOrder++;
							});
						}

						
					}
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				//G.Msg.showWarning("图片上传失败！");
				alert("图片上传失败！");
		    }
		});
	}
	
	//删除图片时函数
	//this.deleteImg = function(fileName, srcUrl, smUrl, imgOrder) {
	upladDeleteImg = function(fileName, fileObjName, imgOrder) {
		var r = confirm("确定删除该图片？");
		if(r == true){
			$.ajax({
				type : "post",
				url : basePath + "/upload!deleteImg.action",
				data : {
					'uploadFilePath':iobj.uploadFilePathTag,
					"fileName" : fileName,//文件名
					"memberId" : iobj.memberId
				},
				success : function(data, textStatus) {
					if (data == 'true') {
						if(imgOrder == "0"){
							$("#imageLi_" + fileObjName + imgOrder).html(_info);
						}else{
							$("#imageLi_" + fileObjName + imgOrder).html('');
						}
					} else {
						//G.Msg.showWarning("图片删除失败！");
						alert("图片删除失败！");
					}
				}
			});
		}
		
		/**
		G.Msg.showConfirm("确定删除该图片？", function(es){
			if(typeof(es) == "undefined" || es){
				$.ajax({
					type : "post",
					url : imageServer + "/upload!deleteImg.dhtml",
					data : {
						'uploadFilePath':iobj.uploadFilePathTag,
						"fileName" : fileName,//文件名
						"smallImg" : iobj.smallImg//是否有缩略图
					},
					success : function(data, textStatus) {
						if (data == 'true') {
							if(imgOrder == "0"){
								$("#imageLi_" + fileObjName + imgOrder).html(_info);
							}else{
								$("#imageLi_" + fileObjName + imgOrder).html('');
							}
						} else {
							G.Msg.showWarning("图片删除失败！");
						}
					}
				});
			}
		});**/

	}
	
	//左移
	upladMoveLeft = function(lobj, fileName, fileObjName) {
		var current = $(lobj).parent().parent();
		var prev = current.prev();
		var curIndex = current.index();
		//alert('left'+curIndex);
		if (curIndex > 0) {
			if(curIndex == 1){
				var divLen = $(prev).find("div").length;
				if(divLen == 1 && divLen != 2){
					$(prev).html("");
				}
			}
			current.insertBefore(prev);
			imgPositionChange(fileObjName);
		}
	}
	
	//右移
	upladMoveRight = function(robj, fileName, fileObjName) {
		var current = $(robj).parent().parent();
		var next = current.next();
		var curIndex = current.index();
		
		//alert('right');
		
		if (next) {
			if(curIndex == 0){
				if($(next).find("div").length == 0){
					$(next).html(_info);
				}
			}
			
			current.insertAfter(next);
			imgPositionChange(fileObjName);
		}
	}
	//左移右移后的处理
	imgPositionChange = function(fileObjName){
		var imgNum = 0;
		$("#imageUl_"+fileObjName+" li").each(function(){
			$(this).attr("id","imageLi_"+fileObjName + imgNum);
			if($(this).find(".del").length == 1){
				var onClickVal = $(this).find(".del").attr("onclick");
				onClickVal = onClickVal.substring(0,onClickVal.indexOf(",") + 1) + "'"+fileObjName+"','" + imgNum + "')";
				$(this).find(".del").attr("onclick",onClickVal);
			}
			
			imgNum++;
		});
	}
	
	$.fn.upload = function(p, _fun) {
		if(typeof p=="string"){
			return $.fn.upload.methods[p](this, _fun);
		}
		p = p || {};
		return this.each(function() {
			var _obj = $.data(this, "novaone-upload_"+$(this).attr("id"));
			if (_obj) {
				$.extend(_obj.options, p);
			} else {
				var setting = $.extend( {}, $.fn.upload.defaults, p);
				var r = init(this, setting);
				_obj = $.data(this, "novaone-upload_"+$(this).attr("id"), {
					options : setting
				});
				//alert($.data(this, "ip").panel.html());
				//alert(_38.panel.html());
				//alert(_38.twopanel.html());
				//$(this).removeAttr("disabled");
			}
		});

	};

	$.fn.upload.methods={
		//获得大图
		big:function(jq){
			var _32=$.data(jq[0],"novaone-upload_"+$(jq[0]).attr("id")).options;
			return getSrcFiles(_32.fileObjName);
		},
		//获得小图
		smaill:function(jq){
			var _32=$.data(jq[0],"novaone-upload_"+$(jq[0]).attr("id")).options;
			return getSmaillFiles(_32.fileObjName);
		},
		//获得中图
		middle:function(jq){
			var _32=$.data(jq[0],"novaone-upload_"+$(jq[0]).attr("id")).options;
			return getMiddleFiles(_32.fileObjName);
		}
	};
	
	$.fn.upload.defaults = {
		//上传file的name名称(此处禁止更改)
		fileObjName : "uploadFile",
		//执行上传操作的action地址(默认)(需单独写上传action控制)
		uploader : '/upload!uploadFileOfAjax.action',
		//返回值类型 一般设置为json
		dataType : "text",
		//要将上传的文件存放哪个目录,此处可传：1:member、2:products、3:shop、4:honor、5:brand、6:news、7:about(默认)、8:富文本attached)
		uploadFilePathTag : "7",
		//必填项：登录用户的ID
		memberId:"a0b0c0d0",
	    //允许上传文件的最大数
	    queueSizeLimit : 10,
	    //图片的宽和高，此处需要结合样式
	    imgWidth : '130px',
	    imgHeight : '130px',
	    // 扩展名
	    fileTypeExts : ["GIF|PNG|JPG|JPEG"], 
	    //设置单个文件大小限制，单位为M(默认1M)
	    fileSizeLimit : '1M'
	};
})(jQuery)