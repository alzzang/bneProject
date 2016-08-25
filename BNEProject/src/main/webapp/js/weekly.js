var openMessageBox = function(boxName) {
	var box = $(boxName);
	if (box.length > 0) {
		box.toggleClass("open");

		var sound = box.data("sound");

		if (sound === 'alert')
			playAudio('alert');

		if (sound === 'fail')
			playAudio('fail');
	}
}

var makeReportId = function(flag) {
	var weeklyNumberText = $('.fc-week-number>span')[0].textContent;
	var weeklyNumber = parseInt(weeklyNumberText[1] + weeklyNumberText[2]);

	var date = $('#calendar').fullCalendar('getDate');
	var year = date._d.getFullYear();
	if (flag == 'prev') {
		report_id = year + "_" + (weeklyNumber - 1) + "_" + $("#employee_Id").val();
	} else if (flag == 'next') {
		report_id = year + "_" + (weeklyNumber + 1) + "_" + $("#employee_Id").val();
	}
	return report_id;
};

var makeSalesInput = function() {
	var salesCell = '<tr>' + '<td class="fc-axis">매출</td>'
			+ '<td><input type="text" class="salesInput" id="sales-mon"></td>'
			+ '<td><input type="text" class="salesInput" id="sales-tue"></td>'
			+ '<td><input type="text" class="salesInput" id="sales-wed"></td>'
			+ '<td><input type="text" class="salesInput" id="sales-thu"></td>'
			+ '<td><input type="text" class="salesInput" id="sales-fri"></td>'
			+ '</tr>';

	$('#weeklyTableHeader>tbody').html(salesCell);

	var s = $('#weeklyTableHeader>thead>tr>th');

	for (var i = 2; i < 7; i++) {
		$('#weeklyTableHeader>tbody>tr>td:nth-child(' + i + ')>input').attr(
				'reg_date', s[i - 1].dataset.date);
	}
};

var isModify = function(now) {
	var result = false;

	var lastDayWeek = $('#calendar').fullCalendar('getView').end;
	lastDayWeek._d.setDate(lastDayWeek._d.getDate() + 1)

	if (now < lastDayWeek)
		return true;

	return result;
}

var mainInputReportData = function(reportData) {

	var weeklyPlanDTOList = reportData.weeklyPlanDTOList;
	var planDetailDTOList = reportData.planDetailDTOList;

	for (var i = 0; i < planDetailDTOList.length; i++) {
		$('#calendar').fullCalendar('renderEvent', {
			"title" : planDetailDTOList[i].content,
			"allDay" : false,
			"start" : planDetailDTOList[i].start_time,
			"end" : planDetailDTOList[i].end_time
		}, true);
	}

	// 매출액 정보 행 삽입
	makeSalesInput();
	console.log("dfdf="+weeklyPlanDTOList[0].reg_date);
	for(var i=0; i<weeklyPlanDTOList.length; i++){
		$('input[reg_date="'+weeklyPlanDTOList[i].reg_date+'"]').attr({'value': weeklyPlanDTOList[i].sales+'원', 'disabled':'disabled'});
	}
};


var inputReportData = function(reportData) {

	var weeklyReportDTO = reportData.weeklyReportDTO;
	var weeklyPlanDTOList = reportData.weeklyPlanDTOList;
	var planDetailDTOList = reportData.planDetailDTOList;
	var employee_name = reportData.employee_name;
	var department_name = reportData.department_name;

	$('#weekly_report_id')[0].value = weeklyReportDTO.weekly_report_id;
	$('#title').html(weeklyReportDTO.title);
	$('#reg_date').html(weeklyReportDTO.reg_date);
	$('#employee_name').html(employee_name);
	$('#department_name').html(department_name);
	$('#sales_goal').html(weeklyReportDTO.sales_goal);
	$('#sales').html(weeklyReportDTO.sales);
	var achievement_rate = Number(weeklyReportDTO.sales)
			/ Number(weeklyReportDTO.sales_goal) * 100;

	$('#achievement_rate').html(achievement_rate + '%');
	if (weeklyReportDTO.sales_goal == 0 || weeklyReportDTO.sales_goal == null)
		achievement_rate = 0;
	$('#achievement_rate').html(achievement_rate + '%');

	for (var i = 0; i < planDetailDTOList.length; i++) {
		$('#calendar').fullCalendar('renderEvent', {
			"title" : planDetailDTOList[i].content,
			"allDay" : false,
			"start" : planDetailDTOList[i].start_time,
			"end" : planDetailDTOList[i].end_time
		}, true);
	}

	// $('#calendar').fullCalendar( 'gotoDate', weeklyPlanDTOList[0].reg_date );

	// 매출액 정보 행 삽입
	makeSalesInput();

	for (var i = 0; i < weeklyPlanDTOList.length; i++) {
		$('input[reg_date="' + weeklyPlanDTOList[i].reg_date + '"]').attr({
			'value' : weeklyPlanDTOList[i].sales+'원',
			'disabled' : 'disabled'
		});
	}

	// ////////////////////////도넛 시작//////////////////////////

	// 도넛을 비워주고
	$('#weeklyDonut').empty();

	var achievement_rate = Number(weeklyReportDTO.sales)
			/ Number(weeklyReportDTO.sales_goal) * 100;

	if (isNaN(achievement_rate) || !isFinite(achievement_rate))
		achievement_rate = 0;

	var lack_rate = 100 - achievement_rate;

	if (lack_rate < 0)
		lack_rate = 0;

	// 받아온 값으로 도넛을 새로 생성하고
	var salesDount = Morris.Donut({
		element : 'weeklyDonut',
		data : [ {
			label : "Achievement rate",
			value : achievement_rate,
			formatted : achievement_rate + '%'
		}, {
			label : "Lack of achievement?",
			value : lack_rate,
			formatted : lack_rate + '%'
		} ],
		colors : [ '#95B75D', '#1caf9a' ],
		formatter : function(x, data) {
			return data.formatted;
		},
		resize : true
	})

	// 매출액부분이 디폴트로 강조되도록
	salesDount.select(0);
	// 마우스를 떼도 매출액이 강조되도록
	$('#weeklyDonut').on('mouseout', function() {
		salesDount.select(0);
	});
}

var registerDailyReportEvent = function(){
	$('.fc-mon ,.fc-tue,.fc-wed,.fc-thu,.fc-fri').on('click',function(){
		var date = this.dataset.date;
		$.ajax({
			type : "POST",
			url : "/dailyReport/checkReport",
			data : {
				date : date
			},
			success : function(data){
				alert(data);
					if(data !== 0){
						var path = "/dailyReport/detail";
						var form = document.createElement("form");
						form.setAttribute("method", "POST");
						form.setAttribute("action", path);
						
						var hiddenField = document.createElement("input");
						hiddenField.setAttribute("type", "hidden");
						hiddenField.setAttribute("name", "dailyReportId");
						hiddenField.setAttribute("value", data);
						form.appendChild(hiddenField);
						document.body.appendChild(form);

						form.submit();
				}else{
					location.href="/dailyReport/write"
				} 
			}
		});

	});
}