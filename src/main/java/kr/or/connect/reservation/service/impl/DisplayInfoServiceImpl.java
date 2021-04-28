package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.CommentDao;
import kr.or.connect.reservation.dao.DisplayInfoDao;
import kr.or.connect.reservation.dao.ProductDao;
import kr.or.connect.reservation.dto.Comment;
import kr.or.connect.reservation.dto.DisplayInfo;
import kr.or.connect.reservation.dto.DisplayInfoImage;
import kr.or.connect.reservation.dto.DisplayInfoResponse;
import kr.or.connect.reservation.dto.ProductImage;
import kr.or.connect.reservation.dto.ProductPrice;
import kr.or.connect.reservation.service.DisplayInfoService;

@Service
public class DisplayInfoServiceImpl implements DisplayInfoService {
	private final DisplayInfoDao displayInfoDao;
	private final ProductDao productDao;
	private final CommentDao commentDao;

	public DisplayInfoServiceImpl(DisplayInfoDao displayInfoDao, ProductDao productDao, CommentDao commentDao) {
		this.displayInfoDao = displayInfoDao;
		this.productDao = productDao;
		this.commentDao = commentDao;
	}

	@Override
	@Transactional
	public DisplayInfoResponse getDisplayInfoById(int displayInfoId) {
		int productId = displayInfoDao.selectProductId(displayInfoId);

		List<Comment> comments = commentDao.selectByDisplayInfoIdAndProductId(productId, displayInfoId,
			DisplayInfoService.COMMENT_LIMIT);

		DisplayInfo displayInfo = displayInfoDao.selectById(displayInfoId);
		DisplayInfoImage displayInfoImage = displayInfoDao.selectImageById(displayInfoId);

		List<ProductImage> productImages = productDao.selectProductImagesById(productId);
		List<ProductPrice> productPrices = productDao.selectProductPricesById(productId);

		double averageScore = comments.stream().mapToDouble(Comment::getScore).average().orElse(0.0);

		DisplayInfoResponse displayInfoResponse = new DisplayInfoResponse(averageScore, comments, displayInfo,
			displayInfoImage, productImages, productPrices);

		return displayInfoResponse;
	}

}
