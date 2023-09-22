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
	childPopup = window.open("applicationCertificatePopup?announce=" + announce + "&detail=" + detail, "자격증 사항 입력", featureWindow);
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
	childPopup = window.open("applicationCertificateModifyPopup?data=" + encodeURIComponent(data) + "&announce=" + announce + "&detail=" + detail, "자격증 사항 수정", featureWindow);
	
}

/* 자격증 정보 리스트 */
function certificateInfo(aplcn_dtls_proper_num) {
	
	setTimeout(function() {
		
	$.ajax({
		url: "../application/certificateInfo",
		method: "POST",
		contentType: "application/json", //보낼 데이터 형식
		data : JSON.stringify( {aplcn_dtls_proper_num: aplcn_dtls_proper_num} ),
		success : function(data) {
		
		$("#certificateList").children(".certificateListRow").remove();
		
		var str = "";
		for(var i = 0; i < data.length; i++) {
			
			str += '<tr class="certificateListRow">';
			str += '<input type="hidden" id="aplcn_crtfc_proper_num" name="aplcn_crtfc_proper_num" value="' + data[i].aplcn_crtfc_proper_num + '">';
			str += '<td style="vertical-align: middle;">' + data[i].crtfc_type + '</td>';				
			str += '<td style="vertical-align: middle;">' + data[i].issue_agency + '</td>';
			str += '<td style="vertical-align: middle;">' + data[i].crtfc_number + '</td>';
			str += '<td style="vertical-align: middle;">' + data[i].issue_date.substring(0, 10) + '</td>';
			str += '<td style="vertical-align: middle;">';
			str += '<input type="button" class="modifyBtn" value="수정" style="margin-right: 3px;" />';
			str += '<input type="button" class="deleteBtn" value="삭제"/>';
			str += '</td>';
			str += '</tr>';
		}
		
		$("#certificateList").append(str);
	
		},
		error : function(status, error) {
			console.log(status);
			console.log(error);
		}
		
	})
	
	childPopup.close();
	}, 35);
		
}

/* 자격증 정보 수정 */
$(document).ready(function() {
	$(document).on("click", ".modifyBtn", function(event) {
		var aplcn_crtfc_proper_num = event.target.parentNode.parentNode.firstElementChild.value;
		modifyPopUp(aplcn_crtfc_proper_num);
	})
})


/* 자격증 정보 삭제 */
$(document).ready(function() {
	
	$(document).on("click", ".deleteBtn", function(event) {
	var aplcn_crtfc_proper_num = event.target.parentNode.parentNode.firstElementChild.value;
	var aplcn_dtls_proper_num = document.getElementById('aplcn_dtls_proper_num').value;
	
		$.ajax({
			url: "../application/certificateInfoDelete",
			method: "POST",
			contentType: "application/json",
			data: JSON.stringify({aplcn_crtfc_proper_num: aplcn_crtfc_proper_num, aplcn_dtls_proper_num: aplcn_dtls_proper_num }),
			success: function(data) {
				
				$("#certificateList").children(".certificateListRow").remove();

				var str = "";
				for (var i = 0; i < data.length; i++) {

					str += '<tr class="certificateListRow">';
					str += '<input type="hidden" id="aplcn_crtfc_proper_num" name="aplcn_crtfc_proper_num" value="' + data[i].aplcn_crtfc_proper_num + '">';
					str += '<td style="vertical-align: middle;">' + data[i].crtfc_type + '</td>';
					str += '<td style="vertical-align: middle;">' + data[i].issue_agency + '</td>';
					str += '<td style="vertical-align: middle;">' + data[i].crtfc_number + '</td>';
					str += '<td style="vertical-align: middle;">' + data[i].issue_date.substring(0, 10) + '</td>';
					str += '<td style="vertical-align: middle;">';
					str += '<input type="button" class="modifyBtn" value="수정" style="margin-right: 3px;" />';
					str += '<input type="button" class="deleteBtn" value="삭제"/>';
					str += '</td>';
					str += '</tr>';
				}

				$("#certificateList").append(str);
			},
			error: function(error) {
				console.error(error);
			}
		});
	});
})


/* 파일 수정 */
window.onload = function() {
	$("#certificateForm").on('submit', function() {

		var uuids = [];
		const elements = document.getElementsByClassName("uuid");
		console.log(elements)
		for(var i = 0; i < elements.length; i++) {
			uuids.push(elements[i].value);
		}
		
		var detail = document.getElementById('aplcn_dtls_proper_num').value;
		$.ajax({
			url: "../application/certificateFileModify",
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
		str += '<input id="file-upload-name" class="upload-name' + idx + '" value="" disabled="disabled">';
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

/* 이전 버튼 */
function returnPage() {
	var announce = document.getElementById('announce_proper_num').value;
	var fcltt_num = document.getElementById('trial_fcltt_proper_num').value;
	var detail = document.getElementById('aplcn_dtls_proper_num').value
	location.href = '/application/applicationWork?announce=' + announce + "&fcltt_num=" + fcltt_num + "&detail=" + detail;
}