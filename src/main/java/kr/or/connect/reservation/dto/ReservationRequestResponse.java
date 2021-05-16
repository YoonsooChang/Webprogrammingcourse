package kr.or.connect.reservation.dto;

import java.time.LocalDate;
import java.util.List;

public class ReservationRequestResponse {
	private boolean cancelYn;
	private LocalDate createDate;
	private int displayInfoId;
	private LocalDate modifyDate;
	private List<ReservationPrice> prices;
	private int productId;
	private LocalDate reservationDate;
	private String reservatinoEmail;
	private int reservationInfoId;
	private String reservationName;
	private String reservationTelephone;

	public boolean isCancelYn() {
		return cancelYn;
	}

	public void setCancelYn(boolean cancelYn) {
		this.cancelYn = cancelYn;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public int getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public LocalDate getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(LocalDate modifyDate) {
		this.modifyDate = modifyDate;
	}

	public List<ReservationPrice> getPrices() {
		return prices;
	}

	public void setPrices(List<ReservationPrice> prices) {
		this.prices = prices;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public LocalDate getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(LocalDate reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getReservatinoEmail() {
		return reservatinoEmail;
	}

	public void setReservatinoEmail(String reservatinoEmail) {
		this.reservatinoEmail = reservatinoEmail;
	}

	public int getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	public String getReservationTelephone() {
		return reservationTelephone;
	}

	public void setReservationTelephone(String reservationTelephone) {
		this.reservationTelephone = reservationTelephone;
	}

	@Override
	public String toString() {
		return "ReservationRequestResponse [cancelYn=" + cancelYn + ", createDate=" + createDate + ", displayInfoId="
			+ displayInfoId + ", modifyDate=" + modifyDate + ", prices=" + prices + ", productId=" + productId
			+ ", reservationDate=" + reservationDate + ", reservatinoEmail=" + reservatinoEmail + ", reservationInfoId="
			+ reservationInfoId + ", reservationName=" + reservationName + ", reservationTelephone="
			+ reservationTelephone + "]";
	}

}
