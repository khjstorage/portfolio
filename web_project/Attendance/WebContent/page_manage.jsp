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
<link rel="stylesheet" href="./css/style.css" />
<script src="./js/jquery-3.1.0.min.js"></script>
<script>
   $(document).ready(function() {
      $("#btnUserId").click(function() {
         if (!$.trim($("#userId").val())) {
            alert("아이디를 입력하세요.");
            $("#userId").focus();
            return false;
         }
         return true;
      });
   });
</script>
<title>매니저 페이지</title>
</head>
<body>
	<%
      String id = "";
      id = (String) session.getAttribute("id");
   %>
	<div class="adminPage">
		<h1><%=id%></h1>
		<form action="${myContextPath}/AttAdminInfoServlet" method="post">
			<input class="leftTopArea" type="Text" id="userId" name="userId"
				placeholder="아이디" />
			<button id="btnUserId" class="rightTopArea">출석조회</button>
		</form>
		<div class="po">
			<form action="${myContextPath}/monthlyAdminInfoPage.jsp" method="post">
				<button class="Obj">월별출석조회</button>
			</form>
			<form action="${myContextPath}/registerForm.jsp" method="post">
				<button class="Obj" type="submit">사용자등록</button>
			</form>
		</div>
		<form action="${myContextPath}/LogoutServlet" method="post">
			<button class="bottomArea" type="submit">로그아웃</button>
		</form>

	</div>
</body>
</html>