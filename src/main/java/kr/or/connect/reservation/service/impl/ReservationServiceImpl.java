package kr.or.connect.reservation.service.impl;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.reservation.dao.CommentDao;
import kr.or.connect.reservation.dao.ReservationInfoDao;
import kr.or.connect.reservation.dto.CommentAddStatus;
import kr.or.connect.reservation.dto.CommentParam;
import kr.or.connect.reservation.dto.FileParam;
import kr.or.connect.reservation.dto.ReservationAddStatus;
import kr.or.connect.reservation.dto.ReservationCancelStatus;
import kr.or.connect.reservation.dto.ReservationInfo;
import kr.or.connect.reservation.dto.ReservationInfoResponse;
import kr.or.connect.reservation.dto.ReservationParam;
import kr.or.connect.reservation.dto.ReservationPrice;
import kr.or.connect.reservation.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {
	private final ReservationInfoDao reservationInfoDao;
	private final CommentDao commentDao;

	public ReservationServiceImpl(ReservationInfoDao reservationInfoDao, CommentDao commentDao) {
		this.reservationInfoDao = reservationInfoDao;
		this.commentDao = commentDao;
	}

	@Override
	public ReservationInfoResponse getReservationInfo(String userEmail) {
		List<ReservationInfo> reservations = reservationInfoDao.selectByEmail(userEmail);
		return new ReservationInfoResponse(reservations);
	}

	@Override
	public ReservationCancelStatus cancelReservation(int reservationId, String userEmail) {
		int cancelResult = reservationInfoDao.updateCancelStateTrue(reservationId, userEmail);

		if (cancelResult == ReservationCancelStatus.SUCCESS.getRowNum()) {
			return ReservationCancelStatus.SUCCESS;
		} else {
			return ReservationCancelStatus.FAILURE;
		}
	}

	@Override
	@Transactional
	public ReservationAddStatus addReservation(ReservationParam reservationParam) {
		int reservationId = reservationInfoDao.insert(reservationParam);
		if (reservationId == 0) {
			return ReservationAddStatus.INFO_FAILURE;
		}

		List<ReservationPrice> prices = new ArrayList<>();

		List<Integer> counts = reservationParam.getCounts();
		List<Integer> productPriceIds = reservationParam.getProductPriceIds();

		for (int i = 0; i < counts.size(); i++) {
			prices.add(new ReservationPrice(counts.get(i), productPriceIds.get(i), reservationId));
		}

		int priceInsertResult = reservationInfoDao.insertReservationPrices(prices);
		return (priceInsertResult == counts.size())
			? ReservationAddStatus.SUCCESS
			: ReservationAddStatus.PRICE_FAILURE;
	}

	@Override
	@Transactional
	public CommentAddStatus addComment(CommentParam commentParam, MultipartFile imageFile) {
		final int NOT_DELETED = 0;

		int commentId = commentDao.insert(commentParam);
		if (commentId == 0) {
			return CommentAddStatus.COMMENT_FAILURE;
		}

		if (imageFile == null) {
			return CommentAddStatus.SUCCESS;
		}

		String fileName = imageFile.getOriginalFilename();
		String saveFileName = "img/" + fileName;
		String absoluteFilePath = "C:/Temp/" + saveFileName;
		String contentType = imageFile.getContentType();

		FileParam fileParam = new FileParam.Builder()
			.fileName(fileName)
			.saveFileName(saveFileName)
			.contentType(contentType)
			.deleteFlag(NOT_DELETED)
			.createDate(commentParam.getCreateDate())
			.modifyDate(commentParam.getModifyDate())
			.build();

		int fileId = commentDao.insertImageFile(fileParam);
		if (fileId == 0) {
			return CommentAddStatus.FILE_FAILURE;
		}

		int junctionImageId = commentDao.insertCommentAndFileIdIntoJuncTbl(commentId, fileId,
			commentParam.getReservationInfoId());
		if (junctionImageId == 0) {
			return CommentAddStatus.JUNCTION_FAILURE;
		}

		try (
			FileOutputStream fos = new FileOutputStream(absoluteFilePath);
			InputStream is = imageFile.getInputStream();) {
			int readCount = 0;
			byte[] buffer = new byte[1024];
			while ((readCount = is.read(buffer)) != -1) {
				fos.write(buffer, 0, readCount);
			}
		} catch (Exception ex) {
			throw new RuntimeException("file Save Error", ex);
		}

		return CommentAddStatus.SUCCESS;
	}

	@Override
	public FileParam getFileByImageId(int imageId) {
		return commentDao.selectCommentFileInfo(imageId);
	}

}
