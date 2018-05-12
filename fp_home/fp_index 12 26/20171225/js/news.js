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
		{
			"url": "",
			"headline": "Promexico and  Freshport  co-held communication  meeting",
			"detail":"Promexico and  Freshport co-held communication  meeting  for Mexican producer delegation and Chinese importing entrepreneur on  7th Nov. in Shanghai Huizhan market.",
			"date":"11-14-2017",
			"special":"true"
		},
		{
			"url": "/staticPage/page2",
			"headline": "ASOEX launch  new fruits season, more  than 100 fruits importers attended to know more about new season Chilean fruits promotion campaign.",
			"detail":"",
			"date":"11-14-2017",
			"special":"true"
		},
		{
			"url": "",
			"headline": "Port Jam alert in Shanghai",
			"detail":"<p>Shanghai Guandong port is suffering vessels congestion, its working time table is under chaos. These schedules of shipment will be delayed. A few shipping lines has announced adjustment and delay of their shipment.</p>",
			"date":"08-30-2017",
			"special":"false"
		},
		{
			"url": "",
			"headline": "Banana may become the 6th Mexican fruit to enter the Chinese market",
			"detail":"Mexican Bananas would probably be able to enter the Chinese market. The Mexican National Health and Food Safety Service has visited Beijing for the purpose of speed up the finalization the agreement about the Mexican Banana imported to China .Currently, there are 5 varieties of Mexican fruits allowed to import china, which are avocado, raspberry, blueberry, black berry and grape. In Asia, China is the second biggest and important bussiness partner of Mexico, with the annual import value of approximate US$ 300million.",
			"date":"08-27-2017",
			"special":"false"
		},
		{
			"url": "",
			"headline": "AFL grand opening on the 6th of September , 2017",
			"detail":"<p>There are over 700 worldwide fruit industry exhibitors will participate the Asia fruit Logistica (AFL)in Hong Kong.  Costa Rica , Finland ordan and Uzbekistan will be on their first show on the AFL. South Africa plans to increase its exhibition venue size by 150% than that of last year. South Korea will increase their size by 70% and Germany, Japan, Taiwan and Chile willincrease their size by 50%.</p><p>There will be more new products and service in this years show. The Tokita seeds company will release their new super-sweet pumpkin variety. The polish berries producer will unveil its jumbo blueberries. And Yumsun , a Chinese company will demonstrate its new prepack and carton design.</p>",
			"date":"08-27-2017",
			"special":"false"
		},
		{
			"url": "",
			"headline": "Huge opportunity in the chinese imported fruit industry",
			"detail":"There are 40 countries and regions allowed to export their fruits to China.  Over hundreds variety of imported fruit are sold in China ,including durian , pineapple, orange, kiwi, apple, banana etc. The total imported fruit value has been over USD 5 billion in 2016.It is expected that by 2021, the imported fruit market will keep increasing with the rise of China鈥檚 economy and its civilians income. Experts predict the annual growth rate of Chinese imported fruit volume will be around 5% in the next 4 years. There is still a big opportunity for oversea fruit trader to enter in the Chinese market.",
			"date":"08-25-2017",
			"special":"false"
		},
		{
			"url": "/staticPage/fruitList",
			"headline": "A new list of China allowable import fruits has been released from AQSIQ (Updated 23 Aug 2017)",
			"detail":"",
			"date":"08-24-2017",
			"special":"true"
		},
		{
			"url": "",
			"headline": "China has implemented AEO agreement with New Zealand",
			"detail":"The AEO Agreement has been implemented between China and New Zealand since this July. The agreement will benefit those two countries from simplified customs procedure for their exporting processing. It is expected that the customs clearance time and cost will be halved with this agreement.<p><p>China has signed AEO with those countries or regions including Singapore, South Korea, EU, Switzerland, New Zealand and Hong Kong. There are around 4000 Chinese companies being under AEO status.",
			"date":"09-01-2017",
			"special":"false"
		},
		{
			"url": "",
			"headline": "Port Jam alert in Shanghai",
			"detail":"Shanghai Guandong port is suffering vessels congestion, its working time table is under chaos. These schedules of shipment will be delayed. A few shipping lines has announced adjustment and delay of their shipment.",
			"date":"08-30-2017",
			"special":"false"
		},
		{
			"url": "",
			"headline": "Banana may become the 6th Mexican fruit to enter the Chinese market",
			"detail":"<p>Mexican Bananas would probably be able to enter the Chinese market. The Mexican National Health and Food Safety Service has visited Beijing for the purpose of speed up the finalization the agreement about the Mexican Banana imported to China .</p><p>Currently, there are 5 varieties of Mexican fruits allowed to import china, which are avocado, raspberry, blueberry, black berry and grape. In Asia, China is the second biggest and important bussiness partner of Mexico, with the annual import value of approximate US$ 300million.</p>",
			"date":"08-30-2017",
			"special":"false"
		},
		{
			"url": "",
			"headline": "AFL grand opening on the 6th of September , 2017",
			"detail":"<p>There are over 700 worldwide fruit industry exhibitors will participate the Asia fruit Logistica (AFL)in Hong Kong.  Costa Rica , Finland ordan and Uzbekistan will be on their first show on the AFL. South Africa plans to increase its exhibition venue size by 150% than that of last year. South Korea will increase their size by 70% and Germany, Japan, Taiwan and Chile willincrease their size by 50%.</p><p>There will be more new products and service in this years show. The Tokita seeds company will release their new super-sweet pumpkin variety. The polish berries producer will unveil its jumbo blueberries. And Yumsun , a Chinese company will demonstrate its new prepack and carton design.</p>",
			"date":"08-30-2017",
			"special":"false"
		},
		{
			"url": "",
			"headline": "Huge opportunity in the chinese imported fruit industry",
			"detail":"<p>There are 40 countries and regions allowed to export their fruits to China.  Over hundreds variety of imported fruit are sold in China ,including durian , pineapple, orange, kiwi, apple, banana etc. The total imported fruit value has been over USD 5 billion in 2016.</p><p>It is expected that by 2021, the imported fruit market will keep increasing with the rise of China鈥檚 economy and its civilians income. Experts predict the annual growth rate of Chinese imported fruit volume will be around 5% in the next 4 years. There is still a big opportunity for oversea fruit trader to enter in the Chinese market.</p>",
			"date":"08-25-2017",
			"special":"false"
		},
		{
			"url": "/staticPage/fruitList",
			"headline": "A new list of China allowable import fruits has been released from AQSIQ (Updated 23 Aug 2017)",
			"detail":"",
			"date":"08-24-2017",
		}
	];

 $(function(){

	newsScroll('vscroller',data);
});

function newsScroll(id,data) {

    var $_this = $('#' + id);

    var contentWrapper = $('<div/>').addClass('news-contents-wrapper');
    var wrapper = $_this.addClass('news-wrapper');
    var contentWrapper = $('<div/>').addClass('news-contents-wrapper');
    var newsHeader = $('<div/>').addClass('news-header').html('INDUSTRIAL NEWS AND ANNOUNCEMENTS');
    var newsContents = $('<div/>').addClass('news-contents');
    wrapper.append(contentWrapper);
    contentWrapper.append(newsHeader);
    contentWrapper.append(newsContents);
 
var category=['red','green','yellow2','blue'];

	for (var i=0;i<data.length;i++ ){
		var value=data[i]; 
		var news = $('<div/>').addClass('news');   
		var history2 = $('<div/>').addClass('history');
        newsContents.append(news);  
        var description = $('<div/>').addClass('description');
        news.append(history2);
        news.append(description);
        history2.append(getCircle(category[i%4], value.date));
        var url =value.url;
        var htext = value.headline;
        description.append($('<h1/>').html("<a href='" + url + "'>" + htext + "</a>"));
        var newsText = value.detail;	
        if (newsText.length > 80) {
            newsText = newsText.substr(0, 80) + "...";
        }
        description.append($('<div/>').addClass('detail').html(newsText));
	}

	var j=0;
	var arr=[];
	h = parseFloat($('.news:eq(0)').outerHeight());
    $('.news', wrapper).each(function() {

        $(this).css({
            top: j++*h
        });
    });
    
    
    t = ( data.length- 1) * h;

   newsContents.mouseenter(function() {
       mouseIn = true;
       if (!isScrolling) {
       $('.news').stop(true, false);
          clearTimeout(interval);
        }
    });
    
    newsContents.mouseleave(function() {
      mouseIn = false;
 	  interval = setTimeout(scroll, settings.stay);
 	});
    
    interval = setTimeout(scroll, 1);

}

function scroll() {
    if (!mouseIn && !isScrolling) {
        isScrolling = true;
        $('.news:eq(0)').stop(true, false).animate({
            top: -h
        },
        settings.speed,
        function() {

            clearTimeout(interval);
            var current = $('.news:eq(0)').clone(true);
            current.css({
                top: t
            });
            $('.news-contents').append(current);
            $('.news:eq(0)').remove();
            isScrolling = false;
            interval = setTimeout(scroll, settings.stay);

        });
        $('.news:gt(0)').stop(true, false).animate({
            top: '-=' + h
        },
        settings.speed);


    }
}

function getCircle(category, date) {
    date = date.replace(/-/g, '/');
    var d = new Date(date);
    var day = '';
    var month = '';
    switch (d.getDate()) {
    case 1:
    case 21:
        day = d.getDate() + ' st';
        break;
    case 2:
    case 22:
        day = d.getDate() + 'nd';
        break;
    case 3:
    case 23:
        day = d.getDate() + 'rd';
        break;
    default:
        day = d.getDate() + 'th';
        break;
    }
    switch (d.getMonth()) {
    case 0:
        month = 'JAN';
        break;
    case 1:
        month = 'FEB';
        break;
    case 2:
        month = 'MAR';
        break;
    case 3:
        month = 'APR';
        break;
    case 4:
        month = 'MAY';
        break;
    case 5:
        month = 'JUN';
        break;
    case 6:
        month = 'JUL';
        break;
    case 7:
        month = 'AUG';
        break;
    case 8:
        month = 'SEP';
        break;
    case 9:
        month = 'OCT';
        break;
    case 10:
        month = 'NOV';
        break;
    case 11:
        month = 'DEC';
        break;

    }
    return $('<div/>').addClass('circle-outer').append($('<div/>').addClass('circle').addClass(category).append($('<span/>').addClass('day').html(day)).append($('<span/>').html('...').addClass('elipses')).append($('<span/>').addClass('month').html(month)));
}

		