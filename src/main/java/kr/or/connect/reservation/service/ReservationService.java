package kr.or.connect.reservation.service;

import kr.or.connect.reservation.dto.ReservationAddStatus;
import kr.or.connect.reservation.dto.ReservationCancelStatus;
import kr.or.connect.reservation.dto.ReservationInfoResponse;
import kr.or.connect.reservation.dto.ReservationParam;

public interface ReservationService {
	ReservationInfoResponse getReservationInfo(String userEmail);

	ReservationCancelStatus cancelReservation(int reservationId, String userEmail);

	ReservationAddStatus addReservation(ReservationParam reservationParam);
}
