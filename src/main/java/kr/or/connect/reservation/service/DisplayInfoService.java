package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.Comment;
import kr.or.connect.reservation.dto.DisplayInfoResponse;

public interface DisplayInfoService {
	static final int COMMENT_LIMIT = 3;

	DisplayInfoResponse getDisplayInfoById(int displayInfoId);

	List<Comment> getCommentsById(int displayInfoId);
}
