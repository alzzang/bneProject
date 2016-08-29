
<%@page import="kr.co.bne.common.WeeklyReportMemberInfo"%>
<%@page import="kr.co.bne.dto.EmployeeDTO"%>
<%@page import="kr.co.bne.common.WeeklyReportSearchElement"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="/js/plugins/jquery/jquery.min.js"></script>
 <script type="text/javascript" src="/js/plugins/jquery/jquery-ui.min.js"></script>
 <script type="text/javascript" src="/js/plugins/bootstrap/bootstrap.min.js"></script>        
 
 
<script>
$(window).ready(function(){
	
	// 페이지에 보여줄 레코드 수
	var pageSize = "${pageSize}";
	// 현재 페이지
	var currentPage = Number("${page}");
	// 총 레코드 수
	var totalRecordNum = "${totalRecordNum}";
	// 선택된 멤버 칸 색칠
	var selectedMemberId = '${selectedMemberId}';	
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
	
	if(selectedMemberId != "" || selectedMemberId != null){
		$('a[href="/weeklyReport/list/'+selectedMemberId+'"]').css('background-color', '#FAF4C0');
	}
});
	
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

var goToWeeklyDetail = function(employee_id, weekly_report_id){
	var form = getForm("POST", "/weeklyReport/detail/" + employee_id);
	var hiddenField = getHiddenChild("weeklyReportId", weekly_report_id);
		
	form.appendChild(hiddenField);
	document.body.appendChild(form);
	form.submit();
};

var goToSearchResult = function(){
	var form = getForm("POST", "/weeklyReport/list/" +  '${selectedMemberId}');
	form.appendChild(getHiddenChild("keyword", $('#keyword').val()));
	form.appendChild(getHiddenChild("planDate", $('#planDate').val()));
	
	document.body.appendChild(form);
	form.submit();
};

var goToPage = function(pageNum){
	
	var form = getForm("POST", "/weeklyReport/list/" +  '${selectedMemberId}');
	form.appendChild(getHiddenChild("pageStr", pageNum));
	form.appendChild(getHiddenChild("keyword", '${keyword}'));
	form.appendChild(getHiddenChild("planDate", '${planDate}'));
	
	document.body.appendChild(form);
	form.submit();
}
</script>
<!-- START CONTENT FRAME -->
<div class="content-frame">                                    
    <!-- START CONTENT FRAME TOP -->
    <div class="content-frame-top">                        
        <div class="page-title">                    
            <h2>주간 계획 목록</h2>
        </div>                                                                                
        
        <div class="pull-right">                            
            <button class="btn btn-default content-frame-left-toggle"><span class="fa fa-bars"></span></button>
        </div>                        
    </div>
    <!-- END CONTENT FRAME TOP -->
    
    <!-- START CONTENT FRAME LEFT -->
    <div class="content-frame-left">
        <div class="block">
            <div class="list-group border-bottom">
                <a href="/weeklyReport/list" class="list-group-item active"><span class="fa fa-inbox"></span>전체보기<span class="badge badge-success">${allReportNum}</span></a>
                <c:forEach items="${myDeptMemberList}" var="myDeptMember">
	                <a href="/weeklyReport/list/${myDeptMember.employee_id}" class="list-group-item">
	          	    	<span class="fa fa-inbox"></span>
	          	    	${myDeptMember.employee_name }(${myDeptMember.employee_id})
	          	    	<span class="badge badge-warning">${myDeptMember.post_num }</span>
	                </a>
                </c:forEach>
            </div>                        
        </div>
    </div>
    <!-- END CONTENT FRAME LEFT -->
    
    <!-- START CONTENT FRAME BODY -->
    <div class="content-frame-body">
        
        <div class="panel panel-default">
            <div class="panel-heading">
            	<div class="col-md-6 col-sm-6">
            	</div>
	            <div class="col-md-3 col-sm-3">
	            	<div class="input-group">
		            		<input type="text" class="form-control" placeholder="제목" id="keyword" value="${keyword }">
		            		<div class="input-group-addon" style="cursor:pointer" id="searchButton"  onclick="goToSearchResult();"><i class="fa fa-search"></i></div>
	            	</div>
	   	        </div>
                <div class="pull-right col-md-3 col-sm-3" >
                    <div class="input-group">
                        <input class="form-control datepicker" placeholder="날짜" id="planDate"  type="text" data-orientation="left" value="${planDate}"/>                                    
                        <div class="input-group-addon"><span class="fa fa-calendar"></span></div>
                    </div>
                </div>
            </div>
            <div class="panel-body mail" id="weeklyReportList">
			<c:choose>
				<c:when test="${empty myDeptWeeklyReportList}">
					<!-- 검색결과가 없으면 -->
					<div class="mail-item mail-danger" style="text-align:center; line-height:10em;">
						<h6>검색 결과가 없습니다.</h6>
	                </div>
	                <!-- 여기가 나와야 해 -->
				</c:when>
				<c:otherwise>
	                <!-- 검색결과가 있으면 -->
	                <c:forEach items="${myDeptWeeklyReportList}" var="record">
	                <div class="mail-item mail-success">
	                    <div class="mail-star">
	                        ${record.idx }
	                    </div>
	                    <div class="mail-user">${record.employee_name }</div>
	    	                <a style="cursor:pointer;" onclick="goToWeeklyDetail('${record.employee_id }', '${record.weekly_report_id }')" class="mail-text">${record.title}</a>                                    
	                    <div class="mail-date">${record.start_date } ~ ${record.end_date }</div>
	                </div>
	                </c:forEach>
	                <!-- 여기가 나와야 해 -->
				</c:otherwise>
			</c:choose>            
            </div>
            <div class="panel-footer col-md-6 col-sm-6" id="tableIndex">                                
        	</div>
        	</div>
    </div>
    <!-- END CONTENT FRAME BODY -->
</div>
<!-- END CONTENT FRAME -->

 