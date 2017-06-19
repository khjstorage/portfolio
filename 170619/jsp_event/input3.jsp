<%@ page contentType="text/html; charset=UTF-8" %> 

<!DOCTYPE html> 
<html lang="ko"> 
<head> 
<meta charset="UTF-8"> 
<title></title> 

<link href="../../css/style.css" rel="Stylesheet" type="text/css">
<script type="text/javascript">
  window.onload = function(){
    var btn_basic = document.getElementById('btn_basic');
    btn_basic.addEventListener('click', basic);
    btn_basic.addEventListener('click', price);

    document.getElementById('btn_increaseCnt').addEventListener('click', updateCnt);
    document.getElementById('btn_decreaseCnt').addEventListener('click', updateCnt);

  }

 function updateCnt(event) {
     var cnt = frm.cnt.value;

     if(cnt.length > 0){
         cnt = parseInt(cnt);
         if(event.target.id == 'btn_increaseCnt'){
             if (cnt > 4){
                 alert("최대 5명까지만 가능합니다.");
             }else{
                 frm.cnt.value = cnt + 1;
             }
         }else if (event.target.id == 'btn_decreaseCnt'){
             if (cnt <= 1 ){
                 alert("최소 1명 이상 가능합니다.");
             }else{
                 frm.cnt.value = cnt - 1;
             }
         }
     } else {
       alert("값이없습니다");
     }


 }



  function basic(){
    document.frm.rname.value = 'daisy';
    frm.py.value = 30;
    frm.cnt.value=3;
  }

  function price(){
    var amount = parseInt(this.form.cnt.value * 30000);
    var _amount = new Number(amount).toLocaleString('en');
    alert('결재 예상 금액: ' + _amount);
  }
  
  function py_amount(){
    var amount = parseInt(frm.py.value * 10000);
    var _amount = new Number(amount).toLocaleString('en');
    alert('객실 비용 계산 1: ' + _amount)
  }

  // 같은 이름의 함수 있으면 안됨
  function py_amount2(f){
    var amount = parseInt(f.py.value * 10000);
    var _amount = new Number(amount).toLocaleString('en');
    alert('객실 비용 계산 2: ' + _amount)
  }
  
</script>
</head> 
<!-- ------------------------------------- -->
<body leftmargin="0" topmargin="0">
<!-- ------------------------------------- -->
<DIV class='title'>펜션 예약</DIV>
<DIV class='content'>
  <form name='frm' action='./input_proc.jsp' method='post'
            onsubmit="return send3();">
    <ul>
      <li>
        객실명: <input type='text' name='rname' value=''>
      </li>
      <li>
        크기: <input type='text' name='py' value=''> 평
      </li>
      <li>
        인원: <input type='text' name='cnt' value=''> 명
      </li>
      <li>
        <button type='button' id='btn_basic'>기본 예약 선택</button>
        <button type='button' id='btn_increaseCnt'>인원 증가</button>
        <button type='button' id="btn_decreaseCnt">인원 감소</button>
        <button type='reset'>입력값 삭제</button>
      </li>
    </ul>
  </form>
</DIV>
<!-- ------------------------------------- -->
</body>
<!-- ------------------------------------- -->
</html> 



