<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" info="" isErrorPage="false"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>발견게시판 글쓰기</title>
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
			<h2 style="float:left;">발견게시판 글쓰기</h2>
			<form id="" action="<c:url value='/finds/create.do'/>" method="post" enctype="multipart/form-data">
				<input id="submitBtn" type="submit" class="rightTopButtons" value="글쓰기"/> 
				<a href="<c:url value='/finds/list.do'/>"><input type="button" class="rightTopButtons" value="뒤로" /></a>
				<div class="editorTool" style="float: left;">
					<table>
						<tr>
							<th>발견 사진</th>
								<td><input type="file" accept="image/*" name="finds_file" multiple="multiple" /></td>
							<th>닉네임</th>
								<td><input type="text" id="name" name="name" placeholder="닉네임" maxlength="10" required /></td>
						</tr>
						<tr>
							<th>색상</th>
								<td><select name="color" required>
										<option value="" selected>색상</option>
										<option value="검정">검정</option>
										<option value="흰색">흰색</option>
								</select></td>
							<th>연락처</th>
								<td><input type="text" id="phone" name="phone" placeholder="010-0000-0000" maxlength="14" required /></td>
						</tr>
						<tr>
							<th>견종</th>
								<td>
									<select name="dog" required>
										<option value="" selected>견종</option>
			                            <option value="코카스파니엘">코카스파니엘</option>
			                            <option value="요크셔테리어">요크셔테리어</option>
			                            <option value="허스키">허스키</option>
			                            <option value="웰시코기">웰시코기</option>
			                            <option value="말티즈">말티즈</option>
									</select>
								</td>
							<th>비밀번호</th>
								<td><input type="password" id="pwd" name="pwd" placeholder="비밀번호"  maxlength="18" required/></td>
						</tr>
						<tr>
							<th>성별</th>
								<td>
									<div class="checks">
										<input type="radio" id="gender_f" name="gender" value="암컷" checked />
										<label for="gender_f"> 암컷 </label>
									</div>
									<div class="checks">
										<input type="radio" id="gender_m" name="gender" value="수컷" />
										<label for="gender_m"> 수컷 </label>
									</div>
									<div class="checks">
										<input type="radio" id="gender_un" name="gender" value="모름" />
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
										<input type="radio" id="size_s" name="size" value="소형" checked />
										<label for="size_s"> 소 </label>
									</div>
									<div class="checks">
										<input type="radio" id="size_m" name="size" value="중형" />
										<label for="size_m"> 중 </label>
									</div>
									<div class="checks">
										<input type="radio" id="size_l" name="size" value="대형" />
										<label for="size_l"> 대 </label>
									</div>
								</td>
								<td colspan="2" rowspan="4" style="background-color: #cccccc; text-align: center; vertical-align: middle;">
									<div>
										<h1>MAP SECTION</h1>
									</div>
								</td>
						</tr>
						<tr>
							<th>발견 날짜</th>
								<td><input type="date" id="date" name="date" required/></td>
						</tr>
						<tr>
							<th>발견 지역</th>
								<td>
									<select name="region" required>
										<option value="" selected>발견지역</option>
										<option value="서울">서울</option>
										<option value="경기">경기</option>
										<option value="일본">일본</option>
									</select>
								</td>
						</tr>
						<tr>
							<th>제목</th>
							<td><input type="text" name="title" required style="width: 90%; text-align: left;" /></td>
						</tr>
						<tr>
							<th>상세 내용</th>
							<td colspan="3"><textarea id="detail" name="detail" required></textarea></td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
