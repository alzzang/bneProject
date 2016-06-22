<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

		<form id="myPasswordForm" action="" role="form" method="POST"
			class="form-horizontal" >
			

				<div class="stepContainer" style="height: 131px;">
					<div id="step-7" class="content" style="display: block;">

						<div class="form-group">
							<label class="col-md-2 control-label">Password</label>
							<div class="col-md-2">
								<input type="text" class="form-control" name="password" id="password"  title="Username must not be blank and contain only letters, numbers and underscores." type="text" required pattern="\w+"
									placeholder="Password">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">New Password</label>
							<div class="col-md-2">
								<input type="password" class="form-control" name="newpassword" id="newpassword" title="Password must contain at least 6 characters, including UPPER/lowercase and numbers." type="password" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}"
									placeholder="New Password">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">Re-New Password</label>
							<div class="col-md-2">
								<input type="password" class="form-control" name="renewpassword" id="renewpassword" title="Please enter the same Password as above." type="password" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}"
									placeholder="Re-New Password">
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-2 control-label" ></label>
							<div class="col-md-2">
								<!-- <button class="btn btn-default">Clear Form</button> -->
								<p><input type="submit" class="btn btn-default pull-right" value="Submit"></p>
							</div>
						</div>

					</div>
				</div>

			
		</form>
	</div>
	
                            
</body>
</html>

