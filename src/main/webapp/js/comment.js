const setUpComments = (comments, averageScore, commentCount) => {
	fillUpCommentSectionNodes(averageScore, commentCount);

	if (comments.length === 0) {
		return;
	}

	Handlebars.registerHelper("formatDate", (date) =>
		`${date.year}.${date.monthValue}.${date.dayOfMonth} 방문`
	);

	const bindComment = Handlebars.compile(document.getElementById("commentsItem").innerText);
	const shortReviewContainer = document.getElementById("review-list");

	comments.forEach((comment) => {
		shortReviewContainer.innerHTML += bindComment(comment)
	});

}

const fillUpCommentSectionNodes = (averageScore, commentCounts) => {
	document.getElementById("average-score").innerHTML
		= parseFloat(averageScore).toFixed(1);

	document.getElementById("star-score").style.width
		= parseFloat(averageScore) / 5.0 * 100 + "%";

	document.getElementById("comment-counts").innerHTML
		= commentCounts + "건";

}
