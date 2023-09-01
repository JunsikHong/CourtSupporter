$(document).ready(function() {


	var firstSelect_a = ["시가등(소송)", "경매", "공사비등", "측량", "문서/인영/필적", "번역/통역"];
	var firstSelect_b = ["통역", "번역/통역", "통번역"];
	var firstSelect_c = ["조정위원", "외부연계조정위원"];
	var firstSelect_d = ["전문심리위원", "상임전문심리위원"];
	var firstSelect_e = ["상담위원", "후견상담위원"];
	var firstSelect_a_id = ["0101", "0102", "0103", "0104", "0105", "0106"];
	var firstSelect_b_id = ["0201", "0202", "0203"];
	var firstSelect_c_id = ["0301", "0302"];
	var firstSelect_d_id = ["0401", "0402"];
	var firstSelect_e_id = ["0501", "0502"];
	var secondSelect = document.getElementById("secondSelect");

	$('#firstSelect').empty(); // 아래 option 삭제
	$('#firstSelect').append("<option value='' disabled selected style='display: none;'>중분류</option>"); // 최상의 선택제목
	secondSelect.style.display = 'none';

	var supporterId = "01";
	if (supporterId == "01") {
		for (i = 0; i < firstSelect_a.length; i++) {
			$('#firstSelect').append("<option value='" + firstSelect_a_id[i] + "'>" + firstSelect_a[i] + "</option>");
		}
	} else if (supporterId == "02") {
		for (i = 0; i < firstSelect_b.length; i++) {
			$('#firstSelect').append("<option value='" + firstSelect_b_id[i] + "'>" + firstSelect_b[i] + "</option>");
		}
	} else if (supporterId == "03") {
		for (i = 0; i < firstSelect_c.length; i++) {
			$('#firstSelect').append("<option value='" + firstSelect_c_id[i] + "'>" + firstSelect_c[i] + "</option>");
		}
	} else if (supporterId == "04") {
		for (i = 0; i < firstSelect_d.length; i++) {
			$('#firstSelect').append("<option value='" + firstSelect_d_id[i] + "'>" + firstSelect_d[i] + "</option>");
		}
	} else if (supporterId == "05") {
		for (i = 0; i < firstSelect_e.length; i++) {
			$('#firstSelect').append("<option value='" + firstSelect_e_id[i] + "'>" + firstSelect_e[i] + "</option>");
		}
	}

})

function selectChange() {

	var secondSelect_a = ["공사비등", "구조(안전진단)", "건축시공A", "건축시공B", "건축구조"];
	var secondSelect_b = ["일반조정위원", "상임조정위원"];
	var secondSelect_a_id = ["0103001", "0103002", "0103011", "0103012", "0103013"];
	var secondSelect_b_id = ["0301001", "0301002"];
	var secondSelect = document.getElementById("secondSelect");

	$('#secondSelect').empty(); // 아래 option 삭제
	$('#secondSelect').append("<option value='' disabled selected style='display: none;'>소분류</option>"); // 최상의 선택제목

	var str = $('#firstSelect').val();
	if (str == "0103") {
		secondSelect.style = 'none';
		for (i = 0; i < secondSelect_a.length; i++) {
			$('#secondSelect').append("<option value='" + secondSelect_a_id[i] + "'>" + secondSelect_a[i] + "</option>");
		}
	} else if (str == "0301") {
		secondSelect.style = 'none';
		for (i = 0; i < secondSelect_b.length; i++) {
			$('#secondSelect').append("<option value='" + secondSelect_b_id[i] + "'>" + secondSelect_b[i] + "</option>");
		}
	} else {
		secondSelect.style.display = 'none';
	}
}


$(document).ready(function() {
    document.getElementById("addressSearch").addEventListener("click", function(){ //주소입력칸을 클릭하면
        //카카오 지도 발생
        new daum.Postcode({
            oncomplete: function(data) { //선택시 입력값 세팅
            	console.log(data)
            	document.getElementById("user_ar_zonecode").value = data.zonecode; // 우편번호 넣기
                document.getElementById("user_ar").value = data.address; // 주소 넣기
                document.querySelector("input[name=user_ar_detail]").focus(); //상세입력 포커싱
                document.querySelector("input[name=user_ar_detail]").value = ''; //상세입력 초기화
                document.querySelector("input[name=user_ar_jibun]").value = data.jibunAddress; //지번주소 
            }
        }).open();
    });
})
function updateEmailDomain() {
        var emailSelect = document.getElementById('email_select');
        var emailDomainInput = document.getElementById('email-domain');
        
        var selectedOption = emailSelect.options[emailSelect.selectedIndex];
        if (selectedOption.value !== '') {
            emailDomainInput.value = selectedOption.value;
        } else {
            emailDomainInput.value = '';
        }
    }

window.onload = function() {
	$("#fetchDataButton").click(function() {
		var rrn1 = document.getElementById("user_rrn1").value;
		var rrn2 = document.getElementById("user_rrn2").value;
		var rrn = rrn1 + '-' + rrn2;

		$.ajax({
			url: "../application/fetchData",   // 데이터 조회를 위한 컨트롤러 경로
			method: "POST",
			contentType: "application/json",
			data: JSON.stringify({ user_rrn: rrn }),
			success: function(response) {
				
				const messageDisplay = $("#messageDisplay");
				if (JSON.parse(response).success) {
					messageDisplay.text("실명인증이 되었습니다.");
				} else {
					messageDisplay.text("일치하지 않습니다.");
				}
			},
			error: function(error) {
				console.error("데이터 조회 오류:", error);
			}
		});
	});
}

$(document).ready(function() {

	
	$('#basicForm').on('submit', function(e){
        e.preventDefault();

		/* 조력자 분류 유효성 검사 */
		$('#firstSelect').on('change', function() {
	        $("#msg1").text(""); // 선택 값이 변경되면 메시지 삭제
	        $("#msg2").text("");
	        $('#firstSelect').focus().css('border', '');
	    });
	    $('#secondSelect').on('change', function() {
	        $("#msg2").text(""); // 선택 값이 변경되면 메시지 삭제
	        $('#secondSelect').focus().css('border', '');
	    });
		
        let firstSelect = $('#firstSelect').val();
        if(!firstSelect){
            $("#msg1").text("필수 선택 항목입니다.");
            $('#firstSelect').focus().css('border', '1.8px solid red');
            return false;
        }
		
		let secondSelect = $('#secondSelect').val();
		if (firstSelect == '0103' || firstSelect == '0301') {
			if (!secondSelect) {
				$("#msg2").text("필수 선택 항목입니다.");
				$('#secondSelect').focus().css('border', '1.8px solid red');
				return false;
			}
		}

		/* 희망 법원 선택 유효성 검사 */
		$('.pBtn, .mBtn').on('click', function() {
	    var selectedOptions = $('option.court_proper_num');
	        if (selectedOptions.length === 0) {
			$('#demo').focus().css('border', '1px solid red');
			$("#multiSelectMsg").text("필수 선택 항목입니다.");
			return false;
		} else {
			$('#demo').focus().css('border', '');
			$("#multiSelectMsg").text("");
			return false;
		}
	    });

		var selectedOptions = $('option.court_proper_num');
		if (selectedOptions.length === 0) {
			$('#demo').focus().css('border', '1px solid red');
			$("#multiSelectMsg").text("필수 선택 항목입니다.");
			return false;
		} 
		
		/* 신청인 기본 정보 유효성 검사*/
		$('#fetchDataButton').on('click', function() {
			$('#fetchDataButton').focus().css({'border': '1px solid #888888', 'color' : ''});
		});
	    if ($('#messageDisplay').html() !== '실명인증이 되었습니다.') {
			$('#fetchDataButton').focus().css({'border': '1px solid red', 'color' : 'red'});
	        $('#messageDisplay').text("실명인증이 필요합니다.");
	        return false;
	    } 
	    
	    
		
		/* 추가 정보 유효성 검사 */
		$("[name='ligtn_case_carer_yn']").on('click', function() {
	        $('#radioMsg1').text("");
	    });
	    
	    $("[name='insrn_indst_carer_yn']").on('click', function() {
	        $('#radioMsg2').text("");
	    });
	    
	    $("[name='criminal_penalty_carer_yn']").on('click', function() {
	        $('#radioMsg3').text("");
	    });
		if($("input[name=ligtn_case_carer_yn]:radio:checked").length < 1) {
			$("[name='ligtn_case_carer_yn']").focus();
			$('#radioMsg1').text("필수 선택 항목입니다.");
			return false;
		} 
		
		if($("input[name=insrn_indst_carer_yn]:radio:checked").length < 1) {
			$("[name='insrn_indst_carer_yn']").focus();
			$('#radioMsg2').text("필수 선택 항목입니다.");
			return false;
		} 
		
		if($("input[name=criminal_penalty_carer_yn]:radio:checked").length < 1) {
			$("[name='criminal_penalty_carer_yn']").focus();
			$('#radioMsg3').text("필수 선택 항목입니다.");
			return false;
		} 
		
		document.basicForm.submit();
	});
	
})
