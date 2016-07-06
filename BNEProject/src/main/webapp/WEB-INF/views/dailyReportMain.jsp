<%@page import="java.util.HashMap"%>
<%@page import="kr.co.bne.common.DailyReportListElement"%>
<%@page import="kr.co.bne.common.DailyReportTeamListElement"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.bne.dto.EmployeeDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    

<%

String url = (String) request.getAttribute("url");


String position = ((EmployeeDTO) session.getAttribute("user")).getPosition();
boolean managerFlag = false; //manager면 true;
int totalUnapprovalNum = (Integer) request.getAttribute("totalUnapprovalNum");
List<DailyReportTeamListElement> memberList = null;

if("manager".equals(position)) {
	managerFlag = true;
	memberList = (List<DailyReportTeamListElement>) request.getAttribute("memberList");
}else { 
	managerFlag = false;
}

List<DailyReportListElement> dailyReportList = (List<DailyReportListElement>) request.getAttribute("dailyReportList");
int totalPageNum = (Integer) request.getAttribute("totalPageNum");

int currentPage = (Integer) request.getAttribute("currentPage");
String currentEmployee_id = null;
String currentApproval_flag = "전체보기";
String currentReg_date = "";


if(request.getAttribute("currentEmployee_id") != null) {
	currentEmployee_id = (String) request.getAttribute("currentEmployee_id");
}
if(request.getAttribute("currentReg_date") != null) {
	currentReg_date = (String) request.getAttribute("currentReg_date");
}

if(request.getAttribute("currentApproval_flag") != null) {
	currentApproval_flag = (String) request.getAttribute("currentApproval_flag");
}

String serviceParamsStr = (String) request.getAttribute("serviceParamsStr");



int startIdx = 0;
int endIdx = 0;

for(int i=1; i <= Math.ceil((double)totalPageNum/4); i++) {
	startIdx = 1 + (i-1)*4;
	endIdx = i*4;
	
	if((currentPage >= startIdx) && (currentPage <= endIdx)) {
		if(endIdx >= totalPageNum) {
			endIdx = totalPageNum;
		}
		
		break;		
	}
}

%>    

<input type="hidden" value=<%=url %> id="url">
<input type="hidden" value=<%=serviceParamsStr %> id="params">
<input type="hidden" value=<%=currentPage %> id="currentPage">


<div class="content-frame">                                    
                    <!-- START CONTENT FRAME TOP -->
                    <div class="content-frame-top">                        
                        <div class="page-title">                    
                            <h2><span class="fa fa-inbox"></span> 일일 업무 보고 <small>(<%=totalUnapprovalNum %> 미승인된 보고)</small></h2>
                        </div>                                                                                
                        
                        <div class="pull-right">                            
                            <button class="btn btn-default content-frame-left-toggle"><span class="fa fa-bars"></span></button>
                        </div>                        
                    </div>
                    <!-- END CONTENT FRAME TOP -->
                    
                    <!-- START CONTENT FRAME LEFT -->
                    <div class="content-frame-left" style="height: 837px;">
                        
                        <%if(!managerFlag) {%>
                        <div class="block">
                            <a href="pages-mailbox-compose.html" class="btn btn-danger btn-block btn-lg"><span class="fa fa-edit"></span> COMPOSE</a>
                        </div>
                        <%}else { %>
                        
                        <div class="block">
                            <div class="list-group border-bottom">
                            	<%if(currentEmployee_id == null) { %>
                            		<a href="/dailyReport/main/all" class="list-group-item active">
                            	<%}else { %>
                            		<a href="/dailyReport/main/all" class="list-group-item">
                            	<%} %>
                            	<span class="fa fa-inbox"></span> 전체 보기 <span class="badge badge-success"><%=totalUnapprovalNum %></span></a>
                            	<%for(DailyReportTeamListElement member : memberList) { %>
                            		<%if(currentEmployee_id == null || !member.getEmployee_id().equals(currentEmployee_id)) { %>
                            			<a class="list-group-item" style="cursor: pointer;" onclick='viewList("employee_id", <%=member.getEmployee_id()%>)'>
                            		<%}else { %>
                            			<a class="list-group-item active"  style="cursor: pointer;" onclick='viewList("employee_id", <%=member.getEmployee_id()%>)'>
                            		<%} %>
                            		<span class="fa fa-inbox"></span><%=member.getEmployee_name() %><span class="badge badge-warning"><%=member.getUnApproval() %></span></a>
                            	<%} %>
                            </div>                        
                        </div>
                        <%} %>
                        
                    </div>
                    <!-- END CONTENT FRAME LEFT -->
                    
                    <!-- START CONTENT FRAME BODY -->
                    <div class="content-frame-body" style="height: 897px;">
                        
                        <div class="panel panel-default">
                            <div class="panel-heading ui-draggable-handle">
                                <div class="btn-group">
                                                    <a href="#" data-toggle="dropdown" class="btn btn-primary dropdown-toggle" aria-expanded="false"><%=currentApproval_flag %> <span class="caret"></span></a>
                                                    <ul class="dropdown-menu" role="menu">
                                                        <li onclick='viewList("approval_flag", -1)' role="presentation" class="dropdown-header">전체보기</li>
                                                        <li><a onclick='viewList("approval_flag", 1)'>승인 목록</a></li>
                                                        <li><a onclick='viewList("approval_flag", 0)'>미승인 목록</a></li>
                                                    </ul>
                                </div>	
                                
                                <div class="pull-right" style="width: 150px;">
                                    <div class="input-group">
                                        <div class="input-group-addon" onclick="viewList('reg_date', undefined)" title="초기화" style="cursor: pointer;"><span class="fa fa-calendar"></span></div>
                                        <input class="form-control datepicker" type="text" data-orientation="left" onchange="viewList('reg_date', this.value)" value=<%=currentReg_date %>>                                    
                                    </div>
                                </div>
                                
                                <div class="pull-right" style="width: 600px;">
                                	<div class="col-md-12">
                                                <div class="input-group">
                                                    <div class="input-group-addon">
                                                        <span class="fa fa-search"></span>
                                                    </div>
                                                    <input type="text" class="form-control" placeholder="Who are you looking for?">
                                                    <div class="input-group-btn">
                                                        <button class="btn btn-primary">Search</button>
                                                    </div>
                                                </div>
                                      </div>
                                </div>                                                                                               
                                
                            </div>
                            
                            
                            
                            
                            <div class="panel-body mail">
                                <%for(DailyReportListElement report : dailyReportList) { %>
                                	<%	String className = report.getApproval_flag() == 1 ? "mail-star" : "mail-star starred";	%>
                                	<div class="mail-item mail-read mail-info">                                    
	                                    <div class="<%=className%>">
	                                        <span class="fa fa-star-o"></span>
	                                    </div>
	                                    <div class="mail-user"><%=report.getEmployee_name() %></div>                                    
	                                    <a href="/dailyReport/detail" class="mail-text"><%=report.getTitle() %></a>                                    
	                                    <div class="mail-date"><%=report.getReg_date() %></div>
                                	</div>
                                
                                <%} %>
                            </div>
                            
                           <div class="panel-footer">                                
                                <ul class="pagination pagination-sm pull-right">
                                    <%if(startIdx > 1) {%>
                                    <li class="disabled"><a href="<%=url %>/<%=startIdx-1 %>">«</a></li>
                                    <%} %>
                                    <%for(int i=startIdx; i<=endIdx; i++) { %>
                                    	<%if(currentPage == i) {%>
                                    	<li class="active">
                                    	<%}else { %>
                                    	<li  style="cursor: pointer;">
                                    	<%} %>
                                    	<a onclick="viewList('page', <%=i%>)"><%=i %></a></li>
                                    <%} %> 
                                    
                                    <%if(totalPageNum > endIdx) {%>                                
                                    <li><a href="<%=url %>/<%=endIdx+1 %>">»</a></li>
                                    <%} %>
                                </ul>
                            </div>                   
                        </div>
                        
                    </div>
                    <!-- END CONTENT FRAME BODY -->
                </div>
                
                
                
<script src="/js/dailyReportMain.js"></script>