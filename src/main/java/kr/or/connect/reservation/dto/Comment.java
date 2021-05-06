package kr.or.connect.reservation.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Comment {
	private String comment;
	private int commentId;
	private List<CommentImage> commentImages;
	private LocalDate createDate;
	private LocalDate modifyDate;
	private int productId;
	private LocalDate reservationDate;
	private String reservationEmail;
	private int reservationInfoId;
	private String reservationName;
	private String reservationTelephone;
	private double score;

	public Comment(String comment, int commentId, LocalDate createDate, LocalDate modifyDate, int productId,
		LocalDate reservationDate, String reservationEmail, int reservationInfoId, String reservationName,
		String reservationTelephone, double score) {
		super();
		this.comment = comment;
		this.commentId = commentId;
		this.commentImages = new ArrayList<CommentImage>();
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.productId = productId;
		this.reservationDate = reservationDate;
		this.reservationEmail = reservationEmail;
		this.reservationInfoId = reservationInfoId;
		this.reservationName = reservationName;
		this.reservationTelephone = reservationTelephone;
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public List<CommentImage> getCommentImages() {
		return commentImages;
	}

	public void setCommentImages(List<CommentImage> commentImages) {
		this.commentImages = commentImages;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public LocalDate getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(LocalDate modifyDate) {
		this.modifyDate = modifyDate;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public LocalDate getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(LocalDate reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getReservationEmail() {
		return reservationEmail;
	}

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}

	public int getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	public String getReservationTelephone() {
		return reservationTelephone;
	}

	public void setReservationTelephone(String reservationTelephone) {
		this.reservationTelephone = reservationTelephone;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Comment [comment=" + comment + ", commentId=" + commentId + ", createDate=" + createDate
			+ ", modifyDate=" + modifyDate + ", productId=" + productId + ", reservationDate=" + reservationDate
			+ ", reservationEmail=" + reservationEmail + ", reservationInfoId=" + reservationInfoId
			+ ", reservationName=" + reservationName + ", reservationTelephone=" + reservationTelephone + ", score="
			+ score + "]";
	}

}
