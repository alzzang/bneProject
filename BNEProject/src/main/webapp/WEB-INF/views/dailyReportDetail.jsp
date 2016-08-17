<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="/js/dailysettings.js"></script>

 <script>
$(document).ready(function(){

	var a = ${dailyReport.drsales};
	var b = ${dailyReport.wpsales}
	changeProgress(a,b);
	
});


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
						<td>${sessionScope.employee.department_name}</td>
					</tr>
					<tr>
						<th>성명</th>
						<td>${sessionScope.employee.employee_name}</td>
					</tr>
					<tr>
						<th>매출목표</th>
						<td id="goalValue">${sessionScope.employee.sales_goal}</td>
					</tr>
				</thead>
			</table>


		</div>
	</div>
	
	<script>
	$('#goalValue').text(addComma('${sessionScope.employee.sales_goal}'));
	</script>
	<!-- END CONTENT FRAME LEFT -->

	<!-- START CONTENT FRAME BODY -->
	<div class="content-frame-body" style="height: 897px;">
		<c:if test="${url ne null}">
		<input type="hidden" value=${params } id="params">
		<input type="hidden" value="${url }" id="url">
		</c:if>
		<c:if test="${url eq null}">
		<input type="hidden" value="{}" id="params">
		<input type="hidden" value="/dailyReport/main/employee/${sessionScope.employee.employee_id}" id="url">
		</c:if>
		<div class="form-horizontal" >
			<div class="panel panel-default">
				<div class="panel-heading ui-draggable-handle">
					<div class="pull-left">
						<img src="/user/download/${dailyReport.file_position}/" class="panel-title-image"
							alt="John Doe">
						<h3 class="panel-title">
							${dailyReport.employee_name}<!-- <small>johndoe@domain.com</small> -->
						</h3>
					</div>
					
				 
				 <c:if test="${0 eq dailyReport.approval_flag && user.position eq 'manager'}">
					 <div class="pull-right" id="approvalDiv">
						<button class="btn btn-success pull-right" onclick="approvalDaily()">
					<span class="fa fa-check" ></span> 승인 
					
					</button>
					</div>
					</c:if>
					
					<c:if test="${user.employee_id eq dailyReport.employee_id &&dailyReport.approval_flag eq 0}">
					<div class="pull-right">
						<button class="btn btn-success pull-right" onclick="deleteDaily(${dailyReport.daily_report_id})" >
					<span class="fa fa-check" ></span> 삭제
					</button>
					</div>
					
					<div class="pull-right">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
					
					
					<div class="pull-right">
						<button class="btn btn-success pull-right" onclick="updateDaily(${dailyReport.daily_report_id})" >
					<span class="fa fa-check" ></span> 수정
					</button>
					
					</div>
					
					
					</c:if>
					<div class="pull-right">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
					<div class="pull-right">
						<c:if test="${page eq null}">
						<button class="btn btn-success pull-right" onclick="viewList('list',1)" >
						</c:if>
						<c:if test="${page ne null}">
						<button class="btn btn-success pull-right" onclick="viewList('list',${page})" >
						</c:if>
					<span class="fa fa-check" ></span> 목록
					</button>
					
					</div>
					<%-- 
	
						 <div class="pull-right">
						<button class="btn btn-success pull-right">
					<span class="fa fa-check"></span> 승인 ${user.position} ${dailyReport.approval_flag}
					</button>
					</div> --%>
					
				</div>

			<div></div> 
				<div class="panel-body">
					<div class="form-group">
						<label class="col-md-3 col-xs-12 control-label">제목</label>
						<div class="col-md-6 col-xs-12">
							<div class="input-group">
								<span class="input-group-addon"><span
									class="fa fa-pencil"></span></span> <input type="text"
									class="form-control" readonly="readonly" style="cursor: default;" style="cursor: default;" value="${dailyReport.title}">
							</div>
						</div>


					</div>



					<div class="form-group">
						<label class="col-md-3 col-xs-12 control-label">작성일</label>
						<div class="col-md-6 col-xs-12">
							<div class="input-group">
								<span class="input-group-addon"><span
									class="fa fa-calendar"></span></span> <input type="text"
									class="form-control " value="${dailyReport.reg_date}" readonly="readonly" style="cursor: default;" style="cursor: default;">
							</div>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 col-xs-12 control-label">매출액</label>
						<div class="col-md-6 col-xs-12">
							<div class="input-group">
								<span class="input-group-addon" style="padding-bottom: 10px;"><span
									class="fa fa-won"></span></span> <input type="text"
									class="form-control" id="aaaaa" style="cursor: default;" style="cursor: default;" readonly="readonly" value="${dailyReport.drsales}"> 
									<span class="progress" > <span
									class="progress-bar progress-bar-danger" role="progressbar"
									aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"
									style="width: 0.01%" id="progressCondition">0%</span>
								</span>

							</div>
						</div>


					</div>


					<div class="form-group">
						<label class="col-md-3 col-xs-12 control-label">주행</label>
						<div class="col-md-2 col-xs-12">
							<div class="input-group">
								<span class="input-group-addon">출근 시 계기판</span> <input
									type="text" class="form-control" readonly="readonly" style="cursor: default;"value="${dailyReport.before_gauge}" >
							</div>
						</div>

						<div class="col-md-2 col-xs-12">
							<div class="input-group">
								<span class="input-group-addon">퇴근 시 계기판</span> <input
									type="text" class="form-control" readonly="readonly" style="cursor: default;" value="${dailyReport.after_gauge}"> 
							</div>
						</div>

						<div class="col-md-2 col-xs-12">
							<div class="input-group">
								<span class="input-group-addon">주행 거리</span> <input type="text"
									class="form-control" readonly="readonly" style="cursor: default;" style="cursor: default;" value="${dailyReport.after_gauge - dailyReport.before_gauge}" id="drivingDistance">
									
							</div>
						</div>
					</div>
				
					<div class="form-group">
						<label class="col-md-3 col-xs-12 control-label">상담 일지</label>
						<div class="col-md-6 col-xs-12">
							<div class="input-group">
								<ul class="list-tags">
								<c:forEach var="result" items="${counselList}">
								<li><a href="#" data-toggle="modal" data-target="#myModal3" onclick="tagDetail('${result.department_name}','${result.employee_name }','${result.reg_date}','${result.title}','${result.client_id}','${result.client_name}','${result.representative}','${result.sec_client_name}','${result.address}','${result.content}')"><span class="fa fa-tag"></span>${result.title }</a></li>
								
								</c:forEach>
 
                                    </ul>
							</div>
						</div>
					</div>

					<br> <br> 
					<div class="col-md-6 col-md-offset-3" id="dailyReportContent" >
						${dailyReport.content}
						<div class="note-statusbar">
							<div class="note-resizebar">
								<div class="note-icon-bar"></div>
								<div class="note-icon-bar"></div>
								<div class="note-icon-bar"></div>
							</div>
						</div>
						
						<br><br>
						<!-- 팀원이고 커멘트가 달리지 않았을 때(빈화면)
						     팀원이고 커멘트가  달렸을 때(작성된 멘트 보여주기)
						     팀장이고 커멘트가 달리지 않았을 때(input type)
						     팀장이고 승인이 되었을 때(작성된 멘트 및 수정버튼 생성)

"${0 eq dailyReport.approval_flag && user.position eq 'manager'}"
						 -->
						 <div id="commentDiv">
						 
						 <c:choose>
						 <c:when test="${dailyReport.manager_comment eq null && user.position eq 'employee'}">
						 </c:when>
						 <c:when test="${dailyReport.manager_comment ne null && user.position eq 'employee'}">
						 <div class="timeline-body comments">
                                 <div class="comment-item">
                                          <img src="/user/download/${dailyReport.manager_file_position}/">
                                             <p class="comment-head">
                                                  <a href="#">${dailyReport.manager_name}</a>
                                             </p>
                                             ${dailyReport.manager_comment}
                                 </div>                                            
                         </div>
						 </c:when>
						 <c:when test="${dailyReport.manager_comment eq null && user.position eq 'manager'}">
						 <div class="form-group push-up-20">
							<div class="col-md-12">
								<div class="input-group">
									<input class="form-control" placeholder="팀장 의견" id="managerComment">
									<span class="input-group-addon"><a href="#" onclick="insertComment()"><span
										class="fa fa-pencil"></span></a></span>
								</div>
							</div>
						 </div>
						 </c:when>
						 <c:when test="${dailyReport.manager_comment ne null && user.position eq 'manager'}">
						<div class="timeline-body comments">
                                 <div class="comment-item">
                                          <img src="/user/download/${dailyReport.manager_file_position}/">
                                             <p class="comment-head">
                                                  <a href="#">${dailyReport.manager_name}</a> <a href="#" class="pull-right" onclick="deleteComment()">삭제</a><span class="pull-right">&nbsp;|&nbsp;</span><a href="#" class="pull-right" onclick="modifyComment('${dailyReport.manager_comment}')">수정</a> 
                                                  <!-- <span class="text-muted">@bradpitt</span> -->
                                             </p>
                                             ${dailyReport.manager_comment}
                                               <!--  <small class="text-muted">10h ago</small> -->
                                 </div>                                            
                         </div>
                         </c:when>
						 </c:choose>
						 </div>
						 
						</div>
						
						
					</div>
				</div>


	

		</div>
		
		<input type="hidden" value="${dailyReport.daily_report_id }" id="report_id">
		<input type="hidden" value="${dailyReport.manager_name }" id="manager_name">
		<input type="hidden" value="${dailyReport.manager_file_position }" id="manager_file_position">
	</div>

</div>


<!-- END CONTENT FRAME BODY -->
<div id="myModal3"  class="modal fade" role="dialog">
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
			
<div class="panel-body">
	<p>
		<code>상담일지</code>
	
	</p>
	<table class="table">
		<thead>
			<tr>
				<th>소속</th>
				<td id="counsel_department"></td>
				<th>성명</th>
				<td id="counsel_empname"></td>
				<th>작성일</th>
				<td id="counsel_regdate"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td id="counsel_title"></td>
			</tr>
			<tr>
				<th>고객코드</th>
				<td id="counsel_code"></td>
				<th>고객명</th>
				<td id="counsel_departmentname"></td>
				<th>대표자</th>
				<td id="counsel_representative"></td>
			</tr>
			<tr>
				<th>2차거래선</th>
				<td id="counsel_sec_client"></td>
				<th>주소</th>
				<td id="counsel_address"></td>
			</tr>
			<tr>
			<th class="col-md-1">상담내역</th>
	<td colspan="5" ><div id="counsel_content"></div></td>
	</tr>
	
		</thead>
	</table>
</div>
		</div>
	</div>
</div>
			</div>
			<div class="modal-footer">

			</div>
		</div>

	</div>
	
</div>
