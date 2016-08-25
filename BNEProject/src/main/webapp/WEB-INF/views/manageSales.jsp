<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%
	Calendar cal = Calendar.getInstance();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
        <link rel="icon" href="favicon.ico" type="image/x-icon" />
        <link rel="stylesheet" type="text/css" id="theme" href="/css/theme-default.css"/>
      <script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
<link rel="stylesheet" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.3/themes/smoothness/jquery-ui.css">
</head>

<body>
<div class="row pull-right">
	<input type="hidden" id="manageSalesLength" value="${ fn:length(manageSales)}">

	
</div>
	 <!-- START RESPONSIVE TABLES -->
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel panel-default">

                                <div class="panel-heading">
                                    <h3 class="panel-title"> Modify Sales Goal</h3>
                                    <input class="pull-right" type="text" id="demo-2" >
                                </div>

                                <div class="panel-body panel-body-table">

                                    <div class="table-responsive">
                                        <table class="table table-bordered table-striped table-actions">
                                            <thead>
                                                <tr>
                                                    <th width="50">id</th>
                                                    <th width="100">name</th>
                                                    <th width="110">Sales Goal</th>
                                                    <th width="50">수정</th>
                                                </tr>
                                            </thead>
                                            <tbody>                                            
                                            
                                            
                                                <c:forEach items="${manageSales}" var="list" varStatus="status">
                                                <tr id="trow_${status.count} ">
													<td id="maSaa${status.index}"><strong>${list.employee_id}</strong></td>
													<td><strong>${list.employee_name}</strong></td>
													<td id="maSa${status.index}" isEmpty="1">0</td>
													<td>
                                                        <button id="manageSalesButton${status.index}" class="btn btn-default btn-rounded btn-sm"><span class="fa fa-pencil"></span></button>
                                                    </td>
													<%-- ${list.sales_goal} <br/> --%>
                                                </tr>
												</c:forEach>
                                                <input type="hidden" id="MS_department_id" value="${manageSales[0].department_id}"> 
                                                <input type="hidden" ID="MS_listSize" >
                                                
                                            </tbody>
                                        </table>
                                    </div>                                

                                </div>
                            </div>                                                

                        </div>
                    </div>
                    <!-- END RESPONSIVE TABLES -->
                    
                     <!-- START SCRIPTS -->
        <!-- START PLUGINS -->
<!--         <script type="text/javascript" src="/js/plugins/jquery/jquery.min.js"></script> -->
<!--         <script type="text/javascript" src="/js/plugins/jquery/jquery-ui.min.js"></script> -->
<!--         <script type="text/javascript" src="/js/plugins/bootstrap/bootstrap.min.js"></script>         -->
        <!-- END PLUGINS -->
        
        <!-- START THIS PAGE PLUGINS-->        
        <script type='text/javascript' src='/js/plugins/icheck/icheck.min.js'></script>
        <script type="text/javascript" src="/js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js"></script>
        
        <script type="text/javascript" src="/js/demo_tables.js"></script>     
        <!-- END THIS PAGE PLUGINS-->  
        
        <!-- START TEMPLATE -->
        <script type="text/javascript" src="/js/jquery.mtz.monthpicker.js"></script>
<!--         <script type="text/javascript" src="/js/settings.js"></script> -->
<!--         <script type="text/javascript" src="/js/plugins.js"></script>         -->
<!--         <script type="text/javascript" src="/js/actions.js"></script>         -->
        <!-- END TEMPLATE -->
    <!-- END SCRIPTS -->    
    <script>
    var aaaa='<%=cal.get(Calendar.YEAR)%>';
    $('#demo-2').monthpicker({pattern: 'yyyy-mm', 
        selectedYear: aaaa,
        startYear: 1900,
        finalYear: 2212,});
    	var options = {
        selectedYear: aaaa,
        startYear: aaaa,
        finalYear: aaaa+10,
        openOnFocus: false // Let's now use a button to show the widget
    };
  	
   	/* dept=? 인 사원 수만큼 click 이벤트리스너 연결*/
    for(var i=0;i<$('#manageSalesLength').val();i++){
	    
    	
    	/* ramda function*/
    	(function(a){	    	
	    var t=0;
	    var cnt=0;
   		var modifyFlag=true;
   		
		$("#manageSalesButton"+a).on("click", function() {
				var th = $(this).parent().prev();
				var ts=$('#demo-2').val().split("-");
				var tty = +'<%=cal.get(Calendar.YEAR)%>';
				var ttm = +'<%=cal.get(Calendar.MONTH)+1%>';
				
				if(tty>ts[0]){
					alert('과거 data는 더 이상 수정 할 수 없습니다.');
					return;
				}else if(tty==(+ts[0]) && ttm>(+ts[1])){
					alert('과거 data는 더 이상 수정 할 수 없습니다.');
					return;
				}
				
				if(modifyFlag){
					t = th.text();
					th.html('');
					th.append("<input id='bbb"+a+"' type=text value='"+t+"'/>");
					th.children().focus(); 
					modifyFlag=false;
					
					
					$('#bbb'+a).on("change",function(){
					
						var tempStr=$('#demo-2').val().split("-");
						
						/* 정규식 패턴검사 */
						var pattern = /^([1-9][0-9]{0,9}$)|^0$/;
						var tt = $('#bbb'+a).val();
						var true_false=pattern.test(tt);
												
						if(true_false){
							th.html($('#bbb'+a).val());
						}else{
							alert('숫자만 입력 가능합니다.');
							th.empty();
							th.html(t);
							modifyFlag=true;
							return;
						}

						
						/*  ajax로 수정된 값 db에 넣기 */
						$.ajax({
	                		type : "POST",
	                		url : "/counselling/insertSalesGoal",
	                		data : {
	                			employee_id:$("#maSaa"+a).text(),
	                			year:tempStr[0],
	                			month:tempStr[1],
	                			sales_goal:tt,
								is_empty:$("#maSa"+a).attr("isEmpty")
	                		},
	                		success : function(data) {
	                			$("#maSa"+a).attr("isEmpty","0");
	                			modifyFlag=true;
	                		},
	                		error : function() {
	                			alert('error');
	                		}
	                	});
						
					});
					
				}else{
					th.empty();
					th.html(t);
					modifyFlag=true;
				}
		});
		})(i);
    }
   	
   	$('#demo-2').val('<%=cal.get(Calendar.YEAR)%>'+'-'+'<%=cal.get(Calendar.MONTH)+1%>');
    

	/* 맨처음 날짜로 뿌려준다*/
    counsellingAjax();
    
</script>       
</body>
</html>