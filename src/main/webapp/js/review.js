const appendComments = (data) => {
	const { item: comments, averageScore } = data;
	document.getElementById("btn-backward").onclick = (e) => {
		e.preventDefault();
		window.history.back();
	}

	setUpComments(comments, averageScore, comments.length);
}

document.addEventListener("DOMContentLoaded", () => {
	setMyReservationLink();

	fetchData(
		"/reservation/api/display/comment/",
		appendComments,
		() => console.log('error'),
		(data) => (data && data.item && data.averageScore != null),
		"display-info-id",
	);
})