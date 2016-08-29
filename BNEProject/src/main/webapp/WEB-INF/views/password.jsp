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
		<li class="active">비밀번호 변경</li>
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
									oninput="matchingCheck(this)"
									title="Please check that you've entered and confirmed your password! " required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" placeholder="Re-New Password" id="renewpassword">
							</div>
						</div>
												
						
						<div class="form-group">
							<label class="col-md-2 control-label"></label>
							<div class="col-md-2">
								<input type="text" hidden="true" name="id" value="${sessionScope.user.employee_id}" >
								<button class="btn btn-primary" onclick="validCheck();" >Confirm</button>
							</div>
						</div>

					</div>
				</div>
		</form>
	
	</div>



<script type="text/javascript">
  function matchingCheck(input) {
		 
	 if (input.value != document.getElementById('newpassword').value) {
        input.setCustomValidity('Password Must be Matching.');
    } else {
        input.setCustomValidity('');
    }

 }  
 
function validCheck(){
	 event.preventDefault();
	 var $myForm = $('#myPasswordForm');
	
	 if (!$myForm[0].checkValidity()) {
	   $('<input type="submit">').hide().appendTo($myForm).click().remove(); 
	 }
	 else
		 notyConfirm();
}

function notyConfirm(){
    noty({
        text: 'Do you want to continue?',
        layout: 'center',
        killer: 'true',
        buttons: [
                {addClass: 'btn btn-success btn-clean', text: 'Ok', onClick: function($noty) {
                	if(document.getElementById("newpassword").value!==document.getElementById("renewpassword").value){
                		alert("변경할 패스워드를 확인하세요");
                		window.location.reload();
                	}
                	else
         			   document.getElementById("myPasswordForm").submit();
                	
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
 			<script type='text/javascript' src='/js/plugins/noty/jquery.noty.js'></script>
            <script type='text/javascript' src='/js/plugins/noty/layouts/center.js'></script>            
            <script type='text/javascript' src='/js/plugins/noty/themes/default.js'></script>
        
</body>
</html>