window.onload = function() {
	$('#popupClose, #educationModifyBtn').on('click', function() {
		$('#educationPopupForm').submit();
		window.opener.educationInfo();
	});
	
}

