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
							    		title="Please check that you've entered and confirmed your password! " required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" placeholder="Re-New Password" id="renewpassword">
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-2 control-label"></label>
							<div class="col-md-2">
								<!-- <button class="btn btn-default">Clear Form</button> -->
								
								<!-- <a href="/user/validCheck" class="btn btn-default pull-right" id='changebutton'>변경</a> -->
								<input type="text" hidden="true" name="id" value="${sessionScope.user.employee_id}" >
								<!-- <input type="submit" value="ok"> -->
								 <button class="btn btn-primary" onClick="notyConfirm();">Confirm</button>  
							</div>
						</div>

					</div>
				</div>
			
		</form>
	</div>
<script type="text/javascript">
function notyConfirm(){
    noty({
        text: 'Do you want to continue?',
        layout: 'center',
        buttons: [
                {addClass: 'btn btn-success btn-clean', text: 'Ok', onClick: function($noty) {

                   
                   var myform = document.createElement("input");  
                   myform.setAttribute("type", "submit");  
                }
                },
                {addClass: 'btn btn-danger btn-clean', text: 'Cancel', onClick: function($noty) {
                    $noty.close();
                   
                    }
                }
            ]
    })                                                    
}  
</script>
     
 

</body>
</html>