    //info pic
    $(window).load(function(){
		$('.piclist1').each(function() {
			liw = 0;
			var li = $(this).find('li');
			li.each(function(){
				liw += $(this).width()+0;
				$(this).css('width',$(this).width()+'px');
			})
			var pl=parseInt(li.css("padding-left"));
			var pr=parseInt(li.css("padding-right"));
			var item = li.length;
			var w=item *pl + item*pr +liw ;
			$(this).width( w + 'px');
		});
        
    })
	function prev(obj) {
		var dom = $(obj).parent().find('.piclist1');
		var domWidth = dom.width();
		var parentDom = $(obj).parent().find('.box').width();
		var liWidth = domWidth/dom.find('li').length;
		if(dom.is(':animated')){
			dom.stop(true,true);
		}/* 避免点击事件重复 */
		
		ml = parseInt(dom.css('left'));
		var value =  parentDom - domWidth;  /* 700为外部区块.infopic的宽度，20为li之间的距离，即.piclist li的margin-right的值 */
		if(ml<=value){
			s = 0;
		}else{
			s = liWidth;
		}
		dom.animate({left: ml - s + 'px'},'4000');           
        
	}
	
	function next(obj){
		var dom = $(obj).parent().find('.piclist1');
		var domWidth = dom.width();
		var liWidth = domWidth/dom.find('li').length;	
		if(dom.is(':animated')){
			dom.stop(true,true);
		}/* 避免点击事件重复 */
		
		ml = parseInt(dom.css('left')); 
		if(ml>= 0){
			s = ml;
		}else{
			s = -liWidth;
		}
		dom.animate({left: ml - s + 'px'},'slow');           
        
	}