const targetComponents = ["categories", "promotions", "products"];

const startLoad = () => targetComponents.forEach(component => getRequest(component));

const getRequest = (componentName, params) => {
	let oReq = new XMLHttpRequest;

	oReq.onload = function() {
		if (oReq.readyState === XMLHttpRequest.DONE && oReq.status === 200) {
			isValid(this.response)
				? renderDOM(this.response, componentName)
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
	.reduce((acc, cur) => `${acc}&${cur})`);


const isValid = (response) => (response && JSON.parse(response).items) ? true : false;

const renderDOM = (data, name) => {
	console.log(data);
}

const renderErr = (name) => {
	console.log(name, 'error');
}

document.addEventListener("DOMContentLoaded", startLoad);