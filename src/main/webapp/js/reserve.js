document.addEventListener("DOMContentLoaded", () => {
	const pathVar = document.getElementById("display-info-id").value;
	const url = `/reservation/api/display/reservation/${pathVar}`;

	const reqHandler
		= new RequestHandler(url,
			renderDOMs,
			() => console.log('error'),
			() => true,
		);

	reqHandler.getRequest();
});

const currencyRegex = /\B(?=(\d{3})+(?!\d))/g;

const renderDOMs = (data) => {
	const {
		displayInfo: {
			openingHours,
			placeLot,
		},
		productImages,
		productPrices,
	} = data;

	const lowestPrice = productPrices.map((item) => parseInt(item.price))
		.sort((priceA, priceB) => priceA - priceB)[0]
		.toString()
		.replace(currencyRegex, ",");

	const [openingDays, ...openingTime] = openingHours.split("\n");

	setUpImage({ ...productImages[0], lowestPrice, openingDays });

	fillUpDetailSectionNodes(placeLot, openingDays, openingTime, productPrices);

	setUpReservationForm(productPrices);

}

const setUpImage = (imageInfos) => {
	const bindProductImage
		= Handlebars.compile(document.getElementById("productImageItem").innerText);

	const productImageContainer
		= document.getElementById("product-image-container");

	productImageContainer.innerHTML += bindProductImage(imageInfos);

}

const PRICE_TYPES = {
	A: "성인",
	Y: "청소년",
	B: "유아",
	S: "셋트",
	D: "장애인",
	C: "지역주민",
	E: "어얼리버드",
	V: "VIP",
	R: "R석",
	B: "B석",
	S: "S석",
	D: "평일",
}

const fillUpDetailSectionNodes = (placeLot, openingDays, openingTime, productPrices) => {
	document.getElementById("detail-lot").innerHTML = placeLot;
	document.getElementById("detail-term").innerHTML = openingDays;
	document.getElementById("detail-time").innerHTML = openingTime;
	document.getElementById("detail-fee").innerHTML =
		productPrices
			.map((item) => {
				const priceType = PRICE_TYPES[item.priceTypeName];
				const price = item.price.toString().replace(currencyRegex, ",");
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

			const minusBtn = controller.querySelector(".ico_minus3");
			const plusBtn = controller.querySelector(".ico_plus3");
			const count = controller.querySelector(".count_control_input");
			const totalPrice = controller.querySelector(".total_price");

			const totalTicketCounts = document.getElementById("total-count");

			if (clicked === minusBtn) {
				if (count.value === this.maxCount) {
					plusBtn.classList.remove("disabled");
					plusBtn.style.pointerEvents = "auto";
				}

				count.value = parseInt(count.value) - 1;
				totalTicketCounts.innerHTML = parseInt(totalTicketCounts.innerHTML) - 1;

				if (count.value === "0") {
					minusBtn.style.pointerEvents = "none";
					minusBtn.classList.add("disabled");
					count.classList.add("disabled");
				}
			} else {
				if (count.value === "0") {
					minusBtn.style.pointerEvents = "auto";
					minusBtn.classList.remove("disabled");
					count.classList.remove("disabled");
				}

				count.value = parseInt(count.value) + 1;
				totalTicketCounts.innerHTML = parseInt(totalTicketCounts.innerHTML) + 1;

				if (count.value === this.maxCount) {
					plusBtn.style.pointerEvents = "none";
					plusBtn.classList.add("disabled");
				}
			}

			totalPrice.innerHTML = (price * parseInt(count.value)).toString().replace(currencyRegex, ",");
		});
	},

	getTicketCounts: function() {
		return Array.from(this.ticketCounters)
			.map(counter => counter.querySelector(".count_control_input").value);
	}
}


const checkValid = () => {
	let isValid = true;
	const name = document.querySelector("input[name='name']").value;
	const tel = document.querySelector("input[name='tel']").value;
	const email = document.querySelector("input[name='email']").value;

	const nameWarning = document.getElementById("name-warning");
	const telWarning = document.getElementById("tel-warning");
	const emailWarning = document.getElementById("email-warning");

	if (name.length === 0) {
		nameWarning.style.visibility = "visible";
		isValid = false;
	} else {
		nameWarning.style.visibility = "hidden";
	}

	if ((/^[\w+_]\w+@\w+\.\w+$/).test(email) === false) {
		emailWarning.style.visibility = "visible";
		isValid = false;
	} else {
		emailWarning.style.visibility = "hidden";
	}

	if ((/^\d{3}-\d{3,4}-\d{4}$/).test(tel) === false) {
		telWarning.style.visibility = "visible";
		isValid = false;
	} else {
		telWarning.style.visibility = "hidden";
	}


	return isValid;
}


const sendReservationParams = (productPriceIds, productId) => {
	const formData = document.getElementById("reservation-form");

	const displayInfoIdAttr = document.createElement("input");
	displayInfoIdAttr.setAttribute("type", "hidden");
	displayInfoIdAttr.setAttribute("name", "displayInfoId");
	displayInfoIdAttr.setAttribute("value", document.getElementById("display-info-id").value);

	formData.appendChild(displayInfoIdAttr);

	const productIdAttr = document.createElement("input");
	productIdAttr.setAttribute("type", "hidden");
	productIdAttr.setAttribute("name", "productId");
	productIdAttr.setAttribute("value", productId);

	formData.appendChild(productIdAttr);


	const ticketCounts = countController.getTicketCounts();

	const counts = document.createElement("input");
	counts.setAttribute("type", "hidden");
	counts.setAttribute("name", "counts");
	counts.setAttribute("value", ticketCounts);

	formData.appendChild(counts);

	const priceIds = document.createElement("input");
	priceIds.setAttribute("type", "hidden");
	priceIds.setAttribute("name", "priceIds");
	priceIds.setAttribute("value", productPriceIds);

	formData.appendChild(priceIds);

	formData.submit();
}

let countController;
let prices = [];

const setUpReservationForm = (productPrices) => {
	const counterTemplate = document.getElementById("countControllerItem");
	const bindProductPrice = Handlebars.compile(counterTemplate.innerText);
	const counterContainer = document.getElementById("counter-container");

	productPrices.forEach((item, index) => {
		prices.push(item.price);

		item = {
			...item,
			priceTypeName: PRICE_TYPES[item.priceTypeName],
			price: item.price.toString().replace(currencyRegex, ","),
			controllerId: `ticket-${index}`,
		};
		counterContainer.innerHTML += bindProductPrice(item);
	});

	countController = new CountController(counterContainer.children, prices);


	document.getElementById("date-now").innerHTML = new Date().toLocaleDateString();

	const submitBtn = document.getElementById("book-btn");
	submitBtn.style.pointerEvents = "none";

	document.querySelectorAll(".btn_agreement")
		.forEach((agreement) => agreement.onclick = (e) => {
			e.preventDefault();
			agreement.closest(".agreement").classList.toggle("open")
		});

	const allCheckBtn = document.getElementById("chk3");
	allCheckBtn.onclick = () => {
		document.getElementById("book-btn-wrapper").classList.toggle("disable");
		submitBtn.style.pointerEvents = (allCheckBtn.checked === true)
			? "auto"
			: "none"
	};

	const productId = productPrices[0].productId;
	submitBtn.onclick = () => {
		if (checkValid() === true) {
			sendReservationParams(
				productPrices.map(price => price.productPriceId),
				productId
			);
		}
	}

}
