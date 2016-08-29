<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- <script src="/js/dailysettings.js"></script> -->
<script>
var removeId=0;
var jsonArray=new Array();

 $(document).ready(function(){
	  searchSalesGoal('${dailyReport.reg_date}');
	 computeGuage();
	var counsellingArray = jQuery.parseJSON('${counsellingJson}');
 	$.each( counsellingArray, function( counselRecord, value ) {
		   var html='<li id="'+value.counsel_id+'"><a href="#" data-toggle="modal" data-target="#myModal2" onclick="tagTest('+'\''+value.counsel_id+'\''+','+'\''+value.title+'\''+','+'\''+value.content+'\''+','+'\''+value.client_id+'\''+','+'\''+value.sec_client_id+'\''+','+'\''+value.address+'\''+','+'\''+value.client_id +'\''+','+'\''+value.representative+'\''+','+'\''+value.counsel_id+'\''+')"><span class="fa fa-tag"></span>'
		  html+=value.title+'</a><span class="glyphicon glyphicon-remove " onclick="removeTag('+'\''+value.counsel_id+'\''+','+1+')"></span></li>';
		  $('.list-tags').prepend(html); 
 	}); 
	jsonArray=counsellingArray;
 	 var t=JSON.stringify(jsonArray);
 	localStorage.setItem("tt", t);
});
 
 $(window).load( function(){
	 var a=${dailyReport.sales};
	 var b=$('#dailyGoal').val();
	 changeProgress(a,b);
	 setUpdateContents('${dailyReport.content}');
 });
</script>
<style>
input[type=text] {
   color: black !important; 
}
</style>

<div class="content-frame">
	<!-- START CONTENT FRAME TOP -->
	<div class="content-frame-top">
		<div class="page-title">
			<h2>
				<span class="fa fa-inbox"></span> 일일 업무 보고
			</h2>
		</div>
		<div class="pull-right">
			<button class="btn btn-default content-frame-left-toggle">
				<span class="fa fa-bars"></span>
			</button>
		</div>
	</div>
	<!-- END CONTENT FRAME TOP -->

	<!-- START CONTENT FRAME LEFT -->
	<div class="content-frame-left" style="height: 837px;">
	 <div class="panel panel-default">
		<div class="panel-heading ui-draggable-handle">
			<div class="page-title">
				<h3 class="panel-title">매출 현황</h3>
			</div>	
		</div>	
			
			<input type="hidden" name="weekly_report_id" id="weekly_report_id" value="2016_34_101"><br>
			<input type="hidden" name="department_id" value="1" disabled=""><br>
			<input type="hidden" name="employee_Id" value="101" disabled="">
			<div class="panel-body">
			<table class="table table-bordered detailInfoTable">
				<thead>
					<tr>
						<th>소속</th>
						<td><span id="department_name">${sessionScope.employee.department_name}</span></td>
					</tr>

					<tr>
						<th>이름</th>
						<td><span id="employee_name">${sessionScope.employee.employee_name}</span></td>
					</tr>

					<tr>
						<th>매출 목표</th>
						<td><span id="goalValue">${sessionScope.employee.sales_goal}</span></td>
					</tr>
				</thead>
			</table>
	      </div>
		</div>
	</div>
	
	<c:if test="${sessionScope.employee.department_name ne null}">
   <script>
      $('#goalValue').text(addComma('${sessionScope.employee.sales_goal}'));
   </script>
   </c:if>
	<!-- END CONTENT FRAME LEFT -->

	<!-- START CONTENT FRAME BODY -->


	<div class="content-frame-body" style="height: 897px;">
		<div class="row">
			<div class="col-md-12">

				<form class="form-horizontal" action="/dailyReport/updateform"
					id="dailyform" method="post">
					<div class="panel panel-default">
						<div class="panel-heading ui-draggable-handle">
							<h3 class="panel-title">일일 업무 보고 작성</h3>
							<ul class="panel-controls">
								<li></li>
							</ul>
						</div>
						<div class="panel-body">

							<div class="form-group">
								<label class="col-md-3 col-xs-12 control-label">제목</label>
								<div class="col-md-6 col-xs-12">
									<div class="input-group">
										<span class="input-group-addon"><span
											class="fa fa-pencil"></span></span> <input type="text"
											class="form-control" name="title" required="required" value="${dailyReport.title}">
									</div>
								</div>


							</div>


							<div class="form-group">
								<label class="col-md-3 col-xs-12 control-label">작성일</label>
								<div class="col-md-6 col-xs-12">
									<div class="input-group">
										<span class="input-group-addon"><span
											class="fa fa-calendar"></span></span> <input type="text" 
											class="form-control datepicker"  value="${dailyReport.reg_date}" id="reg_date" name="reg_date" onchange="searchSalesGoal(this.value)"  required="required" >
									</div>
								</div>
							</div>


							<div class="form-group">
								<label class="col-md-3 col-xs-12 control-label">매출액</label>
								<div class="col-md-4 col-xs-12">
									<div class="input-group">
										<span class="input-group-addon" style="padding-bottom: 10px;"><span
											class="fa fa-won"></span></span> <input type="text"
											class="form-control"
											id="inputSales" 
											name="sales"  pattern="[0]{1}|[1-9]{1}[0-9]{0,15}" required="required" value="${dailyReport.sales}">
										<span class="progress"> <span
											class="progress-bar progress-bar-danger" role="progressbar"
											aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"
											style="width: 0.1%" id="progressCondition"></span>
										</span>

									</div>
								</div>
								
								<div class="col-md-2 col-xs-12">
								<input type="text"
											class="form-control"
											id="dailyGoal"
											pattern="[0]{1}|[1-9]{1}[0-9]{0,15}" readonly="readonly" required="required">
								</div>


							</div>


							<div class="form-group">
								<label class="col-md-3 col-xs-12 control-label">주행</label>
								<div class="col-md-2 col-xs-12">
									<div class="input-group">
										<span class="input-group-addon">출근 시 계기판</span> <input
											type="text" class="form-control" value="${dailyReport.before_gauge }" onchange="computeGuage()" name="before_gauge" id="before_gauge" pattern="[0]{1}|[1-9]{1}[0-9]{0,10}" required="required">
									</div>
								</div>

								<div class="col-md-2 col-xs-12">
									<div class="input-group">
										<span class="input-group-addon">퇴근 시 계기판</span> <input
											type="text" value="${dailyReport.after_gauge }" class="form-control" onchange="computeGuage()" name="after_gauge" id="after_gauge" pattern="[0]{1}|[1-9]{1}[0-9]{0,10}" required="required">
									</div>
								</div>

								<div class="col-md-2 col-xs-12">
									<div class="input-group">
										<span class="input-group-addon">주행 거리</span> <input
											type="text" class="form-control" id="result_guage" pattern="[0-9]{0,10}" required="required" readonly="readonly">
									</div>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-3 col-xs-12 control-label">상담 일지</label>
								<div class="col-md-6 col-xs-12">
									<div class="input-group">
										<!-- <input type="text" class="tagsinput" value="First,Second,Third,Fourth" id="tags1466576242041" style="display: none;" readonly="readonly">
										<span class="input-group-addon" style="cursor: pointer;"><span
											class="fa fa-plus"></span></span> -->
										<ul class="list-tags">

											<li><a href="#" data-toggle="modal" data-target="#myModal2" id="modalAdd"><span class="fa fa-plus"></span>추가</a></li>
										</ul>
									</div>
								</div>
							</div>
							<jsp:include page="SmartEditor2.jsp"></jsp:include>
							<div class="panel-footer">
								<button class="btn btn-primary pull-right"
									id="dailyReportSubmit" onclick="submitContents()">Submit</button>
							</div>

						</div>
						<input type="hidden" name="daily_report_id" value="${dailyReport.daily_report_id }">
						<input type="hidden" name="department_id" value="${sessionScope.user.department_id}">
						<input type="hidden" name="employee_id" value="${sessionScope.user.employee_id}">
						<input type="hidden" id="counsellingJSON" name="counsellingJSON" value="">
						<input type="hidden" id="searchGoalFlag" value="0">
						<input type="hidden" id="dateFlag" value="${dailyReport.reg_date}">
				</form>

			</div>
		</div>



	</div>

	<!-- END CONTENT FRAME BODY -->

</div>
<div id="myModal2"  class="modal fade" role="dialog">
	<div class="modal-dialog modal-admin">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
			
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title"><span class="fa fa-arrow-circle-o-left"></span> 상담일지 작성</h4>
				
			</div>
			<div class="modal-body">
			<div class="page-title">
	 <h2>
		
	</h2>  
</div> 
<!-- END PAGE TITLE -->

<!-- PAGE CONTENT WRAPPER -->

<div class="page-content-wrap">
	<div class="row">
		<div class="col-md-12">
			<form action="#" onsubmit="event.preventDefault();" method="POST" class="form-horizontal" id="dailyModalForm">
				<div class="panel panel-default">
					<div class="panel-body">										
						<div class="form-group">
							<label class="col-md-2 col-xs-12 control-label">제목</label>
							<div class="col-md-8 col-xs-12">
								<div class="input-group">
									<span class="input-group-addon"><span
										class="fa fa-pencil"></span></span> 
										<input type="text" class="form-control" name="title" id="modalTitle">
								</div>
								</div>
						</div>

						<div class="form-group">
							<label class="col-md-2 col-xs-12 control-label">
							</label>
							<div class="col-md-8 col-xs-12">
								<textarea class="form-control summernote" name="content"
									rows="5" id="modalContent"></textarea>

							</div>
						</div>

						<div class="form-group">
							<label class="col-md-2 col-xs-12 control-label">고객명</label> <span
								class="col-md-4 col-xs-12"> <select class="form-control"
								name="counselling_id" id="counselling_id" required>
									<option value="" disabled selected hidden="true">선택하세요!</option>
									<c:forEach var="client" items="${clients}" >
										<option value="${client.client_id }">${client.client_name}</option>
									</c:forEach>
							</select> 
							</span> 
							<span class="col-md-2 col-xs-12"> 
							<input type="text" class="form-control" placeholder="고객코드" readonly id="client_id" name="client_id">
							</span>
							 
							 <span class="col-md-2 col-xs-12"> 
							 <input type="text" class="form-control" placeholder="대표자" readonly id="representative" name="representative">
							</span>

						</div>

						<div class="form-group">
							<label class="col-md-2 col-xs-12 control-label">2차거래선</label>
							<span class="col-md-5 col-xs-12">
								<select class="form-control" name="sec_client_id"
									id="sec_client_id" required>
								</select>
							</span>
							<span class="col-md-3 col-xs-12"> <input type="text"
								class="form-control" placeholder="주소" readonly
								id="address" name="address">
							</span>
						
						</div>
					</div>

					<div class="panel-footer" id="counselling-footer">
					<!-- <button type="button" class="btn btn-primary pull-right" data-dismiss="modal" >Submit</button> -->
					</div> 
				</div>
				<input type="hidden" value="${sessionScope.user.department_id }" name="department_id">
				<input type="hidden" value="" id="temp_scId">
			</form>
			
			
		</div>
	</div>
</div>
			</div>
			<div class="modal-footer">

			</div>
		</div>

	</div>
	
</div>