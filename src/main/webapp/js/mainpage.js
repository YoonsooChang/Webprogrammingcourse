const startLoad = () => {
	categoryReqHandler.getRequest();
	promotionReqHandler.getRequest();
	productReqHandler.getRequest();
}

const appendCategories = (data) => {
	const { items } = data;

	const htmls = renderHTMLByTemplate(items, "category")
	document.getElementById("category_tab").innerHTML += htmls.join("");
}

const appendPromotions = (data) => {
	const { items } = data;

	const htmls = renderHTMLByTemplate(items, "promotion")
	document.getElementById("promotion-slide").innerHTML = htmls.join("");

	runAnimation();
}

const appendProducts = (data) => {
	const { items, totalCount } = data;

	const htmls = renderHTMLByTemplate(items, "product")

	const colCount = 2;
	let cols = document.querySelectorAll(".lst_event_box");
	htmls.forEach((html, index) =>
		cols[index % colCount].innerHTML += html);

	let itemCountsNode = document.getElementById("item-count");
	const currentItemCounts = parseInt(itemCountsNode.value) + htmls.length;

	itemCountsNode.value = currentItemCounts;

	if (currentItemCounts === totalCount) {
		setViewMoreBtnState("off");
	}

	document.getElementById("event_count").innerHTML = totalCount + "개";
}

const renderHTMLByTemplate = (items, componentName) => {
	const templateHTML = document.getElementById(componentName + "Item").innerHTML;
	let resultHTMLs = [];

	items.forEach((item) => {
		let replaced = templateHTML;
		Object.keys(item).forEach((attr) =>
			replaced = replaced.replaceAll(`{${attr}}`, item[attr]));
		resultHTMLs.push(replaced);
	});

	return resultHTMLs;
}

const setViewMoreBtnState = (state) => {
	let viewMore = document.querySelector(".wrap_btn");
	viewMore.style.display = (state === "on" ? "block" : "none");
}

const getCategoryFromClicked = (clicked) => {
	if (clicked.tagName === "UL") {
		return null;
	}

	return clicked.dataset.category;
}

const clearCols = () => {
	document.querySelectorAll(".lst_event_box").forEach(col => col.innerHTML = "");
	document.getElementById("item-count").value = "0";
}

let tabUI = document.querySelector(".event_tab_lst");

tabUI.addEventListener("click", (e) => {
	const clicked = e.target;
	const selectedCategory = getCategoryFromClicked(clicked);
	if (selectedCategory === null) {
		return;
	}

	const currentTab = document.querySelector(".item.active");
	const currentCategory = currentTab.dataset.category;
	if (selectedCategory === currentCategory) {
		return;
	}

	const selectedTab = document.querySelector(`li[data-category="${selectedCategory}"]`);
	currentTab.classList.remove("active");
	selectedTab.classList.add("active");

	setViewMoreBtnState("on");
	clearCols();

	const paramObj = { categoryId: selectedCategory };
	productReqHandler.getRequest(paramObj);
});


let viewMoreBtn = document.querySelector(".btn_more");

viewMoreBtn.addEventListener("click", () => {
	const currentItemCounts = document.getElementById("item-count").value;
	const currentCategory = document.querySelector(".item.active").dataset.category;

	const paramObj = { categoryId: currentCategory, start: currentItemCounts };
	productReqHandler.getRequest(paramObj);
});


let slideBox = document.getElementById("promotion-slide");

let promotionCounts = 0;
let promotionPos = 0;

let waitStart = null;
const movePeriod = 2000;

const runAnimation = () => {
	let firstChildClone = slideBox.firstElementChild.cloneNode(true);
	slideBox.appendChild(firstChildClone);
	slideBox.style.left = 0;

	promotionCounts = slideBox.children.length;

	requestAnimationFrame(slideLeftInfinite);
}

const movePositionLeft = () => {
	promotionPos = (promotionPos + 1) % promotionCounts;
	slideBox.style.left = -promotionPos * 100 + "%";
}

const blinkToStart = () => {
	slideBox.style.transition = "none";
	promotionPos = 0;
	slideBox.style.left = 0;
}

const slideLeftInfinite = (timestamp) => {
	if (!waitStart) {
		waitStart = timestamp;
	}

	const progress = timestamp - waitStart;

	if (progress > movePeriod) {
		movePositionLeft();
		waitStart = timestamp;

		if (promotionPos === promotionCounts - 1) {
			setTimeout(blinkToStart, 1000);
		} else {
			slideBox.style.transition = "left 1s ease-in";
		}
	}

	requestAnimationFrame(slideLeftInfinite);
}

const printReqErr = () => console.log("응답 형식이 잘못되었습니다.");

const hasItem = (data) => (data && data.items);

const categoryReqHandler = new RequestHandler("api/category", appendCategories, printReqErr, hasItem);
const promotionReqHandler = new RequestHandler("api/promotion", appendPromotions, printReqErr, hasItem);
const productReqHandler = new RequestHandler("api/product", appendProducts, printReqErr, hasItem);

document.addEventListener("DOMContentLoaded", startLoad);