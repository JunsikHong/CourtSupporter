/* 팝업창 띄우기 */
function popUp() {
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
	childPopup = window.open("applicationCertificatePopup", "자격증 사항 입력", featureWindow);
}

function modifyPopUp(data) {
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
	childPopup = window.open("applicationCertificateModifyPopup?data=" + encodeURIComponent(data), "자격증 사항 수정", featureWindow);
	
}

/* 자격증 정보 리스트 */
function certificateInfo() {
	
	setTimeout(function() {
		
	$.ajax({
		url: "../application/certificateInfo",
		method: "POST",
		contentType: "application/json", //보낼 데이터 형식
		data : JSON.stringify( {user_id: 'user1'} ),
		success : function(data) {
		
		$("#certificateList").children(".certificateListRow").remove();
		
		var str = "";
		for(var i = 0; i < data.length; i++) {
			
			str += '<tr class="certificateListRow">';
			str += '<input type="hidden" id="aplcn_crtfc_proper_num" name="aplcn_crtfc_proper_num" value="' + data[i].aplcn_crtfc_proper_num + '">';
			str += '<td style="vertical-align: middle;">' + data[i].crtfc_type + '</td>';				
			str += '<td style="vertical-align: middle;">' + data[i].issue_agency + '</td>';
			str += '<td style="vertical-align: middle;">' + data[i].crtfc_number + '</td>';
			str += '<td style="vertical-align: middle;">' + data[i].issue_date + '</td>';
			str += '<td style="vertical-align: middle;">';
			str += '<input type="button" class="modifyBtn" value="수정" style="border: 1px solid #888888;" />';
			str += '<input type="button" class="deleteBtn" value="삭제" style="border: 1px solid #888888;" />';
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
	
		$.ajax({
			url: "../application/certificateInfoDelete",
			method: "POST",
			contentType: "application/json",
			data: JSON.stringify({ user_id: 'user1', aplcn_crtfc_proper_num: aplcn_crtfc_proper_num }),
			success: function(data) {
				
				$("#certificateList").children(".certificateListRow").remove();

				var str = "";
				for (var i = 0; i < data.length; i++) {

					str += '<tr class="certificateListRow">';
					str += '<input type="hidden" id="aplcn_crtfc_proper_num" name="aplcn_crtfc_proper_num" value="' + data[i].aplcn_crtfc_proper_num + '">';
					str += '<td style="vertical-align: middle;">' + data[i].crtfc_type + '</td>';
					str += '<td style="vertical-align: middle;">' + data[i].issue_agency + '</td>';
					str += '<td style="vertical-align: middle;">' + data[i].crtfc_number + '</td>';
					str += '<td style="vertical-align: middle;">' + data[i].issue_date + '</td>';
					str += '<td style="vertical-align: middle;">';
					str += '<input type="button" class="modifyBtn" value="수정" style="border: 1px solid #888888;" />';
					str += '<input type="button" class="deleteBtn" value="삭제" style="border: 1px solid #888888;" />';
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