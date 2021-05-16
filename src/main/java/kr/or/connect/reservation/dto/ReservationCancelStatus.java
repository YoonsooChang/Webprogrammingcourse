package kr.or.connect.reservation.dto;

public enum ReservationCancelStatus {
	SUCCESS(1, "success"), FAILURE(0, "failure");

	private final int rowNum;
	private final String message;

	ReservationCancelStatus(int rowNum, String message) {
		this.rowNum = rowNum;
		this.message = message;
	}

	public int getRowNum() {
		return rowNum;
	}

	public String getMessage() {
		return message;
	}

}
