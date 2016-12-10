<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><link rel="stylesheet" type="text/css" href="style.css"/>
	<title>발견게시판 상세내용</title>
</head>
<script type="text/javascript">
$(document).ready(function() {
	// 발견게시물 수정 비밀번호 입력 폼
	$("#findsUpdateFormView").click(function() {
		$(".findsUpdateFormView").toggleClass("hide");
		$(".findsDeleteFormView").addClass("hide");
	});
	// 발견게시물 삭제 비밀번호 입력 폼
	$("#findsDeleteFormView").click(function() {
		$(".findsDeleteFormView").toggleClass("hide");
		$(".findsUpdateFormView").addClass("hide");
	});
});


</script>
<body>
   <div class="containor">
      <div class="mainContents">
        <h2 style="float:left;">상세내용</h2>
        <a href="<c:url value='/finds/list.do'/>"><input type="button" class="rightTopButtons" value="목록으로" /></a>
		
		<!-- 발견게시물 삭제 비밀번호 입력 -->
		<input type="button" class="rightTopButtons" id="findsDeleteFormView" value="삭제" />
		<div class="findsDeleteFormView hide">
			<img src="${pageContext.request.contextPath}/resources/img/findsUpdateForm.png"/>
			<form action="<c:url value='/finds/delete_auth.do'/>" method="post" enctype="multipart/form-data">
				<input name="idx" type="hidden" value ="${map.F_IDX}"/>                     
				<input name="pwd" type="password" placeholder="게시물 비밀번호 입력" /><br />
				<input type="submit" value="확인" id="deleteSubmit" onclick="return fnDeleteCheck();"/>
			</form>
		</div>
		
		<!-- 발견게시물 수정 비밀번호 입력 -->
		<input type="button" class="rightTopButtons" id="findsUpdateFormView" value="수정" />
		<div class="findsUpdateFormView hide">
			<img src="${pageContext.request.contextPath}/resources/img/findsUpdateForm.png"/>
			<form action="<c:url value='/finds/update_auth.do'/>" method="post" enctype="multipart/form-data">
				<input name="idx" type="hidden" value ="${map.F_IDX}"/>                     
				<input name="pwd" type="password" placeholder="게시물 비밀번호 입력" /><br />
				<input type="submit" value="확인"/>
			</form>
		</div>
		
        <a href="<c:url value='/finds/match.do?color=${map.F_COLOR}&dog=${map.F_DOG}&gender=${map.F_GENDER}&size=${map.F_SIZE}'/>"><input type="button" class="rightTopButtons" value="매칭" /></a>
            <div class="editorTool" style="float: left;">
               <table>
                  <tr>
                     <th>애견 사진</th>
                     <td>
                      <c:choose> 
 						<c:when test="${fn:length(file) > 0 }"> 
							<c:forEach var="row" items="${file }">
	                      		<input type="hidden" id="IDX" value="${row.F_BOARD_IDX }">
	                        	<a href="/finds/download.do?idx=${map.F_IDX}">${row.F_ORIGINAL_FILE_NAME }</a>
	                        	(${row.F_FILE_SIZE }KB)
	                        </c:forEach> 
						</c:when>				
						<c:otherwise> 
							<div>첨부파일이 없습니다 </div>
						 </c:otherwise> 
					</c:choose> 
                     <th>닉네임</th>
                     <td>${map.F_NAME}</td>
                  </tr>
                  <tr>
                     <th>색상</th>
                     <td>${map.F_COLOR}</td>
                     <th>연락처</th>
                     <td>${map.F_PHONE}</td>
                  </tr>
                  <tr>
                     <th>견종</th>
                     <td>${map.F_DOG}</td>
                     <th></th>
                     <td></td>
                  </tr>
                  <tr>
                     <th>성별</th>
                     <td>${map.F_GENDER}</td>
                     <th></th>
                     <td></td>
                  </tr>
                  <tr>
                     <th>크기</th>
                     <td>${map.F_SIZE}
                     <td colspan="2" rowspan="4" style="background-color:#cccccc;text-align:center;vertical-align:middle;">
                        <div>
                           <h1>MAP SECTION</h1>
                        </div>
                     </td>
                  </tr>
                  <tr>
                     <th>발견 날짜</th>
                     <td>${map.F_DATE_1}</td>
                  </tr>
                  <tr>
                     <th>발견 지역</th>
                     <td>${map.F_REGION}</td>
                  </tr>
                  <tr>
                     <th>제목</th>
                     <td>${map.F_TITLE}</td>
                  </tr>
                  <tr>
                     <th>상세 내용</th>
                     <td colspan="3">${map.F_DETAIL}</td>
                  </tr>
               </table>
            </div>
      </div>
   </div>
</body>
</html>