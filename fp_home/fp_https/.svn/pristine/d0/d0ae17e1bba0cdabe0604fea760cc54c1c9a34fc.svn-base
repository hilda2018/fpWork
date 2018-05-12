var $target = {};

(function($) {
	var h, timerC = 60,
		opt;
	var j = function(a) {
		a = $.extend(require.defaults, a || {});
		opt = a;
		$target.parent = new require();
		return $target.parent._init(a)
	};

	function require(f) {
		var g = {
	
		};
		this.rules = {
			isNonEmpty: function(a, b) {
				b = b || " ";
				if(!a.length) return b
			}
		}
	};
	require.prototype = {
		_init: function(b) {
			this.config = b;
			this.getInputs = $('#' + b.formId).find('.required:visible');
			var c = false;
			var d = this;

			$('body').on({
				blur: function(a) {
					d.formValidator($(this));
					if(b.phone && $(this).attr("id") === "phone") d._change($(this));
					b.onBlur ? b.onBlur($(this)) : ''
				},
				focus: function(a) {
					b.onFocus ? b.onFocus($(this)) : $(this).parent().find("label.focus").not(".valid").removeClass("hide").siblings(".valid").addClass("hide") && $(this).parent().find(".blank").addClass("hide") && $(this).parent().find(".close").addClass("hide")
				},
				keyup: function(a) {
					if(b.phone && $(this).attr("id") === "phone") d._change($(this))
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
			if(b === undefined) return false;
			var c = b.split('||');
			var d = a.attr('data-error');
			if(d === undefined) d = "";
			var e = d.split("||");
			var f = [];
			for(var i = 0; i < c.length; i++) {
				f.push({
					strategy: c[i],
					errorMsg: e[i]
				})
			};
			return this._add(a, f)
		},
		_add: function(a, b) {
			var d = this;
			for(var i = 0, rule; rule = b[i++];) {
				var e = rule.strategy.split(':');
				var f = rule.errorMsg;
				var g = e.shift();
				e.unshift(a.val());
				e.push(f);
				e.push(a);
				var c = d.rules[g].apply(a, e);
				if(c) {
					opt.resultTips ? opt.resultTips(a, false, c) : j._resultTips(a, false, c);
					return false
				}
			}
			opt.successTip ? (opt.resultTips ? opt.resultTips(a, true) : j._resultTips(a, true)) : j._clearTips(a);
			return true
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
			if(result) r.push(result)
		});
		if(d.length !== r.length) result = false;
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
		if(c.length > 21) c = "<span>" + c + "</span>";
		var o = a.parent().find("label.valid");
		if(!b) {
			o.addClass("error");
			a.addClass("v_error");
			if($.trim(a.val()).length > 0) a.parent().find(".close").removeClass("hide")
		} else {
			a.parent().find(".blank").removeClass("hide")
		}
		o.text("").append(c)
	};
	j.textChineseLength = function(a) {
		var b = /[一-龥]|[、-。]|[：-？]|[《-』]|[【-〕]|[–-”]|[！-．]|[〈-〉]|[…]|[￥]/g;
		if(b.test(a)) return a.match(b).length;
		else return 0
	};
	j.pwdStrong = function(a) {
		var b = 0;

		return b
	};
	require.defaults = {
		formId: 'verifyCheck',
		onChange: null,
		onFocus: null,
		onBlur: null,
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
		return(new require())._init()
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
			if(c === undefined) return false;
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
			if(type === "text") {
				if(isB) b.hide();
				d.parent().find(".icon-close.close").addClass("hide");
				d.removeAttr("id").hide();
				d.after(e);
				a.addClass("hidepwd")
			} else {
				d.prev("input").attr("id", c).val(value).show();
				if(isB && $.trim(value) === "") {
					d.prev("input").hide();
					b.show()
				}
				d.remove();
				a.removeClass("hidepwd")
			};
			$('body').on("click", "#" + c, function() {
				$(this).parent().find(".hidepwd").click();
				if(isB && $.trim($(this).val()) === "") {
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
		return(new require())._init()
	};

	function require(a) {};
	require.prototype = {
		_init: function() {
			timerC = opt.maxTime;
			this._sendVerify()
		},
		_sendVerify: function() {
			var a = this;
			if(timerC === 0) {
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



	$('#password').bind('input propertychange', function() {
		$target.password = false;
		var value = $(this).val();
		var reg = /^[A-Za-z0-9]+$/;
		if(cleanSpelChar($(this).get(0))) {
			return;
		}
		$(this).siblings('.icon-sucessfill').addClass('hide');
		$(this).siblings('.focus').removeClass('hide');
		$(this).siblings('.close').removeClass('hide');
		if(!value.length) {
			$(this).siblings('.focus').html('Please enter your password')
		} else if(!reg.test(value)) {
			$(this).siblings('.focus').html('6-20 characters, including letters/numbers, no space')
		} else if(value.length < 6) {
			$(this).siblings('.focus').html('between:6-20')
		} else if(value.length > 20) {
			$(this).siblings('.focus').html('between:6-20')
		} else {
			$(this).siblings('.icon-sucessfill').removeClass('hide');
			$(this).siblings('.focus').addClass('hide');
			$(this).siblings('.close').addClass('hide');
			$target.password = true;
	

		}
		
				checkRequire();
	});

	$('#adminNo').bind('input propertychange', function() {
		$target.username = false;
		var value = $(this).val();
		var reg = /^[A-Za-z0-9]+$/;

		$('.tit p').eq(1).add($('.step li').eq(1)).removeClass('on')
		$(this).siblings('.icon-sucessfill').addClass('hide');
		$(this).siblings('.focus').removeClass('hide');
		$(this).siblings('.close').removeClass('hide');
		if(cleanSpelChar($(this).get(0))) {
			return;
		}

		if(!value.length) {
			$(this).siblings('.focus').html('Please enter your username')
		} else if(!reg.test(value)) {
			$(this).siblings('.focus').html('5-25 characters, including letters/numbers, no space')
		} else if(value.length < 5) {
			$(this).siblings('.focus').html('between:5-25')
		} else if(value.length > 25) {
			$(this).siblings('.focus').html('between:5-25')
		} else {
			$(this).siblings('.icon-sucessfill').removeClass('hide');
			$(this).siblings('.focus').addClass('hide');
			$(this).siblings('.close').addClass('hide');

			//校验username是否重复
			var result = registration.controller.systermCheckUser(value);
		
	
			if(result) {
				$target.username = true;
				$(this).siblings('.icon-sucessfill').removeClass('hide');
				$(this).siblings('.focus').html('');
				$(this).siblings('.focus').addClass('hide');
				$(this).siblings('.close').addClass('hide');

			} else {
				$target.username = false;
				$(this).siblings('.icon-sucessfill').addClass('hide');
				$(this).siblings('.focus').html('Your username has been occupied');
				$(this).siblings('.focus').removeClass('hide');
				$(this).siblings('.close').removeClass('hide');
			}


		}
		
		checkRequire();

	});

	//邮箱校验
	$('#email').bind('input propertychange', function() {
		$target.email = false;
		var value = $(this).val();
		var reg = / ^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$  /;// 校验邮箱是否合法
		$('.tit p').eq(1).add($('.step li').eq(1)).removeClass('on')
		$(this).siblings('.icon-sucessfill').addClass('hide');
		$(this).siblings('.focus').removeClass('hide');
		$(this).siblings('.close').removeClass('hide');
		if(cleanSpelChar($(this).get(0))) {
			return;
		}

		if(!value.length) {
			$(this).siblings('.focus').html('Please enter your email')
		} else if(!reg.test(value)) {
			$(this).siblings('.focus').html('Please enter an valid Email address')
		}  else {
			$(this).siblings('.icon-sucessfill').removeClass('hide');
			$(this).siblings('.focus').addClass('hide');
			$(this).siblings('.close').addClass('hide');
			$(this).siblings('.focus').html('');
			$target.email = true;
			
		}
		
		checkRequire();

	});
	
	$('#rePassword').bind('input propertychange', function() {
		$target.rePassword = false;
		var value = $(this).val();
		var value2 = $('#password').val();
		$('.tit p').eq(1).add($('.step li').eq(1)).removeClass('on')
		if(cleanSpelChar($(this).get(0))) {
			return;
		}

		$(this).siblings('.icon-sucessfill').addClass('hide');
		$(this).siblings('.focus').removeClass('hide');
		$(this).siblings('.close').removeClass('hide');

		if(!value.length) {
			$(this).siblings('.focus').html('Please re-enter your password')
		} else if(value !== value2) {
			$(this).siblings('.focus').html('Your password and confirmation password do not match')
		} else if(value.length < 6) {
			$(this).siblings('.focus').html('between:6-20')
		} else if(value.length > 20) {
			$(this).siblings('.focus').html('between:6-20')
		} else {
			$(this).siblings('.icon-sucessfill').removeClass('hide');
			$(this).siblings('.focus').addClass('hide');
			$(this).siblings('.close').addClass('hide');
			$target.rePassword = true;
			$('.tit p').eq(1).add($('.step li').eq(1)).addClass('on')


		}
			checkRequire();
	});

	$('#company').bind('input propertychange', function() {
		$target.company = false;
		var value = $(this).val();
		var reg = /^[a-zA-Z0-9\u4e00-\u9fa5]/
		//	$('.tit p').eq(1).add($('.step li').eq(1)).removeClass('on')	    	
		$(this).siblings('.icon-sucessfill').addClass('hide');
		$(this).siblings('.focus').removeClass('hide');
		$(this).siblings('.close').removeClass('hide');
		if(cleanSpelChar($(this).get(0))) {
			return;
		}

		if(!value.length) {
			$(this).siblings('.focus').html('Please enter your company name')
		} else if(!reg.test(value)) {
			$(this).siblings('.focus').html('Please enter your company name correctly')
		}else{
			
			$(this).siblings('.icon-sucessfill').removeClass('hide');
			$(this).siblings('.focus').addClass('hide');
			$(this).siblings('.close').addClass('hide');

			//校验公司名是否重复
			var result = registration.controller.checkUserCompany(value);
		
	
			if(result) {
				$target.company = true;
				$(this).siblings('.icon-sucessfill').removeClass('hide');
				$(this).siblings('.focus').html('');
				$(this).siblings('.focus').addClass('hide');
				$(this).siblings('.close').addClass('hide');

			} else {
				$target.company = false;
				$(this).siblings('.icon-sucessfill').addClass('hide');
				$(this).siblings('.focus').html('Your company name been occupied');
				$(this).siblings('.focus').removeClass('hide');
				$(this).siblings('.close').removeClass('hide');
			}

			checkRequire();
			
			
		} 	
	});
	//目测无用
	$('#country').bind('input propertychange', function() {

		//$target.country = false;
		var value = $(this).val();

		$(this).siblings('.icon-sucessfill').addClass('hide');
		$(this).siblings('.focus').removeClass('hide');
		$(this).siblings('.close').removeClass('hide');
		if(!value.length) {
			$(this).siblings('.focus').html('Please select country / region')
		} else {
			$(this).siblings('.icon-sucessfill').removeClass('hide');
			$(this).siblings('.focus').addClass('hide');
			$(this).siblings('.close').addClass('hide');
			//$target.country = true;

		}
			checkRequire();

	});
	
	$target.companytype = true;
	$('#companytype').change(function() {
		var value = $(this).val();
		if("0" === value){
			$target.phonenum = false;
			$("#showEmail").show();
			$("#email").siblings('.icon-sucessfill').addClass('hide');
			$("#email").siblings('.focus').removeClass('hide');
			$("#email").siblings('.close').removeClass('hide');
			
			$("#phonenum").val('');
			$("#showTel").hide();
		}else if("1" === value){
			$target.email = false;
			$("#showTel").show();
			$("#phonenum").siblings('.icon-sucessfill').addClass('hide');
			$("#phonenum").siblings('.focus').removeClass('hide');
			$("#phonenum").siblings('.close').removeClass('hide');
			
			$("#email").val('');
			$("#showEmail").hide();
		}

		checkRequire();
	});
		
		$target.email = false;
	$('#email').bind('input propertychange', function() {
		
		var value = $(this).val();
		var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
		if(cleanSpelChar($(this).get(0))) {
			return;
		}

		$(this).siblings('.icon-sucessfill').addClass('hide');
		$(this).siblings('.focus').removeClass('hide');
		$(this).siblings('.close').removeClass('hide');

		if(!value.length) {
			$(this).siblings('.focus').html('Please enter your email')
		} else if(!reg.test(value)) {
			$(this).siblings('.focus').html('Please enter an valid Email address')
		} else {
			$(this).siblings('.icon-sucessfill').removeClass('hide');
			$(this).siblings('.focus').addClass('hide');
			$(this).siblings('.close').addClass('hide');
			$target.email = true;
		}
		
		checkRequire();
	});
	
	$target.phonenum = false;
	$('#phonenum').bind('input propertychange', function() {
		
		var value = $(this).val();
		var reg = /^1[3|4|5|7|8][0-9]{9}$/;
		if(cleanSpelChar($(this).get(0))) {
			return;
		}

		$(this).siblings('.icon-sucessfill').addClass('hide');
		$(this).siblings('.focus').removeClass('hide');
		$(this).siblings('.close').removeClass('hide');
		
		if(!value.length) {
			$(this).siblings('.focus').html('Please enter your phonenum')
		} else if(!reg.test(value)) {
			$(this).siblings('.focus').html('Please enter a valid phonenum')
		}  else {
			$(this).siblings('.icon-sucessfill').removeClass('hide');
			$(this).siblings('.focus').addClass('hide');
			$(this).siblings('.close').addClass('hide');
			$(this).siblings('.focus').html('');
			$target.phonenum = true;
			
		}
		
		checkRequire();
	});
	
	$target.contact = "unclick";
	//两种情况下，1，可为空，二验证通过
	$('#contact').bind('input propertychange', function() {
		$target.contact = "click";
		var reg = /^[ ]/;
		if(cleanSpelChar($(this).get(0))) {
			return;
		}

		$(this).siblings('.icon-sucessfill').addClass('hide');
		$(this).siblings('.focus').removeClass('hide');
		$(this).siblings('.close').removeClass('hide');

		if(reg.test(value)) {
			$(this).siblings('.focus').html('Special symbols are not allowed')
		} else {
			$(this).siblings('.icon-sucessfill').removeClass('hide');
			$(this).siblings('.focus').addClass('hide');
			$(this).siblings('.close').addClass('hide');
			$target.contact = true;
	
		}		
		if(!value){	
			
			$(this).siblings('.icon-sucessfill').addClass('hide');
			$(this).siblings('.focus').addClass('hide');
			$(this).siblings('.close').addClass('hide');
		}
		
		checkRequire();
	});

});

//搜索

(function($) {

	// a case insensitive jQuery :contains selector
	$.expr[":"].searchableSelectContains = $.expr.createPseudo(function(arg) {
		return function(elem) {
			return $(elem).text().toUpperCase().indexOf(arg.toUpperCase()) >= 0;
		};
	});

	$.searchableSelect = function(element, options) {
		this.element = element;
		this.options = options || {};
		this.init();

		var _this = this;

		this.searchableElement.click(function(event) {
			// event.stopPropagation();
			_this.show();
		}).on('keydown', function(event) {
			if(event.which === 13 || event.which === 40 || event.which == 38) {
				event.preventDefault();
				_this.show();
			}
		});

		$(document).on('click', null, function(event) {
			if(_this.searchableElement.has($(event.target)).length === 0)
				_this.hide();
		});

		this.input.on('keydown', function(event) {
			event.stopPropagation();
			if(event.which === 13) { //enter
				event.preventDefault();
				_this.selectCurrentHoverItem();
				_this.hide();
			} else if(event.which == 27) { //ese
				_this.hide();
			} else if(event.which == 40) { //down
				_this.hoverNextItem();
			} else if(event.which == 38) { //up
				_this.hoverPreviousItem();
			}
		}).on('keyup', function(event) {
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
		init: function() {
			var _this = this;
			this.element.hide();

			this.searchableElement = $('<div tabindex="0" class="searchable-select"></div>');
			this.holder = $('<div class="searchable-select-holder"></div>');
			this.dropdown = $('<div class="searchable-select-dropdown searchable-select-hide"></div>');
			this.input = $('<input type="text" class="searchable-select-input" playholeder="enter a keyword and search"/>');

			this.items = $('<div class="searchable-select-items"></div>');
			this.caret = $('<span class="searchable-select-caret"></span>');

			this.scrollPart = $('<div class="searchable-scroll"></div>');
			this.hasPrivious = $('<div class="searchable-has-privious">...</div>');
			this.hasNext = $('<div class="searchable-has-next">...</div>');

			this.hasNext.on('mouseenter', function() {
				_this.hasNextTimer = null;

				var f = function() {
					var scrollTop = _this.items.scrollTop();
					_this.items.scrollTop(scrollTop + 20);
					_this.hasNextTimer = setTimeout(f, 50);
				}

				f();
			}).on('mouseleave', function(event) {
				clearTimeout(_this.hasNextTimer);
			});

			this.hasPrivious.on('mouseenter', function() {
				_this.hasPriviousTimer = null;

				var f = function() {
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
			this.items.find('.searchable-select-item').eq(0).removeClass('hover');
			this.holder.text('');
			$target.country = false;
			this.holder.parent().siblings('.icon-sucessfill').addClass('hide');
		},

		filter: function() {
			var text = this.input.val();
			this.items.find('.searchable-select-item').addClass('searchable-select-hide');
			this.items.find('.searchable-select-item:searchableSelectContains(' + text + ')').removeClass('searchable-select-hide');
			if(this.currentSelectedItem.hasClass('searchable-select-hide') && this.items.find('.searchable-select-item:not(.searchable-select-hide)').length > 0) {
				this.hoverFirstNotHideItem();
			}

			this.setPriviousAndNextVisibility();
		},

		hoverFirstNotHideItem: function() {
			this.hoverItem(this.items.find('.searchable-select-item:not(.searchable-select-hide)').first());
		},

		selectCurrentHoverItem: function() {
			if(!this.currentHoverItem.hasClass('searchable-select-hide'))
				this.selectItem(this.currentHoverItem);
		},

		hoverPreviousItem: function() {
			if(!this.hasCurrentHoverItem())
				this.hoverFirstNotHideItem();
			else {
				var prevItem = this.currentHoverItem.prevAll('.searchable-select-item:not(.searchable-select-hide):first')
				if(prevItem.length > 0)
					this.hoverItem(prevItem);
			}
		},

		hoverNextItem: function() {
			if(!this.hasCurrentHoverItem())
				this.hoverFirstNotHideItem();
			else {
				var nextItem = this.currentHoverItem.nextAll('.searchable-select-item:not(.searchable-select-hide):first')
				if(nextItem.length > 0)
					this.hoverItem(nextItem);
			}
		},

		buildItems: function() {
			var _this = this;
			this.element.find('option').each(function() {
				var item = $('<div class="searchable-select-item" data-value="' + $(this).attr('value') + '">' + $(this).text() + '</div>');

				if(this.selected) {
					_this.selectItem(item);
					_this.hoverItem(item);
				}

				item.on('mouseenter', function() {
					$(this).addClass('hover');
				}).on('mouseleave', function() {
					$(this).removeClass('hover');
				}).click(function(event) {
					event.stopPropagation();
					_this.selectItem($(this));
					_this.hide();
				});

				_this.items.append(item);

			});

			this.items.on('scroll', function() {
				_this.setPriviousAndNextVisibility();
			})
		},
		show: function() {
			this.dropdown.removeClass('searchable-select-hide');
			this.input.focus();
			this.status = 'show';
			this.setPriviousAndNextVisibility();
		},

		hide: function() {
			if(!(this.status === 'show'))
				return;

			if(this.items.find(':not(.searchable-select-hide)').length === 0)
				this.input.val('');
			this.dropdown.addClass('searchable-select-hide');
			this.searchableElement.trigger('focus');
			this.status = 'hide';
		},

		hasCurrentSelectedItem: function() {
			return this.currentSelectedItem && this.currentSelectedItem.length > 0;
		},

		selectItem: function(item) {
			if(this.hasCurrentSelectedItem())
				this.currentSelectedItem.removeClass('selected');

			this.currentSelectedItem = item;
			item.addClass('selected');

			this.hoverItem(item);

			this.holder.text(item.text());
			var value = item.data('value');
			this.holder.data('value', value);
			this.element.val(value);

			if(item.text().length) {

				$target.country = true;
				this.holder.parent().siblings('.icon-sucessfill').removeClass('hide');

			}
			checkRequire();

			if(this.options.afterSelectItem) {
				this.options.afterSelectItem.apply(this);
			}
		},

		hasCurrentHoverItem: function() {
			return this.currentHoverItem && this.currentHoverItem.length > 0;
		},

		hoverItem: function(item) {
			if(this.hasCurrentHoverItem())
				this.currentHoverItem.removeClass('hover');

			if(item.outerHeight() + item.position().top > this.items.height())
				this.items.scrollTop(this.items.scrollTop() + item.outerHeight() + item.position().top - this.items.height());
			else if(item.position().top < 0)
				this.items.scrollTop(this.items.scrollTop() + item.position().top);

			this.currentHoverItem = item;
			item.addClass('hover');
		},

		setPriviousAndNextVisibility: function() {
			if(this.items.scrollTop() === 0) {
				this.hasPrivious.addClass('searchable-select-hide');
				this.scrollPart.removeClass('has-privious');
			} else {
				this.hasPrivious.removeClass('searchable-select-hide');
				this.scrollPart.addClass('has-privious');
			}

			if(this.items.scrollTop() + this.items.innerHeight() >= this.items[0].scrollHeight) {
				this.hasNext.addClass('searchable-select-hide');
				this.scrollPart.removeClass('has-next');
			} else {
				this.hasNext.removeClass('searchable-select-hide');
				this.scrollPart.addClass('has-next');

			}
		}
	});

	$.fn.searchableSelect = function(options) {
		this.each(function() {
			var sS = new $sS($(this), options);
		});

		return this;
	};

})(jQuery);

function checkRequire() {
	
	
	console.info( $target  );
	
	if($target.rePassword  && $target.username && $target.password && $target.company && $target.country 
			 && $target.companytype && ($target.contact != "click" || !$('#contact').val())
			 && (("0" === $('#companytype').val() && $target.email ) || ("1" === $('#companytype').val() && $target.phonenum ))) {

			$('.tit p').eq(2).add($('.step li').eq(2)).addClass('on');
			
			$('#btn_part1').hide(); $('#btn12').show().css("background","#8bc34A");
			$('#btn_part1').addClass('green').attr({"disabled":false,"readonly":false});
					
			$('#btn12').leanModal({top: 160,overlay: 0.45});
			
		
	} else {
		
				$('#btn_part1').show(); $('#btn12').hide();
			$('#btn_part1').removeClass('green').attr({"disabled":true,"readonly":true});
		$('.tit p').eq(2).add($('.step li').eq(2)).removeClass('on');
	
	}
}

function cleanSpelChar(th) {
	if(/["'<>%;)(&+]/.test(th.value) || /^[ ]/.test(th.value)) {
		$(th).val(th.value.replace(/["'<>%;)(&+]/, ''));
		$(th).val(th.value.replace(/^[ ]/, ''));
		return true;

	}

}



(function($){$.fn.extend({leanModal:function(options){var defaults={top:100,overlay:0.5,closeButton:null};var overlay=$("<div id='lean_overlay'></div>");$("body").append(overlay);options=$.extend(defaults,options);return this.each(function(){var o=options;$(this).click(function(e){var modal_id=$(this).attr("href");$("#lean_overlay").click(function(){close_modal(modal_id)});$(o.closeButton).click(function(){close_modal(modal_id)});var modal_height=$(modal_id).outerHeight();var modal_width=$(modal_id).outerWidth();
$("#lean_overlay").css({"display":"block",opacity:0});$("#lean_overlay").fadeTo(200,o.overlay);$(modal_id).css({"display":"block","position":"fixed","opacity":0,"z-index":11000,"left":50+"%","margin-left":-(modal_width/2)+"px","top":o.top+"px"});$(modal_id).fadeTo(200,1);e.preventDefault()})});function close_modal(modal_id){$("#lean_overlay").fadeOut(200);$(modal_id).css({"display":"none"})}}})})(jQuery);





