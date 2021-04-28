const startLoad = () => {
	reqHandler.getRequest();
};

const appendDetails = (data) => {
	const { averageScore,
		comments,
		displayInfo,
		displayInfoImage,
		productImages,
		productPrices } = JSON.parse(data);

	const reviewMoreBtn = document.getElementById("btn-review-more");
	reviewMoreBtn.setAttribute("href", reviewMoreBtn.getAttribute("href").replace("{displayInfoId}", urlGetParams.get("id")));

	document.getElementById("average-score").innerHTML = parseFloat(averageScore).toFixed(1);
	document.querySelectorAll(".product-description").forEach(item => item.innerText = displayInfo.productDescription);
}


const urlGetParams = new URL(window.location.href).searchParams;
const reqHandler = new RequestHandler(`api/display/${urlGetParams.get("id")}`, appendDetails, () => console.log('error'), () => true)

document.addEventListener("DOMContentLoaded", startLoad);