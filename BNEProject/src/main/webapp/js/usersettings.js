function getStorage(sec_id) {
    if(typeof(Storage) !== "undefined") {
    	
    	if (localStorage.secClient) {
    
    		alert(localStorage.getItem["secClient"]);
    	   		
    		
    	
         } 
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
			
			
			window.localStorage.clear();
			localStorage.setItem('secClient', list);
			
			
			if (len > 0) {
				for (var i = 0; i < len; i++) {
					var html = "<option value='' disabled selected hidden='ture'>선택하세요!</option> ";
					html += "<option value="+list[i].sec_client_id + ">" + list[i].sec_client_name +"</option>";
					$("#sec_client_id").append(html);
				}
			}
		}
	})

}





$(function() {
	
	$("#counsel_id").change(function(){
		$("#sec_client_id").empty();
			selectSecontClient($(this).val());
	});

	
	$("#sec_client_id").change(function(){
		getStorage($(this).val());
	});

	
	
	
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
		
	
	var checkPassword = function(str)
	    {
	      var re = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$/;
	      return re.test(str);
	    };

	    var checkForm = function(e)
	    {
	    
	      if(this.password.value == "") {
	        alert("Error: password cannot be blank!");
	        this.password.focus(); 
	        e.preventDefault(); // equivalent to return false
	        return;
	      }
	      re = /^\w+$/;
	      if(!re.test(this.password.value)) {
	        alert("Error: password must contain only letters, numbers and underscores!");
	        this.password.focus();
	        e.preventDefault();
	        return;
	      }
	      if(this.newpassword.value != "" && this.newpassword.value == this.renewpassword.value) {
	        if(!checkPassword(this.newpassword.value)) {
	          alert("The password you have entered is not valid!");
	          this.newpassword.focus();
	          e.preventDefault();
	          return;
	        }
	      } else {
	        alert("Error: Please check that you've entered and confirmed your password!");
	        this.newpassword.focus();
	        e.preventDefault();
	        return;
	      }};
	
	
	 
	    var myForm = document.getElementById("myPasswordForm");
	    myForm.addEventListener("submit", checkForm, true);

	    // HTML5 form validation

	    var supports_input_validity = function()
	    {
	      var i = document.createElement("input");
	      return "setCustomValidity" in i;
	    }

	    if(supports_input_validity()) {
	      var usernameInput = document.getElementById("password");
	      usernameInput.setCustomValidity(usernameInput.title);

	      var pwd1Input = document.getElementById("newpassword");
	      pwd1Input.setCustomValidity(pwd1Input.title);

	      var pwd2Input = document.getElementById("renewpassword");

	      // input key handlers

	      usernameInput.addEventListener("keyup", function() {
	        usernameInput.setCustomValidity(this.validity.patternMismatch ? usernameInput.title : "");
	      }, false);

	      pwd1Input.addEventListener("keyup", function() {
	        this.setCustomValidity(this.validity.patternMismatch ? pwd1Input.title : "");
	        if(this.checkValidity()) {
	          pwd2Input.pattern = this.value;
	          pwd2Input.setCustomValidity(pwd2Input.title);
	        } else {
	          pwd2Input.pattern = this.pattern;
	          pwd2Input.setCustomValidity("");
	        }
	      }, false);
	      pwd2Input.addEventListener("keyup", function() {
	        this.setCustomValidity(this.validity.patternMismatch ? pwd2Input.title : "");
	      }, false);
	    }
	    

})