window.onload = function() {
	$('#popupClose, #workModifyBtn').on('click', function() {
		$('#workPopupForm').submit();
		window.opener.workInfo();
	});
	
}