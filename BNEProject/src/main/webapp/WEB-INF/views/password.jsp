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

		<form id="myPasswordForm"  method="POST" action="/user/validCheck" role="form" 
			class="form-horizontal" >
			

				<div class="stepContainer" style="height: 131px;">
					<div id="step-7" class="content" style="display: block;">

						<div class="form-group">
							<label class="col-md-2 control-label">Password</label>
							<div class="col-md-2">
								<input type="password" class="form-control" name="password" id="password"
									title="Password must contain at least 6 characters, including UPPER/lowercase and numbers." required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}"
									placeholder="Password">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">New Password</label>
							<div class="col-md-2">
								<input type="password" class="form-control" name="newpassword"
									title="Password must contain at least 6 characters, including UPPER/lowercase and numbers." required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}"
									placeholder="New Password" id="newpassword">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label">Re-New Password</label>
							<div class="col-md-2">
								<input type="password" class="form-control" name="renewpassword" id="renewpassword"
											
									title="Password must contain at least 6 characters, including UPPER/lowercase and numbers." required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" placeholder="Re-New Password" id="renewpassword">
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
				
<!-- <form id="myForm" method="POST" action="...">
		<p>Username: <input id="field_username" title="Username must not be blank and contain only letters, numbers and underscores." type="text" required pattern="\w+" name="username"></p>
		<p>Password: <input id="field_pwd1" title="Password must contain at least 6 characters, including UPPER/lowercase and numbers." type="password" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" name="pwd1"></p>
		<p>Confirm Password: <input id="field_pwd2" title="Please enter the same Password as above." type="password" required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" name="pwd2"></p>
		<p><input type="submit" value="Submit"></p>
	 </form>		 -->

			
		</form>
	</div>
</body>
</html>