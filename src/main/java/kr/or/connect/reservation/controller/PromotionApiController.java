package kr.or.connect.reservation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.PromotionResponse;
import kr.or.connect.reservation.service.PromotionService;

@RestController
@RequestMapping(path = "/api/promotion")
public class PromotionApiController {
	private final PromotionService promotionService;

	public PromotionApiController(PromotionService promotionService) {
		this.promotionService = promotionService;
	}

	@GetMapping
	public PromotionResponse getPromotionResponse() {
		return promotionService.getPromotions();
	}
}