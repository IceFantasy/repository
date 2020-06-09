<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>欢迎来到微聊 proposal</title>
		<%
			pageContext.setAttribute("APP_PATH", request.getContextPath());
		%>
		<link rel="stylesheet" href="${APP_PATH }/static/page/all/wedding.css">
		<link href="/static/all/favicaon.ico" rel="icon" type="image/x-icon">
		<link href="/static/all/favsicon.ico" rel="shortcut icon" type="image/x-icon" media="screen">
		<script src="https://libs.baidu.com/jquery/2.1.4/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			$(function() {
				setTimeout(function() {
					window.location.replace("${APP_PATH }/static/page/index.html");
				}, 80000000);
			});
		</script>
	</head>
	<body style="background-color: #646A8D;">
		<div class="w-t">
			<div class="w-t-m">亲爱的，嫁给我好吗？</div>
			<img src="${APP_PATH }/static/page/all/boom.png" alt="" class="boom1">
			<img src="${APP_PATH }/static/page/all/boom.png" alt="" class="boom2">
			<img src="${APP_PATH }/static/page/all/boom.png" alt="" class="boom3">
		</div>
		<div class="w-p">
			<div class="w-m">
				<img src="${APP_PATH }/static/page/all/man.png" alt="">
			</div>
			<div class="w-w">
				<img src="${APP_PATH }/static/page/all/woman.png" alt="">
			</div>
			<div class="w-f">
				<img src="${APP_PATH }/static/page/all/flower.png" alt="">
			</div>
		</div>
		<div class="w-fls">
			<img src="${APP_PATH }/static/page/all/flowers1.png" alt=""> <img src="${APP_PATH }/static/page/all/flowers2.png"
			 alt=""> <img src="${APP_PATH }/static/page/all/flowers1.png" alt=""> <img src="${APP_PATH }/static/page/all/flowers2.png"
			 alt=""> <img src="${APP_PATH }/static/page/all/flowers1.png" alt=""> <img src="${APP_PATH }/static/page/all/flowers2.png"
			 alt="">
		</div>
		<div class="w-fls w-2">
			<img src="${APP_PATH }/static/page/all/flowers1.png" alt=""> <img src="${APP_PATH }/static/page/all/flowers2.png"
			 alt=""> <img src="${APP_PATH }/static/page/all/flowers1.png" alt=""> <img src="${APP_PATH }/static/page/all/flowers2.png"
			 alt=""> <img src="${APP_PATH }/static/page/all/flowers1.png" alt=""> <img src="${APP_PATH }/static/page/all/flowers2.png"
			 alt="">
		</div>
	</body>
</html>
