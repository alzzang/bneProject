<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="content-frame">
	<!-- START CONTENT FRAME TOP -->
	<div class="content-frame-top">
		<div class="page-title">
			<h2>
				<span class="fa fa-inbox"></span> 일일 업무 보고
			</h2>
		</div>
		<div class="pull-right">
			<button class="btn btn-default content-frame-left-toggle">
				<span class="fa fa-bars"></span>
			</button>
		</div>
	</div>
	<!-- END CONTENT FRAME TOP -->

	<!-- START CONTENT FRAME LEFT -->
	<div class="content-frame-left" style="height: 1054px;">
		<div class="panel-body">
			<div class="block"></div>

			<div class="page-title">
				<h5>매출 현황</h5>
			</div>
			<table class="table">
				<thead>
					<tr>
						<th>소속</th>
						<td>남부 지점</td>
					</tr>
					<tr>
						<th>성명</th>
						<td>이태우</td>
					</tr>
					<tr>
						<th>매출목표</th>
						<td>100,000,000</td>
					</tr>
				</thead>
			</table>


		</div>
	</div>
	<!-- END CONTENT FRAME LEFT -->

	<!-- START CONTENT FRAME BODY -->
	<div class="content-frame-body" style="height: 897px;">

		<form class="form-horizontal">
			<div class="panel panel-default">
				<div class="panel-heading ui-draggable-handle">
					<div class="pull-left">
						<img src="/assets/images/users/user2.jpg" class="panel-title-image"
							alt="John Doe">
						<h3 class="panel-title">
							John Doe <small>johndoe@domain.com</small>
						</h3>
					</div>
					<div class="pull-right">
						<button class="btn btn-success pull-right">
					<span class="fa fa-check"></span> 승인
				</button>
					</div>
				</div>


				<div class="panel-body">
					<div class="form-group">
						<label class="col-md-3 col-xs-12 control-label">제목</label>
						<div class="col-md-6 col-xs-12">
							<div class="input-group">
								<span class="input-group-addon"><span
									class="fa fa-pencil"></span></span> <input type="text"
									class="form-control" readonly="readonly" style="cursor: default;" style="cursor: default;">
							</div>
						</div>


					</div>



					<div class="form-group">
						<label class="col-md-3 col-xs-12 control-label">작성일</label>
						<div class="col-md-6 col-xs-12">
							<div class="input-group">
								<span class="input-group-addon"><span
									class="fa fa-calendar"></span></span> <input type="text"
									class="form-control " value="2014-11-01" readonly="readonly" style="cursor: default;" style="cursor: default;">
							</div>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 col-xs-12 control-label">매출액</label>
						<div class="col-md-6 col-xs-12">
							<div class="input-group">
								<span class="input-group-addon" style="padding-bottom: 10px;"><span
									class="fa fa-won"></span></span> <input type="text"
									class="form-control" readonly="readonly" style="cursor: default;" style="cursor: default;"> <span class="progress"> <span
									class="progress-bar progress-bar-danger" role="progressbar"
									aria-valuenow="150" aria-valuemin="0" aria-valuemax="100"
									style="width: 80%"> 80% </span>
								</span>

							</div>
						</div>


					</div>


					<div class="form-group">
						<label class="col-md-3 col-xs-12 control-label">주행</label>
						<div class="col-md-2 col-xs-12">
							<div class="input-group">
								<span class="input-group-addon">출근 시 계기판</span> <input
									type="text" class="form-control" readonly="readonly" style="cursor: default;">
							</div>
						</div>

						<div class="col-md-2 col-xs-12">
							<div class="input-group">
								<span class="input-group-addon">퇴근 시 계기판</span> <input
									type="text" class="form-control" readonly="readonly" style="cursor: default;"> 
							</div>
						</div>

						<div class="col-md-2 col-xs-12">
							<div class="input-group">
								<span class="input-group-addon">주행 거리</span> <input type="text"
									class="form-control" readonly="readonly" style="cursor: default;" style="cursor: default;">
									
							</div>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 col-xs-12 control-label">상담 일지</label>
						<div class="col-md-6 col-xs-12">
							<div class="input-group">
								<ul class="list-tags">
                                        <li><a href="#"><span class="fa fa-tag"></span> amet</a></li>
                                        <li><a href="#"><span class="fa fa-tag"></span> rutrum</a></li>
                                        <li><a href="#"><span class="fa fa-tag"></span> nunc</a></li>
                                        <li><a href="#"><span class="fa fa-tag"></span> tempor</a></li>
                                        <li><a href="#"><span class="fa fa-tag"></span> eros</a></li>
                                        <li><a href="#"><span class="fa fa-tag"></span> suspendisse</a></li>
                                        <li><a href="#"><span class="fa fa-tag"></span> dolor</a></li>
                                    </ul>
							</div>
						</div>
					</div>

					<br> <br> 
					<div class="col-md-8 col-md-offset-2">


						<h3>
							Re: Product development 
						</h3>
						<p>Hello Dmitry,</p>
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
							Mauris ligula risus, viverra sit amet purus id, ullamcorper
							venenatis leo. Ut vitae semper neque. Nunc vel lacus vel erat
							sodales ultricies sed sed massa. Duis non elementum velit. Nunc
							quis molestie dui. Nullam ullamcorper lectus in mattis volutpat.
							Nunc egestas felis sit amet ultrices euismod. Donec lacinia neque
							vel quam pharetra, non dignissim arcu semper. Donec ultricies,
							neque ut vehicula ultrices, ligula velit sodales purus, eget
							eleifend libero risus sed turpis. Fusce hendrerit vel dui ut
							pulvinar. Ut sed tristique ante, sed egestas turpis. Lorem ipsum
							dolor sit amet, consectetur adipiscing elit.</p>
						<p>Fusce sit amet dui at nunc laoreet facilisis. Proin
							consequat orci sollicitudin sem cursus, quis vehicula eros
							ultrices. Cras fermentum justo at nibh tincidunt, consectetur
							elementum est aliquam.</p>
						<p>Nam dignissim convallis nulla, vitae porta purus fringilla
							ac. Praesent consectetur ex eu dui efficitur sollicitudin. Mauris
							libero est, aliquam a diam maximus, dignissim laoreet lacus.</p>
						<p>Nulla ac nisi sodales, auctor dui et, consequat turpis.
							Cras dolor turpis, sagittis vel elit in, varius elementum arcu.
							Mauris aliquet lorem ac enim blandit, nec consequat tortor
							auctor. Sed eget nunc at justo congue mollis eget a urna.
							Phasellus in mauris quis tortor porta tristique at a risus.</p>
						<p class="text-muted">
							<strong>Best Regards<br>John Doe
							</strong>
						</p>
						<div class="note-statusbar">
							<div class="note-resizebar">
								<div class="note-icon-bar"></div>
								<div class="note-icon-bar"></div>
								<div class="note-icon-bar"></div>
							</div>
						</div>
						
						<br><br>
						<%if(true) { %> <!-- 팀원일 때 -->
						<div class="timeline-body comments">
                                            <div class="comment-item">
                                                <img src="/assets/images/users/user4.jpg">
                                                <p class="comment-head">
                                                    <a href="#">Brad Pitt</a> <span class="text-muted">@bradpitt</span>
                                                </p>
                                                <p>Awesome, man, that is awesome...</p>
                                                <small class="text-muted">10h ago</small>
                                            </div>                                            
                                        </div>
						<%}else { %> <!-- 팀장일 때 -->
						<div class="form-group push-up-20">
							<div class="col-md-12">
								<div class="input-group">
									<input class="form-control" placeholder="팀장 의견">
									<span class="input-group-addon"><span
										class="fa fa-pencil"></span></span>
								</div>
							</div>
						<%} %>
						</div>
					</div>
				</div>


			</div>

		</form>
	</div>

</div>



<!-- END CONTENT FRAME BODY -->

