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
					location.href="/dailyReport/write?reg_date="+date;
				} 
			}
		});

	});
}

var preventKeyDown = function(){
	$('#sales-mon ,#sales-tue,#sales-wed,#sales-thu,#sales-fri').keydown(function(e){
		var range = this.selectionStart;
		if((e.keyCode==48 || e.keyCode==96) && range == 0)
			e.preventDefault();
		
	    if (e.keyCode!=37&&e.keyCode!=38&&e.keyCode!=39&&e.keyCode!=40&&e.keyCode!=9&&e.keyCode!=8&&(e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
	        e.preventDefault();
	    }
	});
}
var preventMouseClick = function(){
	$('#sales-mon ,#sales-tue,#sales-wed,#sales-thu,#sales-fri').change(function(e){
		
		/* 정규식 패턴검사 */
		var pattern = /^([1-9][0-9]{0,9}$)|^0$/;
		var oldValue = e.currentTarget.defaultValue;
		var value = e.currentTarget.value;
		var result=pattern.test(value);
								
		if(result == false){
			noWordConfirm();
			e.currentTarget.value = oldValue;
			return;
		}
	});
}
function noWordConfirm(){
    noty({
        text: '숫자만 입력가능합니다',
        layout: 'center',
        buttons: [
                {addClass: 'btn btn-success btn-clean', text: '확인', onClick: function($noty) {
                    $noty.close();
                    }
                }
            ]
    })                                                    
}  

function getReportInfo(weeklyNumber){
/*	var weeklyNumberText = $('.fc-week-number>span')[0].textContent;
	var weeklyNumber = weeklyNumberText[1]+weeklyNumberText[2];*/
	
	var date = $('#calendar').fullCalendar('getDate');
	var year = date._d.getFullYear();
	
	var report_id = year+"_"+weeklyNumber+"_"+$('#employee_id').val();
	alert($('#weeklyReportId').val())
	var report_title = $('#weeklyReportId').val();
	if(report_title == ""){
		report_title = weeklyNumber+"주의 계획";
	}
	
	var employee_id = $('#employee_id').val();
	var department_id = $('#department_id').val();
	var salesGoal = 0;/* ${salesGoal}; */
	var montlySales = 0;/* ${monthlySales}; */
	
	var weeklyReport = {
			weekly_report_id : report_id,
			title : report_title,
			saleGoal : salesGoal,
			sales : montlySales,
			employee_id : employee_id,
			department_id : department_id
	}
	return weeklyReport;
}

function getSalesPlan(){
	var dayOfWeek = ['mon','tue','wed','thu','fri'];
	var sales = [];
	for(var i= 0; i<5; i++){
		var sale = ($('#sales-'+dayOfWeek[i])[0].value);
		regdate = ($('#sales-'+dayOfWeek[i])[0].attributes[3].textContent);
		if(sale == "")
			sale = 0;
		
		var plan = {
					sales : sale,
					reg_date : regdate
		};
		sales.push(plan);
	}
	return sales;
}

function getPlanDetail(){
	var s = $('#calendar').fullCalendar('clientEvents');
	var allPlan = [];
	for(var i = 0; i<s.length; i++){
		var plan;
		
		var title = s[i].title;
		
		var startTimeOrigin = s[i].start.format().split('T');
		var startTime = startTimeOrigin[0]+" "+startTimeOrigin[1];
		var endTimeOrigin = s[i].end.format().split('T');
		var endTime = endTimeOrigin[0]+" "+endTimeOrigin[1];
		plan={
				content:title,
				start_time:startTime,
				end_time:endTime
		}
		

				allPlan.push(plan);
	}
	return allPlan;
}

function modifyButtonClick() {
	noty({
		text : '수정하시겠습니까?',
		layout : 'center',
		killer:'true',
		modal:'true',
		buttons : [
				{
					addClass : 'btn btn-success btn-clean',
					text : 'Ok',
					onClick : function($noty) {
	                	var weeklyNumberText = $('.fc-week-number>span')[0].textContent;
	                	var weeklyNumber = weeklyNumberText[1]+weeklyNumberText[2];
						event.preventDefault();

						var weeklyReport = getReportInfo(weeklyNumber);

						var sales = getSalesPlan();

						var allPlan = getPlanDetail();

						var jPlan = JSON.stringify(allPlan);
						var jPlan2 = JSON.stringify(weeklyReport);
						var jPlan3 = JSON.stringify(sales);
						$('.fc-row .fc-widget-header');

						$.ajax({
							type : "POST",
							url : "/weeklyReport/modify",
							data : {
								sales : jPlan3,
								report : jPlan2,
								weeklyPlan : jPlan
							},

							success : function(data) {
								alert($('#employee_id').val());
								window.location.href = "/weeklyReport/detail/"
										+ $('#employee_id').val();
							},
							error : function() {
								event.preventDefault();
							}
						})
					}
				}, {
					addClass : 'btn btn-danger btn-clean',
					text : 'Cancel',
					onClick : function($noty) {
						$noty.close();
					}
				} ]
	})
}  
function weeklyCancleConfirm(){
	noty({
        text: '취소시 작성한 정보가 사라집니다.',
        layout: 'center',
        modal: 'true',
        killer:'true',
        buttons: [
                {addClass: 'btn btn-success btn-clean', text: 'Ok', onClick: function($noty) {
                	window.location.href = "/main"
                	}
                },
                {addClass: 'btn btn-danger btn-clean', text: 'Cancel', onClick: function($noty) {
                    $noty.close();
                   
                    }
                }
            ]
    })      
}
