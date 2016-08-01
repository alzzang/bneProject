<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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




			<li class="xn-profile active">
			
				<a href="#" class="profile-mini"> <img
					src="/user/download/${sessionScope.user.file_position}/"></a>


				<div class="profile">
					<div class="profile-image">
										
						<a href="#" >
						 <span class="fa fa-gear " style="position: absolute"> </span>
						 <img src="/user/download/${sessionScope.fileName}/">
						</a>
						
					</div>
					<div class="profile-data">
						<div class="profile-data-name">${sessionScope.user.employee_name}</div>
						<div class="profile-data-title">${sessionScope.user.department_name}</div>
					</div>
					<div class="profile-controls">
						<a href="/user/changeProfile" class="profile-control-left">
						<span class="fa fa-gear"></span></a> 
						<a href="#"	class="profile-control-right" id="sockettest">
							<span class="fa fa-envelope"></span></a>
					</div>

				</div>

			</li>

			<li class="xn-title">Navigation</li>
			<li class=""><a href="index.html"><span
					class="fa fa-desktop"></span> <span class="xn-text">Home</span></a></li>
			<li class=""><a href="index.html"><span
					class="fa fa-desktop"></span> <span class="xn-text">주간 계획</span></a></li>

			<li class="xn-openable"><a href="#"><span
					class="fa fa-file-text-o"></span> <span class="xn-text">일일
						업무 보고</span></a>
				<ul>
					<li><a href="layout-boxed.html">메인</a></li>


					
					<c:if test="${ sessionScope.user.position == 'manager' }">
						<li><a href="/counselling/manageSales">매출목표 관리</a>
						<div class="informer informer-danger"></div></li>
					</c:if>
					
					
					<!-- 사원 목록 -->
					<li><a href="layout-nav-toggled.html">이동우</a>
					<div class="informer informer-danger">4</div></li>
					<li><a href="layout-nav-top.html">이태우</a>
					<div class="informer informer-danger">4</div></li>
					<!--  -->
					
					
					
					
					
				</ul></li>

			<li class=""><a href="index.html"><span
					class="fa fa-desktop"></span> <span class="xn-text">일일 업무 보고</span></a>
			</li>

			<li class=""><a href="index.html"><span
					class="fa fa-desktop"></span> <span class="xn-text">프로필</span></a></li>



			<li class=""><a href="/chart/page"><span
					class="fa fa-file-text-o"></span> <span class="xn-text">chart</span></a>
				</li>
			<li class="xn-title">Components</li>
			<li class="xn-openable"><a href="#"><span class="fa fa-cogs"></span>
					<span class="xn-text">UI Kits</span></a>
				<ul>
					<li><a href="ui-widgets.html"><span class="fa fa-heart"></span>
							Widgets</a></li>
					<li><a href="ui-elements.html"><span class="fa fa-cogs"></span>
							Elements</a></li>
					<li><a href="ui-buttons.html"><span class="fa fa-square-o"></span>
							Buttons</a></li>
					<li><a href="ui-panels.html"><span
							class="fa fa-pencil-square-o"></span> Panels</a></li>
					<li><a href="ui-icons.html"><span class="fa fa-magic"></span>
							Icons</a>
						<div class="informer informer-warning">+679</div></li>
					<li><a href="ui-typography.html"><span
							class="fa fa-pencil"></span> Typography</a></li>
					<li><a href="ui-portlet.html"><span class="fa fa-th"></span>
							Portlet</a></li>
					<li><a href="ui-sliders.html"><span class="fa fa-arrows-h"></span>
							Sliders</a></li>
					<li><a href="ui-alerts-popups.html"><span
							class="fa fa-warning"></span> Alerts &amp; Popups</a></li>
					<li><a href="ui-lists.html"><span class="fa fa-list-ul"></span>
							Lists</a></li>
					<li><a href="ui-tour.html"><span class="fa fa-random"></span>
							Tour</a></li>
				</ul></li>
			<li class="xn-openable"><a href="#"><span
					class="fa fa-pencil"></span> <span class="xn-text">Forms</span></a>
				<ul>
					<li><a href="form-layouts-two-column.html"><span
							class="fa fa-tasks"></span> Form Layouts</a>
						<div class="informer informer-danger">New</div>
						<ul>
							<li><a href="form-layouts-one-column.html"><span
									class="fa fa-align-justify"></span> One Column</a></li>
							<li><a href="form-layouts-two-column.html"><span
									class="fa fa-th-large"></span> Two Column</a></li>
							<li><a href="form-layouts-tabbed.html"><span
									class="fa fa-table"></span> Tabbed</a></li>
							<li><a href="form-layouts-separated.html"><span
									class="fa fa-th-list"></span> Separated Rows</a></li>
						</ul></li>
					<li><a href="form-elements.html"><span
							class="fa fa-file-text-o"></span> Elements</a></li>
					<li><a href="form-validation.html"><span
							class="fa fa-list-alt"></span> Validation</a></li>
					<li><a href="form-wizards.html"><span
							class="fa fa-arrow-right"></span> Wizards</a></li>
					<li><a href="form-editors.html"><span
							class="fa fa-text-width"></span> WYSIWYG Editors</a></li>
					<li><a href="form-file-handling.html"><span
							class="fa fa-floppy-o"></span> File Handling</a></li>
				</ul></li>
			<li class="xn-openable"><a href="tables.html"><span
					class="fa fa-table"></span> <span class="xn-text">Tables</span></a>
				<ul>
					<li><a href="table-basic.html"><span
							class="fa fa-align-justify"></span> Basic</a></li>
					<li><a href="table-datatables.html"><span
							class="fa fa-sort-alpha-desc"></span> Data Tables</a></li>
					<li><a href="table-export.html"><span
							class="fa fa-download"></span> Export Tables</a></li>
				</ul></li>
			<li class="xn-openable"><a href="#"><span
					class="fa fa-bar-chart-o"></span> <span class="xn-text">Charts</span></a>
				<ul>
					<li><a href="charts-morris.html"><span class="xn-text">Morris</span></a></li>
					<li><a href="charts-nvd3.html"><span class="xn-text">NVD3</span></a></li>
					<li><a href="charts-rickshaw.html"><span class="xn-text">Rickshaw</span></a></li>
					<li><a href="charts-other.html"><span class="xn-text">Other</span></a></li>
				</ul></li>
			<li><a href="maps.html"><span class="fa fa-map-marker"></span>
					<span class="xn-text">Maps</span></a></li>

			<li class="xn-openable"><a href="#"><span
					class="fa fa-sitemap"></span> <span class="xn-text">Navigation
						Levels</span></a>
				<ul>
					<li class="xn-openable"><a href="#">Second Level</a>
						<ul>
							<li class="xn-openable"><a href="#">Third Level</a>
								<ul>
									<li class="xn-openable"><a href="#">Fourth Level</a>
										<ul>
											<li><a href="#">Fifth Level</a></li>
										</ul></li>
								</ul></li>
						</ul></li>
				</ul></li>

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

