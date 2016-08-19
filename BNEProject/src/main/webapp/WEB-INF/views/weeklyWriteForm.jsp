<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
#mb-alreadyWrittenReport .mb-container{
	width: 440px;
	left:50%;
}
#mb-alreadyWrittenReport .mb-container .mb-middle {
    width: 400px;
    left:0%;
    position: relative;
    color: #FFF;
}
#mb-SavePlan .mb-container{
	width: 440px;
	left:50%;
}
#mb-SavePlan .mb-container .mb-middle {
    width: 400px;
    left:0%;
    position: relative;
    color: #FFF;
}
</style>

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
	 <div class="panel panel-default" >
		<div class="panel-heading">
			<div class="page-title">
				<h3 class="panel-title">개인 정보</h3>
			</div>	
		</div>	
			<input type="hidden" name="department_id" value="${user.department_id}" disabled><br>
			<input type="hidden" name="employee_Id" value="${employee_Id}"disabled>
			<div class="panel-body">
			<table class="table table-bordered detailInfoTable">
				<thead>
					<tr>
						<th>소속</th>
						<td><span id="department_name">${user.department_name}</span></td>
					</tr>

					<tr>
						<th>이름</th>
						<td><span id="employee_name">${user.employee_name}</span></td>
					</tr>

					<tr>
						<th>작성일</th>
						<td><span id="reg_date">${currentDate}</span></td>
					</tr>
					<tr>
						<th>제목</th>
						<td><input type="text" id= "weeklyReportId" placeholder="제목을 입력해주세요"></td>
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
								<td><span id="sales_goal">${salesGoal}</span>원</td>
							</tr>
		 					<tr>
								<th>매출액</th>
								<td><span id="sales">${monthlySales}</span>원</td>
							</tr>
						</thead>
					</table>
					<hr>
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
				<div id="calandarDetail" class="calendar">
					<div id="calendar" class="fc fc-ltr fc-unthemed">
					</div>
				</div>
				<div id="buttonGroup">
					<button id ="cancle" type="submit" class="btn btn-danger pull-right"  style="margin-left:1%; margin-top:1%">취소</button>
					<button id ="savePlan" type="button" class="btn btn-success pull-right mb-control"style="margin-left:1%; margin-top:1%">저장</button>
				</div>
			</div>
		</div>
	</div>
	<!-- END CONTENT FRAME BODY -->

	<!-- MESSAGE BOX-->
	<div class="message-box animated fadeIn" data-sound="alert"	id="mb-SavePlan">
		<div class="mb-container">
			<div class="mb-middle">
				
				<div class="mb-title">
					<span class="fa fa-exclamation"></span><strong>계획을 저장하시겠습니다.</strong>
				</div>
				
				<div class="mb-content">
					<p></p>
					<p>확인시 계획확인 화면으로 이동합니다</p>
				</div>
				
				<div class="mb-footer">
					<div class="pull-right">
						<a id = "confirm"  class="btn btn-success btn-lg">확인</a>
						<button class="btn btn-default btn-lg mb-control-close">취소</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- END MESSAGE BOX-->


	<!-- MESSAGE BOX-->
	<div class="message-box animated fadeIn" data-sound="alert"	id="mb-alreadyWrittenReport">
		<div class="mb-container">
			<div class="mb-middle">
				
				<div class="mb-title">
					<span class="fa fa-exclamation"></span><strong>작성된 계획이 존재합니다.</strong>
				
				</div>
				
				<div class="mb-content">
					<p>계획을 보시겠습니까?</p>
					<p>취소시 전에 있던 페이지로 이동합니다.</p>
				</div>
				
				<div class="mb-footer">
					<div class="pull-right">
						<a href="/weeklyReport/detail/${employee_Id}" class="btn btn-success btn-lg">확인</a>
						<a href= "${beforeUrl}" class="btn btn-default btn-lg">취소</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- END MESSAGE BOX-->


</div>


<script>
window.onload = function(){
	$('#calendar').fullCalendar('getView').calendar.options.editable = true;
	$('#calendar').fullCalendar('getView').calendar.options.selectable = true;
	
	$('#calendar').fullCalendar('next');

	var result = ${result};	
	if(result == false)
		openMessageBox('#mb-alreadyWrittenReport');
	
	
	makeSalesInput();	
	var date = $('#calendar').fullCalendar('getDate');	
	
	$('#savePlan').on('click',function(){
		openMessageBox('#mb-SavePlan');
	});

$('#confirm').on('click',function(){
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
		if(sale == "")
			sale = 0;
		
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
		
		var title = s[i].title;//.replace(/\'/g,"'");
		 
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
	
	$.ajax({
		type : "POST",
		url : "/weeklyReport/write",
		data : {
			sales : jPlan3,
			report : jPlan2,
			weeklyPlan : jPlan
		},
		success : function(){
			alert("성송");
			window.location.href="/weeklyReport/detail/"+employee_id;
		},
		error : function(){
			alert("실패");
		}
	})
});

$('#sales-mon ,#sales-tue,#sales-wed,#sales-thu,#sales-fri').keydown(function(e){
	var range = this.selectionStart;
	if(range == 0 && e.keyCode==48 || e.keyCode==96)
		e.preventDefault();
	
    if (e.keyCode!=37&&e.keyCode!=38&&e.keyCode!=39&&e.keyCode!=40&&e.keyCode!=9&&e.keyCode!=8&&(e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
        e.preventDefault();
    }
});


}

</script>

<script type="text/javascript" src="/js/weekly.js"></script>