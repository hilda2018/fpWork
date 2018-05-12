FilterShip = {
    hash: {},
    data: [],
    data_en: [],
    ports: {},
    ports_en: {},
    hasBound: true,
    placetext: '请选择或者输入',
    days: 7,
    pagesize: 10,
    sort: { name: [], weight: [], length: [], time: [], dest: [], eta: [] },
    show: function () {
        if (!window.IsLogin) {
            Message.alert('查询在港船舶和预抵船舶。<br/><br/>本功能需登录才能使用，请<a href="/Home/Login">登录</a>或<a href="/Home/Register">免费注册</a>！');
            return;
        }
        var _ = this, win = _.getwin();
        if (win.ishide()) win.show();
    },
    hide: function () {
        this.getwin().hide();
    },
    getwin: function () {
        var _ = this;
        if (!_.win) {
            var time1 = ServerDate;
            var time2 = Lib.formatDate(Math.floor(new Date(ServerDate.replace(/-/g, '/')) / 1000) + _.days * 24 * 3600);
            _.win = new XWin({
                width: 240,
                modal: false,
                position: [document.body.clientWidth - 270, 160],
                title: '港口船舶',
                content: ''
                + '<div id="FilterShipBox">'
                + '<ul id="FilterShipTab" onselectstart="return false"><li class="on" onclick="FilterShip.ontab(this)">在港船舶</li><li onclick="FilterShip.ontab(this,1)">预抵船舶</li></ul>'
                + '<form id="FilterShipForm" style="padding:5px 0;" onsubmit="return false">'
                + '<table><tr>'
                + '<td align="right" width="90" id="fs_portlabel"></td><td><input id="fs_port" class="input" maxlength="50"></td>'
                + '</tr><tr id="fs_custom" style="height:25px;">'
                + '<td colspan="2">'
                + '<span class="mapDraw">或绘制区域，双击结束：</span>'
                + '<a id="fs_link1" href="" onclick="FilterShip.custom();return false" class="zcBtn" style="margin-right:0;">绘制</a>'
                + '<a id="fs_link3" href="" onclick="FilterShip.clear();return false" style="display:none">清除区域</a>'
                + '</td>'
                + '</tr><tbody id="FilterShipExtend" style="display:none;"><tr>'
                + '<td align="right">预抵时间 从：</td><td><input id="fs_time1" class="input" onfocus="WdatePicker()" value="' + time1 + '" readonly></td>'
                + '</tr><tr>'
                + '<td align="right">到：</td><td><input id="fs_time2" class="input" onfocus="WdatePicker()" value="' + time2 + '" readonly></td>'
                + '</tr></tbody><tr>'
                + '<td align="right"></td><td><a href="" class="zcBtn" onclick="FilterShip.submit();return false">搜索</a></td>'
                + '</tr></table>'
                + '<div id="fs_msg" style="color:red;padding-top:5px;text-align:center;"></div>'
                + '</form>'
                + '<div id="FilterShipTip" style="display:none;">'
                + '<div style="color:red;text-align:left;padding:16px 10px 0;">此服务已于2016年7月1日收费，已购买船讯网其它服务的用户，将享受部分优惠或免费开放，详情请咨询010 82868599</div>'
                + '<div style="padding:10px 10px;line-height:20px;text-align:left;">'
                + '全新预抵港提醒服务，<a href="http://www.shipxy.com/Page/YDG" target="_blank">点击立即体验</a>'
                + '</div>'
                + '</div>'
                + '</div>',
                onhide: function () {
                    _.msg('');
                    _.setport();
                    _.clearBound();
                }
            });
            _.cacheData();
            new PlaceHolder(G('fs_port'), _.placetext);
            E(G('fs_port'), 'keyup', function () { _.onselect() });
            _.ontab(G('FilterShipTab').childNodes[0]);
            _.complete = new XComplete({
                id: 'fs_port',
                overcolor: '#666',
                bordercolor: '#69a',
                overground: '#def',
                onclick: function () { _.add() },
                onchange: function (o) {
                    if (!o) return;
                    this.input.value = o.innerHTML;
                },
                callback: function (kw) {
                    var c, s = '', a = _.suggest(kw), cp = _.complete;
                    if (!a || !a.length) { cp.hide(); return; }
                    for (var i = 0; i < a.length; i++) {
                        c = a[i];
                        s += '<p style="font:12px/1.2em arial;white-space:nowrap;" title="' + c + '">' + c + '</p>';
                    }
                    cp.show(s);
                }
            });
        }
        return _.win;
    },
    msg: function (s) {
        var o = G('fs_msg');
        o.innerHTML = s;
        o.style.display = s ? '' : 'none';
    },
    setport: function (s) {
        var o = G('fs_port');
        o.value = s || '';
        o.onfocus();
        o.onblur();
    },
    custom: function () {
        var _ = this;
        _.clearBound();
        _.setport('自定义');
        _.showBound('', '');
    },
    clear: function () {
        var _ = this;
        _.setport();
        _.clearBound();
    },
    showBound: function (lats, lngs) {
        var _ = this;
        map.ShowPortBound(lats, lngs);
    },
    clearBound: function (b) {
        var _ = this;
        if (b) {
            if (G('fs_port').value == '自定义') {
                _.setport();
            }
        } else {
            map.ClearPortBound();
        }
        _.isErrorBound = false;
        _.msg('');
    },
    NotIsPortBound: function () {
        this.isErrorBound = true;
    },
    ontab: function (o, b) {
        if (!(window.ArriveService && ArriveService.ServiceStatus == 1 && ArriveService.EndTime.substr(0, 16) > ServerTime.substr(0, 16))) {
            G('FilterShipForm').style.display = b ? 'none' : '';
            G('FilterShipTip').style.display = b ? '' : 'none';
        }
        o.className = 'on';
        o[b ? 'previousSibling' : 'nextSibling'].className = '';
        G('FilterShipExtend').style.display = b ? '' : 'none';
        G('fs_custom').style.display = b ? 'none' : '';
        var _ = this, c, port = G('fs_port'), s = port.value;
        _.hasBound = !b;
        if (b) {
            _.clearBound();
            if (s == '自定义') {
                _.setport();
            }
        } else {
            _.checkBound(s);
        }
        _.msg('');
        if (G('fs_msg').innerHTML == '暂无数据') _.msg('');
        G('fs_portlabel').innerHTML = b ? '预抵港口：' : '请输入港口：';
        if (_.win2) _.win2.hide();
    },
    add: function () {
        var _ = this, o = G('fs_port'), s = o.value.trim();
        _.complete.hide();
        Check.hide();
        if (!s) return;
        _.onselect();
    },
    onselect: function () {
        var _ = this, o = G('fs_port'), port = o.value;
        if (o.value == _.value) return;
        _.value = port;
        _.clearBound();
        if (!_.hasBound) return;
        _.checkBound(port);
    },
    checkBound: function (port) {
        var _ = this, c, port = port.split('[')[0];
        if (c = IPort.hash[port]) {
            c = _.getBound(c.lat, c.lng);
            _.showBound(c.lats, c.lngs);
        }
    },
    getBound: function (lat, lng) {
        var n = 0.083, m = n * (1 + Math.pow(lat / 90, 1.5));
        var lats = [lat + n, lat + n, lat - n, lat - n];
        var lngs = [lng - m, lng + m, lng + m, lng - m];
        return { lats: lats, lngs: lngs };
    },
    getdata: function (c) {
        var e, name = c.name, memo = [], keys = [c.pinyin];
        if (c.parent) {
            c = c.parent;
            memo.push(c.name);
            if (c.pinyin) keys.push(c.pinyin);
            if (c.kw && c.kw != c.pinyin) {
                keys.push(c.kw);
            }
        } else {
            c = c.nodes || [];
            for (var i = 0; i < c.length; i++) {
                e = c[i];
                memo.push(e.name);
                keys.push(e.pinyin);
                if (e.kw && e.kw != e.pinyin) {
                    keys.push(e.kw);
                }
            }
        }
        return [name, memo.join(','), keys.join(',')];
    },
    submit: function () {
        var _ = this;
        var hasBound = _.hasBound;
        var input = G('fs_port');
        var port = input.value.trim().split('[')[0];
        if (port == '' || port == _.placetext) {
            input.focus();
            _.msg(hasBound ? '请输入港口' : '请输入目的地');
            return;
        }
        if (!/^[\w\u4E00-\u9FA5\-\s（）\(\)\,\[\]]{1,50}$/.test(port)) {
            input.focus();
            _.msg(hasBound ? '港口名称格式不正确' : '目的地格式不正确');
            return;
        }
        var etabegin = G('fs_time1').value;
        var etaend = G('fs_time2').value;
        var c, dest = '';

        if (hasBound) {

            if (!map.HasPortBound()) {
                if (c = IPort.hash[port]) {
                    _.checkBound(port);
                } else {
                    _.showBound('', '');
                    _.msg('请选择或者绘制一个多边形区域');
                    return;
                }
            }

            map.GetPortBound();
            var newBoundResult = json(window.BoundResult);
            if (!window.BoundResult || newBoundResult.length < 3) {
                _.msg('区域至少三个点，请重新绘制');
                return;
            }
            if (!map.IsLegalPortBound()) {
                _.msg('多边形区域范围太大，请检查');
                return;
            }

            etabegin = 0;
            etaend = 0;
            dest = '';
        } else {
            if (!etabegin) {
                _.msg('开始日期不能不能为空');
                return;
            }
            if (!etaend) {
                _.msg('开始日期不能不能为空');
                return;
            }
            etabegin = new Date(etabegin.replace(/-/g, '/')).getTime() / 1000;
            etaend = new Date(etaend.replace(/-/g, '/')).getTime() / 1000;
            if (etabegin >= etaend) {
                _.msg('开始日期不能大于结束日期');
                return;
            }
            if (etaend - etabegin > 90 * 24 * 3600) {
                _.msg('查询范围不能超过90天');
                return;
            }
            var data = IPort.hash[port];
            if (data) {
                dest = data.id;
            } else {
                dest = port;
            }
            if (!dest) {
                _.msg('请选择或者输入港口');
                return;
            }
        }
        _.msg('正在获取数据……');

        //添加统计
        shipxyVisit(hasBound ? 8 : 9);
        map.SiftShip(hasBound, etabegin, etaend, dest);
        //hasBound 在线船舶-1, 预抵船舶-0
        var flag = hasBound ? 1 : 0;
        $.get("/Monitor/StatisticsPort", { flag: flag, t: Math.random() });
    },
    callback: function (data) {
        var _ = this;
        if (typeof data == 'string') {
            data = json(data);
        }
        if (!data || !data.count) {
            _.msg('暂无数据');
            return;
        }
        _.msg('');

        if (!_.win2) {
            _.win2 = new XWin({
                width: 600,
                modal: false,
                position: [240, document.body.clientHeight - 435],
                title: '船舶列表',
                content: '<div id="fs_box" style="padding-top:5px;height:277px;overflow:auto;"></div><div id="fs_page"></div>',
                onhide: function () { }
            });
            _.wheel(G('fs_box'), function (b, e) { _.fpage(b ? -1 : 1) });
        }
        _.win2.show({ width: _.hasBound ? 470 : 640 });
        data = data.shipArray;
        var hash = _.hash, count = data.length, m = count - 1, sort = { name: [], stype: [], weight: [], length: [], time: [], dest: [], eta: [] };
        for (var i = 0; i < count; i++) {
            c = data[i];
            c.dest = decodeURIComponent(c.dest).trim();
            hash[i] = c;
            sort.name[m - i] = i;
            sort.stype[m - c.shipTypeIndex < 0 ? 0 : m - c.shipTypeIndex] = i;
            sort.weight[m - c.deadWeightIndex < 0 ? 0 : m - c.deadWeightIndex] = i;
            sort.length[m - c.lengthBeamIndex < 0 ? 0 : m - c.lengthBeamIndex] = i;
            sort.time[c.lastTimeIndex == count ? m : c.lastTimeIndex] = i;
            sort.dest[m - c.destIndex < 0 ? 0 : m - c.destIndex] = i;
            sort.eta[c.etaIndex == count ? m : c.etaIndex] = i;
        }
        _.sort = sort;
        _.pagesum = Math.ceil(count / _.pagesize);
        _.asc = true;
        _.getPageData();
    },
    getPageData: function (type, page) {
        if (!type) type = 'name';
        if (!page) {
            page = 1;
            this.asc = !this.asc;
            if (this.lastfield != type) this.asc = false;
            this.lastfield = type;
        }
        var _ = this, c, t, s = '', data = [], size = _.pagesize, source = _.sort[type], count = source.length, now = size * (page - 1), arr;
        _.pagenow = page;
        if (_.asc) {
            arr = source.slice(now, now + size);
        } else {
            c = count - now;
            now = c - size;
            if (now < 0) now = 0;
            arr = source.slice(now, c);
            arr.reverse();
        }
        for (var i = 0; i < arr.length; i++) {
            c = arr[i];
            c = _.hash[c];
            data.push(c);
        }
        for (var i = 0; i < data.length; i++) {
            c = data[i];
            t = Lib.formatTime(c.lastTime);
            s += '<tr onclick="ShipPage.setHighlight(this);map.locate(\'' + c.shipId + '\')" style="cursor:pointer;">'
            + '<td>' + ((page - 1) * size + (i + 1)) + '</td>'
            + '<td class="l stype' + c.type + '" len="30" style="white-space:nowrap;">' + c.contant + '</td>'
            + '<td class="l" len="15" style="white-space:nowrap;">' + c.shipType + '</td>'
            + '<td class="l">' + (c.deadWeight || '') + '</td>'
            + '<td class="l">' + _.getlengthbeam(c.length, c.beam) + '</td>'
            + '<td class="l" title="' + t + '">' + t.substr(5, 11) + '</td>'
            + (_.hasBound ? '' : (''
            + '<td></td>'
            + '<td class="l" len="20" style="white-space:nowrap;">' + c.dest.replace(/  /g, ' ') + '</td>'
            + '<td class="l">' + _.geteta(c.eta) + '</td>'
            ))
            + '<td></td>'
            + '</tr>';
        }
        if (s) {
            s = '<div class="fsTips">'
                + '<span style="color:#f60;float:right;">点击标题可排序，鼠标滚轮可翻页</span>'
                + '<a target="_blank" href="/IHS/Index">查看船东公司联系方式/船队规模等信息</a></div>'
                + '<table id="tb_FilterShip" class="xtable" style="_width:auto;">'
                + '<tr class="th">'
                + '<td width="35">序号</td>'
                + '<td class="l" onclick="FilterShip.getPageData(\'name\')">船名' + _.getsort('name', type) + '</td>'
                + '<td class="l" width="90" onclick="FilterShip.getPageData(\'stype\')">船舶类型' + _.getsort('stype', type) + '</td>'
                + '<td class="l" width="70" onclick="FilterShip.getPageData(\'weight\')">载重吨' + _.getsort('weight', type) + '</td>'
                + '<td class="l" width="70" onclick="FilterShip.getPageData(\'length\')">长x宽' + _.getsort('length', type) + '</td>'
                + '<td class="l" width="70" onclick="FilterShip.getPageData(\'time\')">最后时间' + _.getsort('time', type) + '</td>'
                + (_.hasBound ? '' : (''
                    + '<td width="10">&nbsp;</td>'
                    + '<td class="l" width="90" class="l" onclick="FilterShip.getPageData(\'dest\')">目的地' + _.getsort('dest', type) + '</td>'
                    + '<td class="l" width="70" onclick="FilterShip.getPageData(\'eta\')">到港时间' + _.getsort('eta', type) + '</td>'
                ))
                + '<td width="10">&nbsp;</td>'
                + '</tr>'
                + s
                + '</table>';
            G('fs_box').innerHTML = s;

            s = '';
            var total = Math.ceil(count / size);
            if (total > 1) {
                s += '<div class="xpage1">'
                s += page > 1 ? ('<a href="javascript:void(FilterShip.getPageData(\'' + type + '\',1))">首页</a>') : '<span>首页</span>';
                s += page > 1 ? ('<a href="javascript:void(FilterShip.getPageData(\'' + type + '\',' + (page - 1) + '))">上页</a>') : '<span>上页</span>';
                s += '<span>第' + page + '页/共' + total + '页</span>';
                s += page < total ? ('<a href="javascript:void(FilterShip.getPageData(\'' + type + '\',' + (page + 1) + '))">下页</a>') : '<span>下页</span>';
                s += page < total ? ('<a href="javascript:void(FilterShip.getPageData(\'' + type + '\',' + total + '))">未页</a>') : '<span>未页</span>';
                s += '</div>';
            }
            G('fs_page').innerHTML = s;
            new XTable('tb_FilterShip');
            Lib.title(G('tb_FilterShip'));
        } else {
            G('fs_box').innerHTML = '<div style="padding:30px;text-align:center;">暂无船舶数据</div>';
        }
    },
    fpage: function (n) {
        var _ = this;
        if (!_.lastfield || !_.pagesum) return;
        n += _.pagenow;
        if (n < 1 || n > _.pagesum) return;
        _.getPageData(_.lastfield, n);
    },
    wheel: function (o, f) { o.onmousewheel = function (e) { e = e || event; f(e.wheelDelta > 0 || e.detail < 0, e); if (e.preventDefault) e.preventDefault(); e.cancelBubble = true; return false; }; if (o.addEventListener) o.addEventListener("DOMMouseScroll", o.onmousewheel, false) },
    getsort: function (t1, t2) {
        if (t1 != t2) return '<s>&nbsp;</s>';
        var _ = this, asc = _.asc;
        if (/^(name|stype|time|dest|eta)$/.test(_.lastfield || '')) asc = !asc;
        asc = asc ? 'asc' : 'desc';
        return '<s class="' + asc + '">&nbsp;</s>';
    },
    getlengthbeam: function (length, beam) {
        length = length || '';
        beam = beam || '';
        if (!length && !beam) return '';
        return (length || '?') + 'x' + (beam || '?');
    },
    geteta: function (eta) {
        if (!eta) return '';
        var c, a = eta.split(/[^\d]/);
        for (var i = 0; i < 4; i++) {
            c = a[i];
            if (c.length == 1) a[i] = '0' + c;
        }
        return a[0] + '-' + a[1] + ' ' + a[2] + ':' + a[3];
    },
    extend: function (data) {
        var a = data.latlon.split(','), lats = [], lngs = [];
        for (var i = 0; i < a.length; i += 2) {
            lats.push(a[i]);
            lngs.push(a[i + 1]);
        }
        data.lats = lats.join(',');
        data.lngs = lngs.join(',');
        return data;
    },
    cacheData: function (b) {
        var _ = this, c, d, p, n = 0, data = b ? _.data : _.data_en, ports = b ? _.ports : _.ports_en, ret = [];
        if (!data.length) {
            a = b ? window.portlist_cn : window.portlist_en;
            for (var i = 0; i < a.length; i++) {
                p = _.extend(a[i]);
                c = p.nodes || [];
                data.push(p);
                ports[p.name] = p;
                for (var j = 0; j < c.length; j++) {
                    d = _.extend(c[j]);
                    d.parent = p;
                    data.push(d);
                    ports[d.name] = d;
                }
            }
        }
        if (!b) _.cacheData(1);
    },
    suggest: function (s) {
        var s = s.trim().toUpperCase();
        if (!s) return '';
        var _ = this, c, d, e, n = 0, data = IPort.data, len = 15, ret = [], ret2 = [];
        for (var i = 0; i < data.length; i++) {
            c = data[i];
            if (c.py) {
                if (c.py.toUpperCase().indexOf(s) != -1 || (c.name_cn && c.name_cn.toUpperCase().indexOf(s) != -1) || (c.name_en && c.name_en.toUpperCase().indexOf(s) != -1)) {
                    d = c.name_cn;
                    e = c.name_en;
                    if (d) e = d + '[' + e + ']';
                    ret.push(e);
                }
            } else {
                if (ret2.length > len) continue;
                if ((c.name_cn && c.name_cn.toUpperCase().indexOf(s) != -1) || (c.name_en && c.name_en.toUpperCase().indexOf(s) != -1)) {
                    d = c.name_cn;
                    e = c.name_en;
                    if (d) e = d + '[' + e + ']';
                    ret2.push(e);
                }
            }
        }
        ret = ret.concat(ret2);
        if (ret.length > len) ret.length = len;
        return ret;
    }
}
