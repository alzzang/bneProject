$(function() {

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

	/*
	 * $('#submitButton').on("click",function(event){ event.preventDefault();
	 * var files = event.originalEvent.dataTransfer.files; var file=files[0];
	 * alert(file); });
	 */

	
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