<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>실종게시판 상세내용</title>
</head>
<body>
	<div class="containor">
		<div class="mainContents">
			<h2 class="floatLeft">상세내용</h2>
			<a href="<c:url value='/disappearance/list.do'/>"><input type="button" class="rightTopButtons" value="목록으로" /></a>
			<c:choose>
				<c:when test="${idcheck.equals('permission')}">
					<a href="<c:url value='/disappearance/delete.do?idx=${map.D_IDX}'/>"><input type="button" class="rightTopButtons" value="삭제" onclick="return fnDeleteCheck();" /></a>
					<a href="<c:url value='/disappearance/edit.do?idx=${map.D_IDX}'/>"><input type="button" class="rightTopButtons" value="수정" /></a>
					<c:remove scope="session" var="idcheck" />
				</c:when>
			</c:choose>
			<a href="<c:url value='/disappearance/match.do?color=${map.D_COLOR}&dog=${map.D_DOG}&gender=${map.D_GENDER}&size=${map.D_SIZE}'/>"><input type="button" class="rightTopButtons" value="매칭" /></a>
				<div class="editorTool floatLeft">
					<table>
						<tr>
							<th>애견 사진</th>
							<td><c:choose> 
			 						<c:when test="${fn:length(file) > 0 }"> 
										<c:forEach var="row" items="${file }">
				                      		<input type="hidden" id="IDX" value="${row.D_BOARD_IDX }">
				                        	<a href="<c:url value='/disappearance/download.do?idx=${map.D_IDX}'/>">${row.D_ORIGINAL_FILE_NAME }</a>
				                        	(${row.D_FILE_SIZE }KB)
				                        </c:forEach> 
									</c:when>				
									<c:otherwise> 
										<div>첨부파일이 없습니다 </div>
									 </c:otherwise> 
							  	</c:choose></td>
							<th>아이디</th>
							<td>${map.D_ID}</td>
						</tr>
						<tr>
							<th>색상</th>
							<td>${map.D_COLOR}</td>
							<th>연락처</th>
							<td>${map.D_PHONE}</td>
						</tr>
						<tr>
							<th>견종</th>
							<td>${map.D_DOG}</td>
							<th>비상연락망</th>
							<td>${map.D_EMERTGENCY_PHONE}</td>
						</tr>
						<tr>
							<th>성별</th>
							<td>${map.D_GENDER}</td>
							<th>애견 이름</th>
							<td>${map.D_DOGNAME}</td>
						</tr>
						<tr>
							<th>크기</th>
							<td>${map.D_SIZE}</td>
							<td colspan="2" rowspan="4" style="background-color: #cccccc; text-align: center; vertical-align: middle;">
								<div>
									<h1>MAP SECTION</h1>
								</div>
							</td>
						</tr>
						<tr>
							<th>실종 날짜</th>
							<td>${map.D_DATE_1}</td>
						</tr>
						<tr>
							<th>실종 지역</th>
							<td>${map.D_REGION}</td>
						</tr>
						<tr>
							<th>제목</th>
							<td>${map.D_TITLE}</td>
						</tr>
						<tr>
							<th>상세 내용</th>
							<td colspan="3">${map.D_DETAIL}</td>
						</tr>
					</table>
				</div>
		</div>
	</div>
</body>
</html>

