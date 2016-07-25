
$(function(){
	
/*	var salesCell
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
	}*/
/*

	Map = function() {
		this.map = new Object();
	};
	Map.prototype = {
		put : function(key, value) {
			this.map[key] = value;
		},
		get : function(key) {
			return this.map[key];
		},
		containsKey : function(key) {
			return key in this.map;
		},
		containsValue : function(value) {
			for ( var prop in this.map) {
				if (this.map[prop] == value)
					return true;
			}
			return false;
		},
		isEmpty : function(key) {
			return (this.size() == 0);
		},
		clear : function() {
			for ( var prop in this.map) {
				delete this.map[prop];
			}
		},
		remove : function(key) {
			delete this.map[key];
		},
		keys : function() {
			var keys = new Array();
			for ( var prop in this.map) {
				keys.push(prop);
			}
			return keys;
		},
		values : function() {
			var values = new Array();
			for ( var prop in this.map) {
				values.push(this.map[prop]);
			}
			return values;
		},
		size : function() {
			var count = 0;
			for ( var prop in this.map) {
				count++;
			}
			return count;
		}
	};
	
	
	var insertDB = function(){
		 $("div.fc-event-container>a"). 
		var s =	$(".fc-event-container>a");
		var arr = [];
		for(var i = 0; i<s.length; i++){
			getPlanData(s[i]);
		}
	}
	
	var getPlanData = function(s){
		
		var weekDay = s.parentElement.attributes.id.value;
		var planData = s.innerText.trim();
		
		var tdArr = $("div#test1>table>thead>tr>td>div>table>thead>tr>th");
		var weekdayMap = new Map();
		for(var i=1; i<tdArr.length; i++){
			var splitTdArr = tdArr[i].innerText.split(' ');
			weekdayMap.put(splitTdArr[0].toLowerCase(), splitTdArr[1]);
		}
		
		var weekDayDate = weekdayMap.get(weekDay);
		var strArr = planData.split('\n');
		
		var eventTime = strArr[0];
		var content = strArr[1];
		
		var startTime = "";
		var endTime = "";
		
		if(eventTime.search("-") > 0){
			var timeArr = eventTime.split(" - ");
			startTime = timeArr[0];
			endTime = timeArr[1];
		}else{
			var startTimeArr = eventTime.split(':');
			var endTimeHours = Number(startTimeArr[0]) + 2;
			
			var endTimeMinutes = startTimeArr[1];
			endTime = endTimeHours + ":" + endTimeMinutes;
			startTime = eventTime;
		}
		
		var finalStartDate = weekDay + " "+  weekDayDate + " " + startTime; 
		var finalEndDate = weekDay + " "+  weekDayDate + " " + endTime; 
		
		
		alert("시작시간 : " + finalStartDate + 
				"\n종료시간 : " +  finalEndDate +
				"\n내용 : " + content); 
	}
	
	var getPlanData = function(s){

		
		var weekDay = s.parentElement.attributes.id.value;
		var planData = s.innerText.replace("\n", "");
		alert("내용 :"+planData);
//		var tdArr = $("div#test1>table>thead>tr>td>div>table>thead>tr>th");
//		var weekdayMap = new Map();
//		for(var i=1; i<tdArr.length; i++){
//			var splitTdArr = tdArr[i].innerText.split(' ');
//			weekdayMap.put(splitTdArr[0].toLowerCase(), splitTdArr[1]);
//		}
//		
//		var weekDayDate = weekdayMap.get(weekDay);
		var strArr = planData.split('\n');
		var eventTime = strArr[0];
		var content = strArr[1];
		
		var startTime = "";
		var endTime = "";
		
		if(eventTime.search("-") > 0){
			var timeArr = eventTime.split(" - ");
			startTime = timeArr[0];
			endTime = timeArr[1];
		}else{
			var startTimeArr = eventTime.split(':');
			var endTimeHours = Number(startTimeArr[0]) + 2;
			
			var endTimeMinutes = startTimeArr[1];
			endTime = endTimeHours + ":" + endTimeMinutes;
			startTime = eventTime;
		}
		
		var finalStartDate = weekDay + " " + startTime; 
		var finalEndDate = weekDay + " " + endTime; 
		
		
//		alert("시작시간 : " + finalStartDate + 
//				"\n종료시간 : " +  finalEndDate +
//				"\n내용 : " + content); 
	}
	
	
*/	
	
	
	

})