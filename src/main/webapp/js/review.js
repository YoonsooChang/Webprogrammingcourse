const startLoad = () => {
	reqHandler.getRequest();
};

const appendReviews = (comments) => {
	document.getElementById("btn-backward").onclick = (e) => {
		e.preventDefault();
		window.history.back();
	}


	let averageScore = 0.0;

	if (comments.length > 0) {
		const shortReviewContainer = document.getElementById("review-all");

		Handlebars.registerHelper("formatDate", function(date) {
			return `${date.year}.${date.monthValue}.${date.dayOfMonth} 방문`;
		});

		const bindComment = Handlebars.compile(document.getElementById("commentsItem").innerText);
		comments.forEach((comment) => {
			averageScore += parseFloat(comment.score);
			shortReviewContainer.innerHTML += bindComment(comment);
		});

		averageScore /= comments.length;
	}

	document.getElementById("average-score").innerHTML = parseFloat(averageScore).toFixed(1);
	document.getElementById("star-score").style.width = averageScore / 5.0 * 100 + "%";
	document.getElementById("comment-counts").innerHTML = comments.length + "건";

}

const urlGetParams = new URL(window.location.href).searchParams;
const reqHandler = new RequestHandler(`api/display/comment/${urlGetParams.get("id")}`, appendReviews, () => console.log('error'), () => true)

document.addEventListener("DOMContentLoaded", startLoad);