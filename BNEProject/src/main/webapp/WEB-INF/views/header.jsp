<%@page import="kr.co.bne.dto.EmployeeDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
.modal-dialog{
 overflow-y: scroll; max-height:85%;
}
.modal-content{
border-width: 0px !important;
}
</style>
<ul class="x-navigation x-navigation-horizontal x-navigation-panel">
	<li class="xn-icon-button">
		<a href="#" class="x-navigation-minimize"><span class="fa fa-dedent"></span></a>
	</li>


	<!-- SEARCH -->
	<%
		if (!((EmployeeDTO) session.getAttribute("user")).getPosition().equals("admin")) {
	%>
	<li class="xn-search"><input type="text" id="empSearch"
		name="search" placeholder="Search..."></li>
	<%
		}
	%>
	<!-- END SEARCH -->
	<!-- SIGN OUT -->
	<li class="xn-icon-button pull-right"><a href="#"
		class="mb-control" data-box="#mb-signout"><span
			class="fa fa-sign-out"></span></a></li>
	<!-- END SIGN OUT -->
	<!-- MESSAGES -->
	<%if(!((EmployeeDTO)session.getAttribute("user")).getPosition().equals("admin")) {%>
	<li class="xn-icon-button pull-right" id="noticeButton"
		onclick='getNoticeList(1, 5)'><a href="#"><span
			class="fa fa-bell"></span></a>
		<div class="informer informer-warning" id="newMessageCount_title">-</div>
		<div class="informer informer-danger" id="appendtest"></div>

		<div
			class="panel panel-primary animated zoomIn xn-drop-left xn-panel-dragging ui-draggable">
			<div class="panel-heading ui-draggable-handle">
				<h3 class="panel-title">
					<span class="fa fa-bell"></span> Notices
				</h3>
				<div class="pull-right">
					<span class="label label-danger" id="newMessageCount">
						<!-- new notice 개수 들어가는 부분 -->
					</span>
				</div>
			</div>
			<div
				class="panel-body list-group list-group-contacts scroll mCustomScrollbar _mCS_2 mCS-autoHide mCS_no_scrollbar"
				style="height: 200px;">
				<div id="mCSB_2"
					class="mCustomScrollBox mCS-light mCSB_vertical mCSB_inside"
					tabindex="0">
					<div id="mCSB_2_container"
						class="mCSB_container mCS_y_hidden mCS_no_scrollbar_y"
						style="position: relative; top: 0; left: 0;" dir="ltr">
						<!-- notice list 들어가는 부분 -->
					</div>
					<div id="mCSB_2_scrollbar_vertical"
						class="mCSB_scrollTools mCSB_2_scrollbar mCS-light mCSB_scrollTools_vertical"
						style="display: none;">
						<div class="mCSB_draggerContainer">
							<div id="mCSB_2_dragger_vertical" class="mCSB_dragger"
								style="position: absolute; min-height: 30px; top: 0px; display: block; height: 162px; max-height: 190px;"
								oncontextmenu="return false;">
								<div class="mCSB_dragger_bar" style="line-height: 30px;"></div>
							</div>
							<div class="mCSB_draggerRail"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="panel-footer text-center">
				<a href="/alarm/detail">Show all messages</a>
			</div>
		</div></li>
	<%} %>
	<!-- END MESSAGES -->


</ul>


<div id="myModal86"  class="modal fade" role="dialog">
   <div class="modal-dialog modal-admin">

      <!-- Modal content-->
      <div class="modal-content">
         <div class="modal-header">
         
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4 class="modal-title"><span class="fa fa-arrow-circle-o-left"></span> 부서 인원</h4>
            
         </div>
         
         <div class="modal-body">
<div class="page-title">
          <h2>
            
         </h2>  
      </div> 

         <div class="page-content-wrap">
            
         </div>
         </div>
         <div class="modal-footer">
         </div>
      </div>
   </div>
</div>
<input type="hidden" href="#" data-toggle="modal" data-target="#myModal86" id="modalAdd86"></button>

