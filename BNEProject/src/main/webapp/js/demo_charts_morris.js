/*function lineArr(){
	var reg_date;
	var sales;
	var employee_id;
	var department_id;
}*/



var morrisCharts = function() {
	
	alert('morrisCharts ajax Line');
	
	var lineArr = new Array();
	var jsonLoop = new Array();
	
	$.ajax({
		type : "POST",
		url : "/morrisChartLine",
		data : {
			//login employee_id 받아오기
			employee_id : 1
		},
		dataType : 'json',

		success : function(data) {
			alert('success');
			console.log(data);
			/*var str = JSON.stringify(data);
			var list = $.parseJSON(str);
			console.log(str);
			var len = Object.keys(list).length;
			console.log(list);
			var len = Object.keys().length;
			alert(len);*/
			for(var i=0;i<data.length;i++){
				lineArr.push({reg_date:data[i].reg_date,
							  sales:data[i].sales	
							});
			}
			
			for(var i=0;i<data.length;i++){
				jsonLoop.push({y: lineArr[i].reg_date, a: lineArr[i].sales, b: 90});
			}
			/*jsonLoop.push({y: '2001', a: 11, b: 90});
			jsonLoop.push({y: '2002', a: 21, b: 90});
			jsonLoop.push({y: '2003', a: 31, b: 90});*/
			
			for(var i=0;i<3;i++){
				alert(lineArr[i].sales);
			}
			
			Morris.Line({
		    	
			      element: 'morris-line-example',
			      data: jsonLoop,
			      xkey: 'y',
			      ykeys: ['a', 'b'],
			      labels: ['My Sales', 'Avg Team Sales'],
			      resize: true,
			      lineColors: ['#33414E', '#95B75D']
			    });

			
			
		},
		error : function(){
			alert('error');
		}
	});
	
	
	
	


    Morris.Area({
        element: 'morris-area-example',
        data: [
            { y: '2006', a: 100, b: 90 },
            { y: '2007', a: 75,  b: 65 },
            { y: '2008', a: 50,  b: 40 },
            { y: '2009', a: 75,  b: 65 },
            { y: '2010', a: 50,  b: 40 },
            { y: '2011', a: 75,  b: 65 },
            { y: '2012', a: 100, b: 90 }
        ],
        xkey: 'y',
        ykeys: ['a', 'b'],
        labels: ['Series A', 'Series B'],
        resize: true,
        lineColors: ['#1caf9a', '#FEA223']
    });


    Morris.Bar({
        element: 'morris-bar-example',
        data: [
            { y: '2006', a: 100, b: 90 },
            { y: '2007', a: 75,  b: 65 },
            { y: '2008', a: 50,  b: 40 },
            { y: '2009', a: 75,  b: 65 },
            { y: '2010', a: 50,  b: 40 },
            { y: '2011', a: 75,  b: 65 },
            { y: '2012', a: 100, b: 90 }
        ],
        xkey: 'y',
        ykeys: ['a', 'b'],
        labels: ['Series A', 'Series B'],
        barColors: ['#B64645', '#33414E']
    });


    Morris.Donut({
        element: 'morris-donut-example',
        data: [
            {label: "Download Sales", value: 12},
            {label: "In-Store Sales", value: 30},
            {label: "Mail-Order Sales", value: 20}
        ],
        colors: ['#95B75D', '#1caf9a', '#FEA223']
    });

}();