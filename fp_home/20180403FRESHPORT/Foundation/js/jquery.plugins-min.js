(function($) {
    var b = "rollIn fadeIn fadeInUp fadeInDown fadeInLeft fadeInRight fadeInRight bounceIn bounceInDown bounceInUp bounceInLeft bounceInRight rotateInDownLeft rotateInDownRight rotateInUpLeft rotateInUpRight".split(" "),
        d = b.length;

   $.fn.oneByOne = function(a) {
        function e(a) {
           globalOptions.slideShow && f();
           $this.stop(!0, !0).animate({
                left: -a * oneWidth
            }, globalOptions.delay, function() {
                a != t && (r = t, q[r] && (!$.browser.msie && !$.browser.opera && q[r].fadeOut(), $(".buttonArea a:eq(" + r + ")", $thisParent).removeClass("active")), $(".buttonArea a:eq(" + a + ")", $thisParent).addClass("active"),
                    "random" != globalOptions.easeType.toLowerCase() ? q[a].show().children().each(function(a) {
                        if ($(this).hasClass(globalOptions.easeType)) {
                          $(this).removeClass(globalOptions.easeType);
                          $(this).hide()
                        }
                      $(this).show().addClass("animate" + a + " " + globalOptions.easeType)
                    }) : (v = b[Math.floor(Math.random() * d)], w[a] = v, q[r] && q[r].children().each(function() {
                        if ($(this).hasClass(w[r])) {
                          $(this).removeClass(w[r]);
                          $(this).fadeOut("medium")
                        }
                    }), q[a].show().children().each(function(a) {
                      $(this).show().addClass("animate" + a + " " + v)
                    })),$this.delay(200 * q[a].children().length).queue(function() {
                    globalOptions.slideShow &&
                    h()
                }), $arrowBtton && $arrowBtton.css("cursor", "pointer"), t = a)
            })
        }

        function f() {
            clearInterval($this.data("interval"))
        }

        function h() {
            clearInterval($this.data("interval"));
            slideShowInt = setInterval(function() {
                k()
            }, globalOptions.slideShowDelay);
           $this.data("interval", slideShowInt)
        }

        function k() {
            var a = t;
            a++;
            a = a >= m ? 0 : a;
            e(a)
        }

        var globalOptions = {
            className: "oneByOne",
            sliderClassName: "oneByOne_item",
            easeType: "",
            width: 960,
            height: 420,
            delay: 300,
            tolerance: 0.25,
            enableDrag: !0,
            showArrow: !0,
            showButton: !0,
            slideShow: !1,
            slideShowDelay: 3E3
        };
        a &&$.extend(globalOptions, a);
        var   $thisParent, $this, t = -1,
            oneWidth = globalOptions.width,
            s = 0,
            i = !1,
            w = [],
            v, q = [],
            m = 0,
            r = 0,
            o, $arrowBtton;
       $this = this;
       $this.wrap('<div class="' + globalOptions.className + '"/>');
       $thisParent =$this.parent();
        $thisParent.css("overflow", "hidden");
       $this.find("." + globalOptions.sliderClassName).each(function(a) {
          $(this).hide();
            m++;
          $(this).css("left",  oneWidth * a);
            q[a] = $(this)
        });
       $this.bind("touchstart", function(ev) {
            ev.preventDefault();
           ev = ev.originalEvent.touches[0] || ev.originalEvent.changedTouches[0];
            i || (i = !0, this.mouseX = ev.pageX);
            o && o.fadeIn();
           $arrowBtton && $arrowBtton.fadeIn();
            return !1
        });
       $this.bind("touchmove", function(ev) {
            ev.preventDefault();
            ev = ev.originalEvent.touches[0] || ev.originalEvent.changedTouches[0];
            i && (s =ev.pageX - this.mouseX,$this.css("left", -t *  oneWidth + s), globalOptions.slideShow && f());
            return !1
        });
       $this.bind("touchend", function(ev) {
            var b = t;
            a.preventDefault();
            i = !1;
            if (!s) return !1;
            var a = parseInt(globalOptions.width),
                c = a / 2; - s > c - a * globalOptions.tolerance ? (b++, b = b >= m ? m - 1 : b, e(b)) : s > c - a * globalOptions.tolerance ? (b--, e(0 > b ? 0 : b)) : (e(b), globalOptions.slideShow && h());
            s = 0;
            o && o.delay(400).fadeOut();
           $arrowBtton && $arrowBtton.delay(400).fadeOut();
            return !1
        });
        globalOptions.enableDrag && ($this.mousedown(function(a) {
            i || (i = !0, $this.mouseX = ev.pageX);
            return !1
        }),$this.mousemove(function(a) {
            i && (s = ev.pageX - this.mouseX,$this.css("left", -t *  oneWidth + s),
            globalOptions.slideShow && f());
            return !1
        }),$this.mouseup(function() {
            i = !1;
            var a = t;
            if (!s) return !1;
            var b = parseInt(globalOptions.width),
                c = b / 2; - s > c - b * globalOptions.tolerance ? (a++, a = a >= m ? m - 1 : a, e(a)) : s > c - b * globalOptions.tolerance ? (a--, e(0 > a ? 0 : a)) : (e(a), globalOptions.slideShow && h());
            s = 0;
            return !1
        }),$this.mouseleave(function() {
          $(this).mouseup()
        }));
       $thisParent.mouseover(function() {
            o && o.fadeIn();
            $arrowBtton && $arrowBtton.fadeIn()
        });
       $thisParent.mouseleave(function() {
            o && o.fadeOut();
            $arrowBtton && $arrowBtton.fadeOut()
        });
        if (globalOptions.showButton) {
            a = $('<div class="buttonArea"><div class="buttonCon"></div></div>');
            $thisParent.append(a);
            o = a.find(".buttonCon");
            for (var u =
                0; u < m; u++) o.append('<a class="theButton" rel="' + u + '">' + (u + 1) + "</a>").css("cursor", "pointer");
            $(".buttonCon a:eq(" + t + ")", a).addClass("active");
            $(".buttonCon a", a).bind("click", function() {
                if ($(this).hasClass("active")) return false;
                var a = $(this).attr("rel");
                e(a)
            })
        }
        globalOptions.showArrow && ($arrowBtton = $('<div class="arrowButton"><div class="prevArrow"></div><div class="nextArrow"></div></div>'), $thisParent.append($arrowBtton), $(".nextArrow", $arrowBtton).bind("click", function() {
            k()
        }), $(".prevArrow", $arrowBtton ).bind("click", function() {
            var a = t;
            a--;
            a = a < 0 ? m - 1 : a;
            e(a)
        }));
        o && o.hide();
       $arrowBtton && $arrowBtton.hide();
        e(0);
        globalOptions.slideShow && (slideShowInt = setInterval(function() {
            k()
        }, globalOptions.slideShowDelay),$this.data("interval", slideShowInt));
        return this
    }
})(jQuery);