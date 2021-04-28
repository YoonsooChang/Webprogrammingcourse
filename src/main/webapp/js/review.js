const startLoad = () => {
	reqHandler.getRequest();
};

const appendReviews = (data) => {
	console.log(data)
}

const urlGetParams = new URL(window.location.href).searchParams;
const reqHandler = new RequestHandler(`api/display/comment/${urlGetParams.get("id")}`, appendReviews, () => console.log('error'), () => true)

document.addEventListener("DOMContentLoaded", startLoad);