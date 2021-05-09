package kr.or.connect.reservation.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import kr.or.connect.reservation.dto.Comment;
import kr.or.connect.reservation.dto.CommentImage;

public class CommentMapper implements ResultSetExtractor<List<Comment>> {
	private final int NONE_ASSIGNED = 0;

	private Comment extractComment(ResultSet rs) throws SQLException {
		return new Comment.Builder(
			rs.getInt("comment_id"),
			rs.getInt("product_id"),
			rs.getInt("reservation_info_id"))
				.comment(rs.getString("comment"))
				.createDate(rs.getDate("create_date").toLocalDate())
				.modifyDate(rs.getDate("modify_date").toLocalDate())
				.reservationDate(rs.getDate("reservation_date").toLocalDate())
				.reservationEmail(rs.getString("reservation_email"))
				.reservationName(rs.getString("reservation_name"))
				.reservationTelephone(rs.getString("reservation_telephone"))
				.score(rs.getDouble("score"))
				.build();
	}

	private CommentImage extractCommentImage(ResultSet rs) throws SQLException {
		return new CommentImage.Builder(rs.getInt("image_id"), rs.getInt("file_id"))
			.contentType(rs.getString("content_type"))
			.createDate(rs.getDate("file_create_date").toLocalDate())
			.modifyDate(rs.getDate("file_modify_date").toLocalDate())
			.fileName(rs.getString("file_name"))
			.deleteFlag(rs.getBoolean("delete_flag"))
			.reservationInfoId(rs.getInt("image_reservation_info_id"))
			.reservationUserCommentId(rs.getInt("reservation_comment_id"))
			.saveFileName(rs.getString("save_file_name"))
			.build();
	}

	@Override
	public List<Comment> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<Comment> commentList = new ArrayList<Comment>();

		int currentCommentId = NONE_ASSIGNED;
		Comment currentCommentInstance = null;

		while (rs.next()) {
			if (rs.getInt("comment_id") != currentCommentId) {
				if (currentCommentInstance != null) {
					commentList.add(currentCommentInstance);
				}
				currentCommentInstance = extractComment(rs);
			}

			int imageId = rs.getInt("image_id");
			if (imageId != 0) {
				currentCommentInstance.addImage(extractCommentImage(rs));
			}

			if (rs.isLast()) {
				commentList.add(currentCommentInstance);
			}
		}
		return commentList;

	}
}
