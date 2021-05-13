package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.sqls.ReservationInfoDaoSqls.INSERT;
import static kr.or.connect.reservation.dao.sqls.ReservationInfoDaoSqls.INSERT_PRICE;
import static kr.or.connect.reservation.dao.sqls.ReservationInfoDaoSqls.SELECT_BY_USER_EMAIL;
import static kr.or.connect.reservation.dao.sqls.ReservationInfoDaoSqls.UPDATE_CANCEL_STATE_TRUE;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.ReservationInfo;
import kr.or.connect.reservation.dto.ReservationParam;
import kr.or.connect.reservation.dto.ReservationPrice;
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

	public int addReservation(ReservationParam reservationParams) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("displayInfoId", reservationParams.getDisplayInfoId());
		params.addValue("productId", reservationParams.getProductId());
		params.addValue("name", reservationParams.getReservationName());
		params.addValue("email", reservationParams.getReservationEmail());
		params.addValue("tel", reservationParams.getReservationTelephone());
		params.addValue("reservation_date", LocalDateTime.now());
		params.addValue("create_date", LocalDateTime.now());
		params.addValue("modify_date", LocalDateTime.now());

		KeyHolder keyHolder = new GeneratedKeyHolder();
		int reservationInsertResult = jdbc.update(INSERT, params, keyHolder, new String[] {"id"});

		Number keyValue = keyHolder.getKey();
		int reservationInfoId = keyValue.intValue();

		List<ReservationPrice> prices = reservationParams.getPrices();
		int reservationPriceInsertResult = prices.stream().mapToInt((price) -> {
			MapSqlParameterSource priceParams = new MapSqlParameterSource();
			priceParams.addValue("count", price.getCount());
			priceParams.addValue("productPriceId", price.getProductPriceId());
			priceParams.addValue("reservationInfoId", reservationInfoId);

			return jdbc.update(INSERT_PRICE, priceParams);
		}).sum();

		return reservationInsertResult + reservationPriceInsertResult;
	}
}
