<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/base/include/basePage.jsp"%>
<html>
  <head>
    <title>My JSP 'test-upload.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%@ include file="/base/include/bootstrap.jsp"%>
	<!-- 上传 -->
	<script type="text/javascript" src="${libJquery}/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${libJquery}/novaone-upload.min.js"></script>

  </head>
  
  <body>
    This is my JSP page. <br>
    <table>
		<tr>
			<td>
				上传图片:
			</td>
			<td>
				<!-- 存放上传图片部分 -->
				<span id="uploadDiv"></span>
				<input type="button" id="upImgSave" value="保存" style="width: 100px;" />
			</td>
		</tr> 
	</table> 
  <script type="text/javascript">
  $(function() {
    try{	
		var pram = {
			//上传file的id名称
			fileObjName : "uploadFile",
			//设置单个文件大小限制，单位为B、K、M(默认1M)
	    	fileSizeLimit : '500K', 
	    	//要将上传的文件存放哪个目录,此处可传：1:member、2:products、3:shop、4:honor、5:brand、6:news、7:about(默认)
			uploadFilePathTag : "7",
			//必填项：登录用户的ID
			memberId:"a0b0c0d0",
			//允许上传文件的最大数
	    	queueSizeLimit : 10
		}
		//初始化上传函数
		$("#uploadDiv").upload(pram);
		
		//保存的处理
		$("#upImgSave").click(function(){
			var a1 = "uploadDiv源图片名列表："+$("#uploadDiv").upload("big")+"----缩略图名列表："+$("#uploadDiv").upload("smaill");
			a1 += "----中图名列表："+$("#uploadDiv").upload("middle");
			//获得上传的源图片及缩略图的图片+路径
			alert(a1);
		});
		
	}catch(e){
		alert("Error(upload.jsp):"+e.message); 
	}
  });
  	
  </script>	  
  </body>
</html>
