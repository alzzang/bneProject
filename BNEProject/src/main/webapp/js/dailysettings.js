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
	   o["counsel_id"]=0;
	   +jsonArray.push(o);
	   console.log(o);
	   console.log(jsonArray);
	   var tagJson=JSON.stringify(o);
	   addTag(tagJson);
	    var t=JSON.stringify(jsonArray);
	    alert(':sss'+t);
	   localStorage.setItem("tt", t);
}
//local 저장소 update
function testJSON2(val){
	var localVar=jQuery.parseJSON(localStorage.getItem("tt"));
	var tempIdx;
	$.each( localVar, function( idx, value ) {
			if(value.remove_Id==val){
				tempIdx=idx;
				return false;
			}
			
	});
	
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
	   o["remove_Id"]=val;
	   o["counsel_id"]=0;
	   
	   jsonArray[tempIdx]=o;
	   
	   /*
	    * html 코드 변경된걸로 적용  onclick과 span값 변경하기
	    * 그리고 JSON으로 변환후 로컬스토리지에 다시 저장하기
	   */
	   $('#'+val+' a').attr('onclick', 'tagTest('+o.counsel_id+','+'\''+o.title+'\''+','+'\''+o.content+'\''+','+'\''+o.counselling_id+'\''+','+'\''+o.sec_client_id+'\''+','+'\''+o.address+'\''+','+'\''+o.client_id+'\''+','+'\''+o.representative+'\''+')'); 
	   $('#'+val+' a').html('<span class="fa fa-tag"></span>'+o.title);
	   //  tagTest('+'\''+obj.counsel_id+'\''+','+'\''+obj.title+'\''+','+'\''+obj.content+'\''+','+'\''+obj.counselling_id+'\''+','+'\''+obj.sec_client_id+'\''+','+'\''+obj.address+'\''+','+'\''+obj.client_id +'\''+','+'\''+obj.representative+'\''+','+'\''+obj.remove_Id+'\''+')"
	   /*$('#'+val+' a').bind('click', function() {
		   alert('aa');
		 });*/
	   var t=JSON.stringify(jsonArray);
	   localStorage.setItem("tt", t);
	   console.log(t);
}
//db update
function testJSON3(val){
	var localVar=jQuery.parseJSON(localStorage.getItem("tt"));
	var tempIdx;
	$.each( localVar, function( idx, value ) {
			if(value.counsel_id==val){
				tempIdx=idx;
				return false;
			}
			
	});
	alert(tempIdx);
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
/*	   o["remove_Id"]=val;*/
	   o["counsel_id"]=val;
	   
	   jsonArray[tempIdx]=o;
	   
	   /*
	    * html 코드 변경된걸로 적용  onclick과 span값 변경하기
	    * 그리고 JSON으로 변환후 로컬스토리지에 다시 저장하기
	   */
	   $('#'+val+' a').attr('onclick', 'tagTest('+o.counsel_id+','+'\''+o.title+'\''+','+'\''+o.content+'\''+','+'\''+o.counselling_id+'\''+','+'\''+o.sec_client_id+'\''+','+'\''+o.address+'\''+','+'\''+o.client_id+'\''+','+'\''+o.representative+'\''+','+'\''+o.counsel_id+'\''+')'); 
	   $('#'+val+' a').html('<span class="fa fa-tag"></span>'+o.title);
	   
	   
	   var t=JSON.stringify(jsonArray);
	   localStorage.setItem("tt", t);
	   alert('db업데이트후'+t);
	   console.log(t);
}
function addTag(testdata){
	var obj = jQuery.parseJSON(testdata);
	console.log(obj);
	alert(JSON.stringify(testdata));
//	var cc="obj.title";
	var appendHtml='<li id="'+obj.remove_Id+'"><a href="#" data-toggle="modal" data-target="#myModal2" onclick="tagTest('+'\''+obj.counsel_id+'\''+','+'\''+obj.title+'\''+','+'\''+obj.content+'\''+','+'\''+obj.counselling_id+'\''+','+'\''+obj.sec_client_id+'\''+','+'\''+obj.address+'\''+','+'\''+obj.client_id +'\''+','+'\''+obj.representative+'\''+','+'\''+obj.remove_Id+'\''+')"><span class="fa fa-tag"></span>'
	appendHtml+=obj.title+'</a><span class="glyphicon glyphicon-remove " onclick="removeTag('+'\''+obj.remove_Id+'\''+','+0+')"></span></li>';
	$('.list-tags').prepend(appendHtml);
	removeId++;
}


function tagTest(b,c,d,e,f,g,h,i,j){
	if(b==0){
		$('#modalTitle').val(c);
		$('#contentDiv').html(d);
		//$('#counsel_id').val(e);
		$('#counselling_id').val(e);
		
		$('#temp_scId').val(f);
		$("#sec_client_id").empty();
		$("#address").val("");
		selectSecontClient(e);
		
		$('#address').val(g);
		$('#client_id').val(h);
		$('#representative').val(i);	
		
		$('#counselling-footer').html('<button type="button" class="btn btn-primary pull-right" data-dismiss="modal" onclick="testJSON2('+j+')">Submit</button>');
	}else{
		alert('띠용!!');
		$('#modalTitle').val(c);
		$('#contentDiv').html(d);
		//$('#counsel_id').val(e);
		$('#counselling_id').val(e);
		
		$('#temp_scId').val(f);
		
		$("#sec_client_id").empty();
		$("#address").val("");
		selectSecontClient(e);
		
		$('#address').val(g);
		$('#client_id').val(h);
		$('#representative').val(i);
		$('#counselling-footer').html('<button type="button" class="btn btn-primary pull-right" data-dismiss="modal" onclick="testJSON3('+j+')">Submit</button>');
	}
	

}
function tagDetail(department_name,employee_name,reg_date,title,client_id,client_name,representative,sec_client_name,address,content){
	alert(department_name);
	$('#counsel_department').html(department_name);
	$('#counsel_empname').html(employee_name);
	$('#counsel_regdate').html(reg_date);
	$('#counsel_title').html(title);
	$('#counsel_code').html(client_id);
	$('#counsel_departmentname').html(client_name);
	$('#counsel_representative').html(representative);
	$('#counsel_sec_client').html(sec_client_name);
	$('#counsel_address').html(address);
	$('#counsel_content').html(content);
}
function removeTag(seq,val){
	if(val==0){
		alert(0);
		jsonArray = jQuery.grep(jsonArray, function( n ) {
			alert(n.remove_Id);
			  return n.remove_Id!=seq;
		});
	}else if(val==1){
		alert(1);
		console.log(jsonArray);
		$.each( jsonArray, function( idx, value ) {
			if(value["counsel_id"]==seq){
				value["counsel_id"]=value["counsel_id"]*-1;
				return false;
			}
			
		});
	}
	
	
	var t="#"+seq;
	$(t).remove();
	 var removeJson=JSON.stringify(jsonArray);
	   localStorage.setItem("tt", removeJson);
	   alert(localStorage.getItem("tt"));
}

function changeProgress(money,goal){
	
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
				$('#inputSales').attr('onKeyUp', 'changeProgress(this.value,'+0+')');
			}else{
				$('#dailyGoal').attr('value',result);
				  $('#inputSales').attr('onKeyUp', 'changeProgress(this.value,'+result+')'); 
			}

		}

	})
}
