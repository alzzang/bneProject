<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<!-- 
morris 차트 사용 시 포함해야할 js 파일 : <script type="text/javascript" src="js/demo_dashboard.js"></script>
							<script type="text/javascript" src="js/plugins/morris/morris.min.js"></script>
 -->

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />




<link rel="stylesheet" type="text/css" href="/css/notice_form.css" />



<!-- <link rel="icon" href="/favicon.ico" type="image/x-icon" /> -->

<link rel="icon" href="/favicon.ico" type="image/x-icon" />

<!-- <link rel="icon" href="favicon.ico" type="image/x-icon" /> -->


<!-- END META SECTION -->

<!-- CSS INCLUDE -->
<link rel="stylesheet" type="text/css" id="theme" href="/css/theme-default.css" />
<!-- EOF CSS INCLUDE -->
<title>Insert title here</title>

<style>
#mCSB_1_scrollbar_vertical {
	display: none;
}
</style>

<script type="text/javascript" src="/js/dailysettings.js"></script>
<script src="http://192.168.1.6:10000/socket.io/socket.io.js"></script> 
</head>



<body>

	
	<c:if test="${sessionScope.user == null}">
		<jsp:forward page="/user/login" />
	</c:if>

	<input type="hidden" id='fileName' value='${sessionScope.fileName}'> 

	<div class="page-container">

		<div
			class="page-sidebar page-sidebar-fixed scroll mCustomScrollbar _mCS_1 mCS-autoHide mCS_no_scrollbar"
			style="height: 979px;">
			<tiles:insertAttribute name="menu" />
		</div>


		<div
			style="width: 220px; float: right; position: relative; background-color: black;"
			id="popup"></div>


		<div class="page-content">
			<tiles:insertAttribute name="header" />
			<tiles:insertAttribute name="body" />
		</div>

	</div>
	
	
	

	<!-- MESSAGE BOX-->
	<div class="message-box animated fadeIn" data-sound="alert"
		id="mb-signout">
		<div class="mb-container">
			<div class="mb-middle">
				<div class="mb-title">
					<span class="fa fa-sign-out"></span> Log <strong>Out</strong> ?
				</div>
				<div class="mb-content">
					<p>Are you sure you want to log out?</p>
					<p>Press No if youwant to continue work. Press Yes to logout
						current user.</p>
				</div>
				<div class="mb-footer">
					<div class="pull-right">
						<a href="/user/logout" class="btn btn-success btn-lg">Yes</a>
						<button class="btn btn-default btn-lg mb-control-close">No</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- END MESSAGE BOX-->

	<!-- START PRELOADS -->
	<audio id="audio-alert" src="/audio/alert.mp3" preload="auto"></audio>
	<audio id="audio-fail" src="/audio/fail.mp3" preload="auto"></audio>
	<!-- END PRELOADS -->

	<!-- START SCRIPTS -->
	<!-- START PLUGINS -->
	<script type="text/javascript" src="/js/plugins/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="/js/plugins/jquery/jquery-ui.min.js"></script>
	<script type="text/javascript"
		src="/js/plugins/bootstrap/bootstrap.min.js"></script>
	<!-- END PLUGINS -->

	<!-- START THIS PAGE PLUGINS-->
	<script type='text/javascript' src='/js/plugins/icheck/icheck.min.js'></script>
	<script type="text/javascript"
		src="/js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js"></script>
	<script type="text/javascript"
		src="/js/plugins/scrolltotop/scrolltopcontrol.js"></script>

	<script type="text/javascript" src="/js/plugins/morris/raphael-min.js"></script>

	<script type="text/javascript" src="/js/plugins/rickshaw/d3.v3.js"></script>
	<script type="text/javascript"
		src="/js/plugins/rickshaw/rickshaw.min.js"></script>
	<script type='text/javascript'
		src='/js/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js'></script>
	<script type='text/javascript'
		src='/js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js'></script>
	<script type='text/javascript'
		src='/js/plugins/bootstrap/bootstrap-datepicker.js'></script>
	<script type="text/javascript" src="/js/plugins/owl/owl.carousel.min.js"></script>

	<script type="text/javascript" src="/js/plugins/moment.min.js"></script>
	<script type="text/javascript"
		src="/js/plugins/daterangepicker/daterangepicker.js"></script>
		<script type="text/javascript" src="/js/plugins/fullcalendar/fullcalendar.min.js"></script>
		<script type="text/javascript" src="/js/plugins/dropzone/dropzone.min.js"></script>
		
	<script type="text/javascript" src="/js/plugins/summernote/summernote.js"></script>
	<script type="text/javascript" src="/js/plugins/bootstrap/bootstrap-select.js"></script>
	<!-- END THIS PAGE PLUGINS-->

	<!-- START TEMPLATE -->
	<script type="text/javascript" src="/js/settings.js"></script>

	<script type="text/javascript" src="/js/plugins.js"></script>
	<script type="text/javascript" src="/js/actions.js"></script>
	<!-- <script type="text/javascript" src="/js/dailysettings.js"></script> -->
	<script src="/js/notice_on_header.js"></script>
	<script type="text/javascript" src="/js/usersettings.js"></script>
	
	
	
	<script type="text/javascript" src="/assets/plugins/bootstrap-notify-master/bootstrap-notify.js"></script>
	
	
	<!-- <script type="text/javascript" src="/js/demo_dashboard.js"></script>
	<script type="text/javascript" src="/js/plugins/morris/raphael-min.js"></script> -->
	<!-- <script type="text/javascript" src="/js/plugins/morris/morris.min.js"></script> -->
	<!-- END TEMPLATE -->
	<!-- END SCRIPTS -->



	<script type="text/javascript">
		var socket = io.connect('http://192.168.1.6:10000');
		socket.emit('getId', {
			employeeId : '${sessionScope.user.employee_id}'
		});
		
		socket.on('newmessage', function(data) {
			var message = createNoticeString(data.fromName, data.notice_type);
			var path = '/dailyReport/main';
			
			$.notify({
				// options
				message: message,
				url: path,
				target: '_self'
			},{
				// settings
				element: '#popup',
				position: 'absolute',
				type: "customNoticeForm-danger",
				allow_dismiss: true,
				newest_on_top: false,
				showProgressbar: false,
				placement: {
					from: "top",
					align: "right"
				},
				offset: 20,
				spacing: 10,
				z_index: 1,
				delay: 5000,
				timer: 1000,
				mouse_over: null,
				animate: {
					enter: 'animated lightSpeedIn',
					exit: 'animated lightSpeedOut'
				},
				onShow: null,
				onShown: null,
				onClose: null,
				onClosed: null,
				icon_type: 'class',
				template: '<div data-notify="container" class="col-xs-11 col-sm-3 alert alert-{0}" role="alert">' +
				'<span data-notify="title">{1}</span>' +
				'<span data-notify="message">{2}</span>' +
			'</div>'
			});

			
			
			
			$(function(){
				var path = "/alarm/unReadCount/" + $("#employee_id").val();
				
				$.ajax({
					url : path,
					success : function(data) {
						$("#newMessageCount_title").html(data);
					}
				});
			});
			
			
			if($('#noticeButton').hasClass('active')) {
				getNoticeList(1, 5);	
			}			
		});

		
	</script>


</body>
</html>

