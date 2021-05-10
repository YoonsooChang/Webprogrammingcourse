package kr.or.connect.reservation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.ReservationInfoResponse;

@RestController
@RequestMapping(path = "/api/reservation")
public class ReservationApiController {
	private final ReservationService reservationService;

	public ReservationApiController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@GetMapping("/")
	public ReservationInfoResponse getReservationInfoResponse() {
		return reservationService.getReservationInfo();
	}

}
