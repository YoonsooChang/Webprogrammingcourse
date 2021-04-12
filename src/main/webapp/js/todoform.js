document.getElementById("clear_btn")
	.addEventListener("click", clearFormData, false);

document.getElementById("back_btn")
	.addEventListener("click", goBack, false);

function clearFormData(_) {
	document.getElementById("todo_form").reset();
}

function goBack(_) {
	window.history.back();
}
