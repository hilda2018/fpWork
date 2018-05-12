/**
* 功能说明:		输入验证
* @author:		vivy <lizhizyan@qq.com>
* @time:		2015-9-25 16:15:30
* @version:		V1.1.0
* @使用方法:	    
* <input class="required" type="text" data-valid="isNonEmpty||isEmail" data-error="email不能为空||邮箱格式不正确" id="" />	
* 1、需要验证的元素都加上【required】样式
* 2、@data-valid		验证规则，验证多个规则中间用【||】隔开，更多验证规则，看rules和rule，后面遇到可继续增加
* 3、@data-error		规则对应的提示信息，一一对应
*
* @js调用方法：
* verifyCheck({
*  	formId:'verifyCheck',		<验证formId内class为required的元素
*	onBlur:null,				<被验证元素失去焦点的回调函数>
*	onFocus:null,				<被验证元素获得焦点的回调函数>
*	onChange: null,				<被验证元值改变的回调函数>
*	successTip: true,			<验证通过是否提示>
*	resultTips:null,			<显示提示的方法，参数obj[当前元素],isRight[是否正确提示],value[提示信息]>
*	clearTips:null,				<清除提示的方法，参数obj[当前元素]>			
*	code:true					<是否需要手机号码输入控制验证码及点击验证码倒计时,目前固定手机号码ID为phone,验证码两个标签id分别为time_box，resend,填写验证框id为code>
*	phone:true					<改变手机号时是否控制验证码>
* })
* $("#submit-botton").click(function(){		<点击提交按钮时验证>	
*  	if(!common.verify.btnClick()) return false;
* })
*
* 详细代码请看register.src.js
*/
var $targer={};


(function($) {
	var h, timerC = 60,
		opt;
	var j = function(a) {
			a = $.extend(require.defaults, a || {});
			opt = a;
			$targer.parent = new require();
			return $targer.parent._init(a)
		};

	function require(f) {
		var g = {
			phone: /^1(3\d|5[0-35-9]|8[025-9]|47)\d{8}$/,
			company:/^[A-Za-z]+$/,
			uname:/^[A-Za-z0-9]+$/,
			email:/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
			isPassword:/^[A-Za-z0-9]{6,20}$/,
			zh: /^[一-龥]+$/,
			card: /^((1[1-5])|(2[1-3])|(3[1-7])|(4[1-6])|(5[0-4])|(6[1-5])|71|(8[12])|91)\d{4}(((((19|20)((\d{2}(0[13-9]|1[012])(0[1-9]|[12]\d|30))|(\d{2}(0[13578]|1[02])31)|(\d{2}02(0[1-9]|1\d|2[0-8]))|(([13579][26]|[2468][048]|0[48])0229)))|20000229)\d{3}(\d|X|x))|(((\d{2}(0[13-9]|1[012])(0[1-9]|[12]\d|30))|(\d{2}(0[13578]|1[02])31)|(\d{2}02(0[1-9]|1\d|2[0-8]))|(([13579][26]|[2468][048]|0[48])0229))\d{3}))$/,
			int: /^[0-9]*$/,
			s: ''
		};
		this.rules = {
			isNonEmpty: function(a, b) {
				b = b || " ";
				if (!a.length) return b
			},
			minLength: function(a, b, c) {
				c = c || " ";
				if (a.length < b) return c
			},
			maxLength: function(a, b, c) {
				c = c || " ";
				if (a.length > b) return c
				console.log(c);
			},
			isCheckUser:function(a,b){
		console.log(a);
				var result = true;//后台查询结果
				if(result){
					$targer.isUname=true ;
				}else{
					return b;
				}
			
			},
			isRepeat: function(a, b, c) {
				c = c || " ";			
				if (a !== $("#" + b).val()) 
				{

					return c
				}else{
					
					$targer.isRepeat=true ;
					
				}
			},
			between: function(a, b, c) {
				c = c || " ";
				var d = parseInt(b.split('-')[0]);
				var e = parseInt(b.split('-')[1]);
				if (a.length < d || a.length > e) return c
			},
			level: function(a, b, c) {
				c = c || " ";
				var r = j.pwdStrong(a);
				
			
				if (b != 0) b = 3;
			},
			isPhone: function(a, b) {
				b = b || " ";
				if (!g.phone.test(a)) return b
			},
			isCompany: function(a, b) {
				b = b || " ";
				if (!g.company.test(a)) return b
			},
			isInt: function(a, b) {
				b = b || " ";
				if (!g.int.test(a)) return b
			},
			isUname: function(a, b) {
				b = b || " ";
				if (!g.uname.test(a))
					 return b
			
			},
			isPassword: function(a, b) {
				b = b || " ";
				if (!g.uname.test(a)) return b
			},
			isZh: function(a, b) {
				b = b || " ";
				if (!g.zh.test(a)) return b
			},
			isCard: function(a, b) {
				b = b || " ";
				if (!g.card.test(a)) return b
			},
			isChecked: function(c, d, e) {
				d = d || " ";
				var a = $(e).find('input:checked').length,
					b = $(e).find('.on').length;
				if (!a && !b) return d
			}
		}
	};
	require.prototype = {
		_init: function(b) {
			this.config = b;
			this.getInputs = $('#' + b.formId).find('.required:visible');
			var c = false;
			var d = this;
			if (b.code) {
				$("#verifyYz").click(function() {
					$("#time_box").text("60 s后可重发");
					d._sendVerify()
				})
			}
			$('body').on({
				blur: function(a) {
					d.formValidator($(this));
					if (b.phone && $(this).attr("id") === "phone") d._change($(this));
					b.onBlur ? b.onBlur($(this)) : ''
				},
				focus: function(a) {
					b.onFocus ? b.onFocus($(this)) : $(this).parent().find("label.focus").not(".valid").removeClass("hide").siblings(".valid").addClass("hide") && $(this).parent().find(".blank").addClass("hide") && $(this).parent().find(".close").addClass("hide")
				},
				keyup: function(a) {
					if (b.phone && $(this).attr("id") === "phone") d._change($(this))
				},
				change: function(a) {
					b.onChange ? b.onChange($(this)) : ''
				}
			}, "#" + b.formId + " .required:visible");
			$('body').on("click", ".close", function() {
				var p = $(this).parent(),
					input = p.find("input");
				input.val("").focus()
			})
		},
		formValidator: function(a) {
			var b = a.attr('data-valid');
			if (b === undefined) return false;
			var c = b.split('||');
			var d = a.attr('data-error');
			if (d === undefined) d = "";
			var e = d.split("||");
			var f = [];
			for (var i = 0; i < c.length; i++) {
				f.push({
					strategy: c[i],
					errorMsg: e[i]
				})
			};
			return this._add(a, f)
		},
		_add: function(a, b) {
			var d = this;
			for (var i = 0, rule; rule = b[i++];) {
				var e = rule.strategy.split(':');
				var f = rule.errorMsg;
				var g = e.shift();
				e.unshift(a.val());
				e.push(f);
				e.push(a);
				var c = d.rules[g].apply(a, e);
				if (c) {
					opt.resultTips ? opt.resultTips(a, false, c) : j._resultTips(a, false, c);
					return false
				}
			}
			opt.successTip ? (opt.resultTips ? opt.resultTips(a, true) : j._resultTips(a, true)) : j._clearTips(a);
			return true
		},
		_sendVerify: function() {
			var a = this;
			$("#verifyYz").text("发送验证码").hide();
			$("#time_box").text("10 s后可重发").show();
			if (timerC === 0) {
				clearTimeout(h);
				timerC = 60;
				var b = /^1([^01269])\d{9}$/;
				if (!b.test($("#phone").val())) {
					$("#time_box").text("发送验证码")
				} else {
					$("#verifyYz").show();
					$("#time_box").hide()
				}
				return
			}
			$("#time_box").text(timerC + " s后可重发");
			timerC--;
			h = setTimeout(function() {
				a._sendVerify()
			}, 1000)
		},
		_change: function(a) {
			var b = this;
			if (a.val().length != 11) {
				$("#verifyYz").hide();
				$("#time_box").show();
				if (timerC === 60) $("#time_box").text("发送验证码");
				$("#verifyNo").val("");
				this.config.clearTips ? this.config.clearTips($("#verifyNo")) : j._clearTips($("#verifyNo"));
				return
			}
			var c = /^1([^01269])\d{9}$/;
			if (!c.test(a.val())) return false;
			if (timerC === 60) {
				$("#verifyYz").show();
				$("#time_box").hide()
			} else {
				$("#verifyYz").hide();
				$("#time_box").show()
			}
		}
	};
	j._click = function(c) {
		c = c || opt.formId;
		var d = $("#" + c).find('.required:visible'),
			self = this,
			result = true,
			t = new require(),
			r = [];
		$.each(d, function(a, b) {
			result = t.formValidator($(b));
			if (result) r.push(result)
		});
		if (d.length !== r.length) result = false;
		return result
	};
	j._clearTips = function(a) {
		a.parent().find(".blank").addClass("hide");
		a.parent().find(".valid").addClass("hide");
		a.removeClass("v_error")
	};
	j._resultTips = function(a, b, c) {
		a.parent().find("label.focus").not(".valid").addClass("hide").siblings(".focus").removeClass("hide");
		a.parent().find(".close").addClass("hide");
		a.removeClass("v_error");
		c = c || "";
		if (c.length > 21) c = "<span>" + c + "</span>";
		var o = a.parent().find("label.valid");
		if (!b) {
			o.addClass("error");
			a.addClass("v_error");
			if ($.trim(a.val()).length > 0) a.parent().find(".close").removeClass("hide")
		} else {
			a.parent().find(".blank").removeClass("hide")
		}
		o.text("").append(c)
	};
	j.textChineseLength = function(a) {
		var b = /[一-龥]|[、-。]|[：-？]|[《-』]|[【-〕]|[–-”]|[！-．]|[〈-〉]|[…]|[￥]/g;
		if (b.test(a)) return a.match(b).length;
		else return 0
	};
	j.pwdStrong = function(a) {
		var b = 0;
	
		a.test(/^[A-Za-z0-9]{6,20}$/);
		if (a ) {
			b = 4
		}

		return b
	};
	require.defaults = {
		formId: 'verifyCheck',
		onBlur: function(){
	
		
			if($targer.isUname&&$targer.isRepeat){
				
				
				$('.tit p').eq(1).add($('.step li').eq(1)).addClass('on')
			}else{
				$('.tit p').eq(1).add($('.step li').eq(1)).removeClass('on')
			}	

					
			if($('#company').val()!=''&&$('#company').val().length ){
			
					$targer.company=true;
		

			}
			
			if( $targer.country&& $targer.isRepeat && $targer.isUname && $targer.company ){
					
					$('.tit p').eq(2).add($('.step li').eq(2)).addClass('on');
					$('#btn_part1').addClass('green');
			}

		
		},
		onFocus: null,
		onChange: null,
		successTip: true,
		resultTips: null,
		clearTips: null,
		code: true,
		phone: false
	};
	window.verifyCheck = $.verifyCheck = j
})(jQuery);
(function($) {
	var f;
	var g = function() {
			return (new require())._init()
		};

	function require(a) {};
	require.prototype = {
		_init: function() {
			var b = this;
			$('body').on({
				click: function(a) {
					b._click($(this))
				}
			}, ".showpwd:visible")
		},
		_click: function(a) {
			var c = a.attr('data-eye');
			if (c === undefined) return false;
			var d = $("#" + c),
				cls = !d.attr("class") ? "" : d.attr("class"),
				value = !d.val() ? "" : d.val(),
				type = d.attr("type") === "password" ? "text" : "password",
				b = d.parent().find("b.placeTextB"),
				isB = b.length === 0 ? false : true;
			var s = d.attr("name") ? " name='" + d.attr("name") + "'" : "";
			s += d.attr("data-valid") ? " data-valid='" + d.attr("data-valid") + "'" : "";
			s += d.attr("data-error") ? " data-error='" + d.attr("data-error") + "'" : "";
			s += d.attr("placeholder") ? " placeholder='" + d.attr("placeholder") + "'" : "";
			var e = '<input readonly type="' + type + '" class="' + cls + '" value="' + value + '" id="' + c + '"' + s + ' />';
			if (type === "text") {
				if (isB) b.hide();
				d.parent().find(".icon-close.close").addClass("hide");
				d.removeAttr("id").hide();
				d.after(e);
				a.addClass("hidepwd")
			} else {
				d.prev("input").attr("id", c).val(value).show();
				if (isB && $.trim(value) === "") {
					d.prev("input").hide();
					b.show()
				}
				d.remove();
				a.removeClass("hidepwd")
			};
			$('body').on("click", "#" + c, function() {
				$(this).parent().find(".hidepwd").click();
				if (isB && $.trim($(this).val()) === "") {
					d.show();
					b.hide()
				}
				d.focus()
			})
		}
	};
	require.defaults = {};
	window.togglePwd = $.togglePwd = g
})(jQuery);


(function($) {
	var b, timerC, opt;
	var d = function(a) {
			a = $.extend(require.defaults, a || {});
			opt = a;
			d._clear();
			return (new require())._init()
		};

	function require(a) {};
	require.prototype = {
		_init: function() {
			timerC = opt.maxTime;
			this._sendVerify()
		},
		_sendVerify: function() {
			var a = this;
			if (timerC === 0) {
				d._clear();
				opt.after();
				timerC = opt.maxTime;
				return
			}
			timerC--;
			opt.ing(timerC);
			b = setTimeout(function() {
				a._sendVerify()
			}, 1000)
		}
	};
	d._clear = function() {
		clearTimeout(b)
	};
	require.defaults = {
		maxTime: 60,
		minTime: 0,
		ing: function(c) {},
		after: function() {}
	};
	window.countdown = $.countdown = d
})(jQuery);
$(function() {
	togglePwd();
	verifyCheck();
	var $email = $('#email');
	var $emailMsg = $('#emailMsg');
	var $contact = $('#contact');
	var $contactMsg = $('#contactMsg');

	$email.change(function() {

		var emailValue = $email.val();
		if(emailValue.length) { //email 不等于空，去验证，

			var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;

			if(!reg.test(emailValue)) {
				$emailMsg.html('<span>Please enter a valid email address </span>');

			} else {

				$emailMsg.html('');
			}

		}

	});

	$contact.change(function() {
		var contactMsg = $contact.val();
		if(contactMsg.length) { // 不等于空，去验证，

			var reg2 = /^[^ ][%&',;=?$\x22A-Za-z0-9 *]+$/;

			if(reg2.test(contactMsg)) { //为true

				$contactMsg.html('');
			} else {

				$contactMsg.html('<span>Unable to input Chinese</span>');
			}

		}

	});



	var str='';
	for(var i=0;i<100;i++){
		str+='<option value="'+i+'">'+i+'</option>'
	}

	$('#country').html(str);
		
	$('select').searchableSelect();
	
	
	$('.searchable-select-holder').html('');

	//第一页的确定按钮
	$("#btn_part1").click(function() {
		
	
		if(!verifyCheck._click()) return;

		// 发送一个请求，看是否注册成功
	
		//计时器
		$('#pic').show();
		$('#ok').click(function(){
				window.location.href="https://www.freshport.com";
				$('#pic').hide();
		});
		
		$('#login2').click(function(){
			
		window.location.href="https://www.freshport.com/fp-online-infoshow/member/login";
		});
	});
	

		
	var $holder=$('.searchable-select-holder');

		$('.part1').hover(function(){
	

				if( $holder.text().length){
					
					$targer.country=true;
				   $(this).off('hover');		
					
					$holder.parent().siblings('.icon-sucessfill').removeClass('hide');
	
					
					
				}
				
				if( $targer.country&& $targer.isRepeat && $targer.isUname && $targer.company ){
					
					$('.tit p').eq(2).add($('.step li').eq(2)).addClass('on');
					$('#btn_part1').addClass('green');
				}
				
	
		});

	
});


//搜索



(function($){

  // a case insensitive jQuery :contains selector
  $.expr[":"].searchableSelectContains = $.expr.createPseudo(function(arg) {
    return function( elem ) {
      return $(elem).text().toUpperCase().indexOf(arg.toUpperCase()) >= 0;
    };
  });

  $.searchableSelect = function(element, options) {
    this.element = element;
    this.options = options || {};
    this.init();

    var _this = this;

    this.searchableElement.click(function(event){
      // event.stopPropagation();
      _this.show();
    }).on('keydown', function(event){
      if (event.which === 13 || event.which === 40 || event.which == 38){
        event.preventDefault();
        _this.show();
      }
    });

    $(document).on('click', null, function(event){
      if(_this.searchableElement.has($(event.target)).length === 0)
        _this.hide();
    });

    this.input.on('keydown', function(event){
      event.stopPropagation();
      if(event.which === 13){         //enter
        event.preventDefault();
        _this.selectCurrentHoverItem();
        _this.hide();
      } else if (event.which == 27) { //ese
        _this.hide();
      } else if (event.which == 40) { //down
        _this.hoverNextItem();
      } else if (event.which == 38) { //up
        _this.hoverPreviousItem();
      }
    }).on('keyup', function(event){
      if(event.which != 13 && event.which != 27 && event.which != 38 && event.which != 40)
        _this.filter();
    })
  }

  var $sS = $.searchableSelect;

  $sS.fn = $sS.prototype = {
    version: '0.0.1'
  };

  $sS.fn.extend = $sS.extend = $.extend;

  $sS.fn.extend({
    init: function(){
      var _this = this;
      this.element.hide();

      this.searchableElement = $('<div tabindex="0" class="searchable-select"></div>');
      this.holder = $('<div class="searchable-select-holder"></div>');
      this.dropdown = $('<div class="searchable-select-dropdown searchable-select-hide"></div>');
      this.input = $('<input type="text" class="searchable-select-input" />');
      this.items = $('<div class="searchable-select-items"></div>');
      this.caret = $('<span class="searchable-select-caret"></span>');

      this.scrollPart = $('<div class="searchable-scroll"></div>');
      this.hasPrivious = $('<div class="searchable-has-privious">...</div>');
      this.hasNext = $('<div class="searchable-has-next">...</div>');

      this.hasNext.on('mouseenter', function(){
        _this.hasNextTimer = null;

        var f = function(){
          var scrollTop = _this.items.scrollTop();
          _this.items.scrollTop(scrollTop + 20);
          _this.hasNextTimer = setTimeout(f, 50);
        }

        f();
      }).on('mouseleave', function(event) {
        clearTimeout(_this.hasNextTimer);
      });

      this.hasPrivious.on('mouseenter', function(){
        _this.hasPriviousTimer = null;

        var f = function(){
          var scrollTop = _this.items.scrollTop();
          _this.items.scrollTop(scrollTop - 20);
          _this.hasPriviousTimer = setTimeout(f, 50);
        }

        f();
      }).on('mouseleave', function(event) {
        clearTimeout(_this.hasPriviousTimer);
      });

      this.dropdown.append(this.input);
      this.dropdown.append(this.scrollPart);

      this.scrollPart.append(this.hasPrivious);
      this.scrollPart.append(this.items);
      this.scrollPart.append(this.hasNext);

      this.searchableElement.append(this.caret);
      this.searchableElement.append(this.holder);
      this.searchableElement.append(this.dropdown);
      this.element.after(this.searchableElement);

      this.buildItems();
      this.setPriviousAndNextVisibility();
    },

    filter: function(){
      var text = this.input.val();
      this.items.find('.searchable-select-item').addClass('searchable-select-hide');
      this.items.find('.searchable-select-item:searchableSelectContains('+text+')').removeClass('searchable-select-hide');
      if(this.currentSelectedItem.hasClass('searchable-select-hide') && this.items.find('.searchable-select-item:not(.searchable-select-hide)').length > 0){
        this.hoverFirstNotHideItem();
      }

      this.setPriviousAndNextVisibility();
    },

    hoverFirstNotHideItem: function(){
      this.hoverItem(this.items.find('.searchable-select-item:not(.searchable-select-hide)').first());
    },

    selectCurrentHoverItem: function(){
      if(!this.currentHoverItem.hasClass('searchable-select-hide'))
        this.selectItem(this.currentHoverItem);
    },

    hoverPreviousItem: function(){
      if(!this.hasCurrentHoverItem())
        this.hoverFirstNotHideItem();
      else{
        var prevItem = this.currentHoverItem.prevAll('.searchable-select-item:not(.searchable-select-hide):first')
        if(prevItem.length > 0)
          this.hoverItem(prevItem);
      }
    },

    hoverNextItem: function(){
      if(!this.hasCurrentHoverItem())
        this.hoverFirstNotHideItem();
      else{
        var nextItem = this.currentHoverItem.nextAll('.searchable-select-item:not(.searchable-select-hide):first')
        if(nextItem.length > 0)
          this.hoverItem(nextItem);
      }
    },

    buildItems: function(){
      var _this = this;
      this.element.find('option').each(function(){
        var item = $('<div class="searchable-select-item" data-value="'+$(this).attr('value')+'">'+$(this).text()+'</div>');

        if(this.selected){
          _this.selectItem(item);
          _this.hoverItem(item);
        }

        item.on('mouseenter', function(){
          $(this).addClass('hover');
        }).on('mouseleave', function(){
          $(this).removeClass('hover');
        }).click(function(event){
          event.stopPropagation();
          _this.selectItem($(this));
          _this.hide();
        });

        _this.items.append(item);
      });

      this.items.on('scroll', function(){
        _this.setPriviousAndNextVisibility();
      })
    },
    show: function(){
      this.dropdown.removeClass('searchable-select-hide');
      this.input.focus();
      this.status = 'show';
      this.setPriviousAndNextVisibility();
    },

    hide: function(){
      if(!(this.status === 'show'))
        return;

      if(this.items.find(':not(.searchable-select-hide)').length === 0)
          this.input.val('');
      this.dropdown.addClass('searchable-select-hide');
      this.searchableElement.trigger('focus');
      this.status = 'hide';
    },

    hasCurrentSelectedItem: function(){
      return this.currentSelectedItem && this.currentSelectedItem.length > 0;
    },

    selectItem: function(item){
      if(this.hasCurrentSelectedItem())
        this.currentSelectedItem.removeClass('selected');

      this.currentSelectedItem = item;
      item.addClass('selected');

      this.hoverItem(item);

      this.holder.text(item.text());
      var value = item.data('value');
      this.holder.data('value', value);
      this.element.val(value);

      if(this.options.afterSelectItem){
        this.options.afterSelectItem.apply(this);
      }
    },

    hasCurrentHoverItem: function(){
      return this.currentHoverItem && this.currentHoverItem.length > 0;
    },

    hoverItem: function(item){
      if(this.hasCurrentHoverItem())
        this.currentHoverItem.removeClass('hover');

      if(item.outerHeight() + item.position().top > this.items.height())
        this.items.scrollTop(this.items.scrollTop() + item.outerHeight() + item.position().top - this.items.height());
      else if(item.position().top < 0)
        this.items.scrollTop(this.items.scrollTop() + item.position().top);

      this.currentHoverItem = item;
      item.addClass('hover');
    },

    setPriviousAndNextVisibility: function(){
      if(this.items.scrollTop() === 0){
        this.hasPrivious.addClass('searchable-select-hide');
        this.scrollPart.removeClass('has-privious');
      } else {
        this.hasPrivious.removeClass('searchable-select-hide');
        this.scrollPart.addClass('has-privious');
      }

      if(this.items.scrollTop() + this.items.innerHeight() >= this.items[0].scrollHeight){
        this.hasNext.addClass('searchable-select-hide');
        this.scrollPart.removeClass('has-next');
      } else {
        this.hasNext.removeClass('searchable-select-hide');
        this.scrollPart.addClass('has-next');
      }
    }
  });

  $.fn.searchableSelect = function(options){
    this.each(function(){
      var sS = new $sS($(this), options);
    });

    return this;
  };

})(jQuery);
