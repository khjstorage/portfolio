<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="./js/jquery-3.1.0.min.js"></script>
<link rel="stylesheet" href="./css/style.css" />
<c:set var="myContextPath" value="${pageContext.request.contextPath}" />
<script>
	$(document).ready(function() {
		$("#backpage").click(function() {
			$(location).attr('href', "${myContextPath}/page_manage.jsp");
		});
		
	});
</script>
<title>�⼮�� ��ȸ(������)</title>
</head>
<body>
	<div class="main">
		<h2>${requestScope.userId}���⼮��ȸ</h2>
		<div class="attCheckDiv">
			<table class="checkTable">
				<c:forEach var="studentAtt" items="${requestScope.resultList}">
					<tbody>
						<tr>
							<td colspan="2">��¥</td>
							<td colspan="7">${studentAtt.att_dt}</td>
							<td colspan="2">�⼮</td>
							<td colspan="2">${studentAtt.att_yn}</td>
							<td colspan="2">����</td>
							<td colspan="2">${studentAtt.early_yn}</td>
							<td></td>
						</tr>
						<tr>
							<td colspan="2">�⼮�ð�</td>
							<td colspan="7">${studentAtt.att_tm}</td>
							<td colspan="2">��ǽð�</td>
							<td colspan="7">${studentAtt.exit_tm}</td>
						</tr>
					</tbody>
				</c:forEach>
 			<!--<tr style="visibility: hidden; line-height: 0;">
					<td colspan="2">�⼮�ð�</td>
					<td colspan="7">1900-00-00 00:00:00.0</td>
					<td colspan="2">��ǽð�</td>
					<td colspan="7">1900-00-00 00:00:00.0</td>
				</tr> -->
			</table>
		</div>
		<button id="backpage" class="bottomArea">�ڷ�</button>
	</div>
</body>
</html>