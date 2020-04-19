
document.addEventListener("DOMContentLoaded", function(event) {
	
	$("#signinbtn").click(function(){
		submitLogin();
	});
	
		function submitLogin() {
		for (var i = 0; $("#loginform")[0].length > i; i++) {
			var id = $("#loginform")[0][i].id;
			if ($("#" + id).val().trim() == "") {
				alert(id + " cannot be blank");
				return false;
			}
		}
		$("#loginform").submit();
	}

});

