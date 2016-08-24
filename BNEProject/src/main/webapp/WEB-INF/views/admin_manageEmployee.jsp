<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<%
String employee_id = "";
String employee_name = "";
int department_id = 0;


%>
<style>
.modal-dialog{
 overflow-y: scroll; max-height:85%;
}
.modal-content{
border-width: 0px !important;
}
</style>
<script>
function EmployeeOfDepartment(departmentId){
	$.ajax({
		type : "POST",
		url : "/admin/teamList",
		data : {
			departmentId:departmentId
		},
		success : function(data) {
			console.log(data);
			for(var i=0;i<data.length;i++){
				var html='';
				if(i==0){
					
					html+='<div class="row"><div class="col-md-12"><div class="col-md-6 col-md-offset-3"><div class="panel panel-default"><div class="panel-body profile">'
                            +'<div class="profile-image"><img src="/user/download/'+data[i].file_position+'" alt="Nadia Ali"></div><div class="profile-data">'
			                +' <div class="profile-data-name">'+data[i].employee_name+'</div><div class="profile-data-title">'+data[i].employee_id+'</div></div><div class="profile-controls">'
			                +'</div></div><div class="panel-body"> <div class="contact-info"><p><small>Position</small><br>'+data[i].position+'</p> <p><small>Mobile</small><br>'+data[i].mobile_phone+'</p>'      
			                +' <p><small>Email</small><br>'+data[i].email+'</p></div></div></div></div>'
			                                                                   			
				}else{
					html+='<div class="col-md-6"><div class="panel panel-default"><div class="panel-body profile">'
                        +'<div class="profile-image"><img src="/user/download/'+data[i].file_position+'" alt="Nadia Ali"></div><div class="profile-data">'
		                +' <div class="profile-data-name">'+data[i].employee_name+'</div><div class="profile-data-title">'+data[i].employee_id+'</div></div><div class="profile-controls">'
		                +'</div></div><div class="panel-body"> <div class="contact-info"><p><small>Position</small><br>'+data[i].position+'</p> <p><small>Mobile</small><br>'+data[i].mobile_phone+'</p>'      
		                +' <p><small>Email</small><br>'+data[i].email+'</p></div></div></div></div>'
		              
				}
				$('.page-content-wrap').append(html);
				
			}
		},
		error:function(){
			alert("수정오류");
		}
	})
}
</script>
<input type="hidden" value=${page } id="page">


<div class="content-frame">
	<!-- START CONTENT FRAME TOP -->
	<div class="content-frame-top">
		<div class="page-title">
			<h2>
				<span class="fa fa-user"></span> 부서 관리
			</h2>


		</div>

		<div class="pull-right">
			<button class="btn btn-danger">
				<span class="fa fa-plus"></span> 부서 추가
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
						<label class="col-md-3 control-label">코드</label>
						<div class="col-md-9">
							<input type="text" class="form-control" id="employee_name" name="employee_name" value=${employee_name}>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label">부서</label>
						<div class="col-md-9">
							<input type="text" class="form-control" id="employee_id" name="employee_id" value=${employee_id}>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">팀장</label>
						<div class="col-md-9">
							<input type="text" class="form-control" id="employee_id" name="employee_id" value=${employee_id}>
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
							<th>부서코드</th>
							<th>부서</th>
							<th>팀장</th>
							<th>연락처</th>
							<th>인원</th>
							<th>수정/삭제</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>1</td>
							<td>Mark</td>
							<td>Otto</td>
							<td>@mdo</td>
							<td><a href="#" data-toggle="modal" data-target="#myModal5" id="modalAdd" onclick="EmployeeOfDepartment(1)">@mdo</a></td>
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
							<td><a href="#" data-toggle="modal" data-target="#myModal5" id="modalAdd" onclick="EmployeeOfDepartment(2)">@mdo</a></td>
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

<div id="myModal5"  class="modal fade" role="dialog">
	<div class="modal-dialog modal-admin">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
			
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title"><span class="fa fa-arrow-circle-o-left"></span> 부서 인원</h4>
				
			</div>
			<div class="modal-body">
			<div class="page-title">
	 <h2>
		
	</h2>  
</div> 
<!-- END PAGE TITLE -->

<!-- PAGE CONTENT WRAPPER -->

<div class="page-content-wrap">
	
	
	
	
	
</div>
			</div>
			<div class="modal-footer">

			</div>
		</div>

	</div>
	
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