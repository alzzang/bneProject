<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<ul class="breadcrumb push-down-0">
	<li><a href="#">Home</a></li>
	<li><a href="#">Pages</a></li>
	<li class="active">Messages</li>
</ul>
<!-- START CONTENT FRAME -->
<div class="content-frame">

	<!-- START CONTENT FRAME TOP -->
	<div class="content-frame-top">
		<div class="page-title">
			<h2>
				<span class="fa fa-comments"></span> Messages
			</h2>
		</div>
		<div class="pull-right">
			<button class="btn btn-danger">
				<span class="fa fa-plus"></span> 새 메세지
			</button>
			<button class="btn btn-default content-frame-right-toggle">
				<span class="fa fa-bars"></span>
			</button>
		</div>
	</div>
	<!-- END CONTENT FRAME TOP -->

	<!-- START CONTENT FRAME RIGHT -->
	<div class="content-frame-right">

		<form class="form-horizontal" role="form">
			<div class="form-group">
				<div class="col-md-12">
					<div class="input-group">
						<span class="input-group-addon"><span class="fa fa-search"></span></span>
						<input type="text" class="form-control" placeholder="이름 검색">
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-12">
					<div class="btn-group btn-group-justified">
						<a href="#" class="btn btn-danger">전체</a> <a href="#"
							class="btn btn-primary">팀원</a>
					</div>
				</div>
			</div>
		</form>

		<br>

		<div class="list-group list-group-contacts border-bottom push-down-10">
			<a href="#" class="list-group-item">
				<div class="list-group-status status-online"></div> <img
				src="/assets/images/users/user.jpg" class="pull-left"
				alt="Dmitry Ivaniuk"> <span class="contacts-title">Dmitry
					Ivaniuk</span>
				<p>Hello my friend, how are...</p>
			</a> <a href="#" class="list-group-item">
				<div class="list-group-status status-online"></div> <img
				src="/assets/images/users/user3.jpg" class="pull-left"
				alt="Nadia Ali"> <span class="contacts-title">Nadia Ali</span>
				<p>Wanna se my photos?</p>
			</a> <a href="#" class="list-group-item active">
				<div class="list-group-status status-online"></div> <img
				src="/assets/images/users/user2.jpg" class="pull-left"
				alt="John Doe">
				<div class="contacts-title">
					John Doe <span class="label label-danger">5</span>
				</div>
				<p>This project is awesome</p>
			</a> <a href="#" class="list-group-item">
				<div class="list-group-status status-away"></div> <img
				src="/assets/images/users/user4.jpg" class="pull-left"
				alt="Brad Pitt"> <span class="contacts-title">Brad Pitt</span>
				<p>ok</p>
			</a> <a href="#" class="list-group-item">
				<div class="list-group-status status-offline"></div> <img
				src="/assets/images/users/no-image.jpg" class="pull-left"
				alt="Darth Vader"> <span class="contacts-title">Darth
					Vader</span>
				<p>We should win this war!!!1</p>
			</a> <a href="#" class="list-group-item">
				<div class="list-group-status status-offline"></div> <img
				src="/assets/images/users/no-image.jpg" class="pull-left"
				alt="Kim Kardashian"> <span class="contacts-title">Kim
					Kardashian</span>
				<p>You received a letter from Darth?</p>
			</a> <a href="#" class="list-group-item">
				<div class="list-group-status status-offline"></div> <img
				src="/assets/images/users/no-image.jpg" class="pull-left"
				alt="Jason Statham"> <span class="contacts-title">Jason
					Statham</span>
				<p>Lets play chess...</p>
			</a>
		</div>

	</div>
	<!-- END CONTENT FRAME RIGHT -->

	<!-- START CONTENT FRAME BODY -->
	<div class="content-frame-body content-frame-body-left">

		<div class="messages messages-img">
			<div class="item in">
				<div class="image">
					<img src="assets/images/users/user2.jpg" alt="John Doe">
				</div>
				<div class="text">
					<div class="heading">
						<a href="#">John Doe</a> <span class="date">08:33</span>
					</div>
					Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed
					facilisis suscipit eros vitae iaculis.
				</div>
			</div>
			<div class="item">
				<div class="image">
					<img src="assets/images/users/user.jpg" alt="Dmitry Ivaniuk">
				</div>
				<div class="text">
					<div class="heading">
						<a href="#">Dmitry Ivaniuk</a> <span class="date">08:39</span>
					</div>
					Integer et ipsum vitae urna mattis dictum. Sed eu sollicitudin
					nibh, in luctus velit.
				</div>
			</div>
			<div class="item">
				<div class="image">
					<img src="assets/images/users/user.jpg" alt="Dmitry Ivaniuk">
				</div>
				<div class="text">
					<div class="heading">
						<a href="#">Dmitry Ivaniuk</a> <span class="date">08:42</span>
					</div>
					In dapibus ex ut nisl laoreet aliquam. Donec in mollis leo. Aenean
					nec suscipit neque, non iaculis justo. Quisque eget odio efficitur,
					porta risus vitae, sagittis neque.
				</div>
			</div>
			<div class="item in">
				<div class="image">
					<img src="assets/images/users/user2.jpg" alt="John Doe">
				</div>
				<div class="text">
					<div class="heading">
						<a href="#">John Doe</a> <span class="date">08:58</span>
					</div>
					Curabitur et euismod urna?
				</div>
			</div>
			<div class="item">
				<div class="image">
					<img src="assets/images/users/user.jpg" alt="Dmitry Ivaniuk">
				</div>
				<div class="text">
					<div class="heading">
						<a href="#">Dmitry Ivaniuk</a> <span class="date">09:11</span>
					</div>
					Fusce ultricies erat quis massa interdum, eu elementum urna iaculis
				</div>
			</div>
			<div class="item in">
				<div class="image">
					<img src="assets/images/users/user2.jpg" alt="John Doe">
				</div>
				<div class="text">
					<div class="heading">
						<a href="#">John Doe</a> <span class="date">09:22</span>
					</div>
					Vestibulum cursus ipsum ut dolor vulputate dapibus. Donec elementum
					est vel vulputate malesuada?
				</div>
			</div>
		</div>

		<div class="panel panel-default push-up-10">
			<div class="panel-body panel-body-search">
				<div class="input-group">
					<input type="text" class="form-control"
						placeholder="Your message..." />
					<div class="input-group-btn">
						<button class="btn btn-default">Send</button>
					</div>
				</div>
			</div>
		</div>

	</div>
	<!-- END CONTENT FRAME BODY -->
</div>
<!-- END PAGE CONTENT FRAME -->