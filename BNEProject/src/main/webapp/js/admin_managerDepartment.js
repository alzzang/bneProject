function visibleAddForm() {
	$("#addEmployeeFormButton").val();
	if($("#addEmployeeFormButton").hasClass('btn-danger')) { //폼 추가
		$("#employeeAddForm").show();
		
		$("#addEmployeeFormButton").html('<span class="fa fa-minus"></span> 숨기기');
		$("#addEmployeeFormButton").removeClass('btn btn-danger').addClass('btn btn-primary');
	}else {
		$("#employeeAddForm").hide();
		
		$("#addEmployeeFormButton").html('<span class="fa fa-plus"></span> 사원 추가');
		$("#addEmployeeFormButton").removeClass('btn btn-primary').addClass('btn btn-danger');
	}
}


function addInputElement() {
	$("#employeeAddForm-body").children().first().clone(true, true).show().appendTo("#employeeAddForm-body");
}

function removeInputElement() {
	}