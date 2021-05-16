const renderDOMs = (data) => {
	const {
		displayInfo: {
			openingHours,
			placeLot,
			categoryId,
		},
		productImages,
		productPrices,
	} = data;

	const lowestPrice = productPrices.map((item) => parseInt(item.price))
		.sort((priceA, priceB) => priceA - priceB)[0]
		.toLocaleString('ko-KR');

	const [openingDays, ...openingTime] = openingHours.split("\n");

	setUpImage({ ...productImages[0], lowestPrice, openingDays });

	fillUpDetailSectionNodes(placeLot, openingDays, openingTime, productPrices, categoryId);

	setUpReservationForm(categoryId, productPrices);

}

const setUpImage = (imageInfos) => {
	const bindProductImage
		= Handlebars.compile(document.getElementById("productImageItem").innerText);

	const productImageContainer
		= document.getElementById("product-image-container");

	productImageContainer.innerHTML += bindProductImage(imageInfos);

}

const PRICE_TYPES_AGE = {
	A: "성인",
	Y: "청소년",
	B: "유아",
	S: "셋트",
	D: "장애인",
	C: "지역주민",
};

const PRICE_TYPES_SEAT = {
	V: "VIP",
	R: "R석",
	B: "B석",
	S: "S석",
	D: "평일",
	E: "어얼리버드"
};

const getPriceTypeFullName = (typeId, typeName) => {
	if (typeId === 1) {
		return PRICE_TYPES_AGE[typeName];
	}
	return PRICE_TYPES_SEAT[typeName];
}

const fillUpDetailSectionNodes = (placeLot, openingDays, openingTime, productPrices, categoryId) => {
	document.getElementById("detail-lot").innerHTML = placeLot;
	document.getElementById("detail-term").innerHTML = openingDays;
	document.getElementById("detail-time").innerHTML = openingTime;
	document.getElementById("detail-fee").innerHTML = productPrices.map((item) => {
		const priceType = getPriceTypeFullName(categoryId, item.priceTypeName);
		const price = item.price.toLocaleString('ko-KR');
		return `${priceType} ${price}`;
	}).join("<br>");

}

function CountController(counters, prices) {
	this.ticketCounters = counters;
	this.ticketPrices = prices;
	this.maxCount = "5";
	this.registerEvents();
}

CountController.prototype = {
	MINUS: -1,
	PLUS: 1,

	registerEvents: function() {
		for (let index = 0; index < this.ticketCounters.length; index++) {
			const controller = this.ticketCounters[index].querySelector(".count_control");
			this.setCounterClickHandler(controller, this.ticketPrices[index]);
		}
	},

	setCounterClickHandler: function(controller, price) {
		controller.addEventListener("click", (event) => {
			event.preventDefault();

			const clicked = event.target;
			if (clicked.tagName !== "A") {
				return;
			}

			this.handleBtnClick(controller, clicked, parseInt(price));
		});
	},

	handleBtnClick: function(controller, clicked, price) {
		const minusBtn = controller.querySelector(".ico_minus3");
		const plusBtn = controller.querySelector(".ico_plus3");
		const ticketCount = controller.querySelector(".count_control_input");

		const totalTicketCount = document.getElementById("total-count");

		if (clicked === minusBtn) {
			if (ticketCount.value === this.maxCount) {
				this.setBtnStateOnMaxCount(plusBtn, this.MINUS);
			}

			this.renewTotalTicketCount(ticketCount, totalTicketCount, this.MINUS);

			if (ticketCount.value === "0") {
				this.setBtnStateOnMinCount(minusBtn, ticketCount, this.MINUS)
			}
		} else {
			if (ticketCount.value === "0") {
				this.setBtnStateOnMinCount(minusBtn, ticketCount, this.PLUS)
			}

			this.renewTotalTicketCount(ticketCount, totalTicketCount, this.PLUS);

			if (ticketCount.value === this.maxCount) {
				this.setBtnStateOnMaxCount(plusBtn, this.PLUS);
			}
		}

		const totalPrice = controller.querySelector(".total_price");
		totalPrice.innerHTML = (price * parseInt(ticketCount.value)).toLocaleString('ko-KR');
	},


	setBtnStateOnMaxCount: function(button, sign) {
		let pointerEvent;
		if (sign === this.MINUS) {
			pointerEvent = "auto";
		} else {
			pointerEvent = "none";
		}

		button.style.pointerEvents = pointerEvent;
		button.classList.toggle("disabled");
	},

	setBtnStateOnMinCount: function(button, ticketCount, sign) {
		let pointerEvent;
		if (sign === this.MINUS) {
			pointerEvent = "none";
		} else {
			pointerEvent = "auto";
		}

		button.style.pointerEvents = pointerEvent;
		button.classList.toggle("disabled");

		ticketCount.classList.add("disabled");
	},

	renewTotalTicketCount: function(current, total, offset) {
		current.value = parseInt(current.value) + offset;
		total.innerHTML = parseInt(total.innerHTML) + offset;
	},

	getTicketCounts: function() {
		return Array.from(this.ticketCounters)
			.map(counter => counter.querySelector(".count_control_input").value);
	}
}


const isValid = () => {

	const name = document.querySelector("input[name='reservationName']").value;
	const tel = document.querySelector("input[name='reservationTelephone']").value;
	const email = document.querySelector("input[name='reservationEmail']").value;

	const nameWarning = document.getElementById("name-warning");
	const telWarning = document.getElementById("tel-warning");
	const emailWarning = document.getElementById("email-warning");

	const emailRegex = /^[\w+_]\w+@\w+\.\w+$/;
	const telRegex = /^\d{3}-\d{3,4}-\d{4}$/;

	const nameValid = name.length !== 0;
	const emailValid = emailRegex.test(email);
	const telValid = telRegex.test(tel);

	nameWarning.style.visibility
		= nameValid ? "hidden" : "visible";

	emailWarning.style.visibility
		= emailValid ? "hidden" : "visible";

	telWarning.style.visibility
		= telValid ? "hidden" : "visible";

	return nameValid && emailValid && telValid;
}


let countController;

const sendReservationParams = (productPriceIds, productId) => {
	let params = {};

	const formData = new FormData(document.getElementById("reservation-form"));
	formData.forEach((value, key) => params[key] = value);

	params.displayInfoId = document.getElementById("display-info-id").value;
	params.productId = productId;
	params.counts = countController.getTicketCounts();
	params.productPriceIds = productPriceIds;

	let jsonObj = JSON.stringify(params);

	const reqHandler = new RequestHandler(
		"/reservation/api/reservation/",
		() => location.href = "/reservation/myreservation",
		() => console.log("error"),
		(msg) => msg === "success",
	);

	reqHandler.postRequest(jsonObj, "application/json", "text");
}

const setUpFormButtons = (priceIds, productId) => {
	const agreementBtns = document.querySelectorAll(".btn_agreement");
	agreementBtns
		.forEach((agreement) => agreement.onclick = (e) => {
			e.preventDefault();
			agreement.closest(".agreement").classList.toggle("open")
		});

	const allCheckBtn = document.getElementById("chk3");
	allCheckBtn.onclick = () => {
		document.getElementById("book-btn-wrapper").classList.toggle("disable");
		
		if (allCheckBtn.checked) {
			submitBtn.style.pointerEvents = "auto";
		} else {
			submitBtn.style.pointerEvents = "true";
		}

	};
	const submitBtn = document.getElementById("book-btn");
	submitBtn.style.pointerEvents = "none";

	submitBtn.onclick = () => {
		if (isValid() === true) {
			sendReservationParams(priceIds, productId);
		}
	}
}

const setUpReservationForm = (categoryId, priceData) => {

	const counterContainer = document.getElementById("counter-container");
	const counterTemplate = document.getElementById("countControllerItem");
	const bindProductPrice = Handlebars.compile(counterTemplate.innerText);

	let priceValues = [];

	priceData.forEach((item, index) => {
		priceValues.push(item.price);

		item = {
			...item,
			priceTypeName: getPriceTypeFullName(categoryId, item.priceTypeName),
			price: item.price.toLocaleString('ko-KR'),
			controllerId: `ticket-${index}`,
		};

		counterContainer.innerHTML += bindProductPrice(item);

	});

	countController = new CountController(counterContainer.children, priceValues);

	document.getElementById("date-now").innerHTML = new Date().toLocaleDateString();

	const productId = priceData[0].productId;
	const priceIds = priceData.map(price => price.productPriceId);

	setUpFormButtons(priceIds, productId);
}

document.addEventListener("DOMContentLoaded", () => {
	setMyReservationLink();

	fetchData(
		"/reservation/api/display/reservation/",
		renderDOMs,
		() => console.log('error'),
		() => true,
		"display-info-id",
	);
})