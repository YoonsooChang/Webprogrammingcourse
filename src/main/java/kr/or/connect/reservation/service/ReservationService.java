package kr.or.connect.reservation.service;

import kr.or.connect.reservation.dto.ReservationInfoResponse;

public interface ReservationService {
	static final int SUCCESS = 1;

	ReservationInfoResponse getReservationInfo(String userEmail);

	boolean cancelReservation(int reservationId, String userEmail);
}
