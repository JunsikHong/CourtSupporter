window.onload = function() {
	$('#certificatePopupForm').on('submit', function() {
		var aplcn_dtls_proper_num = document.getElementById('aplcn_dtls_proper_num').value;
		window.opener.certificateInfo(aplcn_dtls_proper_num);
	});
	
	$('#certificateModifyPopupForm').on('submit', function() {
		var aplcn_dtls_proper_num = document.getElementById('aplcn_dtls_proper_num').value;
		window.opener.certificateInfo(aplcn_dtls_proper_num);
	});
	
}

/* 달력 */
$(document).ready(function () {
	$(".datepicker").datepicker({
		closeText: "닫기",
	    currentText: "오늘",
	    prevText: '이전 달',
	    nextText: '다음 달',
	    monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	    monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		dateFormat: 'yy-mm-dd',
		dayNames: ['일', '월', '화', '수', '목', '금', '토'],
		dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
		dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
		weekHeader: "주",
    	yearSuffix: '년',
    	showOtherMonths: true, // 빈 공간에 현재월의 앞뒤월의 날짜를 표시
	    showMonthAfterYear: true, // 년도 먼저 나오고, 뒤에 월 표시
	    changeYear: true, // 콤보박스에서 년 선택 가능
	    changeMonth: true, // 콤보박스에서 월 선택 가능
	    yearSuffix: "년", // 달력의 년도 부분 뒤에 붙는 텍스트
	    minDate: "-30Y", // 최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
	    maxDate: "+1Y", // 최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후) });
    
	});
	$(".datepicker").prop('readonly', false); //required사용 
});