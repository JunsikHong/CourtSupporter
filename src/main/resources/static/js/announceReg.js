

  let registBtn = document.getElementById("registBtn");  
  registBtn.onclick = () => {
	  
    if(announceForm.announce_title.value == ""){
      alert("제목을 입력하세요.");
      announceForm.announce_title.focus();
      return false;      
	} else if(announceForm.announce_start_date.value == "") {
      alert("모집 시작일을 선택하세요.");
      announceForm.announce_start_date.focus();
      return false;
    } else if(announceForm.announce_end_date.value == "") {
      alert("모집 종료일을 선택하세요.");
      announceForm.announce_end_date.focus();
      return false;
    } else if(announceForm.announce_content.value == "") {
      alert("내용을 입력하세요.");
      announceForm.announce_content.focus();
      return false;
    } else {
      alert("정상적으로 등록되었습니다.");     
      return true;
    }
  }    



	//작성취소 - 목록으로 돌아가기
	let listBtn = document.getElementById("listBtn");
	listBtn.onclick = () => {
		event.preventDefault();
		window.location.href = "/announce/announceList";
	}

	//파일 첨부 및 삭제
	$(document).ready(function() {
		$("a[name='file-delete']").on("click", function(e) {
			e.preventDefault();
			deleteFile($(this));
		})
	});
		  
	function addFile(){ // id='fileList'
		
		 var str = "<span><input type='file' name='file' class='file' multiple>";
		    str += "<a href='#this' name='file-delete' class='delete'>삭제</a></span>";
		   
		
		$("#fileList").append(str);
		$("a[name='file-delete']").on("click", function(e) {
			e.preventDefault();
			deleteFile($(this));
		});
	}
	
	function deleteFile(obj) {
		obj.parent().remove();		
	}


    // 서머노트에 text 쓰기
	$('#summernote').summernote('insertText'); //, 써머노트에 쓸 텍스트	
	// 서머노트 쓰기 비활성화
	$('#summernote').summernote('disable');	
	// 서머노트 쓰기 활성화
	$('#summernote').summernote('enable');	
	// 서머노트 리셋
	$('#summernote').summernote('reset');	
	// 마지막으로 한 행동 취소 ( 뒤로가기 )
	$('#summernote').summernote('undo');
	// 앞으로가기
	$('#summernote').summernote('redo');
		


	$(document).ready(function() {
	//여기 아래 부분
	$('.summernote').summernote({
		  height: 300,                 // 에디터 높이
		  minHeight: null,             // 최소 높이
		  maxHeight: null,             // 최대 높이
		  focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
		  lang: "ko-KR",					// 한글 설정
		  placeholder: '최대 2048자까지 쓸 수 있습니다'	//placeholder 설정    	
		  /*toolbar: [
			       // [groupName, [list of button]]
				    ['fontname', ['fontname']],
				    ['fontsize', ['fontsize']],
				    ['style', ['bold', 'italic', 'underline','strikethrough', 'clear']],
				    ['color', ['forecolor','color']],
				    ['table', ['table']],
				    ['para', ['ul', 'ol', 'paragraph']],
				    ['height', ['height']],
				    ['insert',['picture','link','video']],
				    ['view', ['fullscreen', 'help']]
		  ],
	     fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New','맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체'],
		 fontSizes: ['8','9','10','11','12','14','16','18','20','22','24','28','30','36','50','72']	
		*/
		});
	});	
	
