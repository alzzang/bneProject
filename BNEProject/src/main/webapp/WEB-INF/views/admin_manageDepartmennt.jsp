<%@page import="kr.co.bne.dto.EmployeeDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<%
String employee_id = "";
String employee_name = "";
int department_id = 0;

int currentPage = (Integer)request.getAttribute("currentPage");
List<EmployeeDTO> employeeList = (List<EmployeeDTO>)request.getAttribute("employeeList");

int startIdx = (Integer)request.getAttribute("startIdx");
int endIdx = (Integer)request.getAttribute("endIdx");
int totalPageNum = (Integer)request.getAttribute("totalPageNum");
%>


<div class="content-frame">
	<!-- START CONTENT FRAME TOP -->
	<div class="content-frame-top">
		<div class="page-title">
			<h2>
				<span class="fa fa-user"></span> 사원 관리
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
				<h3 class="panel-title">
					<span class="glyphicon glyphicon-filter"></span> 검색 필터
				</h3>
			</div>

			<form role="form" id="searchForm" class="form-horizontal"
				action="/admin/employee/1">
				<div class="panel-body">
					<div class="form-group">
						<label class="col-md-3 control-label">이름</label>
						<div class="col-md-9">
							<input type="text" class="form-control" id="employee_name"
								name="employee_name" value=${employee_name}>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label">사번</label>
						<div class="col-md-9">
							<input type="text" class="form-control" id="employee_id"
								name="employee_id" value=${employee_id}>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label">부서</label>
						<div class="col-md-9">
							<select class="form-control select" style="display: none;"
								name="department_id" id="department_id">
								<option value="*">전체</option>
								<option value="1">영업 3팀</option>
								<option>Option 2</option>
								<option>Option 3</option>
								<option>Option 4</option>
								<option>Option 5</option>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label">직책</label>
						<div class="col-md-9">
							<select class="form-control select" style="display: none;"
								id="position" name="position">
								<option value="*">전체</option>
								<option>Option 1</option>
								<option>Option 2</option>
								<option>Option 3</option>
								<option>Option 4</option>
								<option>Option 5</option>
							</select>
						</div>
					</div>


				</div>

				<div class="panel-heading ui-draggable-handle">

					<div class="pull-right">
						<button type="submit" class="btn btn-primary">
							<span class="glyphicon glyphicon-search"></span> SEARCH
						</button>
					</div>
				</div>
			</form>
			<!-- </form> -->
		</div>
	</div>
	<!-- END CONTENT FRAME LEFT -->

	<!-- START CONTENT FRAME BODY -->
	<div class="content-frame-body" style="height: 897px;">

		<div class="panel panel-default">
			<div class="panel-heading ui-draggable-handle">
				<h3 class="panel-title">검색 결과</h3>
				<div class="pull-right">
					<button class="btn btn-danger" onclick="visibleAddForm()" id="addEmployeeFormButton">
						<span class="fa fa-plus"></span> 사원 추가
					</button>
				</div>
			</div>

			<div class="panel-body">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>사번</th>
							<th>이름</th>
							<th>부서</th>
							<th>직책</th>
							<th>연락처</th>
							<th>email</th>
							<th>수정/삭제</th>
						</tr>
					</thead>
					<tbody>
						<tr id="employeeAddForm">
						</tr>

						<%
													for (EmployeeDTO employee : employeeList) {
												%>
						<tr>
							<td><%=employee.getEmployee_id() %></td>
							<td><%=employee.getEmployee_name() %></td>
							<td><%=employee.getDepartment_name() %></td>
							<td>
								<%-- <%=employee.getPosition() %> --%> position
							</td>
							<td><%=employee.getMobile_phone() %></td>
							<td><%=employee.getEmail() %></td>
							<td>
								<ul class="panel-controls">
									<li><a href="#"><span
											class="fa fa-pencil"></span></a></li>
									<li><a href="#"><span class="glyphicon glyphicon-trash"></span></a></li>
								</ul>
							</td>
						</tr>
						<%} %>

					</tbody>
				</table>

				<div class="panel-footer">
					<ul class="pagination pagination-sm pull-right">

						<%if(startIdx != 0) { %>
						<%if(startIdx > 1) {%>
						<li><a href="/admin/employee/<%=startIdx-1 %>${optionQuery}">«</a></li>
						<%} %>
						<%for(int i=startIdx; i<=endIdx; i++) { %>
						<%if(currentPage == i) {%>
						<li class="active">
							<%}else { %>
						
						<li style="cursor: pointer;">
							<%} %> <a href="/admin/employee/<%=i %>${optionQuery}"><%=i %></a>
						</li>
						<%} %>

						<%if(totalPageNum > endIdx) {%>
						<li><a href="/admin/employee/<%=endIdx+1 %>${optionQuery}">»</a></li>
						<%} %>
						<%} %>
					</ul>
				</div>

			</div>
		</div>

	</div>
	<!-- END CONTENT FRAME BODY -->
</div>

<script type="text/javascript"
	src="/js/plugins/bootstrap/bootstrap-select.js"></script>
<script type="text/javascript"
	src="/js/plugins/datatables/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="/js/admin_managerDepartment.js"></script>