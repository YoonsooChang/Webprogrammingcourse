const startLoad = () => {
	const urlParams = new URL(window.location.href).searchParams;
	reqHandler.getRequest(urlParams);
};

const appendDetails = (data) => {
	const { averageScore,
		comments,
		displayInfo,
		displayInfoImage,
		productImages,
		productPrices } = JSON.parse(data);

	document.getElementById("average-score").innerHTML = parseFloat(averageScore).toFixed(1);
	document.querySelectorAll(".product-description").forEach(item => item.innerText = displayInfo.productDescription);
}

const reqHandler = new RequestHandler('dummy.txt', appendDetails, () => console.log('error'), () => true)

document.addEventListener("DOMContentLoaded", startLoad);