/*function lineArr(){
	var reg_date;
	var sales;
	var employee_id;
	var department_id;
}*/



var morrisCharts = function() {
	
	alert('morrisCharts ajax Line');
	
	var lineArr = new Array();
	
	var lineArr2 = new Array();
	
	//insert values at chart
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
			console.log(data.List1);
			console.log(data.List2);
			list1=data.List1;
			list2=data.List2;
			

			var chartFlag=true;
			alert(list2.length+' : '+list1.length);
			alert(list1[0].reg_date+' : '+list1[0].sales)
			for(var i=0;i<list2.length;i++){
				
				for(var j=0;j<list1.length;j++){
					if(list2[i].reg_date===list1[j].reg_date){
						alert(list1[j].reg_date+'! ! !'+list2[i].reg_date+' list1:: '+ list1[j].sales+'list2:: '+list2[i].sales);
						
						
						jsonLoop.push({y: list2[i].reg_date, a: list1[j].sales, b: list2[i].sales});
						chartFlag=false;
					}
				}				
				
				if(chartFlag){
					
					alert('flag true : '+list1[i].reg_date+'! ! !'+list2[i].reg_date+' ! '+ list1[i].sales+'list2 : '+list2[i].sales);
					jsonLoop.push({y: list2[i].reg_date, a: 0, b: list2[i].sales});
					
				}
				chartFlag=true;
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