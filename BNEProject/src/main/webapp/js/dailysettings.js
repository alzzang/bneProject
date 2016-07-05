/**
 * 
 */

function testJSON1(){

	  var o = {};
	   var a = $( "#dailyform" ).serializeArray();
	   $.each(a, function() {
		     if (o[this.name]) {
	           if (!o[this.name].push) {
	               o[this.name] = [o[this.name]];
	           }
	          o[this.name].push(this.value || '');
	       } else {
	           o[this.name] = this.value || '';
	       } 
	   });
	   jsonArray.push(o);
	    var t=JSON.stringify(jsonArray);
	   localStorage.setItem("tt", t);
	   alert(localStorage.getItem("tt"));
}

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

/*function computeGuage(){
	  var before=$('#before_gauge').val();
	  var after=$('#after_gauge').val();
	  var result=after-before;
	  $('#result_guage').val(result);
}*/

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

function computeGuage(){
	  var before=$('#before_gauge').val();
	  var after=$('#after_gauge').val();
	  var result=after-before;
	  $('#result_guage').val(result);
}


function searchSalesGoal(reg_date) {

	$.ajax({
		type : "POST",
		url : "/dailyReport/dailysales",
		data : {
		 
			reg_date : $('#reg_date').val()
		},
		success : function(data) {
			var result=parseInt(data);
			if(result==-1){
				alert('해당 목표액이 존재하지 않습니다');
				$('#dailyGoal').attr('value',0);
				$('#aaaa').attr('onKeyUp', 'aa(this.value,'+0+')');
			}else{
				$('#dailyGoal').attr('value',result);
				  $('#aaaa').attr('onKeyUp', 'aa(this.value,'+result+')'); 
			}

		}

	})
}
