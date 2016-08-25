<%@page import="kr.co.bne.dto.EmployeeDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<%
String position = null;

if(((EmployeeDTO) session.getAttribute("user")).getPosition() != null) {
	position = ((EmployeeDTO) session.getAttribute("user")).getPosition();
}
%>



<div id="mCSB_1"
	class="mCustomScrollBox mCS-light mCSB_vertical mCSB_inside"
	tabindex="0">


	<div id="mCSB_1_container"
		class="mCSB_container mCS_y_hidden mCS_no_scrollbar_y"
		style="position: relative; top: 0; left: 0;" dir="ltr">


		<!-- START X-NAVIGATION -->




		<ul class="x-navigation">

			<li class="xn-logo"><a href="/main">BnE</a> <a href="#"
				class="x-navigation-control"></a></li>



			<li class="xn-profile active"><a href="#" class="profile-mini">
					<img src="http://192.168.1.18:8085/test/${sessionScope.user.file_position}">
			</a>
				<div class="profile">
					<div class="profile-image">

						<a href="#"> <span class="fa fa-gear "
							style="position: absolute"> </span> <img
							src="http://192.168.1.18:8085/test/${sessionScope.user.file_position}">
						</a>

					</div>
					<div class="profile-data">
						<div class="profile-data-name">${sessionScope.user.employee_name}</div>
						<div class="profile-data-title">${sessionScope.user.department_name}</div>
					</div>
					<div class="profile-controls">
						<a href="/user/changeProfile" class="profile-control-left"> <span
							class="fa fa-gear"></span></a> <a href="#"
							class="profile-control-right" id="sockettest"> <span
							class="fa fa-envelope"></span></a>
					</div>

				</div></li>



			<li class="xn-title">Navigation</li>
			<%if (!position.equals("admin")) { %>
			<li class=""><a href="/main"><span class="fa fa-desktop"></span>
					<span class="xn-text">Home</span></a></li>
			<li class="xn-openable"><a href="#"> <!-- weeklyReport/write -->
					<span class="fa fa-calendar-o"></span> <span class="xn-text">주간
						계획</span>
			</a>
				<ul>
					<%if (!position.equals("manager")) { %>
					<li><a href="/weeklyReport/detail/${user.getEmployee_id() }">내
							주간 계획</a></li>
					<%} %>
					<li class="xn-openable"><a id="weeklyDetail">팀원의 주간 계획</a>
						<ul id="members">
						</ul></li>
					<%if (!position.equals("manager")) { %>
					<li><a href="/weeklyReport/writeForm">주간계획작성</a></li>
					<%} %>
				</ul></li>


			<c:choose>
				<c:when test="${ sessionScope.user.position == 'manager' }">
					<li class="xn-openable"><a href="#"> <span
							class="fa fa-calendar"></span> <span class="xn-text">일일 업무
								보고</span></a>
						<ul>
							<li><a href="/dailyReport/main">메인</a></li>
							<li><a href="/counselling/manageSales">매출목표 관리</a>
								<div class="informer informer-danger"></div></li>
						</ul></li>
				</c:when>
				<c:otherwise>
					<li class=""><a href="/dailyReport/main"> <span
							class="fa fa-calendar"></span><span class="xn-text">일일 업무
								보고</span></a></li>
				</c:otherwise>
			</c:choose>


			<li class=""><a href="/chart/page"><span
					class="fa fa-bar-chart-o"></span><span class="xn-text">chart</span></a>
			</li>
		<%}else { %>
			<li class=""><a href="/admin/department"><span class="fa fa-building-o"></span><span class="xn-text">부서 관리</span></a>
			</li>
			<li class=""><a href="/admin/employee"><span class="fa fa-user"></span><span class="xn-text">사원 관리</span></a>
			</li>
		<%} %>

		</ul>
		<!-- END X-NAVIGATION -->

	</div>
	<div id="mCSB_1_scrollbar_vertical"
		class="mCSB_scrollTools mCSB_1_scrollbar mCS-light mCSB_scrollTools_vertical"
		style="display: none;">
		<div class="mCSB_draggerContainer">
			<div id="mCSB_1_dragger_vertical" class="mCSB_dragger"
				style="position: absolute; min-height: 30px; height: 0px; top: 0px;"
				oncontextmenu="return false;">
				<div class="mCSB_dragger_bar" style="line-height: 30px;"></div>
			</div>
			<div class="mCSB_draggerRail"></div>
		</div>
	</div>

</div>

<!-- <a href="#" data-toggle="modal" data-target="#myModal">Open Modal</a> -->
<!-- Modal -->
<div id="myModal1" class="modal fade" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Modal Header</h4>
			</div>
			<div class="modal-body">
				<div class="panel-body">
					<h3>
						<span class="fa fa-download"></span> Mini dropzone
					</h3>
					<p>
						Add form with class
						<code>dropzone dropzone-mini</code>
						to get mini dropzone box
					</p>
					<form class="dropzone dropzone-mini dz-clickable" method="post"
						id="myDropzone">
						<div class="dz-default dz-message">
							<span>Drop files here to upload</span>
						</div>
						<input type="text" hidden="true" name="id" id="employee_id"
							value="${sessionScope.user.employee_id}">
					</form>


				</div>
			</div>
			<div class="modal-footer">
				<input class="btn btn-primary " type="button" value="저장"
					id="submitButton">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>

	</div>
</div>


