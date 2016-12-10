var ScheduleJSONObj = {201607:{20160701:["살기","자기"]}};

function kCalendar(id, date) {
	var kCalendar = document.getElementById(id);
	
	if( typeof( date ) !== 'undefined' ) {
		date = date.split('-');
		date[1] = date[1] - 1;
		date = new Date(date[0], date[1], date[2]);
	} else {
		var date = new Date();
	}
	var currentYear = date.getFullYear();
	//년도를 구함
	
	var currentMonth = date.getMonth() + 1;
	//연을 구함. 월은 0부터 시작하므로 +1, 12월은 11을 출력
	
	var currentDate = date.getDate();
	//오늘 일자.
	
	date.setDate(1);
	var currentDay = date.getDay();
	//이번달 1일의 요일은 출력. 0은 일요일 6은 토요일
	
	var dateString = new Array('sun', 'mon', 'tue', 'wed', 'thu', 'fri', 'sat');
	var lastDate = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
	if( (currentYear % 4 === 0 && currentYear % 100 !== 0) || currentYear % 400 === 0 )
		lastDate[1] = 29;
	//각 달의 마지막 일을 계산, 윤년의 경우 년도가 4의 배수이고 100의 배수가 아닐 때 혹은 400의 배수일 때 2월달이 29일 임.
	
	var currentLastDate = lastDate[currentMonth-1];
	var week = Math.ceil( ( currentDay + currentLastDate ) / 7 );
	//총 몇 주인지 구함.
	
	if(currentMonth != 1)
		var prevDate = currentYear + '-' + ( currentMonth - 1 ) + '-' + currentDate;
	else
		var prevDate = ( currentYear - 1 ) + '-' + 12 + '-' + currentDate;
	//만약 이번달이 1월이라면 1년 전 12월로 출력.
	
	if(currentMonth != 12) 
		var nextDate = currentYear + '-' + ( currentMonth + 1 ) + '-' + currentDate;
	else
		var nextDate = ( currentYear + 1 ) + '-' + 1 + '-' + currentDate;
	//만약 이번달이 12월이라면 1년 후 1월로 출력.

	
	if( currentMonth < 10 )
		var currentMonth = '0' + currentMonth;
	//10월 이하라면 앞에 0을 붙여준다.
	
	var calendar = '';
	
	calendar += '<div id="header">';
	calendar += '			<span><a href="#" class="button left" onclick="kCalendar(\'' +  id + '\', \'' + prevDate + '\')"><</a></span>';
	calendar += '			<span id="date">' + currentYear + '년 ' + currentMonth + '월</span>';
	calendar += '			<span><a href="#" class="button right" onclick="kCalendar(\'' + id + '\', \'' + nextDate + '\')">></a></span>';
	calendar += '		</div>';
	calendar += '		<table border="1" cellspacing="0" cellpadding="0">';
	calendar += '			<caption>' + currentYear + '년 ' + currentMonth + '월 달력</caption>';
	calendar += '			<thead>';
	calendar += '				<tr>';
	calendar += '				  <th class="sun" scope="row">일</th>';
	calendar += '				  <th class="mon" scope="row">월</th>';
	calendar += '				  <th class="tue" scope="row">화</th>';
	calendar += '				  <th class="wed" scope="row">수</th>';
	calendar += '				  <th class="thu" scope="row">목</th>';
	calendar += '				  <th class="fri" scope="row">금</th>';
	calendar += '				  <th class="sat" scope="row">토</th>';
	calendar += '				</tr>';
	calendar += '			</thead>';
	calendar += '			<tbody>';
	
	var dateNum = 1 - currentDay;
	
	for(var i = 0; i < week; i++) {
		calendar += '			<tr>';
		for(var j = 0; j < 7; j++, dateNum++) {
			if( dateNum < 1 || dateNum > currentLastDate ) {
				calendar += '				<td class="' + dateString[j] + '"> </td>';
				//빈 공간 월 끼워 맞추기용 공백
				continue;
			}if(dateNum > 0 && dateNum < 10){
				dateNum = '0' + dateNum;}
			calendar += '<td class="' + dateString[j] + '">' + dateNum +
						'<div style="max-width: 100%;max-height: 50%; overflow-x: hidden;">'+
							'<table id="'+currentYear+''+currentMonth+''+dateNum+'" style="min-height: 100;max-height: 10px;"></table>'+
						'</div>'
						+'<button id="'+dateNum+'AddButton"; onclick="togglebutton(this)">+</button>'
						+'<input id="'+dateNum+'TextBox"; style="display:none; max-width:100%"/>'
						+'<button id="'+dateNum+'AppliedButton"; onclick="addScheduel(this, ' + currentYear + ', ' + currentMonth + ')" style="display:none">적용</button>'+'</td>';
		}
		calendar += '</tr>';
	}
	
	calendar += '</tbody>';
	calendar += '</table>';
	
	kCalendar.innerHTML = calendar;
	//다시 처음부터 1일 부터 Schedule을 Table에 추가시키기 위한 ID 값 조합 Key
	var mixIdKey = 0;
	//처음부터 해당 월의 최종 일 수 까지 반복
	for(var i=0; i < currentLastDate; i++){
		//해당 일 수 증가
		mixIdKey++;
		//Key 값의 자리수 맞추기용
		if(mixIdKey > 0 && mixIdKey < 10){
			mixIdKey = '0' + mixIdKey;
		}
		//FinalKey 값 생성
		var FinalKey = currentYear+''+currentMonth+''+mixIdKey;	
		//JSON에 있는 값을 Claender에 뿌리기 메소드
		setSchedule(FinalKey, date);		
		
	}
}
/**setSchedule
*@brief : JSON에 있는 값들을 claendar 생성과 동시에 스캐줄 생성 매서드
*@finalKey : JSON 데이터를 가져올 마지막 키값
*@pDate : 달력의 날짜 파라미터 값
*return : void
*/
function setSchedule(finalKey, pDate){
	//키 값에 맞는 자리 수 변형
	if( pDate.getMonth() + 1 < 10 ){
		var currentMonth = '0' + (pDate.getMonth() + 1);
	}
	//JSON FirstKey 값 지정
	var firstKey = pDate.getFullYear() +""+ currentMonth;
	//JSON FirrstKey 값 확인
	if(firstKey in ScheduleJSONObj){
		//JSON FinalKey 값 확인
		if(finalKey in ScheduleJSONObj[firstKey]){
			//JSON의 FinalKey에 있는 Array의 length 만큼 반복문
			for(var i = 0; i < ScheduleJSONObj[firstKey][finalKey].length; i++)
			{
				//Schedule을 추가할 테이블 지정
				addTable = document.getElementById(finalKey);
				//추가될 테이블의 마지막 TR 지정
				row = addTable.insertRow(addTable.rows.length);
				//Schedule이 들어갈 TD 생성
				cell = row.insertCell(0);
				//만들어진 TD에 Schedule 추가
				cell.innerHTML = ScheduleJSONObj[firstKey][finalKey][i]+'<button id="'+finalKey+'" onclick="delSchedule(this, '+firstKey+')";>X</button>';
			}
			
		}
	}
}
/**delSchedule
*@brief : 달력에 있는 Schedule을 제거 + JSON에 있는 데이터 제거 메소드
*@pButton : 버튼의 파라미터 값
*@firstKey : JSON에 첫번째 키 값
*return : void
*/
function delSchedule(pButton, firstKey){
	//삭제될 TR 지정
	var row = pButton.parentNode.parentNode;
	//JSON의 마지막 키 값 설정
	var finalKey = pButton.id;	
	//JSON FirstKey 값 확인
	if(firstKey in ScheduleJSONObj){
		//jSON FinalKey 값 확인
		if(finalKey in ScheduleJSONObj[firstKey]){
			//JSON의 키 키값에 있는 Array 값 삭제
			ScheduleJSONObj[firstKey][finalKey].splice(pButton.parentNode.parentNode.sectionRowIndex, 1);
		}
	}
	row.parentNode.removeChild(row);	
}
/**togglebutton
*@brief : 달력의 + 버튼 클릭시 텍스트 박스 적용 버튼 출력 메소드
*@pButton : 버튼의 파라미터 값
*@thisDay : ID 조합하기 위한 값 추출
*@return : void
*/
function togglebutton(pButton){
	var thisDay = pButton.id.substring(0,2);
	addToggle = document.getElementById(thisDay+'AddButton');
	textToggle = document.getElementById(thisDay+'TextBox');
	appliedToggel = document.getElementById(thisDay+'AppliedButton');
	
	//버튼 사라짐 
	addToggle.style.display = 'none';
	textToggle.style.display = 'inline';
	appliedToggel.style.display = 'inline';
}
/**addScheduel
*@brief : 적용 버튼을 클릭 하였을 시 텍스트 박스에 있는 값이 JSON에 추가됨과 동시에 Claender에 추가
*@pButton : 버튼의 파라미터 값
*@pYearKey : firstKey 값을 조합하기 위한 파라미터
*@pMonthKey : firstKey 값을 조합하기 위한 파라미터
*/
function addScheduel(pButton, pYearKey, pMonthKey){
	//thisDay : toggel할 ID 조합용 값
	var thisDay = pButton.id.substring(0,2);
	//toggle하기 위한 ID 조합 및 Document 지정
	addToggle = document.getElementById(thisDay+'AddButton');
	textBox = document.getElementById(thisDay+'TextBox');
	appliedToggel = document.getElementById(thisDay+'AppliedButton');
	
	//farstKey 값의 자릿수를 맞추기용
	var firstKey = pYearKey + ((pMonthKey) > 10 ? "" : "0") + (pMonthKey);
	//finalKey 값 조합
	var finalKey = firstKey + thisDay;
	//텍스트 박스가 비어있으면 추가하지 않음
	if(textBox.value != ""){
		//JSON에 FirstKey가 있는지 확인
		if(firstKey in ScheduleJSONObj){
			//FirstKey 이후 FinalKey가 있는지 확인
			if(finalKey in ScheduleJSONObj[firstKey]){
				//FinalKey 확인까지 다한 후 JSON에 TextBox Value 값 Push로 추가
				ScheduleJSONObj[firstKey][finalKey].push(textBox.value);
			}else {
				//FinalKey가 존재 하지 않으면 FinalKey로 생성 후 TextBox Value 값 Array로 추가
				ScheduleJSONObj[firstKey][finalKey] = [textBox.value];
			}			
		}else{
			//FirstKey가 없으면 FirstKey 추가
			ScheduleJSONObj[firstKey] = [finalKey];
			//FirstKey 추가 후 FinalKey 추가 후 TextBox Value 값 Array로 추가
			ScheduleJSONObj[firstKey][finalKey] = [textBox.value];
		}
		
		//추가한 JSON 값을 화면에 출력
		//tableCount : 현재 Schedule이 들어갈 테이블 배열 위치 값
		var tableCount = ScheduleJSONObj[firstKey][finalKey].length - 1;
		//추가될 JSON이 들어갈 테이블 지정
		addTable = document.getElementById(finalKey);
		//추가될 Schedule TR 값 지정
		row = addTable.insertRow(addTable.rows.length);
		//Table에 셀 생성
		cell = row.insertCell(0);
		//추가된 셀에 값 추가
		cell.innerHTML = ScheduleJSONObj[firstKey][finalKey][tableCount]+'<button id="'+finalKey+'" onclick="delSchedule(this, '+finalKey+')";>X</button>';
		//TextBox 공백으로 초기화
		textBox.value = "";
	}

	//버튼 클릭시 사라짐
	addToggle.style.display = 'inline';
	textBox.style.display = 'none';
	appliedToggel.style.display = 'none';
}