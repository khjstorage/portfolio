$(document).ready(function() {
	if(location.href.match("login"))
		$("#pageFocusLogin").addClass("pageFocus");
	
	if(location.href.match("register"))
		$("#pageFocusRegister").addClass("pageFocus");
	
	if(location.href.match("disappearance"))
		$("#pageFocusDisList").addClass("pageFocus");
	
	if(location.href.match("finds"))
		$("#pageFocusFindList").addClass("pageFocus");
	
	if(location.href.match("etc"))
		$("#pageFocusAbout").addClass("pageFocus");
});