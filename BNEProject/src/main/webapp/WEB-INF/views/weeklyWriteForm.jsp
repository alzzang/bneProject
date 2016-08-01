<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
.panel-body input{
	background-color: transparent;
	border:0px solid black;
}

.salesInput {
	width:100%;
	background-color: transparent;
	border:0px solid black;
	text-align: center;
}
</style>
<script type="text/javascript">
window.onload = function(){

	
	$('bbbb').on('click',function(){
		var s = $(".salesInput");
	});
	
};
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
						<td><input type="text" id= "weeklyReportId" placeholder="제목을 입력해주세요"></td>
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
					<div id="calendar" class="fc fc-ltr fc-unthemed">
					</div>
				</div>
			</div>
		</div>
	<button id='aaaa'> aa</button>
	<button id='bbbb'> bb</button>
	</div>
	<!-- END CONTENT FRAME BODY -->

</div>


<script>
window.onload = function(){
	$('#calendar').fullCalendar('next');
	
	var tbodyTag
	= 	'<tr>' + 
			'<td class="fc-axis">매출</td>' +
			'<td><input type="text" class="salesInput" id="sales-mon"></td>' +
			'<td><input type="text" class="salesInput" id="sales-tue"></td>' +
			'<td><input type="text" class="salesInput" id="sales-wed"></td>' +
			'<td><input type="text" class="salesInput" id="sales-thu"></td>' +
			'<td><input type="text" class="salesInput" id="sales-fri"></td>' +
		'</tr>';
		
	$('#weeklyTableHeader>tbody').html(tbodyTag);
	
	var s = $('#weeklyTableHeader>thead>tr>th');
	for(var i=2; i<7; i++){
		$('#weeklyTableHeader>tbody>tr>td:nth-child('+i+')>input').attr('reg_date', s[i-1].dataset.date);
	} 
	var date = $('#calendar').fullCalendar('getDate');
	
	
$('#aaaa').on('click',function(){
	var weeklyNumberText = $('.fc-week-number>span')[0].textContent;
	var weeklyNumber = weeklyNumberText[1]+weeklyNumberText[2];
	
	var date = $('#calendar').fullCalendar('getDate');
	var year = date._d.getFullYear();
	
	
	
	var report_id = year+"_"+weeklyNumber+"_"+${user.employee_id};
	var report_title = $('#weeklyReportId')[0].value;
	if(report_title == ""){
		report_title = weeklyNumber+"주의 계획";
	}
	
	var employee_id = "${user.employee_id}";
	var department_id = "${user.department_id}";
	var salesGoal = 0;/* ${salesGoal}; */
	var montlySales = 0;/* ${monthlySales}; */
	
	var dayOfWeek = ['mon','tue','wed','thu','fri'];
	var sales = [];
	var regdate = [];
	for(var i= 0; i<5; i++){

		sale = ($('#sales-'+dayOfWeek[i])[0].value);
		regdate = ($('#sales-'+dayOfWeek[i])[0].attributes[3].textContent);
		if(sales[i] == "")
			sales[i] = 0;
		
		var plan = {
					sales : sale,
					reg_date : regdate
		};
		sales.push(plan);
	}
	
	var weeklyReport = {
			weekly_report_id : report_id,
			title : report_title,
			saleGoal : salesGoal,
			sales : montlySales,
			employee_id : employee_id,
			department_id : department_id
	}
	
	
	
	var s = $('#calendar').fullCalendar('clientEvents');
	var allPlan = [];
	for(var i = 0; i<s.length; i++){
		var plan;
		
		var title = s[i].title;
		
		var startTimeOrigin = s[i].start.format().split('T');
		var startTime = startTimeOrigin[0]+" "+startTimeOrigin[1];
		var endTimeOrigin = s[i].end.format().split('T');
		var endTime = endTimeOrigin[0]+" "+endTimeOrigin[1];
		plan={
				content:title,
				start_time:startTime,
				end_time:endTime
		}
		
		allPlan.push(plan);
	}

	var jPlan = JSON.stringify(allPlan);
	var jPlan2 = JSON.stringify(weeklyReport);
	var jPlan3 = JSON.stringify(sales);
	$('.fc-row .fc-widget-header');
	
	$.ajax({
		type : "POST",
		url : "/weeklyReport/write",
		data : {
			sales : jPlan3,
			report : jPlan2,
			weeklyPlan : jPlan
		},

		success : function(){
			//alert("성공~");
		},
		error : function(){
			alert("실패~");
		}
	})
	
});
}

</script>