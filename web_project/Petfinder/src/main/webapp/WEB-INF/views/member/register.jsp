<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>회원가입</title>
</head>
<script>
$(document).ready(function(){
	var flag = 0;
	var re_pwd = /^[a-z0-9_-]{6,18}$/; // 비밀번호 검사식
	var re_name = /^[가-힣a-zA-Z]+$/; // 이름 검사식
	var re_tel = /^\d{3}-\d{3,4}-\d{4}$/; // 전화번호 검사식
	
	$("#duplication").click(function() {
		if($("#id").val()){
			var query = {id:$("#id").val()};
			$.ajax({
				url : "/member/duplication.do",
				type : "post",
				data : query,
				success : function(data) {
					var re_id = /^[a-z0-9_-]{3,10}$/; // 아이디 검사식
					if(data==1){
						alert("이미 사용중인 아이디입니다.");
						$("#id").val("");
						$("#id").focus();
					}else if(!re_id.test($("#id").val())){
						alert("영어(소문자),숫자 3~10글자만 가능");
						$("#id").val("");
						$("#id").focus();
					}else if(data==-1){
						alert("사용할 수 있는 아이디");
						$("#id").attr("readonly",true);
						flag = 1;
					}
				}
			});
		}else{
			alert("사용할 아이디를 입력");
			$("#id").focus();
		}
	});
	
	$("#submitBtn").click(function(){
		if(flag==0){
			alert("아이디 중복검사를 해주세요")
			return false;
		}
		
		var pwd = $("#pwd"), pwd2 = $("#pwd2");
		if(pwd.val()!=pwd2.val()){
			alert("비밀번호가 일치하지 않습니다.");
			$("#pwd2").focus();
			return false;
		}
		
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
			alert("휴대폰 번호를 형식에 맞게 입력해주세요");
			$("#phone").focus();
			return false;
		}
	});
	
	
});
</script>
<body>
   <div class="containor">
      <div class="mainContents">
         <h2>회원가입</h2>
         <div class="signup_form">
            <form id="signupForm" action="<c:url value='/member/signup.do'/>" method="post" enctype="multipart/form-data">
               <div>
	               <input type="text" id="id" name="id" placeholder="아이디" required /> 
	               <input type="button" id="duplication" value="아이디 중복검사" />
	               <br /> 
	               <input type="password" id="pwd" name="pwd" placeholder="비밀번호" required/>
	               <input type="password" id="pwd2" name="pwd2" placeholder="비밀번호 확인" required/>
	               <br />
	               <input type="text" id="name" name="name" placeholder="이름" required/>
	               <input type="text" id="phone" name="phone" size="11" maxlength="13" placeholder="010-0000-0000" required/>
	               <br /> 
	               <select name="region1" required>
	                  <option value="" selected>시</option>
	                  <option value="서울시">서울시</option>
	                  <option value="경기도">경기도</option>
	                  <option value="전라도">전라도</option>
	                  <option value="충청도">충청도</option>
	                  <option value="경상도">경상도</option>
	                  <option value="경상도">강원도</option>
	               </select> <select name="region2" required>
	                  <option value="" selected>구</option>
	                  <option value="종로구">종로구</option>
	                  <option value="용산구">용산구</option>
	                  <option value="성동구">성동구</option>
	                  <option value="광진구">광진구</option>
	                  <option value="중랑구">중랑구</option>
	                  <option value="성북구">성북구</option>
	               </select>
	               <br /> 
	               <input style="width: 428px; margin-left: 5px;" type="text" id="region3" name="region3" placeholder="나머지 주소" required>
	               <br /> 
	               <select id="color" name="color" required>
	                  <option value="">색깔을 선택하세요.</option>
	                  <option value="갈색">갈색</option>
	                  <option value="검정색">검정색</option>
	                  <option value="검정색">얼룩(흰색+검정색)</option>
	                  <option value="얼룩(흰색+갈색)">얼룩(흰색+갈색)</option>
	                  <option value="얼룩(갈색+검정색)">얼룩(갈색+검정색)</option>
	               </select>
	               <select name="dog" required>
	                  <option value="" selected>견종을 선택하세요.</option>
	                  <option value="코카스파니엘">코카스파니엘</option>
	                  <option value="요크셔테리어">요크셔테리어</option>
	                  <option value="허스키">허스키</option>
	                  <option value="웰시코기">웰시코기</option>
	               </select>
	               <br />
	               <span> 애견 사진 </span>
	               <input type="file" accept="image/*" name="member_file" multiple="multiple"/>
	               <br />
	               <div class="checks">
	                  <input type="radio" id="gender_m" name="gender" value="수컷" checked />
	                  <label for="gender_m"> 수컷 </label>
	               </div>
	               <div class="checks">
	                  <input type="radio" id="gender_f" name="gender" value="암컷" /> 
	                  <label for="gender_f"> 암컷 </label>
	               </div>
	               <span> &nbsp; / &nbsp; </span>
	               <div class="checks">
	                  <input type="radio" id="size_s" name="size" value="소형" checked />
	                  <label for="size_s"> 소형 </label>
	               </div>
	               <div class="checks">
	                  <input type="radio" id="size_m" name="size" value="중형" />
	                  <label for="size_m"> 중형 </label>
	               </div>
	               <div class="checks">
	                  <input type="radio" id="size_l" name="size" value="대형" />
	                  <label for="size_l"> 대형 </label>
	               </div>
	            </div>
               <input id="submitBtn" type="submit" value="회원가입" >
               <a href="<c:url value='/main.do'/>"><input id="backBtn" type="button" value="취소"></a>
            </form>
         </div>
      </div>
   </div>
</body>
</html>