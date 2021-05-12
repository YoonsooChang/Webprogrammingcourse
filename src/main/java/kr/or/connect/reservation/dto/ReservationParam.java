package kr.or.connect.reservation.dto;

import java.util.ArrayList;
import java.util.List;

public class ReservationParam {
	private String reservationName;
	private String reservationTelephone;
	private String reservationEmail;
	private List<ReservationPrice> prices;
	private int displayInfoId;
	private int productId;

	public static class Builder {
		private final int displayInfoId;
		private final int productId;

		private String reservationName = "";
		private String reservationTelephone = "";
		private String reservationEmail = "";
		private List<ReservationPrice> prices = new ArrayList<>();

		public Builder(int displayInfoId, int productId) {
			this.displayInfoId = displayInfoId;
			this.productId = productId;
		}

		public Builder reservationName(String name) {
			reservationName = name;
			return this;
		}

		public Builder reservationTelephone(String tel) {
			reservationTelephone = tel;
			return this;
		}

		public Builder reservationEmail(String email) {
			reservationEmail = email;
			return this;
		}

		public Builder prices(List<ReservationPrice> prices) {
			this.prices = prices;
			return this;
		}

		public ReservationParam build() {
			return new ReservationParam(this);
		}

	}

	public ReservationParam(Builder builder) {
		reservationName = builder.reservationName;
		reservationTelephone = builder.reservationTelephone;
		reservationEmail = builder.reservationEmail;
		prices = builder.prices;
		displayInfoId = builder.displayInfoId;
		productId = builder.productId;
	}

	public String getReservationName() {
		return reservationName;
	}

	public String getReservationTelephone() {
		return reservationTelephone;
	}

	public String getReservationEmail() {
		return reservationEmail;
	}

	public List<ReservationPrice> getPrices() {
		return prices;
	}

	public int getDisplayInfoId() {
		return displayInfoId;
	}

	public int getProductId() {
		return productId;
	}

	@Override
	public String toString() {
		return "ReservationParam [reservationName=" + reservationName + ", reservationTelephone=" + reservationTelephone
			+ ", reservationEmail=" + reservationEmail + ", prices=" + prices + ", displayInfoId=" + displayInfoId
			+ ", productId=" + productId + "]";
	}

}
