function visibleAddForm() {
	$("#addEmployeeFormButton").val();
	if($("#addEmployeeFormButton").hasClass('btn-danger')) { //폼 추가
		var formHtml =		'<td><input type="text" class="form-control"></td>'+
							'<td><input type="text" class="form-control"></td>'+
							'<td><input type="text" class="form-control"></td>'+
							'<td><input type="text" class="form-control"></td>'+
							'<td><input type="text" class="form-control"></td>'+
							'<td>'+
								'<div class="input-group">'+
									'<input type="text" class="form-control"> <span'+
										' class="input-group-addon"><img'+
										' src="data:image/svg+xml;utf8;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/Pgo8IS0tIEdlbmVyYXRvcjogQWRvYmUgSWxsdXN0cmF0b3IgMTguMC4wLCBTVkcgRXhwb3J0IFBsdWctSW4gLiBTVkcgVmVyc2lvbjogNi4wMCBCdWlsZCAwKSAgLS0+CjwhRE9DVFlQRSBzdmcgUFVCTElDICItLy9XM0MvL0RURCBTVkcgMS4xLy9FTiIgImh0dHA6Ly93d3cudzMub3JnL0dyYXBoaWNzL1NWRy8xLjEvRFREL3N2ZzExLmR0ZCI+CjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB4bWxuczp4bGluaz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94bGluayIgdmVyc2lvbj0iMS4xIiBpZD0iQ2FwYV8xIiB4PSIwcHgiIHk9IjBweCIgdmlld0JveD0iMCAwIDQ5My4zMzIgNDkzLjMzMiIgc3R5bGU9ImVuYWJsZS1iYWNrZ3JvdW5kOm5ldyAwIDAgNDkzLjMzMiA0OTMuMzMyOyIgeG1sOnNwYWNlPSJwcmVzZXJ2ZSIgd2lkdGg9IjE2cHgiIGhlaWdodD0iMTZweCI+CjxnIGlkPSJYTUxJRF83OF8iPgoJPGcgaWQ9IlhNTElEXzc5XyI+CgkJPGcgaWQ9IlhNTElEXzgwXyI+CgkJCTxwYXRoIGlkPSJYTUxJRF84MV8iIGQ9Ik0zNTIuNzMxLDQ1MC45NTZjMy4wMzcsOS43ODktMi4wNDQsMjAuMjEzLTExLjYxNiwyMy44NzhjLTM1LjgxMSwxMy43MDktNjkuMjk0LDE4LjQ5OC0xMTIuODczLDE4LjQ5OCAgICAgYy0xMTcuNjYzLDAtMjIxLjE0Ny04NC4zNDctMjIxLjE0Ny0yMjMuMjc1QzcuMDk1LDEyNS40NiwxMTIuMDAzLDAsMjcyLjIsMGMxMjQuNzM1LDAsMjE0LjAzNiw4NS43NjQsMjE0LjAzNiwyMDQuODQ1ICAgICBjMCwxMDMuNDgzLTU4LjEyLDE2OC42OTMtMTM0LjY2LDE2OC42OTNjLTMzLjMyNywwLTU3LjQxOS0xNy4wMDctNjAuOTU1LTU0LjU4MmgtMS40MjRjLTIxLjk2MywzNi4xNS01My44Niw1NC41ODItOTEuNDMsNTQuNTgyICAgICBjLTQ2LjA4MiwwLTc5LjM3Ni0zNC4wMjItNzkuMzc2LTkyLjE0MmMwLTg2LjQ3NSw2My43NjktMTY1LjE0NywxNjUuODQxLTE2NS4xNDdjMTguNjkxLDAsMzguOSwyLjc5NSw1NS45MDksNy4xNzIgICAgIGMxNi40MjUsNC4yMjcsMjYuODYxLDIwLjIzNiwyNC4xNDcsMzYuOTc4bC0xNi45NzIsMTA0LjY5NWMtNy4wNzIsNDEuODE4LTIuMTEyLDYwLjk1MywxNy43MjEsNjEuNjY2ICAgICBjMzAuNDc1LDAuNzExLDY4Ljc2Mi0zOC4yNzIsNjguNzYyLTExOS43ODZjMC05Mi4xNS01OS41NDMtMTYzLjczMi0xNjkuNDEtMTYzLjczMmMtMTA4LjQ0NCwwLTIwMy40MjgsODUuMDUxLTIwMy40MjgsMjIwLjQzNiAgICAgYzAsMTE4LjM2OSw3NS44MzQsMTg1LjcwOCwxODEuNDQ4LDE4NS43MDhjMjcuNDg5LDAsNTYuMjAzLTQuNTEsODAuODUzLTEzLjIxNGM1Ljg2My0yLjA3LDEyLjMxNS0xLjY2MiwxNy44NzIsMS4xMjYgICAgIGM1LjU1OCwyLjc4OCw5Ljc1Myw3LjcxNiwxMS41OTUsMTMuNjU1TDM1Mi43MzEsNDUwLjk1NnogTTI5My45NTQsMTgxLjM2MmMwLjUzNC0zLjYyNi0xLjg5Mi03LjAyNy01LjQ5Ni03LjY5NiAgICAgYy00LjQxMy0wLjgxOS05LjUzNi0xLjQyNi0xNS41NzMtMS40MjZjLTQ2Ljc3MSwwLTgzLjYxNyw0Ni4wNzYtODMuNjE3LDEwMC42NTNjMCwyNi45NCwxMi4wMzcsNDMuOTQ2LDM1LjQyMyw0My45NDYgICAgIGMyNi4yMzEsMCw1My44NzYtMzMuMzEsNjAuMjY1LTc0LjQyNUwyOTMuOTU0LDE4MS4zNjJ6IiBmaWxsPSIjRkZGRkZGIi8+CgkJPC9nPgoJPC9nPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+CjxnPgo8L2c+Cjwvc3ZnPgo=" /></span>'+
									'<input type="text" class="form-control">'+
								'</div>'+
							'</td>'+
							'<td>'+
								'<ul class="panel-controls">'+
									'<li><a href="#" class="panel-collapse"><span'+
											' class="glyphicon glyphicon-ok"></span></a></li>'+
								'</ul>' + 
							'</td>';
						
		$("#employeeAddForm").html(formHtml);
		$("#addEmployeeFormButton").html('<span class="fa fa-minus"></span> 숨기기');
		$("#addEmployeeFormButton").removeClass('btn btn-danger').addClass('btn btn-primary');
	}else {
		$("#employeeAddForm").empty();
		$("#addEmployeeFormButton").html('<span class="fa fa-plus"></span> 사원 추가');
		$("#addEmployeeFormButton").removeClass('btn btn-primary').addClass('btn btn-danger');
	}
}