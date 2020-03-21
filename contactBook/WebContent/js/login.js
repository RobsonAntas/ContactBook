$(document).ready(function() {
	$("#valida").submit(function() {
		var password=$("$password").value();
		var email=$("#email").value();
			$.ajax({
	            url: "LoginServlet",
	            type: "POST",
	            data: {password:password,email:email},
	            success: function(data){
	                $("#error").html(data);
	                
	            },
	            error: function(response){
	                $("#error").html(response);
	            }.done(function(){
	            	$("#email").value("");
	                $("#password").value("");
	            })           	
		});
		return false;
	});
});