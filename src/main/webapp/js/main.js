const updateBtns = document.querySelectorAll(".btn-update");
const registerBtn = document.querySelector(".btn-register");

updateBtns.forEach(updateBtn =>
	updateBtn.addEventListener("click", updateTodoType, false)
);

registerBtn.addEventListener("click", registerNewTodo, false);

function updateTodoType(event) {
	const todoItemInfo = event.target.id.split("-");

	const type = todoItemInfo[0].toUpperCase();
	const id = todoItemInfo[1];

	let oReq = new XMLHttpRequest;

	oReq.onload = function() {
		const updateResult = this.responseText;

		if (oReq.readyState === XMLHttpRequest.DONE &&
			oReq.status === 200 &&
			updateResult === "Success") {
			renewItemColumn(id);
		} else ("요청에 실패하였습니다.")
	}

	oReq.open("POST", "http://localhost:8080/todoapp/update");
	oReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	oReq.send(`id=${id}&type=${type}`);
}

function renewItemColumn(currentItemId){
	const currentItem = document.getElementById(`card-${currentItemId}`);
	
	const currentItemType = currentItem.children[2].id.split("-")[0];
	const newItemType = (currentItemType === 'todo' ? 'doing' : 'done');
	currentItem.children[2].id = `${newItemType}-${currentItemId}`;	
	
	const nextColumnItems = currentItem.parentNode.nextSibling.nextSibling.children;
	
	let itemIndex = 1;
	for(itemIndex = 1; itemIndex < nextColumnItems.length; itemIndex++) {
		const item = nextColumnItems[itemIndex];
		const itemId = item.id.split("-")[1];
		if(currentItemId < itemId){
			item.parentNode.insertBefore(currentItem, item);
			break;
		}
	}
	if(itemIndex === nextColumnItems.length){
		nextColumnItems[0].parentNode.appendChild(currentItem);
	}
	
}

function registerNewTodo(_) {
	window.location.href = "http://localhost:8080/todoapp/register";
}