const A_SECOND = 1000;

let productImageContainer;
let productImageCount;

let imagePos = 1;
let imageStart, imageEnd;
let currentImageNumNode;
let averageScore = 0.0;

document.addEventListener("DOMContentLoaded", () => reqHandler.getRequest());


const appendDetails = (data) => {
	const {
		comments,
		displayInfo,
		displayInfoImage: {
			saveFileName,
		},
		productImages,
	} = data;

	const { productDescription, productContent } = displayInfo;

	document.getElementById("book-btn").onclick
		= (() => window.location.href = `reserve?id=${urlGetParams.get("id")}`);

	setUpImageSlide(productImages, productDescription);

	setUpContentToggler();

	setUpComments(comments);
	fillUpCommentSectionNodes(averageScore, productContent, comments.length);

	setUpInnerTabs();
	fillUpLocationSectionNodes(displayInfo, saveFileName);

}

const printReqErr = () => console.log("응답 형식이 잘못되었습니다.");

const isValid = (data) => {
	const {
		comments,
		displayInfo,
		displayInfoImage,
		productImages,
		productPrices
	} = data;

	return (data
		&& displayInfo && displayInfoImage
		&& Array.isArray(comments)
		&& Array.isArray(productImages)
		&& Array.isArray(productPrices)
	);
}


const setUpImageSlide = (productImages, productDescription) => {
	const bindProductImage
		= Handlebars.compile(document.getElementById("productImagesItem").innerText);

	productImageContainer
		= document.getElementById("product-image-slide");

	productImages.forEach(image => {
		image.productTitle = productDescription;
		productImageContainer.innerHTML += bindProductImage(image);
	});

	currentImageNumNode = document.getElementById("current-figure");
	currentImageNumNode.innerHTML = 1;

	productImageCount = productImages.length;
	document.getElementById("total-figure").innerHTML = productImageCount;

	const prevImageLink
		= document.getElementById("product-image-prev");

	const nextImageLink
		= document.getElementById("product-image-nxt");

	if (productImageCount === 1) {
		prevImageLink.style.display = "none";
		nextImageLink.style.display = "none";
		return;
	}

	setImagePaddingSides();

	prevImageLink.onclick = slideToLeft;
	nextImageLink.onclick = slideToRight;

}

const setImagePaddingSides = () => {
	const firstChildClone
		= productImageContainer.firstElementChild.cloneNode(true);

	const lastChildClone
		= productImageContainer.lastElementChild.cloneNode(true);

	productImageContainer.appendChild(firstChildClone);
	productImageContainer.insertBefore(lastChildClone, productImageContainer.firstElementChild);

	imageStart = 1;
	imageEnd = productImageCount;

	productImageContainer.style.left = "-100%";
}

const moveForASecond = () => {
	productImageContainer.style.transition = "left 1s ease-in";
	productImageContainer.style.left = -imagePos * 100 + "%";
}

const blinkTo = (destination) => {
	productImageContainer.style.transition = "none";
	imagePos = destination;
	productImageContainer.style.left = -destination * 100 + "%";
}

const slideToLeft = (e) => {
	e.preventDefault();

	currentImageNumNode.innerHTML
		= (imagePos === 1
			? imageEnd
			: imagePos - 1);

	imagePos--;
	moveForASecond();

	if (imagePos === imageStart - 1) {
		setTimeout(() => blinkTo(imageEnd), A_SECOND);
	}
}

const slideToRight = (e) => {
	e.preventDefault();

	currentImageNumNode.innerHTML
		= (imagePos === imageEnd
			? imageStart
			: imagePos + 1);

	imagePos++;
	moveForASecond();

	if (imagePos === imageEnd + 1) {
		setTimeout(() => blinkTo(imageStart), A_SECOND);
	}
}


const setUpContentToggler = () => {
	const contentOpener
		= document.querySelector("._open ");

	const contentCloser
		= document.querySelector("._close");

	const contentSection
		= document.getElementById("product-content-section");

	contentOpener.onclick = (e) => {
		e.preventDefault();

		contentOpener.style.display = "none";
		contentCloser.style.display = "block";
		contentSection.classList.remove("close3");
	}

	contentCloser.onclick = (e) => {
		e.preventDefault();

		contentCloser.style.display = "none";
		contentOpener.style.display = "block";
		contentSection.classList.add("close3");
	}
}


const setUpComments = (comments) => {
	const reviewMoreBtn = document.getElementById("btn-review-more");
	reviewMoreBtn.setAttribute("href", `review?id=${urlGetParams.get("id")}`);

	if (comments.length === 0) {
		return;
	}

	Handlebars.registerHelper("formatDate", (date) =>
		`${date.year}.${date.monthValue}.${date.dayOfMonth} 방문`
	);

	const bindComment = Handlebars.compile(document.getElementById("commentsItem").innerText);
	const shortReviewContainer = document.getElementById("review-short");

	comments.forEach((comment) => {
		averageScore += parseFloat(comment.score);
		shortReviewContainer.innerHTML += bindComment(comment)
	});

	averageScore /= comments.length;

}

const fillUpCommentSectionNodes = (averageScore, productContent, commentCounts) => {
	document.getElementById("average-score").innerHTML
		= parseFloat(averageScore).toFixed(1);

	document.getElementById("star-score").style.width
		= parseFloat(averageScore) / 5.0 * 100 + "%";

	document.getElementById("comment-counts").innerHTML
		= commentCounts + "건";

	document.querySelectorAll(".product_content")
		.forEach(item => item.innerText = productContent);
}

const setUpInnerTabs = () => {
	const tabAnchors
		= document.querySelectorAll(".info_tab_lst > .item > .anchor");

	const tabSections
		= document.querySelectorAll(".section_info_tab > .section");

	tabAnchors.forEach((tab) => {
		tab.onclick = (e) => {
			e.preventDefault();
			tabSections.forEach(section => section.classList.toggle("hide"));
			tabAnchors.forEach(anchor => anchor.classList.toggle("active"));
		}
	});

}


const fillUpLocationSectionNodes = (displayInfo, saveFileName) => {
	const { placeStreet, placeLot, placeName, telephone } = displayInfo;

	document.getElementById("map-image").setAttribute("src", saveFileName);
	document.getElementById("store-street").innerHTML = placeStreet;
	document.getElementById("store-lot").innerHTML = placeLot;
	document.getElementById("store-name").innerHTML = placeName;
	document.getElementById("store-telephone").innerHTML = telephone;
	document.getElementById("store-telephone").setAttribute("href", `tel:${telephone}`);
}


const urlGetParams = new URL(window.location.href).searchParams;
const reqHandler = new RequestHandler(`api/display/${urlGetParams.get("id")}`, appendDetails,
	printReqErr,
	isValid);
