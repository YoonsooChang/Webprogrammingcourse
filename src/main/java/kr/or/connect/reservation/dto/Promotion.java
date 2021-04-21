package kr.or.connect.reservation.dto;

public class Promotion {
	private Integer id;
	private Integer productId;
	private Integer productImageId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getProductImageId() {
		return productImageId;
	}

	public void setProductImageId(Integer productImageId) {
		this.productImageId = productImageId;
	}

	@Override
	public String toString() {
		return "Promotion [id=" + id + ", productId=" + productId + ", productImageId=" + productImageId + "]";
	}

}
