package kr.or.connect.reservation.dto;

public enum CommentAddStatus {
	COMMENT_FAILURE("failure"), FILE_FAILURE("failure"), JUNCTION_FAILURE("failure"), SUCCESS("success");

	private final String message;

	CommentAddStatus(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}
}
