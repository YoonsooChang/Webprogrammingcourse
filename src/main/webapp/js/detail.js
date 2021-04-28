const startLoad = () => {
	reqHandler.getRequest();
};

const appendDetails = (data) => {
	console.log(data)
	const { averageScore,
		comments,
		displayInfo,
		displayInfoImage,
		productImages,
		productPrices } = JSON.parse(data);

	document.getElementById("average-score").innerHTML = parseFloat(averageScore).toFixed(1);
	document.querySelectorAll(".product-description").forEach(item => item.innerText = displayInfo.productDescription);
}


const urlGetParams = new URL(window.location.href).searchParams;
const reqHandler = new RequestHandler(`api/display/${urlGetParams.get("id")}`, appendDetails, () => console.log('error'), () => true)

document.addEventListener("DOMContentLoaded", startLoad);