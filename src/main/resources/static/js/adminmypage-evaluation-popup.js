/////////////////////////////////////////////////////////////////////////////
/* submit */
//confirm
const subbtn = document.querySelector(".subbtn");

subbtn.addEventListener("click", function(event) {
	event.preventDefault();
	
    if (all_career_score.value == 0 || office_score.value == 0) {
        document.querySelector('.error-message').innerHTML = "(필수)";
        alert('기본평점은 필수입력입니다.');
        document.querySelector('.error-message').scrollIntoView({ behavior: 'smooth' });
    } else {
		const confirmResult = confirm("평가를 완료하시겠습니까?");
		if (confirmResult) {
			event.target.form.submit();
		}
    }	
});

/////////////////////////////////////////////////////////////////////////////
/* 총점 */
var evaluate_score = document.getElementById('evaluate_score');
var total = document.getElementById('total');

/////////////////////////////////////////////////////////////////////////////
/* 기본평정 */
var carrerSelectRadios = document.getElementsByName('carrerSelect');
var carrer2SelectRadios = document.getElementsByName('carrer2Select');
var locationSelectRadios = document.getElementsByName('locationSelect');
var carrerSum = document.getElementById('carrerSum');

var jrsdc_career_score = document.getElementById('jrsdc_career_score');
var all_career_score = document.getElementById('all_career_score');
var office_score = document.getElementById('office_score');

var carrerScore = document.getElementById('carrerScore');
var locationScore = document.getElementById('locationScore');
//통산경력
carrerSelectRadios.forEach(function(radio) {
	radio.addEventListener('change', updateCarrerScore);
});

//관내경력
carrer2SelectRadios.forEach(function(radio) {
	radio.addEventListener('change', updateCarrerScore);
});

//경력점수 함수
function updateCarrerScore() {
	

	var carrerTotalScore = 0;
	var allCarrerScore = 0;
	var jrsdcCarrerScore = 0;

	carrerSelectRadios.forEach(function(radio) {
		if (radio.checked) {
			carrerTotalScore += parseInt(radio.value);
			allCarrerScore += parseInt(radio.value);
		}
	});

	carrer2SelectRadios.forEach(function(radio) {
		if (radio.checked) {
			carrerTotalScore += parseInt(radio.value);
			jrsdcCarrerScore += parseInt(radio.value);
		}
	});

	carrerScore.value = carrerTotalScore;
	all_career_score.value = parseInt(allCarrerScore);
	jrsdc_career_score.value = parseInt(jrsdcCarrerScore);
	carrerSum.value = parseInt(carrerScore.value) + parseInt(locationScore.value);
	
	total.innerHTML = parseInt(all_career_score.value) +
					  parseInt(jrsdc_career_score.value) +
					  parseInt(office_score.value) +
					  parseFloat(certificate_score.value) +
					  parseInt(adjust_score.value);
					  
	evaluate_score.value = parseInt(all_career_score.value) +
					  	   parseInt(jrsdc_career_score.value) +
					       parseInt(office_score.value) +
					       parseFloat(certificate_score.value) +
					       parseInt(adjust_score.value);
}

//사무소 소재지
locationSelectRadios.forEach(function(radio) {
	radio.addEventListener('change', updateLocationScore);
});

//사무소소재지 함수
function updateLocationScore() {
	

	locationSelectRadios.forEach(function(radio) {
		if (radio.checked) {
			locationScore.value = parseInt(radio.value);
			office_score.value = parseInt(radio.value);
		}
	});

	carrerSum.value = parseInt(carrerScore.value) + parseInt(locationScore.value);
	
	total.innerHTML = parseInt(all_career_score.value) +
					  parseInt(jrsdc_career_score.value) +
					  parseInt(office_score.value) +
					  parseFloat(certificate_score.value) +
					  parseInt(adjust_score.value);
					  
	evaluate_score.value = parseInt(all_career_score.value) +
					  	   parseInt(jrsdc_career_score.value) +
					       parseInt(office_score.value) +
					       parseFloat(certificate_score.value) +
					       parseInt(adjust_score.value);
}

/////////////////////////////////////////////////////////////////////////////
/* 자격증 평정 */
var surveyorScore = document.getElementById('surveyorScore'); //점수합계
var surveyorSum = document.getElementById('surveyorSum'); //점수합계
var certificate_score = document.getElementById('certificate_score'); //실제값

if (trial_fcltt_proper_num == 1010303 ||
	trial_fcltt_proper_num == 1010509 ||
	trial_fcltt_proper_num == 1010408) {
	var surveyorSelect = document.getElementsByName('surveyorSelect'); //체크박스
	var appraiserScore = document.getElementsByName('appraiserScore'); //점수

	// 체크박스 이벤트 리스너 추가
	surveyorSelect.forEach(function(checkbox) {
		checkbox.addEventListener('change', updateSurveyorScore);
	});

	// 자격증 평점 업데이트 함수
	function updateSurveyorScore() {
		var totalScore = 0;

		surveyorSelect.forEach(function(checkbox, index) {
			if (checkbox.checked) {
				var score = parseFloat(appraiserScore[index].value);
				totalScore += score;
			}
		});

		surveyorScore.value = parseFloat(totalScore);
		surveyorSum.value = parseFloat(totalScore);
		certificate_score.value = parseFloat(totalScore);
		
		total.innerHTML = parseInt(all_career_score.value) +
					  parseInt(jrsdc_career_score.value) +
					  parseInt(office_score.value) +
					  parseFloat(certificate_score.value) +
					  parseInt(adjust_score.value);
					  
		evaluate_score.value = parseInt(all_career_score.value) +
					  	       parseInt(jrsdc_career_score.value) +
					           parseInt(office_score.value) +
					           parseFloat(certificate_score.value) +
					           parseInt(adjust_score.value);
	}
} else {
	surveyorScore.addEventListener('change', function(e) {
		if (e.target.value <= 10) {
			surveyorSum.value = parseInt(e.target.value);
			certificate_score.value = parseInt(e.target.value);
		} else {
			e.target.value = 10;
			surveyorSum.value = parseInt(e.target.value);
			certificate_score.value = parseInt(e.target.value);
		}
		total.innerHTML = parseInt(all_career_score.value) +
					  parseInt(jrsdc_career_score.value) +
					  parseInt(office_score.value) +
					  parseFloat(certificate_score.value) +
					  parseInt(adjust_score.value);
					  
		evaluate_score.value = parseInt(all_career_score.value) +
					  	       parseInt(jrsdc_career_score.value) +
					           parseInt(office_score.value) +
					           parseFloat(certificate_score.value) +
					           parseInt(adjust_score.value);
	});
}


/////////////////////////////////////////////////////////////////////////////
/* 심사위원 추천 */
var jurorScore = document.getElementById('jurorScore');
var adjust_score = document.getElementById('adjust_score');
var jurorSum = document.getElementById('jurorSum');

jurorScore.addEventListener('change', function(e) {
	if (e.target.value <= 7) {
		jurorSum.value = parseInt(e.target.value);
		adjust_score.value = parseInt(e.target.value);
	} else {
		e.target.value = 7;
		jurorSum.value = parseInt(e.target.value);
		adjust_score.value = parseInt(e.target.value);
	}
	total.innerHTML = parseInt(all_career_score.value) +
					  parseInt(jrsdc_career_score.value) +
					  parseInt(office_score.value) +
					  parseFloat(certificate_score.value) +
					  parseInt(adjust_score.value);
					  
	evaluate_score.value = parseInt(all_career_score.value) +
					  	   parseInt(jrsdc_career_score.value) +
					       parseInt(office_score.value) +
					       parseFloat(certificate_score.value) +
					       parseInt(adjust_score.value);
});


/////////////////////////////////////////////////////////////////////////////
/* 총점 */

