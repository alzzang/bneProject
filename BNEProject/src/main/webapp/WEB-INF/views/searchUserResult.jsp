<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<!-- <h1>hello</h1> -->
<%-- <h1>${fn:length(depUserList)}</h1> --%>
<style>
.salesInput {
   width:100%;
   background-color: transparent;
   border:0px solid black;
   text-align: center;
}
</style>


<div class="content-frame">
   <div class="content-frame-top">
      <div class="page-title">
         <h2>사원 정보</h2>
      </div>
   </div>
   <div class="content-frame-left">
      <div class="panel panel-default">
         <div class="panel-body profile"
            style="background: url('/assets/images/gallery/music-4.jpg') center center no-repeat;">
            <div class="profile-image">
               <img src="http://192.168.1.18:8085/test/${emp.file_position }" alt="Nadia Ali" />
            </div>
            <div class="profile-data">
               <div class="profile-data-name">${emp.employee_name}</div>
               <div class="profile-data-title" style="color: #FFF;">${emp.department_name }</div>
            </div>
            <div class="profile-controls">
<!--                <a href="#" class="profile-control-left twitter"><span
                  class="fa fa-twitter"></span></a> <a href="#"
                  class="profile-control-right facebook"><span
                  class="fa fa-facebook"></span></a> -->
            </div>
         </div>
         <div class="panel-body">
            <div class="row">
               <div class="col-md-6">
               <a href="tel:${emp.mobile_phone }">
                  <button class="btn btn-info btn-rounded btn-block">
                     <span class="glyphicon glyphicon-earphone"></span> Call
                  </button>
                  <input type="hidden" value="${emp.mobile_phone }">
               </a>
               </div>
               <div class="col-md-6">
               <a href="mailto:${emp.email }">
                  <button class="btn btn-primary btn-rounded btn-block">
                     <span class="glyphicon glyphicon-envelope"></span> e-mail
                  </button>
                  <input type="hidden" value="${emp.email }">
               </a>
               </div>
            </div>
          </div>
<%--         <div class="panel-body list-group border-bottom">

            <!--  ARCODIAN DROPDOWN MENU -->
            <div class="w3-accordion w3-light-grey">
               <button onclick="myFunction('Demo1')" class="w3-btn-block w3-left-align">Department Groups</button>
               <div id="Demo1" class="w3-accordion-content w3-container">
                  <c:forEach items="${depUserList }" var="list" varStatus="status">
                     <c:if test="${list.employee_id != employeeDTO.employee_id}">
                        <c:if test="${list.employee_id != emp.employee_id}">
                           <a href="/user/searchUser/${list.employee_id}"><span>${list.employee_id}</span>${list.employee_name}</a>
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
         </div> --%>
         
         <div class="panel-body">
            <h2 class="text-title">Employee <br>Information</h2>
            <div class="contact-info">
               <p>
                  <small>Department</small><br />${emp.department_name}</p>
               <p>
                  <small>Phone Number</small><br />${emp.mobile_phone}</p>
               <p>
                  <small>E-Mail</small><br />${emp.email}</p>
               <p>
                  <small>Position</small><br />${emp.position}</p>
            </div>


         </div>
         
         <!-- 부서 팀원 목록 -->
         <div class="panel-body">
            <h2 class="text-title">Team <br>Members</h2>
            <div class="row">
               <c:forEach items="${depUserList }" var="list" varStatus="status">
                  <c:if test="${list.employee_id != employeeDTO.employee_id}">
                      <c:if test="${list.employee_id != emp.employee_id}">
                      	<c:if test="${list.position != 'manager'}">
	                        <div class="col-md-4 col-xs-4">
	                           <a href="/user/searchUser/${list.employee_id}" class="friend">
	                              <img src="http://192.168.1.18:8085/test/${list.file_position }">
	                              <span>${list.employee_name}<br></span>
	                           </a>
	                        </div>
                        </c:if>
                     </c:if>
                  </c:if>
               </c:forEach>
            </div>
         </div>
      </div>

   </div>
   <div class="content-frame-body">
      <div class="row">
         <div class="col-md-12">
            <div class="panel panel-default">
               <div class="panel-heading">
                  <h3 class="panel-title">주간 계획</h3>
               </div>
               <div class="panel-body">
                  <div id="calander" class="calendar">
                     <div id="calendar" class="fc fc-ltr fc-unthemed "></div>
                  </div>
                  <div class=""></div>
               </div>
            </div>
         </div>
<!--          <div class="col-md-3">
            START BAR CHART
            <div class="panel panel-default">
               <div class="panel-heading">
                  <h3 class="panel-title">거래처별 매출</h3>
               </div>
               <div class="panel-body">
                  <div class="row stacked" id="customerSales" style="height: 300px;"></div>
               </div>
            </div>
            END Area CHART
         </div> -->
         <div class="col-md-9 col-sm-6">
            <!-- START BAR CHART -->
            <div class="panel panel-default">
               <div class="panel-heading">
                  <h3 class="panel-title">사원 매출 차트</h3>
               </div>
               <div class="panel-body">
                  <div id="morris-line-example" style="height: 300px;"></div>
               </div>
            </div>
            <!-- END Area CHART -->
         </div>
         <div class="col-md-3 col-sm-6">
         <!-- START DONUT CHART -->
         <div class="panel panel-default">
            <div class="panel-heading">
               <h3 class="panel-title">사원 이번달 달성률</h3>
            </div>
            <div class="panel-body">
               <div id="morris-donut-example" style="height: 300px;"></div>
            </div>
         </div>
         <!-- END DONUT CHART -->
         </div>
      </div>
   </div>
</div>
<script type="text/javascript" src="/js/plugins/jquery/jquery.min.js"></script>
<script type="text/javascript" src="/js/plugins/jquery/jquery-ui.min.js"></script>
<script type="text/javascript" src="/js/plugins/morris/raphael-min.js"></script>
<script type="text/javascript" src="/js/plugins/morris/morris.min.js"></script>
<script type="text/javascript" src="/js/weekly.js"></script>

<script>
$(window).load(function(){

   // 달성도, 매출 차트
   var mainpageMorrisCharts = function() {
      $.ajax({
         type : "POST",
         url : "/chart/monthlySales",
         data : {
            employee_id : '${emp.employee_id }'
         },
         dataType : 'json',
         success : function(data) {
               
            var achievement = Math.round((data.sumofMonthlyGoal / data.monthlyGoal) * 100);
                     
            if(isNaN(achievement)||!isFinite(achievement)){
               achievement=0;
            }
   
            var achievermentLimit = achievement;
               
            if(achievement>=100){
               achievermentLimit=100;
            }
                  
            Morris.Donut({
               element : 'morris-donut-example',
               data : [ {
                  label : "achievement rate",
                  value : achievement,
                  formatted : achievement+'%'
               }, {
                  label : "lack of achievement rate",
                  value : 100-achievermentLimit,
                  formatted : 100-achievermentLimit+'%'
               } ],
               colors : [ '#95B75D', '#1caf9a'],
               formatter : function(x, data) {
                  return data.formatted;
               },
                 resize: true
            });
         },
         error : function() {
            alert("nono");
         }
      });

      $.ajax({
         type : "POST",
         url : "/chart/morrisChartLine",
         data : {
            // login employee_id 받아오기
            employee_id : '${emp.employee_id }',
            department_id: '${emp.department_id }'
            
         },
         dataType : 'json',
   
         success : function(data) {
            var jsonLoop = new Array();
            
            list1 = data.List1;
            list2 = data.List2;
            var d = new Date();
             var n = d.getFullYear();
            if(list2.length==0){
   
   //            $("#morris-line-example").append('<span>no data</span>');
               jsonLoop.push({
                  y : n+'',
                  a : 0,
                  b : 0
               });
            }
            var chartFlag = true;
            
            for (var i = 0; i < list2.length; i++) {
   
               for (var j = 0; j < list1.length; j++) {
                  if (list2[i].reg_date === list1[j].reg_date) {
   
                     jsonLoop.push({
                        y : list2[i].reg_date,
                        a : list1[j].sales,
                        b : list2[i].sales
                     });
                     chartFlag = false;
                  }
               }
               if (chartFlag) {
                  jsonLoop.push({
                     y : list2[i].reg_date,
                     a : 0,
                     b : list2[i].sales
                  });
               }
               chartFlag = true;
            }
            Morris.Line({
               element : 'morris-line-example',
               data : jsonLoop,
               xkey : 'y',
               ykeys : [ 'a', 'b' ],
               labels : [ 'My Sales', 'Team Sales Avg' ],
               resize : true,
               lineColors : [ '#33414E', '#95B75D' ]
            });
         },
         error : function() {
            alert('error');
         }
      });
   }

   mainpageMorrisCharts();
   
   // 거래처별 매출
   var customerSales = function(){
      
      $.ajax({
         type : "POST",
         url : "/chart/customersSales",
         data : {
            department_id : '${emp.department_id }'
         },
         dataType : 'json',
         success : function(data) {
            
            for(var i=0; i<data.length;i++){
               var html= "<div class='progress-list'> " +
                     "<div class='pull-left'>" +
                     "<strong>" +
                     data[i].client_name +
                     "</strong></div> " +
                     "<div class='pull-right'>" +
                     data[i].sales +"만"+
                     "</div> " +
                     "<div class='progress progress-small progress-striped active'>"+
                     "<div class='progress-bar progress-bar-primary' role='progressbar' aria-valuenow='50' aria-valuemin='0' aria-valuemax='100' style='width: " +
                     data[i].sales_rate +
                     "%;'>"+
                     data[i].sales_rate+
                     "</div></div></div>";
               $("#customerSales").append(html);
            }
         },
         error : function() {
            alert("nono");
         }
      });
      
   }
   
   customerSales();

   
   // 주간계획
   var makeCurrentReportId = function(){
      var weeklyNumberText = $('.fc-week-number>span')[0].textContent;
      var weeklyNumber = parseInt(weeklyNumberText[1] + weeklyNumberText[2]);
    
      var date = $('#calendar').fullCalendar('getDate');
      var year = date._d.getFullYear();
      
      return year +"_"+ weeklyNumber + "_" + '${emp.employee_id}';
   };
   
   makeSalesInput();
   $.ajax({
      type : "POST",
      url : "/weeklyReport/getPlan",
      data : {
         ReportId : makeCurrentReportId()
      },
      success : function(data){
		if(data.weeklyReportDTO == null){
			var day = ['mon','tue','wed','thu','fri'];
			for(var i=0; i<5; i++){
				$('input[id="sales-'+day[i]+'"]').attr({'value': '0', 'disabled':'disabled'});
			}
        }else{
	        mainInputReportData(data);
        }
      }
   })
   
});
</script>