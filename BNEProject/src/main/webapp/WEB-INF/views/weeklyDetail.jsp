<%@page import="kr.co.bne.dto.WeeklyReportDetailDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%-- <c:set var="employeeIdList" scope = "request" value= "${reqeustScope.employeeIdList}" ></c:set> --%>
<%
/* 	List<Integer> reportIdList = (List<Integer>)request.getAttribute("reportIdList");
	int reportSize = reportIdList.size(); 
	int curIdx = reportIdList.size()-1; */
%>


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
						<td><input type="text"   placeholder="제목을 입력해주세요" readonly></td>
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
	window.onload = function(){
		var weeklyDetail = JSON.parse('${weeklyReportDetail}');
		inputReportData(weeklyDetail);
		
		
		var reportDetail = {};
		$('#calendarWeek').fullCalendar('getView').calendar.options.editable = false;
		$('#calendarWeek').fullCalendar('getView').calendar.options.selectable = false;
		var o = '<button type="button" class="fc-next-button fc-button fc-state-default fc-corner-right"><span class="fc-icon fc-icon-right-single-arrow"></span></button>';
		$('.fc-center').append(o);
		o = '<button type="button" class="fc-prev-button fc-button fc-state-default fc-corner-left"><span class="fc-icon fc-icon-left-single-arrow"></span></button>'
		$('.fc-center').prepend(o);
		
		$('#calendarWeek').fullCalendar('renderEvent',{
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
	    },true);
	    
	    $('.fc-next-button').on('click',function(){
	    	$('#calendarWeek').fullCalendar('removeEvents');
	    	$('#calendarWeek').fullCalendar('next');
	    	curIdx++;
	    	if(reportIdList.length-1>=curIdx){
		    	$.ajax({
		    		type:"POST",
		    		url : "/weeklyReport/getPlan",
		    		data:{
		    			ReportId : reportIdList[curIdx]
		    		},
		    		success :function(data){
		    			alert("성공");
		    			addSegment(data.planDetailDTOList);
		    		},
					error : function(){
						alert("실패");
					}
		    	})
	    	}
	    });
	   	$('.fc-prev-button').on('click',function(){
	   		$('#calendarWeek').fullCalendar('removeEvents');
	    	$('#calendarWeek').fullCalendar('prev');
	    	curIdx--;
	    	if(0<=curIdx){
	    		
	    	}
	    });
	   	var addSegment = function(planDetailList){
	   		for(var i= 0; i<planDetailList.length; i++){
			    $('#calendarWeek').fullCalendar('renderEvent',{
				    "title":planDetailList[i].content,
					    "allDay":"",
					    //"id":"15",
					    "start":planDetailList[i].start_time,
					    "end":planDetailList[i].end_time
			    },true);
	   		}

	   	};
	}
</script>