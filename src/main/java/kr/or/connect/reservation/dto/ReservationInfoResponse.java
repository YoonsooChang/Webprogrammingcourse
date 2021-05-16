package kr.or.connect.reservation.dto;

import java.util.List;

public class ReservationInfoResponse {
	private List<ReservationInfo> reservations;

	public ReservationInfoResponse() {
		super();
	}

	public ReservationInfoResponse(List<ReservationInfo> reservations) {
		super();
		this.reservations = reservations;

	}

	public List<ReservationInfo> getReservations() {
		return reservations;
	}

	@Override
	public String toString() {
		return "ReservationInfoResponse [reservations=" + reservations + "]";
	}

}
