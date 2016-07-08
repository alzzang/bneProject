$(function() {
	
	$("#mysummernote").destroy();
	$('#mysummernote').prop('disabled', true);
    $('#mysummernote').summernote({
        minHeight: null,
        maxHeight: null,
        focus: true,
        styleWithSpan: false,
        toolbar: [
        ]                         
    });

	
	Dropzone.options.myDropzone = {
		url : "/user/upload",
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