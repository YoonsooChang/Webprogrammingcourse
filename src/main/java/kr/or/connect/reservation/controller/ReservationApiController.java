package kr.or.connect.reservation.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping("/cancel/{id}")
	public String cancelReservation(@PathVariable(name = "id")
	int reservationId, HttpSession session, HttpServletResponse response) {
		return reservationService.cancelReservation(reservationId,
			session.getAttribute("user").toString());
	}

	@PostMapping("/add")
	public void addReservation(@RequestParam
	Map<String, Object> reservationParameters, HttpServletResponse response) throws IOException {
		if ("success".equals(reservationService.addReservation(reservationParameters))) {
			response.sendRedirect("/reservation/myreservation");
		} else {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

}
