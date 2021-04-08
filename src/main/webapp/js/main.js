let updateBtns = document.querySelectorAll(".btn-update");
let registerBtn = document.querySelector(".btn-register");

updateBtns.forEach(updateBtn =>
	updateBtn.addEventListener("click", updateTodoType, false)
);

registerBtn.addEventListener("click", registerNewTodo, false);

function updateTodoType(event) {
	const todoItemInfo = event.target.id.split("_");

	const type = todoItemInfo[0].toUpperCase();
	const id = todoItemInfo[1];

	let oReq = new XMLHttpRequest;

	oReq.onload = function() {
		const updateResult = this.responseText;

		if (oReq.readyState === XMLHttpRequest.DONE &&
			oReq.status === 200 &&
			updateResult === "Success") {
			window.location.href = 'http://localhost:8080/todoapp/main';
		} else ("요청에 실패하였습니다.")
	}

	oReq.open("POST", "http://localhost:8080/todoapp/update");
	oReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	oReq.send(`id=${id}&type=${type}`);
}

function registerNewTodo(_) {
	window.location.href = "http://localhost:8080/todoapp/register";
}