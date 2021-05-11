package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.sqls.ReservationInfoDaoSqls.SELECT_BY_USER_EMAIL;
import static kr.or.connect.reservation.dao.sqls.ReservationInfoDaoSqls.UPDATE_CANCEL_STATE_TRUE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.ReservationInfo;
import kr.or.connect.reservation.mapper.ReservationInfoMapper;

@Repository
public class ReservationInfoDao {
	private NamedParameterJdbcTemplate jdbc;

	public ReservationInfoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<ReservationInfo> getReservationInfosByEmail(String userEmail) {
		Map<String, String> params = new HashMap<>();
		params.put("user", userEmail);

		return jdbc.query(SELECT_BY_USER_EMAIL, params, new ReservationInfoMapper());
	}

	public int cancelReservation(int reservationId, String userEmail) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", reservationId);
		params.put("user", userEmail);

		return jdbc.update(UPDATE_CANCEL_STATE_TRUE, params);
	}
}