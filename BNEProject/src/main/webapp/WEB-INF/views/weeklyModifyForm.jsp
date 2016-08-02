<%@page import="kr.co.bne.dto.WeeklyReportDetailDTO"%>
<%@page import="java.util.List"%>
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


     <!-- START DONUT CHART -->
     <div class="panel panel-default">
         <div class="panel-heading">
             <h3 class="panel-title">달성률</h3>                                
         </div>
         <div class="panel-body">
             <div id="weeklyDonut" style="height: 300px;"></div>
         </div>
     </div>
     <!-- END DONUT CHART -->      
     
<form  action="/modifyWeeklyReport">

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
			주간계획 ID : <span name = "weekly_report_id" id="weekly_report_id"></span><br>
			부서 ID : <input type="text" name="department_id" value="${user.department_id}" disabled><br>
			로그인 ID : <input type="text" name="employee_Id" value="${employee_Id}" disabled>
			<table class="table">
				<thead>
					<tr>
						<th>소속</th>
						<td><span id="department_name"></span></td>
					</tr>

					<tr>
						<th>이름</th>
						<td><span id="employee_name"></span></td>
					</tr>

					<tr>
						<th>작성일</th>
						<td><span id="reg_date"></span></td>
					</tr>
					<tr>
						<th>제목</th>
						<td><span id="title"></span></td>
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
						<td><span id="sales_goal"></span></td>
					</tr>
					<tr>
						<th>매출액</th>
						<td><span id="sales"></span></td>
					</tr>
					<tr>
						<th>달성률</th>
						<td><span id="achievement_rate"></span></td>
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
				<div id="calanderDetail" class="calendar">
					<div id="calendar" class="fc fc-ltr fc-unthemed">
					</div>
				</div>
			</div>
		</div>
			<button id='bbbbbbb' > 수정</button>
	</div>
	<!-- END CONTENT FRAME BODY -->
</form>     
<script>

	var inputReportData = function(reportData) {

		var weeklyReportDTO = reportData.weeklyReportDTO;
		var weeklyPlanDTOList = reportData.weeklyPlanDTOList;
		var planDetailDTOList = reportData.planDetailDTOList;
		var employee_name = reportData.employee_name;
		var department_name = reportData.department_name;

		$('#weekly_report_id')[0].value = weeklyReportDTO.weekly_report_id;
		$('#title').html(weeklyReportDTO.title);
		$('#reg_date').html(weeklyReportDTO.reg_date);
		$('#employee_name').html(employee_name);
		$('#department_name').html(department_name);
		$('#sales_goal').html(weeklyReportDTO.sales_goal);
		$('#sales').html(weeklyReportDTO.sales);
		var achievement_rate = Number(weeklyReportDTO.sales) / Number(weeklyReportDTO.sales_goal) * 100;

		$('#achievement_rate').html(achievement_rate + '%');
		if(weeklyReportDTO.sales_goal == 0 || weeklyReportDTO.sales_goal == null)
		achievement_rate = 0;
		$('#achievement_rate').html(achievement_rate + '%');

		for (var i = 0; i < planDetailDTOList.length; i++) {
			$('#calendar').fullCalendar('renderEvent', {
				"title" : planDetailDTOList[i].content,
				"allDay" : false,
				"start" : planDetailDTOList[i].start_time,
				"end" : planDetailDTOList[i].end_time
			}, true);
		}

		$('#calendar').fullCalendar( 'gotoDate', weeklyPlanDTOList[0].reg_date );
	
		// 매출액 정보 행 삽입
		makeSalesInput();
		
		for(var i=0; i<weeklyPlanDTOList.length; i++){
			$('input[reg_date="'+weeklyPlanDTOList[i].reg_date+'"]').attr({'value': weeklyPlanDTOList[i].sales, 'disabled':'disabled'});
		}

		//////////////////////////도넛 시작//////////////////////////
	
	// 도넛을 비워주고
	$('#weeklyDonut').empty();

	var achievement_rate = Number(weeklyReportDTO.sales) / Number(weeklyReportDTO.sales_goal) * 100;

	if(isNaN(achievement_rate) || !isFinite(achievement_rate))
		achievement_rate = 0;
	
	var lack_rate = 100 - achievement_rate;
	
	if(lack_rate < 0)
		lack_rate = 0;			

	// 받아온 값으로 도넛을 새로 생성하고
    var salesDount = Morris.Donut({
        element: 'weeklyDonut',
        data: [
            		{
            			label: "Achievement rate",
            			value: achievement_rate,
            			formatted: achievement_rate + '%'
            		},
            		{
            			label: "Lack of achievement?",
            			value: lack_rate,
            			formatted: lack_rate + '%'
            		}
            	],
        colors: ['#95B75D', '#1caf9a'],
        formatter: function(x, data){
        	return data.formatted;	
        },
        resize: true
    })
    
    // 매출액부분이 디폴트로 강조되도록
    salesDount.select(0);
    // 마우스를 떼도 매출액이 강조되도록
    $('#weeklyDonut').on('mouseout', function(){	salesDount.select(0);   });
	}

	window.onload = function() {
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
		
		
		var reportData = JSON.parse('${weeklyReportDetail}');
		inputReportData(reportData);

		
		
		$('#bbbbbbb').on('click',function(){
			//insertDB();
			//$('#calendarWeek').fullCalendar('next');
		/*		var s = $('#calendarWeek').fullCalendar('clientEvents');
			alert(s[0]);*/
			//editable 속성 false;
			//$('#calendarWeek').fullCalendar('getView').calendar.options.editable = false;
			
			event.preventDefault();
			
			var weeklyNumberText = $('.fc-week-number>span')[0].textContent;
			var weeklyNumber = weeklyNumberText[1]+weeklyNumberText[2];
			
			var date = $('#calendar').fullCalendar('getDate');
			var year = date._d.getFullYear();
			
			
			
			var report_id = year+"_"+weeklyNumber+"_"+${user.employee_id};
			var report_title = $('#title')[0].textContent;
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
				url : "/weeklyReport/modify",
				data : {
					sales : jPlan3,
					report : jPlan2,
					weeklyPlan : jPlan
				},

				success : function(data){
					window.location.href="/weeklyReport/detail/"+employee_id;
					alert("성공");
					//inputReportData(data);
				},
				error : function(){
					alert("실패~");
					event.preventDefault();
				}
			})
			
		});
	}
</script>

<!-- START THIS PAGE PLUGINS-->        
 <script type="text/javascript" src="/js/plugins/jquery/jquery.min.js"></script>
 <script type="text/javascript" src="/js/plugins/jquery/jquery-ui.min.js"></script>
 <script type="text/javascript" src="/js/plugins/bootstrap/bootstrap.min.js"></script>        
<script type="text/javascript" src="/js/plugins/morris/raphael-min.js"></script>
<script type="text/javascript" src="/js/plugins/morris/morris.min.js"></script>
<!-- END THIS PAGE PLUGINS-->       