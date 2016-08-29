<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
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
         tasksIdx++;
      };
   });
   $tasksComplete.scroll(function(){
      if($("#tasks_completed").prop("scrollHeight")-$("#tasks_completed").scrollTop()==640){
         if (timer1) {
            confirmedData(); // 실행
         } 
         tasksCompleteIdx++;
      };
   });

});

function selectNoticeType(type){
   event.preventDefault();
   
   var form = document.createElement("form");     
   form.setAttribute("method","post");                    
   form.setAttribute("action","/alarm/detail");        
   document.body.appendChild(form);                        
    
   //input
   var input_id = document.createElement("input");  
   input_id.setAttribute("type", "hidden");                 
   input_id.setAttribute("name", "notice_type");                        
   input_id.setAttribute("value", type);                          
   form.appendChild(input_id);
    
   //폼전송
   form.submit();  
      
}

function unconfirmedData(){
      timer = false;

      $.ajax({
               type : "POST",
               url : '/alarm/unconfirmed', // url에
               // 주소 넣기
               data : {
                  "start" : tasksIdx,
                  "type" : $('#notice_type').val()
               },
               dataType : 'json',
               error : function() {
               },
               success : function(data) {

                  var str = JSON.stringify(data);
                  var list = $.parseJSON(str);
                  var len = Object.keys(list).length
                  if(len>0){
                     for(var i=0;i<len;i++){
                        var html="";
                        if(list[i].notice_type=="DAILY_POST"){
                           html='<div class="task-item task-primary" id="'+list[i].notice_id+'" onclick='+'\"moveLink(this.id,'+'\''+list[i].notice_type+'\''+','+list[i].link_id+','+list[i].subject+')'+'\"'+'>';
                        }else if(list[i].notice_type=="WEEKLY_POST"){
                           html='<div class="task-item task-success" id="'+list[i].notice_id+'" onclick='+'\"moveLink(this.id,'+'\''+list[i].notice_type+'\''+','+list[i].link_id+','+list[i].subject+')'+'\"'+'>';
                        }else if(list[i].notice_type=="DAILY_CORRECT"){
                           html='<div class="task-item task-warning" id="'+list[i].notice_id+'" onclick='+'\"moveLink(this.id,'+'\''+list[i].notice_type+'\''+','+list[i].link_id+','+list[i].subject+')'+'\"'+'>';
                        }else if(list[i].notice_type=="WEEKLY_CORRECT"){
                           html='<div class="task-item task-danger" id="'+list[i].notice_id+'" onclick='+'\"moveLink(this.id,'+'\''+list[i].notice_type+'\''+','+list[i].link_id+','+list[i].subject+')'+'\"'+'>';
                        }else if(list[i].notice_type=="APPROVAL"){
                           html='<div class="task-item task-primary" id="'+list[i].notice_id+'" onclick='+'\"moveLink(this.id,'+'\''+list[i].notice_type+'\''+','+list[i].link_id+','+list[i].subject+')'+'\"'+'>';
                        }else if(list[i].notice_type=="COMMENT"){
                           html='<div class="task-item task-warning" id="'+list[i].notice_id+'" onclick='+'\"moveLink(this.id,'+'\''+list[i].notice_type+'\''+','+list[i].link_id+','+list[i].subject+')'+'\"'+'>';
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
               }
      });
}
/* function moveLink(noticeId,type,linkId,subject){
   $.ajax({
      type : "POST",
      url : '/alarm/click',
      data : {
         "noticeId" : noticeId
      },
      success : function(data) {
         if(type=="DAILY_POST"){
            detailDaily(linkId);
         }else if(type=="WEEKLY_POST"){
            detailWeekly(linkId,subject);
         }else if(type=="DAILY_CORRECT"){
            detailDaily(linkId);
         }else if(type=="WEEKLY_CORRECT"){
            detailWeekly(linkId,subject);
         }else if(type=="APPROVAL"){
            detailDaily(linkId);
         }else if(type=="COMMENT"){
            detailDaily(linkId);
         }
      }
   });
   
} */
function confirmedData(){
   
   timer1 = false;

   $.ajax({
            type : "POST",
            url : '/alarm/confirmed', // url에
            // 주소 넣기
            data : {
               "start" : tasksCompleteIdx,
               "type" : $('#notice_type').val()
            },
            dataType : 'json',
            error : function() {
            },
            success : function(data) {

               var str = JSON.stringify(data);
               var list = $.parseJSON(str);

               var len = Object.keys(list).length
               if(len>0){
                  for(var i=0;i<len;i++){
                     var html="";
                     if(list[i].notice_type=="DAILY_POST"){
                        html='<div class="task-item task-primary task-complete"  id="'+list[i].notice_id+'" onclick='+'\"moveLink(this.id,'+'\''+list[i].notice_type+'\''+','+list[i].link_id+','+list[i].subject+')'+'\"'+'>';
                     }else if(list[i].notice_type=="WEEKLY_POST"){
                        html='<div class="task-item task-success task-complete"  id="'+list[i].notice_id+'"onclick='+'\"moveLink(this.id,'+'\''+list[i].notice_type+'\''+','+list[i].link_id+','+list[i].subject+')'+'\"'+'>';
                     }else if(list[i].notice_type=="DAILY_CORRECT"){
                        html='<div class="task-item task-warning task-complete"   id="'+list[i].notice_id+'"onclick='+'\"moveLink(this.id,'+'\''+list[i].notice_type+'\''+','+list[i].link_id+','+list[i].subject+')'+'\"'+'>';
                     }else if(list[i].notice_type=="WEEKLY_CORRECT"){
                        html='<div class="task-item task-danger task-complete"  id="'+list[i].notice_id+'" onclick='+'\"moveLink(this.id,'+'\''+list[i].notice_type+'\''+','+list[i].link_id+','+list[i].subject+')'+'\"'+'>';
                     }else if(list[i].notice_type=="APPROVAL"){
                        html='<div class="task-item task-primary task-complete"  id="'+list[i].notice_id+'" onclick='+'\"moveLink(this.id,'+'\''+list[i].notice_type+'\''+','+list[i].link_id+','+list[i].subject+')'+'\"'+'>';
                     }else if(list[i].notice_type=="COMMENT"){
                        html='<div class="task-item task-warning task-complete"  id="'+list[i].notice_id+'" onclick='+'\"moveLink(this.id,'+'\''+list[i].notice_type+'\''+','+list[i].link_id+','+list[i].subject+')'+'\"'+'>';
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
            }
   });
}

function removeUnconfirmed(noticeId,type,linkId,subject){
   event.stopPropagation();
   $.ajax({
      type : "POST",
      url : '/alarm/click',
      data : {
         "noticeId" : noticeId
      },
      success : function(data) {
         
         
         var $html=$('<div class="task-item task-nowconfirmed" id="'+noticeId+'" onclick='+'\"moveLink('+noticeId+','+'\''+type+'\''+','+linkId+','+subject+')'+'\"'+'>'+$('#'+noticeId+'').html()+'</div>');

         if(type=="DAILY_POST"){
            $html.addClass("task-primary")
         }else if(type=="WEEKLY_POST"){
            $html.addClass("task-success")
         }else if(type=="DAILY_CORRECT"){
            $html.addClass("task-warning")
         }else if(type=="WEEKLY_CORRECT"){
            $html.addClass("task-danger")
         }else if(type=="APPROVAL"){
            $html.addClass("task-primary")
         }else if(type=="COMMENT"){
            $html.addClass("task-warning")
         }
         $('#'+noticeId+'').remove();
         $('#tasks_completed').prepend($html);
         $('#'+noticeId+' a').remove();      
      }
   });
   
}
</script> 

<div class="content-frame">   
                    <!-- START CONTENT FRAME TOP -->
                    <div class="content-frame-top">                        
                        <div class="page-title">                    
                            <h2><span class="fa fa-bell"></span> 알림</h2>
                        </div>                                                
                        <div class="pull-right">
                            <button class="btn btn-default content-frame-left-toggle"><span class="fa fa-bars"></span></button>
                        </div>                        
                        
                        
                    </div>                    
                    <div class="content-frame-left" style="height: 837px;">
                                            
                        <div class="form-group">
                            <h4>Task groups:</h4>
                            <div class="list-group border-bottom">
                               <a href="#" class="list-group-item" onclick="selectNoticeType('')"><span class="fa fa-circle text-muted"></span> 전체</a>
                                <c:if test="${position eq 'manager'}">
                                <a href="#" class="list-group-item" onclick="selectNoticeType('DAILY_POST')"><span class="fa fa-circle text-primary"></span> 일일업무 등록</a>
                                <a href="#" class="list-group-item" onclick="selectNoticeType('DAILY_CORRECT')"><span class="fa fa-circle text-warning"></span> 일일업무 수정</a>
                                </c:if>
                                <c:if test="${position eq 'employee'}">
                                <a href="#" class="list-group-item" onclick="selectNoticeType('APPROVAL')"><span class="fa fa-circle text-primary"></span> 승인</a>
                                <a href="#" class="list-group-item" onclick="selectNoticeType('COMMENT')"><span class="fa fa-circle text-warning"></span> COMMENT</a>
                                </c:if>
                                <a href="#" class="list-group-item" onclick="selectNoticeType('WEEKLY_POST')"><span class="fa fa-circle text-success"></span> 주간업무 등록</a>
                                <a href="#" class="list-group-item" onclick="selectNoticeType('WEEKLY_CORRECT')"><span class="fa fa-circle text-danger"></span> 주간업무 수정</a>
                                
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
                                 <div class="task-item task-primary" id="${ulist.notice_id }" onclick="moveLink(this.id,'${ulist.notice_type}',${ulist.link_id},${ulist.subject })">
                              </c:if>
                              <c:if test="${ulist.notice_type eq 'WEEKLY_POST'}">
                                 <div class="task-item task-success" id="${ulist.notice_id }" onclick="moveLink(this.id,'${ulist.notice_type}',${ulist.link_id},${ulist.subject })">
                              </c:if>
                              <c:if test="${ulist.notice_type eq 'DAILY_CORRECT'}">
                                 <div class="task-item task-warning" id="${ulist.notice_id }" onclick="moveLink(this.id,'${ulist.notice_type}',${ulist.link_id},${ulist.subject })">
                              </c:if>
                              <c:if test="${ulist.notice_type eq 'WEEKLY_CORRECT'}">
                                 <div class="task-item task-danger" id="${ulist.notice_id }" onclick="moveLink(this.id,'${ulist.notice_type}',${ulist.link_id},${ulist.subject })">
                              </c:if>
                              <c:if test="${ulist.notice_type eq 'APPROVAL'}">
                                 <div class="task-item task-primary" id="${ulist.notice_id }" onclick="moveLink(this.id,'${ulist.notice_type}',${ulist.link_id},${ulist.subject })">
                              </c:if>
                              <c:if test="${ulist.notice_type eq 'COMMENT'}">
                                 <div class="task-item task-warning" id="${ulist.notice_id }" onclick="moveLink(this.id,'${ulist.notice_type}',${ulist.link_id},${ulist.subject })">
                              </c:if>
                                  <a class="pull-right" href="#" onclick="removeUnconfirmed(${ulist.notice_id },'${ulist.notice_type}',${ulist.link_id},${ulist.subject })"><span class="glyphicon glyphicon-remove"></span></a>                
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
                                 <div class="task-item task-primary task-complete" id="${clist.notice_id }" onclick="moveLink(this.id,'${clist.notice_type}',${clist.link_id})">
                              </c:if>
                              <c:if test="${clist.notice_type eq 'WEEKLY_POST'}">
                                 <div class="task-item task-success task-complete" id="${clist.notice_id }" onclick="moveLHink(this.id,'${clist.notice_type}',${clist.link_id})">
                              </c:if>
                              <c:if test="${clist.notice_type eq 'DAILY_CORRECT'}">
                                 <div class="task-item task-warning task-complete" id="${clist.notice_id }" onclick="moveLink(this.id,'${clist.notice_type}',${clist.link_id})">
                              </c:if>
                              <c:if test="${clist.notice_type eq 'WEEKLY_CORRECT'}">
                                 <div class="task-item task-danger task-complete" id="${clist.notice_id }" onclick="moveLink(this.id,'${clist.notice_type}',${clist.link_id})">
                              </c:if>
                              
                              <c:if test="${clist.notice_type eq 'APPROVAL'}">
                                 <div class="task-item task-primary task-complete" id="${clist.notice_id }" onclick="moveLink(this.id,'${clist.notice_type}',${clist.link_id})">
                              </c:if>
                              <c:if test="${clist.notice_type eq 'COMMENT'}">
                                 <div class="task-item task-warning task-complete" id="${clist.notice_id }" onclick="moveLink(this.id,'${clist.notice_type}',${clist.link_id})">
                              </c:if>
                                                  
                                           <div class="task-text ui-sortable-handle">${clist.content}</div>
                                           <div class="task-footer">
                                               <div class="pull-left"><span class="fa fa-clock-o"></span>${clist.passtime}</div>                                    
                                           </div>                                    
                                       </div>
                           </c:forEach>
                           <input type="hidden" id="notice_type"   value="${type}">
                                                                    
                                </div>
                            </div>
                        </div>                        
                                                
                    </div>
                    <!-- END CONTENT FRAME BODY -->
                </div>