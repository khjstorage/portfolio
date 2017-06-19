<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017-06-19
  Time: 오후 5:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="../../css/style.css" rel="Stylesheet" type="text/css">

  <script type="text/javascript">
    function find(id) {
        return document.getElementById(id);
    }

    window.onload = function () {
        find('btn_basic').addEventListener('click', basic);
        find('popup').style.display = 'none';
    }

    function basic(){
        document.frm.rname.value = 'daisy';
        frm.py.value = 30;
        frm.cnt.value=3;
    }

    function send() {
        if(frm.rname.value.trim().length==0){
            find('msg_rname').innerHTML ="객실명을 입력해주세요.";
            find("panel").innerHTML="객실명을 입력해주세요";
            find("panel").style.display='';
            frm.rname.focus();
            return false;
        }else{
            find('msg_rname').style.display = 'none';
        }

        if(frm.py.value.trim().length==0){
            find('msg_py').innerHTML ="평수를 입력해주세요.";
            find("panel").innerHTML="평수를 입력해주세요";
            find("panel").style.display='';
            frm.py.focus();
            return false;
        }else{
            find('msg_py').style.display = 'none';
        }

        if(frm.cnt.value.trim().length==0){
            find('msg_cnt').innerHTML ="인원을 입력해주세요.";
            find("panel").innerHTML="인원을 입력해주세요";
            find("panel").style.display='';
            frm.cnt.focus();
            return false;
        }else{
            find('msg_cnt').style.display = 'none';
        }

        return true;
    }

    function msgShow() {
        find('panel').style.display='';
    }

    function msgHide() {
        find('panel').style.display='none';
    }

    function popupShow() {
        find('popup').style.display='';
    }

    function popupHide() {
        find('popup').style.display='none';
    }


  </script>
</head>
<body>
<DIV class='title'>펜션 예약</DIV>
<DIV class='content'>
  <form name='frm' action='./input_proc.jsp' method='post'  onsubmit="return send();">
    <ul>
      <li>
        객실명: <input type='text' name='rname' value=''>
          <span id="msg_rname" class="warning"></span>
      </li>
      <li>
        크기: <input type='text' name='py' value=''> 평
        <span id="msg_py" class="warning"></span>
      </li>
      </li>
      <li>
        인원: <input type='text' name='cnt' value=''> 명
        <span id="msg_cnt" class="warning"></span>
      </li>
      </li>
      <li>
        <button type='button' id='btn_basic'>기본 예약 선택</button>
        <button type='button' id='btn_increaseCnt'>인원 증가</button>
        <button type='button' id="btn_decreaseCnt">인원 감소</button>
        <button type='reset'>입력값 삭제</button>
        <button tpye="submit">예약</button>
      </li>
    </ul>
  </form>
  <p>
    <span class="box1"><a href="javascript: msgShow()">알림 출력</a> </span>
    <span class="box1"><a href="javascript: msgHide()">알림 닫기</a> </span>
  </p>
  <div id="panel" class="warning" style="display: none"></div>
  <span class="box1"><a href="javascript: popupShow()">레이어 열기</a> </span>

  <div id="popup" class="popup1">
    공지사항<br>
    오늘은 눈이 많이 옵니다.<br>
    대중 교통을<br>
    이용하시길 바랍니다.<br>
    <span class="box1"><a href="javascript: popupHide()">CLOSE</a></span>
  </div>
</DIV>
</body>
</html>
