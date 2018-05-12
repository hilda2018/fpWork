$(document).ready(function(){
	$(".sidelist h3:first").addClass("h3-first");
    $('.sidelist').mouseenter(function(){
		if($(this).parent().hasClass("side-left")) return;
        var top = $(this).position().top + $(this).height()/2 - $(this).find('.i-list').height()/2;
        if( top <= 0){
            $(this).find('.i-list').show();
        }else{
			
            $(this).find('.i-list').show().css("top",top);
        }       
        $(this).find('h3').addClass('hover');
		if($(this).hasClass("fs"))
		{
			$(this).parent().addClass("h3-first");
			$(this).parent().css("borderTop","1");
		}
	});
	 $('.side-left .sidelist').mouseenter(function(){
        var top = $(this).position().top + $(this).height()/2 - $(this).find('.i-list').height()/2;
        if( top <= 0){
            $(this).find('.i-list').show().css("top",35);
        }else{
			
            $(this).find('.i-list').show().css("top",top);
        }       
        $(this).find('h3').addClass('hover');
		if($(this).hasClass("fs"))
		{
			$(this).parent().addClass("h3-first");
			$(this).parent().css("borderTop","1");
		}
	});
	$('.sidelist').mouseleave(function(){
        $(this).find('.i-list').hide();
		if($(this).hasClass("fs"))
		{
			$(this).parent().addClass("h3-first");
			$(this).parent().css("borderTop","1");
		}
        $(this).find('h3').removeClass('hover');
	});
	
	
	
		//点击导航中的全部产品分类显示菜单
		$(".nav .all").hover(function(){
			var $all=$(this);
			$(".nav .side-left").css("display","block");
			$(this).removeClass("all");
			$(this).addClass("all-up");
		},
			function()
			{
				$(".nav .side-left").hover(
					function()
					{
						$(this).css("display","block");
						$(".nav ul li:first").removeClass("all");
						$(".nav ul li:first").addClass("all-up");
						return;
					},
					function()
					{
						$(this).css("display","none");
						$(".nav ul li:first").removeClass("all-up");
						$(".nav ul li:first").addClass("all");
						return;
					}
				)
				$(".nav .side-left").css("display","none");
				$(".nav ul li:first").removeClass("all-up");
				$(".nav ul li:first").addClass("all");
				return;	
			}
		);
	
	
});

//slideUp