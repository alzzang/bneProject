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
	
	if($("#employeeAddForm-body").children("tr").size() <= 1) {
		alert("등록할 사원 정보가 없습니다.");
		return;
	}
	
	for(var i=1; i<$("#employeeAddForm-body").children("tr").size(); i++) {
		var trChild = $("#employeeAddForm-body").children("tr:eq(" + i + ")");
		trChild.css("background-color", "");
		
		var el = {};
		for(var j=0; j<$(trChild).children("td").size(); j++) {
			var ctrl = trChild.children("td:eq("+j+")").find(".form-control");			
						
			for(var k=0; k<ctrl.size(); k++) {
				var key = ctrl.attr("attr");
				var val = ctrl.val();
				el[key] = val;
			}
		}
		//각 element들 유효성 검사
		var validCheckResult = validCheck(el);
		if(validCheckResult.status == false) {
			trChild.css("background-color", "#E04B4A");
			alert(validCheckResult.message);
			return;
		}else {
			arr.push(el);
		}
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



function isExistEmployee(id) {
	$.ajax({
		url : "/admin/employee/existCheck/" + id,
		success : function(data) {
			alert("성공");
			result.status = false;
			result.message = JSON.stringify(data);
			
			return data;
		},
		error: function() {alert("실패!");}
	});
}



function validCheck(el) {
	var result = {"status":true, "message":""};

	for(var key in el) {
		if(key === "employee_id") {
			var pattern = /^[\w\Wㄱ-ㅎㅏ-ㅣ가-힣]{1,20}$/;//특문 제외 1자 ~ 20자
			if(!pattern.test(el[key])){
				result.status = false;
				result.message = "사번은 특수문자를 제외한 1자에서 20자 여야 합니다.";
				break;
			}else {
				var stat = isExistEmployee(el[key]);
				result.status = false;
				result.message = stat;
			}
		}else if(key === "employee_name") {
			var pattern =  /^[a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]{2,20}$/; //특문 제외 2자 ~ 20자
			if(!pattern.test(el[key])){
				result.status = false;
				result.message = "이름은 특수문자를 제외한 2자에서 20자의 문자 여야 합니다.";
				break;
			}					
		}else if(key === "mobile_phone") {
			var pattern =  /^01([0|1|6|7|8|9]?)([0-9]{3,4})([0-9]{4})$/;
			if(!pattern.test(el[key])){
				result.status = false;
				result.message = "휴대폰 번호 형식에 맞지 않습니다.";
				break;
			}					
		}else if(key === "email") {
			var pattern =  /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
			if(!pattern.test(el[key])){
				result.status = false;
				result.message = "이메일 형식에 맞지 않습니다.";
				break;
			}				
		}
	}

	return result;
}



function viewModifyForm() {
	
}