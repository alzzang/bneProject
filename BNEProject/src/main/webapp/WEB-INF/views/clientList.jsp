<%@page import="kr.co.bne.common.ClientSearchElement"%>
<%@page import="org.json.JSONObject"%>
<%@page import="kr.co.bne.dto.EmployeeDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <style>
 a:hover{
	cursor:pointer; 
 }
 a:active{
	opacity:0.5;
 }
 </style>
<!-- START MODAL -->
   <div class="modal fade" id="modifyClientModal" tabindex="-1" aria-hidden="true">
	<div class="col-md-12">
      <div class="modal-dialog" style="overflow-y:hidden;">
         <form class="form-horizontal" id="modifyClientFormId" method="POST" action="/client/modify">
            <div class="panel panel-danger">
               <div class="panel-heading ui-draggable-handle">
					<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                  <h3 class="panel-title">거래처 정보 수정</h3>
               </div>
               <div class="panel-body">
                  <div class="form-group">
                      <label class="col-md-3 col-xs-12 control-label">거래처 ID</label>
                      <div class="col-md-8 col-xs-12">                                            
                           <input type="text" name="client_id"  class="form-control" readonly="readonly">
                      </div>
                  </div>           
                      		
                  <div class="form-group">
                      <label class="col-md-3 col-xs-12 control-label">거래처명</label>
                      <div class="col-md-8 col-xs-12">                                            
                          <input type="text" name="client_name" maxLength="30" class="form-control">
                      </div>
                  </div>               		
               
                  <div class="form-group">
                      <label class="col-md-3 col-xs-12 control-label">대표자</label>
                      <div class="col-md-8 col-xs-12">                                            
                           <input type="text" name="representative" maxLength="16" class="form-control">
                      </div>
                  </div>               		
               
                  <div class="form-group">
                      <label class="col-md-3 col-xs-12 control-label">주소</label>
                      <div class="col-md-8 col-xs-12">                                            
                           <input type="text" name="address" maxLength="100"  class="form-control">
                      </div>
                  </div>               		
                  <div class="form-group">
                      <label class="col-md-3 col-xs-12 control-label">전화번호</label>
                      <div class="col-md-8 col-xs-12">                                            
                           <input type="text" name="telephone" maxLength="12"  class="form-control" maxlength="11">
                      </div>
                  </div>              
                   		
                  <div class="form-group">
                      <label class="col-md-3 col-xs-12 control-label">부서</label>
                      <div class="col-md-8 col-xs-12">                                            
                           	<select class="form-control" name="department_id">
								<option value="">전체</option>
								<c:forEach items="${deptList}" var="dept">
										<option value="${dept.department_id}">${dept.department_name}(${dept.department_id})</option>
								</c:forEach>
							</select>
                      </div>
                  </div>               		
               
               </div>
               <div class="panel-footer">
               		<div class="pull-right">
                  		<button class="btn btn-primary" type="button" onclick="modifyClientConfirm(this.form.client_id.value);">완료</button>
                  		<button class="btn btn-primary" type="button" data-dismiss="modal">취소</button>
               		</div>
               </div>
            </div>
         </form>
      </div>
      </div>
      
   </div>
   <!-- END MODAL -->
 
  <div class="message-box message-box-warning animated fadeIn" id="modifyClientConfirm">
      <div class="mb-container">
          <div class="mb-middle">
              <div class="mb-title"><span class="fa fa-warning"></span>거래처(<span id="modifyClientId"></span>)를 수정하시겠습니까?</div>
              <div class="mb-content">
                  <p></p>                  
              </div>
              <div class="mb-footer">
              	<div class="pull-right">
            		<a id="removeClientAnchor" class="btn btn-default btn-lg mb-control-close" onclick="submitModifyForm();">Yes</a>
                   <button class="btn btn-default btn-lg mb-control-close">No</button>
				</div>
              </div>
          </div>
      </div>
  </div>
  
  <div class="message-box message-box-warning animated fadeIn" id="removeClientConfirm">
      <div class="mb-container">
          <div class="mb-middle">
              <div class="mb-title"><span class="fa fa-warning"></span>거래처(<span id="removeClientId"></span>)를 삭제하시겠습니까?</div>
              <div class="mb-content">
                  <p>삭제 이후에는 되돌릴 수 없습니다.</p>                  
              </div>
              <div class="mb-footer">
              	<div class="pull-right">
            		<a id="removeClientAnchor" class="btn btn-default btn-lg">Yes</a>
                   <button class="btn btn-default btn-lg mb-control-close">No</button>
				</div>
              </div>
          </div>
      </div>
  </div>
  
  <div class="message-box message-box-warning animated fadeIn" id="addClientConfirm">
      <div class="mb-container">
          <div class="mb-middle">
              <div class="mb-title"><span class="glyphicon glyphicon-question-sign"></span>거래처들을 추가하시겠습니까?</div>
              <div class="mb-content">
                  <p></p>                  
              </div>
              <div class="mb-footer">
              	<div class="pull-right">
            		<a id="addClientAnchor" onclick="submitAddForm()" class="btn btn-default btn-lg mb-control-close">Yes</a>
                   <button class="btn btn-default btn-lg mb-control-close" id="refuseAddClient">No</button>
                </div>
              </div>
          </div>
      </div>
  </div>
 
  <div class="message-box message-box-danger animated fadeIn" id="clientAlert">
      <div class="mb-container">
          <div class="mb-middle">
              <div class="mb-title"><span class="fa fa-warning"></span><span id="clientAlertMessage"></span></div>
              <div class="mb-content">
                  <p></p>                  
              </div>
              <div class="mb-footer">
              	<div class="pull-right">
                   <button class="btn btn-default btn-lg mb-control-close" id="">OK</button>
				</div>
              </div>
          </div>
      </div>
  </div>
 
<div class="content-frame">
	<!-- START CONTENT FRAME TOP -->
	<div class="content-frame-top">
		<div class="page-title">
			<h2>
				<span class="fa fa-money"></span> 거래처 관리
			</h2>
		</div>
		
		<div class="pull-right">
			<button class="btn btn-danger" onclick="visibleAddForm()" id="addClientFormButton">
				<span class="fa fa-plus"></span> 거래처 추가
			</button>
			<button class="btn btn-default content-frame-left-toggle">
				<span class="fa fa-bars"></span>
			</button>
		</div>

	</div>
	<!-- END CONTENT FRAME TOP -->

	<!-- START CONTENT FRAME LEFT -->
	<div class="content-frame-left" style="height:837px;display:block;">

		<div class="panel panel-default">
	
			<div class="panel-heading ui-draggable-handle">
				<h3 class="panel-title">
					<span class="glyphicon glyphicon-filter"></span> 검색 필터
				</h3>
				<div class="pull-right">
                  <a href="/client/list/" class="btn btn-danger">
                     <span class="fa fa-eraser"></span> 초기화
                  </a>
            </div>
			</div>

			<form role="form" id="searchForm" class="form-horizontal" action="/admin/client/1">
				<div class="panel-body">
					<div class="form-group">
						<label class="col-md-4 control-label">거래처 명</label>
						<div class="col-md-8">
							<input type="text" class="form-control" id="search_client_name" name="client_name" value="${ListCondition.client.client_name}">
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-4 control-label">대표자 명</label>
						<div class="col-md-8">
							<input type="text" class="form-control" id="search_representative"	name="representative" value="${ListCondition.client.representative}">
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-4 control-label">부서</label>
						<div class="col-md-8">
							<select class="form-control" name="department_id" id="search_department_id">
								<option value="">전체</option>
								<c:forEach items="${deptList}" var="dept">
									<c:choose>
										<c:when test="${dept.department_id eq ListCondition.client.department_id}">
											<option value="${dept.department_id}" selected>${dept.department_name}</option>
										</c:when>
										<c:otherwise>
											<option value="${dept.department_id}">${dept.department_name}(${dept.department_id})</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
					</div>

				</div>

				<div class="panel-heading ui-draggable-handle">

					<div class="pull-right">
						<button type="button" class="btn btn-primary" onclick="goToSearchResult();">
							<span class="glyphicon glyphicon-search"></span> SEARCH
						</button>
					</div>
				</div>
			</form>
			<!-- </form> -->
		</div>
	</div>
	<!-- END CONTENT FRAME LEFT -->

	<!-- START CONTENT FRAME BODY -->
	<div class="content-frame-body" style="height: 897px;">
		<div class="panel panel-danger" id="clientAddForm" style="display:none;">
			<div class="panel-heading ui-draggable-handle">
            <h3 class="panel-title">거래처 추가</h3>
				<ul class="panel-controls">
					<li>
						<a onclick="addInputElement()"><span class="fa fa-plus"></span></a>
					</li>
					<li>
						<a onclick="removeInputElement()"><span class="fa fa-minus"></span></a>
					</li>
				</ul>

			</div>
			<div class="panel-body">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>거래처명</th>
							<th>대표자</th>
							<th>주소</th>
							<th>전화번호</th>
							<th>부서</th>
						</tr>
					</thead>
					<tbody id="clientAddForm-body">
						<tr style="display:none;">
							<td><input type="text" class="form-control" maxLength="30" attr="client_name" required></td>
							<td><input type="text" class="form-control" maxLength="16" attr="representative" required></td>
							<td><input type="text" class="form-control" maxLength="100" attr="address" required></td>
							<td><input type="text" class="form-control" maxLength="11" placeholder="0부터 시작, 12자 내" attr="telephone" required></td>
							<td>
								<select class="form-control" attr="department_id">
									<c:forEach items="${deptList}" var="dept">
										<option value="${dept.department_id}">${dept.department_name}(${dept.department_id})</option>
									</c:forEach>	
								</select>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="panel-footer">
				<div class="pull-right">
					<button onclick="addClientConfirm()" class="btn btn-danger">
						<span class="fa fa-check"></span> 등록
					</button>
				</div>
			</div>
		</div>

		<div class="panel panel-colorful">
			<div class="panel-heading ui-draggable-handle">
				<h3 class="panel-title">검색 결과</h3>
			</div>

			<div class="panel-body">
				<table class="table table-bordered" id="searchResultTable">
					<thead>
						<tr>
							<th>거래처 ID</th>
							<th>거래처명</th>
							<th>대표자</th>
							<th>주소</th>
							<th>전화번호</th>
							<th>담당 부서</th>
							<th style="width: 7%;text-align: center;">수정/삭제</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${clientList}" var="client" varStatus="status">
							<tr id="tr_${status.index}">
								<td>${client.client_id}</td>
								<td>${client.client_name}</td>
								<td>${client.representative}</td>
								<td>${client.address}</td>
								<td>${client.telephone}</td>
								<td>${client.department_name}</td>
								<td style="display:none;">${client.department_id}</td>
								<td style="width: 7%;text-align: center;">
									<ul class="panel-controls">
										<li>
											<a onclick="modifyClientForm();"><span class="fa fa-pencil"></span></a>
										</li>
										<li>
											<a onclick="removeClientConfirm(${client.client_id});"><span class="glyphicon glyphicon-trash"></span></a>
										</li>
									</ul>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="panel-footer col-md-6 col-sm-6" id="tableIndex">                                
        	</div>
		</div>

	</div>
	<!-- END CONTENT FRAME BODY -->
</div>

<script type="text/javascript">


 /* 검색 및 페이징 처리 */
var getForm = function(method, action){
	var form = document.createElement("form");
	form.setAttribute("method", method);
	form.setAttribute("action", action);
	return form;
}
var getHiddenChild = function(name, value){
	var hiddenField = document.createElement("input");
	hiddenField.setAttribute("type", "hidden");
	hiddenField.setAttribute("name", name);
	hiddenField.setAttribute("value", value);
	return hiddenField;
}

var goToSearchResult = function(){
	var form = getForm("GET", "/client/list/");
	form.appendChild(getHiddenChild("client_name", $('#search_client_name').val()));
	form.appendChild(getHiddenChild("representative", $('#search_representative').val()));
	form.appendChild(getHiddenChild("department_id", $('#search_department_id option:selected').val()));

	document.body.appendChild(form);
	form.submit();
};

var goToPage = function(pageNum){
	
	var form = getForm("GET", "/client/list/");
	form.appendChild(getHiddenChild("pageStr", pageNum));
	form.appendChild(getHiddenChild("client_name", '${ListCondition.client.client_name}'));
	form.appendChild(getHiddenChild("representative", '${ListCondition.client.representative}'));
	form.appendChild(getHiddenChild("department_id", '${ListCondition.client.department_id}'));
	
	document.body.appendChild(form);
	form.submit();
}

var makePaging = function(){
	
	
	// 페이지에 보여줄 레코드 수
	var pageSize = "${ListCondition.pageSize}";
	// 현재 페이지
	var currentPage = Number("${ListCondition.page}");
	// 총 레코드 수
	var totalRecordNum = "${totalRecordNum}";
	
	// 보여질 인덱스 갯수
	var indexRange = 4;
	
	// 모든 인덱스의 수
	var allPageIndex = Math.ceil(totalRecordNum / pageSize);
	// 인덱스 그룹 번호
	var groupNumIndex  =  Math.floor(currentPage / indexRange + (currentPage % indexRange == 0 ? -1 : 0));
	// 보여질 인덱스의 시작과 끝
	var startIndex = groupNumIndex * indexRange + 1;
	var endIndex = startIndex + indexRange - 1;

	// 다음, 이전 페이지 
	var prevPage = currentPage - 1;
	var nextPage = currentPage + 1;
	
	if(currentPage == 1){
		prevPage = 1;
	}
	
	if(currentPage == allPageIndex){
		nextPage = allPageIndex;
	}	
	
	// 인덱스 삽입
	var indexTagStr = '<ul class="pagination pagination-sm pull-right">' +
								    '<li><a href="#" id="first_page" onclick="goToPage(1)"><span class="glyphicon glyphicon-fast-backward"></span></a></li>' + 
								    '<li><a href="#" id="prev_page" onclick="goToPage('+prevPage+')"><span class="glyphicon glyphicon-backward"></span></a></li>';
	
	if(allPageIndex != 0){
		for(var i=startIndex; i<=endIndex; i++){
			if(i == currentPage)
				indexTagStr += 	    '<li class="active"><a href="#" onclick="goToPage('+i+')">'+ i +'</a></li>';
			else
				indexTagStr += 	    '<li><a href="#" onclick="goToPage('+i+')">'+ i +'</a></li>';
					
			if(i == allPageIndex)
				break;
		}
	}							    
	indexTagStr += 	         '<li><a href="#" id="next_page" onclick="goToPage('+nextPage+')"><span class="glyphicon glyphicon-forward"></span></a></li>'+
									'<li><a href="#" id="last_page" onclick="goToPage('+allPageIndex+')"><span class="glyphicon glyphicon-fast-forward"></span></a></li>'+
							   '</ul>';
							   
	$("#tableIndex").html(indexTagStr);
	
}


/* 추가 폼 관리 */
var visibleAddForm = function(){
   $("#addClientFormButton").val();
   if($("#addClientFormButton").hasClass('btn-danger')) { //폼 추가
      $("#clientAddForm").show();
      $("#addClientFormButton").html('<span class="fa fa-minus"></span> 숨기기');
      $("#addClientFormButton").removeClass('btn btn-danger').addClass('btn btn-primary');
   }else {
      $("#clientAddForm").hide();
      $("#addClientFormButton").html('<span class="fa fa-plus"></span> 거래처 추가');
      $("#addClientFormButton").removeClass('btn btn-primary').addClass('btn btn-danger');
   }
}

var addInputElement = function(){
	$("#clientAddForm-body").children().first().clone(true, true).removeClass(".dummyElement").show().appendTo("#clientAddForm-body");
};

var removeInputElement = function(){
	if($("#clientAddForm-body").children().last().index() > 0) {
		$("#clientAddForm-body").children().last().remove();
	}
}


/* 템플릿 alert창 생성 */
var	showAlert = function(message, alertId){
	$("#clientAlertMessage").html(message);
	openMessageBox(alertId);
}

/* 거래처 데이터 처리 */

// 삭제 선택시 확인
var removeClientConfirm= function(client_id){
	$("#removeClientId").html(client_id);
	$("#removeClientAnchor").attr("href", "/client/remove/"+client_id);
	openMessageBox("#removeClientConfirm");
}

//수정 폼 생성
var modifyClientForm = function(){
	var tr = $(event.target).parents("tr");
	
	var client_id = tr.children()[0].innerText;
	var client_name = tr.children()[1].innerText;
	var representative = tr.children()[2].innerText;
	var address = tr.children()[3].innerText;
	var telephone = tr.children()[4].innerText;
	var department_id = tr.children()[6].innerText;
	
	$("#modifyClientFormId input[name=client_id]").val(client_id);	
	$("#modifyClientFormId input[name=client_name]").val(client_name);	
	$("#modifyClientFormId input[name=representative]").val(representative);	
	$("#modifyClientFormId input[name=address]").val(address);	
	$("#modifyClientFormId input[name=telephone]").val(telephone);	
	
	$("#modifyClientFormId select>option[value="+department_id+"]").attr("selected", "selected");
	$("#modifyClientModal").modal("show");
}
// 수정 완료시 확인
var modifyClientConfirm = function(value){
	$("#modifyClientId").html(value);
	openMessageBox("#modifyClientConfirm");
}


// 수정 완료 확인 시 데이터 전송
var submitModifyForm = function(){
	
	var telephone = $("#modifyClientFormId input[name=telephone]").val();
	var representative = $("#modifyClientFormId input[name=representative]").val();
	
	var el = {
			"telephone" : telephone,
			"representative" : representative
	};
	
	var validCheckResult = validCheck(el);
    if(validCheckResult.status == false) {
       showAlert(validCheckResult.message, "#clientAlert");
       return;
    }
	
   	$("#modifyClientFormId").submit();
}

// 추가 완료시 확인
var addClientConfirm = function(form){
	openMessageBox("#addClientConfirm");
}

// 추가 완료 확인 시 데이터 전송
var submitAddForm = function(){
   	var arr = [];
   
	if($("#clientAddForm-body").children("tr").size() <= 1) {
		showAlert("등록할 거래처 정보가 없습니다.", "#clientAlert");
	    return;
	 }
	
   
   for(var i=1; i<$("#clientAddForm-body").children("tr").size(); i++) {
      var trChild = $("#clientAddForm-body").children("tr:eq(" + i + ")");
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
         showAlert(validCheckResult.message, "#clientAlert");
         return;
      }else {
         arr.push(el);
      }
   }

	var form = getForm("POST", "/client/add")
	var hiddenField = getHiddenChild("client_add_info", JSON.stringify(arr));
	form.appendChild(hiddenField);
	document.body.appendChild(form);
	form.submit();
	
	
	
}

// 유효성 검사
var validCheck = function(el) {
	var result = {"status":true, "message":""};
	
	
	   for(var key in el) {
			if(key === "representative") {
				var pattern =  /^[\w\Wㄱ-ㅎㅏ-ㅣ가-힣]{2,20}$/; //특문 제외 2자 ~ 20자
				if(!pattern.test(el[key])){
					result.status = false;
					result.message = "이름은 특수문자를 제외한 2자에서 20자 여야 합니다.";
					break;
				}               
			}
			if(key === "telephone") {
				var pattern =  /^0([0-9]{1,2})([0-9]{3,4})([0-9]{4})$/;
				if(!pattern.test(el[key])){
					result.status = false;
					result.message = "\n전화번호는 0으로 시작된 11자리 내 숫자여야 합니다.";
					break;
				}               
	   		}
	   }

	   return result;
}



window.onload = function(){
	makePaging();
}

</script>