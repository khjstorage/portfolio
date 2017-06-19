<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-06-19
  Time: 오전 10:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="../../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/javascript">
    function basic() {
        document.frm.rname.value = 'daisy';
        frm.py.value = 30;
        frm.cnt.value = 3;
    }
    function increaseCnt() {
        console.log(isNaN(123));
        console.log(isNaN("123"));
        console.log(isNaN("ABC"));

        console.log(frm.cnt.value + 1);
        console.log(parseInt(frm.cnt.value) + 1);
        console.log(isNaN(frm.cnt.value));

        var cnt = frm.cnt.value;
        console.log(cnt.length);
        console.log(typeof frm.cnt.value);
        console.log(typeof parseInt(cnt));
        cnt = parseInt(cnt);

        if (cnt > 4){
            alert("최대 5명까지만 가능합니다.");
        }else{
            frm.cnt.value = cnt+1;
        }
    }
    function decreaseCnt() {
        var cnt = frm.cnt.value;
        if(cnt.length > 0){
            cnt = parseInt(cnt);
            if(cnt <= 1){
                alert("최소 1명 이상 가능합니다.");
            }else{
                frm.cnt.value = cnt - 1;
            }
        }else{
            alert("값이 없습니다.")
        }
    }
    function send1() {
        if(frm.rname.value.trim().length==0){
            alert("객실명을 입력해주세요");
            return;
        }
        if(frm.py.value.trim().length==0){
            alert("명수를 입력해주세요");
            return;
        }
        if(frm.cnt.value.trim().length==0){
            alert("인원수를 입력해주세요");
            return;
        }
      frm.submit();
    }
    function send2() {
        var rname = frm.rname.value;
        var py = frm.py.value;
        var cnt = frm.cnt.value;

        location.href = './input_proc.jsp?rname='+rname+'&py='+py+'&cnt='+cnt;
    }
    function send3() {
        if(frm.rname.value.trim().length==0){
            alert("객실명을 입력해주세요.");
            frm.rname.focus();
            return false;
        }

        if(frm.py.value.trim().length==0){
            alert("명수를 입력해주세요.");
            frm.py.focus();
            return false;
        }

        if(frm.cnt.value.trim().length==0) {
            alert("인원수를 입력해주세요.");
            frm.cnt.focus();
            return false;
        }
        return true;
    }
    function price(f) {
        var amount = parseInt(f.cnt.value * 30000);
        var _amount = new Number(amount).toLocaleString('en');
        alert("객실 예상 계산1: " + _amount)
    }
    function py_amount() {
        var amount = parseInt(frm.py.value * 10000);
        var _amount = new Number(amount).toLocaleString('en');
        alert("객실 비용 계산1: " + _amount)
    }
    function py_amount2(f) {
        var amount = parseInt(f.py.value * 30000);
        var _amount = new Number(amount).toLocaleString('en');
        alert("객실 비용 계산2: " + _amount)
    }
    function this_test(f) {
        alert('인원:' + f.cnt.value);
    }
</script>
</head>
<body>
<div class="title">팬션 예약</div>
<div class="content">
    <form name="frm", action="./input_proc.jsp" method="post" onsubmit="return send3();">
        <ul>
            <li> 객실명 : <input id= "rnameid" type="text", name="rname", value=""> </li>
            <li> 크기 : <input type="text", name="py" value=""> </li>
            <li> 인원 : <input type="text", name="cnt" value=""> </li>
            <li>
                <button type="button" onclick="basic()">기본 예약 선택</button>
                <button type="button" onclick="increaseCnt()">인원 증가</button>
                <button type="button" onclick="decreaseCnt()">인원 감소</button>
                <button type="reset">입력값 삭제</button>
                <button type="button" onclick="send1()">신청 1</button>
                <button type="button" onclick="send2()">신청 2</button>
                <button type="submit">신청 3</button>
                <button type="button" onclick="price(this.form)">금액 계산</button>
            </li>
            <li>
                [<a href="http://www.kma.go.kr" onclick="alert('내일 눈이 많이 옵니다.')">기상청</a>]
                [<a href="py_amount()">객실 비용 계산1(ERROR) </a>]
                [<a href="javascript: py_amount()">객실 비용 계산1</a>]
                [<a href="javascript: py_amount2(frm)">객실 비용 계산2</a>]
                [<a href="javascript: this_test(this.form)">A태그 this 테스트</a>]
            </li>
        </ul>
    </form>

    <br>추가옵션<br>

    <form name="frm2" action="./input_proc.jsp" method="post">
        <ul>
            <li>인원: <input type="text" name="cnt" value=""> </li>
            <li><button type="button" onclick="price(this.form)">금액계산</button> </li>
        </ul>
    </form>

</div>
</body>
</html>
