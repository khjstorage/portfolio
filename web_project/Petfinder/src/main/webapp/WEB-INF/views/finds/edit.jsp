<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>발견게시판 수정</title>
</head>
<script type="text/javascript">
$(document).ready(function() {
	var re_pwd = /^[a-z0-9_-]{6,18}$/; // 비밀번호 검사식
	var re_tel = /^\d{3}-\d{3,4}-\d{4}$/; // 전화번호 검사식

	$("#submitBtn").click(function(){
		if(!re_tel.test($("#phone").val())){
			alert("휴대폰 번호를 형식에 맞게 입력해주세요");
			$("#phone").focus();
			return false;
		}
		
		if(!re_pwd.test($("#pwd").val())){
			alert("비밀번호는 6글자 이상 18글자 이하로 설정해주세요");
			$("#pwd").focus();
			return false;
		}
	});
});



</script>
<body>
   <div class="containor">
      <div class="mainContents">
         <h2 style="float:left;">발견게시판 수정</h2>
         <form id="" action="<c:url value='/finds/update.do?idx=${map.infoMap.F_IDX}'/>" method="post" enctype="multipart/form-data">
           <a href="/finds/list.do"><input type="button" class="rightTopButtons" value="취소"></a>
           <input type="submit" id="submitBtn" class="rightTopButtons" value="수정" onclick="return fnUpdateCheck();"/>
            <div class="editorTool" style="float: left;">
               <table>
                  <tr>
                     <th>애견 사진</th>
                     <td><input type="file" accept="image/*" name="finds_file" multiple="multiple" /></td>
                     <th>닉네임</th>
                     <td><input type="text" name="name" placeholder="닉네임" value="${map.infoMap.F_NAME}" required /></td>
                  </tr>
                  <tr>
                     <th>색상</th>
                     <td>
                        <select name="color" required>
                           <option value="">색상</option>
                           <option value="검정" <c:if test="${map.infoMap.F_COLOR eq '검정'}">selected</c:if>>검정</option>
                           <option value="흰색" <c:if test="${map.infoMap.F_COLOR eq '흰색'}">selected</c:if>>흰색</option>
                        </select>
                     </td>
                     <th>연락처</th>
                     <td><input type="text" id="phone" name="phone" placeholder="010-0000-0000" value="${map.infoMap.F_PHONE}" /></td>
                  </tr>
                  <tr>
                     <th>견종</th>
                     <td>
                        <select name="dog" required>
                           <option value="">견종</option>
                           <option value="코카스파니엘" <c:if test="${map.infoMap.F_DOG eq '코카스파니엘'}">selected</c:if>>코카스파니엘</option>
                           <option value="요크셔테리어" <c:if test="${map.infoMap.F_DOG eq '요크셔테리어'}">selected</c:if>>요크셔테리어</option>
                           <option value="허스키" <c:if test="${map.infoMap.F_DOG eq '허스키'}">selected</c:if>>허스키</option>
                           <option value="웰시코기" <c:if test="${map.infoMap.F_DOG eq '웰시코기'}">selected</c:if>>웰시코기</option>
                           <option value="말티즈" <c:if test="${map.infoMap.F_DOG eq '말티즈'}">selected</c:if>>말티즈</option>
                        </select>
                     </td>
                     <th>비밀번호</th>
                     <td><input type="password" id="pwd" name="pwd" placeholder="비밀번호" value="${map.infoMap.F_PASSWORD}" /></td>
                  </tr>
                  <tr>
                     <th>성별</th>
                     <td>
                        <div class="checks">
                        	<input type="radio" id="gender_f" name="gender" value="암컷" <c:if test="${map.infoMap.F_GENDER eq '암컷'}">checked</c:if> />
                        	<label for="gender_f"> 암컷 </label>
                        </div>
                        <div class="checks">
                        	<input type="radio" id="gender_m" name="gender" value="수컷" <c:if test="${map.infoMap.F_GENDER eq '수컷'}">checked</c:if> /> 
                        	<label for="gender_m"> 수컷 </label>
                        </div>
                        <div class="checks">
							<input type="radio" id="gender_un" name="gender" value="모름" <c:if test="${map.infoMap.F_GENDER eq '모름'}">checked</c:if>/>
							<label for="gender_un"> 모름 </label>
                        </div>
                     </td>
                     <th></th>
                     <td></td>
                  </tr>
                  <tr>
                     <th>크기</th>
                     <td>
                        <div class="checks">
                           <input type="radio" id="size_s" name="size" value="소형" <c:if test="${map.infoMap.F_SIZE eq '소형'}">checked</c:if> />
                           <label for="size_s"> 소 </label>
                        </div>
                        <div class="checks">
                           <input type="radio" id="size_m" name="size" value="중형" <c:if test="${map.infoMap.F_SIZE eq '중형'}">checked</c:if> /> <label
                              for="size_m"> 중 </label>
                        </div>
                        <div class="checks">
                           <input type="radio" id="size_l" name="size" value="대형" <c:if test="${map.infoMap.F_SIZE eq '대형'}">checked</c:if> /> <label
                              for="size_l"> 대 </label>
                        </div>
                     </td>
                     <td colspan="2" rowspan="4" style="background-color:#cccccc;text-align:center;vertical-align:middle;">
                        <div>
                           <h1>MAP SECTION</h1>
                        </div>
                     </td>
                  </tr>
                  <tr>
                     <th>발견 날짜</th>
                     <td><input type="date" id="date" name="date" value="${map.infoMap.F_DATE_1}"/></td>
                  </tr>
                  <tr>
                     <th>발견 지역</th>
                     <td>
	                     <select name="region">
	                           <option value="">발견지역</option>
	                           <option value="서울" <c:if test="${map.infoMap.F_REGION eq '서울'}">selected</c:if>>서울</option>
	                           <option value="경기" <c:if test="${map.infoMap.F_REGION eq '경기'}">selected</c:if>>경기</option>
	                           <option value="일본" <c:if test="${map.infoMap.F_REGION eq '일본'}">selected</c:if>>일본</option>
	                     </select>
                     </td>
                  </tr>
                  <tr>
                     <th>제목</th>
                     <td><input type="text" name="title" maxlength="20" value="${map.infoMap.F_TITLE}" style="width:90%; text-align:left;" /></td>
                  </tr>
                  <tr>
                     <th>상세 내용</th>
                     <td colspan="3"><textarea id="detail" name="detail">${map.infoMap.F_DETAIL}</textarea></td>
                  </tr>
                  <tr>
                  <th>첨부파일</th>
                  <td>
                  	<c:choose>
                  		<c:when test="${fn:length(map.fileMap) > 0 }">
	                       	<c:forEach var="row" items="${map.fileMap }">
		                        <input type="hidden" id="IDX" value="${row.F_IDX }">
		                        <a>${row.F_ORIGINAL_FILE_NAME }</a> 
		                        (${row.F_FILE_SIZE } KB)
                    		</c:forEach>
                    	</c:when>
	                    <c:otherwise>
	                    	<div>첨부파일이 없습니다</div>
	                    </c:otherwise>
                    </c:choose>
                	</td>
                  </tr> 
               </table>
            </div>
         </form>
      </div>
   </div>
</body>
</html>