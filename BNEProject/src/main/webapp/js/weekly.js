$(function(){
	 
	var insertDB = function(){
		/*$("div.fc-event-container>a").*/
		var s =	$(".fc-event-container>a");
		var arr = [];
		for(var i = 0; i<s.length; i++){
			var d = [];
			
			var strArr = (s[i].innerText).split('\n');
			var id = s[i].parentElement.attributes.id.value;
			arr[i] = strArr;
			for(var j = 0; j<strArr.length;j++)
				alert(strArr[j]);
		}
	}
	
	$('#aaaa').on('click',function(){
		//insertDB();
		//$('#calendarWeek').fullCalendar('next');
/*		var s = $('#calendarWeek').fullCalendar('clientEvents');
		alert(s[0]);*/
		//editable 속성 false;
		$('#calendarWeek').fullCalendar('getView').calendar.options.editable = false;
	})
	var events = [];
	
	var date = new Date();
	var d = date.getDate();
	var m = date.getMonth();
	var y = date.getFullYear();
	
    $('#calendarWeek').fullCalendar('renderEvent',{
	    "title":"dd",
		    "allDay":"",
		    "id":"15",
		    "start":"2016-07-11 09:30:00",
		    "end":"2016-07-11 17:30:00"
    },true);
		
	
})