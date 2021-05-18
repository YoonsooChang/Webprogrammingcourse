package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.sqls.CommentDaoSqls.INSERT;
import static kr.or.connect.reservation.dao.sqls.CommentDaoSqls.INSERT_COMMENT_AND_FILE_IDS;
import static kr.or.connect.reservation.dao.sqls.CommentDaoSqls.INSERT_IMAGE_FILE;
import static kr.or.connect.reservation.dao.sqls.CommentDaoSqls.SELECT_BY_DISPLAY_ID;
import static kr.or.connect.reservation.dao.sqls.CommentDaoSqls.SELECT_FILE_INFO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.Comment;
import kr.or.connect.reservation.dto.CommentParam;
import kr.or.connect.reservation.dto.FileParam;
import kr.or.connect.reservation.mapper.CommentMapper;

@Repository
public class CommentDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<FileParam> fileMapper = BeanPropertyRowMapper.newInstance(FileParam.class);

	public CommentDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Comment> selectByDisplayInfoId(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);

		return jdbc.query(SELECT_BY_DISPLAY_ID, params, new CommentMapper());
	}

	public int insert(CommentParam commentParam) {
		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(commentParam);
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbc.update(INSERT, namedParameters, keyHolder, new String[] {"id"});
		Number keyValue = keyHolder.getKey();

		return keyValue.intValue();
	}

	public int insertImageFile(FileParam fileParam) {
		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(fileParam);
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbc.update(INSERT_IMAGE_FILE, namedParameters, keyHolder, new String[] {"id"});
		Number keyValue = keyHolder.getKey();

		return keyValue.intValue();
	}

	public int insertCommentAndFileIdIntoJuncTbl(int commentId, int imageFileId, int reservationInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("reservationId", reservationInfoId);
		params.put("commentId", commentId);
		params.put("fileId", imageFileId);

		SqlParameterSource parameters = new MapSqlParameterSource(params);
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbc.update(INSERT_COMMENT_AND_FILE_IDS, parameters, keyHolder, new String[] {"id"});
		Number keyValue = keyHolder.getKey();

		return keyValue.intValue();
	}

	public FileParam selectCommentFileInfo(int imageId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("imageId", imageId);

		return jdbc.queryForObject(SELECT_FILE_INFO, params, fileMapper);
	}

}
