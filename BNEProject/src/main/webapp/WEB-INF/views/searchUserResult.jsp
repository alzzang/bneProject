<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
</head>


<body>


<h1>hello</h1>
<h1>${fn:length(depUserList)}</h1>
<h1>${emp.employee_id}</h1>
 <!-- PAGE CONTENT WRAPPER -->
                <div class="page-content-wrap">
                    
                    <div class="row">
                        <div class="col-md-3">
                            
                            <div class="panel panel-default">
                                <div class="panel-body profile" style="background: url('/assets/images/gallery/music-4.jpg') center center no-repeat;">
                                    <div class="profile-image">
                                        <img src="/assets/images/users/user3.jpg" alt="Nadia Ali"/>
                                    </div>
                                    <div class="profile-data">
                                        <div class="profile-data-name">${emp.employee_name}</div>
                                        <div class="profile-data-title" style="color: #FFF;">${emp.employee_id }</div>
                                    </div>
                                    <div class="profile-controls">
                                        <a href="#" class="profile-control-left twitter"><span class="fa fa-twitter"></span></a>
                                        <a href="#" class="profile-control-right facebook"><span class="fa fa-facebook"></span></a>
                                    </div>                                    
                                </div>                                
                                <div class="panel-body">                                    
                                    <div class="row">
                                        <div class="col-md-6">
                                            <button class="btn btn-info btn-rounded btn-block"><span class="fa fa-check"></span> Following</button>
                                        </div>
                                        <div class="col-md-6">
                                            <button class="btn btn-primary btn-rounded btn-block"><span class="fa fa-comments"></span> Chat</button>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel-body list-group border-bottom">
                                
<!--                                ARCODIAN DROPDOWN MENU  				-->
									<div class="w3-accordion w3-light-grey">
									  <button onclick="myFunction('Demo1')" class="w3-btn-block w3-left-align">Department Groups</button>
									  <div id="Demo1" class="w3-accordion-content w3-container">
									  <c:forEach items="${depUserList }" var="list" varStatus="status">
										  <c:if test="${list.employee_id != employeeDTO.employee_id}">
										  	<c:if test="${list.employee_id != emp.employee_id}">
										    	<a href="#" ><span>${list.employee_id}</span>${list.employee_name}</a>
										    </c:if>
										  </c:if>
									  </c:forEach>
									    
									    
									  </div>
									  <button onclick="myFunction('Demo2')" class="w3-btn-block w3-left-align">Open Section 2</button>
									  <div id="Demo2" class="w3-accordion-content w3-container">
									    <h4>Section 2</h4>
									    <p>Some other text..</p>
									  </div>
									</div>
									
                                </div>
                                <div class="panel-body">
                                    <h4 class="text-title">Friends</h4>
                                    <div class="row">
                                        <div class="col-md-4 col-xs-4">
                                            <a href="#" class="friend">
                                                <img src="/assets/images/users/user.jpg"/>
                                                <span>Dmitry Ivaniuk</span>
                                            </a>                                            
                                        </div>
                                        <div class="col-md-4 col-xs-4">                                            
                                            <a href="#" class="friend">
                                                <img src="/assets/images/users/user2.jpg"/>
                                                <span>John Doe</span>
                                            </a>                                            
                                        </div>
                                        <div class="col-md-4 col-xs-4">                                            
                                            <a href="#" class="friend">
                                                <img src="/assets/images/users/user4.jpg"/>
                                                <span>Brad Pit</span>
                                            </a>                                            
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-4 col-xs-4">                                            
                                            <a href="#" class="friend">
                                                <img src="/assets/images/users/user5.jpg"/>
                                                <span>John Travolta</span>
                                            </a>                                            
                                        </div>
                                        <div class="col-md-4 col-xs-4">                                            
                                            <a href="#" class="friend">
                                                <img src="/assets/images/users/user6.jpg"/>
                                                <span>Darth Vader</span>
                                            </a>                                            
                                        </div>
                                        <div class="col-md-4 col-xs-4">                                            
                                            <a href="#" class="friend">
                                                <img src="/assets/images/users/user7.jpg"/>
                                                <span>Samuel Leroy Jackson</span>
                                            </a>                                            
                                        </div>
                                    </div>
                                
                                    
                                </div>
                            </div>                            
                            
                        </div>
                        
                       
                        
                    </div>

                </div>
                <!-- END PAGE CONTENT WRAPPER -->
</body>
</html>