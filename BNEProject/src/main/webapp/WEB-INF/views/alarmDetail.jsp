<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- <style>
 #log{
	position:fixed;
	top:0;
	left:0;
	width:100px;
	height:50px;
	background-color:rgba(0,0,0,0.7);
	color:white;
	text-align:center;
	line-height:50px;
}
</style> -->
<script>
var tasksIdx=2;
var tasksCompleteIdx=2;
var timer = true;
var timer1 = true;
$(window).load(function(){
	
	var $tasks=$("#tasks");
	var $tasksComplete=$("#tasks_completed");
	
	$tasks.scroll(function(){
		if($("#tasks").prop("scrollHeight")-$("#tasks").scrollTop()==640){
			 if (timer) {
				unconfirmedData(); // 실행
			} 
			//alert('aa');
			//sendData();
			tasksIdx++;
		};
	});
	$tasksComplete.scroll(function(){
		if($("#tasks_completed").prop("scrollHeight")-$("#tasks_completed").scrollTop()==640){
			if (timer1) {
				confirmedData(); // 실행
			} 
			//alert('bb');
			tasksCompleteIdx++;
		};
	});

});
function unconfirmedData(){
		timer = false;

		$.ajax({
					type : "POST",
					url : '/alarm/unconfirmed', // url에
					// 주소 넣기
					data : {
						"start" : tasksIdx,
					},
					dataType : 'json',
					error : function() {
						alert("건들지마 멍청아!!");
					},
					success : function(data) {

						var str = JSON.stringify(data);
						var list = $.parseJSON(str);
						console.log(list);
						var len = Object.keys(list).length
						if(len>0){
							for(var i=0;i<len;i++){
								var html="";
								if(list[i].notice_type=="DAILY_POST"){
									html='<div class="task-item task-primary">';
								}else if(list[i].notice_type=="WEEKLY_POST"){
									html='<div class="task-item task-success">';
								}else if(list[i].notice_type=="DAILY_CORRECT"){
									html='<div class="task-item task-warning">';
								}else if(list[i].notice_type=="WEEKLY_CORRECT"){
									html='<div class="task-item task-danger">';
								}
								html+='<div class="task-text ui-sortable-handle">'+list[i].content+'</div>';
								html+='<div class="task-footer">'+'<div class="pull-left"><span class="fa fa-clock-o"></span>'+list[i].passtime+'</div>';
								html+='</div></div>';
								$('#tasks').append(html);
								
								setTimeout(function() {
									timer = true;
								}, 500)
							}
						}
						//var ip = $('#ip').val();
						//var kind = $('#kind').val();
					}
		});
}
function confirmedData(){
	timer1 = false;

	$.ajax({
				type : "POST",
				url : '/alarm/confirmed', // url에
				// 주소 넣기
				data : {
					"start" : tasksCompleteIdx,
				},
				dataType : 'json',
				error : function() {
					alert("건들지마 멍청아!!");
				},
				success : function(data) {

					var str = JSON.stringify(data);
					var list = $.parseJSON(str);
					console.log(list);
					var len = Object.keys(list).length
					if(len>0){
						for(var i=0;i<len;i++){
							var html="";
							if(list[i].notice_type=="DAILY_POST"){
								html='<div class="task-item task-primary task-complete">';
							}else if(list[i].notice_type=="WEEKLY_POST"){
								html='<div class="task-item task-success task-complete">';
							}else if(list[i].notice_type=="DAILY_CORRECT"){
								html='<div class="task-item task-warning task-complete">';
							}else if(list[i].notice_type=="WEEKLY_CORRECT"){
								html='<div class="task-item task-danger task-complete">';
							}
							html+='<div class="task-text ui-sortable-handle">'+list[i].content+'</div>';
							html+='<div class="task-footer">'+'<div class="pull-left"><span class="fa fa-clock-o"></span>'+list[i].passtime+'</div>';
							html+='</div></div>';
							$('#tasks_completed').append(html);
							
							setTimeout(function() {
								timer1 = true;
							}, 500)
						}
					}
					//var ip = $('#ip').val();
					//var kind = $('#kind').val();
				}
	});
}
function testAdd(){
	var html='<div class="task-item task-success">';                                    
    html+='<div class="task-text ui-sortable-handle">Suspendisse a tempor eros. Curabitur fringilla maximus lorem, eget congue lacus ultrices eu. Nunc et molestie elit. Curabitur consectetur mollis ipsum, id hendrerit nunc molestie id.</div>';
    html+='<div class="task-footer"><div class="pull-left"><span class="fa fa-clock-o"></span> 1h 45min</div>';
    html+='<div class="pull-right"><a href="#"><span class="fa fa-chain"></span></a> <a href="#"><span class="fa fa-comments"></span></a></div></div></div>';
	$('#tasks_completed').append(html);
}
</script> 
<!-- <style>
.pre-scrollable, .myDiv1{
    height: 90px;
    width: 300px;
    border: 1px solid;
    background-color: lavender;
    overflow: scroll;
}
</style> -->
<div class="content-frame">  
<button id="btn1" onclick="testAdd()">aaa</button>   
                    <!-- START CONTENT FRAME TOP -->
                    <div class="content-frame-top">                        
                        <div class="page-title">                    
                            <h2><span class="fa fa-bell"></span> 알림</h2>
                        </div>                                                
                        <div class="pull-right">
                            <button class="btn btn-default content-frame-left-toggle"><span class="fa fa-bars"></span></button>
                        </div>                                
                        <div class="pull-right" style="width: 100px; margin-right: 5px;">
                            <select class="form-control select" style="display: none;">
                                <option>All</option>                                
                                <option>Work</option>
                                <option>Home</option>
                                <option>Friends</option>
                                <option>Closed</option>
                            </select><!-- <div class="btn-group bootstrap-select form-control select"><button type="button" class="btn dropdown-toggle selectpicker btn-default" data-toggle="dropdown" title="All"><span class="filter-option pull-left">All</span>&nbsp;<span class="caret"></span></button><div class="dropdown-menu open"><ul class="dropdown-menu inner selectpicker" role="menu"><li rel="0" class="selected"><a tabindex="0" class="" style=""><span class="text">All</span><i class="glyphicon glyphicon-ok icon-ok check-mark"></i></a></li><li rel="1"><a tabindex="0" class="" style=""><span class="text">Work</span><i class="glyphicon glyphicon-ok icon-ok check-mark"></i></a></li><li rel="2"><a tabindex="0" class="" style=""><span class="text">Home</span><i class="glyphicon glyphicon-ok icon-ok check-mark"></i></a></li><li rel="3"><a tabindex="0" class="" style=""><span class="text">Friends</span><i class="glyphicon glyphicon-ok icon-ok check-mark"></i></a></li><li rel="4"><a tabindex="0" class="" style=""><span class="text">Closed</span><i class="glyphicon glyphicon-ok icon-ok check-mark"></i></a></li></ul></div></div> -->
                        </div>
                        
                    </div>                    
                    <div class="content-frame-left" style="height: 837px;">
                                            
                        <div class="form-group">
                            <h4>Task groups:</h4>
                            <div class="list-group border-bottom">
                                <a href="#" class="list-group-item"><span class="fa fa-circle text-primary"></span> 등록</a>
                                <a href="#" class="list-group-item"><span class="fa fa-circle text-success"></span> 커멘트</a>
                                <a href="#" class="list-group-item"><span class="fa fa-circle text-warning"></span> 승인</a>
                                <a href="#" class="list-group-item"><span class="fa fa-circle text-danger"></span> 수정</a>
                                <!-- <a href="#" class="list-group-item"><span class="fa fa-circle text-info"></span> Work</a> -->
                            </div>
                        </div>
                        
                    </div>       
                    <!-- END CONTENT FRAME TOP -->
                    
                    <!-- START CONTENT FRAME BODY -->
                    <div class="content-frame-body" style="height: 897px;">
                                                
                        <div class="row push-up-10" >
                        	<div>
                            <div class="col-md-6">
                                
                                <h3>UnConfirmed List</h3>
                                <div id="log">
                            
                                <div class="tasks ui-sortable pre-scrollable" id="tasks">
									<c:forEach items="${unList}" var="ulist">
										<c:if test="${ulist.notice_type eq 'DAILY_POST'}">
											<div class="task-item task-primary" onclick="alert('aa')">
										</c:if>
										<c:if test="${ulist.notice_type eq 'WEEKLY_POST'}">
											<div class="task-item task-success" onclick="alert('aa')">
										</c:if>
										<c:if test="${ulist.notice_type eq 'DAILY_CORRECT'}">
											<div class="task-item task-warning" onclick="alert('aa')">
										</c:if>
										<c:if test="${ulist.notice_type eq 'WEEKLY_CORRECT'}">
											<div class="task-item task-danger" onclick="alert('aa')">
										</c:if>
										                      
                                        	<div class="task-text ui-sortable-handle">${ulist.content}</div>
                                        	<div class="task-footer">
                                            	<div class="pull-left"><span class="fa fa-clock-o"></span>${ulist.passtime}</div>                                    
                                        	</div>                                    
                                    	</div>
									</c:forEach>
									<!-- <img src="/img/ajax-loading.gif" width="390px;"> -->
									</div> 
                                   
                                </div>                            

                            </div>
                            </div>



                            <div class="col-md-6">
                                <h3>Confirmed List</h3>
                                <div class="tasks ui-sortable pre-scrollable" id="tasks_completed">
                                    
                                    <c:forEach items="${cnList}" var="clist">
										<c:if test="${clist.notice_type eq 'DAILY_POST'}">
											<div class="task-item task-primary task-complete">
										</c:if>
										<c:if test="${clist.notice_type eq 'WEEKLY_POST'}">
											<div class="task-item task-success task-complete">
										</c:if>
										<c:if test="${clist.notice_type eq 'DAILY_CORRECT'}">
											<div class="task-item task-warning task-complete">
										</c:if>
										<c:if test="${clist.notice_type eq 'WEEKLY_CORRECT'}">
											<div class="task-item task-danger task-complete">
										</c:if>
										                    
                                        	<div class="task-text ui-sortable-handle">${clist.content}</div>
                                        	<div class="task-footer">
                                            	<div class="pull-left"><span class="fa fa-clock-o"></span>${clist.passtime}</div>                                    
                                        	</div>                                    
                                    	</div>
									</c:forEach>
                                    <!-- <div class="task-item task-danger task-complete">                                    
                                        <div class="task-text ui-sortable-handle">Donec maximus sodales feugiat.</div>
                                        <div class="task-footer">
                                            <div class="pull-left"><span class="fa fa-clock-o"></span> 15min</div>                                    
                                        </div>                                    
                                    </div>
                                    
                                    <div class="task-item task-info task-complete">                                    
                                        <div class="task-text ui-sortable-handle">Aliquam eget est a dui tincidunt commodo in nec ante.</div>
                                        <div class="task-footer">
                                            <div class="pull-left"><span class="fa fa-clock-o"></span> 35min</div>                                    
                                        </div>                                    
                                    </div>
                                     -->                                   
                                </div>
                            </div>
                        </div>                        
                                                
                    </div>
                    <!-- END CONTENT FRAME BODY -->
                    
                </div>