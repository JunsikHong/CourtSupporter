/* 파일 첨부 추가 */
/* 사업자 등록증 */
$(document).ready(function() {
	var idx = 1;
	$(document).on("click", ".CoFilePlusBtn1", function() {
		var str = "";
		str += '<div class="file-container">';
		str += '<label for="license-file-input-' + idx + '" class="file-label">';
		str += '<input type="hidden" class="fileAdd" style="border: none;" value="' + idx + '">';
		str += '<input type="file" id="license-file-input-' + idx + '" name="co_license" style="display: none;">';
		str += '<span class="fileAddBtn">파일선택</span>';
		str += '</label>';
		str += '<input class="lisence-upload-name' + idx + '" value="" disabled="disabled" style="border: none;">';
		str += '<input type="button" class="fileDeleteBtn" value="삭제" style="color: #fff; width: 80px;display: none">';
		str += '</div>';
		
		$("#CoFileAddList1").append(str);
		idx++;
	});
	
})
/* 업무 관련 등록 신고 현황 */
$(document).ready(function() {
	var idx = 1;
	$(document).on("click", ".CoFilePlusBtn2", function() {
		var str = "";
		str += '<div class="file-container">';
		str += '<label for="report-file-input-' + idx + '" class="file-label">';
		str += '<input type="hidden" class="fileAdd" style="border: none;" value="' + idx + '">';
		str += '<input type="file" id="report-file-input-' + idx + '" name="co_report" style="display: none;">';
		str += '<span class="fileAddBtn">파일선택</span>';
		str += '</label>';
		str += '<input class="report-upload-name' + idx + '" value="" disabled="disabled" style="border: none;">';
		str += '<input type="button" class="fileDeleteBtn" value="삭제" style="color: #fff; width: 80px;display: none">';
		str += '</div>';
		
		$("#CoFileAddList2").append(str);
		idx++;
	});
	
})

/* 납세증명확인서 */
$(document).ready(function() {
	var idx = 1;
	$(document).on("click", ".CoFilePlusBtn3", function() {
		var str = "";
		str += '<div class="file-container">';
		str += '<label for="tax-file-input-' + idx + '" class="file-label">';
		str += '<input type="hidden" class="fileAdd" style="border: none;" value="' + idx + '">';
		str += '<input type="file" id="tax-file-input-' + idx + '" name="co_tax" style="display: none;">';
		str += '<span class="fileAddBtn">파일선택</span>';
		str += '</label>';
		str += '<input class="tax-upload-name' + idx + '" value="" disabled="disabled" style="border: none;">';
		str += '<input type="button" class="fileDeleteBtn" value="삭제" style="color: #fff; width: 80px;display: none">';
		str += '</div>';
		
		$("#CoFileAddList3").append(str);
		idx++;
	});
	
})

/* 학력 정보 */
$(document).ready(function() {
	var idx = 1;
	$(document).on("click", ".PeFilePlusBtn2", function() {
		var str = "";
		str += '<div class="file-container">';
		str += '<label for="edu-file-input-' + idx + '" class="file-label">';
		str += '<input type="hidden" class="fileAdd" style="border: none;" value="' + idx + '">';
		str += '<input type="file" id="edu-file-input-' + idx + '" name="pe_edu" style="display: none;">';
		str += '<span class="fileAddBtn">파일선택</span>';
		str += '</label>';
		str += '<input class="edu-upload-name' + idx + '" value="" disabled="disabled" style="border: none;">';
		str += '<input type="button" class="fileDeleteBtn" value="삭제" style="color: #fff; width: 80px;display: none">';
		str += '</div>';
		
		$("#PeFileAddList2").append(str);
		idx++;
	});
	
})

/* 경력 정보 */
$(document).ready(function() {
	var idx = 1;
	$(document).on("click", ".PeFilePlusBtn3", function() {
		var str = "";
		str += '<div class="file-container">';
		str += '<label for="work-file-input-' + idx + '" class="file-label">';
		str += '<input type="hidden" class="fileAdd" style="border: none;" value="' + idx + '">';
		str += '<input type="file" id="work-file-input-' + idx + '" name="pe_work" style="display: none;">';
		str += '<span class="fileAddBtn">파일선택</span>';
		str += '</label>';
		str += '<input class="work-upload-name' + idx + '" value="" disabled="disabled" style="border: none;">';
		str += '<input type="button" class="fileDeleteBtn" value="삭제" style="color: #fff; width: 80px;display: none">';
		str += '</div>';
		
		$("#PeFileAddList3").append(str);
		idx++;
	});
	
})

/* 자격증 정보 */
$(document).ready(function() {
	var idx = 1;
	$(document).on("click", ".PeFilePlusBtn4", function() {
		var str = "";
		str += '<div class="file-container">';
		str += '<label for="cert-file-input-' + idx + '" class="file-label">';
		str += '<input type="hidden" class="fileAdd" style="border: none;" value="' + idx + '">';
		str += '<input type="file" id="cert-file-input-' + idx + '" name="pe_cert" style="display: none;">';
		str += '<span class="fileAddBtn">파일선택</span>';
		str += '</label>';
		str += '<input class="cert-upload-name' + idx + '" value="" disabled="disabled" style="border: none;">';
		str += '<input type="button" class="fileDeleteBtn" value="삭제" style="color: #fff; width: 80px;display: none">';
		str += '</div>';
		
		$("#PeFileAddList4").append(str);
		idx++;
	});
	
})

/* 기타 증빙서류 */
$(document).ready(function() {
	var idx = 1;
	$(document).on("click", ".OtFilePlusBtn", function() {
		var str = "";
		str += '<div class="file-container">';
		str += '<label for="ot-file-input-' + idx + '" class="file-label">';
		str += '<input type="hidden" class="fileAdd" style="border: none;" value="' + idx + '">';
		str += '<input type="file" id="ot-file-input-' + idx + '" name="ot_file" style="display: none;">';
		str += '<span class="fileAddBtn">파일선택</span>';
		str += '</label>';
		str += '<input class="ot_upload-name' + idx + '" value="" disabled="disabled" style="border: none;">';
		str += '<input type="button" class="fileDeleteBtn" value="삭제" style="color: #fff; width: 80px;display: none">';
		str += '</div>';
		
		$("#OtFileAddList").append(str);
		idx++;
	});
	
})

/* 파일 수정 */
window.onload = function() {
	$("#attachmentForm").on('submit', function(e) {
		e.preventDefault();
		var uuid_types = [];
		const elements = document.getElementsByClassName("uuid_type");
		for(var i = 0; i < elements.length; i++) {
		console.log(elements[i].value)
			uuid_types.push(elements[i].value);
		}
		
		$.ajax({
			url: "../application/attachmentFileModify",
			method: "POST",
			contentType: "application/json",
			data: JSON.stringify(uuid_types),
			success: function(data) {
				console.log(data)
				
			},
			error: function(error) {
				console.error(error);
			}
		});
		document.attachmentForm.submit();
	})
}

/* 파일 첨부 추가 삭제 */
$(document).ready(function() {
	$(document).on("click", ".fileDeleteBtn, .fileDeleteBtn2", function() {
		$(this).parent().remove();
	});
	
})
$(document).ready(function() {
	$(document).on("change", "#file-input", function(){
		var fileInput = $(this);
	  	var fileName = fileInput.val().split('\\').pop();
	  	fileInput.parent().next().val(fileName);
		$(this).parent().next().css('display', '');
	});
	
	$(document).on("click", ".PeFileDeleteBtn1", function() {
		$(this).parent().remove();

		var str = "";
		str += '<div>';
		str += '<label for="file-input" class="file-label">';
		str += '<input type="hidden" class="uuid_type">';
		str += '<input type="file" id="file-input" name="pe_resume" style="display: none;">';
		str += '<span id="resume_btn" class="fileAddBtn" style="width: 130px;height: 14px;">파일 선택</span>';
		str += '</label>';
		str += '<input id="resume_name" class="upload-name" disabled="disabled" style="width: 166px;display: none;">';
		str += '<input type="button" class="PeFileDeleteBtn1" value="삭제" style="color: #fff; width: 80px;display: none;">';
		str += '</div>';
		
		$("#resume_container").append(str);
	});
	
})
/* 파일 업로드 */
$(document).ready(function() {

   $(document).on("change", 'input[type=file]', function() {
	   
  	var fileInput = $(this);
  	var fileName = fileInput.val().split('\\').pop();
  	fileInput.parent().next().val(fileName);
  	
  	$(this).next().css('display', 'none');
  	$(this).parent().next().next().css({'display': '', 'background-color' : 'rgb(11, 38, 110)'});
  });
  
});