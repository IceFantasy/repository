<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:forward page="/cool_login.jsp"></jsp:forward><!-- proposal.jsp -->
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>对小姐姐的问候</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
		<script src="https://libs.baidu.com/jquery/2.1.4/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			$(function() {
				function run() {
					var $runBtn = $("#run");
					setInterval(function() {
						$runBtn.offset({
							top: randomXY(),
							left: randomXY()
						});
					}, 200);
					return false;
				}

				function randomXY() {
					//x上限，y下限     
					var x = 400;
					var y = 80;
					var rand = parseInt(Math.random() * (x - y + 99) + y);
					return rand;
				}

				$('#exampleModal').modal({
					keyboard: true,
					backdrop: "static", //点击空白处不关闭对话框
				});
				$("#notClose").click(function() {
					alert("关不掉我！关不掉我！");
					return false;
				});
				// js 入口
				$("#exit").click(function() {
					alert("来抓我呀！！");
					$(this).attr("id", "run");
					run();
					return false;
				});
			});
		</script>

	</head>
	<body style="background-color: antiquewhite;">
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" style="background-color: cornsilk;">
			<div class="modal-dialog" role="document">
				<div class="modal-content" style="background-color: antiquewhite;">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span id="notClose" aria-hidden="true">&times;</span></button>
						<span class="glyphicon glyphicon-heart-empty" aria-hidden="true"></span>
						<h4 class="modal-title" id="exampleModalLabel">来自一个有诗情画意的小哥哥</h4>
					</div>
					<div class="modal-body" style="width: 31.25rem; height: 31.25rem;">
						<h3 class="modal-title" id="exampleModalLabel">周末，逛公园 可好！</h3>
						<img src="https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3123125406,1008046631&fm=26&gp=0.jpg" class="img-thumbnail">
					</div>
					<div class="modal-footer">
						<button style="margin-right: 18.75rem" type="button" data-dismiss="modal" class="btn btn-success dropdown-toggle">好呀！</button>
						<button type="button" class="btn btn-primary" id="exit">算了吧！</button>
					</div>
				</div>
			</div>
		</div>
		<!-- 欢呼 -->
		<div class="modal fade" id="cheerExampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" style="background-color: cornsilk;">
			<div class="modal-dialog" role="document">
				<div class="modal-content" style="background-color: antiquewhite;">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span id="notClose" aria-hidden="true">&times;</span></button>
						<span class="glyphicon glyphicon-heart-empty" aria-hidden="true"></span>
						<h4 class="modal-title" id="exampleModalLabel">心里别提有多高兴了!</h4>
					</div>
					<img style="width: 25rem; height: 25rem; align-items: center; margin: 1.875rem;" src="https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=745927930,2990518268&fm=26&gp=0.jpg"
					 alt="欢呼" class="img-circle">
				</div>
			</div>
		</div>
		<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
	</body>
	<script type="text/javascript">
		// 欢呼
		$(".dropdown-toggle").click(function() {
			$('#cheerExampleModal').modal({
				keyboard: false
			})
		});
	</script>

</html>
