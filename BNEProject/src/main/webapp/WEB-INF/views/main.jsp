<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
#bookmark{

}
.tasks .task-warning.task-item.task-complete{
	background:#fdfbf3;
}

</style>
<div class="page-content-wrap">
	<div class="row">
		<div class="col-md-6">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">주간계획</h3>
				</div>
				<div class="panel-body">
					<div id="calanderDetail" class="calendar" >
						<div id="calendar" class="fc fc-ltr fc-unthemed "style="height: 450px;">
						</div>
					</div>
				</div>
			</div>

			<!-- END LINE CHART -->

		</div>
		<div class="col-md-6">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">미승인</h3>
				</div>
				<div class="panel-body">
					<div class="tasks ui-sortable" id="unapproval"
						style="height: 450px;">
						<c:forEach items="${requestScope.unapproval.dailyReportList}"
							var="rlist">


							<div
								class="task-item task-warning task-complete widget widget-item-icon"
								id='${rlist.idx}' style="max-height:102px; min-height:10px;">

								<div  class="task-text ui-sortable-handle" id='${rlist.idx}'
									onclick="detailDaily('${rlist.idx}')" style="max-height:102px; padding:0px; padding-top:10px; background:#fdfbf3; color:black;">
									<div class="widget-item-left">
										<span id="bookmark" class="fa fa-file-text-o"
											style="font-size: 350%; color: #000000;"></span>
<!-- 										<span id="bookmark" class="fa fa-bookmark"
											style="font-size: 350%; color: #FFFFFF;"></span> -->
									</div>
									<div style="font-size:1.7em; white-space: nowrap; max-width:25em;margin-top:3%; overflow:hidden; text-overflow:ellipsis;">
										${rlist.employee_name}님이 ${rlist.reg_date}일에 작성한  미승인 보고서
									</div>
								</div>
							</div>

						</c:forEach>

					</div>
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
window.onload = function(){
	weeklyReportDetail = ${weeklyReportDetail};
	
	// 처음에 받아온 주간계획 정보 삽입 
	if(weeklyReportDetail !== false){
		var parseToWeeklyReportDetail = JSON.stringify(weeklyReportDetail);
		var reportData = JSON.parse(parseToWeeklyReportDetail); 
		mainInputReportData(reportData);
	}else{
		var weeklyNumberText = $('.fc-week-number>span')[0].textContent;
		var weeklyNumber = parseInt(weeklyNumberText[1] + weeklyNumberText[2]);
		//$('#weekly_report_id')[0].value = weeklyReportDTO.weekly_report_id;
		$('#title').html(weeklyNumber+'주의 계획');
		$('#employee_name').html('${user.employee_name}');
		$('#department_name').html('${user.department_name}');
		
		makeSalesInput();
		var day = ['mon','tue','wed','thu','fri'];
		for(var i=0; i<5; i++){
			$('input[id="sales-'+day[i]+'"]').attr({'value': '0', 'disabled':'disabled'});
		}
		openMessageBox('#mb-NoWeeklyPlan');
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