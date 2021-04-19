package kr.or.connect.reservation.dto;

import java.time.LocalDateTime;

public class ProductPrice {
	private LocalDateTime createDate;
	private Double discountRate;
	private LocalDateTime modifyDate;
	private Integer price;
	private String priceTypeName;
	private Integer productId;
	private Integer productPriceId;

	@Override
	public String toString() {
		return "ProductPrice [createDate=" + createDate + ", discountRate=" + discountRate + ", modifyDate="
			+ modifyDate + ", price=" + price + ", priceTypeName=" + priceTypeName + ", productId=" + productId
			+ ", productPriceId=" + productPriceId + "]";
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public Double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(Double discountRate) {
		this.discountRate = discountRate;
	}

	public LocalDateTime getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(LocalDateTime modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getPriceTypeName() {
		return priceTypeName;
	}

	public void setPriceTypeName(String priceTypeName) {
		this.priceTypeName = priceTypeName;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getProductPriceId() {
		return productPriceId;
	}

	public void setProductPriceId(Integer productPriceId) {
		this.productPriceId = productPriceId;
	}
}
