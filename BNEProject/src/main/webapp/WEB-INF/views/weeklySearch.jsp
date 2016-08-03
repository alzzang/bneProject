<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

 <!-- PAGE CONTENT -->
<div class="content-frame">
	<!-- START CONTENT FRAME TOP -->
	<div class="content-frame-top">
		<div class="page-title">
			<h2>주간 계획 목록</h2>
		</div>
	</div>
	<!-- END CONTENT FRAME TOP -->
	<div class="content-frame" >
	<br>
		<!-- PAGE CONTENT WRAPPER -->
		<div class="page-content-wrap">

			<div class="row">
				<div class="col-md-12">

					<!-- START DEFAULT DATATABLE -->
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">내 부서 주간 계획</h3>
							<!--                         <ul class="panel-controls">
                            <li><a href="#" class="panel-collapse"><span class="fa fa-angle-down"></span></a></li>
                            <li><a href="#" class="panel-refresh"><span class="fa fa-refresh"></span></a></li>
                            <li><a href="#" class="panel-remove"><span class="fa fa-times"></span></a></li>
                        </ul> -->
						</div>
						<div class="panel-body">
							<table class="table datatable table-striped table-hover"
								id="weeklySearchTable">
								<thead>
									<tr>
										<th>소속</th>
										<th>작성자</th>
										<th>주간계획 ID</th>
										<th>사원 ID</th>
										<th>제목</th>
										<th>기간</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${myDeptWeeklyReportList}" var="weeklyReportSearch">
										<tr>
											<td>${weeklyReportSearch.department_name }</td>
											<td>${weeklyReportSearch.employee_name }</td>
											<td>${weeklyReportSearch.weekly_report_id }</td>
											<td>${weeklyReportSearch.employee_id }</td>
											<td>
												<form
													action="/weeklyReport/detail/${weeklyReportSearch.employee_id}"
													method="POST">
													<input type="hidden" name="weeklyReportId"
														value="${weeklyReportSearch.weekly_report_id}"> <input
														type="submit" value="${weeklyReportSearch.title }">
												</form>
											</td>
											<td>${weeklyReportSearch.date_period }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<!-- END DEFAULT DATATABLE -->
				</div>
			</div>
		</div>
		<!-- PAGE CONTENT WRAPPER -->
	</div>
</div>
<!-- END PAGE CONTENT -->
