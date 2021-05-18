package kr.or.connect.reservation.service;

import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.reservation.dto.CommentAddStatus;
import kr.or.connect.reservation.dto.CommentParam;
import kr.or.connect.reservation.dto.FileParam;
import kr.or.connect.reservation.dto.ReservationAddStatus;
import kr.or.connect.reservation.dto.ReservationCancelStatus;
import kr.or.connect.reservation.dto.ReservationInfoResponse;
import kr.or.connect.reservation.dto.ReservationParam;

public interface ReservationService {
	ReservationInfoResponse getReservationInfo(String userEmail);

	ReservationCancelStatus cancelReservation(int reservationId, String userEmail);

	ReservationAddStatus addReservation(ReservationParam reservationParam);

	CommentAddStatus addComment(CommentParam commentParam, MultipartFile imageFile);

	FileParam getFileByImageId(int imageId);
}
