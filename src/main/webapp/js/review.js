document.addEventListener("DOMContentLoaded", () => {
	const pathVar = document.getElementById("display-info-id").value;
	const url = `api/display/comment/${pathVar}`;

	const reqHandler
		= new RequestHandler(url,
			appendComments,
			() => console.log('error'),
			(data) => (data && data.item && data.averageScore != null),
		);

	reqHandler.getRequest();
});


const appendComments = (data) => {
	const { item: comments, averageScore } = data;
	document.getElementById("btn-backward").onclick = (e) => {
		e.preventDefault();
		window.history.back();
	}

	setUpComments(comments, averageScore, comments.length);
}
