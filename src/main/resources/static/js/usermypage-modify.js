//email
var email_select = document.getElementById("email_select");
var user_email = document.getElementById("user_email");
var user_email1 = document.getElementById("user_email1");
var user_email2 = document.getElementById("user_email2");

email_select.onchange = function(e) {
	user_email2.value = e.target.value;
	user_email.value = user_email1.value + '@' + user_email2.value;
}

user_email1.onchange = function(e) {
	user_email1.value = e.target.value;
	user_email.value = user_email1.value + '@' + user_email2.value;
}

user_email.value = user_email1.value + '@' + user_email2.value;

//account
var account_select = document.getElementById("account_select");
var user_bank_account = document.getElementById("user_bank_account");
var user_bank = document.getElementById("user_bank");
var user_bank_holder = document.getElementById("user_bank_holder");

account_select.onchange = function(e) {
	user_bank.value = e.target.value;
}

user_bank_account.onchange = function(e) {
	user_bank_account.value = e.target.value;
}

user_bank_holder.onchange = function(e) {
	user_bank_holder.value = e.target.value;
}

//phone
var user_phone = document.getElementById("user_phone");
var user_phone1 = document.getElementById("user_phone1");
var user_phone2 = document.getElementById("user_phone2");
var user_phone3 = document.getElementById("user_phone3");

user_phone1.onchange = function(e) {
	user_phone1.value = e.target.value;
	user_phone.value = user_phone1.value + '-' + user_phone2.value + '-' + user_phone3.value;
}

user_phone2.onchange = function(e) {
	user_phone2.value = e.target.value;
	user_phone.value = user_phone1.value + '-' + user_phone2.value + '-' + user_phone3.value;
}

user_phone3.onchange = function(e) {
	user_phone3.value = e.target.value;
	user_phone.value = user_phone1.value + '-' + user_phone2.value + '-' + user_phone3.value;
}

user_phone.value = user_phone1.value + '-' + user_phone2.value + '-' + user_phone3.value;

//address
window.onload = function() {
	document.getElementById("address_kakao").addEventListener("click", function() { //주소입력칸을 클릭하면
		//카카오 지도 발생
		new daum.Postcode({
			oncomplete: function(data) { //선택시 입력값 세팅
				document.getElementById("address-zonecode").value = data.zonecode; // 지번 넣기
				document.getElementById("address").value = data.address; // 지번 넣기
				document.querySelector("input[name=user_ar_detail]").focus(); //상세입력 포커싱
			}
		}).open();
	});
}

//confirm
const subbtn = document.querySelector(".subbtn");

subbtn.addEventListener("click", function(event) {
	event.preventDefault();  // 기본 동작인 폼 제출 방지

	var fileInput = document.getElementById('main_file');
	var file = fileInput.files[0];

	if (!file || file.type.startsWith('image/')) {
		const confirmResult = confirm("정말로 정보를 변경하시겠습니까?");
		if (confirmResult) {
			event.target.form.submit();
		} else {
			alert("정보 변경이 취소되었습니다.");
		}
	}

	if (!file.type.startsWith('image/')) {
		alert('이미지 파일을 선택해주세요.');
		return false;
	}

});

function setThumbnail(event) {
	var reader = new FileReader();

	reader.onload = function(event) {
		var imgregist = document.querySelector(".imgregist");
		imgregist.setAttribute("src", event.target.result);
		imgregist.classList.add("imgregistauto");
	};
	reader.readAsDataURL(event.target.files[0]);
}

