<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<c:set var="myContextPath" value="${pageContext.request.contextPath}" />
<meta charset="UTF-8">
<script src="./js/jquery-3.1.0.min.js"></script>
<link rel="stylesheet" href="./css/style.css" />

<title>회원 페이지</title>
</head>
<body>
	<%
		String id = "";
		id = (String) session.getAttribute("id");
	%>

		
	<div class="title">
		<h2><%=id%></h2>
		<form action="${myContextPath}/attCheck.jsp" method="post">
			<button class="Obj">출석체크</button>
		</form>
		
		<form action="${myContextPath}/AttUserInfoServlet" method="post">
			<button class="leftArea">출석조회</button>
		</form>
		
		<form action="${myContextPath}/monthlyUserInfoPage.jsp" method="post">
			<button class="rightArea">월별조회</button>
		</form>
		
		<form action="${myContextPath}/LogoutServlet" method="post">
			<button class="bottomArea">로그아웃</button>
		</form>
	</div>
</body>
</html>