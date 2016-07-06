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

function selectSecontClient(value) {

	$
			.ajax({
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

		url : "/user/upload",
		acceptedFiles : 'image/*',
		autoDiscover : false,
		autoProcessQueue : false,
		init : function() {
			var submitButton = document.querySelector("#submitButton");
			var myDropzone = this;
			submitButton.addEventListener("click", function() {
				myDropzone.processQueue();
			});
			this.on('success', function() {
				location.reload();
			});
		}
	};

})