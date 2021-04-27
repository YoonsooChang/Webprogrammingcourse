const startLoad = () => {
	const urlParams = new URL(window.location.href).searchParams;
	//getRequest(['product', urlParams.get('id')], appendDetails);
	getRequest('dummy.txt', null, appendDetails);
};

const appendDetails = (data, componentName) => {
	const { averageScore,
		comments,
		displayInfo,
		displayInfoImage,
		productImages,
		productPrices } = data;
	
}

document.addEventListener("DOMContentLoaded", startLoad);