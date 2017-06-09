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
	//아이디 중복 검사
	$("#checkId").click(function() {
		if ($("#id").val()) {
			var query = {
				id : $("#id").val()
			};
			$.ajax({
				type : "post",
				url : "${myContextPath}/ConfirmIdServlet",
				data : query,
				success : function(data) {
					if (data == 1) {
						alert("이미 존재하는 아이디 입니다.");
						$("#id").val("");
					} else if (data == -1) {
						alert("사용할 수 있는 아이디 입니다.");
					} 
				}
			});
		} else {
			alert("사용할 아이디를 입력");
			$("#id").focus();
		}
	});

	//회원가입 등록 버튼 클릭
	$("#process").click(function() {
		if (isValidId()) {
			var query = {
				id : $("#id").val(),
				name : $("#name").val(),
				pwd : $("#pwd").val(),
				birth_ymd : $("#birth_ymd").val(),
				sexual_tp : $("input:radio[name='sexual_tp']:checked").val(),
				addr : $("#addr").val(),
				cell_phone : $("#cell_phone").val(),
			};
			$.ajax({
				type : "post",
				url : "registerPro.jsp",
				data : query,
				success : function(data) {
					alert("등록되었습니다.");
					$(location).attr('href', "${myContextPath}/page_manage.jsp");
				}
			});
		}
	});
	
	$("#backpage").click(function() {
		$(location).attr('href', "${myContextPath}/page_manage.jsp");
	});
	
});

function isValidId() {
	if (!$("#id").val()) {//아이디를 입력하지 않으면 수행
		alert("아이디를 입력하세요");
		$("#id").focus();
		return false;//사용자가 서비스를 요청한 시점으로 돌아감
	}

	if (!$("#name").val()) {//이름을 입력하지 않으면 수행
		alert("사용자 이름을 입력하세요");
		$("#name").focus();
		return false;
	}

	if (!$("#pwd").val()) {//비밀번호를 입력하지 않으면 수행
		alert("비밀번호를 입력하세요");
		$("#pwd").focus();
		return false;
	}

	//비밀번호와 재입력비밀번호가 같지않으면 수행
	if ($("#pwd").val() != $("#repwd").val()) {
		alert("비밀번호를 동일하게 입력하세요");
		$("#repwd").focus();
		return false;
	}

	if (!$("#birth_ymd").val()) {//생일을 입력하지 않으면 수행
		alert("생일을 입력하세요");
		$("#birth_ymd").focus();
		return false;
	}

	if (!$('input:radio[name=sexual_tp]').is(':checked')) { //성별을 체크하지 않으면 수행
		alert("성별을 체크하세요");
		$("#sexual_tp_m").focus();
		return false;
	}

	if (!$("#addr").val()) {//생일을 입력하지 않으면 수행
		alert("주소를 입력하세요");
		$("#addr").focus();
		return false;
	}

	if (!$("#cell_phone").val()) {//전화번호를 입력하지 않으면 수행
		alert("전화번호를 입력하세요");
		$("#cell_phone").focus();
		return false;
	}
	return true;
}
</script>
<title>회원가입</title>
</head>
<body>
	<div class="main">
		<h1>사용자 등록</h1>
		<input class="leftTopArea" id="id" name="id" type="text" placeholder="아이디" />
		<button class="rightTopArea" id="checkId">중복확인</button>
		<div class="po">
			<input class="Obj" id="name" name="name" type="text" placeholder="이름" />
			<input class="Obj" id="pwd" name="pwd" type="password" placeholder="비밀번호" />
			<input class="Obj" id="repwd" name="repwd" type="password" placeholder="비밀번호 확인" />
			<input class="Obj" id="addr" name="addr" type="text" placeholder="주소">
			<input class="Obj" id="cell_phone" name="cell_phone" type="tel" placeholder="전화번호">
			<div class="radioArea1">생일
				<input id="birth_ymd" name="birth_ymd" type="date" />
			</div>
			<div class="radioArea2">성별 &nbsp; &nbsp;
				<input type="radio" id="sexual_tp_m" name="sexual_tp" value="M" checked> 남자 &nbsp;
				<input type="radio" id="sexual_tp_f" name="sexual_tp" value="F"> 여자
			</div>
		</div>
		<button id="backpage" class="leftBottomArea">뒤로</button>
		<button id="process" class="rightBottomArea">회원가입</button>
	</div>

</body>
</html>
