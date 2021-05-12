package kr.or.connect.reservation.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.ReservationInfoDao;
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
		List<ReservationInfo> reservations = reservationInfoDao.getReservationInfosByEmail(userEmail);
		return new ReservationInfoResponse(reservations, reservations.size());
	}

	@Override
	public String cancelReservation(int reservationId, String userEmail) {
		return (reservationInfoDao.cancelReservation(reservationId, userEmail) == ReservationService.SUCCESS)
			? "success"
			: "failure";
	}

	@Override
	public String addReservation(Map<String, Object> params) {

		List<String> countIds = Arrays.asList(((String)params.get("counts")).split(","));
		List<String> priceIds = Arrays.asList(((String)params.get("priceIds")).split(","));

		List<ReservationPrice> prices = IntStream.iterate(0, i -> i + 1)
			.limit(countIds.size())
			.mapToObj(i -> new ReservationPrice(
				Integer.parseInt(countIds.get(i)),
				Integer.parseInt(priceIds.get(i))))
			.collect(Collectors.toList());

		ReservationParam reservationParam = new ReservationParam.Builder(
			Integer.parseInt((String)params.get("displayInfoId")),
			Integer.parseInt((String)params.get("productId")))
				.reservationEmail((String)params.get("email"))
				.reservationName((String)params.get("name"))
				.reservationTelephone((String)params.get("tel"))
				.prices(prices)
				.build();

		int addResult = reservationInfoDao.addReservation(reservationParam);
		return (addResult == prices.size() + 1)
			? "success"
			: "failure";
	}

}
