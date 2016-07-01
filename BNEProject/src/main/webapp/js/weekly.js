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
		insertDB();
	})
	
})