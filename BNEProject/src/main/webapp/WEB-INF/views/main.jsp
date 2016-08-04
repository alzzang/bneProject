<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page-content-wrap">
	<div class="row">
		<div class="col-md-6">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">주간계획</h3>
				</div>
				<div class="panel-body">
					<div id="calanderDetail" class="calendar" >
						<div id="calendar" class="fc fc-ltr fc-unthemed" style="height: 430px;">
						</div>
					</div>
				</div>
			</div>

			<!-- END LINE CHART -->

		</div>
		<div class="col-md-6">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">결제/미결제</h3>
				</div>
				<div class="panel-body"></div>
			</div>
			<!-- START BAR CHART -->

			<!-- END Area CHART -->

		</div>

	</div>
	<div class="row">
		<div class="col-md-4">

			<!-- START BAR CHART -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">팀원별 실적</h3>
				</div>
				<div class="panel-body">
					<div id="morris-bar-example1" style="height: 300px;"></div>

				</div>
			</div>
			<!-- END LINE CHART -->

		</div>
		<div class="col-md-4">

			<!-- START BAR CHART -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">매출</h3>
				</div>
				<div class="panel-body">
					<div id="morris-line-example" style="height: 300px;"></div>
				</div>
			</div>
			<!-- END Area CHART -->

		</div>
		<div class="col-md-4">

			<!-- START DONUT CHART -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">달성률</h3>
				</div>
				<div class="panel-body">
					<div id="morris-donut-example" style="height: 300px;"></div>
				</div>
			</div>
			<!-- END DONUT CHART -->

		</div>
	</div>
	
	
</div>
<script type="text/javascript" src="/js/plugins/jquery/jquery.min.js"></script>
<script type="text/javascript" src="/js/plugins/jquery/jquery-ui.min.js"></script>
<script type="text/javascript" src="/js/plugins/morris/raphael-min.js"></script>
<script type="text/javascript" src="/js/plugins/morris/morris.min.js"></script>
<script type="text/javascript" src="/js/graphData.js"></script>
<script type="text/javascript">mainpageMorrisCharts();</script>
<!-- <script type="text/javascript" src="/js/mainWeekly.js"></script> -->
<style>
.salesInput {
	width:100%;
	background-color: transparent;
	border:0px solid black;
	text-align: center;
}
</style>
<script>
var makeSalesInput = function(){
	var salesCell
	= 	'<tr>' + 
			'<td class="fc-axis">매출</td>' +
			'<td><input type="text" class="salesInput" id="sales-mon"></td>' +
			'<td><input type="text" class="salesInput" id="sales-tue"></td>' +
			'<td><input type="text" class="salesInput" id="sales-wed"></td>' +
			'<td><input type="text" class="salesInput" id="sales-thu"></td>' +
			'<td><input type="text" class="salesInput" id="sales-fri"></td>' +
		'</tr>';
		
	$('#weeklyTableHeader>tbody').html(salesCell);
	
	var s = $('#weeklyTableHeader>thead>tr>th');
	
	for(var i=2; i<7; i++){
		$('#weeklyTableHeader>tbody>tr>td:nth-child('+i+')>input').attr('reg_date', s[i-1].dataset.date);
	}	
};


var inputReportData = function(reportData) {

	var weeklyPlanDTOList = reportData.weeklyPlanDTOList;
	var planDetailDTOList = reportData.planDetailDTOList;

	for (var i = 0; i < planDetailDTOList.length; i++) {
		$('#calendar').fullCalendar('renderEvent', {
			"title" : planDetailDTOList[i].content,
			"allDay" : false,
			"start" : planDetailDTOList[i].start_time,
			"end" : planDetailDTOList[i].end_time
		}, true);
	}

	//$('#calendar').fullCalendar( 'gotoDate', weeklyPlanDTOList[0].reg_date );

	// 매출액 정보 행 삽입
	makeSalesInput();
	console.log("dfdf="+weeklyPlanDTOList[0].reg_date);
	for(var i=0; i<weeklyPlanDTOList.length; i++){
		$('input[reg_date="'+weeklyPlanDTOList[i].reg_date+'"]').attr({'value': weeklyPlanDTOList[i].sales, 'disabled':'disabled'});
	}
};


window.onload = function(){

	
	// 처음에 받아온 주간계획 정보 삽입 
	if('${weeklyReportDetail}'!=''){
		var reportData = JSON.parse('${weeklyReportDetail}') 
		inputReportData(reportData);
	}else{
		var day = ['mon','tue','wed','thu','fri'];
		makeSalesInput();
		for(var i=0; i<5; i++){
			$('input[id="sales-'+day[i]+'"]').attr({'value': '', 'disabled':'disabled'});
		}
	}
	
	// 이 주간계획서의 작성자 ID
	
	$('#calendar').fullCalendar('getView').calendar.options.editable = false;
	$('#calendar').fullCalendar('getView').calendar.options.selectable = false;
	//var sessionUserId = ${user.employee_id};
	var employeeId = ${requestScope.employee_Id};
	var date = $('#calendar').fullCalendar('getDate');
	var now = $('#calendar').fullCalendar('getView').calendar.getNow();


};
</script>