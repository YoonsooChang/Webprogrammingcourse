package kr.or.connect.reservation.dto;

import java.time.LocalDate;

public class ReservationInfo {
	private boolean cancelYn;
	private LocalDate createDate;
	private DisplayInfo displayInfo;
	private int displayInfoId;
	private LocalDate modifyDate;
	private int productId;
	private LocalDate reservationDate;
	private String reservationEmail;
	private int reservationInfoId;
	private String reservationName;
	private String reservationTelephone;
	private int totalPrice;

	@Override
	public String toString() {
		return "ReservationInfo [cancelYn=" + cancelYn + ", createDate=" + createDate + ", displayInfo=" + displayInfo
			+ ", displayInfoId=" + displayInfoId + ", modifyDate=" + modifyDate + ", productId=" + productId
			+ ", reservationDate=" + reservationDate + ", reservationEmail=" + reservationEmail + ", reservationInfoId="
			+ reservationInfoId + ", reservationName=" + reservationName + ", reservationTelephone="
			+ reservationTelephone + ", totalPrice=" + totalPrice + "]";
	}

}
