$(function(){
	var insertDB = function(){

		/*$("div.fc-event-container>a").*/
		var s = $(".fc-event-container>a");
		for(var i = 0; i<s.length; i++){
			alert(s[i].innerText);
		}
	}
	
	$('#aaaa').on('click',function(){
		insertDB();
	})
	
})