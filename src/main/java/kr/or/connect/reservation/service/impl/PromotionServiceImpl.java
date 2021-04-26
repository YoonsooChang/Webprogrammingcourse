package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.PromotionDao;
import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.dto.PromotionResponse;
import kr.or.connect.reservation.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService {
	private final PromotionDao promotionDao;

	public PromotionServiceImpl(PromotionDao promotionDao) {
		this.promotionDao = promotionDao;
	}

	@Override
	public PromotionResponse getPromotions() {
		List<Promotion> list = promotionDao.selectAll();
		PromotionResponse promotionResponse = new PromotionResponse(list);

		return promotionResponse;
	}

}
