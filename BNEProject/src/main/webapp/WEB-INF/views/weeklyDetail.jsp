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
#mb-NoWeeklyPlan .mb-container{
	width: 400px;
	left:50%;
}
#mb-NoWeeklyPlan .mb-container .mb-middle {
    width: 360px;
    left:0%;
    position: relative;
    color: #FFF;
}
#contentFrameBody{
    position: absolute;
    left: 0px;
    top: 0px;
	width:100%;
	height:100%;
    background: rgba(0, 0, 0, 0.3);
    z-index: 9999;
    display:table-cell;
    vertical-align: middle;

}
#contentFrameBody h1{
	position:relative;
	text-align:center;
	top : 45%;
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
	<form  action="/weeklyReport/modifyWeeklyReport" method="post">	
	<div class="content-frame-left" style="height: 1054px;">
	 <div class="panel panel-default" >
		<div class="panel-heading">
			<div class="page-title">
				<h3 class="panel-title">개인 정보</h3>
			</div>	
		</div>	
			
			<input type ="hidden" name = "weekly_report_id" id = "weekly_report_id" ><br>
			<input type="hidden" name="department_id" value="${user.department_id}" disabled><br>
			<input type="hidden" name="employee_Id" id = "employee_Id" value="${employee_Id}" disabled>
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
	</form>
	
	<!-- MESSAGE BOX-->
	<div class="message-box animated fadeIn" data-sound="alert"	id="mb-NoWeeklyPlan">
		<div class="mb-container">
			<div class="mb-middle">
				<div class="mb-title">
					<span class="fa fa-exclamation"></span><strong>작성된 계획이 없습니다.</strong>
				</div>
				<div class="mb-content">
					<p></p>
					<p></p>
				</div>
				<div class="mb-footer">
					<div class="pull-right">
						<button class="btn btn-success btn-lg mb-control-close">확인</button>
						<!-- //<a href="" class="btn btn-success btn-lg">확인</a> -->
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- END MESSAGE BOX-->
	<!-- END CONTENT FRAME BODY -->
</div>

<!-- START THIS PAGE PLUGINS-->        
 <script type="text/javascript" src="/js/plugins/jquery/jquery.min.js"></script>
 <script type="text/javascript" src="/js/plugins/jquery/jquery-ui.min.js"></script>
 <script type="text/javascript" src="/js/plugins/bootstrap/bootstrap.min.js"></script>        
<script type="text/javascript" src="/js/plugins/morris/raphael-min.js"></script>
<script type="text/javascript" src="/js/plugins/morris/morris.min.js"></script>
<script type="text/javascript" src="/js/weekly.js"></script>
<!-- END THIS PAGE PLUGINS-->       

<script>
	$(window).load(function(){
		weeklyReportDetail = ${weeklyReportDetail};
		
		// 처음에 받아온 주간계획 정보 삽입 
		if(weeklyReportDetail !== false){
			var parseToWeeklyReportDetail = JSON.stringify(weeklyReportDetail);
			var reportData = JSON.parse(parseToWeeklyReportDetail); 
			inputReportData(reportData);
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
		var o = '<button type="button" class="fc-next-button fc-button fc-state-default fc-corner-right"><span class="fc-icon fc-icon-right-single-arrow"></span></button>';
		$('.fc-center').append(o);
		o = '<button type="button" class="fc-prev-button fc-button fc-state-default fc-corner-left"><span class="fc-icon fc-icon-left-single-arrow"></span></button>';
		$('.fc-center').prepend(o);
		
		var sessionUserId = ${user.employee_id};
		var employeeId = ${employee_Id};
		var date = $('#calendar').fullCalendar('getDate');
		var now = $('#calendar').fullCalendar('getView').calendar.getNow();
		now._d.setDate(now._d.getDate()+7);
		if(sessionUserId == employeeId && isModify(now)){
			o = '<button type="submit" class="fc-button fc-state-default fc-corner-right fc-corner-left"><span class="fa fa-pencil"></span></button></form>';
			$('.fc-right').prepend(o);
		}

		$('.fc-next-button').on('click', function() {
			var report_id = makeReportId('next');

			$.ajax({
				type : "POST",
				url : "/weeklyReport/getPlan",
				data : {
					ReportId : report_id
				},
				success : function(data) {
	    			if(data.weeklyReportDTO == null){
	    				$('#calendar').fullCalendar('next');
	    		   		$('#calendar').fullCalendar('removeEvents');
	    				var o = '<div id="contentFrameBody"><h1>등록된 계획이 없습니다.</h1></div>'
		    			$('#trash').append(o);
						openMessageBox('#mb-NoWeeklyPlan');
						var weeklyNumberText = $('.fc-week-number>span')[0].textContent;
						var weeklyNumber = parseInt(weeklyNumberText[1] + weeklyNumberText[2]);
						
	    				$('#reg_date').html("");
	    				$('#title').html(weeklyNumber+'주의 계획');
	    				$('#employee_name').html('${user.employee_name}');
	    				$('#department_name').html('${user.department_name}');
	    				
	    				var day = ['mon','tue','wed','thu','fri'];
	    				makeSalesInput();
	    				for(var i=0; i<5; i++){
	    					$('input[id="sales-'+day[i]+'"]').attr({'value': '0', 'disabled':'disabled'});
	    				}
	    				var box = $('#mb-NoWeeklyPlan');
	    			}else{
	    				$('#calendar').fullCalendar('next');
	    		   		$('#calendar').fullCalendar('removeEvents');
	    		   		
	    				if(sessionUserId == employeeId){
	    	 				if(!isModify(now)){
	    						$('.fc-right').empty();
	    	 				}
	    	 				else if(isModify(now)){
	    	 					o = '<button type="submit" class="fc-button fc-state-default fc-corner-right fc-corner-left"><span class="fa fa-pencil"></span></button>';
	    	 					$('.fc-right').prepend(o);
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
	    				$('#calendar').fullCalendar('prev');
	    				$('#calendar').fullCalendar('removeEvents');
	    				var o = '<div id="contentFrameBody"><h1>등록된 계획이 없습니다.</h1></div>'
	    				$('#trash').append(o);
	    				//$('#contentFrameBody').css("background",'rgba(255,255,255,0.5)');
						openMessageBox('#mb-NoWeeklyPlan');
						var weeklyNumberText = $('.fc-week-number>span')[0].textContent;
						var weeklyNumber = parseInt(weeklyNumberText[1] + weeklyNumberText[2]);
						//$('#weekly_report_id')[0].value = weeklyReportDTO.weekly_report_id;
						$('#title').html(weeklyNumber+'주의 계획');
						$('#employee_name').html('${user.employee_name}');
						$('#department_name').html('${user.department_name}');
						
						var day = ['mon','tue','wed','thu','fri'];
						makeSalesInput();
						for(var i=0; i<5; i++){
							$('input[id="sales-'+day[i]+'"]').attr({'value': '0', 'disabled':'disabled'});
						}
						var box = $('#mb-NoWeeklyPlan');
				    	//$('#calendar').fullCalendar('next');
	    			}else{
	    				$('#calendar').fullCalendar('prev');
	    				$('#calendar').fullCalendar('removeEvents');

	    				result = isModify(now);
	    				console.log(result);
	    	 			if(sessionUserId == employeeId){
	    	 				if(!result){
	    						$('.fc-right').empty();
	    	 				}else if(result){
	    	 					o = '<button type="submit" class="fc-button fc-state-default fc-corner-right fc-corner-left"><span class="fa fa-pencil"></span></button>';
	    	 					$('.fc-right').prepend(o);
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
		$('.fc-mon ,.fc-tue,.fc-wed,.fc-thu,.fc-fri').on('click',function(){
			var date = this.dataset.date;
			$.ajax({
				type : "POST",
				url : "/dailyReport/checkReport",
				data : {
					date : date
				},
				success : function(data){
					alert(data);
 					if(data !== 0){
 						var path = "/dailyReport/detail";
   						var form = document.createElement("form");
   						form.setAttribute("method", "POST");
   						form.setAttribute("action", path);
 						
 						var hiddenField = document.createElement("input");
 						hiddenField.setAttribute("type", "hidden");
 						hiddenField.setAttribute("name", "dailyReportId");
 						hiddenField.setAttribute("value", data);
 						form.appendChild(hiddenField);
 						document.body.appendChild(form);

 						form.submit();
					}else{
						location.href="/dailyReport/write"
					} 
				}
			});

		});

	});
	$(document).ready(function() {


	});
</script>
