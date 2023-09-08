window.onload = function() {
	$('#popupClose, #certificateModifyBtn').on('click', function() {
		$('#certificatePopupForm').submit();
		window.opener.certificateInfo();
	});
	
}