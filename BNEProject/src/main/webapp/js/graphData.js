var mainpageMorrisCharts = function() {
	$.ajax({
		type : "POST",
		url : "/chart/monthlySales",
		data : {
			employee_id : $('#employee_id').val()
		},
		dataType : 'json',
		success : function(data) {
				
			var achievement = Math.round((data.sumofMonthlyGoal / data.monthlyGoal) * 100);
						
			if(isNaN(achievement)||!isFinite(achievement)){
				achievement=0;
			}

			var achievermentLimit = achievement;
				
			if(achievement>=100){
				achievermentLimit=100;
			}
					
			Morris.Donut({
				element : 'morris-donut-example',
				data : [ {
					label : "achievement rate",
					value : achievement,
					formatted : achievement+'%'
				}, {
					label : "lack of achievement rate",
					value : 100-achievermentLimit,
					formatted : 100-achievermentLimit+'%'
				} ],
				colors : [ '#95B75D', '#1caf9a'],
				formatter : function(x, data) {
					return data.formatted;
				}
			});
		},
		error : function() {
		}
	});

	$.ajax({
		type : "POST",
		url : "/chart/teamMonthlySales",
		data : {
			department_id : $('#department_id').val()
		},
		dataType : 'json',
		success : function(data) {
			var morrisData = [];
			for(var i=0; i<data.length;i++){
				morrisData.push({
					y: data[i].employee_name,
					a: data[i].sales_goal
				});
			}
			Morris.Bar({
				element : 'morris-bar-example1',
				data : morrisData,
				xkey : 'y',
				ykeys : [ 'a' ],
				labels : [ 'monthly Sales' ],
				barColors : [ '#33414E' ],
				
				resize : true				
				
			});
		},
		error : function() {
		}
	});

	$.ajax({
		type : "POST",
		url : "/chart/morrisChartLine",
		data : {
			// login employee_id 받아오기
			employee_id : $('#employee_id').val(),
			department_id: $('#department_id').val()
			
		},
		dataType : 'json',

		success : function(data) {
			var jsonLoop = new Array();
			
			list1 = data.List1;
			list2 = data.List2;
			var d = new Date();
		    var n = d.getFullYear();
			if(list2.length==0){

//				$("#morris-line-example").append('<span>no data</span>');
				jsonLoop.push({
					y : n+'',
					a : 0,
					b : 0
				});
			}
			var chartFlag = true;
			
			for (var i = 0; i < list2.length; i++) {

				for (var j = 0; j < list1.length; j++) {
					if (list2[i].reg_date === list1[j].reg_date) {

						jsonLoop.push({
							y : list2[i].reg_date,
							a : list1[j].sales,
							b : list2[i].sales
						});
						chartFlag = false;
					}
				}
				if (chartFlag) {
					jsonLoop.push({
						y : list2[i].reg_date,
						a : 0,
						b : list2[i].sales
					});
				}
				chartFlag = true;
			}
			Morris.Line({
				element : 'morris-line-example',
				data : jsonLoop,
				xkey : 'y',
				ykeys : [ 'a', 'b' ],
				labels : [ 'My Sales', 'Team Sales Avg' ],
				resize : true,
				lineColors : [ '#33414E', '#95B75D' ]
			});
		},
		error : function() {
		}
	});
}

var customerSales = function(){
	
	$.ajax({
		type : "POST",
		url : "/chart/customersSales",
		data : {
			department_id : $('#department_id').val()
		},
		dataType : 'json',
		success : function(data) {
			
			for(var i=0; i<data.length;i++){
				var html= "<div class='progress-list'> " +
						"<div class='pull-left'>" +
						"<strong>" +
						data[i].client_name +
						"</strong></div> " +
						"<div class='pull-right'>" +
						data[i].sales +"만"+
						"</div> " +
						"<div class='progress progress-small progress-striped active'>"+
						"<div class='progress-bar progress-bar-primary' role='progressbar' aria-valuenow='50' aria-valuemin='0' aria-valuemax='100' style='width: " +
						data[i].sales_rate +
						"%;'>"+
						data[i].sales_rate+
						"</div></div></div>";
				$("#customerSales").append(html);
			}
		},
		error : function() {
		}
	});
	
}


var gaugeDistance = function(data) {
	
	var employeeId=$('#employee_id').val();
	if(data!=undefined){
		employeeId=data;
	}

	$.ajax({
		type : "POST",
		url : "/chart/vehicleGauge",
		data : {
			employee_id :  employeeId
		},
		dataType : 'json',
		success : function(data) {
							
			$("#morris-bar-gaugeChart").empty();
			
			var morrisData = [];
			for(var i=0; i<data.length;i++){
				morrisData.push({
					y: data[i].day,
					a: data[i].distance
				});
			}
			Morris.Bar({
				element : 'morris-bar-gaugeChart',
				data : morrisData,
				xkey : 'y',
				ykeys : [ 'a' ],
				labels : [ 'distance' ],
				barColors : [ '#33414E' ],
				gridTextSize : 2,
				resize : true				
				
			});
		},
		error : function() {
		}
	});

}

$("#member_id").change(function() {
	gaugeDistance($(this).val());
});
var mainpageMorrisCharts = function() {
	$.ajax({
		type : "POST",
		url : "/chart/monthlySales",
		data : {
			employee_id : $('#employee_id').val()
		},
		dataType : 'json',
		success : function(data) {
				
			var achievement = Math.round((data.sumofMonthlyGoal / data.monthlyGoal) * 100);
						
			if(isNaN(achievement)||!isFinite(achievement)){
				achievement=0;
			}

			var achievermentLimit = achievement;
				
			if(achievement>=100){
				achievermentLimit=100;
			}
					
			Morris.Donut({
				element : 'morris-donut-example',
				data : [ {
					label : "achievement rate",
					value : achievement,
					formatted : achievement+'%'
				}, {
					label : "lack of achievement rate",
					value : 100-achievermentLimit,
					formatted : 100-achievermentLimit+'%'
				} ],
				colors : [  '#1caf9a','#95B75D'],
				formatter : function(x, data) {
					return data.formatted;
				}
			});
		},
		error : function() {
		}
	});

	$.ajax({
		type : "POST",
		url : "/chart/teamMonthlySales",
		data : {
			department_id : $('#department_id').val()
		},
		dataType : 'json',
		success : function(data) {
			var morrisData = [];
			for(var i=0; i<data.length;i++){
				morrisData.push({
					y: data[i].employee_name,
					a: data[i].sales_goal
				});
			}
			Morris.Bar({
				element : 'morris-bar-example1',
				data : morrisData,
				xkey : 'y',
				ykeys : [ 'a' ],
				labels : [ 'monthly Sales' ],
				barColors : [ '#1caf9a' ],

				
				resize : true				
				
			});
		},
		error : function() {
		}
	});

	$.ajax({
		type : "POST",
		url : "/chart/morrisChartLine",
		data : {
			// login employee_id 받아오기
			employee_id : $('#employee_id').val(),
			department_id: $('#department_id').val()
			
		},
		dataType : 'json',

		success : function(data) {
			var jsonLoop = new Array();
			
			list1 = data.List1;
			list2 = data.List2;
			var d = new Date();
		    var n = d.getFullYear();
			if(list2.length==0){

//				$("#morris-line-example").append('<span>no data</span>');
				jsonLoop.push({
					y : n+'',
					a : 0,
					b : 0
				});
			}
			var chartFlag = true;
			
			for (var i = 0; i < list2.length; i++) {

				for (var j = 0; j < list1.length; j++) {
					if (list2[i].reg_date === list1[j].reg_date) {

						jsonLoop.push({
							y : list2[i].reg_date,
							a : list1[j].sales,
							b : list2[i].sales
						});
						chartFlag = false;
					}
				}
				if (chartFlag) {
					jsonLoop.push({
						y : list2[i].reg_date,
						a : 0,
						b : list2[i].sales
					});
				}
				chartFlag = true;
			}
			Morris.Line({
				element : 'morris-line-example',
				data : jsonLoop,
				xkey : 'y',
				ykeys : [ 'a', 'b' ],
				labels : [ 'My Sales', 'Team Sales Avg' ],
				resize : true,
				lineColors : [ '#1caf9a','#95B75D' ]
			});
		},
		error : function() {
		}
	});
}

var customerSales = function(){
	
	$.ajax({
		type : "POST",
		url : "/chart/customersSales",
		data : {
			department_id : $('#department_id').val()
		},
		dataType : 'json',
		success : function(data) {
			
			for(var i=0; i<data.length;i++){
				var html= "<div class='progress-list'> " +
						"<div class='pull-left'>" +
						"<strong>" +
						data[i].client_name +
						"</strong></div> " +
						"<div class='pull-right'>" +
						data[i].sales +"만"+
						"</div> " +
						"<div class='progress progress-small progress-striped active'>"+
						"<div class='progress-bar progress-bar-primary' role='progressbar' aria-valuenow='50' aria-valuemin='0' aria-valuemax='100' style='width: " +
						data[i].sales_rate +
						"%;'>"+
						data[i].sales_rate+
						"</div></div></div>";
				$("#customerSales").append(html);
			}
		},
		error : function() {
		}
	});
	
}


var gaugeDistance = function(data) {
	
	var employeeId=$('#employee_id').val();
	if(data!=undefined){
		employeeId=data;
	}

	$.ajax({
		type : "POST",
		url : "/chart/vehicleGauge",
		data : {
			employee_id :  employeeId
		},
		dataType : 'json',
		success : function(data) {
							
			$("#morris-bar-gaugeChart").empty();
			
			var morrisData = [];
			for(var i=0; i<data.length;i++){
				morrisData.push({
					y: data[i].day,
					a: data[i].distance
				});
			}
			Morris.Bar({
				element : 'morris-bar-gaugeChart',
				data : morrisData,
				xkey : 'y',
				ykeys : [ 'a' ],
				labels : [ 'distance' ],
				barColors : [ '#33414E' ],
				gridTextSize : 2,
				resize : true				
				
			});
		},
		error : function() {
		}
	});

}

$("#member_id").change(function() {
	gaugeDistance($(this).val());
});
