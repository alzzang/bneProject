<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<%
String employee_id = "";
String employee_name = "";
int department_id = 0;


%>

<input type="hidden" value=${page } id="page">


<div class="content-frame">
	<!-- START CONTENT FRAME TOP -->
	<div class="content-frame-top">
		<div class="page-title">
			<h2>
				<span class="fa fa-user"></span> 사원 관리
			</h2>


		</div>

		<div class="pull-right">
			<button class="btn btn-danger">
				<span class="fa fa-plus"></span> 사원 추가
			</button>
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
			
			<form role="form" id="searchForm" class="form-horizontal" action="/admin/employee/${page}">
			<div class="panel-body">
					<div class="form-group">
						<label class="col-md-3 control-label">이름</label>
						<div class="col-md-9">
							<input type="text" class="form-control" id="employee_name" name="employee_name" value=${employee_name}>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label">사번</label>
						<div class="col-md-9">
							<input type="text" class="form-control" id="employee_id" name="employee_id" value=${employee_id}>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label">부서</label>
						<div class="col-md-9">
							<select class="form-control select" style="display: none;" name="department_id" id="department_id">
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
							<select class="form-control select" style="display: none;" id="position" name="position">
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
					<button type="submit" class="btn btn-primary" onclick="formSubmit()">
						<span class="glyphicon glyphicon-search"></span> SEARCH
					</button>
				</div>

			</div>
			<!-- </form> -->
		</div>
	</div>
	<!-- END CONTENT FRAME LEFT -->

	<!-- START CONTENT FRAME BODY -->
	<div class="content-frame-body" style="height: 897px;">

		<div class="panel panel-default">
			<div class="panel-heading ui-draggable-handle">
				<h3 class="panel-title">검색 결과</h3>
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
						<tr>
							<td>1</td>
							<td>Mark</td>
							<td>Otto</td>
							<td>@mdo</td>
							<td>@mdo</td>
							<td>@mdo</td>
							<td>
								<button class="btn btn-default btn-rounded btn-sm">
									<span class="fa fa-pencil"></span>
								</button>
								<button class="btn btn-danger btn-rounded btn-sm"
									onclick="delete_row('trow_1');">
									<span class="fa fa-times"></span>
								</button>
							</td>
						</tr>
						<tr>
							<td>2</td>
							<td>Jacob</td>
							<td>Thornton</td>
							<td>@fat</td>
							<td>@mdo</td>
							<td>@mdo</td>
							<td>
								<button class="btn btn-default btn-rounded btn-sm">
									<span class="fa fa-pencil"></span>
								</button>
								<button class="btn btn-danger btn-rounded btn-sm"
									onclick="delete_row('trow_1');">
									<span class="fa fa-times"></span>
								</button>
							</td>
						</tr>
						<tr>
							<td>3</td>
							<td>Larry</td>
							<td>the Bird</td>
							<td>@twitter</td>
							<td>@mdo</td>
							<td>@mdoㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴ</td>
							<td>
								<button class="btn btn-default btn-rounded btn-sm">
									<span class="fa fa-pencil"></span>
								</button>
								<button class="btn btn-danger btn-rounded btn-sm"
									onclick="delete_row('trow_1');">
									<span class="fa fa-times"></span>
								</button>
							</td>
						</tr>
					</tbody>
				</table>

				<div class=" pull-right">
					<ul class="pagination">
						<li class="disabled"><a href="#">«</a></li>
						<li class="active"><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#">»</a></li>
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
<script>

	function formSubmit() {
		var form = document.createElement("form");
		form.setAttribute("action", "/admin/employee/1");

		if ($("#employee_id").val().trim() != "") {
			var hiddenField = document.createElement("input");
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", "employee_id");
			hiddenField.setAttribute("value", $("#employee_id").val());
			form.appendChild(hiddenField);
		}
		if ($("#employee_name").val().trim() != "") {
			var hiddenField = document.createElement("input");
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", "employee_name");
			hiddenField.setAttribute("value", $("#employee_name").val());
			form.appendChild(hiddenField);
		}
		if ($("#department_id").val().trim() != "*") {
			var hiddenField = document.createElement("input");
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", "department_id");
			hiddenField.setAttribute("value", $("#department_id").val());
			form.appendChild(hiddenField);
		}
		if ($("#position").val().trim() != "*") {
			var hiddenField = document.createElement("input");
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", "position");
			hiddenField.setAttribute("value", $("#position").val());
			form.appendChild(hiddenField);
		}
		document.body.appendChild(form);
		form.submit();
	}
</script>