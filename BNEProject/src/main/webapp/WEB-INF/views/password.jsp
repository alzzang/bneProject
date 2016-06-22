<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <link rel="stylesheet" type="text/css" id="theme"
	href="/css/theme-default.css" /> -->
<title>Insert title here</title>
</head>
<body>
	<ul class="breadcrumb">
		<li><a href="#">Home</a></li>
		<li class="active">Dashboard</li>
	</ul> 

	<div class="block">

		<form action="/user/validCheck" role="form" method="POST"
			class="form-horizontal" >
			

				<div class="stepContainer" style="height: 131px;">
					<div id="step-7" class="content" style="display: block;">

						<div class="form-group">
							<label class="col-md-2 control-label">Password</label>
							<div class="col-md-2">
								<input type="text" class="form-control" name="password"
									placeholder="Password">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">New Password</label>
							<div class="col-md-2">
								<input type="password" class="form-control" name="newpassword"
									placeholder="New Password" id="newpassword">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">Re-New Password</label>
							<div class="col-md-2">
								<input type="password" class="form-control" name="renewpassword"
									placeholder="Re-New Password" id="renewpassword">
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-2 control-label"></label>
							<div class="col-md-2">
								<!-- <button class="btn btn-default">Clear Form</button> -->
								
								<!-- <a href="/user/validCheck" class="btn btn-default pull-right" id='changebutton'>변경</a> -->
								<input type="text" hidden="true" name="id" value="${sessionScope.user.employee_id}" >
								<input type="submit" value="ok">
							</div>
						</div>

					</div>
				</div>

			
		</form>
	</div>
</body>
</html>