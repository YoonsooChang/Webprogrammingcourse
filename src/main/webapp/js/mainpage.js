const loadTargets = ["category", "promotion", "product"];
const DOMAppenders = {};

const startLoad = () => {
	setDOMAppenders();
	loadTargets.forEach(target => getRequest(target));
}

const setDOMAppenders = () => {
	DOMAppenders.product = appendProducts;
	DOMAppenders.promotion = appendPromotions;
	DOMAppenders.category = appendCategories;
}

const getRequest = (componentName, paramObj) => {
	let oReq = new XMLHttpRequest;

	oReq.onload = function() {
		if (oReq.readyState === XMLHttpRequest.DONE && oReq.status === 200) {
			isValid(this.response)
				? DOMAppenders[componentName](this.response, componentName)
				: handleErr(componentName);
		} else {
			alert(`${componentName} 요청에 실패하였습니다.`);
		}
	}

	const queryString = makeQueryString(paramObj);
	const url = `api/${componentName}${queryString}`;

	oReq.open("GET", url);
	oReq.send();
}

const makeQueryString = (paramObj) => (paramObj ? ("?" + new URLSearchParams(paramObj)) : "");

const isValid = (response) => (response && JSON.parse(response).items) ? true : false;

const handleErr = (name) => console.log(name, 'error');

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

const appendCategories = (data, componentName) => {
	const { items } = JSON.parse(data);

	const htmls = renderHTMLByTemplate(items, componentName)
	document.getElementById("category_tab").innerHTML += htmls.join("");
}

const appendPromotions = (data, componentName) => {
	const { items } = JSON.parse(data);

	const htmls = renderHTMLByTemplate(items, componentName)
	document.getElementById("promotion-slide").innerHTML = htmls.join("");

	runAnimation();
}

const appendProducts = (data, componentName) => {
	const { items, totalCount } = JSON.parse(data);

	const htmls = renderHTMLByTemplate(items, componentName)

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
	getRequest("product", paramObj);
});


let viewMoreBtn = document.querySelector(".btn_more");

viewMoreBtn.addEventListener("click", () => {
	const currentItemCounts = document.getElementById("item-count").value;
	const currentCategory = document.querySelector(".item.active").dataset.category;

	const paramObj = { categoryId: currentCategory, start: currentItemCounts };
	getRequest("product", paramObj);
});


let promotionCounts = 0;
let slideBox = document.getElementById("promotion-slide");
let promotionPos = 0;
let tick = 0;

const runAnimation = () => {
	let firstChildClone = slideBox.firstElementChild.cloneNode(true);
	slideBox.appendChild(firstChildClone);
	slideBox.style.left = 0;

	promotionCounts = slideBox.children.length;

	requestAnimationFrame(slideLeft);
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

const slideLeft = () => {
	tick = (tick + 1) % 100;

	if (tick === 0) {
		movePositionLeft();

		if (promotionPos === promotionCounts - 1) {
			setTimeout(blinkToStart, 1000);
		} else {
			slideBox.style.transition = "left 1s ease-in";
		}
	}

	requestAnimationFrame(slideLeft);
}

document.addEventListener("DOMContentLoaded", startLoad);