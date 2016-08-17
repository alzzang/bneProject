/**
 * 
 */



function viewList(option, value) {
	event.stopPropagation();
	var originParams =jQuery.parseJSON($("#params").val());
	var path = $("#url").val();
	
	console.log(originParams);

	var page = 1;
	var employee_id = originParams.employee_id;
	var reg_date = originParams.reg_date;
	var approval_flag = originParams.approval_flag;

	var search_text = originParams.search_text;
	var search_type = originParams.search_type;
	
	if(option == "page") { //페이지가 옵션에 있으면 페이지 갱신
		page = value;
	}else if(option == "reg_date") {
		reg_date = value;
		page = 1;
	}else if(option == "employee_id") {
		employee_id = value;
		search_text='';
		page = 1;
	}else if(option == "approval_flag") {
		approval_flag = value;
		page = 1;
	}else if(option == "search"){
		search_text=$("#searchText").val();
		search_type=$("#search_type").val();
		alert(search_text+":"+search_type);
		page=1;
	}else if(option =="list"){
		page = value;
	}
	path += "/" + page;
	var form = document.createElement("form");
	form.setAttribute("method", "POST");
	form.setAttribute("action", path);

	
	if(employee_id != undefined) {
		var hiddenField = document.createElement("input");
		hiddenField.setAttribute("type", "hidden");
		hiddenField.setAttribute("name", "employee_id");
		hiddenField.setAttribute("value", employee_id);
		form.appendChild(hiddenField);
	}
	
	if(approval_flag != undefined && (approval_flag == 0 || approval_flag == 1)) {
		var hiddenField = document.createElement("input");
		hiddenField.setAttribute("type", "hidden");
		hiddenField.setAttribute("name", "approval_flag");
		hiddenField.setAttribute("value", approval_flag);
		form.appendChild(hiddenField);
	}

	if(reg_date != undefined) {
		var hiddenField = document.createElement("input");
		hiddenField.setAttribute("type", "hidden");
		hiddenField.setAttribute("name", "reg_date");
		hiddenField.setAttribute("value", reg_date);
		form.appendChild(hiddenField);
	}
	/*
	*/
	if( search_text != undefined){
	//	alert(typeof search_text+":");
		var hiddenField = document.createElement("input");
		hiddenField.setAttribute("type", "hidden");
		hiddenField.setAttribute("name", "search_text");
		hiddenField.setAttribute("value", search_text);
		form.appendChild(hiddenField);
		
		var hiddenField1 = document.createElement("input");
		hiddenField1.setAttribute("type", "hidden");
		hiddenField1.setAttribute("name", "search_type");
		hiddenField1.setAttribute("value", search_type);
		form.appendChild(hiddenField1);
	}
	
	document.body.appendChild(form);
	form.submit();
}