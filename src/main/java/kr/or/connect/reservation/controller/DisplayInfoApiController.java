package kr.or.connect.reservation.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.Comment;
import kr.or.connect.reservation.dto.DisplayInfoResponse;
import kr.or.connect.reservation.service.DisplayInfoService;

@RestController
@RequestMapping(path = "/api/display")
public class DisplayInfoApiController {
	private final DisplayInfoService displayInfoService;

	public DisplayInfoApiController(DisplayInfoService displayInfoService) {
		this.displayInfoService = displayInfoService;
	}

	@GetMapping("/{id}")
	public DisplayInfoResponse getDisplayInfoResponseById(@PathVariable(name = "id")
	int displayInfoId) {
		return displayInfoService.getDisplayInfoById(displayInfoId);
	}

	@GetMapping("/comment/{id}")
	public List<Comment> getCommentsById(@PathVariable(name = "id")
	int displayInfoId) {
		return displayInfoService.getCommentsById(displayInfoId);
	}

	@GetMapping("/reservation/{id}")
	public DisplayInfoResponse getDisplayInfoReservationResponseById(@PathVariable(name = "id")
	int displayInfoId) {
		return displayInfoService.getDisplayInfoReservationById(displayInfoId);
	}
}
