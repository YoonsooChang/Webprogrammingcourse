let todoSection;
let doneSection;
let canceledSection;

document.addEventListener("DOMContentLoaded", () => {
	const url = `/reservation/api/reservation/`;

	const reqHandler
		= new RequestHandler(url,
			appendReservations,
			() => console.log('error'),
			() => true,
		);

	reqHandler.getRequest();
});

const appendReservations = (data) => {
	const { reservations, size } = data;

	let todoCount = 0;
	let doneCount = 0;
	let canceledCount = 0;

	todoSection = document.getElementById("confirmed-section");
	doneSection = document.getElementById("used-section");
	canceledSection = document.getElementById("canceled-section");

	const bindReservationSection =
		Handlebars.compile(document.getElementById("reservationItem").innerText);

	const currencyRegex = /\B(?=(\d{3})+(?!\d))/g;

	reservations.forEach((reservation) => {
		const {
			reservationInfoId,
			displayInfo: {
				productDescription,
				openingHours
			},
			totalPrice
		} = reservation;

		const sectionInfo = {
			reservationInfoId,
			productDescription,
			openingDays: openingHours.split("\n")[0],
			totalPrice: totalPrice.toString().replace(currencyRegex, ","),
		}

		if (reservation.cancelYn === true) {
			canceledCount++;
			canceledSection.innerHTML += bindReservationSection(sectionInfo);
		} else {
			todoCount++;
			todoSection.innerHTML += bindReservationSection(sectionInfo);
		}

	})

	setUpCategoryCountNodes([size, todoCount, doneCount, canceledCount]);

	setUpCancelBtnClickListener();
}

const setUpCategoryCountNodes = (categoryCounts) => {
	["count-all", "count-todo", "count-done", "count-canceled"]
		.forEach((nodeId, index) => {
			document.getElementById(nodeId).innerHTML = categoryCounts[index]
		});
}

const reRenderNodes = (result, id) => {
	/* TO DO : 실패 시 예외 처리 */
	const targetNode = document.getElementById(`reservation-${id}`);
	canceledSection.appendChild(targetNode);
}

const setUpCancelBtnClickListener = () => {
	const cancelBtns
		= Array.from(document.querySelectorAll(".booking_cancel"))
			.map(cancelNode => cancelNode.firstElementChild)

	cancelBtns.forEach(btn => btn.onclick = (e) => {
		e.preventDefault();

		const reservationInfoId = btn.id.split("-")[1];
		const url = `/reservation/api/reservation/cancel/${reservationInfoId}`;
		const reqHandler
			= new RequestHandler(url,
				(response) => {
					reRenderNodes(response, reservationInfoId)
				},
				() => console.log('error'),
				() => true,
			);

		reqHandler.getRequest();
	});
}
