<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%-- 	<div class="panel-body">
		<h3>
			<span class="fa fa-download"></span> Mini dropzone
		</h3>
		<p>
			Add form with class
			<code>dropzone dropzone-mini</code>
			to get mini dropzone box
		</p>
		<form class="dropzone dropzone-mini dz-clickable" method="post"
			id="myDropzone">
			<div class="dz-default dz-message">
				<span>Drop files here to upload</span>
			</div>
			<input type="text" hidden="true" name="id" value="${sessionScope.user.employee_id}" >
			
		</form>
		<input type="button" value="저장" id="submitButton">
	</div> --%>
	<!-- Trigger the modal with a button -->


<a href="#" data-toggle="modal" data-target="#myModal">Open Modal</a>

<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Modal Header</h4>
      </div>
      <div class="modal-body">
        <div class="panel-body">
		<h3>
			<span class="fa fa-download"></span> Mini dropzone
		</h3>
		<p>
			Add form with class
			<code>dropzone dropzone-mini</code>
			to get mini dropzone box
		</p>
		<form class="dropzone dropzone-mini dz-clickable" method="post"
			id="myDropzone">
			<div class="dz-default dz-message">
				<span>Drop files here to upload</span>
			</div>
			<input type="text" hidden="true" name="id" value="${sessionScope.user.employee_id}" >
			
		</form>
		<input type="button" value="저장" id="submitButton">
	</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>
</body>
</html>