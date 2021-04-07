let updateBtns = document.querySelectorAll(".btn-update");

updateBtns.forEach(_ =>
	this.addEventListener("click", updateTodoType, false));

function updateTodoType(event) {
	const todoItemInfo = event.target.id.split("_");

	const type = todoItemInfo[0].toUpperCase();
	const id = todoItemInfo[1];

	let oReq = new XMLHttpRequest;
	oReq.onreadystatechange = function() {
		const updateResult = this.responseText;
		if (oReq.readyState === XMLHttpRequest.DONE) {
			if (oReq.status === 200 && updateResult === "Success") {
				window.location.href = 'http://localhost:8080/todoapp/main';
			} else console.log("요청에 실패하였습니다.");
		}
	};

	oReq.open("POST", "http://localhost:8080/todoapp/type");
	oReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	oReq.send(`id=${id}&type=${type}`);

	return;
}