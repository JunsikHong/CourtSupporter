//confirm
const subbtn = document.querySelector(".subbtn");
const delete_code = document.getElementById("delete_code");
const err = document.querySelector(".err");

subbtn.addEventListener("click", function(event) {
	event.preventDefault();  // 기본 동작인 폼 제출 방지

	if(delete_code.value !== '회원탈퇴') {
		err.innerHTML = '삭제코드를 정확히 입력해주세요.';
		return;
	}
	
	const confirmResult = confirm("정말로 탈퇴하시겠습니까?");
	
	if (confirmResult) {
		event.target.form.submit();
	}
});

