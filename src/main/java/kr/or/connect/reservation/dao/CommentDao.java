package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.sqls.CommentDaoSqls.SELECT_BY_DISPLAY_ID_AND_PRODUCT_ID_EXCEPT_IMAGE;
import static kr.or.connect.reservation.dao.sqls.CommentDaoSqls.SELECT_IMAGES_BY_COMMENT_ID;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.Comment;
import kr.or.connect.reservation.dto.CommentImage;

@Repository
public class CommentDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Comment> itemMapper = BeanPropertyRowMapper.newInstance(Comment.class);
	private RowMapper<CommentImage> imageMapper = BeanPropertyRowMapper.newInstance(CommentImage.class);

	public CommentDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Comment> selectByDisplayInfoIdAndProductId(int productId, int displayInfoId, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("productId", productId);
		params.put("displayInfoId", displayInfoId);
		params.put("limit", limit);

		List<Comment> comments = jdbc.query(SELECT_BY_DISPLAY_ID_AND_PRODUCT_ID_EXCEPT_IMAGE, params, itemMapper);

		comments.forEach(comment -> {
			Map<String, Integer> paramsForImage = Map.of(
				"reservationInfoId", comment.getReservationInfoId(),
				"commentId", comment.getCommentId());
			List<CommentImage> commentImages = jdbc.query(SELECT_IMAGES_BY_COMMENT_ID, paramsForImage, imageMapper);
			comment.setCommentImages(commentImages);
		});

		return comments;
	}
}
