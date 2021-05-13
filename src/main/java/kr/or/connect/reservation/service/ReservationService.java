package kr.or.connect.reservation.service;

import java.util.Map;

import kr.or.connect.reservation.dto.ReservationInfoResponse;

public interface ReservationService {
	static final int SUCCESS = 1;

	ReservationInfoResponse getReservationInfo(String userEmail);

	String cancelReservation(int reservationId, String userEmail);

	String addReservation(Map<String, Object> params);
}
