package kr.or.connect.reservation.service;

import kr.or.connect.reservation.dto.CommentResponse;
import kr.or.connect.reservation.dto.DisplayInfoResponse;

public interface DisplayInfoService {
	static final int COMMENT_LIMIT_IN_DETAIL_PAGE = 3;
	static final double EMPTY_SCORE = 0.0;

	DisplayInfoResponse getDisplayInfoById(int displayInfoId);

	CommentResponse getCommentsById(int displayInfoId);
}
