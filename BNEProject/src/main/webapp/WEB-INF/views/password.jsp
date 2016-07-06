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
			
		</form>
	</div>
<script type="text/javascript">
var checkPassword = function(str) {
	var re = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$/;
	return re.test(str);
};

var checkForm = function(e) {

	if (this.password.value == "") {
		alert("Error: password cannot be blank!");
		this.password.focus();
		e.preventDefault(); // equivalent to return false
		return;
	}
	re = /^\w+$/;
	if (!re.test(this.password.value)) {
		alert("Error: password must contain only letters, numbers and underscores!");
		this.password.focus();
		e.preventDefault();
		return;
	}
	if (this.newpassword.value != ""
			&& this.newpassword.value == this.renewpassword.value) {
		if (!checkPassword(this.newpassword.value)) {
			alert("The password you have entered is not valid!");
			this.newpassword.focus();
			e.preventDefault();
			return;
		}
	} else {
		alert("Error: Please check that you've entered and confirmed your password!");
		this.newpassword.focus();
		e.preventDefault();
		return;
	}
};

var myForm = document.getElementById("myPasswordForm");
myForm.addEventListener("submit", checkForm, true);

// HTML5 form validation

var supports_input_validity = function() {
	var i = document.createElement("input");
	return "setCustomValidity" in i;
}

if (supports_input_validity()) {
	var usernameInput = document.getElementById("password");
	usernameInput.setCustomValidity(usernameInput.title);

	var pwd1Input = document.getElementById("newpassword");
	pwd1Input.setCustomValidity(pwd1Input.title);

	var pwd2Input = document.getElementById("renewpassword");

	// input key handlers

	usernameInput
			.addEventListener(
					"keyup",
					function() {
						usernameInput
								.setCustomValidity(this.validity.patternMismatch ? usernameInput.title
										: "");
					}, false);

	pwd1Input
			.addEventListener(
					"keyup",
					function() {
						this
								.setCustomValidity(this.validity.patternMismatch ? pwd1Input.title
										: "");
						if (this.checkValidity()) {
							pwd2Input.pattern = this.value;
							pwd2Input.setCustomValidity(pwd2Input.title);
						} else {
							pwd2Input.pattern = this.pattern;
							pwd2Input.setCustomValidity("");
						}
					}, false);
	pwd2Input
			.addEventListener(
					"keyup",
					function() {
						this
								.setCustomValidity(this.validity.patternMismatch ? pwd2Input.title
										: "");
					}, false);
}
</script>
</body>
</html>