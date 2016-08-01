function getStorage(sec_id) {
	if (typeof (Storage) !== "undefined") {
		var secClient = $.parseJSON(localStorage.getItem("secClient"));
		if (localStorage.secClient) {
			for (var i = 0; i < localStorage.secClient.length; i++) {
				if (sec_id == secClient[i].sec_client_id) {
					$("#address").val(secClient[i].address);
					/* localStorage.removeItem('secClient'); */
					return;
				}
			}
		}
	}
}

function updateImage() {
	$.ajax({
		type : "POST",
		url : "/user/defaultFile",
		data : {
			id : $('#employee_id').val()
		},
		success : function(data) {
			location.reload(); 
		},
	})
}

function selectSecontClient(value) {
	$.ajax({	
		type : "POST",
		url : "/counselling/secondaryClient",
		data : {
			client_id : value
		},
			dataType : 'json',
			success : function(data) {
				var str = JSON.stringify(data);
				var list = $.parseJSON(str);
				var len = Object.keys(list).length;
							
				$("#client_id").val(list[0].client_id);
				$("#representative").val(list[0].representative);
				localStorage.setItem('secClient', str);
				if (len > 0) {
					for (var i = 0; i < len; i++) {
						var html = "<option value='' disabled selected hidden='ture'>선택하세요!</option> ";
						html += "<option value=" + list[i].sec_client_id
								+ ">" + list[i].sec_client_name
								+ "</option>";
						$("#sec_client_id").append(html);
					}
				}
				$("#sec_client_id").val($("#temp_scId").val());
			}
	})
}

$(function() {
	$('#sockettest').on('click',function(){
		socket.emit('notice',{fromId:($("#employee_id").val()), toId:'2', message:'새로운 메세지'});
	});	
	
	$('#myModal1').on('shown.bs.modal',	function() {
		var myDropzone = Dropzone.forElement("#myDropzone");
		var mockFile = {
						name : $('#fileName').val(),
						accepted : true,
						size : 12345,
						status : Dropzone.ACCEPTED
					};
		myDropzone.options.addedfile.call(myDropzone, mockFile);
		myDropzone.options.thumbnail.call(myDropzone, mockFile,
				"/user/download/" + $('#fileName').val() + "/");
		myDropzone.emit("complete", mockFile);
		myDropzone.files.push(mockFile);
	});
		
		
	$('#myModal1,#myModal2').on('hidden.bs.modal', function() {
		$(this).find('form')[0].reset();
		$(this).find('.note-editable').empty();
		var myDropzone = Dropzone.forElement("#myDropzone");
		myDropzone.removeAllFiles();
	});
	$("#counsel_id").change(function() {
		$("#sec_client_id").val('');
	    $(this).find('form')[0].reset();
	    $(this).find('.note-editable').empty();
	    //$(this).find('#myDropzone').filter($('.dz-preview').remove());
	});
	$('#myModal4').on('hidden.bs.modal', function() {
		$("#sec_client_id").val('');
	    $(this).find('form')[0].reset();
	    $(this).find('.note-editable').empty();
	});

	$("#counselling_id").change(function() {
		$("#sec_client_id").empty();
		$("#address").val("");
		$("#temp_scId").val('');
		selectSecontClient($(this).val());
	});

	$('#modalAdd').on('click',function(){
		$('#counselling-footer').html('<button type="button" class="btn btn-primary pull-right" data-dismiss="modal" onclick="testJSON1()">Submit</button>');
	});
	
	
	
	$("#sec_client_id").change(function() {
		getStorage($(this).val());
	});

	$("#mysummernote").destroy();
	$('#mysummernote').prop('disabled', true);
	$('#mysummernote').summernote({
		minHeight : null,
		maxHeight : null,
		focus : true,
		styleWithSpan : false,
		toolbar : []
	});

		
	Dropzone.options.myDropzone = {
	//	paramName : 'NewImages',
	//	url : "/user/upload",
		acceptedFiles : 'image/*',
		autoDiscover : false,
		autoProcessQueue : false,
		init : function() {
			var submitButton = document.querySelector("#submitButton");
			var myDropzone = this;
			submitButton.addEventListener("click", function() {
				if (myDropzone.getQueuedFiles().length > 0) {
					myDropzone.processQueue();
				} 
				else {
					if($("#fileName").val()=='default.png'){
						location.reload();
					}
					else
					updateImage();
				}
			});
			this.on('success', function() {
				location.reload();
			});
		}
	};
	
/*	$('.sub-menu ul').hide();
	$(".sub-menu a").click(function () {
		$(this).parent(".sub-menu").children("ul").slideToggle("200");
		$(this).find("i.fa").toggleClass("fa-angle-up fa-angle-down");
	});
*/	
	$("#empSearch").on("keyup",function(e){
		/* db에 보낼 str*/
		var str=$("#empSearch").val();
		var html='';
		var html2='';
		var keycode = e.keyCode;
		console.log(keycode+':'+str.length);
		console.log(str);
		$(".xn-search").attr("class","xn-search");
		if(str==''){
		}else{
			empSearchKeyUp(str,keycode);
		}
		
		
		
	});
	
	$("#empSearch").on("click",function(e){
		e.stopPropagation();
	});
	
	
	empSearchKeyUp = function(str,keycode){
		$.ajax({
			type : "POST",
    		url : "/user/empSearch",
			data:{
				empSearch:str
			},
			dataType:"json",
			success:function(data){
				console.log('success');
				$("#empSearch").parent().find(".panel-primary").remove();
				html=
					'<div class="panel panel-primary zoomIn xn-drop-left xn-panel-dragging">'+
	                 '   <div class="panel-heading">'+
	                  '      <h3 class="panel-title"><span class="fa fa-comments"></span> Result</h3>'+                                
	                   '     <div class="pull-right">'+
	                    '        <span class="label label-danger">'+data.length+' new</span>'+
	                    '    </div>'+
	                    '</div>'+
	                    '<div id="empSGJ" class="panel-body list-group list-group-contacts scroll" style="">'+
	                    
	                    '</div>     '+
//	                    '<div class="panel-footer text-center">'+
//	                    '    <a href="pages-messages.html">Show all messages</a>'+
//	                    '</div>'+                            
	                '</div>';
				$("#empSearch").parent().append(html);

				if(data.length===0){
					console.log('data.length===0');
					$(".xn-search").attr("class","xn-search active");
					html2='<a href="#" id="iii" class="list-group-item">'+
                    '        <div class="list-group-status status-online"></div>'+
                    '        <span class="contacts-title">검색결과 없음</span>'+
                    '    </a>';
				
					$("#empSGJ").append(html2);
					
					
				}else{
					for(var i=0;i<data.length;i++){
						console.log('data find '+i);
						html2='<a href="#" id="iii'+data[i].employee_id+'" class="list-group-item">'+
	                    '        <div class="list-group-status status-online"></div>'+
	                    '        <img src="/assets/images/users/user2.jpg" class="pull-left" alt="John Doe"/>'+
	                    '        <span class="contacts-title">'+data[i].employee_name+'</span>'+
	                    '        <p>Praesent placerat tellus id augue condimentum</p>'+
	                    '    </a>';
					
						$("#empSGJ").append(html2);
						
						(function(aaa){
							$("#iii"+data[aaa].employee_id).on("click",function(){
								location.href='/user/searchUser/'+data[aaa].employee_id+'';
						});
						})(i);
						
					}
					
					
					$(".xn-search").attr("class","xn-search active");
					
					if(keycode=='13'){
						console.log('str:'+str);
						
						
						
						
						
						
					}
					
				}// end else
				
				//alert(data[0].employee_id+data[0].employee_name);
				
				
				
			},
			error:function(){
				console.log('error');
			}
			
			
		});//end ajax
		
	};

})