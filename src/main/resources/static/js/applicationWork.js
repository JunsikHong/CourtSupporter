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
	childPopup = window.open("applicationWorkPopup?announce=" + announce + "&detail=" + detail, "경력사항 입력", featureWindow);
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
	childPopup = window.open("applicationWorkModifyPopup?data=" + encodeURIComponent(data) + "&announce=" + announce + "&detail=" + detail, "경력사항 수정", featureWindow);
	
}

/* 경력 정보 리스트 */
function workInfo(aplcn_dtls_proper_num) {
	
	setTimeout(function() {
		
	$.ajax({
		url: "../application/workInfo",
		method: "POST",
		contentType: "application/json", //보낼 데이터 형식
		data : JSON.stringify( {aplcn_dtls_proper_num: aplcn_dtls_proper_num} ),
		success : function(data) {
			
		$("#workList").children(".workListRow").remove();
		
		var str = "";
		for(var i = 0; i < data.length; i++) {
			
			str += '<tr class="workListRow">';
			str += '<input type="hidden" id="aplcn_carer_proper_num" name="aplcn_carer_proper_num" value="' + data[i].aplcn_carer_proper_num + '">';
			str += '<td style="vertical-align: middle;">' + data[i].company_name + '</td>';				
			str += '<td style="vertical-align: middle;">' + data[i].work_start_date.substring(0, 10) + '~' + data[i].work_end_date.substring(0, 10) + '</td>';
			str += '<td style="vertical-align: middle;">' + data[i].work_description + '</td>';
			str += '<td style="vertical-align: middle;">' + data[i].work_department + '/' + data[i].work_position + '</td>';
			str += '<td style="vertical-align: middle;">';
			str += '<input type="button" class="modifyBtn" value="수정" style="border: 1px solid #888888;" />';
			str += '<input type="button" class="deleteBtn" value="삭제" style="border: 1px solid #888888;" />';
			str += '</td>';
			str += '</tr>';
		}
		
		$("#workList").append(str);
	console.log(data)
		},
		error : function(status, error) {
			console.log(status);
			console.log(error);
		}
		
	})
	
	childPopup.close();
	}, 40);
		
}

/* 경력 정보 수정 */
$(document).ready(function() {
	$(document).on("click", ".modifyBtn", function(event) {
		var aplcn_carer_proper_num = event.target.parentNode.parentNode.firstElementChild.value;
		modifyPopUp(aplcn_carer_proper_num);
	})
})


/* 경력 정보 삭제 */
$(document).ready(function() {
	
	$(document).on("click", ".deleteBtn", function(event) {
	var aplcn_carer_proper_num = event.target.parentNode.parentNode.firstElementChild.value;
	var aplcn_dtls_proper_num = document.getElementById('aplcn_dtls_proper_num').value;
	
		$.ajax({
			url: "../application/workInfoDelete",
			method: "POST",
			contentType: "application/json",
			data: JSON.stringify({aplcn_carer_proper_num: aplcn_carer_proper_num, aplcn_dtls_proper_num: aplcn_dtls_proper_num }),
			success: function(data) {
				
				$("#workList").children(".workListRow").remove();

				var str = "";
				for (var i = 0; i < data.length; i++) {

					str += '<tr class="workListRow">';
					str += '<input type="hidden" id="aplcn_carer_proper_num" name="aplcn_carer_proper_num" value="' + data[i].aplcn_carer_proper_num + '">';
					str += '<td style="vertical-align: middle;">' + data[i].company_name + '</td>';
					str += '<td style="vertical-align: middle;">' + data[i].work_start_date.substring(0, 10) + '~' + data[i].work_end_date.substring(0, 10) + '</td>';
					str += '<td style="vertical-align: middle;">' + data[i].work_description + '</td>';
					str += '<td style="vertical-align: middle;">' + data[i].work_department + '/' + data[i].work_position + '</td>';
					str += '<td style="vertical-align: middle;">';
					str += '<input type="button" class="modifyBtn" value="수정" style="border: 1px solid #888888;" />';
					str += '<input type="button" class="deleteBtn" value="삭제" style="border: 1px solid #888888;" />';
					str += '</td>';
					str += '</tr>';
				}

				$("#workList").append(str);
			},
			error: function(error) {
				console.error(error);
			}
		});
	});
})

/* 파일 수정 & 필수 입력 항목 유효성 검사 */
window.onload = function() {
	$("#workForm").on('submit', function() {
		
		$('#carer_description').on('input', function(){
			$("#carer_description").css({ 'border': '1px solid #ddd', 'color': '' });
			$("#msg1").text("");
		})
		
		$('#special_note_description').on('input', function(){
			$("#special_note_description").css({ 'border': '1px solid #ddd', 'color': '' });
			$("#msg2").text("");
		})
		
		if(!$('#carer_description').val()) {
			$("#carer_description").css({ 'border': '1px solid red', 'color': 'red' });
			$("#msg1").text("필수 입력 사항입니다");
			return false;
		}
		
		if(!$('#special_note_description').val()) {
			$("#special_note_description").css({ 'border': '1px solid red', 'color': 'red' });
			$("#msg2").text("필수 입력 사항입니다");
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
			url: "../application/workFileModify",
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
		str += '<input class="upload-name' + idx + ' name_box" value="" disabled="disabled" style="border: none;">';
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