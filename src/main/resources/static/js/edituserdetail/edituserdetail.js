var isDisable=true;
document.addEventListener("DOMContentLoaded", function(event) {

	$("#editformbtn").click(function(){
		if(isDisable)
		{
			$('#editform').css('pointer-events','auto');
			$('#editform').css('opacity','1');
			isDisable=false;
		}else
		{
			$('#editform').css('pointer-events','none');
			$('#editform').css('opacity','.5');
			isDisable=true;
		}
	});
	
	
	$("#submituser").click(function() {
		submitLogin();
	});
	
	
	
	function submitLogin() {
		var isAllValidData=true;
		var userDetails={};
		var fields= ["firstName" ,"lastName","dob"];
		for (var i = 0; $("#editform")[0].length > i; i++) {
			if(fields.includes($("#editform")[0][i].id))
			{
				if($("#"+$("#editform")[0][i].id).val().trim()=="")
				{
					$("#"+$("#editform")[0][i].id+"_err").text("This field cannot be remain blank");
					$("#"+$("#editform")[0][i].id+"_err").css("display", "block");
					isAllValidData=false;
					
				}else
				{
					$("#"+$("#editform")[0][i].id+"_err").text("");
					$("#"+$("#editform")[0][i].id+"_err").css("display", "none");
				}
				
			}
			
			if($("#editform")[0][i].id=="contactNumber" && $("#contactNumber").val().trim()!=""){
				
				if($("#contactNumber").val().length>10 || $("#contactNumber").val().length<7)
				{
					$("#"+$("#editform")[0][i].id+"_err").text("Mobile number is in wrong format");
					$("#"+$("#editform")[0][i].id+"_err").css("display", "block");
					isAllValidData=false;
				}else
				{
					$("#"+$("#editform")[0][i].id+"_err").text("");
					$("#"+$("#editform")[0][i].id+"_err").css("display", "none");
				}
				
			}
			
			
			if(!($("#editform")[0][i].id=="Update" || $("#editform")[0][i].name=="gender")){
			userDetails[$("#editform")[0][i].id]=$("#"+$("#editform")[0][i].id).val();
			}
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
				url : "updateuser",
				type: "POST",
				contentType : 'application/json; charset=utf-8',
			    data : JSON.stringify(userDetails),
				success : function(result) {
					/*$("#successmsg").text("");
					$("#successmsg").text(result);
					$("#successmsgmodal").modal();*/
					$("#pageloader").css("display", "none");
					$("#bodydiv").css("opacity", "1");
					
					window.location.href="editviewUser";
				},
				error: function (jqXHR, textStatus, errorThrown)
			    {
					alert(textStatus);
			    }
			});
		}
		
	}
	
	  $("#submitphoto").click(function(){
		  
		  if($("#fileupload").val().trim()!="")
		  {
			var extensionArray=["JPG","JPEG","PNG","PDF"];
			var filePath = $("#fileupload").val();
			var splitFilePath=filePath.split(".");
			var extention=splitFilePath[splitFilePath.length-1];
			if(extensionArray.includes(extention.toUpperCase()))
			{
				if(numberofImages<5)
				{
					$("#pageloader").css("display", "block");
					$("#bodydiv").css("opacity", ".5");
					$("#fileuploadform").submit();
				}else
				{
					$("#fileupload_err").text("User cannot upload more than 5 images in profile");
				}
			}else
			{
				$("#fileupload_err").text("file type should be one of them  JPG,JPEG,PNG,PDF .");
			}
			
		  }else{
			  $("#fileupload_err").text("Please choose the file before submit");
		  }
		  
	  });

});


function showCross(file) {
	$("#picname").val(file);

	if (file == $("#displayPic").val()) {
		$("#fileupload_err").text("You cannot delete your display picture");
	} else {
		$("#deletephoto").submit();
	}
}