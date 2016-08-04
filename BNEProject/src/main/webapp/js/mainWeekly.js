$(function(){
	var makeSalesInput = function(){
		var salesCell
		= 	'<tr>' + 
				'<td class="fc-axis">매출</td>' +
				'<td><input type="text" class="salesInput" id="sales-mon"></td>' +
				'<td><input type="text" class="salesInput" id="sales-tue"></td>' +
				'<td><input type="text" class="salesInput" id="sales-wed"></td>' +
				'<td><input type="text" class="salesInput" id="sales-thu"></td>' +
				'<td><input type="text" class="salesInput" id="sales-fri"></td>' +
			'</tr>';
			
		$('#weeklyTableHeader>tbody').html(salesCell);
		
		var s = $('#weeklyTableHeader>thead>tr>th');
		
		for(var i=2; i<7; i++){
			$('#weeklyTableHeader>tbody>tr>td:nth-child('+i+')>input').attr('reg_date', s[i-1].dataset.date);
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
		var achievement_rate = Number(weeklyReportDTO.sales) / Number(weeklyReportDTO.sales_goal) * 100;

		$('#achievement_rate').html(achievement_rate + '%');
		if(weeklyReportDTO.sales_goal == 0 || weeklyReportDTO.sales_goal == null)
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

		$('#calendar').fullCalendar( 'gotoDate', weeklyPlanDTOList[0].reg_date );
	
		// 매출액 정보 행 삽입
		makeSalesInput();
		
		for(var i=0; i<weeklyPlanDTOList.length; i++){
			$('input[reg_date="'+weeklyPlanDTOList[i].reg_date+'"]').attr({'value': weeklyPlanDTOList[i].sales, 'disabled':'disabled'});
		}
	};
	
	
	window.onload = function(){

		
		// 처음에 받아온 주간계획 정보 삽입 
		var reportData = JSON.parse('${weeklyReportDetail}') 
		inputReportData(reportData);
		
		// 이 주간계획서의 작성자 ID
		var employee_id = reportData.weeklyReportDTO.employee_id;
		var weekly_report_id = reportData.weeklyReportDTO.weekly_report_id;
		
		$('#calendar').fullCalendar('getView').calendar.options.editable = false;
		$('#calendar').fullCalendar('getView').calendar.options.selectable = false;
		//var sessionUserId = ${user.employee_id};
		var employeeId = ${requestScope.employee_Id};
		var date = $('#calendar').fullCalendar('getDate');
		var now = $('#calendar').fullCalendar('getView').calendar.getNow();

	
	};
});