
$(function(){
	var path = "/alarm/unReadCount/" + $("#employee_id").val();
	
	$.ajax({
		url : path,
		success : function(data) {
			$("#newMessageCount_title").html(data);
		}
	});
});





function getNoticeList(page, perContentNum) {
	var user_id = $("#employee_id").val();
	var path = "/alarm/notices/" + page + "/" + perContentNum;
	
	var user_name = 'gg';
	var file_position = '/assets/images/users/user2.jpg';
	var user_name = 'John Doe';
	var message = 'Praesent placerat tellus id augue condimentum';	
	
	var noticelist_html = '';
	
	
	
	$.ajax({
		url : path,
		type : "post",
		data : "user_id=" + user_id,
		dataType : "json",
		success : function(data) {
			var noticeList = data.noticeList;
			var newMessageCount = data.newMessageCount;
			
			for(var i=0; i<noticeList.length; i++) {
				noticelist_html += createNoticeElementHTML(noticeList[i]);
			}	
			
			$("#newMessageCount_title").html(newMessageCount);
			$("#newMessageCount").html(newMessageCount + " new");
			$("#mCSB_2_container").html(noticelist_html);
		}
	});
	
};




function createNoticeString(subject_name, notice_type) {
	var message = '';
	
	if(notice_type === "WEEKLY_POST") {
		message = subject_name + ' 님이 ' + '주간 업무 계획을 작성하였습니다.';
	}else if(notice_type === "WEEKLY_CORRECT") {
		message = subject_name + ' 님이 ' + '주간 업무 계획을 수정하였습니다.';
	}else if(notice_type === "DAILY_POST") {
		message = subject_name + ' 님이 ' + '일일 업무 보고를 작성하였습니다.';
	}else if(notice_type === "DAILY_CORRECT") {
		message = subject_name + ' 님이 ' + '일일 업무 보고를 수정하였습니다.';
	}else if(notice_type === "COMMENT") {
		message = subject_name + ' 님이 ' + 'COMMENT를 작성하였습니다.';
	}else if(notice_type === "APPROVAL") {
		message = subject_name + ' 님이 ' + '일일 업무 보고를 승인하였습니다.';
	}

	return message;
}





function createNoticeElementHTML(noticeObj) {
	var message = createNoticeString(noticeObj.subject_name, noticeObj.notice_type);
	var href = '/alarm/detail/' + noticeObj.link_id;
	var status = "";
	var file= '/assets/images/users/user.jpg';
	
	
	if(noticeObj.notice_type === "WEEKLY_POST") {
		status += "fa fa-circle text-danger";
	}else if(noticeObj.notice_type === "WEEKLY_CORRECT") {
		status += "fa fa-circle text-danger";
	}else if(noticeObj.notice_type === "DAILY_POST") {
		status += "fa fa-circle text-primary";
	}else if(noticeObj.notice_type === "DAILY_CORRECT") {
		status += "fa fa-circle text-warning";
	}else if(noticeObj.notice_type === "COMMENT") {
		status += "fa fa-circle text-warning";
	}else if(noticeObj.notice_type === "APPROVAL") {
		status += "fa fa-circle text-primary";
	}else {
		return;
	}
	
	var params = noticeObj.notice_id + ", '" + noticeObj.notice_type + "', " + noticeObj.link_id + ", '" + noticeObj.subject_id + "'";
	
	var html = '<a href="#" onclick="moveLink(' + params + ')" class="list-group-item">' +
					'<span class="' + status + '"></span>' +
					'<img src="' + file + '" class="pull-left" alt="' + noticeObj.user_name + '">' +
					'<span class="contacts-title">'+ noticeObj.subject_name + '</span>' +
					'<p>' + message + '</p>' +
				'</a>';

	return html;
};



