/**
 * 
 */
function updateDaily(id){
	event.preventDefault();
	var url = "update?daily_report_id="+id;    
	location.href=url;
}

function testJSON1(){
	$("#modalContent").val($( "#contentDiv" ).html());
	  var o = {};
	   var a = $( "#dailyModalForm" ).serializeArray();
	   
	   $.each(a, function() {
		  // alert(this.name+":"+this.value);
		     if (o[this.name]) {
	           if (!o[this.name].push) {
	               o[this.name] = [o[this.name]];
	           }
	          o[this.name].push(this.value || '');
	       } else {
	           o[this.name] = this.value || '';
	       } 
	   });
	   o["remove_Id"]=removeId;
	   
	   jsonArray.push(o);
	   console.log(o);
	   console.log(jsonArray);
	   var tagJson=JSON.stringify(o);
	   addTag(tagJson);
	    var t=JSON.stringify(jsonArray);
	   localStorage.setItem("tt", t);
}

function addTag(testdata){
	var obj = jQuery.parseJSON(testdata);
	console.log(obj);
	alert(JSON.stringify(testdata));
//	var cc="obj.title";
	var appendHtml='<li id="'+obj.remove_Id+'"><a href="#" data-toggle="modal" data-target="#myModal2" onclick="tagTest('+'\''+obj.title+'\''+','+'\''+obj.content+'\''+','+'\''+obj.counsel_id+'\''+','+'\''+obj.sec_client_id+'\''+','+'\''+obj.address+'\''+','+'\''+obj.client_id +'\''+','+'\''+obj.representative+'\''+')"><span class="fa fa-tag"></span>'
	appendHtml+=obj.title+'</a><span class="glyphicon glyphicon-remove " onclick="removeTag('+'\''+obj.remove_Id+'\''+')"></span></li>';
	//tagTest(obj.title);
	$('.list-tags').prepend(appendHtml);
	removeId++;
}
function tagTest(c,d,e,f,g,h,i){
	$('#modalTitle').val(c);
	$('#contentDiv').html(d);
	$('#counsel_id').val(e);

	$('#temp_scId').val(f);

	$("#sec_client_id").empty();
	$("#address").val("");
	selectSecontClient(e);

	$('#address').val(g);
	$('#client_id').val(h);
	$('#representative').val(i);

}

function removeTag(seq){
	jsonArray = jQuery.grep(jsonArray, function( n ) {
		alert(n.remove_Id);
		  return n.remove_Id!=seq;
	});
	var t="#"+seq;
	$(t).remove();
	 var removeJson=JSON.stringify(jsonArray);
	   localStorage.setItem("tt", removeJson);
	   alert(localStorage.getItem("tt"));
}

function aa(money,goal){
	
	/*alert(money+':'+goal);*/
	
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
