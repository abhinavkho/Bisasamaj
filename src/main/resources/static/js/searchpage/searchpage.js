var startIndex=10;
var isProcessCompleted=true;

document.addEventListener("DOMContentLoaded", function(event) {

	$("#agenumber").text($("#age").val());
	$("#age").on('change', function() {
		$("#agenumber").text($("#age").val());
	});
	
	
	$(document).scroll(function(e){
	    if ($(window).scrollTop() >= ($(document).height() - $(window).height())*0.9){
	    	if(startIndex<totalResult && isProcessCompleted)
	    	{
	    		isProcessCompleted=false;
	    		$("#searchLoader").css("display","block");
	    		getPendingdata(searchType);
	    	}
	    }
	});
	
	$("#filtersearchbtn").click(function(){
		$("#pageloader").css("display", "block");
		$("#bodydiv").css("opacity", ".5");
		$("#searchFilterResultform").submit();
	})
	
	if(searchType=="F")
	{
		$("#firstName").val(searchfilterData.firstName[0]);
		$("#lastName").val(searchfilterData.lastName[0]);
		$("#age").val(searchfilterData.age[0]);
		$("#agenumber").text($("#age").val());
		$("#gotra").val(searchfilterData.gotra[0]);
		$("#caste").val(searchfilterData.caste[0]);
	}

});



function loadFullProfile(id)
{

	$("#pageloader").css("display", "block");
	$("#bodydiv").css("opacity", ".5");
	 $.ajax({
		url : "loadfullprofileofuser/"+id,
		type: "POST",
		success : function(result) {
			$("#fullprofilemodaltitle").text(result['firstName']+" "+result['lastName']+" Bio Data")
			for(var key in result){ 
				if(key!="fileNameList")
				$("#"+key+"_mdl").text(result[key]);
			}
			
			
			
			if(result.fileNameList.length!=0){
				$('#userimages').text("");
				result.fileNameList.forEach(function(imagePath){
					$('#userimages').prepend($('<img>',{class:'userimages',src:imagePath}));
				});
			}else
			{
				$('#userimages').text("Photos not Available");
			}
			
            $("#inputhiddenUserId").val(result['id']);
            $('#download').text("");
            $("#download").prepend("<a href=\"download?id="+result['id']+"\" id=\"downloaduserdata\" >Download</a>");
            $("#pageloader").css("display", "none");
            $("#bodydiv").css("opacity", "1");
            $("#fullprofilemodal").modal();
		},
		error: function (jqXHR, textStatus, errorThrown)
	    {
	 
	    }
	});
	
}

function email()
{
	var id=$("#inputhiddenUserId").val();
	
	 $.ajax({
			url : "email/"+id,
			type: "POST",
			success : function(result) {
				$('#fullprofilemodal').modal('hide');
				$("#messageModalBody").text("");
				$("#messageModalBody").text(result);
				$("#messageModal").modal();
			},
			error: function (jqXHR, textStatus, errorThrown)
		    {
		 
		    }
		});
}


function getPendingdata(searchType)
{
	var searchCriteria={};
	searchCriteria["gender"] =$("#gender").val();
	searchCriteria["startIndex"] = startIndex;
	searchCriteria["searchType"] = searchType;
	if(searchType=="F")
	{
		searchCriteria["firstName"] = $("#firstname").val();
		searchCriteria["lastName"] =  $("#lastName").val();
		searchCriteria["age"] =  $("#age").val();
		searchCriteria["caste"] = $("#caste").val();
		searchCriteria["gotra"] =  $("#gotra").val();
	}
	$.ajax({
		url : "getpendingdata",
		type: "POST",
		contentType : 'application/json; charset=utf-8',
	    data : JSON.stringify(searchCriteria),
		success : function(result) {
			isProcessCompleted=true;
			startIndex+=10;
			
			 var searchDiv = $("#searchresultdiv").html();
			 
			 for(var resultCounter=0;result.length>resultCounter;resultCounter++)
			 {
				var userDetail=result[resultCounter];
				var span ="<span>"+
					"<table style=\"width: 100%\" class=\"resultbox shadow-sm p-3 mb-5 bg-blue rounded\">"+
						"<tbody><tr>"+
							"<td rowspan=\"5\" class=\"imageWidth\"><img src=\"i"+userDetail.displayPic+"\" alt=\"no image\" class=\"image_set\" onclick=\"loadFullProfile('11')\"><input type=\"hidden\" id=\"userid\" value=\"11\"></td>"+
							"<td><span>"+userDetail.firstName+"</span> <span>"+userDetail.lastName+"</span></td>"+
						"</tr>"+
						"<tr>"+
							"<td>DOB : <span>"+userDetail.dob+"</span></td>"+
						"</tr>"+
						"<tr>"+
							"<td>Contact Number : <span>"+userDetail.contactNumber+"</span></td>"+
						"</tr>"+
						"<tr>"+
							"<td>Gotra : <span>"+userDetail.gotra+"</span></td>"+
						"</tr>"+
					"</tbody></table>"+
				"</span>";
				
				searchDiv+=span;
				
			 }
			 
			 $("#searchresultdiv").html(searchDiv);
			
			 $("#searchLoader").css("display","none");
		},
		error: function (jqXHR, textStatus, errorThrown)
	    {
			 $("#searchLoader").css("display","none");
	    }
	});
}

