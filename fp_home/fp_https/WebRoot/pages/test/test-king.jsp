<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/base/include/basePage.jsp"%>
<html>
  <head>
    <title>My JSP 'test-king.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	
	<link href="${libKindeditor}/themes/default/default.css" rel="stylesheet" />
	<link href="${libKindeditor}/plugins/code/prettify.css" rel="stylesheet">
	
	<%@ include file="/base/include/bootstrap.jsp"%>
		
	<script type="text/javascript" src="${libKindeditor}/kindeditor.js"></script>
	<script type="text/javascript" src="${libKindeditor}/lang/zh_CN.js"></script>
	<script type="text/javascript" src="${libKindeditor}/plugins/code/prettify.js"></script>
	
	<script type="text/javascript" src="${libJquery}/novaone-keditor.min.js"></script>

  </head>
  
  <body>
    This is my JSP page. <br>
    <table width="100%" border="0">
		<tr>
			<td>
				富文本:
			</td>
		</tr>
		<tr>
			<td>
				<textarea cols="100" rows="8" style="width:130px; height:350px;" name="descriptionStr" id="zhDesc"></textarea>
			</td>
		</tr> 
		<tr>
			<td>
				<div class="right">
					已输入<span id="zhDescLength" style="color: green;"></span>/最多输入6000
				</div>
				<label id="zhDescTip" style="display: none;" class="error">
					请减少中文字符数量，否则无法发布成功
				</label>
				<label id="zhImgTip" style="display: none;" class="error">
					商品中文描述中最多插入10张图片，请减少中文描述中插入的图片数量，否则无法发布成功
				</label>			
			</td>
		</tr>
		<tr>
			<td>
				<textarea cols="100" rows="8" style="visibility:hidden;" name="testData.context" id="context">dfg</textarea>
			</td>
		</tr>
		<tr>
			<td><input type="button" id="upImgSave" value="保存" style="width: 100px;" /></td>
		</tr>
	</table>   
  <script type="text/javascript">
  $(function() {
    try{	
    	//富文本编辑
		//$("#context").keditor();
    	
		KindEditor.ready(function(K) {
			zhDescEditor = K.create('#zhDesc', {
				items : [ 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor',
						'bold', 'italic', 'underline', 'removeformat', '|',
						'justifyleft', 'justifycenter', 'justifyright',
						'insertorderedlist', 'insertunorderedlist', '|', 'image',
						'emoticons', 'link' ],
				//cssPath : '${libKindeditor}/plugins/code/prettify.css',
				uploadJson : basePath + '/upload!uploadOfEditor.action?memberId=f3f3f3',
				fileManagerJson : basePath + '/upload!uploadFileManager.action?memberId=f3f3f3',
				allowFileManager : true,
				afterCreate: function () {
					this.sync();
				},
				afterChange : function() {
					this.sync();
					
					$("#zhDescLength").html(this.count('text'));
					
					var limitNum = 6000;  //设定限制字数
					if(this.count('text') > limitNum) {
				        $("#zhDescTip").css("display","block");
				    }else{
				    	$("#zhDescTip").css("display","none");
				    }
					
					var imgNum = 10;
					if(this.count('img') > imgNum) {
				        $("#zhImgTip").css("display","block");
				    }else{
				    	$("#zhImgTip").css("display","none");
				    }
				},
				afterBlur : function() {
					this.sync();
				}
			});
		});
		
		$("#upImgSave").click(function(){
			alert($("#zhDesc").val());
		});
	}catch(e){
		alert("Error(upload.jsp):"+e.message); 
	}
  });
  	
  </script>		  
  </body>
</html>
