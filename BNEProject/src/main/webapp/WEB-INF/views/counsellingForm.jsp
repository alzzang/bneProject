<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="panel-body">
	<p>
		<code>상담일지</code>
	
	</p>
	<table class="table">
		<thead>
			<tr>
				<th>소속</th>
				<td>${sessionScope.user.department_name}</td>
				<th>성명</th>
				<td>${sessionScope.user.employee_name}</td>
				<th>작성일</th>
				<td>${requestScope.counsellingrecord.reg_date}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${requestScope.counsellingrecord.title}</td>
			</tr>
			<tr>
				<th>고객코드</th>
				<td>${requestScope.counsellingrecord.client_id}</td>
				<th>고객명</th>
				<td>${requestScope.counsellingrecord.client_name}</td>
				<th>대표자</th>
				<td>${requestScope.counsellingrecord.representative}</td>
			</tr>
			<tr>
				<th>2차거래선</th>
				<td>${requestScope.counsellingrecord.sec_client_name}</td>
				<th>주소</th>
				<td>${requestScope.counsellingrecord.address}</td>
			</tr>
			<tr>
			<th class="col-md-1">상담내역</th>
	<td colspan="5" ><textarea class="summernote" id="mysummernote" >${requestScope.counsellingrecord.content}</textarea></td>
	</tr>
	
		</thead>
	</table>
</div>
</body>
