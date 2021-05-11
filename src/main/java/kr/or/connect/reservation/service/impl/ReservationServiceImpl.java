package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.ReservationInfoDao;
import kr.or.connect.reservation.dto.ReservationInfo;
import kr.or.connect.reservation.dto.ReservationInfoResponse;
import kr.or.connect.reservation.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {
	private final ReservationInfoDao reservationInfoDao;

	public ReservationServiceImpl(ReservationInfoDao reservationInfoDao) {
		this.reservationInfoDao = reservationInfoDao;
	}

	@Override
	public ReservationInfoResponse getReservationInfo(String userEmail) {
		List<ReservationInfo> reservations = reservationInfoDao.getReservationInfosByEmail(userEmail);

		return new ReservationInfoResponse(reservations, reservations.size());
	}

	@Override
	public boolean cancelReservation(int reservationId, String userEmail) {
		return (reservationInfoDao.cancelReservation(reservationId, userEmail) == ReservationService.SUCCESS)
			? true
			: false;
	}

}
