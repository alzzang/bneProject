$(function(){
	

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
		/* $("div.fc-event-container>a"). */
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
	

	
	$('#aaaa').on('click',function(){
		//insertDB();
		//$('#calendarWeek').fullCalendar('next');
/*		var s = $('#calendarWeek').fullCalendar('clientEvents');
		alert(s[0]);*/
		//editable 속성 false;
/*		var aa = $('#calendarWeek').fullCalendar('clientEvents');
		
		alert(aa[0].start);*/
		
		
		var s = [];
		var plan=[];
		
		s['title']="title";
		s['name']="배진우";
		plan.push(s);
		s['title']="title2";
		s['name']="배진우2";
		plan.push(s);
		s['title']="title3";
		s['name']="배진우3";
		plan.push(s);
		
		var jPlan = JSON.stringify(plan);
	})
/*	    $('#calendarWeek').fullCalendar('renderEvent',{
	    "title":"dd",
		    "allDay":"",
		    "id":"15",
		    "start":"2016-07-11 09:30:00",
		    "end":"2016-07-11 17:30:00"
    },true);*/
})