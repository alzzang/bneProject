<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="page-title">
	<h2>
		<span class="fa fa-arrow-circle-o-left"></span> 상담일지 작성
	</h2>
</div>
<!-- END PAGE TITLE -->

<!-- PAGE CONTENT WRAPPER -->

<div class="page-content-wrap">
	<div class="row">
		<div class="col-md-12">
			<form action="/counselling/writeCounsellingRecord" method="POST" class="form-horizontal">
				<div class="panel panel-default">
					<div class="panel-body">
						<!-- <div class="form-group">
							<label class="col-md-3 col-xs-12 control-label">제목</label>
							<div class="col-md-6 col-xs-12">
								<div class="input-group">
									<span class="input-group-addon"><span
										class="fa fa-pencil"></span></span> <input type="text"
										class="form-control" name="title">
								</div>
								<span class="help-block">This is sample of text field</span>
							</div>
						</div> -->
						<input type="hidden" value="1" name=daily_report_id>
						<input type="hidden" value="1" name=department_id>
						
						<div class="form-group">
							<label class="col-md-3 col-xs-12 control-label">제목</label>
							<div class="col-md-6 col-xs-12">
								<div class="input-group">
									<span class="input-group-addon"><span
										class="fa fa-calendar"></span></span> <input type="text"
										class="form-control datepicker" name="title">
								</div>
								<span class="help-block">Click on input field to get
									datepicker</span>
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 col-xs-12 control-label">Text
								Field</label>
							<div class="col-md-6 col-xs-12">

								<textarea class="form-control summernote" name="content"
									rows="5"></textarea>

								<span class="help-block">This is text field</span>
							</div>
						</div>



						<div class="form-group">
							<label class="col-md-3 col-xs-12 control-label">고객명</label> <span
								class="col-md-4 col-xs-12"> <select class="form-control"
								name="counsel_id" id="counsel_id" required>
									<option value="" disabled selected hidden="true">선택하세요!</option>
									<option value="1">동작대리점</option>
									<option value="2">검암대리점</option>
							</select> <span class="help-block">Select box </span>
							</span> 
							<span class="col-md-1 col-xs-12"> 
							<input type="text" class="form-control" placeholder="고객코드" readonly id="client_id">
							</span>
							 
							 <span class="col-md-1 col-xs-12"> 
							 <input type="text" class="form-control" placeholder="대표자" readonly id="representative">
							</span>

						</div>

						<div class="form-group">
							<label class="col-md-3 col-xs-12 control-label">2차거래선</label>
							<span class="col-md-4 col-xs-12">
								<select class="form-control" name="sec_client_id"
									id="sec_client_id" required>
								</select> <span class="help-block">Select box </span>
							</span>
							<span class="col-md-2 col-xs-12"> <input type="text"
								class="form-control" placeholder="주소" readonly
								id="address">
							</span>
						
						</div>
					</div>

					<div class="panel-footer">
						<button class="btn btn-primary pull-right">Submit</button>
					</div>
				</div>

			</form>
		</div>
	</div>
</div>







