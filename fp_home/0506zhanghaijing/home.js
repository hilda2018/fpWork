
var TYPE_IMPORTERS = 1;
var TYPE_EXPORTERS = 2;
var TYPE_ASSOCIATIONS = 3;


$.fn.parallelRoll = function(options){

    var opts = defaults;

    var _this = this;

    var l = _this.find(opts.tagName).length;

    if( l <= 4){
        return false;
    }

    var autoRollTimer;

    var flag = true; //

    var arr = new Array();


    if(opts.amount + opts.visual > l){

        _this[0].innerHTML += _this[0].innerHTML;

        l = 2 * l;

    }else{

        l = l;

    }

    //var w = $(opts.tagName).outerWidth(); //璁＄畻鍏冪礌鐨勫搴�  鍖呮嫭琛ョ櫧+杈规
    //console.log($(".button_item").outerWidth());
    var w = $("li.button_item").outerWidth(); //璁＄畻鍏冪礌鐨勫搴�  鍖呮嫭琛ョ櫧+杈规

    _this.css({width: (l * w) + 'px'}); // 璁剧疆婊氬姩灞傜洅瀛愮殑瀹藉害

    return this.each(function(){

        _this.closest('.'+opts.boxName).hover(function(){

            clearInterval(autoRollTimer);

        },function(){

            switch (opts.direction){

                case 'left':

                    autoRollTimer = setInterval(function(){

                        left();

                    },opts.time);

                    break;

                case 'right':

                    autoRollTimer = setInterval(function(){

                        right();

                    },opts.time);

                    break;

                default :

                    alert('鍙傛暟閿欒锛�');

                    break;

            }

        }).trigger('mouseleave');

        $('.'+opts.prev).on('click',function(){

            flag ? left() : "";

        });

        $('.'+opts.next).on('click',function(){

            flag ? right() : "";

        });

    });

    function left(){

        flag = false;

        _this.animate({marginLeft : -(w*opts.amount)},2000,function(){

            _this.find(opts.tagName).slice(0,opts.amount).appendTo(_this);

            _this.css({marginLeft:0});

            flag = true;

        });

    };

    function right(){

        flag = false;

        arr = _this.find(opts.tagName).slice(-opts.amount);

        for(var i = 0; i<opts.amount; i++){

            $(arr[i]).css({marginLeft : -w*(i+1)}).prependTo(_this);

        }

        _this.animate({marginLeft : w*opts.amount},2000,function(){

            _this.find(opts.tagName).removeAttr('style');

            _this.css({marginLeft:0});

            flag = true;

        });

    };

};


