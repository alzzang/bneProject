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
.fc-time-grid .fc-slats td {
    height: 2.8em;
}

.detailInfoTable th{
	text-align: left;
	width: 100px;
}
</style>

<div class="content-frame">
	<!-- START CONTENT FRAME TOP -->
	<div class="content-frame-top">
		<div class="page-title">
			<h2>주간 계획 </h2>
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
	 <div class="panel panel-default" >
		<div class="panel-heading">
			<div class="page-title">
				<h3 class="panel-title">개인 정보</h3>
			</div>	
		</div>	
			<form  action="/weeklyReport/modifyWeeklyReport" method="post">	
			<input type ="hidden" name = "weekly_report_id" id = "weekly_report_id" ><br>
			<input type="hidden" name="department_id" value="${user.department_id}" disabled><br>
			<input type="hidden" name="employee_Id" value="${employee_Id}" disabled>
			<div class="panel-body">
			<table class="table table-bordered detailInfoTable">
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
	      </div>
		</div>
	     <!-- START DONUT CHART -->
	     <div class="panel panel-default" >
	         <div class="panel-heading">
	             <h3 class="panel-title">매출 현황</h3>                          
	         </div>
	         <div class="panel-body">
					<table class="table table-bordered detailInfoTable">
						<thead>
							<tr>
								<th>매출목표 </th>
								<td><span id="sales_goal"></span>원</td>
							</tr>
		 					<tr>
								<th>매출액</th>
								<td><span id="sales"></span>원</td>
							</tr>
						</thead>
					</table>
					<hr>
	             <div id="weeklyDonut" style="height: 300px;"></div>
	         </div>
	     </div>
	     <!-- END DONUT CHART -->      
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
	</div>
	<!-- END CONTENT FRAME BODY -->
</div>    
<script>
	var makeReportId = function(flag){
		var report_id;
		var weeklyNumberText = $('.fc-week-number>span')[0].textContent;
		var weeklyNumber = parseInt(weeklyNumberText[1] + weeklyNumberText[2]);

		var date = $('#calendar').fullCalendar('getDate');
		var year = date._d.getFullYear();
		if(flag == 'prev'){
			report_id = year + "_" + (weeklyNumber-1) + "_" + ${employee_Id};
		}
		else if(flag == 'next'){
			report_id = year + "_" + (weeklyNumber+1) + "_" + ${employee_Id};
		}
		return report_id;
	};
	
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

	var isModify = function(now){
		var result = false;
		
		
		var lastDayWeek = $('#calendar').fullCalendar('getView').end;
		lastDayWeek._d.setDate(lastDayWeek._d.getDate() +1)
		var getDate = lastDayWeek._d.getDate();
		console.log(lastDayWeek);
		console.log(now);
		//console.log(now<lastDayWeek);
/* 		lastDayWeek._d.setDate(lastDayWeek._d.getDate() +1);
		lastDayWeek._d.setHours(24); */
		if(now<lastDayWeek)
			return true;
		
		return result;
	}
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
		var isModifyButton = true;

		
		// 처음에 받아온 주간계획 정보 삽입 
		var reportData = JSON.parse('${weeklyReportDetail}') 
		inputReportData(reportData);
		
		// 이 주간계획서의 작성자 ID
		var employee_id = reportData.weeklyReportDTO.employee_id;
		var weekly_report_id = reportData.weeklyReportDTO.weekly_report_id;
		
		$('#calendar').fullCalendar('getView').calendar.options.editable = false;
		$('#calendar').fullCalendar('getView').calendar.options.selectable = false;
		var o = '<button type="button" class="fc-next-button fc-button fc-state-default fc-corner-right"><span class="fc-icon fc-icon-right-single-arrow"></span></button>';
		$('.fc-center').append(o);
		o = '<button type="button" class="fc-prev-button fc-button fc-state-default fc-corner-left"><span class="fc-icon fc-icon-left-single-arrow"></span></button>';
		$('.fc-center').prepend(o);
		
		var sessionUserId = ${user.employee_id};
		var employeeId = ${employee_Id};
		var date = $('#calendar').fullCalendar('getDate');
		var now = $('#calendar').fullCalendar('getView').calendar.getNow();
		if(sessionUserId == employeeId && isModify(now)){
			o = '<button type="submit" class="fc-button fc-state-default fc-corner-right fc-corner-left"><span class="fa fa-pencil"></span></button></form>';
			$('.fc-right').prepend(o);
		}

		$('.fc-next-button').on('click', function() {
			var report_id = makeReportId('next');
			
			//$('#weekly_report_id').html(report_id);
			$.ajax({
				type : "POST",
				url : "/weeklyReport/getPlan",
				data : {
					ReportId : report_id
				},
				success : function(data) {
	    			if(data.weeklyReportDTO == null){
	    				alert("입력된 주간계획이 없습니다!");
	    			}else{
	    				$('#calendar').fullCalendar('next');
	    		   		$('#calendar').fullCalendar('removeEvents');
	    		   		
	    				if(sessionUserId == employeeId){
	    	 				if(!isModify(now)){
	    						$('.fc-right').empty();
	    						isModifyButton = false;
	    	 				}
	    	 				else if(isModify(now) && isModifyButton == false){
	    	 					o = '<button type="submit" class="fc-button fc-state-default fc-corner-right fc-corner-left"><span class="fa fa-pencil"></span></button>';
	    	 					$('.fc-right').prepend(o);
	    	 					isModifyButton = true;
	    	 				}
	    				}
	    				inputReportData(data);
	    			}
				},
				error : function() {
					alert("주간계획 데이터를 불러오는데 실패했습니다."); 
				}
			})

		});
		$('.fc-prev-button').on('click', function() {
			
			var report_id = makeReportId('prev');
		
			$.ajax({
				type : "POST",
				url : "/weeklyReport/getPlan",
				data : {
					ReportId : report_id
				},
				success : function(data) {
	    			if(data.weeklyReportDTO == null){
						alert("입력된 주간계획이 없습니다!");
				    	//$('#calendar').fullCalendar('next');
	    			}else{
	    				$('#calendar').fullCalendar('prev');
	    				$('#calendar').fullCalendar('removeEvents');

	    				result = isModify(now);
	    				console.log(result);
	    	 			if(sessionUserId == employeeId){
	    	 				if(!result){
	    						$('.fc-right').empty();
	    						isModifyButton = false;
	    	 				}else if(result && isModifyButton == false){
	    	 					o = '<button type="submit" class="fc-button fc-state-default fc-corner-right fc-corner-left"><span class="fa fa-pencil"></span></button>';
	    	 					$('.fc-right').prepend(o);
	    	 					isModifyButton = true;
	    	 				}
	    				}
	    				
	    				
	    		   		
	    				inputReportData(data);
	    			}
				},
				error : function() {
					alert("주간계획 데이터를 불러오는데 실패했습니다."); 
				}
			})
		});

		var reportData = JSON.parse('${weeklyReportDetail}');
		inputReportData(reportData);

	}
</script>

<!-- START THIS PAGE PLUGINS-->        
 <script type="text/javascript" src="/js/plugins/jquery/jquery.min.js"></script>
 <script type="text/javascript" src="/js/plugins/jquery/jquery-ui.min.js"></script>
 <script type="text/javascript" src="/js/plugins/bootstrap/bootstrap.min.js"></script>        
<script type="text/javascript" src="/js/plugins/morris/raphael-min.js"></script>
<script type="text/javascript" src="/js/plugins/morris/morris.min.js"></script>
<!-- END THIS PAGE PLUGINS-->       