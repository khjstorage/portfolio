<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>로그인</title>
</head>
<body>
	<div class="containor">
		<div class="mainContents">
			<h2>로그인</h2>
			<div class="login">
				<form action="<c:url value='/member/loginProcess.do'/>" method="post" enctype="multipart/form-data">
					<c:choose>
						<c:when test="${empty idfail}">
							<p>아이디와 비밀번호를 입력하세요</p>
							<input name="id" type="text" placeholder="아이디 입력" required/><br />
							<input name="pwd" type="password" placeholder="비밀번호 입력" required/><br />
							<input type="submit" value="로그인"><br />
						</c:when>
						
						<c:otherwise>
							<p>아이디와 비밀번호를 확인하세요</p>
							<input name="id" type="text" placeholder="아이디 입력" value="${idfail}" required/><br />
							<c:remove scope="session" var="idfail" />
							<input name="pwd" type="password" placeholder="비밀번호 입력" required/><br />
							<input type="submit" value="로그인"><br />
						</c:otherwise>
					</c:choose>
				
				</form>
				<a href="#">계정 찾기</a> / <a href="<c:url value='/member/register.do'/>">회원가입</a>
				
			</div>
		</div>
	</div>
</body>
</html>