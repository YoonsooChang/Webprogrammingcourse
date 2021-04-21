package kr.or.connect.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.PromotionResponse;
import kr.or.connect.reservation.service.PromotionService;

@RestController
@RequestMapping(path = "/api/promotion")
public class PromotionApiController {
	@Autowired
	private PromotionService promotionService;

	@GetMapping
	public PromotionResponse list() {
		PromotionResponse promotionList = promotionService.getPromotions();
		return promotionList;
	}
}