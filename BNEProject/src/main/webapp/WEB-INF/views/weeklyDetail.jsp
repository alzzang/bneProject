<%@page import="kr.co.bne.dto.WeeklyReportDetailDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



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
			주간계획 ID : <span id="weekly_reportI_d"></span><br>
			
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
						<td></td>
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
	var inputReportData = function(weeklyDetail){
		var s =JSON.parse(weeklyDetail.weeklyReportName);
		alert(s);
	};

	window.onload = function(){
		
		var weeklyDetail = JSON.parse('${weeklyReportDetail}');
		inputReportData(weeklyDetail);
		
		
		var reportDetail = {};
		$('#calendarWeek').fullCalendar('getView').calendar.options.editable = false;
		$('#calendarWeek').fullCalendar('getView').calendar.options.selectable = false;
		var o = '<button type="button" class="fc-next-button fc-button fc-state-default fc-corner-right"><span class="fc-icon fc-icon-right-single-arrow"></span></button>';
		$('.fc-center').append(o);
		o = '<button type="button" class="fc-prev-button fc-button fc-state-default fc-corner-left"><span class="fc-icon fc-icon-left-single-arrow"></span></button>';
		$('.fc-center').prepend(o);
		
	/* 	$('#calendarWeek').fullCalendar('renderEvent',{
		    "title":"dd~",
			    "allDay":"",
			    //"id":"15",
			    "start":"2016-07-18 09:30:00",
			    "end":"2016-07-18 17:30:00"
	    },true);
	    
	    $('#calendarWeek').fullCalendar('renderEvent',{
		    "title":"공부~",
			    "allDay":"",
			    //"id":"15",
			    "start":"2016-07-20 13:30:00",
			    "end":"2016-07-20 17:30:00"
	    },true);
	    
	    $('#calendarWeek').fullCalendar('renderEvent',{
		    "title":"프로젝트~",
			    "allDay":"",
			    //"id":"15",
			    "start":"2016-07-22 09:30:00",
			    "end":"2016-07-22 17:30:00"
	    },true); */
	
	
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
	}

</script>