<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" id="theme" href="/css/theme-default.css"/>
<title>Insert title here</title>
</head>
<body>
<div class="block">
                                <h4>Wizard with form validation</h4>                                
                                <form action="javascript:alert('Validated!');" role="form" class="form-horizontal" id="wizard-validation" novalidate="novalidate">
                                <div class="wizard show-submit wizard-validation">
                                    <ul class="steps_2 anchor">
                                        <li>
                                            <a href="#step-7" class="selected" isdone="1" rel="1">
                                                <span class="stepNumber">1</span>
                                                <span class="stepDesc">Login<br><small>Information</small></span>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#step-8" class="disabled" isdone="0" rel="2">
                                                <span class="stepNumber">2</span>
                                                <span class="stepDesc">User<br><small>Personal data</small></span>
                                            </a>
                                        </li>                                    
                                    </ul>

                                    

                                                                                                                                                
                                <div class="stepContainer" style="height: 131px;"><div id="step-7" class="content" style="display: block;">   

                                        <div class="form-group">
                                            <label class="col-md-2 control-label">Login</label>
                                            <div class="col-md-10">
                                                <input type="text" class="form-control" name="login" placeholder="Login">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-2 control-label">Password</label>
                                            <div class="col-md-10">
                                                <input type="password" class="form-control" name="password" placeholder="Password" id="password">
                                            </div>
                                        </div>             
                                        <div class="form-group">
                                            <label class="col-md-2 control-label">Re-Password</label>
                                            <div class="col-md-10">
                                                <input type="password" class="form-control" name="repassword" placeholder="Re-Password">
                                            </div>
                                        </div>

                                    </div><div id="step-8" class="content" style="display: none;">

                                        <div class="form-group">
                                            <label class="col-md-2 control-label">Name</label>
                                            <div class="col-md-10">
                                                <input type="text" class="form-control" name="name" placeholder="Name">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-2 control-label">E-mail</label>
                                            <div class="col-md-10">
                                                <input type="text" class="form-control" name="email" placeholder="Your email">
                                            </div>
                                        </div>                                    
                                        <div class="form-group">
                                            <label class="col-md-2 control-label">Adress</label>
                                            <div class="col-md-10">
                                                <input type="text" class="form-control" name="adress" placeholder="Your adress">
                                            </div>                                        
                                        </div>                                                     

                                    </div></div><div class="actionBar"><div class="loader">Loading</div><a href="#" class="btn btn-primary pull-right disabled">Finish</a><a href="#" class="btn btn-default pull-right">Next</a><a href="#" class="btn btn-default pull-left disabled">Previous</a></div></div>
                                </form>
                            </div>
</body>
</html>