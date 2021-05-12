class RequestHandler {
	constructor(path, successHandler, failureHandler, validator) {
		this.path = path;
		this.onSuccess = successHandler;
		this.onFailure = failureHandler;
		this.isValid = validator;
		this.setRequestObject();

	};

	setRequestObject = () => {
		const oReq = new XMLHttpRequest;

		oReq.onload = () => {
			if (oReq.readyState === XMLHttpRequest.DONE && oReq.status === 200) {
				this.isValid(oReq.response)
					? this.onSuccess(oReq.response)
					: this.onFailure();
			} else {
				alert("요청에 실패하였습니다.");
			}
		}

		return oReq;
	}

	getRequest = (paramObject) => {
		const url = this.path + this.makeQueryString(paramObject);

		const oReq = this.setRequestObject();
		oReq.responseType = "json";
		oReq.open("GET", url);
		oReq.send();
	}

	postRequest = (paramObject) => {
		const oReq = this.setRequestObject();
		oReq.responseType = "json";
		oReq.open("POST", url);
		oReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		oReq.send(makeParameterStr(paramObject));
	}

	makeQueryString = (paramObject) => (paramObject ? ("?" + new URLSearchParams(paramObject)) : "");
	makeParameterStr = (paramObject) => Object.entries(paramObject)
		.map((kv) => `${kv[0]}=${kv[1]}`)
		.reduce((acc, cur) => `${acc}&${cur}}`);
}
