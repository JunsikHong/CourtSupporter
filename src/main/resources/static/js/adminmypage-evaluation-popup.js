//confirm
const subbtn = document.querySelector(".subbtn");

subbtn.addEventListener("click", function(event) {
	event.preventDefault();  // 기본 동작인 폼 제출 방지
	
	const confirmResult = confirm("평가를 완료하시겠습니까?");
	if (confirmResult) {
		event.target.form.submit();
	}
});

