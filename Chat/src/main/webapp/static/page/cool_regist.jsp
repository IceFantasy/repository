<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" class="cye-disabled cye-lm">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		
		<title>欢迎注册</title>
		
		<%
			pageContext.setAttribute("APP_PATH", request.getContextPath());
		%>

		<link href="${APP_PATH }/static/page/all/default.css" rel="stylesheet" type="text/css">
		<link href="${APP_PATH }/static/page/all/styles.css" rel="stylesheet" type="text/css">
		<link href="${APP_PATH }/static/page/all/demo.css" rel="stylesheet" type="text/css">
		<link href="${APP_PATH }/static/page/all/loaders.css" rel="stylesheet" type="text/css">
		<link id="layuicss-skinlayercss" rel="stylesheet" href="${APP_PATH }/static/page/all/layer.css" media="all">

		<link id="networkDiskToolsIconfontLink" rel="stylesheet" type="text/css" href="${APP_PATH }/static/page/all/font_1195811_wvahqlnfkan.css">
		
		
	</head>
	<body style="background-image: url(${APP_PATH }/static/page/all/Starry.jpg);"><canvas class="pg-canvas" width="1366"
		 height="614"></canvas>
		<div class="login" style="width: 380px;">
			<div class="login_title">
				<span>人海茫茫，遇到你是我的缘分，欢迎注册</span>
			</div>
			<div class="login_fields">
				<form action="${APP_PATH }/chatUser" method="post">
					<div class="login_fields__user">
						<div class="icon">
							<img alt="" src="${APP_PATH }/static/page/all/user_icon_copy.png">
						</div>
						<input id="empName_add_input" name="nickName" placeholder="昵称" maxlength="16" type="text" autocomplete="off" value=""><!-- kbcxy -->
						<div class="validation">
							<img alt="" src="${APP_PATH }/static/page/all/tick.png">
						</div>
					</div>
					<div class="login_fields__user">
						<div class="icon">
							<img alt="" src="${APP_PATH }/static/page/all/user_icon_copy.png">
						</div>
						<input id="email_add_input" name="emailAccount" placeholder="邮箱" maxlength="30" type="text" autocomplete="off" value=""><!-- kbcxy -->
						<font id='sendemailcode' size="0.5" color="red">发送验证码</font>
						<div class="validation">
							<img alt="" src="${APP_PATH }/static/page/all/tick.png">
						</div>
					</div>
					<div class="login_fields__password">
						<div class="icon">
							<img alt="" src="${APP_PATH }/static/page/all/lock_icon_copy.png">
						</div>
						<input name="emailCode" placeholder="邮箱验证码" maxlength="6" type="text" autocomplete="off">
						<font id='checkmailcode' size="0.5" color="red"></font>
						<div class="validation">
							<img alt="" src="${APP_PATH }/static/page/all/tick.png">
						</div>
					</div>
					<div class="login_fields__password">
						<div class="icon">
							<img alt="" src="${APP_PATH }/static/page/all/lock_icon_copy.png">
						</div>
						<input name="password" placeholder="密码" maxlength="16" type="password" autocomplete="off">
						<div class="validation">
							<img alt="" src="${APP_PATH }/static/page/all/tick.png">
						</div>
					</div>
					<div class="login_fields__password">
						<div class="icon">
							<img alt="" src="${APP_PATH }/static/page/all/key.png">
						</div>
						<input name="code" placeholder="验证码" maxlength="4" type="text" autocomplete="off">
						<div class="validation" style="opacity: 1; right: -5px;top: -3px;">
							<canvas class="J_codeimg" id="myCanvas" onclick="Code();">对不起，您的浏览器不支持canvas，请下载最新版浏览器!</canvas>
						</div>
					</div>
					<div class="login_fields__submit">
						<input type="hidden" name="temp" id="" value="我是隐藏的元素" alt="0"/>
						<input type="submit" value="注册"><font id="msg" size="1" color="chartreuse"></font>
					</div>
				</form>
			</div>
			<div class="success">
			</div>
			<div class="disclaimer">
				<p> </p>
			</div>
		</div>
		<div class="authent">
			<div class="loader" style="height: 44px;width: 44px;margin-left: 28px;">
				<div class="loader-inner ball-clip-rotate-multiple">
					<div></div>
					<div></div>
					<div></div>
				</div>
			</div>
			<p>认证中...</p>
		</div>
		<div class="OverWindows"></div>
		<link href="${APP_PATH }/static/page/all/layui.css" rel="stylesheet" type="text/css">
		<script src="${APP_PATH }/static/page/all/jquery-1.10.2.js"></script>
		<script src="${APP_PATH }/static/page/all/jquery.min.js"></script>
		<script type="text/javascript" src="${APP_PATH }/static/page/all/jquery-ui.min.js"></script>
		<script type="text/javascript" src="${APP_PATH }/static/page/all/stopExecutionOnTimeout.js"></script>
		<script src="${APP_PATH }/static/page/all/layui.js" type="text/javascript"></script>
		<script src="${APP_PATH }/static/page/all/Particleground.js" type="text/javascript"></script>
		<script src="${APP_PATH }/static/page/all/Treatment.js" type="text/javascript"></script>
		<script src="${APP_PATH }/static/page/all/jquery.mockjax.js" type="text/javascript"></script>
		<!-- 数据验证 -->
		<script type="text/javascript">
			var canGetCookie = 0; //是否支持存储Cookie 0 不支持 1 支持
			var ajaxmockjax = 1; //是否启用虚拟Ajax的请求响 0 不启用  1 启用
			//默认账号密码

			var truelogin = ""; // kbcxy
			var truepwd = "mcwjs";

			var CodeVal = 0;
			Code();
			
			function Code() {
				if (canGetCookie == 1) {
					createCode("AdminCode");
					var AdminCode = getCookieValue("AdminCode");
					showCheck(AdminCode);
				} else {
					showCheck(createCode(""));
				}
			}

			function showCheck(a) {
				CodeVal = a;
				var c = document.getElementById("myCanvas");
				var ctx = c.getContext("2d");
				ctx.clearRect(0, 0, 1000, 1000);
				ctx.font = "80px 'Hiragino Sans GB'";
				ctx.fillStyle = "#E8DFE8";
				ctx.fillText(a, 0, 100);
			}
			$(document).keypress(function(e) {
				// 回车键事件  
				if (e.which == 13) {
					$('input[type="button"]').click();
				}
			});
			//粒子背景特效
			$('body').particleground({
				dotColor: '#E8DFE8',
				lineColor: '#133b88'
			});
			$('input[name="pwd"]').focus(function() {
				$(this).attr('type', 'password');
			});
			$('input[type="text"]').focus(function() {
				$(this).prev().animate({
					'opacity': '1'
				}, 200);
			});
			$('input[type="text"],input[type="password"]').blur(function() {
				$(this).prev().animate({
					'opacity': '.5'
				}, 200);
			});
			$('input[name="login"],input[name="pwd"]').keyup(function() {
				var Len = $(this).val().length;
				if (!$(this).val() == '' && Len >= 5) {
					$(this).next().animate({
						'opacity': '1',
						'right': '30'
					}, 200);
				} else {
					$(this).next().animate({
						'opacity': '0',
						'right': '20'
					}, 200);
				}
			});
			var open = 0;
			layui.use('layer', function() {
				var msgalert = '默认账号:' + truelogin + '<br/> 默认密码:' + truepwd;
				var index = layer.alert(msgalert, {
					icon: 6,
					time: 4000,
					offset: 't',
					closeBtn: 0,
					title: '友情提示',
					btn: [],
					anim: 2,
					shade: 0
				});
				layer.style(index, {
					color: '#777'
				});
				//非空验证
				$('input[type="button"]').click(function() {
					var login = $('input[name="login"]').val();
					var pwd = $('input[name="pwd"]').val();
					var code = $('input[name="code"]').val();
					if (login == '') {
						ErroAlert('请输入您的账号');
					} else if (pwd == '') {
						ErroAlert('请输入密码');
					} else if (code == '' || code.length != 4) {
						ErroAlert('输入验证码');
					} else {
						//认证中..
						fullscreen();
						$('.login').addClass('test'); //倾斜特效
						setTimeout(function() {
							$('.login').addClass('testtwo'); //平移特效
						}, 300);
						setTimeout(function() {
							$('.authent').show().animate({
								right: -320
							}, {
								easing: 'easeOutQuint',
								duration: 600,
								queue: false
							});
							$('.authent').animate({
								opacity: 1
							}, {
								duration: 200,
								queue: false
							}).addClass('visible');
						}, 500);

						//登陆
						var JsonData = {
							login: login,
							pwd: pwd,
							code: code
						};
						//此处做为ajax内部判断
						var url = "";
						if (JsonData.login == truelogin && JsonData.pwd == truepwd && JsonData.code.toUpperCase() == CodeVal.toUpperCase()) {
							url = "Ajax/Login";
						} else {
							url = "Ajax/LoginFalse";
						}


						AjaxPost(url, JsonData,
							function() {
								//ajax加载中
							},
							function(data) {
								//ajax返回 
								//认证完成
								setTimeout(function() {
									$('.authent').show().animate({
										right: 90
									}, {
										easing: 'easeOutQuint',
										duration: 600,
										queue: false
									});
									$('.authent').animate({
										opacity: 0
									}, {
										duration: 200,
										queue: false
									}).addClass('visible');
									$('.login').removeClass('testtwo'); //平移特效
								}, 2000);
								setTimeout(function() {
									$('.authent').hide();
									$('.login').removeClass('test');
									if (data.Status == 'ok') {
										//登录成功
										$('.login div').fadeOut(100);
										$('.success').fadeIn(1000);
										$('.success').html(data.Text);
										//跳转操作

									} else {
										AjaxErro(data);
									}
								}, 2400);
							})
					}
				})
			})
			var fullscreen = function() {
				elem = document.body;
				if (elem.webkitRequestFullScreen) {
					elem.webkitRequestFullScreen();
				} else if (elem.mozRequestFullScreen) {
					elem.mozRequestFullScreen();
				} else if (elem.requestFullScreen) {
					elem.requestFullscreen();
				} else {
					//浏览器不支持全屏API或已被禁用  
				}
			}
			if (ajaxmockjax == 1) {
				$.mockjax({
					url: 'Ajax/Login',
					status: 200,
					responseTime: 50,
					responseText: {
						"Status": "ok",
						"Text": "登陆成功<br /><br />欢迎回来"
					}
				});
				$.mockjax({
					url: 'Ajax/LoginFalse',
					status: 200,
					responseTime: 50,
					responseText: {
						"Status": "Erro",
						"Erro": "账号名或密码或验证码有误"
					}
				});
			}
		</script>
		<div class="layui-layer-move"></div>
		<div style="z-index: 10000;position: relative;" id="networkDiskToolsOtherLinkMenu">
			<div class="sc-AykKE jiZwhX"></div>
		</div>
	</body>
	<div id="cyeBlackMaskLayer" style="background-color: rgb(19, 19, 19); position: fixed; width: 1980px; height: 1080px; z-index: -2147483648;"></div>
	<div id="cye-workaround-body" style="position: absolute; left: 0px; top: 0px; z-index: -2147483646; background: none 0% 0% / 100% no-repeat scroll padding-box border-box rgb(36, 38, 69); height: 663px; width: 1366px;"></div>
	<div id="cye-workaround-body-image" style="position: absolute; left: 0px; top: 0px; z-index: -2147483645; background: url(&quot;http://www.jq22.com/demo/jQueryLogin201708272212/img/Starry.jpg&quot;) 0% 0% / 100% no-repeat scroll padding-box border-box rgba(0, 0, 0, 0); height: 663px; width: 1366px;"></div>
	<script type="text/javascript">
		var $check = $('[type="hidden"]');
		//校验用户名是否可用
		$("#empName_add_input").change(function() {
			//发送ajax请求校验用户名是否可用
			$("#empName_add_input + a").remove();
			var empName = this.value;
			var $nickName = $(this);
			$.ajax({
				url: "${APP_PATH}/checkChatUser",
				data: "empName=" + empName,
				type: "POST",
				success: function(result) {
					if (result.code == 100) {
						$nickName.after("<a style='color: cornsilk;'>用户名可用！<a/>");
						$check.attr("alt", 1);
					} else {
						$nickName.after("<a style='color: cornsilk;'>用户名不可用！<a/>");
						$check.attr("alt", 0);
					}
				}
			});
		});
		//校验邮箱是否可用
		$("#email_add_input").change(function() {
			//发送ajax请求校验邮箱是否可用
			var $sendcode = $("#sendemailcode");
			$sendcode.empty();
			var email = this.value;
			var $nickName = $(this);
			$.ajax({
				url: "${APP_PATH}/checkemail",
				data: "email=" + email,
				type: "POST",
				success: function(result) {
					if (result.code == 100) {
						$sendcode.append("发送验证码");
						$check.attr("alt", 1);
					} else {
						$sendcode.append("邮箱不可用！");
						$check.attr("alt", 0);
					}
				}
			});
		});
		
		// 点击发送邮箱验证码然后发送验证码  phantom@qq.com
		$("#sendemailcode").click(function () {
			var $sendcode = $("#sendemailcode");
			$sendcode.empty();
			var email = $("#email_add_input").val();
			$.ajax({
				url: "${APP_PATH}/sendemailcode",
				data: "email=" + email,
				type: "POST",
				success: function(result) {
					if (result.code == 100) {
						$sendcode.append("验证码发送成功！");
						$check.attr("alt", 1);
					} else {
						$sendcode.append(result.extend.va_msg);
						$check.attr("alt", 0);
					}
				}
			});
		});
		// 查看是否验证成功 emailCode
		$("[name='emailCode']").change(function () {
			// 1. 获取用户输入的验证码
			var $font = $("#checkmailcode");
			$font.empty();// 清空验证信息
			var checkcode = $(this).val();
			// 2. ajax请求是否成功
			$.ajax({
				url: "${APP_PATH}/checkmailcode/" + checkcode,
				type: "GET",
				success: function(result) {
					if (result.code == 100) {
						$font.append("验证成功！");
						$check.attr("alt", 1);
					} else {
						$font.append(result.extend.va_msg);
						$check.attr("alt", 0);
					}
				}
			});
		});
		$('[type="submit"]').click(function () {
			var $msg = $("#msg");
			$msg.empty();
			var check = $check.attr("alt");// 0: 失败 1: 成功
			if (check == 0) {
				$msg.append("请检查提交内容！");
				return false;
			}
		})
		
	</script>
</html>
