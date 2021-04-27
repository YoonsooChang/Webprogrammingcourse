class RequestHandler {
	constructor(path, successHandler, failureHandler, validator) {
		this.path = path;
		this.onSuccess = successHandler;
		this.onFailure = failureHandler;
		this.isValid = validator;
	};

	getRequest = (paramObject) => {
		let oReq = new XMLHttpRequest;

		oReq.onload = () => {
			if (oReq.readyState === XMLHttpRequest.DONE && oReq.status === 200) {
				this.isValid(oReq.response)
					? this.onSuccess(oReq.response)
					: this.onFailure();
			} else {
				alert("요청에 실패하였습니다.");
			}
		}

		const url = this.path + this.makeQueryString(paramObject);

		oReq.open("GET", url);
		oReq.send();
	}

	makeQueryString = (paramObj) => (paramObj ? ("?" + new URLSearchParams(paramObj)) : "");

}
