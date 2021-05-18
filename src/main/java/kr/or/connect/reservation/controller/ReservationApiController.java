package kr.or.connect.reservation.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.reservation.dto.CommentAddStatus;
import kr.or.connect.reservation.dto.CommentParam;
import kr.or.connect.reservation.dto.ReservationAddStatus;
import kr.or.connect.reservation.dto.ReservationCancelStatus;
import kr.or.connect.reservation.dto.ReservationInfoResponse;
import kr.or.connect.reservation.dto.ReservationParam;
import kr.or.connect.reservation.service.ReservationService;

@RestController
@RequestMapping(path = "/api/reservation")
public class ReservationApiController {
	private final ReservationService reservationService;

	public ReservationApiController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@GetMapping
	public ReservationInfoResponse getReservationInfoResponse(
		@SessionAttribute(name = "user", required = false)
		String userEmail,
		HttpServletResponse response) throws IOException {
		if (userEmail == null) {
			response.sendError(400, "unconnected");
		}
		return reservationService.getReservationInfo(userEmail);
	}

	@PutMapping("/{id}")
	public String cancelReservation(@PathVariable(name = "id")
	int reservationId, @SessionAttribute("user")
	String userEmail) {
		ReservationCancelStatus cancelStatus = reservationService.cancelReservation(reservationId, userEmail);

		return cancelStatus.getMessage();
	}

	@PostMapping
	public String addReservation(@RequestBody
	ReservationParam reservationParams) {
		ReservationAddStatus addStatus = reservationService.addReservation(reservationParams);
		return addStatus.getMessage();
	}

	@PostMapping("/{id}/comments")
	public String addComment(HttpServletRequest request, HttpServletResponse response,
		@RequestParam("comment")
		String comment,
		@RequestParam("productId")
		int productId,
		@RequestParam("score")
		int score,
		@RequestParam(name = "attachedImage", required = false)
		MultipartFile imageFile,
		@PathVariable(name = "id")
		int reservationId) throws UnsupportedEncodingException {

		request.setCharacterEncoding("UTF-8");

		CommentParam commentParam = new CommentParam.Builder()
			.comment(comment)
			.reservationInfoId(reservationId)
			.productId(productId)
			.score(score)
			.build();

		CommentAddStatus addResult = reservationService.addComment(commentParam, imageFile);
		return addResult.getMessage();
	}

}
