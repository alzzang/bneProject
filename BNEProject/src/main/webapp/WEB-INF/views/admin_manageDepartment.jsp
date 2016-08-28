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

					<div class="form-group">
						<label class="col-md-3 control-label">직책</label>
						<div class="col-md-9">
							<select class="form-control select" id="position" name="position">
								<option value="*">전체</option>
								<option value="manager">팀장</option>
								<option value="employee">팀원</option>
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
		<div class="panel panel-danger" style="display: none;" id="employeeAddForm">
			<div class="panel-heading ui-draggable-handle">
				<h3 class="panel-title">사원 추가</h3>
				<ul class="panel-controls">
					<li><a onclick="addInputElement()"><span
							class="fa fa-plus"></span></a></li>
							<li><a onclick="addInputElement()"><span
							class="fa fa-minus"></span></a></li>
				</ul>
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
						</tr>
					</thead>
					<tbody id="employeeAddForm-body">
						<tr style="display:none;" class="dummyElement">
							<td><input type="text" class="form-control"></td>
							<td><input type="text" class="form-control"></td>
							<td>					
                                            <select class="form-control">
                                                <%
											for (DepartmentDTO department : departmentList) {
										%>
										<option value="<%=department.getDepartment_id()%>"><%=department.getDepartment_name()%></option>
										<%
											}
										%>
                                            </select>
							</td>
							<td>
									<select class="form-control">
									<option value="employee">팀원</option>
										<option value="manager">팀장</option>										
									</select>
								</td>
							<td><input type="text" class="form-control"></td>
							<td><div class="input-group">
									<input type="text" class="form-control"> <span
										class="input-group-addon"><img
										src="data:image/svg+xml;utf8;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pgo8IS0tIEdlbmVyYXRvcjogQWRvYmUgSWxsdXN0cmF0b3IgMTguMC4wLCBTVkcgRXhwb3J0IFBsdWctSW4gLiBTVkcgVmVyc2lvbjogNi4wMCBCdWlsZCAwKSAgLS0+CjwhRE9DVFlQRSBzdmcgUFVCTElDICItLy9XM0MvL0RURCBTVkcgMS4xLy9FTiIgImh0dHA6Ly93d3cudzMub3JnL0dyYXBoaWNzL1NWRy8xLjEvRFREL3N2ZzExLmR0ZCI+CjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB4bWxuczp4bGluaz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94bGluayIgdmVyc2lvbj0iMS4xIiBpZD0iQ2FwYV8xIiB4PSIwcHgiIHk9IjBweCIgdmlld0JveD0iMCAwIDQ5My4zMzIgNDkzLjMzMiIgc3R5bGU9ImVuYWJsZS1iYWNrZ3JvdW5kOm5ldyAwIDAgNDkzLjMzMiA0OTMuMzMyOyIgeG1sOnNwYWNlPSJwcmVzZXJ2ZSIgd2lkdGg9IjE2cHgiIGhlaWdodD0iMTZweCI+CjxnIGlkPSJYTUxJRF83OF8iPgoJPGcgaWQ9IlhNTElEXzc5XyI+CgkJPGcgaWQ9IlhNTElEXzgwXyI+CgkJCTxwYXRoIGlkPSJYTUxJRF84MV8iIGQ9Ik0zNTIuNzMxLDQ1MC45NTZjMy4wMzcsOS43ODktMi4wNDQsMjAuMjEzLTExLjYxNiwyMy44NzhjLTM1LjgxMSwxMy43MDktNjkuMjk0LDE4LjQ5OC0xMTIuODczLDE4LjQ5OCAgICAgYy0xMTcuNjYzLDAtMjIxLjE0Ny04NC4zNDctMjIxLjE0Ny0yMjMuMjc1QzcuMDk1LDEyNS40NiwxMTIuMDAzLDAsMjcyLjIsMGMxMjQuNzM1LDAsMjE0LjAzNiw4NS43NjQsMjE0LjAzNiwyMDQuODQ1ICAgICBjMCwxMDMuNDgzLTU4LjEyLDE2OC42OTMtMTM0LjY2LDE2OC42OTNjLTMzLjMyNywwLTU3LjQxOS0xNy4wMDctNjAuOTU1LTU0LjU4MmgtMS40MjRjLTIxLjk2MywzNi4xNS01My44Niw1NC41ODItOTEuNDMsNTQuNTgyICAgICBjLTQ2LjA4MiwwLTc5LjM3Ni0zNC4wMjItNzkuMzc2LTkyLjE0MmMwLTg2LjQ3NSw2My43NjktMTY1LjE0NywxNjUuODQxLTE2NS4xNDdjMTguNjkxLDAsMzguOSwyLjc5NSw1NS45MDksNy4xNzIgICAgIGMxNi40MjUsNC4yMjcsMjYuODYxLDIwLjIzNiwyNC4xNDcsMzYuOTc4bC0xNi45NzIsMTA0LjY5NWMtNy4wNzIsNDEuODE4LTIuMTEyLDYwLjk1MywxNy43MjEsNjEuNjY2ICAgICBjMzAuNDc1LDAuNzExLDY4Ljc2Mi0zOC4yNzIsNjguNzYyLTExOS43ODZjMC05Mi4xNS01OS41NDMtMTYzLjczMi0xNjkuNDEtMTYzLjczMmMtMTA4LjQ0NCwwLTIwMy40MjgsODUuMDUxLTIwMy40MjgsMjIwLjQzNiAgICAgYzAsMTE4LjM2OSw3NS44MzQsMTg1LjcwOCwxODEuNDQ4LDE4NS43MDhjMjcuNDg5LDAsNTYuMjAzLTQuNTEsODAuODUzLTEzLjIxNGM1Ljg2My0yLjA3LDEyLjMxNS0xLjY2MiwxNy44NzIsMS4xMjYgICAgIGM1LjU1OCwyLjc4OCw5Ljc1Myw3LjcxNiwxMS41OTUsMTMuNjU1TDM1Mi43MzEsNDUwLjk1NnogTTI5My45NTQsMTgxLjM2MmMwLjUzNC0zLjYyNi0xLjg5Mi03LjAyNy01LjQ5Ni03LjY5NiAgICAgYy00LjQxMy0wLjgxOS05LjUzNi0xLjQyNi0xNS41NzMtMS40MjZjLTQ2Ljc3MSwwLTgzLjYxNyw0Ni4wNzYtODMuNjE3LDEwMC42NTNjMCwyNi45NCwxMi4wMzcsNDMuOTQ2LDM1LjQyMyw0My45NDYgICAgIGMyNi4yMzEsMCw1My44NzYtMzMuMzEsNjAuMjY1LTc0LjQyNUwyOTMuOTU0LDE4MS4zNjJ6IiBmaWxsPSIjRkZGRkZGIi8+CgkJPC9nPgoJPC9nPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+Cjwvc3ZnPgo="></span><input
										type="text" class="form-control">
								</div></td>
						</tr>
					</tbody>
				</table>
			</div>

			<div class="panel-heading ui-draggable-handle">

				<div class="pull-right">
					<button type="submit" class="btn btn-danger">
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
					<tbody>
						<%for (EmployeeDTO employee : employeeList) {%>
						<tr style="text-align: center;">
							<td><%=employee.getEmployee_id()%></td>
							<td><%=employee.getEmployee_name()%></td>
							<td><%=employee.getDepartment_name()%></td>
							<td><%=employee.getPosition().equals("employee") ? "팀원" : "팀장"%>
							</td>
							<td><%=employee.getMobile_phone()%></td>
							<td><%=employee.getEmail()%></td>
							<td>
								<ul class="panel-controls">
									<li><a href="#"><span class="fa fa-pencil"></span></a></li>
									<li><a
										href="/admin/employee/delete/<%=employee.getEmployee_id()%>"><span
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
	<!-- END CONTENT FRAME BODY -->
</div>

<script type="text/javascript"
	src="/js/plugins/bootstrap/bootstrap-select.js"></script>
<script type="text/javascript"
	src="/js/plugins/datatables/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="/js/admin_managerDepartment.js"></script>