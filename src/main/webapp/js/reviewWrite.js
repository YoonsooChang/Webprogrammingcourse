const MIN_TEXT_LEN = 5;
const MAX_TEXT_LEN = 400;

document.addEventListener("DOMContentLoaded", () => {
	setTextareaEvents();

	setStarClickEvent();

	setFileUploadEvent();

	const submitBtn = document.getElementById("submit-btn");
	submitBtn.onclick = postReview;
})

const setTextareaEvents = () => {
	const textarea = document.getElementById("review-textarea");

	document.getElementById("review-textarea-ref").onclick = (e) => {
		e.preventDefault();

		e.currentTarget.style.visibility = "hidden";
		textarea.focus();
	}

	textarea.oninput = (e) => {
		const letterCount = document.getElementById("letter-count");
		letterCount.innerHTML = e.target.value.length;
		if (e.target.value.length > MAX_TEXT_LEN) {
			letterCount.style.color = "red";
		} else {
			letterCount.style.color = "#999999";
		}
	}
}

const setStarClickEvent = () => {
	function Star(starNode, index) {
		this.nodeRef = starNode;
		this.value = index + 1;
		this.setClickEvent();
	}

	Star.prototype.setRank = function(rankNode) {
		rankNode.innerHTML = this.value;

		if (parseInt(rankNode.innerHTML) === 0) {
			rankNode.classList.add("gray_star");
		} else {
			rankNode.classList.remove("gray_star");
		}
	}

	Star.prototype.setClickEvent = function() {
		this.nodeRef.onclick = (e) => {
			e.preventDefault();

			const starRank = document.getElementById("star-rank");
			const starNodes = document.querySelectorAll(".rating_rdo");

			if (this.value === parseInt(starRank.innerHTML)) {
				this.nodeRef.classList.remove("checked");
				starRank.innerHTML = this.value - 1;
				return;
			}

			for (let i = 0; i < starNodes.length; i++) {
				if (i < this.value) {
					starNodes[i].classList.add("checked");
				} else {
					starNodes[i].classList.remove("checked");
				}
			}

			this.setRank(starRank);
		}
	}

	const stars = Array.from(document.querySelectorAll(".rating_rdo"))
		.map((starNode, index) => new Star(starNode, index));

}

const setFileUploadEvent = () => {
	const fileInput = document.getElementById("reviewImageFileOpenInput");
	fileInput.addEventListener("change", (e) => {
		const image = e.target.files[0];
		if (!validImageType(image)) {
			console.warn("invalide image file type");
			return;
		}

		const thumbnail = document.getElementById("thumbnail-img");
		thumbnail.src = window.URL.createObjectURL(image);
		document.getElementById("wrap-thumbnail").style.display = "inline-block";
	});

	function validImageType(image) {
		const imageTypes = ['image/jpeg', 'image/png', 'image/jpg'];
		return imageTypes.indexOf(image.type) > -1;
	}

}

const postReview = (e) => {
	e.preventDefault();

	const letterCount = document.getElementById("letter-count").innerHTML;

	if (letterCount < MIN_TEXT_LEN) {
		alert("최소 5자 이상 입력해주세요.");
		return;
	}

	if (letterCount > MAX_TEXT_LEN) {
		alert("최대 400자까지 입력가능합니다.");
		return;
	}

	const queryParams = {
		comment: document.getElementById("review-textarea").value,
		score: parseInt(document.getElementById("star-rank").innerHTML),
		productId: document.getElementById("product-id").value,
	}

	const reservationInfoId = document.getElementById("reservation-id").value;

	const url = `api/reservation/${reservationInfoId}/comments?${new URLSearchParams(queryParams)}`;

	const reqHandler = new RequestHandler(
		url,
		redirectToMyReservation,
		() => console.log("error"),
		(msg) => {
			console.log(msg);
			return (msg && msg === "success")
		}
	)

	const formData = new FormData();
	formData.enctype = "multipart/form-data";
	formData.append("attachedImage", document.getElementById("reviewImageFileOpenInput").files[0]);
	reqHandler.postRequest(formData, "text");
}

const redirectToMyReservation = () => {
	alert("한 줄 평이 등록되었습니다.");
	location.href = "/reservation/myreservation";
}