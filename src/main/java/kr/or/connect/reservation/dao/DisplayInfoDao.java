package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.sqls.DisplayInfoDaoSqls.SELECT_BY_ID;
import static kr.or.connect.reservation.dao.sqls.DisplayInfoDaoSqls.SELECT_IMAGE_BY_ID;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.DisplayInfo;
import kr.or.connect.reservation.dto.DisplayInfoImage;

@Repository
public class DisplayInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<DisplayInfo> infoMapper = BeanPropertyRowMapper.newInstance(DisplayInfo.class);
	private RowMapper<DisplayInfoImage> imageMapper = BeanPropertyRowMapper.newInstance(DisplayInfoImage.class);

	public DisplayInfoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public DisplayInfo selectById(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("id", displayInfoId);

		return jdbc.queryForObject(SELECT_BY_ID, params, infoMapper);
	}

	public DisplayInfoImage selectImageById(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("id", displayInfoId);

		return jdbc.queryForObject(SELECT_IMAGE_BY_ID, params, imageMapper);
	}
}
