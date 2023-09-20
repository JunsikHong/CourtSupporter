/* 팝업창 띄우기 */
function popUp() {
	var announce = encodeURIComponent(document.getElementById('announce_proper_num').value);
	var detail = encodeURIComponent(document.getElementById('aplcn_dtls_proper_num').value);
	
	var popupWidth = 700;
	var popupHeight = 500;
	// window.screen.width  : 윈도우의 가로 크기
	// window.screen.height : 윈도우의 세로 크기
	var popupX = Math.round((window.screen.width / 2) - (popupWidth / 2));
	var popupY = Math.round((window.screen.height / 2) - (popupHeight / 2));

	// 윈도우 팝업창의 스타일 지정
	featureWindow = "width=" + popupWidth + ", height=" + popupHeight
		+ ", left=" + popupX + ", top=" + popupY;
	// open("경로", "이름", "옵션")
	
	
	childPopup = window.open("applicationEducationPopup?announce=" + announce + "&detail=" + detail, "학력사항 입력", featureWindow);
}

function modifyPopUp(data) {
	var announce = encodeURIComponent(document.getElementById('announce_proper_num').value);
	var detail = encodeURIComponent(document.getElementById('aplcn_dtls_proper_num').value);
	
	var popupWidth = 700;
	var popupHeight = 500;
	// window.screen.width  : 윈도우의 가로 크기
	// window.screen.height : 윈도우의 세로 크기
	var popupX = Math.round((window.screen.width / 2) - (popupWidth / 2));
	var popupY = Math.round((window.screen.height / 2) - (popupHeight / 2));

	// 윈도우 팝업창의 스타일 지정
	featureWindow = "width=" + popupWidth + ", height=" + popupHeight
		+ ", left=" + popupX + ", top=" + popupY;
		
	
	// open("경로", "이름", "옵션")
	childPopup = window.open("applicationEducationModifyPopup?data=" + encodeURIComponent(data) + "&announce=" + announce + "&detail=" + detail, "학력사항 수정", featureWindow);
	
}

/* 학력 정보 리스트 */
function educationInfo(aplcn_dtls_proper_num) {
	
	setTimeout(function() {
		
	$.ajax({
		url: "../application/educationInfo",
		method: "POST",
		contentType: "application/json", //보낼 데이터 형식
		data : JSON.stringify( {aplcn_dtls_proper_num: aplcn_dtls_proper_num} ),
		success : function(data) {
			
		$("#educationList").children(".educationListRow").remove();
		
		var str = "";
		for(var i = 0; i < data.length; i++) {
			
			str += '<tr class="educationListRow">';
			str += '<input type="hidden" name="edctn_dtls_proper_num" value="' + data[i].edctn_dtls_proper_num + '">';
			str += '<td style="vertical-align: middle;">' + data[i].edctn_school_name + '</td>';				
			str += '<td style="vertical-align: middle;">' + data[i].edctn_major + '</td>';
			str += '<td style="vertical-align: middle;">' + data[i].edctn_degree + '</td>';
			str += '<td style="vertical-align: middle;">' + data[i].edctn_admsn_date.substring(0, 10) + '</td>';
			str += '<td style="vertical-align: middle;">' + data[i].edctn_grdtn_date.substring(0, 10) + '</td>';
			str += '<td style="vertical-align: middle;">';
			str += '<input type="button" class="modifyBtn" value="수정" style="border: 1px solid #888888;" />';
			str += '<input type="button" class="deleteBtn" value="삭제" style="border: 1px solid #888888;" />';
			str += '</td>';
			str += '<td style="text-align:center; vertical-align: middle;" class="toptd">';
			str += '<input type="radio" name="edctn_final_yn" value=""' + (data[i].edctn_final_yn === 'Y' ? ' checked' : '') + '>';
			str += '</td>';
			str += '</tr>';
		}
		
		$("#educationList").append(str);
	
		},
		error : function(status, error) {
			console.log(status);
			console.log(error);
		}
		
	})
	childPopup.close();
	}, 40);
		
}

/* 학력 사항 수정 */
$(document).ready(function() {
	$(document).on("click", ".modifyBtn", function(event) {
		var edctn_dtls_proper_num = event.target.parentNode.parentNode.firstElementChild.value;
		
		modifyPopUp(edctn_dtls_proper_num);
	})
})


/* 학력 사항 삭제 */
$(document).ready(function() {
	
	$(document).on("click", ".deleteBtn", function(event) {
	var edctn_dtls_proper_num = event.target.parentNode.parentNode.firstElementChild.value;
	var aplcn_dtls_proper_num = document.getElementById('aplcn_dtls_proper_num').value;
	
		$.ajax({
			url: "../application/educationInfoDelete",
			method: "POST",
			contentType: "application/json",
			data: JSON.stringify({edctn_dtls_proper_num: edctn_dtls_proper_num, aplcn_dtls_proper_num: aplcn_dtls_proper_num }),
			success: function(data) {
				
				$("#educationList").children(".educationListRow").remove();

				var str = "";
				for (var i = 0; i < data.length; i++) {

					str += '<tr class="educationListRow">';
					str += '<input type="hidden" name="edctn_dtls_proper_num" value="' + data[i].edctn_dtls_proper_num + '">';
					str += '<td style="vertical-align: middle;">' + data[i].edctn_school_name + '</td>';
					str += '<td style="vertical-align: middle;">' + data[i].edctn_major + '</td>';
					str += '<td style="vertical-align: middle;">' + data[i].edctn_degree + '</td>';
					str += '<td style="vertical-align: middle;">' + data[i].edctn_admsn_date.substring(0, 10) + '</td>';
					str += '<td style="vertical-align: middle;">' + data[i].edctn_grdtn_date.substring(0, 10) + '</td>';
					str += '<td style="vertical-align: middle;">';
					str += '<input type="button" class="modifyBtn" value="수정" style="border: 1px solid #888888;" />';
					str += '<input type="button" class="deleteBtn" value="삭제" style="border: 1px solid #888888;" />';
					str += '</td>';
					str += '<td style="text-align:center; vertical-align: middle;" class="toptd">';
					str += '<input type="radio" name="edctn_final_yn" value=""' + (data[i].edctn_final_yn === 'Y' ? ' checked' : '') + '>';
					str += '</td>';
					str += '</tr>';
				}

				$("#educationList").append(str);
			},
			error: function(error) {
				console.error(error);
			}
		});
	});
})

/* 최종 학력 입력 & 파일 수정 & 필수 입력 항목 뮤효성 검사 */
window.onload = function() {
	$("#educationForm").on('submit', function() {
		
		$('.popup_btn').on('click', function() {
	        $("#msg1").text(""); 
	    });
	    
	    $('input[name="edctn_final_yn"]').on('change', function() {
			$("#msg2").text(""); 
		});
	    
		var educationList = $("#educationList");
		if (educationList.children().length === 0) {
			$("#msg1").text("필수 입력 사항입니다");
			return false;
		}
	    
		var finalEducation = $('input[name="edctn_final_yn"]:checked').closest('tr').find('input[type="hidden"]').val();
		$('#final_education_chk').val(finalEducation);
		
		if (!$('input[name="edctn_final_yn"]').is(':checked')) {
			$("#msg2").text("최종 학력을 선택해 주세요");
			return false;
		}
		
		var uuids = [];
		const elements = document.getElementsByClassName("uuid");
		console.log(elements)
		for(var i = 0; i < elements.length; i++) {
			uuids.push(elements[i].value);
		}
		
		var detail = document.getElementById('aplcn_dtls_proper_num').value;
		$.ajax({
			url: "../application/educationFileModify",
			method: "POST",
			contentType: "application/json",
			data: JSON.stringify({uuids: uuids, detail: detail}),
			success: function(data) {
				console.log(data)
				
			},
			error: function(error) {
				console.error(error);
			}
		});
		
	})
}

/* 파일 첨부 추가 */
$(document).ready(function() {
	var idx = 1;
	$(document).on("click", ".filePlusBtn", function() {
		var str = "";
		str += '<div class="file-container">';
		str += '<label for="file-input-' + idx + '" class="file-label">';
		str += '<input type="hidden" class="fileAdd" style="border: none;" value="' + idx + '">';
		str += '<input type="file" id="file-input-' + idx + '" name="file" style="display: none;">';
		str += '<span class="fileAddBtn">파일선택</span>';
		str += '</label>';
		str += '<input class="upload-name' + idx + '" value="" disabled="disabled" style="border: none;">';
		str += '<input type="button" class="fileDeleteBtn" value="삭제" style="color: #fff; width: 80px;display: none">';
		str += '</div>';
		
		$("#fileAddList").append(str);
		idx++;
	});
	
	
	
})
/* 파일 첨부 추가 삭제 */
$(document).ready(function() {
	$(document).on("click", ".fileDeleteBtn, .fileDeleteBtn2", function() {
		$(this).parent().remove();
	});
	
})
/* 파일 업로드 */
$(document).ready(function() {

   $(document).on("change", 'input[type=file]', function() {
	   
  	var fileInput = $(this);
  	var fileName = fileInput.val().split('\\').pop();
  	fileInput.parent().next().val(fileName);
  	$(this).next().css('display', 'none');
  	$(this).parent().next().next().css('display', '');
  	
  });
  
});
