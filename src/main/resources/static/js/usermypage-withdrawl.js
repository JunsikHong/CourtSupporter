//confirm
const subbtn = document.querySelector(".subbtn");

subbtn.addEventListener("click", function(event) {
	event.preventDefault();  // 기본 동작인 폼 제출 방지

	const confirmResult = confirm("정말로 탈퇴하시겠습니까?");
	if (confirmResult) {
		popUp();
	}
});

function popUp() {
	var popupWidth = 700;
	var popupHeight = 500;
	// window.screen.width  : 윈도우의 가로 크기
	// window.screen.height : 윈도우의 세로 크기
	var popupX = Math.round((window.screen.width / 2) - (popupWidth / 2));
	var popupY = Math.round((window.screen.height / 2) - (popupHeight / 2));

	// 윈도우 팝업창의 스타일 지정
	var featureWindow = "width=" + popupWidth + ", height=" + popupHeight
		+ ", left=" + popupX + ", top=" + popupY;
	// open("경로", "이름", "옵션")
	window.open("usermypage_withdrawl_popup", "자격 및 면허사항 입력", featureWindow);
}
