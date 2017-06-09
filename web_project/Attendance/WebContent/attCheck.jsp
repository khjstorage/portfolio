<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date, java.text.SimpleDateFormat"%>
<%@ page import="paran.attendance.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/style.css" />
<script src="./js/jquery-3.1.0.min.js"></script>
<c:set var="myContextPath" value="${pageContext.request.contextPath}" />
<title>출석체크</title>
<script>
$(document).ready(function() {
	$("#backpage").click(function() {
		$(location).attr('href', "${myContextPath}/page_user.jsp");
		
	});
	
});		
</script>
<script>
window.onload = function(){
	(function startTime() {
	    var today = new Date();
	    var h = today.getHours();
	    var m = today.getMinutes();
	    var s = today.getSeconds();
	    m = checkTime(m);
	    s = checkTime(s);
	    document.getElementById('clock').innerHTML =
	    h + ":" + m + ":" + s;
	    var t = setTimeout(startTime, 1000);
	})();
	function checkTime(i) {
	    if (i < 10) {i = "0" + i}; // 숫자가 10보다 작을 경우 앞에 0을 붙여줌
	    return i;
	}
}
</script>
</head>
<body>

	<%
		java.text.SimpleDateFormat dateformatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String today = dateformatter.format(new java.util.Date());
	%>
	<%
		String id = "";
		id = (String) session.getAttribute("id");
		AttendanceDBBean manager = new AttendanceDBBean();
		String[] arr = manager.onOffAttCheckBtn(id);
	%>

	<%
		if("Y".equals(arr[0])){
			if(arr[1]==null){
	%>
				<div class="title">
					<h2><%=id%>님 출석체크</h2>
					<div class="Obj" style="font-size:15px; line-height:45px;">
						날짜 <%=today %> / 시간 <span id="clock"></span>
					</div>
					
					<form action="${myContextPath}/EntranceServlet" method="post">
						<button class="leftArea disButton" disabled>입실</button>
					</form>
					
					<form action="${myContextPath}/ExitServlet" method="post" >
						<button class="rightArea">퇴실</button>
					</form>
					
					<button id="backpage" class="bottomArea">뒤로</button>
				</div>
	<%
			}else{
	%>	
				<div class="title">
					<h2><%=id%>님 출석체크</h2>
					<div class="Obj" style="font-size:15px; line-height:45px;">
						날짜 <%=today %> / 시간 <span id="clock"></span>
					</div>
					
					<form action="${myContextPath}/EntranceServlet" method="post">
						<button class="leftArea disButton" disabled>입실</button>
					</form>
					
					<form action="${myContextPath}/ExitServlet" method="post" >
						<button class="rightArea disButton" disabled>퇴실</button>
					</form>
					
					<button id="backpage" class="bottomArea">뒤로</button>
				</div>
	<%
			}	
		}else{
	%>
				<div class="title">
					<h2><%=id%>님 출석체크</h2>
					<div class="Obj" style="font-size:15px; line-height:45px;">
						날짜 <%=today %> / 시간 <span id="clock"></span>
					</div>
					
					<form action="${myContextPath}/EntranceServlet" method="post">
						<button class="leftArea">입실</button>
					</form>
					
					<form action="${myContextPath}/ExitServlet" method="post" >
						<button class="rightArea disButton"disabled>퇴실</button>
					</form>
					
					<button id="backpage" class="bottomArea">뒤로</button>
				</div>
	<%
		}
	%>
	


</body>
</html>