const targetComponents = ["promotion", "product"];
const DOMAppenders = {};

const startLoad = () => {
	setDOMAppenders();
	targetComponents.forEach(component => getRequest(component));
}

const setDOMAppenders = () => {
	DOMAppenders.product = appendProducts;
	DOMAppenders.promotion = appendPromotions;
}

const getRequest = (componentName, params) => {
	let oReq = new XMLHttpRequest;

	oReq.onload = function() {
		if (oReq.readyState === XMLHttpRequest.DONE && oReq.status === 200) {
			isValid(this.response)
				? renderDOMByTemplate(this.response, componentName)
				: renderErr(componentName);
		} else {
			alert(`${componentName} 요청에 실패하였습니다.`);
		}
	}

	const queryString = params ? makeQueryString(params) : "";
	const url = `api/${componentName}${queryString}`;

	oReq.open("GET", url);
	oReq.send();
}

const makeQueryString = (params) => "?" + Object.entries(params)
	.map(KVpair => `${KVpair[0]}=${KVpair[1]}`)
	.reduce((acc, cur) => `${acc}&${cur}`);


const isValid = (response) => (response && JSON.parse(response).items) ? true : false;

const renderDOMByTemplate = (data, name) => {
	const { items, ...subData } = JSON.parse(data);
	const templateHTML = document.getElementById(name + "Item").innerHTML;
	let resultHTMLs = [];

	items.forEach((item) => {
		let replaced = templateHTML;
		Object.keys(item).forEach((key) =>
			replaced = replaced.replaceAll(`{${key}}`, item[key]));
		resultHTMLs.push(replaced);
	});

	DOMAppenders[name](resultHTMLs, subData);
}

const appendPromotions = (htmls) =>
	document.querySelector(".visual_img").innerHTML = htmls.join("");

const appendProducts = (htmls, subData) => {
	const { totalCount } = subData;
	const colCount = 2;
	let cols = document.querySelectorAll(".lst_event_box");
	const currentItemCounts = (cols[0].children.length + cols[1].children.length);

	htmls.forEach((html, index) =>
		cols[index % colCount].innerHTML += html);

	(totalCount === currentItemCounts + htmls.length) && setViewMoreBtnState("off");

	document.querySelector("#event_count").innerHTML = totalCount + "개";
}

const renderErr = (name) => {
	console.log(name, 'error');
}

const setViewMoreBtnState = (state) => {
	let viewMore = document.querySelector(".wrap_btn");
	viewMore.style.display = (state === "on" ? "block" : "none");
}

const clearCols = () => document.querySelectorAll(".lst_event_box")
	.forEach(col => col.innerHTML = "");

const getCategoryFromClicked = (clicked) => {
	const tagName = clicked.tagName;

	if (tagName === "UL") {
		return null;
	}

	if (tagName !== "LI") {
		const closest = clicked.closest("LI");
		category = closest.dataset.category;
	} else {
		category = clicked.dataset.category;
	}

	return category;
}

let tabUI = document.querySelector(".event_tab_lst");
tabUI.addEventListener("click", (e) => {
	const clicked = e.target;
	let selectedCategory = getCategoryFromClicked(clicked);
	if (selectedCategory === null) {
		return;
	}

	const currentAnchor = document.querySelector(".active");
	const currentCategory = currentAnchor.closest("LI").dataset.category;
	if (selectedCategory === currentCategory) {
		return;
	}

	currentAnchor.className = "anchor";

	document.querySelectorAll(".anchor").forEach(anchor => {
		(anchor.closest("LI").dataset.category === selectedCategory) && (anchor.className = "anchor active");
	});

	setViewMoreBtnState("on");
	clearCols();

	const params = (category === "0" ? null : { categoryId: selectedCategory });
	getRequest("product", params);
});

let viewMoreBtn = document.querySelector(".btn_more");
viewMoreBtn.addEventListener("click", () => {
	let cols = document.querySelectorAll(".lst_event_box");
	const currentItemCounts = (cols[0].children.length + cols[1].children.length);

	const anchor = document.querySelector(".active");
	const category = anchor.closest("LI").dataset.category;

	let params = { start: currentItemCounts };
	(category !== "0") && (params = { categoryId: category, ...params });

	getRequest("product", params);
});

document.addEventListener("DOMContentLoaded", startLoad);