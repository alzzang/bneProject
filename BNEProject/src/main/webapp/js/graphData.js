var morrisCharts = function() {
	$.ajax({
		type : "POST",
		url : "/monthlySales",
		data : {
			employee_id : $('#employee_id').val()
		},
		dataType : 'json',
		success : function(data) {

			var achievement = data.sumofMonthlyGoal / data.monthlyGoal * 100;
			Morris.Donut({
				element : 'morris-donut-example',
				data : [ {
					label : "achievement rate",
					value : achievement,
					formatted : achievement+'%'
				}, {
					label : "lack of achievement rate",
					value : 100-achievement,
					formatted : 100-achievement+'%'
				} ],
				formatter : function(x, data) {
					return data.formatted;
				}
			});
		},
		error : function() {
			alert("nono");
		}
	});

	$.ajax({
		type : "POST",
		url : "/teamMonthlySales",
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
				labels : [ 'monthly Sales' ]
			});
		},
		error : function() {
			alert("nono");
		}
	});

	

	Morris.Bar({
		element : 'morris-bar-example2',
		data : [ {
			y : '일',
			a : 260
		}, {
			y : '월',
			a : 2850
		}, {
			y : '화',
			a : 2133
		}, {
			y : '수',
			a : 2754
		}, {
			y : '목',
			a : 2905
		}, {
			y : '금',
			a : 2163
		}, {
			y : '토',
			a : 731
		} ],
		xkey : 'y',
		ykeys : [ 'a' ],
		labels : [ 'Series A' ]
	});
	Morris.Bar({
		element : 'morris-bar-example3',
		data : [ {
			y : '홍길동',
			a : 260
		}, {
			y : '홍길동',
			a : 2850
		}, {
			y : '홍길동',
			a : 2133
		}, {
			y : '홍길동',
			a : 2754
		}, {
			y : '홍길동',
			a : 2905
		}, {
			y : '홍길동',
			a : 2163
		}, {
			y : '홍길동',
			a : 2163
		}, {
			y : '홍길동',
			a : 2163
		}, {
			y : '홍길동',
			a : 2850
		}, {
			y : '홍길동',
			a : 2133
		}, {
			y : '홍길동',
			a : 2754
		}, {
			y : '홍길동',
			a : 2905
		}, {
			y : '홍길동',
			a : 2163
		}, {
			y : '홍길동',
			a : 2163
		}, {
			y : '홍길동',
			a : 2163
		}, {
			y : '홍길동',
			a : 731
		} ],
		xkey : 'y',
		ykeys : [ 'a' ],
		labels : [ 'Series A' ]
	});


}();