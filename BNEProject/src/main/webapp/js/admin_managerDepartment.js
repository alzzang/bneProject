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
	var d = $("#employeeAddForm-body").children().first().clone();
	
	$("#employeeAddForm-body").append(d.show());
	
	alert(d.html());
}

function removeInputElement() {
	}
/* 부서추가 버튼 눌렀을때 */
$("#addDepartmentFormButton").on("click",function(){
    $("#addDepartmentFormButton").val();
      if($("#addDepartmentFormButton").hasClass('btn-danger')) { //폼 추가
         $("#departmentAddForm").show();
         
         $("#addDepartmentFormButton").html('<span class="fa fa-minus"></span> 숨기기');
         $("#addDepartmentFormButton").removeClass('btn btn-danger').addClass('btn btn-primary');
      }else {
         $("#departmentAddForm").hide();
         
         $("#addDepartmentFormButton").html('<span class="fa fa-plus"></span> 부서 추가');
         $("#addDepartmentFormButton").removeClass('btn btn-primary').addClass('btn btn-danger');
      }
});



/* 부서추가 input box에 값 입력할때마다 */
$("#deptAFInputDName").on("keyup",function(){
   $("#deptAFInputDName").attr("formflag",false);
    var department_name= $('#deptAFInputDName').val();
       $("#deptAFInputDName").parent().find(".panel-primary").remove();
       if(department_name!==''){
          
         $.ajax({
            type : "POST",
            url : "/admin/department/departmentSearch",
            data : {
               department_name:department_name
            },
            success : function(data) {
               
               var key=department_name;
                var pattern = /^[a-z]+[a-z0-9]{2,19}$/g; //특문 제외 2자 ~ 20자
                  
               
               $("#deptSGJ").empty();
               html=
                  '<div class="panel panel-primary zoomIn xn-drop-left xn-panel-dragging" style="position:absolute;border:0px;margin:0px;" id="SearchDiv1">'+
                          '<div id="deptSGJ" class="panel-body list-group list-group-contacts scroll" style="">'+
                          '</div>     '+
                      '</div>';
               $("#deptAFInputDName").parent().append(html);
               
               
               if(data!=='-1'){
                  
                  html2='<a href="#" id="SearchDeptName" class="list-group-item" >'+
                       '        <span class="contacts-title" style="color:red;">이미 존재하는 부서입니다.</span>'+
                       '       '+
                       '    </a>';
                  
                       $("#deptSGJ").append(html2);
               }else{
                  $("#deptAFInputDName").attr("formflag",true);
                  
                  html2='<a href="#" id="iiik" class="list-group-item">'+
                       '        <span class="contacts-title">사용가능</span>'+
                       '    </a>';
                  
                  if(!pattern.test(key)){
                        $("#deptAFInputDName").attr("formflag",false);
                        html2='<a href="#" id="iiik" class="list-group-item">'+
                          '        <span class="contacts-title" style="color:red;">부서는 영문자로 시작하는 3~20자 영문자 또는 숫자이어야 합니다</span>'+
                          '    </a>';
                     }    
                  
                  
                  $("#deptSGJ").append(html2);
               }
               
            }
         });
       }
       
});

/* 부서 등록버튼 눌렀을때*/
$("#submitAddFormBtn1").on("click",function(){
    var department_name= $('#deptAFInputDName').val();
    
    if(department_name==''){
       alert('등록할 부서명이 없습니다.');       
       return;
    }
    
    if (confirm("부서를 추가하시겠습니까?") == true){

       if($("#deptAFInputDName").attr("formflag")=='false'){
          alert('부서를 등록 할 수 없습니다.');
          return;
       }
       
        var form = document.createElement("form");
         var path = "/admin/department/add";
         var hiddenField = document.createElement("input");
         form.setAttribute("method", "POST");
         form.setAttribute("action", path);
         var tem=[];
         tem.push({"name":department_name});
         hiddenField.setAttribute("type", "hidden");
         hiddenField.setAttribute("name", "department_add");
         hiddenField.setAttribute("value", JSON.stringify(tem));
         
         form.appendChild(hiddenField);
         document.body.appendChild(form);
         form.submit();
        
       
      }else{   //취소
          return;
      }
   
});


