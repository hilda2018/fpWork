
$(document).ready(function() {
	var $wrapper = $('.subnav');
	$('#search').click(function() {
		
		
		if($('body').hasClass("open")) {
			$('body').removeClass("open");
		} else {
			$('body').addClass("open");
			
		}

	})
	$('#searchClose').click(function() {
		$('body').removeClass("open");
		
	});

});

		

		
	