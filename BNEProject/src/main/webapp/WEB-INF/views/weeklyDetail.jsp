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
			주간계획 ID : <span id="weekly_report_id"></span><br>
			
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
					<div id="calendarWeek" class="fc fc-ltr fc-unthemed">
					</div>
				</div>
			</div>
		</div>
			<button id='aaaa' > aa</button>
	</div>
	<!-- END CONTENT FRAME BODY -->
  
</div>

<script>
	var inputReportData = function(reportData){
		var weeklyReportDTO = reportData.weeklyReportDTO;
		var weeklyPlanDTOList = reportData.weeklyPlanDTOList;
		var planDetailDTOList = reportData.planDetailDTOList;
		var employee_name = reportData.employee_name;
		var department_name = reportData.department_name;
		
		$('#weekly_report_id').html(weeklyReportDTO.weekly_report_id);
		$('#title').html(weeklyReportDTO.title);
		$('#reg_date').html(weeklyReportDTO.reg_date);
		$('#employee_name').html(employee_name);
		$('#department_name').html(department_name);
		$('#sales_goal').html(weeklyReportDTO.sales_goal);
		$('#sales').html(weeklyReportDTO.sales);
		var achievement_rate = Number(weeklyReportDTO.sales) / Number(weeklyReportDTO.sales_goal) * 100;
		
		$('#achievement_rate').html(achievement_rate + '%');
		
		for(var i=0; i<planDetailDTOList.length; i++){
			$('#calendarWeek').fullCalendar('renderEvent',{
			   		"title":planDetailDTOList[i].content,
				    "allDay":"",
				    "start":planDetailDTOList[i].start_time,
				    "end":planDetailDTOList[i].end_time
		    },true);
		}

		for(var i=0; i<weeklyPlanDTOList.length; i++){
			$('input[reg_date="'+weeklyPlanDTOList[i].reg_date+'"]').attr({'value': weeklyPlanDTOList[i].sales, 'disabled':'disabled'});
		}
		
	    Morris.Donut({
	        element: 'weeklyDonut',
	        data: [
	            {label: "Sales", value: weeklyReportDTO.sales},
	            {label: "Sales 남은거", value: Number(weeklyReportDTO.sales_goal) - Number(weeklyReportDTO.sales)},
	        ],
	        colors: ['#95B75D', '#1caf9a']
	    });
		
	}


	window.onload = function(){
		
		var reportDetail = {};
		$('#calendarWeek').fullCalendar('getView').calendar.options.editable = false;
		$('#calendarWeek').fullCalendar('getView').calendar.options.selectable = false;
		var o = '<button type="button" class="fc-next-button fc-button fc-state-default fc-corner-right"><span class="fc-icon fc-icon-right-single-arrow"></span></button>';
		$('.fc-center').append(o);
		o = '<button type="button" class="fc-prev-button fc-button fc-state-default fc-corner-left"><span class="fc-icon fc-icon-left-single-arrow"></span></button>';
		$('.fc-center').prepend(o);
		
	
	    $('.fc-next-button').on('click',function(){
	    	$('#calendarWeek').fullCalendar('removeEvents');
	    	$('#calendarWeek').fullCalendar('next');
	    	
	    	$.ajax({
	    		type:"POST",
	    		url : "/weeklyReport/getPlan",
	    		data:{
	    			ReportId : 10
	    		},
	    		success :function(data){
/* 	    			alert("성공~");
 */	    			data;
	    		},
				error : function(){
/* 					alert("실패~"); */
				}
	    	})
	    	
	    });
	   	$('.fc-prev-button').on('click',function(){
	   		$('#calendarWeek').fullCalendar('removeEvents');
	    	$('#calendarWeek').fullCalendar('prev');
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