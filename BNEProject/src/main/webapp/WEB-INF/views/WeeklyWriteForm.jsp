<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style>

</style>

<script>
/* window.onload = function(){
	setInterval(function(){
		$("#udongi").css("height", "500px");	
	}, 1000);
} */
</script>


<div class="content-frame">
	<!-- START CONTENT FRAME TOP -->
	<div class="content-frame-top">
		<div class="page-title">
			<h2>주간 계획</h2>
		</div>
		<div class="pull-right">
			<button class="btn btn-default content-frame-left-toggle">
				<span class="fa fa-bars"></span>
			</button>
		</div>
	</div>
	<!-- END CONTENT FRAME TOP -->

	<!-- START CONTENT FRAME LEFT -->
	<div class="content-frame-left" style="height: 1054px;">
		<div class="panel-body">
			<div class="page-title">
				<h5>개인 정보</h5>
			</div>
			<table class="table">
				<thead>
					<tr>
						<th>소속</th>
						<td>비앤이</td>
					</tr>

					<tr>
						<th>이름</th>
						<td>이동우</td>
					</tr>

					<tr>
						<th>작성일</th>
						<td>2014.05.02.17:50</td>
					</tr>
					<tr>
						<th>제목</th>
						<td>제목을 입력해주세요</td>
					</tr>
				</thead>
			</table>
			<div class="page-title">
				<h5>매출 현황</h5>
			</div>
			<table class="table">
				<thead>
					<tr>
						<th>목표</th>
						<td>100,000,000</td>
					</tr>
					<tr>
						<th>매출액</th>
						<td>100,000,000</td>
					</tr>
				</thead>
			</table>

		</div>
	</div>
	<!-- END CONTENT FRAME LEFT -->

	<!-- START CONTENT FRAME BODY -->
	<div class="content-frame-body padding-bottom-0" style="height: 887px;">

		<div class="row">
			<div class="col-md-12">
				<div id="alert_holder"></div>
				<div class="calendar">
					<div id="calendarWeek" class="fc fc-ltr fc-unthemed">
					</div>
				</div>
			</div>
		</div>

	</div>
	<!-- END CONTENT FRAME BODY -->

</div>