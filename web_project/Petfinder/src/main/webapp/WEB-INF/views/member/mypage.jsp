<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>My Page</title>
</head>
<script>
$(document).ready(function(){
	var re_pwd = /^[a-z0-9_-]{6,18}$/; // 비밀번호 검사식
	var re_name = /^[가-힣a-zA-Z]+$/; // 이름 검사식
	var re_tel = /^\d{3}-\d{3,4}-\d{4}$/; // 전화번호 검사식
		
	$("#submitBtn").click(function(){
		if(!re_pwd.test($("#pwd").val())){
			alert("비밀번호는 6글자 이상 18글자 이하로 설정해주세요");
			$("#pwd").focus();
			return false;
		}

		if(!re_name.test($("#name").val())){
			alert("이름형식이 올바르지 않습니다.");
			$("#name").focus();
			return false;
		}
		
		if(!re_tel.test($("#phone").val())){
			alert("휴대폰번호를 형식에 맞게 적어주세요")
			$("#phone").focus();
			return false;
		}
		
	});
});
</script>
<body>
<div class="containor">
	<div class="mainContents">
	  <h2 style="float: left;">My Page</h2>
	  <form action= "<c:url value='/member/update.do'/>" method="post" enctype="multipart/form-data">
	  	<a href="<c:url value='/member/delete.do'/>"><input class="rightTopButtons" type="button" value="탈퇴" onclick="return fnDeleteCheck();"/></a>
	  	<input class="rightTopButtons" id="submitBtn" type="submit" value="수정" onclick="return fnUpdateCheck();"/>
	  	<c:forEach items="${memberlist}" var="member">
	      	<div class="editorTool">
	          	<table class="myPageTable">
	              	<tr>
	                  	<th>애견 사진</th>
	                    	<td><input type="file" accept="image/*" name="member_file" multiple="multiple"/></td>
	                    <th>아이디</th>
	                     	<td><input type="text" name="id" placeholder="아이디" value="${member.M_ID}" readOnly /></td>
	                </tr>
	                <tr>
	                  	<th>색상</th>
	                     	<td>
		                    	<select id="color" name="color">
		                           <option value="">색상</option>
		                           <option value="갈색" <c:if test="${member.M_COLOR eq '갈색'}">selected</c:if>>갈색</option>
		                           <option value="검정색" <c:if test="${member.M_COLOR eq '검정색'}">selected</c:if>>검정색</option>
		                           <option value="얼룩(흰색+검정색)" <c:if test="${member.M_COLOR eq '얼룩(흰색+검정색)'}">selected</c:if>>얼룩(흰색+검정색)</option>
		                           <option value="얼룩(흰색+갈색)" <c:if test="${member.M_COLOR eq '얼룩(흰색+갈색)'}">selected</c:if>>얼룩(흰색+갈색)</option>
		                           <option value="얼룩(갈색+검정색)" <c:if test="${member.M_COLOR eq '얼룩(갈색+검정색)'}">selected</c:if>>얼룩(갈색+검정색)</option>
		                    	</select>
	                     	</td>
	                    <th>연락처</th>
	                      	<td><input type="text" id="phone" name="phone" placeholder="010-0000-0000" value="${member.M_PHONE}" /></td>
	                </tr>
	                <tr>
	                   	<th>견종</th>
	                   		<td>
	                   			<select name="dog">
		                            <option value="">견종</option>
		                            <option value="코카스파니엘" <c:if test="${member.M_DOG eq '코카스파니엘'}">selected</c:if>>코카스파니엘</option>
		                            <option value="요크셔테리어" <c:if test="${member.M_DOG eq '요크셔테리어'}">selected</c:if>>요크셔테리어</option>
		                            <option value="허스키" <c:if test="${member.M_DOG eq '허스키'}">selected</c:if>>허스키</option>
	                            	<option value="웰시코기" <c:if test="${member.M_DOG eq '웰시코기'}">selected</c:if>>웰시코기</option>
	                      		</select>
	                      	</td>
	                     <th>비밀번호</th>
	                     	<td><input type="password" id="pwd" name="pwd" placeholder="비밀번호" value="${member.M_PASSWORD}" /></td>
	                  </tr>
	                  <tr>
	                     <th>성별</th>
	                      	<td>
	                      	 <div class="checks">
	                         	<input type="radio" id="gender_m" name="gender" value="수컷" <c:if test="${member.M_GENDER eq '수컷'}">checked</c:if> />
	                         	<label for="gender_m"> 수컷 </label>
	                       	 </div>
	                       	<div class="checks">
	                       		<input type="radio" id="gender_f" name="gender" value="암컷" <c:if test="${member.M_GENDER eq '암컷'}">checked</c:if>/> 
	                       		<label for="gender_f"> 암컷 </label>
	                       	</div>
	                     	</td>
	                     <th>이름</th>
	                    	<td><input type="text" id="name" name="name" placeholder="이름" value="${member.M_NAME}" /></td>
	                  </tr>
	                  <tr>
	                     <th>크기</th>
	                      	<td>
	                      	  <div class="checks">
	                            <input type="radio" id="size_s" name="size" value="소형" <c:if test="${member.M_SIZE eq '소형'}">checked</c:if> />
	                            <label for="size_s"> 중형 </label>
	                         </div>
	                         <div class="checks">
	                            <input type="radio" id="size_m" name="size" value="중형" <c:if test="${member.M_SIZE eq '중형'}">checked</c:if> />
	                            <label for="size_m"> 중형 </label>
	                         </div>
	                         <div class="checks">
	                            <input type="radio" id="size_l" name="size" value="대형" <c:if test="${member.M_SIZE eq '대형'}">checked</c:if>  />
	                            <label for="size_l"> 대형 </label>
	                         </div>
	                		</td>
	                     		<td colspan="2" rowspan="4" style="background-color: #cccccc; text-align: center; vertical-align: middle;">
	                        
	                        <div class="card">
	                           <img src="<c:url value='/image/memberfile/${member.M_STORED_FILE_NAME}' />" />
	                        </div>
	                        <div class="card">
	                           <img src="">
	                        </div>
	                     	 	</td>
	                  </tr>
	                  <tr>
	                     <th></th>
	                     <td></td>
	                  </tr>
	                  <tr>
	                     <th rowspan="2">주소</th>
	                      	<td><select name="region1" style="width: 45%; padding: 0 20px;">
	                            <option value="">시</option>
	                            <option value="서울시" <c:if test="${member.M_REGION1 eq '서울시'}">selected</c:if>>서울시</option>
	                            <option value="경기도" <c:if test="${member.M_REGION1 eq '경기도'}">selected</c:if>>경기도</option>
	                            <option value="전라도" <c:if test="${member.M_REGION1 eq '전라도'}">selected</c:if>>전라도</option>
	                            <option value="충청도" <c:if test="${member.M_REGION1 eq '충청도'}">selected</c:if>>충청도</option>
	                            <option value="경상도" <c:if test="${member.M_REGION1 eq '경상도'}">selected</c:if>>경상도</option>
	                            <option value="경상도" <c:if test="${member.M_REGION1 eq '강원도'}">selected</c:if>>강원도</option>
	                      	</select>
	                      	<select name="region2" style="width: 45%; padding: 0 20px;">                            
	                            <option value="종로구" <c:if test="${member.M_REGION2 eq '종로구'}">selected</c:if>>종로구</option>
	                            <option value="용산구" <c:if test="${member.M_REGION2 eq '용산구'}">selected</c:if>>용산구</option>
	                            <option value="성동구" <c:if test="${member.M_REGION2 eq '성동구'}">selected</c:if>>성동구</option>
	                            <option value="광진구" <c:if test="${member.M_REGION2 eq '광진구'}">selected</c:if>>광진구</option>
	                            <option value="중랑구" <c:if test="${member.M_REGION2 eq '중랑구'}">selected</c:if>>중랑구</option>
	                            <option value="성북구" <c:if test="${member.M_REGION2 eq '성북구'}">selected</c:if>>성북구</option>
	                      	</select></td>
	                  </tr>
	                  <tr>
	                  	<td><input style="width: 85%; text-align: left;" type="text" id="" name="region3" placeholder="나머지 주소" value="${member.M_REGION3}" /></td>
	                  </tr>
	          	</table>
	        </div>
	  	</c:forEach>
	  </form>
	</div>
</div>
</body>
</html>