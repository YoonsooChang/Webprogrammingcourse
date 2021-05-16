const appendReservations = (data) => {
	const { reservations } = data;

	const RESERVATION_ID_LENGTH = 7;

	let todoCount = 0;
	let doneCount = 0;
	let canceledCount = 0;

	const todoSection = document.getElementById("confirmed-section");
	const doneSection = document.getElementById("used-section");
	const canceledSection = document.getElementById("canceled-section");

	const listWrapper = document.getElementById("list-wrapper");
	const errWrapper = document.getElementById("err-wrapper");

	const hide = (node) => node.style.display = "none";

	if (reservations.length === 0) {
		hide(listWrapper);
	} else {
		hide(errWrapper);
	}

	const bindTodoReservation = Handlebars.compile(document.getElementById("todoItem").innerText);
	const bindDoneReservation = Handlebars.compile(document.getElementById("doneItem").innerText);
	const bindCanceledReservation = Handlebars.compile(document.getElementById("canceledItem").innerText);

	reservations.forEach((reservation) => {
		const templateParams = {
			reservationInfoId:
				reservation.reservationInfoId,

			reservationInfoIdPadding:
				reservation.reservationInfoId.toString().padStart(RESERVATION_ID_LENGTH, '0'),

			productDescription:
				reservation.displayInfo.productDescription,

			openingDays:
				reservation.displayInfo.openingHours.split("\n")[0],

			totalPrice:
				reservation.totalPrice.toLocaleString('ko-KR'),
		}

		if (reservation.cancelYn) {
			canceledCount++;
			canceledSection.innerHTML += bindCanceledReservation(templateParams);
		} else {
			todoCount++;
			todoSection.innerHTML += bindTodoReservation(templateParams);
		}

	})

	if (todoCount === 0) {
		hide(todoSection);
	}

	if (doneCount === 0) {
		hide(doneSection);
	}

	if (canceledCount === 0) {
		hide(cancelSection);
	}

	document.getElementById("count-all").innerHTML = reservations.length;
	document.getElementById("count-todo").innerHTML = todoCount;
	document.getElementById("count-done").innerHTML = doneCount;
	document.getElementById("count-canceled").innerHTML = canceledCount;

	setUpCancelBtnClickListener();
}

const moveToCancelSection = (id) => {
	const targetNode = document.getElementById(`reservation-${id}`);
	targetNode.querySelector(".booking_cancel").style.visibility = "hidden";
	document.getElementById("canceled-section").appendChild(targetNode);
}

const requestCancel = (reservationId) => {
	const url = `/reservation/api/reservation/${reservationId}`;
	const reqHandler
		= new RequestHandler(url,
			() => moveToCancelSection(reservationId),
			() => alert("취소 요청에 실패하였습니다."),
			(data) => (data && data === "success"),
		);

	reqHandler.putRequest(null, "text");
}

const handlePopup = (reservationId, title, openingDays) => {
	document.getElementById("product-title").innerHTML = title;
	document.getElementById("opening-days").innerHTML = openingDays;


	const popup = document.getElementById("cancel-confirm-popup");
	popup.style.display = "block";

	const closeButton = document.getElementById("close-btn");
	closeButton.onclick = (e) => {
		e.preventDefault();
		popup.style.display = "none";
	}

	const popupButtons = document.querySelectorAll(".btn_bottom");
	popupButtons.forEach((popupBtn) => {
		popupBtn.onclick = (e) => {
			e.preventDefault();
			popup.style.display = "none";

			if (popupBtn.id === "confirm-btn") {
				requestCancel(reservationId)
			}
		}
	})
}

const setUpCancelBtnClickListener = () => {
	const cancelBtns = Array.from(document.querySelectorAll(".booking_cancel"))
		.map(cancelNode => cancelNode.firstElementChild)

	cancelBtns.forEach(cancelBtn => cancelBtn.onclick = (e) => {
		e.preventDefault();

		const reservationId = cancelBtn.id.split("-")[1];
		const reservation = document.getElementById(`reservation-${reservationId}`);

		const productDescription = reservation.querySelector(".tit").innerText;
		const openingDays = reservation.querySelector(".opening").innerText;

		handlePopup(reservationId, productDescription, openingDays);
	});
}


document.addEventListener("DOMContentLoaded", () => {
	setMyReservationLink();

	if (!isSessionOn()) {
		location.href = "/reservation/bookinglogin";
	} else {
		fetchData(
			"/reservation/api/reservation/",
			appendReservations,
			() => console.log('error'),
			() => true,
		);
	}
});