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
					console.log(data);
					var str = JSON.stringify(data);
					var list = $.parseJSON(str);
					console.log(str);
					var len = Object.keys(list).length;
					console.log(list)

					var len = Object.keys(data).length;

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
				}
			})
}

$(function() {
	
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
		$(this).find('#myDropzone').filter($('.dz-preview').remove());
	});

	$("#counsel_id").change(function() {
		$("#sec_client_id").empty();
		$("#address").val("");
		selectSecontClient($(this).val());
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
		paramName : 'NewImages',
		url : "/user/upload",
		acceptedFiles : 'image/*',
		autoDiscover : false,
		autoProcessQueue : false,

		init : function() {
			var submitButton = document.querySelector("#submitButton");
			var myDropzone = this;
			submitButton.addEventListener("click", function() {
				if (myDropzone.getQueuedFiles().length > 0) {
					myDropzone.processQueue();
				} else {
					updateImage();
				}
			});
			this.on('success', function() {
				location.reload();
			});
		}
	};


})