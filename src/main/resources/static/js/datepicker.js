//달력
$(document).ready(function () {
	$(".datepicker").datepicker({
		dateFormat: 'yy-mm-dd',
		dayNames: ['일', '월', '화', '수', '목', '금', '토'],
		dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
		dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
	});
	$(".datepicker").prop('readonly', false); //required사용 
});
// - 토글
$(document).ready(function() {
	$(".showtoggle").click(function() {
		
		var result = $(this).next("ul").css("display");
		if(result == "block") {
			$(this).next("ul").slideUp();
		} else {
			$(this).next("ul").slideDown();
		}

	});
});