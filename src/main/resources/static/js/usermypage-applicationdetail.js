document.addEventListener('DOMContentLoaded', function() {
	const arrowElements = document.querySelectorAll('.arrow');
	
	arrowElements.forEach(function(arrow) {
		arrow.addEventListener('click', function() {
			arrow.classList.toggle('arrow-up')
			arrow.classList.toggle('arrow-down')

			const tableToToggle = arrow.parentElement.nextElementSibling;
			tableToToggle.style.display = (tableToToggle.style.display === 'none') ? 'table' : 'none';
		});
	});
});

//confirm
const subbtn = document.querySelector(".subbtn");

if(subbtn != null) {
	subbtn.addEventListener("click", function(event) {
		event.preventDefault();  // 기본 동작인 폼 제출 방지
	    document.getElementById('evaluateresult').submit();		
	});
}

//confirm
const resultbtn = document.querySelector(".resultbtn");

if(resultbtn != null) {
	resultbtn.addEventListener("click", function(event) {
		event.preventDefault();  // 기본 동작인 폼 제출 방지
		resultpopUp();
	});
	
}

function resultpopUp() {
	var popupWidth = 800;
	var popupHeight = 500;
	var popupX = Math.round((window.screen.width / 2) - (popupWidth / 2));
	var popupY = Math.round((window.screen.height / 2) - (popupHeight / 2));
	var featureWindow = "width=" + popupWidth + ", height=" + popupHeight + ", left=" + popupX + ", top=" + popupY;
	
    var popupWindow = window.open("", "_blank", featureWindow);
    var evaluatepopup = document.getElementById('evaluateresult');
    
    popupWindow.document.write(evaluatepopup.outerHTML);
    popupWindow.document.getElementById('evaluateresult').submit();
}
