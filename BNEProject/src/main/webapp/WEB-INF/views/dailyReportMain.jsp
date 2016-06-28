<%@page import="kr.co.bne.common.DailyReportListElement"%>
<%@page import="kr.co.bne.common.DailyReportTeamListElement"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.bne.dto.EmployeeDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    

<%

String position = ((EmployeeDTO) session.getAttribute("user")).getPosition();
boolean managerFlag = false; //manager면 true;
int totalUnapprovalNum = 0;
List<DailyReportTeamListElement> memberList = null;

if("manager".equals(position)) {
	managerFlag = true;
	totalUnapprovalNum = (Integer) request.getAttribute("totalUnapprovalNum");
	memberList = (List<DailyReportTeamListElement>) request.getAttribute("memberList");
}else { 
	managerFlag = false;
}

List<DailyReportListElement> reportList = (List<DailyReportListElement>) request.getAttribute("reportList");
int totalPageNum = (Integer) request.getAttribute("totalPageNum");
int currentPage = (Integer) request.getAttribute("currentPage");

%>    

<input type="hidden" value=<%=totalPageNum %> id="totalPageNum">


<div class="content-frame">                                    
                    <!-- START CONTENT FRAME TOP -->
                    <div class="content-frame-top">                        
                        <div class="page-title">                    
                            <h2><span class="fa fa-inbox"></span> 일일 업무 보고 
                            <%if(managerFlag) { %>
                            	<small>(<%=totalUnapprovalNum %> 미승인된 보고)</small>
                            <%} %>
                            </h2>
                        </div>                                                                                
                        
                        <div class="pull-right">                            
                            <button class="btn btn-default content-frame-left-toggle"><span class="fa fa-bars"></span></button>
                        </div>                        
                    </div>
                    <!-- END CONTENT FRAME TOP -->
                    
                    <!-- START CONTENT FRAME LEFT -->
                    <div class="content-frame-left" style="height: 837px;">
                        
                        <%if(!managerFlag) { %>
                        <div class="block" onclick="goWriteForm();">
                            <a href="#" class="btn btn-danger btn-block btn-lg"><span class="fa fa-edit"></span> COMPOSE</a>
                        </div>
                        <%} %>
                        
                        <%if(managerFlag) { %>
                        <div class="block">
                            <div class="list-group border-bottom">
                            	<a href="#" class="list-group-item active"><span class="fa fa-inbox"></span> 전체 보기 <span class="badge badge-success"><%=totalUnapprovalNum %></span></a>
                            	
                            	<%for(DailyReportTeamListElement el : memberList) {%>
                            		<a href="#" class="list-group-item" employee_id=<%=el.getEmployee_id() %>><span class="fa fa-star"></span> <%=el.getEmployee_name() %> <span class="badge badge-warning"><%=el.getUnApproval() %></span></a>	
                            	<%} %>
                            </div>                        
                        </div>
                        <%} %>
                       <div class="block">
	                        <div class="list-group list-group-simple">                                
	                                <div class="list-group-item"><span class="fa fa-circle text-info"></span> 미승인 항목</div>
	                                <div class="list-group-item"><span class="fa fa-circle text-primary"></span> 승인 항목</div>
	                        </div>
                        </div>
                    </div>
                    <!-- END CONTENT FRAME LEFT -->

	<!-- START CONTENT FRAME BODY -->
	<div class="content-frame-body" style="height: 897px;">

		<div class="panel panel-default">
			<div class="panel-heading ui-draggable-handle">
				<div class="btn-group">
					<a href="#" data-toggle="dropdown"
						class="btn btn-primary dropdown-toggle" aria-expanded="false">전체보기
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu" role="menu">
						<li role="presentation" class="dropdown-header">전체보기</li>
						<li><a href="#">미승인 목록</a></li>
						<li><a href="#">승인 목록</a></li>
					</ul>
				</div>

				<div class="pull-right" style="width: 150px;">
					<div class="input-group">
						<div class="input-group-addon">
							<span class="fa fa-calendar"></span>
						</div>
						<input class="form-control datepicker" type="text"
							data-orientation="left">
					</div>
				</div>

				<div class="pull-right" style="width: 600px;">
					<div class="col-md-12">
						<div class="input-group">
							<div class="input-group-addon">
								<span class="fa fa-search"></span>
							</div>
							<input type="text" class="form-control"
								placeholder="Who are you looking for?">
							<div class="input-group-btn">
								<button class="btn btn-primary">Search</button>
							</div>
						</div>
					</div>
				</div>

			</div>




			<div class="panel-body mail">

				<% for(DailyReportListElement el : reportList) { %>

				<div class="mail-item mail-read mail-info" idx=<%=el.getIdx() %>>
					<%if(el.getApproval_flag() == 0) { %>
					<div class="mail-star starred">
						<%} else { %>
						<div class="mail-star">
							<%} %>
							<span class="fa fa-star-o"></span>
						</div>
						<div class="mail-user"><%=el.getEmployee_name() %></div>
						<a href="pages-mailbox-message.html" class="mail-text"><%=el.getTitle() %></a>
						<div class="mail-date"><%=el.getReg_date() %></div>
					</div>

					<%} %>

				</div>
				<div class="panel-footer">
					<ul class="pagination pagination-sm pull-right">
						<%if(currentPage == 1) { %>
						<li class="disabled">
						<%} else { %>
						<li>
						<%} %> 
						<a href="/dailyReport/main/1">«</a></li>

						<%
                           int endIdx = (totalPageNum < currentPage + 3) ? totalPageNum : (currentPage + 3);
                           for(int i=1; i<=endIdx; i++) { %>
								<%if(currentPage == i) { %>
									<li class="active">
								<%} else { %>
									<li><a href="/dailyReport/main/<%=i%>"><%=i %></a></li>
								<%} %>						
							<%} %>
						<li><a href="/dailyReport/main/<%=totalPageNum%>">»</a></li>
					</ul>
				</div>
			</div>

		</div>
		<!-- END CONTENT FRAME BODY -->
                </div>
                
                
                
        <script>
        
        	function goWriteForm() {
        		location.href= "/dailyReport/write"; 
        	}
        
        </script>