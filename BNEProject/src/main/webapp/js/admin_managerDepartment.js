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
                  '<div class="panel panel-primary zoomIn xn-drop-left xn-panel-dragging" style="position:absolute;border:0px;margin:0px; width:31%" id="searchDd">'+
                          '<div id="deptSGJ" class="panel-body list-group list-group-contacts scroll" style="">'+
                          '</div>     '+
                      '</div>';
               $("#deptAFInputDName").parent().append(html);
               
               
               if(data!=='-1'){
                  
                  html2=''+
                       '        <span class="list-group-item contacts-title" style="color:red;"><b>이미 존재하는 부서입니다.</b></span>'+
                       '       '+
                       '    ';
                  
                       $("#deptSGJ").append(html2);
               }else{
                  $("#deptAFInputDName").attr("formflag",true);
                  
                  html2=''+
                       '        <span class="list-group-item contacts-title" style="color:green;"><b>사용가능</b></span>'+
                       '    ';
                  
                  if(!pattern.test(key)){
                        $("#deptAFInputDName").attr("formflag",false);
                        html2=''+
                          '        <span class="list-group-item contacts-title" style="color:red;"><b>부서는 영문자로 시작하는 3~20자 영문자 또는 숫자이어야 합니다</b></span>'+
                          '    ';
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
    var manager_name= $('#deptAFInputMName').val();
    var telephone= $('#deptAFInputDNum').val();
    var manager_id=$('#deptAFInputMName').attr("managerId");
    
    
    if(department_name==''){
       alert('등록할 부서명이 없습니다.');       
       return;
    }
    
    if (confirm("부서를 추가하시겠습니까?") == true){

       if($("#deptAFInputDName").attr("formflag")=='false'){
          alert('부서를 등록 할 수 없습니다.(부서명)');
          return;
       }
       
       if($("#deptAFInputMName").attr("formflag")=='false'){
           alert('부서를 등록 할 수 없습니다.(팀장)');
           return;
        }
       
       if($("#deptAFInputDNum").attr("formflag")=='false'){
           alert('부서를 등록 할 수 없습니다.(연락처)');
           return;
        }
       
           var form = document.createElement("form");
       var path = "/admin/department/add";
       var hiddenField = document.createElement("input");
       form.setAttribute("method", "POST");
       form.setAttribute("action", path);
       var tem=[];
       tem.push({
                "department_name":department_name,
                "manager_id":manager_id,
                "telephone":telephone
                });
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


/* 팀장 input box에 값 입력할때마다 */
$("#deptAFInputMName").on("keyup",function(){
   var str= $('#deptAFInputMName').val();
   if(str==''){
      $("#deptAFInputMName").parent().find(".panel-primary").remove();
      return;
   }
   $.ajax({
      type : "POST",
      url : "/user/empSearch",
      data:{   
         empSearch:str
      },
      dataType:"json",
      success:function(data){
         console.log('success');
         $("#deptAFInputMName").parent().find(".panel-primary").remove();
         $("#deptSGJ2").empty();
         html=
            '<div class="panel panel-primary zoomIn xn-drop-left xn-panel-dragging" style="position:absolute;overflow-y:scroll;z-index:1;height:120px;border:0px;margin:0px;width:31%" id="SearchEmpIn">'+
                    '<div id="deptSGJ2" class="panel-body list-group list-group-contacts scroll" style="width:100%;">'+
                    '</div>     '+
                '</div>';
         $("#deptAFInputMName").parent().append(html);

         if(data.length===0){
            console.log('data.length===0');
            $(".xn-search").attr("class","xn-search active");
            html2=''+
                '        <span class="list-group-item contacts-title"><b>검색결과 없음</b></span>'+
                '    ';
                
            $("#deptSGJ2").append(html2);
            
            
         }else{
            
            for(var i=0;i<data.length;i++){
               html2='<a href="#" id="SearchDepEmpName'+data[i].employee_id+'" class="list-group-item" >'+
                    '        <span class="contacts-title">'+data[i].employee_name+"(" +data[i].employee_id+")"+'</span>'+
                    '       '+
                    '    </a>';
               
                    $("#deptSGJ2").append(html2);
                    (function(id){
                  $('#SearchDepEmpName'+data[id].employee_id).on('click',function(e){
                     $("#deptAFInputMName").val(data[id].manager_name);
                     $("#SearchEmpIn"+id).remove();
                       });
                    })(i);
            }
         }
      },
      error:function(){
         console.log('error');
      }
   });//end ajax
   
   
})

 $("#deptAFInputMName").on("focusout",function(e){
    $("#deptAFInputMName").attr("formflag",false);            
   if(e.relatedTarget != null ){
      var select = e.relatedTarget.innerText;
      if(select==""){
         var select= $("#deptAFInputMName").val();
      }
      var temp =select.split("(");
      e.currentTarget.value = temp[0];
      
      if(temp.length==2){
         $("#deptAFInputMName").attr("formflag",true);
         var mgrId=temp[1].split(")");
         var cnt=checkManager((mgrId[0]));
//         $('#'+index+' td:nth-child(6) #managerId'+index).val(mgrId[0]);
         
         $('#deptAFInputMName').attr("managerId",mgrId[0]);
         if(cnt==1){
        	 $('#deptAFInputMName').attr("formflag",false);
         }
         targetflag=true;
      }
      
   }
      $("#SearchEmpIn").remove(); 
}) 

/* 연락처 input box에 값 입력할때마다 */
$("#deptAFInputDNum").on("keyup",function(){
   console.log('a');
   $("#deptAFInputDNum").parent().find(".panel-primary").remove();
   
   var telephone= $('#deptAFInputDNum').val();
     var key=telephone;
     var pattern = /^[0-9]{3,9}$/;
     
     $("#deptSGJ3").empty();
      html=
         '<div class="panel panel-primary zoomIn xn-drop-left xn-panel-dragging" style="position:absolute;border:0px;margin:0px; width:31%" id="searchDdd">'+
                 '<div id="deptSGJ3" class="panel-body list-group list-group-contacts scroll" style="">'+
                 '</div>     '+
             '</div>';
      $("#deptAFInputDNum").parent().append(html);
     
     if(!pattern.test(key)){
        console.log('b');
          $("#deptAFInputDNum").attr("formflag",false);
          html2=''+
            '        <span class="list-group-item contacts-title" style="color:red;"><b>3~9자리의 숫자만 입력가능합니다.</b></span>'+
            '    ';
          $("#deptSGJ3").append(html2);
          
       }else{
          console.log('c');
          $("#deptAFInputDNum").attr("formflag",true);
           
           html2='<a href="#" id="iiika2" class="list-group-item">'+
                '        <span class="contacts-title" style="color:green;">사용가능</span>'+
                '    </a>';
           $("#deptSGJ3").append(html2);
       } 
   
})

