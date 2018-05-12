/**
 * @Description:注册
 * @author xiadecheng
 * @date 2017年10月26日 上午11:10:59
 */
var registration = {
	/**
	 * 参数
	 */
	options : {
		saveRegistrationUrl : basePath + "/registrationAction!saveRegistrationInfo.dhtml",//保存注册信息
		queryAllCountryUrl : basePath + '/registrationAction!queryAllCountry.dhtml',//所有的国家
		checkUsernameUrl : basePath + '/registrationAction!checkUsername.dhtml',//所有的国家
		checkUserCompanyUrl : basePath + '/registrationAction!checkUserCompany.dhtml',//校验公司名称
	},
	/**
	 * 初始化
	 */
	init : function() {
		this.controller.init();
		this.view.init();
	},
	/**
	 * 控制层
	 */
	controller : {
		init : function() {
			this.mouseClickEvent();
		},
		/*
		 * 校验username是否重复，在register.js中调用
		 */
		systermCheckUser : function(){
			var flag;
			$.ajax({
				type : "POST",
				url : registration.options.checkUsernameUrl,
				data : {username : $("#adminNo").val()},
				dataType : "JSON",
				async : false,
				success : function(backdata){
					if(backdata == '0'){
	 					flag = true;
	 				}else{
	 					flag = false;
	 				}
				}
			});
			return flag;
		},
		/*
		 * 校验公司名称是否重复，在register.js中调用
		 */
		checkUserCompany : function(){
			var ccflag;
			$.ajax({
				type : "POST",
				url : registration.options.checkUserCompanyUrl,
				data : {company : $("#company").val()},
				dataType : "JSON",
				async : false,
				success : function(backdata){
					if(backdata == '0'){
						ccflag = true;
	 				}else{
	 					ccflag = false;
	 				}
				}
			});
			return ccflag;
		},
		mouseClickEvent : function() {
			/*
			 * 点击注册按钮
			 */
			$(document).on("click","#btn12",function(){
				if(!verifyCheck._click()) return;
			
				var username = $("#adminNo").val();//Username
				var password = $("#password").val();//Password
				var confirmPassword = $("#rePassword").val();//Confirm Password
				var companyName = $("#company").val();//Company Name
				var country = $("#country").val();//Country / Region
				var contactPerson = $("#contact").val();//Contact Person
				var email = $("#email").val();//Email
				var phonenum = $("#phonenum").val(); //phonenum
				var companytype = $("#companytype").val(); //companytype
				
				var user = [{
						username : username,
						password : password,
						confirmpassword : confirmPassword,
						companyname : companyName,
						country : country,
						contactperson : contactPerson,
						email : email,
						phonenum : phonenum,
						companytype : companytype
				}];
				var userStr = JSON.stringify(user);
				$.post(registration.options.saveRegistrationUrl, {json:userStr}, function(rsp) {
	 				if(rsp.success){
	 			
	 					$('#fly-full').hide();// 注冊表單關閉
	 					$('#success').show();//成功层弹出
	 					$('#failure').hide();//失败层隐藏
	 					
	 					
	 					
	 				}else{
	 					$('#fly-full').hide();// 注冊表單關閉
	 					$('#success').hide();//成功层隐藏
	 					$('#failure').show();//失败层弹出
	 					
	 				}
	 				
	 				$('#all').addClass('modal');
	 				
	 			}, "JSON").error(function() {
	 				$('#failure').hide();//失败层弹出
	 				$('#all').addClass('modal');
	 				
	 			});

			});
			

			
		}
		
		
		
	},
	/**
	 * 视图层
	 */
	view : {
		init : function() {	
			this.initConditions();
			this.initHidden();
		},
		initConditions : function(){
			$.post(registration.options.queryAllCountryUrl, null, function(backdata) {
 				$.each(backdata,function(key,value){
 					$("#country").append("<option value='"+ value.countryid +"'>" + value.countryenname + "</option>");
 					
 				});
 				$("#country").searchableSelect();
 			}, "JSON").error(function() {
 					//$.messager.alert("Info", "保存失败");
 			});
		},
		initHidden: function(){
			//$("#showEmail").hide();
			$("#showTel").hide();
		}
		
	}
};
$(function() {
	registration.init();
});