document.getElementById('clear-btn')
	.addEventListener('click', clearFormData, false);

document.getElementById('back-btn')
	.addEventListener('click', goBack, false);

function clearFormData(_) {
	document.getElementById('todo-form').reset();
}

function goBack(_) {
	window.history.back();
}
