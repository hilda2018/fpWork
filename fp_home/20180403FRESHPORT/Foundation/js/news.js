var interval = null;
var mouseIn = false;
var totalElements;
var isScrolling = false;
var h;
var t;
 var settings = {
        speed: 1000000000,
        stay: 2000,
        newsfeed: '',
        cache: true
};


var data = [
		[{
			"url": "",
			"headline": "Promexico and  Freshport  co-held communication  meeting",
			"date":"11-14-2017",
			"special":"true"
		},
		{
			"url": "/staticPage/page2",
			"headline": "ASOEX launch  new fruits season, more  than 100 fruits importers attended to know more about new season Chilean fruits promotion campaign.",
			"date":"11-14-2017",
			"special":"true"
		},
		{
			"url": "",
			"headline": "Port Jam alert in Shanghai",
			"date":"08-30-2017",
			"special":"false"
		},
		{
			"url": "",
			"headline": "Banana may become the 6th Mexican fruit to enter the Chinese market",
			
			"date":"08-27-2017",
			"special":"false"
		},
		{
			"url": "",
			"headline": "AFL grand opening on the 6th of September , 2017",
			"date":"08-27-2017",
			"special":"false"
		},
		{
			"url": "",
			"headline": "Huge opportunity in the chinese imported fruit industry",
			"date":"08-25-2017",
			"special":"false"
		},
		{
			"url": "/staticPage/fruitList",
			"headline": "A new list of China allowable import fruits has been released from AQSIQ (Updated 23 Aug 2017)",
			"date":"08-24-2017",
			"special":"true"
		},
		{
			"url": "",
			"headline": "China has implemented AEO agreement with New Zealand",
			"date":"09-01-2017",
			"special":"false"
		},
		{
			"url": "",
			"headline": "Port Jam alert in Shanghai",
			"date":"08-30-2017",
			"special":"false"
		},
		{
			"url": "",
			"headline": "Banana may become the 6th Mexican fruit to enter the Chinese market",
			"date":"08-30-2017",
			"special":"false"
		},
		{
			"url": "",
			"headline": "AFL grand opening on the 6th of September , 2017",
			"date":"08-30-2017",
			"special":"false"
		},
		{
			"url": "",
			"headline": "Huge opportunity in the chinese imported fruit industry",
			"date":"08-25-2017",
			"special":false
		},
		{
			"url": "/staticPage/fruitList",
			"headline": "A new list of China allowable import fruits has been released from AQSIQ (Updated 23 Aug 2017)",
			"date":"08-24-2017",
		}],
				[{
			"url": "",
			"headline": "Promexico and  Freshport  co-held communication  meeting",
			"date":"11-14-2017",
			"special":"true"
		},
		{
			"url": "/staticPage/page2",
			"headline": "ASOEX launch  new fruits season, more  than 100 fruits importers attended to know more about new season Chilean fruits promotion campaign.",
			"date":"11-14-2017",
			"special":"true"
		},
		{
			"url": "",
			"headline": "Port Jam alert in Shanghai",
			"date":"08-30-2017",
			"special":"false"
		},
		{
			"url": "",
			"headline": "Banana may become the 6th Mexican fruit to enter the Chinese market",
			
			"date":"08-27-2017",
			"special":"false"
		},
		{
			"url": "",
			"headline": "AFL grand opening on the 6th of September , 2017",
			"date":"08-27-2017",
			"special":"false"
		},
		{
			"url": "",
			"headline": "Huge opportunity in the chinese imported fruit industry",
			"date":"08-25-2017",
			"special":"false"
		},
		{
			"url": "/staticPage/fruitList",
			"headline": "A new list of China allowable import fruits has been released from AQSIQ (Updated 23 Aug 2017)",
			"date":"08-24-2017",
			"special":"true"
		},
		{
			"url": "",
			"headline": "China has implemented AEO agreement with New Zealand",
			"date":"09-01-2017",
			"special":"false"
		},
		{
			"url": "",
			"headline": "Port Jam alert in Shanghai",
			"date":"08-30-2017",
			"special":"false"
		},
		{
			"url": "",
			"headline": "Banana may become the 6th Mexican fruit to enter the Chinese market",
			"date":"08-30-2017",
			"special":"false"
		},
		{
			"url": "",
			"headline": "AFL grand opening on the 6th of September , 2017",
			"date":"08-30-2017",
			"special":"false"
		},
		{
			"url": "",
			"headline": "Huge opportunity in the chinese imported fruit industry",
			"date":"08-25-2017",
			"special":false
		},
		{
			"url": "/staticPage/fruitList",
			"headline": "A new list of China allowable import fruits has been released from AQSIQ (Updated 23 Aug 2017)",
			"date":"08-24-2017",
		}]
		
	];

 $(function(){

geneNews(data);
});



function geneNews(news){
    	

    
    	var str='';
		var category=['redTab','greenTab','yellowTab','blueTab'];

		for(var i=0;i<news.length;i++){
				
			for(var j=0;j<news[i].length;j++){
				
					var value=news[i][j]; 
					var url =value.url;
			        var htext = value.headline;
			        var time2 = value.date;
        
        
				var item = "<a class=\"description\"><span  class=\"time "+category[j%4]+"\"   >"+time2+"</span> <span class=\"htext\"  >"+ htext +"</span></a>";
			
				str+=item;
			}
			
			$('.newslist01').eq(i).html(str);
			str='';
		}
		
		
		$(".tab li").click(function(){
			$(".tab li").eq($(this).index()).addClass("activ").siblings().removeClass("activ");
			$(".newslist01").removeClass("on").eq($(this).index()).addClass("on");
		})

    }