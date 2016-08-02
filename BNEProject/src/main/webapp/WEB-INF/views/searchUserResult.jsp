<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<br/>
<br/>
<br/>

<div class="col-md-3">
                            <!-- CONTACT ITEM -->
    <div class="panel panel-default">
        <div class="panel-body profile">
            <div class="profile-image">
                <img src="/assets/images/users/user2.jpg" alt="John Doe"/>
            </div>
            <div class="profile-data">
                <div class="profile-data-name">${emp.employee_name}</div>
                <div class="profile-data-title">${emp.employee_id}</div>
            </div>
            <div class="profile-controls">
                <a href="#" class="profile-control-left"><span class="fa fa-info"></span></a>
                <a href="#" class="profile-control-right"><span class="fa fa-phone"></span></a>
            </div>
        </div>                                
        <div class="panel-body">                                    
            <div class="contact-info">
                <p><small>Department</small><br/>${emp.department_id}</p>
                <p><small>Department</small><br/>${emp.mobile_phone}</p>
                <p><small>Department</small><br/>${emp.email}</p>
            </div>
        </div>                                
    </div>
    <!-- END CONTACT ITEM -->
    
    
    
</div>

</body>
</html>