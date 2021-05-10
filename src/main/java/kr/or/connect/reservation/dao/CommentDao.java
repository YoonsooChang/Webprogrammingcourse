package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.sqls.CommentDaoSqls.SELECT_BY_DISPLAY_ID;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.Comment;
import kr.or.connect.reservation.mapper.CommentMapper;

@Repository
public class CommentDao {
	private NamedParameterJdbcTemplate jdbc;

	public CommentDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Comment> selectByDisplayInfoId(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);

		return jdbc.query(SELECT_BY_DISPLAY_ID, params, new CommentMapper());
	}

}
