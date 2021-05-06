package kr.or.connect.reservation.dto;

import java.time.LocalDateTime;

public class CommentRequest {
	private String comment;
	private int commentId;
	private CommentImage commentImage;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
	private int productId;
	private int reservationInfoId;
	private int score;

	public CommentRequest(String comment, int commentId, CommentImage commentImage, LocalDateTime createDate,
		LocalDateTime modifyDate, int productId, int reservationInfoId, int score) {
		super();
		this.comment = comment;
		this.commentId = commentId;
		this.commentImage = commentImage;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.productId = productId;
		this.reservationInfoId = reservationInfoId;
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public int getCommentId() {
		return commentId;
	}

	public CommentImage getCommentImage() {
		return commentImage;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public LocalDateTime getModifyDate() {
		return modifyDate;
	}

	public int getProductId() {
		return productId;
	}

	public int getReservationInfoId() {
		return reservationInfoId;
	}

	public int getScore() {
		return score;
	}

	@Override
	public String toString() {
		return "CommentResponse [comment=" + comment + ", commentId=" + commentId + ", commentImage=" + commentImage
			+ ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", productId=" + productId
			+ ", reservationInfoId=" + reservationInfoId + ", score=" + score + "]";
	}

}
