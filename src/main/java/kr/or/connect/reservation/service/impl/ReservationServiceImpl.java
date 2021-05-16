package kr.or.connect.reservation.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.ReservationInfoDao;
import kr.or.connect.reservation.dto.ReservationAddStatus;
import kr.or.connect.reservation.dto.ReservationCancelStatus;
import kr.or.connect.reservation.dto.ReservationInfo;
import kr.or.connect.reservation.dto.ReservationInfoResponse;
import kr.or.connect.reservation.dto.ReservationParam;
import kr.or.connect.reservation.dto.ReservationPrice;
import kr.or.connect.reservation.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {
	private final ReservationInfoDao reservationInfoDao;

	public ReservationServiceImpl(ReservationInfoDao reservationInfoDao) {
		this.reservationInfoDao = reservationInfoDao;
	}

	@Override
	public ReservationInfoResponse getReservationInfo(String userEmail) {
		List<ReservationInfo> reservations = reservationInfoDao.selectByEmail(userEmail);
		return new ReservationInfoResponse(reservations);
	}

	@Override
	public ReservationCancelStatus cancelReservation(int reservationId, String userEmail) {
		int cancelResult = reservationInfoDao.updateCancelStateTrue(reservationId, userEmail);

		if (cancelResult == ReservationCancelStatus.SUCCESS.getRowNum()) {
			return ReservationCancelStatus.SUCCESS;
		} else {
			return ReservationCancelStatus.FAILURE;
		}
	}

	@Override
	@Transactional
	public ReservationAddStatus addReservation(ReservationParam reservationParam) {
		int reservationId = reservationInfoDao.insert(reservationParam);
		if (reservationId == 0) {
			return ReservationAddStatus.INFO_FAILURE;
		}

		List<ReservationPrice> prices = new ArrayList<>();

		List<Integer> counts = reservationParam.getCounts();
		List<Integer> productPriceIds = reservationParam.getProductPriceIds();

		for (int i = 0; i < counts.size(); i++) {
			prices.add(new ReservationPrice(counts.get(i), productPriceIds.get(i), reservationId));
		}

		int priceInsertResult = reservationInfoDao.insertReservationPrices(prices);
		return (priceInsertResult == counts.size())
			? ReservationAddStatus.SUCCESS
			: ReservationAddStatus.PRICE_FAILURE;
	}

}
