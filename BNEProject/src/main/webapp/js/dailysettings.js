/**
 * 
 */

function aa(money,goal){
	

	
	var achievementRate=money/goal*10000;
	achievementRate=Math.round(achievementRate);
	achievementRate=achievementRate/100;
	/*achievementRate=Math.round10(achievementRate,-3); */
	if(achievementRate == 0) {
		var rate=achievementRate.toString()+'%';
		$("#progressCondition").css('width',0.1);
		$("#progressCondition").html(rate);
		
	}
	else{

		if(achievementRate>100){
			achievementRate=100;
		}
		var rate=achievementRate.toString()+'%';
		$("#progressCondition").html(rate);
		$("#progressCondition").css('width',rate);
	}
	
	
}

function computeGuage(){
	  var before=$('#before_gauge').val();
	  var after=$('#after_gauge').val();
	  var result=after-before;
	  $('#result_guage').val(result);
}

function approvalDaily() {
    /*var txt;*/
    var r = confirm("승인하시겠습니까?");
    if (r == true) {
    	$.ajax({
    		type : "POST",
    		url : "/dailyReport/approval",
    		data : {
    			report_id: $('#report_id').val()
    		},
    		success : function(data) {
    			$('#approvalDiv').remove();
    			alert('승인되었습니다.');
    		}
    	})
    	
        /*txt = "You pressed OK!";*/
    } else {
    	alert('승인이 취소되었습니다.');
        /*txt = "You pressed Cancel!";*/
    }
    /*document.getElementById("demo").innerHTML = txt;*/
}