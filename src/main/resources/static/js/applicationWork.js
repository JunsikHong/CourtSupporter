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
	childPopup = window.open("applicationWorkPopup", "경력사항 입력", featureWindow);
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
	childPopup = window.open("applicationWorkModifyPopup?data=" + encodeURIComponent(data), "경력사항 수정", featureWindow);
	
}

/* 경력 정보 리스트 */
function workInfo() {
	
	setTimeout(function() {
		
	$.ajax({
		url: "../application/workInfo",
		method: "POST",
		contentType: "application/json", //보낼 데이터 형식
		data : JSON.stringify( {user_id: 'user1'} ),
		success : function(data) {
			
		$("#workList").children(".workListRow").remove();
		
		var str = "";
		for(var i = 0; i < data.length; i++) {
			
			str += '<tr class="workListRow">';
			str += '<input type="hidden" id="aplcn_carer_proper_num" name="aplcn_carer_proper_num" value="' + data[i].aplcn_carer_proper_num + '">';
			str += '<td style="vertical-align: middle;">' + data[i].company_name + '</td>';				
			str += '<td style="vertical-align: middle;">' + data[i].work_start_date + '~' + data[i].work_end_date + '</td>';
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
		error : function(status, error) {
			console.log(status);
			console.log(error);
		}
		
	})
	
	childPopup.close();
	}, 35);
		
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
	
		$.ajax({
			url: "../application/workInfoDelete",
			method: "POST",
			contentType: "application/json",
			data: JSON.stringify({ user_id: 'user1', aplcn_carer_proper_num: aplcn_carer_proper_num }),
			success: function(data) {
				
				$("#workList").children(".workListRow").remove();

				var str = "";
				for (var i = 0; i < data.length; i++) {

					str += '<tr class="workListRow">';
					str += '<input type="hidden" id="aplcn_carer_proper_num" name="aplcn_carer_proper_num" value="' + data[i].aplcn_carer_proper_num + '">';
					str += '<td style="vertical-align: middle;">' + data[i].company_name + '</td>';
					str += '<td style="vertical-align: middle;">' + data[i].work_start_date + '~' + data[i].work_end_date + '</td>';
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

