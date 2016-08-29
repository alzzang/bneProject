<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<%
String employee_id = "";
String employee_name = "";
int department_id = 0;

int currentPage = (Integer)request.getAttribute("currentPage");
int startIdx = (Integer)request.getAttribute("startIdx");
int endIdx = (Integer)request.getAttribute("endIdx");
int totalPageNum = (Integer)request.getAttribute("totalPageNum");
%>
<style>
.modal-dialog{
 overflow-y: scroll; max-height:85%;
}
.modal-content{
border-width: 0px !important;
}
tbody tr td{
	text-align: center !important;
}
</style>

<script>
 var jsonInfo=new Array();
 var jsonIdx=0;
 function departmentManagerSearch(departmentId){
		/* db에 보낼 str*/
			var str=$("#employee_name"+departmentId).val();
			
			var html='';
			var html2='';
			var keycode = e.keyCode;

			if(str==''){
			}else{
				empSearchKeyUp(str,keycode);
			}
		
	}
 
 
 function enterDepartmentName(departmentId){
	 var department_name= $('#department_name'+departmentId).val();
	$.ajax({
		type : "POST",
		url : "/admin/department/departmentSearch",
		data : {
			department_name:department_name
		},
		success : function(data) {
	
			if(data!=$('#'+departmentId+' td:nth-child(1)').html()&&data!=-1){
				alert('이미 존재하는 부서입니다.');
		
				$('#'+departmentId+' td:nth-child(6) #deptFlag'+departmentId).val(-1);
		
			}else{
				$('#'+departmentId+' td:nth-child(6) #deptFlag'+departmentId).val(0);
			}
			
		}
	})
 }
function sendModifyDept(departmentId){
	var pattern =  /^0([0-9]{1,2})([0-9]{3,4})([0-9]{4})$/;
	if($('#'+departmentId+' td:nth-child(6) #deptFlag'+departmentId).val()==-1){
		alert('부서 이름을 확인해주세요');
	}else if($('#'+departmentId+' td:nth-child(6) input').val()=="" && $('#employee_name'+departmentId).val()!="" ){
		alert('팀장 이름을 확인해주세요');
	} else if(!pattern.test($('#telephone'+departmentId).val())){
		alert('전화번호를 확인해주세요');
	} 
	else{
		var deptId= $('#'+departmentId +' td:nth-child(1)').html();
		var dept=$('#department_name'+departmentId).val();
		var manager=$('#employee_name'+departmentId).val();
		var phone=$('#telephone'+departmentId).val();
		var manager_id=$('#'+departmentId+' td:nth-child(6) input').val();
		
		var form = document.createElement("form");
		form.setAttribute("action", "/admin/department/update/"+deptId);

			var hiddenField = document.createElement("input");
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", "department_name");
			hiddenField.setAttribute("value", dept);
			form.appendChild(hiddenField);


			var hiddenField1 = document.createElement("input");
			hiddenField1.setAttribute("type", "hidden");
			hiddenField1.setAttribute("name", "employee_name");
			hiddenField1.setAttribute("value",manager);
			form.appendChild(hiddenField1);

			var hiddenField2 = document.createElement("input");
			hiddenField2.setAttribute("type", "hidden");
			hiddenField2.setAttribute("name", "telephone");
			hiddenField2.setAttribute("value", phone);
			form.appendChild(hiddenField2);
			
			var hiddenField3 = document.createElement("input");
			hiddenField3.setAttribute("type", "hidden");
			hiddenField3.setAttribute("name", "manager_id");
			hiddenField3.setAttribute("value", manager_id);
			form.appendChild(hiddenField3);

		document.body.appendChild(form);
		form.submit();
	}
	
	
}



function EmployeeOfDepartment(departmentId){
	$.ajax({
		type : "POST",
		url : "/admin/teamList",
		data : {
			departmentId:departmentId
		},
		success : function(data) {
			
			var html='';
			for(var i=0;i<data.length;i++){
	
				if(i==0){
					
					html+='<div class="row"><div class="col-md-12"><div class="col-md-6 col-md-offset-3"><div class="panel panel-default"><div class="panel-body profile">'
                            +'<div class="profile-image"><img src="http://192.168.1.18:8085/test/'+data[i].file_position+'" alt="Nadia Ali"></div><div class="profile-data">'
			                +' <div class="profile-data-name">'+data[i].employee_name+'</div><div class="profile-data-title">'+data[i].employee_id+'</div></div><div class="profile-controls">'
			                +'</div></div><div class="panel-body"> <div class="contact-info"><p><small>Position</small><br>'+data[i].position+'</p> <p><small>Mobile</small><br>'+data[i].mobile_phone+'</p>'      
			                +' <p><small>Email</small><br>'+data[i].email+'</p></div></div></div></div>'
			                $('.page-content-wrap').append(html);
			                html='';
			                                                                   			
				}else{
					if(i%2==1){
						html+='<div class="row">';
					}
					html+='<div class="col-md-6"><div class="panel panel-default"><div class="panel-body profile">'
                        +'<div class="profile-image"><img src="http://192.168.1.18:8085/test/'+data[i].file_position+'" alt="Nadia Ali"></div><div class="profile-data">'
		                +' <div class="profile-data-name">'+data[i].employee_name+'</div><div class="profile-data-title">'+data[i].employee_id+'</div></div><div class="profile-controls">'
		                +'</div></div><div class="panel-body"> <div class="contact-info"><p><small>Position</small><br>'+data[i].position+'</p> <p><small>Mobile</small><br>'+data[i].mobile_phone+'</p>'      
		                +' <p><small>Email</small><br>'+data[i].email+'</p></div></div></div></div>'
		           if(i%2==0){
		        	   html+='</div>';
		        	   $('.page-content-wrap').append(html);
		        	   html='';
		           }
		           if(i==data.length-1 && i%2==1){
		        	   $('.page-content-wrap').append(html);
		           }   
		              
				}
				
				
			}
		},
		error:function(){
			alert("수정오류");
		}
	})
}
</script>
<input type="hidden" value=${page } id="page">


<div class="content-frame">
	<!-- START CONTENT FRAME TOP -->
	<div class="content-frame-top">
		<div class="page-title">
			<h2>
				<span class="fa fa-user"></span> 부서 관리
			</h2>


		</div>

		<div class="pull-right">
			<button class="btn btn-danger" id="addDepartmentFormButton">
				<span class="fa fa-plus"></span> 부서 추가
			</button>
		</div>

		<div class="pull-right">
			<button class="btn btn-default content-frame-left-toggle">
				<span class="fa fa-bars"></span>
			</button>
		</div>
	</div>
	<!-- END CONTENT FRAME TOP -->

	<!-- START CONTENT FRAME LEFT -->
	<div class="content-frame-left" style="height: 837px;">

		<div class="panel panel-default">
			<div class="panel-heading ui-draggable-handle" >
				<h3 class="panel-title">
					<span class="glyphicon glyphicon-filter"></span> 검색 필터
				</h3>
			</div>
			
			<form role="form" id="searchForm" class="form-horizontal" action="/admin/department/${page}">
			<div class="panel-body">
					<div class="form-group">
						<label class="col-md-3 control-label">코드</label>
						<div class="col-md-9">
							<input type="text" class="form-control" id="department_id" name="department_id" value=${department_id}>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label">부서</label>
						<div class="col-md-9">
							<input type="text" class="form-control" id="department_name" name="department_name" value=${department_name}>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-md-3 control-label">팀장</label>
						<div class="col-md-9">
							<input type="text" class="form-control" id="employee_name" name="employee_name" value=${employee_name}>
						</div>
					</div>
				
				
			</div>

			<div class="panel-heading ui-draggable-handle">

				<div class="pull-right">
					<button type="submit" class="btn btn-primary" onclick="formSubmit()">
						<span class="glyphicon glyphicon-search"></span> SEARCH
					</button>
				</div>

			</div>
			 </form> 
		</div>
	</div>
	<!-- END CONTENT FRAME LEFT -->

	<!-- START CONTENT FRAME BODY -->
	<div class="content-frame-body" style="height: 897px;">
		
      <!-- start 부서 추가 폼 -->      
      <div class="panel panel-danger" style="display: none;" id="departmentAddForm">
         <div class="panel-heading ui-draggable-handle">
            <h3 class="panel-title">부서 추가</h3>
         </div>

         <div class="panel-body">
            <table class="table table-bordered">
               <thead>
                  <tr>
                     <th>부서명</th>
                     <th>팀  장</th>
                     <th>연락처</th>
                  </tr>
               </thead>
               <tbody id="deptartmentAddForm-body">
                  <tr>
                     <td><input type="text" id="deptAFInputDName" formflag="false" class="form-control" required></td>
                     <td><input type="text" id="deptAFInputMName" formflag="false" managerId="" class="form-control" required></td>
                     <td><input type="text" id="deptAFInputDNum" formflag="false" telephone"" class="form-control" required></td>
                  </tr>
               </tbody>
            </table>
         </div>

         <div class="panel-heading ui-draggable-handle">

            <div class="pull-right">
               <button id="submitAddFormBtn1" class="btn btn-danger">
                  <span class="fa fa-check"></span> 부서등록
               </button>
            </div>
         </div>
      </div>
      <!-- end 사원 추가 폼 -->
     
     
		<div class="panel panel-default">
			<div class="panel-heading ui-draggable-handle" id="departmentBody">
				<h3 class="panel-title">검색 결과</h3>
			</div>
			<div class="panel-body">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>부서코드</th>
							<th>부서</th>
							<th>팀장</th>
							<th>연락처</th>
							<th>인원</th>
							<th>수정/삭제</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${departmentList}" var="deptlist"  varStatus="status">
						<tr id="${status.index}" style="text-align: center;">
							<td style="width: 10%;">${deptlist.department_id }</td>
							<td style="width: 20%;">${deptlist.department_name }</td>
							<td style="width: 20%;">${deptlist.employee_name }</td>
							<td style="width: 20%;">${deptlist.telephone }</td>
							<td style="width: 20%;"><a href="#" data-toggle="modal" data-target="#myModal5" id="modalAdd" onclick="EmployeeOfDepartment(${deptlist.department_id })">${deptlist.count }</a></td>
							<td align="center" style="width: 7%;">
								<ul class="panel-controls" >
									<li><a href="#" id="correct${status.index}"><span class="fa fa-pencil"></span></a></li>
									<li><a
										href="/admin/department/delete/${deptlist.department_id}"><span
											class="glyphicon glyphicon-trash"></span></a></li>
								</ul>
								<!-- <button class="btn btn-default btn-rounded btn-sm">
									<span class="fa fa-pencil"></span>
								</button>
								<button class="btn btn-danger btn-rounded btn-sm"
									onclick="delete_row('trow_1');">
									<span class="fa fa-times"></span>
								</button> -->
								<input type="hidden" value="${deptlist.manager_id}" id="managerId${status.index}">
								<input type="hidden" value="0" id="deptFlag${status.index}">
							</td>
							<!-- <input type="hidden" value="0" id="deptFlag"> -->
						</tr>
						</c:forEach>
						
						
					</tbody>
				</table>

				<div class="panel-footer">
					<ul class="pagination pagination-sm pull-right">

						<%if(startIdx != 0) { %>
						<%if(startIdx > 1) {%>
						<li><a href="/admin/department/<%=startIdx-1 %>${optionQuery}">«</a></li>
						<%} %>
						<%for(int i=startIdx; i<=endIdx; i++) { %>
						<%if(currentPage == i) {%>
						<li class="active">
							<%}else { %>
						
						<li style="cursor: pointer;">
							<%} %> <a href="/admin/department/<%=i %>${optionQuery}"><%=i %></a>
						</li>
						<%} %>

						<%if(totalPageNum > endIdx) {%>
						<li><a href="/admin/department/<%=endIdx+1 %>${optionQuery}">»</a></li>
						<%} %>
						<%} %>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- END CONTENT FRAME BODY -->
</div>

<div id="myModal5"  class="modal fade" role="dialog">
	<div class="modal-dialog modal-admin">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
			
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title"><span class="fa fa-arrow-circle-o-left"></span> 부서 인원</h4>
				
			</div>
			<div class="modal-body">
			<div class="page-title">
	 <h2>
		
	</h2>  
</div> 
<!-- END PAGE TITLE -->

<!-- PAGE CONTENT WRAPPER -->

<div class="page-content-wrap">
	
</div>
			</div>
			<div class="modal-footer">
			</div>
		</div>
	</div>
</div>

<script type="text/javascript"
	src="/js/plugins/bootstrap/bootstrap-select.js"></script>
<script type="text/javascript"
	src="/js/plugins/datatables/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="/js/admin_managerDepartment.js"></script>
<script>

	function formSubmit() {
		var form = document.createElement("form");
		form.setAttribute("action", "/admin/department/1");

		if ($("#department_id").val().trim() != "") {
			var hiddenField = document.createElement("input");
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", "department_id");
			hiddenField.setAttribute("value", $("#department_id").val());
			form.appendChild(hiddenField);
		}
		if ($("#department_name").val().trim() != "") {
			var hiddenField = document.createElement("input");
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", "department_name");
			hiddenField.setAttribute("value", $("#department_name").val());
			form.appendChild(hiddenField);
		}
		if ($("#employee_name").val().trim() != "") {
			var hiddenField = document.createElement("input");
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", "employee_name");
			hiddenField.setAttribute("value", $("#employee_name").val());
			form.appendChild(hiddenField);
		}

		document.body.appendChild(form);
		form.submit();
	}
	
	
	
	function checkManager(managerId){
		var cnt=0;
		$.ajax({
			type : "POST",
    		url : "/admin/department/searchManager",
			data:{
				manager_id:managerId
			},
			async: false,
			success:function(data){
				cnt=data;
			}
		})
		return cnt;
	}
	
	
	function submitAll(){
		/**/
		/* var flag=0; */
		var result = {"flag":0, "index":0};
		var pattern =  /^0([0-9]{1,2})([0-9]{3,4})([0-9]{4})$/;
		$.each( jsonInfo, function( idx, value ) {
		
			var deptId= $('#'+value["row_num"] +' td:nth-child(1)').html();
			var dept=$('#department_name'+value["row_num"]).val();
			var manager=$('#employee_name'+value["row_num"]).val();
			var phone=$('#telephone'+value["row_num"]).val();
			var manager_id=$('#'+value["row_num"]+' td:nth-child(6) input').val();
			value["department_id"]=deptId;
			value["department_name"]=dept;
			value["employee_name"]=manager;
			value["telephone"]=phone;
			value["manager_id"]=manager_id;
			
			jsonInfo[idx]=value;

			 if($('#'+value["row_num"]+' td:nth-child(6) #deptFlag'+value["row_num"]).val()==-1){
					result.flag=-1;
					result.index=value["row_num"];
					return false;
				} 
			 else if($('#'+value["row_num"]+' td:nth-child(6) input').val()=="" && $('#employee_name'+value["row_num"]).val()!="" ){
				 result.flag=-2;
				result.index=value["row_num"];
				return false;
			} else if(!pattern.test($('#telephone'+value["row_num"]).val())){
				result.flag=-3;
				result.index=value["row_num"];
				return false;
			} 
		
		});
		if(result.flag==-1){
			alert('부서코드(' +$('#'+result.index +' td:nth-child(1)').html()+')의 부서 이름을 확인해주세요');
		}else if(result.flag==-2){
			alert('부서코드(' +$('#'+result.index +' td:nth-child(1)').html()+')의 팀장 이름을 확인해주세요');
			
		}else if(result.flag==-3){
			alert('부서코드(' +$('#'+result.index +' td:nth-child(1)').html()+')의 전화번호를 확인해주세요');
		}else{
			var departmentJson=JSON.stringify(jsonInfo);
			
			var form = document.createElement("form");
			form.setAttribute("action", "/admin/department/updateall");
			var hiddenField = document.createElement("input");
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", "departmentJson");
			hiddenField.setAttribute("value", departmentJson);
			form.appendChild(hiddenField);
			document.body.appendChild(form);
			form.submit();
		}
		
	}
	function checkAll(number,numberIdx){
		var result = {"status":true, "message":""};
	            var pattern =  /^0([0-9]{1,2})([0-9]{3,4})([0-9]{4})$/;
	            if(!pattern.test(number)){
	               result.status = false;
	               result.message = "\n전화번호는 0으로 시작된 11자리 내 숫자여야 합니다.";  
	               
	            }

	}
	for(var i=0;i<15;i++){
	    
		
		/* ramda function*/
		(function(index){
	    var t=0;
	    var cnt=0;
			var modifyFlag=true;
			
		$("#correct"+index).on("click", function() {
			
			if(modifyFlag){
				/* var jsonInfo=new Array();
				 var  */
				 var o={};
				 jsonIdx=0;
				modifyFlag=false;
				var idx= $('#'+index +' td').parent().index();
				var deptId= $('#'+index +' td:nth-child(1)').html();
				o["row_num"]=index;
				jsonInfo.push(o);
				if(jsonInfo.length==2){
					
					
						
					
				
					/* var inputHtml='<input type="button" value="수정" id="correctSubmit" onclick="submitAll()">';
					$('#departmentBody').append(inputHtml); */
				var inputHtml='<div class="pull-right"><button type="submit" class="btn btn-primary"  id="correctSubmit" onclick="submitAll()" ><span class="glyphicon glyphicon-search"></span> 전체 수정</button></div>';
				$('#departmentBody').append(inputHtml);
				}
				var dept=$('#'+index+' td:nth-child(2)').html();
				var manager=$('#'+index+' td:nth-child(3)').html();
				var phone=$('#'+index+' td:nth-child(4)').html();
				$('#'+index+' td:nth-child(2)').html('<div class="col-md-8 col-md-offset-2"  style="text-align: center;">'
						+'<input type="text" class="form-control"  onkeyup="enterDepartmentName('+index+')" style="text-align: center;" id="department_name'+index+'" name="department_name" value='+dept+'></div>');
				$('#'+index+' td:nth-child(3)').html('<div class="col-md-8 col-md-offset-2"  style="text-align: center;">'
						+'<input type="text" class="form-control"   style="text-align: center;" id="employee_name'+index+'" name="employee_name" value='+manager+'></div>');
				$('#'+index+' td:nth-child(4)').html('<div class="col-md-8 col-md-offset-2"  style="text-align: center;">'
						+'<input type="text" class="form-control"  style="text-align: center;"  id="telephone'+index+'" name="telephone" value='+phone+'></div>');
				$('#'+index+' td:nth-child(6) ul li:nth-child(1) a').attr('onclick','sendModifyDept('+index+')');
				/* onkeyup="checkPhone(this.value,'+index+')" */
				$("#employee_name"+index).on("keyup",function(e){
					/* db에 보낼 str*/
					
					var str=$("#employee_name"+index).val();
					var html='';
					var html2='';
					var keycode = e.keyCode;
					$(".xn-search").attr("class","xn-search");
					if(str==''){
					}else{
						managerSearchKeyUp(str,keycode,index);
					}
				})
				
				 $("#employee_name"+index).on("focusout",function(e){
					
					if(e.relatedTarget != null ){
						var select = e.relatedTarget.innerText;
						if(select==""){
							var select= $("#employee_name"+index).val();
						}
						var temp =select.split("(");
						e.currentTarget.value = temp[0];
						
						if(temp.length==2){
							var mgrId=temp[1].split(")");
							var cnt=checkManager((mgrId[0]));
							$('#'+index+' td:nth-child(6) #managerId'+index).val(mgrId[0]);
							targetflag=true;
							if(cnt==1){
								$('#'+index+' td:nth-child(6) #managerId'+index).val("");
							}
						}
						
					}
					
				
						$("#SearchDiv"+index).remove(); 
					
					
				}) 
				
			}
		
		})
		
	})(i);
		
		
		
		
	} 
	
	managerSearchKeyUp = function(str,keycode,index){
		$.ajax({
			type : "POST",
    		url : "/user/empSearch",
			data:{
				empSearch:str
			},
			dataType:"json",
			success:function(data){
				$("#employee_name"+index).parent().find(".panel-primary").remove();
				$("#empSGJ"+index).empty();
				html=
					'<div class="panel panel-primary zoomIn xn-drop-left xn-panel-dragging" style="position:absolute;overflow-y:scroll;z-index:1;height:120px;border:0px;margin:0px;" id="SearchDiv'+index+'">'+

	                    '<div id="empSGJ'+index+'" class="panel-body list-group list-group-contacts scroll" style="">'+
	                    
	                    '</div>     '+
	                '</div>';
				$("#employee_name"+index).parent().append(html);

				if(data.length===0){
					$(".xn-search").attr("class","xn-search active");
					html2=''+
                    '        <span class="list-group-item contacts-title"><b>검색결과 없음</b></span>'+
                    '    ';
                    
					$("#empSGJ"+index).append(html2);
					
					
				}else{
					
					for(var i=0;i<data.length;i++){
						html2='<a href="#" id="SearchEmpName'+data[i].employee_id+'" class="list-group-item" >'+
	                    '        <span class="contacts-title">'+data[i].employee_name+"(" +data[i].employee_id+")"+'</span>'+
	                    '       '+
	                    '    </a>';
						
	                    $("#empSGJ"+index).append(html2);
	                    (function(id){
	                    	
							$('#SearchEmpName'+data[id].employee_id).on('click',function(e){
						
								$("#employee_name"+index).val(data[id].employee_name);
							
								$("#SearchDiv"+id).remove();
								
		                    });
						
	                    })(i);
	                    
	                    
						
						
					}

	
					

					
				}
			},
			error:function(){
				console.log('error');
			}
		});//end ajax
	};
</script>