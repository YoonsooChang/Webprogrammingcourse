const getRequest = (/*urlTokens,*/ url, componentName, handleDataCallback, paramObj) => {
	//const componentName = urlTokens[0];
	let oReq = new XMLHttpRequest;

	oReq.onload = function() {
		if (oReq.readyState === XMLHttpRequest.DONE && oReq.status === 200) {
			/*
			isValid(this.response)
				? handleDataCallback(this.response, componentName)
				: handleErr(componentName);
			*/
			handleDataCallback(this.response, componentName)
		} else {
			alert(`${componentName} 요청에 실패하였습니다.`);
		}
	}

	const queryString = makeQueryString(paramObj);
	//const url = `api/${urlTokens.join('/')}${queryString}`;
	url += queryString;
	oReq.open("GET", url);
	oReq.send();
}

const makeQueryString = (paramObj) => (paramObj ? ("?" + new URLSearchParams(paramObj)) : "");

const isValid = (response) => (response && JSON.parse(response).items) ? true : false;

const handleErr = (name) => console.log(name, 'error');