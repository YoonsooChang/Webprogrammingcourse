const startLoad = () => {
	reqHandler.getRequest();
};

const appendComments = (data) => {
	const { item: comments, averageScore } = data;
	document.getElementById("btn-backward").onclick = (e) => {
		e.preventDefault();
		window.history.back();
	}

	setUpComments(comments, averageScore, comments.length);
}

const getParams = new URL(window.location.href).searchParams;
const url = `api/display/comment/${getParams.get("id")}`;

const reqHandler = new RequestHandler(url,
	appendComments,
	() => console.log('error'),
	(data) => (data && data.item && data.averageScore != null),
);

document.addEventListener("DOMContentLoaded", startLoad);