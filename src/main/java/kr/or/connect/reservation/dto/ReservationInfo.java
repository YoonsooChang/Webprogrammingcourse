package kr.or.connect.reservation.dto;

import java.time.LocalDate;

public class ReservationInfo {
	private boolean cancelYn;
	private LocalDate createDate;
	private DisplayInfo displayInfo;
	private int displayInfoId;
	private LocalDate modifyDate;
	private int productId;
	private LocalDate reservationDate;
	private String reservationEmail;
	private int reservationInfoId;
	private String reservationName;
	private String reservationTelephone;
	private int totalPrice;

	public static class Builder {
		private final int displayInfoId;
		private final DisplayInfo displayInfo;
		private final int productId;
		private final int reservationInfoId;

		private boolean cancelYn = false;
		private LocalDate createDate = null;
		private LocalDate modifyDate = null;
		private LocalDate reservationDate = null;
		private String reservationEmail = "";
		private String reservationName = "";
		private String reservationTelephone = "";
		private int totalPrice = 0;

		public Builder(int displayInfoId, DisplayInfo displayInfo, int productId, int reservationInfoId) {
			this.displayInfoId = displayInfoId;
			this.displayInfo = displayInfo;
			this.productId = productId;
			this.reservationInfoId = reservationInfoId;
		}

		public Builder cancelYn(boolean val) {
			cancelYn = val;
			return this;
		}

		public Builder createDate(LocalDate date) {
			createDate = date;
			return this;
		}

		public Builder modifyDate(LocalDate date) {
			modifyDate = date;
			return this;
		}

		public Builder reservationDate(LocalDate date) {
			reservationDate = date;
			return this;
		}

		public Builder reservationEmail(String email) {
			reservationEmail = email;
			return this;
		}

		public Builder reservationName(String name) {
			reservationName = name;
			return this;
		}

		public Builder reservationTelephone(String tel) {
			reservationTelephone = tel;
			return this;
		}

		public Builder totalPrice(int price) {
			totalPrice = price;
			return this;
		}

		public ReservationInfo build() {
			return new ReservationInfo(this);
		}

	}

	public ReservationInfo(Builder builder) {
		cancelYn = builder.cancelYn;
		createDate = builder.createDate;
		displayInfo = builder.displayInfo;
		displayInfoId = builder.displayInfoId;
		modifyDate = builder.modifyDate;
		productId = builder.productId;
		reservationDate = builder.reservationDate;
		reservationEmail = builder.reservationEmail;
		reservationInfoId = builder.reservationInfoId;
		reservationName = builder.reservationName;
		reservationTelephone = builder.reservationTelephone;
		totalPrice = builder.totalPrice;
	}

	@Override
	public String toString() {
		return "ReservationInfo [cancelYn=" + cancelYn + ", createDate=" + createDate + ", displayInfo=" + displayInfo
			+ ", displayInfoId=" + displayInfoId + ", modifyDate=" + modifyDate + ", productId=" + productId
			+ ", reservationDate=" + reservationDate + ", reservationEmail=" + reservationEmail + ", reservationInfoId="
			+ reservationInfoId + ", reservationName=" + reservationName + ", reservationTelephone="
			+ reservationTelephone + ", totalPrice=" + totalPrice + "]";
	}

}
