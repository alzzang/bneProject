<%@page import="kr.co.bne.dto.DepartmentDTO"%>
<%@page import="kr.co.bne.dto.EmployeeDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<%
	String employee_id = "";
	String employee_name = "";
	int department_id = 0;

	int currentPage = (Integer) request.getAttribute("currentPage");
	List<EmployeeDTO> employeeList = (List<EmployeeDTO>) request.getAttribute("employeeList");
	List<DepartmentDTO> departmentList = (List<DepartmentDTO>) request.getAttribute("departmentList");

	int startIdx = (Integer) request.getAttribute("startIdx");
	int endIdx = (Integer) request.getAttribute("endIdx");
	int totalPageNum = (Integer) request.getAttribute("totalPageNum");
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
			<button class="btn btn-danger" onclick="visibleAddForm()"
				id="addEmployeeFormButton">
				<span class="fa fa-plus"></span> 사원 추가
			</button>
		</div>
	</div>
	<!-- END CONTENT FRAME TOP -->

	<!-- START CONTENT FRAME LEFT -->
	<div class="content-frame-left" style="height: 837px;">

		<div class="panel panel-colorful">
			<div class="panel-heading ui-draggable-handle">
				<h3 class="panel-title">
					<span class="glyphicon glyphicon-filter"></span> 검색 필터
				</h3>
				<div class="pull-right">
					<a href="/admin/employee/1" class="btn btn-danger"> <span
						class="fa fa-eraser"></span> 초기화
					</a>
				</div>
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
							<select class="form-control select" name="department_id"
								id="department_id">
								<option value="*">전체</option>
								<%
									for (DepartmentDTO department : departmentList) {
								%>
								<option value="<%=department.getDepartment_id()%>"><%=department.getDepartment_name()%></option>
								<%
									}
								%>
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
		<!-- start 사원 추가 폼 -->
		<div class="panel panel-danger" style="display: none;"
			id="employeeAddForm">
			<div class="panel-heading ui-draggable-handle">
				<h3 class="panel-title">사원 추가</h3>
				<ul class="panel-controls">
					<li><a onclick="addInputElement()"><span
							class="fa fa-plus"></span></a></li>
					<li><a onclick="removeInputElement()"><span
							class="fa fa-minus"></span></a></li>
				</ul>
			</div>

			<div class="panel-body">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>이름</th>
							<th>부서</th>
							<th>연락처</th>
							<th>email</th>
						</tr>
					</thead>
					<tbody id="employeeAddForm-body">
						<tr style="display: none;">
							<td><input type="text" class="form-control"
								attr="employee_name" required></td>
							<td><select class="form-control" attr="department_id">
									<%
											for (DepartmentDTO department : departmentList) {
										%>
									<option value="<%=department.getDepartment_id()%>"><%=department.getDepartment_name()%></option>
									<%
											}
										%>
							</select></td>
							<td><input type="text" class="form-control"
								attr="mobile_phone" placeholder="하이픈(-) 제외" required></td>
							<td><div class="input-group">
									<span class="input-group-addon">@</span> <input type="text"
										class="form-control" attr="email" required>
								</div></td>
						</tr>
					</tbody>
				</table>
			</div>

			<div class="panel-heading ui-draggable-handle">

				<div class="pull-right">
					<button onclick="submitAddForm()" class="btn btn-danger">
						<span class="fa fa-check"></span> 등록
					</button>
				</div>
			</div>
		</div>
		<!-- end 사원 추가 폼 -->



		<!-- start 검색 결과 폼 -->
		<div class="panel panel-colorful">
			<div class="panel-heading ui-draggable-handle">
				<h3 class="panel-title">검색 결과</h3>
			</div>

			<div class="panel-body">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th style="width: 10%;">사번</th>
							<th>이름</th>
							<th>부서</th>
							<th>직책</th>
							<th>연락처</th>
							<th>email</th>
							<th style="width: 7%;">수정/삭제</th>
						</tr>
					</thead>
					<tbody id="employeeSearchResultForm-body">


						<%for (EmployeeDTO employee : employeeList) {%>
						<tr style="text-align: center;">
							<td><%=employee.getEmployee_id()%></td>
							<td><%=employee.getEmployee_name()%></td>
							<td department_id=<%=employee.getDepartment_id() %>><%=employee.getDepartment_name()%></td>
							<td><%=employee.getPosition().equals("employee") ? "팀원" : "팀장"%></td>
							<td><%=employee.getMobile_phone()%></td>
							<td><%=employee.getEmail()%></td>
							<td>
								<ul class="panel-controls">
									<li><a class="editButton mb-control"
										onclick="openEditForm()"><span class="fa fa-pencil"></span></a></li>
									<li><a
										onclick="removeEmployee('<%=employee.getEmployee_id()%>')"><span
											class="glyphicon glyphicon-trash"></span></a></li>
								</ul>
							</td>
						</tr>

						<%}%>
					</tbody>
				</table>
			</div>

			<div class="panel-heading ui-draggable-handle">
				<ul class="pagination pagination-sm pull-right">

					<%
						if (startIdx != 0) {
					%>
					<%
						if (startIdx > 1) {
					%>
					<li><a href="/admin/employee/<%=startIdx-1 %>${optionQuery}">«</a></li>
					<%
						}
					%>
					<%
						for (int i = startIdx; i <= endIdx; i++) {
					%>
					<%
						if (currentPage == i) {
					%>
					<li class="active">
						<%
							} else {
						%>
					
					<li style="cursor: pointer;">
						<%
							}
						%> <a href="/admin/employee/<%=i %>${optionQuery}"><%=i%></a>
					</li>
					<%
						}
					%>

					<%
						if (totalPageNum > endIdx) {
					%>
					<li><a href="/admin/employee/<%=endIdx+1 %>${optionQuery}">»</a></li>
					<%
						}
					%>
					<%
						}
					%>
				</ul>
			</div>
		</div>
		<!-- end 검색 결과 폼 -->
	</div>

	<!-- START MODAL -->
	<div class="modal fade" id="editModal" tabindex="-1" aria-hidden="true">
		<div class="modal-dialog">
			<form class="form-horizontal" id="editForm" name="editForm" method="POST"
				onsubmit="return submitEditForm()">
				<div class="panel panel-danger">
					<div class="panel-heading ui-draggable-handle">
						<h3 class="panel-title">사원 정보 수정</h3>
					</div>
					<div class="panel-body">
						<div class="form-group">
							<div class="col-md-10">
								<div class="input-group">
									<span class="input-group-addon">사번</span> <input type="text" name="employee_id"
										class="form-control" readonly="readonly">
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-10">
								<div class="input-group">
									<span class="input-group-addon">이름</span> <input
										attr="employee_name" type="text" class="form-control" name="employee_name"
										placeholder="Left side addon">
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-10">
								<div class="input-group">
									<span class="input-group-addon">부서</span> <select
										attr="department_id" class="form-control" attr="department_id" name="department_id">
										<%
											for (DepartmentDTO department : departmentList) {
										%>
										<option value="<%=department.getDepartment_id()%>"><%=department.getDepartment_name()%></option>
										<%
											}
										%>
									</select>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-10">
								<div class="input-group">
									<span class="input-group-addon">연락처</span> <input
										attr="mobile_phone" type="text" class="form-control" name="mobile_phone"
										placeholder="Left side addon">
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-10">
								<div class="input-group">
									<span class="input-group-addon">이메일</span> <input attr="email" name="email"
										type="text" class="form-control" placeholder="Left side addon">
								</div>
							</div>
						</div>
					</div>
					<div class="panel-footer">
						<button class="btn btn-primary pull-right">Submit</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!-- END MODAL -->
	<!-- END CONTENT FRAME BODY -->
</div>




<script type="text/javascript"
	src="/js/plugins/bootstrap/bootstrap-select.js"></script>
<script type="text/javascript"
	src="/js/plugins/datatables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="/js/admin_managerDepartment.js"></script>