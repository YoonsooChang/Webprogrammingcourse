package kr.or.connect.reservation.dto;

import java.time.LocalDateTime;

public class CommentParam {
	private String comment;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
	private int productId;
	private int reservationInfoId;
	private int score;

	public static class Builder {
		private String comment;
		private LocalDateTime createDate;
		private LocalDateTime modifyDate;
		private int productId;
		private int reservationInfoId;
		private int score;

		public Builder() {
			LocalDateTime now = LocalDateTime.now();
			createDate = now;
			modifyDate = now;
		}

		public Builder comment(String comment) {
			this.comment = comment;
			return this;
		}

		public Builder productId(int id) {
			productId = id;
			return this;
		}

		public Builder reservationInfoId(int id) {
			reservationInfoId = id;
			return this;
		}

		public Builder score(int score) {
			this.score = score;
			return this;
		}

		public CommentParam build() {
			return new CommentParam(this);
		}

	}

	public CommentParam(Builder builder) {
		createDate = builder.createDate;
		modifyDate = builder.modifyDate;
		comment = builder.comment;
		productId = builder.productId;
		reservationInfoId = builder.reservationInfoId;
		score = builder.score;
	}

	public String getComment() {
		return comment;
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

}
