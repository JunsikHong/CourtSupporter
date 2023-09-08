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