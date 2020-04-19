
document.addEventListener("DOMContentLoaded", function(event) {

var headerDetails=$("#mainheader").html();
$("#mainheader").html("");
$("#newheader").html(headerDetails);



$("#createuser").click(function(){
	$("#pageloader").css("display", "block");
	$("#bodydiv").css("opacity", ".5");
	location.href="createuser";
	});

$("#createadvertise").click(function(){
	$("#pageloader").css("display", "block");
	$("#bodydiv").css("opacity", ".5");
	location.href="createadvertise";
	});


$("#home").click(function(){
	$("#pageloader").css("display", "block");
	$("#bodydiv").css("opacity", ".5");
	location.href="home";
	});


$("#editviewuser").click(function(){
	$("#pageloader").css("display", "block");
	$("#bodydiv").css("opacity", ".5");
	location.href="editviewUser";
	});


$("#contactus").click(function(){
	$("#pageloader").css("display", "block");
	$("#bodydiv").css("opacity", ".5");
	location.href="contactus";
	});


$("#logout").click(function(){
	$("#pageloader").css("display", "block");
	$("#bodydiv").css("opacity", ".5");
	location.href="logout";
	});

$("#searchbutton").click(function(){
	$("#pageloader").css("display", "block");
	$("#bodydiv").css("opacity", ".5");
	$("#searchpageform").submit();
	});


});

