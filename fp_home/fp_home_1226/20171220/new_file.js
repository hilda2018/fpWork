if (/\.(\w+\.\w+)$/.test(location.host)) {
    document.domain = RegExp.$1;
}
//正则
var RE = {
    searchkey: /^[^\u4E00-\u9FA5]{1,50}$/,
    shipname: /^[\w\u4E00-\u9FA5\-\s]{1,50}$/,
    company: /^[\w\u4E00-\u9FA5\-\,\.\(\)\s（）]{2,50}$/,
    name: /^.{2,20}$/,
    mobile: /^1[34578]\d{9}$/,
    phone: /^(0\d{2,3}[^\d]+)?\d{7,8}([^\d]+\d{1,6})?$/,
    email: /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/,
    zipcode: /^\d{6}$/,
    username: /^[\w@\.]{3,50}$/,
    password: /^[a-zA-Z0-9]{6,12}$/,
    address: /^[\w\d\u4E00-\u9FA5\-\,\.\(\)\s\@\#（）]{4,50}$/,
    goods: /^[\w\d\u4E00-\u9FA5\(\)\/]+$/,
    port: /^[\w\d\u4E00-\u9FA5\- ]{2,}$/,
    date: /^[\d]{4}-[\d]{2}-[\d]{2}$/,
    datetime: /^[\d]{4}-[\d]{2}-[\d]{2} [\d]{2}:[\d]{2}:[\d]{2}$/,
    empty: /^\s*$/,
    html: /<\s*(\S+)(\s[^>]*)?>[\s\S]*<\s*\/\1\s*>/,
    zint_8: /^[1-9]\d{0,8}$/, //正数
    float_7_2: /^\d{0,5}([.]\d{1,2})?$/, //float(7位，两位小数)
    number: /^\d+$///数字
}
var isie6 = /IE 6/.test(navigator.userAgent);
if (isie6) document.execCommand('BackgroundImageCache', false, true);
//Jquery实现Ajax异步请求(清缓存，type默认post方式)
var AjaxOperate = function (url, data, success, type, error) {
    $.ajax({
        type: type || "POST",
        url: url,
        data: data,
        cache: false,
        success: success,
        error: error || function (xhr) {
        }
    });
};

//元素在数组中的索引
if (!Array.prototype.indexOf) {
    Array.prototype.indexOf = function (o) {
        for (var i = 0; i < this.length; i++) {
            if (this[i] === o) return i;
        }
        return -1;
    }
}

//根据字段和排序方式对数组排序
if (!Array.prototype.sortOn) {
    Array.prototype.sortOn = function (field, field2, type) {//type:排序方式,ase=升序、desc=降序，默认为ase
        this.sort(function (a, b) {
            var ref = field;
            type = type || 'ase';
            if (field2 != undefined) {
                if (!a[field] || !b[field] || a[field] == b[field]) {//以第二字段排序
                    ref = field2;
                }
            }
            if (a[ref] < b[ref]) return type == 'ase' ? -1 : 1;
            else if (a[ref] == b[ref]) return 0;
            else if (a[ref] > b[ref]) return type == 'ase' ? 1 : -1;
        });
    }
}

//截取字符串两端空格
if (!String.prototype.trim) {
    String.prototype.trim = function () {
        return this.replace(/(^\s*)|(\s*$)/g, "");
    }
}

//将时间数据值格式化
Date.prototype.Format = function (format) {
    if (!format) format = 'yyyy-MM-dd';
    var o = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S+": this.getMilliseconds()
    }
    if (/(y+)/.test((format))) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
};
//基于总页数的分页--示例：Pager(1,10,"Pager","FuncName")
//pageIndex:当前页码  totalPage:总页数
var Pager = function (pageIndex, totalPage, ctrId, fn) {
    if (!pageIndex) pageIndex = 1;
    //如果当前页码大于总页数，那么当前页码等于总页数
    if (pageIndex > totalPage && totalPage > 0) {
        pageIndex = totalPage;
    }
    var html = "";
    //首页
    if (pageIndex == 1) {
        html += "<em class=\"disabled\">首页</em><em class=\"disabled\">上一页</em>";
    }
    else {
        html += "<a href=\"javascript:" + fn + "(1)\">首页</a>";
        html += "<a href=\"javascript:" + fn + "(" + eval(pageIndex - 1) + ")\">上一页</a>";
    }
    //导航页码
    var index = 0;
    //是否已经显示前端符号[...]
    var flag = 0;
    for (var i = 1; i <= totalPage; i++) {
        if (i == 1 && i != pageIndex) {
            html += "<a href=\"javascript:" + fn + "(1)\">" + i + "</a>";
        }
        else {
            //默认显示5个导航页码
            if (index < 5) {
                if (eval(pageIndex - i) > 2 && eval(i + 4) < totalPage) {
                    if (flag == 0) {
                        html += " ... ";
                        flag++;
                    }
                }
                else {
                    if (i == pageIndex) {
                        html += "<em class=\"current\">" + i + "</em>";
                    }
                    else {
                        html += "<a href=\"javascript:" + fn + "(" + i + ")\">" + i + "</a>";
                    }
                    index++;
                }
            }
            else {
                //超过五条之后用省略号
                html += " ... ";
                html += "<a href=\"javascript:" + fn + "(" + totalPage + ")\">" + totalPage + "</a>";
                break;
            }
        }
    }
    //尾页
    if (pageIndex == totalPage || totalPage == 0) {
        html += "<em class=\"disabled\">下一页</em>";
        html += "<em class=\"disabled\">尾页</em>";
    }
    else {
        html += "<a href=\"javascript:" + fn + "(" + eval(pageIndex + 1) + ")\">下一页</a>";
        html += "<a href=\"javascript:" + fn + "(" + totalPage + ")\">尾页</a>";
    }
    $("#" + ctrId).html(html);
};
//得到控件值(去除前后空格)
var GetTrimVal = function (ctrId, isHtml) {
    var ctrlVal = "";
    if (isHtml) {
        ctrlVal = $("#" + ctrId).html();
    }
    else {
        ctrlVal = $("#" + ctrId).val();
    }
    return $.trim(ctrlVal);
};
//将值赋给控件
var SetVal = function (ctrId, cVal, isHtml) {
    if (isHtml) {
        $("#" + ctrId).html(cVal);
    }
    else {
        $("#" + ctrId).val(cVal);
    }
};
//设置用户输入的文本去除空格
var SetInputTrim = function (ctrId, cVal, isHtml) {
    SetVal(ctrId, GetTrimVal(ctrId, isHtml), isHtml);
};

//常用函数
function G(s) { return document.getElementById(s) }
function E(o, e, f) { if (!e) { e = 'load'; f = o; o = window } if (window.attachEvent) { o.attachEvent('on' + e, f) } else { o.addEventListener(e, f, false) } };
function offset(o) { var x = y = 0; while (o) { x += o.offsetLeft; y += o.offsetTop; o = o.offsetParent } return { x: x, y: y } }
function json(s) { try { return eval('(' + s + ')') } catch (e) { } }
function setCookie(name, value) { var t = new Date(); t.setTime(t.getTime() + 30 * 24 * 3600 * 1000); document.cookie = escape(name) + "=" + escape(value) + ";path=/;expires=" + t.toGMTString(); }
function getCookie(name) { if (new RegExp("\\b" + name + "=([^;]+)", "g").test(document.cookie)) return unescape(RegExp.$1 || ''); return ''; }
function setWheel(o, f) { o.onmousewheel = function (e) { e = e || event; f(e.wheelDelta > 0 || e.detail < 0, e); if (e.preventDefault) e.preventDefault(); e.cancelBubble = true; return false; }; if (o.addEventListener) o.addEventListener("DOMMouseScroll", o.onmousewheel, false) }

function Ajax(o) {
    var b = /POST/i.test(o.type), p = o.data || '', t = o.dataType || 'json', url = o.url || location.href, q = /\?/.test(url) ? '&' : '?',
    x = window.XMLHttpRequest ? new XMLHttpRequest() : (new ActiveXObject('Msxml2.XMLHTTP') || new ActiveXObject('Microsoft.XMLHTTP')),
    z = function (s) { if (x.readyState == 4) { if (x.status == 200) { s = x.responseText; if (t == 'json') s = json(s); if (b = o.success) b(s) } else if (o.error) { o.error(x.status, x) } } };
    x.onreadystatechange = z; if (typeof p == 'object') { var r = []; for (var k in p) r.push(encodeURIComponent(k) + '=' + encodeURIComponent(p[k])); p = r.join('&') }
    x.open(b ? 'POST' : 'GET', url + (b ? '' : ((p ? q : '') + p + (o.cache ? '' : (((!p && q == '?') ? '?' : '&') + '_=' + new Date().getTime())))), true);
    if (b) x.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    x.send(p); return x;
}
function loadScript(url, callback) {
    var c = document.createElement('script');
    c.src = url;
    if (callback) {
        if (/IE (6|7|8)/.test(navigator.userAgent)) {
            c.onreadystatechange = function () { if (/loaded|complete/.test(c.readyState)) callback(1) }
        } else {
            c.onload = function () { callback(1) }
        }
        c.onerror = function () { callback() }
    }
    document.body.appendChild(c);
}

Lib = {
    //模板替换
    rend: function (o, s) { return s.replace(/\{\w+\}/g, function (s) { s = o[s.replace(/\{|\}/g, '')]; if (s == undefined) s = ''; return s; }) },
    //对过长字符串截取
    cut: function (s, l, z) {
        var c, m, n, v, w, s, r = /[A-Z\u4e00-\u9fa5]/;
        if (z == undefined) z = '..';
        w = z.length;
        if (l <= w) return s.substr(0, l);
        if (s.replace(/[A-Z\u4e00-\u9fa5]/g, '11').length <= l) return s;
        m = ''; n = 0;
        for (var j = 0; j < s.length; j++) {
            v = s.charAt(j);
            n += r.test(v) ? 2 : 1;
            if (n + w > l) break;
            m += v;
        }
        return m + z;
    },
    //对过长字符串截取并提示
    title: function (o) {
        var c, l, d, s, r = /[A-Z\u4e00-\u9fa5]/g, a = (o || document).getElementsByTagName('*');
        for (var i = 0; i < a.length; i++) {
            if ((c = a[i]) && (l = c.getAttribute('len'))) {
                s = c.innerHTML.trim().replace(/&amp;/g, '&').replace(/&lt;/g, '<').replace(/&gt;/g, '>').replace(/&nbsp;/g, ' ');
                if (s.replace(r, '11').length <= l) continue;
                if (!c.title && !c.getAttribute('xtitle')) c.title = s;
                c.innerHTML = this.cut(s, l);
            }
        }
        Lib.xtitle(o); //顺便更新一下xtitle
    },
    //替换特殊提示
    xtitle: function (c) {
        var o, a = (c || document).getElementsByTagName('*');
        for (var i = 0; i < a.length; i++) {
            o = a[i];
            if (o.getAttribute('xtitle')) {
                o.onmouseover = function () { XTitle(this) }
            }
        }
    },
    //自动完成
    suggest: function (o, b, f) {
        if (!o) return;
        var c, e, a = o.childNodes, l = a.length;
        for (var i = 0; i < l; i++) { c = a[i]; if (c.className == 'on') { c.className = ''; e = c; break; } }
        a = e ? c[b ? 'nextSibling' : 'previousSibling'] : a[b ? 0 : l - 1];
        if (a) a.className = 'on';
        if (f) f(a);
    },
    //格式化日期
    formatDate: function (time) {
        return this.formatTime(time, 10);
    },
    //格式化时间
    formatTime: function (time, len) {
        if (!time) time = new Date().getTime() / 1000;
        var t = new Date(parseFloat(time) * 1000);
        var m = t.getMonth() + 1, d = t.getDate(), H = t.getHours(), M = t.getMinutes(), S = t.getSeconds();
        if (m < 10) m = '0' + m;
        if (d < 10) d = '0' + d;
        if (H < 10) H = '0' + H;
        if (M < 10) M = '0' + M;
        if (S < 10) S = '0' + S;
        t = [t.getFullYear(), '-', m, '-', d, ' ', H, ':', M, ':', S].join('');
        if (len) t = t.substr(0, len);
        return t;
    },
    getTime: function (s) {
        return Math.round(new Date(s.replace(/-/g, '/')).getTime() / 1000);
    },
    page: function (p) {
        var sum = p.sum || 0, now = p.now || 1, step = p.step || 3, href = p.href || '', prev = p.prev || '上页', next = p.next || '下页';
        if (p.size) { sum = Math.floor(p.total / p.size); if (p.total % p.size) sum++; }
        if (sum <= 1) return '';
        var end, ret = '', len = step * 2 + 1, start = now - step, each = function (page, text) {
            if (page === '') return '<span>' + text + '</span> ';
            if (page < 1 || page > sum) return '';
            if (page == now) return text ? ('<span>' + text + '</span> ') : ('<b>' + page + '</b> ');
            return '<a href="' + href.replace(/\{0\}/g, page) + '">' + (text || page) + '</a> ';
        };
        if (start < 1) start = 1;
        if (start + len > sum) start = sum - len;
        end = start + len;
        if (end > sum) end = sum;
        if (now == 1) ret += each('', prev);
        if (now > 1) ret += each(now - 1, prev);
        if (start > 1) ret += each(1);
        if (start > 2) ret += each(Math.floor((1 + start) / 2), '...');
        for (var i = start; i < end; i++) ret += each(i);
        if (end < sum) ret += each(Math.floor((sum + end) / 2), '...');
        if (end < sum + 1) ret += each(sum);
        if (now < sum) ret += each(now + 1, next);
        if (now == sum) ret += each('', next);
        return '<div class="xpage1">' + ret + '</div>';
    }
}

//表单相关
Form = {
    ie6: /IE 6/.test(navigator.userAgent),
    //json化表单
    json: function (form) {
        var c, d, v, r = {};
        for (var i = 0; i < form.length; i++) {
            c = form[i];
            if (d = (c.name || c.id)) {
                if (c.name && c.name == '!') continue;
                v = c.value || '';
                if (c.type == 'checkbox') v = c.checked ? 1 : '';
                r[d] = v;
            }
        }
        return r;
    },
    //提交表单
    submit: function (form, callback, error) {
        if (!Check.check(form)) return;
        var data = this.json(form);
        var cmd = data.cmd;
        delete (data.cmd);
        if (form.lock) return;
        form.lock = 1;
        Ajax({
            type: 'POST',
            url: cmd || form.action,
            dataType: 'json',
            data: data,
            success: function (r) {
                form.lock = 0;
                if (callback) callback(r);
            },
            error: function (r) {
                form.lock = 0;
                if (error) error(r);
            }
        })
    }
}

//跨框架弹出层
XPicker = {
    ie6: /IE 6/.test(navigator.userAgent),
    show: function (o, p) {
        var _ = this, a, c, x = y = 0, m = _.create(), p = p || {};
        _.onhide = _.onshow = _.x = _.y = _.s = '';
        for (var k in p) _[k] = p[k];
        _.o = o;
        top.CurrentPicker = o;
        _.cframe(o);
        if (_.ie6) { _.iframe.style.width = 0; _.iframe.style.height = 0; }
        c = _.picker;
        c.innerHTML = _.s || '';
        m.style.display = '';
        a = _.elems;
        for (var i = 0; i < a.length; i++) {
            c = a[i].getBoundingClientRect();
            x += c.left;
            y += c.top;
        }
        x = x + (_.x || 0);
        y = y + (_.y || 0) + o.offsetHeight + Math.max(top.document.body.scrollTop, top.document.documentElement.scrollTop);
        if (_.ie6) { x -= 2; y -= 2 };
        var w = top.document.body.clientWidth;
        var w2 = m.childNodes[0].offsetWidth;
        if (w > w2 && x + w2 > w) x = w - w2;
        m.style.left = x + 'px';
        m.style.top = y + 'px';
        if (c = _.iframe) { c.style.width = _.picker.offsetWidth + 'px'; c.style.height = _.picker.offsetHeight + 'px'; }
        if (o.tagName == 'INPUT' && !o.hasPicker) {
            o.hasPicker = true;
            E(o, 'blur', function (e) { if (_.lock) return; if (e && (e.target || e.srcElement) != _.o) return; _.hide() });
            E(o, 'keyup', function (e) { if ((e || event).keyCode != 13 && _.ishide() && o.onfocus) o.onfocus() });
            if (!o.onmousedown) o.onmousedown = function (e) { if (!_.ishide()) (e || event).cancelBubble = true; };
        }
        if (_.onshow) _.onshow(_.picker);
        top.$('.xwin_mask').css({ height: top.$(top.document).height() }); //防止遮罩出现空白
    },
    hide: function (e) {
        var _ = this;
        if (e && (e.target || e.srcElement) == _.o) return;
        if (_.onhide) _.onhide();
        if (_.m) _.m.style.display = 'none';
    },
    ishide: function () { var _ = this; if (!_.m) return true; return _.m.style.display == 'none' },
    create: function () {
        var _ = this, a, c, m = _.m;
        if (m) return m;
        m = top.document.createElement('div');
        m.style.left = m.style.top = '0';
        m.style.position = 'absolute';
        m.style.zIndex = '100000';
        m.innerHTML = '<div style="background:white;border:1px solid #5072A9;background:#F5F9FC;-moz-box-shadow:3px 3px 5px #aaa;box-shadow:3px 3px 5px #aaa;"></div>';
        if (_.ie6) m.innerHTML += '<iframe style="position:absolute;width:0;height:0;left:0;top:0;background:white;z-index:-1;" frameborder="0"></iframe>';
        top.document.body.appendChild(m);
        a = m.childNodes;
        c = a[0];
        if (_.ie6) _.iframe = a[1];
        m.style.display = 'none';
        m.onclick = function (e) {
            e = e || top.event;
            e = e.srcElement || e.target;
            if (e && e.getAttribute('selected')) { if (_.onselect) _.onselect(e); else { top.CurrentPicker.value = e.innerHTML; _.hide(); return false } };
            if (e.tagName == 'A') return false;
        }
        _.m = m;
        _.picker = c;
        E(m, 'mousedown', function (e) { _.lock = true; e.cancelBubble = true; });
        E(document, 'mousedown', function (e) { _.lock = false; _.hide(e || event); });
        return m;
    },
    cframe: function (o) {
        var _ = this, c = window, r = [o];
        while (true) {
            var p = c.parent;
            if (c == p) break;
            E(p.document.body, 'mousedown', function (e) { _.lock = false; _.hide(e || p.event) });
            var u = c.location.href.split('/');
            u = u[u.length - 1];
            var a = p.document.getElementsByTagName('iframe');
            for (var i = 0; i < a.length; i++) {
                var d = a[i], v = d.src.split('/');
                if (v[v.length - 1] == u) { r.push(d); break; }
            }
            c = p;
        }
        _.elems = r;
    }
}

//数据验证
Check = {
    ie6: /IE 6/.test(navigator.userAgent),
    check: function (a) {
        this.hide();
        if (a && !a.length) return this.chk(a);
        if (!a) a = document.forms[0] || [];
        for (var i = 0; i < a.length; i++) if (!this.chk(a[i])) return false;
        return true;
    },
    chk: function (o) {
        if (o.getAttribute('uncheck')) return true;
        var b, c, d, e, s = o.value, a = (o.getAttribute('check') || '').split('||'), p = a.pop();
        if (/^\{/.test(p)) { p = eval('(' + p + ')'); } else { a.push(p); p = {} };
        b = a.length % 2;
        if (b && !s.replace(/\s+/g, '').length) { e = a[0]; } else {
            for (var j = b; j < a.length; j += 2) {
                c = a[j]; d = a[j + 1];
                if (/^\[(.*),(.*)\]/.test(c)) {
                    b = RegExp.$1; c = RegExp.$2;
                    if (!s || isNaN(s) || (!(/\./.test(b) || /\./.test(c[1])) && /\./.test(s)) || (b && parseFloat(s) < parseFloat(b)) || (c && parseFloat(s) > parseFloat(c))) e = d;
                } else { b = 0; if (/^\/.+/.test(c)) { b = eval(c) } else { b = RE[c] } if ((b && !b.test(s)) || (!b && !eval(c.replace(/this.value/g, '"' + s + '"')))) e = d; }
                if (e) break;
            }
        }
        if (e) { if (p.f) { p.f(o, e, p) } else { this.msg(o, e, p) } };
        return e ? false : true;
    },
    msg: function (o, s, p) {
        var _ = this, x = y = 0, m, c = offset(o), p = p || {}, b = p.z || 0, t = _.tips(), w = document.documentElement.clientWidth;
        if (!c.x && !c.y) { alert(s); return };
        try { o.focus(); } catch (e) { alert(s); return };
        if (!o.close) { var f = function () { _.hide() }; E(o, 'blur', f); if (!p.keep) E(o, 'click', f); o.close = 1 };
        t.b.style.display = b == 0 ? '' : 'none';
        t.r.style.display = b == 1 ? '' : 'none';
        t.t.style.display = b == 2 ? '' : 'none';
        if (b == 0) m = t.b;
        if (b == 2) m = t.t;
        if (m) m.style.left = '5px';
        t.o.innerHTML = s;
        t.style.display = '';
        if (b == 0) y = o.offsetHeight + 5;
        if (b == 1) x = o.offsetWidth + 5;
        if (b == 2) y = -t.offsetHeight - 5;
        x = c.x + x + (p.x || 0);
        y = c.y + y + (p.y || 0);
        b = t.o.offsetWidth;
        if (m && (x + b > w)) { m.style.left = x + b - w + 5 + 'px'; x = w - b; }
        t.style.left = x + 'px';
        t.style.top = y + 'px';
    },
    hide: function () { if (this.t) this.t.style.display = 'none' },
    tips: function () {
        var _ = this, t = _.t;
        if (!t) {
            t = document.createElement('DIV');
            t.style.display = 'none';
            t.style.position = 'absolute';
            t.style.zIndex = '1000000000';
            if (/IE (6|7)/.test(navigator.userAgent)) t.style.height = '22px';
            t.innerHTML = '<div style="position:relative;background:#FFFDEB;color:#f00;border:solid 1px #EDCE70;cursor:default;padding:3px 5px !important;padding:3px 5px 1px 5px;line-height:1.3em;_position:absolute;white-space:nowrap;'
                    + '-moz-border-radius:3px;border-radius:3px;-moz-box-shadow:1px 1px 3px #EDCE70;box-shadow:1px 1px 3px #EDCE70;"></div>'
                    + '<div style="top:-10px;left:5px;position:absolute;width:0;height:0;overflow:hidden;border:5px solid transparent;_border-color:tomato;border-bottom-color:#EDCE70;_filter:chroma(color=tomato);display:none;"></div>'
                    + '<div style="top:6px;left:-10px;position:absolute;width:0;height:0;overflow:hidden;border:5px solid transparent;_border-color:tomato;border-right-color: #EDCE70;_filter:chroma(color=tomato);display:none;"></div>'
                    + '<div style="bottom:-10px;left:5px;position:absolute;width:0;height:0;overflow:hidden;border:5px solid transparent;_border-color:tomato;border-top-color:#EDCE70;_filter:chroma(color=tomato);display:none;"></div>'
            document.body.appendChild(t);
            var a = t.childNodes;
            _.t = t; t.o = a[0]; t.b = a[1]; t.r = a[2]; t.t = a[3];
        }
        return t;
    }
}

//文本框占位
PlaceHolder = function () { this.init.apply(this, arguments) };
PlaceHolder.prototype = {
    init: function (input, text, color) {
        if (typeof input == 'string') input = document.getElementById(input);
        var that = this; this.input = input; this.setText(text || input.getAttribute('placeholder') || '');
        input.onfocus = function () { if (!this.value || this.value == that.text) this.value = ''; this.style.color = color || 'black' };
        input.onblur = function () { if (!this.value) this.value = that.text; if (this.value == that.text) this.style.color = '#ccc' };
        input.onblur();
    },
    setText: function (text) { this.text = text; if (this.input.style.color) this.input.value = this.text }
}

//自定义提示
function XTitle(o, s, b) {
    var _ = arguments.callee;
    if (!b) { clearTimeout(_.mo); _.mo = setTimeout(function () { _(o, s, 1) }, 300); return; }
    var m = _.m, p = o.getBoundingClientRect(), x = p.left, y, w, h,
        st = document.body.scrollTop || document.documentElement.scrollTop, bw = document.body.clientWidth, bh = document.body.clientHeight + st;
    if (!s) s = o.getAttribute('xtitle') || '';
    if (!m) {
        m = document.createElement('div');
        m.style.position = 'absolute';
        m.innerHTML = '<div style="border:1px solid #000;background:#FFFFE1;padding:0 3px;font:12px/1.3 arial;z-index:99999999;'
            + 'box-shadow:3px 3px 3px gray;-webkit-box-shadow:3px 3px 3px gray;-moz-box-shadow:3px 3px 3px gray;-ms-box-shadow:3px 3px 3px gray;"></div>';
        m.style.display = 'none';
        document.body.appendChild(m);
        _.m = m;
    }
    if (!o.onmouseout) o.onmouseout = function () { m.style.display = 'none'; clearTimeout(_.mo); clearTimeout(_.mo2); }
    m.childNodes[0].innerHTML = s;
    m.style.display = '';
    w = m.offsetWidth;
    h = m.offsetHeight;
    if (x + w > bw) x = bw - w;
    y = p.top + o.offsetHeight + st;
    if (y + h > bh) y = p.top + st - h;
    m.style.left = x + document.body.scrollLeft + 'px';
    m.style.top = y + 'px';
    m.n = 0;
    m.style.opacity = 0;
    m.style.filter = 'alpha(opacity=0)';
    clearInterval(_.mo2);
    _.mo2 = setInterval(function () {
        _.m.n += 0.1;
        if (_.m.n >= 1) { _.m.n = 1; clearInterval(_.mo2); }
        m.style.opacity = _.m.n;
        m.style.filter = 'alpha(opacity=' + (_.m.n * 100) + ')';
    }, 20);
}

//港口操作
XPort = {
    hash: {},
    //自动完成
    autoSearch: function (o) {
        if (o.isAutoSearch) return;
        o.isAutoSearch = true;
        o.onkeyup = function (e) {
            e = e || event;
            var n = e.keyCode;
            if (/^(9|13|16|37|38|39|40)$/.test(n) || !window.XPicker) return;
            var o = XPicker.m;
            if (!o || o.style.display) return;
            o.style.display = '';
            o = o.getElementsByTagName('LI')[0];
            this.defaultValue = this.value;
            var s = XPort.getSuggestHTML(this.value);
            if ((e = G('portSuggest')) || (e = top.G('portSuggest'))) e.parentNode.removeChild(e);
            o.style.display = s ? '' : 'none';
            o.innerHTML = '<b style="color:#f60">搜索</b><span id="portSuggest">' + s + '</span>';
        }
        o.onkeydown = function (e) {
            e = e || event;
            var n = e.keyCode, input = this;
            input.autocomplete = 'off';
            if (n == 13) XPicker.hide();
            if ((n == 38 || n == 40) && !XPicker.ishide()) {
                Lib.suggest(top.G('portSuggest'), n == 40, function (a) {
                    input.value = a ? a.innerHTML : input.defaultValue;
                });
            }
        }
    },
    //模糊搜索
    getSuggestHTML: function (s, b) {
        var s = s.replace(/\s+/g, '').toLowerCase();
        if (!s) return '';
        var _ = arguments.callee, c, n = 0, r = _.data, ret = [];
        if (!r) {
            r = [];
            var a = window.portlist || [];
            for (var i = 0; i < a.length; i++) {
                c = a[i].nodes;
                for (var j = 0; j < c.length; j++) {
                    r.push(c[j]);
                }
            }
            _.data = r;
        }
        for (var i = 0; i < r.length; i++) {
            c = r[i];
            if ((c.name && c.name.indexOf(s) != -1) || (c.py && c.py.indexOf(s) != -1) || (c.pinyin && c.pinyin.indexOf(s) != -1)) {
                if (b && n > 10) break;
                n++;
                ret.push(b ? c.name : ('<a href="" selected="1">' + c.name + '</a>'));
            }
        }
        return b ? ret : ret.join('');
    },
    //高亮已经选择的港口
    highLight: function (m, s) {
        var html = m.innerHTML.replace(/ style="color:\s?red[;]?"/ig, '');
        if (s) {
            s = '">' + s + '</';
            html = html.replace(s, '" style="color:red' + s);
        }
        m.innerHTML = html;
    },
    //获取港口名称
    getPortName: function (portid) {
        if (!this.portHash) this.getPortHTML();
        return this.portHash[portid] || '';
    },
    //获取港口
    getPortHTML: function (fix, flag, templete) {
        var _ = this, b, c, d, a = window.portlist || [], s = '';
        if (!templete) templete = '<a href="" portid="{id}" selected="1">{name}</a>';
        for (var i = 0; i < a.length; i++) {
            d = a[i];
            b = !flag && d.name == '台湾';
            if (!b) {
                s += Lib.rend(d, '<li' + ((i % 2) ? ' class="bg"' : '') + '><b>{name}</b>');
            }
            c = a[i].nodes;
            for (var j = 0; j < c.length; j++) {
                d = c[j];
                if (!_.portHash) _.portHash = {};
                _.portHash[d.id] = d.name;
                _.hash[d.name] = d;
                if (b || (!flag && !d.name)) continue;
                s += Lib.rend(d, templete);
            }
            s += '</li>';
        }
        if (!s) return '暂无港口数据';
        s = '<ul class="xports"><li style="display:none;background:#ffe;color:red;border-bottom:1px solid #ddd;"></li>' + s + '</ul>';
        if (fix) s = '<div style="width:490px;">' + s + '</div>';
        return s;
    }
}

var businessTypes = [
    { TypeName: '船东' },
    { TypeName: '货主' },
    { TypeName: '货代' },
    { TypeName: '物流贸易' },
    { TypeName: '保险金融' },
    { TypeName: '船舶经纪人' },
    { TypeName: '船舶管理' },
    { TypeName: '船代' },
    { TypeName: '码头' },
    { TypeName: '仓储/场站' },
    { TypeName: '政府机构' },
    { TypeName: '法律服务' },
    { TypeName: '船舶服务' },
    { TypeName: '船舶制造/维修' },
    { TypeName: '船舶注册/入级' },
    { TypeName: '油料供应' },
    { TypeName: '船员/家属' },
    { TypeName: '其他' }
]

//业务函数
var Fn = {
    shipHolderText: '请输入船名、呼号、MMSI或IMO',
    //业务类型
    getBusinessType: function (dlId, ddName, checks) {
        var dd = "";
        $.each(businessTypes, function (i, n) {
            dd += '<dd>';
            dd += '<span class="regCheck">';
            dd += '<input name="' + ddName + '" type="checkbox" value="' + n.TypeName + '"';
            if (checks && checks.indexOf(n.TypeName) > -1) {
                dd += ' checked="checked"';
            }
            dd += ' />';
            dd += '</span>';
            dd += n.TypeName;
            dd += '</dd> ';
        });
        $("#" + dlId + " dd").remove();
        $("#" + dlId).append(dd);
    },
    //搜索船舶
    searchShip: function (form) {
        var kw = G('kw').value;
        if (kw == Fn.shipHolderText) G('kw').value = kw = '';
        if (!Check.check(form)) return;
        location.href = '/Monitor?kw=' + encodeURIComponent(kw);
    },
    //转换URL
    jsonURL: function (s) {
        var c, r = {}, s = s || location.search, a = s.match(/\w+=[^&]*/g) || [];
        for (var i = 0; i < a.length; i++) {
            c = a[i].split('=');
            r[decodeURIComponent(c[0])] = decodeURIComponent(c[1] || '');
        }
        return r;
    },
    //解析验证字符串
    parseToken: function (s) {
        var arr = (s || '').match(/(\w+);ship.host=(.*);ship.port=(.*);ship.obj=(.*)/i);
        if (!arr) return;
        var key = arr[1];
        var url = arr[2] + ':' + arr[3] + arr[4];
        return { key: key, url: url }
    },
    //获取日期
    getDate: function (days, time) {
        if (typeof time == 'string') time = new Date(time.replace(/-/g, '/'));
        return new Date((time || new Date()).getTime() + (days || 0) * 24 * 3600 * 1000).Format('yyyy-MM-dd');
    },
    //验证受载日期、空船日期
    checkLaydays: function (s, p) {
        var days = 62; //即日起延后天数
        var t = new Date(s.replace(/-/g, '/')), n = new Date(), y = n.getFullYear(), m = n.getMonth(), d = n.getDate();
        if (t < new Date(y, m, d) || t > new Date(y, m, d + parseInt(p || 0) + days)) return false;
        return true;
    },
    //导航栏样式标注
    navTab: function (value) {
        var id = "tab" + value;
        $(".nav a").each(function () {
            if (this.id == id) {
                $(this).removeClass("noSelect");
                $(this).addClass("navSelect");
            }
            else {
                $(this).removeClass("navSelect");
                $(this).addClass("noSelect");
            }

        });
    },
    //检测表单元素内容是否改变
    formChange: function (form) {
        for (var i = 0; i < form.length; i++) {
            var o = form[i];
            if (o.tagName == "INPUT" && o.type != "checkbox" && o.type != "radio" && o.value !== o.defaultValue) return true;
        }
    },
    //比较起始日期是否小于截止日期
    CompareDate: function (s1, s2) {
        var date1 = new Date(Date.parse(s1.replace(/-/g, "/")));
        var date2 = new Date(Date.parse(s2.replace(/-/g, "/")));
        return date1 <= date2;
    },
    //倒计时
    leftTimes: function (s) {
        if (s < 0) return;
        var d, h, m, z = 24 * 3600;
        d = Math.floor(s / z); s -= d * z; z = 3600;
        h = Math.floor(s / z); s -= h * z; z = 60;
        m = Math.floor(s / z);
        s -= m * z;
        return { d: d, h: h, m: m, s: parseInt(s) };
    },
    //更新父页面高度
    updateHeight: function (win, o) {
        if (!win) return;
        var h = o ? o.scrollHeight : Math.max(document.documentElement.scrollHeight, document.body.scrollHeight);
        if (h > 0) { win.el.body.style.height = win.el.content.style.height = win.el.lock.style.height = h + 'px'; return };
        setTimeout(function () { Fn.updateHeight(win) }, 100);
    },
    //获取常用货种
    getNormalCargo: function () {
        var _ = this, b, c, d, r = [], a = window.cargoTypes || [];
        for (var i = 0; i < a.length; i++) {
            c = a[i];
            if (c.cy) {
                b = 1;
                for (var j = 0; j < r.length; j++) {
                    d = r[j];
                    if (c.cy > d.cy) break;
                }
                r.splice(j, 0, c);
            }
        }
        return r.reverse();
    },
    //货种自动完成
    getCargoSuggest: function (s, b) {
        var s = s.replace(/\s+/g, '');
        if (!s) return '';
        var _ = arguments.callee, c, r = _.data, ret = [], n = 0;
        if (!r) {
            r = [];
            var a = window.cargoTypes || [];
            for (var i = 0; i < a.length; i++) {
                c = a[i];
                if (c.pid != 0) r.push(c);
            }
            _.data = r;
        }
        for (var i = 0; i < r.length; i++) {
            c = r[i];
            if ((c.TypeName && c.TypeName.indexOf(s) != -1) || (c.py && c.py.indexOf(s) != -1) || (c.pinyin && c.pinyin.indexOf(s) != -1)) {
                ret.push(b ? c.TypeName : ('<a href="" selected="1">' + c.TypeName + '</a>'));
                if (n == 10) break;
                n++;
            }
        }
        return b ? ret : ret.join('');
    },
    //绑定船舶类型下拉列表
    bindShipTypes: function (sel) {
        var a = window.shipTypes || [];
        for (var i = 0; i < a.length; i++) {
            var c = a[i].TypeName;
            sel[sel.length] = new Option(c, c);
        }
    },
    //选择港口
    selectPort: function (input, type) {
        XPort.autoSearch(input);
        XPicker.show(input, { s: XPort.getPortHTML(1) });
        XPort.highLight(XPicker.picker, input.value);
    },
    //选择联系人
    selectContact: function (input, option) {
        var p = {};
        if (option.x) p.x = option.x;
        XPicker.show(input, p);
        top.XContact.init({
            container: XPicker.picker,
            model: option.model,
            onconfirm: function (r) {
                XPicker.hide();
                option.onconfirm(r);
            },
            oncancel: function (r) {
                XPicker.hide();
            }
        });
    },
    //闪烁
    light: function (a, p) {
        var _ = this, o, p = p || {}, color = p.color || '#fff', background = p.background || '#f60';
        a.n = 0;
        if (!p.times) p.times = 4;
        clearInterval(a.mo);
        a.mo = setInterval(function () {
            if (a.n > p.times) {
                clearInterval(a.mo);
                if (p.callback) p.callback();
            }
            o = a.n % 2;
            a.style.color = o ? '' : color;
            a.style.backgroundColor = o ? '' : background;
            a.n++;
        }, p.delay || 300);
        if ((o = _.ta) && o != a) {
            clearInterval(o.mo);
            o.style.color = '';
            o.style.backgroundColor = '';
        }
        _.ta = a;
        if (!p.nofocus) a.focus();
    },
    //更新页脚位置
    resizeFooter: function () {
        var o = G('footer');
        if (!o) return;
        var h = $('.header').height() + $('.content').height() - document.body.clientHeight;
        o.style.position = (h > (isie6 ? -60 : -38)) ? '' : (isie6 ? 'absolute' : 'fixed');
    },
    //转换URL
    getParam: function (s) {
        var c, r = {}, s = s || location.search, a = s.match(/\w+=[^&]*/g) || [];
        for (var i = 0; i < a.length; i++) {
            c = a[i].split('=');
            r[decodeURIComponent(c[0])] = decodeURIComponent(c[1] || '');
        }
        return r;
    }
}

//转换货种数据
function convertCargoType() {
    var c, d, e, id = 0, a = window.cargoTypes || [], l = a.length;
    for (var i = 0; i < l; i++) {
        c = a[i];
        if (c.aid && !c.bid && !c.cid) {
            c.id = ++id;
            c.pid = 0;
            for (var j = 0; j < l; j++) {
                d = a[j];
                if (d.aid == c.aid && d.bid && !d.cid) {
                    d.id = ++id;
                    d.pid = c.id;
                    for (var k = 0; k < l; k++) {
                        e = a[k];
                        if (e.aid == d.aid && e.bid == d.bid && e.cid) {
                            e.id = ++id;
                            e.pid = d.id;
                        }
                    }
                }
            }
        }
    }
    for (var i = 0; i < l; i++) {
        c = a[i];
        delete (c.aid);
        delete (c.bid);
        delete (c.cid);
    }
    window.cargoTypes = a;
}

//余额不足提醒
function payTip(yebz) {
    //if (!getCookie('pay_tip')) return;
    if (!G('pay_tip')) {
        var c, o = G('headerMain');
        if (!o) return;
        c = document.createElement('div');
        c.innerHTML = ''
        + '<div id="pay_tip" class="xhzh" style="position:absolute;top:60px;right:55px;width:165px;height:60px;z-index:10;">'
        + '<div class="s"><i></i>'
        + '<s class="n"></s><s class="s"></s><s class="w"></s><s class="e"></s>'
        + '<s class="ne"></s><s class="se"></s><s class="nw"></s><s class="sw"></s>'
        + '<s class="nr"></s>'
        + '<a href="" onclick="G(\'pay_tip\').style.display=\'none\';setCookie(\'pay_tip\',\'\');return false" title="关闭"></a>'
        + '</div>'
        + '<div class="x">'
        + '<div style="padding:10px 0 0 10px;">余额不足<br>请及时充值</div>'
        + '</div>'
        + '</div>';
        o.appendChild(c);
    }
    if (yebz) {
        $('#pay_tip').show();
    } else {
        $('#pay_tip').hide();
    }
}

//小提示
MiniTip = {
    show: function (o, s, x, y, z) {
        var div = this.div;
        if (!div) {
            div = document.createElement('div');
            div.style.position = 'absolute';
            div.style.display = 'none';
            div.innerHTML = '<div style="position:relative;background:#FFFDEB;color:#f00;border:solid 1px #EDCE70;cursor:default;padding:3px 5px !important;padding:3px 5px 1px 5px;line-height:1.3em;_position:absolute;white-space:nowrap;'
                + '-moz-border-radius:3px;border-radius:3px;-moz-box-shadow:1px 1px 3px #EDCE70;box-shadow:1px 1px 3px #EDCE70;"></div>'
                + '<div style="top:-10px;left:5px;position:absolute;width:0;height:0;overflow:hidden;border:5px solid transparent;_border-color:tomato;border-bottom-color:#EDCE70;_filter:chroma(color=tomato);"></div>';
            document.body.appendChild(div);
            this.div = div;
        }
        var t = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
        var p = o.getBoundingClientRect();
        div.style.left = p.left + (x || 0) + 'px';
        div.style.top = t + o.offsetHeight + p.top + (y || 5) + 'px';
        div.childNodes[1].style.left = (z || 15) + 'px';
        div.childNodes[0].innerHTML = s;
        div.style.display = '';
    },
    hide: function () {
        if (this.div) this.div.style.display = 'none';
    }
}

//图片放大
function ImageView(o, src, offx, offy) {
    var div = window.ImageViewPanel, p = o.getBoundingClientRect(), t = Math.max(document.documentElement.scrollTop, document.body.scrollTop), y = t + p.top, h = document.documentElement.clientHeight;
    if (!div) {
        div = document.createElement('div');
        div.className = 'ImageView';
        div.style.display = 'none';
        div.style.top = '-1000px';
        div.innerHTML = '<table class="ImageViewPad" cellpadding="0" cellspacing="0"><tr><td align="center" id="ImageViewPad"></td></tr></table>'
                + '<div id="ImageViewCursor" style="left:-20px;top:15px;position:absolute;width:0;height:0;overflow:hidden;border:10px solid transparent;_border-color:tomato;border-right-color:#69d;_filter:chroma(color=tomato);"></div>';
        document.body.appendChild(div);
        window.ImageViewPanel = div;
        div.onhide = function () {
            clearTimeout(div.mo);
            div.mo = setTimeout(function () {
                div.style.display = 'none';
            }, 300);
        }
        div.onmouseover = function () {
            clearTimeout(div.mo);
            div.style.display = '';
        }
        div.onmouseout = div.onhide;
        div.box = document.getElementById('ImageViewPad');
        div.cursor = document.getElementById('ImageViewCursor');
        div.cache = {};
    }
    clearTimeout(div.mo);
    if (div.style.display != 'none' && div.obj == o) return;
    if (!o.onmouseout) o.onmouseout = div.onhide;
    div.obj = o;
    div.box.innerHTML = '';
    div.style.display = '';
    if (p.top + div.offsetHeight > h) y = t + h - div.offsetHeight;
    div.style.left = p.left + o.offsetWidth + 10 + (offx || 0) + 'px';
    div.style.top = y + (offy || 0) + 'px';
    div.cursor.style.top = p.top - div.getBoundingClientRect().top + (o.offsetHeight / 2) - 10 + 'px';
    function show(div, img) {
        div.box.innerHTML = '';
        var w = img.width;
        var h = img.height;
        var ww = div.offsetWidth - 10;
        var hh = div.offsetHeight - 10;
        var scale = ww / hh;
        if (w > ww || h > hh) {
            if (w / h > scale) {
                img.width = ww;
                img.height = ww * h / w;
            } else {
                img.height = hh;
                img.width = hh * w / h;
            }
        }
        div.box.appendChild(img);
    }
    var img = div.cache[src];
    if (img) {
        show(div, img);
    } else {
        img = new Image();
        img.src = src + (isie6 ? '?r=' + Math.random() : '');
        img.onload = function () { show(div, this) }
        div.cache[src] = img;
    }
}

//弹出层
function PopMenu(o, html, offx, offy) {
    var div = window.PopMenuPanel, p = o.getBoundingClientRect(), t = Math.max(document.documentElement.scrollTop, document.body.scrollTop), y = t + p.top;
    if (!div) {
        div = document.createElement('div');
        div.style.position = 'absolute';
        div.style.display = 'none';
        div.style.top = '-1000px';
        document.body.appendChild(div);
        window.PopMenuPanel = div;
        div.hide = function () {
            div.style.display = 'none';
            var p, c = div.o.getAttribute('activeclass');
            if (c) div.o.className = '';
            if (p = div.p) {
                c = p.getAttribute('activeclass');
                if (c) p.className = '';
            }
        }
        div.onhide = function () {
            clearTimeout(div.mo);
            div.mo = setTimeout(function () { div.hide() }, 300);
        }
        div.onmouseover = function () {
            clearTimeout(div.mo);
            div.style.display = '';
            var c = div.o.getAttribute('activeclass');
            if (c) div.o.className = c;
        }
        div.onmouseout = div.onhide;
    }
    div.o = o;
    if (div.p != o) {
        div.hide();
        div.p = o;
    }
    clearTimeout(div.mo);
    if (typeof html == 'object') {
        var c, a = div.childNodes;
        for (var i = 0; i < a.length; i++) {
            c = a[i];
            if (c.tagName) c.style.display = 'none';
        }
        html.style.display = '';
        div.appendChild(html);
    } else {
        div.innerHTML = html;
    }
    if (div.style.display != 'none') return;
    if (!o.onmouseout) o.onmouseout = div.onhide;
    div.onmouseover();
    var z = isie6 ? -2 : 0;
    div.style.left = p.left + (offx || 0) + z + 'px';
    div.style.top = y + o.offsetHeight + (offy || 0) + z + 'px';
}

function number_format(number, decimals, dec_point, thousands_sep) {
    /* 
    * 参数说明： 
    * number：要格式化的数字 
    * decimals：保留几位小数 
    * dec_point：小数点符号 
    * thousands_sep：千分位符号 
    * */
    number = (number + '').replace(/[^0-9+-Ee.]/g, '');
    var n = !isFinite(+number) ? 0 : +number,
        prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
        sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
        dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
        s = '',
        toFixedFix = function (n, prec) {
            var k = Math.pow(10, prec);
            return '' + Math.ceil(n * k) / k;
        };

    s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
    var re = /(-?\d+)(\d{3})/;
    while (re.test(s[0])) {
        s[0] = s[0].replace(re, "$1" + sep + "$2");
    }

    if ((s[1] || '').length < prec) {
        s[1] = s[1] || '';
        s[1] += new Array(prec - s[1].length + 1).join('0');
    }
    return s.join(dec);
}

//页面加载执行
E(function (e) {
    if (e = G('kw')) new PlaceHolder(e, Fn.shipHolderText);
    setInterval(function () { Fn.resizeFooter() }, 10);

    Lib.title(); //过长文字截取
    Lib.xtitle(); //特殊title提示
    convertCargoType(); //转换货种数据
});
