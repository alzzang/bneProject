$(function(){
	  // JavaScript form validation

	 alert('usersettings.js');
	
    var checkPassword = function(str)
    {
      var re = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}$/;
      return re.test(str);
    };

    var checkForm = function(e)
    {
    	 alert('incheckForm');
      if(this.password.value == "") {
        alert("Error: Username cannot be blank!");
        this.password.focus(); 
        e.preventDefault(); // equivalent to return false
        return;
      }
      re = /^\w+$/;
      if(!re.test(this.password.value)) {
        alert("Error: Username must contain only letters, numbers and underscores!");
        this.password.focus();
        e.preventDefault();
        return;
      }
      if(this.pwd1.value != "" && this.pwd1.value == this.pwd2.value) {
        if(!checkPassword(this.pwd1.value)) {
          alert("The password you have entered is not valid!");
          this.pwd1.focus();
          e.preventDefault();
          return;
        }
      } else {
        alert("Error: Please check that you've entered and confirmed your password!");
        this.pwd1.focus();
        e.preventDefault();
        return;
      }
      
      alert("Both username and password are VALID!");
 /*     var formObj =document.getElementById("myPasswordForm");
      formObj.attr("action", "/user/validCheck");
 */     
     
      
      
      //  /user/validCheck
      /* 무한 스크롤 전 ajax 테스트 실행 */
//		$(".btn-default").on("click", function() {
//			formObj.attr("action", "/user/validCheck");
//			formObj.attr("method", "post");
//		});
      
    };


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