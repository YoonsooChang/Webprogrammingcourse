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

		DisplayInfo displayInfo = displayInfoDao.selectById(displayInfoId);
		DisplayInfoImage displayInfoImage = displayInfoDao.selectImageById(displayInfoId);

		List<Comment> comments = commentDao.selectByDisplayInfoIdLimit(displayInfoId,
			DisplayInfoService.COMMENT_LIMIT);

		int productId = displayInfo.getProductId();

		List<ProductImage> productImages = productDao.selectProductImagesById(productId);
		List<ProductPrice> productPrices = productDao.selectProductPricesById(productId);

		DisplayInfoResponse displayInfoResponse = new DisplayInfoResponse.Builder(displayInfo, productImages,
			productPrices)
				.commentList(comments)
				.displayInfoImage(displayInfoImage)
				.build();

		return displayInfoResponse;
	}

	@Override
	public List<Comment> getCommentsById(int displayInfoId) {
		List<Comment> comments = commentDao.selectByDisplayInfoId(displayInfoId);

		return comments;
	}

	@Override
	public DisplayInfoResponse getDisplayInfoReservationById(int displayInfoId) {

		DisplayInfo displayInfo = displayInfoDao.selectById(displayInfoId);

		int productId = displayInfo.getProductId();

		List<ProductImage> productImages = productDao.selectProductImagesById(productId);
		List<ProductPrice> productPrices = productDao.selectProductPricesById(productId);

		DisplayInfoResponse displayInfoResponse = new DisplayInfoResponse.Builder(displayInfo, productImages,
			productPrices).build();

		return displayInfoResponse;
	}

}
