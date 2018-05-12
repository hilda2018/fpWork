

$(function() {
	
		// 是否是缩略图显示
	var onOff=true;
	var $showSupplier=$('#showSupplier');
	
	$('#icons-list').click(function(){
		

		
		if(onOff){
			$(this).css({"background-position":"0px bottom"});
			$showSupplier.addClass('smallView');
		}else{
			$(this).css({"background-position":"0px top"});	
			$showSupplier.removeClass('smallView');
		}
	
		onOff=!onOff;
		
		
		$('#list').delegate('li','click',function(){
			
			window.open( 'newCompany.html' );
		});
		
	});
	
	
	$.ajax({
		type:"get",
		url:"./2.txt",
		dataType:"json",
		async:true,
		success:function(resultData){	
			//默认加载 传入过来的数据
			shopsListData = resultData.rows;
			
			createListElement(shopsListData);
		}
	});



});


	




function createListElement(filterArr){
	
	var $list=$('#list');
	$('#list').empty();
	
	for(var i=0;i<filterArr.length;i++){
		
		var $listImg=$('<div class="list_img"></div>');
		
	
		
		var $img=$('<img>').attr({"src":filterArr[i]["imgUrl"]});
		var $span=$('<span>&nbsp;</span>');
		
		 $listImg.append($img);
		 $listImg.append($span);
		 
		var $propTitle=$('<div class="prop-title" title="companyName">'+filterArr[i]["companyName"]+'</div>');
		

		var $span1=$('<span><em>Country:</em> <strong class="established">'+filterArr[i]["Origins"]+'</strong></span>');
		var $span2=$('<span><em>Years Established:</em> <strong class="Yearwork">'+filterArr[i]["companyEstablished"]+'</strong></span>');
		var $span3=$('<span><em>Business Type:</em> <strong class="Turnover">'+filterArr[i]["businessTypes"]+'</strong></span>');

		
		var $infoTable=$('<div class="info-table"></div>');
		
		$infoTable.append($span1,$span2,$span3);
		
		var $listInfo=$('<div class="info-info"></div>');

		$listInfo.append($propTitle,$infoTable);
		
		var $btn=$('<a class="btn btn-danger"   href="newCompany.html">&nbsp;View Details&nbsp;</a>');
		var $clearfix=$('<div class="clearfix"></div>');
		
		var $Li=$('<li class="thumbnail"></li>');
		
		$Li.append($listImg,$propTitle,$listInfo,$btn,$clearfix);

		$list.append($Li);
	}
	

	
}
