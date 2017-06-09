<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:set var="myContextPath" value="${pageContext.request.contextPath}" />
		<%-- ${pageContext.request.scheme}: http
			 ${pageContext.request.serverName}: localhost
			 ${pageContext.request.serverPort}: 8080
			 ${pageContext.request.contextPath}: /someApp --%>
<link rel="stylesheet" href="./css/style.css" />
<script src="./js/jquery-3.1.0.min.js"></script>
<script>
	$(document).ready(function() {
		$("#login").click(function() {
			if (!$.trim($("#id").val())) {
				alert("아이디를 입력하세요.");
				$("#id").focus();
				return false;
			}
			if (!$.trim($("#passwd").val())) {
				alert("비밀번호를 입력하세요.");
				$("#passwd").focus();
				return false;
			}
			return true;
		});
	});
</script>

<title>메인 페이지</title>
</head>
</body>
<form id="actionForm" action="${myContextPath}/LoginProcServlet" method="post">
	<div class="title">
		<h1>운영반 출석관리</h1>
		<input type="text" id="id" name="id" placeholder="ID" class="Obj" />
		<input type="password" id="passwd" name="passwd" placeholder="PASSWORD" class="Obj" />
		<button id="login" class="bottomArea">로그인</button>
		<c:if test='${loginSuccess=="id_false"}'>
			<script>alert("존재하지않는 아이디입니다")</script>
		</c:if>
		<c:if test='${loginSuccess=="pwd_false"}'>
			<script>
				alert("비밀번호가 틀렸습니다")
				var id = "${id}";
				$("#id").val(id);
				$("#passwd").focus();
			</script>
		</c:if>
	</div>
</form>
</body>
</html>
