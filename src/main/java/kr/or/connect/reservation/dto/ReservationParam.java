package kr.or.connect.reservation.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ReservationParam {
	private String reservationName;
	private String reservationTelephone;
	private String reservationEmail;
	private List<Integer> counts;
	private List<Integer> productPriceIds;
	private int displayInfoId;
	private int productId;
	private LocalDateTime reservationDate;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;

	public ReservationParam() {
		LocalDateTime now = LocalDateTime.now();
		reservationDate = now;
		createDate = now;
		modifyDate = now;
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

	public String getReservationEmail() {
		return reservationEmail;
	}

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}

	public List<Integer> getCounts() {
		return counts;
	}

	public void setCounts(List<Integer> counts) {
		this.counts = counts;
	}

	public List<Integer> getProductPriceIds() {
		return productPriceIds;
	}

	public void setProductPriceIds(List<Integer> productPriceIds) {
		this.productPriceIds = productPriceIds;
	}

	public int getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public LocalDateTime getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(LocalDateTime reservationDate) {
		this.reservationDate = reservationDate;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(LocalDateTime modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "ReservationParam [reservationName=" + reservationName + ", reservationTelephone=" + reservationTelephone
			+ ", reservationEmail=" + reservationEmail + ", counts=" + counts + ", productPriceIds=" + productPriceIds
			+ ", displayInfoId=" + displayInfoId + ", productId=" + productId + "]";
	}

}
