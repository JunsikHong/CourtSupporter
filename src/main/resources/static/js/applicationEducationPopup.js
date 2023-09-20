window.onload = function() {
	$('#educationPopupForm, #educationModifyPopupForm').on('submit', function(e) {
		e.preventDefault();
		
		/* 학교명 유효성 검사*/
		$('#edctn_school_name').on('input', function() {
	        $("#msg1").text(""); 
	        $('#edctn_school_name').css({'border': '', 'color' : ''});
	    });
	    
		let edctn_school_name = $('#edctn_school_name').val();
		if(!edctn_school_name){
			$("#msg1").text("필수 입력 항목입니다");
            $('#edctn_school_name').focus().css('border', '1.8px solid red');
            return false;
		}
		
	    /* 학과(전공) 유효성 검사 */
	    $('#edctn_major').on('input', function() {
	        $("#msg2").text(""); 
	        $('#edctn_major').css({'border': '', 'color' : ''});
	    });
	    
		let edctn_major = $('#edctn_major').val();
		if(!edctn_major){
			$("#msg2").text("필수 입력 항목입니다");
            $('#edctn_major').focus().css('border', '1.8px solid red');
            return false;
		}
		
		/* 학위 유효성 검사 */
		$('#edctn_degree').on('change', function() {
	        $("#msg3").text(""); // 선택 값이 변경되면 메시지 삭제
	        $('#edctn_degree').focus().css('border', '');
	    });
		
        let edctn_degree = $('#edctn_degree').val();
        if(!edctn_degree){
            $("#msg3").text("필수 선택 항목입니다");
            $('#edctn_degree').focus().css('border', '1.8px solid red');
            return false;
        }
        
        /* 입학일 유효성 검사 */
        $('#edctn_admsn_date').on('change', function() {
	        $("#msg4").text(""); 
	        $('#edctn_admsn_date').css({'border': '', 'color' : ''});
	    });
	    
		let edctn_admsn_date = $('#edctn_admsn_date').val();
		if(!edctn_admsn_date){
			$("#msg4").text("필수 선택 항목입니다");
            $('#edctn_admsn_date').css('border', '1.8px solid red');
            return false;
		}
		
		/* 졸업일 유효성 검사 */
        $('#edctn_grdtn_date').on('change', function() {
	        $("#msg5").text(""); 
	        $('#edctn_grdtn_date').css({'border': '', 'color' : ''});
	    });
	    
		let edctn_grdtn_date = $('#edctn_grdtn_date').val();
		if(!edctn_grdtn_date){
			$("#msg5").text("필수 선택 항목입니다");
            $('#edctn_grdtn_date').css('border', '1.8px solid red');
            return false;
		}
		if($(this).is('#educationPopupForm')){
		    document.educationPopupForm.submit();
		    var aplcn_dtls_proper_num = document.getElementById('aplcn_dtls_proper_num').value;
			window.opener.educationInfo(aplcn_dtls_proper_num);
		} else if($(this).is('#educationModifyPopupForm')) {
			document.educationModifyPopupForm.submit();
			var aplcn_dtls_proper_num = document.getElementById('aplcn_dtls_proper_num').value;
			window.opener.educationInfo(aplcn_dtls_proper_num);
		}
		
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