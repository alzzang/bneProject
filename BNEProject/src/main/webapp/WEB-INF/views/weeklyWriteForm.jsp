<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
.panel-body input{
	background-color: transparent;
	border:0px solid black;
}
</style>
<%-- <table border="1">
	<tr>
	<c:forEach items="${dayList}" var="day">
		<th>${day.key}</th>
	</c:forEach>
	</tr>
	<tr>
	<c:forEach items="${dayList}" var="day">
		<td>${day.value}</td>
	</c:forEach>
	</tr>
</table>

<script lang="javascript">
window.onload = function(){
	<c:forEach items="${dayList}" var="day">
		$("th[class='fc-day-header fc-widget-header fc-${day.key}']").text("${day.value}");	
	</c:forEach>
};
</script> --%>

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
			부서 ID : <input type="text" name="department_id" value="${user.department_id}" disabled><br>
			로그인 ID : <input type="text" name="employee_id" value="${user.employee_id}" disabled>
			
			<table class="table">
				<thead>
					<tr>
						<th>소속</th>
						<td><input type="text" value="${user.department_name}" disabled></td>
					</tr>

					<tr>
						<th>이름</th>
						<td><input type="text" value="${user.employee_name}" disabled></td>
					</tr>

					<tr>
						<th>작성일</th>
						<td>${currentDate}</td>
					</tr>
					<tr>
						<th>제목</th>
						<td><input type="text"  placeholder="제목을 입력해주세요"></td>
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
						<td><input type="text" name="salesGoal" value="${salesGoal}" disabled></td>
					</tr>
					<tr>
						<th>매출액</th>
						<td><input type="text" name="sales" value="${monthlySales}" disabled></td>
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
	<button id='aaaa' > aa</button>
	</div>
	<!-- END CONTENT FRAME BODY -->

</div>