package kr.or.connect.reservation.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.ReservationInfoResponse;
import kr.or.connect.reservation.service.ReservationService;

@RestController
@RequestMapping(path = "/api/reservation")
public class ReservationApiController {
	private final ReservationService reservationService;

	public ReservationApiController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@GetMapping("/")
	public ReservationInfoResponse getReservationInfoResponse(HttpSession session) {
		return reservationService.getReservationInfo(session.getAttribute("user").toString());
	}

	@PatchMapping("/cancel/{id}")
	public String cancelReservation(@PathVariable(name = "id")
	int reservationId, HttpSession session, HttpServletResponse response) {
		boolean cancelResult = reservationService.cancelReservation(reservationId,
			session.getAttribute("user").toString());
		if (!cancelResult) {
			return "failure";
		}
		return "success";
	}

}
