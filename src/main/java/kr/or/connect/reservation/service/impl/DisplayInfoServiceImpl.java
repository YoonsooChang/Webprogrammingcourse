package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.CommentDao;
import kr.or.connect.reservation.dao.DisplayInfoDao;
import kr.or.connect.reservation.dao.ProductDao;
import kr.or.connect.reservation.dto.Comment;
import kr.or.connect.reservation.dto.CommentResponse;
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

		List<Comment> comments = commentDao.selectByDisplayInfoId(displayInfoId);
		final int commentCountToSend = Math.min(comments.size(), DisplayInfoService.COMMENT_LIMIT_IN_DETAIL_PAGE);

		DisplayInfo displayInfo = displayInfoDao.selectById(displayInfoId);
		DisplayInfoImage displayInfoImage = displayInfoDao.selectImageById(displayInfoId);

		int productId = displayInfo.getProductId();

		List<ProductImage> productImages = productDao.selectProductImagesById(productId);
		List<ProductPrice> productPrices = productDao.selectProductPricesById(productId);

		DisplayInfoResponse displayInfoResponse = new DisplayInfoResponse(
			comments.size(), computeAverageScore(comments),
			comments.subList(0, commentCountToSend),
			displayInfo, displayInfoImage,
			productImages, productPrices);

		return displayInfoResponse;

	}

	@Override
	public CommentResponse getCommentsById(int displayInfoId) {
		List<Comment> comments = commentDao.selectByDisplayInfoId(displayInfoId);
		return new CommentResponse(computeAverageScore(comments), comments);
	}

	public double computeAverageScore(List<Comment> comments) {
		return comments.stream().mapToDouble(Comment::getScore)
			.average()
			.orElse(DisplayInfoService.EMPTY_SCORE);
	}

}
