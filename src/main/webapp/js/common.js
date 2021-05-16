class RequestHandler {
	constructor(path, successHandler, failureHandler, validator) {
		this.path = path;
		this.onSuccess = successHandler;
		this.onFailure = failureHandler;
		this.isValid = validator;
	};

	setRequestObject = () => {
		const oReq = new XMLHttpRequest;

		oReq.onload = () => {
			if (oReq.readyState === XMLHttpRequest.DONE) {
				if (oReq.status === 200) {
					this.isValid(oReq.response)
						? this.onSuccess(oReq.response)
						: this.onFailure();
				} else if (oReq.status >= 400) {
					alert("요청에 실패하였습니다.");
				}
			}
		}
		return oReq;
	};

	getRequest = (paramObject, responseType = "json") => {
		const url = this.path + this.makeQueryString(paramObject);
		const oReq = this.setRequestObject();

		oReq.responseType = responseType;
		oReq.open("GET", url);
		oReq.send();
	};

	postRequest = (requestBody, contentType = "application/x-www-form-urlencoded", responseType = "json") => {
		const url = this.path;
		const oReq = this.setRequestObject();

		oReq.responseType = responseType;
		oReq.open("POST", url);
		oReq.setRequestHeader("Content-Type", contentType);
		oReq.send(requestBody);
	};

	putRequest = (paramObject, responseType = "json") => {
		const url = this.path + this.makeQueryString(paramObject);
		const oReq = this.setRequestObject();

		oReq.responseType = responseType;
		oReq.open("PUT", url);
		oReq.send();
	};

	makeQueryString = (paramObject) => (paramObject ? ("?" + new URLSearchParams(paramObject)) : "");
}

const fetchData = (url, successHandler, failureHandler, validator, pathVariableNodeId) => {
	if (pathVariableNodeId) {
		const pathVariable = document.getElementById(pathVariableNodeId);
		if (pathVariable !== null) {
			url = `${url}${pathVariable.value}`;
		}
	}

	const reqHandler = new RequestHandler(url, successHandler, failureHandler, validator);
	reqHandler.getRequest();
}

const isSessionOn = () => {
	const userEmail = document.getElementById("user-email");
	return userEmail && userEmail.value !== "";
}

const setMyReservationLink = () => {
	const myReservationBtnSessionOff = document.getElementById("btn-my-session-off");
	const myReservationBtnSessionOn = document.getElementById("btn-my-session-on");

	if (isSessionOn()) {
		myReservationBtnSessionOff.style.visibility = "hidden";
		myReservationBtnSessionOn.style.visibility = "auto";
	} else {
		myReservationBtnSessionOff.style.visibility = "auto";
		myReservationBtnSessionOn.style.visibility = "hidden";
	}
}
