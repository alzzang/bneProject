/*$(function() {
	
	$('#changebutton').click(function() {
		alert("aa");
		val flag=false;
		$.ajax({
			type : "POST",
			url : "/user/validCheck",
			data : {
				id : '${sessionScope.user}',
				password : $('#password').val()
			},
			
			success : function(data) {
				flag=data;

				if (($('#renewpassword').val() === $('#newpassword').val())&& flag==true) {
					
				}
				
			}
		})
		return "redirect:/main";
		

	});

});*/