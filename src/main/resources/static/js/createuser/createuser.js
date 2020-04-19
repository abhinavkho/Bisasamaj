var isValidEmailId = false;
var isValidUserId = false;


document.addEventListener("DOMContentLoaded", function(event) {

	$("#emailId").blur(function(){
		var emailId=$("#emailId").val();
		if(emailId.trim()!="")
		{
			var pattern = /^\b[A-Z0-9._%-]+@[A-Z0-9.-]+\.[A-Z]{2,4}\b$/i
			if(pattern.test(emailId))
			{
				 $.ajax({
					url : "checkDuplicateEmailId/"+emailId,
					type: "POST",
					success : function(result) {
						if(result)
						{
							$("#emailId_err").text("Email has already been taken");
							$("#emailid_err").css("display", "block");
							isValidEmailId=false;
						}else
						{
							$("#emailId_err").text("");
							$("#emailId_err").css("display", "none");
							isValidEmailId=true;
						}
						
						document.getElementById("submituser").disabled= !(isValidEmailId && isValidUserId)
					},
					error: function (jqXHR, textStatus, errorThrown)
				    {
				 
				    }
				});
				
			}else
			{
				$("#emailId_err").text("Email format is wrong");
				$("#emailId_err").css("display", "block");
				isValidEmailId=false;
			}
				
		}else
		{
			document.getElementById("submituser").disabled=true;
			$("#emailId_err").text("Enter email id");
			$("#emailId_err").css("display", "block");
			isValidEmailId=false;
		}
	});
	
	$("#userId").blur(function(){
		

		var username=$("#userId").val();
		if(username.trim()!="")
		{
			if(username.length>3)
			{
				 $.ajax({
					url : "checkDuplicateUsername/"+username,
					type: "POST",
					success : function(result) {
						if(result)
						{
							$("#userId_err").text("Username has already been taken");
							$("#userId_err").css("display", "block");
							isValidUserId=false;
						}else
						{
							$("#userId_err").text("");
							$("#userId_err").css("display", "none");
							isValidUserId=true;
						}
						
						document.getElementById("submituser").disabled= !(isValidEmailId && isValidUserId)
					},
					error: function (jqXHR, textStatus, errorThrown)
				    {
				 
				    }
				});
				
			}else
			{
				$("#userId_err").text("Lenght is too less");
				$("#userId_err").css("display", "block");
				isValidUserId=false;
			}
				
		}else
		{
			document.getElementById("submituser").disabled=true;
			$("#userId_err").text("Enter Username");
			$("#userId_err").css("display", "block");
			isValidUserId=false;
		}
	
	});
	
	
	$("#submituser").click(function() {
		submitLogin();
	});
	
	function submitLogin() {
		var isAllValidData=true;
		var userDetails={};
		var fields= ["firstName" ,"lastName","emailId","userId","dob"];
		for (var i = 0; $("#createuserform")[0].length > i; i++) {
			if(fields.includes($("#createuserform")[0][i].id))
			{
				if($("#"+$("#createuserform")[0][i].id).val().trim()=="")
				{
					$("#"+$("#createuserform")[0][i].id+"_err").text("This field cannot be remain blank");
					$("#"+$("#createuserform")[0][i].id+"_err").css("display", "block");
					isAllValidData=false;
					
				}else
				{
					$("#"+$("#createuserform")[0][i].id+"_err").text("");
					$("#"+$("#createuserform")[0][i].id+"_err").css("display", "none");
				}
				
			}
			
			if($("#createuserform")[0][i].id=="contactNumber" && $("#contactNumber").val().trim()!=""){
				
				if($("#contactNumber").val().length>10 || $("#contactNumber").val().length<7)
				{
					$("#"+$("#createuserform")[0][i].id+"_err").text("Mobile number is in wrong format");
					$("#"+$("#createuserform")[0][i].id+"_err").css("display", "block");
					isAllValidData=false;
				}else
				{
					$("#"+$("#createuserform")[0][i].id+"_err").text("");
					$("#"+$("#createuserform")[0][i].id+"_err").css("display", "none");
				}
				
			}
			
			
			if(!($("#createuserform")[0][i].id=="submituser" || $("#createuserform")[0][i].name=="gender"))
			userDetails[$("#createuserform")[0][i].id]=$("#"+$("#createuserform")[0][i].id).val();
		}
		
		userDetails["gender"]=$("input[name=gender]:checked").val();
		if($("#dob").val().trim()!="")
		{
			var dateSplit =$("#dob").val().split("-");
			var date = new Date();
			var year=date.getFullYear()-18;
			if(dateSplit[0]>=year)
			{
				isAllValidData=false;
				$("#dob_err").text("Age should be atleast 18");
				$("#dob_err").css("display", "block");
			}
		}
		
		if(isAllValidData)
		{
			$("#pageloader").css("display", "block");
			$("#bodydiv").css("opacity", ".5");
			
			$.ajax({
				url : "submituser",
				type: "POST",
				contentType : 'application/json; charset=utf-8',
			    data : JSON.stringify(userDetails),
				success : function(result) {
					/*$("#successmsg").text("");
					$("#successmsg").text(result);
					$("#successmsgmodal").modal();*/
					
					$("#pageloader").css("display", "none");
					$("#bodydiv").css("opacity", "1");
					window.location.href="createuser";
				},
				error: function (jqXHR, textStatus, errorThrown)
			    {
					alert(textStatus);
			    }
			});
		}
		
	}

});