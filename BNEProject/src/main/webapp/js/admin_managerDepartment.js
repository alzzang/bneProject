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
	$("#employeeAddForm-body").children().first().clone(true, true).removeClass(".dummyElement").show().appendTo("#employeeAddForm-body");
}

function removeInputElement() {
	if($("#employeeAddForm-body").children().last().index() > 0) {
		$("#employeeAddForm-body").children().last().remove();
	}
}


function submitAddForm() {
	var arr = [];
	
	for(var i=1; i<$("#employeeAddForm-body").children("tr").size(); i++) {
		var trChild = $("#employeeAddForm-body").children("tr:eq(" + i + ")");
		
		var el = {};
		for(var j=0; j<$(trChild).children("td").size(); j++) {
			var ctrl = trChild.children("td:eq("+j+")").find(".form-control");			
						
			for(var k=0; k<ctrl.size(); k++) {
				var key = ctrl.attr("attr");
				var val = ctrl.val();
				el[key] = val;
			}
		}
		
		arr.push(el);
	}
	
	var form = document.createElement("form");
	var path = "/admin/employee/add";
	form.setAttribute("method", "POST");
	form.setAttribute("action", path);
	var hiddenField = document.createElement("input");
	hiddenField.setAttribute("type", "hidden");
	hiddenField.setAttribute("name", "employee_add_info");
	hiddenField.setAttribute("value", JSON.stringify(arr));
	form.appendChild(hiddenField);
	document.body.appendChild(form);

	form.submit();
}