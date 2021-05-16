package kr.or.connect.reservation.dto;

public enum ReservationAddStatus {
	SUCCESS("success"), INFO_FAILURE("failure"), PRICE_FAILURE("failure");

	private final String message;

	ReservationAddStatus(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
