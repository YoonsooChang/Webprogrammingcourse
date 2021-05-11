package kr.or.connect.reservation.dto;

import java.util.List;

public class ReservationInfoResponse {
	private List<ReservationInfo> reservations;
	private int size;

	public ReservationInfoResponse(List<ReservationInfo> reservations, int size) {
		super();
		this.reservations = reservations;
		this.size = size;
	}

	public List<ReservationInfo> getReservations() {
		return reservations;
	}

	public int getSize() {
		return size;
	}

	@Override
	public String toString() {
		return "ReservationInfoResponse [size=" + size + "]";
	}

}
