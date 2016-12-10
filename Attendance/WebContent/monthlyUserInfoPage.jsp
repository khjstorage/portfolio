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
		$("#backpage").click(function() {
			$(location).attr('href', "${myContextPath}/page_user.jsp");
		});

		$("#process").click(function() {
			var $form = $("#actionForm");
			$form.attr("action", "${myContextPath}/MonthlyUserViewsServlet");
			$form.submit();
		});
	});
</script>
<title>Insert title here</title>
</head>
<body>
	<%
		String id = "";
		id = (String) session.getAttribute("id");
	%>
	<div class="title">

		<h2><%=id%>님 월별출석
		</h2>
		<form id="actionForm" class="monthAttArea" action="" method="post">
			<input class="selMonth" name="month" type="month" value="${requestScope.month}"/>
			<button id="process" class=monthAttButton>조회</button>
		</form>
		<div class="userMonthAttTable">
			<table class="checkTable">
				<thead>
					<tr>
						<td>이름이름이름</td>
						<td>출석출석출석</td>
						<td>조퇴조퇴조퇴</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>이름</td>
						<td>출석일</td>
						<td>조퇴일</td>
					</tr>
					<tr>
						<td>${requestScope.name}</td>
						<td>${requestScope.att_cnt}일</td>
						<td>${requestScope.early_cnt}일</td>
					</tr>
				</tbody>
			</table>
		</div>
		<button id="backpage" class="bottomArea">뒤로</button>
	</div>
</body>
</html>