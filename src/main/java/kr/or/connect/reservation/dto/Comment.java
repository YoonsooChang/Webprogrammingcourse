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

	public static class Builder {
		private final int commentId;
		private final int productId;
		private final int reservationInfoId;

		private String comment = "";
		private LocalDate createDate = null;
		private LocalDate modifyDate = null;
		private LocalDate reservationDate = null;
		private String reservationEmail = "";
		private String reservationName = "";
		private String reservationTelephone = "";
		private double score = 0.0;

		public Builder(int commentId, int productId, int reservationInfoId) {
			this.commentId = commentId;
			this.productId = productId;
			this.reservationInfoId = reservationInfoId;
		}

		public Builder comment(String val) {
			this.comment = val;
			return this;
		}

		public Builder createDate(LocalDate val) {
			this.createDate = val;
			return this;
		}

		public Builder modifyDate(LocalDate val) {
			this.modifyDate = val;
			return this;
		}

		public Builder reservationDate(LocalDate val) {
			this.reservationDate = val;
			return this;
		}

		public Builder reservationEmail(String val) {
			this.reservationEmail = val;
			return this;
		}

		public Builder reservationName(String val) {
			this.reservationName = val;
			return this;
		}

		public Builder reservationTelephone(String val) {
			this.reservationTelephone = val;
			return this;
		}

		public Builder score(double val) {
			this.score = val;
			return this;
		}

		public Comment build() {
			return new Comment(this);
		}
	}

	private Comment(Builder builder) {
		comment = builder.comment;
		commentId = builder.commentId;
		commentImages = new ArrayList<CommentImage>();
		createDate = builder.createDate;
		modifyDate = builder.modifyDate;
		productId = builder.productId;
		reservationDate = builder.reservationDate;
		reservationEmail = builder.reservationEmail;
		reservationInfoId = builder.reservationInfoId;
		reservationName = builder.reservationName;
		reservationTelephone = builder.reservationTelephone;
		score = builder.score;
	}

	public void addImage(CommentImage image) {
		commentImages.add(image);
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
