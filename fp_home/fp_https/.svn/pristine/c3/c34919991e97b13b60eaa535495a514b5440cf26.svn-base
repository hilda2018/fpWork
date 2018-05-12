<%@ page contentType="text/html; charset=utf-8" language="java" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

	<head>
		<%-- <base href="<%=basePath%>"> --%>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title>freshport</title>
		<link rel="shortcut icon" href="${applicationScope.sysConfigs['sysPath']}/pages/registration/images/favicon.ico">
		<link href="${applicationScope.sysConfigs['sysPath']}/pages/registration/css/register.css" rel="stylesheet" type="text/css" media="all">

		<script type="text/javascript">
			var basePath = "${applicationScope.sysConfigs['sysPath']}";
		</script>

	</head>

	<body class="all" id="all">
		<div class="fly-full" id="fly-full">

			<div class="wrapWhite wrapHeader">
				<div class="main">
					<div class="header" style="height:60px;">
						<a class="navg" href="${applicationScope.sysConfigs['frontPath']}">
							<span class="solgan">
						Online Information & Service Platform for Imported Fruits Industry
					</span>
						</a>

						<a class="btn2" href="${applicationScope.sysConfigs['frontPath']}">Return to homepage!</a>
					</div>
				</div>
			</div>
			<!-- 搜索-->

			<div class="wrapWhite wrapLogo">
				<div class="main">

					<div class="header2" style="margin-bottom: 0px;text-align: center;">
						<h2>Exporter Sign Up</h2>
						<p>Required fields are indicated by a red asterisk <span style="color:red;">*</span></p>
					</div>

				</div>
			</div>

			<div class="wrapWhite" style="height: 600px;">
				<div class="main" style="width:1080px;height: 600px;">

					<div class="login-box f-mt10 f-pb50" id="registerAccount">
						<div class="bgf">
							<div class="reg-box-pan display-inline">

								<div class="step">
									<ul>
										<li class="on">
											<span class="num"><em class="f-r5"></em><i>1</i></span>
											<span class="line_bg lbg-r"></span>

										</li>

										<li>
											<span class="num"><em class="f-r5"></em><i>2</i></span>
											<span class="line_bg lbg-r"></span>

										</li>

										<li>
											<span class="num"><em class="f-r5"></em><i>3</i></span>
											<span class="line_bg lbg-l"></span>

										</li>
									</ul>

								</div>
								<div class="tit">
									<p class="on">Create your account</p>
									<p style="float: left;">Set password</p>
									<p style="float: left;">Finish</p>
								</div>

								<div class="reg-box" id="verifyCheck" style="margin-top:30px;position: relative;min-height: 400px;">
									<div class="part1">

										<div class="item col12">
											<span class="intelligent-label f-fl"><b class="ftx04">*</b>Username:</span>
											<div class="f-fl item-ifo"><input id="adminNo" type="text" maxlength="25" placeholder="Create your username" class="txt03 f-r3 " tabindex="1" data-valid="isNonEmpty||between:5-25||isUname||isCheckUser" data-error="Please enter your username||between:5-25||5-25 characters, including letters/numbers, no space||repeat of user name" />
												<span class="ie8 icon-close close hide">×</span>

												<label class="icon-sucessfill blank hide"> ✔</label>
												<label class="focus">5-25 characters, including letters/numbers, no space</label>
												<label class="focus valid"></label>

											</div>

										</div>

										<div class="item col12 itempassword">
											<span class="intelligent-label f-fl"><b class="ftx04">*</b>Password:</span>
											<div class="f-fl item-ifo" style=" position: relative;">
												<input type="password" id="password" maxlength="20" class="txt03 f-r3 " tabindex="2" style="ime-mode:disabled;" onpaste="return  false" autocomplete="off" data-valid="isNonEmpty||between:6-20||isPassword" data-error="Please enter your password||between:6-20||6-20 characters, including letters/numbers, no space" />
												<span class="ie8 icon-close close hide">×</span>
												<span class="showpwd" data-eye="password"></span>

												<label class="icon-sucessfill blank hide">✔</label>
												<label class="focus"><span>6-20 characters, including letters/numbers, no space</span></label>

												<label class="focus valid"></label>
												<span class="clearfix"></span>
											</div>

										</div>

										<div class="item col12 itempassword">
											<span class="intelligent-label f-fl"><b class="ftx04">*</b>Confirm Password:</span>
											<div class="f-fl item-ifo" style=" position: relative;">
												<input type="password" maxlength="20" class="txt03 f-r3 " tabindex="3" style="ime-mode:disabled;" onpaste="return  false" autocomplete="off" data-valid="isNonEmpty||between:6-20||isRepeat:password" data-error="Please re-enter your password||between:6-20||Your password and confirmation password do not match" id="rePassword" />
												<span class="ie8 icon-close close hide">×</span>
												<span class="showpwd" data-eye="rePassword"></span>

												<label class="icon-sucessfill blank hide">✔</label>
												<label class="focus"></label>
												<label class="focus valid"></label>

											</div>
										</div>

										<div class="item col12">
											<span class="intelligent-label f-fl"><b class="ftx04">*</b>Company Name:</span>
											<div class="f-fl item-ifo">
												<input type="text" class="txt03 f-r3" tabindex="4" data-valid="isNonEmpty" data-error="Please enter your company Name" placeholder="Enter your company full name" id="company" />

												<span class="ie8 icon-close close hide">×</span>

												<label class="icon-sucessfill blank hide">✔</label>
												<label class="focus"></label>

												<label class="focus valid"></label>

											</div>

										</div>

										<div class="item col12" style="position: relative;z-index: 9999;">
											<span class="intelligent-label f-fl"><b class="ftx04">*</b>Country / Region:</span>
											<div class="f-fl item-ifo">

												<select class="txt03 f-r3 f-fl " tabindex="5" data-valid="isNonEmpty" data-error="Pleaselect country / region " id="country">

												</select>

												<span class="ie8 icon-close close hide">×</span>
												<label class="icon-sucessfill blank hide">✔</label>
												<label class="focus"></label>
												<label class="focus valid"></label>
											</div>

										</div>
										
										
										<div class="item col12" style="position: relative;z-index: 9998;">
											<span class="intelligent-label f-fl"><b class="ftx04">*</b>Company Type:</span>
											<div class="f-fl item-ifo">

												<select class="txt03 f-r3 f-fl " tabindex="6" id="companytype" data-valid="isNonEmpty" data-error="Please select companytype " >
													<option value='0' >SUPPLIER</option>
													<option value='1' >BUYER</option>
												</select>

												<label class="icon-sucessfill blank">✔</label>
												<label class="focus"></label>
												<label class="focus valid"></label>
											</div>

										</div>
										
										
										
	<!--           单选    Contact  -->
	<div class="item col12 colStatic" id="showEmail" >
		<span class="intelligent-label f-fl"><b class="ftx04">*</b>Email:</span>
		<div class="f-fl item-ifo">
			<input type="text" class="txt03 f-r3 f-fl" tabindex="7" id="email" name="email" />
			<span class="ie8 icon-close close hide">×</span>
			<label class="icon-sucessfill blank hide">✔</label>
			<label class="focus" id="emailMsg" style="color: red;"></label>
			<label class="focus valid"></label>
		</div>
	</div>
	
	<!--           单选    Contact  -->
	<div class="item col12 colStatic" id="showTel" >
		<span class="intelligent-label f-fl"><b class="ftx04">*</b>TEL:</span>
		<div class="f-fl item-ifo">
			<input type="text" class="txt03 f-r3 f-fl" tabindex="7" id="phonenum" name="phonenum" />
			<span class="ie8 icon-close close hide">×</span>
			<label class="icon-sucessfill blank hide">✔</label>
			<label class="focus" id="telMsg" style="color: red;"></label>
			<label class="focus valid"></label>
		</div>
	</div>
										<!--              非必选    Contact Person-->
										<div class="item col12 colStatic">
											<span class="intelligent-label f-fl"><b class="ftx04">&nbsp;</b>Contact Person:</span>
											<div class="f-fl item-ifo">
												<input type="text" class="txt03 f-r3 f-fl" tabindex="8" id="contact" name="contact" />

												<span class="ie8 icon-close close hide">×</span>
												<label class="icon-sucessfill blank hide">✔</label>
												<label class="focus" id="contactMsg" style="color:red;"> </label>
												<label class="focus valid"></label>
											</div>

										</div>

										<a class="flatbtn loginClick" id="btn_part1"> Create Account </a>
										<a class="flatbtn loginClick green" style="display:none" href="#success" id="btn12"> Create Account </a>

										<a class="flatbtn loginClick green" style="display:none"> Create Account </a>

									</div>

								</div>
							</div>

						</div>

					</div>

				</div>

			</div>

			<!--footer start here-->
			<div id="footeCompany" class="wrapWhite">

				<div class="main footer" style="z-index:1">
					<p style="height: 60px;line-height: 32px;font-size:14px;color:#999;">备案/许可证号 沪ICP备15035308 &nbsp;&nbsp;|&nbsp;&nbsp;<span> 2015 Freshport.com. All rights reserved </span></p>
				</div>

			</div>

		</div>

		<div id="success" class="modal" style="display: none;">

			<h1>Your registration<br/> is successful</h1>

			<p>
				<a id="okbtn" class="flatbtn hidemodal login login2" href="${applicationScope.sysConfigs['frontPath']}" tabindex="1">OK</a>
				<a id="loginbtn" class="flatbtn hidemodal  login" href="${applicationScope.sysConfigs['frontPath']}/fp-online-infoshow/member/login" tabindex="2"> LOGIN</a>
			</p>

		</div>

		<div id="failure" class="modal" style="display: none;">

			<h1>Your registration<br/> is unsuccessful</h1>

			<p>
				<a href="${applicationScope.sysConfigs['frontPath']}" class="flatbtn hidemodal login login2 login3" id="close"> CLOSE </a>
			</p>

		</div>

		<script src="${applicationScope.sysConfigs['sysPath']}/pages/registration/js/jquery.min.js"></script>
		<script src="${applicationScope.sysConfigs['sysPath']}/pages/registration/registration.js"></script>
		<script src="${applicationScope.sysConfigs['sysPath']}/pages/registration/js/register.js" type="text/javascript"></script>

	</body>

</html>