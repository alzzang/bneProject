var morrisCharts = function() {

	$.ajax({
		type : "POST",
		url : "/sales",
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

	

	Morris.Bar({
		element : 'morris-bar-example1',
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
		element : 'morris-bar-example2',
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
	Morris.Bar({
		element : 'morris-bar-example3',
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

}();