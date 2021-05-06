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
		return new Comment(
			rs.getString("comment"),
			rs.getInt("comment_id"),
			rs.getDate("create_date").toLocalDate(),
			rs.getDate("modify_date").toLocalDate(),
			rs.getInt("product_id"),
			rs.getDate("reservation_date").toLocalDate(),
			rs.getString("reservation_email"),
			rs.getInt("reservation_info_id"),
			rs.getString("reservation_name"),
			rs.getString("reservation_telephone"),
			rs.getDouble("score"));
	}

	private CommentImage extractCommentImage(ResultSet rs) throws SQLException {
		return new CommentImage(
			rs.getString("content_type"),
			rs.getDate("file_create_date").toLocalDate(),
			rs.getBoolean("delete_flag"),
			rs.getInt("file_id"),
			rs.getString("file_name"),
			rs.getInt("image_id"),
			rs.getDate("file_modify_date").toLocalDate(),
			rs.getInt("image_reservation_info_id"),
			rs.getInt("reservation_comment_id"),
			rs.getString("save_file_name"));
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
				currentCommentInstance.getCommentImages().add(extractCommentImage(rs));
			}

			if (rs.isLast()) {
				commentList.add(currentCommentInstance);
			}
		}
		return commentList;

	}
}
