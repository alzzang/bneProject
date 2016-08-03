<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>


	

    <!-- CONTACT ITEM -->
    <c:forEach items="${list}" var="list" varStatus="status">
    
	<div class="col-md-3">
	    <div class="panel panel-default">
	        <div class="panel-body profile">
	            <div class="profile-image">
	                <img src="/assets/images/users/user2.jpg" alt="John Doe"/>
	            </div>
	            <div class="profile-data">
	                <div class="profile-data-name">${list.employee_name}</div>
	                <div class="profile-data-title">${list.employee_id}</div>
	            </div>
	            <div class="profile-controls">
	                <a href="#" id="ioi${list.employee_id}" class="profile-control-left aabbcc"><span class="fa fa-info"></span></a>
	                <a href="#" class="profile-control-right"><span class="fa fa-phone"></span></a>
	            </div>
	        </div>                                
	        <div class="panel-body">                                    
	            <div class="contact-info">
	                <p><small>DepartmentNum</small><br/>${list.department_id}</p>
	                <p><small>MOBILE</small><br/>${list.mobile_phone}</p>
	                <p><small>E-Mail</small><br/>${list.email}</p>
	            </div>
	        </div>                                
	    </div>
	</div>
	    <!-- END CONTACT ITEM -->

    </c:forEach>                            
    
    
    

</body>
</html>