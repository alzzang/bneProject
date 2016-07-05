<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- <script src="/js/dailysettings.js"></script> -->
<script>
/* function comma(str) {
    str = String(str);
    alert(str);
    document.getElementById('a').innerHTML = str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
} */



/* $( "#dailyReportSubmit" ).click(function() {
	  $( "#dailyform" ).submit();
}); */
var jsonArray=new Array();
/* function test1(){
	str++;
	alert(str);
	
} */
/* 
function testJSON1(){

	  var o = {};
	   var a = $( "#dailyform" ).serializeArray();
	   $.each(a, function() {
		     if (o[this.name]) {
	           if (!o[this.name].push) {
	               o[this.name] = [o[this.name]];
	           }
	          o[this.name].push(this.value || '');
	       } else {
	           o[this.name] = this.value || '';
	       } 
	   });
	   jsonArray.push(o);
	    var t=JSON.stringify(jsonArray);
	   localStorage.setItem("tt", t);
	   alert(localStorage.getItem("tt"));
}
 */

 
</script>
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
	<div class="content-frame-left" style="height: 1054px;">
		<div class="panel-body">
			<div class="block"></div>

			<div class="page-title">
				<h5>매출 현황</h5>
			</div>
			<table class="table">
				<thead>
					<tr>
						<th>소속</th>
						<td>${employee.department_name}</td>
					</tr>
					<tr>
						<th>성명</th>
						<td>${employee.employee_name}</td>
					</tr>
					<tr>
						<th>매출목표</th>
						<td>${employee.sales_goal}</td>
					</tr>
				</thead>
			</table>


		</div>
	</div>
	<!-- END CONTENT FRAME LEFT -->

	<!-- START CONTENT FRAME BODY -->


	<div class="content-frame-body" style="height: 897px;">
		<div class="row">
			<div class="col-md-12">

				<form class="form-horizontal" action="/dailyReport/writeform"
					id="dailyform" method="post">
					<div class="panel panel-default">
						<div class="panel-heading ui-draggable-handle">
							<h3 class="panel-title">일일 업무 보고 작성</h3>
							<ul class="panel-controls">
								<li><a href="#" class="panel-remove"><span
										class="fa fa-times"></span></a></li>
							</ul>
						</div>
						<div class="panel-body">

							<div class="form-group">
								<label class="col-md-3 col-xs-12 control-label">제목</label>
								<div class="col-md-6 col-xs-12">
									<div class="input-group">
										<span class="input-group-addon"><span
											class="fa fa-pencil"></span></span> <input type="text"
											class="form-control" name="title" required="required">
									</div>
								</div>


							</div>



							<div class="form-group">
								<label class="col-md-3 col-xs-12 control-label">작성일</label>
								<div class="col-md-6 col-xs-12">
									<div class="input-group">
										<span class="input-group-addon"><span
											class="fa fa-calendar"></span></span> <input type="text" 
											class="form-control datepicker"  value="" name="reg_date" onchange="searchSalesGoal(this.value)" required="required" id="reg_date">
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
											id="aaaa" 
											name="sales"  pattern="[0]{1}|[1-9]{1}[0-9]{0,15}" required="required">
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
											type="text" class="form-control" value="0" onchange="computeGuage()" name="before_gauge" id="before_gauge" pattern="[0]{1}|[1-9]{1}[0-9]{0,10}" required="required">
									</div>
								</div>

								<div class="col-md-2 col-xs-12">
									<div class="input-group">
										<span class="input-group-addon">퇴근 시 계기판</span> <input
											type="text" value="0" class="form-control" onchange="computeGuage()" name="after_gauge" id="after_gauge" pattern="[0]{1}|[1-9]{1}[0-9]{0,10}" required="required">
									</div>
								</div>

								<div class="col-md-2 col-xs-12">
									<div class="input-group">
										<span class="input-group-addon">주행 거리</span> <input
											type="text" class="form-control" id="result_guage" pattern="[0-9]{0,10}" required="required">
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

											<%
												
											%>
											<li><a href="#"><span class="fa fa-tag"></span> amet</a></li>
											<li><a href="#"><span class="fa fa-tag"></span>
													rutrum</a></li>
											<li><a href="#"><span class="fa fa-tag"></span> nunc</a></li>
											<li><a href="#"><span class="fa fa-tag"></span>
													tempor</a></li>
											<li><a href="#"><span class="fa fa-tag"></span> eros</a></li>
											<li><a href="#"><span class="fa fa-tag"></span>
													suspendisse</a></li>
											<li><a href="#"><span class="fa fa-tag"></span>
													dolor</a></li>
											<%
												
											%>

											<li><a href="#" onclick="testJSON1()"><span class="fa fa-plus"></span>추가</a></li>
										</ul>
									</div>
								</div>
							</div>
							<jsp:include page="SmartEditor2.jsp"></jsp:include>
							<%-- <jsp:include page="../../SmartEditor2.jsp"></jsp:include> --%>
							<div class="panel-footer">
								<button class="btn btn-primary pull-right"
									id="dailyReportSubmit" onclick="submitContents()">Submit</button>
							</div>

						</div>
						<input type="hidden" name="department_id" value="${sessionScope.user.department_id}">
						<input type="hidden" name="employee_id" value="${sessionScope.user.employee_id}">
						<input type="hidden" id="counsellingJSON" name="counsellingJSON" value="">
				</form>

			</div>
		</div>



	</div>

	<!-- END CONTENT FRAME BODY -->

</div>


<script type="text/javascript">

//function aa(money,goal){
	
	/* var achievementRate=money/goal*100;
	achievementRate=Math.round(achievementRate);
	achievementRate=achievementRate/100;
	
	 *//*achievementRate=Math.round10(achievementRate,-3); */
	
	/* if(achievementRate>100){
		achievementRate=100;
	}
	
	if(achievementRate == 0) {
		var rate=achievementRate.toString()+'%';
		$("#progressCondition").html(rate);
		$("#progressCondition").css('width',0.1);
	}
	else{
		var rate=achievementRate.toString()+'%';
		$("#progressCondition").html(rate);
		  $("#progressCondition").css('width',rate);
	} */
	
	
//}


/* 

function computeGuage(){
	  var before=$('#before_gauge').val();
	  var after=$('#after_gauge').val();
	  var result=after-before;
	  $('#result_guage').val(result);
}


function searchSalesGoal(reg_date) {

	$.ajax({
		type : "POST",
		url : "/dailyReport/dailysales",
		data : {
		 
			reg_date : $('#reg_date').val()
		},
		success : function(data) {
			var result=parseInt(data);
			if(result==-1){
				alert('해당 목표액이 존재하지 않습니다');
				$('#dailyGoal').attr('value',0);
				$('#aaaa').attr('onKeyUp', 'aa(this.value,'+0+')');
			}else{
				$('#dailyGoal').attr('value',result);
				  $('#aaaa').attr('onKeyUp', 'aa(this.value,'+result+')'); 
			}

		}

	})
} */


</script>