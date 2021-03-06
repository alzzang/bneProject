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
/* searchuserResult.jsp */
function myFunction(id) {
    var x = document.getElementById(id);
    if (x.className.indexOf("w3-show") == -1) {
        x.className += " w3-show";
    } else {
        x.className = x.className.replace(" w3-show", "");
    }
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
	$('#myModal1').on('shown.bs.modal',	function() {
		var myDropzone = Dropzone.forElement("#myDropzone");
		var mockFile = {
						name : $('#fileName').val(),
						accepted : true,
						status : Dropzone.ACCEPTED
					};
		myDropzone.options.addedfile.call(myDropzone, mockFile);
		myDropzone.options.thumbnail.call(myDropzone, mockFile,
				"http://192.168.1.18:8085/test/" + $('#fileName').val());
		myDropzone.emit("complete", mockFile);
		myDropzone.files.push(mockFile);
	});
		
	$('#myModal1,#myModal2').on('hidden.bs.modal', function() {
		$(this).find('form')[0].reset();
		$(this).find('.note-editable').empty();
		var myDropzone = Dropzone.forElement("#myDropzone");
		myDropzone.removeAllFiles();
	});
	$('#myModal4').on('hidden.bs.modal', function() {
		$("#sec_client_id").val('');
	    $(this).find('form')[0].reset();
	    $(this).find('.note-editable').empty();
	});
	
	$('#myModal5').on('hidden.bs.modal', function() {
	    $(this).find('.page-content-wrap').empty();
	});
	$("#counsel_id").change(function() {
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
		$('#counselling-footer').html('<button type="button" class="btn btn-primary pull-right" data-dismiss="modal" onclick="localSave()">Submit</button>');
	});
	
	
	
	$("#sec_client_id").change(function() {
		getStorage($(this).val());
	});

   $('#modalAdd').on('click',function(){
      $('#counselling-footer').html('<button type="button" class="btn btn-primary pull-right" data-dismiss="modal" onclick="localSave()">Submit</button>');
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
               '<div class="panel panel-primary zoomIn xn-drop-left xn-panel-dragging" style="width:250px;">'+
                    '   <div class="panel-heading">'+
                     '      <h3 class="panel-title"><span class="fa fa-comments"></span> Result</h3>'+                                
                      '     <div class="pull-right">'+
                       '        <span class="label label-danger">'+data.length+' new</span>'+
                       '    </div>'+
                       '</div>'+
                       '<div id="empSGJ" class="panel-body list-group list-group-contacts scroll" style="">'+
                       '</div>'+
                   '</div>';
            $("#empSearch").parent().append(html);

            if(data.length===0){
               $(".xn-search").attr("class","xn-search active");
               html2='<a href="#" id="iii" class="list-group-item">'+
                    '        <div class="list-group-status status-online"></div>'+
                    '        <span class="contacts-title">검색결과 없음</span>'+
                    '    </a>';
            
               $("#empSGJ").append(html2);
               
               
            }else{
               var more='<a href="#" id="iii" class="list-group-item showUserList text-center panel-footer">'+
                    '        <span class="contacts-title">List로 보기</span>'+
                    '    </a>';
               
               for(var i=0;i<data.length;i++){
                  console.log('data find '+i);
                  html2='<a href="#" id="iii'+data[i].employee_id+'" class="list-group-item">'+
                       '        <div class="list-group-status status-online"></div>'+
                       '        <img src="http://192.168.1.18:8085/test/'+data[i].file_position+'" class="pull-left" alt="John Doe" style="height:40px;"/>'+
                       '        <span class="contacts-title">'+data[i].employee_name+'('+data[i].employee_id+')</span>'+
                       '        <p><strong> mobile : '+data[i].mobile_phone+'</strong></p>'+
                       '    </a>';
               
                  $("#empSGJ").append(html2);
                  
                  (function(aaa){
                     $("#iii"+data[aaa].employee_id).on("click",function(){
                        location.href='/user/searchUser/'+data[aaa].employee_id+'';
                  });
                  })(i);
                  
               }
                    
               $("#empSGJ").append(more);
               $(".xn-search").attr("class","xn-search active");

               var ttt='';
               if(keycode=='13'){
                  myModalAutoComplete(data);
               }
               
               $(".showUserList").on("click",function(e){
                  myModalAutoComplete(data);
               });
               
            }
         },
         error:function(){
            console.log('error');
         }
      });//end ajax
   };
   
   
   myModalAutoComplete =function(data){
      $("#myModal86").find("div[class='page-content-wrap']").empty();
      for(var i=0;i<data.length;i++){
         ttt='<div class="col-md-6">'+
            '<div class="panel panel-default">'+
           '<div class="panel-body profile">'+
               '<div class="profile-image">'+
                   '<img src="http://192.168.1.18:8085/test/'+data[i].file_position+'" style="height:100px" alt="J"/>'+
              '</div>'+
              ' <div class="profile-data">'+
                  ' <div class="profile-data-name">'+data[i].employee_id+'</div>'+
                   '<div class="profile-data-title">'+data[i].employee_name+'</div>'+
               '</div>'+
               '<div class="profile-controls">'+
                   '<a href="/user/searchUser/'+data[i].employee_id+'" id="ioi'+data[i].employee_id+'" class="profile-control-left aabbcc"><span class="fa fa-info"></span></a>'+
                   '<a href="#" class="profile-control-right"><span class="fa fa-phone"></span></a>'+
               '</div>'+
           '</div>'+                                
           '<div class="panel-body">'+                                    
               '<div class="contact-info">'+
                  ' <p><small>DepartmentNum</small><br/>'+data[i].department_id+'</p>'+
                   '<p><small>MOBILE</small><br/>'+data[i].mobile_phone+'</p>'+
                   '<p><small>E-Mail</small><br/>'+data[i].email+'</p>'+
               '</div>'+
           '</div>'+                                
       '</div>'+
           '</div>';
         $("#myModal86").find("div[class='page-content-wrap']").append(ttt);
      }
      
      $("#modalAdd86").trigger("click");
   };
   
   
   
})


