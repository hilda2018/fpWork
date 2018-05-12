! function() {
	if(/(\w+)\.(com|net)/i.test(location.href)) document.domain = RegExp.$1 + "." + RegExp.$2;
	var t = 1,
		e = "http://im.shipxy.com",
		i = e + "/chat/",
		n = "/Service/Chat.aspx",
		s = "",
		a = e + "/proxy2.htm?v=" + t,
		r = e + "/cache/SoundPlayer.swf?v=" + t,
		o = null,
		d = false,
		u = "",
		c = [],
		f = null,
		m = "",
		h = "SYS_ID",
		_ = [],
		p = {},
		v = {},
		g = {},
		y = {},
		T = {},
		b = 10,
		x = 10,
		k = 5,
		w = 50,
		C = e + "/images/default.png?v=" + t,
		M = e + "/images/sys.jpg?v=" + t,
		E = e + "/im.css?v=" + t,
		I = 400,
		$ = 600,
		N = {
			close: 0,
			min: 1,
			max: 2
		},
		L = {
			offline: 0,
			online: 1,
			hiding: 2
		},
		S = L.online,
		U = false,
		H = {
			consulter: 0,
			my: 1,
			friend: 2,
			mate: 3,
			sys: 5
		},
		B = {
			friend: 1,
			mate: 2,
			consulter: 3,
			consultReply: 5
		},
		A = {
			enter: "enter",
			ctrl: "ctrl+enter"
		},
		G = A.enter,
		j = {
			enter: "按Enter键发送消息，按Ctrl+Enter键换行",
			"ctrl+enter": "按Ctrl+Enter键发送消息，按Enter键换行"
		},
		O = ["hehe", "xixi", "haha", "keai", "kelian", "wabishi", "chijing", "haixiu", "jiyan", "bizui", "bishi", "aini", "daku", "touxiao", "qinqin", "shengbing", "kaixin", "lande", "youheng", "zuoheng", "xu", "shuai", "weiqu", "tu", "dahaqian", "baobao", "nu", "yiwen", "chanzui", "baibai", "sikao", "han", "kun", "shuijiao", "qian", "shiwang", "ku", "huaxin", "heng", "guzhang", "yun", "beishang", "zhuakuang", "heixian", "yinxian", "numa", "xin", "shangxin", "jiong", "shenma", "liwu", "weiwu", "geili", "zhutou", "ok", "ye", "good", "buyao", "zan", "lai", "ruo", "lazhu", "dangao", "zhong", "huatong"],
		R = {
			hehe: "[呵呵]",
			xixi: "[嘻嘻]",
			haha: "[哈哈]",
			keai: "[可爱]",
			kelian: "[可怜]",
			wabishi: "[挖鼻屎]",
			chijing: "[吃惊]",
			haixiu: "[害羞]",
			jiyan: "[挤眼]",
			bizui: "[闭嘴]",
			bishi: "[鄙视]",
			aini: "[爱你]",
			daku: "[大哭]",
			touxiao: "[偷笑]",
			qinqin: "[亲亲]",
			shengbing: "[生病]",
			kaixin: "[开心]",
			lande: "[懒得理你]",
			youheng: "[右哼哼]",
			zuoheng: "[左哼哼]",
			xu: "[嘘]",
			shuai: "[衰]",
			weiqu: "[委屈]",
			tu: "[吐]",
			dahaqian: "[打哈欠]",
			baobao: "[抱抱]",
			nu: "[怒]",
			yiwen: "[疑问]",
			chanzui: "[馋嘴]",
			baibai: "[拜拜]",
			sikao: "[思考]",
			han: "[汗]",
			kun: "[困]",
			shuijiao: "[睡觉]",
			qian: "[钱]",
			shiwang: "[失望]",
			ku: "[酷]",
			huaxin: "[花心]",
			heng: "[哼]",
			guzhang: "[鼓掌]",
			yun: "[晕]",
			beishang: "[悲伤]",
			zhuakuang: "[抓狂]",
			heixian: "[黑线]",
			yinxian: "[阴险]",
			numa: "[怒骂]",
			xin: "[心]",
			shangxin: "[伤心]",
			jiong: "[囧]",
			shenma: "[神马]",
			liwu: "[礼物]",
			weiwu: "[威武]",
			geili: "[给力]",
			zhutou: "[猪头]",
			ok: "[OK]",
			ye: "[耶]",
			good: "[good]",
			buyao: "[不要]",
			zan: "[赞]",
			lai: "[来]",
			ruo: "[弱]",
			lazhu: "[蜡烛]",
			dangao: "[蛋糕]",
			zhong: "[钟]",
			huatong: "[话筒]"
		};
	IM = {
		version: "2.0.0",
		init: function(t, e) {
			if(!t) {
				alert("My ID is empty。无法初始化聊天工具！");
				return
			}
			e = e || {};
			c = e.friends || [];
			if(!m) {
				m = t;
				n = e.usersvr;
				s = e.mobilesvr;
				u = e.title || "";
				if(e.isSchedule) {
					d = e.isSchedule;
					b = 50
				}
				if(e.sysfn) f = e.sysfn;
				F();
				q();
				pe.load()
			} else {
				Z.setFriends(c, true)
			}
		},
		talk: function(t, e, i) {
			if(!t) return;
			if(he.indexOf(_, t) == -1) {
				_.push(t);
				P.registerUsers(_);
				P.getStates([t])
			}
			if(!J.isFriend(t)) i ? J.addConsulter(t) : J.addMate(t);
			Z.setTopic(t, i);
			Z.addTalk(t)
		},
		proxy: function() {
			if(window.proxy_ajax) {
				he.proxy_request = proxy_ajax;
				Z.initialize()
			} else this.log("cross domain error")
		}
	};

	function F() {
		o = he._("div");
		o.onselectstart = function() {
			return false
		};
		he.addClass(o, "im_main");
		document.body.appendChild(o)
	}

	function q() {
		var t = he._("div");
		t.className = "im_proxy";
		t.innerHTML = '<iframe onload="IM.proxy();" src="' + a + '" frmeborder="0" scrolling="no" class="im_proxy_frame"></iframe>';
		o.appendChild(t)
	}
	var P = {
		tick: 0,
		sendTimeOut: 120,
		pollingErrorCount: 0,
		registerUsers: function(t, e) {
			if(U) return;
			he.proxy_request(i + "register.ashx", {
				method: "POST",
				data: {
					uid: m,
					bids: t.join(",")
				},
				success: function(t) {
					if(e) e.call(null, t ? t.status : 100)
				},
				error: function() {
					if(e) e.call(null, json ? json.status : 200)
				}
			})
		},
		getAllState: function() {
			if(U) return;
			he.proxy_request(i + "getonlinebuddy.ashx?uid=" + m, {
				success: function(t) {
					t = t || {};
					if(t.status == 0) {
						var e = {},
							i = t.online || [],
							n = 0,
							s = i.length;
						for(; n < s; n++) e[i[n]] = L.online;
						Z.setUsersState(e)
					}
				}
			})
		},
		getStates: function(t) {
			if(U) return;
			he.proxy_request(i + "getonlineusers.ashx?uids=" + t.join(","), {
				success: function(t) {
					t = t || {};
					if(t.status == 0) {
						var e = {},
							i = t.online || [],
							n = 0,
							s = i.length;
						for(; n < s; n++) e[i[n]] = L.online;
						Z.setUsersState(e)
					}
				}
			})
		},
		setState: function(t, e) {
			he.proxy_request(i + "setonline.ashx?uid=" + m + "&status=" + t, {
				success: function(i) {
					i = i || {};
					if(i.status == 0) Z.setMyState(t);
					if(e) e.call(null, i.status != undefined ? i.status : 100)
				},
				error: function() {
					if(e) e.call(null, 200)
				}
			})
		},
		postMsg: function(t, e, n) {
			if(U) return;
			t.arrive = 1;
			he.proxy_request(i + "postMsg.ashx", {
				method: "POST",
				data: t,
				el: e,
				timeout: this.sendTimeOut,
				success: function(t) {
					t = t || {};
					if(t.status == 0) {
						this.data.id = t.id;
						this.data.time = t.time;
						if(n) n.call(this)
					}
					Z.postMsgResult(this.data.to, this.data, t.status != undefined ? t.status : 100)
				},
				error: function() {
					Z.postMsgResult(this.data.to, this.data, 200)
				},
				ontimeout: function() {
					Z.postMsgResult(this.data.to, this.data, 300)
				}
			})
		},
		polling: function() {
			if(U) return;
			he.proxy_request(i + "polling.ashx?uid=" + m + "&tick=" + this.tick, {
				success: function(t) {
					t = t || {};
					P.pollingErrorCount = 0;
					var e = t.status != undefined ? t.status : 100;
					if(e == 0) {
						P.tick = t.tick;
						D(t.chat || [], t.sys || []);
						z(t.online || [], t.offline || [])
					} else if(e == 10) {
						P.registerUsers(_)
					} else if(e == 11) {
						Z.setMyState(L.offline);
						return
					}
					P.polling()
				},
				error: function() {
					if(++P.pollingErrorCount >= 5) {
						Z.setMyState(L.offline);
						return
					}
					P.polling()
				}
			})
		},
		getHistory: function(t, e, n, s) {
			if(U) return;
			he.proxy_request(i + "getchatHist.ashx?uid=" + m + "&bid=" + t + "&offset=" + (e || 0) + "&count=" + (n || x), {
				success: function(e) {
					e = e || {};
					var i, n = 0;
					if(e.status == 0) {
						i = e.msg;
						n = e.sum
					}
					s ? Z.setShortHistory(t, i) : Z.setHistory(t, i, n)
				},
				error: function() {
					s ? Z.setShortHistory(t, null) : Z.setHistory(t, null, 0)
				}
			})
		},
		getRecents: function(t, e) {
			if(U) return;
			he.proxy_request(i + "getrecentbuddy.ashx?uid=" + m + "&offset=" + (t || 0) + "&count=" + (e || b), {
				success: function(t) {
					t = t || {};
					Z.setRecents(t.buddys || [])
				}
			})
		},
		answer: function(t) {
			if(U) return;
			he.proxy_request(i + "SetReadMsg.ashx?uid=" + m + "&maxmsgid=" + t)
		},
		getUserInfo: function(t) {
			if(U) return;
			var e = {
				cmd: "getUserInfo",
				uids: t.join(",")
			};
			if(!d) e.uid = m;
			he.request(n, {
				method: "POST",
				data: e,
				success: function(t) {
					t = t || {};
					Z.setUsersInfo(t.data || [])
				}
			})
		},
		saveFriendGroup: function(t, e, i) {
			if(U) return;
			he.request(s + "?cmd=SaveFriendGroup&sid=0&GroupId=" + t + "&GroupName=" + encodeURIComponent(e), {
				success: function(t) {
					t = t || {};
					if(i) i(t.status, t.GroupId)
				}
			})
		},
		delFriendGroup: function(t, e) {
			if(U) return;
			he.request(s + "?cmd=DeleteFriendGroup&sid=0&GroupId=" + t, {
				success: function(t) {
					t = t || {};
					if(e) e(t.status)
				}
			})
		},
		moveFriend: function(t, e, i) {
			if(U) return;
			he.request(s + "?cmd=MoveFriendGroup&sid=0&FriendId=" + t + "&GroupId=" + e, {
				success: function(t) {
					t = t || {};
					if(i) i(t.status)
				}
			})
		},
		releaseFriend: function(t, e) {
			if(U) return;
			he.request(s + "?cmd=DeleteFriend&sid=0&FriendId=" + t, {
				success: function(t) {
					t = t || {};
					if(e) e(t.status)
				}
			})
		}
	};

	function D(t, e) {
		var i = -1,
			n = 0,
			s, a = [],
			r = [],
			o;
		for(s = t.length; n < s; n++) {
			o = t[n];
			a.push(o);
			i = Math.max(i, o.id)
		}
		if(a.length) Z.receiveMsgs(a);
		for(n = 0, s = e.length; n < s; n++) {
			o = e[n];
			if(!o.hasOwnProperty("from")) o.from = h;
			r.push(o);
			i = Math.max(i, o.id)
		}
		if(r.length) Z.receiveSysMsgs(r);
		if(i > -1) P.answer(i)
	}

	function z(t, e) {
		var i = {},
			n, s;
		for(n = 0, s = t.length; n < s; n++) i[t[n]] = L.online;
		for(n = 0, s = e.length; n < s; n++) i[e[n]] = L.offline;
		Z.setUsersState(i)
	}

	function X(t, e) {
		if(t == h || J.isFriend(t)) return;
		if(e == H.mate) J.addMate(t);
		else if(e == H.consulter) J.addConsulter(t)
	}

	function W(t, e) {
		if(t == h || J.isFriend(t)) return;
		if(e == B.mate) J.addMate(t);
		else if(e == B.consulter) {
			if(!d && V(t)) {
				var i = p[t];
				if(i) {
					i.name = i.sname;
					i.longName = i.company ? i.name + " - " + i.company : i.name
				}
			}
			J.addConsulter(t)
		}
	}

	function V(t) {
		if(J.isConsulter(t)) {
			var e = g[t] || [];
			var i = e[e.length - 1];
			if(i) {
				var n = e.length - 2;
				while(i.from != t) {
					i = e[i];
					if(!i) return false;
					l--
				}
				return i.ext == B.consulter
			}
		}
		return false
	}

	function Y() {
		return {
			uid: h,
			name: "系统消息",
			longName: "系统消息",
			company: "",
			face: [M, M, M],
			relation: H.sys,
			url: "javascript:void(0)"
		}
	}

	function Q(t) {
		if(!(t instanceof Array)) t = [t];
		var e = 0,
			i = t.length,
			n, s;
		for(; e < i; e++) {
			n = t[e];
			if(n != h && he.indexOf(_, n) == -1) {
				_.push(n);
				s = true
			}
		}
		if(s) P.registerUsers(_)
	}
	var Z = {
		initialize: function() {
			this.initializeView();
			this.setFriends(c);
			P.getUserInfo([m]);
			P.setState(L.online, function(t) {
				if(t == 0) {
					P.getRecents(0, b);
					return
				}
				Z.setMyState(L.offline)
			})
		},
		initializeView: function() {
			K.create().setBoxStatus(N.max).setTabs(d ? ["recent"] : ["friend", "recent"]).setCurrentTab(d ? "recent" : "friend");
			ie.create().setBoxStatus(N.close);
			me(he.cookie("enterWay") || A.enter)
		},
		setMyState: function(t) {
			S = t;
			U = S == L.offline;
			K.setMyState(t);
			if(U) {
				for(var e in v) v[e] = L.offline;
				this.setUsersState(v)
			} else {
				P.registerUsers(_, function(t) {
					if(t == 0) {
						P.getAllState();
						P.polling()
					}
				})
			}
		},
		setFriends: function(t, e) {
			J.setFriends(t, e);
			if(!e) _ = J.friendsNoGroup.concat();
			else {
				var i = J.friendsNoGroup,
					n = 0,
					s = i.length,
					a;
				for(; n < s; n++) {
					a = i[n];
					this.showUserState(a, v[a])
				}
				Q(i)
			}
		},
		setRecents: function(t) {
			te.setRecents(t);
			Q(t);
			P.getUserInfo(t);
			P.getStates(t)
		},
		addRecent: function(t) {
			te.addRecent(t);
			Q(t)
		},
		setTalkBoxStatus: function(t) {
			ie.setBoxStatus(t);
			ae.position(t);
			if(t == N.max) {
				ie.focus();
				this.readUnReadMsg(ie.curTalker)
			} else if(t == N.close) {
				ie.clearTalker();
				ae.hide()
			}
		},
		addTalk: function(t) {
			if(!t) return;
			if(ie.addTalker(t)) {
				Q(t);
				if(t != h) P.getUserInfo([t]);
				ie.setCurTalker(t);
				ie.setTalkerTopic(t, T[t]);
				ie.showMsgs(this.getMessage(t))
			} else ie.setCurTalker(t);
			this.setTalkBoxStatus(N.max)
		},
		removeTalk: function(t) {
			if(!t) return;
			var e = ie.removeTalker(t);
			e ? this.readUnReadMsg(e) : this.setTalkBoxStatus(N.close)
		},
		setUsersInfo: function(t) {
			var e = 0,
				i = t.length,
				n, s;
			for(; e < i; e++) {
				n = t[e];
				this.setUserInfo(n.uid, n)
			}
			if(!p[h]) this.setUserInfo(h, Y())
		},
		setUserInfo: function(t, e, i) {
			if(e.relation === undefined) e.relation = H.consulter;
			if(e.relation == H.consulter) {
				if(!d && !V(t)) {
					e.sname = e.name;
					e.name = e.name.charAt(0) + "**"
				}
			}
			e.longName = e.company ? e.name + " - " + e.company : e.name;
			p[t] = e;
			X(t, e.relation);
			if(!i) this.showUserInfo(t, e)
		},
		showUserInfo: function(t, e) {
			if(t == m) {
				K.setMyInfo(e);
				return
			}
			if(e.relation == H.friend) J.setFriendInfo(t, e);
			else if(e.relation == H.mate) J.setMateInfo(t, e);
			else if(e.relation == H.consulter) J.setConsulterInfo(t, e);
			if(te.isRecent(t)) te.setRecentInfo(t, e);
			if(ie.isTalker(t)) ie.setTalkerInfo(t, e);
			if(t == ie.curTalker) ie.setCurTalkerInfo(e);
			if(t == ae.talker) ae.setUserInfo(e);
			if(ee.has(t)) ee.setAlertInfo(t, e)
		},
		setUsersState: function(t) {
			for(var e in t) this.setUserState(e, t[e])
		},
		setUserState: function(t, e) {
			v[t] = e;
			this.showUserState(t, e)
		},
		showUserState: function(t, e) {
			if(J.isFriend(t)) J.setFriendState(t, e);
			else if(J.isMate(t)) J.setMateState(t, e);
			else if(J.isConsulter(t)) J.setConsulterState(t, e);
			if(te.isRecent(t)) te.setRecentState(t, e);
			if(ie.isTalker(t)) ie.setTalkerState(t, e);
			if(t == ie.curTalker) ie.setCurTalkerState(e)
		},
		setTopic: function(t, e) {
			if(T[t] === e) return;
			T[t] = e;
			ie.setTalkerTopic(t, e)
		},
		postMsgResult: function(t, e, i) {
			if(!t) return;
			this.addRecent(t);
			if(i != 0) {
				var n = {
					from: "",
					fromname: "消息提示",
					to: "",
					toname: "",
					text: '消息内容：<br><span class="im_errorMsgTip">' + e.text + "</span><br>发送" + (i == 200 ? "错误" : i == 300 ? "超时" : "失败") + "！",
					time: null,
					remark: "",
					ext: ""
				};
				ie.addErrorMsg(t, n)
			} else this.addMessage(t, e)
		},
		receiveMsgs: function(t) {
			var e = 0,
				i = t.length,
				n, s, a = {},
				r = [],
				o = [];
			for(; e < i; e++) {
				n = t[e];
				s = n.from;
				if(!s) continue;
				this.addMessage(s, n);
				if(s != ie.curTalker || ie.uistatus == N.min) this.addUnReadMsg(s, n);
				else ie.showMsgs(n);
				if(s !== m && s !== h) {
					if(!a[s]) {
						a[s] = [];
						this.addRecent(s);
						if(!p[s]) r.push(s);
						if(!v[s]) o.push(s)
					}
					a[s].push(n)
				}
			}
			for(var s in a) {
				var l = a[s];
				var d = l[l.length - 1];
				W(s, d.ext);
				this.setTopic(s, d.remark)
			}
			if(r.length > 0) P.getUserInfo(r);
			if(o.length > 0) P.getStates(o)
		},
		receiveSysMsgs: function(t) {
			this.receiveMsgs(t);
			if(f) f(t)
		},
		addMessage: function(t, e) {
			if(!this.hasMessage(t, e)) {
				var i = g[t];
				if(!i) {
					i = [];
					g[t] = i
				}
				i.push(e);
				if(t != h) {
					var n = i.length;
					if(n > w) {
						n -= w;
						while(n--) i.shift()
					}
				}
			}
		},
		hasMessage: function(t, e) {
			var i = g[t];
			if(i) {
				for(var n = 0, s = i.length; n < s; n++)
					if(i[n].id == e.id) return true
			}
			return false
		},
		getMessage: function(t) {
			var e = g[t];
			if(e) {
				if(t == h || e.length <= 5) return e;
				return e.slice(e.length - 5)
			}
			if(t != h) P.getHistory(t, 0, k, true);
			return null
		},
		addUnReadMsg: function(t, e) {
			if(!this.hasUnReadMsg(t, e)) {
				var i = y[t];
				if(!i) {
					i = [];
					y[t] = i
				}
				i.push(e);
				this.alertUnReadMsg(t)
			}
		},
		hasUnReadMsg: function(t, e) {
			var i = y[t];
			if(i) {
				for(var n = 0, s = i.length; n < s; n++)
					if(i[n].id == e.id) return true
			}
			return false
		},
		alertUnReadMsg: function(t) {
			K.showMsgTip(t == h);
			ie.isTalker(t) ? ie.showMsgTip(t) : ee.add(t);
			pe.play(e + "/sounds/pop.mp3")
		},
		readUnReadMsg: function(t) {
			if(t != ie.curTalker || ie.uistatus != N.max) return;
			var e = y[t];
			if(e) {
				delete y[t];
				ie.showMsgs(e);
				ie.hideMsgTip(t);
				if(he.isEmpty(y)) K.hideMsgTip()
			}
		},
		setShortHistory: function(t, e) {
			if(!e || e.length == 0) return;
			var i = e.length,
				n;
			while(i--) {
				n = e[i];
				this.addMessage(t, n)
			}
			W(t, n.ext);
			if(!T[t]) this.setTopic(t, n.remark);
			ie.showMsgs(e)
		},
		setHistory: function(t, e, i) {
			ae.showHistory(t, e, i)
		},
		openHistoryView: function(t) {
			ae.show(t);
			ae.position(ie.uistatus)
		}
	};
	var K = {
		ui: null,
		maxUI: null,
		minUI: null,
		uistatus: -1,
		create: function() {
			this.ui = he._("div");
			this.ui.className = "im_mainview";
			this.maxUI = this.createMaxUI();
			this.minUI = this.createMinUI();
			this.ui.appendChild(this.maxUI);
			this.ui.appendChild(this.minUI);
			o.appendChild(this.ui);
			this.addEvent();
			this.addResize();
			return this
		},
		createMaxUI: function() {
			var t = he._("div");
			t.innerHTML = '<div id="im_friend_nresize" class="im_friend_nresize" title="上下拖动拉伸高度"></div>' + '<div class="im_list_title">' + '<div class="im_list_title_lf" id="im_state_change" data-action="stateView" title="切换状态">' + '<a href="javascript:void(0)">离线</a>' + '<em class="im_list_title_arrow"></em>' + "</div>" + '<div class="im_list_title_rt">' + '<a data-action="min" href="javascript:void(0)" title="最小化" class="im_narrow"></a>' + "</div>" + '<div class="im_list_title_img">' + '<img id="im_mv_face" src="' + C + '" title="' + m + '">' + '<span class="im_offline" id="im_mv_state"></span>' + "</div>" + '<div class="im_list_title_name"><span>' + u + "</span></div>" + "</div>" + '<div class="im_list_tab" data-action="list" id="im_listtab"></div>' + '<div id="im_listbox"></div>' + '<div class="im_list_pos" data-action="min">' + '<a id="im_mv_sys_ico" style="display:' + (d ? "none" : "") + '" href="javascript:void(0)" title="系统消息" class="im_list_pos_sys"></a>' + '<a href="javascript:void(0)" title="最小化" class="im_list_pos_min"></a>' + '<a id="im_mv_msgtip" href="javascript:void(0)" class="im_friend_min_tip" style="display:none;" title="查看消息"></a>' + "</div>" + '<div class="im_status_list" id="im_status_list" style="display:none;" data-action="state">' + '<a href="javascript:void(0)" data-state="1">在线<span class="im_online" data-state="1"></span></a>' + '<a href="javascript:void(0)" data-state="0">离线<span class="im_offline" data-state="0"></span></a>' + '<a href="javascript:void(0)" data-state="2">隐身<span class="im_hiding" data-state="2"></span></a>' + "</div>";
			he.addClass(t, "im_friend_list");
			return t
		},
		createMinUI: function() {
			var t = he._("div");
			t.innerHTML = '<div class="im_friend_min_img">' + '<img id="im_mmv_face" src="' + C + '" title="' + m + '">' + '<span class="im_offline"></span>' + "</div>" + (d ? "最近聊天人" : "我的好友") + '[<span id="im_mmv_count">0</span>]' + '<a id="im_mmv_msgtip" href="javascript:void(0)" class="im_friend_min_tip" style="display:none;" title="查看消息"></a>';
			t.setAttribute("data-action", "max");
			he.addClass(t, "im_friend_min");
			return t
		},
		addEvent: function() {
			var t = this;
			he.addEvent(this.ui, "click", function(e) {
				e = e.target;
				var i = he.getAttribute(e, "data-action", true);
				if(!i) return;
				switch(i[0]) {
					case "min":
						var n = he.$("im_mv_sys_ico");
						if(e == n) {
							Z.addTalk(h);
							return
						}
						t.setBoxStatus(N.min);
						break;
					case "max":
						t.setBoxStatus(N.max);
						break;
					case "stateView":
						t.toggleStateList();
						break;
					case "state":
						t.toggleStateList();
						var s = e.getAttribute("data-state");
						P.setState(s);
						break;
					case "list":
						var a = he.getAttribute(e, "data-list", true);
						if(!a) return;
						t.setCurrentTab(a[0]);
						break
				}
			});
			he.addEvent(this.minUI, "mouseover", function() {
				he.addClass(t.minUI, "im_friend_min_hover")
			});
			he.addEvent(this.minUI, "mouseout", function() {
				he.removeClass(t.minUI, "im_friend_min_hover")
			});
			he.addEvent(he.$("im_mv_msgtip"), "mouseover", function() {
				ee.show()
			});
			he.addEvent(he.$("im_mmv_msgtip"), "mouseover", function() {
				ee.show()
			})
		},
		addResize: function() {
			var t = he.$("im_friend_nresize");
			if(!t) return;
			var e = {},
				i = this;
			$ = document.body.offsetHeight - 5;
			he.addEvent(t, "mousedown", function(i) {
				if(i.target != t) return;
				he.removeEvent(document.body, "mousemove", n);
				he.removeEvent(document.body, "mouseup", s);
				he.stopPropagation(i);
				e = he.getXY(i);
				he.addEvent(document.body, "mousemove", n);
				he.addEvent(document.body, "mouseup", s);
				he.preventDefault(i)
			});
			var n = function(t) {
				he.stopPropagation(t);
				var n = he.getXY(t);
				if(e) {
					var s = e.y - n.y;
					if(s == 0) return;
					var a = i.maxUI.offsetHeight,
						r = a + s - 2;
					if(r > $ || r < I) return;
					i.maxUI.style.height = r + "px";
					var o = J.ui,
						l = te.ui,
						d = o.offsetHeight || l.offsetHeight;
					o.style.height = d + s + "px";
					l.style.height = d + s + "px";
					J.box.style.height = d + s - 20 + "px";
					e = n
				}
				he.preventDefault(t)
			};
			var s = function(t) {
				he.stopPropagation(t);
				he.removeEvent(document.body, "mousemove", n);
				he.removeEvent(document.body, "mouseup", s);
				he.preventDefault(t)
			}
		},
		setBoxStatus: function(t) {
			if(t != this.uistatus) {
				this.uistatus = t;
				if(t == N.min) {
					he.show(this.minUI);
					he.hide(this.maxUI)
				} else if(t == N.max) {
					he.show(this.maxUI);
					he.hide(this.minUI)
				}
			}
			return this
		},
		setMyInfo: function(t) {
			if(t) {
				var e = he.$("im_mv_face"),
					i = he.$("im_mmv_face");
				e.title = t.name;
				e.src = i.src = t.face[0]
			}
		},
		setMyState: function(t) {
			if(t !== undefined) {
				var e = ["离线", "在线", "隐身"],
					i = ["im_offline", "im_online", "im_hiding"],
					n = he.$("im_state_change").getElementsByTagName("a")[0],
					s = he.$("im_mv_state"),
					a = this.minUI.getElementsByTagName("span")[0],
					r;
				n.innerHTML = e[t];
				r = i[t];
				he.setClass(s, r);
				he.setClass(a, r);
				this.hideStateList()
			}
		},
		showMsgTip: function(t) {
			he.show(he.$("im_mv_msgtip"));
			he.show(he.$("im_mmv_msgtip"));
			if(t) he.setClass(he.$("im_mv_sys_ico"), "im_list_pos_sys_light")
		},
		hideMsgTip: function() {
			he.hide(he.$("im_mv_msgtip"));
			he.hide(he.$("im_mmv_msgtip"));
			he.setClass(he.$("im_mv_sys_ico"), "im_list_pos_sys")
		},
		showStateList: function() {
			he.show(he.$("im_status_list"));
			he.addClass(he.$("im_state_change"), "im_list_title_lf_hover")
		},
		hideStateList: function() {
			he.hide(he.$("im_status_list"));
			he.removeClass(he.$("im_state_change"), "im_list_title_lf_hover")
		},
		toggleStateList: function() {
			he.isHide(he.$("im_status_list")) ? this.showStateList() : this.hideStateList()
		},
		setTabs: function(t) {
			var e = {
				friend: '<a id="im_listtab_friend" href="javascript:void(0)" class="im_cur" title="我的好友" data-list="friend"><em class="im_user"></em></a>',
				recent: '<a id="im_listtab_recent" href="javascript:void(0)" title="最近聊天人" data-list="recent"><em class="im_record"></em></a>'
			};
			var i = he.$("im_listtab"),
				n = he.$("im_listbox"),
				s = "",
				a = 0,
				r = t.length,
				o, l = document.createDocumentFragment();
			for(; a < r; a++) {
				o = t[a];
				s += e[o];
				if(a < r - 1) s += "<span></span>";
				l.appendChild(o == "friend" ? J.create().ui : te.create().ui)
			}
			i.innerHTML = s;
			n.appendChild(l);
			J.addQueryEvent();
			return this
		},
		setCurrentTab: function(t) {
			if(t == "friend") {
				he.show(J.ui);
				he.hide(te.ui);
				he.setClass(he.$("im_listtab_friend"), "im_cur");
				he.setClass(he.$("im_listtab_recent"), "")
			} else if(t == "recent") {
				he.show(te.ui);
				he.hide(J.ui);
				he.setClass(he.$("im_listtab_friend"), "");
				he.setClass(he.$("im_listtab_recent"), "im_cur")
			}
			return this
		}
	};
	var J = {
		ui: null,
		box: null,
		friends: [],
		friendsNoGroup: [],
		friendGroups: {},
		mates: [],
		consulters: [],
		onlines: [],
		gids: {
			online: -1,
			mate: -2,
			consult: -3,
			def: 0
		},
		groupwin: null,
		selectList: null,
		create: function() {
			this.ui = he._("div");
			this.ui.innerHTML = '<div class="im_query_box"><h4 class="im_query">' + '<s class="im_query_left"></s>' + '<s class="im_query_center">' + '<form onsubmit="return false;">' + '<input id="imQueryInput" type="text" name="kw" placeholder="搜索好友">' + "</form>" + "</s>" + '<s class="im_query_right"></s>' + "</h4>" + (s ? '<a class="im_group_add" title="添加分组" data-action="add"></a>' : "") + "</div>" + '<ul id="imQueryResult" class="im_query_result" style="display:none;"></ul>' + '<div class="im_friend_box"></div>';
			he.addClass(this.ui, "im_friend_list_box");
			this.addEvent();
			return this
		},
		createGroup: function(t, e, i, n, a) {
			var r = he._("div");
			he.setClass(r, "im_title_cate");
			r.setAttribute("id", "im_frd_grp_" + t);
			r.setAttribute("data-gid", t);
			r.setAttribute("data-action", "group");
			r.innerHTML = '<a href="javascript:void(0)" class="im_title_cate_tit">' + e + "[<span>0</span>" + (t == this.gids.online ? "" : "/<span>" + i.length + "</span>") + "]" + (s && n && t != 0 ? '<p class="im_group_edit_box">' + '<s class="im_group_mod" title="修改组名" data-action="mod"></s>' + '<s class="im_group_del" title="删除分组" data-action="del"></s>' + "</p>" : "") + "</a>" + this.createList(i, n);
			if(a) he.hide(r);
			return r
		},
		createList: function(t, e) {
			var i = '<ul class="im_title_cate_list" style="display:none;">';
			if(t) {
				var n = 0,
					a = t.length,
					r, o = "";
				for(; n < a; n++) {
					r = t[n];
					o += '<li data-action="talk" id="im_frd_li_' + r.uid + '" data-uid="' + r.uid + '">' + '<div class="im_title_cate_list_img">' + '<img src="' + (r.face[1] || C) + '"><span class="im_offline"></span>' + "</div>" + '<a href="javascript:void(0)" title="' + (r.longName || r.uid) + '" class="im_listName">' + (r.longName || r.uid) + (s && e ? '<p class="im_friend_edit_box">' + '<s class="im_friend_move" title="迁移到其他分组" data-action="move"></s>' + '<s class="im_friend_del" title="解除好友关系" data-action="release"></s>' + "</p>" : "") + "</a>" + "</li>"
				}
				i += o
			}
			i += "</ul>";
			return i
		},
		addEvent: function() {
			var t = this;
			he.addEvent(this.ui, "click", function(e) {
				var i = e.target;
				var n = he.getAttribute(i, "data-action", true);
				if(!n) return;
				switch(n[0]) {
					case "group":
						var s = n[1],
							a = t.ison(s) ? 0 : 1,
							r = s.getAttribute("data-gid");
						t.setFriendStatus(r, a);
						break;
					case "talk":
						var o = he.getAttribute(i, "data-uid", true)[0];
						Z.addTalk(o);
						break;
					case "add":
						t.addGroup();
						break;
					case "mod":
						var r = n[1].parentNode.parentNode.parentNode.getAttribute("data-gid");
						t.modGroup(r);
						break;
					case "del":
						var r = n[1].parentNode.parentNode.parentNode.getAttribute("data-gid");
						t.delGroup(r);
						break;
					case "move":
						he.stopPropagation(e);
						var l = n[1].parentNode.parentNode.parentNode,
							o = l.getAttribute("data-uid"),
							r = l.parentNode.parentNode.getAttribute("data-gid");
						t.moveBefore(o, r, l.parentNode, l.offsetTop);
						break;
					case "release":
						var o = n[1].parentNode.parentNode.parentNode.getAttribute("data-uid");
						t.releaseFriend(o);
						break
				}
			})
		},
		addQueryEvent: function() {
			var t = he.$("imQueryInput");
			he.addEvent(t, "keyup", function() {
				J.query(this.value)
			});
			he.addEvent(t, "focus", function() {
				J.query(this.value)
			});
			if(!("placeholder" in he._("input")) && window.PlaceHolder) new PlaceHolder("imQueryInput", t.getAttribute("placeholder"))
		},
		setFriends: function(t, e) {
			if(e) this.clear();
			this.handle(t);
			var i = document.createDocumentFragment();
			i.appendChild(this.createGroup(this.gids.online, "在线好友", []));
			var n = 0,
				s = t.length,
				a, r, o = s + 2,
				l = he.browser.name == "msie" && he.browser.version <= 7;
			for(; n < s; n++) {
				a = t[n];
				r = this.createGroup(a.id, a.name, a.data, true);
				if(l) r.style.zIndex = o - n;
				i.appendChild(r)
			}
			if(!e) {
				r = this.createGroup(this.gids.mate, "企业同事", []);
				if(l) r.style.zIndex = 2;
				i.appendChild(r);
				r = this.createGroup(this.gids.consult, "询盘", [], false, true);
				if(l) r.style.zIndex = 1;
				i.appendChild(r)
			}
			if(!this.box) this.box = this.ui.childNodes[2];
			this.box.insertBefore(i, he.$("im_frd_grp_" + this.gids.mate));
			if(!d) he.$("im_mmv_count").innerHTML = this.friendsNoGroup.length
		},
		handle: function(t) {
			var e = 0,
				i = t.length,
				n, s, a, r, o, l;
			for(; e < i; e++) {
				n = t[e];
				s = n.data;
				s.sort(function(t, e) {
					return t["name"].localeCompare(e["name"])
				});
				a = {
					id: n.id,
					name: n.name,
					data: []
				};
				this.friends.push(a);
				this.friendGroups[n.id] = a;
				r = 0;
				o = s.length;
				for(; r < o; r++) {
					l = s[r];
					Z.setUserInfo(l.uid, l, true);
					a.data.push(l.uid);
					this.friendsNoGroup.push(l.uid)
				}
			}
		},
		isFriend: function(t) {
			return he.indexOf(this.friendsNoGroup, t) > -1
		},
		moveFriend: function(t, e) {
			var i = he.indexOf(this.friendsNoGroup, t);
			if(i > -1) {
				var n = 0,
					s = this.friends.length,
					a, r;
				for(; n < s; n++) {
					a = this.friends[n];
					r = he.indexOf(a.data, t);
					if(r > -1) {
						a.data.splice(r, 1);
						break
					}
				}
				var o = this.friendGroups[e].data,
					l = o[this.insertIntoGroup(t, o) + 1],
					d = he.$("im_frd_grp_" + a.id),
					u = he.$("im_frd_grp_" + e),
					c = u.getElementsByTagName("UL")[0],
					f = d.getElementsByTagName("span"),
					m = u.getElementsByTagName("span");
				c.insertBefore(he.$("im_frd_li_" + t), he.$("im_frd_li_" + l));
				if(he.browser.name == "msie" && he.browser.version <= 6) c.style.zoom = "100%";
				f[1].innerText = a.data.length;
				m[1].innerText = o.length;
				if(v[t] == L.online) {
					f[0].innerText = parseInt(f[0].innerText) - 1;
					m[0].innerText = parseInt(m[0].innerText) + 1
				}
			}
		},
		removeFriend: function(t) {
			var e = he.indexOf(this.friendsNoGroup, t);
			if(e > -1) {
				var i = 0,
					n = this.friends.length,
					s, a;
				for(; i < n; i++) {
					s = this.friends[i];
					a = he.indexOf(s.data, t);
					if(a > -1) {
						s.data.splice(a, 1);
						break
					}
				}
				var r = he.$("im_frd_grp_" + s.id),
					o = r.getElementsByTagName("UL")[0],
					l = r.getElementsByTagName("span");
				o.removeChild(he.$("im_frd_li_" + t));
				l[1].innerText = parseInt(l[1].innerText) - 1;
				var d = he.$("im_frd_li_online_" + t);
				if(d) {
					var u = he.$("im_frd_grp_" + this.gids.online),
						c = u.getElementsByTagName("UL")[0],
						f = u.getElementsByTagName("span");
					c.removeChild(d);
					f[0].innerText = parseInt(f[0].innerText) - 1;
					l[0].innerText = parseInt(l[0].innerText) - 1
				}
			}
		},
		addFriendGroup: function(t, e) {
			var i = {
				id: t,
				name: e,
				data: []
			};
			this.friendGroups[t] = i;
			this.friends.push(i);
			var n = this.createGroup(t, e, [], true);
			if(he.browser.name == "msie" && he.browser.version <= 7) n.style.zIndex = this.friends.length + 2;
			this.box.insertBefore(n, he.$("im_frd_grp_" + this.gids.online).nextSibling)
		},
		updateFriendGroup: function(t, e) {
			var i = this.friendGroups[t];
			if(i) {
				i.name = e;
				var n = he.$("im_frd_grp_" + t),
					s = n.getElementsByTagName("a")[0],
					a = s.innerHTML;
				a = a.replace(/(.+)(\[.+)/g, e + "$2");
				s.innerHTML = a
			}
		},
		removeFriendGroup: function(t) {
			var e = this.friendGroups[t];
			if(e) {
				delete this.friendGroups[t];
				for(var i = 0, n = this.friends.length, s; i < n; i++) {
					s = this.friends[i];
					if(s.id == t) {
						this.friends.splice(i, 1);
						break
					}
				}
				var a = this.friendGroups[this.gids.def];
				if(!a) this.addFriendGroup(this.gids.def, "未分组");
				var r = a.data,
					o = e.data,
					l = he.$("im_frd_grp_" + this.gids.def),
					d = l.getElementsByTagName("UL")[0],
					u = l.getElementsByTagName("span"),
					i = 0,
					n = o.length,
					c, f, m;
				for(; i < n; i++) {
					c = o[i];
					f = this.insertIntoGroup(c, r);
					d.insertBefore(he.$("im_frd_li_" + c), he.$("im_frd_li_" + r[f + 1]));
					if(he.browser.name == "msie" && he.browser.version <= 6) d.style.zoom = "100%";
					if(v[c] == L.online) u[0].innerText = parseInt(u[0].innerText) + 1
				}
				u[1].innerText = r.length;
				this.box.removeChild(he.$("im_frd_grp_" + t))
			}
		},
		insertIntoGroup: function(t, e) {
			var i = (p[t] || {}).name || "",
				n = v[t],
				s = he.indexOf(e, t),
				a = 0,
				r = e.length,
				o, l, d, u, c;
			if(s > -1) e.splice(s, 1);
			for(; a < r; a++) {
				o = e[a];
				d = (p[o] || {}).name || "";
				u = v[o];
				if(u != n) {
					if(n == L.online) {
						c = a;
						break
					} else continue
				}
				if(!l && i.localeCompare(d) < 0 || i.localeCompare(l) > 0 && i.localeCompare(d) < 0) {
					c = a;
					break
				}
				l = d
			}
			if(c === undefined) c = e.length;
			e.splice(c, 0, t);
			return c
		},
		setFriendInfo: function(t, e) {
			if(e) {
				var i = he.$("im_frd_li_" + t);
				if(i) {
					var n = i.parentNode,
						s = n.parentNode.getAttribute("data-gid"),
						a = this.friendGroups[s].data,
						r = this.insertIntoGroup(t, a);
					this.setInfoToItem(i, e);
					n.insertBefore(i, he.$("im_frd_li_" + a[r + 1]));
					if(he.indexOf(this.onlines, t) > -1) this.setOnlineInfo(t, e)
				}
			}
		},
		setOnlineInfo: function(t, e) {
			var i = he.$("im_frd_li_online_" + t),
				n = this.insertIntoGroup(t, this.onlines);
			this.setInfoToItem(i, e);
			i.parentNode.insertBefore(i, he.$("im_frd_li_online_" + this.onlines[n + 1]))
		},
		setMateInfo: function(t, e) {
			if(e) {
				var i = he.$("im_mt_li_" + t);
				if(i) {
					var n = this.insertIntoGroup(t, this.mates);
					this.setInfoToItem(i, e);
					i.parentNode.insertBefore(i, he.$("im_mt_li_" + this.mates[n + 1]))
				}
			}
		},
		setConsulterInfo: function(t, e) {
			if(e) {
				var i = he.$("im_cst_li_" + t);
				if(i) {
					var n = this.insertIntoGroup(t, this.consulters);
					this.setInfoToItem(i, e);
					i.parentNode.insertBefore(i, he.$("im_cst_li_" + this.consulters[n + 1]))
				}
			}
		},
		setInfoToItem: function(t, e) {
			if(t) {
				var i = t.getElementsByTagName("img")[0],
					n = t.getElementsByTagName("a")[0],
					s = e.longName,
					a = n.getElementsByTagName("p")[0];
				n.title = s;
				if(a) s += '<p class="im_friend_edit_box"><s class="im_friend_move" title="迁移到其他分组" data-action="move"></s><s class="im_friend_del" title="解除好友关系" data-action="release"></s></p>';
				n.innerHTML = s;
				i.src = e.face[1]
			}
		},
		setFriendState: function(t, e) {
			if(e !== undefined) {
				var i = he.$("im_frd_li_" + t);
				if(i) {
					var n = i.parentNode,
						s = n.parentNode.getAttribute("data-gid"),
						a = this.friendGroups[s].data,
						r = this.insertIntoGroup(t, a);
					var o = this.setStateToItem(i, e);
					n.insertBefore(i, he.$("im_frd_li_" + a[r + 1]));
					if(o === true) this.addOnline(t);
					else if(o === false) this.removeOnline(t)
				}
			}
		},
		setMateState: function(t, e) {
			if(e !== undefined) {
				var i = he.$("im_mt_li_" + t);
				if(i) {
					var n = this.insertIntoGroup(t, this.mates);
					this.setStateToItem(i, e);
					i.parentNode.insertBefore(i, he.$("im_mt_li_" + this.mates[n + 1]))
				}
			}
		},
		setConsulterState: function(t, e) {
			if(e !== undefined) {
				var i = he.$("im_cst_li_" + t);
				if(i) {
					var n = this.insertIntoGroup(t, this.consulters);
					this.setStateToItem(i, e);
					i.parentNode.insertBefore(i, he.$("im_cst_li_" + this.consulters[n + 1]))
				}
			}
		},
		setStateToItem: function(t, e) {
			if(t) {
				var i = t.parentNode,
					n = t.parentNode.parentNode,
					s = n.getElementsByTagName("span")[0],
					a = parseInt(s.innerHTML),
					r = t.getElementsByTagName("span")[0],
					o = he.hasClass(r, "im_online") ? L.online : L.offline,
					l = null;
				if(e == o) return null;
				if(e == L.online && o != L.online) {
					a++;
					l = true
				}
				if(e != L.online && o == L.online) {
					a--;
					l = false
				}
				s.innerHTML = a;
				he.setClass(r, e == L.online ? "im_online" : "im_offline");
				return l
			}
			return null
		},
		addOnline: function(t) {
			if(he.indexOf(this.onlines, t) == -1) {
				var e = p[t] || {},
					i = this.onlines[this.insertIntoGroup(t, this.onlines) + 1],
					n = he._("li"),
					s = he.$("im_frd_grp_" + this.gids.online),
					a = s.getElementsByTagName("span")[0],
					r = s.getElementsByTagName("UL")[0];
				n.setAttribute("data-action", "talk");
				n.setAttribute("id", "im_frd_li_online_" + t);
				n.setAttribute("data-uid", t);
				n.innerHTML = '<div class="im_title_cate_list_img">' + '<img src="' + (e.face ? e.face[1] : C) + '"><span class="im_online"></span>' + "</div>" + '<a href="javascript:void(0)" title="' + (e.longName || t) + '" class="im_listName">' + (e.longName || t) + "</a>";
				r.insertBefore(n, he.$("im_frd_li_online_" + i));
				a.innerHTML = this.onlines.length
			}
		},
		removeOnline: function(t) {
			var e = he.indexOf(this.onlines, t);
			if(e > -1) {
				this.onlines.splice(e, 1);
				var i = he.$("im_frd_li_online_" + t),
					n = he.$("im_frd_grp_" + this.gids.online),
					s = n.getElementsByTagName("span")[0],
					a = n.getElementsByTagName("UL")[0];
				a.removeChild(i);
				s.innerHTML = this.onlines.length
			}
		},
		addMate: function(t) {
			if(he.indexOf(this.mates, t) == -1) {
				this.mates.push(t);
				var e = v[t],
					i = he.$("im_frd_grp_" + this.gids.mate),
					n = i.getElementsByTagName("span"),
					s = i.getElementsByTagName("UL")[0],
					a = he._("li");
				a.setAttribute("data-action", "talk");
				a.setAttribute("id", "im_mt_li_" + t);
				a.setAttribute("data-uid", t);
				a.innerHTML = '<div class="im_title_cate_list_img">' + '<img src="' + C + '"><span class="' + (e == L.online ? "im_online" : "im_offline") + '"></span>' + "</div>" + '<a href="javascript:void(0)" title="' + t + '" class="im_listName">' + t + "</a>";
				s.appendChild(a);
				if(e == L.online) n[0].innerText = parseInt(n[0].innerText) + 1;
				n[1].innerHTML = this.mates.length
			}
		},
		isMate: function(t) {
			return he.indexOf(this.mates, t) > -1
		},
		addConsulter: function(t) {
			if(he.indexOf(this.consulters, t) == -1) {
				this.consulters.push(t);
				var e = v[t],
					i = he.$("im_frd_grp_" + this.gids.consult),
					n = i.getElementsByTagName("span"),
					s = i.getElementsByTagName("UL")[0],
					a = he._("li");
				a.setAttribute("data-action", "talk");
				a.setAttribute("id", "im_cst_li_" + t);
				a.setAttribute("data-uid", t);
				a.innerHTML = '<div class="im_title_cate_list_img">' + '<img src="' + C + '"><span class="' + (e == L.online ? "im_online" : "im_offline") + '"></span>' + "</div>" + '<a href="javascript:void(0)" title="' + t + '" class="im_listName">' + t + "</a>";
				s.appendChild(a);
				if(e == L.online) n[0].innerText = parseInt(n[0].innerText) + 1;
				n[1].innerHTML = this.consulters.length;
				he.show(i)
			}
		},
		isConsulter: function(t) {
			return he.indexOf(this.consulters, t) > -1
		},
		setFriendStatus: function(t, e) {
			var i = he.$("im_frd_grp_" + t);
			if(i) {
				var n = i.getElementsByTagName("a")[0],
					s = i.getElementsByTagName("ul")[0];
				if(e == 1) {
					he.addClass(n, "im_title_cate_tit_hover");
					he.show(s)
				} else {
					he.removeClass(n, "im_title_cate_tit_hover");
					he.hide(s)
				}
			}
		},
		ison: function(t) {
			return he.hasClass(t.getElementsByTagName("a")[0], "im_title_cate_tit_hover")
		},
		clear: function() {
			this.box.removeChild(he.$("im_frd_grp_" + this.gids.online));
			for(var t in this.friendGroups) this.box.removeChild(he.$("im_frd_grp_" + t));
			this.friends = [];
			this.friendsNoGroup = [];
			this.friendGroups = {};
			this.onlines = []
		},
		query: function(t) {
			var e = he.$("imQueryResult");
			e.innerHTML = "";
			if(!t) {
				he.hide(e);
				return
			}
			var i = [],
				n = 0,
				s = this.friendsNoGroup.length,
				a, r, o;
			for(; n < s; n++) {
				a = this.friendsNoGroup[n];
				r = p[a];
				if(r.name.indexOf(t) > -1) {
					o = v[a];
					i.push('<li data-action="talk" data-uid="' + a + '"><a href="javascript:;">' + '<img src="' + (r.face[1] || C) + '">' + '<span class="' + (o == L.online ? "im_online" : "im_offline") + '"></span>' + '<span class="name" title="' + (r.longName || a) + '">' + (r.longName || a) + "</span>" + "</a></li>")
				}
				if(i.length >= 10) break
			}
			e.innerHTML = i.join("");
			i.length ? he.show(e) : he.hide(e)
		},
		addGroup: function() {
			if(!this.groupwin) this.groupwin = this.createGroupWin();
			var t = he.$("imGroupForm");
			t["gid"].value = "";
			t["gn"].value = "";
			this.groupwin.show({
				title: "添加分组"
			})
		},
		modGroup: function(t) {
			if(!this.groupwin) this.groupwin = this.createGroupWin();
			var e = he.$("imGroupForm");
			e["gid"].value = t;
			e["gn"].value = this.friendGroups[t].name;
			this.groupwin.show({
				title: "修改分组"
			})
		},
		saveGroup: function(t, e) {
			if(!e) {
				Message.alert("组名不能为空");
				return
			}
			if(!/^[\w\u4E00-\u9FA5（）]{1,20}$/.test(e)) {
				Message.alert("组名中不能有特殊字符");
				return
			}
			var i = {
				"在线好友": 1,
				"未分组": 1,
				"企业同事": 1,
				"询盘": 1
			};
			if(i[e]) {
				Message.alert("禁止使用系统保留组名");
				return
			}
			P.saveFriendGroup(t, e, function(i, n) {
				if(i == 0) {
					t ? J.updateFriendGroup(t, e) : J.addFriendGroup(n, e);
					J.groupwin.hide()
				} else Message.alert((t ? "修改" : "添加") + "分组失败！")
			})
		},
		delGroup: function(t) {
			Message.confirm("删除分组时，该分组下的好友会变成未分组状态。您确定要删除该分组吗？", function() {
				P.delFriendGroup(t, function(e) {
					e == 0 ? J.removeFriendGroup(t) : Message.alert("删除分组失败！")
				})
			})
		},
		moveBefore: function(t, e, i, n) {
			if(!this.selectList) {
				this.selectList = he._("div");
				this.selectList.innerHTML = '<a class="im_close" href="javascript:;" data-action="close"></a><div class="im_group_select_list"></div>';
				he.setClass(this.selectList, "im_group_select");
				he.addEvent(this.selectList, "click", function(t) {
					he.stopPropagation(t);
					t = t.target;
					if(t.tagName == "A") {
						if(t.getAttribute("data-action") == "close") he.hide(J.selectList);
						else J.moveToGroup(this.getAttribute("data-uid"), t.getAttribute("data-gid"))
					}
				})
			}
			i.appendChild(this.selectList);
			he.show(this.selectList);
			var s = "",
				a;
			for(a in this.friendGroups) {
				if(a == e) {
					s += "<span>" + this.friendGroups[a].name + "</span>";
					continue
				}
				s += '<a href="javascript:;" data-gid="' + a + '">' + this.friendGroups[a].name + "</a>"
			}
			this.selectList.childNodes[1].innerHTML = s;
			this.selectList.setAttribute("data-uid", t);
			this.selectList.style.top = n + 30 + "px"
		},
		moveToGroup: function(t, e) {
			if(t === undefined || e === undefined) return;
			P.moveFriend(t, e, function(i) {
				if(i == 0) {
					J.moveFriend(t, e);
					he.hide(J.selectList)
				} else Message.alert("迁移好友到其他分组失败！")
			})
		},
		releaseFriend: function(t) {
			Message.confirm("您确认要删除该好友吗？", function() {
				P.releaseFriend(t, function(e) {
					if(e == 0) J.removeFriend(t);
					else if(e == 3) Message.alert("你们不是好友！");
					else Message.alert("删除好友失败！")
				})
			})
		},
		createGroupWin: function() {
			var t = new XWin({
				width: 260,
				zindex: 1e6,
				content: '<form id="imGroupForm" class="im_group_form">' + '<input type="hidden" name="gid" value="" />' + "<fieldset>" + '<label>组名：<input name="gn" maxlength="7" autocomplete="off"/></label>' + "</fieldset>" + '<fieldset class="center">' + '<a class="im_btn" href="javascript:;">保存</a> ' + '<a class="im_btn" href="javascript:;">取消</a>' + "</fieldset>" + "</form>"
			});
			var e = he.$("imGroupForm");
			he.addEvent(e, "submit", function(t) {
				he.preventDefault(t);
				J.saveGroup(this["gid"].value, he.trim(this["gn"].value))
			});
			he.addEvent(e, "click", function(t) {
				t = t.target;
				if(t.tagName == "A") t.innerText == "保存" ? J.saveGroup(this["gid"].value, he.trim(this["gn"].value)) : J.groupwin.hide()
			});
			return t
		}
	};
	var te = {
		ui: null,
		recents: [],
		create: function() {
			this.ui = he._("div");
			this.ui.innerHTML = '<div class="im_title_cate">' + '<a href="javascript:void(0)" class="im_title_cate_tit im_title_cate_tit_hover">最近聊天人[<span id="im_rct_online_count">0</span>/<span id="im_rct_count">0</span>]</a>' + '<ul class="im_title_cate_list" id="im_rct_list">' + "</ul>" + "</div>";
			he.addClass(this.ui, "im_friend_list_box");
			this.addEvent();
			return this
		},
		createItem: function(t, e, i) {
			var n = he._("li");
			n.setAttribute("data-action", "talk");
			n.setAttribute("id", "im_rct_li_" + t);
			n.setAttribute("data-uid", t);
			n.innerHTML = '<div class="im_title_cate_list_img">' + '<img src="' + (e.face ? e.face[1] : C) + '"><span class="' + (i == L.online ? "im_online" : "im_offline") + '"></span>' + "</div>" + '<a class="im_listName" href="javascript:void(0)" title="' + (e.longName || t) + '">' + (e.longName || t) + "</a>" + '<a href="javascript:void(0)" title="聊天记录"></a>';
			return n
		},
		addEvent: function() {
			var t = this;
			he.addEvent(this.ui, "click", function(t) {
				var e = t.target;
				var i = he.getAttribute(e, "data-action", true);
				if(!i) return;
				if(i[0] == "talk") {
					var n = i[1],
						s = he.getAttribute(n, "data-uid", true)[0],
						a = n.getElementsByTagName("a")[1];
					if(e == a) {
						he.stopPropagation(t);
						if(U) return;
						Z.openHistoryView(s);
						return
					}
					Z.addTalk(s)
				}
			});
			he.addEvent(this.ui, "mouseover", function(t) {
				t = t.target;
				var e = he.getAttribute(t, "data-action", true);
				if(!e) return;
				if(e[0] == "talk") {
					var i = e[1].getElementsByTagName("a")[1];
					he.addClass(e[1], "im_hover");
					he.addClass(i, "im_seeHist")
				}
			});
			he.addEvent(this.ui, "mouseout", function(t) {
				t = t.target;
				var e = he.getAttribute(t, "data-action", true);
				if(!e) return;
				if(e[0] == "talk") {
					var i = e[1].getElementsByTagName("a")[1];
					he.removeClass(e[1], "im_hover");
					he.removeClass(i, "im_seeHist")
				}
			})
		},
		setRecents: function(t) {
			this.recents = t;
			var e = 0,
				i = this.recents.length,
				n, s = document.createDocumentFragment(),
				a = he.$("im_rct_list");
			for(; e < i; e++) {
				n = this.recents[e];
				s.appendChild(this.createItem(n, p[n] || {}, v[n]))
			}
			a.appendChild(s);
			he.$("im_rct_count").innerHTML = i;
			if(d) he.$("im_mmv_count").innerHTML = i
		},
		addRecent: function(t) {
			var e = he.indexOf(this.recents, t);
			if(e == 0) return;
			var i = v[t];
			if(e > 0) this.recents.splice(e, 1);
			else {
				if(i == L.online) {
					var n = he.$("im_rct_online_count");
					n.innerText = parseInt(n.innerText) + 1
				}
			}
			this.recents.unshift(t);
			if(this.recents.length > b) te.removeRecent(this.recents.pop());
			var s = he.$("im_rct_li_" + t),
				a = he.$("im_rct_list");
			if(!s) {
				s = this.createItem(t, p[t] || {}, i);
				he.$("im_rct_count").innerHTML = this.recents.length
			}
			a.insertBefore(s, a.firstChild)
		},
		removeRecent: function(t) {
			var e = he.indexOf(this.recents, t);
			if(e > -1) this.recents.splice(e, 1);
			var i = he.$("im_rct_li_" + t);
			if(i) {
				he.$("im_rct_list").removeChild(i);
				he.$("im_rct_count").innerHTML = this.recents.length;
				if(v[t] == L.online) {
					var n = he.$("im_rct_online_count");
					n.innerText = parseInt(n.innerText) - 1
				}
			}
		},
		isRecent: function(t) {
			return he.indexOf(this.recents, t) > -1
		},
		setRecentInfo: function(t, e) {
			if(e) {
				var i = he.$("im_rct_li_" + t);
				if(i) {
					var n = i.getElementsByTagName("img")[0],
						s = i.getElementsByTagName("a")[0];
					s.innerHTML = s.title = e.longName;
					n.src = e.face[1]
				}
			}
		},
		setRecentState: function(t, e) {
			if(e !== undefined) {
				var i = he.$("im_rct_li_" + t);
				if(i) {
					var n = he.$("im_rct_online_count"),
						s = parseInt(n.innerHTML),
						a = i.getElementsByTagName("span")[0],
						r = he.hasClass(a, "im_online") ? L.online : L.offline;
					if(e == r) return;
					if(e == L.online && r != L.online) s++;
					if(e != L.online && r == L.online) s--;
					n.innerText = s;
					he.setClass(a, e == L.online ? "im_online" : "im_offline")
				}
			}
		}
	};
	var ee = {
		ui: null,
		list: [],
		create: function() {
			this.ui = he._("div");
			this.ui.innerHTML = '<div class="im_alert_box">' + '<div class="im_alert_title">消息盒子<a class="im_close" title="关闭" data-action="close"></a></div>' + '<ul id="im_alert_list" class="im_alert_list"></ul>' + '<div class="im_alert_bar"><a href="javascript:;" data-action="all">查看全部</a></div>' + "</div>" + '<div class="im_alert_arrow"><a></a></div>';
			he.setClass(this.ui, "im_alert");
			he.hide(this.ui);
			o.appendChild(this.ui);
			this.addEvent()
		},
		createItem: function(t, e) {
			var i = y[t] || [];
			var n = he._("li");
			n.setAttribute("id", "im_alert_li_" + t);
			n.innerHTML = '<a href="javascript:;" data-uid="' + t + '" data-action="read">' + '<img src="' + (e.face ? e.face[1] : C) + '">' + '<span class="name" title="' + (e.longName || t) + '">' + (e.longName || t) + "</span>" + '<span class="count">[1]</span>' + "</a>";
			return n
		},
		addEvent: function() {
			he.addEvent(this.ui, "click", function(t) {
				var e = t.target;
				var i = he.getAttribute(e, "data-action", true);
				if(!i) return;
				switch(i[0]) {
					case "close":
						ee.hide();
						break;
					case "read":
						he.stopPropagation(t);
						ee.read(i[1].getAttribute("data-uid"));
						break;
					case "all":
						he.stopPropagation(t);
						ee.readAll();
						break
				}
			});
			he.addEvent(this.ui, "mouseover", function() {
				ee.show()
			});
			he.addEvent(this.ui, "mouseout", function() {
				ee.hide()
			})
		},
		show: function() {
			he.show(this.ui)
		},
		hide: function() {
			he.hide(this.ui)
		},
		add: function(t) {
			if(!this.ui) this.create();
			if(!this.has(t)) {
				this.list.push(t);
				he.$("im_alert_list").appendChild(this.createItem(t, p[t] || {}))
			} else {
				var e = he.$("im_alert_li_" + t);
				var i = y[t] || [];
				e.getElementsByTagName("span")[1].innerText = "[" + i.length + "]"
			}
		},
		has: function(t) {
			return he.indexOf(this.list, t) > -1
		},
		read: function(t) {
			var e = he.indexOf(this.list, t);
			if(e > -1) {
				Z.addTalk(t);
				this.list.splice(e, 1);
				he.$("im_alert_list").removeChild(he.$("im_alert_li_" + t))
			}
			if(this.list.length == 0) this.hide()
		},
		readAll: function() {
			var t = this.list.length;
			while(t--) Z.addTalk(this.list[t]);
			this.list = [];
			he.$("im_alert_list").innerHTML = "";
			this.hide()
		},
		setAlertInfo: function(t, e) {
			if(!e) return;
			var i = he.$("im_alert_li_" + t);
			if(i) {
				i.getElementsByTagName("img")[0].src = e.face[1];
				i.getElementsByTagName("span")[0].innerText = e.longName
			}
		}
	};
	var ie = {
		ui: null,
		maxUI: null,
		minUI: null,
		uistatus: -1,
		talkers: [],
		curTalker: "",
		cardList: {},
		tipTimers: {},
		create: function() {
			this.ui = he._("div");
			this.ui.className = "im_talkbox";
			this.maxUI = this.createMaxUI();
			this.minUI = this.createMinUI();
			this.ui.appendChild(this.maxUI);
			this.ui.appendChild(this.minUI);
			o.appendChild(this.ui);
			this.addEvent();
			this.addDrag();
			return this
		},
		createMaxUI: function() {
			var t = he._("div");
			he.addClass(t, "im_chat_window");
			t.innerHTML = '<div class="im_chat_window_title" data-action="move">' + '<div class="im_chat_window_title_img">' + '<img id="im_tb_face" src="' + C + '">' + '<span class="im_offline" id="im_tb_state"></span>' + "</div>" + '<p><a href="javascript:void(0)" id="im_tb_title" target="_blank"></a></p>' + '<div class="im_chat_window_title_rt">' + '<a href="javascript:void(0)" class="im_close" title="关闭" data-action="close"></a>' + '<a href="javascript:void(0)" class="im_narrow" title="最小化" data-action="min"></a>' + "</div>" + "</div>" + '<div class="im_chat_window_lf">' + '<a href="javascript:void(0)" class="im_chat_window_lf_arrow_top" title="上移" data-action="up"></a>' + '<a href="javascript:void(0)" class="im_chat_window_lf_arrow_bottom" title="下移" data-action="down"></a>' + '<ul id="im_tb_tl"></ul>' + "</div>";
			return t
		},
		createMinUI: function() {
			var t = he._("div");
			he.addClass(t, "im_chat_window_min");
			t.setAttribute("data-action", "max");
			t.style.display = "none";
			t.innerHTML = '<em class="im_offline" id="im_mtb_state"></em>' + '<span id="im_mtb_curt"></span>' + '<a id="im_mtb_msgtip" class="im_friend_min_tip" style="display:none;"></a>';
			return t
		},
		createItem: function(t, e, i) {
			var n = he._("li");
			n.setAttribute("title", t);
			n.setAttribute("data-action", "talker");
			n.setAttribute("data-uid", t);
			n.setAttribute("id", "im_tbk_li_" + t);
			he.setClass(n, t == this.curTalker ? "im_cur" : "");
			n.innerHTML = '<span class="' + (t == h ? "im_sys" : i == L.online ? "im_online" : "im_offline") + '"></span>' + '<a href="javascript:void(0)" class="im_name">' + (e.name || t) + "</a>" + '<a href="javascript:void(0)"></a>';
			return n
		},
		addEvent: function() {
			var t = this,
				e = he.$("im_tb_tl");
			he.addEvent(this.ui, "click", function(e) {
				var i = e.target;
				var n = he.getAttribute(i, "data-action", true);
				if(!n) return;
				switch(n[0]) {
					case "min":
						Z.setTalkBoxStatus(N.min);
						break;
					case "max":
						Z.setTalkBoxStatus(N.max);
						break;
					case "close":
						Z.setTalkBoxStatus(N.close);
						break;
					case "up":
						t.scroll(-35);
						break;
					case "down":
						t.scroll(35);
						break;
					case "talker":
						var s = n[1],
							a = n[1].getAttribute("data-uid");
						if(i == s.getElementsByTagName("a")[1]) {
							he.stopPropagation(e);
							Z.removeTalk(a);
							return
						}
						Z.addTalk(a);
						break
				}
			});
			he.addEvent(this.minUI, "mouseover", function(e) {
				he.addClass(t.minUI, "im_chat_window_min_hover")
			});
			he.addEvent(this.minUI, "mouseout", function(e) {
				he.removeClass(t.minUI, "im_chat_window_min_hover")
			});
			he.addEvent(e, "mouseover", function(t) {
				t = t.target;
				var e = he.getAttribute(t, "data-action", true);
				if(!e) return;
				switch(e[0]) {
					case "talker":
						var i = e[1].getElementsByTagName("a")[1];
						he.addClass(i, "im_title_cate_list_close");
						break
				}
			});
			he.addEvent(e, "mouseout", function(t) {
				t = t.target;
				var e = he.getAttribute(t, "data-action", true);
				if(!e) return;
				switch(e[0]) {
					case "talker":
						var i = e[1].getElementsByTagName("a")[1];
						he.removeClass(i, "im_title_cate_list_close");
						break
				}
			})
		},
		addDrag: function() {
			if(he.browser.name == "msie" && he.browser.version <= 6) return;
			var t = this,
				e = null,
				i = 0,
				n = 0,
				s = document.body.clientWidth || document.documentElement.clientWidth,
				a = document.body.clientHeight || document.documentElement.clientHeight;
			he.addEvent(this.maxUI, "mousedown", function(s) {
				he.stopPropagation(s);
				l(s);
				var a = s.target,
					o = he.getAttribute(a, "data-action", true);
				if(o && o[0] == "move") {
					e = he.getXY(s);
					i = t.maxUI.offsetWidth;
					n = t.maxUI.offsetHeight;
					he.addEvent(document.body, "mousemove", r);
					he.addEvent(document.body, "mouseup", l);
					he.mouseleave(document.body, l)
				}
				he.preventDefault(s)
			});
			var r = function(r) {
				he.stopPropagation(r);
				var l = he.getXY(r),
					d = {
						x: l.x - e.x,
						y: l.y - e.y
					};
				var u = {
						left: t.maxUI.offsetLeft + o.offsetLeft,
						top: t.maxUI.offsetTop + o.offsetTop
					},
					c = {
						left: u.left + d.x,
						top: u.top + d.y
					};
				if(c.left < 0) c.left = 0;
				if(c.top < 0) c.top = 0;
				if(c.left + i > s) c.left = s - i;
				if(c.top + n > a) c.top = a - n;
				t.maxUI.style.left = c.left - o.offsetLeft + "px";
				t.maxUI.style.top = c.top - o.offsetTop + "px";
				e = l;
				he.preventDefault(r)
			};
			var l = function(t) {
				he.stopPropagation(t);
				he.removeEvent(document.body, "mousemove", r);
				he.removeEvent(document.body, "mouseup", l);
				he.removeEvent(document.body, "mouseleave", l);
				he.preventDefault(t)
			}
		},
		setBoxStatus: function(t) {
			if(this.uistatus != t) {
				this.uistatus = t;
				if(t == N.min) {
					he.show(this.minUI);
					he.hide(this.maxUI);
					he.show(this.ui)
				} else if(t == N.max) {
					he.show(this.maxUI);
					he.hide(this.minUI);
					he.show(this.ui)
				} else if(t == N.close) {
					he.hide(this.ui);
					this.maxUI.style.left = "auto";
					this.maxUI.style.top = "auto"
				}
			}
			return this
		},
		setCurTalker: function(t) {
			var e = he.$("im_tbk_li_" + this.curTalker),
				i = he.$("im_tbk_li_" + t),
				n = this.cardList[this.curTalker],
				s = this.cardList[t];
			he.removeClass(e, "im_cur");
			he.addClass(i, "im_cur");
			if(n) {
				n.hide();
				if(this.maxUI.contains(n.ui)) this.maxUI.removeChild(n.ui)
			}
			if(s) {
				this.maxUI.appendChild(s.ui);
				s.show()
			}
			this.curTalker = t;
			this.setCurTalkerInfo(p[t] || {});
			this.setCurTalkerState(v[t])
		},
		setTalkerInfo: function(t, e) {
			if(e) {
				var i = he.$("im_tbk_li_" + t),
					n, s = this.cardList[t];
				if(i) {
					n = i.getElementsByTagName("a")[0];
					i.title = n.innerHTML = n.title = e.name
				}
				if(s) s.setTalkerInfo(e)
			}
		},
		setTalkerState: function(t, e) {
			if(e !== undefined) {
				var i = he.$("im_tbk_li_" + t);
				if(i) he.setClass(i.getElementsByTagName("span")[0], t == h ? "im_sys" : e == L.online ? "im_online" : "im_offline")
			}
		},
		setTalkerTopic: function(t, e) {
			var i = this.cardList[t];
			if(i && i instanceof ne) i.setTopic(e)
		},
		setCurTalkerInfo: function(t) {
			var e = he.$("im_tb_title"),
				i = he.$("im_tb_face"),
				n = he.$("im_mtb_curt");
			e.innerHTML = n.innerHTML = t.longName || this.curTalker;
			e.setAttribute("href", t.url || "javascript:;");
			i.src = t.face ? t.face[2] : C
		},
		setCurTalkerState: function(t) {
			var e = this.curTalker == h ? "im_sys" : t == L.online ? "im_online" : "im_offline";
			he.setClass(he.$("im_tb_state"), e);
			he.setClass(he.$("im_mtb_state"), e)
		},
		addTalker: function(t) {
			if(!this.isTalker(t)) {
				t == h ? this.talkers.unshift(t) : this.talkers.push(t);
				var e = he.$("im_tbk_li_" + t);
				if(!e) {
					e = this.createItem(t, p[t] || {}, v[t]);
					ul = he.$("im_tb_tl");
					t == h ? ul.insertBefore(e, ul.firstChild) : ul.appendChild(e)
				}
				if(!this.cardList[t]) {
					var i = t === h ? se : new ne(t);
					i.create();
					this.cardList[t] = i
				}
				return true
			}
			return false
		},
		removeTalker: function(t) {
			var e = he.indexOf(this.talkers, t);
			if(e > -1) {
				var i = he.$("im_tbk_li_" + t),
					n = this.cardList[t];
				if(i) he.$("im_tb_tl").removeChild(i);
				if(n) {
					this.maxUI.removeChild(n.ui);
					delete this.cardList[t]
				}
				this.talkers.splice(e, 1);
				if(t == this.curTalker) {
					if(e >= this.talkers.length) e--;
					this.setCurTalker(this.talkers[e])
				}
				return this.curTalker
			}
			return false
		},
		clearTalker: function() {
			this.talkers = [];
			this.curTalker = "";
			he.$("im_tb_tl").innerHTML = "";
			for(var t in this.cardList) this.maxUI.removeChild(this.cardList[t].ui);
			this.cardList = {};
			for(var e in this.tipTimers) clearInterval(this.tipTimers[e]);
			this.tipTimers = {}
		},
		isTalker: function(t) {
			return he.indexOf(this.talkers, t) > -1
		},
		getTalker: function(t) {
			return this.talkers[t]
		},
		length: function() {
			return this.talkers.length
		},
		getCurCard: function() {
			return this.cardList[this.curTalker]
		},
		showMsgs: function(t) {
			if(t) {
				if(!he.isArray(t)) t = [t];
				var e = 0,
					i = t.length,
					n, s, a;
				for(; e < i; e++) {
					n = t[e];
					s = n.from == m ? n.to : n.from;
					a = this.cardList[s];
					if(a) a.addMsg(n)
				}
			}
		},
		addErrorMsg: function(t, e) {
			var i = this.cardList[t];
			if(i) i.addErrorMsg(e)
		},
		showMsgTip: function(t) {
			he.show(he.$("im_mtb_msgtip"));
			if(!this.tipTimers[t]) {
				var e = setInterval(function(t) {
					var e = he.$("im_tbk_li_" + t);
					if(e) he.hasClass(e, "im_flicker") ? he.removeClass(e, "im_flicker") : he.addClass(e, "im_flicker")
				}, 500, t);
				this.tipTimers[t] = e
			}
		},
		hideMsgTip: function(t) {
			var e = this.tipTimers[t];
			if(e) {
				clearInterval(e);
				delete this.tipTimers[t];
				var i = he.$("im_tbk_li_" + t);
				if(i) he.removeClass(i, "im_flicker");
				if(he.isEmpty(this.tipTimers)) he.hide(he.$("im_mtb_msgtip"))
			}
		},
		scroll: function(t) {
			he.$("im_tb_tl").scrollTop += t
		},
		focus: function() {
			var t = this.getCurCard();
			if(t && t instanceof ne) t.focus()
		},
		setEnterWay: function(t) {
			var e, i;
			for(i in this.cardList) {
				e = this.cardList[i];
				if(e && e instanceof ne) e.setEnterWay(t)
			}
		}
	};

	function ne(t) {
		this.talker = t;
		this.topic = "";
		this.ui = null
	}
	ne.prototype = {
		create: function() {
			this.ui = he._("div");
			he.addClass(this.ui, "im_chat_window_rt");
			var t = "来自船讯网用户：" + this.talker + "的对话";
			this.ui.innerHTML = '<div class="im_chat_window_rt_con">' + '<div class="im_warning">' + '<p id="im_tc_tpc_' + this.talker + '" title="' + t + '">' + t + "</p>" + "</div>" + '<div class="im_content" id="im_tc_cnt_' + this.talker + '"></div>' + '<div class="im_tip" style="display:none;">' + '<p id="im_tc_tip_' + this.talker + '"></p>' + "</div>" + "</div>" + '<div class="im_face_box" id="im_tc_fccom_' + this.talker + '">' + '<a href="javascript:void(0)" class="im_face" id="im_tc_fc_' + this.talker + '" title="发送表情"></a>' + '<a href="javascript:void(0)" class="im_history" id="im_tc_hs_' + this.talker + '">聊天记录</a>' + "</div>" + '<div class="im_sendBox">' + '<textarea id="im_tc_txa_' + this.talker + '"></textarea>' + "</div>" + '<div class="im_sendBoxBtn" id="im_tc_sdbox_' + this.talker + '">' + '<span id="im_tc_msglen_' + this.talker + '">1024</span>' + '<div class="im_btn_send">' + '<a href="javascript:void(0)" class="im_send" id="im_tc_snd_' + this.talker + '" title="' + j[G] + '"></a>' + '<a href="javascript:void(0)" class="im_choose" id="im_tc_fcchs_' + this.talker + '"></a>' + "</div>" + "</div>";
			this.addEvent()
		},
		createItem: function(t, e, i, n, s) {
			var a = he._("dl");
			if(t) a.setAttribute("id", "im_tc_msg_" + t);
			a.innerHTML = "<dt>" + '<span class="im_' + s + '">' + e + "</span>" + '<span class="im_time">' + he.formatTime(n) + "</span>" + "</dt>" + "<dd>" + he.parseLineBreak(he.parseFace(he.parseURL(i))) + "</dd>";
			var r = a.getElementsByTagName("img");
			if(r.length > 0) {
				r = r[0];
				var o = this;
				he.addEvent(r, "load", function() {
					o.scrollToBottom();
					he.removeEvent(r, "load", arguments.callee)
				})
			}
			return a
		},
		addEvent: function() {
			var t = this;
			he.addEvent(this.ui, "click", function(e) {
				e = e.target;
				switch(e) {
					case he.$("im_tc_snd_" + t.talker):
						t.sendMsg();
						break;
					case he.$("im_tc_hs_" + t.talker):
						if(U) {
							t.tip("您已离线，无法查看聊天记录");
							return
						}
						Z.openHistoryView(t.talker);
						break;
					case he.$("im_tc_fc_" + t.talker):
						de();
						break;
					case he.$("im_tc_fcchs_" + t.talker):
						ce();
						break
				}
			});
			he.addEvent(this.ui, "dragstart", function(t) {
				var e = t.target;
				if(e.tagName == "IMG") {
					if(t.dataTransfer && t.dataTransfer.setData) t.dataTransfer.setData("Text", e.title)
				}
			});
			he.addEvent(this.ui, "mousedown", function(t) {
				he.stopPropagation(t)
			});
			he.addEvent(this.ui, "selectstart", function(t) {
				he.stopPropagation(t);
				return true
			})
		},
		addInputEvent: function() {
			var t = he.$("im_tc_txa_" + this.talker),
				e = this;
			he.addEvent(t, "keydown", function(i) {
				var n = window.event ? i.keyCode : i.which;
				if(n == 13) {
					if(G == A.enter) {
						i.ctrlKey ? t.value += "\r\n" : e.sendMsg();
						he.preventDefault(i)
					} else if(G == A.ctrl) {
						if(i.ctrlKey) e.sendMsg()
					}
				}
			});
			he.addEvent(t, "keypress", function() {
				e.updateCount()
			});
			he.addEvent(t, "keyup", function() {
				e.updateCount()
			});
			t.onpaste = function() {
				var t = setTimeout(function() {
					e.updateCount();
					clearTimeout(t)
				}, 100)
			}
		},
		show: function() {
			he.show(this.ui);
			if(!this.isRegisted) {
				this.addInputEvent();
				this.isRegisted = true
			}
			this.scrollToBottom()
		},
		hide: function() {
			he.hide(this.ui)
		},
		setTalkerInfo: function(t) {
			if(!t || this.topic) return;
			var e = he.$("im_tc_tpc_" + this.talker);
			e.innerHTML = e.title = "来自船讯网用户：" + t.name + "的对话"
		},
		setTopic: function(t) {
			this.topic = t || "";
			if(this.topic) {
				var e = he.$("im_tc_tpc_" + this.talker);
				var i = this.topic.indexOf("[url="),
					n = "",
					s = this.topic;
				if(i > -1) {
					n = this.topic.substring(i + 5, this.topic.length - 1);
					s = this.topic.substring(0, i)
				}
				e.setAttribute("title", s);
				var a = d ? "" : "主题：";
				e.innerHTML = a + (n ? '<a href="' + n + '" target="_blank">' + s + "</a>" : s)
			} else this.setTalkerInfo(p[this.talker]);
			this.updateContentBoxHeight()
		},
		addMsg: function(t) {
			if(!t) return;
			var e = he.$("im_tc_cnt_" + this.talker);
			if(e) {
				var i = e.getElementsByTagName("dl"),
					n = "im_tc_msg_" + t.id,
					s = he.$(n);
				var a = t.from,
					r = p[a] || {},
					o = r.name || a,
					l = a == m ? "my" : a == this.talker ? "friend" : "sys";
				if(!s) s = this.createItem(t.id, o, t.text, t.time, l);
				re(e, i, s, n);
				this.scrollToBottom();
				return s
			}
			return null
		},
		addErrorMsg: function(t) {
			this.addMsg(t)
		},
		sendMsg: function() {
			if(!this.validate()) return;
			var t = he.$("im_tc_cnt_" + this.talker),
				e = this.createMsg();
			P.postMsg.call(this, e, this.addMsg(e), function() {
				var t = this.el.getElementsByTagName("span")[1];
				t.innerHTML = he.formatTime(this.data.time);
				this.el.setAttribute("id", "im_tc_msg_" + this.data.id)
			});
			this.clearInput();
			this.clearCountOut();
			this.scrollToBottom()
		},
		validate: function() {
			if(U) {
				this.tip("您已离线，无法发消息");
				return false
			}
			var t = he.$("im_tc_txa_" + this.talker).value;
			if(!t) {
				this.tip("不能发空消息");
				return false
			}
			if(t.length > 1024) {
				this.tip("您输入的消息超过1024个字符，请删掉一部分再发送");
				return false
			}
			return true
		},
		createMsg: function() {
			var t = p[m] || {},
				e = p[this.talker] || {};
			var i = "";
			if(!d) {
				if(J.isMate(this.talker)) i = B.mate;
				else if(J.isConsulter(this.talker)) {
					i = V(this.talker) ? B.consultReply : B.consulter
				}
			}
			return {
				from: m,
				fromname: t.name || m,
				to: this.talker,
				toname: e.name || this.talker,
				text: he.encodeHTML(he.$("im_tc_txa_" + this.talker).value),
				time: Math.round((new Date).getTime() / 1e3),
				remark: this.topic,
				ext: i
			}
		},
		tip: function(t) {
			if(!t) return;
			var e = he.$("im_tc_tip_" + this.talker),
				i = e.parentNode;
			e.innerHTML = t;
			he.show(i);
			var n = 0;
			if(this.tipTimer) {
				clearInterval(this.tipTimer);
				he.setAlpha(i, 1)
			}
			this.tipTimer = setInterval(function() {
				n++;
				if(n >= 10) {
					clearInterval(this.tipTimer);
					he.hide(i);
					he.setAlpha(i, 1);
					return
				}
				he.setAlpha(i, 1 - n * .1)
			}, 200)
		},
		insertFace: function(t) {
			if(!t) return;
			var e = he.$("im_tc_txa_" + this.talker),
				i = e.value;
			i += t;
			e.value = i;
			e.focus();
			if(document.selection) {
				var n = e.createTextRange();
				n.moveStart("character", i.length);
				n.collapse();
				n.select()
			} else if(typeof e.selectionStart == "number") {
				e.selectionStart = e.selectionEnd = i.length
			}
			this.updateCount()
		},
		updateCount: function() {
			he.$("im_tc_msglen_" + this.talker).innerHTML = 1024 - he.$("im_tc_txa_" + this.talker).value.length
		},
		clearInput: function() {
			he.$("im_tc_txa_" + this.talker).value = "";
			this.updateCount()
		},
		clearCountOut: function() {
			var t = he.$("im_tc_cnt_" + this.talker),
				e = t.getElementsByTagName("DL").length;
			if(e > w) {
				var i = e - w;
				while(i--) cntbox.removeChild(cntbox.firstChild)
			}
		},
		scrollToBottom: function() {
			var t = he.$("im_tc_cnt_" + this.talker);
			if(t) t.scrollTop = t.scrollHeight;
			this.updateContentBoxHeight()
		},
		updateContentBoxHeight: function() {
			var t = he.$("im_tc_cnt_" + this.talker);
			if(t) {
				var e = t.previousSibling,
					i = t.parentNode,
					n = i.offsetHeight - e.offsetHeight - 1;
				if(n > 0) t.style.height = n + "px"
			}
		},
		focus: function() {
			he.$("im_tc_txa_" + this.talker).focus()
		},
		setEnterWay: function(t) {
			var e = he.$("im_tc_snd_" + this.talker);
			if(e) e.setAttribute("title", j[t])
		}
	};
	var se = {
		ui: null,
		create: function() {
			this.ui = he._("div");
			he.addClass(this.ui, "im_chat_sysMsg_box");
			this.ui.innerHTML = '<div class="im_chat_sysMsg_con" id="im_sysMsg_con">' + '<span id="im_chat_sysMsg_empty" class="im_chat_sysMsg_empty">您还没有收到系统消息</span>' + "</div>" + '<div class="im_chat_sysMsg_footer"></div>';
			return this
		},
		createItem: function(t) {
			var e = he._("dl");
			if(t.id) e.setAttribute("id", "im_sys_msg_" + t.id);
			e.innerHTML = "<dt>" + '<span class="im_sys_icon"></span>' + '<span class="im_time">' + he.formatTime(t.time) + "</span>" + "</dt>" + "<dd>" + he.parseLineBreak(he.parseURL(t.text)) + "</dd>";
			return e
		},
		show: function() {
			he.show(this.ui)
		},
		hide: function() {
			he.hide(this.ui)
		},
		setTalkerInfo: function(t) {},
		addMsg: function(t) {
			if(!t) return;
			var e = he.$("im_sysMsg_con");
			if(e) {
				he.hide(he.$("im_chat_sysMsg_empty"));
				var i = e.getElementsByTagName("dl"),
					n = "im_sys_msg_" + t.id,
					s = he.$(n);
				if(!s) s = this.createItem(t);
				re(e, i, s, n, true);
				this.scrollToTop()
			}
		},
		scrollToTop: function() {
			var t = he.$("im_sysMsg_con");
			if(t) t.scrollTop = 0
		}
	};
	var ae = {
		ui: null,
		talker: null,
		currentPage: 1,
		create: function() {
			this.ui = he._("div");
			he.setClass(this.ui, "im_chat_history_box");
			this.ui.innerHTML = '<div class="im_chat_history_title">' + '<p>与 <span id="im_hv_title_talker"></span> 的聊天记录</p>' + '<a href="javascript:void(0)" class="im_close" title="关闭" id="im_hv_close"></a>' + "</div>" + '<div class="im_chat_window_rt_con im_chat_history_con">' + '<div class="im_chat_history_con_msg" id="im_hv_msgcon"></div>' + '<div class="im_chat_history_page" id="im_hv_page">' + '<span>分 页</span><select id="im_hv_page_slc"></select>' + "</div>" + '<div class="im_chat_history_loading" id="im_hv_loading">' + '<a href="javascript:void(0)" class="im_hv_loading"></a>' + "<p>正在加载......</p>" + "</div>" + "</div>";
			o.appendChild(this.ui);
			this.addEvent();
			return this
		},
		createItem: function(t) {
			var e = t.from == m,
				i = "";
			if(t.remark) {
				var n = t.remark.indexOf("[url="),
					s = "",
					a = t.remark;
				if(n > -1) {
					s = t.remark.substring(n + 5, t.remark.length - 1);
					a = t.remark.substring(0, n)
				}
				var r = d ? "" : "主题：";
				i = '<span class="im_hv_topic">' + r + (s ? '<a href="' + s + '" target="_blank">' + a + "</a>" : a) + "</span><br>"
			}
			var o = p[t.from] || {},
				l = o.name || t.from,
				u = he._("dl");
			u.innerHTML = "<dt>" + '<span class="' + (e ? "im_my" : "im_friend") + '">' + l + "</span>" + '<span class="im_time">' + he.formatTime(t.time) + "</span>" + "</dt>" + "<dd>" + i + he.parseLineBreak(he.parseFace(he.parseURL(t.text))) + "</dd>";
			return u
		},
		addEvent: function() {
			var t = this;
			he.addEvent(he.$("im_hv_close"), "click", function(e) {
				t.hide()
			});
			he.addEvent(he.$("im_hv_page_slc"), "change", function(e) {
				t.currentPage = this.value;
				t.query(t.currentPage)
			});
			he.addEvent(this.ui, "selectstart", function(t) {
				he.stopPropagation(t);
				return true
			})
		},
		show: function(t) {
			if(!this.ui) this.create();
			he.show(this.ui);
			this.talker = t;
			var e = p[t] || {};
			he.$("im_hv_title_talker").innerHTML = e.name || t;
			this.currentPage = 1;
			he.$("im_hv_page_slc").length = 0;
			if(!U) {
				this.query(this.currentPage)
			} else {
				he.hide(he.$("im_hv_loading"));
				he.$("im_hv_msgcon").innerHTML = '<p class="im_error">网络错误，请检查网络。</p>'
			}
			return this
		},
		hide: function() {
			he.hide(this.ui);
			return this
		},
		setUserInfo: function(t) {
			if(t) he.$("im_hv_title_talker").innerHTML = t.name;
			return this
		},
		showHistory: function(t, e, i) {
			if(t == this.talker) {
				he.hide(he.$("im_hv_loading"));
				var n = he.$("im_hv_msgcon"),
					s = document.createDocumentFragment();
				n.innerHTML = "";
				if(he.isEmpty(e)) {
					var a = he._("p");
					a.className = "im_error";
					a.innerText = "没有该历史聊天记录";
					s.appendChild(a)
				} else {
					for(var r = 0, o = e.length; r < o; r++) s.appendChild(this.createItem(e[r]))
				}
				n.appendChild(s);
				var l = he.$("im_hv_page_slc"),
					d = "",
					u = 1,
					c, f = Math.ceil(i / x);
				l.length = 0;
				for(; u <= f; u++) {
					c = new Option(u, u);
					if(u == this.currentPage) c.selected = "selected";
					l[l.length] = c
				}
			}
			return this
		},
		query: function(t) {
			P.getHistory(this.talker, (t - 1) * x, x);
			he.show(he.$("im_hv_loading"))
		},
		position: function(t) {
			if(this.ui) this.ui.style.right = (t == N.close ? 200 : t == N.min ? 400 : 621) + "px";
			return this
		}
	};

	function re(t, e, i, n, s) {
		var a = e.length,
			r = e[0],
			o = e[a - 1];
		if(a == 0 || (!s ? n > o.id : n < o.id)) {
			t.appendChild(i)
		} else if(!s ? n < r.id : n > r.id) {
			t.insertBefore(i, r)
		} else {
			var l = 1,
				d = r,
				u, c = false;
			for(; l < a; l++) {
				u = e[l];
				if(!s ? n > d.id && n < u.id : n < d.id && n > u.id) {
					c = true;
					t.insertBefore(i, u);
					break
				}
				d = u
			}
			if(!c) !s ? t.appendChild(i) : t.insertBefore(i, r)
		}
	}
	var oe = null,
		le = null;

	function de() {
		if(!oe) oe = ue();
		var t = ie.getCurCard(),
			e = he.$("im_tc_fccom_" + t.talker);
		if(!e.contains(oe)) e.appendChild(oe);
		else he.isHide(oe) ? he.show(oe) : he.hide(oe)
	}

	function ue() {
		var t = he._("div");
		he.setClass(t, "im_face_common");
		var i = "",
			n = 0,
			s = O.length,
			a;
		i = '<ul class="im_face_common_con" style="padding:13px 20px;" data-action="face">';
		for(; n < s; n++) {
			a = O[n];
			i += '<li><a href="javascript:void(0)" title="' + R[a] + '"><img src="' + e + "/images/faces/" + a + '.gif"></a></li>'
		}
		i += "</ul>";
		t.innerHTML = '<span class="im_face_common_arrow"></span>' + '<div class="im_face_common_title">' + "<span>常用表情</span>" + '<a href="javascript:void(0)" class="im_close" id="im_tc_fcclose" title="关闭"></a>' + "</div>" + i;
		he.addEvent(t, "click", function(e) {
			e = e.target;
			var i = he.getAttribute(e, "data-action", true),
				n = e.tagName;
			if(i && i[0] == "face" && n != "UL") {
				if(n == "LI") e = e.firstChild;
				else if(n == "IMG") e = e.parentNode;
				ie.getCurCard().insertFace(e.getAttribute("title"));
				he.hide(t);
				return
			}
			if(e.id == "im_tc_fcclose") he.hide(t)
		});
		return t
	}

	function ce() {
		if(!le) le = fe();
		var t = ie.getCurCard(),
			e = he.$("im_tc_sdbox_" + t.talker);
		if(!e.contains(le)) e.appendChild(le);
		he.isHide(le) ? he.show(le) : he.hide(le)
	}

	function fe() {
		var t = he._("div");
		he.setClass(t, "im_chooseBox");
		t.innerHTML = '<dl data-way="enter"><dt></dt><dd><a href="javascript:void(0)">按Enter键发送</a></dd></dl>' + '<dl data-way="ctrl+enter"><dt></dt><dd><a href="javascript:void(0)">按Ctrl+Enter键发送</a></dd></dl>';
		he.addEvent(t, "click", function(t) {
			t = t.target;
			var e = t.tagName,
				i;
			if(e == "A") i = t.parentNode.parentNode;
			else if(e == "DD") i = t.parentNode;
			else if(e == "DL") i = t;
			if(i) {
				var n = he.hasClass(i, "im_cur");
				if(!n) me(i.getAttribute("data-way"))
			}
			he.hide(le)
		});
		return t
	}

	function me(t) {
		if(!le) le = fe();
		var e = le.getElementsByTagName("dl");
		if(t == A.enter) {
			he.setClass(e[0], "im_cur");
			he.setClass(e[1], "")
		} else if(t == A.ctrl) {
			he.setClass(e[0], "");
			he.setClass(e[1], "im_cur")
		}
		G = t;
		ie.setEnterWay(G);
		he.cookie("enterWay", t)
	}
	var he = {
		browser: {
			name: "",
			version: "0"
		},
		class2type: {},
		$: function(t) {
			return document.getElementById(t)
		},
		_: function(t, e) {
			t = t.toUpperCase();
			return t == "TEXT" ? document.createTextNode(e) : document.createElement(t)
		},
		addEvent: function(t, e, i) {
			if(t.addEventListener) {
				this.addEvent = function(t, e, i) {
					t.addEventListener(e, i, false)
				}
			} else if(t.attachEvent) {
				this.addEvent = function(t, e, i) {
					t["e" + e + i] = i;
					t[e + i] = function() {
						var n = window.event;
						if(!n.target) {
							n.target = n.srcElement
						}
						t["e" + e + i](n)
					};
					t.attachEvent("on" + e, t[e + i])
				}
			} else {
				this.addEvent = function(t, e, i) {
					t["on" + e] = i
				}
			}
			this.addEvent(t, e, i)
		},
		removeEvent: function(t, e, i) {
			if(t.removeEventListener) {
				this.removeEvent = function(t, e, i) {
					t.removeEventListener(e, i, false)
				}
			} else if(t.detachEvent) {
				this.removeEvent = function(t, e, i) {
					if(t[e + i]) {
						t.detachEvent("on" + e, t[e + i]);
						t[e + i] = null
					}
				}
			} else {
				this.removeEvent = function(t, e, i) {
					t["on" + e] = null
				}
			}
			this.removeEvent(t, e, i)
		},
		stopPropagation: function(t) {
			if(t.stopPropagation) {
				this.stopPropagation = function(t) {
					t.stopPropagation()
				}
			} else {
				this.stopPropagation = function(t) {
					t.cancelBubble = true
				}
			}
			this.stopPropagation(t)
		},
		preventDefault: function(t) {
			if(t.preventDefault) {
				this.preventDefault = function(t) {
					t.preventDefault()
				}
			} else {
				this.preventDefault = function(t) {
					t.returnValue = false
				}
			}
			this.preventDefault(t)
		},
		fromElement: function(t) {
			if(t.fromElement) return t.fromElement;
			var e;
			if(t.type == "mouseover") e = t.relatedTarget;
			else if(t.type == "mouseout") e = t.target;
			if(e) {
				while(e.nodeType != 1) e = e.parentNode
			}
			return e
		},
		toElement: function(t) {
			if(t.toElement) return t.toElement;
			var e;
			if(t.type == "mouseout") e = t.relatedTarget;
			else if(t.type == "mouseover") e = t.target;
			if(e) {
				while(e.nodeType != 1) e = e.parentNode
			}
			return e
		},
		getXY: function(t) {
			var e = document.body,
				i = document.documentElement;
			return {
				x: t.pageX || t.clientX + Math.max(e.scrollLeft, i.scrollLeft),
				y: t.pageY || t.clientY + Math.max(e.scrollTop, i.scrollTop)
			}
		},
		now: function() {
			return(new Date).getTime()
		},
		getScript: function(t, e) {
			var i = this._("script");
			i.type = "text/javascript";
			i.onload = i.onreadystatechange = function() {
				if(!this.readyState || this.readyState && (this.readyState == "loaded" || this.readyState == "complete")) {
					i.onload = i.onreadystatechange = i.onerror = null;
					if(e) e.call(this)
				}
			};
			i.onerror = function() {
				i.onload = i.onreadystatechange = i.onerror = null;
				document.getElementsByTagName("head")[0].removeChild(i)
			};
			i.src = t;
			document.getElementsByTagName("head")[0].appendChild(i)
		},
		loadSwf: function(t, e, i, n) {
			if(!window.SWFObject) {
				this.getScript("swfobject.js", function() {
					he.loadSwf(t, e, i, n)
				});
				return
			}
			var s = this._("div");
			this.setClass(s, "im_swf");
			o.appendChild(s);
			var a = new SWFObject(t, e || "swf_" + this.now() + Math.random(), i || 300, n || 150, "", "#ccc", true);
			a.addParam("allowScriptAccess", "always");
			a.addParam("wmode", "transparent");
			a.addParam("bgcolor", "#869ca7");
			a.addVariable("myId", m);
			a.addVariable("version", IM.version);
			a.skipDetect = "false";
			a.write(s);
			return he.browser.name == "msie" ? s.childNodes[0] : s.getElementsByTagName("embed")[0]
		},
		request: function(t, i) {
			if(!t) return;
			i = i || {};
			if(i.async === undefined) i.async = true;
			if(i.cache === undefined) i.cache = false;
			if(i.timeout === undefined) i.timeout = 0;
			i.method = (i.method || "get").toUpperCase();
			i.dataType = i.dataType || "json";
			var n = ve();
			n.onreadystatechange = function() {
				if(i.timeout > 0) clearTimeout(s);
				if(n.readyState === 4) {
					if(n.status === 200) {
						if(i.dataType === "json") {
							try {
								if(!window.JSON) {
									he.getScript(e + "/json.js", function() {
										var t = JSON.parse(n.responseText);
										if(i.success) i.success(t)
									})
								} else {
									var t = JSON.parse(n.responseText);
									if(i.success) i.success(t)
								}
							} catch(a) {
								if(i.error) i.error()
							}
						}
					} else {
						if(i.error) i.error()
					}
				}
			};
			if(i.timeout > 0) {
				var s = setTimeout(function() {
					clearTimeout(s);
					n.abort();
					if(i.ontimeout) i.ontimeout.call(n)
				}, i.timeout * 1e3)
			}
			if(!i.cache && i.method === "GET") t += (t.indexOf("?") > -1 ? "&" : "?") + "_=" + this.now();
			n.open(i.method, t, i.async);
			n.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			if(!i.cache) n.setRequestHeader("Cache-Control", "no-cache");
			if(i.method === "POST") {
				var a = i.data || "";
				if(typeof a === "object") {
					var r = "";
					for(var o in a) {
						r += o + "=" + encodeURIComponent(a[o]);
						r += "&"
					}
					r = r.substring(0, r.length - 1);
					a = r
				}
				n.send(a)
			} else n.send(null);
			return n
		},
		getBrowser: function() {
			var t = navigator.userAgent.toLowerCase();
			var e = /(chrome)[ \/]([\w.]+)/.exec(t) || /(webkit)[ \/]([\w.]+)/.exec(t) || /(opera)(?:.*version|)[ \/]([\w.]+)/.exec(t) || /(msie) ([\w.]+)/.exec(t) || t.indexOf("compatible") < 0 && /(mozilla)(?:.*? rv:([\w.]+)|)/.exec(t) || [];
			this.browser.name = e[1] || "";
			this.browser.version = e[2] || "0"
		},
		getTypes: function() {
			var t = ["Boolean", "Number", "String", "Function", "Array", "Date", "RegExp", "Object"];
			for(var e = 0, i = t.length, n; e < i; e++) {
				n = t[e];
				this.class2type["[object " + n + "]"] = n.toLowerCase()
			}
		},
		isArray: function(t) {
			return this.class2type[Object.prototype.toString.call(t)] === "array"
		},
		isEmpty: function(t) {
			if(!t) return true;
			if(t instanceof Array) return !t.length;
			for(var e in t) return false;
			return true
		},
		length: function(t) {
			if(!t) return 0;
			if(t instanceof Array) return t.length;
			var e = 0;
			for(var i in t) e++;
			return e
		},
		indexOf: function(t, e, i) {
			if(!t) return -1;
			i = i || 0;
			if(t.indexOf) return t.indexOf(e, i);
			for(var n = i, s = t.length; n < s; n++)
				if(t[n] == e) return n;
			return -1
		},
		lastIndexOf: function(t, e, i) {
			if(!t) return -1;
			i = i || 0;
			if(t.lastIndexOf) return t.lastIndexOf(e, i);
			for(var n = t.length; n >= i; n--)
				if(t[n] == e) return n;
			return -1
		},
		formatTime: function(t) {
			if(!t) return "";
			var e = new Date(t * 1e3);
			return e.getFullYear() + "-" + this.addZero(e.getMonth() + 1) + "-" + this.addZero(e.getDate()) + " " + this.addZero(e.getHours()) + ":" + this.addZero(e.getMinutes()) + ":" + this.addZero(e.getSeconds())
		},
		addZero: function(t) {
			return t < 10 ? "0" + t : t
		},
		ltrim: function(t) {
			if(!t) return t;
			if(t.ltrim) return t.ltrim();
			if(t.trimRight) return t.trimRight();
			return t.replace(/\s+$/, "")
		},
		rtrim: function(t) {
			if(!t) return t;
			if(t.ltrim) return t.ltrim();
			if(t.trimLeft) return t.trimLeft();
			return t.replace(/^\s+/, "")
		},
		trim: function(t) {
			if(t.trim) return t.trim();
			return this.rtrim(this.ltrim(t))
		},
		show: function(t) {
			if(t) t.style.display = ""
		},
		hide: function(t) {
			if(t) t.style.display = "none"
		},
		isHide: function(t) {
			return t ? t.style.display == "none" : false
		},
		setClass: function(t, e) {
			if(t) t.className = e
		},
		getClass: function(t) {
			if(t) return t.className.replace(/\s+/, " ").split(" ");
			return []
		},
		addClass: function(t, e) {
			if(!t || this.hasClass(t, e)) return false;
			t.className += (t.className ? " " : "") + e;
			return true
		},
		removeClass: function(t, e) {
			if(t) {
				var i = this.getClass(t),
					n = i.length,
					s;
				while(n--) {
					if(i[n] === e) {
						delete i[n];
						break
					}
				}
				t.className = i.join(" ");
				return n == i.length ? false : true
			}
			return false
		},
		hasClass: function(t, e) {
			if(t) {
				var i = this.getClass(t);
				for(var n = 0, s = i.length; n < s; n++) {
					if(i[n] === e) return true
				}
			}
			return false
		},
		hasChild: function(t, e) {
			if(!t || !e) return false;
			var i = e.tagName;
			var n = t.getElementsByTagName(i);
			for(var s = 0, a = n.length; s < a; s++) {
				if(n[s] == e) return true
			}
			return false
		},
		getAttribute: function(t, e, i) {
			if(!t || !e || !t.getAttribute) return null;
			if(!i) return t.getAttribute(e);
			var n;
			while(t) {
				if(!t.getAttribute) return null;
				n = t.getAttribute(e);
				if(n) break;
				t = t.parentNode
			}
			return [n, t]
		},
		setAlpha: function(t, e) {
			if(t) {
				t.style.opacity = e;
				t.style.filter = "alpha(opacity = " + e * 100 + ")"
			}
		},
		parseURL: function(t) {
			t = t || "";
			return t.replace(/(http[s]?:\/\/[^\s\[]+)/gi, '<a target="_blank" title="点击跳转" href="$1">$1</a>')
		},
		parseLineBreak: function(t) {
			t = t || "";
			return t.replace(/\n/g, "<br>")
		},
		parseFace: function(t) {
			t = t || "";
			return t.replace(/\[([^\[\]]*)\]/g, function(t) {
				var i;
				for(var n in R) {
					if(R[n] == t) {
						i = n;
						break
					}
				}
				return i ? '<img src="' + e + "/images/faces/" + i + '.gif" title="' + t + '">' : t
			})
		},
		encodeHTML: function(t) {
			return String(t).replace(/&/g, "&amp;").replace(/\</g, "&lt;").replace(/\>/g, "&gt;").replace(/ /g, "&nbsp;")
		},
		decodeHTML: function(t) {
			return String(t).replace(/&amp;/g, "&").replace(/&lt;/g, "<").replace(/&gt;/g, ">").replace(/&nbsp;/g, " ")
		},
		mouseleave: function(t, e) {
			if(this.browser.name != "msie") {
				this.addEvent(t, "mouseout", _e(e))
			} else this.addEvent(t, "mouseleave", e)
		},
		mouseenter: function(t, e) {
			if(this.browser.name != "msie") {
				this.addEvent(t, "mouseover", _e(e))
			} else this.addEvent(t, "mouseenter", e)
		},
		augment: function(t, e, i) {
			t = t || {};
			e = e || {};
			for(var n = 0, s = i.length; n < s; n++) {
				var a = i[n];
				if(!t.hasOwnProperty(a) && e.hasOwnProperty(a)) t[a] = e[a]
			}
		},
		cookie: function(t, e, i, n, s, a) {
			if(e === undefined) {
				var r = document.cookie,
					o = encodeURIComponent(t) + "=",
					l = r.indexOf(o),
					d = null;
				if(l > -1) {
					var u = r.indexOf(";", l);
					if(u === -1) u = r.length;
					d = decodeURIComponent(r.substring(l + o.length, u))
				}
				return d
			} else {
				var c = encodeURIComponent(t) + "=" + encodeURIComponent(e);
				if(i instanceof Date) c += ";expires=" + i.toGMTString();
				if(n) c += ";path=" + n;
				if(s) c += ";domain=" + s;
				if(a) c += ";secure";
				document.cookie = c
			}
		},
		removeCookie: function(t, e, i, n) {
			this.cookie(t, "", new Date(0), e, i, n)
		}
	};

	function _e(t) {
		return function(e) {
			var i = e.relatedTarget;
			while(i != this && i) {
				try {
					i = i.parentNode
				} catch(n) {
					break
				}
			}
			if(i != this) t(e)
		}
	}
	var pe = {
		flash: null,
		load: function() {
			this.flash = he.loadSwf(r, null, null, null)
		},
		play: function(t) {
			if(this.flash) this.flash.playSound(t)
		}
	};

	function ve() {
		if(typeof XMLHttpRequest != "undefined") {
			ve = function() {
				return new XMLHttpRequest
			}
		} else if(typeof ActiveXObject != "undefined") {
			ve = function() {
				if(typeof arguments.callee.activeString != "string") {
					var t = ["MSXML2.XMLHttp.6.0", "MSXML2.XMLHttp.3.0", "MSXML2.XMLHttp"];
					for(var e = 0, i = t.length; e < i; e++) {
						try {
							var n = new ActiveXObject(t[e]);
							arguments.callee.activeString = t[e];
							return n
						} catch(s) {}
					}
				}
				return new ActiveXObject(arguments.callee.activeString)
			}
		} else ve = function() {
			throw new Error("Your browser is too lower,no support Ajax.")
		};
		return ve()
	}
	he.getBrowser();
	he.getTypes();
	var ge = document.createElement("LINK");
	ge.setAttribute("rel", "stylesheet");
	ge.setAttribute("type", "text/css");
	ge.setAttribute("href", E);
	document.getElementsByTagName("HEAD")[0].appendChild(ge);
	he.addEvent(document.body, "click", function(t) {
		t = t.target;
		if(!he.isHide(ie.ui)) {
			var e = false,
				i = false,
				n = false;
			if(t == ie.ui || he.hasChild(ie.ui, t)) i = true;
			if(t == K.ui || he.hasChild(K.ui, t)) e = true;
			if(t == ae.ui || he.hasChild(ae.ui, t)) n = true;
			if(!e && !i && !n) Z.setTalkBoxStatus(N.min)
		}
		var s = he.$("im_status_list"),
			a = he.$("im_state_change");
		if(s && !he.isHide(s)) {
			if(t != s && t != a && !he.hasChild(s, t) && !he.hasChild(a, t)) K.hideStateList()
		}
		if(t != he.$("imQueryInput")) he.hide(he.$("imQueryResult"));
		he.hide(J.selectList);
		if(oe && !he.isHide(oe)) {
			if(/^im_tc_fc_/.test(t.id) || /^im_tc_fcclose/.test(t.id)) return;
			if(t != oe && !he.hasChild(oe, t)) he.hide(oe)
		}
		if(le && !he.isHide(le)) {
			if(/^im_tc_fcchs_/.test(t.id)) return;
			if(t != le && !he.hasChild(le, t)) he.hide(le)
		}
	});
	he.addEvent(window, "online", function(t) {
		if(!U) P.setState(S)
	});
	he.addEvent(window, "offline", function() {
		Z.setMyState(L.offline)
	});
	if(he.browser.name == "msie" && he.browser.version <= 6) {
		var ye = 0;
		he.addEvent(window, "scroll", function(t) {
			ye = ye ? 0 : 1;
			o.style.bottom = ye + "px"
		})
	}
}();