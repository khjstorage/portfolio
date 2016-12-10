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
			$(location).attr('href', "${myContextPath}/page_manage.jsp");
		});
		
	    $("#process").click(function() {
	        var $form = $("#actionForm");
	        $form.attr("action", "${myContextPath}/MonthlyAdminViewsServlet");
	        $form.submit();
	    });
	});
</script>
<title>Insert title here</title>
</head>
<body>
	<!-- 관리자 월별 출석조회 -->
	<div class="main">
		<h2>회원님들 월별출석</h2>
		<form id="actionForm" class="monthAttArea" action="" method="post">
			<input class="selMonth" name="month" type="month" value="${requestScope.month}"/>
			<button id="process" class="monthAttButton">조회</button>
		</form>
		<div class="adminMonthAttTable">
			<table class="checkTable">
				<thead>
					<tr>
						<td>아이디아이디</td>
						<td>이름이름</td>
						<td>출석출석</td>
						<td>조퇴조퇴</td>
					</tr>
				</thead>
				<c:forEach var="monthly" items="${requestScope.resultList}">
					<tbody>
						<tr>
							<td>ID</td>
							<td>이름</td>
							<td>출석일</td>
							<td>조퇴일</td>
						</tr>
						<tr>
							<td>${monthly.id}</td>
							<td>${monthly.name}</td>
							<td>${monthly.att_cnt}</td>
							<td>${monthly.early_cnt}</td>
						</tr>
					</tbody>
				</c:forEach>
			</table>
		</div>
		<button id="backpage" class="bottomArea">뒤로</button>
	</div>
</body>
</html>