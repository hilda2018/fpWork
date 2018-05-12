<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@ include file="/base/include/basePage.jsp"%>
<html>
	<head>
		<%@ include file="/base/include/bootstrap.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>${sysName}</title>
		<script>
			$(function() {
				var time = 1000;
				var interval = 10; //调度器对象。
				var timer;
				timer = setInterval(function() {
					$('#msg').text(interval);
					interval = interval - 1;
					if (interval <= 0) {
						clearTimeout(timer);
						location.href = $('#index').attr('href');
					}
				}, time);
			})
		</script>
	</head>
	<body>

			<!--内容后续修改-->
			404
	</body>
</html>
