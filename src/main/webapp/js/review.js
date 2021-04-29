const startLoad = () => {
	reqHandler.getRequest();
};

const appendReviews = (data) => {
	document.getElementById("btn-backward").onclick = (e) => {
		e.preventDefault();
		window.history.back();
	}

	const comments = JSON.parse(data);
	let scoreSum = 0;

	const shortReviewContainer = document.getElementById("review-all");

	Handlebars.registerHelper("formatDate", function(date) {
		return `${date.year}.${date.monthValue}.${date.dayOfMonth} 방문`;
	});

	const bindComment = Handlebars.compile(document.getElementById("commentsItem").innerText);
	comments.forEach((comment) => {
		scoreSum += comment.score;
		shortReviewContainer.innerHTML += bindComment(comment);
	});

	const averageScore = (parseFloat(scoreSum) / comments.length).toFixed(1);

	document.getElementById("average-score").innerHTML = averageScore;
	document.getElementById("star-score").style.width = averageScore / 5.0 * 100 + "%";
	document.getElementById("comment-counts").innerHTML = comments.length + "건";

}

const urlGetParams = new URL(window.location.href).searchParams;
const reqHandler = new RequestHandler(`api/display/comment/${urlGetParams.get("id")}`, appendReviews, () => console.log('error'), () => true)

document.addEventListener("DOMContentLoaded", startLoad);