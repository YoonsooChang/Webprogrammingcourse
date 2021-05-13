package kr.or.connect.reservation.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import kr.or.connect.reservation.dto.DisplayInfo;
import kr.or.connect.reservation.dto.ReservationInfo;

public class ReservationInfoMapper implements RowMapper<ReservationInfo> {

	@Override
	public ReservationInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		DisplayInfo displayInfo = new DisplayInfo.Builder(rs.getInt("category_id"),
			rs.getInt("display_info_id"),
			rs.getInt("product_id"))
				.createDate(rs.getDate("create_date").toLocalDate())
				.modifyDate(rs.getDate("modify_date").toLocalDate())
				.categoryName(rs.getString("category_name"))
				.email(rs.getString("email"))
				.homepage(rs.getString("homepage"))
				.openingHours(rs.getString("opening_hours"))
				.placeLot(rs.getString("place_lot"))
				.placeName(rs.getString("place_name"))
				.placeStreet(rs.getString("place_street"))
				.productContent(rs.getString("product_content"))
				.productDescription(rs.getString("product_description"))
				.productEvent(rs.getString("product_event"))
				.telephone(rs.getString("telephone"))
				.build();

		return new ReservationInfo.Builder(displayInfo.getDisplayInfoId(),
			displayInfo, displayInfo.getProductId(), rs.getInt("reservation_info_id"))
				.cancelYn(rs.getBoolean("cancel_yn"))
				.createDate(rs.getDate("reservation_create_date").toLocalDate())
				.modifyDate(rs.getDate("reservation_modify_date").toLocalDate())
				.reservationDate(rs.getDate("reservation_date").toLocalDate())
				.reservationEmail(rs.getString("reservation_email"))
				.reservationName(rs.getString("reservation_name"))
				.reservationTelephone(rs.getString("reservation_telephone"))
				.totalPrice(rs.getInt("total_price"))
				.build();
	}

}
