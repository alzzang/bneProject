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
                                        <div class="profile-data-name">${emp.employee_name }</div>
                                        <div class="profile-data-title" style="color: #FFF;">${emp.employee_id }</div>
                                    </div>
                                    <div class="profile-controls">
                                        <a href="#" class="profile-control-left twitter"><span class="fa fa-twitter"></span></a>
                                        <a href="#" class="profile-control-right facebook"><span class="fa fa-facebook"></span></a>
                                    </div>                                    
                                </div>                                
                                <div class="panel-body">                                    
                                    <div class="row">
                                        <div class="col-md-12">
                                            <button class="btn btn-primary btn-rounded btn-block"><span class="fa fa-comments"></span> Chat</button>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel-body list-group border-bottom">
                                    <a href="#" class="list-group-item active"><span class="fa fa-bar-chart-o"></span> Activity</a>
                                    <a href="#" class="list-group-item"><span class="fa fa-coffee"></span> Groups <span class="badge badge-default">18</span></a>                                
                                    <a href="#" class="list-group-item"><span class="fa fa-users"></span> Friends <span class="badge badge-danger">+7</span></a>
                                    <a href="#" class="list-group-item"><span class="fa fa-folder"></span> Apps</a>
                                    <a href="#" class="list-group-item"><span class="fa fa-cog"></span> Settings</a>
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
                                
                                    <h4 class="text-title">Photos</h4>
                                    <div class="gallery" id="links">                                                
                                        <a href="/assets/images/gallery/music-1.jpg" title="Music Image 1" class="gallery-item" data-gallery>
                                            <div class="image">
                                                <img src="/assets/images/gallery/music-1.jpg" alt="Music Image 1"/>
                                            </div>                                            
                                        </a>
                                        <a href="/assets/images/gallery/music-2.jpg" title="Music Image 2" class="gallery-item" data-gallery>
                                            <div class="image">
                                                <img src="/assets/images/gallery/music-2.jpg" alt="Music Image 2"/>
                                            </div>                                            
                                        </a>
                                        <a href="/assets/images/gallery/music-3.jpg" title="Music Image 3" class="gallery-item" data-gallery>
                                            <div class="image">
                                                <img src="/assets/images/gallery/music-3.jpg" alt="Music Image 3"/>
                                            </div>                                            
                                        </a>
                                        <a href="/assets/images/gallery/nature-1.jpg" title="Nature Image 1" class="gallery-item" data-gallery>
                                            <div class="image">
                                                <img src="/assets/images/gallery/nature-1.jpg" alt="Nature Image 1"/>
                                            </div>                                            
                                        </a>
                                        <a href="/assets/images/gallery/nature-2.jpg" title="Nature Image 2" class="gallery-item" data-gallery>
                                            <div class="image">
                                                <img src="/assets/images/gallery/nature-2.jpg" alt="Nature Image 2"/>
                                            </div>                                            
                                        </a>
                                        <a href="/assets/images/gallery/nature-3.jpg" title="Nature Image 3" class="gallery-item" data-gallery>
                                            <div class="image">
                                                <img src="/assets/images/gallery/nature-3.jpg" alt="Nature Image 3"/>
                                            </div>                                            
                                        </a>
                                        <a href="/assets/images/gallery/nature-4.jpg" title="Nature Image 4" class="gallery-item" data-gallery>
                                            <div class="image">
                                                <img src="/assets/images/gallery/nature-4.jpg" alt="Nature Image 4"/>
                                            </div>                                            
                                        </a>
                                        <a href="/assets/images/gallery/nature-5.jpg" title="Nature Image 5" class="gallery-item" data-gallery>
                                            <div class="image">
                                                <img src="/assets/images/gallery/nature-5.jpg" alt="Nature Image 5"/>
                                            </div>                                            
                                        </a>                                        
                                    </div>
                                </div>
                            </div>                            
                            
                        </div>
                        
                        <div class="col-md-9">

                                                       
                            
                        </div>
                        
                    </div>

                </div>
                <!-- END PAGE CONTENT WRAPPER -->

    
</div>

</body>
</html>